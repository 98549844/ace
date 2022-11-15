package com.models.entity;

import com.models.entity.base.BaseEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @Classname: Repliers
 * @Date: 2022/11/15 下午 03:48
 * @Author: kalam_au
 * @Description:
 */

@EntityListeners(AuditingEntityListener.class)
@Table(name = "reportLinks")
@Entity
public class ReportLinks extends BaseEntity {
    private static final Logger log = LogManager.getLogger(ReportLinks.class.getName());

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(strategy = "identity", name = "id")
    @Column
    private Long id;

    @Column
    private Long reportId;
    @Column
    private Long subReportId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public Long getSubReportId() {
        return subReportId;
    }

    public void setSubReportId(Long subReportId) {
        this.subReportId = subReportId;
    }
}

