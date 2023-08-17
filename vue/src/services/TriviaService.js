import axios from 'axios';

const triviaApi = axios.create();
triviaApi.defaults.headers.common = {};



export default{

    getTriviaByNumberOfQuestions(number) {
        return triviaApi.get(`https://opentdb.com/api.php?amount=${number}&category=10`);
    },
    getMultipleChoiceQuestions() {
        return triviaApi.get(`https://opentdb.com/api.php?amount=10&category=10&type=multiple`);
    },
    get10QuestionsChooseDifficulty(difficulty) {
        return triviaApi.get(`https://opentdb.com/api.php?amount=10&category=10&difficulty=${difficulty}&type=multiple`)
        .then(response => { 
            response.data.results.forEach(element => {
                element.allPossibleAnswers = this.randomizeArray(element.incorrect_answers.concat(element.correct_answer));              
            });
            return response;
         })
    },
    get1TrueFalseQuestion() {
        return triviaApi.get(`https://opentdb.com/api.php?amount=1&category=10&type=boolean`);
    },


    randomizeArray(array) {
        for (let i = 0; i < 5*array.length; i++) {
            let index = Math.floor(Math.random() * array.length);
            array.splice(0, 0, array[index]);
            array.splice((index + 1), 1);
        }
        return array;
    }

}