<template>
  <div class="default-layout">
    <header class="header">
      <router-link :to="{ name: 'home' }" class="logo">
        <img class="logo-image" src="../assets/image.png" alt="Logo" />
      </router-link>


      <div class="login-logout">
        <router-link v-bind:to="{ name: 'logout' }" class="login-logout-button" v-if="$store.state.token != ''">Sign In/Sign Out</router-link>
      </div>
    </header>



    <map class="map-container">
      <div class="map-box">
      <AddGoogleMapVue />
      </div>
      

  <div class="bookstore-h-container">  
    <div class="bookstore-highlight">
      <h1 class="highlight-header">Bookstore Highlight</h1> 
      <img :src="featuredBookstore.image" class="bookstore-image" :alt="featuredBookstore.name" />
      <div class="bookstore-details">
        <h3 class="bookstore-name">{{ featuredBookstore.name }}</h3>
        <p class="bookstore-description">{{ featuredBookstore.description }}</p>
        <a :href="featuredBookstore.link" class="bookstore-link" target="_blank">Learn More</a>
      </div>
    </div>
   </div>
    </map>


    <main class="main">
      <!-- BOOK BOXES -->
    <div class="book-container">
      <h2 class="book-box-text">
        <span class="app-name-text">Look at a popular book series! </span>
        <span class="app-name-accent">Now Showing: {{this.series}}</span> 
          </h2>
      <div class="horizontal-book-box">
        <!-- Content of the first book box -->
        <!-- By Series/Title -->
        <div v-for="book in $store.state.homePageSeries" v-bind:key="book.bookId" class="book-content">
          <div class="book-content">
            <img :src="book.volumeInfo.imageLinks.thumbnail" alt="" class="bookCover" />
            <h2 class="book-title">{{ truncateTitle(book.volumeInfo.title, 10) }}</h2>
            <p class="book-author">{{ book.volumeInfo.authors.join(', ') }}</p>           
          </div>
        </div>
      </div>
    </div>

    <div class="book-container">
<h2 class="book-box-text">
        <span class="app-name-text">Look at a popular Author! </span>
        <span class="app-name-accent">Now Showing: {{this.author}}</span> 
          </h2>      
      <div class="horizontal-book-box">
        <!-- Content of the second book box -->
        <!-- By Author -->
        <div v-for="book in $store.state.homePageAuthors" v-bind:key="book.bookId" class="book-content">
          <div class="book-content">
            <img :src="book.volumeInfo.imageLinks.thumbnail" alt="" class="bookCover" />
            <h2 class="book-title">{{ truncateTitle(book.volumeInfo.title, 10) }}</h2>
            <p class="book-author">{{ book.volumeInfo.authors.join(', ') }}</p>           
          </div>
        </div>
      </div>
    </div>

    <div class="book-container">
<h2 class="book-box-text">
        <span class="app-name-text">Look at a popular Topic! </span>
        <span class="app-name-accent">Now Showing: {{this.topics}}</span> 
      </h2>      
      <div class="horizontal-book-box">
        <!-- Content of the third book box -->
        <!-- By Topic -->
        <div v-for="book in $store.state.homePageTopics" v-bind:key="book.bookId" class="book-content">
          <div class="book-content">
            <img :src="book.volumeInfo.imageLinks.thumbnail" alt="" class="bookCover" />
            <h2 class="book-title">{{ truncateTitle(book.volumeInfo.title, 10) }}</h2>
            <p class="book-author">{{ book.volumeInfo.authors.join(', ') }}</p>           
          </div>
        </div>
      </div>
    </div>

      <router-view />
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

        <div class="nav-item" @click="$router.push({ name: 'addBook' })"  v-if="$store.state.user.username === 'admin'">
          <span class="nav-box"></span>
          <img src="../assets/icons8-plus-48.png" alt="AddBook" class="nav-icon">
          <span>Add Book</span>
        </div>
      </nav>

      
    <div class="trivia-container">
      <HomeTriviaVue />
    </div>

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
      topics: this.randomizeTopics(),
      featuredBookstoreIndex: 0,
      featuredBookstores: [
        {
          name: "Cover to Cover",
          description: "We have loved sharing our passion for reading with children, parents, and teachers since 1980. At our new store at 2116 Arlington Avenue, we continue to offer the very best of classic and new literature for young readers. Our in-store events include story-time, reading clubs, writing workshops, and author/illustrator visits.",
          link: "https://www.covertocoverchildrensbooks.com/",
          image: require("../assets/CovertoCoverFrontLineCOLOR_1.jpg")
        },
        {
          name: "Prologue Bookshop",
          description: "Welcome to Prologue Bookshop, your source for inspiration, imagination, and community in the Short North Arts District. We’re a new bookstore in Columbus, Ohio, stop in today!",
          link: "https://www.prologuebookshop.com/",
          image: require("../assets/348s.jpg")
        },
      ]
    };
  },
  components: {
    AddGoogleMapVue,
    HomeTriviaVue
  },
  computed: {
    featuredBookstore() {
      return this.featuredBookstores[this.featuredBookstoreIndex];
    }
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
      let authors = ["Tolkien", "Ray Bradbury", "Brandon Sanderson", "James Patterson", "Tom Clancy", "George R. R. Martin", "Stephen King" ];
      let randomNumber = Math.floor(Math.random() * authors.length);
      return authors[randomNumber];
    },
    randomizeSeries() {
      let series = ["Game of Thrones", "Harry Potter", "Dune", "Percy Jackson", "Handmaid's Tale", "Twilight", "The Raven Cycle"];
      let randomNumber = Math.floor(Math.random() * series.length);
      return series[randomNumber];
    },
    randomizeTopics() {
      let topics = ["Football", "Science", "Basketball", "History", "Soccer", "Baseball", "Physics", "Messi"];
      let randomNumber = Math.floor(Math.random() * topics.length);
      return topics[randomNumber];
    },
    rotateFeaturedBookstore() {
      this.featuredBookstoreIndex = (this.featuredBookstoreIndex + 1) % this.featuredBookstores.length;
    }
  },
  created() {
    this.getRandomizedBooksAuthor();
    this.getRandomizedBooksSeries();
    this.getRandomizedBooksTopics();
    setInterval(this.rotateFeaturedBookstore, 24 * 60 * 60 * 1000); 

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
    "nav main map"
    "nav main map"
    "footer footer footer";
  min-height: 100vh;
}

.trivia-container {
  grid-area: nav;
  width: 310px;
  overflow: hidden;
  margin-bottom: 20px;
}

.map-container {
  grid-area: map;
  width: 310px;
  overflow: hidden;
}

/* NAV MENU CSS CODE */
.nav-item {
  grid-area: nav;
  position: relative;
  display: flex;
  flex-direction: column;
  cursor: pointer;
  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
  margin-bottom: 1rem;
  margin-right: 350px;
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

.nav-icon {
  margin-left: 10px;
}

/* BOOKSTORE HIGHLIGHT CODE */
.bookstore-h-container {
  grid-area: map;
  display: flex;
  justify-content: center;
  align-items: flex-end;
  min-height: 5vh;
  padding: 1px;
  border-radius: 10px;
  width: 100%;
  overflow: auto;
  background-color: #f4f4f4;
  word-wrap: break-word;
}

.bookstore-highlight {
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  background-color: white;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  margin-top: 20px;
  width: 70%;
}

.bookstore-image {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  margin-right: 20px;
}

.bookstore-details {
  flex: 1;
}

.bookstore-name {
  font-size: 18px;
  color: black;
  font-family: 'Arial', sans-serif;
  margin-bottom: 5px;
  text-align: center;
}

.bookstore-description {
  font-size: 14px;
  color: black;
  font-family: 'Arial', sans-serif;
  margin-top: 5px;
  text-align: center;
}

.bookstore-link {
  display: inline-block;
  margin-top: 10px;
  color: white;
  text-decoration: none;
  font-weight: bold;
  font-family: 'Arial', sans-serif;
  border-radius: 5px;
  padding: 5px 10px;
  background-color: rgb(209, 77, 4);
  transition: background-color 0.3s, transform 0.3s;
}

.bookstore-link:hover {
  background-color: rgb(255, 102, 0);
  text-decoration: none;
}

.highlight-header {
  color: rgb(175, 174, 174);
  font-family: Arial, sans-serif;
  margin-bottom: 10px;
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
  text-align: center;
}

.horizontal-book-box {
  display: flex;
  overflow-x: auto;
  gap: 20px;
  padding: 10px;
  align-items: flex-start;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  box-sizing: auto;
  margin-top: 20px;
  transition: transform 0.3s, box-shadow 0.3s;
}

.horizontal-book-box::-webkit-scrollbar {
  display: none; 
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

.book-box-text {
  font-family: Arial, sans-serif;
  font-size: 25px;
  color: #333;
  text-align: center;
  margin-bottom: 10px;
}

.book-box {
  background-color: #fff;
  display: flex;
  align-items: center;
  width: 100%; /* Adjusted width */
  height: auto;
  padding: 10px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  margin-top: 20px;
  transition: transform 0.3s, box-shadow 0.3s;
  flex-wrap: wrap;
  gap: 20px;
  justify-content: space-evenly;
}

.book-box:hover {
  transform: translateY(-5px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
}

.book-content {
  font-family: Arial, sans-serif;
  flex-direction: column;
  align-items: center;
  text-align: center;
  display: flex;
  gap: 10px;
  width: 245px;
  height: auto;
}

.book-content:hover {
  transform: translateY(-5px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
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

/* SIGN IN SIGN OUT BUTTON CSS CODE */
.login-logout-button {
  grid-area: header;
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

/* FOOTER CSS CODE */
.footer {
  grid-area: footer;
  text-align: center;
  padding: 1rem;
}

/* NAV CSS CODE */
.nav {
  grid-area: nav;
  text-align: center;
  padding: 0.5rem;
}
</style>