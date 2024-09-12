<template>
  <div class="flex gap-6 pt-6">
    <div class="hidden xl:block w-[20%]">
      <LeftMenu type="home" />
    </div>
    <div class="w-full lg:w-[70%] xl:w-[50%]">
      <div class="flex flex-col gap-6">
        <Stories />
        <AddPost />
        <Feed />
      </div>
    </div>
    <div class="hidden lg:block w-[30%]">
      <RightMenu />
    </div>
  </div>
</template>

<script setup>
import LeftMenu from "./leftMenu/LeftMenu.vue";
import RightMenu from "./rightMenu/RightMenu.vue";
import Stories from "./Stories.vue";
import AddPost from "./AddPost.vue";
import Feed from "./feed/Feed.vue";
</script>
