package edu.jiahui.testgroup.controller;

import edu.jiahui.framework.util.ResultCode;
import edu.jiahui.testgroup.domain.request.SearchReportReq;
import edu.jiahui.testgroup.domain.request.WeekReportReq;
import edu.jiahui.testgroup.domain.response.SearchReportRes;
import edu.jiahui.testgroup.domain.response.WeekReportRes;
import edu.jiahui.testgroup.service.WeekReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/weekReport")
@Slf4j
public class WeekReportController {
    @Autowired
    private WeekReportService weekReportService;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResultCode addWeekReport(@RequestBody @Valid WeekReportReq req) {
        try{
            weekReportService.addWeekReport(req);
        }catch (Exception e){
            log.error("添加周报异常:{}",e.getMessage());
            return ResultCode.getFailure(null,"服务器繁忙，请稍后重试！");
        }
        return  ResultCode.getSuccessReturn(null, "添加成功！", null);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/edit/{id}")
    public ResultCode editWeekReport(@RequestBody @Valid WeekReportReq req,@PathVariable("id") Integer id){
        try{
            weekReportService.editWeekReport(req,id);
        }catch (Exception e){
            log.error("编辑周报异常:{}",e.getMessage());
            return ResultCode.getFailure(null,"服务器繁忙，请稍后重试！");
        }
        return ResultCode.getSuccessReturn(null,"更新成功！",null);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/detail/{id}")
    public ResultCode weekReportDetail(@PathVariable("id") Integer id){
        WeekReportRes rep= new WeekReportRes();
        try{
            rep=weekReportService.weekReportDetail(id);
        }catch (Exception e){
            log.error("查询周报详情异常:{}",e.getMessage());
            return ResultCode.getFailure(null,"服务器繁忙，请稍后重试！");
        }
        return ResultCode.getSuccessReturn(null,"查询成功！",rep);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public ResultCode delteWeekReport(@PathVariable("id") Integer id){
        try{
            weekReportService.deleteWeekReport(id);
        }catch (Exception e){
            log.error("删除周报详情异常：{}",e.getMessage());
            return ResultCode.getFailure(null,"服务器繁忙，请稍后重试！");
        }
        return ResultCode.getSuccessReturn(null,"删除成功!",null);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/list")
    public ResultCode WeekReportList(@RequestBody @Valid SearchReportReq req){
        SearchReportRes res= new SearchReportRes();
        try{
            res=weekReportService.searchWeekReport(req);
        }catch (Exception e){
            log.error("查询周报异常：{}",e.getMessage());
            return ResultCode.getFailure(null,"服务器繁忙，请稍后重试！");
        }
        return ResultCode.getSuccessReturn(null,"查询成功！",res);
    }
}
