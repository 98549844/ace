<script>
/*

生命周期function
创建期 beforeCreate created
挂载期 beforeMount mounted
更新期 beforeUpdate Updated
锁毁期 beforeUnmount Unmounted
*/

export default {
  data() {
    return {
      message: 'before update',
      banner: []
    }
  },
  beforeCreate() {
    console.log("组件创建之前")
  },
  created() {
    console.log("组件创建")
    //模拟网络请求
    this.banner = [
      {
        "title":"title 1",
        "content":"content 1"
      },
      {
        "title":"title 2",
        "content":"content 2"
      },
      {
        "title":"title 3",
        "content":"content 3"
      }
    ]

  },
  beforeMount() {
    console.log("组件挂载之前")

  },
  mounted() {
    console.log("组件挂载")
    console.log(this.$refs.name)
  },

  beforeUpdate() {
    console.log("组件更新之前")
  },
  updated() {
    console.log("组件更新")
  },
  beforeUnmount() {
    console.log("组件销毁之前")
  },
  unmounted() {
    console.log("组件销毁")
  },
  methods: {
    updateEvent() {
      this.message = 'updated after'
    }
  }
}
</script>

<template>
  <h3>组件生命周期</h3>
  <br>
  <p>{{ message }}</p>
  <button @click="updateEvent"> update</button>
  <br>
  <h3>组件生命周期 function</h3>
  <p ref="name"> system programmer </p>
  <ul>
    <li v-for="(item,index) in banner" :key="index">
      <h3>{{ item.title }}</h3>
      <p>{{ item.content }}</p>
    </li>
  </ul>
</template>
