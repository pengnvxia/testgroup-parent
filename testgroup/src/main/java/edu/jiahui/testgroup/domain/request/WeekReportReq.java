package edu.jiahui.testgroup.domain.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
public class WeekReportReq {

    private Integer id;

    private String startTime;

    private String endTime;

    @NotEmpty(message="thisWeek不能为空")
    private List<Report> thisWeek;

    @NotEmpty(message="nextWeek不能为空")
    private List<Report> nextWeek;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Report {

        private Integer detailId;

        private String system;

        private Integer priority;

        private String jobContent;

        private String problem;
    }
}
