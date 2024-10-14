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
          <router-link to="/">
            <img
              src="../../public/noAvatar.png"
              alt="Profile"
              width="30px"
              height="30px"
              class="bg-blue-900 rounded-xl cursor-pointer"
              @click="handleIsOpen"
            />
            <div 
              v-if="isOpen"
              class="absolute w-screen h-screen top-0 left-0 bg-black bg-opacity-65 flex items-center justify-center z-50">
              <form
                  class="p-12 bg-white rounded-lg shadow-md flex flex-col gap-2 w-full md:w-1/2 xl:w-1/3 relative"
                  >
                  <!-- TITLE -->
                <h1>Profile</h1>
                <div class="mt-4 text-xs text-gray-500">
                  Name: {{ user.name || 'Loading name' }} 
                </div>
                <div class="mt-4 text-xs text-gray-500">
                  Surname: {{ user.surname || 'Loading Surname' }}
                </div>
                <div class="mt-4 text-xs text-gray-500">
                  Email: {{ user.email || 'Loading email'}}
                </div>
                <button class="bg-blue-500 p-2 mt-2 rounded-md text-white" @click="logout">Logout</button>
                <div
                    class="absolute text-xl right-2 top-3 cursor-pointer"
                    @click="handleIsOpen"
                    >
                    X
                </div>
              </form>
            </div>
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
import { useRouter } from "vue-router";
import { computed, ref } from "vue";

const store = useStore();
const router = useRouter();
const user = store.state.userObj?.user || "Loading";
const isLogged = computed(() => store.state.isLogged);
const isOpen = ref(false)

const handleIsOpen = () => {
  isOpen.value = !isOpen.value;
}

const logout = async () => {
  handleIsOpen();
  try {
    await store.dispatch("logoutUser");

    if (!store.state.accessToken) {
      router.go("/sign-in");
    } else {
      router.go("/");
    }
  } catch (error) {
    console.error(error);
  }
}
</script>
