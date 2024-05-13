function getUuidFromServer() {
    let uuid;
    $.ajax({
        type: "get", // 以get方式发起请求
        url: "/rest/uuid/get.html",
        dataType: "json",
        async: false, //同歩执行
        success(data) {
            uuid = data.data;
        },
        error(data) {
            window.location.href = '/ace/500.html';
        }
    })
    return uuid;
}
