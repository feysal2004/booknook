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

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    topics: [],
    activeTopic: {
      id: 0,
      title: "",
      messages: []
    },
    user: currentUser || {},
    books:[
    {
      title:"book test",
      author:"author test",
      description:"descrciption test",
      series:"series test",
      book_id:"book id test",
      release_date:"release date test"
    }

    ],

  },
  mutations: {
    SET_TOPICS(state, data) {
      state.topics = data;
    },
    SET_ACTIVE_TOPIC(state, data) {
      state.activeTopic = data;
    },
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },

    ADD_BOOK(state,book){
      state.books.push(book);
    },

    DELETE_MESSAGE(state,id){
      state.messages.splice(id)

    }


  }
})
