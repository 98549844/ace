package com.controller;

import com.controller.common.CommonController;
import com.models.entity.Reports;
import com.models.entity.Users;
import com.models.info.ReportsInfo;
import com.service.ReportsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
        log.info("access report/list");
        ModelAndView modelAndView = super.page("ace/modules/report/report-list");

        ReportsInfo reportsInfo = reportsService.getReportById(9000L);
        modelAndView.addObject("report", reportsInfo);

        return modelAndView;
    }



}

