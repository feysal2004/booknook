<template>
  <div class="myBookShelf">
    <header class="header">
      <router-link :to="{ name: 'home' }" class="logo">
        <img
          class="logo-image"
          src="../assets/T0GNFLF6D-U0192MVUM7C-d3304dbc9516-512.png"
          alt="Logo"
        />
      </router-link>
      <div class="app-name">
        <span class="app-name-text">App</span>
        <span class="app-name-accent">Name</span>
      </div>
      <div class="login-logout">
        <router-link
          v-bind:to="{ name: 'logout' }"
          class="login-logout-button"
          v-if="$store.state.token != ''"
          >Sign In/Sign Out</router-link
        >
      </div>
    </header>

    <main>
      <h4>Here are my unread books</h4>
      <div v-for="book in $store.state.unRead" v-bind:key="book.bookId">
        <img :src="book.thumbnail" alt="" class="bookCover" />
        <h5>{{ book.book_name }}</h5>
        <p class="book-author">{{ book.author }}</p>
        <button v-on:click="moveToReadingList(book)">
          Move to Reading List
        </button>
      </div>

      <h5>Full List of My Books</h5>
      <div v-for="book in $store.state.bookShelf" v-bind:key="book.bookId">
        <img :src="book.thumbnail" alt="" class="bookCover" />
        <h5>{{ book.book_name }}</h5>
        <p class="book-author">{{ book.author }}</p>
        <button v-on:click="markAsRead(book)">Read</button>
        <button v-on:click="removeFromFullList(book)">Remove Book</button>
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
export default {
  methods: {
    getMyBooksFromDatabase() {
      bookService
        .getBooksFromBookshelf()
        .then((response) => {
          this.$store.commit("SET_BOOKSHELF_FROM_DATABASE", response.data);
        })
        .catch(console.error);
    },
    markAsRead(book) {
      this.$store.state.unRead.push(book);
      const index = this.$store.state.bookShelf.findIndex(
        (b) => b.bookId === book.bookId
      );
      if (index !== -1) {
        this.$store.state.bookShelf.splice(index, 1);
      }
    },
    moveToReadingList(book) {
      const index = this.$store.state.unRead.findIndex(
        (b) => b.bookId === book.bookId
      );
      if (index !== -1) {
        this.$store.state.unRead.splice(index, 1);
      }

      this.$store.state.bookShelf.push(book);
    },
    removeFromFullList: function (book) {
      const index = this.$store.state.bookShelf.findIndex(
        (b) => b.bookId === book.bookId
      );
      if (index !== -1) {
        this.$store.state.bookShelf.splice(index, 1);
      }
    },
  },
  created() {
    this.getMyBooksFromDatabase();
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
.unRead {
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
/* NAV MENU CSS CODE */
.nav-item {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  font-family: Impact, Haettenschweiler, "Arial Narrow Bold", sans-serif;
  margin-bottom: 1rem;
}
.default-layout {
  display: grid;
  grid-template-rows: auto auto 1fr auto;
  grid-template-columns: auto 1fr auto;
  grid-template-areas:
    "header header header"
    "nav main trivia"
    "nav main map"
    "footer footer footer";
  min-height: 100vh;
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
</style>