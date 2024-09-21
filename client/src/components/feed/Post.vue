<template>
  <div class="flex flex-col gap-4">
    <!-- USER -->
    <div class="flex items-center justify-between">
      <div class="flex items-center gap-4">
        <img
          :src="postObj.user.avatar || require('../../../public/noAvatar.png')"
          width="40px"
          height="40px"
          alt=""
          class="w-10 h-10 rounded-full"
        /><img />
        <span class="font-medium"
          >{{ postObj.user.name }} {{ postObj.user.surname }}</span
        >
      </div>
      <img src="../../../public/more.png" width="16px" height="16px" alt="" />
    </div>
    <!-- DESC -->
    <div class="flex flex-col gap-4">
      <div class="w-full min-h-96 relative" v-if="postObj.imgUrl">
        <img
          :src="postObj.imgUrl"
          fill
          alt=""
          class="object-cover rounded-md"
        /><img />
      </div>
      <p>
        {{ postObj.description }}
      </p>
    </div>
    <!-- INTERACTION -->
    <PostInteraction
      :postId="postObj.idPost"
      :likes="postObj.likesCount"
      :commentNumber="postObj.commentsCount"
    />
    <Comments />
  </div>
</template>

<script setup>
import { defineProps, ref, watch } from "vue";
import Comments from "./Comments.vue";
import PostInteraction from "./PostInteraction.vue";

const postObj = ref({});

const props = defineProps({
  post: {
    type: Object,
    required: true,
  },
});

watch(
  props.post,
  (post) => {
    postObj.value = post;
  },
  { immediate: true }
);
</script>
