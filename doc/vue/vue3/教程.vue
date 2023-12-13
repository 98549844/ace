{{ }}:
双大括号将数值以纯文本插入, 等效innerText('')
它不能在HTML attributes里面使用

v-html:
插入html, 等效innerHtml('')

v-bind: 简写为直接冒号
:id
:class
绑定attribute, 需要用v-bind:class绑定数据
null或undefined, 会被属性移除
支持boolean属性

<script>
export default {
  data() {
    return {
      msg: 'hello world',
      rawHtml: <a href="https://google.com">google</a>,
      dynamicId: 'appId',
      dynamicClass: 'active',
      objectAttrs: {
        id: 'objectId',
        class: 'objectActive',
      }
    }
  }
}
</script>

<p>
<span>{{ msg }}</span>
<span v-html="rawHtml"></span>
<span v-bind:id="dynamicId" :class="dynamicClass">bind attributes 绑定属性</span>
<span v-bind="objectAttrs">绑定对像属性,一次绑定多属性</span>
</p>
