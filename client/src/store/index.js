import { createStore } from "vuex";
import axios from "axios";
import { useStorage } from '@vueuse/core';

const APP_HOST = process.env.APP_HOST;

const accessToken = useStorage('access-token', null);
const userId = useStorage('user-id', null);
const isLogged = useStorage('is-logged', null);
const userObj = useStorage('user-object', null);
const responseLogin = useStorage('response-login', null);

const store = createStore({
  state() {
    return {
      userId: JSON.parse(userId.value),
      userObj: JSON.parse(userObj.value),
      responseLogin: JSON.parse(responseLogin.value),
      accessToken: accessToken.value,
      isLogged: !!accessToken.value
    };
  },
  mutations: {
    setAccessToken(state, token) {
      state.accessToken = token;
      accessToken.value = token;
    },
    setUserId(state, id) {
      state.userId = id;
      userId.value = JSON.stringify(id);
    },
    setIsLogged(state, isUserLogged) {
      state.isLogged = isUserLogged;
      isLogged.value = isUserLogged;
    },
    setResponseLogin(state, respLogin) {
      state.responseLogin = respLogin;
      responseLogin.value = JSON.stringify(respLogin);
    },
    setUserObj(state, obj) {
      state.userObj = obj;
      userObj.value = JSON.stringify(obj);
    },
    clearUserData(state) {
      state.accessToken = null;
      state.userId = null;
      state.userObj = null;
      state.responseLogin = null;

      accessToken.value = null;
      userId.value = null;
      userObj.value = null;
      responseLogin.value = null;
    }
  },
  actions: {
    async loginUser({ commit }, { email, password }) {
      try {
        const response = await axios.post(
          `${APP_HOST}/api/auth/login`,
          {
            email,
            password,
          },
          {
            headers: { "Content-Type": "application/json" },
          }
        );
        const { token } = response.data;
        const userIdd = response.data.user.user;
        const userObject = response.data.user;
        const userIsLogged = token !== null;
        commit("setUserObj", userObject);
        commit("setResponseLogin", response);
        commit("setUserId", userIdd);
        commit("setAccessToken", token);
        commit("setIsLogged", userIsLogged);
      } catch (error) {
        commit("setResponseLogin", error);
        console.error(`Error in LoginUser index.js : ${error}`);
      }
    },
    async logoutUser({ commit }) {
      commit("clearUserData");
      commit("setIsLogged", false);
    }
  },
});

export default store;
