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

function getUUID() {
    //使用浏览器内置api生成uuid
    return crypto.randomUUID();
}

//https://blog.csdn.net/tiven_/article/details/135554988
function getUuid() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        const r = Math.random() * 16 | 0, v = c === 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
}
