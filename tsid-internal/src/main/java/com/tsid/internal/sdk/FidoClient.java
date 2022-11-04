package com.tsid.internal.sdk;

import com.tsid.common.exception.ApiServerException;
import com.tsid.common.model.enums.ErrorAction;
import com.tsid.common.model.enums.ErrorCode;
import com.tsid.common.util.EncryptUtil;
import com.tsid.internal.dto.FidoDto;
import com.tsid.internal.dto.res.SKSFResponse;
import com.tsid.internal.util.FidoUtil;
import com.tsid.internal.util.UrlUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.tsid.internal.util.FidoUtil.*;

@Component
@RequiredArgsConstructor
public class FidoClient {

    @Value("${tsid.skfs.url}")
    private String SKFS_URL;
    private static final String AUTHENTICATORATTACHMENT = "";
    private static final String REQUIRERESIDENTKEY = "";
    private static final String AUTHTYPE = "HMAC";

    public SKSFResponse preregister(FidoDto.PreRegister request) {

        JsonObjectBuilder payload = Json.createObjectBuilder()
                .add("username", request.getUsername())
                .add("displayname", request.getDisplayName())
                .add("options", getRegOptions(request.getPolicy()))
                .add("extensions", "{}");

        String fidoResonse = callSKFSRestApi(PREREGISTER, payload, getDid(request.getPolicy()));

        SKSFResponse response = new SKSFResponse();
        if (fidoResonse == null) {
            response.setError("True");
        } else {
            response.setResponse(fidoResonse);
            response.setMessage("");
            response.setError("False");
        }

        return response;
    }

    public SKSFResponse register(Map<String, Object> request) {

        String username = MapUtils.getString(request, "username");
        String policy = MapUtils.getString(request, "policy", "");

        String id = MapUtils.getString(request, "id");
        String rawId = MapUtils.getString(request, "rawId");
        String type = MapUtils.getString(request, "type");

        Map<String, Object> resMap = (Map<String, Object>) request.get("response");

        String attestationObject = MapUtils.getString(resMap, "attestationObject");
        String clientDataJSON = MapUtils.getString(resMap, "clientDataJSON");

        JsonObject reg_metadata = Json.createObjectBuilder()
                .add("version", PROTOCOL_VERSION)
                .add("create_location", "Sunnyvale, CA")
                .add("username", username)
                .add("origin", SKFS_URL).build();
        JsonObjectBuilder reg_inner_response = Json.createObjectBuilder()
                .add("attestationObject", attestationObject)
                .add("clientDataJSON", clientDataJSON);
        JsonObject reg_response = Json.createObjectBuilder()
                .add("id", id)
                .add("rawId", rawId)
                .add("response", reg_inner_response)
                .add("type", type).build();
        JsonObjectBuilder payloadBuilder = Json.createObjectBuilder()
                .add("publicKeyCredential", reg_response)
                .add("strongkeyMetadata", reg_metadata);

        String fidoResonse = callSKFSRestApi(REGISTER, payloadBuilder, getDid(policy));

        SKSFResponse response = new SKSFResponse();
        if (fidoResonse == null) {
            response.setError("True");
        } else {
            response.setResponse(fidoResonse);
            response.setMessage("");
            response.setError("False");
        }

        return response;
    }

    public SKSFResponse preAuthenticate(FidoDto.PreRegister request) {

        JsonObjectBuilder payload = Json.createObjectBuilder()
                .add("username", request.getUsername())
                .add("options", getAuthOptions(request.getPolicy()));

        String fidoResonse = callSKFSRestApi(PREAUTHENTICATE, payload, getDid(request.getPolicy()));

        SKSFResponse response = new SKSFResponse();
        if (fidoResonse == null) {
            response.setError("True");
        } else {
            response.setResponse(fidoResonse);
            response.setMessage("");
            response.setError("False");
        }

        return response;
    }

    public SKSFResponse authenticate(Map<String, Object> request) {

        String username = MapUtils.getString(request, "username");
        String policy = MapUtils.getString(request, "policy", "");
        String authFlag = MapUtils.getString(request, "authFlag", "LOGIN");
        String clientUserAgent = MapUtils.getString(request, "clientUserAgent");

        String id = MapUtils.getString(request, "id");
        String rawId = MapUtils.getString(request, "rawId");
        String type = MapUtils.getString(request, "type");

        Map<String, Object> resMap = (Map<String, Object>) request.get("response");

        String authenticatorData = MapUtils.getString(resMap, "authenticatorData", "");
        String signature = MapUtils.getString(resMap, "signature", "");
        String userHandle = MapUtils.getString(resMap, "userHandle", "");
        String clientDataJSON = MapUtils.getString(resMap, "clientDataJSON", "");

        JsonObject auth_metadata = Json.createObjectBuilder()
                .add("version", PROTOCOL_VERSION)
                .add("last_used_location", "Sunnyvale, CA")
                .add("username", username)
                .add("origin", SKFS_URL)
                .add("clientUserAgent", clientUserAgent)
                .build();
        JsonObjectBuilder auth_inner_response = Json.createObjectBuilder()
                .add("authenticatorData", authenticatorData)
                .add("signature", signature)
                .add("userHandle", userHandle)
                .add("clientDataJSON", clientDataJSON);
        JsonObject auth_response = Json.createObjectBuilder()
                .add("id", id)
                .add("rawId", rawId)
                .add("response", auth_inner_response)
                .add("type", type)
                .build();
        JsonObjectBuilder payload = Json.createObjectBuilder()
                .add("publicKeyCredential", auth_response)
                .add("strongkeyMetadata", auth_metadata);

        String fidoResonse = callSKFSRestApi(AUTHENTICATE, payload, getDid(policy));

        SKSFResponse response = new SKSFResponse();
        if (fidoResonse == null) {
            response.setError("True");
        } else {
            String rk = "";
            if (authFlag.equals("CERT")) {
                rk = randomKey();
            }
            response.setResponse(rk);
            response.setMessage("");
            response.setError("False");
        }

        return response;
    }

    public SKSFResponse deregister(FidoDto.PreRegister request) {

        SKSFResponse response = new SKSFResponse();

        JsonObjectBuilder payload = Json.createObjectBuilder()
                .add("username", request.getUsername());

        String fidoResonse = callSKFSRestApi(GETKEYINFO, payload, getDid(request.getPolicy()));
        if (fidoResonse == null) {
            response.setError("True");
            return response;
        }

        JsonArray keyIds = getKeyIdsFromSKFSResponse(fidoResonse);

        for (int keyIndex = 0; keyIndex < keyIds.size(); keyIndex++) {
            String keyid = keyIds.getJsonObject(keyIndex).getString("keyid");

            JsonObjectBuilder deregisterPayload = Json.createObjectBuilder()
                    .add("keyid", keyid);

            callSKFSRestApi(DEREGISTER, deregisterPayload, getDid(request.getPolicy()));
        }

        return response;
    }

    private JsonArray getKeyIdsFromSKFSResponse(String fidoResonse) {
        JsonObject SKFSResponseObject = Json.createReader(new StringReader(fidoResonse)).readObject();
        return SKFSResponseObject.getJsonObject("Response").getJsonArray("keys");
    }

    private String randomKey() {
        Random random = new Random();
        StringBuffer buff = new StringBuffer();
        for(int i=0 ; i<32 ; i++){
            if(random.nextBoolean()){
                buff.append((char)((int)(random.nextInt(26)) + 65));
            } else {
                buff.append(random.nextInt(10));
            }
        }
        return EncryptUtil.sha256Encrypt(buff.toString() + System.currentTimeMillis());
    }

    private static JsonObject getAuthOptions(String policy) {
        JsonObjectBuilder authOptionBuilder = Json.createObjectBuilder();
        switch (policy) {
            case MODERATE_POLICY:
            case MINIMAL_POLICY:
                authOptionBuilder.add(SKFS_JSON_KEY_OPTIONS_USERVERIFICATION, SKFS_JSON_KEY_OPTIONS_PREFERRED);
                break;
            case RESTRICTED_FIPS_POLICY:
            case RESTRICTED_APPLE_POLICY:
            case RESTRICTED_TPM_POLICY:
            case RESTRICTED_ANDROID_KEY_POLICY:
            case STRICT_ANDROID_SAFETYNET_POLICY:
            case STRICT_POLICY:
            default:
                authOptionBuilder.add(SKFS_JSON_KEY_OPTIONS_USERVERIFICATION, SKFS_JSON_KEY_OPTIONS_REQUIRED);
                break;
        }
        return authOptionBuilder.build();
    }

    private JsonObject getRegOptions(String policy) {

        JsonObjectBuilder regOptionBuilder = Json.createObjectBuilder();
        JsonObjectBuilder authSelectBuilder = Json.createObjectBuilder();
        JsonObject authSelect;
        switch (policy) {

            case MODERATE_POLICY:
            case MINIMAL_POLICY:
                if(AUTHENTICATORATTACHMENT != null && !AUTHENTICATORATTACHMENT.isEmpty()){
                    authSelectBuilder.add(SKFS_JSON_KEY_OPTIONS_ATTACHMENT, AUTHENTICATORATTACHMENT);
                }
                if(REQUIRERESIDENTKEY != null && !REQUIRERESIDENTKEY.isEmpty()){
                    authSelectBuilder.add(SKFS_JSON_KEY_OPTIONS_RESIDENTKEY, REQUIRERESIDENTKEY);
                }
                authSelectBuilder.add(SKFS_JSON_KEY_OPTIONS_USERVERIFICATION, SKFS_JSON_KEY_OPTIONS_PREFERRED);
                authSelect = authSelectBuilder.build();
                if(!authSelect.isEmpty()){
                    regOptionBuilder.add(SKFS_JSON_KEY_OPTIONS_AUTHSELECTION, authSelect);
                }
                if(ATTESTATION != null && !ATTESTATION.isEmpty()){
                    regOptionBuilder.add(SKFS_JSON_KEY_OPTIONS_ATTESTATION, ATTESTATION);
                }
                break;
            case RESTRICTED_FIPS_POLICY:
            case RESTRICTED_APPLE_POLICY:
            case RESTRICTED_TPM_POLICY:
            case RESTRICTED_ANDROID_KEY_POLICY:
            case STRICT_ANDROID_SAFETYNET_POLICY:
            case STRICT_POLICY:
            default:
                if(AUTHENTICATORATTACHMENT != null && !AUTHENTICATORATTACHMENT.isEmpty()){
                    authSelectBuilder.add(SKFS_JSON_KEY_OPTIONS_ATTACHMENT, AUTHENTICATORATTACHMENT);
                }
                authSelectBuilder.add(SKFS_JSON_KEY_OPTIONS_RESIDENTKEY, SKFS_JSON_KEY_OPTIONS_REQUIRED);
                authSelectBuilder.add(SKFS_JSON_KEY_OPTIONS_USERVERIFICATION, SKFS_JSON_KEY_OPTIONS_REQUIRED);
                authSelect = authSelectBuilder.build();
                if(!authSelect.isEmpty()){
                    regOptionBuilder.add(SKFS_JSON_KEY_OPTIONS_AUTHSELECTION, authSelect);
                }
                if(ATTESTATION != null && !ATTESTATION.isEmpty()){
                    regOptionBuilder.add(SKFS_JSON_KEY_OPTIONS_ATTESTATION, ATTESTATION);
                }
                break;
        }
        return regOptionBuilder.build();
    }

    private static int getDid(String policy) {
        int SKFSDID = 0;

        switch (policy) {
            case FidoUtil.RESTRICTED_FIPS_POLICY:
                SKFSDID = 8;
                break;
            case FidoUtil.RESTRICTED_APPLE_POLICY:
                SKFSDID = 7;
                break;
            case FidoUtil.RESTRICTED_ANDROID_KEY_POLICY:
                SKFSDID = 6;
                break;
            case FidoUtil.RESTRICTED_TPM_POLICY:
                SKFSDID = 5;
                break;
            case FidoUtil.STRICT_ANDROID_SAFETYNET_POLICY:
                SKFSDID = 4;
                break;
            case FidoUtil.STRICT_POLICY:
                SKFSDID = 3;
                break;
            case FidoUtil.MODERATE_POLICY:
                SKFSDID = 2;
                break;
            case FidoUtil.MINIMAL_POLICY:
                SKFSDID = 1;
                break;
            default:
                break;
        }
        return SKFSDID;
    }

    private String callSKFSRestApi(String actionType, JsonObjectBuilder payload, int did) {

        String result = "";
        try {
            JsonObjectBuilder svcinfoBuilder = Json.createObjectBuilder()
                    .add("did", did)
                    .add("protocol", PROTOCOL);

            if (AUTHTYPE.equalsIgnoreCase(FidoUtil.AUTHORIZATION_HMAC)) {
                svcinfoBuilder.add("authtype", FidoUtil.AUTHORIZATION_HMAC);
            } else {
                svcinfoBuilder
                        .add("authtype", FidoUtil.AUTHORIZATION_PASSWORD)
                        .add("svcusername", SVCUSERNAME)
                        .add("svcpassword", SVCPASSWORD);
            }

            JsonObject body = Json.createObjectBuilder()
                    .add("svcinfo", svcinfoBuilder)
                    .add("payload", payload).build();

            String currentDate = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH).format(new Date());
            String payloadHash = calculateHash(body.getJsonObject("payload").toString());
            String hmac = "";
            String targetUrl = "";

            if (actionType.equals(PREREGISTER)) {
                targetUrl = UrlUtil.FIDO_PRE_REGISTER;
                String requestToHmac = "POST" + "\n"
                        + payloadHash + "\n"
                        + MediaType.APPLICATION_JSON + "\n"
                        + currentDate + "\n"
                        + API_VERSION + "\n"
                        + targetUrl;
                hmac = calculateHMAC(SECRETKEY, requestToHmac);
            } else if (actionType.equals(REGISTER)) {
                targetUrl = UrlUtil.FIDO_REGISTER;
                String requestToHmac = "POST" + "\n"
                        + payloadHash + "\n"
                        + MediaType.APPLICATION_JSON + "\n"
                        + currentDate + "\n"
                        + API_VERSION + "\n"
                        + targetUrl;
                hmac = calculateHMAC(SECRETKEY, requestToHmac);
            } else if (actionType.equals(PREAUTHENTICATE)) {
                targetUrl = UrlUtil.FIDO_PRE_AUTHENTICATE;
                String requestToHmac = "POST" + "\n"
                        + payloadHash + "\n"
                        + MediaType.APPLICATION_JSON + "\n"
                        + currentDate + "\n"
                        + API_VERSION + "\n"
                        + targetUrl;
                hmac = calculateHMAC(SECRETKEY, requestToHmac);
            } else if (actionType.equals(AUTHENTICATE)) {
                targetUrl = UrlUtil.FIDO_AUTHENTICATE;
                String requestToHmac = "POST" + "\n"
                        + payloadHash + "\n"
                        + MediaType.APPLICATION_JSON + "\n"
                        + currentDate + "\n"
                        + API_VERSION + "\n"
                        + targetUrl;
                hmac = calculateHMAC(SECRETKEY, requestToHmac);
            } else if (actionType.equals(GETKEYINFO)) {
                targetUrl = UrlUtil.FIDO_GETKEYINFO;
                String requestToHmac = "POST" + "\n"
                        + payloadHash + "\n"
                        + MediaType.APPLICATION_JSON + "\n"
                        + currentDate + "\n"
                        + API_VERSION + "\n"
                        + targetUrl;
                hmac = calculateHMAC(SECRETKEY, requestToHmac);
            } else if (actionType.equals(DEREGISTER)) {
                targetUrl = UrlUtil.FIDO_DE_REGISTER;
                String requestToHmac = "POST" + "\n"
                        + payloadHash + "\n"
                        + MediaType.APPLICATION_JSON + "\n"
                        + currentDate + "\n"
                        + API_VERSION + "\n"
                        + targetUrl;
                hmac = calculateHMAC(SECRETKEY, requestToHmac);
            }

            URL url = new URL(SKFS_URL + targetUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Date", currentDate);
            conn.setRequestProperty("Authorization", "HMAC " + ACCESSKEY + ":" + hmac);
            conn.setRequestProperty("strongkey-api-version", API_VERSION);
            conn.setRequestProperty("strongkey-content-sha256", payloadHash);
            conn.setRequestProperty("Content-Type", "application/json");

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            wr.write(body.toString());
            wr.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                result = output;
            }

            conn.disconnect();

        } catch (Exception e) {
            return null;
        }

        return result;
    }

    private static String calculateHash(String contentToEncode) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(contentToEncode.getBytes());
            return Base64.getEncoder().encodeToString(digest.digest());
        } catch (Exception e) {
            throw new ApiServerException(ErrorCode.E500_INTERNAL_SERVER_ENCRYPT, ErrorAction.NONE, "calculateHash error");
        }
    }

    private static String calculateHMAC(String secret, String data) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(DatatypeConverter.parseHexBinary(secret), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(rawHmac);
        } catch (Exception e) {
            throw new ApiServerException(ErrorCode.E500_INTERNAL_SERVER_ENCRYPT, ErrorAction.NONE, "calculateHMAC error");
        }
    }

}
