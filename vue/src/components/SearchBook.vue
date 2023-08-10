<template>
  <div>
    <h2>Search For Books</h2>
    <input
      class="input field"
      type="text"
      placeholder="Title"
      v-model="searchBook.book_name"
    />
    <input
      class="input field"
      type="text"
      placeholder="Author name"
      v-model="searchBook.author"
    />
    <p>{{ filteredBooks }}</p>
  </div>
</template>


<script>
//import bookService from "../services/BookService"

export default {
  data() {
    return {
      books: [],
      book: {
        book_name: "",
        author: "",
      },
      searchBook: {
        book_name: "",
        author: "",
      },
    };
  },

  methods: {
    
  },

  computed: {
    filteredBooks() {
     
      let filteredList = [];
      this.books.forEach((book) => {
        console.log(book);
        console.log(this.searchBook.book_name);
        console.log(this.searchBook.author);

        if (
          book.book_name
            .toLowerCase()
            .includes(this.searchBook.book_name.toLowerCase()) 
        ) {
            filteredList.push(book);
         }
        //  else if (
        //   book.author
        //     .toLowerCase()
        //     .includes(this.searchBook.author.toLowerCase())
        // ) {
        //   filteredList.push(book);
        // }
      });

      return filteredList;
    },
  },

  created() {
    this.books = this.$store.state.books;
  },
};
</script>

<style>
</style>