<template v-if="userObj">
  <div class="p-4 bg-white rounded-lg shadow-md text-sm flex flex-col gap-4">
    <!-- TOP -->
    <div class="flex justify-between items-center font-medium">
      <span class="text-gray-500">User Information</span>
      <router-link to="/" class="text-blue-500 text-xs">See all</router-link>
    </div>
    <!-- BOTTOM -->
    <div class="flex flex-col gap-4 text-gray-500">
      <div class="flex items-center gap-2">
        <span class="text-xl text-black">{{
          `${userObj?.user?.name} ${userObj?.user?.surname}`
        }}</span>
        <span class="text-sm">@{{ `${userObj?.user?.username}` }}</span>
      </div>
      <p>
        {{ `${userObj?.user?.description}` }}
      </p>
      <div class="flex items-center gap-2">
        <img src="../../../public/map.png" width="16px" height="16px" alt="" />
        <span
          >Living in <b>{{ `${userObj?.user?.city}` }}</b></span
        >
      </div>
      <div class="flex items-center gap-2">
        <img src="../../../public/work.png" width="16px" height="16px" />
        <span
          >Works at <b>{{ `${userObj?.user?.work}` }}</b></span
        >
      </div>
      <div class="flex items-center justify-between">
        <div class="flex gap-1 items-center">
          <img
            src="../../../public/link.png"
            alt=""
            width="16px"
            height="16px"
          />
          <router-link to="/" class="text-blue-500 font-medium">{{
            `${userObj?.user?.website || "Site..."}`
          }}</router-link>
        </div>
      </div>
      <div class="flex gap-1 items-center">
        <img src="../../../public/date.png" alt="" width="16px" height="16px" />
        <span>Joined {{ `${formattedDate}` }}</span>
      </div>
    </div>
    <UserInfoCardInteraction
      :userId="userObj?.user?.idUser"
      :currentUserId="currentUserId"
      :isUserBlocked="isBlocked"
      :isFollowing="isFollowing"
      :isFollowingSent="false"
    />
  </div>
</template>

<script setup>
import { defineProps, computed, ref, watch } from "vue";
import { useStore } from "vuex";
import UserInfoCardInteraction from "./UserInfoCardInteraction.vue";
import axios from "axios";

const store = useStore();

const APP_HOST = process.env.APP_HOST;

const props = defineProps({
  userObj: {
    type: Object,
    required: false,
  },
});

const followerId = ref("");
const blockerId = ref("");

let isBlocked = ref(false);
let isFollowing = ref(false);

watch(
  () => props.userObj,
  (newVal) => {
    // Profile Id
    followerId.value = newVal?.user?.idUser || "";

    // Profile Blocker Id
    blockerId.value = newVal?.user?.idUser || "";
  },
  { immediate: true }
);

// User Authenticated Id
const followeedId = computed(() => {
  return store.state?.userObj?.user?.idUser;
});

// User Authenticated Blocked Id
const blockedId = computed(() => {
  return store.state?.userObj?.user?.idUser;
});

// Current User Id
const currentUserId = computed(() => {
  return store.state?.userObj?.user?.idUser;
});

const createdAtDate = props.userObj.user
  ? new Date(props.userObj.user.createdAt)
  : null;

const formattedDate = computed(() => {
  return createdAtDate
    ? createdAtDate.toLocaleDateString("en-US", {
        year: "numeric",
        month: "long",
        day: "numeric",
      })
    : "In the Start";
});

const findBlockerUser = async () => {
  try {
    const response = await axios.post(
      `${APP_HOST}/blocked`,
      {
        blockerId: blockerId.value,
        blockedId: blockedId.value,
      },
      {
        headers: {
          Authorization: `Bearer ${store.state.accessToken}`,
        },
      }
    );
    return response;
  } catch (error) {
    console.error(error);
  }
};

const findFollowerUser = async () => {
  try {
    const response = await axios.post(
      `${APP_HOST}/isfollow`,
      {
        followerId: followerId.value,
        followeedId: followeedId.value,
      },
      {
        headers: {
          Authorization: `Bearer ${store.state.accessToken}`,
        },
      }
    );
    return response;
  } catch (error) {
    console.error(error);
  }
};

watch(
  () => followerId.value,
  async () => {
    const followRes = await findFollowerUser();
    followRes.data === true
      ? (isFollowing.value = true)
      : (isFollowing.value = false);
  }
);

watch(
  () => blockerId.value,
  async () => {
    const blockRes = await findBlockerUser();
    blockRes ? (isBlocked.value = true) : (isBlocked.value = false);
  }
);
</script>
