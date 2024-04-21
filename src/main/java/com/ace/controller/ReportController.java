package com.ace.controller;

import com.ace.constant.AceEnvironment;
import com.ace.constant.MessageConstants;
import com.ace.controller.common.CommonController;
import com.ace.models.common.AjaxResponse;
import com.ace.models.entity.Reports;
import com.ace.models.info.ReportsInfo;
import com.ace.service.FilesService;
import com.ace.service.ReportsService;
import com.ace.utilities.DateTimeUtil;
import com.ace.utilities.UUID;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;

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
    private final FilesService filesService;
    private final String imagePath;


    @Autowired
    public ReportController(AceEnvironment aceEnvironment, ReportsService reportsService, FilesService filesService) {
        this.reportsService = reportsService;
        this.filesService = filesService;
        this.imagePath = aceEnvironment.getImagesPath();
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
     * 图片上传
     */
    @RequestMapping(value = "/report/upload.html", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject upload(@RequestParam(value = "editormd-image-file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String trueFileName = file.getOriginalFilename();

        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));

        String fileName = DateTimeUtil.getCurrentDateTimeAsFileName() + "-" + UUID.get(8) + suffix;

        String path = request.getSession().getServletContext().getRealPath("/assets/msg/upload/");
        System.out.println(path);

        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }


        JSONObject res = new JSONObject();
       // res.put("url", Constant.WEB_ROOT + "assets/msg/upload/" + fileName);
        res.put("success", 1);
        res.put("message", "upload success!");

        return res;

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

