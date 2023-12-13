<template>
  <p>{{ num + 1 }}</p>
  <p>{{ ok ? 'yes' : 'no' }}</p>
  <p>{{msg.split(' ').reverse().join('garlam')}}</p> <!--reverse(): 字符串数组反转-->
</template>

<script>
export default {
  data() {
    return {
      num: 10,
      ok: true,
      msg: 'hello world',
    }
  }
}
</script>
