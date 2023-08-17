import axios from 'axios';


export default{
    addMyBook(book){
        return axios.post(`/books/myBookShelf`, book);
    },

    deleteFromMyBookshelf(book) {
        return axios.delete(`/books/myBookShelf`, book);
    },

    getUnreadBooks() {
        return axios.get(`/books/myBookShelf/unread`);
    },

    changeBookToRead(bookId) {
        return axios.put(`/books/myBookShelf/${bookId}`)
    }

}