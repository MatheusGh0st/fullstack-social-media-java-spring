<template>
  <div class="p-4 bg-white rounded-lg shadow-md overflow-scroll text-xs scrollbar-hide">
    <div class="flex gap-8 w-max">
      <StoryList :stories="storiesObj" :userId="currentUserId" />
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { useStore } from "vuex";
import { onMounted, ref } from "vue";
import StoryList from "./StoryList.vue";

const store = useStore();

const currentUserId = store.state.userObj?.user?.idUser;

const APP_HOST = process.env.APP_HOST;

const storiesObj = ref([]);

const formatDate = (date) => {
  return date.toISOString().slice(0, 19).replace('T', ' ');
}

onMounted(async () => {
  if (!currentUserId) return null;
  try {
    const response = await axios.get(`${APP_HOST}/storiesEx`, {
      headers: { Authorization: `Bearer ${store.state.accessToken}` },
      params: {
          dateTime: formatDate(new Date()), // Convert to ISO string for proper format
          userId: currentUserId
      }
    });

    storiesObj.value = response.data || null;
  } catch(err) {
    console.error(err);
  }
});
</script>
