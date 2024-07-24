<template>
  <div class="md:hidden">
    <div class="flex flex-col gap-[4.5px] cursor-pointer" @click="show = !show">
      <div class="w-6 h-1 bg-blue-500 rounded-sm origin-left ease-in-out duration-500" :class="{ 'rotate-45': show }"></div>
      <div class="w-6 h-1 bg-blue-500 rounded-sm ease-in-out duration-500" :class="{ 'opacity-0': show}"/>
        <div class="w-6 h-1 bg-blue-500 rounded-sm origin-left ease-in-out duration-500" :class="{ '-rotate-45': show }" />
        </div>
        <div class="absolute left-0 top-24 w-full h-[calc(100vh-96px)] bg-white flex flex-col items-center justify-center" v-show="show">
          <router-link to="/">Home</router-link>
          <router-link to="/">Friends</router-link>
          <router-link to="/">Groups</router-link>
          <router-link to="/">Stories</router-link>
          <router-link to="/">Login</router-link>
        </div>
      </div>
</template>

<script setup>
import { ref } from "vue";

const show = ref(false);
</script>
