<template>
  <div>
       <h5>My Books</h5>  
       <div v-for="book in userLibrary" v-bind:key="book.bookId" >
            <img :src="book.volumeInfo.imageLinks.thumbnail" alt="" class="bookCover" />
           <h5>{{book.volumeInfo.title}}</h5>
           <p class="book-author">{{ book.volumeInfo.authors }}</p>
       </div>

  </div>
</template>

<script>
export default {
    computed:{
    userLibrary(){
      return this.$store.state.userLibrary;
    }
  }


}
</script>

<style>
.default-layout {
  display: grid;
  grid-template-rows: auto auto 1fr auto;
  grid-template-columns: auto 1fr auto;
  grid-template-areas:
    "header header header"
    "nav main main"
    "nav main main"
    "footer footer footer";
  min-height: 100vh;
}

</style>