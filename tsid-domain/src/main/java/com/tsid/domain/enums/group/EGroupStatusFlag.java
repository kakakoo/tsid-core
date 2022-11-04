package com.tsid.domain.enums.group;

import com.tsid.domain.exception.ConvertException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum EGroupStatusFlag {
    INVITE( "초대", "I"),
    CANCEL("초대 거절","C"),
    EXPIRED("초대 만료","E"),
    WAIT("대기", "W"),
    ACTIVE("정상", "A"),
    RELEASE("그룹 해제 요청중","R"),
    WITHDRAW("그룹 제거","H"),
    WITHDRAW_MAKER("그룹장 회원 탈퇴","Z"),
    WITHDRAW_USER("회원 탈퇴","X");

    private String description;
    private String code;

    public static EGroupStatusFlag ofCode(String code){

        return Arrays.stream(EGroupStatusFlag.values())
                .filter(v -> v.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new ConvertException(String.format("상태코드에 code=[%s]가 존재하지 않습니다.", code)));
    }
}
