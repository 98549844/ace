<template>
  <h3>{{ obj.name }}</h3>
  <p>{{ getResult }}</p>
  <p>{{ getResults() }}</p>
</template>

<script>
export default {
  data() {
    return {
      obj: {
        name: 'xxx',
        nameList: ['aa', 'bb', 'cc'],
      }
    }
  },
  //计算属性
  computed:{
    getResult() {
      return this.nameList.contain.length > 0 ? 'Yes' : 'No'
    }
  },
  //执行方法
  methods:{
    getResults() {
      return this.nameList.contain.length > 0 ? 'Yes' : 'No'
    }
  }
}
</script>


