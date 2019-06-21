<template>
  <div>
    <div class="form-group">
      <label for="personName">Name</label>
      <input type="text" class="form-control" id="personName" v-model="person.name">
    </div>
    <div class="form-group">
      <label for="personSex">Sex</label>
      <select class="form-control" id="personSex" v-model="person.sex">
        <option v-bind:value="'MALE'">Male</option>
        <option v-bind:value="'FEMALE'">Female</option>
      </select>
    </div>
    <button type="button" class="btn btn-primary" v-on:click="savePerson">Submit</button>
  </div>
</template>

<script>

import * as axios from 'axios';

export default {
  name: 'PersonPage',
  props: ['id'],
  data() {
    return {
      person: {
        id: null,
        name: null,
        sex: null,
      },
    };
  },
  methods: {
    savePerson() {
      if (this.person.id === null) {
        axios.post('http://localhost:8081/api/v1/people', this.person)
          .then((response) => {
            this.$router.replace({
              name: 'PersonPage',
              params: {
                id: response.data.id,
              },
            });
          });
      } else {
        axios.post('http://localhost:8081/api/v1/people', this.person)
          .then(() => {
          });
      }
    },
  },
  mounted() {
    if (this.id || this.id === 0) {
      axios.get(`http://localhost:8081/api/v1/people/${this.id}`)
        .then((response) => {
          this.person = response.data;
        });
    }
  },
};
</script>

<style scoped>

</style>
