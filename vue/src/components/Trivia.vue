<template>
    <div class="trivia-corner">
    <header class="header">
      <router-link :to="{ name: 'home' }" >
        <img class="logo-image" src="../assets/image.png" alt="Logo" />
      </router-link>


      <div class="login-logout">
        <router-link v-bind:to="{ name: 'logout' }" class="login-logout-button" v-if="$store.state.token != ''">Sign In/Sign Out</router-link>
      </div>
    </header>


    <div class="trivia-section">
      <h2 class="book-box-text">
        <span class="app-name-text">Trivia </span>
        <span class="app-name-accent">Corner</span> 
          </h2>
      <p>
          Welcome to the Trivia Corner! Test out your book knowledge with some trivia below. 
          Choose your difficulty and see how well you can do!
      </p>

    
      <div class="dropDown">
        <select v-model="difficultyChoice" @change="get10QuestionsByDifficulty(difficultyChoice)" >
            <!-- <option value="">Select Difficulty</option> -->
            <option value="easy" >Easy</option>
            <option value="medium" >Medium</option>
            <option value="hard" >Hard</option>
        </select>
      </div>

      <div class="question-container">
          <div v-for="triviaQ in $store.state.triviaDifficulty" v-bind:key="triviaQ.question"  >
              <div class="question-content">
                  <h2> Q. 
                      <span v-html="triviaQ.question" ></span>
                  </h2>
                  <h3>
                    <p>A) {{triviaQ.allPossibleAnswers[0]}} </p>
                    <p>B) {{triviaQ.allPossibleAnswers[1]}} </p>
                    <p>C) {{triviaQ.allPossibleAnswers[2]}} </p>
                    <p>D) {{triviaQ.allPossibleAnswers[3]}} </p>
                  </h3>
                  <button @click="revealAnswer(triviaQ)" >Reveal the Correct Answer</button>
                  <div v-if="showAnswer[triviaQ.question]" >
                      <h4> Answer: 
                        <span v-html="triviaQ.correct_answer" > </span>
                      </h4>
                  </div>
              </div>
          </div>
      </div>
  </div>


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
import triviaService from '../services/TriviaService';

export default {
  data() {
      return {
          difficultyChoice: "",
          showAnswer: {},
      }
  },
  methods: {
    getTriviaByCount(number) {
        triviaService.getTriviaByNumberOfQuestions(number);
    },
    get10MultipleChoiceQuestions() {
        triviaService.getMultipleChoiceQuestions().then(response => {
            this.$store.commit("SET_TRIVIA_DIFFICULTY", response.data.results);
        }).catch(console.error);
    },
    get10QuestionsByDifficulty(difficultyChoice) {
        triviaService.get10QuestionsChooseDifficulty(difficultyChoice).then(response => {
          this.$store.commit("SET_TRIVIA_DIFFICULTY", response.data.results);
        }).catch(console.error);
    },
    revealAnswer(triviaQ) {
        this.showAnswer[triviaQ.question] = true;
        this.$forceUpdate();
    }
  }
   
}
</script>

<style>

.trivia-corner {
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

.trivia-section {
  background-color: #fff;
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  margin: 2rem auto;
  max-width: 800px;
}

.book-box-text {
  font-size: 34px;
}

.trivia-section h1 {
  font-size: 2.5rem;
  color: #333;
  margin-bottom: 1rem;
}

.trivia-section p {
  font-size: 1.1rem;
  color: #555;
  margin-bottom: 2rem;
}

.dropDown {
  display: flex;
  justify-content: center;
  margin-bottom: 2rem;
}

.select {
  background-color: #f7f7f7;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 5px;
  font-size: 1rem;
  color: #333;
}

.question-container {
  display: flex;
  flex-direction: column;
  gap: 2rem;
}

.question-content {
  background-color: #f7f7f7;
  padding: 1.5rem;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.question-content h2 {
  font-size: 1.5rem;
  color: #333;
}

.question-content h3 {
  font-size: 1.2rem;
  color: #555;
  margin-top: 1rem;
}

.question-content button {
  background-color: rgb(209, 77, 4);
  color: white;
  font-weight: bold;
  font-family: Arial, sans-serif;
  border: none;
  border-radius: 5px;
  padding: 0.5rem 1rem;
  cursor: pointer;
  transition: background-color 0.3s, transform 0.3s;
  margin-top: 1rem;
}

.question-content button:hover {
  background-color: rgb(255, 102, 0);
  transform: scale(1.05);
}

.question-content h4 {
  font-size: 1.1rem;
  color: #555;
  margin-top: 1rem;
}
</style>



// add show answer all equal to false
// each passes own index
// show answer for particular object