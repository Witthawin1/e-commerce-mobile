<template>
  <div class="flex flex-col min-h-screen bg-gradient-to-br from-indigo-100 via-white to-purple-100 text-black">
    <Header />
    <main class="flex-grow">
      <!-- Add Product Form -->
      <div class="bg-white text-black px-8 py-10 mx-auto max-w-6xl rounded-2xl shadow-lg mt-8 mb-12">
        <!-- Breadcrumb Navigation -->
        <nav class="flex items-center text-lg text-gray-600 mb-6">
          <router-link to="/sale-items"
            class="itbms-home-button font-medium text-blue-600 hover:text-blue-800 transition-colors">
            Home
          </router-link>
          <span class="mx-2 text-gray-400 font-light"> > </span>
          <span class="text-gray-800 font-medium">Add New Product</span>
        </nav>

        <h1 class="text-3xl font-bold text-gray-800 mb-8 border-b border-gray-200 pb-4">
          Add New Product
        </h1>
        <SaleItemForm @payload="saveProduct" :form="form"></SaleItemForm>
      </div>
    </main>
    <Footer />
  </div>
</template>

<script setup>
import { ref, onMounted, watch, computed } from "vue";
import { useRouter } from "vue-router";
import Header from "../components/Header.vue";
import Footer from "../components/Footer.vue";
import { postProduct } from "../libs/fetchProduct.js";
import { fetchBrands } from "../libs/fetchBrand.js";
import { useStateStore } from "../stores/stateStore.js";
import SaleItemForm from "../components/SaleItemForm.vue"

// router
const router = useRouter();

// all brand
const brands = ref([]);

// form data
const form = ref({
  brandName: "",
  model: "",
  price: null,
  ramGb: null,
  storageGb: null,
  screenSizeInch: null,
  color: "",
  quantity: null,
  description: "",
});

onMounted(async () => {
  try {
    brands.value = await fetchBrands();
    brands.value.sort((a, b) => a.name.localeCompare(b.name));
  } catch (err) {
    console.error("Failed to load brands", err);
  }
});

const submitting = ref(false);
const saveProduct = async (payload) => {
  try {
    const matched = brands.value.find(b => b.name === payload.brand.name);
    const sendPayload = {
      ...payload,
      brand: { id: matched?.id, name: matched?.name }
    }
    const res = await postProduct(sendPayload);
    if (res && res.ok) {
      router.push({ path: "/sale-items/list", query: { added: "true" } });
    } else {
      alert("Failed to add product.");
    }
  } catch (error) {
    console.error(error);
    alert("An error occurred.");
  }
  submitting.value = false;
};

</script>