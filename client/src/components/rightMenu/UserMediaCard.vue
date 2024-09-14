<template>
  <div class="p-4 bg-white rounded-lg shadow-md text-sm flex flex-col gap-4">
    <!-- TOP -->
    <div class="flex justify-between items-center font-medium">
      <span class="text-gray-500">User Media</span>
      <router-link to="/" class="text-blue-500 text-xs">See all</router-link>
    </div>
    <!-- BOTTOM -->
    <div class="flex gap-4 justify-between flex-wrap">
      <div
        class="relative w-1/5 h-24"
        v-for="media in mediasObj"
        :key="media.idPost"
      >
        <img
          :src="media.imgUrl"
          style="width: 100%; height: 100%"
          class="object-cover rounded-md"
        />
      </div>
    </div>
  </div>
</template>

<script setup>
import { defineProps, ref, watch } from "vue";
import axios from "axios";
import { useStore } from "vuex";

const APP_HOST = process.env.APP_HOST;

const store = useStore();
const mediasRes = ref({});
const mediasObj = ref([]);

const props = defineProps({
  userObj: {
    type: Object,
    required: false,
  },
});

const fetchMedia = async () => {
  const userId = props.userObj?.user?.idUser;
  // const page = 1;
  // const pageSize = 10;
  const postsWithMedia = await axios.get(`${APP_HOST}/posts/${userId}`, {
    headers: {
      Authorization: `Bearer ${store.state.accessToken}`,
    },
  });
  return postsWithMedia.data;
};

watch(
  () => props.userObj,
  async () => {
    mediasRes.value = await fetchMedia();
    mediasObj.value = mediasRes.value.content;
  }
);
</script>
