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
                @click="onPersonChange()">Clear
        </button>
        <button class="btn btn-outline-secondary"
                type="button"
                data-toggle="modal"
                :data-target="`#${this.id}PeopleListModal`">Switch
        </button>
      </div>
    </div>
    <people-list-modal :id="`${this.id}PeopleListModal`" v-on:person-selected="onPersonChange"/>
  </div>
</template>

<script>
import PeopleListModal from '@/components/common/PeopleListModal';

export default {
  name: 'PersonSelect',
  components: { PeopleListModal },
  props: ['value', 'id'],
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
