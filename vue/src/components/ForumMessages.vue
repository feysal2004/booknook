<template>
  <div class="topic-list">
        <header class="header">
          <router-link :to="{ name: 'home' }" class="logo">
      <img class="logo-image" src="../assets/T0GNFLF6D-U0192MVUM7C-d3304dbc9516-512.png" alt="Logo" />
    </router-link>

            <div class="app-name">
        <span class="app-name-text">App</span>
       <span class="app-name-accent">Name</span>
      </div>

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
          <tbody>
            <tr v-for="message in filteredMessages" :key="message.messageTextId">
              <td width="80%">{{ message.message_text }}</td>
                        <button>edit</button>

            </tr>
          </tbody>
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
// import topicService from '../services/TopicService';

export default {
  props: ["topicId", "messageId"],
  data() {
    return {
      title: "",
      message_text: "",
      newMessage: {
        message_text: "",
      },
      currentTopicName: ""
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
      forumService.createForumMessage(this.topicId, this.newMessage).then( () => {
        this.newMessage.message_text = "";
        this.getMessages();
      }).catch(console.error);
    },
  },
  created(){
    this.getMessages();
    this.getTopicName();
  },

  editMessage(){
    forumService.editForumMessage(this.messageId, this.message_text)

  },

  computed: {
    filteredMessages(){
      return this.$store.state.messages;
    }
  }
};
</script>

<style scoped>
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
}

.message-table {
  width: 100%;
  border-collapse: collapse;
}

.message-table th {
  font-weight: bold;
  font-family: Arial, sans-serif;
  padding: 10px;
  border-bottom: 2px solid rgb(209, 77, 4);
  color: rgb(209, 77, 4);
}

.message-table td {
  padding: 10px;
  border-bottom: 1px solid #ddd;
}

/* NAV MENU CSS CODE */
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



/* HEADER CSS CODE */
.header {
  grid-area: header;
 display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 1rem;
  background-color: #ffffff;
 
}

.logo img {
  width: 150px; 
  height: auto; 
}

.logo-image {
  width: 100px; 
  height: auto; 
  border-radius: 50%;
  overflow: hidden; 
}

/* SIGN IN SIGN OUT BUTTON CSS CODE */
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


/* APPNAME CSS CODE */
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



.main {
  grid-area: main;
  padding: 5rem;
}

</style>