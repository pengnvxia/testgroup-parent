package edu.jiahui.testgroup.domain.response;

import edu.jiahui.testgroup.model.bo.BaseBo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class SearchReportRes{

    private List<WeekReport> weekReportList;

    private Long total;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WeekReport{

        private Integer id;

        private String startTime;

        private String endTime;
        
        private String createdBy;

        private List<Report> thisWeek;

        private List<Report> nextWeek;
    }



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
