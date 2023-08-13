import axios from 'axios';

const triviaApi = axios.create();
triviaApi.defaults.headers.common = {};



export default{

getTriviaByNumberOfQuestions(number) {
    return triviaApi.get(`https://opentdb.com/api.php?amount=${number}&category=10`);
}

}