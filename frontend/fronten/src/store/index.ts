import { createStore } from "vuex";
import { User } from "./types";

export default createStore({
  state: {
    user: {
      name: "admin",
      role: "admin",
    } as User | null, // 显式指定 user 的类型为 User | null
  },
  getters: {
    getUser: (state): User | null => state.user,
    isAdmin: (state): boolean => state.user?.role === "admin", // 使用可选链操作符
  },
  mutations: {
    SET_USER(state, user: User) {
      state.user = user;
    },
    CLEAR_USER(state) {
      state.user = null;
    },
  },
  actions: {
    setUser({ commit }, user: User) {
      commit("SET_USER", user);
    },
    clearUser({ commit }) {
      commit("CLEAR_USER");
    },
    fetchUser({ commit }) {
      setTimeout(() => {
        const user: User = { name: "John", role: "admin" };
        commit("SET_USER", user);
      }, 1000);
    },
  },
});
