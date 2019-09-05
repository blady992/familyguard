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
    <button type="button"
            class="btn btn-success"
            data-toggle="modal"
            data-target="#peopleListModal"
            v-if="eventAlreadyExists">Add participant
    </button>
    <div v-if="event.participants">
      <label class="badge badge-pill badge-success align-middle"
             v-for="participant in event.participants"
             v-bind:key="participant.id">
        <router-link :to="{name: 'PersonPage', params: {id: participant.id}}"
                     tag="button" class="btn" style="padding: 0; opacity: .7;">
          {{participant.name}}
        </router-link>
        <button type="button"
                class="btn btn-sm btn-link close-icon"
                @click="deleteParticipant(participant.id)">
          <font-awesome-icon icon="times-circle"/>
        </button>
      </label>
    </div>
    <people-list-modal id="peopleListModal" v-on:person-selected="addParticipant"/>
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
import PeopleListModal from '@/components/common/PeopleListModal';

export default {
  name: 'EventPage',
  props: ['id'],
  components: {
    PeopleListModal,
  },
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
        axios.post('/data-storage/api/v1/events', this.event)
          .then((response) => {
            this.event = response.data;
          });
      } else {
        axios.post('/data-storage/api/v1/events', this.event)
          .then((response) => {
            this.$router.replace({
              name: 'EventPage',
              params: {
                id: response.data.id,
              },
            });
            this.event = response.data;
            this.eventAlreadyExists = true;
          });
      }
    },
    deleteEvent() {
      axios.delete(`/data-storage/api/v1/events/${this.event.id}`)
        .then(() => {
          this.$router.replace({
            name: 'EventListPage',
          });
        });
    },
    deleteParticipant(participantId) {
      axios.delete(`/data-storage/api/v1/events/${this.event.id}/participants/${participantId}`)
        .then(() => {
          this.event.participants =
            this.event.participants.filter(person => person.id !== participantId);
        });
    },
    addParticipant(participant) {
      axios.put(`/data-storage/api/v1/events/${this.event.id}/participants`, participant.id, {
        headers: {
          'Content-Type': 'application/json',
        },
      })
        .then(() => {
          this.event.participants.push(participant);
        });
    },
  },
  mounted() {
    this.eventAlreadyExists = this.id || this.id === 0;
    if (this.eventAlreadyExists) {
      axios.get(`/data-storage/api/v1/events/${this.id}`)
        .then((response) => {
          this.event = response.data;
          this.event.participants = this.event.participants || [];
        });
    }
  },
};
</script>

<style scoped>
.close-icon {
  padding: 0;
  background-color: transparent;
  border: 0;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  color: #000;
  text-shadow: 0 1px 0 #fff;
  opacity: .5;
}
</style>
