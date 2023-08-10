<template>
  <div >
      <div v-for="book in $store.state.bookInput" v-bind:key="book.bookId" >
        <h2> {{ book.volumeInfo.title }} </h2>
        <h2> {{book.volumeInfo.authors}} </h2>
        <h2> {{book.volumeInfo.imageLinks.thumbnail}} </h2>
        <img src="book.volumeInfo.imageLinks.thumbnail" alt="">
      </div>
      <!-- <button v-on:click="getBookSearch()" v-model="" >Button</button> -->

    <!-- have google book api give us a selection of books -->
    <!-- Get title author and thumbnail from the selection of Books -->
    <!-- Have a block to display each book with that info -->
    
    

  </div>

</template>

<script>
import googleBookAPI from "../services/GoogleBookApiService";

export default {
    data() {
        return {
            input: "a"
        }
    },

    methods: {
        getBookSearch() {
            googleBookAPI.getBookListBySearchQuery(this.input).then(response => {
                this.$store.commit("SET_GOOGLE_BOOK_SEARCH", response.data.items);

                console.log("Logging google books get books search method", response.data.items)
            }).catch(console.error);
        }
    },
    created() {
        console.log("google books component created")
        this.getBookSearch();
    }
}
</script>

<style>

</style>