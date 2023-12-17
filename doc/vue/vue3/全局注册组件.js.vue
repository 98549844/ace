import Header from "@/components/header.vue";

// 全局注册组件
const app = createApp(App)
app.component('Header', Header) //把引入的template组件注入vue application内, 并让全局引用
app.mount('#app')
