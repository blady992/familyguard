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
    <label for="personMother">Mother</label>
    <person-select id="personMother" v-model="person.mother"/>
    <label for="personFather">Father</label>
    <person-select id="personFather" v-model="person.father"/>
    <div v-if="person.spouses">
      <p>Spouses</p>
      <ul>
        <li v-for="spouse in person.spouses"
            v-bind:key="spouse.id">{{spouse.name}}</li>
      </ul>
    </div>
    <div v-if="person.children">
      <p>Children</p>
      <ul>
        <li v-for="child in person.children"
            v-bind:key="child.id">{{child.name}}</li>
      </ul>
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

import PersonSelect from '@/components/people/PersonSelect';

export default {
  name: 'PersonPage',
  components: { PersonSelect },
  props: ['id'],
  data() {
    return {
      personAlreadyExists: false,
      person: {
        id: null,
        name: null,
        sex: null,
        mother: null,
        father: null,
        spouses: [],
        children: [],
      },
    };
  },
  methods: {
    savePerson() {
      if (this.personAlreadyExists) {
        axios.post('http://localhost:8080/data-storage/api/v1/people', this.person)
          .then(() => {
          });
      } else {
        axios.post('http://localhost:8080/data-storage/api/v1/people', this.person)
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
      axios.delete(`http://localhost:8080/data-storage/api/v1/people/${this.person.id}`)
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
      axios.get(`http://localhost:8080/data-storage/api/v1/people/${this.id}`)
        .then((response) => {
          this.person = response.data;
          this.person.spouses = this.person.spouses || [];
          this.person.children = this.person.children || [];
        });
    }
  },
};
</script>

<style scoped>

</style>
