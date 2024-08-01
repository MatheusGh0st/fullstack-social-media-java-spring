<template>
  <div class="p-4 bg-white rounded-lg shadow-md text-sm flex flex-col gap-4">
    <!-- TOP -->
    <div class="flex justify-between items-center font-medium">
      <span class="text-gray-500">User Information</span>
      <router-link to="/" class="text-blue-500 text-xs">See all</router-link>
    </div>
    <!-- BOTTOM -->
    <div class="flex flex-col gap-4 text-gray-500">
      <div class="flex items-center gap-2">
        <span class="text-xl text-black">Lloyd Flemegin</span>
        <span class="text-sm">@llsscod</span>
      </div>
      <p>
      Lorem ipsum dolor sit amet, qui minim labore adipisicing 
      minim sint cillum sint consectetur cupidatat.
      </p>
      <div class="flex items-center gap-2">
        <img src="../../public/map.png" width="16px" height="16px" alt="" />
        <span>Living in <b>Denver</b></span>
      </div>
      <div class="flex items-center gap-2">
        <img src="../../public/work.png" width="16px" height="16px" />
        <span>Works at <b>Apple Inc.</b></span>
      </div>
      <div class="flex items-center justify-between">
        <div class="flex gap-1 items-center">
          <img src="../../public/link.png" alt="" width="16px" height="16px" />
          <router-link to="/" class="text-blue-500 font-medium">Ghost.dev</router-link>
        </div>
      </div>
      <div class="flex gap-1 items-center">
        <img src="../../public/date.png" alt="" width="16px" height="16px" />
        <span>Joined November 2024</span>
      </div>
    </div>
    <button class="bg-blue-500 text-white text-sm rounded-md">Follow</button>
    <span class="text-red-400 self-end text-xs cursor-pointer">Block User</span>
  </div>
</template>

<script setup>
  import { defineProps } from "vue";
  defineProps({
  userId: {
  type: String,
  required: false,
  },
  })
</script>
