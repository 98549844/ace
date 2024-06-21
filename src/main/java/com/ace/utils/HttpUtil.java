package com.ace.utils;

import com.ace.utilities.ConsoleTable;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * @Classname: HttpUtil
 * @Date: 5/7/2021 6:49 上午
 * @Author: garlam
 * @Description:
 */


public class HttpUtil {
    private static final Logger log = LogManager.getLogger(HttpUtil.class.getName());

    //http://tools.jb51.net/table/http_status_code
    public static void main(String[] args) {
        httpStatusCommonCode();
        httpStatusCode();
        httpStatusList();
    }



    private static void httpStatusCommonCode() {
        List<String> header = new ArrayList<>();
        header.add("常见的HTTP状态码");

        List<String[]> body = new ArrayList<>();
        body.add(new String[]{"200 - 请求成功"});
        body.add(new String[]{"301 - 资源(网页等)被永久转移到其它URL"});
        body.add(new String[]{"404 - 请求的资源(网页等)不存在"});
        body.add(new String[]{"500 - 内部服务器错误"});

        ConsoleTable.println(header, body);
    }

    public static void httpStatusCode() {
        List<String> header = new ArrayList<>();
        header.add("code分类");
        header.add("分类描述");

        List<String[]> body = new ArrayList<>();
        body.add(new String[]{"1**", "信息,服务器收到请求,需要请求者继续执行操作"});
        body.add(new String[]{"2**", "成功,操作被成功接收并处理"});
        body.add(new String[]{"3**", "重定向,需要进一步的操作以完成请求"});
        body.add(new String[]{"4**", "客户端错误,请求包含语法错误或无法完成请求"});
        body.add(new String[]{"5**", "服务器错误,服务器在处理请求的过程中发生了错误"});

        ConsoleTable.println(header, body);
    }

    public static void httpStatusList() {
        List<String> header = new ArrayList<>();
        header.add("状态码");
        header.add("状态码英文名称");
        header.add("中文描述");

        List<String[]> body = new ArrayList<>();
        body.add(new String[]{"100", "Continue", "继续。客户端应继续其请求"});
        body.add(new String[]{"101", "Switching Protocols", "切换协议。服务器根据客户端的请求切换协议。只能切换到更高级的协议，例如，切换到HTTP的新版本协议"});
        body.add(new String[]{"–", "–", "–"});
        body.add(new String[]{"200", "OK", "请求成功。一般用于GET与POST请求"});
        body.add(new String[]{"201", "Created", "已创建。成功请求并创建了新的资源"});
        body.add(new String[]{"202", "Accepted", "已接受。已经接受请求，但未处理完成"});
        body.add(new String[]{"203", "Non-Authoritative Information", "非授权信息。请求成功。但返回的meta信息不在原始的服务器，而是一个副本"});
        body.add(new String[]{"204", "No Content", "无内容。服务器成功处理，但未返回内容。在未更新网页的情况下，可确保浏览器继续显示当前文档"});
        body.add(new String[]{"205", "Reset Content", "重置内容。服务器处理成功，用户终端（例如：浏览器）应重置文档视图。可通过此返回码清除浏览器的表单域"});
        body.add(new String[]{"206", "Partial Content", "部分内容。服务器成功处理了部分GET请求"});
        body.add(new String[]{"", "", ""});
        body.add(new String[]{"300", "Multiple Choices", "多种选择。请求的资源可包括多个位置，相应可返回一个资源特征与地址的列表用于用户终端（例如：浏览器）选择"});
        body.add(new String[]{"301", "Moved Permanently", "永久移动。请求的资源已被永久的移动到新URI，返回信息会包括新的URI，浏览器会自动定向到新URI。今后任何新的请求都应使用新的URI代替"});
        body.add(new String[]{"302", "Found", "临时移动。与301类似。但资源只是临时被移动。客户端应继续使用原有URI"});
        body.add(new String[]{"303", "See Other", "查看其它地址。与301类似。使用GET和POST请求查看"});
        body.add(new String[]{"304", "Not Modified", "未修改。所请求的资源未修改，服务器返回此状态码时，不会返回任何资源。客户端通常会缓存访问过的资源，通过提供一个头信息指出客户端希望只返回在指定日期之后修改的资源"});
        body.add(new String[]{"305", "Use Proxy", "使用代理。所请求的资源必须通过代理访问"});
        body.add(new String[]{"306", "Unused", "已经被废弃的HTTP状态码"});
        body.add(new String[]{"307", "Temporary Redirect", "临时重定向。与302类似。使用GET请求重定向"});
        body.add(new String[]{"–", "–", "–"});
        body.add(new String[]{"400", "Bad Request", "客户端请求的语法错误，服务器无法理解"});
        body.add(new String[]{"401", "Unauthorized", "请求要求用户的身份认证"});
        body.add(new String[]{"402", "Payment Required", "保留，将来使用"});
        body.add(new String[]{"403", "Forbidden", "服务器理解请求客户端的请求，但是拒绝执行此请求"});
        body.add(new String[]{"404", "Not Found", "服务器无法根据客户端的请求找到资源（网页）。通过此代码，网站设计人员可设置\"您所请求的资源无法找到\"的个性页面"});
        body.add(new String[]{"405", "Method Not Allowed", "客户端请求中的方法被禁止"});
        body.add(new String[]{"406", "Not Acceptable", "服务器无法根据客户端请求的内容特性完成请求"});
        body.add(new String[]{"407", "Proxy Authentication Required", "请求要求代理的身份认证，与401类似，但请求者应当使用代理进行授权"});
        body.add(new String[]{"408", "Request Time-out", "服务器等待客户端发送的请求时间过长，超时"});
        body.add(new String[]{"409", "Conflict", "服务器完成客户端的 PUT 请求时可能返回此代码，服务器处理请求时发生了冲突"});
        body.add(new String[]{"410", "Gone", "客户端请求的资源已经不存在。410不同于404，如果资源以前有，现在被永久删除了可使用410代码，网站设计人员可通过301代码指定资源的新位置"});
        body.add(new String[]{"411", "Length Required", "服务器无法处理客户端发送的不带Content-Length的请求信息"});
        body.add(new String[]{"412", "Precondition Failed", "客户端请求信息的先决条件错误"});
        body.add(new String[]{"413", "Request Entity Too Large", "由于请求的实体过大，服务器无法处理，因此拒绝请求。为防止客户端的连续请求，服务器可能会关闭连接。如果只是服务器暂时无法处理，则会包含一个Retry-After的响应信息"});
        body.add(new String[]{"414", "Request-URI Too Large", "请求的URI过长（URI通常为网址），服务器无法处理"});
        body.add(new String[]{"415", "Unsupported Media Type", "服务器无法处理请求附带的媒体格式"});
        body.add(new String[]{"416", "Requested range not satisfiable", "客户端请求的范围无效"});
        body.add(new String[]{"417", "Expectation Failed", "服务器无法满足Expect的请求头信息"});
        body.add(new String[]{"", "", ""});
        body.add(new String[]{"500", "Internal Server Error", "服务器内部错误，无法完成请求"});
        body.add(new String[]{"501", "Not Implemented", "服务器不支持请求的功能，无法完成请求"});
        body.add(new String[]{"502", "Bad Gateway", "作为网关或者代理工作的服务器尝试执行请求时，从远程服务器接收到了一个无效的响应"});
        body.add(new String[]{"503", "Service Unavailable", "由于超载或系统维护，服务器暂时的无法处理客户端的请求。延时的长度可包含在服务器的Retry-After头信息中"});
        body.add(new String[]{"504", "Gateway Time-out", "充当网关或代理的服务器，未及时从远端服务器获取请求"});
        body.add(new String[]{"505", "HTTP Version not supported", "服务器不支持请求的HTTP协议的版本，无法完成处理"});

        ConsoleTable.println(header, body);
    }


    public static Map getHttpServletRequest(HttpServletRequest request) {
        Map map = new HashMap();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (!paramValue.isEmpty()) {
                    map.put(paramName, paramValue);
                }
            }
        }
        return map;
    }

    public static Map getRemoteInfo(HttpServletRequest request) {
        Map m = new HashMap();
        String remoteHost = request.getRemoteHost();
        String remoteUser = request.getRemoteUser();
        String remoteAddress = request.getRemoteAddr();
        int remotePort = request.getRemotePort();
        String protocol = request.getProtocol();
        String servletPath = request.getServletPath();

        m.put("remoteHost", remoteHost == null ? "null" : remoteHost);
        m.put("remoteUser", remoteUser == null ? "null" : remoteUser);
        m.put("remoteAddress", remoteAddress == null ? "null" : remoteAddress);
        m.put("remotePort", remotePort);
        m.put("protocol", protocol == null ? "" : protocol);
        m.put("servletPath", servletPath == null ? "null" : servletPath);
        return m;
    }

    public static String getClientIP(HttpServletRequest request) {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader != null) {
            return xfHeader.split(",")[0];
        }
        return request.getRemoteAddr();
    }

    public static List<String> requestInfo(HttpServletRequest request) {
        List<String> requestInfo = new ArrayList<>();

        String agent = request.getHeader("user-agent");
        requestInfo.add("Http header: " + agent);

        System.out.println(agent);
        StringTokenizer st = new StringTokenizer(agent, ";");
        st.nextToken();
        String clientOs = st.nextToken();
        System.out.println(clientOs);
        requestInfo.add("Client OS: " + clientOs);

        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.version"));
        System.out.println(System.getProperty("os.arch"));
        requestInfo.add("os.name: " + System.getProperty("os.name"));
        requestInfo.add("os.version: " + System.getProperty("os.version"));
        requestInfo.add("os.arch: " + System.getProperty("os.arch"));

        System.out.println(request.getHeader("user-agent")); //返回客户端浏览器的版本号, 类型
        System.out.println(request.getMethod()); //: 获得客户端向服务器端传送数据的方法有get, post, put等类型
        System.out.println(request.getRequestURI()); //: 获得发出请求字符串的客户端地址
        System.out.println(request.getServletPath()); //: 获得客户端所请求的脚本文件的文件路径
        System.out.println(request.getServerName()); //: 获得服务器的名字
        System.out.println(request.getServerPort()); //: 获得服务器的端口号
        System.out.println(request.getRemoteAddr()); //: 获得客户端的ip地址
        System.out.println(request.getRemoteHost()); //: 获得客户端电脑的名字,若失败,则返回客户端电脑的ip地址
        System.out.println(request.getProtocol());
        System.out.println(request.getHeaderNames()); //: 返回所有request header的名字,结果集是一个enumeration(枚举)类的实例

        requestInfo.add("Header: " + request.getHeader("user-agent"));
        requestInfo.add("Method: " + request.getMethod());
        requestInfo.add("RequestURI: " + request.getRequestURI());
        requestInfo.add("ServletPath: " + request.getServletPath());
        requestInfo.add("ServerName: " + request.getServerName());
        requestInfo.add("ServerPort: " + request.getServerPort());
        requestInfo.add("RemoteAddr: " + request.getRemoteAddr());
        requestInfo.add("RemoteHost: " + request.getRemoteHost());
        requestInfo.add("Protocol: " + request.getProtocol());
        requestInfo.add("HeaderNames: " + request.getHeaderNames());

        System.out.println("Protocol: " + request.getProtocol());
        System.out.println("Scheme: " + request.getScheme());
        System.out.println("Server Name: " + request.getServerName());
        System.out.println("Server Port: " + request.getServerPort());
        System.out.println("Protocol: " + request.getProtocol());
        System.out.println("Remote Address: " + request.getRemoteAddr());
        System.out.println("Remote Host: " + request.getRemoteHost());
        System.out.println("Character Encoding: " + request.getCharacterEncoding());
        System.out.println("Content Length: " + request.getContentLength());
        System.out.println("Content Type: " + request.getContentType());
        System.out.println("Auth Type: " + request.getAuthType());
        System.out.println("HTTP Method: " + request.getMethod());
        System.out.println("Path Info: " + request.getPathInfo());
        System.out.println("Path Trans: " + request.getPathTranslated());
        System.out.println("Query String: " + request.getQueryString());
        System.out.println("Remote User: " + request.getRemoteUser());
        System.out.println("Session Id: " + request.getRequestedSessionId());
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Servlet Path: " + request.getServletPath());
        System.out.println("Accept: " + request.getHeader("Accept"));
        System.out.println("Host: " + request.getHeader("Host"));
        System.out.println("Referer : " + request.getHeader("Referer"));
        System.out.println("Accept-Language : " + request.getHeader("Accept-Language"));
        System.out.println("Accept-Encoding : " + request.getHeader("Accept-Encoding"));
        System.out.println("User-Agent : " + request.getHeader("User-Agent"));
        System.out.println("Connection : " + request.getHeader("Connection"));
        System.out.println("Cookie : " + request.getHeader("Cookie"));

        requestInfo.add("Protocol: " + request.getProtocol());
        requestInfo.add("Scheme: " + request.getScheme());
        requestInfo.add("Server Name: " + request.getServerName());
        requestInfo.add("Server Port: " + request.getServerPort());
        requestInfo.add("Protocol: " + request.getProtocol());
        requestInfo.add("Remote Address: " + request.getRemoteAddr());
        requestInfo.add("Remote Host: " + request.getRemoteHost());
        requestInfo.add("Character Encoding: " + request.getCharacterEncoding());
        requestInfo.add("Content Length: " + request.getContentLength());
        requestInfo.add("Content Type: " + request.getContentType());
        requestInfo.add("Auth Type: " + request.getAuthType());
        requestInfo.add("HTTP Method: " + request.getMethod());
        requestInfo.add("Path Info: " + request.getPathInfo());
        requestInfo.add("Path Trans: " + request.getPathTranslated());
        requestInfo.add("Query String: " + request.getQueryString());
        requestInfo.add("Remote User: " + request.getRemoteUser());
        requestInfo.add("Session Id: " + request.getRequestedSessionId());
        requestInfo.add("Request URI: " + request.getRequestURI());
        requestInfo.add("Servlet Path: " + request.getServletPath());
        requestInfo.add("Accept: " + request.getHeader("Accept"));
        requestInfo.add("Host: " + request.getHeader("Host"));
        requestInfo.add("Referer : " + request.getHeader("Referer"));
        requestInfo.add("Accept-Language : " + request.getHeader("Accept-Language"));
        requestInfo.add("Accept-Encoding : " + request.getHeader("Accept-Encoding"));
        requestInfo.add("User-Agent : " + request.getHeader("User-Agent"));
        requestInfo.add("Connection : " + request.getHeader("Connection"));
        requestInfo.add("Cookie : " + request.getHeader("Cookie"));

        return requestInfo;
    }

}

