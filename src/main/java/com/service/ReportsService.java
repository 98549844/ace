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

        List<ReportLinks> reportLinks = reportLinksDao.findAllByReportIdOrderByCreatedDateDesc(report.getReportId());
        List<Reports> reports = new ArrayList<>();
        if (reportLinks.size() != 0) {
            for (ReportLinks reportLink : reportLinks) {
                Reports r = reportsDao.findAllByReportId(reportLink.getSubReportId());
                if (NullUtil.isNull(r)) {
                    reports.add(reportsDao.findAllByReportId(reportLink.getReportId()));
                }
            }
            reportsInfo.setSubReports(reports);
        }


        log.info(reportsInfo.getReportId());
        return reportsInfo;
    }
}

