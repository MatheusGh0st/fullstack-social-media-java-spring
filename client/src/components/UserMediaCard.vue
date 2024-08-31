<template>
  <div class="p-4 bg-white rounded-lg shadow-md text-sm flex flex-col gap-4">
    <!-- TOP -->
    <div class="flex justify-between items-center font-medium">
      <span class="text-gray-500">User Media</span>
      <router-link to="/" class="text-blue-500 text-xs">See all</router-link>
    </div>
    <!-- BOTTOM -->
    <div class="flex gap-4 justify-between flex-wrap">
      <div class="relative w-1/5 h-24">
        <img
          src="https://images.pexels.com/photos/3421812/pexels-photo-3421812.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
          style="width: 100%; height: 100%"
          class="object-cover rounded-md"
        />
      </div>
      <div class="relative w-1/5 h-24">
        <img
          src="https://images.pexels.com/photos/3421812/pexels-photo-3421812.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
          style="width: 100%; height: 100%"
          class="object-cover rounded-md"
        />
      </div>
      <div class="relative w-1/5 h-24">
        <img
          src="https://images.pexels.com/photos/3421812/pexels-photo-3421812.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
          style="width: 100%; height: 100%"
          class="object-cover rounded-md"
        />
      </div>
      <div class="relative w-1/5 h-24">
        <img
          src="https://images.pexels.com/photos/3421812/pexels-photo-3421812.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
          style="width: 100%; height: 100%"
          class="object-cover rounded-md"
        />
      </div>
      <div class="relative w-1/5 h-24">
        <img
          src="https://images.pexels.com/photos/3421812/pexels-photo-3421812.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
          style="width: 100%; height: 100%"
          class="object-cover rounded-md"
        />
      </div>
      <div class="relative w-1/5 h-24">
        <img
          src="https://images.pexels.com/photos/3421812/pexels-photo-3421812.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
          style="width: 100%; height: 100%"
          class="object-cover rounded-md"
        />
      </div>
      <div class="relative w-1/5 h-24">
        <img
          src="https://images.pexels.com/photos/3421812/pexels-photo-3421812.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
          style="width: 100%; height: 100%"
          class="object-cover rounded-md"
        />
      </div>
      <div class="relative w-1/5 h-24">
        <img
          src="https://images.pexels.com/photos/3421812/pexels-photo-3421812.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
          style="width: 100%; height: 100%"
          class="object-cover rounded-md"
        />
      </div>
    </div>
  </div>
</template>

<script setup>
import { defineProps } from "vue";

defineProps({
  userObj: {
    type: Object,
    required: false,
  },
});
</script>
