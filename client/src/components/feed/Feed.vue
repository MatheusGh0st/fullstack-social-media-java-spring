<template>
  <div class="p-4 bg-white shadow-md rounded-lg flex flex-col gap-12">
    <Post v-for="post in listPosts" :key="post.idPost" :post="post" />
  </div>
</template>

<script setup>
import { defineProps, watch, ref } from "vue";
import { useStore } from "vuex";
import axios from "axios";
import Post from "./Post.vue";

const APP_HOST = process.env.APP_HOST;

const store = useStore();
let listPosts = ref([]);

const props = defineProps({
  userObj: {
  type: Object,
  required: true,
  }
});

const getPosts = async () => {
  if (props.userObj == null) {
    return null;
  }

  try {
    const response = await axios.get(`${APP_HOST}/posts/username/${props.userObj?.user?.username}`,
    { headers: { Authorization: `Bearer ${store.state.accessToken}`}
    });
    return response.data?.content || [];
  } catch (error) {
    console.error("Error fetching posts ", error);
    return [];
  }
}

watch(
  () => props.userObj,
  async () => {
    listPosts.value = await getPosts();
  }, { immediate: true }
);
</script>
