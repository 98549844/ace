package com.ace.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @Classname: MailConfig
 * @Date: 5/7/2021 2:20 上午
 * @Author: garlam
 * @Description:
 */

@Configuration
public class MailConfig {
    private static final Logger log = LogManager.getLogger(MailConfig.class.getName());

    @Value("${ace.mail.username}")
    private String from;
    @Value("${ace.mail.password}")
    private String password;
    @Value("${ace.mail.host}")
    private String host;
    @Value("${ace.mail.port}")
    private int port;


    @Bean
    public JavaMailSender getJavaMailOutlookSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);

        mailSender.setUsername(from);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.transport.protocol", "smtp");
        return mailSender;
    }

    //@Bean
    public JavaMailSender getJavaQQMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.qq.com");
        mailSender.setPort(465);

        mailSender.setUsername("87548744@qq.com");
        mailSender.setPassword("expyxtjadzylbjab");
        // 授权码的有效期为一个月 (2023-08-15更新)
        // 邮箱首页 => 设置 => 账号
        // => POP3/IMAP/SMTP/Exchange/CardDAV/CalDAV服务 => 管理服务 => 生成授权码

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }

}

