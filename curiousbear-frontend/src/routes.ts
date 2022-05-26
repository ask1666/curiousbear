import Home from './views/Home.vue'
import About from './views/About.vue'
import CreateUser from './views/CreateUser.vue'
import Login from './views/Login.vue'

export const protectedRoutes = [
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

export const routes = [
	...protectedRoutes,
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

export default routes