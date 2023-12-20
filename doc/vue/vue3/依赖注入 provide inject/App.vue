<script>
import Parent from "@/components/dependentInjection/parent.vue";

export default {
  components: {Parent},
  provide() {
    return{
      rootMessage: this.rootMsg
    }
  },
  data(){
    return{
      rootMsg: "root msg"
    }
  }

}

</script>
<template>
  <h3>root</h3>
  <br>
  <parent></parent>
</template>

注意:
provide和inject只能由上而下传递, 并他们有依赖关系

