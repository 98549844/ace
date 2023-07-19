package com.ace.util;

import com.util.AsciiTableUtil;
import com.util.Console;
import com.util.ConsoleTable;
import com.util.impl.consoleTable.table.Cell;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;

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
        List<Cell> header = new ArrayList<>() {{
            add(new Cell("常见的HTTP状态码"));
        }};

        List<List<Cell>> body = new ArrayList<>() {{
            add(new ArrayList<>() {{
                add(new Cell("200 - 请求成功"));
            }});
            add(new ArrayList<>() {{
                add(new Cell("301 - 资源(网页等)被永久转移到其它URL"));
            }});
            add(new ArrayList<>() {{
                add(new Cell("404 - 请求的资源(网页等)不存在"));
            }});
            add(new ArrayList<>() {{
                add(new Cell("500 - 内部服务器错误"));
            }});

        }};


        ConsoleTable.println(header, body);

    }

    public static void httpStatusCode() {
        Console.println("HTTP状态码的分类", Console.BLUE, Console.BOLD);
        List<Cell> header = new ArrayList<>() {{
            add(new Cell("code分类"));
            add(new Cell("分类描述"));
        }};

        List<List<Cell>> body = new ArrayList<>() {{
            add(new ArrayList<>() {{
                add(new Cell("1**"));
                add(new Cell("信息,服务器收到请求,需要请求者继续执行操作"));
            }});
            add(new ArrayList<>() {{
                add(new Cell("2**"));
                add(new Cell("成功,操作被成功接收并处理"));
            }});
            add(new ArrayList<>() {{
                add(new Cell("3**"));
                add(new Cell("重定向,需要进一步的操作以完成请求"));
            }});
            add(new ArrayList<>() {{
                add(new Cell("4**"));
                add(new Cell("客户端错误,请求包含语法错误或无法完成请求"));
            }});
            add(new ArrayList<>() {{
                add(new Cell("5**"));
                add(new Cell("服务器错误,服务器在处理请求的过程中发生了错误"));
            }});
        }};

        ConsoleTable.println(header, body);
    }

    public static void httpStatusList() {
        List<Cell> header = new ArrayList<>() {{
            add(new Cell("状态码"));
            add(new Cell("状态码英文名称"));
            add(new Cell("中文描述"));
        }};

        List<List<Cell>> body = new ArrayList<>() {{
            add(new ArrayList<>() {{add(new Cell("100"));add(new Cell("Continue"));add(new Cell("继续. 客户端应继续其请求"));}});
            add(new ArrayList<>() {{add(new Cell("101"));add(new Cell("Switching Protocols"));add(new Cell("切换协议. 服务器根据客户端的请求切换协议. 只能切换到更高级的协议, 例如, 切换到HTTP的新版本协议"));}});
            add(new ArrayList<>() {{add(new Cell(""));add(new Cell(""));add(new Cell(""));}});
            add(new ArrayList<>() {{add(new Cell("200"));add(new Cell("OK"));add(new Cell("请求成功. 一般用于GET与POST请求"));}});
            add(new ArrayList<>() {{add(new Cell("201"));add(new Cell("Created"));add(new Cell("已创建. 成功请求并创建了新的资源"));}});
            add(new ArrayList<>() {{add(new Cell("202"));add(new Cell("Accepted"));add(new Cell("已接受. 已经接受请求, 但未处理完成"));}});
            add(new ArrayList<>() {{add(new Cell("203"));add(new Cell("Non-Authoritative Information"));add(new Cell("非授权信息. 请求成功. 但返回的meta信息不在原始的服务器, 而是一个副本"));}});
            add(new ArrayList<>() {{add(new Cell("204"));add(new Cell("No Content"));add(new Cell("无内容. 服务器成功处理, 但未返回内容. 在未更新网页的情况下, 可确保浏览器继续显示当前文档"));}});
            add(new ArrayList<>() {{add(new Cell("205"));add(new Cell("Reset Content"));add(new Cell("重置内容. 服务器处理成功, 用户终端(例如: 浏览器)应重置文档视图. 可通过此返回码清除浏览器的表单域"));}});
            add(new ArrayList<>() {{add(new Cell("206"));add(new Cell("Partial Content"));add(new Cell("部分内容. 服务器成功处理了部分GET请求"));}});

            add(new ArrayList<>() {{add(new Cell(""));add(new Cell(""));add(new Cell(""));}});
            add(new ArrayList<>() {{add(new Cell("300"));add(new Cell("Multiple Choices"));add(new Cell("多种选择. 请求的资源可包括多个位置, 相应可返回一个资源特征与地址的列表用于用户终端(例如: 浏览器)选择"));}});
            add(new ArrayList<>() {{add(new Cell("301"));add(new Cell("Moved Permanently"));add(new Cell("永久移动. 请求的资源已被永久的移动到新URI, 返回信息会包括新的URI, 浏览器会自动定向到新URI. 今后任何新的请求都应使用新的URI代替"));}});
            add(new ArrayList<>() {{add(new Cell("302"));add(new Cell("Found"));add(new Cell("临时移动. 与301类似. 但资源只是临时被移动. 客户端应继续使用原有URI"));}});
            add(new ArrayList<>() {{add(new Cell("303"));add(new Cell("See Other"));add(new Cell("查看其它地址. 与301类似. 使用GET和POST请求查看"));}});
            add(new ArrayList<>() {{add(new Cell("304"));add(new Cell("Not Modified"));add(new Cell("未修改. 所请求的资源未修改, 服务器返回此状态码时, 不会返回任何资源. 客户端通常会缓存访问过的资源, 通过提供一个头信息指出客户端希望只返回在指定日期之后修改的资源"));}});
            add(new ArrayList<>() {{add(new Cell("305"));add(new Cell("Use Proxy"));add(new Cell("使用代理. 所请求的资源必须通过代理访问"));}});
            add(new ArrayList<>() {{add(new Cell("306"));add(new Cell("Unused"));add(new Cell("已经被废弃的HTTP状态码"));}});
            add(new ArrayList<>() {{add(new Cell("307"));add(new Cell("Temporary Redirect"));add(new Cell("临时重定向. 与302类似. 使用GET请求重定向"));}});
            add(new ArrayList<>() {{add(new Cell(""));add(new Cell(""));add(new Cell(""));}});

            add(new ArrayList<>() {{add(new Cell("400"));add(new Cell("Bad Request"));add(new Cell("客户端请求的语法错误, 服务器无法理解"));}});
            add(new ArrayList<>() {{add(new Cell("401"));add(new Cell("Unauthorized"));add(new Cell("请求要求用户的身份认证"));}});
            add(new ArrayList<>() {{add(new Cell("402"));add(new Cell("Payment Required"));add(new Cell("保留, 将来使用"));}});
            add(new ArrayList<>() {{add(new Cell("403"));add(new Cell("Forbidden"));add(new Cell("服务器理解请求客户端的请求, 但是拒绝执行此请求"));}});
            add(new ArrayList<>() {{add(new Cell("404"));add(new Cell("Not Found"));add(new Cell("服务器无法根据客户端的请求找到资源(网页). 通过此代码, 网站设计人员可设置\"您所请求的资源无法找到\" 的个性页面"));}});
            add(new ArrayList<>() {{add(new Cell("405"));add(new Cell("Method Not Allowed"));add(new Cell("客户端请求中的方法被禁止"));}});
            add(new ArrayList<>() {{add(new Cell("406"));add(new Cell("Not Acceptable"));add(new Cell("服务器无法根据客户端请求的内容特性完成请求"));}});
            add(new ArrayList<>() {{add(new Cell("407"));add(new Cell("Proxy Authentication Required"));add(new Cell("请求要求代理的身份认证, 与401类似, 但请求者应当使用代理进行授权"));}});
            add(new ArrayList<>() {{add(new Cell("408"));add(new Cell("Request Time-out"));add(new Cell("服务器等待客户端发送的请求时间过长, 超时"));}});
            add(new ArrayList<>() {{add(new Cell("409"));add(new Cell("Conflict"));add(new Cell("服务器完成客户端的 PUT 请求时可能返回此代码, 服务器处理请求时发生了冲突"));}});

            add(new ArrayList<>() {{add(new Cell("410"));add(new Cell("Gone"));add(new Cell("客户端请求的资源已经不存在. 410不同于404, 如果资源以前有, 现在被永久删除了可使用410代码, 网站设计人员可通过301代码指定资源的新位置"));}});
            add(new ArrayList<>() {{add(new Cell("411"));add(new Cell("Length Required"));add(new Cell("服务器无法处理客户端发送的不带Content-Length的请求信息"));}});
            add(new ArrayList<>() {{add(new Cell("412"));add(new Cell("Precondition Failed"));add(new Cell("客户端请求信息的先决条件错误"));}});
            add(new ArrayList<>() {{add(new Cell("413"));add(new Cell("Request Entity Too Large"));add(new Cell("由于请求的实体过大, 服务器无法处理, 因此拒绝请求. 为防止客户端的连续请求, 服务器可能会关闭连接. 如果只是服务器暂时无法处理, 则会包含一个Retry-After的响应信息"));}});
            add(new ArrayList<>() {{add(new Cell("414"));add(new Cell("Request-URI Too Large"));add(new Cell("请求的URI过长(URI通常为网址), 服务器无法处理"));}});
            add(new ArrayList<>() {{add(new Cell("415"));add(new Cell("Unsupported Media Type"));add(new Cell("服务器无法处理请求附带的媒体格式"));}});
            add(new ArrayList<>() {{add(new Cell("416"));add(new Cell("Requested range not satisfiable"));add(new Cell("客户端请求的范围无效"));}});
            add(new ArrayList<>() {{add(new Cell("417"));add(new Cell("Expectation Failed"));add(new Cell("服务器无法满足Expect的请求头信息"));}});
            add(new ArrayList<>() {{add(new Cell(""));add(new Cell(""));add(new Cell(""));}});
            add(new ArrayList<>() {{add(new Cell("500"));add(new Cell("Internal Server Error"));add(new Cell("服务器内部错误, 无法完成请求"));}});

            add(new ArrayList<>() {{add(new Cell("501"));add(new Cell("Not Implemented"));add(new Cell("服务器不支持请求的功能, 无法完成请求"));}});
            add(new ArrayList<>() {{add(new Cell("502"));add(new Cell("Bad Gateway"));add(new Cell("作为网关或者代理工作的服务器尝试执行请求时, 从远程服务器接收到了一个无效的响应"));}});
            add(new ArrayList<>() {{add(new Cell("503"));add(new Cell("Service Unavailable"));add(new Cell("由于超载或系统维护, 服务器暂时的无法处理客户端的请求. 延时的长度可包含在服务器的Retry-After头信息中"));}});
            add(new ArrayList<>() {{add(new Cell("504"));add(new Cell("Gateway Time-out"));add(new Cell("充当网关或代理的服务器, 未及时从远端服务器获取请求"));}});
            add(new ArrayList<>() {{add(new Cell("505"));add(new Cell("HTTP Version not supported"));add(new Cell("服务器不支持请求的HTTP协议的版本, 无法完成处理"));}});
        }};


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
                if (paramValue.length() != 0) {
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
        requestInfo.add("os.name : " + System.getProperty("os.name"));
        requestInfo.add("os.version : " + System.getProperty("os.version"));
        requestInfo.add("os.arch : " + System.getProperty("os.arch"));

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

