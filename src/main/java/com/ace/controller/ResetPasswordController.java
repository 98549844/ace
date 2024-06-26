package com.ace.controller;

import com.ace.constant.Css;
import com.ace.controller.common.CommonController;
import com.ace.models.entity.Users;
import com.ace.service.UsersService;
import com.ace.utilities.NullUtil;
import com.ace.utilities.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;

/**
 * @Classname: ResetPasswordController
 * @Date: 2022/10/24 下午 06:03
 * @Author: kalam_au
 * @Description:
 */

@Controller
public class ResetPasswordController extends CommonController {
    private static final Logger log = LogManager.getLogger(ResetPasswordController.class.getName());

    @Value("${ace.mail.username}")
    private String from;
    private final UsersService usersService;
    private final JavaMailSender javaMailSender;


    public ResetPasswordController(UsersService usersService, JavaMailSender javaMailSender) {
        this.usersService = usersService;
        this.javaMailSender = javaMailSender;
    }

    @RequestMapping(value = {"/ace/password/reset.html"}, method = RequestMethod.POST)
    public ModelAndView reset(String email) throws MessagingException {
        email = email.trim();
        log.info("access ace/password/reset.html");
        log.info("reset password email: {}", email);
        Users users = usersService.findUsersByEmail(email);
        String msg;
        ModelAndView modelAndView = super.page("ace/login.html");
        if (NullUtil.isNull(users) || NullUtil.isNull(users.getUserId())) {
            msg = "Email not registered";
            log.warn("message: {}", msg);
            modelAndView.addObject("msg", msg);
            modelAndView.addObject(Css.css, Css.red);
        } else {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper emailHelper = new MimeMessageHelper(message, true);
            emailHelper.setFrom(from);
            emailHelper.setTo(email);
            emailHelper.setSubject("Reset Password - Ace Application"); // 标题
            emailHelper.setText("Click here to reset Ace Application login password"); // 内容
            String uuid = UUID.get();
            setSession(uuid);
            modelAndView = super.page("ace/login.html");
            try {
                javaMailSender.send(message);
                log.info("Reset password success !");
                msg = "Sent reset mail";
                modelAndView.addObject("msg", msg);
                modelAndView.addObject(Css.css, Css.green);
            } catch (Exception e) {
                log.error(e.getMessage());
                e.printStackTrace();
                msg = "fail to send reset mail";
                modelAndView.addObject("msg", msg);
                modelAndView.addObject(Css.css, Css.red);
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/ace/password/set/{password}/{uuid}"}, method = RequestMethod.POST)
    public ModelAndView set(@PathVariable String uuid, @PathVariable String password) throws MessagingException {
        log.info("access ace/password/set/{} ", uuid);
        ModelAndView modelAndView;
        String result = getSession(uuid);
        if (uuid.equals(result)) {
            modelAndView = super.page("ace/login.html");
            String msg = "Reset password success";
            modelAndView.addObject("msg", msg);
            modelAndView.addObject(Css.css, Css.green);
            removeSession(uuid);
            log.info("reset password success !!!");
        } else {
            modelAndView = super.page("reset password 页面没有做好");
            String msg = "Reset password fail";
            modelAndView.addObject("msg", msg);
            modelAndView.addObject(Css.css, Css.red);
            log.warn("reset password fail !!!");
        }
        return modelAndView;
    }

    private void setSession(String uuid) {
        HttpSession session = getRequest().getSession();
        String mySessionId = session.getId();
        log.info("setSession ID: {}", mySessionId);
        session.setAttribute(uuid, uuid);
    }

    private String getSession(String uuid) {
        HttpSession session = getRequest().getSession();
        String mySessionId = session.getId();
        log.info("getSession ID: {}", mySessionId);
        String getUuid = (String) session.getAttribute(uuid);
        session.removeAttribute(uuid);
        return getUuid;
    }

    private void removeSession(String uuid) {
        HttpSession session = getRequest().getSession();
        session.removeAttribute(uuid);
    }
}

