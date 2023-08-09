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
        <div class="nav-item" @click="$router.push({ name: 'MyBookShelf' })" v-if="$store.state.token != ''">
            <span class="nav-box"></span>
          <img src="../assets/icons8-bookcase-50.png" alt="BookShelf">
          <span>My BookShelf</span>
        </div>
        <div class="nav-item" @click="$router.push({ name: 'BookLibrary' })" v-if="$store.state.token != ''">
            <span class="nav-box"></span>
        <img src="../assets/icons8-library-50.png" alt="BookLibrary">
          <span>Book Library</span>
        </div>
        <div class="nav-item" @click="$router.push({ name: 'addBook' })" v-if="$store.state.token != ''">
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



/* NAV MENU CSS CODE */
.nav-item {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
  margin-bottom: 1rem;
}


.nav {
  grid-area: nav;
  display: flex;
  flex-direction: column;
  align-items: left; 
  justify-content: left; 
  padding: 1rem;
}

.nav-box {
  position: absolute;
  left: -45px; 
  top: 0;
  width: 150px;
  height: 100%;
  background-color: rgb(175, 174, 174);
  border-radius: 5px; 
  z-index: -1;
  transition: background-color 0.3s; 
}


.nav-item:hover .nav-box {
  background-color: #555; 
}

.nav-text {
  margin-top: 0.5rem; 
}





/* APPNAME CSS CODE */
.app-name {
  grid-area: app-name;
  text-align: center;
  padding: 1rem;
  font-family: Arial, sans-serif;
  font-size: 4rem;
}

.app-name-text {
  color: rgb(175, 174, 174);
}

.app-name-accent {
  color: rgb(209, 77, 4);
}






/* BOOK BOXES */
.main {
  grid-area: main;
  display: flex;
  flex-direction: column; 
  justify-content: center; 
  align-items: center; 
  gap: 20px; 
  padding: 0.5rem;
}

.book-box {
  background-color: #fff;
  width: 50%; 
  padding: 10px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  margin-top: 200px;
}





/* HEADER CSS CODE */
.header {
  grid-area: header;
 display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 1rem;
  background-color: #ffffff;
 
}

.logo img {
  width: 150px; 
  height: auto; 
}

.logo-image {
  width: 100px; 
  height: auto; 
  border-radius: 50%;
  overflow: hidden; 
}

/* SIGN IN SIGN OUT BUTTON CSS CODE */
.login-logout-button {
  padding: 0.5rem 1rem;
  border: none;
  background-color: rgb(209, 77, 4);
  color: white;
  font-weight: bold;
  font-family:Arial, sans-serif;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s, transform 0.3s;
}

.login-logout-button:hover {
  background-color: rgb(255, 102, 0);
  transform: scale(1.05);
}







.app-name {
  grid-area: app-name;
  text-align: center;
  padding: 1rem;
}

.main {
  grid-area: main;
  padding: 0.5rem;
}

.footer {
  grid-area: footer;
  text-align: center;
  padding: 1rem;
}

.nav {
  grid-area: nav;
  text-align: center;
  padding: 0.5rem;
}
</style>