<template>
  <div v-if="listPosts" class="p-4 bg-white shadow-md rounded-lg flex flex-col gap-12">
    <Post v-for="post in listPosts" :key="post.idPost" :post="post" />
  </div>
  <div v-if="listPostsModified" class="p-4 bg-white shadow-md rounded-lg flex flex-col gap-12">
    <Post 
      v-for="post in listPostsModified"
      :key="post.idPost"
      :post="post"
    />
  </div>
</template>

<script setup>
import { defineProps, watch, ref, toRefs } from "vue";
import { useStore } from "vuex";
import axios from "axios";
import Post from "./Post.vue";

const APP_HOST = process.env.APP_HOST;

const store = useStore();
const userId = store.state?.userObj?.user?.idUser;
let listPosts = ref([]);
let listPostsModified = ref([]);

const props = defineProps({
  userObj: {
  type: Object,
  required: true,
  }
});

const flatObj = (data) => {
  const { post, user, likesCount, commentCount, comments } = data;

  return {
    ...post,
    user: { ...user },
    likesCount,
    commentCount,
    comments
  }
}

const { username } = toRefs(props.userObj?.user);

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
    try {
      if (!username && userId) {
        const followingIdReq = await axios.get(`${APP_HOST}/followingsId/${userId}`,
        { headers: { Authorization: `Bearer ${store.state.accessToken}`} });

        if (followingIdReq.data != null) {
          const followingIds = followingIdReq.data.map((f) => ({ "idUserFollowerUUID": f.idUserFollowerUUID }));
          const ids = [ { "idUserFollowerUUID": userId }, ...followingIds];

          const postsIds = await axios.post(`${APP_HOST}/postUUIDs`,
          { uuids: ids },
          { headers: { Authorization: `Bearer ${store.state.accessToken}`} });
          const flatPosts = postsIds.data.map((d) => (flatObj(d)));
          listPostsModified.value = flatPosts;
        }
      }

      listPosts.value = await getPosts();
      // Handle the response here
    } catch (error) {
      console.error('Error fetching followings:', error);
      // Handle the error appropriately
    }
  }, { immediate: true }
);
</script>
