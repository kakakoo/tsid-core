package com.tsid.external.util;

import com.tsid.common.exception.InvalidException;
import com.tsid.common.model.enums.ErrorAction;
import com.tsid.common.model.enums.ErrorCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtils {

    private static final List<String> imageContentTypes = Arrays.asList("image/png", "image/jpeg");

    public static String createFileUuidNameWithExtension(String originalFileName) {
        String extension =  getFileExtension(originalFileName);
        return UUID.randomUUID().toString().concat(extension);
    }

    public static String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidException(ErrorCode.E400_INVALID_FILE_FORMAT_EXCEPTION, ErrorAction.TOAST, String.format("잘못된 형식의 파일 (%s) 입니다", fileName));
        }
    }

    public static void validateImageFile(String contentType) {
        if (!imageContentTypes.contains(contentType)) {
            throw new InvalidException(ErrorCode.E400_INVALID_FILE_FORMAT_EXCEPTION, ErrorAction.TOAST, String.format("허용되지 않은 파일 형식 (%s) 입니다", contentType));
        }
    }
}
