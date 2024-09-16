<template>
  <div class="p-4 bg-white rounded-lg shadow-md text-sm flex flex-col gap-4">
    <!-- TOP -->
    <div class="flex justify-between items-center font-medium">
      <span class="text-gray-500">Friend Requests</span>
      <router-link to="/" class="text-blue-500 text-xs">See all</router-link>
    </div>
    <!-- USER -->
    <FriendRequestList
      v-if="isMounted"
      :requests="requests"
      :userObj="props.userObj"
    />
  </div>
</template>

<script setup>
import FriendRequestList from "./FriendRequestList.vue";
import { defineProps, watch, onMounted, ref } from "vue";
import { useStore } from "vuex";
import axios from "axios";

const store = useStore();
const isMounted = ref(false);
let requests = ref([]);

const props = defineProps({
  userObj: {
    type: Object,
    required: true,
  },
});

onMounted(() => {
  isMounted.value = true;
});

const APP_HOST = process.env.APP_HOST;

const findAllFollowRequests = async () => {
  try {
    const followRequestsRes = await axios.post(
      `${APP_HOST}/followsReceivers`,
      {
        receiverId: props.userObj?.user?.idUser,
      },
      {
        headers: {
          Authorization: `Bearer ${store.state.accessToken}`,
        },
      }
    );
    requests.value = followRequestsRes.data;
  } catch (err) {
    console.error(err);
  }
};

watch(
  () => props.userObj,
  async () => {
    await findAllFollowRequests();
  }
);
</script>
