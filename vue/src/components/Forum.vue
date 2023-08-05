<template>
  <div class="add-new-message">
      <h2>
      <button class ="btnAddMessage"> Add Message</button>
      </h2>
      <router-link :to="{name:'postMessage'}">Create New Post</router-link>

    <div>
        
        <h3 class="message-title">{{message.title}}</h3>
        <p class="message-body">{{message.messageText}}</p>

        <router-link :to="{name: 'EditMessage'}" class="btnEditMessage"> Edit Message</router-link>
        <button class="btnDeleteMessage" v-on:click ="deleteMessage(Message.Id)">Delete</button>


        


    </div>



  </div>
</template>

<script>
import forumService from '../services/ForumService'
export default {

    name:"forum",
     methods: {
    deleteMessage(id) {
       forumService.delete(id).then( response => {
        if(response.status == 200) {
          this.$store.commit("DELETE_MESSAGE", id);
        }
      }).catch(error => console.error(error))}
  }


}
</script>

<style>

</style>