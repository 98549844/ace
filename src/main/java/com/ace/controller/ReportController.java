package com.ace.controller;

import com.ace.constant.AceEnvironment;
import com.ace.constant.MessageConstants;
import com.ace.controller.common.CommonController;
import com.ace.models.common.RespResult;
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
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
     * 图片上传
     */
    @RequestMapping(value = "/report/upload.html", method = RequestMethod.POST)
    @ResponseBody
    public String uploadImg(HttpServletRequest request, @RequestParam(value = "editormd-image-file", required = false) MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        if (file != null) {
            //获取此项目的tomcat路径
            String webapp = request.getSession().getServletContext().getRealPath("/");
            try {
                //获取文件名
                String filename = file.getOriginalFilename();
                java.util.UUID uuid = UUID.randomUUID();
                String name = "";
                if (filename != null) {
                    name = filename.substring(filename.lastIndexOf(".")); //获取文件后缀名
                }
                // 图片的路径+文件名称
                String fileName = "/upload/" + uuid + name;
                // 图片的在服务器上面的物理路径
                File destFile = new File(webapp, fileName);
                // 生成upload目录
                File parentFile = destFile.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();// 自动生成upload目录
                }
                // 把上传的临时图片，复制到当前项目的webapp路径
                FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(destFile));
                map.put("success", 1); //设置回显的数据 0 表示上传失败，1 表示上传成功
                map.put("message", "上传成功"); //提示的信息，上传成功或上传失败及错误信息等
                map.put("url", fileName); //图片回显的url 上传成功时才返回
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return FastJson2Util.ObjectToJson(map);
    }
    //@ResponseBody
    //public JSONObject upload(@RequestParam(value = "editormd-image-file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
    //
    //    String trueFileName = file.getOriginalFilename();
    //
    //    String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));
    //
    //    String fileName = DateTimeUtil.getCurrentDateTimeAsFileName() + "-" + UUID.get(8) + suffix;
    //
    //    String path = request.getSession().getServletContext().getRealPath("/assets/msg/upload/");
    //    System.out.println(path);
    //
    //    File targetFile = new File(path, fileName);
    //    if(!targetFile.exists()){
    //        targetFile.mkdirs();
    //    }
    //
    //    //保存
    //    try {
    //        file.transferTo(targetFile);
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //
    //
    //    JSONObject res = new JSONObject();
    //   // res.put("url", Constant.WEB_ROOT + "assets/msg/upload/" + fileName);
    //    res.put("success", 1);
    //    res.put("message", "upload success!");
    //
    //    return res;
    //
    //}


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

