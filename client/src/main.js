import { createApp } from "vue";
import { createRouter, createWebHistory } from "vue-router";
import store from "./store/index.js";
import App from "./App.vue";
import ProfileCard from "./components/leftMenu/ProfileCard.vue";
import Profile from "./components/Profile.vue";
import SignIn from "./components/SignIn.vue";
import SignUp from "./components/SignUp.vue";
import "./index.css";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/sign-in",
      name: "SignIn",
      components: { main: SignIn },
      meta: { requiresVisitor: true },
    },
    {
      path: "/sign-up",
      name: "SignUp",
      components: { main: SignUp },
      meta: { requiresVisitor: true },
    },
    {
      path: "/",
      name: "App",
      component: App,
      meta: { requiresAuth: true },
    },
    {
      path: "/profile",
      name: "Profile",
      components: { main: Profile },
      meta: { requiresAuth: true },
    },
    {
      path: "/profile/:username",
      name: "ProfileCard",
      components: { main: ProfileCard },
      meta: { requiresAuth: true },
    },
  ],
});

router.beforeEach((to, from, next) => {
  const loggedIn = store.state.isLogged;

  if (to.matched.some((record) => record.meta.requiresAuth) && !loggedIn) {
    next("/sign-in");
  } else if (
    to.matched.some((record) => record.meta.requiresVisitor) &&
    loggedIn
  ) {
    next("/");
  } else {
    next();
  }
});

createApp(App).use(router).use(store).mount("#app");
