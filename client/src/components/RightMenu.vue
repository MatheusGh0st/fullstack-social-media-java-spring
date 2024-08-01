<template>
  <div class="flex flex-col gap-6">
    <div class="flex flex-col justify-between gap-6" v-if="userId">
      <UserInfoCard :userId={userId} />
      <UserMediaCard :userId={userId} />
    </div>
    <FriendsRequests />
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
  import Ad from "./Ad.vue";

  defineProps({
    userId: {
    type: String,
    required: false,
    },
  });
</script>
