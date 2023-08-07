import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000"
  });

  export default{

    getForumMessageId(id){
        return http.get(`/forum/${id}`);
    },

    createForumMessage(){
        return http.post(`/forum`)
    },

    postMessage(message){
        return http.post(`/message`, message)

    },

    delete(id){
        return http.post(`/forum/${id}`)

    },
    editMessage(id,message){
        return http.put(`/forum/${id}`, message)

    }





  }