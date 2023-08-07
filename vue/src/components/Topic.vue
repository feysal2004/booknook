<template>
  <div class="topic-list">
    <h1>Forum Board</h1>
    <table>
      <thead>
        <tr>
          <th>Topic</th>
          <th>Edit</th>
          <th>Delete</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="topic in filteredTopics" v-bind:key="topic.id">
          <td width="80%">
            <router-link
              v-bind:to="{ name: 'Messages', params: { id: topic.id } }"
            >{{ topic.title }}</router-link>
          </td>
          <td>
            <router-link :to="{ name: 'EditTopic', params: {id: topic.id} }">Edit</router-link>
          </td>
          <td>
            <a href="#" v-on:click="deleteTopic(topic.id)">Delete</a>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import topicService from "../services/TopicService.js";

export default {
  name: "topic-list",
  methods: {
    getTopics() {
      topicService.list().then(response => {
        this.$store.commit("SET_TOPICS", response.data);
      });
    },
    deleteTopic(id) {
      topicService.delete(id)
        .then(() => {
          this.$store.commit('SET_TOPICS', this.$store.state.topics.filter(topic => topic.id !== id));
        })
        .catch(() => {
          alert('Failed to delete topic. Please try again.');
        });
    }
  },
  created() {
    this.getTopics();
  },
  computed: {
    filteredTopics() {
      return this.$store.state.topics;
    }
  }
};
</script>

<style>

</style>