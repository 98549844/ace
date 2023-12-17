<script>
export default {
  data() {
    return {
      names: ['aa', 'bb', 'cc'],
    }
  },
  methods: {
    addNames() {
      this.names.push('dd');
    //  this.names = this.names.concat('ff');
    }
  }
}
</script>

<template>
  <h3>数组变化</h3>
  <button @click="addNames()">add</button>
  <ul>
    <li v-for="(name, index) in names" :key="index">{{ name }}</li>
  </ul>
</template>

数组更新或变更
变更方法
push()
pop()
shift()
unshift()
splice()
sort()
reverse()

替代数组(ui不会发生变化)
filter()
concat()
slice()
