package com.ace.models.common;

import com.ace.constant.MessageConstants;
import com.ace.exception.ResponseException;
import com.ace.exception.ResponseExceptionType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Classname: AjaxResponse
 * @Date: 9/11/2021 10:49 下午
 * @Author: garlam
 * @Description:
 */


public class RespResult {
    private static final Logger log = LogManager.getLogger(RespResult.class.getName());


    private boolean isOk;
    private int code;
    private String message;
    private Object data;

    public static final String SUCCESS = "success";
    public static final String WARNING = "warning";


    //请求出现异常时的响应数据封装
    public static RespResult error(ResponseException e) {
        RespResult resultBean = new RespResult();
        resultBean.setOk(false);
        resultBean.setCode(e.getCode());

        if (e.getCode() == ResponseExceptionType.CUSTOM_EXCEPTION.getCode()) {
            resultBean.setMessage(e.getMessage());
        } else if (e.getCode() == ResponseExceptionType.USER_INPUT_ERROR.getCode()) {
            resultBean.setMessage(e.getMessage() + ", 用户输入资料错误");
        } else if (e.getCode() == ResponseExceptionType.PAGE_NOT_FOUND_ERROR.getCode()) {
            resultBean.setMessage(e.getMessage() + ", 未找到页面，请联系管理员!");
        } else if (e.getCode() == ResponseExceptionType.SYSTEM_ERROR.getCode()) {
            resultBean.setMessage(e.getMessage() + ", 系统出现未知异常，请联系管理员!");
        } else {
            resultBean.setMessage(e.getMessage());
        }
        return resultBean;
    }

    public static RespResult success() {
        RespResult resultBean = new RespResult();
        resultBean.setOk(true);
        resultBean.setCode(200);
        resultBean.setMessage(SUCCESS);
        return resultBean;
    }

    public static RespResult success(Object data) {
        RespResult resultBean = new RespResult();
        resultBean.setOk(true);
        resultBean.setCode(200);
        resultBean.setMessage(MessageConstants.SUCCESS);
        resultBean.setData(data);
        return resultBean;
    }

    public static RespResult warn(Object data) {
        RespResult resultBean = new RespResult();
        resultBean.setOk(true);
        resultBean.setCode(200);
        resultBean.setMessage(WARNING);
        resultBean.setData(data);
        return resultBean;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

