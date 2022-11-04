package com.tsid.domain.enums.group;

import com.tsid.domain.exception.ConvertException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum EUpdateFlag {
    DELEGATE("위임", "D"),
    TO_REFERRER("참조자로", "R"),
    WITHDRAW("해제", "W"),
    DESTROY( "삭제", "X");

    private String description;
    private String code;

    public static EUpdateFlag ofCode(String code){

        return Arrays.stream(EUpdateFlag.values())
                .filter(v -> v.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new ConvertException(String.format("상태코드에 code=[%s]가 존재하지 않습니다.", code)));
    }

    public static EUpdateFlag ofName(String name){

        return Arrays.stream(EUpdateFlag.values())
                .filter(v -> v.name().equals(name))
                .findAny()
                .orElseThrow(() -> new ConvertException(String.format("상태코드에 code=[%s]가 존재하지 않습니다.", name)));
    }
}
