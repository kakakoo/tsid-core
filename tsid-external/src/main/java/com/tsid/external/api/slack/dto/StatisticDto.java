package com.tsid.external.api.slack.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StatisticDto {
    private int joinUser;
    private int certRequest;
    private int certDone;

    private String date;
    private Long weeklyExecute;
    private Long monthlyExecute;
}
