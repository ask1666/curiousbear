<template>
	<h3 class="text-xl p-4 font-semibold w-full text-center">Create Quiz</h3>
	<form @submit="submit" class="flex justify-center">
		<div class="formInnerContainer">
			<label class="formLabel">
				<p class="font-bold">Title:</p>
				<input class="formInput" v-model="title" placeholder="Cool quiz..." />
			</label>

			<label class="formLabel">
				<p class="font-bold">Description:</p>
				<input class="formInput" v-model="description" placeholder="Quiz about monkeys..." />
			</label>

			<label class="formLabel">
				<p class="font-bold">Do you want the quiz to be public?</p>
				<input class="formInput" v-model="isPublic" type="checkbox" />
			</label>
			<div class="flex justify-between w-2/3 p-2">
				<button class="primaryButton" type="submit">Submit</button>
			</div>
		</div>
	</form>
</template>

<script lang="ts">
import { ref, onBeforeMount, Ref, reactive, toRefs } from "vue"
import { createQuiz } from '../api/QuizService'
import { useRouter } from 'vue-router'
import routes, { CustomRoute } from '../routes'

type State = {
	title: string,
	description: string,
	isPublic: boolean
}

export default {
	name: 'Home',
	setup() {
		const state = reactive<State>({
			title: '',
			description: '',
			isPublic: false
		})
		const router = useRouter()

		const goToRoute = (route: CustomRoute) => {
			router.push(route.path)
		}

		const goToHome = () => {
				goToRoute(routes.find((e: CustomRoute) => e.name === 'Home') as CustomRoute)
			}

		const submit = (e: Event) => {
			e.preventDefault()
			createQuiz(
				state.title,
				state.description,
				state.isPublic)

			goToHome()
			
		}

		return {
			...toRefs(state),
			submit
		}
	}
}
</script>