<template>
  <div class="topic-list">
    <header class="header">
      <router-link :to="{ name: 'home' }" >
        <img class="logo-image" src="../assets/image.png" alt="Logo" />
      </router-link>


      <div class="login-logout">
        <router-link v-bind:to="{ name: 'logout' }" class="login-logout-button" v-if="$store.state.token != ''">Sign In/Sign Out</router-link>
      </div>
    </header>




    <main class="main">
      <h1 class="page-title">Forum Topic: {{ this.currentTopicName }}</h1>

      <div class="add-message">
        <h3>Add a new message</h3>
        <div class="message-box">
          <textarea
            name="newPost"
            id="newMessage"
            rows="3"
            v-model="newMessage.message_text"
            placeholder="Enter your message here"
          ></textarea>
          <button class="submit-button" v-on:click="saveMessage()">Submit</button>
        </div>
      </div>

      <div class="message-list">
        <table class="message-table">
          <thead>
            <tr>
              <th>Messages</th>
            </tr>
          </thead>
          <div id="content">
            <tbody>
              <tr v-for="(message, index) in filteredMessages" :key="message.message_id">
                <td :id = "message.message_id" width="80%" v-bind:currentMessageId=message.message_id ><span v-if="activeIndex !== index">{{ message.message_text }} </span>
                <input type="text" v-model="editObject.message_text" v-if="activeIndex === index"  ></td>
                <td class="edit-button-cell">
  <div class="button-group">
    <button class="action-button" v-on:click.prevent.stop="editMessage(index, message)">Edit</button>
    <button class="action-button" v-on:click="submitEditedMessage(message.message_id, editObject)" v-if="activeIndex === index">Save</button>
    <button class="action-button delete-button" v-on:click="deleteMessage(message.message_id)" v-if="$store.state.user.username === 'admin'">Delete</button>
  </div>
</td>
              </tr> 
            </tbody>
          </div>
        </table>
      </div>
    </main>

    <nav class="nav">
                     <!-- NAVIGATION MENU CODE -->
      <nav class="nav">
        <div class="nav-item" @click="$router.push({ name: 'home' })" v-if="$store.state.token != ''">
          <span class="nav-box"></span>
          <img src="../assets/icons8-home-60.png" alt="Home">
          <span class="nav-text">Home</span>
        </div>
        <div class="nav-item" @click="$router.push({ name: 'Topic' })" v-if="$store.state.token != ''">
          <span class="nav-box"></span>
          <img src="../assets/icons8-keyboard-50.png" alt="Forum">
          <span>Forum</span>
        </div>
      </nav>
    </nav>
  </div>
</template>

<script>
import forumService from "../services/ForumService";

export default {
  props: ["topicId", "messageId"],
  data() {
    return {
       showButton: true,
      showMessage : false,
      activeIndex: -1,
      currentMessageId: 1,
      title: "",
      message_text: "",
      newMessage: {
        message_text: "",
        message_written_by:""
        
      },
      currentTopicName: "",
      editObject: {
        message_text: "",
       message_written_by:""
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
      forumService.getTopicName(this.topicId).then(response => {
        this.currentTopicName = response.data;
      });
    },
    saveMessage() {
      this.newMessage.message_written_by = this.$store.state.user.username;
      forumService.createForumMessage(this.topicId, this.newMessage).then( () => {
        this.newMessage.message_text = "";
        this.getMessages();
      }).catch(console.error);
    },
    editMessage(index, message){
     
      this.editObject.message_text = message.message_text;
      this.activeIndex = index
      
    },
    submitEditedMessage(message_id, editedMessage) {
     
      forumService.editForumMessage(message_id, editedMessage).then( () => {
        
        location.reload();
      }).catch(console.error)
    },
    toggleButtons(button){
        this.showButton = !this.showButton === button ? true : button;
    },

    toggleEditRow(index){
       this.activeIndex = this.activeIndex === index ? -1 : index;
    },
    deleteMessage(id){
      forumService.deleteForumMessage(id).then(() => {
        this.getMessages();
      }).catch(console.error)
    }
  },
  
  created(){
    this.getMessages();
    this.getTopicName();
    this.editObject.message_written_by = this.$store.state.user.username;
  },
  computed: {
    filteredMessages(){
      return this.$store.state.messages;
    }
  }
};
</script>

<style scoped>
/* General styles */
.topic-list {
  display: grid;
  grid-template-rows: auto auto 1fr auto;
  grid-template-columns: auto 1fr auto;
  grid-template-areas:
    "header header header"
    "nav main main"
    "nav main main"
    "footer footer footer";
  min-height: 100vh;
}

.page-title {
  text-align: center;
  font-size: 35px;
  font-family: Arial, sans-serif;
  margin-bottom: 20px;
  color: rgb(209, 77, 4);
}

.add-message {
  background-color: rgb(175, 174, 174);
  padding: 10px;
  border-radius: 5px;
  margin-bottom: 20px;
  text-align: center;
  font-family: Arial, sans-serif;
  width: 90%;
}

.message-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-bottom: 10px;
}

#newMessage {
  width: 100%;
  padding: 5px;
  border: none;
  border-radius: 5px;
  resize: none;
  margin-bottom: 5px;
}

.add-message h3 {
  font-size: 18px;
  font-family: Arial, sans-serif;
  margin-bottom: 10px;
  color: #fff;
}

.submit-button {
  background-color: rgb(209, 77, 4);
  color: #fff;
  border: none;
  padding: 5px 15px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.2s;
  font-family: Arial, sans-serif;
}

.submit-button:hover {
  background-color: #e65c00;
}

.message-list {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.message-table {
  width: 100%;
  border-collapse: collapse;
}

.message-table th {
  font-weight: bold;
  font-family: Arial, sans-serif;
  padding: 1px;
  border-bottom: 2px solid rgb(209, 77, 4);
  color: rgb(209, 77, 4);
}

.message-table td {
  padding: 10px;
  border-bottom: 1px solid #ddd;
  width: 92%;
}

/* Navigation styles */
.nav-item {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
  margin-bottom: 1rem;
}

.nav {
  grid-area: nav;
  display: flex;
  flex-direction: column;
  align-items: left;
  justify-content: left;
  padding: 1rem;
}

.nav-box {
  position: absolute;
  left: -45px;
  top: 0;
  width: 150px;
  height: 100%;
  background-color: rgb(175, 174, 174);
  border-radius: 5px;
  z-index: -1;
  transition: background-color 0.3s;
}

.nav-item:hover .nav-box {
  background-color: #555;
}

.nav-text {
  margin-top: 0.5rem;
}

/* Header styles */
.header {
  grid-area: header;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 1rem;
  background-color: #ffffff;
}


.logo-image {
  width: 300px;
  height: auto;
}

/* Sign In/Sign Out Button styles */
.login-logout-button {
  padding: 0.5rem 1rem;
  border: none;
  background-color: rgb(209, 77, 4);
  color: white;
  font-weight: bold;
  font-family: Arial, sans-serif;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s, transform 0.3s;
}

.login-logout-button:hover {
  background-color: rgb(255, 102, 0);
  transform: scale(1.05);
}

/* App Name styles */
.app-name {
  grid-area: app-name;
  text-align: center;
  padding: 1rem;
  font-family: Arial, sans-serif;
  font-size: 4rem;
}

.app-name-text {
  color: rgb(175, 174, 174);
}

.app-name-accent {
  color: rgb(209, 77, 4);
}

/* Action Button styles */
.action-button {
  background-color: rgb(209, 77, 4);
  color: white;
  border: none;
  padding: 5px 10px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.2s;
  font-family: Arial, sans-serif;
  margin-right: 5px;
}

.action-button:hover {
  background-color: #e65c00;
}

.button-group {
  display: flex;
  gap: 5px;
}

.main {
  grid-area: main;
  padding: 5rem;
}
</style>