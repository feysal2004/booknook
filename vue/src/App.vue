<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{name: 'AddBook'}">Add Book</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{name:'BookLibrary'}"> Book Library </router-link>&nbsp;|&nbsp;
      <router-link v-bind:to ="{name:'Forum'}"> Forum </router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{name:'MyBookShelf'}"> My BookShelf</router-link>
    </div>
    <router-view />
  </div>
</template>
