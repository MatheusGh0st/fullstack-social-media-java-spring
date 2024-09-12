<template>
  <div class="p-4 bg-white rounded-lg shadow-md text-sm flex flex-col gap-4">
    <!-- TOP -->
    <div class="flex justify-between items-center font-medium">
      <span class="text-gray-500">Birthdays</span>
      <router-link to="/" class="text-blue-500 text-xs">See all</router-link>
    </div>
    <!-- USER -->
    <div class="flex items-center justify-between">
      <div class="flex items-center gap-4">
        <img
          src="https://images.pexels.com/photos/21550488/pexels-photo-21550488/free-photo-of-azul-parede-muro-motocicleta.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
          alt=""
          width="40px"
          height="40px"
          class="w-10 h-10 rounded-full object-cover"
        />
        <span class="font-semibold">Wayne Burton</span>
      </div>
      <div class="flex gap-3 justify-end">
        <button class="bg-blue-500 text-white text-xs px-2 py-1 rounded-md">
          Celebrate
        </button>
      </div>
    </div>
    <!-- UPCOMING -->
    <div class="p-4 bg-slate-100 rounded-lg flex items-center gap-4">
      <img
        src="../../../public/gift.png"
        alt=""
        width="24px"
        height="24px"
        class="w-10 h-10 rounded-full object-cover"
      />
      <router-link to="/" class="flex flex-col gap-1 text-xs"></router-link>
      <span class="text-gray-700">Upcoming Birthdays</span>
      <span class="text-gray-500">See other 16 have upcoming birthdays</span>
    </div>
  </div>
</template>

<script setup></script>
