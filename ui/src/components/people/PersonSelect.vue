<template>
  <div>
    <div class="input-group mb-3">
      <input type="text"
             class="form-control"
             readonly
             v-model="personName">
      <div class="input-group-append">
        <button class="btn btn-outline-secondary"
                type="button"
                @click="onPersonChange()">Clear</button>
        <button class="btn btn-outline-secondary"
                type="button"
                data-toggle="modal"
                data-target="#peopleListModal">Switch</button>
      </div>
    </div>
    <people-list-modal id="peopleListModal" v-on:person-selected="onPersonChange"/>
  </div>
</template>

<script>
import PeopleListModal from '@/components/people/PeopleListModal';

export default {
  name: 'PersonSelect',
  components: { PeopleListModal },
  props: ['value'],
  data() {
    return {
      personName: '',
    };
  },
  watch: {
    value(newValue) {
      this.personName = newValue ? newValue.name : '';
    },
  },
  methods: {
    onPersonChange(person) {
      this.$emit('input', person);
    },
  },
};
</script>

<style scoped>
</style>
