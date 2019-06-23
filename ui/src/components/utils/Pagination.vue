<template>
  <div>
    <nav aria-label="Page navigation">
      <ul class="pagination">
        <li class="page-item" v-bind:class="{ disabled: current === 1 }">
          <a class="page-link" href="#" aria-label="Previous" @click="switchPage(current - 1)">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
        <li class="page-item"
            v-bind:class="{ active: page === current }"
            v-for="page in total"
            v-bind:key="page">
          <a class="page-link" href="#" @click="switchPage(page)">{{page}}</a>
        </li>
        <li class="page-item"
            v-bind:class="{ disabled: current === total }">
          <a class="page-link" href="#" aria-label="Next" @click="switchPage(current + 1)">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
export default {
  name: 'Pagination',
  props: ['totalPages'],
  watch: {
    totalPages: {
      immediate: true,
      handler(val) {
        this.total = parseInt(val, 10);
      },
    },
  },
  data() {
    return {
      total: 1,
      current: 1,
    };
  },
  methods: {
    switchPage(pageNumber) {
      this.current = pageNumber;
      this.$emit('page-switch', pageNumber);
    },
  },
};
</script>

<style scoped>

</style>
