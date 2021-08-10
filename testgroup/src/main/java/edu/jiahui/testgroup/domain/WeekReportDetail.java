package edu.jiahui.testgroup.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeekReportDetail {

    private Integer id;

    private String system;

    private Integer priority;

    private Integer isThisWeek;

    private Integer reportId;

    private String createdBy;

    private String updatedBy;

    private Date createdAt;

    private Date updatedAt;

    private String jobContent;

    private String problem;
}
