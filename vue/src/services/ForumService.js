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

    delete(id){
        return http.post(`/topic/message/${id}`)

    },
    editMessage(id,message){
        return http.put(`/topic/message/${id}`, message)

    },





  }