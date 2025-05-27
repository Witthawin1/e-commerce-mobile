<template>
  <div class="modal-backdrop" @click="closeModal">
    <div class="modal-container" role="dialog" @click.stop>
      <div class="modal-header">
        <h3 class="modal-title">{{title || "title"}} </h3>
      </div>
      <div class="modal-content">
        <p class="itbms-message">{{ message || "message" }}</p>
      </div>
      <div class="modal-footer gap-4">
        <button v-if="displayConfirm === true"  class="itbms-confirm-button px-5 py-2 bg-red-600 text-white rounded-lg hover:bg-red-700 transition" 
            @click="confirmAction">
          <span>Confirm</span>
        </button>
           <button  class="itbms-cancel-button px-5 py-2 bg-gray-200 text-gray-800 rounded-lg hover:bg-gray-300 transition" 
           @click="closeAction">
          <span>Cancel</span>
        </button>
      </div>
    </div>
  </div>

  
</template>

<script setup>
import { ref } from 'vue';

const props = defineProps({
    displayConfirm:true,
    title:String,
    message:String,
    confirmAction : Function,
    closeAction : Function
})

</script>

<style scoped>
.modal-backdrop {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 50;
  animation: fadeIn 0.3s ease;
}

.modal-container {
  background-color: white;
  border-radius: 12px;
  width: 90%;
  max-width: 500px;
  box-shadow: 0 20px 25px -5px rgba(0, 0, 0, 0.1), 0 10px 10px -5px rgba(0, 0, 0, 0.04);
  overflow: hidden;
  animation: slideIn 0.3s ease;
  transform-origin: center;
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1.5rem;
  border-bottom: 1px solid #f3f4f6;
  background-color: #f9fafb;
}

.modal-title {
  font-size: 1.25rem;
  font-weight: 600;
  color: #111827;
  margin: 0;
}

.close-button {
  background: transparent;
  border: none;
  cursor: pointer;
  padding: 0.25rem;
  border-radius: 9999px;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: background-color 0.2s;
}

.close-button:hover {
  background-color: #f3f4f6;
}

.close-icon {
  color: #6b7280;
}

.modal-content {
  padding: 2rem 1.5rem;
  color: #4b5563;
  font-size: 1rem;
  line-height: 1.5;
}

.modal-footer {
  padding: 1.5rem;
  display: flex;
  justify-content: flex-end;
  border-top: 1px solid #f3f4f6;
  background-color: #f9fafb;
}

.primary-button {
  background-color: #4f46e5;
  color: white;
  font-weight: 500;
  padding: 0.625rem 1.25rem;
  border-radius: 0.375rem;
  border: none;
  cursor: pointer;
  transition: background-color 0.2s;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
}

.primary-button:hover {
  background-color: #4338ca;
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

@keyframes slideIn {
  from {
    transform: scale(0.95);
    opacity: 0;
  }
  to {
    transform: scale(1);
    opacity: 1;
  }
}

@media (max-width: 640px) {
  .modal-container {
    width: 95%;
  }
  
  .modal-header, .modal-content, .modal-footer {
    padding: 1rem;
  }
}
</style>
