<template>
  <div class="topic-details">
    <h1>{{ this.$store.state.activeTopic.title }}</h1>
    <router-link
      :to="{ name: 'AddMessage', params: {topicId: $store.state.activeTopic.id} }"
      class="addMessage"
    >Add New Message</router-link>
    <div
      v-for="message in this.$store.state.activeTopic.messages"
      v-bind:key="message.id"
      class="topic-message bubble"
    >
      <h3 class="message-title">{{ message.title }}</h3>
      <p class="message-body">{{ message.messageText }}</p>
      <router-link
        :to="{name: 'EditMessage', params: {topicId: $store.state.activeTopic.id, messageId: message.id} }"
        class="btnEditMessage"
      >Edit</router-link>
      <button class="btnDeleteMessage" v-on:click="deleteMessage(message.id)">Delete</button>
    </div>
  </div>
</template>

<script>
import topicService from "../services/TopicService.js";
import forumService from "../services/ForumService.js";

export default {
  name: "topic-details",
  props: {
    topicId: Number
  },
  methods: {
    deleteMessage(id) {
      forumService.delete(id)
        .then((response) => {
          if (response.status === 200) {
            this.$store.commit("DELETE_MESSAGE", id);
          } 
        })
    }
  },
  created() {
    topicService
      .get(this.topicId)
      .then(response => {
        this.$store.commit("SET_ACTIVE_TOPIC", response.data);
      })
      .catch(error => {
        if (error.response.status == 404) {
          this.$router.push({name: 'NotFound'});
        }
      });
  }
};
</script>