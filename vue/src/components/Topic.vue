<template>
  <div class="topic-list">
    <header class="header">
      </header>
    <main class="main">
    <h1 class="page-title">Forum Board</h1>
    <div class="add-topic">
      <h3>Add a new topic</h3>
      <div class="search-box">
        <textarea name="addTopic" id="addForumTopic" rows="3" v-model="topic.topicName" placeholder="Enter your topic"></textarea>
        <button class="submit-button" v-on:click="saveTopic()">Submit</button>
      </div>
    </div>
    <table class="topic-table">
      <thead>
        <tr>
          <th>Topics</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="topic in filteredTopics" v-bind:key="topic.topicId">
          <td>
            <router-link
              class="topic-link"
              v-bind:to="{ name: 'forumMessages', params: { id: topic.topicId } }">
              {{ topic.topicName }}
            </router-link>
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
        <div class="nav-item" @click="$router.push({ name: 'Topic' })" v-if="$store.state.token != ''">
            <span class="nav-box"></span>
          <img src="../assets/icons8-keyboard-50.png" alt="Forum">
          <span>Forum</span>
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
        <div class="nav-item" @click="$router.push({ name: 'AddBook' })" v-if="$store.state.token != ''">
            <span class="nav-box"></span>
          <img src="../assets/icons8-plus-48.png" alt="AddBook">
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
      topicService.createNewForumTopic(this.topic);
      // Clears out the textbox
      this.getTopics();
       
    },
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
  margin-bottom: 20px;
  color: rgb(209, 77, 4);
}


.add-topic {
  background-color: rgb(175, 174, 174);
  padding: 10px;
  border-radius: 5px;
  margin-bottom: 20px;
  text-align: center;
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
  padding: 10px;
  border-bottom: 2px solid rgb(209, 77, 4);
  color: rgb(209, 77, 4);
}

.topic-table td {
  padding: 10px;
  border-bottom: 1px solid #ddd;
}

.topic-link {
  color: rgb(209, 77, 4);
  text-decoration: none;
  transition: color 0.2s;
}

.topic-link:hover {
  color: #e65c00;
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



.main {
  grid-area: main;
  padding: 5rem;
}
</style>