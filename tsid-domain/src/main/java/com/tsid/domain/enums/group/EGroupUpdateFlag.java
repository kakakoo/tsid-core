package com.tsid.domain.enums.group;

import com.tsid.domain.exception.ConvertException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum EGroupUpdateFlag {
    PROGRESS("진행중", "P"),
    DONE("완료", "D"),
    CANCEL("취소", "C"),
    EXPIRED("만료", "E"),
    REJECT( "거절", "R");

    private String description;
    private String code;

    public static EGroupUpdateFlag ofCode(String code){

        return Arrays.stream(EGroupUpdateFlag.values())
                .filter(v -> v.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new ConvertException(String.format("상태코드에 code=[%s]가 존재하지 않습니다.", code)));
    }
}
