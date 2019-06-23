<template>
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
              type="button">Switch</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PersonSelect',
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
