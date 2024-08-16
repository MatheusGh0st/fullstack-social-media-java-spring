<template>
  <div class="h-24 flex items-center justify-between">
    <div class="md:hidden lg:block w-[20%]">
      <router-link to="/" class="font-bold text-xl text-blue-600"
        >NYXSOCIAL</router-link
      >
    </div>
    <div
      class="hidden md:flex w-[-50%] text-sm items-center justify-between"
    ></div>
    <div class="flex gap-6 text-gray-600">
      <router-link to="/" class="flex items-center gap-2">
        <img
          src="../../public/home.png"
          alt="Homepage"
          width="16px"
          height="16px"
          class="w-4 h-4"
        /><img />
        <span>Homepage</span>
      </router-link>
      <router-link to="/" class="flex items-center gap-2">
        <img
          src="../../public/friends.png"
          alt="Friends"
          width="16px"
          height="16px"
          class="w-4 h-4"
        /><img />
        <span>Friends</span>
      </router-link>
      <router-link to="/" class="flex items-center gap-2">
        <img
          src="../../public/stories.png"
          alt="Stories"
          width="16px"
          height="16px"
          class="w-4 h-4"
        /><img />
        <span>Stories</span>
      </router-link>
    </div>
    <div class="hidden xl:flex p-2 bg-slate-100 items-center rounded-xl">
      <input
        type="text"
        placeholder="search..."
        class="bg-transparent outline-none"
      />
      <img src="../../public/search.png" alt="" width="14" height="14" /><img />
    </div>
    <div class="w-[20%] flex items-center gap-4 xl:gap-8 justify-end">
      <div v-if="!isLogged" class="flex items-center gap-2 text-sm">
        <img
          src="../../public/login.png"
          alt="Login/Register"
          width="20px"
          height="20px"
        />
        <router-link to="/sign-in">Login/Register</router-link>
      </div>
      <div v-else class="flex items-center gap-6 text-sm">
        <div class="flex items-center gap-6 text-sm">
          <router-link to="/people">
            <img
              src="../../public/people.png"
              alt="People"
              width="30px"
              height="30px"
              class="cursor-pointer"
            />
          </router-link>
          <router-link to="/messages">
            <img
              src="../../public/messages.png"
              alt="Messages"
              width="30px"
              height="30px"
              class="cursor-pointer"
            />
          </router-link>
          <router-link to="/notifications">
            <img
              src="../../public/notifications.png"
              alt="Notifications"
              width="30px"
              height="30px"
              class="cursor-pointer"
            />
          </router-link>
          <router-link to="/profile">
            <img
              src="../../public/noAvatar.png"
              alt="Profile"
              width="30px"
              height="30px"
              class="bg-blue-900 rounded-xl cursor-pointer"
            />
          </router-link>
        </div>
      </div>
      <MobileMenu />
    </div>
  </div>
</template>

<script setup>
import MobileMenu from "./MobileMenu.vue";
import { useStore } from "vuex";
import { computed } from "vue";

const store = useStore();
const isLogged = computed(() => store.state.isLogged);
</script>
