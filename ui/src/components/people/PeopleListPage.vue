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

export default {
  name: 'PeopleListPage',
  data() {
    return {
      people: [],
    };
  },
  mounted() {
    axios.get('http://localhost:8081/api/v1/people')
      .then((response) => {
        this.people = response.data.content;
      });
  },
};
</script>

<style scoped>

</style>
