<template>
  <div
    class="p-4 bg-gray-100 rounded-xl shadow-md flex flex-col md:flex-row md:items-center md:justify-between gap-4"
  >
    <!-- Pagination Controls -->
    <div class="join flex flex-wrap items-center gap-2" v-show="totalPages > 1">
      <!-- First -->
      <button
        @click="
          () => {
            emit('update:currentPage', 0);
            emit('clickButton');
          }
        "
        :disabled="currentPage === 0"
        class="itbms-page-first join-item btn btn-sm px-4"
      >
        First
      </button>
      <!-- Prev -->
      <button
        @click="
          () => {
            emit('update:currentPage', currentPage - 1);
            emit('clickButton');
          }
        "
        :disabled="currentPage === 0"
        class="itbms-page-prev join-item btn btn-sm px-4"
      >
        Prev
      </button>

      <button
        v-for="page in visiblePages"
        :key="page"
        @click="$emit('update:currentPage', page)"
        :class="[
          `itbms-page-${page} join-item btn btn-sm px-3`,
          currentPage == page && 'btn-primary',
        ]"
      >
        {{ page + 1 }}
      </button>

      <!-- Next -->
      <button
        @click="
          () => {
            emit('update:currentPage', currentPage + 1);
            emit('clickButton');
          }
        "
        :disabled="currentPage === totalPages - 1"
        class="itbms-page-next join-item btn btn-sm px-4"
      >
        Next
      </button>
      <button
        @click="
          () => {
            emit('update:currentPage', totalPages - 1);
            emit('clickButton');
          }
        "
        :disabled="currentPage === totalPages - 1"
        class="itbms-page-last join-item btn btn-sm px-4"
      >
        Last
      </button>
    </div>

    <!-- Page Size Selector -->
    <div class="flex items-center text-sm">
      <label class="mr-2 font-medium">Show</label>
      <select
        :value="pageSize"
        @change="$emit('update:pageSize', Number($event.target.value))"
        class="itbms-page-size px-2 py-1 rounded-md border border-gray-300 text-black bg-white shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-400"
      >
        <option :value="5">5</option>
        <option :value="10">10</option>
        <option :value="20">20</option>
      </select>
    </div>
  </div>
</template>

<script setup>
import { ref, watch, computed, onMounted } from "vue";

const props = defineProps({
  currentPage: Number,
  pageSize: Number,
  totalPages: Number,
});

onMounted(() => {
  console.log(props.totalPages);
});

const emit = defineEmits([
  "update:currentPage",
  "update:pageSize",
  "clickButton",
]);

const maxVisible = 10;
const visibleStart = ref(0);

watch(
  () => props.currentPage,
  (newPage) => {
    if (newPage < visibleStart.value) {
      visibleStart.value = newPage;
    } else if (newPage >= visibleStart.value + maxVisible) {
      visibleStart.value = newPage - maxVisible + 1;
    }
  }
);

const visiblePages = computed(() => {
  const pages = [];
  const end = Math.min(visibleStart.value + maxVisible, props.totalPages);
  for (let i = visibleStart.value; i < end; i++) {
    pages.push(i);
  }
  console.log(pages);

  return pages;
});
</script>
