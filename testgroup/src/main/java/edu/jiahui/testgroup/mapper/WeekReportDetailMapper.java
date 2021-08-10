package edu.jiahui.testgroup.mapper;

import edu.jiahui.testgroup.domain.WeekReportDetail;
import java.util.List;

import edu.jiahui.testgroup.domain.response.SearchReportRes;
import edu.jiahui.testgroup.domain.response.WeekReportRes;
import org.apache.ibatis.annotations.Param;

public interface WeekReportDetailMapper {
//    WeekReportDetail selectByPrimaryKey(Integer id);
    void insert(@Param("list") List<WeekReportDetail> weekReportDetailList);
    void update(WeekReportDetail weekReportDetail);
    void deleteById(Integer id);
    void deleteByReportId(Integer reportId);
    void deleteNotIn(@Param("list") List<Integer> ids,Integer reportId);
    List<WeekReportDetail> selectByReportId(Integer reportId, Integer isThisWeek);
}
