<template>
  <div class="container">
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
    <button
      type="button"
      class="btn btn-primary"
      @click="savePerson">
      Submit
    </button>
    <button
      type="button"
      class="btn btn-danger"
      @click="deletePerson"
      v-if="this.personAlreadyExists">
      Delete
    </button>
  </div>
</template>

<script>

import * as axios from 'axios';

export default {
  name: 'PersonPage',
  props: ['id'],
  data() {
    return {
      personAlreadyExists: false,
      person: {
        id: null,
        name: null,
        sex: null,
      },
    };
  },
  methods: {
    savePerson() {
      if (this.personAlreadyExists) {
        axios.post('http://localhost:8081/api/v1/people', this.person)
          .then(() => {
          });
      } else {
        axios.post('http://localhost:8081/api/v1/people', this.person)
          .then((response) => {
            this.$router.replace({
              name: 'PersonPage',
              params: {
                id: response.data.id,
              },
            });
          });
      }
    },
    deletePerson() {
      axios.delete(`http://localhost:8081/api/v1/people/${this.person.id}`)
        .then(() => {
          this.$router.replace({
            name: 'PeopleListPage',
          });
        });
    },
  },
  mounted() {
    this.personAlreadyExists = this.id || this.id === 0;
    if (this.personAlreadyExists) {
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
