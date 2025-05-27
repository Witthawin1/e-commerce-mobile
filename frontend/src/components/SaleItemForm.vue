<template>
  <div>
    <form @submit.prevent="handleSubmit" class="flex flex-col md:flex-row gap-12">
      <!-- Product Image Preview -->
      <div class="md:w-1/2 w-full">
        <div
          class="bg-gray-50 rounded-xl border border-gray-200 shadow-sm p-3 transition-all duration-300 hover:shadow-md">
          <img :src="getImageUrl" class="w-full rounded-lg object-cover aspect-square" />
        </div>
      </div>
 
      <!-- Product Info Form -->
      <div class="md:w-1/2 w-full text-base itbms-row">
        <div class="bg-blue-50 rounded-xl p-6 mb-6">
          <h2 class="text-xl font-semibold text-gray-800 mb-1">
            Product Information
          </h2>
          <p class="text-gray-500 text-sm">
            Fill in the details below to add a new product
          </p>
        </div>
 
        <div class="space-y-5">
          <div>
            <div class="flex justify-between mb-1">
              <label class="text-gray-700 font-medium">Brand</label>
              <span class="text-red-500 text-sm font-medium">Required*</span>
            </div>
            <select @blur="trim('brandName')" v-model="form.brandName"
              class="itbms-brand w-full border border-gray-300 p-3 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 outline-none transition-all duration-200"
              required :ref="el => inputRefs[0] = el" @keydown.enter.prevent="handleEnter(0)">
              <option v-if="formtype !== 'edit'" value="" selected>   
              </option>
              <option v-for="(brand, index) in brands" :key="brand.id || index" :value="brand.name">
                {{ brand?.name }}
              </option>
            </select>
            <p v-if="errors.brandName" class="itbms-message text-red-500 text-sm mt-1">{{ errors.brandName }}</p>
          </div>
 
          <div>
            <label class="text-gray-700 font-medium block mb-1">Model <span class="text-red-500">*</span></label>
            <input @blur="trim('model')" v-model="form.model" type="text" placeholder="Enter model name"
              class="itbms-model w-full border border-gray-300 p-3 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 outline-none transition-all duration-200"
              maxlength="80" :ref="el => inputRefs[1] = el" @keydown.enter.prevent="handleEnter(1)" />
            <p v-if="errors.model" class="itbms-message text-red-500 text-sm mt-1">{{ errors.model }}</p>
          </div>
 
          <!-- Price + Quantity -->
          <div class="grid grid-cols-2 gap-5">
            <div>
              <label class="text-gray-700 font-medium block mb-1">Price <span class="text-red-500">*</span></label>
              <div class="relative">
                <input @blur="trim('price')" v-model.number="form.price" type="number" placeholder="0"
                  class="itbms-price w-full border border-gray-300 p-3 pl-16 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 outline-none transition-all duration-200"
                  :ref="el => inputRefs[2] = el" @keydown.enter.prevent="handleEnter(2)" />
                <div
                  class="absolute inset-y-0 left-0 flex items-center px-3 pointer-events-none bg-gray-100 rounded-l-lg border-r border-gray-300 text-gray-700 font-medium">
                  Baht
                </div>
              </div>
              <p v-if="errors.price" class="itbms-message text-red-500 text-sm mt-1">{{ errors.price }}</p>
            </div>
          </div>
 
          <!-- Description -->
          <div>
            <label class="text-gray-700 font-medium block mb-1">Description <span class="text-red-500">*</span></label>
            <textarea v-model="form.description" placeholder="Enter product description"
              class="itbms-description w-full border border-gray-300 p-3 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 outline-none transition-all duration-200"
              rows="3" @blur="trim('description')" :ref="el => inputRefs[3] = el"
              @keydown.enter.prevent="handleEnter(3)"></textarea>
            <p v-if="errors.description" class="itbms-message text-red-500 text-sm mt-1">{{ errors.description }}</p>
          </div>
 
          <!-- Optional Fields Section -->
          <div class="mt-8">
            <h3 class="text-gray-700 font-medium pb-2 border-b border-gray-200 mb-4">
              Additional Information (Optional)
            </h3>
            <div class="grid grid-cols-2 gap-5">
              <div>
                <label class="text-gray-600 text-sm block mb-1">RAM</label>
                <div class="relative">
                  <input @blur="trim('ramGb')" v-model="form.ramGb" type="number" placeholder="0"
                    class="itbms-ramGb w-full border border-gray-300 p-3 pl-12 rounded-lg focus:ring-1 focus:ring-blue-400 focus:border-blue-400 outline-none transition-all duration-200"
                    :ref="el => inputRefs[4] = el" @keydown.enter.prevent="handleEnter(4)" />
                  <div
                    class="absolute inset-y-0 left-0 flex items-center px-3 pointer-events-none text-gray-500 font-medium">
                    GB
                  </div>
                </div>
                <p v-if="errors.ramGb" class="itbms-message text-red-500 text-sm mt-1">{{ errors.ramGb }}</p>
              </div>
              <div>
                <label class="text-gray-600 text-sm block mb-1">Screen Size</label>
                <div class="relative">
                  <input @blur="trim('screenSizeInch')" v-model="form.screenSizeInch" type="number" step="0.01"
                    placeholder="0.0"
                    class="itbms-screenSizeInch w-full border border-gray-300 p-3 pl-16 rounded-lg focus:ring-1 focus:ring-blue-400 focus:border-blue-400 outline-none transition-all duration-200"
                    :ref="el => inputRefs[5] = el" @keydown.enter.prevent="handleEnter(5)" />
                  <div
                    class="absolute inset-y-0 left-0 flex items-center px-2 pointer-events-none text-gray-500 font-medium">
                    inches
                  </div>
                </div>
                <p v-if="errors.screenSizeInch" class="itbms-message text-red-500 text-sm mt-1">{{ errors.screenSizeInch }}</p>
              </div>
              <div>
                <label class="text-gray-600 text-sm block mb-1">Storage</label>
                <div class="relative">
                  <input @blur="trim('storageGb')" v-model="form.storageGb" type="number" placeholder="0"
                    class="itbms-storageGb w-full border border-gray-300 p-3 pl-12 rounded-lg focus:ring-1 focus:ring-blue-400 focus:border-blue-400 outline-none transition-all duration-200"
                    :ref="el = inputRefs[6] = el" @keydown.enter.prevent="handleEnter(6)" />
                  <div
                    class="absolute inset-y-0 left-0 flex items-center px-3 pointer-events-none text-gray-500 font-medium">
                    GB
                  </div>
                </div>
                <p v-if="errors.storageGb" class="itbms-message text-red-500 text-sm mt-1">{{ errors.storageGb }}</p>
              </div>
              <div>
                <label class="text-gray-600 text-sm block mb-1">Color</label>
                <input @blur="trim('color')" v-model="form.color" type="text" placeholder="Enter color"
                  class="itbms-color w-full border border-gray-300 p-3 rounded-lg focus:ring-1 focus:ring-blue-400 focus:border-blue-400 outline-none transition-all duration-200"
                  :ref="el => inputRefs[7] = el" @keydown.enter.prevent="handleEnter(7)" />
                <p v-if="errors.color" class="itbms-message text-red-500 text-sm mt-1">{{ errors.color }}</p>
              </div>
 
              <div>
                <label class="text-gray-600 text-sm block mb-1">Quantity</label>
 
                <input @blur="trim('quantity')" v-model="form.quantity" type="number" placeholder="1"
                  class="itbms-quantity w-full  border border-gray-300 p-3 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 outline-none transition-all duration-200"
                  :ref="el => inputRefs[8] = el" @keydown.enter.prevent="handleEnter(8)" />
                <p v-if="errors.quantity" class="itbms-message text-red-500 text-sm mt-1">{{ errors.quantity }}</p>
              </div>
            </div>
          </div>
 
          <!-- Form Actions -->
          <div class="flex items-center gap-4 pt-6 mt-8 border-t border-gray-200">
            <button ref="submitButton" type="submit" :disabled="!isFormValid || submitting || !isFormModified" :class="[
              'itbms-save-button px-6 py-3 rounded-lg font-medium transition-all duration-200',
              isFormValid && !submitting
                ? 'bg-blue-600 text-white hover:bg-blue-700 shadow-md hover:shadow-lg disable'
                : 'bg-gray-100 text-gray-400 border border-gray-300 cursor-not-allowed',
              !isFormModified ? 'disabled:opacity-50' : ''
            ]">
              Save
            </button>
            <button type="button" @click="router.push('/sale-items')"
              class="itbms-cancel-button px-6 py-3 bg-white border border-gray-300 text-gray-700 rounded-lg hover:bg-gray-50 hover:text-red-600 hover:border-red-200 transition-all duration-200">
              Cancel
            </button>
          </div>
        </div>
      </div>
    </form>
  </div>
</template>
 
<script setup>
import { ref, reactive, onMounted, watch, computed } from "vue";
import { useRouter } from "vue-router";
import { useStateStore } from "../stores/stateStore.js";
import { fetchBrands } from "../libs/fetchBrand.js";
 
const stateStore = useStateStore();
const { getImageUrl } = stateStore;
 
const inputRefs = ref([]);
const router = useRouter();
const submitting = ref(false);
const originData = ref({});
 
const props = defineProps({
  formtype: String,
  form: Object,
});
 
const form = ref({ ...props.form });
const brands = ref([]);
 
const errors = reactive({
  brandName: '',
  model: '',
  description: '',
  price: '',
  ramGb: '',
  screenSizeInch: '',
  storageGb: '',
  quantity: '',
  color: '',
});
 
const handleEnter = (index) => {
  const nextInput = inputRefs.value[index + 1];
  if (nextInput) nextInput.focus();
};
 
onMounted(async () => {
  try {
    brands.value = await fetchBrands();
    brands.value.sort((a, b) => a.name.localeCompare(b.name));
 
    if (brands.value.length > 0 && !form.value.brandId) {
      form.value.brandId = brands.value[0].id;
    }
  } catch (err) {
    console.error("Failed to load brands", err);
  }
 
  if (props.formtype === "edit") {
    Object.assign(form.value, props.form);
    originData.value = JSON.parse(JSON.stringify(props.form));
  }
});
 
watch(
  () => form.value.brandId,
  (selectedId) => {
    const selected = brands.value.find((b) => b.id === selectedId);
    form.value.brandName = selected ? selected.name : '';
  }
);
 
const validateField = (key) => {
  const value = form.value[key];
  switch (key) {
    case 'brandName':
      errors.brandName = !value || value.trim() === "" ? 'Brand must be selected.' : '';
      break;
    case 'model':
      const trimmedModel = value?.trim() || '';
      errors.model = trimmedModel.length === 0 || trimmedModel.length > 60 ? 'Model must be 1-60 characters long.' : '';
      break;
    case 'description':
      const trimmedDescription = value?.trim() || '';
      errors.description = trimmedDescription.length === 0 || trimmedDescription.length > 65535  ? "Description must be 1-65,535 characters long." : '';

      break;
    case 'price':
      errors.price = value === '' || isNaN(Number(value)) || Number(value) < 0 ? "Price must be non-negative integer." : '';
      break;
    case 'ramGb':
      errors.ramGb = value !== '' && value !== null && value !== undefined && Number(value) <= 0 ? "RAM size must be positive integer or not specified." : '';
      break;
    case 'screenSizeInch':
      const isValidDecimal = (val) => {
        if (val === '' || val === null || val === undefined) return true;
        const num = Number(val);
        if (isNaN(num) || num <= 0) return false;
        const decimalPart = val.toString().split('.')[1];
        return !decimalPart || decimalPart.length <= 2;
      };
      errors.screenSizeInch = value !== '' && (!isValidDecimal(value)) ? "Screen size must be positive number with at most 2 decimal points or not specified." : '';
      break;
    case 'storageGb':
      errors.storageGb = value !== '' && value !== null && value !== undefined && Number(value) <= 0 ? "Storage size must be positive integer or not specified." : '';
      break;
    case 'quantity':
      errors.quantity = value !== '' && Number(value) < 0 ? 'Quantity must be non-negative integer.' : '';
      break;
    case 'color':
      const trimmedColor = value?.trim() || '';
      errors.color = trimmedColor.length > 40 ? "Color must be 1-40 characters long or not specified." : '';
      break;
  }
};
 
const validateForm = () => {
  ['brandName', 'model', 'description', 'price', 'ramGb', 'screenSizeInch', 'storageGb', 'quantity', 'color'].forEach(validateField);
  return !Object.values(errors).some(e => e);
};
 
const trim = (key) => {
  if (typeof form.value[key] === 'string') {
    form.value[key] = form.value[key].trim();
  }
  validateField(key)
};
 
const payload = computed(() => ({
  model: form.value.model.trim(),
  description: form.value.description.trim(),
  price: Number(form.value.price),
  quantity: form.value.quantity,
  color: form.value.color?.trim() || null,
  ramGb: form.value.ramGb ? Number(form.value.ramGb) : null,
  screenSizeInch: form.value.screenSizeInch ? Number(form.value.screenSizeInch) : null,
  storageGb: form.value.storageGb ? Number(form.value.storageGb) : undefined,
  brand: {
    id: form.value.brandId,
    name: form.value.brandName,
  }
}));
 
const isFormValid = computed(() => {
  return (
    form.value.brandName &&
    form.value.model?.trim().length > 0 &&
    form.value.description?.trim().length > 0 &&
    form.value.price >= 0 &&
    !Object.values(errors).some((e) => e)
  );
});
 
const isFormModified = computed(() => JSON.stringify(form.value) !== JSON.stringify(originData.value));
 
const emits = defineEmits(["payload"]);
 
const handleSubmit = () => {
  if (validateForm()) {
    emits("payload", payload.value);
  }
};
</script>
 
<style scoped></style>