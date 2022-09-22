//$(document).ready(function () {
jQuery(function ($) {
    //menu class
    //active:selected
    //open:open

    //2layer menu
    $('ul.main-menu li a').each(function () {
        if (String(window.location).indexOf($(this)[0].href) !== -1) {
            $(this).parent().parent().parent().addClass('active open');
            if ($("#sidebar").find('active open')) {
                $("#sidebar").removeClass('active open');
            }
            $(this).parent().addClass('active');

            //获取父类html
            const fatherHTML = $("#main-menu").find('.active, .open').find('a span.menu-text').text().trim();
            $("#breadcrumb-lv2").html(fatherHTML);
            //获取自已html
            const selfHTML = $(this).text().trim();
            if (fatherHTML != selfHTML) {
                $("#breadcrumb-lv3").html(selfHTML);
            } else {
                $("#breadcrumb-lv3").html("");
            }
        }
    });
    //3layer menu
    $('ul.main-menu ul.submenu li a').each(function () {
        if (String(window.location).indexOf($($(this))[0].href) !== -1) {
            $(this).parent().parent().parent().parent().parent().addClass('active open');
            $(this).parent().parent().parent().addClass('active open');
            $(this).parent().addClass('active');
            var fatherHTML = $("#main-menu").find('.active, .open').find('a span.menu-text').text()
            $("#breadcrumb-lv2").html(fatherHTML);
            //获取父类html
            var fatherHTML = $("#main-menu").find('.active, .open').find('a span.menu-text').text()
            $("#breadcrumb-lv2").html(fatherHTML);
            //获取自已html
            var selfHTML = $(this).text();
            $("#breadcrumb-lv3").html(selfHTML);
        }
    });

    //其他需要动态加载的事件绑定
    //loading bar
    docReady();
    pageCss();
    manuCss();
});

/**
 * 弹出一个加载弹出框
 */
let d;

function showLoading() {
    d = dialog({
        content: $("#loading_img").html(),
    });
    d.show();
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

//menu css controller
function manuCss() {
    $(".menu").each(function () {
        // full url
        const fullHref = window.location.href;
        // url
        const href = $(this).attr("href");
        const result = fullHref.indexOf(href) !== -1;

        if (result && href === "/ace/users/user.html") {
            $("#userMenu").addClass("open");
            $("#userSubMenu1").addClass("active");
        } else if (result && href === "/ace/users/search.html") {
            $("#userMenu").addClass("open");
            $("#userSubMenu1").addClass("active");
        } else if (result && href === "/ace/roles.html") {
            $("#userMenu").addClass("open");
            $("#userSubMenu2").addClass("active");

        }
    });
}

//page css controller
function pageCss() {
    $(".pageForm").each(function () {
        // full url
        const fullHref = window.location.href;
        // url
        const href = $(this).attr("action");
        const result = fullHref.indexOf(href) !== -1;

        if (result && href === "/ace/roles/create.html") {
            $("#userMenu").addClass("open");
            $("#widgetBox").removeClass("collapsed");
            $("#faChevron").addClass("ace-icon fa fa-chevron-up");
        }
    });
}

function docReady() {
    //提交表单验证
    $("#current_form").off("submit");
    $("#current_form").on("submit", function () {
        showLoading();
        return true;
    });

    //链接点击绑定弹出加载框
    $("a[href*='.html']").off();
    $("a[href*='.html']").on("click", function () {
        if ($(this).attr("target") != "_blank") {
            showLoading();
        }
    });
}


