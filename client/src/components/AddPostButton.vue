<template>
  <button 
    class="bg-blue-500 p-2 mt-2 rounded-md text-white disabled:bg-blue-300 disabled:cursor-not-allowed" 
    :disabled="!pending"
  >
    <div class="flex items-center gap-2">
      <div 
        class="inline-block h-[10px] w-[10px] animate-spin rounded-full border-2 border-white-300 border-solid border-current border-e-transparent align-[-0.125em] text-surface motion-reduce:animate-[spin_1.5s_linear_infinite]" 
        v-if="pending" 
      />
      {{ pending ? 'Sending' : 'Send' }}
    </div>
  </button>
</template>

<script setup>
import { defineProps, defineEmits, watch, ref } from "vue";

const props = defineProps({
  modelValue: {
    type: String,
    required: true,
  },
});

const emit = defineEmits(['update:modelValue']);
const pending = ref(false); // Local state for pending

// Watch for changes in modelValue and update pending state
watch(() => props.modelValue, (newValue) => {
  // Set pending to true if modelValue has content, false if it's empty
  pending.value = newValue.length > 0;
});

// Optional: Emit updates for modelValue if needed
watch(() => props.modelValue, (newValue) => {
  emit('update:modelValue', newValue);
});
</script>
