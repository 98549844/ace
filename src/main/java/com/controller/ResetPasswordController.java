package com.controller;

import com.constant.Css;
import com.controller.common.CommonController;
import com.models.entity.dao.Email;
import com.models.entity.dao.Users;
import com.service.UsersService;
import com.util.NullUtil;
import com.util.UUID;
import org.apache.kerby.kerberos.kerb.type.pa.PaAuthenticationSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

/**
 * @Classname: ResetPasswordController
 * @Date: 2022/10/24 下午 06:03
 * @Author: kalam_au
 * @Description:
 */

@Controller
public class ResetPasswordController extends CommonController {
    private static final Logger log = LogManager.getLogger(ResetPasswordController.class.getName());

    @Value("${spring.mail.username}")
    private String from;
    private final UsersService usersService;
    private final JavaMailSender javaMailSender;


    public ResetPasswordController(UsersService usersService, JavaMailSender javaMailSender) {
        this.usersService = usersService;
        this.javaMailSender = javaMailSender;
    }

    @RequestMapping(value = {"/ace/password/reset.html"}, method = RequestMethod.POST)
    public ModelAndView login(String email) throws MessagingException {
        email = email.trim();
        log.info("access ace/password/reset.html");
        log.info("reset password email: " + email);
        Users users = usersService.findUsersByEmail(email);
        ModelAndView modelAndView;
        String msg;
        if (NullUtil.isNull(users) || NullUtil.isNull(users.getUserId())) {
            msg = "Email not registered";
            log.warn("message: {}", msg);
            modelAndView = super.page("ace/login.html");
            modelAndView.addObject("msg", msg);
            modelAndView.addObject(Css.css, Css.red);
        } else {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper emailHelper = new MimeMessageHelper(message, true);
            emailHelper.setFrom(from);
            emailHelper.setTo(email);
            emailHelper.setSubject("Reset Password - Ace Application"); // 标题
            emailHelper.setText("Click here to reset Ace Application login password"); // 内容
            //  javaMailSender.send(message);

            String uuid = UUID.get();
            System.out.println(uuid);
            setSession(uuid);
            String getUuid = getSession(uuid);
            System.out.println(getUuid);
            removeSession(uuid);

            modelAndView = super.page("500.html");
        }
        return modelAndView;
    }

    protected void setSession(String uuid) {
        HttpSession session = getRequest().getSession();
        String mySessionId = session.getId();
        log.info("setSession ID: {}", mySessionId);
        session.setAttribute(uuid, uuid);
    }

    protected String getSession(String uuid) {
        HttpSession session = getRequest().getSession();
        String mySessionId = session.getId();
        log.info("getSession ID: {}", mySessionId);
        String getUuid = (String) session.getAttribute(uuid);
        session.removeAttribute(uuid);
        return getUuid;
    }

    protected void removeSession(String uuid) {
        HttpSession session = getRequest().getSession();
        session.removeAttribute(uuid);
    }
}

