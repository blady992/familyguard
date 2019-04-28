<template>
  <div>
    People list
    <br />
    <ul>
      <li v-for="person in people" v-bind:key="person.id">{{person.name}}</li>
    </ul>
    <router-link to="/person">Create new person</router-link>
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
