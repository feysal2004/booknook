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
    <h1 class="page-title">Forum Board</h1>
    <div class="add-topic">
      <h3>Add a new topic</h3>
      <div class="search-box">
        <textarea name="addTopic" id="addForumTopic" rows="3" v-model="topic.topicName" placeholder="Enter your topic"></textarea>
        <button class="submit-button" v-on:click="saveTopic()">Submit</button>
        <!-- <button v-on:click="deleteTopic(topic.topicId)">Delete Topic</button> -->
      </div>
    </div>
    <table class="topic-table">
      <thead>
        <tr>
          <th>Topics</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="topicset in filteredTopics" v-bind:key="topicset.topicId">
          <td>
            <router-link
              class="topic-link"
              v-bind:to="{ name: 'forumMessages', params: { id: topicset.topicId } }">
              {{ topicset.topicName }}
            </router-link>
              <button class="delete-topic-button" v-on:click="deleteTopic(topicset.topicId)"  v-if="$store.state.user.username === 'admin'">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
   
    </main>

        <nav class="nav">
                     <!-- NAVIGATION MENU CODE -->
        <nav class="nav">
        <div class="nav-item" @click="$router.push({ name: 'home' })" v-if="$store.state.token != ''">
            <span class="nav-box"></span>
          <img src="../assets/icons8-home-60.png" alt="Home">
            <span class="nav-text">Home</span>
        </div>
        <div class="nav-item" @click="$router.push({ name: 'MyBookShelf' })" v-if="$store.state.token != ''">
            <span class="nav-box"></span>
          <img src="../assets/icons8-bookcase-50.png" alt="BookShelf">
          <span>My BookShelf</span>
        </div>
        <div class="nav-item" @click="$router.push({ name: 'BookLibrary' })" v-if="$store.state.token != ''">
            <span class="nav-box"></span>
        <img src="../assets/icons8-library-50.png" alt="BookLibrary">
          <span>Book Library</span>
        </div>
        <div class="nav-item" @click="$router.push({ name: 'addBook' })" v-if="$store.state.token != ''">
            <span class="nav-box"></span>
          <img src="../assets/icons8-plus-48.png" alt="addBook">
          <span>Add Book</span>
        </div>
    </nav>
    </nav>
  </div>
</template>

<script>
import topicService from "../services/TopicService.js";

export default {
  data () {
    return {
      topics: [],
      topic: {
        topicName: "",
      }
    }
  },
  methods: {
    getTopics() {
      topicService.list().then(response => {
        this.$store.commit("SET_TOPICS", response.data);
      });
    },
    saveTopic() {
      topicService.createNewForumTopic(this.topic).then( () => {
          this.getTopics();
          this.topic.topicName = "";
      }).catch(console.error);      
    },

    deleteTopic(id){
      topicService.delete(id).then(() => {
        this.getTopics();
      }).catch(console.error)

    }
  },
  created() {
    this.getTopics();
  },
  computed: {
    filteredTopics() {
      return this.$store.state.topics;
    }
  }
};
</script>

<style>
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


.add-topic {
  background-color: rgb(175, 174, 174);
  padding: 10px;
  border-radius: 5px;
  margin-bottom: 20px;
  text-align: center;
  font-family: Arial, sans-serif;
}

.search-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-bottom: 10px;
}

#addForumTopic {
  width: 100%;
  padding: 5px;
  border: none;
  border-radius: 5px;
  resize: none;
  margin-bottom: 5px;
}

.add-topic h3 {
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

.topic-table {
  width: 100%;
  border-collapse: collapse;
}

.topic-table th {
  font-weight: bold;
  font-family: Arial, sans-serif;
  padding: 10px;
  border-bottom: 2px solid rgb(209, 77, 4);
  color: rgb(209, 77, 4);
}

.topic-table td {
  position: relative;
  padding: 10px;
  border-bottom: 1px solid #ddd;
}

.topic-link {
  color: rgb(209, 77, 4);
  text-decoration: none;
  transition: color 0.2s;
  font-family: Arial, sans-serif;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.topic-link:hover {
  color: #e65c00;

}



.delete-topic-button {
  background-color: rgb(209, 77, 4);
  color: white;
  border: none;
  padding: 5px 10px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.2s;
  font-family: Arial, sans-serif;
  position: absolute;
  top: 50%;
  right: 10px; 
  transform: translateY(-50%);
}

.delete-topic-button:hover {
  background-color: #e6003e;
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
  font-family:Arial, sans-serif;
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