package edu.jiahui.testgroup.domain.response;

import edu.jiahui.testgroup.domain.request.WeekReportReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeekReportRes {

    private Integer id;

    private String startTime;

    private String endTime;

    private List<Report> thisWeek;

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
