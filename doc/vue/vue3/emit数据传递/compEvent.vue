<script>
import child from "@/components/child.vue"
export default {
  components:{
    child
  },
  methods:{
    getHandler(data){
      console.log("triggered", data)
      this.message = data;
    }
  },
  data(){
    return {
      message:''
    }
  }

}
</script>

<template>
  <div>组件事件</div>
  <child @someEvent="getHandler"></child>
  <div>{{ message }}</div>
</template>

重点
用作子template传数据上父template
关键字 this.$emit

