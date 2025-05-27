<template>
  <div
    class="bg-gradient-to-br from-indigo-100 via-white to-purple-100 text-black min-h-screen font-[Poppins]"
  >
    <Header />

    <!-- Section Title -->
    <section class="py-12 px-7 text-center">
      <h2
        class="text-5xl font-bold mb-4 text-transparent bg-clip-text bg-gradient-to-r from-purple-600 to-indigo-600 drop-shadow-sm"
      >
        รายการมือถือทั้งหมด
      </h2>
      <p class="text-gray-700 text-lg max-w-2xl mx-auto drop-shadow-sm">
        ดูสินค้าทั้งหมดที่มีในระบบในรูปแบบแกลเลอรี
      </p>
    </section>

    <!-- Alerts -->
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
      <div
        class="bg-white/80 backdrop-blur-2xl rounded-3xl p-8 shadow-2xl border border-white/30 ring-1 ring-gray-200/20 hover:shadow-3xl transition-all duration-700"
      >
        <div
          class="flex flex-col lg:flex-row justify-between items-center gap-8"
        >
          <!-- Left section: Add + Filter -->
          <div class="flex items-center gap-6 w-full lg:w-auto flex-wrap">
            <!-- Add Sale Item -->
            <router-link
              to="/sale-items/add"
              class="itbms-sale-item-add group bg-gradient-to-r from-blue-600 via-blue-700 to-purple-700 text-white px-6 py-3 rounded-2xl hover:from-blue-700 hover:via-purple-700 hover:to-purple-800 transition-all duration-500 shadow-xl hover:shadow-2xl transform hover:-translate-y-1 hover:scale-105 flex items-center gap-3 whitespace-nowrap font-semibold text-base relative overflow-hidden"
            >
              <div
                class="absolute inset-0 bg-gradient-to-r from-white/0 via-white/20 to-white/0 transform -skew-x-12 -translate-x-full group-hover:translate-x-full transition-transform duration-1000"
              ></div>
              <svg
                class="w-5 h-5 relative z-10"
                fill="none"
                stroke="currentColor"
                viewBox="0 0 24 24"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M12 6v6m0 0v6m0-6h6m-6 0H6"
                ></path>
              </svg>
              <span class="relative z-10">Add Sale Item</span>
            </router-link>

            <!-- Filter Brand -->
            <div class="w-full sm:w-auto relative min-w-[250px]">
              <div
                class="flex flex-col sm:flex-row sm:items-center gap-3 w-full"
              >
                <div class="relative w-full sm:w-72">
                  <div
                    class="w-full min-h-[48px] flex items-center justify-between px-4 py-2 text-base bg-white/90 backdrop-blur-sm border-2 border-gray-200 rounded-2xl shadow-md hover:shadow-lg cursor-pointer text-gray-700 transition-all"
                  >
                    <div
                      @click="toggleDropdown"
                      class="itbms-brand-filter flex flex-wrap items-center gap-1 overflow-hidden"
                    >
                      <span
                        v-if="selectedBrands.length === 0"
                        class="text-gray-400"
                        >Filter by brand(s)</span
                      >
                      <span
                        v-for="brand in selectedBrands"
                        :key="brand"
                        class="itbms-filter-item bg-purple-100 text-purple-700 text-sm px-2 py-1 rounded-lg flex items-center gap-1"
                      >
                        {{ brand }}
                        <button
                          @click="removeBrand(brand)"
                          class="itbms-filter-item-clear text-purple-500 hover:text-red-600"
                        >
                          ✕
                        </button>
                      </span>
                    </div>
                    <button
                      @click="toggleDropdown"
                      class="itbms-brand-filter-button"
                    >
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        class="w-5 h-5 text-gray-700"
                        fill="currentColor"
                        viewBox="0 0 24 24"
                      >
                        <path d="M19 9l-7 7-7-7" />
                      </svg>
                    </button>
                  </div>

                  <!-- Dropdown -->
                  <div
                    v-if="showDropdown"
                    class="absolute z-50 mt-2 w-full bg-white border border-gray-200 rounded-2xl shadow-lg max-h-60 overflow-y-auto"
                  >
                    <label
                      v-for="(brand, index) in sortedBrands"
                      :key="index"
                      class="itbms-filter-item flex items-center px-4 py-2 text-sm cursor-pointer hover:bg-purple-50"
                    >
                      <input
                        type="checkbox"
                        :value="brand.name"
                        v-model="selectedBrands"
                        class="mr-2 rounded text-purple-600 focus:ring-purple-500"
                      />
                      {{ brand.name }}
                    </label>
                  </div>
                </div>

                <!-- Clear Button -->
                <button
                  @click="clearAllBrands"
                  class="itbms-brand-filter-clear px-4 py-2 text-sm font-medium bg-purple-50 text-purple-700 border border-purple-200 rounded-xl hover:bg-red-500 hover:text-white transition-all"
                >
                  Clear
                </button>
              </div>
            </div>
          </div>

          <!-- View Toggle Buttons -->
          <div
            class="flex gap-2 w-full lg:w-auto bg-gradient-to-r from-gray-100 to-gray-200 p-2 rounded-2xl shadow-inner"
          >
            <button
              disabled
              class="flex-1 lg:flex-none px-6 py-3 rounded-xl bg-purple-600 text-white shadow-md flex items-center justify-center gap-2 text-base font-semibold bg-gradient-to-r from-blue-600 to-purple-600"
            >
              <svg
                class="w-6 h-6"
                fill="none"
                stroke="currentColor"
                viewBox="0 0 24 24"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M4 6a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2H6a2 2 0 01-2-2V6zM14 6a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2h-2a2 2 0 01-2-2V6zM4 16a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2H6a2 2 0 01-2-2v-2zM14 16a2 2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2h-2a2 2 0 01-2-2v-2z"
                ></path>
              </svg>
              Gallery
            </button>
            <button
              @click="goToList"
              class="flex-1 lg:flex-none px-6 py-3 rounded-xl transition-all duration-300 flex items-center justify-center gap-2 bg-white text-black hover:bg-gray-600 font-semibold text-base"
            >
              <span class="font-mono">≡</span>
              List
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Empty State -->
    <div v-if="saleItems.length === 0" class="text-center py-32">
      <div class="itbms-row max-w-lg mx-auto">
        <div class="relative mb-8">
          <div
            class="w-32 h-32 mx-auto bg-gradient-to-br from-blue-100 to-purple-100 rounded-full flex items-center justify-center shadow-2xl"
          >
            <svg
              class="w-16 h-16 text-gray-400"
              fill="none"
              stroke="currentColor"
              viewBox="0 0 24 24"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="1.5"
                d="M20 13V6a2 2 0 00-2-2H6a2 2 0 00-2 2v7m16 0v5a2 2 0 01-2 2H6a2 2 0 01-2 2v-5m16 0h-2.586a1 1 0 00-.707.293l-2.414 2.414a1 1 0 01-.707.293h-3.172a1 1 0 01-.707-.293l-2.414-2.414A1 1 0 006.586 13H4"
              ></path>
            </svg>
          </div>
          <div
            class="absolute -top-2 -right-2 w-8 h-8 bg-yellow-400 rounded-full flex items-center justify-center animate-bounce"
          >
            <span class="text-lg">✨</span>
          </div>
        </div>
        <h3 class="itbms-message text-4xl font-bold mb-4 text-gray-700">
          no sale item
        </h3>
        <p class="text-gray-500 text-xl mb-8">
          Start building your inventory by adding your first mobile device
        </p>
        <router-link
          to="/sale-items/add"
          class="inline-flex items-center gap-3 bg-gradient-to-r from-blue-600 to-purple-600 text-white px-8 py-4 rounded-2xl font-semibold text-lg hover:from-blue-700 hover:to-purple-700 transition-all duration-300 shadow-xl hover:shadow-2xl transform hover:-translate-y-1"
        >
          <svg
            class="w-6 h-6"
            fill="none"
            stroke="currentColor"
            viewBox="0 0 24 24"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M12 6v6m0 0v6m0-6h6m-6 0H6"
            ></path>
          </svg>
          Add Your First Item
        </router-link>
      </div>
    </div>

    <!-- Sort Buttons -->
    <main class="max-w-6xl mx-auto px-1 pb-8 flex flex-col gap-4">
      <div v-if="saleItems.length !== 0" class="flex justify-end gap-3 mt-4">
        <button
          @click="sortOrder = 'default'"
          class="itbms-brand-none"
          :class="[
            'p-2 rounded-lg',
            sortOrder === 'default'
              ? 'bg-purple-600 text-white'
              : 'bg-gray-100 text-gray-700',
          ]"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="w-5 h-5"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M4 6h16M4 12h16M4 18h16"
            />
          </svg>
        </button>
        <button
          @click="sortOrder = 'asc'"
          :class="[
            'p-2 rounded-lg',
            sortOrder === 'asc'
              ? 'bg-purple-600 text-white'
              : 'bg-gray-100 text-gray-700',
          ]"
          class="itbms-brand-asc"
        >
          <img
            src="@/assets/icons/view-sort-ascending-svgrepo-com.svg"
            alt="Sort Ascending"
            class="w-5 h-5"
          />
        </button>
        <button
          @click="sortOrder = 'desc'"
          :class="[
            'p-2 rounded-lg',
            sortOrder === 'desc'
              ? 'bg-purple-600 text-white'
              : 'bg-gray-100 text-gray-700',
          ]"
          class="itbms-brand-desc"
        >
          <img
            src="@/assets/icons/view-sort-descending-svgrepo-com.svg"
            alt="Sort Descending"
            class="w-5 h-5"
          />
        </button>
      </div>
    </main>

    <!-- Product Grid -->
    <main
      class="max-w-7xl mx-auto grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 gap-6 px-6 pb-16"
    >
      <div
        v-for="(product, index) in saleItems"
        :key="product.id"
        class="bg-white p-4 rounded-2xl hover:bg-purple-50 transition-all duration-300"
      >
        <ProductCard
          class="itbms-row"
          :product="product"
          :imageUrl="imageArray[index % imageArray.length]?.url"
        />
      </div>
    </main>

    <!-- Pagination -->
    <div class="max-w-7xl mx-auto px-6 pb-12">
      <PageBar
        :currentPage="currentPage"
        :totalPages="pageResponse.totalPages"
        :pageSize="Number(pageSize)"
        @update:currentPage="(val) => (currentPage = val)"
        @update:pageSize="(val) => (pageSize = val)"
        @clickButton="clickButton = !clickButton"
      />
    </div>

    <Footer />
  </div>
</template>

<script setup>
import { computed, onMounted, ref, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import Alert from "../components/Alert.vue";
import Footer from "../components/Footer.vue";
import Header from "../components/Header.vue";
import ProductCard from "../components/ProductCard.vue";
import images from "../data/image.json";
import { fetchBrands } from "../libs/fetchBrand";
import { fetchSaleItemByCondition } from "../libs/fetchSaleItem";
import PageBar from "../components/PageBar.vue";
import { usePageStore } from "../stores/pageStore.js";

const imageArray = images;

// Reactive variables
const saleItems = ref([]);
const brands = ref([]);
const selectedBrands = ref([]);
const showDropdown = ref(false);
const currentPage = ref(1);
const pageSize = ref(Number(sessionStorage.getItem("pageSize")) || 10);
const pageResponse = ref({ totalPages: 0 });
const sortOrder = ref(sessionStorage.getItem("sortOrder") || "default");
const clickButton = ref(false);
const isLoading = ref(false);

// Router and store
const router = useRouter();
const route = useRoute();
const pageStore = usePageStore();

// Computed properties
const added = computed(() => route.query.added === "true");
const deleted = computed(() => route.query.deleted === "true");

const sortDirection = computed(() => {
  if (sortOrder.value === "asc") return "ASC";
  if (sortOrder.value === "desc") return "DESC";
  else {
    return "default"; // default
  }
});

watch(sortOrder, () => {
  console.log(sortOrder.value);
  console.log(sortDirection);
})

const sortedBrands = computed(() => {
  return [...brands.value].sort((a, b) => a.name.localeCompare(b.name));
});

// Main function to fetch data
const fetchData = async (resetPage = false) => {
  if (isLoading.value) return; // Prevent duplicate calls

  try {
    isLoading.value = true;

    if (resetPage) {
      currentPage.value = 0;
    }

    const payload = {
      filterBrands: selectedBrands.value,
      page: currentPage.value || 0,
      size: pageSize.value || 10,
      sortField: sortDirection.value === "default" ? "createdOn" : "brand.name",
      sortDirection: sortDirection.value,
    };

    const response = await fetchSaleItemByCondition(payload);

    // Handle pagination adjustment
    if (response.totalPages > 0 && currentPage.value > response.totalPages) {
      currentPage.value = response.totalPages;
      // Fetch again with corrected page
      const correctedPayload = { ...payload, page: currentPage.value };
      const correctedResponse = await fetchSaleItemByCondition(
        correctedPayload
      );
      saleItems.value = correctedResponse.content || [];
      pageResponse.value.totalPages = correctedResponse.totalPages || 0;
    } else {
      saleItems.value = response.content || [];
      pageResponse.value.totalPages = response.totalPages || 0;
    }

    // Update page store
    pageStore.setPageNumber(currentPage.value);
  } catch (error) {
    console.error("Error fetching data:", error);
    saleItems.value = [];
    pageResponse.value.totalPages = 0;
  } finally {
    isLoading.value = false;
  }
};

// Initialize data on component mount
onMounted(async () => {
  console.log(currentPage.value);
  
  try {
    // Load brands
    brands.value = await fetchBrands();
    console.log("onMount");

    // Restore saved state
    const savedBrands = sessionStorage.getItem("selectedBrands");
    if (savedBrands) {
      selectedBrands.value = JSON.parse(savedBrands);
    }

    // Get initial page from store
    currentPage.value = pageStore.getPageNumber() || 0;

    const payload = {
      filterBrands: selectedBrands.value,
      page: currentPage.value || 0,
      size: pageSize.value || 10,
      sortField: sortDirection.value === "default" ? "createdOn" : "brand.name",
      sortDirection: sortDirection.value,
    };
    // Initial data fetch
    console.log(payload);

    await fetchData();
    const response = await fetchSaleItemByCondition(payload);
    console.log(response);
    if (response.content.length <= 0) {
      currentPage.value = pageStore.setPageNumber(
        pageStore.getPageNumber() - 1
      );
      currentPage.value = pageStore.getPageNumber();
    }
  } catch (error) {
    console.error("Error in onMounted:", error);
  }
});

// Watchers for reactive updates
watch([selectedBrands, sortOrder, pageSize], () => {
  fetchData(true); // reset to page 1 
});

watch(currentPage, () => {
  console.log(currentPage.value);
  if (currentPage.value >= 0) {
    fetchData();
  }
});

watch(clickButton, () => {
  fetchData();
});

// Save to session storage
watch(pageSize, (newVal) => {
  sessionStorage.setItem("pageSize", newVal.toString());
});

watch(sortOrder, (newVal) => {
  sessionStorage.setItem("sortOrder", newVal);
});

watch(selectedBrands, (newVal) => {
  sessionStorage.setItem("selectedBrands", JSON.stringify(newVal));
});

// UI event handlers
function toggleDropdown() {
  showDropdown.value = !showDropdown.value;
}

function clearAllBrands() {
  selectedBrands.value = [];
}

function removeBrand(brand) {
  selectedBrands.value = selectedBrands.value.filter((b) => b !== brand);
}

function goToList() {
  router.push("/sale-items/list");
}
</script>
