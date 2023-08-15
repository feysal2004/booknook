<template>
  <div class="trivia-container">
    <div class="trivia-box"> 
      <div>
        <h1 class="app-name-text">Trivia</h1>
        <h3>True or False?</h3>
      </div>

      <div v-for="triviaQ in $store.state.triviaBoolean" :key="triviaQ.triviaId" class="trivia-item">
        <h4> 
            <span v-html="triviaQ.question" > </span>
        </h4>
        <h5> (Difficulty: {{triviaQ.difficulty}} )</h5>
        <button @click="revealAnswer(triviaQ)" class="reveal-button">Reveal the Correct Answer</button>
        <div v-if="triviaQ.showAnswer" class="answer-box">
          <h4> A: {{triviaQ.correct_answer}} </h4>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import triviaService from '../services/TriviaService';

export default {
    name: "HomeTriviaVue",
    data() {
        return {
            difficultyChoice: "",
        }
    },
    methods: {
        getTriviaQuestion() {
            triviaService.get1TrueFalseQuestion().then(response => {
                const triviaData = response.data.results.map(trivia => ({
                    ...trivia,
            showAnswer: false,
        }));
        this.$store.commit("SET_TRIVIA_TRUE_FALSE", triviaData);
            }).catch(console.error);
        },
        revealAnswer(triviaQ) {
            triviaQ.showAnswer = !triviaQ.showAnswer;
        }
    },
    mounted() {
    this.getTriviaQuestion();
    }
};
</script>

<style scoped>
.trivia-container {
  display: flex;
  justify-content: center;
  align-items: flex-end;
  min-height: 5vh;
  padding: 1px;
  border-radius: 10px;
  width: 100%;
  height: auto;
  overflow: auto;
  background-color: #f4f4f4;
  word-wrap: break-word;
}

.trivia-box {
  background-color: #fff;
  padding: 5px;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  text-align: center;
  width: 80%;
  max-width: 800px;
  font-family: Arial, sans-serif;
  word-wrap: break-word;
  margin: 10px;
}

.trivia-item {
  margin-bottom: 5px;
  font-family: Arial, sans-serif;
  word-wrap: break-word;
}

.reveal-button {
  padding: 0.5rem 1rem;
  background-color: rgb(209, 77, 4);
  color: white;
  font-weight: bold;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s, transform 0.3s;
  font-family: Arial, sans-serif;
  word-wrap: break-word;
}

.reveal-button:hover {
  background-color: rgb(255, 102, 0);
  transform: scale(1.05);
}

.answer-box {
  margin-top: 10px;
  font-family: Arial, sans-serif;
}

</style>





