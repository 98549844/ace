//$(document).ready(function () {
jQuery(function ($) {
    docReady();
});

function docReady() {
    //提交表单验证
    $("#current_form").off("submit");
    $("#current_form").on("submit", function () {
        showLoading();
        return true;
    });

    //链接点击绑定弹出加载框
    $("a[href*='.html']").off();
    $("a[href*='.html']").off().on("click", function () {
        const link = $(this).attr("href");
        $.cookie('currentURL', link);
        if ($(this).attr("target") != "_blank") {
            setTimeout(() => {
            }, 1000);
            showLoading();
        }
    });

    const currentURL = $.cookie('currentURL');
    $.each($("a[href*='.html']"), function () {
        const link = $(this).attr("href");
        if (link === currentURL) {
            $(this).parent().parent().parent().addClass("active").addClass("open");
            $(this).parent().addClass("active");
        }
    });
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









