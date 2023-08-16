<template>
  <div class="google-books">
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
      <!-- books from google books api -->
      <div class="search-container">
        <input class="search-bar" type="text" placeholder="Search" v-model="input" />
        <button class="submit-button" v-on:click="chooseBookSearch()" >Search</button>

        <div class="dropDown">
          <select id="dropdown" v-model="selectedSearchMethod">
            <option value="option1">General Search</option>
            <option value="option2">By Title</option>
            <option value="option3">By Author</option>
            <option value="option4">In Relation To</option>
          </select>
          <p id="selectedOption"></p>
        </div>
      </div>

      <div class="book-container">
        <div v-for="book in $store.state.bookInput" v-bind:key="book.bookId" class="book-box">
          <div class="book-content">
            <img :src="book.volumeInfo.imageLinks.thumbnail" alt="" class="bookCover" />
            <h2 class="book-title">{{ truncateTitle(book.volumeInfo.title, 10) }}</h2>
            <p class="book-author">{{ book.volumeInfo.authors ? book.volumeInfo.authors.join(', ') : 'Unknown'}}</p>
           
           <button v-on:click="addToLibrary(book)">Add to library</button>
           
           
          </div>
        </div>
      </div>

      <!-- books from internal database -->
      <div class="book-container">
        <div v-for="book in $store.state.adminBooks" v-bind:key="book.bookId" class="book-box" >
          <div class="book-content">
            <img src="" alt="" class="bookCover" />  <!-- add no image icon -->
            <h2 class="book-title"> {{ truncateTitle(book.book_name, 10) }} </h2>
            <p class="book-author"> {{ book.author }} </p>
          </div>
        </div>
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
        <div class="nav-item" @click="$router.push({ name: 'MyBookShelf' })" v-if="$store.state.token != ''">
          <span class="nav-box"></span>
          <img src="../assets/icons8-bookcase-50.png" alt="BookShelf">
          <span>My BookShelf</span>
        </div>
        <div class="nav-item" @click="$router.push({ name: 'addBook' })"  v-if="$store.state.user.username === 'admin'">
          <span class="nav-box"></span>
          <img src="../assets/icons8-plus-48.png" alt="AddBook">
          <span>Add Book</span>
        </div>
        </nav>
    </nav>

    <footer class="footer">
    </footer>

  </div>

</template>

<script>
import bookService from '../services/BookService';
import googleBookAPI from "../services/GoogleBookApiService";

export default {
    data() {
        return {
            input: this.generateRandomLetter(),
            dropDownInput: this.newestToOldest(),
            selectedSearchMethod: 'option1'
        }
    },

    methods: {
      chooseBookSearch() {
        if (this.selectedSearchMethod == 'option1') {
          this.getBookSearch();
        } else if (this.selectedSearchMethod == 'option2') {
          this.getBooksByTitle();
        } else if (this.selectedSearchMethod == 'option3') {
          this.getBooksByAuthor();
        } else if (this.selectedSearchMethod == 'option4') {
          this.getBooksBySubject();
        }  
      },

      getBookSearch() {
          googleBookAPI.getBookListBySearchQuery(this.input).then(response => {
              this.$store.commit("SET_GOOGLE_BOOK_SEARCH", response.data.items);
              this.input = "";
          }).catch(console.error);
      },

      getBooksByTitle() {
        googleBookAPI.getBookListByTitle(this.input).then(response => {
              this.$store.commit("SET_GOOGLE_BOOK_SEARCH", response.data.items);
              this.input = "";
          }).catch(console.error);
      },

      getBooksByAuthor() {
        googleBookAPI.getBookListByAuthor(this.input).then(response => {
              this.$store.commit("SET_GOOGLE_BOOK_SEARCH", response.data.items);
              this.input = "";
          }).catch(console.error);
      },

      getBooksBySubject() {
        googleBookAPI.getBookListBySubject(this.input).then(response => {
              this.$store.commit("SET_GOOGLE_BOOK_SEARCH", response.data.items);
              this.input = "";
          }).catch(console.error);
      },

      truncateTitle(title, words) {
        const titleWords = title.split(' ');
        if (titleWords.length > words) {
          return titleWords.slice(0, words).join(' ') + '...';
        }
        return title;
      },

      generateRandomLetter() {
        const alphabet = "aitxz"
        return alphabet[Math.floor(Math.random() * alphabet.length)]
      },

      getAdminAddedBooks() {
        return bookService.getBooksFromOurDatabase().then(response => {
          this.$store.commit("SET_ADMIN_ADDED_BOOKS", response.data);
        }).catch(console.error);
      },

      addToLibrary(book){
        
        this.$store.dispatch('addToLibrary',book)
      
      },
      newestToOldest(){
        
      }
    },
    created() {
        this.getBookSearch();
        this.getAdminAddedBooks();
    }
}
</script>

<style scoped>
.google-books {
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

.main {
  grid-area: main;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0.5rem;
}

.search-container {
  display: flex;
  align-items: center;
  gap: 10px;
  margin-bottom: 20px;
  font-family: Arial, sans-serif;
}

.search-bar {
  border: 1px solid #ccc;
  padding: 8px;
  border-radius: 5px;
  flex: 1;
  font-size: 14px;
  font-family: Arial, sans-serif;
}

.submit-button {
  background-color: rgb(209, 77, 4);
  color: #fff;
  border: none;
  padding: 8px 15px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.2s;
  font-family: Arial, sans-serif;
  font-size: 14px;
}

.submit-button:hover {
  background-color: #e65c00;
}

.book-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 15px;
  padding: 0 10px;
}

.book-box {
  background-color: #fff;
  width: calc(22% - 15px);
  padding: 10px;
  border-radius: 10px;
  box-shadow: 0 3px 6px black;
  text-align: center;
  font-family: Arial, sans-serif;
  margin-bottom: 20px;
}

.book-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
  font-family: Arial, sans-serif;
}

.book-cover {
  width: 80px;
  height: auto;
  margin: 0 auto;
}

.book-title {
  font-size: 1rem;
  margin: 0;
  white-space: normal;
  overflow: hidden;
  display: -webkit-box;
  -webkit-line-clamp: 2; 
  -webkit-box-orient: vertical;
}

.book-author {
  color: #888;
  margin: 0;
  font-family: Arial, sans-serif;
}

.nav {
  grid-area: nav;
  text-align: center;
  padding: 0.5rem;
}

.footer {
  grid-area: footer;
  text-align: center;
  padding: 1rem;
}
</style>