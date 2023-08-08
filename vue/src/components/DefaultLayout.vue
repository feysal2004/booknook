<template>
  <div class="default-layout">
    <header class="header">
      <div class="logo">Logo</div>

      <div class="login-logout">
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Sign In/Sign Out</router-link>
      </div>
    </header>



    <div class="app-name">App Name</div>



    <main class="main">
      <router-view />
        <nav class="nav">
        <router-link v-bind:to="{ name: 'home' }"  v-if="$store.state.token != ''"><img src="../assests/icons8-home-60.png" alt="Home"></router-link>
        <router-link v-bind:to ="{name:'Topic'}"> Forum </router-link>
        <router-link v-bind:to="{name:'MyBookShelf'}"> My BookShelf</router-link>
        <router-link v-bind:to="{name:'BookLibrary'}"> Book Library </router-link>
        <router-link v-bind:to="{name: 'AddBook'}">Add Book</router-link>
    </nav>
    </main>


    <footer class="footer">
    </footer>


    
  </div>
</template>

<style scoped>
.default-layout {
  display: grid;
  grid-template-rows: auto auto 1fr auto;
  grid-template-columns: auto 1fr auto;
  grid-template-areas:
    "header header header"
    "app-name app-name app-name"
    "main main main"
    "footer footer footer";
  min-height: 100vh;
}

.nav {
  grid-area: nav;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 1rem;
}


.header {
  grid-area: header;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
}



.logo {
}

.nav-buttons {
}

.login-logout {
}

.app-name {
  grid-area: app-name;
  text-align: center;
  padding: 1rem;
}

.main {
  grid-area: main;
  padding: 2rem;
}

.footer {
  grid-area: footer;
  text-align: center;
  padding: 1rem;
}
</style>