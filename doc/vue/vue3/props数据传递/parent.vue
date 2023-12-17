<script>
import Child from "@/components/child.vue"

export default {
  components:{
    Child
  },
  data(){
    return{
      msg:'parent status message'
    }
  }
}

</script>

<template>
  <div>parent</div>
  <Child :parentStatus="msg"></Child>
</template>

注意:
props传递数据只能从parent传到child, 不能反过来传递
props支持字符, 数字, 对像, 数组
