<script>
export default {
  data() {
    return {
      displayColor: 'red',
      displaySize: 30,
      styleObject: {
        color: 'red',
        fontSize: '50px',
      }
    }
  }
}
</script>

<template>
  <p :style="{color: displayColor, fontSize: displaySize+'px'}">style binding</p>
  <p :style="styleObject">style object binding</p>
</template>


