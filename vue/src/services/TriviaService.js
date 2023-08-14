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
        return triviaApi.get(`https://opentdb.com/api.php?amount=10&category=10&difficulty=${difficulty}&type=multiple`);
    },
    get1TrueFalseQuestion() {
        return triviaApi.get(`https://opentdb.com/api.php?amount=1&category=10&type=boolean`);
    }

}