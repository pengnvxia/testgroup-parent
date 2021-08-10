package edu.jiahui.testgroup.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.jiahui.framework.threadlocal.ParameterThreadLocal;
import edu.jiahui.testgroup.domain.WeekReportDetail;
import edu.jiahui.testgroup.domain.WeekReports;
import edu.jiahui.testgroup.domain.request.SearchReportReq;
import edu.jiahui.testgroup.domain.request.WeekReportReq;
import edu.jiahui.testgroup.domain.response.SearchReportRes;
import edu.jiahui.testgroup.domain.response.WeekReportRes;
import edu.jiahui.testgroup.mapper.WeekReportDetailMapper;
import edu.jiahui.testgroup.mapper.WeekReportsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class WeekReportService {

    @Resource
    private WeekReportsMapper weekReportsMapper;

    @Resource
    private WeekReportDetailMapper weekReportDetailMapper;

    public void addWeekReport(WeekReportReq req){
        WeekReports weekReports=WeekReports.builder()
                .startTime(req.getStartTime())
                .endTime(req.getEndTime())
                .createdBy(ParameterThreadLocal.getUid())
                .build();
        //添加周报数据
        weekReportsMapper.insert(weekReports);
        List<WeekReportDetail> weekReportDetailList=new ArrayList<>();
        //获取本周信息
        for (WeekReportReq.Report report: req.getThisWeek()){
            WeekReportDetail detail= WeekReportDetail.builder()
                    .system(report.getSystem())
                    .priority(report.getPriority())
                    .jobContent(report.getJobContent())
                    .problem(report.getProblem())
                    .isThisWeek(1)
                    .reportId(weekReports.getId())
                    .createdBy(ParameterThreadLocal.getUid())
                    .build();
            weekReportDetailList.add(detail);
        }
        //获取下周信息
        for (WeekReportReq.Report report: req.getNextWeek()){
            WeekReportDetail detail= WeekReportDetail.builder()
                    .system(report.getSystem())
                    .priority(report.getPriority())
                    .jobContent(report.getJobContent())
                    .problem(report.getProblem())
                    .isThisWeek(0)
                    .reportId(weekReports.getId())
                    .createdBy(ParameterThreadLocal.getUid())
                    .build();
            weekReportDetailList.add(detail);
        }

        //添加周报详情数据
        weekReportDetailMapper.insert(weekReportDetailList);
    }

    public void editWeekReport(WeekReportReq req,Integer id){
        WeekReports weekReports=WeekReports.builder()
                .startTime(req.getStartTime())
                .endTime(req.getEndTime())
                .updatedBy(ParameterThreadLocal.getUid())
                .id(id)
                .build();
        List<WeekReportDetail> weekReportDetailList= new ArrayList<>();
        List<Integer> reportIds= new ArrayList<>();
        for(WeekReportReq.Report report: req.getThisWeek()){
            if(report.getDetailId()!=null){
                WeekReportDetail weekReportDetail=WeekReportDetail.builder()
                        .id(report.getDetailId())
                        .system(report.getSystem())
                        .priority(report.getPriority())
                        .jobContent(report.getJobContent())
                        .problem(report.getProblem())
                        .isThisWeek(1)
                        .reportId(id)
                        .updatedBy(ParameterThreadLocal.getUid())
                        .build();
                weekReportDetailMapper.update(weekReportDetail);
                reportIds.add(report.getDetailId());
            }else {
                WeekReportDetail weekReportDetail=WeekReportDetail.builder()
                        .system(report.getSystem())
                        .priority(report.getPriority())
                        .jobContent(report.getJobContent())
                        .problem(report.getProblem())
                        .isThisWeek(1)
                        .reportId(id)
                        .updatedBy(ParameterThreadLocal.getUid())
                        .build();
                weekReportDetailList.add(weekReportDetail);
            }
        }
        for(WeekReportReq.Report report: req.getNextWeek()){
            if(report.getDetailId()!=null){
                WeekReportDetail weekReportDetail=WeekReportDetail.builder()
                        .id(report.getDetailId())
                        .system(report.getSystem())
                        .priority(report.getPriority())
                        .jobContent(report.getJobContent())
                        .problem(report.getProblem())
                        .isThisWeek(0)
                        .reportId(id)
                        .updatedBy(ParameterThreadLocal.getUid())
                        .build();
                weekReportDetailMapper.update(weekReportDetail);
                reportIds.add(report.getDetailId());
            }else {
                WeekReportDetail weekReportDetail=WeekReportDetail.builder()
                        .system(report.getSystem())
                        .priority(report.getPriority())
                        .jobContent(report.getJobContent())
                        .problem(report.getProblem())
                        .isThisWeek(0)
                        .reportId(id)
                        .updatedBy(ParameterThreadLocal.getUid())
                        .build();
                weekReportDetailList.add(weekReportDetail);
            }
        }
        weekReportDetailMapper.deleteNotIn(reportIds,id);
        if(weekReportDetailList.size()>0){
            weekReportDetailMapper.insert(weekReportDetailList);
        }
        weekReportsMapper.update(weekReports);
    }

    public WeekReportRes weekReportDetail(Integer id){
        List<WeekReportDetail> thisWeekReportList=weekReportDetailMapper.selectByReportId(id,1);
        List<WeekReportRes.Report> thisWeeKResList= new ArrayList<>();
        for(WeekReportDetail detail: thisWeekReportList){
            WeekReportRes.Report report=WeekReportRes.Report.builder()
                    .detailId(detail.getId())
                    .priority(detail.getPriority())
                    .jobContent(detail.getJobContent())
                    .problem(detail.getProblem())
                    .system(detail.getSystem())
                    .build();
            thisWeeKResList.add(report);
        }
        List<WeekReportDetail> nextWeekReportList=weekReportDetailMapper.selectByReportId(id,0);
        List<WeekReportRes.Report> nextWeeKResList= new ArrayList<>();
        for(WeekReportDetail detail: nextWeekReportList){
            WeekReportRes.Report report=WeekReportRes.Report.builder()
                    .detailId(detail.getId())
                    .priority(detail.getPriority())
                    .jobContent(detail.getJobContent())
                    .problem(detail.getProblem())
                    .system(detail.getSystem())
                    .build();
            nextWeeKResList.add(report);
        }
        WeekReports weekReports=weekReportsMapper.selectByPrimaryKey(id);
        WeekReportRes res=WeekReportRes.builder()
                .id(weekReports.getId())
                .startTime(weekReports.getStartTime())
                .endTime(weekReports.getEndTime())
                .thisWeek(thisWeeKResList)
                .nextWeek(nextWeeKResList)
                .build();
        return res;
    }

    public void deleteWeekReport(Integer id){
        weekReportDetailMapper.deleteByReportId(id);
        weekReportsMapper.delete(id);
    }

    public SearchReportRes searchWeekReport(SearchReportReq req){
        SearchReportRes res= new SearchReportRes();
        PageHelper.startPage(req.currentifPage(),req.sizeif());
        List<SearchReportRes.WeekReport> weekReportList= weekReportsMapper.selectByCondition(req);
        PageInfo<SearchReportRes.WeekReport> pageInfo= new PageInfo<SearchReportRes.WeekReport>(weekReportList);
        res.setTotal(pageInfo.getTotal());
        if(weekReportList.size()<=0){
            return res;
        }
        for(SearchReportRes.WeekReport weekReport: weekReportList){
            List<WeekReportDetail> weekReportDetailList= weekReportDetailMapper.selectByReportId(weekReport.getId(),1);
            List<SearchReportRes.Report> thisWeekReportList= new ArrayList<>();
            for(WeekReportDetail detail: weekReportDetailList){
                SearchReportRes.Report report= SearchReportRes.Report.builder()
                        .detailId(detail.getId())
                        .system(detail.getSystem())
                        .priority(detail.getPriority())
                        .jobContent(detail.getJobContent())
                        .problem(detail.getProblem())
                        .build();
                thisWeekReportList.add(report);
            }
            weekReportDetailList= weekReportDetailMapper.selectByReportId(weekReport.getId(),0);
            List<SearchReportRes.Report> nextWeekReportList= new ArrayList<>();
            for(WeekReportDetail detail: weekReportDetailList){
                SearchReportRes.Report report= SearchReportRes.Report.builder()
                        .detailId(detail.getId())
                        .system(detail.getSystem())
                        .priority(detail.getPriority())
                        .jobContent(detail.getJobContent())
                        .problem(detail.getProblem())
                        .build();
                nextWeekReportList.add(report);
            }
            weekReport.setThisWeek(thisWeekReportList);
            weekReport.setNextWeek(nextWeekReportList);
        }
        res.setWeekReportList(weekReportList);
        return res;
    }

}
