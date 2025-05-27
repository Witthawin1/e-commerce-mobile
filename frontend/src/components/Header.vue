<template>
  <header class="bg-white sticky top-0 z-50 shadow-md border-b border-gray-200 px-4 py-4">
    <div class="max-w-7xl mx-auto flex flex-col md:flex-row items-center justify-between gap-4">
      
      <!-- Logo -->
      <router-link
        to="/"
        class="text-2xl font-bold text-gray-800 hover:text-gray-600 transition text-center md:text-left"
        style="font-family: 'SF Pro Display', -apple-system, BlinkMacSystemFont, sans-serif; font-weight: 600; letter-spacing: -0.5px;"
      >
        ITB MSHOP
      </router-link>

      <!-- Right Controls -->
      <div class="flex flex-col sm:flex-row items-center gap-3 w-full md:w-auto">

        <!-- Search Box -->
        <div
          class="flex items-center bg-gray-100 border rounded-full px-3 py-1 w-full sm:w-auto"
          style="border-color: #e2e2e2; background-color: #f5f5f7;"
        >
          <input
            type="text"
            placeholder="ค้นหาสินค้า..."
            class="bg-transparent text-gray-700 outline-none placeholder-gray-400 w-full sm:w-48 md:w-60"
            style="font-family: 'SF Pro Text', -apple-system, BlinkMacSystemFont, sans-serif; font-size: 14px;"
          />
          <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4 text-gray-500 ml-2" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
            <path stroke-linecap="round" stroke-linejoin="round" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
          </svg>
        </div>

        <!-- Cart Button -->
        <button
          class="relative bg-white text-gray-800 border border-gray-300 px-4 py-2 rounded-full font-medium flex items-center gap-2 hover:bg-gray-50 transition w-full sm:w-auto justify-center"
          style="border-color: #e2e2e2; font-family: 'SF Pro Text', -apple-system, BlinkMacSystemFont, sans-serif; font-weight: 500; font-size: 14px;"
        >
          <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
            <path stroke-linecap="round" stroke-linejoin="round" d="M16 11V7a4 4 0 00-8 0v4M5 9h14l1 12H4L5 9z" />
          </svg>
          ตะกร้า
        </button>

        <!-- Login Button -->
        <button
          class="bg-black text-white px-4 py-2 rounded-full font-medium flex items-center gap-2 hover:opacity-90 transition w-full sm:w-auto justify-center"
          style="font-family: 'SF Pro Text', -apple-system, BlinkMacSystemFont, sans-serif; font-weight: 500; font-size: 14px;"
        >
          <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
            <path stroke-linecap="round" stroke-linejoin="round" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
          </svg>
          เข้าสู่ระบบ
        </button>

      </div>
    </div>
  </header>
</template>

<script setup></script>

<style scoped>
@font-face {
  font-family: 'SF Pro Text';
  src: local('SF Pro Text'), local('SFProText-Regular');
  font-weight: normal;
  font-style: normal;
}

button, input {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

button {
  transition: all 0.2s ease;
}
</style>
