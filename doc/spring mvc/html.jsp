空格键: &nbsp;
用户资料: USER_LOGIN_SESSION
包括外部jsp:
<%@ include file="相关路径jsp文件" %>


<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    request.setAttribute("basePath", basePath);
    /* 将基本路径set入session */
%>

<html lang="en">
<head>
    <title>Document</title>
</head>
<body>
function1
<p>click here to display result</p>
<button onclick="myFunction()">click here</button>
<p id="demo"></p>

//form参数
<form id="current_form" action="url" method="post"></form>

<c:if>用法
    <c:if test="${not empty listName}">xxx</c:if>
    <c:if test="${empty listName}">xxx</c:if>
    <c:if test="${listName == 10}">xxx</c:if>
    <c:if test="${listName > 10}">xxx</c:if>

    //循环控制list
    varStatus="每个对象的状态"
    begin="循环从哪儿开始"
    end="循环到哪儿结束"
    step="循环的步长"
    <c:forEach items="${listName}" var="rowData" varStatus="count">
        <td>${rowData.id}</td>
    </c:forEach>

    //将value的对像set入var内供jsp调用
    <c:set var="po" value="${po.subPo}"/>

    js获取和设置<input>的值
    获取元素value
    <input id='userName' value='123' /> 

    元素一|元素二
    <div style="display:inline;">元素一|</div><div style="display:inline;">元素二</div>

    无序列明
    <dl>
    <dt>计算机</dt>
    <dd>用来计算的仪器 ... ...</dd>
    <dt>显示器</dt>
    <dd>以视觉方式显示信息的装置 ... ...</dd>
    </dl>

    <table>
    <tbody>
    <tr>
    <td>a</td>
    <td rowspan=2>a</td>//rowspan合并行(上下合并)，colspan合并列(左右合并)
    <td>b</td>
    </tr>
    <tr>
    <td>b</td>
    <td>b</td>
    </tr>
    </tbody>
    </table
    </body>
    </html>

    <ul id="ul-demo">
    <li class="demo" value="1">1</li>
    <li class="demo" value="2">2</li>
    <li class="demo" value="3">3</li>
    <li class="demo" value="4">4</li>
    </ul>

    <script type="text/javascript">

    $(document).ready(function(){
    //页面载入完成时自动加载JavaScript
    });

    $("#ul-demo"){
    $("demo").eq(0).html();//得到的就是1
    $("demo").eq(3).html();//得到的就是4

    //获得ul.demo的数组
    var arr=new Array();
    $(".demo").each(function() {
    arr.push($(this).val());
    //读每个i的值
    var index = $($(".demo").get(i)).val();
    });
    }

    $("*") 所有元素
    $("#lastname") id="lastname" 的元素
    $(".class") 所有 class="intro" 的元素
    $("element") 所有 <element> 元素
    $(".class.subclass") 所有class=”class”且class=”subclass的元素”

    //function1
    function myFunction()
    {
    var car = "toyota"
    document.getElementById("demo").innerHTML = car;
    }

    //jQuery 事件方法
    $("button#demo").click(function(){
    $("img").hide()})
    上面的例子将触发 id="demo" 的button元素的click事件

    //id绑定
    $("#m_mkFolder").bind("click",function(){
    $("#current_form").attr("action",basePath+"file/mkFolder.htmls");
    $("#current_form").submit();
    $("#current_form").attr("action", basePath + "page/fileManager/list.htmls");
    });

    //class绑定
    $(".editAddress").bind("click", function () {
    //获取自定义属性
    var index = $(this).attr("index");
    var pId = $(this).attr("pId");
    var cId = $(this).attr("cId");
    //javascript方法体
    });

    //ajax异步加载
    $(".class").click(function () {//挷定className
    $.ajax({
    url: basePath + "setDefaultAddress.html",//绑定后台Controller
    //单一或多个提交数据
    data: {
    "addressId": $(this).attr("addressId")
    },
    //整个表单提交
    data: $('#addr_form').serialize(),
    //如果不是json格式, dataType: "json",不要写入
    dataType: "json",
    type: "POST",
    success: function (data) {
    if (data) {
    window.location.reload();
    }
    }
    });
    });

    //jq加class和删除class
    $("#sequence").bind("click", function(){
    $(".sort").find("li.first").removeClass("first");
    $("#sequence").addClass("first");
    });

    //获取元素value
    var val = $("#userName").val(); //获取id值
    var val = $("#userName").val("abc"); //设置id值

    //一段html代码
    var code="<div class='form-group' style='width:700px'>";
    $("#tBodyDiv").append(code);//插入一段html
    $("#no-information").hide();//隐藏一段html
    $("#no-information").show();//显示一段html
    $("#tBodyDiv").remove();//删除一段html
    $("#tBodyDiv ").html(code);//代替一段html

    //页面刷新方法
    window.location.reload(); 刷新当前页面.
    parent.location.reload(); 刷新父亲对象（用于框架）
    opener.location.reload(); 刷新父窗口对象（用于单开窗口）
    top.location.reload(); 刷新最顶端对象（用于多开窗口）

    //jquery通过name属性获取html对象并赋值为string
    $("input[name='name']").val("string");

    //获取滚动Y轴的px数值
    var y = event.clientY + $(window).scrollTop();
    //获取滚动x轴的px数值
    var x = event.clientX + $(window).scrollLeft();

    var s = "";
    s += " 网页可见区域宽："+ document.body.clientWidth+"<br />";
    s += " 网页可见区域高："+ document.body.clientHeight+"<br />";
    s += " 网页可见区域宽："+ document.body.offsetWidth + " (包括边线和滚动条的宽)"+"<br />";
    s += " 网页可见区域高："+ document.body.offsetHeight + " (包括边线的宽)"+"<br />";
    s += " 网页正文全文宽："+ document.body.scrollWidth+"<br />";
    s += " 网页正文全文高："+ document.body.scrollHeight+"<br />";
    s += " 网页被卷去的高(ff)："+ document.body.scrollTop+"<br />";
    s += " 网页被卷去的高(ie)："+ document.documentElement.scrollTop+"<br />";
    s += " 网页被卷去的左："+ document.body.scrollLeft+"<br />";
    s += " 网页正文部分上："+ window.screenTop+"<br />";
    s += " 网页正文部分左："+ window.screenLeft+"<br />";
    s += " 屏幕分辨率的高："+ window.screen.height+"<br />";
    s += " 屏幕分辨率的宽："+ window.screen.width+"<br />";
    s += " 屏幕可用工作区高度："+ window.screen.availHeight+"<br />";
    s += " 屏幕可用工作区宽度："+ window.screen.availWidth+"<br />";
    s += " 你的屏幕设置是 "+ window.screen.colorDepth +" 位彩色"+"<br />";
    s += " 你的屏幕设置 "+ window.screen.deviceXDPI +" 像素/英寸"+"<br />";
    </script>