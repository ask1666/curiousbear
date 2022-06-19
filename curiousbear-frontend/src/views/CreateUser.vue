<template>
	<h3 class="text-xl p-4 font-semibold w-full text-center">Create User</h3>

	<form @submit="submit" class="flex justify-center">
		<div class="w-[38%] border flex flex-col items-center shadow-md rounded p-3">
			<label class="formLabel">
				<p class="font-bold">Username:</p>
				<input class="formInput" v-model="username" placeholder="CoolUsername..." />
			</label>

			<label class="formLabel">
				<p class="font-bold">Email:</p>
				<input class="formInput" v-model="email" placeholder="someone@mail.com" />
			</label>

			<label class="formLabel">
				<p class="font-bold">Password:</p>
				<input class="formInput" :v-model="password" placeholder="y2DqDTqd8Q7V..." type="password" />
			</label>
			<div class="flex justify-between w-2/3 p-2">
				<button class="optionalButton" @click="goToLogin" type="button">Login</button>
				<button class="primaryButton" type="submit">Submit</button>
			</div>
		</div>
	</form>
</template>

<script lang="ts">
import { createUser } from '../api/UserService'
import { CreateUserDto } from '../../generated'
import { reactive, toRefs } from '@vue/reactivity'
import { useRouter } from 'vue-router'
import routes, { CustomRoute } from '../routes'

type State = {
	username: string
	email: string
	password: string
}

export default {
	name: 'CreateUser',
	setup() {
		const state = reactive<State>({ username: '', email: '', password: '' })
		const router = useRouter()

		const goToRoute = (route: CustomRoute) => {
			router.push(route.path)
		}

		const submit = (e: Event) => {
			e.preventDefault()
			createUser({
				username: state.username,
				email: state.email,
				password: state.password,
			} as CreateUserDto)
		}

		return {
			...toRefs(state),

			goToLogin: () => {
				goToRoute(routes.find((e: CustomRoute) => e.name === 'Login') as CustomRoute)
			},

			submit,
		}
	},
}
</script>
