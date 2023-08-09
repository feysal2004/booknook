<template>
<div>
  <div class="book-data" v-if="this.$store.state.user.username === 'admin'">
    <input class="book_name" type="text" placeholder="Title" v-model=book.book_name />
    <input class="author" type="text" placeholder="Author" v-model=book.author />
    <input class="author_second" type="text" placeholder="Second Author" v-model=book.author_second />
    <input class="description" type="text" placeholder="description" v-model=book.description />
    <input class="series" type="text" placeholder="Series" v-model=book.series />
    <input class="book_id" type="text" placeholder="Book ID" v-model=book.book_id />
    <input class="release_date" type="date" placeholder="Release Date" v-model=book.release_date />
    <input class="date_added_to_collection" type="date" placeholder="Date added to Collection" v-model=book.date_added_to_collection />

    <button v-on:click="saveBook()">Add Book</button>
    </div>
    <h2 v-if="this.$store.state.user.username !== 'admin'">Must Be Authorized</h2>
  </div>
</template>

<script>
import bookService from "../services/BookService.js"
export default {
  data() {
    return {
      book: {
        book_name: "",
        author: "",
        author_second: "",
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
           bookService.addBook(this.book).then(()=> {
             this.book.book_name="";
             this.book.author="";
             this.book.author_second=""
             this.book.series="";
             this.book.book_id="";
             this.book.date_added_to_collection="";
             this.book.release_date="";
             this.book.description="";
           }).catch(console.error);
      }

  }




};
</script>

<style>
</style>