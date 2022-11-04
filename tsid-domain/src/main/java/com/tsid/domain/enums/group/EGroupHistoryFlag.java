package com.tsid.domain.enums.group;

import com.tsid.domain.exception.ConvertException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum EGroupHistoryFlag {
    INVITE("초대", "I"),
    INVITE_DENY("초대 거절", "ID"),
    INVITE_CANCEL("초대 취소", "IC"),
    MAKE("그룹 생성", "M"),
    ACCEPT("초대 수락", "A"),
    REQUEST("해제 신청", "R"),
    REQUEST_DENY("해제 거절", "RD"),
    REQUEST_CANCEL("해제 취소", "RC"),
    WITHDRAW("해제","W");

    private String description;
    private String code;

    public static EGroupHistoryFlag ofCode(String code){

        return Arrays.stream(EGroupHistoryFlag.values())
                .filter(v -> v.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new ConvertException(String.format("상태코드에 code=[%s]가 존재하지 않습니다.", code)));
    }
}
