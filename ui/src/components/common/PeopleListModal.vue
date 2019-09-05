<template>
  <div class="modal fade"
       tabindex="-1"
       role="dialog"
       aria-labelledby="exampleModalLabel"
       aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
          <button type="button"
                  class="close"
                  data-dismiss="modal"
                  aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div class="list-group">
            <a href="#"
               class="list-group-item list-group-item-action"
               data-dismiss="modal"
               v-for="person in people"
               v-bind:key="person.id"
               @click="onPersonSelect(person)">
              {{person.name}}
            </a>
          </div>
          <pagination
            v-bind:totalPages="totalPages"
            v-on:page-switch="fetchPeople"/>
        </div>
        <div class="modal-footer">
          <button type="button"
                  class="btn btn-secondary"
                  data-dismiss="modal">Close
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import * as axios from 'axios';
import Pagination from '@/components/utils/Pagination';

export default {
  name: 'PeopleListModal',
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
      axios.get('/data-storage/api/v1/people', {
        params: {
          page: pageNumber - 1,
        },
      })
        .then((response) => {
          this.people = response.data.content;
          this.totalPages = response.data.totalPages;
        });
    },
    onPersonSelect(person) {
      this.$emit('person-selected', person);
    },
  },
};
</script>

<style scoped>

</style>
