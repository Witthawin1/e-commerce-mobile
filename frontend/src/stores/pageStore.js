import { ref, computed } from "vue";
import { acceptHMRUpdate, defineStore } from "pinia";

export const usePageStore = defineStore("counter", () => {
  const pageNumber = ref(sessionStorage.getItem("pageNumber") || 0);
  const pageSize = ref(sessionStorage.getItem("pageSize") || 0)
  const sortOrder = ref(sessionStorage.getItem("sortOrder") || "asc")
  const selectedBrand = ref(sessionStorage.getItem("sortOrder") || [])
  const payLoad = ref(sessionStorage.getItem("payload"))

  const setPageNumber = (pageNum) => {
    pageNumber.value = pageNum;
    sessionStorage.setItem("pageNumber", pageNum);
  };

  const setPageSize = (size) => {
    pageSize.value = size
    sessionStorage.setItem("pageSize",size)
  }

  const setPayLoad = (payload) => {
    payLoad.value = payload
    sessionStorage.setItem("payload",payload)
  }

  const getPageNumber = () => {
    return Number(pageNumber.value);
  };

    const getPageSize = () => {
    return Number(pageSize.value) || 10;
  };

      const getPayLoad = () => {
    return payLoad.value
  };

  return { setPageNumber, getPageNumber , setPageSize , getPageSize, setPayLoad , getPayLoad};
});

if (import.meta.hot) {
  import.meta.hot.accept(acceptHMRUpdate(usePageStore, import.meta.hot));
}
