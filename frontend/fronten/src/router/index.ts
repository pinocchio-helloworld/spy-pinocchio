import { createRouter, createWebHistory } from "vue-router";
import Login from "@/views/login/userLogin.vue";
import HomeAdmin from "@/views/homepage/adminHomepage.vue";
import AdminBooker from "@/views/booksearch/bookSearch.vue";
import AddBook from "@/views/addnewbook/addBook.vue";
import BorrowBook from "@/views/borrowbook/adminBorrow.vue";
import ReturnBook from "@/views/returnbook/adminReturnBook.vue";
import DeleteBook from "@/views/deletebook/adminDeleteBook.vue";
import Register from "@/views/login/userRegister.vue";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/",
      name: "userLogin",
      component: Login,
    },
    {
      path: "/admin-homepage",
      name: "homepage-admin",
      component: HomeAdmin,
    },
    {
      path: "/admin-booksearch",
      name: "admin-booksearch",
      component: AdminBooker,
    },
    {
      path: "/admin-addbook",
      name: "admin-addbook",
      component: AddBook,
    },
    {
      path: "/admin-borrowbook",
      name: "admin-borrowbook",
      component: BorrowBook,
    },
    {
      path: "/admin-returnbook",
      name: "admin-returnbook",
      component: ReturnBook,
    },
    {
      path: "/admin-deletebook",
      name: "admin-deletebook",
      component: DeleteBook,
    },
    {
      path: "/admin-register",
      name: "admin-register",
      component: Register,
    },
  ],
});

export default router;
