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
      <h1 class="page-title">My BookShelf</h1>

      <h2 class="horizontal-book-box-text">
          <span class="app-name-text">Here Are My </span>
          <span class="app-name-accent">Unread Books</span>
        </h2>
      <div class="book-container">
        
        <div class="horizontal-book-box">
        <div v-for="unreadBook in $store.state.unread" v-bind:key="unreadBook.bookshelf_book_id" class="book-content">
            <div class="book-content">
          <img :src="unreadBook.thumbnail" alt="" class="book-cover" />
          <h5>{{ unreadBook.book_name }}</h5>
          <p class="book-author">{{ unreadBook.author }}</p>
          <button class="action-button orange" v-on:click="markBookAsRead(unreadBook.bookshelf_book_id)">Mark as Read</button>
        </div>
      </div>
     </div>
    </div>

<h2 class="horizontal-book-box-text">
          <span class="app-name-text">Here Are My </span>
          <span class="app-name-accent">Read Books</span>
        </h2>
      <div class="book-container">
        <div class="horizontal-book-box">
          <div v-for="readBook in $store.state.readBooks" v-bind:key="readBook.bookshelf_book_id" class="book-content">
            <div class="book-content">
            <img :src="readBook.thumbnail" alt="" class="book-cover" />
            <h5>{{ readBook.book_name }}</h5>
            <p class="book-author">{{ readBook.author }}</p>
          </div>
        </div>
      </div>
    </div>

<h2 class="horizontal-book-box-text">
          <span class="app-name-text">Full List of </span>
          <span class="app-name-accent">My Books:</span>
        </h2>
      <div class="book-container">
        <div class="horizontal-book-box">
          <div v-for="book in $store.state.bookShelf" v-bind:key="book.bookshelf_book_id" class="book-content">
            <div class="book-content">
              <img :src="book.thumbnail" alt="" class="bookCover" />
              <h5>{{ book.book_name }}</h5>
              <p class="book-author">{{ book.author }}</p>
            </div>
          </div>
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
          <img src="../assets/icons8-game-50.png" alt="TriviaCorner" />
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
    getReadBooksFromDatabase() {
      bookshelfService.getReadBooks().then(response => {
        this.$store.commit("SET_READ_BOOKS", response.data);
      }).catch(console.error);
    },
    markBookAsRead(bookId) {
      bookshelfService.changeBookToRead(bookId).then(() => {
        this.getUnreadBooksFromDatabase();
        this.getReadBooksFromDatabase();
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
    this.getReadBooksFromDatabase();
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

.page-title {
  text-align: center;
  font-size: 40px;
  font-family: Arial, sans-serif;
  margin-bottom: 20px;
  color: rgb(209, 77, 4);
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

/* Button Styles */
.action-button {
  background-color: rgb(209, 77, 4);
  color: white;
  font-weight: bold;
  font-family: Arial, sans-serif;
  border: none;
  border-radius: 5px;
  padding: 5px 10px;
  cursor: pointer;
  transition: background-color 0.3s, transform 0.3s;
  margin-top: 10px;
}

.action-button:hover {
  background-color: rgb(255, 102, 0);
  transform: scale(1.05);
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

/* BOOK BOXES */
.horizontal-book-box {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  overflow-x: auto;
  max-width: 1200px;
  margin: 0 auto;
  gap: 20px;
  padding: 10px;
  align-items: flex-start;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  box-sizing: auto;
  transition: transform 0.3s, box-shadow 0.3s;
}

 @media (max-width: 768px) {
    .book-list {
      max-width: 100%;
    }
  }

.horizontal-book-box::-webkit-scrollbar {
  display: none; 
}

.horizontal-book-box-text {
  font-family: Arial, sans-serif;
  font-size: 25px;
  color: #333;
  text-align: center;
  margin-top: 10px;
}

.book-container {
  background-color: #fff;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 15px;
  padding: 0 10px;
  border-radius: 10px;
  margin-top: 20px;
  transition: transform 0.3s, box-shadow 0.3s;
}

.book-container:hover {
  transform: translateY(-1px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
}

.book-content {
  font-family: Arial, sans-serif;
  flex-direction: column;
  align-items: center;
  text-align: center;
  display: flex;
  gap: 1px;
  width: 245px;
  height: auto;
}

.book-content:hover {
  transform: translateY(-1px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
}

</style>