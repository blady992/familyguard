<template>
  <div class="container">
    <div class="list-group">
      <router-link
        class="list-group-item list-group-item-action"
        v-for="event in events"
        v-bind:key="event.id"
        :to="{name: 'EventPage', params: {id: event.id}}"
        tag="a">
        {{event.name}}
      </router-link>
    </div>
    <keep-alive>
      <pagination
        v-bind:totalPages="totalPages"
        v-on:page-switch="fetchPeople"></pagination>
    </keep-alive>
    <router-link
      class="btn btn-outline-success"
      :to="{name : 'NewEventPage'}"
      tag="button">
      Create new event
    </router-link>
  </div>
</template>

<script>
import Pagination from '@/components/utils/Pagination';

export default {
  name: 'EventListPage',
  components: { Pagination },
  data() {
    return {
      totalPages: 1,
      events: [],
    };
  },
  mounted() {
    this.fetchPeople(1);
  },
  methods: {
    fetchPeople(pageNumber) {
      this.$http.get('http://localhost:8080/data-storage/api/v1/events', {
        params: {
          page: pageNumber - 1,
        },
      })
        .then((response) => {
          this.events = response.data.content;
          this.totalPages = response.data.totalPages;
        });
    },
  },
};
</script>

<style scoped>

</style>
