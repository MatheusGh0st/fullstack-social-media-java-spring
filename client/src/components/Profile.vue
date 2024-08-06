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
              src="https://images.pexels.com/photos/14596412/pexels-photo-14596412.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
              alt=""
              fill
              style="width: 100%; height: 100%"
              class="rounded-md object-cover"
            />
            <img
              src="https://images.pexels.com/photos/27307368/pexels-photo-27307368/free-photo-of-jjj.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
              alt=""
              fill
              class="w-32 h-32 rounded-full absolute left-0 right-0 m-auto -bottom-16 ring-4 object-cover"
            />
          </div>
          <h1 class="mt-20 mb-4 text-2xl font-medium">Elva Weaver</h1>
          <div class="flex items-center justify-center gap-12 mb-4">
            <div class="flex flex-col items-center">
              <span class="font-medium">123</span>
              <span class="text-sm">Posts</span>
            </div>
            <div class="flex flex-col items-center">
              <span class="font-medium">1.1K</span>
              <span class="text-sm">Followers</span>
            </div>
            <div class="flex flex-col items-center">
              <span class="font-medium">1.5K</span>
              <span class="text-sm">Followings</span>
            </div>
          </div>
          <div></div>
        </div>
        <Feed />
      </div>
    </div>
    <div class="hidden lg:block w-[30%]">
      <RightMenu userId="test" />
    </div>
  </div>
</template>

<script setup>
import LeftMenu from "./LeftMenu.vue";
import RightMenu from "./RightMenu.vue";
import Feed from "./Feed.vue";
</script>
