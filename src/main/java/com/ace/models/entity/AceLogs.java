package com.ace.models.entity;

import com.ace.models.entity.base.BaseEntity;
import com.ace.utilities.FileUtil;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;


@EntityListeners(AuditingEntityListener.class)
@Table(name = "aceLogs")
@Entity
public class AceLogs extends BaseEntity implements Serializable {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(strategy = "identity", name = "id")
    @Column(name = "logId")
    private Long logId;

    @Column(name = "operator")
    private String operator = NONE;
    @Column(name = "description")
    private String description = NORMAL;
    private static final String NORMAL = "normal";

    @Column(name = "accessTime")
    private LocalDateTime accessTime;

    @Column(name = "clazz")
    private String clazz;
    @Column(name = "exception")
    private String exception = NONE;
    public static final String NONE = "none";
    public static final String EXCEPTION = "exception";

    @Column(name = "aspectType")
    private String aspectType;
    public static final String BEFORE = "Before";
    public static final String AFTER_RETURNING = "AfterReturning";
    public static final String AFTER_THROWING = "AfterThrowing";
    public static final String AFTER = "After";
    public static final String AROUND = "Around";


    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(LocalDateTime accessTime) {
        this.accessTime = accessTime;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getAspectType() {
        return aspectType;
    }

    public void setAspectType(String aspectType) {
        this.aspectType = aspectType;
    }
}
