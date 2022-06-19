<template>
	<h3 class="text-xl p-4 font-semibold w-full text-center">Create User</h3>

	<form @submit="submit" class="flex justify-center">
		<div class="w-[38%] border flex flex-col items-center shadow-md rounded p-3">
			<label class="formLabel">
				<p class="font-bold">Username:</p>
				<input class="formInput" v-model="username" placeholder="CoolUsername..." />
			</label>

			<label class="formLabel">
				<p class="font-bold">Password:</p>
				<input class="formInput" v-model="password" placeholder="y2DqDTqd8Q7V..." type="password" />
			</label>
			<div class="flex justify-between w-2/3 p-2">
				<button class="optionalButton" @click="goToCreateUser" type="button">
					Create User
				</button>
				<button class="primaryButton" type="submit">Submit</button>
			</div>
		</div>
	</form>
</template>

<script lang="ts">
import { reactive, toRefs } from '@vue/reactivity'
import { CustomRoute, routes } from '../routes'
import { useRouter } from 'vue-router'
import { login } from '../api/UserService'
import axios, { AxiosResponse } from 'axios'
import { useStore } from 'vuex'
import { AccessToken } from '../Store'

type State = {
	username: string
	password: string
}

export default {
	name: 'Login',
	setup() {
		const state = reactive<State>({ username: '', password: '' })
		const router = useRouter()
		const store = useStore()

		const submit = async (e: Event) => {
			e.preventDefault()
			const response: AxiosResponse = await login(state.username, state.password)!
			store.commit('setToken', { access_token: response.data.access_token, expires_in: response.data.expires_in } as AccessToken)
			store.commit('setUser', response.data.username)
			axios.defaults.headers.common['Authorization'] = 'Bearer ' + store?.getters?.getToken?.access_token ?? ""
		}

		const goToRoute = (route: CustomRoute) => {
			router.push(route.path)
		}

		return {
			...toRefs(state),
			routes,
			submit,
			goToCreateUser: () => {
				goToRoute(routes.find((e: CustomRoute) => e.name === 'Create User') as CustomRoute)
			}
		}
	},
}
</script>
