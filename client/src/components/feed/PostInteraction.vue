<template>
  <div class="flex items-center justify-between text-sm my-4">
    <div class="flex gap-8">
      <div class="flex items-center gap-4 bg-slate-50 p-2 rounded-xl">
        <form @submit.prevent="likeAction">
          <button>
            <img
              :src="
                likeStateOptimistic.isLiked
                  ? require('../../../public/liked.png')
                  : require('../../../public/like.png')
              "
              width="16px"
              height="16px"
              alt=""
              class="cursor-pointer"
            />
          </button>
        </form>
        <span class="text-gray-300">|</span>
        <span class="text-gray-500">
          <span class="hidden md:inline">{{ likeStateOptimistic.likeCount }} Likes</span></span
        >
      </div>
      <div class="flex items-center gap-4 bg-slate-50 p-2 rounded-xl">
        <img
          src="../../../public/comment.png"
          width="16px"
          height="16px"
          alt=""
          class="cursor-pointer"
        />
        <span class="text-gray-300">|</span>
        <span class="text-gray-500">
          <span class="hidden md:inline"> Comments</span></span
        >
      </div>
    </div>
    <div class="">
      <div class="flex items-center gap-4 bg-slate-50 p-2 rounded-xl">
        <img
          src="../../../public/share.png"
          width="16px"
          height="16px"
          alt=""
          class="cursor-pointer"
        />
        <span class="text-gray-300">|</span>
        <span class="hidden md:inline">Share</span>
      </div>
    </div>
  </div>
</template>

<script setup>
import { defineProps, reactive, onMounted } from "vue";
import { useStore } from "vuex";
import { useOptimisticValue } from "vue-useoptimistic";
import axios from "axios";

const store = useStore();

const userId = store.state.userObj?.user?.idUser;

const APP_HOST = process.env.APP_HOST;

const props = defineProps({
  postId: {
    type: Object,
    required: true,
  },
  likes: {
    type: Object,
    required: true,
  },
  commentNumber: {
    type: Object,
    required: true,
  },
});

onMounted(async () => {
  try {
    const existLikeRes = await axios.post(
      `${APP_HOST}/isLiked`, 
      { postId: props.postId, userId: userId }, 
      { headers: { Authorization: `Bearer ${store.state.accessToken}`}} );

    const likeExist = existLikeRes.data.isLiked;
    likeState.isLiked = likeExist;
  } catch (error) {
    console.error("Error fetching like state: ", error);
  }
})

const likeState = reactive({
  likeCount: props.likes || 0,
  isLiked: false,
});

const likeStateOptimistic = useOptimisticValue(likeState);

const switchLike = async () => {
  if (!userId) throw new Error("User is not authenticated");

  try {
    const existingLike = await axios.post(
      `${APP_HOST}/isLiked`,
      {
        postId: props.postId,
        userId: userId,
      },
      { headers: { Authorization: `Bearer ${store.state.accessToken}` } }
    );

    console.log(existingLike);

    if (existingLike.data.likeId) {
      const likeId = existingLike.data.likeId;
      const deleteLike = await axios.delete(`${APP_HOST}/like/${likeId}`,
      { headers: { Authorization: `Bearer ${store.state.accessToken}` }});
      console.log(deleteLike);
    } else {
      const createLike = await axios.post(`${APP_HOST}/like`,
      {
        userId: userId,
        postId: props.postId,
        commentId: null,
      },
      { headers: { Authorization: `Bearer ${store.state.accessToken}`}});
      console.log(createLike);
    }
  } catch (err) {
    console.error(err);
  }
};

const likeAction = async () => {
  try {
    await switchLike();
    likeState.likeCount = likeState.isLiked ? likeState.likeCount - 1 : likeState.likeCount + 1;
    likeState.isLiked = !likeState.isLiked;

    likeStateOptimistic.likeCount = likeStateOptimistic.isLiked ? likeStateOptimistic.likeCount - 1 : likeStateOptimistic.likeCount + 1;
    likeStateOptimistic.isLiked = !likeStateOptimistic.isLiked;
    console.log(likeStateOptimistic);
  } catch (err) {
    console.error(err);
  }
};
</script>
