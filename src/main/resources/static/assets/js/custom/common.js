// window.addEventListener('DOMContentLoaded', function () {
//     // 这里写入需要在页面加载完成后执行的 JavaScript 代码
// });
//$(document).ready(function () {
jQuery(function ($) {
    docReady();
    setTitle();
    // getIcon();
});

function isAdmin(admin) {
    return admin.indexOf("ADMIN") !== -1;
}


function printUrl() {
    const currentURL = window.location.href; // http://192.168.1.100:8088/ace/index.html
    const currentPathname = window.location.pathname; // /ace/index.html
    const currentHost = window.location.host; // 192.168.1.100:8088
    const currentPort = window.location.port; // 8088

    const result = 'currentURL: ' + currentURL + '\n' +
        'currentPathname: ' + currentPathname + '\n' +
        'currentHost: ' + currentHost + '\n' +
        'currentPort: ' + currentPort + '\n';

    alert(result)
}

function getIcon() {
    alert("common.js => getIcon()");
    let iconHtml = "";
    const fileName = 'garlam-icon-xxxxx';
    //拿不到值
    const username = /*[[${currentUser.username}]]*/ '';
    // const icon = /*[[${icon}]]*/ '';
    // alert(username + "  " + icon);
    iconHtml = iconHtml + `<img class="nav-user-photo" src="/ace/users/avatar/get/${fileName}" alt="150x150"/>
                             <span class="user-info">
                                <small>Welcome,</small>
                                ${username}
                            </span>
                            <i class="ace-icon fa fa-caret-down"></i>`;

    $("#navIcon").html(iconHtml);
}

function setTitle() {
    const pName = window.location.pathname;
    //  alert("pName: "+pName);
    if (pName.indexOf("/ace/") !== -1) {
        //"/ace/" 存在
        const t = pName.split("/ace/")[1].split(".html")[0];
        let m = t.toLowerCase().replace(/( |^)[a-z]/g, (L) => L.toUpperCase());
        //首字母大写
        $("title").html("Ace " + m);
        $("#breadcrumb2").text(m);
    } else {
        $("title").html("Ace Application");
    }
}

//循环左边menu, 用作控制class
//暂时技持两层打开
function docReady() {
    // const aHref = "a[href*='.html']"; // a元素并四配.html
    const aHref = "#sidebar a[href*='.html']"; //id=sidebar a元素并四配.html

    const currentForm = "#current_form";
    //提交表单验证
    $(currentForm).off("submit");
    $(currentForm).on("submit", function () {
        showLoading();
        return true;
    });

    // const currentURL = window.location.href; // fullUrl
    const currentURL = window.location.pathname; // /ace/index.html
    $.each($(aHref), function () {
        const link = $(this).attr("href");
        if (link === currentURL) {
            $(this).parent().parent().parent().addClass("active").addClass("open");
            $(this).parent().addClass("active");
        }
    });
}

$("a.logout").on("click", function () {
    //mobile logout and hidden menu
    //  $(".navbar-toggle.menu-toggler.pull-left.display").removeClass(".display");
    //  $("#sidebar").removeClass(".display");
    logout();
});

function isMobile() {
    return (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent));
}

function logout() {
    //clear cookies
    $.cookie('currentURL', '');
    //  /ace/logout.html
    d = dialog({
        title: 'Logout',
        content: 'Confirm to logout ?',
        okValue: 'Confirm',
        ok: function () {
            this.title('Logging out …');
            $.ajax({
                type: "get", // 以get方式发起请求
                url: "logout.html",
                // 绝对路径 url: 'http://' + window.location.host + '/rest/logout.html',
                success(data) {
                    window.location.href = '/ace/logout.html';
                   // 绝对路径  window.location.href = 'http://' + window.location.host + '/ace/logout.html';

                }
            })
            /*  setTimeout(() => {
              }, 3000);*/
            //   location.href = '/ace/login.html';
            return false;
        },
        cancelValue: 'Cancel',
        cancel: function () {
        }
    });
    d.showModal();
}


/**
 * 弹出一个加载弹出框
 */
let d;

function showLoading() {
    d = dialog({
        content: $("#loading_img").html(),
    });
    d.showModal();
    setTimeout(function () {
        d.close().remove();
    }, 120000);
}

/**
 * 关闭加载弹出框
 */
function closeLoading() {
    d.close().remove();
}

//-------------------------------------------------------------------------------------------------------------
//获取页面顶部被卷起来的高度
function scrollTop() {
    return Math.max(
        //chrome
        document.body.scrollTop,
        //firefox/IE
        document.documentElement.scrollTop);
}

//获取页面文档的总高度
function documentHeight() {
    //现代浏览器（IE9+和其他浏览器）和IE8的document.body.scrollHeight和document.documentElement.scrollHeight都可以
    return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
}

//获取页面浏览器视口的高度
function windowHeight() {
    //document.compatMode有两个取值。BackCompat：标准兼容模式关闭。CSS1Compat：标准兼容模式开启。
    return (document.compatMode === "CSS1Compat") ?
        document.documentElement.clientHeight :
        document.body.clientHeight;
}

//-------------------------------------------------------------------------------------------------------------


//check scrolling up or down
let position = $(window).scrollTop();

function scrollDown() {
    let down;
    const scroll = $(window).scrollTop();
    if (scroll > position) {
        console.log("向下滚动");
        down = true;
    } else {
        console.log("向上滚动");
        down = false;
    }
    position = scroll;
    return down;
}











