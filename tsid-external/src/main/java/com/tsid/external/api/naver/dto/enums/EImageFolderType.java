package com.tsid.external.api.naver.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum EImageFolderType {

    COMPANY("사용처", "company/"),
    NOTICE("공지사항", "notice/"),
    QNA("1:1문의", "qna/"),
    SERVER("서버 점검 메세지", "server/"),
    BANNER("배너", "banner/");

    private String description;
    private String code;

}
