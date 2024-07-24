<template>
  <div class="h-24 flex items-center justify-between">
    <div class="md:hidden lg:block w-[20%]">
      <router-link to="/" class="font-bold text-xl text-blue-600">NYXSOCIAL</router-link>
    </div>
    <div class="hidden md:flex w-[-50%] text-sm">
    </div>
    <div class="flex gap-6 text-gray-600">
      <router-link to="/" class="flex items-center gap-2">
        <img src="../../public/home.png" alt="Homepage" width="16px" height="16px" class="w-4 h-4"><img />
        <span>Homepage</span>
      </router-link>
      <router-link to="/" class="flex items-center gap-2">
        <img src="../../public/friends.png" alt="Homepage" width="16px" height="16px" class="w-4 h-4"><img />
        <span>Friends</span>
      </router-link>
      <router-link to="/" class="flex items-center gap-2">
        <img src="../../public/stories.png" alt="Homepage" width="16px" height="16px" class="w-4 h-4" ><img />
        <span>Stories</span>
      </router-link>
    </div>
    <div class="w-[30%] flex items-center gap-4 xl:gap-8 justify-end">
      <MobileMenu />
    </div>
  </div>
</template>

<script>
import MobileMenu from "./MobileMenu.vue";

export default {
  name: "Navbar",
  components: {
    MobileMenu,
  },
}
</script>
