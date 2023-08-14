<template>
  <div> 
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