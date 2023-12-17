<script>
export default {
  data() {
    return {
      isActive: true, //boolean logic
      hasError: true,
      classObject:{ // object
        'active':true,
        'text-size': true
      },
      arrayActive:'active',
      arrayError: 'text-size',
      //class的数组和对像嵌套中, 必须是数组嵌套提到像
      //:class = "[{'active':isActive, 'text-size': hasError} , arrayActive , arrayError ]"
    }
  }
}
</script>

<template>
  <p :class="{'active':isActive, 'text-size': hasError}">class binding</p>
  <p :class="classObject">class object binding</p>
  <p :class="[arrayActive, arrayError]">class array binding</p>
</template>

<style scoped>
.active {
  color: red;
}

.text-size {
  font-size: 30px;
}


</style>
