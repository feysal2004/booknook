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
   
      </div>
  </form>
</template>

<script>
import forumService from '../services/ForumService';
// import topicPage from '../components/Topic.vue';

export default {
  
  props: ["topicId", "messageId"],
  data() {
    return {
      title: "",
      messageText: ""
    };
  },
  methods: {
    
    getMessages(){
      forumService.messageList()
    },
    getTopicName() {
      forumService;
    }
    
  },
 
  
};
</script>

<style>

</style>