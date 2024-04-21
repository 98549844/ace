package com.ace.controller;

import com.ace.constant.MessageConstants;
import com.ace.controller.common.CommonController;
import com.ace.models.common.AjaxResponse;
import com.ace.models.entity.Reports;
import com.ace.models.info.ReportsInfo;
import com.ace.service.ReportsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Classname: ReportController
 * @Date: 2022/11/13 上午 01:16
 * @Author: kalam_au
 * @Description:
 */


@Controller
@RequestMapping("/ace")
public class ReportController extends CommonController {
    private static final Logger log = LogManager.getLogger(ReportController.class.getName());

    private final ReportsService reportsService;


    @Autowired
    public ReportController(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    @RequestMapping(value = "/report/list.html", method = RequestMethod.GET)
    public ModelAndView getReports() {
        log.info("access reports/list");
        ModelAndView modelAndView = super.page("ace/modules/reports/report-list");
        modelAndView.addObject("reports", reportsService.getReportList());
        return modelAndView;
    }

    /**
     * @return
     */
    @RequestMapping(value = "/report/newIssue.html", method = RequestMethod.GET)
    public ModelAndView newIssue() {
        log.info("access reports/newIssue.html");
        ModelAndView modelAndView = super.page("ace/modules/reports/new-issue");
        return modelAndView;
    }

    /**
     * @param reports
     * @return
     */
    @RequestMapping(value = "/report/commitNewIssue", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse commitIssue(@ModelAttribute Reports reports) {
        log.info("access report/commitIssue => create new issue");
        reports.setStatus(Reports.NEW);
        reportsService.save(reports);

        return AjaxResponse.success(MessageConstants.SUCCESS);
    }


    /**
     * 未完成
     *
     * @param reportId
     * @return
     */
    @RequestMapping(value = "/report/reportId.html/{reportId}", method = RequestMethod.GET)
    public ModelAndView getReportInfoById(@PathVariable(value = "reportId") Long reportId) {
        log.info("access reports/list {} => update issue", reportId);
        ModelAndView modelAndView = super.page("ace/modules/reports/reports");
        ReportsInfo reportsInfo = reportsService.getReportInfoById(reportId);
        modelAndView.addObject("report", reportsInfo);
        return modelAndView;
    }


    /**
     * 未完成
     *
     * @param reportsInfo
     * @return
     */
    @RequestMapping(value = "/report/submit.html", method = RequestMethod.POST)
    public ModelAndView submit(@ModelAttribute ReportsInfo reportsInfo) {
        log.info("access reports/submit.html");
        ModelAndView modelAndView = super.page("ace/modules/reports/reports");
        reportsInfo = reportsService.saveAndFlush(reportsInfo);

        modelAndView.addObject("report", reportsInfo);
        return modelAndView;
    }

    /**
     * 未完成
     *
     * @param reportId
     * @return
     */
    @RequestMapping(value = "/report/delete.html/{reportId}", method = RequestMethod.GET)
    @ResponseBody
    public boolean deleteReportById(@PathVariable Long reportId) {
        log.info("access reports/delete reportId: {}", reportId);
        reportsService.deleteById(reportId);
        return true;
    }

}

