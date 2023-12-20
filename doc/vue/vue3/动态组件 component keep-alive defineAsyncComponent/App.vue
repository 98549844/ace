<script>
import {defineAsyncComponent} from "vue"; //引入异歩加载
import CompA from "@/components/dynicComp/compA.vue";
// import CompB from "@/components/dynicComp/compB.vue";


const CompB = defineAsyncComponent(() =>
    import("@/components/dynicComp/compB.vue"), //实现异歩加载方法
)

export default {
  components: {
    CompA,
    CompB
  },
  data() {
    return {
      tabComponent: CompA,
    }
  },
  methods: {
    changeComp() {
      const dComp = this.tabComponent === "CompA" ? "CompB" : "CompA";
      this.tabComponent = dComp

    }
  }
}
</script>
<template>
  <!--  <CompA></CompA>-->
  <!--  <br>-->
  <!--  <CompB></CompB>-->
  <br>
  <keep-alive>
    <component :is="tabComponent"></component>
  </keep-alive>
  <br>
  <button @click="changeComp">切换组件</button>
</template>



