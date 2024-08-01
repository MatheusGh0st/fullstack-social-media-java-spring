<template>
  <div class="w-full bg-white px-4 md:px-8 lg:px-16 xl:px-32 2xl:px-64 font-Roboto">
    <Navbar />
  </div>
  <div class="bg-slate-100 px-4 md:px-8 lg:px-16 xl:px-32 2xl:px-64">
    <div v-if="isHomePage">
      <HomePage />
    </div>
    <div v-if="isProfilePage">
      <Profile />
    </div>
  </div>
</template>

<script setup>
import Navbar from "./components/Navbar.vue";
import HomePage from "./components/HomePage.vue";
import Profile from "./components/Profile.vue";
import { useRoute } from "vue-router";
import { computed } from "vue";

const route = useRoute();

const isHomePage = computed(() => route.path === '/');
const isProfilePage = computed(() => route.path === '/profile');
</script>
