import axios from 'axios';

// const http = axios.create({
//     baseURL: "http://localhost:9000"
//   });

  export default{
      
    addBook(book){
        return axios.post('/addBook', book);
    },

    filteredBook(){
      return axios.get(`/bookLibrary`);

    }

    

  }