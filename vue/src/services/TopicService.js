import axios from 'axios';

// const http = axios.create({
//   baseURL: "http://localhost:9000"
// });

export default {

  list() {
    return axios.get('/topics');
  },

  getTopicName(id) {
    return axios.get(`/topics/${id}`);
  },

  createNewForumTopic(topic) {
    return axios.post('/topics', topic);
  },
};