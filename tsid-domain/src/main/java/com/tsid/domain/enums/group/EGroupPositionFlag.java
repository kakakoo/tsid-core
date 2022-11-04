package com.tsid.domain.enums.group;

import com.tsid.domain.exception.ConvertException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum EGroupPositionFlag {
    MAKER("그룹장", "M"),
    CONSENTER("동의자", "C"),
    REFERRER( "참조자", "R");

    private final String description;
    private final String code;

    public static EGroupPositionFlag ofCode(String code) {

        return Arrays.stream(EGroupPositionFlag.values())
                .filter(v -> v.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new ConvertException(String.format("상태코드에 code=[%s]가 존재하지 않습니다.", code)));
    }
}
