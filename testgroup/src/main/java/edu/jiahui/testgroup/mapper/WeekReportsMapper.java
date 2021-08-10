package edu.jiahui.testgroup.mapper;

import edu.jiahui.testgroup.domain.WeekReports;
import edu.jiahui.testgroup.domain.request.SearchReportReq;
import edu.jiahui.testgroup.domain.response.SearchReportRes;
import edu.jiahui.testgroup.domain.response.WeekReportRes;
import java.util.List;


public interface WeekReportsMapper {
    WeekReports selectByPrimaryKey(Integer id);
    void insert(WeekReports weekReports);
    void update(WeekReports weekReports);
    void delete(Integer id);
    List<SearchReportRes.WeekReport> selectByCondition(SearchReportReq req);
}
