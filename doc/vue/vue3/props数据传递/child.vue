<script>
export default {
  data() {
    return {}
  },
  props:["parentStatus"]
}
</script>

<template>
  <div>child</div>
  <div>{{ parentStatus }}</div>
</template>
