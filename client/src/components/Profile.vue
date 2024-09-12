<template>
  <div class="flex gap-6 pt-6">
    <div class="hidden xl:block w-[20%]">
      <LeftMenu type="profile" />
    </div>
    <div class="w-full lg:w-[70%] xl:w-[50%]">
      <div class="flex flex-col gap-6">
        <div class="flex flex-col items-center justify-center">
          <div class="w-full h-64 relative">
            <img
              :src="
                profileObj && profileObj.user && profileObj.user.avatar
                  ? profileObj.user.avatar
                  : require('../../public/noCover.png')
              "
              alt=""
              fill
              style="width: 100%; height: 100%"
              class="rounded-md object-cover"
            />
            <img
              :src="
                profileObj && profileObj.user && profileObj.user.avatar
                  ? profileObj.user.avatar
                  : require('../../public/noAvatar.png')
              "
              alt=""
              fill
              class="w-32 h-32 rounded-full absolute left-0 right-0 m-auto -bottom-16 ring-4 object-cover"
            />
          </div>
          <h1 class="mt-20 mb-4 text-2xl font-medium">
            {{ `${profileObj?.user?.name} ${profileObj?.user?.surname}` }}
          </h1>
          <div class="flex items-center justify-center gap-12 mb-4">
            <div class="flex flex-col items-center">
              <span class="font-medium">{{
                profileObj?.user?.posts.length || 0
              }}</span>
              <span class="text-sm">Posts</span>
            </div>
            <div class="flex flex-col items-center">
              <span class="font-medium">{{ profileObj?.followers || 0 }}</span>
              <span class="text-sm">Followers</span>
            </div>
            <div class="flex flex-col items-center">
              <span class="font-medium">{{ profileObj?.following || 0 }}</span>
              <span class="text-sm">Followings</span>
            </div>
          </div>
          <div></div>
        </div>
        <Feed />
      </div>
    </div>
    <div class="hidden lg:block w-[30%]">
      <RightMenu :userObj="profileObj" />
    </div>
  </div>
</template>

<script setup>
import LeftMenu from "./leftMenu/LeftMenu.vue";
import RightMenu from "./rightMenu/RightMenu.vue";
import Feed from "./feed/Feed.vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { ref } from "vue";
import axios from "axios";

const router = useRouter();
const username = ref("");
const profileObj = ref({});
const store = useStore();

const API_HOST = process.env.APP_HOST;

router.isReady().then(async () => {
  const path = router.currentRoute.value.path;
  const regex = /^\/profile\/([^/]+)$/;
  const match = path.match(regex);
  username.value = match ? match[1] : null;
  console.log("Compoennt Profile");
  profileObj.value = await findProfile(username.value);
});

const findProfile = async (username) => {
  if (username === null) return null;
  try {
    const response = await axios.get(
      `${API_HOST}/api/auth/${username}/profile`,
      {
        headers: {
          Authorization: `Bearer ${store.state.accessToken}`,
        },
      }
    );
    return response.data;
  } catch (error) {
    console.error(error);
  }
};
</script>
