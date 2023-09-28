package com.ace.models.entity;

import com.ace.constant.AceEnvironment;
import com.ace.controller.common.CommonController;
import com.ace.models.entity.base.BaseEntity;
import jakarta.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    @Column
    private String path = AceEnvironment.getAce(); // 默认 root/ace/
    @Column
    private String status = CREATED; // 已新建
    public static final String DENIED = "denied"; //拒绝读取
    public static final String CREATED = "created"; //新建

    @Column
    private boolean isShared = false; //默认不分享
    @Column
    private String sharedUserId;


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
}

