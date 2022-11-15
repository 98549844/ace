package com.dao;

import com.models.entity.ReportLinks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Classname: RepliersDao
 * @Date: 2022/11/15 下午 04:03
 * @Author: kalam_au
 * @Description:
 */

@Repository
@Transactional
public interface ReportLinksDao extends JpaRepository<ReportLinks, Long>, JpaSpecificationExecutor<ReportLinks> {

    List<ReportLinks> findAllByReportIdOrderByCreatedDateDesc(Long reportId);
}
