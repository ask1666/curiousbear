<template>
	<h3 class="text-xl p-4 font-semibold w-full text-center">Home</h3>
	<div v-for="quiz in quizzes">
		{{ quiz.name }}
	</div>
</template>

<script lang="ts">
import { ref, onBeforeMount, Ref } from "vue"
import { Quiz } from "../../generated/api"
import { getAllMyQuiz } from "../api/QuizService"
import { useStore } from "vuex"

export default {
	name: 'Home',
	setup() {
		const quizzes: Ref<Quiz[]> = ref([])
		const store = useStore()

		onBeforeMount(async () => {
			if (store) {
				quizzes.value = await getAllMyQuiz(store.getters.getUser)
			}
		})
	}
}
</script>
