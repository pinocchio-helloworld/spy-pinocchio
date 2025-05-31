<template>
  <!--<Navbar v-if="$route.name !== 'userLogin'"></Navbar>-->
  <!-- 路由视图 -->
  <router-view />
  <!--<Navbar></Navbar>-->
  <Navbar
    v-if="$route.name !== 'userLogin' && $route.name !== 'admin-register'"
  ></Navbar>
</template>

<script>
// 引入 navbar 组件
import Navbar from "./components/userNavbar.vue";

export default {
  components: {
    Navbar,
  },
};
</script>

<style lang="scss">
#app {
  font-family: Vanir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
