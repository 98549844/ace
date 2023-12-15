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

v-if / v-else / v-else-if
指令用于条件性地渲染一块内容
使用 v-else 为 v-if 添加一个“else 区块”
一个 v-else 元素必须跟在一个 v-if
或者 v-else-if 元素后面，否则它将不会被识别

v-show = v-if
v-show初始时开销开, 适合频繁切换状态, 基于css渲染

v-for
指令的值需要使用 item in items 形式的特殊语法，
其中 items 是源数据的数组，而 item 是迭代项的别名
可以使用 v-for 来遍历一个对象的所有属性。
遍历的顺序会基于对该对象调用 Object.keys() 的返回值来决定
v-for关键值
value key index => 这是它的默认顺序

v-on 简写为 @
事件处理
使用 v-on 指令 (简写为 @) 来监听 DOM 事件，
并在事件触发时执行对应的 JavaScript。
用法：v-on:click="handler" 或 @click="handler"

事件处理器 (handler) 的值可以是：
内联事件处理器：事件被触发时执行的内联 JavaScript 语句 (与 onclick 类似)。
方法事件处理器：一个指向组件上定义的方法的属性名或是路径。

<script>
export default {
  methods: {
    addCount(num, e) {
      //e for event对像
      //vue里面的event对像就是原生js对像
      console.log(e)
      //读取data内数值
      this.count = this.count + num + 1
    }
  },
  data() {
    return {
      count: 4,
      msg: 'hello world',
      rawHtml: <a href="https://google.com">google</a>,
      dynamicId: 'appId',
      dynamicClass: 'active',
      objectAttrs: {
        id: 'objectId',
        class: 'objectActive',
      },
      vif: true,
      vType: 'aa',
      forItems: ['aa', 'bb', 'cc'],
      forObject: [
        {
          id: 101,
          title: 'How to do lists in Vue',
          author: 'Jane Doe',
          publishedAt: '2016-04-10'
        }
      ]
    }
  }
}
</script>

<p>
<span>{{ msg }}</span>
<span v-html="rawHtml"></span>
<span v-bind:id="dynamicId" :class="dynamicClass">bind attributes 绑定属性</span>
<span v-bind="objectAttrs">绑定对像属性,一次绑定多属性</span>
<span v-if="vif"></span>
<span v-else></span>
<span v-if="vType==='a'">a</span>
<span v-else-if="vType==='b'">b</span>
<span v-else>not a/b</span>
<span v-for="item in forItems">{{ item }}</span>
<span v-for="(title,author,publishedAt) in forItems" :key="forItems.id">
  {{ title }},{{ author }},{{ publishedAt }}
</span>
<button @click="addCount(4, $event)">add</button>
<span>{{ count }}</span>
</p>
