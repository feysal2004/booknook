<template>
  <div>
      <button v-on:click="getBookSearch()" >Button</button>
  </div>

</template>

<script>
import googleBookAPI from "../services/GoogleBookApiService";

export default {
    data() {
        return {
            input: "lordoftherings"
        }
    },

    methods: {
        getBookSearch() {
            googleBookAPI.getBookListBySearchQuery(this.input).then(response => {
                this.$store.commit("SET_GOOGLE_BOOK_SEARCH", response.data);
                console.log()
            }).catch(console.error);
        }
    },
    computed: {
       
    }
}
</script>

<style>

</style>