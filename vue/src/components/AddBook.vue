<template>

  <div class="page-container">
    <header class="header">
      <div class="logo-container">
      <router-link :to="{ name: 'home' }" >
        <img class="logo-image" src="../assets/image.png" alt="Logo" />
      </router-link>
</div>

      <div class="login-logout">
        <router-link v-bind:to="{ name: 'logout' }" class="login-logout-button" v-if="$store.state.token != ''">Sign In/Sign Out</router-link>
      </div>
    </header>
<main class="main">
    <div class="book-data" v-if="$store.state.user.username === 'admin'">
      <h2 class="add-book-title">Add a Book</h2>
      <input class="input-field" type="text" placeholder="Title" v-model="book.book_name" />
      <input class="input-field" type="text" placeholder="Author" v-model="book.author" />
      <input class="input-field" type="text" placeholder="Second Author" v-model="book.author_second" />
      <input class="input-field" type="text" placeholder="Description" v-model="book.description" />
      <input class="input-field" type="text" placeholder="Series" v-model="book.series" />
      <!-- <input class="input-field" type="text" placeholder="Book ID" v-model="book.book_id" /> -->
      <input class="input-field" type="date" placeholder="Release Date" v-model="book.release_date" />
      <input class="input-field" type="date" placeholder="Date added to Collection" v-model="book.date_added_to_collection" />

      <button class="add-button" v-on:click="saveBook();$router.push({ name: 'BookLibrary' })"  >Add Book</button>
    </div>
    <h2 v-else class="auth-required">Must Be Authorized</h2>
    </main>


<nav class="nav">
                     <!-- NAVIGATION MENU CODE -->
      <nav class="nav-container">
        <div class="nav-item" @click="$router.push({ name: 'home' })" v-if="$store.state.token != ''">
          <span class="nav-box"></span>
          <img src="../assets/icons8-home-60.png" alt="Home" class="nav-icon">
          <span class="nav-text">Home</span>
        </div>

        <div class="nav-item" @click="$router.push({ name: 'Topic' })" v-if="$store.state.token != ''">
          <span class="nav-box"></span>
          <img src="../assets/icons8-keyboard-50.png" alt="Forum" class="nav-icon">
          <span>Forum</span>
        </div>

        <div class="nav-item" @click="$router.push({ name: 'MyBookShelf' })" v-if="$store.state.token != ''">
          <span class="nav-box"></span>
          <img src="../assets/icons8-bookcase-50.png" alt="BookShelf" class="nav-icon">
          <span>My BookShelf</span>
        </div>

        <div class="nav-item" @click="$router.push({ name: 'BookLibrary' })" v-if="$store.state.token != ''">
          <span class="nav-box"></span>
          <img src="../assets/icons8-library-50.png" alt="BookLibrary" class="nav-icon">
          <span>Book Library</span>
        </div>

        <div class="nav-item" @click="$router.push({ name: 'TriviaCorner' })" v-if="$store.state.token != ''">
          <span class="nav-box"></span>
          <img src="../assets/icons8-game-50.png" alt="TriviaCorner" class="nav-icon">
          <span>Trivia Corner</span>
        </div>

      </nav>
 </nav>
  </div>
</template>


<script>
import bookService from "../services/BookService.js"
export default {
  data() {
    return {
      book: {
        book_name: "",
        author: "",
        author_second: "",
        description: "",
        series: "",
        // book_id: "",
        release_date: "",
        date_added_to_collection: ""
      },
    };
  },

  methods:{

    saveBook(){
      bookService.addBook(this.book).then(()=> {
        this.book.book_name="";
        this.book.author="";
        this.book.author_second=""
        this.book.series="";
        this.book.book_id="";
        this.book.date_added_to_collection="";
        this.book.release_date="";
        this.book.description="";
      }).catch(console.error);
    }

  }




};
</script>

<style scoped>
/* Layout and General Styling */
.page-container {
  display: grid;
  grid-template-rows: auto auto 1fr auto;
  grid-template-columns: auto 1fr auto;
  grid-template-areas:
    "header header header"
    "nav main main"
    "nav main main"
    "footer footer footer";
  min-height: 100vh;
  font-family: Arial, sans-serif;
}

/* Book Data Form Styling */
.book-data {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  background-color: #ffffff;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  width: 80%;
  max-width: 500px;
  margin: 0 auto;
}

.add-book-title {
  font-size: 24px;
  margin-bottom: 20px;
  color: #d14d04;
}

.input-field {
  width: 100%;
  padding: 15px;
  font-size: 16px;
  margin-bottom: 20px;
  border: 2px solid #ccc;
  border-radius: 8px;
}

.add-button {
  background-color: #d14d04;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.2s;
}

.add-button:hover {
  background-color: rgb(255, 102, 0);
  transform: scale(1.05);
}


.auth-required {
  text-align: center;
  font-size: 18px;
  color: #ff3860;
  margin-top: 20px;
}

/* Header Styling */
.header {
  grid-area: header;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 1rem;
  background-color: #ffffff;
}

.logo img {
  width: 550px;
  height: auto;
}

.logo-image {
  width: 300px;
  height: auto;
}
.logo-container {
  display: flex;
  align-items: center;
}

.sign-out-container {
  display: flex;
  align-items: center;
}

.login-logout-button {
  padding: 0.5rem 1rem;
  border: none;
  background-color: rgb(209, 77, 4);
  color: white;
  font-weight: bold;
  font-family: sans-serif;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s, transform 0.3s;
}

.login-logout-button:hover {
  background-color: rgb(255, 102, 0);
  transform: scale(1.05);
}

/* App Name Styling */
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
</style>