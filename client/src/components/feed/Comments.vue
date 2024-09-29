<template>
  <div class="">
    <CommentsList :comments="comments" :postId="props.postId" />
  </div>
</template>

<script setup>
import { defineProps, watch, ref } from "vue";
import { useStore } from "vuex";
import CommentsList from "./CommentsList.vue";
import axios from "axios";

const store = useStore();

const APP_HOST = process.env.APP_HOST;

const comments = ref({});

const props = defineProps({
  postId: {
    type: String,
    required: true
  }
});

const getComments = async () => {
  try {
    const commentsReq = await axios.get(`${APP_HOST}/comments/${props.postId}`,
      { headers: { Authorization: `Bearer ${store.state.accessToken}` }});
    return commentsReq.data;
  } catch (err) {
    console.error(err);
  }
}

watch(() => props.postId,
  async () => {
    comments.value = await getComments();
  }, { immediate: true }
);
</script>
