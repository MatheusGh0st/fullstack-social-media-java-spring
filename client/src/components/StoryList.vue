<template>
  <div class="flex flex-col items-center gap-2 cursor-pointer relative">
    <img
        :src="'/noAvatar.png'"
        alt=""
        width="80"
        height="80"
        class="w-20 h-20 rounded-full ring-2 object-cover"
        @click="open"
        />
    <form @submit.prevent="add">
      <button class="text-xs bg-blue-500 p-1 rounded-md text-white">
        Send
      </button>
    </form>
    <span class="font-medium">Add a Story</span>
    <div class="absolute text-6xl text-gray-200 top-1">+</div>
  </div>
  <div class="flex flex-col items-center gap-2 cursor-pointer" v-for="storie in storiesList" :key="storie.storieId">
    <img 
      :src="storie.content || 'https://images.pexels.com/photos/2043035/pexels-photo-2043035.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1'" 
      alt=""
      width="80px"
      height="80px"
      class="w-20 h-20 rounded-full ring-2"><img />
      <span class="font-medium">{{ storie.user.name || storie.user.username }}</span>
  </div>
</template>

<script setup>
import { defineProps, watch, ref } from "vue";

const storiesList = ref([]);

const props = defineProps({
  stories: {
    type: Array,
    required: true,
  },
  userId: {
    type: String,
    required: true,
  }
});

watch(
  () => props.stories,
  (stories) => {
    storiesList.value = stories;
  },
  { immediate: true }
);
</script>
