<script>
export default {
  data() {
    return {
      message: "",
      checked: true,
    }
  }
}
</script>

<template>
  <h3>input form</h3>
  <form>
    <input type="text" v-model="message">
    <p>{{ message }}</p>
    <input type="checkbox" id="checkbox" v-model="checked">
    <label for="checkbox">{{ checked }}</label>
  </form>

</template>

v-model.number: 只接受数字
v-model.trim: 去除空格
v-model.lazy: 非实时执行, 意思是当输入完成才执行logic
