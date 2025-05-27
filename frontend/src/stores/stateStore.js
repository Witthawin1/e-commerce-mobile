import { defineStore , acceptHMRUpdate } from 'pinia';
import { ref , computed } from 'vue';

export const useStateStore = defineStore('imageStore', () => {
  const imageUrl = ref(JSON.parse(sessionStorage.getItem('imageUrl')) || null)

  const setImageUrl = (url) => {
    imageUrl.value = url || '../../images/huawei.png'
    sessionStorage.setItem('imageUrl',JSON.stringify(imageUrl.value))
  }

  const getImageUrl = computed(() => imageUrl.value || '../../images/huawei.png');
 
  return {
    setImageUrl,
    getImageUrl
  }
});

if(import.meta.hot){
    import.meta.hot.accept(acceptHMRUpdate(useStateStore,import.meta.hot))
}
