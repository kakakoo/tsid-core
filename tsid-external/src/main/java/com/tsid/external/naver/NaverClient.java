package com.tsid.external.naver;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.tsid.common.exception.InternalServerException;
import com.tsid.common.model.enums.ErrorAction;
import com.tsid.common.model.enums.ErrorCode;
import com.tsid.external.api.naver.dto.enums.EImageFolderType;
import com.tsid.external.util.FileUtils;
import com.tsid.external.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class NaverClient {

    @Value("${naver.region}")
    private String REGION;

    @Value("${naver.endpoint}")
    private String END_POINT;

    @Value("${naver.api.access-key}")
    private String ACCESS_KEY;

    @Value("${naver.api.secret-key}")
    private String SECRET_KEY;

    @Value("${naver.bucket}")
    private String BUCKET;

    private ObjectMetadata createObjectMetadata(MultipartFile file) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
//        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());
        return objectMetadata;
    }

    public String upload(MultipartFile file, EImageFolderType folder){
        FileUtils.validateImageFile(file.getContentType());
        AmazonS3 amazonS3Client = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(END_POINT, REGION))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY)))
                .build();
        String bucketKey = FileUtils.createFileUuidNameWithExtension(file.getOriginalFilename());
        if (folder != null) {
            bucketKey = folder.getCode() + bucketKey;
        }
        ObjectMetadata objectMetadata = createObjectMetadata(file);
        String updateImage = END_POINT + BUCKET + File.separator + bucketKey;
        try {
            InputStream inputStream = file.getInputStream();
            if(EImageFolderType.COMPANY.equals(folder)){
                inputStream = ImageUtil.resize(bucketKey, inputStream, 300, 300);
            }
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET, bucketKey, inputStream, objectMetadata);
            amazonS3Client.putObject(putObjectRequest);
            amazonS3Client.setObjectAcl(BUCKET, bucketKey, CannedAccessControlList.PublicRead);
        } catch (AmazonS3Exception e) {
            throw new InternalServerException(ErrorCode.E500_INTERNAL_SERVER, ErrorAction.NONE, e.getErrorMessage());
        } catch(SdkClientException e) {
            throw new InternalServerException(ErrorCode.E500_INTERNAL_SERVER, ErrorAction.NONE, e.getMessage());
        } catch (IOException e) {
            throw new InternalServerException(ErrorCode.E500_INTERNAL_SERVER, ErrorAction.NONE, e.getMessage());
        }

        return updateImage;
    }

}

