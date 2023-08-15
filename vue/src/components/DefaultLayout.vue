<template>
  <div class="default-layout">
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






    <div class="trivia-container">
      <HomeTriviaVue />
    </div>


    <map class="map-container">
      <AddGoogleMapVue />
    </map>


    <main class="main">
      <!-- BOOK BOXES -->
      <div class="book-box">
        <!-- Content of the first book box -->
        <!-- By Series/Title -->
        <p>Look at a popular book series!  Now Showing {{this.series}}</p>
        <div v-for="book in $store.state.homePageSeries" v-bind:key="book.bookId" class="book-box">
          <div class="book-content">
            <img :src="book.volumeInfo.imageLinks.thumbnail" alt="" class="bookCover" />
            <h2 class="book-title">{{ truncateTitle(book.volumeInfo.title, 10) }}</h2>
            <p class="book-author">{{ book.volumeInfo.authors.join(', ') }}</p>           
          </div>
        </div>
        

      </div>
      <div class="book-box">
        <!-- Content of the second book box -->
        <!-- By Author -->
        <p>Look at a popular author!  Now Showing {{this.author}}</p>
        <div v-for="book in $store.state.homePageAuthors" v-bind:key="book.bookId" class="book-box">
          <div class="book-content">
            <img :src="book.volumeInfo.imageLinks.thumbnail" alt="" class="bookCover" />
            <h2 class="book-title">{{ truncateTitle(book.volumeInfo.title, 10) }}</h2>
            <p class="book-author">{{ book.volumeInfo.authors.join(', ') }}</p>           
          </div>
        </div>
      </div>
      <div class="book-box">
        <!-- Content of the third book box -->
        <!-- By Topic -->
        <p>Look at a popular topic!  Now Showing {{this.topics}}</p>
        <div v-for="book in $store.state.homePageTopics" v-bind:key="book.bookId" class="book-box">
          <div class="book-content">
            <img :src="book.volumeInfo.imageLinks.thumbnail" alt="" class="bookCover" />
            <h2 class="book-title">{{ truncateTitle(book.volumeInfo.title, 10) }}</h2>
            <p class="book-author">{{ book.volumeInfo.authors.join(', ') }}</p>           
          </div>
        </div>

      </div>
      <router-view />
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
        <div class="nav-item" @click="$router.push({ name: 'TriviaCorner' })" v-if="$store.state.token != ''">
          <span class="nav-box"></span>
          <img src="../assets/icons8-library-50.png" alt="TriviaCorner">
          <span>Trivia Corner</span>
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
import AddGoogleMapVue from '../components/AddGoogleMap.vue';
import HomeTriviaVue from '../components/HomeTrivia.vue';
import homePageService from '../services/HomePageService.js';

export default {
  name: "home",
  data() {
    return {
      author: this.randomizeAuthors(),
      series: this.randomizeSeries(),
      topics: this.randomizeTopics()
    }
  },
  components: {
    AddGoogleMapVue,
    HomeTriviaVue
  },

  methods: {

    getRandomizedBooksAuthor() {
      homePageService.getByAuthor(this.author).then(response => {
              this.$store.commit("SET_HOME_PAGE_AUTHORS", response.data.items);
          }).catch(console.error);
    },

    getRandomizedBooksSeries() {
      homePageService.getBySeries(this.series).then(response => {
              this.$store.commit("SET_HOME_PAGE_SERIES", response.data.items);
          }).catch(console.error);
    },

    getRandomizedBooksTopics() {
      homePageService.getBySeries(this.topics).then(response => {
              this.$store.commit("SET_HOME_PAGE_TOPICS", response.data.items);
          }).catch(console.error);
    },

    truncateTitle(title, words) {
      const titleWords = title.split(' ');
      if (titleWords.length > words) {
        return titleWords.slice(0, words).join(' ') + '...';
      }
      return title;
    },
      
    randomizeAuthors() {
      let authors = ["Tolkien", "Brandon Sanderson", "James Patterson", "Tom Clancy", "George R. R. Martin", "Stephen King" ];
      let randomNumber = Math.floor(Math.random() * authors.length);
      return authors[randomNumber];
    },
    randomizeSeries() {
      let series = ["Game of Thrones", "Harry Potter", "Dune"];
      let randomNumber = Math.floor(Math.random() * series.length);
      return series[randomNumber];
    },
    randomizeTopics() {
      let topics = ["Football", "Science", "Basketball", "History"];
      let randomNumber = Math.floor(Math.random() * topics.length);
      return topics[randomNumber];
    }
  },
  created() {
    this.getRandomizedBooksAuthor();
    this.getRandomizedBooksSeries();
    this.getRandomizedBooksTopics();
  }
};
</script>



<style scoped>
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

.trivia-container {
  grid-area: trivia;
  width: 300px; 
  height: 100%; 
  overflow: hidden; 
  margin-bottom: 20px;
}

.map-container {
  grid-area: map;
  width: 300px; 
  height: 100%; 
  overflow: hidden; 
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






/* BOOK BOXES */
.main {
  grid-area: main;
  display: flex;
  flex-direction: column; 
  justify-content: center; 
  align-items: center; 
  gap: 20px; 
  padding: 0.5rem;
}

.book-box {
  background-color: #fff;
  width: 50%; 
  padding: 10px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  margin-top: 200px;
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







.app-name {
  grid-area: app-name;
  text-align: center;
  padding: 1rem;
}

.main {
  grid-area: main;
  padding: 0.5rem;
}

.footer {
  grid-area: footer;
  text-align: center;
  padding: 1rem;
}

.nav {
  grid-area: nav;
  text-align: center;
  padding: 0.5rem;
}
</style>