import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import AddItem from "../views/AddItem.vue"
import SaleItems from "../views/SaleItems.vue"
import SaleItemDetail from "../views/SaleItemDetail.vue"
import EditSaleItemDetail from "../views/EditSaleItemDetail.vue"
import EditBrandDetail from "../views/EditBrandDetail.vue";
import Brand from "../views/Brand.vue";
import SaleItemList from "../views/SaleItemList.vue";
import AddBrand from "../views/AddBrand.vue";
import Modal from "@/components/Modal.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/sale-items",
      name: "sale-items",
      component: SaleItems
    },
    {
      path: "/sale-items/add",
      name: "AddItem",
      component: AddItem
    },
    {
      path: "/sale-items/:id",
      name: "SaleItemDetail",
      component: SaleItemDetail,
    },
    {
      path: "/sale-items/:id/edit",
      name: "EditSaleItemDetail",
      component: EditSaleItemDetail,
    },
    {
      path: "/brands/:id/edit",
      name: "EditBrandDetail",
      component: EditBrandDetail,
    },
    {
      path: "/brands",
      name: "Brand",
      component: Brand,
    }
    ,
    {
      path: '/sale-items/list',
      name: 'SaleItemList',
      component: SaleItemList
    },
    {
      path: '/brands/add',
      name: 'AddBrand',
      component: AddBrand
    },
    {
      path: '/modal',
      name: 'Modal',
      component: Modal
    }
  ],
});

export default router;
