<template>
  <div>
    <span class="text-blue-500 text-xs cursor-pointer" @click="handleIsOpen"
      >Update</span
    >
    <div
      v-if="isOpen"
      class="absolute w-screen h-screen top-0 left-0 bg-black bg-opacity-65 flex items-center justify-center z-50"
    >
      <form
        @submit.prevent="updateProfile"
        class="p-12 bg-white rounded-lg shadow-md flex flex-col gap-2 w-full md:w-1/2 xl:w-1/3 relative"
      >
      <!-- TITLE -->
        <h1>Update Profile</h1>
        <div class="mt-4 text-xs text-gray-500">
          Use the navbar profile to change the avatar or username
        </div>
      <!-- COVER PIC UPLOAD -->
      <div class="flex flex-col gap-4 my-4">
        <label htmlFor="">Cover Picture</label>
        <div class="flex items-center gap-2 cursor-pointer">
          <img :src="userObj.avatar ? userObj.avatar : require('../../../public/noAvatar.png')" alt="" width="48px" height="32px" class="w-12 h-8 rounded-md object-cover" />
          <span class="text-xs underline text-gray-600">Change</span>
        </div>
      </div>
      <!-- WRAPPER -->
      <div class="flex flex-wrap justify-between gap-2 xl:gap-4">
        <div class="flex flex-col gap-4">
          <label htmlFor="" class="text-xs text-gray-500">First Name</label>
          <input type="text" :placeholder="userObj.name ? userObj.name : 'Jonh'" class="ring-1 ring-gray-300 p-[13px] rounded-md text-sm" name="firstName"  v-model="formData.firstName" />
        </div>
        <div class="flex flex-col gap-4">
          <label htmlFor="" class="text-xs text-gray-500">Surname</label>
          <input type="text" :placeholder="userObj.surname ? userObj.surname : 'Smith'" class="ring-1 ring-gray-300 p-[13px] rounded-md text-sm" name="surname"  v-model="formData.surname" />
        </div>
        <div class="flex flex-col gap-4">
          <label htmlFor="" class="text-xs text-gray-500">Description</label>
          <input type="text" :placeholder="userObj.description ? userObj.description : 'I am from ......'" class="ring-1 ring-gray-300 p-[13px] rounded-md text-sm" name="description"  v-model="formData.description" />
        </div>
        <div class="flex flex-col gap-4">
          <label htmlFor="" class="text-xs text-gray-500">City</label>
          <input type="text" :placeholder="userObj.city ? userObj.city : 'Dallas Texas'" class="ring-1 ring-gray-300 p-[13px] rounded-md text-sm" name="city"  v-model="formData.city"/>
        </div>
        <div class="flex flex-col gap-4">
          <label htmlFor="" class="text-xs text-gray-500">School</label>
          <input type="text" :placeholder="userObj.school ? userObj.school : 'MIT'" class="ring-1 ring-gray-300 p-[13px] rounded-md text-sm" name="school"  v-model="formData.school"/>
        </div>
        <div class="flex flex-col gap-4">
          <label htmlFor="" class="text-xs text-gray-500">Work</label>
          <input type="text" :placeholder="userObj.work ? userObj.work : 'Apple'" class="ring-1 ring-gray-300 p-[13px] rounded-md text-sm" name="work" v-model="formData.work"/>
        </div>
        <div class="flex flex-col gap-4">
          <label htmlFor="" class="text-xs text-gray-500">Website</label>
          <input type="text" :placeholder="userObj.site ? userObj.site : 'www.site.com'" class="ring-1 ring-gray-300 p-[13px] rounded-md text-sm" name="website" v-model="formData.website"/>
        </div>
      </div>
      <button class="bg-blue-500 p-2 mt-2 rounded-md text-white">Update</button>
        <div
          class="absolute text-xl right-2 top-3 cursor-pointer"
          @click="handleIsOpen"
        >
          X
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { defineProps, ref, reactive } from "vue";
import { useStore } from "vuex";
import axios from "axios";
import { z } from "zod";

const APP_HOST = process.env.APP_HOST;

const isOpen = ref(false);
const store = useStore();

const handleIsOpen = () => {
  isOpen.value = !isOpen.value;
};

defineProps({
  userObj: {
    type: Object,
    required: true,
  }
});

const formData = reactive({
  firstName: "",
  surname: "",
  description: "",
  city: "",
  school: "",
  work: "",
  website: "",
});

const updateProfile = async () => {

  const Profile = z.object({
    cover: z.string().optional(),
    name: z.string().max(60).optional(),
    description: z.string().max(255).optional(),
    city: z.string().max(60).optional(),
    school: z.string().max(60).optional(),
    work: z.string().max(60).optional(),
    website: z.string().max(60).optional(),
  });

  const validateFields = Profile.safeParse(formData);

  if(!validateFields.success) {
  return "error in validation";
  }
  
  const updateUserRes = await axios.put(`${APP_HOST}/api/auth/user/${store.state?.userObj?.user?.idUser}`, {
    firstName: formData.firstName,
    surname: formData.surname,
    description: formData.description,
    city: formData.city,
    school: formData.school,
    work: formData.work,
    website: formData.website,
  }, { headers: { Authorization: `Bearer ${store.state.accessToken}`}});

  return updateUserRes;
}
</script>
