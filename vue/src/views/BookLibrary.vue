<template>
  <div>

<GoogleBooks /> 
<SearchBook />


  </div>
</template>

<script>
import SearchBook from '../components/SearchBook.vue'
import GoogleBooks from "../components/GoogleBooks.vue"



export default {
  components: { SearchBook ,
  GoogleBooks},

}
</script>

<style>

</style>