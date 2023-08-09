<template>
  <div>
      <h2>Search For Books</h2>
      <input class="input field" type="text" placeholder="Title"  />
      <input class="input field" type="text" placeholder="Author name" />
      

  </div>
</template>


<script>
import bookService from "../services/BookService"
//import index from "../store/index.js"

export default {
  data(){
    return{
      book:{
        book_name: "",
        author: "",
        
      },
      searchBook:[
       {
        title:"",
       author:""

     }
     ]
    }

  },

 
  methods:{
    getBooks(){
      bookService.getBooks()

    },
    filteredList(){
      let filteredList = this.book.filter(book =>{
        return book.title.toLowerCase().includes(this.searchBook.title.toLowerCase())&&
        book.author.toLowerCase().includes(this.searchBook.author.toLowerCase())
      })
     return filteredList
     }




  }


}
</script>

<style>

</style>