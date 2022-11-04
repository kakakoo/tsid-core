package com.tsid.internal.util;

public class FidoUtil {

    public static final String SVCUSERNAME = "name";
    public static final String SVCPASSWORD = "pwd";
    // Policies
    public static final String MINIMAL_POLICY = "minimal";
    public static final String MODERATE_POLICY = "moderate";
    public static final String STRICT_POLICY = "strict";
    public static final String STRICT_ANDROID_SAFETYNET_POLICY = "strictAndroidSafetyNet";
    public static final String RESTRICTED_ANDROID_KEY_POLICY = "restrictedAndroidKey";
    public static final String RESTRICTED_TPM_POLICY = "restrictedTpm";
    public static final String RESTRICTED_APPLE_POLICY = "restrictedApple";
    public static final String RESTRICTED_FIPS_POLICY = "restrictedFips";

    public static final String SKFS_JSON_KEY_OPTIONS_USERVERIFICATION = "userVerification";
    public static final String SKFS_JSON_KEY_OPTIONS_RESIDENTKEY = "requireResidentKey";
    public static final String SKFS_JSON_KEY_OPTIONS_ATTACHMENT = "authenticatorAttachment";
    public static final String SKFS_JSON_KEY_OPTIONS_PREFERRED = "preferred";
    public static final String SKFS_JSON_KEY_OPTIONS_AUTHSELECTION = "authenticatorSelection";
    public static final String SKFS_JSON_KEY_OPTIONS_ATTESTATION = "attestation";
    public static final String SKFS_JSON_KEY_OPTIONS_REQUIRED = "required";
    public static final String AUTHORIZATION_PASSWORD = "PASSWORD";
    public static final String AUTHORIZATION_HMAC = "HMAC";
    public static final String API_VERSION = "SK3_0";

    public static final String ATTESTATION = "direct";
    public static final String PROTOCOL = "FIDO2_0";
    public static final String PROTOCOL_VERSION = "1.0";
    public static final String ACCESSKEY = "accesskey";
    public static final String SECRETKEY = "secretkey";

    public static final String PREREGISTER = "preregister";
    public static final String REGISTER = "register";
    public static final String PREAUTHENTICATE = "preauthenticate";
    public static final String AUTHENTICATE = "authenticate";
    public static final String GETKEYINFO = "getkeyinfo";
    public static final String DEREGISTER = "deregister";
}
