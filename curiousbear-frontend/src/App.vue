<template>
	<div>
		<top-navigation-bar />
		<router-view />
	</div>
</template>

<script lang="ts">
import TopNavigationBar from './components/TopNavigationBar.vue'

export default {
	name: 'App',
	components: { TopNavigationBar },
	setup() {
		return {}
	},
}
</script>
