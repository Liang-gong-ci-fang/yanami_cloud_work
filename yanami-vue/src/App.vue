<script setup>
import { ref, onMounted } from 'vue'
import CharacterShowcase from './components/CharacterShowcase.vue'
import LoginForm from './components/LoginForm.vue'

const isLoggedIn = ref(false)
const currentUser = ref(null)

onMounted(() => {
  // 检查是否已登录
  const token = localStorage.getItem('token')
  const user = localStorage.getItem('user')
  if (token && user) {
    isLoggedIn.value = true
    currentUser.value = JSON.parse(user)
  }
})

const handleLoginSuccess = (user) => {
  isLoggedIn.value = true
  currentUser.value = user
}

const handleLogout = () => {
  localStorage.removeItem('token')
  localStorage.removeItem('user')
  isLoggedIn.value = false
  currentUser.value = null
}
</script>

<template>
  <div id="app">
    <div v-if="isLoggedIn" class="user-header">
      <span>欢迎，{{ currentUser?.username }}</span>
      <button @click="handleLogout" class="logout-btn">退出登录</button>
    </div>
    <LoginForm v-if="!isLoggedIn" @login-success="handleLoginSuccess" />
    <CharacterShowcase v-else />
  </div>
</template>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: 'Arial', sans-serif;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  min-height: 100vh;
  overflow-x: hidden;
}

#app {
  width: 100%;
  height: 100vh;
}

.user-header {
  position: fixed;
  top: 20px;
  right: 20px;
  background: rgba(255, 255, 255, 0.95);
  padding: 10px 20px;
  border-radius: 8px;
  display: flex;
  align-items: center;
  gap: 15px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  z-index: 1000;
}

.user-header span {
  color: #333;
  font-weight: 500;
}

.logout-btn {
  padding: 6px 16px;
  background: #e74c3c;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 14px;
  transition: background 0.3s;
}

.logout-btn:hover {
  background: #c0392b;
}
</style>
