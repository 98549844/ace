package com.service;

import com.dao.ReportLinksDao;
import com.dao.ReportsDao;
import com.models.entity.ReportLinks;
import com.models.entity.Reports;
import com.models.info.ReportsInfo;
import com.util.BeanUtil;
import com.util.NullUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname: ReportsService
 * @Date: 2022/11/15 下午 03:54
 * @Author: kalam_au
 * @Description:
 */

@Service
public class ReportsService {
    private static final Logger log = LogManager.getLogger(ReportsService.class.getName());

    private final ReportsDao reportsDao;
    private final ReportLinksDao reportLinksDao;

    @Autowired
    public ReportsService(ReportLinksDao reportLinksDao, ReportsDao reportsDao) {
        this.reportsDao = reportsDao;
        this.reportLinksDao = reportLinksDao;
    }

    public ReportsInfo getReportById(Long reportId) {
        Reports report = reportsDao.findAllByReportId(reportId);
        BeanUtil beanUtil = new BeanUtil();
        ReportsInfo reportsInfo = beanUtil.copy(report, ReportsInfo.class);
        reportsInfo.setSubReports(getSubReports(reportsInfo));
        return reportsInfo;
    }


    public List<ReportsInfo> getSubReports(ReportsInfo reportsInfo) {
        BeanUtil beanUtil = new BeanUtil();
        List<ReportLinks> reportLinks = reportLinksDao.findAllByReportIdOrderByCreatedDateDesc(reportsInfo.getReportId());
        List<ReportsInfo> subReports = new ArrayList<>();
        for (ReportLinks reportLink : reportLinks) {
            Reports r = reportsDao.findAllByReportId(reportLink.getSubReportId());
            ReportsInfo reportsInfo2 = beanUtil.copy(r, ReportsInfo.class);
            reportsInfo2.setSubReports(getSubReports(reportsInfo2));
            subReports.add(reportsInfo2);
        }
        return subReports;
    }

}

