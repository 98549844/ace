package com.ace.restController;

import com.ace.controller.common.CommonController;
import com.ace.models.common.RespResult;
import com.ace.utilities.FastJson2Util;
import com.ace.utilities.MapUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;


/**
 * @Classname: SessionRestController
 * @Date: 8/1/2024 1:21 am
 * @Author: garlam
 * @Description:
 */

@CrossOrigin // 解决浏览器禁止ajax请求本地以外的资源, 后端同时在Controller层的类上增加@CrossOrign注解
@RestController
@RequestMapping("/rest/session")
@Tag(name = "Session")
public class SessionRestController extends CommonController {
    private static final Logger log = LogManager.getLogger(SessionRestController.class.getName());

    @RequestMapping(method = RequestMethod.GET, value = "/getId.html")
    public RespResult getId() {
        String sessionId = super.getHttpSessionId();
        return RespResult.success(sessionId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getSaId.html")
    public RespResult getSaId() {
        if (isLogin()) {
            return RespResult.success(getSaSession().getId());
        } else {
            return RespResult.warn("SaSession null, login is " + isLogin());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/setAttribute.html")
    public RespResult setHttpAttribute(@RequestParam("attr") String attr, @RequestParam("val") Object val, HttpSession session) {
        session.setAttribute(attr, val);
        return RespResult.success(session.getAttribute(attr));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/setCommonAttribute.html")
    public RespResult setCommonAttribute(@RequestParam("attr") String attr, @RequestParam("val") Object val) {
        setHttpSession(attr, val);
        return RespResult.success(getHttpSession(attr));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/setSaAttribute.html")
    public RespResult setSaAttribute(@RequestParam("attr") String attr, @RequestParam("val") Object val) {
        if (isLogin()) {
            setSaSession(attr, val);
            return RespResult.success(getSaSession().get(attr));
        } else {
            return RespResult.warn("SaLogin: " + isLogin());

        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllHttpSessionAttr.html")
    public RespResult getAllHttpSessionAttr(HttpSession session) {
        List sessionResult = new ArrayList();
        Enumeration<String> attrs = session.getAttributeNames();
        sessionResult.add("http session Id: " + getHttpSessionId());
        while (attrs.hasMoreElements()) {
            String name = attrs.nextElement();
            Object value = session.getAttribute(name);
            sessionResult.add(name + ": " + value);
        }
        return RespResult.success(sessionResult);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllSaSessionAttr.html")
    public RespResult getAllSaSessionAttr() {
        List sessionResult = new ArrayList();
        if (isLogin()) {
            sessionResult.add("sa session Id: " + getSaSessionId());
            Map<String, Object> map = getSaSession().getDataMap();
            List keyset = MapUtil.getKeySet(map);
            for (Object key : keyset) {
                Object val = map.get(key);
                sessionResult.add(key + ": " + FastJson2Util.ObjectToJson(val));
            }
            return RespResult.success(sessionResult);
        } else {
            sessionResult.add("SaLogin: " + isLogin());
            return RespResult.warn(sessionResult);
        }
    }
}

