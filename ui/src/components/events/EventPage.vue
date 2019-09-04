<template>
  <div class="container">
    <div class="form-group">
      <label for="eventName">Name</label>
      <input type="text" class="form-control" id="eventName" v-model="event.name">
    </div>
    <div class="form-group">
      <label for="eventDescription">Description</label>
      <input type="text" class="form-control" id="eventDescription" v-model="event.description">
    </div>
    <div v-if="event.participants">
      <p>Participants</p>
      <ul>
        <li v-for="participant in event.participants"
            v-bind:key="participant.id">
          <router-link :to="{name: 'PersonPage', params: {id: participant.id}}"
                       tag="a">{{participant.name}}</router-link>
        </li>
      </ul>
    </div>
    <button
      type="button"
      class="btn btn-primary"
      @click="saveEvent">
      Submit
    </button>
    <button
      type="button"
      class="btn btn-danger"
      @click="deleteEvent"
      v-if="this.eventAlreadyExists">
      Delete
    </button>
  </div>
</template>

<script>
import * as axios from 'axios';

export default {
  name: 'EventPage',
  props: ['id'],
  data() {
    return {
      eventAlreadyExists: false,
      event: {
        id: null,
        name: null,
        description: null,
        location: null,
        participants: [],
      },
    };
  },
  methods: {
    saveEvent() {
      if (this.eventAlreadyExists) {
        axios.post('http://localhost:8080/data-storage/api/v1/events', this.event)
          .then(() => {
          });
      } else {
        axios.post('http://localhost:8080/data-storage/api/v1/events', this.event)
          .then((response) => {
            this.$router.replace({
              name: 'EventPage',
              params: {
                id: response.data.id,
              },
            });
          });
      }
    },
    deleteEvent() {
      axios.delete(`http://localhost:8080/data-storage/api/v1/events/${this.event.id}`)
        .then(() => {
          this.$router.replace({
            name: 'EventsListPage',
          });
        });
    },
  },
  mounted() {
    this.eventAlreadyExists = this.id || this.id === 0;
    if (this.eventAlreadyExists) {
      axios.get(`http://localhost:8080/data-storage/api/v1/events/${this.id}`)
        .then((response) => {
          this.event = response.data;
          this.event.participants = this.event.participants || [];
        });
    }
  },
};
</script>

<style scoped>

</style>
