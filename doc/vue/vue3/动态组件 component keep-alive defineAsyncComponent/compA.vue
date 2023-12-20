<template>
  <h3>component A</h3>
  <br>
  <p>{{ message }}</p>
  <br>
  <button @click="updateEvent">update !!!</button>
</template>
<script>
export default {
  data() {
    return {
      message: "old data !!!"
    }
  },
  methods: {
    updateEvent() {
      this.message = 'new data'
    }
  },
  beforeUnmount() {
    console.log("component A 组件销毁之前")
  },
  unmounted() {
    console.log("component A 组件销毁")
  },
}

</script>

