package com.ace.controller;

import com.ace.constant.AceEnvironment;
import com.ace.constant.MessageConstants;
import com.ace.controller.common.CommonController;
import com.ace.models.common.RespResult;
import com.ace.models.entity.Files;
import com.ace.models.entity.Reports;
import com.ace.models.info.ReportsInfo;
import com.ace.service.FilesService;
import com.ace.service.ReportsService;
import com.ace.utilities.FastJson2Util;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

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
    private final String filePath;


    @Autowired
    public ReportController(AceEnvironment aceEnvironment, ReportsService reportsService, FilesService filesService) {
        this.reportsService = reportsService;
        this.filesService = filesService;
        this.filePath = aceEnvironment.getFilePath();
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

    @RequestMapping(value = "/report/search.html", method = RequestMethod.GET)
    @ResponseBody
    public RespResult reportSearch(@RequestParam(value = "level") String level, @RequestParam(value = "criteria") String criteria) {
        log.info("access report/search.html");
        Reports reports = new Reports();
        reports.setLevel(level);
        return RespResult.success(reportsService.search(reports, criteria));
    }


    /**
     * @param reports
     * @return
     */
    @RequestMapping(value = "/report/commitNewIssue", method = RequestMethod.POST)
    @ResponseBody
    public RespResult commitIssue(@ModelAttribute Reports reports) {
        log.info("access report/commitIssue => create new issue");
        reports.setStatus(Reports.NEW);
        reportsService.save(reports);

        return RespResult.success(MessageConstants.SUCCESS);
    }

    /**
     * 上传
     */
    @RequestMapping(value = "/report/upload.html", method = RequestMethod.POST)
    @ResponseBody
    public String uploadImg(HttpServletRequest request, @RequestParam(value = "editormd-image-file", required = false) MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        if (file != null) {
            try {
                Files f = filesService.upload(file, null, filePath);
                String preUrl;
                if (f.getType().equals(Files.IMAGE)) {
                    preUrl = "/ace/image/get/";
                } else if (f.getType().equals(Files.VIDEO)) {
                    preUrl = "/ace/video/play/";
                } else {
                    throw new RuntimeException("未知的文件类型, 无法预览, 文件后缀为: " + f.getExt());
                }
                String filename = preUrl + f.getFileName() + f.getExt();
                map.put("success", 1); //设置回显的数据 0 表示上传失败，1 表示上传成功
                map.put("message", "上传成功"); //提示的信息，上传成功或上传失败及错误信息等
                map.put("url", filename); //图片回显的url 上传成功时才返回
            } catch (Exception e) {
                map.put("success", 0);
                map.put("message", "上传失败");
                e.printStackTrace();
            }
        }
        return FastJson2Util.ObjectToJson(map);
        //上传图片返回url问题解决, 但没有心机调整正确的url. 有心机再搞
        //https://www.jianshu.com/p/5d654cf267d9  Editor.md图片粘贴插入插件的开发
        // https://www.codehui.net/info/39.html Editor.md图片粘贴插入插件的开发
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

