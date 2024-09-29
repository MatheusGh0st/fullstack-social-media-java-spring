<template>
  <div class="p-4 bg-white rounded-lg flex gap-4 justify-between text-sm">
    <!-- AVATAR -->
    <img 
       :src="user?.imgUrl || require('../../public/noAvatar.png')"
       width="48px"
       height="48px"
       alt=""
       class="w-12 h-12 object-cover rounded-full"
    />
    <!-- POST -->
    <div class="flex-1">
      <!-- TEXT INPUT -->
      <form class="flex gap-4" @submit.prevent="addPost">
        <textarea 
          placeholder="What's on your mind?" 
          class="flex-1 bg-slate-100 rounded-lg" 
          v-model="description"
        ></textarea>
        <div>
          <img
              src="../../public/emoji.png"
              alt=""
              width="20px"
              height="20px"
              class="w-5 h-5 cursor-pointer self-end"
          />
          <input type="text" v-model="imgUrl" placeholder="Enter image URL">
          <AddPostButton v-model="description" />
        </div>
      </form>
      <!-- POST OPTIONS -->
      <div class="flex items-center gap-4 mt-4 text-gray-400 flex-wrap">
        <div class="flex items-center gap-2 cursor-pointer">
          <img src="../../public/addimage.png" alt="" width="20px" height="20px">Photo
        </div>
        <div class="flex items-center gap-2 cursor-pointer">
          <img src="../../public/addVideo.png" alt="" width="20px" height="20px">Video
        </div>
        <div class="flex items-center gap-2 cursor-pointer">
          <img src="../../public/poll.png" alt="" width="20px" height="20px">Poll
        </div>
        <div class="flex items-center gap-2 cursor-pointer">
          <img src="../../public/addevent.png" alt="" width="20px" height="20px">Event
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useStore } from "vuex";
import { ref } from "vue";
import AddPostButton from "./AddPostButton.vue";
import axios from "axios";
import { z } from "zod";
// import { useRouter } from "vue-router";

const APP_HOST = process.env.APP_HOST;

const store = useStore();
// const router = useRouter();
const userId = store.state?.userObj?.user?.idUser;
const user = store.state?.userObj;
const description = ref("");
const imgUrl = ref("");

const addPost = async () => {

  if (!description.value) return null;

  const dConstraints = z.string().min(1).max(255);
  const validateDesc = dConstraints.safeParse(description.value);

  if (!validateDesc.success) return null;

  try {
    const addPostReq = await axios.post(`${APP_HOST}/post`,
    { 
      description: description.value,
      imgUrl: imgUrl.value || "",
      userId: userId
    },
    { headers: { Authorization: `Bearer ${store.state.accessToken}` }});

    // router.replace('/');
    description.value = "";
    imgUrl.value = "";

    console.log(addPostReq);
  } catch (err) {
    console.error(err);
  }
}
</script>
