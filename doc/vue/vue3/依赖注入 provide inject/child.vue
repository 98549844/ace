<script>
import {inject} from "vue";

export default {
  data() {
    return {
      childMsg: 'child get root ' + this.rootMessage,
      globalMsg: this.globalData,
    }
  },
  inject: ['rootMessage', 'globalData'],

}
</script>

<template>
  <p>Child</p>
  <br>
  <p>{{ childMsg }}</p>
  <br>
  <p>{{globalMsg}}</p>

</template>

