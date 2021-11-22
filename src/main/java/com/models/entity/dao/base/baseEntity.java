package com.models.entity.dao.base;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

//import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDateTime;
import util.*;


/**
 * @Classname: baseEntity
 * @Date: 5/5/2021 12:22 上午
 * @Author: garlam
 * @Description:
 */


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class baseEntity {


    //https://blog.csdn.net/qq_28804275/article/details/84801457?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1.showsourcetag&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1.showsourcetag

    @CreatedDate
    //@Column(name = "created_date", updatable = false)
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    //@Column(name = "last_update_date")
    @Column()
    private LocalDateTime lastUpdateDate;

    @CreatedBy
    //@Column(name = "created_by", updatable = false)
    @Column( updatable = false)
    private Long createdBy;

    @LastModifiedBy
    //@Column(name = "last_update_by")
    @Column()
    private Long lastUpdatedBy;

    //@Column(name = "version", columnDefinition = "int default 1")
    @Version
    @Column(name = "version")
    private Integer version = 1;


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

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        if(NullUtil.isNull(version)){
            version = 1;
        }
        this.version = version;
    }
}

