package edu.jiahui.testgroup.domain.request;

import edu.jiahui.testgroup.model.bo.BaseBo;
import lombok.Data;
import java.util.Date;

@Data
public class SearchReportReq extends BaseBo {

    private String startTime;

    private String endTime;

    private Integer userId;

}
