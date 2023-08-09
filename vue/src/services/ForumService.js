import axios from 'axios';

// const http = axios.create({
//     baseURL: "http://localhost:9000"
//   });

  export default{

    messageList(topicId){
        return axios.get(`/topics/message/${topicId}`)
    },

    getForumMessageId(messageId){
        return axios.get(`/topics/${messageId}`);
    },

    createForumMessage(topicId, message_text){
        return axios.post(`/topics/message/${topicId}`, message_text)
    },

    getTopicName(topicId){
        return axios.get(`/topics/message/${topicId}/name`);
    }





  }