<template>
  <div class="container">
    <div class="search-section">
      <h2 class="section-title">图书查询</h2>
      <div class="search-form">
        <input
          v-model="bookId"
          type="text"
          placeholder="请输入图书编号"
          class="search-input"
        />
        <input
          v-model="bookName"
          type="text"
          placeholder="请输入图书名称"
          class="search-input"
        />
        <button class="search-button" @click="searchBooks">查询</button>
      </div>
    </div>

    <div class="result-section">
      <div class="result-count">查询结果：{{ books.length }}条记录</div>
      <div class="table-container">
        <table class="result-table">
          <thead>
            <tr>
              <th>图书编号</th>
              <th>图书名称</th>
              <th>作者</th>
              <th>图书类型</th>
              <th>阅览室编号</th>
              <th>借阅状态</th>
            </tr>
          </thead>
          <tbody>
            <tr v-if="books.length === 0">
              <td colspan="6" class="no-result">未找到匹配的图书信息</td>
            </tr>
            <tr v-for="book in books" :key="book.id">
              <td>{{ book.id }}</td>
              <td>{{ book.name }}</td>
              <td>{{ book.author }}</td>
              <td>{{ book.type }}</td>
              <td>{{ book.room }}</td>
              <td
                :class="{
                  borrowed: book.borrowed,
                  available: !book.borrowed,
                }"
              >
                {{ book.borrowed ? "已借阅" : "可借阅" }}
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import axios from "axios";
import { onMounted, ref } from "vue";

interface Book {
  id: string;
  name: string;
  author: string;
  type: string;
  room: string;
  borrowed: boolean;
}

const bookId = ref("");
const bookName = ref("");
const books = ref<Book[]>([]);
const mockBooks = ref<Book[]>([]);

const fetchBooks = async () => {
  try {
    const response = await axios.get<Book[]>("http://localhost:8080/api/books");
    mockBooks.value = response.data; // 将获取到的数据赋值给 mockBooks
    // 初始化显示所有图书
    searchBooks();
  } catch (error) {
    console.error("获取图书数据失败:", error); // 如果请求失败，打印错误信息
  }
};

const searchBooks = () => {
  if (!bookId.value && !bookName.value) {
    books.value = mockBooks.value;
    return;
  }

  const searchId = bookId.value?.toLowerCase() || "";
  const searchName = bookName.value?.toLowerCase() || "";

  books.value = mockBooks.value.filter((book) => {
    // 检查图书编号是否包含用户输入的 bookId
    const idMatch = searchId ? book.id.toLowerCase().includes(searchId) : true;
    // 检查书名是否包含用户输入的 bookName
    const nameMatch = searchName
      ? book.name.toLowerCase().includes(searchName)
      : true;
    // 如果编号或书名匹配，则保留该图书
    return idMatch && nameMatch;
  });
};
onMounted(fetchBooks);
</script>

<style scoped>
.container {
  width: 100%;
  padding: 20px;
  min-height: calc(100vh - 40px);
  background-color: #fff;
}

.section-title {
  font-size: 22px;
  margin-bottom: 20px;
  color: #333;
}

.search-form {
  display: flex;
  justify-content: flex-end; /* 将内容靠右对齐 */
  gap: 20px;
  margin-bottom: 40px;
}

.search-input {
  width: 200px;
  height: 36px;
  padding: 0 12px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
}

.search-input:focus {
  outline: none;
  border-color: #409eff;
}

.search-button {
  width: 80px;
  height: 36px;
  background-color: #409eff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
}

.search-button:hover {
  background-color: #66b1ff;
}

.result-section {
  display: flex; /* 使用 flexbox 布局 */
  justify-content: flex-end; /* 将内容靠右对齐 */
  align-items: center; /* 垂直居中对齐 */
  padding: 20px; /* 添加一些内边距 */
  border: 1px solid #ccc; /* 设置边框 */
  border-radius: 4px; /* 设置圆角 */
  background-color: #f9f9f9; /* 设置背景颜色 */
}

.result-count {
  font-size: 14px;
  color: #999;
  margin-bottom: 10px;
}

.table-container {
  width: 90%;
  overflow-x: auto;
}

.result-table {
  width: 100%;
  border-collapse: collapse;
}

.result-table th {
  background-color: #f5f5f5;
  height: 50px;
  text-align: left;
  padding: 0 15px;
  font-weight: 500;
  color: #333;
}

.result-table td {
  height: 50px;
  padding: 0 15px;
  border-bottom: 1px solid #eee;
}

.result-table tbody tr:nth-child(even) {
  background-color: #fafafa;
}

.result-table tbody tr:hover {
  background-color: #f0f7ff;
}

.no-result {
  text-align: center;
  color: #999;
  padding: 20px 0;
}

.borrowed {
  color: #f56c6c;
}

.available {
  color: #67c23a;
}
</style>
