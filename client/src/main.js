import { createApp } from 'vue';
import { createRouter, createWebHistory } from "vue-router";
import store from "./store/index.js";
import App from './App.vue';
import './index.css';

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      name: App,
      component: App
    },
  ]
})

createApp(App).use(router).use(store).mount('#app')
