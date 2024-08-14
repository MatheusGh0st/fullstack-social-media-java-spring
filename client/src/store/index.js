import { createStore } from "vuex";
import axios from "axios";

const APP_HOST = process.env.APP_HOST;

const defaultState = {
  userId: null,
  accessToken: null,
  isLogged: null,
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
  },
  actions: {
    async loginUser({ commit }, { email, password }) {
      try {
        console.log(APP_HOST);
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
        const userIsLogged = token !== null;
        commit("setAccessToken", token);
        commit("setIsLogged", userIsLogged);
      } catch (error) {
        console.error(error);
      }
    },
  },
});

export default store;
