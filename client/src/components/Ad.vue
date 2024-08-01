<template>
  <div class="p-4 bg-white rounded-lg shadow-md text-sm">
    <!-- TOP -->
    <div class="flex items-center justify-between text-gray-500 font-medium">
      <span>Sponsored Ads</span>
      <img src="../../public/more.png" alt="" width="16px" height="16px" />
    </div>
    <!-- BOTTOM -->
    <div class="flex flex-col mt-4" :class="{
                'gap-2': size === 'sm',
                'gap-4': size !== 'sm'
                }">
      <div class="relative w-full" :class="{
                  'h-24': size === 'sm',
                  'h-36': size === 'md',
                  'h-48': size !== 'sm' && size !== 'md'
                  }"> 
        <img
            src="https://images.pexels.com/photos/10928747/pexels-photo-10928747.jpeg?auto=compress&cs=tinysrgb&w=800&h=750&dpr=1"
            alt="Description of the image"
            style="width: 100%; height: 100%;"
            class="rounded-lg object-cover"
            />
      </div>
      <div class="flex items-center gap-4">
        <img 
          src="https://images.pexels.com/photos/10928747/pexels-photo-10928747.jpeg?auto=compress&cs=tinysrgb&w=800&h=750&dpr=1"
          alt=""
          width="24px"
          height="24px"
          class="rounded-full w-6 h-6 object-cover"
        />
        <span class="text-blue-500 font-medium">BigChef Lounge</span>
      </div>
      <p
        :class="{ 'text-xs': size === 'sm',
                  'text-sm': size !== 'sm'
        }">
      {{ size === 'sm' ? 
        'Lorem ipsum dolor sit amet, qui minim labore adipisicing minim sint cillum sint consectetur cupidatat.' : 
        size === 'md' ? 
        'Lorem ipsum dolor sit amet, qui minim labore adipisicing minim sint cillum sint consectetur cupidatat. AAAAAAAAAAAA' : 
        'Lorem ipsum dolor sit amet, officia excepteur ex fugiat reprehenderit enim labore culpa sint ad nisi Lorem pariatur mollit ex esse exercitation amet. Nisi anim cupidatat excepteur officia. Reprehenderit nostrud nostrud ipsum Lorem est aliquip amet voluptate voluptate dolor minim nulla est proident. Nostrud officia pariatur ut officia. Sit irure elit esse ea nulla sunt ex occaecat reprehenderit commodo officia dolor Lorem duis laboris cupidatat officia voluptate. Culpa proident adipisicing id nulla nisi laboris ex in Lorem sunt duis officia eiusmod. Aliqua reprehenderit commodo ex non excepteur duis sunt velit enim. Voluptate laboris sint cupidatat ullamco ut ea consectetur et est culpa et culpa duis.' }}
      </p>
      <button class="bg-gray-200 text-gray-500 p-2 text-xs rounded-lg">
        Learn more
      </button>
    </div>
  </div>
</template>

<script setup>
  import { defineProps } from "vue";

  defineProps({
    size: {
    type: String,
    required: true,
    validator: (value) => {
      return ['sm', 'md', 'lg'].includes(value);
      }
    }
  })
</script>
