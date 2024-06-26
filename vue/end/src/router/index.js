import { createRouter, createWebHistory } from 'vue-router'
import Layout from "@/layout/Layout.vue"

const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: "/login",
    children: [
      {
        path: 'index',
        name: 'Index',
        component: () => import("@/views/Index")
      },
      {
        path: 'userAssign',
        name: 'userAssign',
        component: () => import("@/views/UserAssign.vue")
      },
      {
        path: 'shopAdmin',
        name: 'shopAdmin',
        component: () => import("@/views/shopAdmin.vue")
      },
      {
        path: 'adminAssign',
        name: 'adminAssign',
        component: () => import("@/views/adminAssign.vue")
      },
      {
        path: 'userinfo',
        name: 'UserInfo',
        component: () => import("@/views/UserInfo")
      },
      {
        path: 'collection',
        name: 'collection',
        component: () => import("@/views/Collection")
      },

      {
        path: 'collections',
        name: 'collections',
        component: () => import("@/views/Collections")
      },
      {
        path: 'feedback',
        name: 'feedback',
        component: () => import("@/views/Feedback")
      },
      {
        path: 'address',
        name: 'Address',
        component: () => import("@/views/Address")
      },

      {
        path: 'wantbuyInfor',
        name: 'wantbuyInfor',
        component: () => import("@/views/WantbuyInfor")
      },
      {
        path: 'origin',
        name: 'origin',
        component: () => import("@/views/Origin")
      },
      {
        path: 'originInfo/:id',
        name: 'originInfo',
        component: () => import("@/views/OriginInfo")
      },
      {
        path: 'order',
        name: 'order',
        component: () => import("@/views/Order")
      },
      {
        path: 'product',
        name: 'Product',
        component: () => import("@/views/Product")
      },
      {
        path: 'template',
        name: 'template',
        component: () => import("@/views/Template")
      },
      {
        path: 'useraddr',
        name: 'Useraddr',
        component: () => import("@/views/Useraddr")
      },
      {
        path: 'record',
        name: 'Record',
        component: () => import("@/views/Record")
      },
      {
        path: 'comment',
        name: 'Comment',
        component: () => import("@/views/Comment")
      },
      {
        path: 'refund',
        name: 'Refund',
        component: () => import("@/views/Refund")
      },
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import("@/views/Login")
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import("@/views/Register")
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
