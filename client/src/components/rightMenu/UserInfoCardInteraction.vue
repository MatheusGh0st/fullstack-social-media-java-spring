<template>
  <form @submit.prevent="follow">
    <button class="bg-blue-500 text-white text-sm rounded-md w-full">
      {{
        userStateOptimistic.following
          ? "Following"
          : userStateOptimistic.followingRequestSent
          ? "Friend Request Sent"
          : "Follow"
      }}
    </button>
  </form>
  <form @submit.prevent="block" class="self-end">
    <button>
      <span class="text-red-400 self-end text-xs cursor-pointer">
        {{ userStateOptimistic.blocked ? "Unlock User" : "Block User" }}
      </span>
    </button>
  </form>
</template>

<script setup>
import { defineProps, reactive } from "vue";
import axios from "axios";
import { useOptimisticValue } from "vue-useoptimistic";
import { useStore } from "vuex";

const store = useStore();

const APP_HOST = process.env.APP_HOST;

const props = defineProps({
  userId: {
    type: String,
    required: true,
  },
  currentUserId: {
    type: String,
    required: true,
  },
  isUserBlocked: {
    type: Boolean,
    required: true,
  },
  isFollowing: {
    type: Boolean,
    required: true,
  },
  isFollowingSent: {
    type: Boolean,
    required: true,
  },
});

const userState = reactive({
  following: props.isFollowing,
  blocked: props.isUserBlocked,
  followingRequestSent: props.isFollowingSent,
});

const userStateOptimistic = useOptimisticValue(userState);

const switchProfile = async () => {
  if (!props.currentUserId) {
    throw new Error("User is not authenticated!");
  }

  try {
    console.log("SWITCH PROFILE");
    const existingFollow = await axios.post(
      `${APP_HOST}/isfollow`,
      {
        followerId: props.currentUserId,
        followeedId: props.userId,
      },
      {
        headers: {
          Authorization: `Bearer ${store.state.accessToken}`,
        },
      }
    );

    if (existingFollow.data.isFollowing) {
      const idFollow = existingFollow.data.idFollow;
      const deleteFollow = await axios.delete(
        `${APP_HOST}/delete/${idFollow}`,
        { headers: { Authorization: `Bearer ${store.state.accessToken}` } }
      );
      console.log(deleteFollow);
    } else {
      const existingFollowRequest = await axios.post(
        `${APP_HOST}/isfollowRequest`,
        { senderId: props.currentUserId, recieverId: props.userId },
        {
          headers: {
            Authorization: `Bearer ${store.state.accessToken}`,
          },
        }
      );

      if (existingFollowRequest.data.isFollowingRequest) {
        const idFollowRequest = existingFollowRequest.data.idFollowRequest;
        const deleteFollowRequest = await axios.delete(
          `${APP_HOST}/delete/${idFollowRequest}`,
          {
            headers: { Authorization: `Bearer ${store.state.accessToken}` },
          }
        );
        console.log(deleteFollowRequest);
      } else {
        const createFollowRequest = await axios.post(
          `${APP_HOST}/followRequest`,
          { senderId: props.currentUserId, recieverId: props.userId },
          { headers: { Authorization: `Bearer ${store.state.accessToken}` } }
        );
        console.log(createFollowRequest);
      }
    }
    return existingFollow;
  } catch (err) {
    console.error(err);
  }
};

const follow = async () => {
  try {
    await switchProfile();
    userState.following = userState.following && false;
    userState.followingRequestSent =
      !userState.following && !userState.followingRequestSent;

    // Optimistically update the state
    userStateOptimistic.following = userStateOptimistic.following && false;

    // Update followingRequestSent based on the new following state
    userStateOptimistic.followingRequestSent =
      !userStateOptimistic.following &&
      !userStateOptimistic.followingRequestSent;
  } catch (err) {
    console.error(err);
    // Optionally revert optimistic state on error
    userStateOptimistic.following = userState.following;
    userStateOptimistic.followingRequestSent = userState.followingRequestSent;
  }
};

const switchBlock = async () => {
  if (!props.currentUserId) {
    throw new Error("User is not authenticated");
  }

  try {
    const existingBlock = await axios.post(
      `${APP_HOST}/isblocked`,
      {
        blockerId: props.currentUserId,
        blockedId: props.userId,
      },
      {
        headers: {
          Authorization: `Bearer ${store.state.accessToken}`,
        },
      }
    );

    if (existingBlock.data.isBlocked) {
      const idBlock = existingBlock.data.idBlock;
      const deleteBlock = await axios.delete(`${APP_HOST}/block/${idBlock}`, {
        headers: { Authorization: `Bearer ${store.state.accessToken}` },
      });
      console.log(deleteBlock);
    } else {
      const createBlock = await axios.post(
        `${APP_HOST}/block`,
        { blockerId: props.currentUserId, blockedId: props.userId },
        { headers: { Authorization: `Bearer ${store.state.accessToken}` } }
      );
      console.log(createBlock);
    }
  } catch (err) {
    console.error(err);
  }
};

const block = async () => {
  try {
    await switchBlock();
    userState.blocked = !userState.blocked;
    userStateOptimistic.blocked = !userStateOptimistic.blocked;
  } catch (err) {
    console.error(err);
    userStateOptimistic.blocked = userState.blocked;
  }
};
</script>
