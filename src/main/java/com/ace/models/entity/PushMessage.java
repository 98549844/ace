package com.ace.models.entity;

import com.ace.models.entity.base.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@EntityListeners(AuditingEntityListener.class)
@Table(name = "pushMessage",
        indexes = {
        @Index(name = "idxTemplateId", columnList = "templateId"),
                @Index(name = "idxReceiver", columnList = "receiver"),})
@Entity
public class PushMessage extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(strategy = "identity", name = "id")
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long msgId;
    @Column
    private Long templateId;
    @Column
    private int type; // 推送渠道1短信2邮件3微信 4APP
    @Column
    private String receiver;
    @Column
    private String deviceInfo;
    @Column
    private String content;
    @Column
    private Integer deleted;

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
