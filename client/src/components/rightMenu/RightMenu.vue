<template>
  <div class="flex flex-col gap-6">
    <Suspense>
      <template #default>
        <div class="flex flex-col justify-between gap-6" v-if="userObj">
          <UserInfoCard :userObj="userObj" />
          <UserMediaCard :userObj="userObj" />
        </div>
      </template>
      <template #fallback>
        <div class="flex justify-center items-center">
          <p>Loading user information...</p>
        </div>
      </template>
    </Suspense>
    <Suspense>
      <template #default>
        <FriendsRequests v-if="userObj" :userObj="userObj" />
      </template>
      <template #fallback>
        <p>Loading user information....</p>
      </template>
    </Suspense>
    <Birthdays />
    <Ad size="md" />
  </div>
</template>

<script setup>
import { defineProps } from "vue";
import FriendsRequests from "./FriendRequests.vue";
import Birthdays from "./Birthdays.vue";
import UserInfoCard from "./UserInfoCard.vue";
import UserMediaCard from "./UserMediaCard.vue";
import Ad from "../Ad.vue";

defineProps({
  userObj: {
    type: Object,
    required: true,
  },
});
</script>
