import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000"
  });

  export default{

    messageList(topicId){
        return http.get(`/topics/message/${topicId}`)
    },

    getForumMessageId(messageId){
        return http.get(`/topics/${messageId}`);
    },

    createForumMessage(topicId, message_text){
        return http.post(`/topics/message/${topicId}`, message_text)
    },

    getTopicName(topicId){
        return http.get(`/topics/message/${topicId}/name`);
    }





  }