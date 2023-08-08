import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:9000"
});

export default {

  list() {
    return http.get('/topics');
  },

  get(id) {
    return http.get(`/topics/${id}`);
  },

  createNewForumTopic(topic) {
    return http.post('/topics', topic);
  },
};