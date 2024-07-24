<template>
  <div class="w-full bg-white px-4 md:px-8 lg:px-16 xl:px-32 2xl:px-64 font-Roboto">
    <Navbar />
  </div>
  <div class="bg-slate-100 px-4 md:px-8 lg:px-16 xl:px-32 2xl:px-64">
    <HomePage />
  </div>
</template>

<script>
import Navbar from "./components/Navbar.vue";
import HomePage from "./components/HomePage.vue";
export default {
  name: 'App',
  components: {
    Navbar,
    HomePage,
  }
}
</script>

<style lang="scss"></style>
