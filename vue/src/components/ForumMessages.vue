<template>
  <form v-on:submit.prevent>
    <h1>
      {{ topicId }}
    </h1>
    <h2>New Title</h2>
    <div class="field">
      <h4>Add new message:</h4>
      <textarea name="newPost" id="newMessage" cols="70" rows="8"></textarea>
      <button v-on:click="saveMessage()">Add Message</button>
    </div>
    <div>
      <table>
        <thead>
          <tr>
            <th>Messages</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="messages in filteredMessages"
            v-bind:key="messages.messageTextId"
          >
            <td width="80%">
              {{ message.messageText }}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </form>
</template>

<script>
import forumService from "../services/ForumService";
// import topicPage from '../components/Topic.vue';

export default {
  props: ["topicId", "messageId"],
  data() {
    return {
      title: "",
      messageText: "",
    };
  },
  methods: {
    getMessages() {
      forumService.messageList().then(response => {
        this.$store.commit("SET_MESSAGES", response.data);
      });
    },
    getTopicName() {
      forumService;
    },
    saveMessage() {
      forumService.createForumMessage(this.message);
     
       
    },
  },
  created(){
    this.getMessages();
  },
  computed: {
    filteredMessages(){
      return this.$store.state.messages;
    }
  }
};
</script>

<style>
</style>