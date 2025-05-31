<template>
  <div class="register-container">
    <h1 class="register-title">图书管理系统注册</h1>
    <form class="register-form">
      <fieldset>
        <legend>用户类型</legend>
        <div class="radio-group">
          <label class="radio-label">
            <input
              type="radio"
              name="userType"
              value="user"
              v-model="userType"
              checked
            />
            <span class="radio-custom"></span>
            普通用户
          </label>
          <label class="radio-label">
            <input
              type="radio"
              name="userType"
              value="admin"
              v-model="userType"
            />
            <span class="radio-custom"></span>
            管理员
          </label>
        </div>
      </fieldset>

      <div class="form-group">
        <label for="username">用户名</label>
        <input
          type="text"
          id="username"
          v-model="username"
          placeholder="请输入用户名"
          required
        />
      </div>

      <div class="form-group">
        <label for="password">密码</label>
        <input
          type="password"
          id="password"
          v-model="password"
          placeholder="请输入密码"
          required
        />
      </div>

      <div class="form-group">
        <label for="confirmPassword">确认密码</label>
        <input
          type="password"
          id="confirmPassword"
          v-model="confirmPassword"
          placeholder="请再次输入密码"
          required
        />
      </div>

      <div class="form-group" v-if="userType === 'admin'">
        <label for="adminCode">管理员注册码</label>
        <input
          type="password"
          id="adminCode"
          v-model="adminCode"
          placeholder="请输入管理员注册码"
          required
        />
      </div>

      <button type="submit" class="submit-btn" @click="submitForm">注册</button>
    </form>
  </div>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import axios from "axios";

const userType = ref<"user" | "admin">("user");
const username = ref("");
const password = ref("");
const confirmPassword = ref("");
const adminCode = ref("");

const submitForm = async () => {
  // 验证密码是否一致
  if (password.value !== confirmPassword.value) {
    alert("密码和确认密码不一致");
    return;
  }

  // 构造请求数据
  const formData = {
    username: username.value,
    password: password.value,
    adminCode: adminCode.value,
    userType: userType.value,
  };

  try {
    // 发送请求到后端
    const response = await axios.post(
      "http://localhost:8080/api/register",
      formData
    );
    if (response.data.success) {
      alert("注册成功" + response.data.message);
    } else {
      alert(response.data.message || "注册失败");
    }
  } catch (error) {
    alert("注册失败,请检查");
    console.error(error);
  }
  return {
    username,
    password,
    confirmPassword,
    adminCode,
    userType,
    submitForm,
  };
};
</script>

<style>
.register-container {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
  font-family: "Microsoft YaHei", sans-serif;
  padding: 20px;
}

.register-title {
  color: #333;
  font-size: 2rem;
  margin-bottom: 2rem;
  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1);
}

.register-form {
  background: white;
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
}

fieldset {
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 1rem;
  margin-bottom: 1.5rem;
}

legend {
  padding: 0 0.5rem;
  color: #555;
  font-size: 0.9rem;
}

.radio-group {
  display: flex;
  gap: 1.5rem;
  margin-top: 0.5rem;
}

.radio-label {
  display: flex;
  align-items: center;
  cursor: pointer;
  position: relative;
  padding-left: 1.8rem;
}

.radio-label input {
  position: absolute;
  opacity: 0;
}

.radio-custom {
  position: absolute;
  left: 0;
  height: 1.2rem;
  width: 1.2rem;
  background-color: #fff;
  border: 2px solid #ccc;
  border-radius: 50%;
}

.radio-label input:checked ~ .radio-custom {
  background-color: #4a6baf;
  border-color: #4a6baf;
}

.radio-custom:after {
  content: "";
  position: absolute;
  display: none;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  width: 0.6rem;
  height: 0.6rem;
  border-radius: 50%;
  background: white;
}

.radio-label input:checked ~ .radio-custom:after {
  display: block;
}

.form-group {
  margin-bottom: 1.5rem;
}

.form-group label {
  display: block;
  margin-bottom: 0.5rem;
  color: #555;
  font-size: 0.9rem;
}

.form-group input {
  width: 100%;
  padding: 0.8rem;
  border: 1px solid #ddd;
  border-radius: 5px;
  font-size: 1rem;
  transition: border-color 0.3s;
}

.form-group input:focus {
  outline: none;
  border-color: #4a6baf;
  box-shadow: 0 0 0 2px rgba(74, 107, 175, 0.2);
}

.submit-btn {
  width: 100%;
  padding: 1rem;
  background: linear-gradient(to right, #4a6baf, #3a5a99);
  color: white;
  border: none;
  border-radius: 5px;
  font-size: 1rem;
  cursor: pointer;
  transition: all 0.3s;
  margin-top: 0.5rem;
}

.submit-btn:hover {
  background: linear-gradient(to right, #3a5a99, #2d4a7f);
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.submit-btn:active {
  transform: translateY(0);
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

@media (max-width: 480px) {
  .register-form {
    padding: 1.5rem;
  }

  .register-title {
    font-size: 1.5rem;
  }
  .radio-group {
    flex-direction: column;
    gap: 0.8rem;
  }
}
</style>
