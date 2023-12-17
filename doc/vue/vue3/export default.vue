<script>

import componentsName from "@/components/componentsName.vue";

export default {
  data() {
    return {
      name: 'data',
    }
  },
  components: {
    componentsName,
  },
  //计算属性, 如果计算执行结果一样, 属性只执行一次并缓存
  computed: {
    calcComputed() {
      console.log("computed attr")
    }
  },
  //执行方法
  methods: {
    calcMethods() {
      console.log("methods attr")
    }
  },
  watch: {
    name(newVal, oldVal) {

    }
  }
}
</script>

<template>

</template>

