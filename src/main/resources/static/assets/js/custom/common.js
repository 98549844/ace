//$(document).ready(function () {
jQuery(function ($) {
    docReady();
    setTitle();
});

function setTitle() {
    const pName = window.location.pathname;
    //  alert("pName: "+pName);
    if (pName.indexOf("/ace/") !== -1) {
        // "/ace/" exist
        const t = pName.split("/ace/")[1].split(".html")[0];
        let m = t.toLowerCase().replace(/( |^)[a-z]/g, (L) => L.toUpperCase());
        if ("Index" === m) {
            m = "Dashboard";
        }
        //首字母大写
        $("title").html("Ace " + m);
        $("#breadcrumb2").text(m);
    } else {
        $("title").html("Ace Application");
    }

}


function docReady() {
    const aHref = "a[href*='.html']";
    const currentForm = "#current_form";
    //提交表单验证
    $(currentForm).off("submit");
    $(currentForm).on("submit", function () {
        showLoading();
        return true;
    });

    //链接点击绑定弹出加载框
    $(aHref).off();
    $(aHref).on("click", function () {
        const link = $(this).attr("href");
        $.cookie('currentURL', link);

        //左菜单 loading bar
        /*  if ($(this).attr("target") !== "_blank") {
            setTimeout(() => {
            }, 1000);
            showLoading();
        }*/
    });

    const currentURL = $.cookie('currentURL');
    $.each($(aHref), function () {
        const link = $(this).attr("href");
        if (link === currentURL) {
            $(this).parent().parent().parent().addClass("active").addClass("open");
            $(this).parent().addClass("active");
        }
    });
}

$("a.logout").on("click", function () {
    logout();
});

function logout() {
    //clear cookies
    $.cookie('currentURL', '');
    //  /ace/logout.html
    d = dialog({
        title: 'Logout',
        content: 'Are you confirm log out !!!',
        okValue: 'Confirm',
        ok: function () {
            this.title('Logging out …');
            $.ajax({
                type: "get", // 以get方式发起请求
                url: "logout.html",
                success(data) {
                    window.location.href = '/ace/login.html';
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













