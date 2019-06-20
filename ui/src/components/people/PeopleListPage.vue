<template>
  <div>
    People list
    <br/>
    <ul>
      <router-link
        v-for="person in people"
        v-bind:key="person.id"
        :to="{name: 'PersonPage', params: {id: person.id}}"
        tag="li">
        {{person.name}}
      </router-link>
    </ul>
    <router-link :to="{name : 'NewPersonPage'}">Create new person</router-link>
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
