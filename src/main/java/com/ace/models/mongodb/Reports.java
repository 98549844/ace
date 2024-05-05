package com.ace.models.mongodb;

import cn.dev33.satoken.stp.StpUtil;
import com.ace.models.entity.Users;
import com.ace.models.entity.base.BaseEntity;
import com.ace.utilities.NullUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @Classname: Reports
 * @Date: 2022/11/15 下午 12:14
 * @Author: kalam_au
 * @Description:
 */


@Document(collection = "reports")
public class Reports extends BaseEntity {
    private static final Logger log = LogManager.getLogger(Reports.class.getName());


    @Id
    private String reportId;
    private Long subReportId = 0L;
    private String subject;
    private String content;
    private String status = NEW; //NEW, FOLLOWING, CLOSE

    public static final String NEW = "NEW";
    public static final String FOLLOWING = "FOLLOWING";
    public static final String CLOSE = "CLOSE";

    private String level; //info, warning, error, critical,fatal

    public static final String SUGGESTION = "SUGGESTION";
    public static final String INFO = "INFO"; //资料性质
    public static final String BUG = "BUG"; // 错误
    public static final String WARNING = "WARNING"; //警告但不应响使用
    public static final String ERROR = "ERROR"; // 警告并不能使用
    public static final String CRITICAL = "CRITICAL"; //模组崩溃
    public static final String FATAL = "FATAL"; //系统崩溃

    private String attachment;
    private LocalDateTime reportDate = LocalDateTime.now();
    private String reporter;


    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public Long getSubReportId() {
        return subReportId;
    }

    public void setSubReportId(Long subReportId) {
        this.subReportId = subReportId;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        if (NullUtil.isNull(reporter)) {
            Users user = (Users) StpUtil.getSession().get("user");
            this.reporter = user.getUserAccount();
        } else {
            this.reporter = reporter;
        }
    }

}

