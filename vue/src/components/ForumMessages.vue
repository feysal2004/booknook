<template>
  <form v-on:submit.prevent>
    <h1>
      Forum Topic: {{ }}
    </h1>

    <div class="field">
      <textarea name="newPost" id="newMessage" cols="70" rows="8" placeholder="Enter your message here" v-model="newMessage.message_text" ></textarea>
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
            v-bind:key="messages.messageTextId">
            <td width="80%">
              {{ messages.message_text }}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </form>
</template>

<script>
import forumService from "../services/ForumService";
import topicService from '../services/TopicService';

export default {
  props: ["topicId", "messageId"],
  data() {
    return {
      title: "",
      message_text: "",
      newMessage: {
        message_text: "",
      }
    };
  },
  methods: {
    getMessages() {
      forumService.messageList(this.topicId).then(response => {
        this.$store.commit("SET_MESSAGES", response.data);
      }).catch(console.error);
    },
    getTopicName() {
      topicService.getTopicName(this.topicId);
    },
    saveMessage() {
      forumService.createForumMessage(this.topicId, this.newMessage).then( () => {
        this.newMessage.message_text = "";
        this.getMessages();
      }).catch(console.error);
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