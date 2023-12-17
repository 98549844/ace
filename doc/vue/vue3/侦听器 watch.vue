<script>
export default {
  data() {
    return {
      msg: 'hello'
    }
  },
  methods: {
    updateValue() {
      this.msg = 'hello world'
    }
  },
  //侦听器, 侦听数据
  watch: {
    msg(newValue, oldValue) {
      //数据发生变化, 自动执行的function
      //watch的function要和return data一致
    }
  }
}
</script>

<template>
  <p>{{ msg }}</p>
  <button @click="updateValue">update</button>
</template>
