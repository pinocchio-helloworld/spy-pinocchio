<template>
  <div class="delete-book-container">
    <h1>删除图书</h1>
    <form @submit.prevent="deleteBook">
      <div class="form-group">
        <label for="search-id">图书 ID 或名字：</label>
        <input
          id="search-id"
          v-model="searchTerm"
          type="text"
          placeholder="输入图书 ID 或名字"
          required
        />
      </div>
      <div class="form-group">
        <button type="submit">删除图书</button>
      </div>
    </form>
    <div v-if="bookToDelete" class="book-details">
      <h2>找到的图书信息：</h2>
      <p><strong>ID：</strong>{{ bookToDelete.id }}</p>
      <p><strong>名字：</strong>{{ bookToDelete.name }}</p>
      <p><strong>作者：</strong>{{ bookToDelete.author }}</p>
      <p><strong>类型：</strong>{{ bookToDelete.type }}</p>
      <p><strong>房间：</strong>{{ bookToDelete.room }}</p>
      <p>
        <strong>是否借出：</strong>{{ bookToDelete.borrowed ? "是" : "否" }}
      </p>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import axios, { AxiosResponse } from "axios";

interface Book {
  id: string;
  name: string;
  author: string;
  type: string;
  room: string;
  borrowed: boolean;
}

export default defineComponent({
  setup() {
    const searchTerm = ref<string>("");
    const bookToDelete = ref<Book | null>(null);

    const deleteBook = async () => {
      try {
        // 首先通过 ID 或名字查找图书
        const response: AxiosResponse<Book> = await axios.get<Book>(
          `http://localhost:8080/api/update/searchbook?search=${searchTerm.value}`
        );

        const books = response.data;

        // 假设只找到一本书
        bookToDelete.value = books;

        // 确认是否删除
        if (confirm(`确定要删除图书 "${bookToDelete.value.name}" 吗？`)) {
          // 发送删除请求
          await axios.delete(
            `http://localhost:8080/api/update/deletebook/${bookToDelete.value.id}`
          );

          alert("图书删除成功！");
          // 清空搜索框和显示的图书信息
          searchTerm.value = "";
          bookToDelete.value = null;
        }
      } catch (error) {
        console.error("删除失败:", error);
        alert("删除失败，请检查网络或输入信息！");
      }
    };

    return {
      searchTerm,
      bookToDelete,
      deleteBook,
    };
  },
});
</script>

<style scoped>
.delete-book-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
}

h1 {
  text-align: center;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input[type="text"] {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #dc3545;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #a71d2a;
}

.book-details {
  margin-top: 20px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 8px;
}

.book-details h2 {
  margin-top: 0;
}

.book-details p {
  margin: 5px 0;
}
</style>
