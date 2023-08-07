<template>
  <div class="add-new-message">
    <button class="btnAddMessage"> Add Message</button>
    <router-link :to="{ name: 'postMessage' }">Create New Post</router-link>
      
      <button class ="btnAddMessage"> Add Message</button>
      
      <router-link :to="{name:'postMessage'}">Create New Post</router-link>

    <div>
      <h3 class="message-title">{{ message.title }}</h3>
      <p class="message-body">{{ message.messageText }}</p>

      <router-link :to="{ name: 'EditMessage', params: { messageId: message.id } }" class="btnEditMessage"> Edit Message</router-link>
      <button class="btnDeleteMessage" v-on:click="deleteMessage(message.id)">Delete</button>
    </div>
  </div>
</template>

<script>
export default{
 //import forumService from '../services/ForumService'

    name:"forumService",
      methods: {

      }
};
</script>

<style>

</style>