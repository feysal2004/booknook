<template>
  <div class="book-data">
    <input class="book_name" type="text" placeholder="Title" />
    <input class="author" type="text" placeholder="Author" />
    <input class="author_second" type="text" placeholder="Second Author" />
    <input class="description" type="text" placeholder="description" />
    <input class="series" type="text" placeholder="Series" />
    <input class="book_id" type="text" placeholder="Book ID" />
    <input class="release_date" type="text" placeholder="Release Date" />
    <input class="date_added_to_collection" type="text" placeholder="Date added to Collection" />

    <button v-on:click="saveBook()">Add Book</button>
  </div>
</template>

<script>
import bookService from "../services/BookService.js"
export default {
  name: "new-book",
  data() {
    return {
      book: {
        title: "",
        author: "",
        description: "",
        series: "",
        book_id: "",
        release_date: "",
        date_added_to_collection: ""
      },
    };
  },

  methods:{
      saveBook(){
           bookService.addBook(this.book);
      }

  }




};
</script>

<style>
</style>