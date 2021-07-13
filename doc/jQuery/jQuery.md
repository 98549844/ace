jQuery 语法 - jQuery 选择器
$(this).hide()              隐藏当前元素
$("p").hide()               隐藏所有 <p> 元素
$("p.test").hide()          隐藏所有 class="test" 的 <p> 元素
$(".test").hide()           隐藏所有 class="test"
$("#test").hide()           隐藏 id="test" 的元素
    
$("*")                      选取所有元素	
$(this)                     选取当前 HTML 元素	
$("p:first")                选取第一个 <p> 元素
$("ul li:first")            选取第一个 <ul> 元素的第一个 <li> 元素	
$("ul li:first-child")	    选取每个 <ul> 元素的第一个 <li> 元素	
$("[href]")                 选取带有 href 属性的元素	
$("a[target='_blank']")	    选取所有 target 属性值等于 "_blank" 的 <a> 元素	
$("a[target!='_blank']")    选取所有 target 属性值不等于 "_blank" 的 <a> 元素	
$(":button")                选取所有 type="button" 的 <input> 元素 和 <button> 元素	
$("tr:even")                选取偶数位置的 <tr> 元素	
$("tr:odd")                 选取奇数位置的 <tr> 元素

jQuery 事件
鼠标事件	    键盘事件	    表单事件	    文档/窗口事件
click	    keypress	submit	    load
dblclick	keydown	    change	    resize
mouseenter	keyup	    focus	    scroll
mouseleave              blur	    unload
hover

jQuery 效果- 隐藏和显示
    $("#hide").click(h);
    $("#show").click(s);

    //1000: hide or show speed
    function h() {
        $("p").hide(1000);
    };
    function s() {
        $("p").show(1000);
    };

通过 jQuery，您可以使用 toggle() 方法来切换 hide() 和 show() 方法
    function press(){
        $("p").toggle();
    }