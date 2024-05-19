package com.ace.models.entity;

import com.ace.constant.AceEnvironment;
import com.ace.models.entity.base.BaseEntity;
import com.ace.utilities.Console;
import com.ace.utilities.OsUtil;
import jakarta.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname: Files
 * @Date: 11/7/2021 2:32 上午
 * @Author: garlam
 * @Description:
 */

@EntityListeners(AuditingEntityListener.class)
@Table(name = "folders", uniqueConstraints = {@UniqueConstraint(name = "constraint_path", columnNames = {"path"})})
@Entity
@PropertySource(value = {"classpath:ace.properties"}, encoding = "UTF-8", name = "ace.properties")
public class Folders extends BaseEntity {
    private static final Logger log = LogManager.getLogger(Folders.class.getName());

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(strategy = "identity", name = "id")
    @Column
    private Long id;
    @Column
    private Long parentId = 0l;
    @Column
    private String folderName;
    @Column
    private Long ownerId;

    @Value("${file.ace}")
    @Column
    private String path; // 默认 root/ace/
    @Column
    private String status = CREATED; // 已新建
    public static final String DENIED = "denied"; //拒绝读取
    public static final String CREATED = "created"; //新建

    @Column
    private boolean isShared = false; //默认不分享
    @Column
    private String sharedUserId;

    @Column
    private String osType;

    @Transient
    private List<Folders> subFolders = new ArrayList<>();

    public Folders() {
        String osName = OsUtil.getOsName();
        if (osName.contains(OsUtil.WINDOWS)) {
            this.osType = OsUtil.WINDOWS;
        } else if (osName.contains(OsUtil.MAC)) {
            this.osType = OsUtil.MAC;
        } else if (osName.contains(OsUtil.LINUX)) {
            this.osType = OsUtil.LINUX;
        } else {
            this.osType = OsUtil.UNKNOWN;
        }
    }


    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public boolean isShared() {
        return isShared;
    }

    public void setShared(boolean shared) {
        isShared = shared;
    }

    public String getSharedUserId() {
        return sharedUserId;
    }

    public void setSharedUserId(String sharedUserId) {
        this.sharedUserId = sharedUserId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public List<Folders> getSubFolders() {
        return subFolders;
    }

    public void setSubFolders(List<Folders> subFolders) {
        this.subFolders = subFolders;
    }

    public void addSubFolder(Folders child) {
        this.subFolders.add(child);
    }
}

