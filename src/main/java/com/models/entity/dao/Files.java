package com.models.entity.dao;

import com.constant.AceEnvironment;
import com.models.entity.dao.base.baseEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @Classname: Files
 * @Date: 11/7/2021 2:32 上午
 * @Author: garlam
 * @Description:
 */

@EntityListeners(AuditingEntityListener.class)
@Table(name = "files", uniqueConstraints = {@UniqueConstraint(name = "constraint_fileName", columnNames = {"fileName","path"})})
@Entity
public class Files extends baseEntity {
    private static Logger log = LogManager.getLogger(Files.class.getName());

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(strategy = "identity", name = "id")
    @Column
    private Long id;
    @Column
    private String ext;
    @Column
    private String location;
    @Column
    private String path = AceEnvironment.getFilePath();
    @Column
    private Long size;
    @Column
    private String fileName;
    @Column
    private String originationName; //原文件名
    @Column
    private String owner;
    @Column
    private String remark = "default path:" + AceEnvironment.getFilePath();

    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger log) {
        Files.log = log;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getSize() {
        return size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOriginationName() {
        return originationName;
    }

    public void setOriginationName(String originationName) {
        this.originationName = originationName;
    }
}

