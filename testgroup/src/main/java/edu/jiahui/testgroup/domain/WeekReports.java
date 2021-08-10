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
public class WeekReports {

    private Integer id;

    private String startTime;

    private String endTime;

    private String createdBy;

    private String updatedBy;

    private Date createdAt;

    private Date updatedAt;
}
