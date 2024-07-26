<template>
  <div class="flex flex-col gap-4">
    <!-- USER -->
    <div class="flex items-center justify-between">
      <div class="flex items-center gap-4">
        <img 
           src="https://images.pexels.com/photos/20060267/pexels-photo-20060267/free-photo-of-rocks-on-sea-coast.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
           width="40px"
           height="40px"
           alt="" 
           class="w-10 h-10 rounded-full"
           ><img/>
           <span class="font-medium">Jack McJonhson</span>
      </div>
      <img src="../../public/more.png" width="16px" height="16px" alt="" />
    </div>
      <!-- DESC -->
      <div class="flex flex-col gap-4">
        <div class="w-full min-h-96 relative">
          <img 
          src="https://images.pexels.com/photos/14837630/pexels-photo-14837630.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
          fill
          alt=""
          class="object-cover rounded-md"
          ><img/>
        </div>
          <p>
          Lorem ipsum dolor sit amet, officia excepteur ex fugiat reprehenderit enim labore culpa sint ad nisi 
          Lorem pariatur mollit ex esse exercitation amet. Nisi anim cupidatat excepteur officia. 
          Reprehenderit nostrud nostrud ipsum Lorem est aliquip amet voluptate voluptate dolor minim nulla est proident.
          Nostrud officia pariatur ut officia. Sit irure elit esse ea nulla sunt ex occaecat reprehenderit commodo officia dolor
          LoremLorem duis laboris cupidatat officia voluptate. Culpa proident adipisicing id nulla nisi laboris ex in 
          Lorem sunt duis officia eiusmod. Aliqua reprehenderit commodo ex non excepteur duis sunt velit enim. 
          Voluptate laboris sint cupidatat ullamco ut ea consectetur et est culpa et culpa duis.
          </p>
      </div>
      <!-- INTERACTION -->
      <div class="flex items-center justify-between text-sm my-4">
        <div class="flex gap-8">
          <div class="flex items-center gap-4 bg-slate-50 p-2 rounded-xl">
            <img src="../../public/like.png" width="16px" height="16px" alt="" class="cursor-pointer" />
            <span class="text-gray-300">|</span>
            <span class="text-gray-500">123<span class="hidden md:inline"> Likes</span></span>
          </div>
          <div class="flex items-center gap-4 bg-slate-50 p-2 rounded-xl">
            <img src="../../public/comment.png" width="16px" height="16px" alt="" class="cursor-pointer" />
            <span class="text-gray-300">|</span>
            <span class="text-gray-500">123<span class="hidden md:inline"> Comments</span></span>
          </div>
        </div>
        <div class="">
          <div class="flex items-center gap-4 bg-slate-50 p-2 rounded-xl">
            <img src="../../public/share.png" width="16px" height="16px" alt="" class="cursor-pointer" />
            <span class="text-gray-300">|</span>
            <span class="text-gray-500">123<span class="hidden md:inline"> Shares</span></span>
          </div>
        </div>
      </div>
  </div>
</template>

<script setup>
  
</script>
