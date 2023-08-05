<template>
  <form v-on:submit.prevent>
      <div class="field">
        <label for="title"> Title </label>
        <input name="title" type="text" v-model="title"/>
      </div>
      <div class="field">
          <label for="messsageText">Message</label>
          <input name="messageText" type="text" v-model="message"/>
      </div>
      <div class = "actions">
          <button type="submit" v-on:click ="editMessage()">Edit message</button>
      </div>
  </form>
</template>

<script>
import forumService from '../services/ForumService';
export default {
    name: "update-message",
  props: ["topicId", "messageId"],
  data() {
    return {
      title: "",
      messageText: ""
    };
  },
  methods: {
    editMessage() {
      const message = {
        id: this.messageId,
        title: this.title,
        messageText: this.messageText
      };
      forumService.editMessage(message.id, message).then( response => {
        if(response.status === 200) {
          this.$router.push( {
            name: 'Messages',
            params: { id: message.topicId}
          })
        }
      }).catch(error => console.error(error))
     
    }
  },
  created() {
    forumService
      .get(this.messageId)
      .then(response => {
        this.$store.commit("SET_ACTIVE_MESSAGE", response.data);
        this.title = response.data.title;
        this.messageText = response.data.messageText;
      })
      .catch(error => {
        if (error.response.status == 404) {
          this.$router.push({name: 'NotFound'});
        }
      });
  },
  deleteMessage(id) {
       forumService.delete(id).then( response => {
        if(response.status == 200) {
          this.$store.commit("DELETE_MESSAGE", id);
        }
      }).catch(error => console.error(error))}
  }


</script>

<style>

</style>