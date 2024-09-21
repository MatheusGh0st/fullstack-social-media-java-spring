<template>
  <div class="p-4 bg-white rounded-lg shadow-md text-sm flex flex-col gap-6">
    <div class="h-20 relative">
      <img
        :src="profileObj?.user?.cover || require('../../../public/noCover.png')"
        alt="User Avatar"
        style="width: 100%; height: 100%"
        class="rounded-md object-cover"
      />
      <img
        :src="
          profileObj?.user?.avatar || require('../../../public/noAvatar.png')
        "
        alt="User Avatar"
        width="48px"
        height="48px"
        class="rounded-full object-cover w-12 h-12 absolute left-0 right-0 m-auto -bottom-6 ring-1 ring-white z-10"
      />
    </div>
    <div class="h-20 flex flex-col gap-2 items-center">
      <span class="font-semibold">{{
        `${profileObj?.user?.name} ${profileObj?.user?.surname}`
      }}</span>
      <div class="flex items-center gap-4">
        <div class="flex">
          <img
            src="https://images.pexels.com/photos/27008964/pexels-photo-27008964/free-photo-of-a-tree-is-sitting-on-the-shore-of-a-lake.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
            alt=""
            width="12px"
            height="12px"
            class="rounded-full object-cover w-3 h-3"
          />
          <img
            src="https://images.pexels.com/photos/27008964/pexels-photo-27008964/free-photo-of-a-tree-is-sitting-on-the-shore-of-a-lake.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
            alt=""
            width="12"
            height="12"
            class="rounded-full object-cover w-3 h-3"
          />
          <img
            src="https://images.pexels.com/photos/27008964/pexels-photo-27008964/free-photo-of-a-tree-is-sitting-on-the-shore-of-a-lake.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
            alt=""
            width="12px"
            height="12px"
            class="rounded-full object-cover w-3 h-3"
          />
        </div>
        <span class="text-xs text-gray-500"
          >{{ profileObj?.followers || 0 }} Followers</span
        >
      </div>
      <button class="bg-blue-500 text-white text-xs p-2 rounded-md">
        <router-link :to="`${profileLink?.value}`">My Profile</router-link>
      </button>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { ref, computed } from "vue";

const router = useRouter();
const profileObj = ref({});
const store = useStore();
const profileLink = ref("");

profileObj.value = store.state.userObj;

router.isReady().then(async () => {
  profileLink.value = computed(() => {
    return `/profile/${profileObj?.value?.user?.username}`;
  });
});
</script>
