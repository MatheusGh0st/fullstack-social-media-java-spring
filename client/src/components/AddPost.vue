<template>
  <div class="p-4 bg-white rounded-lg flex gap-4 justify-between text-sm">
    <!-- AVATAR -->
    <img 
       src="https://images.pexels.com/photos/1840396/pexels-photo-1840396.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" 
       width="48px"
       height="48px"
       alt=""
       class="w-12 h-12 object-cover rounded-full"
       ><img/>
       <!-- POST -->
    <div class="flex-1">
      <!-- TEXT INPUT -->
      <div class="flex gap-4">
        <textarea placeholder="What's on your mind?" class="flex-1 bg-slate-100 rounded-lg"></textarea>
        <img
            src="../../public/emoji.png"
            alt=""
            width="20px"
            height="20px"
            class="w-5 h-5 cursor-pointer self-end"
            ><img/>
      </div>
        <!-- POST OPTIONS -->
        <div class="flex items-center gap-4 mt-4 text-gray-400 flex-wrap">
          <div class="flex items-center gap-2 cursor-pointer">
            <img 
               src="../../public/addimage.png"
               alt=""
               width="20px"
               height="20px"
               >Photo<img/>
          </div>
            <div class="flex items-center gap-2 cursor-pointer">
              <img 
                 src="../../public/addVideo.png"
                 alt=""
                 width="20px"
                 height="20px"
                 >Video<img/>
            </div>
              <div class="flex items-center gap-2 cursor-pointer">
                <img 
                   src="../../public/poll.png"
                   alt=""
                   width="20px"
                   height="20px"
                   >Poll<img/>
              </div>
                <div class="flex items-center gap-2 cursor-pointer">
                  <img 
                     src="../../public/addevent.png"
                     alt=""
                     width="20px"
                     height="20px"
                     >Event<img/>
                </div>
        </div>
    </div>
  </div>
</template>

<script setup>
</script>
