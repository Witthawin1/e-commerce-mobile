<template>
  <div class="home">
    <div
      class="bg-white text-black min-h-screen font-[SF Pro Display, -apple-system, BlinkMacSystemFont, sans-serif] flex flex-col justify-between">
      <!-- Header -->
      <Header />

      <!-- Hero Section -->
      <section class="pt-16 pb-0 px-6 overflow-hidden bg-[url(/)]">
        <div class="max-w-6xl mx-auto text-center">
          <h2 class="text-5xl md:text-6xl font-semibold tracking-tight text-black mb-3 animate-fade-in">
            ITB MSHOP
          </h2>
          <h3
            class="text-3xl md:text-4xl font-medium tracking-tight text-black/80 mb-6 animate-fade-in animation-delay-100">
            ยินดีต้อนรับสู่ประสบการณ์ใหม่
          </h3>
          <p class="text-gray-500 text-xl mb-8 max-w-2xl mx-auto leading-relaxed animate-fade-in animation-delay-200">
            พบกับมือถือรุ่นล่าสุด ดีไซน์พรีเมียม
            พร้อมข้อเสนอสุดพิเศษที่คุณไม่ควรพลาด
          </p>

          <div
            class="flex flex-col sm:flex-row items-center justify-center gap-5 mb-8 animate-fade-in animation-delay-300">
            <router-link to="/sale-items"
              class="itbms-shopnow bg-black text-white px-10 py-3 rounded-full font-medium text-lg transition-all duration-300 hover:bg-black/90 w-full sm:w-auto">
              Shop Now
            </router-link>

            <router-link to="/sale-items/list"
              class="itbms-seller text-black px-10 py-3 font-medium text-lg transition-all duration-300 hover:underline w-full sm:w-auto">
              Seller
            </router-link>
          </div>
        </div>

        <!-- Product Hero Image -->
        <div class="relative mt-8 mb-0 overflow-hidden">
          <div
            class="w-full h-[500px] md:h-[1000px] flex items-center justify-center bg-gradient-to-b from-gray-50 to-gray-100 overflow-hidden">
            <img src="../assets/icons/nat-dA0-qxdbyyY-unsplash.jpg" alt="Premium Smartphone"
              class="w-full h-full object-contain scale-110 animate-float" />
          </div>
        </div>
      </section>

      <!-- Features Section -->
      <section class="py-24 px-6">
        <div class="max-w-6xl mx-auto">
          <h2 class="text-3xl md:text-4xl font-semibold text-center mb-16 tracking-tight">
            ทำไมต้องเลือก ITB MSHOP
          </h2>

          <div class="grid grid-cols-1 md:grid-cols-3 gap-12">
            <div class="text-center">
              <div class="w-16 h-16 mx-auto mb-6 flex items-center justify-center">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-12 w-12 text-black" fill="none" viewBox="0 0 24 24"
                  stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1"
                    d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
              </div>
              <h3 class="text-xl font-semibold mb-3">ราคาพิเศษ</h3>
              <p class="text-gray-500 leading-relaxed">
                ข้อเสนอสุดพิเศษและส่วนลดมากมายสำหรับลูกค้าทุกท่าน
              </p>
            </div>

            <div class="text-center">
              <div class="w-16 h-16 mx-auto mb-6 flex items-center justify-center">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-12 w-12 text-black" fill="none" viewBox="0 0 24 24"
                  stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1"
                    d="M5 3v4M3 5h4M6 17v4m-2-2h4m5-16l2.286 6.857L21 12l-5.714 2.143L13 21l-2.286-6.857L5 12l5.714-2.143L13 3z" />
                </svg>
              </div>
              <h3 class="text-xl font-semibold mb-3">สินค้าคุณภาพ</h3>
              <p class="text-gray-500 leading-relaxed">
                รับประกันสินค้าคุณภาพสูงจากแบรนด์ชั้นนำทั่วโลก
              </p>
            </div>

            <div class="text-center">
              <div class="w-16 h-16 mx-auto mb-6 flex items-center justify-center">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-12 w-12 text-black" fill="none" viewBox="0 0 24 24"
                  stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1"
                    d="M20 7l-8-4-8 4m16 0l-8 4m8-4v10l-8 4m0-10L4 7m8 4v10M4 7v10l8 4" />
                </svg>
              </div>
              <h3 class="text-xl font-semibold mb-3">จัดส่งรวดเร็ว</h3>
              <p class="text-gray-500 leading-relaxed">
                บริการจัดส่งที่รวดเร็วและปลอดภัยถึงบ้านคุณ
              </p>
            </div>
          </div>
        </div>
      </section>

      <!-- Product Showcase -->
      <section class="py-24 px-6 bg-gray-50">
        <div class="max-w-6xl mx-auto">
          <h2 class="text-3xl md:text-4xl font-semibold text-center mb-16 tracking-tight">
            สินค้าแนะนำ
          </h2>

          <div class="grid grid-cols-1 md:grid-cols-2 gap-8">
            <div class="bg-white rounded-3xl overflow-hidden shadow-sm hover:shadow-md transition-shadow duration-300">
              <div class="h-64 bg-gray-100 flex items-center justify-center p-8">
                <img src="" alt="Smartphone Model" class="h-full object-contain" />
              </div>
              <div class="p-8">
                <h3 class="text-xl font-semibold mb-2">iPhone 15 Pro</h3>
                <p class="text-gray-500 mb-4">เทคโนโลยีล้ำสมัย ดีไซน์หรูหรา</p>
                <p class="text-lg font-medium">฿39,900</p>
              </div>
            </div>

            <div class="bg-white rounded-3xl overflow-hidden shadow-sm hover:shadow-md transition-shadow duration-300">
              <div class="h-64 bg-gray-100 flex items-center justify-center p-8">
                <img src="" alt="Smartphone Model" class="h-full object-contain" />
              </div>
              <div class="p-8">
                <h3 class="text-xl font-semibold mb-2">Samsung Galaxy S24</h3>
                <p class="text-gray-500 mb-4">
                  ประสิทธิภาพสูง กล้องคมชัดระดับโปร
                </p>
                <p class="text-lg font-medium">฿32,900</p>
              </div>
            </div>
          </div>

          <div class="text-center mt-12">
            <router-link to="/sale-items"
              class="inline-block text-black border-b border-black pb-1 font-medium hover:border-black/70 transition-colors">
              ดูสินค้าทั้งหมด
            </router-link>
          </div>
        </div>
      </section>

      <!-- Footer -->
      <Footer />
    </div>
  </div>
</template>

<script setup>
import Header from "../components/Header.vue";
import Footer from "../components/Footer.vue";
</script>

<style scoped>

.animate-fade-in {
  opacity: 0;
  animation: fadeIn 0.8s ease-out forwards;
}

.animate-float {
  animation: float 6s ease-in-out infinite;
}

.animation-delay-100 {
  animation-delay: 100ms;
}

.animation-delay-200 {
  animation-delay: 200ms;
}

.animation-delay-300 {
  animation-delay: 300ms;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(10px);
  }

  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@keyframes float {
  0% {
    transform: translateY(0px);
  }

  50% {
    transform: translateY(-10px);
  }

  100% {
    transform: translateY(0px);
  }
}
</style>
