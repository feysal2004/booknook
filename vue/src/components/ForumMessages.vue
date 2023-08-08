<template>
  <form v-on:submit.prevent>
      <h1  >
        {{ topicId }}
      </h1>
      <h2>New Title  </h2>
      <div class="field">
          <h4>Add new message: </h4>
          <textarea name="newPost" id="newMessage" cols="70" rows="8"></textarea>
      </div>
      <div>
        <!-- <router-link  >

        </router-link> -->
      </div>
  </form>
</template>

<script>
import forumService from '../services/ForumService';
import topicPage from '../components/Topic.vue';

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
    // createForumMessage() {
    //   forumService
    //     .getForumMessageId(this.messageId)
    //     .then((response) => {
    //       this.title = response.data.title;
    //       this.messageText = response.data.messageText;
    //     })
    //     .catch((error) => {
    //       if (error.response.status == 404) {
    //         this.$router.push({ name: 'NotFound' });
    //       }
    //     });
    // },
    getMessages(){
      forumService.messageList()
    },
    getTopicName() {
      return topicPage.topic.topicName;
    }
    
  },
  // created() {
  //   this.createForumMessage();
    
  // }
  
};
</script>

<style>

</style>