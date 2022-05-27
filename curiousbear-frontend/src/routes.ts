import Home from './views/Home.vue'
import About from './views/About.vue'
import CreateUser from './views/CreateUser.vue'
import Login from './views/Login.vue'
import { Component } from 'vue'

type CustomRoute = {
	path: string,
		name: string,
		component: Component,
}

export const protectedRoutes: CustomRoute[] = [

]

export const topBarRoutes: CustomRoute[] = [
  {
		path: '/',
		name: 'Home',
		component: Home,
	},
	{
		path: '/about',
		name: 'About',
		component: About,
	},
]

export const profileRoutes: CustomRoute[] = [
  {
		path: '/createUser',
		name: 'Create User',
		component: CreateUser,
	},
	{
		path: '/login',
		name: 'Login',
		component: Login,
	},
]

export const routes: CustomRoute[] = [
	...protectedRoutes,
	...profileRoutes,
	...topBarRoutes
]

export default routes