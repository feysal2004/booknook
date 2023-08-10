<template>
  <div class="default-layout">
    <header class="header">
          <router-link :to="{ name: 'home' }" class="logo">
      <img class="logo-image" src="../assets/T0GNFLF6D-U0192MVUM7C-d3304dbc9516-512.png" alt="Logo" />
    </router-link>

            <div class="app-name">
        <span class="app-name-text">App</span>
       <span class="app-name-accent">Name</span>
      </div>

      <div class="login-logout">
        <router-link v-bind:to="{ name: 'logout' }" class="login-logout-button" v-if="$store.state.token != ''">Sign In/Sign Out</router-link>
      </div>

    </header>


    <main class="main">
                        <!-- BOOK BOXES -->
        <div class="book-box">
        <!-- Content of the first book box -->
            </div>
        <div class="book-box">
        <!-- Content of the second book box -->
            </div>
        <div class="book-box">
        <!-- Content of the third book box -->
            </div>
    <router-view />
    </main>


    <nav class="nav">
                     <!-- NAVIGATION MENU CODE -->
        <nav class="nav">
        <div class="nav-item" @click="$router.push({ name: 'home' })" v-if="$store.state.token != ''">
            <span class="nav-box"></span>
          <img src="../assets/icons8-home-60.png" alt="Home">
            <span class="nav-text">Home</span>
        </div>
        <div class="nav-item" @click="$router.push({ name: 'Topic' })" v-if="$store.state.token != ''">
            <span class="nav-box"></span>
          <img src="../assets/icons8-keyboard-50.png" alt="Forum">
          <span>Forum</span>
        </div>
        <div class="nav-item" @click="$router.push({ name: 'BookLibrary' })" v-if="$store.state.token != ''">
            <span class="nav-box"></span>
        <img src="../assets/icons8-library-50.png" alt="BookLibrary">
          <span>Book Library</span>
        </div>
        <div class="nav-item" @click="$router.push({ name: 'addBook' })"  v-if="$store.state.user.username === 'admin'">
            <span class="nav-box"></span>
          <img src="../assets/icons8-plus-48.png" alt="AddBook">
          <span>Add Book</span>
        </div>
    </nav>
    </nav>



    <footer class="footer">
    </footer>
  </div>
</template>


<script>
export default {

}
</script>


<style scoped>
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