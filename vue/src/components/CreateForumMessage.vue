<template>
  <div>

<form v-on:submit.prevent>
    <div class="field">
        <label for= "title">Title</label>
        <input type = "text" name="title" v-model="message.title"/>

    </div>
    <div class ="field">
         <label for="messageText">Message</label>
         <input type="text" name="messageText" v-model="message.messageText" />
    </div>
    <div class = "actions">
         <button type="submit" v-on:click="postMessage()">Post Message</button>
    </div>
</form>

  </div>
</template>

<script>
import forumService from "../services/ForumService";
export default {
    name: "post-message",
  props: ["topicId"],
  data() {
    return {
      message: {
        id: Math.floor(Math.random() * (1000 - 100) + 100),
        title: "",
        messageText: ""
      }
    };
  },
  methods: {
    postMessage() {
        forumService.postMessage(this.message).then( response => {
        if(response.status === 201) {
          this.$router.push( {
            name: 'Messages',
            params: { id: this.message.id }
          })
        }
      }).catch( error => console.error(error))

    }
  }

}
</script>

<style>

</style>