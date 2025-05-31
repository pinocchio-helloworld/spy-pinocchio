<template>
  <div class="borrow-book">
    <h1>借阅图书</h1>
    <div class="search-container">
      <input v-model="searchTerm" placeholder="输入图书编号或书名" />
      <button @click="searchBooks">搜索</button>
    </div>
    <div v-if="books" class="book-list">
      <h2>搜索结果</h2>
      <ul>
        <li key="books.id" class="book-item">
          <p>图书编号: {{ books.id }}</p>
          <p>书名: {{ books.name }}</p>
          <p>作者: {{ books.author }}</p>
          <p>类型: {{ books.type }}</p>
          <p>存放位置: {{ books.room }}</p>
          <p>
            可借阅状态: {{ books.borrowed === false ? "可借阅" : "不可借阅" }}
          </p>
          <button
            v-if="books.borrowed === false"
            @click="borrowBook(books.id)"
            class="borrow-button"
          >
            借阅
          </button>
        </li>
      </ul>
    </div>
    <div v-else>
      <p>未找到相关图书。</p>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import axios, { AxiosResponse } from "axios";

interface Book {
  id: string;
  name: string;
  author: string;
  type: string;
  room: string;
  borrowed: boolean;
}

const searchTerm = ref<string>("");
const books = ref<Book | null>(null);

const searchBooks = async () => {
  try {
    const response: AxiosResponse<Book> = await axios.get<Book>(
      `http://localhost:8080/api/update/searchbook?search=${searchTerm.value}`
    );
    books.value = response.data;
  } catch (error) {
    console.error("搜索图书失败:", error);
    alert("搜索图书失败，请稍后再试");
  }
};

const borrowBook = async (bookId: string) => {
  try {
    const response = await axios.post(
      `http://localhost:8080/api/update/borrowbook`,
      {
        bookId,
      }
    );
    if (response.status === 200) {
      alert("借阅成功！");
      // 重新搜索以更新图书状态
      await searchBooks();
    } else {
      alert("借阅失败，请稍后再试");
    }
  } catch (error) {
    console.error("借阅图书失败:", error);
    alert("借阅失败，请稍后再试");
  }
};
</script>

<style scoped>
.borrow-book {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.search-container {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

.search-container input {
  flex: 1;
  padding: 10px;
  margin-right: 10px;
}

.book-list {
  margin-top: 20px;
}

.book-item {
  border: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 10px;
}

.book-item p {
  margin: 5px 0;
}

.borrow-button {
  background-color: #4caf50;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.borrow-button:hover {
  background-color: #45a049;
}
</style>
