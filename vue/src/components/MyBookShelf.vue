<template>
  <div class="myBookShelf">
    <header class="header">
      <router-link :to="{ name: 'home' }" class="logo">
        <img class="logo-image" src="../assets/image.png" alt="Logo" />
      </router-link>


      <div class="login-logout">
        <router-link v-bind:to="{ name: 'logout' }" class="login-logout-button" v-if="$store.state.token != ''">Sign In/Sign Out</router-link>
      </div>
    </header>


    <main class="main">
      <h4>Here are my unread books</h4>
      <div class="book-container">
      <div v-for="unreadBook in $store.state.unread" v-bind:key="unreadBook.bookshelf_book_id" class="book-box">
        <img :src="unreadBook.thumbnail" alt="" class="book-cover" />
        <h5>{{ unreadBook.book_name }}</h5>
        <p class="book-author">{{ unreadBook.author }}</p>
        <button v-on:click="markBookAsRead(unreadBook.bookshelf_book_id)">Mark as Read</button>
      </div>
      </div>

      <h5>Full List of My Books</h5>
      <div class="book-container">
      <div v-for="book in $store.state.bookShelf" v-bind:key="book.bookshelf_book_id" class="book-box">
        <img :src="book.thumbnail" alt="" class="bookCover" />
        <h5>{{ book.book_name }}</h5>
        <p class="book-author">{{ book.author }}</p>
        <button v-on:click="removeFromMyBookshelf(book)">Remove Book</button>
      </div>
      </div>
    </main>
    <nav class="nav">
      <!-- NAVIGATION MENU CODE -->
      <nav class="nav">
        <div
          class="nav-item"
          @click="$router.push({ name: 'home' })"
          v-if="$store.state.token != ''"
        >
          <span class="nav-box"></span>
          <img src="../assets/icons8-home-60.png" alt="Home" />
          <span class="nav-text">Home</span>
        </div>
        <div
          class="nav-item"
          @click="$router.push({ name: 'Topic' })"
          v-if="$store.state.token != ''"
        >
          <span class="nav-box"></span>
          <img src="../assets/icons8-keyboard-50.png" alt="Forum" />
          <span>Forum</span>
        </div>
        <div
          class="nav-item"
          @click="$router.push({ name: 'MyBookShelf' })"
          v-if="$store.state.token != ''"
        >
          <span class="nav-box"></span>
          <img src="../assets/icons8-bookcase-50.png" alt="BookShelf" />
          <span>My BookShelf</span>
        </div>
        <div
          class="nav-item"
          @click="$router.push({ name: 'BookLibrary' })"
          v-if="$store.state.token != ''"
        >
          <span class="nav-box"></span>
          <img src="../assets/icons8-library-50.png" alt="BookLibrary" />
          <span>Book Library</span>
        </div>
        <div
          class="nav-item"
          @click="$router.push({ name: 'TriviaCorner' })"
          v-if="$store.state.token != ''"
        >
          <span class="nav-box"></span>
          <img src="../assets/icons8-library-50.png" alt="TriviaCorner" />
          <span>Trivia Corner</span>
        </div>
        <div
          class="nav-item"
          @click="$router.push({ name: 'addBook' })"
          v-if="$store.state.user.username === 'admin'"
        >
          <span class="nav-box"></span>
          <img src="../assets/icons8-plus-48.png" alt="AddBook" />
          <span>Add Book</span>
        </div>
      </nav>
    </nav>
  </div>
</template>

<script>
import bookService from "../services/BookService.js";
import bookshelfService from "../services/BookShelfService.js";

export default {
  methods: {
    getMyBooksFromDatabase() {
      bookService.getBooksFromBookshelf().then((response) => {
          this.$store.commit("SET_BOOKSHELF_FROM_DATABASE", response.data);
        }).catch(console.error);
    },
    getUnreadBooksFromDatabase() {
      bookshelfService.getUnreadBooks().then(response => {
        this.$store.commit("SET_UNREAD_BOOKS", response.data);
        console.log("here", this.$store.state.unread);
      }).catch(console.log);
    },
    markBookAsRead(bookId) {
      bookshelfService.changeBookToRead(bookId).then(() => {
        this.getUnreadBooksFromDatabase();
      }).catch(console.error);
    },
    removeFromMyBookshelf(book) {
      bookshelfService.deleteFromMyBookshelf(book).then(() => {
        this.getMyBooksFromDatabase();
      }).catch(console.error);
    },  
  },
  created() {
    this.getMyBooksFromDatabase();
    this.getUnreadBooksFromDatabase();
    console.log("unread books:", this.$store.state.unread);
    console.log("bookshelf books:", this.$store.state.bookShelf);
  },
};
</script>

<style>
.myBookShelf {
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

/* Header Styles */
.header {
  grid-area: header;
  background-color: #ffffff;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 1rem;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.logo-image {
  width: 300px;
  height: auto;
}

/* Main Content Styles */
.main {
  grid-area: main;
  flex-direction: column;
  align-items: center;
  padding: 1rem;
}

h4 {
  font-size: 1.5rem;
  margin-bottom: 1rem;
}

h5 {
  font-size: 1.2rem;
  margin-top: 2rem;
  margin-bottom: 1rem;
}

/* NAV MENU CSS CODE */
.nav-item {
  position: relative;
  display: flex;
  align-items: center;
  justify-content: flex-start;
  cursor: pointer;
  font-family: Impact, Haettenschweiler, "Arial Narrow Bold", sans-serif;
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

.book-container {
  display: flex;
  justify-content: space-between;
  gap: 5px;
  padding: 0 10px;
  margin-top: 20px;
}

.book-box {
  /* Book box styles */
  background-color: #fff;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 23%; /* Adjusted width */
  padding: 10px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
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
  width: 100px;
  height: auto;
  margin-bottom: 0.5rem;
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

.button-group {
  display: flex;
  justify-content: space-around;
  width: 100%;
  margin-top: 10px;
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

.logo-image {
  width: 300px;
  height: auto;
}
</style>