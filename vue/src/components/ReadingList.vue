<template>
  <div>
      <google-books v-for="book in $store.state.bookInput" v-bind:key="book.bookId"/>
  </div>
</template>

<script>
import GoogleBooks from '../components/GoogleBooks.vue';
export default {
  components: { GoogleBooks },

}
</script>

<style>

</style>