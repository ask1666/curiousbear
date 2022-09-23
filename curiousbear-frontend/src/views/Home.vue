<template>
	<h3 class="text-xl p-4 font-semibold w-full text-center">Home</h3>
	<template v-if="quizzes.length">
		<div v-for="quiz in quizzes">
			{{ quiz.name }}
		</div>
	</template>
	<div v-else>
		<p class="text-center text-red-400 font-bold">No quizzes</p>
	</div>
</template>

<script lang="ts">
import { ref, onBeforeMount, Ref } from "vue"
import { Quiz } from "../../generated/api"
import { getAllMyQuiz } from "../api/QuizService"
import { useStore } from "vuex"
import { useRouter } from "vue-router"
import { profileRoutes } from "../routes"

export default {
	name: 'Home',
	setup() {
		const quizzes: Ref<Quiz[]> = ref([])
		const store = useStore()
		const router = useRouter()

		onBeforeMount(async () => {
			if (!!store.getters.getUser) {
				quizzes.value = await getAllMyQuiz(store.getters.getUser)
			} else {
				router.push('/createUser')
			}
		})
		return {
			quizzes
		}
	}
}
</script>
