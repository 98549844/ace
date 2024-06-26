package com.ace.models.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * @Classname: Email
 * @Date: 7/7/2021 12:09 下午
 * @Author: garlam
 * @Description:
 */


public class Email {
    private static final Logger log = LogManager.getLogger(Email.class.getName());

    private String from;
    @NotNull
    private String to = "";
    @NotNull
    private String subject = "";
    private String content;
    private String cc;
    private String bcc;
    private String attachmentsPath;


    public String getAttachmentsPath() {
        return attachmentsPath;
    }

    public void setAttachmentsPath(String attachmentsPath) {
        this.attachmentsPath = attachmentsPath;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

