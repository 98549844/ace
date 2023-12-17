<script>
export default {
  data() {
    return {
      content: '内容',
    }
  },
  methods: {
    getElement() {
      console.log(this.$refs.container.innerHTML = "hello world")
    }
  }
}
</script>

<template>
  <div ref="container" class="container">{{ content }}</div>
  <button @click="getElement">getElement</button>
</template>

关键字: ref / $refs

