<template>
  <div class="add-book-form">
    <h2>增添图书</h2>
    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label for="book-id">图书编号</label>
        <input id="book-id" v-model="book.id" type="text" required />
      </div>
      <div class="form-group">
        <label for="book-name">图书名称</label>
        <input id="book-name" v-model="book.name" type="text" required />
      </div>
      <div class="form-group">
        <label for="book-author">图书作者</label>
        <input id="book-author" v-model="book.author" type="text" required />
      </div>
      <div class="form-group">
        <label for="book-type">类型</label>
        <input id="book-type" v-model="book.type" type="text" required />
      </div>
      <div class="form-group">
        <label for="book-room">存放场馆号</label>
        <input id="book-room" v-model="book.room" type="text" required />
      </div>
      <button type="submit">确定存入</button>
    </form>
  </div>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import axios from "axios";

const book = ref({
  id: "",
  name: "",
  author: "",
  type: "",
  room: "",
  borrowed: false,
});

const submitForm = async () => {
  // 这里可以添加将图书信息提交到后端的逻辑
  try {
    const response = (await axios.post)<{
      message: string;
    }>("http://localhost:8080/api/update/addbook", book.value); // 直接传递book.value对象
    console.log("提交的图书信息:", book.value);
    console.log("提交成功" + (await response).data.message);
  } catch (error) {
    if (axios.isAxiosError(error)) {
      console.error("添加图书失败:", error.response?.data);
      // 可以在这里添加显示错误消息等操作
    } else {
      console.error("发生未知错误:", error);
    }
  }
  /*
  book.value = {
    id: "",
    name: "",
    author: "",
    type: "",
    room: "",
  };*/
};
</script>

<style scoped>
.add-book-form {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 4px;
  background-color: #f9f9f9;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #409eff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
}

button:hover {
  background-color: #307ecc;
}
</style>
