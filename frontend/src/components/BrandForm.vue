<template>
  <div>
    <form @submit.prevent="handleSubmit" class="space-y-6">
      <!-- Name -->
      <div class="space-y-1">
        <label for="name" class="block text-sm font-medium text-gray-700">
          Name <span class="text-red-500">*</span>
        </label>
        <input
          id="name"
          v-model="form.name"
          type="text"
          placeholder="Brand Name"
          required
          class="itbms-name w-full border border-gray-300 rounded-xl p-3 focus:outline-none focus:ring-2 focus:ring-blue-300"
          :ref="(el) => (inputRefs[0] = el)"
          @keydown.enter.prevent="handleEnter(0)"
          @blur="trimField('name')"
        />
        <p v-if="errors.name" class="itbms-message text-red-500 text-sm mt-1">{{ errors.name }}</p>
      </div>
 
      <!-- Other Fields -->
      <div class="grid grid-cols-2 gap-6">
        <div>
          <label
            for="websiteUrl"
            class="block text-sm font-medium mb-1 text-gray-700"
            >Website URL</label
          >
          <input
            id="websiteUrl"
            v-model="form.websiteUrl"
            type="url"
            placeholder="https://example.com"
            class="itbms-websiteUrl w-full border border-gray-300 rounded-xl p-2 focus:outline-none focus:ring-2 focus:ring-blue-300"
            :ref="(el) => (inputRefs[1] = el)"
            @keydown.enter.prevent="handleEnter(1)"
            @blur="trimField('websiteUrl')"
          />
          <p v-if="errors.websiteUrl" class="itbms-message text-red-500 text-sm mt-1">{{ errors.websiteUrl }}</p>
        </div>
        <div>
          <label
            for="countryOfOrigin"
            class="block text-sm font-medium mb-1 text-gray-700"
            >Country of Origin</label
          >
          <input
            id="countryOfOrigin"
            v-model="form.countryOfOrigin"
            type="text"
            placeholder="e.g., Japan"
            class="itbms-countryOfOrigin w-full border border-gray-300 rounded-xl p-2 focus:outline-none focus:ring-2 focus:ring-blue-300"
            :ref="(el) => (inputRefs[2] = el)"
            @keydown.enter.prevent="handleEnter(2)"
            @blur="trimField('countryOfOrigin')"
          />
          <p v-if="errors.countryOfOrigin" class="itbms-message text-red-500 text-sm mt-1">{{ errors.countryOfOrigin }}</p>
        </div>
        <div>
          <label
            for="isActive"
            class="block text-sm font-medium mb-1 text-gray-700"
            >Active Status</label
          >
          <input
            type="checkbox"
            class="itbms-isActive toggle"
            v-model="form.isActive"
            @click="form.isActive = !form.isActive"
          />
        </div>
      </div>
 
      <!-- Buttons -->
      <div class="flex gap-4 pt-4">
        <button
          type="submit"
          class="itbms-save-button flex-1 py-3 rounded-xl bg-blue-600 text-white font-medium hover:bg-blue-700 transition"
          :disabled="(!isFormValid || submitting) || !isFormModified"
          :class="[
            !isFormValid || submitting ? 'opacity-50 cursor-not-allowed' : '',
            !isFormModified ? 'disabled:opacity-50' : '',
          ]"
        >
          Save
        </button>
        <button
          type="button"
          @click="cancel"
          class="itbms-cancel-button flex-1 py-3 rounded-xl border border-red-400 text-red-600 font-medium hover:bg-red-50 transition"
        >
          Cancel
        </button>
      </div>
    </form>
  </div>
</template>
 
<script setup>
import { ref, reactive, computed, onMounted } from "vue";
import { useRouter } from "vue-router";
 
const emits = defineEmits(["payload"]);
const router = useRouter();
 
const inputRefs = ref([]);
const submitting = ref(false);
 
const props = defineProps({
  formtype: String,
  form: Object,
  originData: Object,
});
 
const form = reactive({
  name: "",
  websiteUrl: "",
  countryOfOrigin: "",
  isActive: true,
});
 
const errors = reactive({
  name: '',
  websiteUrl: '',
  countryOfOrigin: '',
});
 
const payload = computed(() => ({
  name: form.name.trim(),
  websiteUrl: form.websiteUrl.trim() || undefined,
  countryOfOrigin: form.countryOfOrigin.trim() || undefined,
  isActive: form.isActive,
}));
 
const trimField = (key) => {
  if (typeof form[key] === "string") {
    form[key] = form[key].trim()
    validateField(key)
  }
};
 
const handleEnter = (index) => {
  const nextInput = inputRefs.value[index + 1];
  if (nextInput) nextInput.focus();
};
 
const cancel = () => {
  router.back();
};
 
const isFormValid = computed(() => {
  return (
    form.name.trim().length > 0 &&
    !errors.name &&
    !errors.websiteUrl &&
    !errors.countryOfOrigin
  );
});
 
const handleSubmit = () => {
  emits("payload", payload.value);
};
 
//for edit brand
const originData = ref({});
const isFormModified = computed(
  () => JSON.stringify(form) !== JSON.stringify(originData.value)
);
 
if (props.formtype === "edit") {
  onMounted(() => {
    Object.assign(form, props?.form);
    originData.value = JSON.parse(JSON.stringify(props.form)); // Save original data for comparison
  });
}
 
const validateField = (key) => {
  const value = form[key].trim();
 
  switch (key) {
    case 'name':
      errors.name = value.length < 1 || value.length > 30
        ? 'Brand name must be 1–30 characters long.'
        : '';
      break;
 
    case 'websiteUrl':
      if (value) {
        try {
          new URL(value);
          errors.websiteUrl = '';
        } catch (e) {
          errors.websiteUrl = 'Brand URL must be a valid URL or not specified.';
        }
      } else {
        errors.websiteUrl = '';
      }
      break;
 
    case 'countryOfOrigin':
      errors.countryOfOrigin = value.length > 80
        ? 'Brand country of origin must be 1–80 characters long or not specified.'
        : '';
      break;
  }
};
</script>
 