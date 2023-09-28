package com.ace.models.entity;

import com.ace.constant.AceEnvironment;
import com.ace.models.entity.base.BaseEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;

/**
 * @Classname: Files
 * @Date: 11/7/2021 2:32 上午
 * @Author: garlam
 * @Description:
 */

@EntityListeners(AuditingEntityListener.class)
@Table(name = "files", uniqueConstraints = {@UniqueConstraint(name = "constraint_fileName", columnNames = {"fileName", "path"})})
@Entity
public class Files extends BaseEntity {
    private static final Logger log = LogManager.getLogger(Files.class.getName());

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(strategy = "identity", name = "id")
    @Column
    private Long id;
    @Column
    private String ext;
    @Column
    private String type = "File type undefine"; //默认文件种类
    public static final String IMAGE = "image";
    public static final String VIDEO = "video";
    public static final String APPLICATION = "application";
    @Column
    private String location;
    @Column
    private String path = AceEnvironment.getFilePath();
    @Column
    private Long size;
    @Column
    private String fileName;
    @Column
    private int count = 0;
    @Column
    private String status = UPLOADED;
    public static final String DELETED = "deleted"; //已删除
    public static final String UPLOADED = "uploaded"; //已上载
    public static final String FRAGMENT = "fragment"; //已切片
    public static final String COMPRESSED = "compressed";//已压缩
    public static final String LOST = "lost";
    public static final String DENIED = "denied"; //拒绝读取

    @Column
    private String originationName; //原文件名
    @Column
    private String owner;
    @Column
    private String remark = "default path:" + AceEnvironment.getFilePath();

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

