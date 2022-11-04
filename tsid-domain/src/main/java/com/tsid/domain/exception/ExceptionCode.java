package com.tsid.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum ExceptionCode {
    // TODO: Need to refine
    NOT_FOUND_ENUM_CODE(500, "T5200", "상태 코드가 존재하지 않습니다.");

//    ATTESTATION_CERTIFICATE_ERROR(1),
//    U2F_ATTESTATION_KEY_NOT_ECC_TYPE(2),
//    U2F_ATTESTATION_USER_KEY_INVALID(3),
//    U2F_ATTESTATION_AAGUID_INVALID(4),
//    SIGNATURE_VERIFICATION_ERROR(4),
//    PACKED_ATTESTATION_ALGORITHM_NOT_MATCHED(5),
//    USER_PUBLIC_KEY_INVALID_KEY_SPEC(6),
//
//    INVALID_ATTESTATION_FORMAT(52),
//    INVALID_AUTHENTICATOR_DATA(53),
//    CREDENTIAL_NOT_INCLUDED(54),
//    CREDENTIAL_NOT_FOUND(55),
//    ASSERTION_SIGNATURE_VERIFICATION_FAIL(56),
//
//    INVALID_ORIGIN(80),
//
//    INTERNAL_SERVER_ERROR(999);


    @Getter private final int status;
    @Getter private final String code;
    @Getter private final String message;


}
