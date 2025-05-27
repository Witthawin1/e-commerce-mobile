<template>
  <router-link
    @click="setImage(imageUrl)"
    :to="{
      name: 'SaleItemDetail',
      params: { id: product.id },
    }"
    class="block"
  >
    <div
      class="bg-gradient-to-br from-purple-50 to-white text-gray-900 rounded-2xl p-5 hover:-translate-y-1 transition-transform duration-300 ease-in-out w-full max-w-xs mx-auto min-h-[360px] flex flex-col justify-between shadow-xl border border-purple-100"
    >
      <!-- Image -->
      <div
        class="h-48 bg-zinc-100 rounded-xl mb-4 bg-center bg-cover border border-purple-100 shadow-inner"
        :style="{ backgroundImage: `url(${imageUrl})` }"
      ></div>

      <!-- Product Info -->
      <div class="text-sm leading-snug space-y-1">
        <p
          class="itbms-brand font-semibold tracking-wide text-purple-700"
          :class="{ 'text-gray-400': !product.brandName?.trim() }"
        >
          {{ product.brandName || "-" }}
        </p>
        <p
          class="itbms-model text-gray-700"
          :class="{ 'text-gray-400': !product.model?.trim() }"
        >
          {{ product.model || "-" }}
        </p>
        <p class="text-gray-600">
          <span
            class="itbms-ramGb"
            :class="{ 'text-gray-400': !product.ramGb?.toString().trim() }"
          >
            {{ product.ramGb || "-" }}
          </span>
          /
          <span
            class="itbms-storageGb"
            :class="{ 'text-gray-400': !product.storageGb?.toString().trim() }"
          >
            {{ product.storageGb || "-" }}
          </span>
          <span class="itbms-storageGb-unit text-gray-500 text-xs ml-1">GB</span>
        </p>
        <p
          class="Itbms-color text-gray-600"
          :class="{ 'text-gray-400': !product.color?.trim() }"
        >
          {{ product.color || "-" }}
        </p>
      </div>

      <!-- Price -->
      <p class="text-lg font-bold text-purple-800 mt-4">
        <span class="itbms-price-unit text-sm font-medium text-gray-500">Baht</span>
        <span class="itbms-price pl-1.5">
          {{ product.price?.toLocaleString?.() || "-" }}
        </span>
      </p>
    </div>
  </router-link>
</template>



<script setup>
import {useStateStore} from '../stores/stateStore.js'

const stateStore = useStateStore()
const {setImageUrl} = stateStore

const props = defineProps({
  product: {
    type: Object,
    required: true,
  },
  imageUrl: {
    type: String,
  },
});

const setImage = (imageUrl) => {
  setImageUrl(imageUrl)
}
</script>
