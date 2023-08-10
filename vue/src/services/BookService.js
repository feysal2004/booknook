import axios from 'axios';

// const http = axios.create({
//     baseURL: "http://localhost:9000"
//   });

  export default{

    getData(){

      const data = {

        "results": [
          {
            "title":"pop up",
            "author":"hardcoded",
            "secondAuthor":"hardcoded",
            "description":"hardcoded",
            "series":"hardcoded",
            "releaseDate":"01-01-2022",
            "dateAddedToCollection":"01-01-2023"
          }
        ]


      }

      return data

    },

   

    
      
    addBook(book){
        return axios.post('/addBook', book);
    },

    filteredBook(){
      return axios.get(`/bookLibrary`);

    },
   

    

  }
