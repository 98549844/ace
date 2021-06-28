package com.entity.dao.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.NullUtil;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Classname: baseEntity
 * @Date: 5/5/2021 12:22 上午
 * @Author: garlam
 * @Description:
 */


@MappedSuperclass
public class baseEntity {

    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "last_update_by")
    private String lastUpdateBy;
    @Column(name = "version")
    private Integer version;

    public baseEntity() {
        if (NullUtil.isNull(version) || version == 0) {
            version = 1;
        }
        if (NullUtil.isNull(createdDate)) {
            createdDate = LocalDateTime.now();
        }
        if (NullUtil.isNull(lastUpdateDate)) {
            createdDate = LocalDateTime.now();
        }

    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}

