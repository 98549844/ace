<script>
import SearchComp from "@/components/comp v-model/searchComp.vue";

export default {
  data() {
    return {
      searchResult: ''
    }
  },
  components: {
    SearchComp
  },
  methods: {
    getWatch(data) {
      this.searchResult = data;
    }
  }

}
</script>

<template>
  <div>main</div>
  <div>{{ searchResult }}</div>
  <SearchComp @searchEvent="getWatch"></SearchComp>
</template>
