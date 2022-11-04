package com.tsid.internal.dto.req;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class EmailRequest {

    @Getter
    @Builder
    public static class QnaEmail{
        private String title;
        private String text;
    }

    @Getter
    @Builder
    public static class Email{
        private List<String> email;
        private String title;
        private String text;
    }
}
