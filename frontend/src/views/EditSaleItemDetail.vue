<template>
  <div class="bg-gradient-to-br from-indigo-100 via-white to-purple-100 min-h-screen flex-col">
    <Header />
    <main class="flex-grow">
      <!-- Product Detail -->
      <div v-if="product"
        class="bg-white text-gray-800 px-8 py-12 mx-auto max-w-5xl rounded-3xl shadow-2xl mt-10 space-y-8">
        <!-- Breadcrumb -->
        <nav class="flex items-center space-x-2 text-sm text-gray-500">
          <router-link to="/sale-items" class="itbms-home-button font-medium text-blue-600 hover:underline">
            Home
          </router-link>
          <span>›</span>
          <router-link :to="`/sale-items/${route.params.id}`" class="itbms-back-button font-semibold text-gray-900">
            {{ form.brandName }} {{ form.model }} {{ form.ramGb }}GB/
            {{ form.storageGb }}GB {{ form.color }}
          </router-link>
        </nav>

        <div class="flex flex-col lg:flex-row gap-12">
          <SaleItemForm @payload="saveProduct" :formtype="'edit'" :form=form>
          </SaleItemForm>
        </div>
      </div>

      <!-- Fallback -->
      <div v-else class="text-center text-red-500 py-16 text-lg">
        Product not found.
      </div>

      <!-- Error Modal -->
      <transition name="fade">
        <div v-if="showErrorModal" class="fixed inset-0 bg-black bg-opacity-50 z-50 flex items-center justify-center">
          <div class="bg-white p-6 rounded-2xl shadow-lg max-w-sm text-center">
            <h2 class="text-xl font-bold text-red-600 mb-2">Item Not Found</h2>
            <p class="mb-4 text-gray-700">The requested sale item does not exist.</p>
            <p class="text-sm text-gray-500 mb-6">Redirecting in {{ secondsLeft }} seconds...</p>
            <button @click="goSaleItemList"
              class="px-6 py-2 rounded-xl bg-yellow-500 text-white font-medium hover:bg-yellow-600 transition">OK</button>
          </div>
        </div>
      </transition>
    </main>

    <Footer />
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, watch, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import { fetchProductById, editProduct } from "../libs/fetchProduct.js";
import { fetchBrands } from '../libs/fetchBrand.js'
import Modal from "../components/Modal.vue";
import Header from "../components/Header.vue";
import Footer from "../components/Footer.vue";
import { useStateStore } from "../stores/stateStore.js";
import SaleItemForm from '../components/SaleItemForm.vue'

const route = useRoute();
const router = useRouter();
// const stateStore = useStateStore();
// const { getImageUrl } = stateStore;


const product = ref(null);
const brands = ref([]);
// const inputRefs = ref([]);

const showErrorModal = ref(false);
const secondsLeft = ref(3);
let timeoutRef = null;
let countdownInterval = null;

// form state
const form = reactive({
  brandName: "",
  model: "",
  price: 0,
  description: "",
  ramGb: 0,
  screenSizeInch: 0,
  storageGb: 0,
  color: "",
  quantity: null,
});

onMounted(async () => {
  try {
    const data = await fetchProductById(route.params.id);
    product.value = data;
    console.log(product.value);

    Object.assign(form, {
      brandName: data.brandName,
      model: data.model,
      price: data.price,
      description: data.description,
      ramGb: data.ramGb,
      screenSizeInch: data.screenSizeInch,
      storageGb: data.storageGb,
      color: data.color,
      quantity: data.quantity,
    });
    // originData.value = { ...form };
    const bList = await fetchBrands();
    brands.value = bList.sort((a, b) => a.name.localeCompare(b.name));
  } catch (err) {
    showErrorModal.value = true;
    timeoutRef = setTimeout(() => router.push("/sale-items"), 3000);
    countdownInterval = setInterval(() => { if (secondsLeft.value > 0) secondsLeft.value--; }, 1000);
  }
});

const saveProduct = async (payload) => {
  try {
    console.log(payload);

    const matched = brands.value.find(b => b.name === payload.brand.name);
    console.log(matched);

    const sendPayload = {
      ...payload,
      brand: { id: matched?.id, name: matched?.name }
    }
    console.log(sendPayload);
    const a = await editProduct(route.params.id, sendPayload);
    console.log(a);

    router.push({ path: `/sale-items/${route.params.id}`, query: { updated: 'true' } });
  } catch (err) {
    if (err.response?.status === 404) showErrorModal.value = true;
    else console.error(err);
  }
};

// Watch for error modal to start countdown
watch(showErrorModal, (v) => {
  if (v) {
    countdownInterval = setInterval(() => {
      if (secondsLeft.value > 0) {
        secondsLeft.value--;
      }
    }, 1000);
  }
});

const goSaleItemList = () => {
  clearTimeout(timeoutRef); clearInterval(countdownInterval);
  router.push("/sale-items");
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