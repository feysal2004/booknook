import axios from 'axios';

export default{
    addMyBook(book){
        return axios.post(`/books/myBookShelf`, book);
    }



}