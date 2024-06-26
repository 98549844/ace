package com.ace.restController;

import com.ace.exception.ResponseException;
import com.ace.models.common.RespResult;
import com.ace.models.common.Email;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;


/**
 * @Classname: MailRestController
 * @Date: 5/7/2021 1:28 上午
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/mail")
@Tag(name = "Mail")
public class MailRestController {
    private static final Logger log = LogManager.getLogger(MailRestController.class.getName());

    private final JavaMailSender javaMailSender;

    //@Value("${spring.mail.username}")
    //private String from;

    @Autowired
    public MailRestController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Operation(summary = "Sample mail")
    @PostMapping("/sample.html")
    public RespResult sendEmail(@RequestBody Email email) {
        String status;
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getFrom());
            message.setTo(email.getTo()); // 接收地址
            message.setSubject(email.getSubject()); // 标题
            message.setText(email.getContent()); // 内容
            javaMailSender.send(message);
            log.info("sent mail successful");
            return RespResult.success(RespResult.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return RespResult.error(new ResponseException(e.getMessage()));
        }

    }


    @PostMapping("/htmlEmail.html")
    @Operation(summary = "Html mail")
    public RespResult sendHtmlEmail(@RequestBody Email email) {
        String status;
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(email.getFrom());
            helper.setTo(email.getTo()); // 接收地址
            helper.setSubject(email.getSubject()); // 标题
            // 带HTML格式的内容
            helper.setText(email.getContent(), true);
            javaMailSender.send(message);
            status = "success";
            return RespResult.success(status);
        } catch (Exception e) {
            status = e.getMessage();
            e.printStackTrace();
            return RespResult.error(new ResponseException(status));
        }
    }


    @PostMapping("/mailWithAttachments.html")
    @Operation(summary = "Mail with attachments")
    public RespResult sendAttachmentsMail(@RequestBody Email email) {
        String status;
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(email.getFrom());
            helper.setTo(email.getTo()); // 接收地址
            helper.setSubject(email.getSubject()); // 标题
            helper.setText(email.getContent()); // 内容
            // 传入附件
            File f = new File(email.getAttachmentsPath());
            FileSystemResource file = new FileSystemResource(f);
            helper.addAttachment(f.getName(), file);
            javaMailSender.send(message);
            status = "success !";
            return RespResult.success(status);
        } catch (Exception e) {
            status = e.getMessage();
            e.printStackTrace();
            return RespResult.error(new ResponseException(status));
        }
    }

}

