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

  updateTopic(id, topic) {
    return http.put(`/topics/${id}`, topic);
  },

  delete(id) {
    return http.delete(`/topics/${id}`)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  }
};