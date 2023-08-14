import axios from 'axios';

export default{
    addMyBook(book,userId){
        return axios.post(`/myBooks/${userId}`, book);
    }



}