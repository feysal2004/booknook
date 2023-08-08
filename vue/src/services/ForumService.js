import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000"
  });

  export default{

    messageList(topicId){
        return http.get(`/topic/message/${topicId}`)
    },

    getForumMessageId(messageId){
        return http.get(`/topic/${messageId}`);
    },

    createForumMessage(){
        return http.post(`/topic/message`)
    },

    postMessage(message){
        return http.post(`/topic/message`, message)

    },
    getTopicName(topicId){
        return http.get(`/topic/message/name/${topicId}`);
    }





  }