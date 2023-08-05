<template>
  <div class="book-data">
    <input class="book_name" type="text" placeholder="Title" />
    <input class="author" type="text" placeholder="Author" />
    <input class="author_second" type="text" placeholder="Second Author" />
    <input class="description" type="text" placeholder="description" />
    <input class="series" type="text" placeholder="Series" />
    <input class="book_id" type="text" placeholder="Book ID" />
    <input class="release_date" type="text" placeholder="Release Date" />

    <button>Add Book</button>
  </div>
</template>

<script>
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
      },
    };
  },

  methods:{

      saveBook(){
            this.$store.commit('ADD_BOOK', this.book)
            this.book = {
                title: "",
                author: "",
                description: "",
                series: "",
                book_id: "",
                release_date: "",

            }

            this.$router.push({name:'Library'})

      }

  }




};
</script>

<style>
</style>