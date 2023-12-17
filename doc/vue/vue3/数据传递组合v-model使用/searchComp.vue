<script>

export default {
  data() {
    return {
      search: '' // default value
    }
  },
  //侦听器
  watch:{
    search(newVal, oldVal){
      this.$emit("searchEvent", newVal)

    }
  }
}
</script>

<template>
  <label>searching: </label>
  <input type="text" v-model="search">
</template>

