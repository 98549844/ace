package com.constant;


public class WebServiceInfo {
    /**
     * 请求状态
     */
    private Integer status = LoginStatus.FAIL;
    /**
     * 请求消息
     */
    private String msg;
    /**
     * 返回数据(对象)
     */
    private Object obj;

    /**
     * 无参构造方法
     */
    public WebServiceInfo() {
    }

    public WebServiceInfo(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }


}
