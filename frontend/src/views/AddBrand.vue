<template>
  <div class="bg-gray-50 min-h-screen flex flex-col bg-gradient-to-br from-indigo-100 via-white to-purple-100">
    <Header />
    <main class="flex-grow">

      <div class="bg-white text-gray-800 px-8 py-12 mx-auto max-w-5xl rounded-3xl shadow-2xl mt-10 space-y-8">
        <!-- Breadcrumb -->
        <nav class="flex items-center space-x-2 text-sm text-gray-500">
          <router-link to="/sale-items/list" class="itbms-item-list font-medium text-blue-600 hover:underline">
            Sale Item List
          </router-link>
          <span> > </span>
          <router-link to="/brands" class="itbms-manage-brand text-blue-500 hover:text-blue-700 transition-colors">Brand
            List</router-link>
          <span> > </span>
          <b>New Brand</b>
        </nav>

        <h1 class="text-3xl font-bold text-gray-900 mb-8 border-b border-gray-200 pb-4">Add New Brand</h1>
        <BrandForm formtype="add" @payload="saveBrand" />
      </div>
    </main>
    <Footer />
  </div>
</template>

<script setup>
import { ref, reactive, computed } from 'vue';
import { useRouter } from 'vue-router';
import Header from '../components/Header.vue';
import Footer from '../components/Footer.vue';
import { AddBrand } from '../libs/fetchBrand.js';
import BrandForm from '../components/BrandForm.vue'

const router = useRouter();




const saveBrand = async (payload) => {
  try {
    const res = await AddBrand(payload);
    if (res) {
      router.push({ path: '/brands', query: { added: 'true' } });
    } else {
      alert('Failed to add brand.');
    }
  } catch (error) {
    console.error(error);
    alert('Already has this brand name.');
  } finally {

  }
};

</script>

<style scoped></style>