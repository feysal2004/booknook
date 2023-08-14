<template>
  <div> 
    

    <div>
        <h1>Trivia</h1>
        <h3>True or False?</h3>
    </div>
        <div v-for="triviaQ in $store.state.triviaBoolean" v-bind:key="triviaQ.triviaId" >
            <h4> T/F: {{triviaQ.question}} </h4>
            <h5> (Difficulty: {{triviaQ.difficulty}} ) </h5>
            <button @click="revealAnswer" >Reveal the Correct Answer</button>
            <div v-if="showAnswer(index)" >
                <h4> A: {{triviaQ.correct_answer}} Id = {{triviaQ.triviaId}} </h4>
            </div>
        </div>
    <div>

    </div>


  </div>
</template>

<script>
import triviaService from '../services/TriviaService';

export default {
    name: "HomeTrivia",
    data() {
        return {
            difficultyChoice: "",
            showAnswer: false
        }
    },
    methods: {
        getTriviaQuestion() {
            triviaService.get1TrueFalseQuestion().then(response => {
                this.$store.commit("SET_TRIVIA_TRUE_FALSE", response.data.results);
            }).catch(console.error);
        },
        revealAnswer() {
            this.showAnswer = !this.showAnswer;
        }
    }
}
</script>

<style>

</style>