package com.ace.service;

import com.ace.dao.ReportsDao;
import com.ace.models.entity.Reports;
import com.ace.models.info.ReportsInfo;
import com.ace.utils.BeanUtil;
import com.ace.utilities.ListUtil;
import com.ace.utilities.NullUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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
    private final EntityManager entityManager;


    @Autowired
    public ReportsService(ReportsDao reportsDao, EntityManager entityManager) {
        this.reportsDao = reportsDao;
        this.entityManager = entityManager;
    }

    public List<Reports> getReportList() {
        return reportsDao.findAllBySubReportIdOrderByCreatedDateDesc(0L);
    }


    public ReportsInfo getReportInfoById(Long reportId) {
        Reports report = reportsDao.findAllByReportId(reportId);
        BeanUtil beanUtil = new BeanUtil();
        ReportsInfo reportsInfo = beanUtil.copy(report, ReportsInfo.class);
        reportsInfo.setSubReports(getSubReports(reportsInfo));
        return reportsInfo;
    }

    private List<ReportsInfo> getSubReports(ReportsInfo reportsInfo) {
        BeanUtil beanUtil = new BeanUtil();
        List<Reports> reports = reportsDao.findAllBySubReportIdOrderByCreatedDateDesc(reportsInfo.getReportId());
        List<ReportsInfo> subReports = new ArrayList<>();
        for (Reports report : reports) {
            Reports r = reportsDao.findAllByReportId(report.getReportId());
            ReportsInfo info = beanUtil.copy(r, ReportsInfo.class);
            info.setSubReports(getSubReports(info));
            subReports.add(info);
        }
        return subReports;
    }

    public ReportsInfo saveAndFlush(ReportsInfo reportsInfo) {
        return reportsDao.saveAndFlush(reportsInfo);
    }

    public Reports saveAndFlush(Reports reports) {
        return reportsDao.saveAndFlush(reports);
    }

    public Reports save(Reports reports) {
        return reportsDao.save(reports);
    }

    public void deleteById(Long reportId) {
        reportsDao.deleteById(reportId);
    }


    /** 动态sql查询
     * @param reports
     * @param criteria
     * @return
     */
    public List<Reports> search(Reports reports, String criteria) {
        StringBuilder sql = new StringBuilder("select reportId, createdBy, createdDate, lastUpdateDate, lastUpdatedBy, version, attachment, content, level, reportDate, reporter, status, subReportId, subject from reports where 1=1 ");
        sql.append("and subReportId = 0 "); //只看主report
        if (NullUtil.isNonNull(reports)) {
            sql.append("and level = '").append(reports.getLevel()).append("'");
        }

        if (NullUtil.isNonNull(criteria)) {
            List<String> criteriaList = ListUtil.stringArrayToList(criteria.split(" "));
            sql.append("and ");
            for (String s : criteriaList) {
                sql.append("subject like '%").append(s).append("%' or ");
                sql.append("content like '%").append(s).append("%' or ");
            }
        }
        sql = new StringBuilder(sql.substring(0, sql.length() - 3));//扣除最后一个or
        System.out.println("native sql: " + sql);
        Query query = entityManager.createNativeQuery(sql.toString());
        List<Reports> results = query.getResultList();
        return results;
    }


}

