<script>
export default {
  methods: {
    clickEvent() {
      console.log("this is child !")
      this.$emit("someEvent", "child data");
      //第一个参数是事件方法名: event
      //第二个参数是传递数据: args
    }
  }
}
</script>

<template>
  <div>child</div>
  <button @click="clickEvent">传递数据</button>
</template>

