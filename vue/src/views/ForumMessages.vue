<template>
  <div>
      <ForumMessages v-bind:topicId="parseInt(this.$route.params.id)" />
  </div>
</template>

<script>
import ForumMessages from "../components/ForumMessages.vue"

export default {
    components: {ForumMessages}
}
</script>

<style>

</style>