import axios from 'axios';

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

    getBooksFromOurDatabase(){
      return axios.get('/books');
    },
    addBookToBookshelf(book){
        return axios.post('/books/myBookShelf', book);
    },

    getBooksFromBookshelf() {
      return axios.get(`/books/myBookShelf`);
    }
    

  }
