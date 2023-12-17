<script>
import BaseSlots from "@/components/slots/baseSlots.vue";

export default {
  components: {
    BaseSlots
  },
  data() {
    return {
      slotMessage: 'Slot Message ',
    }
  }
}
</script>

<template>
  <header>
    <img alt="Vue logo" class="logo" src="./assets/logo.svg" width="125" height="125"/>
  </header>

  <main>
    <BaseSlots v-slot="slotProps">
      <template v-slot:compA> <!--v-slot 可以简写成 # -->
        <div>slots component -------</div>
      </template>
      <template v-slot:compB>
        <div>slots content +++++++++</div>
      </template>
      <template #compC>
        <div>{{ slotMessage }}</div>
      </template>
      <template #compD="slotProps">
        <div>{{ slotMessage }} - {{ slotProps.childSlotMsg }}</div>
      </template>
    </BaseSlots>
  </main>
</template>

<style scoped>
header {
  line-height: 1.5;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }
}
</style>
