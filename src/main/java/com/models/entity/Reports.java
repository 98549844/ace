package com.models.entity;

import com.models.entity.base.BaseEntity;
import com.sun.jdi.Value;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.ElementCollection;


import javax.persistence.*;
import java.security.Key;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname: Reports
 * @Date: 2022/11/15 下午 12:14
 * @Author: kalam_au
 * @Description:
 */

@EntityListeners(AuditingEntityListener.class)
@Table(name = "reports")
@Entity
public class Reports extends BaseEntity {
    private static final Logger log = LogManager.getLogger(Reports.class.getName());


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(strategy = "identity", name = "id")
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long reportId;

    @Column
    private String title;
    @Lob
    @Column
    private String content;
    @Column
    private String status; //NEW, FOLLOWING, CLOSE
    public static final String NEW = "NEW";
    public static final String FOLLOWING = "FOLLOWING";
    public static final String CLOSE = "CLOSE";
    @Column
    private String level; //info, warning, error, critical,fatal
    public static final String INFO = "INFO";
    public static final String WARNING = "WARNING";
    public static final String ERROR = "ERROR";
    public static final String CRITICAL = "CRITICAL";
    public static final String FATAL = "FATAL";
    @Column
    private String attachment;
    @Column
    private LocalDateTime reportDate;



    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }
}

