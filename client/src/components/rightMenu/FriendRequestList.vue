<template>
  <div
    class="flex items-center justify-between"
    v-for="request in requestsFriends"
    :key="request.idFollowRequests"
  >
    <div class="flex items-center gap-4">
      <img
        :src="
          request.sender.avatar
            ? request.sender.avatar
            : require('../../../public/noAvatar.png')
        "
        alt=""
        width="40px"
        height="40px"
        class="w-10 h-10 rounded-full object-cover"
      />
      <span class="font-semibold">
        {{ request.sender.name }} {{ request.sender.surname }}
      </span>
    </div>
    <div class="flex gap-3 justify-end">
      <form @submit.prevent="acceptFollowRequest(request.sender.idUser)">
        <button>
          <img
            src="../../../public/accept.png"
            alt=""
            width="20px"
            height="20px"
            class="cursor-pointer"
          />
        </button>
      </form>
      <form @submit.prevent="declineFollowRequest(request.sender.idUser)">
        <button>
          <img
            src="../../../public/reject.png"
            alt=""
            width="20px"
            height="20px"
            class="cursor-pointer"
          />
        </button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { defineProps, ref, watch } from "vue";
import { useStore } from "vuex";
import axios from "axios";

const APP_HOST = process.env.APP_HOST;
const store = useStore();

const props = defineProps({
  requests: {
    type: Array,
    required: true,
  },
  userObj: {
    type: Object,
    required: true,
  },
});

// Use ref for reactivity
let requestsFriends = ref([...props.requests]);

// Watch for changes in props.requests and update requestsFriends
watch(
  () => props.requests,
  (newRequests) => {
    requestsFriends.value = newRequests; // Update here
  },
  { immediate: true }
);

const acceptFollowRequest = async (idFollowerRequest) => {
  try {
    const existingFollowRequest = await axios.post(
      `${APP_HOST}/isfollowRequest`,
      { senderId: idFollowerRequest, recieverId: props.userObj?.user?.idUser },
      {
        headers: {
          Authorization: `Bearer ${store.state.accessToken}`,
        },
      }
    );

    if (existingFollowRequest.data.isFollowingRequest) {
      const idFollowingRequest = existingFollowRequest.data.idFollowRequest;

      // Delete the follow request
      await axios.delete(`${APP_HOST}/followRequest/${idFollowingRequest}`, {
        headers: { Authorization: `Bearer ${store.state.accessToken}` },
      });
    }

    // Create a new follow relationship
    await axios.post(
      `${APP_HOST}/follow`,
      {
        followerId: idFollowerRequest,
        followeedId: store.state.userObj?.user?.idUser,
      },
      {
        headers: { Authorization: `Bearer ${store.state.accessToken}` },
      }
    );

    // Remove the accepted request from local state
    requestsFriends.value = requestsFriends.value.filter(
      (request) => request.sender.idUser !== idFollowerRequest
    );
  } catch (err) {
    console.error(err);
  }
};

const declineFollowRequest = async (idFollowerRequest) => {
  console.log(idFollowerRequest);
  try {
    const existingFollowRequest = await axios.post(
      `${APP_HOST}/isfollowRequest`,
      {
        senderId: idFollowerRequest,
        recieverId: store.state.userObj?.user?.idUser,
      },
      {
        headers: {
          Authorization: `Bearer ${store.state.accessToken}`,
        },
      }
    );

    if (existingFollowRequest.data.isFollowingRequest) {
      const idFollowingRequest = existingFollowRequest.data.idFollowRequest;

      // Delete the follow request
      await axios.delete(`${APP_HOST}/followRequest/${idFollowingRequest}`, {
        headers: { Authorization: `Bearer ${store.state.accessToken}` },
      });

      // Remove the declined request from local state
      requestsFriends.value = requestsFriends.value.filter(
        (request) => request.sender.idUser !== idFollowerRequest
      );
    }
  } catch (err) {
    console.error(err);
  }
};
</script>
