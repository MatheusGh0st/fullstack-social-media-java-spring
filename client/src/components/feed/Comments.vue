<template>
  <div class="">
    <!-- WRITE -->
    <div class="flex items-center gap-4">
      <img
        src="https://images.pexels.com/photos/2645245/pexels-photo-2645245.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
        alt=""
        width="32px"
        height="32px"
        class="w-8 h-8 rounded-full"
      />
      <div
        class="flex-1 flex items-center justify-between bg-slate-100 rounded-xl text-sm px-6 py-2 w-full"
      >
        <input
          type="text"
          placeholder="Write a comment..."
          class="bg-transparent outline-none flex-1"
        />
        <img
          src="../../../public/emoji.png"
          alt=""
          width="16px"
          height="16px"
          class="cursor-pointer"
        />
      </div>
    </div>
    <!-- COMMENTS -->
    <div class="">
      <!-- COMMENT -->
      <div class="flex gap-4 justify-between mt-6">
        <!-- AVATAR -->
        <img
          src="https://images.pexels.com/photos/2645245/pexels-photo-2645245.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
          alt=""
          width="40px"
          height="40px"
          class="w-10 h-10 rounded-full"
        />
        <!-- DESC -->
        <div class="flex flex-col gap-2 flex-1">
          <span class="font-medium">Bernice Spencer</span>
          <p>
            Lorem ipsum dolor sit amet, officia excepteur ex fugiat
            reprehenderit enim labore culpa sint ad nisi LoremLorem pariatur
            mollit ex esse exercitation amet. Nisi anim cupidatat excepteur
            officia. Reprehenderit nostrud nostrud ipsum Lorem est aliquip amet
            voluptate voluptate dolor minim nulla est proident. Nostrud officia
            pariatur ut officia. Sit irure elit esse ea nulla sunt ex occaecat
            reprehenderit commodo officia dolor Lorem duis laboris cupidatat
            officia voluptate. Culpa proident adipisicing id nulla nisi laboris
            ex in Lorem sunt duis officia eiusmod. Aliqua reprehenderit commodo
            ex non excepteur duis sunt velit enim. Voluptate laboris sint
            cupidatat ullamco ut ea consectetur et est culpa et culpa duis.
          </p>
          <div class="flex items-center gap-8 text-xs text-gray-500 mt-2">
            <div class="flex items-center gap-4">
              <img
                src="../../../public/like.png"
                alt=""
                width="12px"
                height="12px"
                class="cursor-pointer w-4 h-4"
              />
              <span class="text-gray-300">|</span>
              <span class="text-gray-500">123 likes</span>
            </div>
            <div class="">Reply</div>
          </div>
        </div>
        <div class=""></div>
        <!-- ICON -->
        <img
          src="../../../public/more.png"
          alt=""
          class="cursor-pointer w-4 h-4"
          width="16px"
          height="16px"
        />
      </div>
    </div>
  </div>
</template>

<script setup></script>
