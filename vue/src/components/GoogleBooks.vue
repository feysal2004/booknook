<template>
  <div class="google-books">
    <header class="header">
      <router-link :to="{ name: 'home' }" >
        <img class="logo-image" src="../assets/image.png" alt="Logo" />
      </router-link>


      <div class="login-logout">
        <router-link v-bind:to="{ name: 'logout' }" class="login-logout-button" v-if="$store.state.token != ''">Sign In/Sign Out</router-link>
      </div>
    </header>

    <main class="main">
<h1 class="page-title">Book Library</h1>

      <!-- books from google books api -->
      <div class="search-box">
        <div class="search-container">
          <input class="search-bar" type="text" placeholder="Search" v-model="input" />
          <button class="submit-button" v-on:click="chooseBookSearch()">Search</button>
        </div>
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
            <img :src="book.volumeInfo.imageLinks ? book.volumeInfo.imageLinks.thumbnail : require('@/assets/360_F_462936689_BpEEcxfgMuYPfTaIAOC1tCDurmsno7Sp.jpg')" alt="" class="bookCover resizable-image" />
            <h2 class="book-title">{{ truncateTitle(book.volumeInfo.title, 10) }}</h2>
            <p class="book-author">{{ book.volumeInfo.authors ? book.volumeInfo.authors.join(', ') : 'Unknown Author'}}</p>
           
           <button class="submit-button" v-on:click="addToBookshelf(book)">Add to Bookshelf</button>
                     
          </div>
        </div>
      </div>

      <!-- books from internal database -->
      <div class="book-container-admin">
        <div v-for="book in $store.state.adminBooks" v-bind:key="book.bookId" class="book-box-admin" >
          <div class="book-content-admin">
            <img src="../assets/360_F_462936689_BpEEcxfgMuYPfTaIAOC1tCDurmsno7Sp.jpg" alt="" class="bookCover resizable-image" />  <!-- add no image icon -->
            <h2 class="book-title"> {{ truncateTitle(book.book_name, 10) }} </h2>
            <p class="book-author"> {{ book.author }} </p>

            <button class="submit-button" v-on:click="addToBookshelf(book)">Add to Bookshelf</button>
          </div>
        </div>
      </div>

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

        <div class="nav-item" @click="$router.push({ name: 'TriviaCorner' })" v-if="$store.state.token != ''">
          <span class="nav-box"></span>
          <img src="../assets/icons8-game-50.png" alt="TriviaCorner" class="nav-icon">
          <span>Trivia Corner</span>
        </div>

        <div class="nav-item" @click="$router.push({ name: 'addBook' })"  v-if="$store.state.user.username === 'admin'">
          <span class="nav-box"></span>
          <img src="../assets/icons8-plus-48.png" alt="AddBook" class="nav-icon">
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
      const alphabet = "abcdfghijklnopqrstvwxz"
      return alphabet[Math.floor(Math.random() * alphabet.length)]
    },

    getAdminAddedBooks() {
      return bookService.getBooksFromOurDatabase().then(response => {
        this.$store.commit("SET_ADMIN_ADDED_BOOKS", response.data);
      }).catch(console.error);
    },

    addToBookshelf(book){
      let newBook = {};
      // get bookTitle from book
      newBook.book_name = book.volumeInfo.title;
      // get author from book
      newBook.author = book.volumeInfo.authors ?book.volumeInfo.authors[0] : 'Unknown Author';
      // get thumbnail from book
      newBook.thumbnail = book.volumeInfo.imageLinks ? book.volumeInfo.imageLinks.thumbnail : require('@/assets/360_F_462936689_BpEEcxfgMuYPfTaIAOC1tCDurmsno7Sp.jpg');
      // get description from book
      newBook.description = book.volumeInfo.description;
      // newBook.isbn = book.volumeInfo.industryIdentifiers[0].identifier;

      return bookService.addBookToBookshelf(newBook);
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

.page-title {
  text-align: center;
  font-size: 35px;
  font-family: Arial, sans-serif;
  margin-bottom: 20px;
  color: rgb(209, 77, 4);
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
  width: 550px; 
  height: auto; 
}

.logo-image {
  width: 300px; 
  height: auto; 
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

.search-box {
  background-color: #f2f2f2;
  padding: 20px;
  border-radius: 10px;
  margin-bottom: 20px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
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
  border: 1px solid #ccc;
  flex: 1;
  font-size: 14px;
  font-family: Arial, sans-serif;
}

.submit-button {
  background-color: rgb(209, 77, 4);
  color: #fff;
  border: none;
  padding: 10px 15px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.2s;
  font-family: Arial, sans-serif;
  font-size: 14px;
}

.submit-button:hover {
  background-color: #e65c00;
}

.dropDown select {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-family: Arial, sans-serif;
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
  transition: transform 0.3s, box-shadow 0.3s;
}

.book-box:hover {
  transform: translateY(-5px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
}

.book-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
  font-family: Arial, sans-serif;
}

.book-cover {
  width: auto;
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

.submit-button {
  background-color: rgb(209, 77, 4);
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s, transform 0.3s;
  font-family: Arial, sans-serif;
}

.submit-button:hover {
  background-color: rgb(255, 102, 0);
  transform: scale(1.05);
}

.book-cover {
  width: auto;
  max-height: 150px; 
  margin: 0 auto;
}


.resizable-image {
  max-width: 235px; 
  height: auto;
}

.book-box-admin {
  background-color: #fff;
  width: 400px;
  padding: 10px;
  border-radius: 10px;
  box-shadow: 0 3px 6px black;
  text-align: center;
  font-family: Arial, sans-serif;
  margin-bottom: 20px;
  transition: transform 0.3s, box-shadow 0.3s;
}

.book-content-admin {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
  font-family: Arial, sans-serif;
}

.book-container-admin {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 15px;
  padding: 0 10px;
  flex-direction: row;
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