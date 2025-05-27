<template>
  <div class="bg-gradient-to-br from-indigo-100 via-white to-purple-100 text-gray-900 min-h-screen font-[Inter] flex flex-col relative overflow-hidden">

    <Header />
    
    <main class="flex-grow relative z-10">
      <!-- Hero Section Title -->
      <section class="py-24 px-7 text-center relative">
        <div class="absolute inset-0 bg-gradient-to-r from-transparent via-white/50 to-transparent"></div>
        <div class="relative z-10 max-w-5xl mx-auto">
          
          <h2 class="text-7xl font-black mb-8 bg-clip-text text-transparent bg-gradient-to-r from-blue-600 via-purple-600 to-pink-600 drop-shadow-2xl leading-tight">
            รายการแบบ List
          </h2>
          
          <p class="text-gray-600 text-xl max-w-3xl mx-auto leading-relaxed font-medium mb-8">
            ดูมือถือในรูปแบบรายการพร้อมรายละเอียดเบื้องต้น
          </p>
          
          <!-- Stats Cards -->
          <div class="grid grid-cols-1 md:grid-cols-2 gap-6 max-w-4xl mx-auto mt-12">
            <div class="bg-white/70 backdrop-blur-xl rounded-2xl p-6 border border-white/50 shadow-xl hover:shadow-2xl transition-all duration-500 hover:-translate-y-1">
              <div class="text-3xl font-bold text-blue-600">{{ filteredProducts.length }}</div>
              <div class="text-gray-600 font-medium">Total Items</div>
            </div>
            <div class="bg-white/70 backdrop-blur-xl rounded-2xl p-6 border border-white/50 shadow-xl hover:shadow-2xl transition-all duration-500 hover:-translate-y-1">
              <div class="text-3xl font-bold text-purple-600">{{ brands.length }}</div>
              <div class="text-gray-600 font-medium">Brands</div>
            </div>
          </div>
        </div>
      </section>

      <!-- Alerts with Enhanced Design -->
   <Alert
      v-if="added"
      :message="'The sale item has been successfully added.'"
      :state="'created'"
    />
    <Alert
      v-if="deleted"
      :message="'The sale item has been deleted.'"
      :state="'created'"
    />

      <!-- Enhanced Controls Bar -->
      <div class="max-w-7xl mx-auto px-6 mb-12">
        <div class="bg-white/80 backdrop-blur-2xl rounded-3xl p-8 shadow-2xl border border-white/30 ring-1 ring-gray-200/20 hover:shadow-3xl transition-all duration-700">
          <div class="flex flex-col lg:flex-row justify-between items-center gap-8">
            <div class="flex items-center gap-6 w-full lg:w-auto flex-wrap">
              <!-- Add Sale Item -->
              <router-link to="/sale-items/add"
                class="itbms-sale-item-add group bg-gradient-to-r from-blue-600 via-blue-700 to-purple-700 text-white px-8 py-4 rounded-2xl hover:from-blue-700 hover:via-purple-700 hover:to-purple-800 transition-all duration-500 shadow-xl hover:shadow-2xl transform hover:-translate-y-2 hover:scale-105 flex items-center gap-4 whitespace-nowrap font-bold text-lg relative overflow-hidden">
                <div class="absolute inset-0 bg-gradient-to-r from-white/0 via-white/20 to-white/0 transform -skew-x-12 -translate-x-full group-hover:translate-x-full transition-transform duration-1000"></div>
                <svg class="w-6 h-6 relative z-10" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6"></path>
                </svg>
                <span class="relative z-10">Add Sale Item</span>
              </router-link>

              <!-- Manage Brand -->
              <router-link to="/brands"
                class="itbms-manage-brand group bg-gradient-to-r from-gray-800 via-gray-900 to-black text-white px-8 py-4 rounded-2xl hover:from-gray-700 hover:via-gray-800 hover:to-gray-900 transition-all duration-500 shadow-xl hover:shadow-2xl transform hover:-translate-y-2 hover:scale-105 flex items-center gap-4 whitespace-nowrap font-bold text-lg relative overflow-hidden">
                <div class="absolute inset-0 bg-gradient-to-r from-white/0 via-white/10 to-white/0 transform -skew-x-12 -translate-x-full group-hover:translate-x-full transition-transform duration-1000"></div>
                <svg class="w-6 h-6 relative z-10" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 21V5a2 2 0 00-2-2H7a2 2 0 00-2 2v16m14 0h2m-2 0h-5m-9 0H3m2 0h5M9 7h1m-1 4h1m4-4h1m-1 4h1m-5 10v-5a1 1 0 011-1h2a1 1 0 011 1v5m-4 0h4"></path>
                </svg>
                <span class="relative z-10">Manage Brand</span>
              </router-link>

              <!-- Enhanced Select Brand -->
              <div class="relative w-full sm:w-auto min-w-[250px]">
                <select v-model="selectedBrand"
                  class="pl-6 pr-14 py-4 rounded-2xl bg-white/90 backdrop-blur-sm border-2 border-gray-200 text-gray-800 focus:outline-none focus:ring-4 focus:ring-blue-500/30 focus:border-blue-500 appearance-none w-full shadow-lg font-semibold text-lg transition-all duration-300 hover:shadow-xl hover:border-gray-300">
                  <option value="all">🌟 All Brands</option>
                  <option v-for="(brand, index) in brands" :key="brand.id || index" :value="brand.name">
                    📱 {{ brand.name }}
                  </option>
                </select>
                <div class="absolute inset-y-0 right-0 flex items-center pr-6 pointer-events-none">
                  <svg class="w-6 h-6 text-gray-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7"></path>
                  </svg>
                </div>
              </div>
            </div>

            <!-- Enhanced View Toggle -->
            <div class="flex gap-2 w-full lg:w-auto bg-gradient-to-r from-gray-100 to-gray-200 p-2 rounded-2xl shadow-inner">
              <button @click="goToGallery"
                class="flex-1 lg:flex-none px-8 py-3 rounded-xl transition-all duration-300 flex items-center justify-center gap-3 text-gray-600 hover:bg-white hover:shadow-lg hover:text-gray-800 font-semibold text-lg transform hover:-translate-y-0.5">
                <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2H6a2 2 0 01-2-2V6zM14 6a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2h-2a2 2 0 01-2-2V6zM4 16a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2H6a2 2 0 01-2-2v-2zM14 16a2 2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2h-2a2 2 0 01-2-2v-2z"></path>
                </svg>
                Gallery
              </button>
              <button disabled
                class="flex-1 lg:flex-none px-8 py-3 rounded-xl bg-gradient-to-r from-blue-600 to-purple-600 text-white shadow-xl flex items-center justify-center gap-3 font-semibold text-lg">
                <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 10h16M4 14h16M4 18h16"></path>
                </svg>
                List
              </button>
            </div>
          </div>
        </div>
      </div>

      <!-- Enhanced Empty State -->
      <div v-if="filteredProducts.length === 0" class="text-center py-32">
        <div class="itbms-row max-w-lg mx-auto">
          <div class="relative mb-8">
            <div class="w-32 h-32 mx-auto bg-gradient-to-br from-blue-100 to-purple-100 rounded-full flex items-center justify-center shadow-2xl">
              <svg class="w-16 h-16 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5" d="M20 13V6a2 2 0 00-2-2H6a2 2 0 00-2 2v7m16 0v5a2 2 0 01-2 2H6a2 2 0 01-2 2v-5m16 0h-2.586a1 1 0 00-.707.293l-2.414 2.414a1 1 0 01-.707.293h-3.172a1 1 0 01-.707-.293l-2.414-2.414A1 1 0 006.586 13H4"></path>
              </svg>
            </div>
            <div class="absolute -top-2 -right-2 w-8 h-8 bg-yellow-400 rounded-full flex items-center justify-center animate-bounce">
              <span class="text-lg">✨</span>
            </div>
          </div>
          <h3 class="itbms-message text-4xl font-bold mb-4 text-gray-700">no sale item</h3>
          <p class="text-gray-500 text-xl mb-8">Start building your inventory by adding your first mobile device</p>
          <router-link to="/sale-items/add" 
            class="inline-flex items-center gap-3 bg-gradient-to-r from-blue-600 to-purple-600 text-white px-8 py-4 rounded-2xl font-semibold text-lg hover:from-blue-700 hover:to-purple-700 transition-all duration-300 shadow-xl hover:shadow-2xl transform hover:-translate-y-1">
            <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6"></path>
            </svg>
            Add Your First Item
          </router-link>
        </div>
      </div>

      <!-- Premium Table Design -->
      <main v-if="filteredProducts.length > 0" class="max-w-7xl mx-auto px-6 pb-20">
        <div class="bg-white/90 backdrop-blur-2xl rounded-3xl shadow-2xl overflow-hidden border border-white/50 ring-1 ring-gray-200/20">
          <!-- Table Header with Search -->
          <div class="bg-gradient-to-r from-gray-50 to-blue-50 p-6 border-b border-gray-200/50">
            <div class="flex flex-col sm:flex-row justify-between items-center gap-4">
              <h3 class="text-2xl font-bold text-gray-800">Product Inventory</h3>
              <div class="flex items-center gap-4">
                <div class="relative">
                  <input type="text" placeholder="Search products..." 
                    class="pl-12 pr-4 py-3 rounded-xl bg-white border-2 border-gray-200 focus:border-blue-500 focus:outline-none focus:ring-4 focus:ring-blue-500/20 transition-all duration-300 w-64">
                  <svg class="w-5 h-5 text-gray-400 absolute left-4 top-1/2 transform -translate-y-1/2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
                  </svg>
                </div>
              </div>
            </div>
          </div>

          <div class="overflow-x-auto">
            <table class="min-w-full table-auto">
              <thead>
                <tr class="bg-gradient-to-r from-gray-50 via-blue-50 to-purple-50 border-b border-gray-200">
                  <th class="px-8 py-6 text-center text-sm font-black text-gray-700 uppercase tracking-wider">
                    <div class="flex items-center justify-center gap-2">
                      <span>ID</span>
                      <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 16V4m0 0L3 8m4-4l4 4m6 0v12m0 0l4-4m-4 4l-4-4"></path>
                      </svg>
                    </div>
                  </th>
                  <th class="px-8 py-6 text-center text-sm font-black text-gray-700 uppercase tracking-wider">Brand</th>
                  <th class="px-8 py-6 text-left text-sm font-black text-gray-700 uppercase tracking-wider">Model</th>
                  <th class="px-8 py-6 text-center text-sm font-black text-gray-700 uppercase tracking-wider">Ram</th>
                  <th class="px-8 py-6 text-center text-sm font-black text-gray-700 uppercase tracking-wider">Storage</th>
                  <th class="px-8 py-6 text-center text-sm font-black text-gray-700 uppercase tracking-wider">Color</th>
                  <th class="px-8 py-6 text-center text-sm font-black text-gray-700 uppercase tracking-wider">Price</th>
                  <th class="px-8 py-6 text-center text-sm font-black text-gray-700 uppercase tracking-wider">Actions</th>
                </tr>
              </thead>
              <tbody class="divide-y divide-gray-100">
                <tr v-for="(product, index) in filteredProducts" :key="product.id || index"
                  class="itbms-row hover:bg-gradient-to-r hover:from-blue-50/80 hover:via-purple-50/80 hover:to-pink-50/80 transition-all duration-500 group hover:shadow-lg"
                  :class="index % 2 === 0 ? 'bg-white/80' : 'bg-gray-50/50'">
                  
                  <td class="px-8 py-6 itbms-id text-center">
                    <div class="inline-flex items-center justify-center text-black rounded-2xl text-l font-bold shadow-lg group-hover:scale-110 transition-all duration-300 group-hover:text-blue-900">
                      {{ product.id }}
                    </div>
                  </td>
                  
                  <td class="px-8 py-6 itbms-brand text-center">
                    <span class="inline-flex items-center  text-l font-bold text-black group-hover:scale-110 transition-all duration-300 group-hover:text-blue-900">
                      {{ product.brandName }}
                    </span>
                  </td>
                  
                  <td class="px-8 py-6 itbms-model">
                    <div class="font-bold text-l text-black group-hover:text-blue-900 group-hover:scale-110 transition-colors duration-300">
                      {{ product.model }}
                    </div>
                  </td>
                  
                  <td class="px-8 py-6 itbms-ramGb text-center">
                    <span class="inline-flex items-center text-l font-bold text-green-700 group-hover:text-green-900 group-hover:scale-110 transition-all duration-300">
                      {{ product.ramGb || "-" }} GB
                    </span>
                  </td>
                  
                  <td class="px-8 py-6 itbms-storageGb text-center">
                    <span class="inline-flex items-center text-l font-bold  text-orange-700 group-hover:text-orange-900  group-hover:scale-110 transition-all duration-300">
                      {{ product.storageGb }} GB
                    </span>
                  </td>
                  
                  <td class="px-8 py-6 itbms-color text-center">
                    <span class="inline-flex items-center  text-l font-bold  text-black  group-hover:scale-110 transition-all duration-300 group-hover:text-blue-900">
                      {{ product.color || "-" }}
                    </span>
                  </td>
                  
                  <td class="px-8 py-6 itbms-price text-center">
                    <div class="text-l font-black bg-gradient-to-r from-green-600 to-emerald-600 bg-clip-text text-transparent group-hover:from-green-700 group-hover:to-emerald-700 transition-all duration-300 group-hover:scale-110">
                      {{ product.price?.toLocaleString() }} ฿
                    </div>
                  </td>
                  
                  <td class="px-8 py-6">
                    <div class="flex gap-4 justify-center">
                      <button @click="editProduct(product.id)"
                        class="itbms-edit-button group bg-gradient-to-r from-blue-500 to-blue-600 hover:from-blue-600 hover:to-blue-700 text-white p-4 rounded-2xl transition-all duration-300 shadow-lg hover:shadow-2xl transform hover:-translate-y-2 hover:scale-110 relative overflow-hidden">
                        <div class="absolute inset-0 bg-gradient-to-r from-white/0 via-white/20 to-white/0 transform -skew-x-12 -translate-x-full group-hover:translate-x-full transition-transform duration-700"></div>
                        <svg class="w-5 h-5 relative z-10" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z"></path>
                        </svg>
                      </button>
                      
                      <button @click="deleteProductHandler(product.id)"
                        class="itbms-delete-button group bg-gradient-to-r from-red-500 to-red-600 hover:from-red-600 hover:to-red-700 text-white p-4 rounded-2xl transition-all duration-300 shadow-lg hover:shadow-2xl transform hover:-translate-y-2 hover:scale-110 relative overflow-hidden">
                        <div class="absolute inset-0 bg-gradient-to-r from-white/0 via-white/20 to-white/0 transform -skew-x-12 -translate-x-full group-hover:translate-x-full transition-transform duration-700"></div>
                        <svg class="w-5 h-5 relative z-10" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"></path>
                        </svg>
                      </button>
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </main>
    </main>

    <Footer />

    <!-- Enhanced Modals -->
    <Modal v-if="showConfirmModal" :title="'Confirm Deletion'" :message="'Do you want to delete this sale item?'"
      :displayConfirm="true" :confirmAction="confirmDeleteItem" :closeAction="() => {
        showConfirmModal = false;
      }">
    </Modal>

    <Modal v-if="itemNotExist === true" :title="'Error Occurred'"
      :message="'An error has occurred, the sale item does not exist.'" :displayConfirm="true"
      :confirmAction="confirmDeleteItem" :closeAction="() => {
        (itemNotExist = false), updateItems();
      }">
    </Modal>

  </div>
</template>

<script setup>
import { fetchProductById, deleteProduct } from "../libs/fetchProduct.js";
import { ref, computed, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";
import Header from "../components/Header.vue";
import Footer from "../components/Footer.vue";
import { fetchBrands } from "../libs/fetchBrand";
import { fetchProducts } from "../libs/fetchProduct";
import Modal from "../components/Modal.vue";
import Alert from "../components/Alert.vue";
import images from "../data/image.json";

const products = ref([]);
const brands = ref([]);
const selectedBrand = ref("all");
const router = useRouter();
const itemNotExist = ref(false);
const showConfirmModal = ref(false);
const productToDeleteId = ref(null);

//router
const route = useRoute();
const added = computed(() => route.query.added === "true");
const deleted = ref(false);

const updateItems = async () => {
  itemNotExist.value = false;
  products.value = await fetchProducts(); 
};

const confirmDeleteItem = async () => {
  try {
    const detail = await fetchProductById(productToDeleteId.value);

    if (detail.error) {
      itemNotExist.value = true;
      return;
    }
    await deleteProduct(productToDeleteId.value);
    deleted.value = true;
    await updateItems();
    showConfirmModal.value = false;
  } catch (error) {
    itemNotExist.value = true;
    showConfirmModal.value = false;
    deleted.value = false;
  }
};

onMounted(async () => {
  try {
    const basicList = await fetchProducts();
    products.value = basicList;
  } catch (error) {
    console.error("Error fetching products:", error);
    products.value = [];
  }

  try {
    brands.value = await fetchBrands();
    if (!Array.isArray(brands.value)) {
      console.error("Error: fetched brands is not an array");
      brands.value = [];
    }
  } catch (error) {
    console.error("Error fetching brands:", error);
    brands.value = [];
  }
});

const editProduct = (id) => {
  router.push({ name: "EditSaleItemDetail", params: { id: id } });
};

function deleteProductHandler(id) {
  productToDeleteId.value = id;
  showConfirmModal.value = true;
}

function goToGallery() {
  router.push("/sale-items");
}

const sortedProducts = computed(() =>
  [...products.value].sort(
    (a, b) => new Date(a.createdTime) - new Date(b.createdTime)
  )
);

const filteredProducts = computed(() => {
  if (selectedBrand.value === "all") return sortedProducts.value;
  return sortedProducts.value.filter(
    (p) => p.brandName === selectedBrand.value
  );
});
</script>

<style scoped>
@keyframes slide-in {
  from {
    opacity: 0;
    transform: translateY(-20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.animate-slide-in {
  animation: slide-in 0.5s ease-out;
}
</style>