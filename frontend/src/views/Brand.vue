<template>
  <div class="bg-gradient-to-br from-indigo-100 via-white to-purple-100 min-h-screen font-sans flex flex-col text-black">
    <!-- Header -->
    <Header></Header>
    <main class="flex-grow">
    <div class="bg-white border-b border-gray-200 pt-8 pb-6 px-6 flex justify-between items-center">
      <div class="flex items-center space-x-4">
        <router-link to="/sale-items/list" class="text-blue-500 hover:text-blue-700 transition-colors">Sale Item
          List</router-link>
      </div>
      <button class="itbms-add-button bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded transition-colors">
        <router-link to="/brands/add"> Add Brand </router-link>
      </button>
    </div>

    <main class="container mx-auto py-6 px-4">
      <!-- Alerts -->
      <div class="max-w-4xl mx-auto mb-6">
        <Alert v-if="added" :message="'The brand has been added.'" :state="'created'">
        </Alert>

        <Alert v-if="deleted" :message="'The brand has been deleted.'" :state="'error'">
        </Alert>

        <Alert v-if="updated" :message="'The brand has been updated.'" :state="'updated'">
        </Alert>
      </div>

      <!-- Empty State -->
      <div v-if="brands.length === 0" class="flex flex-col items-center justify-center text-center text-gray-500 py-24">
        <div class="text-6xl text-gray-400 mb-4">🏷️</div>
        <h3 class="text-2xl font-semibold mb-2">No Brands Found</h3>
        <p class="text-gray-400 max-w-md">
          Start by adding your first brand using the button above.
        </p>
      </div>

      <!-- Brands Table -->
      <div v-else class="max-w-7xl mx-auto bg-white border border-gray-200 rounded-md shadow-sm">
        <div class="overflow-x-auto">
          <table class="min-w-full bg-white">
            <thead>
              <tr class="bg-gray-50 border-b border-gray-200">
                <th class="py-3 px-4 text-left">ID</th>
                <th class="py-3 px-4 text-left">Name</th>
                <th class="py-3 pl-6 text-left">Action</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="brand in brands" :key="brand.id" class="itbms-row border-b border-gray-200 relative">
                <td class="py-3 px-6 itbms-id">{{ brand.id || "-" }}</td>
                <td class="py-3 px-6 itbms-name">{{ brand.name }}</td>
                <td class="py-3 pl-6 inline-flex">
                  <div class="inline-flex"></div>
                  <router-link :to="`/brands/${brand.id}/edit`"
                    class="itbms-edit-button bg-blue-700 text-white px-3 py-1 rounded-sm mr-2 hover:bg-gray-300 transition-colors">
                    Edit
                  </router-link>
                  <button
                    class="itbms-delete-button bg-red-700 text-white px-3 py-1 rounded-sm hover:bg-gray-300 transition-colors relative"
                    @click="displayModal(brand.id)">
                    Delete
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </main>

    <!-- Delete modal confirm-->
    <Modal v-if="showConfirmModal" :title="'Confirm Deletion'"
      :message="`Do you want to delete ${chooseBrand?.name} brand?`" :confirmAction="confirmDeleteProduct"
      :displayConfirm="true" :closeAction="() => {
          brandNotExist = false;
          updateBrand();
          showConfirmModal = false;
        }
        ">
    </Modal>

    <Modal v-if="brandNotExist === true" :title="'Brand not exist'"
      :message="`An error has occurred, the brand does not exist.`" :displayConfirm="false" :closeAction="() => {
          brandNotExist = false;
          updateBrand();
        }
        ">
    </Modal>

    <Modal v-if="showHaveItemInBrandModal === true" :title="'Have sale item in brand'"
      :message="`Delete ${chooseBrand.name} is not allowed. There are sale items with ${chooseBrand.name} brand.`"
      :displayConfirm="false" :closeAction="() => {
          showHaveItemInBrandModal = false;
        }
        ">
    </Modal>
    </main>
    <Footer></Footer>
  </div>
</template>

<script setup>
import { computed, onMounted, ref } from "vue";
import { useRoute } from "vue-router";
import {
  fetchBrands,
  deleteBrandById,
  fetchBrandById,
} from "../libs/fetchBrand.js";
import Footer from "../components/Footer.vue";
import Header from "../components/Header.vue";
import Modal from "../components/Modal.vue";
import Alert from "../components/Alert.vue";
// brands
const brands = ref([]);

// router
const route = useRoute();
const added = computed(() => route.query.added === "true");
const deleted = ref(false);
const updated = computed(() => route.query.updated === "true");

// fetch brands
onMounted(async () => {
  try {
    brands.value = await fetchBrands();
    console.log(brands.value);
  } catch (err) {
    console.error("Failed to load brands:", err);
  }
});

//delete brand confirm modal
const showConfirmModal = ref(false);

//have item in brand modal
const showHaveItemInBrandModal = ref(false);

const brandIdToDelete = ref(null);
const chooseBrand = ref(null);
const checkItdeleted = ref(null);

const brandNotExist = ref(false);

const updateBrand = async () => {
  brandNotExist.value = false;
  brands.value = await fetchBrands();
};

const displayModal = async (brandId) => {
  try {
    brandIdToDelete.value = brandId;
    const brand = brands.value.find((brand) => brand.id == brandId);
    const detail = await fetchBrandById(brandId);
    brand.noOfSaleItems = detail.noOfSaleItems;
    chooseBrand.value = brand;

    if (!brand) {
      console.error("ไม่พบข้อมูลแบรนด์ใน brandsDetail");
      return;
    }

    if (brand.noOfSaleItems > 0) {
      showHaveItemInBrandModal.value = true;
    } else {
      showConfirmModal.value = true;
    }
  } catch (error) {
    console.log(error.message);
  }
};

const confirmDeleteProduct = async () => {
  try {
    const detail = await fetchBrandById(brandIdToDelete.value);
    if (detail.error) {
      brandNotExist.value = true;
      return;
    }
    await deleteBrandById(brandIdToDelete.value);
    deleted.value = true;
    brands.value = await fetchBrands();
    showConfirmModal.value = false;
  } catch (error) {
    showConfirmModal.value = false;
    brandNotExist.value = true;
    deleted.value = false;
  }
};
</script>
