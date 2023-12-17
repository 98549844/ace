<script>
export default {
  data() {
    return {
      childSlotMessage: 'child Slot Message !!!',
    }
  }
}
</script>

<template>
  <div>base slots</div>
  <slot name="compB">default value B</slot>
  <slot name="compA">default value A</slot>
  <slot name="compC">default value C</slot>
  <slot name="compD" :childSlotMsg="childSlotMessage">default value D</slot>
</template>

