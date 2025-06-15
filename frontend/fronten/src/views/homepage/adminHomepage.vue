<!-- 代码已包含 CSS：使用 TailwindCSS , 安装 TailwindCSS 后方可看到布局样式效果 -->

<template>
  <div class="admin-dashboard">
    <!-- Background with overlay -->
    <div class="background-overlay">
      <img
        src="../../assets//images/adminhome.jpg"
        alt="Library background"
        class="background-image"
      />
    </div>

    <!-- Main content container -->
    <div class="content-container">
      <!-- Statistics cards -->
      <div class="stats-container">
        <div class="stat-card">
          <div class="stat-icon">
            <i class="fas fa-book"></i>
          </div>
          <div class="stat-content">
            <div class="stat-title">总图书数量</div>
            <div class="stat-value">{{ totalBooks }}</div>
          </div>
        </div>

        <div class="stat-card">
          <div class="stat-icon">
            <i class="fas fa-arrow-circle-up"></i>
          </div>
          <div class="stat-content">
            <div class="stat-title">今日借书数量</div>
            <div class="stat-value">{{ todayBorrowed }}</div>
          </div>
        </div>

        <div class="stat-card">
          <div class="stat-icon">
            <i class="fas fa-arrow-circle-down"></i>
          </div>
          <div class="stat-content">
            <div class="stat-title">今日还书数量</div>
            <div class="stat-value">{{ todayReturned }}</div>
          </div>
        </div>

        <div class="stat-card">
          <div class="stat-icon">
            <i class="fas fa-users"></i>
          </div>
          <div class="stat-content">
            <div class="stat-title">用户数量</div>
            <div class="stat-value">{{ totalUsers }}</div>
          </div>
        </div>
      </div>

      <!-- Notification panel -->
      <div class="notification-panel">
        <div class="panel-header">
          <i class="fas fa-bell"></i>
          <h2>系统通知</h2>
        </div>
        <div class="notification-list">
          <div class="notification-item">
            <div class="notification-time">10:30 AM</div>
            <div class="notification-content">
              <h3>系统维护通知</h3>
              <p>
                系统将于今晚 23:00 至次日 2:00 进行维护升级，期间将暂停服务。
              </p>
            </div>
          </div>
          <div class="notification-item">
            <div class="notification-time">昨天 15:45</div>
            <div class="notification-content">
              <h3>新书入库</h3>
              <p>新增 6 本计算机类图书已入库，请及时更新书架信息。</p>
            </div>
          </div>
          <div class="notification-item">
            <div class="notification-time">昨天 09:20</div>
            <div class="notification-content">
              <h3>借阅规则更新</h3>
              <p>学生用户单次借阅上限调整为 10 本。</p>
            </div>
          </div>
          <div class="notification-item">
            <div class="notification-time">前天 14:10</div>
            <div class="notification-content">
              <h3>系统更新日志</h3>
              <p>版本 1.0.0 已发布，优化了搜索功能和借阅流程。</p>
            </div>
          </div>
          <div class="notification-item">
            <div class="notification-time">前天 11:05</div>
            <div class="notification-content">
              <h3>活动通知</h3>
              <p>下周三将举办"数字阅读"主题讲座，欢迎师生参加。</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { computed, ref } from "vue";
import axios from "axios";
import { useStore } from "vuex";

// 统计数据
const totalBooks = ref(12856);
const todayBorrowed = ref(243);
const todayReturned = ref(187);
const totalUsers = ref(3428);
const store = useStore();

const fetchInformation = async () => {
  try {
    const response = await axios.get("http://localhost:8080/api/information");
    totalBooks.value = response.data.bookcounts;
    todayBorrowed.value = response.data.borrowcounts;
    todayReturned.value = response.data.returncounts;
    totalUsers.value = response.data.usercounts;
  } catch (error) {
    console.error("获取数据失败", error);
  }
};

// 在组件挂载时调用 fetchInformation
fetchInformation();

// 用户名
const getUser = computed(() => store.getters.getUser?.name);
</script>

<style scoped>
.admin-dashboard {
  position: relative;
  min-height: 820px;
  width: 100%;
  overflow: hidden;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  color: #333;
}

.background-overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
}

.background-overlay::after {
  content: " ";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
}

.background-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  object-position: center;
}

.content-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 40px 20px;
}

.stats-container {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 20px;
  margin-bottom: 40px;
}

.stat-card {
  background: white;
  border-radius: 10px;
  padding: 25px;
  display: flex;
  align-items: center;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease;
}

.stat-card:hover {
  transform: translateY(-5px);
}

.stat-icon {
  font-size: 2.5rem;
  color: #2c5282;
  margin-right: 20px;
}

.stat-content {
  display: flex;
  flex-direction: column;
}

.stat-title {
  font-size: 1rem;
  color: #718096;
  margin-bottom: 5px;
}

.stat-value {
  font-size: 2rem;
  font-weight: bold;
  color: #2d3748;
}

.notification-panel {
  background: white;
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  overflow: hidden;
}

.panel-header {
  display: flex;
  align-items: center;
  padding: 20px;
  background: #2c5282;
  color: white;
}

.panel-header i {
  font-size: 1.5rem;
  margin-right: 15px;
}

.panel-header h2 {
  margin: 0;
  font-size: 1.5rem;
}

.notification-list {
  max-height: 500px;
  overflow-y: auto;
}

.notification-item {
  padding: 20px;
  border-bottom: 1px solid #e2e8f0;
  display: flex;
  transition: background-color 0.2s ease;
}

.notification-item:hover {
  background-color: #f7fafc;
}

.notification-time {
  min-width: 100px;
  color: #718096;
  font-size: 0.9rem;
}

.notification-content {
  flex: 1;
}

.notification-content h3 {
  margin: 0 0 8px 0;
  color: #2d3748;
  font-size: 1.1rem;
}

.notification-content p {
  margin: 0;
  color: #4a5568;
  font-size: 0.95rem;
  line-height: 1.5;
}

@media (max-width: 1024px) {
  .stats-container {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media (max-width: 640px) {
  .stats-container {
    grid-template-columns: 1fr;
  }

  .content-container {
    padding: 20px 15px;
  }

  .stat-card {
    padding: 20px;
  }

  .stat-icon {
    font-size: 2rem;
    margin-right: 15px;
  }

  .stat-value {
    font-size: 1.8rem;
  }
}
</style>
