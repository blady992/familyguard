<template>
  <div class="container">
    <div class="list-group">
      <router-link
        class="list-group-item list-group-item-action"
        v-for="person in people"
        v-bind:key="person.id"
        :to="{name: 'PersonPage', params: {id: person.id}}"
        tag="a">
        {{person.name}}
      </router-link>
    </div>
    <keep-alive>
      <pagination
        v-bind:totalPages="totalPages"
        v-on:page-switch="fetchPeople"></pagination>
    </keep-alive>
    <router-link
      class="btn btn-outline-success"
      :to="{name : 'NewPersonPage'}"
      tag="button">
      Create new person
    </router-link>
  </div>
</template>

<script>
import * as axios from 'axios';
import Pagination from '@/components/utils/Pagination';

export default {
  name: 'PeopleListPage',
  components: { Pagination },
  data() {
    return {
      totalPages: 1,
      people: [],
    };
  },
  mounted() {
    this.fetchPeople(1);
  },
  methods: {
    fetchPeople(pageNumber) {
      axios.get('http://localhost:8081/api/v1/people', {
        params: {
          page: pageNumber - 1,
        },
      })
        .then((response) => {
          this.people = response.data.content;
          this.totalPages = response.data.totalPages;
        });
    },
  },
};
</script>

<style scoped>

</style>
