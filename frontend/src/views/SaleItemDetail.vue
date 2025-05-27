<template>
  <div class="min-h-screen flex flex-col bg-gradient-to-br from-indigo-100 via-white to-purple-100">
    <Header />
    <main class="flex-grow">
      <!-- Alerts -->
      <Alert v-if="updated" :message="'The sale item has been updated.'" :state="'updated'">
      </Alert>

      <!-- Product Detail -->
      <div v-if="product" class="bg-white text-black px-6 py-10 mx-auto max-w-6xl rounded-3xl shadow-xl mt-6 space-y-6">
        <nav class="text-xl text-gray-700 px-1 pt-6 max-w-6xl mx-auto">
          <router-link to="/sale-items" class="itbms-home-button font-medium text-blue-600 hover:underline">
            Home
          </router-link>
          <span> ></span>
          <span v-if="product" class="ml-2 text-black font-medium">
            {{ product.brandName }} {{ product.model }} {{ product.ramGb }}/
            {{ product.storageGb }}{{ product.storageGbUnit }} {{ product.color }}
          </span>
        </nav>

        <div class="flex flex-col md:flex-row gap-10">
          <!-- Product Images -->
          <div class="md:w-1/2 w-full">
            <img :src="getImageUrl" :alt="`${product.brandName} ${product.model}`"
              class="w-full rounded-2xl shadow-md object-cover aspect-square" />
            <div class="flex gap-3 mt-4 overflow-x-auto">
              <img v-for="(img, index) in product.images" :key="index" :src="img" :alt="`Image ${index + 1}`"
                class="w-20 h-20 object-cover rounded-lg border hover:ring-2 hover:ring-blue-400 transition-all duration-200 cursor-pointer" />
            </div>
          </div>

          <!-- Product Info -->
          <div class="md:w-1/2 w-full space-y-4 text-base itbms-row">
            <h1 class="text-2xl font-semibold mb-2">
              {{ product.brandName }} {{ product.model }}
            </h1>

            <p class="text-3xl font-extrabold text-yellow-400 itbms-price">
              {{ product.price.toLocaleString() }}
              <span class="font-extrabold itbms-price-unit">Baht</span>
            </p>

            <p class="text-gray-600 itbms-description">
              {{ product.description || "-" }}
            </p>

            <div class="grid grid-cols-2 gap-y-3 gap-x-4 mt-6 text-sm">
              <p>
                <strong>Brand: </strong><span class="itbms-brand"
                  :class="{ 'text-gray-400': !product.brandName?.trim() }">{{ product.brandName || "-" }}</span>
              </p>
              <p>
                <strong>Model: </strong><span class="itbms-model"
                  :class="{ 'text-gray-400': !product.model?.trim() }">{{
                    product.model || "-" }}</span>
              </p>
              <p>
                <strong>RAM: </strong><span class="itbms-ramGb" :class="{ 'text-gray-400': !product.ramGb }">{{
                  product.ramGb || "-" }}</span><span class="itbms-ramGb-unit" v-if="product.ramGb">GB</span>
              </p>
              <p>
                <strong>Screen Size: </strong><span class="itbms-screenSizeInch"
                  :class="{ 'text-gray-400': !product.screenSizeInch }">{{
                    product.screenSizeInch
                      ? Number(product.screenSizeInch)
                      // .toFixed(1)
                      : "-"
                  }}</span><span class="itbms-screenSizeInch-unit" v-if="product.screenSizeInch">Inches</span>
              </p>
              <p>
                <strong>Storage: </strong><span class="itbms-storageGb"
                  :class="{ 'text-gray-400': !product.storageGb }">{{ product.storageGb || "-" }}</span><span
                  class="itbms-storageGb-unit">{{
                    product.storageGb ? product.storageGbUnit || "GB" : ""
                  }}</span>
              </p>
              <p>
                <strong>Color: </strong><span class="itbms-color"
                  :class="{ 'text-gray-400': !product.color?.trim() }">{{
                    product.color || "-" }}</span>
              </p>
              <p>
                <strong>Available Quantity: </strong><span class="itbms-quantity"
                  :class="{ 'text-gray-400': !product.quantity }">{{ product.quantity }}</span><span
                  class="itbms-quantity-unit"> units</span>
              </p>
            </div>

            <!-- Edit / Delete Buttons -->
            <div class="flex gap-4 mt-6">
              <!-- Edit Button -->
              <button @click="editProduct"
                class="itbms-edit-button px-4 py-2 rounded-md border border-gray-300 bg-white text-gray-800 hover:bg-gray-100 transition font-medium">
                Edit
              </button>

              <!-- Delete Button -->
              <button @click="deleteProductHandler"
                class="itbms-delete-button px-4 py-2 rounded-md border border-red-300 bg-white text-red-600 hover:bg-red-50 transition font-medium">
                Delete
              </button>
            </div>
          </div>
        </div>
      </div>

      <!-- product is not found -->
      <div v-else class="text-center text-red-500 py-16 text-lg">
        Product not found.
      </div>

      <!-- Error Modal -->
      <transition name="fade">
        <div v-if="showErrorModal" class="fixed inset-0 bg-black bg-opacity-60 z-50 flex items-center justify-center">
          <div class="bg-white p-6 rounded-xl shadow-lg max-w-md text-center">
            <h2 class="text-xl font-bold text-red-600 mb-2">Item Not Found</h2>
            <p class="itbms-message text-gray-700 mb-4">
              The requested sale item does not exist.
            </p>
            <p class="text-sm text-gray-500 mb-4">
              Redirecting in {{ secondsLeft }} seconds...
            </p>
            <button @click="goBack"
              class="itbms-button px-5 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition">
              OK
            </button>
          </div>
        </div>
      </transition>

      <transition name="fade">
        <div v-if="showConfirmModal" class="fixed inset-0 bg-black bg-opacity-60 z-50 flex items-center justify-center">
          <div class="bg-white p-6 rounded-xl shadow-lg max-w-md text-center">
            <h2 class="text-xl font-bold text-gray-800 mb-4">Confirm Deletion</h2>
            <p class="itbms-message text-gray-700 mb-6">
              Do you want to delete this sale item?
            </p>
            <div class="flex justify-center gap-4">
              <button @click="confirmDeleteProduct"
                class="itbms-confirm-button px-5 py-2 bg-red-600 text-white rounded-lg hover:bg-red-700 transition">
                Confirm
              </button>
              <button @click="showConfirmModal = false"
                class="itbms-cancel-button px-5 py-2 bg-gray-200 text-gray-800 rounded-lg hover:bg-gray-300 transition">
                Cancel
              </button>
            </div>
          </div>
        </div>
      </transition>
    </main>
    <Footer />
  </div>
</template>

<script setup>
import { ref, onMounted, watch, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import { fetchProductById, deleteProduct } from "../libs/fetchProduct.js";
import Header from "../components/Header.vue";
import Footer from "../components/Footer.vue";
import { useStateStore } from "../stores/stateStore.js";
import Alert from "../components/Alert.vue";

// router
const route = useRoute();
const router = useRouter();

// product info
const product = ref(null);

//error modal
const showErrorModal = ref(false);

//timing redirect after modal
const timeoutRef = ref(null);
const secondsLeft = ref(3);
const countdownInterval = ref(null);

//imageStore
const stateStore = useStateStore();
const { getImageUrl } = stateStore;

// confirm to delete
const showConfirmModal = ref(false);

const updated = computed(() => route.query.updated === "true");

onMounted(async () => {
  try {
    product.value = await fetchProductById(route.params.id);
  } catch (error) {
    showErrorModal.value = true;
    timeoutRef.value = setTimeout(() => {
      router.push("/sale-items");
    }, 3000);
  }
});

// go back
const goBack = () => {
  if (timeoutRef.value) clearTimeout(timeoutRef.value);
  if (countdownInterval.value) clearInterval(countdownInterval.value);
  router.push("/sale-items");
};

// go edit page
const editProduct = () => {
  router.push({ name: "EditSaleItemDetail" });
};

// start count timer redirect
watch(showErrorModal, (newVal) => {
  if (newVal && !countdownInterval.value) {
    countdownInterval.value = setInterval(() => {
      if (secondsLeft.value > 0) {
        secondsLeft.value -= 1;
      }
    }, 1000);
  }
});

// confirm delete
const confirmDeleteProduct = async () => {
  try {
    const res = await deleteProduct(route.params.id);
    
    if (res.ok) {
      showConfirmModal.value = false;
      router.push({ path: "/sale-items", query: { deleted: "true" } });
    } else if (res.status === 404) {
      showConfirmModal.value = false;
      showErrorModal.value = true;
      secondsLeft.value = 3;

      countdownInterval.value = setInterval(() => {
        if (secondsLeft.value > 0) {
          secondsLeft.value -= 1;
        } else {
          clearInterval(countdownInterval.value);
        }
      }, 1000);

      timeoutRef.value = setTimeout(() => {
        router.push("/sale-items");
      }, 3000);
    } else {
      console.error("Delete failed with status:", res.status);
    }
  } catch (err) {
    console.error("Error:", err);
  }
};

// show delete confirm modal
const deleteProductHandler = () => {
  showConfirmModal.value = true;
};
</script>

<style scoped>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
