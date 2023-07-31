// Home.vue
export default {
  computed: {
    username() {
      // 我们很快就会看到 `params` 是什么
      return this.$route.params.username
    },
  },
  methods: {
    goToDashboard() {
      if (isAuthenticated) {
        this.$router.push('/dashboard')
      } else {
        this.$router.push('/login')
      }
    },
  },
}


<!--
要在 setup 函数中访问路由,
请调用 useRouter 或 useRoute 函数.
我们将在 Composition API 中了解更多信息.
在整个文档中, 我们会经常使用 router 实例,
请记住, this.$router 与直接使用通过 createRouter 创建的 router 实例完全相同.
我们使用 this.$router 的原因是, 我们不想在每个需要操作路由的组件中都导入路由.
-->
