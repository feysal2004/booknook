<template>
  <div class="add-new-message">
    
      
      <button class ="btnAddMessage"> Add Message</button>
      
      <router-link :to="{name:'postMessage'}">Create New Post</router-link>

    <div>
        
        <h3 class="message-title">{{message.title}}</h3>
        <p class="message-body">{{message.messageText}}</p>

        <router-link :to="{name: 'EditMessage'}" class="btnEditMessage"> Edit Message</router-link>
        <button class="btnDeleteMessage" v-on:click ="deleteMessage(message.id)">Delete</button>


        


    </div> 



  </div>
</template>

<script>
 import forumService from '../services/ForumService'
 export default {

    name:"forumService",
    props: {
      message:{
        type: Object,
        required:true,
      },
    }, 
     methods: {
    delete(id) {
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