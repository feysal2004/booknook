import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    topics: [],
    messages: [],
    bookInput: [],
    triviaDifficulty: [],
    maps: [],
    adminBooks: [],
    messageCount: [],
    activeTopic: {
      id: 0,
      title: "",
      messages: []
    },
    user: currentUser || {},
    
  

    books: [
      {
        "book_name": "admin",
        "author": "admin",
        "author_second": "heyeye",
        "description": "eeh",
        "series": "ehe",
        "release_date": "2020-02-02",
        "date_added_to_collection": "2021-02-02"

      },
      {
        "book_name": "heloo",
        "author": "adyyymin",
        "author_second": "yees",
        "description": "dumb",
        "series": "data",
        "release_date": "2020-02-02",
        "date_added_to_collection": "2021-02-02"
      }
    ],
    searchBook:
    {
      title: "",
      author: ""
    }
  },
  mutations: {
    SET_TOPICS(state, data) {
      state.topics = data;
    },
    SET_MESSAGES(state, data) {
      state.messages = data;
    },
    SET_GOOGLE_BOOK_SEARCH(state, data) {
      state.bookInput = data;
    },
    SET_TRIVIA_DIFFICULTY(state, data) {
      state.triviaDifficulty = data;
    },
    SET_ADMIN_ADDED_BOOKS(state, data) {
      state.adminBooks = data;
    },
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    ADD_BOOK(state, book) {
      state.books.push(book);
    },
    SET_NUMBER_OF_MESSAGES(state, data) {
      state.messageCount = data;
    },
    SAVE_BOOK_TO_LIBRAY(state,book){
      state.books.push(book)
    }
  }
})
