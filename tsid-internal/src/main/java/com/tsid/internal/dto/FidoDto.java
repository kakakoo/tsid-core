package com.tsid.internal.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class FidoDto {

    @Builder
    @Getter
    @Setter
    public static class PreRegister {
        private String username;
        private String displayName;
        private String policy;
    }
}
