<script>
import child from "@/components/compDataTransfer/child.vue";

export default {
  components: {
    child
  },
  data() {
    return {
      msg: '',
    }
  },
  methods: {
    dataFn(data) {
      console.log(data);
      this.msg = data;
    }
  }
}
</script>

<template>
  <div>parent A</div>
  <div>{{msg}}</div>
  <child title="title B" :onEvent="dataFn"></child>

</template>
