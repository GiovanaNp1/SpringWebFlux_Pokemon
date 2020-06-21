import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      alias: "/pokemon",
      name: "pokemon",
      component: () => import("./components/pokemon")
    },
    {
      path: "/pokemon/:id",
      name: "pokemonid",
      component: () => import("./components/pokemonid")
    },
    {
      path: "/add",
      name: "add",
      component: () => import("components/add")
    }
  ]
});