import { createStore } from "vuex";
import axios from "axios";

const APP_HOST = process.env.APP_HOST;

const defaultState = {
  userId: null,
  accessToken: null,
  isLogged: null,
  responseLogin: null,
};

const store = createStore({
  state() {
    return { ...defaultState };
  },
  mutations: {
    setAccessToken(state, accessToken) {
      state.accessToken = accessToken;
    },
    setUserId(state, userId) {
      state.userId = userId;
    },
    setIsLogged(state, isLogged) {
      state.isLogged = isLogged;
    },
    setResponseLogin(state, responseLogin) {
      state.responseLogin = responseLogin;
    },
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
        const { userId } = response.data;
        const userIsLogged = token !== null;
        commit("setResponseLogin", response);
        commit("setUserId", userId);
        commit("setAccessToken", token);
        commit("setIsLogged", userIsLogged);
      } catch (error) {
        commit("setResponseLogin", error);
        console.error(`Error in LoginUser index.js : ${error}`);
      }
    },
  },
});

export default store;
