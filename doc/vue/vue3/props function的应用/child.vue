<script>
export default {
  data() {
    return {}
  },
  props: {
    title: String,
    onEvent:Function
  }
}
</script>

<template>
  <div>child B</div>
  <div>{{ title }}</div>
  <div>{{ onEvent("child data event") }}</div>
</template>
