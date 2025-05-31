<template>
  <div class="nav-container">
    <div class="logo-area">
      <div class="logo-icon">
        <i class="fas fa-book-open fa-3x"></i>
      </div>
      <div class="logo-text">图书管理系统</div>
    </div>
    <nav class="nav-menu">
      <template v-if="userRole === 'user'">
        <div
          class="nav-item"
          :class="{ active: activeNav === 'home' }"
          @click="gotoPage('home', '/admin-homepage')"
        >
          <i class="fas fa-home"></i>
          <span>首页</span>
        </div>
        <div
          class="nav-item"
          :class="{ active: activeNav === 'search' }"
          @click="gotoPage('search', '/admin-booksearch')"
        >
          <i class="fas fa-search"></i>
          <span>图书查询</span>
        </div>
      </template>
      <template v-if="userRole === 'admin'">
        <div
          class="nav-item"
          :class="{ active: activeNav === 'home' }"
          @click="gotoPage('home', '/admin-homepage')"
        >
          <i class="fas fa-home"></i>
          <span>首页</span>
        </div>
        <div
          class="nav-item"
          :class="{ active: activeNav === 'search' }"
          @click="gotoPage('search', '/admin-booksearch')"
        >
          <i class="fas fa-search"></i>
          <span>图书查询</span>
        </div>
        <div
          class="nav-item"
          :class="{ active: activeNav === 'add' }"
          @click="gotoPage('add', '/admin-addbook')"
        >
          <i class="fas fa-plus-circle"></i>
          <span>增添图书</span>
        </div>
        <div
          class="nav-item"
          :class="{ active: activeNav === 'borrow' }"
          @click="gotoPage('borrow', '/admin-borrowbook')"
        >
          <i class="fas fa-book-reader"></i>
          <span>借阅登记</span>
        </div>
        <div
          class="nav-item"
          :class="{ active: activeNav === 'return' }"
          @click="gotoPage('return', '/admin-returnbook')"
        >
          <i class="fas fa-exchange-alt"></i>
          <span>归还登记</span>
        </div>
        <div
          class="nav-item"
          :class="{ active: activeNav === 'delete' }"
          @click="gotoPage('delete', '/admin-deletebook')"
        >
          <i class="fas fa-trash-alt"></i>
          <span>删除图书</span>
        </div>
      </template>
    </nav>
  </div>
</template>

<script lang="ts" setup>
import { ref, computed } from "vue";
import { useStore } from "vuex";
import router from "../router/index";

const store = useStore();
const userRole1 = computed(() => store.getters.getUser?.role);
const userRole = ref(userRole1.value);
const activeNav = ref("home");

const gotoPage = (page: string, pageRouter: string) => {
  activeNav.value = page;
  router.push(pageRouter);
};
</script>

<style scoped>
.nav-container {
  width: 13vw;
  height: 100vh;
  background-color: #2c3e50;
  color: #ffffff;
  display: flex;
  flex-direction: column;
  position: fixed;
  left: 0;
  top: 0;
}

.logo-area {
  height: 120px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 20px 0;
  border-bottom: 1px solid #34495e;
}

.logo-icon {
  margin-bottom: 10px;
}

.logo-text {
  font-size: 18px;
  font-weight: bold;
}

.nav-menu {
  flex: 1;
  display: flex;
  flex-direction: column;
  padding: 20px 0;
}

.nav-item {
  height: 60px;
  display: flex;
  align-items: center;
  padding: 0 20px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.nav-item:hover {
  background-color: #34495e;
}

.nav-item.active {
  background-color: #3498db;
}

.nav-item i {
  font-size: 16px;
  margin-right: 10px;
  width: 20px;
  text-align: center;
}

.nav-item span {
  font-size: 14px;
}

.role-switch {
  padding: 20px;
  border-top: 1px solid #34495e;
}

.role-switch button {
  width: 100%;
  padding: 10px;
  background-color: #3498db;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.role-switch button:hover {
  background-color: #2980b9;
}
</style>
