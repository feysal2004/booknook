<template>
  <div class="topic-list">
    <h1>Forum Board</h1>
    <h3>
      <p>Add a new topic</p>
      <textarea name="addTopic" id="addForumTopic" cols="40" rows="3" v-model=topic.topicName ></textarea>
      <button v-on:click="saveTopic()" >Submit</button>
    </h3>
    <table>
      <thead>
        <tr>
          <th>Topics</th>
          <!-- <th>Edit</th>
          <th>Delete</th> -->
        </tr>
      </thead>
      <tbody>
        <tr v-for="topic in filteredTopics" v-bind:key="topic.topicId">
          <td width="80%">
            <router-link
              v-bind:to="{ name: 'forumMessages', params: { id: topic.topicId } }">
               {{ topic.topicName }} 
            </router-link>
          </td>
          <!-- <td>
            <router-link :to="{ name: 'EditTopic', params: {id: topic.topicId} }">Edit</router-link>
          </td>
          <td>
            <a href="#" v-on:click="deleteTopic(topic.topicId)">Delete</a>
          </td> -->
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import topicService from "../services/TopicService.js";

export default {
  data () {
    return {
      topics: [],
      topic: {
        topicName: "",
      }
    }
  },
  methods: {
    getTopics() {
      topicService.list().then(response => {
        console.log("logging get topics data")
        console.log(response.data)
        this.$store.commit("SET_TOPICS", response.data);
      });
    },
    saveTopic() {
      topicService.createNewForumTopic(this.topic);
      // Clears out the textbox
      this.getTopics();
       
    },
    // deleteTopic(topicId) {
    //   topicService.delete(topicId)
    //     .then(() => {
    //       this.$store.commit('SET_TOPICS', this.$store.state.topics.filter(topic => topic.topicId !== topicId));
    //     })
    //     .catch(() => {
    //       alert('Failed to delete topic. Please try again.');
    //     });
    // }
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