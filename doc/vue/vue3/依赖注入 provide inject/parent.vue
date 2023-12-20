<script>
import Child from "@/components/dependentInjection/child.vue";

export default {
  components: {
    Child
  }
}

</script>

<template>
  <h3>Parent</h3>
  <br>
  <Child></Child>
</template>

