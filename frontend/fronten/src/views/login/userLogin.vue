<template>
  <div class="login-container">
    <div class="background-overlay"></div>
    <div class="login-content">
      <div class="title-section">
        <h1 class="main-title">墨阁书驿</h1>
        <p class="sub-title">图书管理系统</p>
      </div>

      <div class="login-form">
        <h2 class="form-title">用户登录</h2>

        <div class="input-group">
          <div class="input-icon">
            <i class="fas fa-user"></i>
          </div>
          <input
            type="text"
            placeholder="请输入账号"
            class="input-field"
            v-model="username"
          />
        </div>

        <div class="input-group">
          <div class="input-icon">
            <i class="fas fa-lock"></i>
          </div>
          <input
            :type="showPassword ? 'text' : 'password'"
            placeholder="请输入密码"
            class="input-field"
            v-model="password"
            @keyup.enter="handleLogin"
          />
          <div class="password-toggle" @click="showPassword = !showPassword">
            <i :class="showPassword ? 'fas fa-eye-slash' : 'fas fa-eye'"></i>
          </div>
        </div>

        <button class="login-button" @click="handleLogin" :disabled="isLoading">
          <span v-if="!isLoading">登 录</span>
          <i v-else class="fas fa-spinner fa-spin"></i>
        </button>

        <button class="register-button" @click="handleRegister">
          注册账号
        </button>
      </div>

      <div class="footer">
        <p>© 2025 信2302-2-3组 版权所有</p>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import axios from "axios";
import { useStore } from "vuex";
import { User } from "../../store/types";
import router from "../../router/index";

const username = ref<string>("");
const password = ref<string>("");
const showPassword = ref<boolean>(false);
const isLoading = ref<boolean>(false);

const store = useStore();

const handleLogin = async () => {
  if (!username.value || !password.value) {
    alert("请输入账号和密码");
    return;
  }

  isLoading.value = true;
  try {
    const response = await axios.post<{
      success: boolean;
      message: string;
      role: string;
    }>("http://localhost:8080/api/login", {
      userid: username.value,
      password: password.value,
    });
    if (response.data.success) {
      alert("登录成功");
      const userRole = response.data.role; // 获取用户角色
      const user: User = {
        name: username.value, // 假设用户名作为用户名称
        role: userRole,
      };
      store.dispatch("setUser", user);
      // 根据用户角色进行不同的处理逻辑
      if (userRole === "admin") {
        alert("欢迎管理员！");
        // 跳转到管理员页面
        router.push("/admin-homepage");
      } else if (userRole === "user") {
        alert("欢迎普通用户！");
        router.push("/admin-homepage");
        // 跳转到用户页面
      } else {
        alert("未知用户角色：" + userRole);
      }
    } else {
      alert("登录失败：" + response.data.message);
    }
  } catch (error) {
    console.error("登录失败", error);
    alert("登录失败，请检查网络或联系管理员");
  } finally {
    isLoading.value = false;
  }
};

const handleRegister = () => {
  router.push("/admin-register");
};
</script>

<style scoped>
.login-container {
  position: relative;
  width: 100%;
  min-height: 820px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-image: url("../../assets/images/userloginback.jpg");
  background-size: cover;
  background-position: center;
  font-family: "Noto Sans SC", sans-serif;
}

.background-overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
}

.login-content {
  position: relative;
  z-index: 1;
  width: 100%;
  max-width: 500px;
  padding: 40px;
  text-align: center;
}

.title-section {
  margin-bottom: 40px;
}

.main-title {
  font-size: 48px;
  font-weight: 700;
  color: #fff;
  margin-bottom: 10px;
  letter-spacing: 4px;
  text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.3);
}

.sub-title {
  font-size: 18px;
  color: #fff;
  opacity: 0.9;
}

.login-form {
  background-color: rgba(255, 255, 255, 0.9);
  border-radius: 12px;
  padding: 40px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
}

.form-title {
  font-size: 24px;
  color: #333;
  margin-bottom: 30px;
  font-weight: 600;
}

.input-group {
  position: relative;
  margin-bottom: 25px;
}

.input-icon {
  position: absolute;
  left: 15px;
  top: 50%;
  transform: translateY(-50%);
  color: #666;
  font-size: 16px;
}

.input-field {
  width: 85%;
  padding: 15px 15px 15px 45px;
  border: 1px solid #ddd;
  border-radius: 6px;
  font-size: 16px;
  transition: all 0.3s;
}

.input-field:focus {
  border-color: #8b5a2b;
  outline: none;
  box-shadow: 0 0 0 2px rgba(139, 90, 43, 0.2);
}

.password-toggle {
  position: absolute;
  right: 15px;
  top: 50%;
  transform: translateY(-50%);
  color: #666;
  cursor: pointer;
  font-size: 16px;
}

.login-button {
  width: 100%;
  padding: 15px;
  background-color: #8b5a2b;
  color: white;
  border: none;
  border-radius: 6px;
  font-size: 16px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s;
  margin-top: 10px;
}

.login-button:hover {
  background-color: #6d4722;
}

.login-button:disabled {
  background-color: #a78b6d;
  cursor: not-allowed;
}

.register-button {
  width: 100%;
  padding: 15px;
  background-color: transparent;
  color: #8b5a2b;
  border: 1px solid #8b5a2b;
  border-radius: 6px;
  font-size: 16px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s;
  margin-top: 15px;
}

.register-button:hover {
  background-color: rgba(139, 90, 43, 0.1);
}

.footer {
  margin-top: 30px;
  color: #fff;
  font-size: 14px;
  opacity: 0.8;
}

@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+SC:wght@400 ;500;700&display=swap");
</style>
