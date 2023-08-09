<template>

  <div class="page-container">
    <header class="header">
</header>
<main class="main">
    <div class="book-data" v-if="$store.state.user.username === 'admin'">
      <h2 class="add-book-title">Add a Book</h2>
      <input class="input-field" type="text" placeholder="Title" v-model="book.book_name" />
      <input class="input-field" type="text" placeholder="Author" v-model="book.author" />
      <input class="input-field" type="text" placeholder="Second Author" v-model="book.author_second" />
      <input class="input-field" type="text" placeholder="Description" v-model="book.description" />
      <input class="input-field" type="text" placeholder="Series" v-model="book.series" />
      <input class="input-field" type="text" placeholder="Book ID" v-model="book.book_id" />
      <input class="input-field" type="date" placeholder="Release Date" v-model="book.release_date" />
      <input class="input-field" type="date" placeholder="Date added to Collection" v-model="book.date_added_to_collection" />

      <button class="add-button" v-on:click="saveBook()">Add Book</button>
    </div>
    <h2 v-else class="auth-required">Must Be Authorized</h2>
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
        book_id: "",
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
}

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
  background-color:#d14d04;
}

.auth-required {
  text-align: center;
  font-size: 18px;
  color: #ff3860;
  margin-top: 20px;
}
</style>