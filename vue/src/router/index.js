import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import AddBook from '../views/AddBook.vue'
import MyBookShelf from '../views/MyBookShelf.vue'
import BookLibrary from '../views/BookLibrary.vue'
import Topic from '../views/Topic.vue'
import ForumMessages from '../views/ForumMessages.vue'
import DefaultLayout from '@/components/DefaultLayout.vue';
import GoogleMaps from '../components/AddGoogleMap.vue';
import TriviaCorner from '../views/TriviaCorner.vue';

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      component: DefaultLayout,
      children: [
        {
      path: '',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/some-route',
      component: DefaultLayout,
      children: [

      ],
    },
  ],
},
    {
      path:"/addBook",
      name:"addBook",
      component: AddBook,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },


    {
      path:"/MyBookShelf",
      name:"MyBookShelf",
      component: MyBookShelf,
      meta: {
        requiresAuth: true
      }

    },
    {
      path:"/BookLibrary",
      name:"BookLibrary",
      component: BookLibrary,
      meta: {
        requiresAuth: true
      }

    },
    {
      path: '/topic',
      name: 'Topic',
      component: Topic,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/topic/:id',
      name: 'TopicId',
      component: Topic,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/topics/message/:id', 
      name: 'forumMessages',
      component: ForumMessages
    },
    {
      path: '/googlemaps',
      name: 'googleMaps',
      component: GoogleMaps
    },
    {
      path: '/triviacorner',
      name: 'TriviaCorner',
      component: TriviaCorner
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
