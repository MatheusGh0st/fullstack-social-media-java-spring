<template>
  <div class="flex flex-col gap-6">
    <ProfileCard v-if="type === 'home'" />
    <div class="p-4 bg-white rounded-lg shadow-md text-sm text text-gray-500 flex flex-col gap-2">
      <router-link
          to="/"
          class="flex items-center gap-4 p-2 rounded-lg hover:bg-slate-100">
        <img src="../../public/posts.png" alt="" width="20px" height="20px" />
        <span>My Posts</span>
      </router-link>
      <hr class="border-t-1 border-gray-50 w-36 self-center"  />
      <router-link
          to="/"
          class="flex items-center gap-4 p-2 rounded-lg hover:bg-slate-100">
        <img src="../../public/activity.png" alt="" width="20px" height="20px" />
        <span>Activity</span>
      </router-link>
      <hr class="border-t-1 border-gray-50 w-36 self-center"  />
      <router-link
          to="/"
          class="flex items-center gap-4 p-2 rounded-lg hover:bg-slate-100">
        <img src="../../public/events.png" alt="" width="20px" height="20px" />
        <span>Events</span>
      </router-link>
      <hr class="border-t-1 border-gray-50 w-36 self-center"  />
      <router-link
          to="/"
          class="flex items-center gap-4 p-2 rounded-lg hover:bg-slate-100">
        <img src="../../public/albums.png" alt="" width="20px" height="20px" />
        <span>Albums</span>
      </router-link>
      <hr class="border-t-1 border-gray-50 w-36 self-center"  />
      <router-link
          to="/"
          class="flex items-center gap-4 p-2 rounded-lg hover:bg-slate-100">
        <img src="../../public/news.png" alt="" width="20px" height="20px" />
        <span>News</span>
      </router-link>
      <hr class="border-t-1 border-gray-50 w-36 self-center"  />
      <router-link
          to="/"
          class="flex items-center gap-4 p-2 rounded-lg hover:bg-slate-100">
        <img src="../../public/courses.png" alt="" width="20px" height="20px" />
        <span>Courses</span>
      </router-link>
      <hr class="border-t-1 border-gray-50 w-36 self-center"  />
      <router-link
          to="/"
          class="flex items-center gap-4 p-2 rounded-lg hover:bg-slate-100">
        <img src="../../public/lists.png" alt="" width="20px" height="20px" />
        <span>Lists</span>
      </router-link>
      <hr class="border-t-1 border-gray-50 w-36 self-center"  />
      <router-link
          to="/"
          class="flex items-center gap-4 p-2 rounded-lg hover:bg-slate-100">
        <img src="../../public/settings.png" alt="" width="20px" height="20px" />
        <span>settings</span>
      </router-link>
      <hr class="border-t-1 border-gray-50 w-36 self-center"  />
    </div>
    <Ad size="sm" />
  </div>
</template>

<script setup>
  import { defineProps } from "vue";
  import Ad from "./Ad.vue";
  import ProfileCard from "./ProfileCard.vue";

  defineProps({
    type: {
    type: String,
    required: true,
    validator: (value) => {
      return ['home', 'profile'].includes(value);
      }
    }
  });
</script>
