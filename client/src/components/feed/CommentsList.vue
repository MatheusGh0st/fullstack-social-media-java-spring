<template>
  <div class="flex items-center gap-4" v-if="userObj">
    <img
        src="https://images.pexels.com/photos/2645245/pexels-photo-2645245.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
        alt=""
        width="32px"
        height="32px"
        class="w-8 h-8 rounded-full"
        />
    <form @submit.prevent="add"
        class="flex-1 flex items-center justify-between bg-slate-100 rounded-xl text-sm px-6 py-2 w-full"
        >
        <input
            type="text"
            placeholder="Write a comment..."
            class="bg-transparent outline-none flex-1"
            v-model="description"
            />
        <img
            src="../../../public/emoji.png"
            alt=""
            width="16px"
            height="16px"
            class="cursor-pointer"
            />
    </form>
  </div>
  <!-- COMMENTS -->
  <div class="" v-if="commentState.comments">
    <!-- COMMENT -->
    <div class="flex gap-4 justify-between mt-6" v-for="comment in commentState.comments" :key="comment.idComment">
      <!-- AVATAR -->
      <img
          :src="comment?.user?.avatar || require('../../../public/noAvatar.png')"
          alt=""
          width="40px"
          height="40px"
          class="w-10 h-10 rounded-full"
          />
      <!-- DESC -->
      <div class="flex flex-col gap-2 flex-1">
        <span class="font-medium">{{ comment.user.name }} {{ comment.user.surname}}</span>
        <p>
          {{ comment.content }}
        </p>
        <div class="flex items-center gap-8 text-xs text-gray-500 mt-2">
          <div class="flex items-center gap-4">
            <form @submit.prevent="likeAction(comment.idComment)">
              <button>
                <img
                    :src="
                          comment.likeState.isLiked
                          ? require('../../../public/liked.png')
                          : require('../../../public/like.png')"
                    alt=""
                    width="12px"
                    height="12px"
                    class="cursor-pointer w-4 h-4"
                    />
              </button>
            </form>
            <span class="text-gray-300">|</span>
            <span class="text-gray-500">{{ comment.likeState.likeCount }} likes</span>
          </div>
          <div class="">Reply</div>
        </div>
      </div>
      <div class=""></div>
      <!-- ICON -->
      <img
          src="../../../public/more.png"
          alt=""
          class="cursor-pointer w-4 h-4"
          width="16px"
          height="16px"
          />
    </div>
  </div>
</template>

<script setup>
import { defineProps, ref, reactive, watch } from "vue";
import axios from "axios";
import { useStore } from "vuex";
import { useOptimisticValue } from "vue-useoptimistic";

const store = useStore();
const userId = store.state?.userObj?.user?.idUser;

const APP_HOST = process.env.APP_HOST;

const props = defineProps({
  comments: {
    type: Object,
    required: true
  },
  postId: {
    type: String,
    required: true
  }
});

const commentState = reactive({
  comments: []
});

let commentStateOptimistic = useOptimisticValue(commentState);

const description = ref("");
const userObj = store.state?.userObj?.user;

const addComment = async () => {
  if (!userId) return null;

  try {
    const createComment = await axios.post(`${APP_HOST}/comment`, {
      content: description.value,
      userId: userId,
      postId: props.postId
    }, { headers: { Authorization: `Bearer ${store.state.accessToken}`}});

    createComment.data.likeState = { likeCount: 0, isLiked: false };

    return createComment.data;
  } catch (error) {
    console.error(error);
  }
}

const add = async () => {
  if (!userId || !description.value) return null;

  try {
    const createdComment = await addComment();
    commentState.comments.push(createdComment);
    commentStateOptimistic.value.comments = commentState.comments;

    description.value = "";
  } catch (error) {
    console.error(error);
  }
}

watch(
  () => props.comments,
  (newComments) => {
    commentState.comments = Array.from(newComments).map(comment => ({
      ...comment,
      likeState: {
        likeCount: comment.likes.likes || 0,
        isLiked: false,
      }
    }));
  }, { immediate: true }
);

const switchLike = async (commentId) => {
  if (!userId) throw new Error("User is not authenticated");

  try {
    const existingLike = await axios.post(
      `${APP_HOST}/isLiked`,
      {
        postId: props.postId,
        userId: userId,
        commentId: commentId
      },
      { headers: { Authorization: `Bearer ${store.state.accessToken}` } }
    );

    console.log(existingLike);

    const comment = commentState.comments.find(c => c.idComment === commentId);

    if (existingLike.data.likeId) {
      const likeId = existingLike.data.likeId;
      await axios.delete(`${APP_HOST}/like/${likeId}`,
      { headers: { Authorization: `Bearer ${store.state.accessToken}` }});
      
      // Update local state
      if (comment) {
        comment.likeState.likeCount -= 1;
        comment.likeState.isLiked = false;
      }
      
    } else {
      await axios.post(`${APP_HOST}/like`,
      {
        userId: userId,
        postId: props.postId,
        commentId: commentId,
      },
      { headers: { Authorization: `Bearer ${store.state.accessToken}`}});
      
      // Update local state
      const comment = commentState.comments.find(c => c.idComment === commentId);
      if (comment) {
        comment.likeState.likeCount += 1;
        comment.likeState.isLiked = true;
      }
      
    }

    commentStateOptimistic.value.comments = [...commentState.comments];
    
  } catch (err) {
    console.error(err);
  }
};

const likeAction = async (commentId) => {
  const comment = commentState.comments.find(c => c.idComment === commentId);
  
  if (!comment) return;

  try {
    await switchLike(commentId);
    
    // Toggle like state and update count
    if (comment.likeState.isLiked) {
      comment.likeState.likeCount -= 1;
    } else {
      comment.likeState.likeCount += 1;
    }

    comment.likeState.isLiked = !comment.likeState.isLiked;

    commentStateOptimistic.value.comments = [...commentState.comments];
    
  } catch (err) {
    console.error(err);
  }
};
</script>
