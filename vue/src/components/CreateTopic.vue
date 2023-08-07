<template>
  <form v-on:submit.prevent>
    <div class="field">
      <label for="title">Title</label>
      <input type="text" v-model="topic.title" />
    </div>
    <div class="actions">
      <button type="submit" v-on:click="saveTopic()">Save Document</button>
    </div>
  </form>
</template>

<script>
import topicService from "../services/TopicService";

export default {
  name: 'create-topic',
  data() {
    return {
      topic: {
        id: Math.floor(Math.random() * (1000 - 100) + 100),
        title: ''
      }
    };
  },
  methods: {
    saveTopic() {
      topicService.create(this.topic)
        .then((response) => {
          if (response.status === 201) {
            this.$router.push({ name: 'Home' });
          } else {
            alert('Failed to create topic. Please try again.');
          }
        })
        .catch(() => {
          alert('Failed to create topic. Please try again.');
        });
    }
  }
};
</script>