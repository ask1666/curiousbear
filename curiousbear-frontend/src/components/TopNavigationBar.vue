<!-- This example requires Tailwind CSS v2.0+ -->
<template>
	<div as="nav" class="bg-gray-800">
		<div class="max-w-full mx-auto px-2 sm:px-6 lg:px-8">
			<div class="relative flex items-center justify-between h-16">
				<div class="absolute inset-y-0 left-0 flex items-center sm:hidden">
					<!-- Mobile menu button-->
					<button
						class="
							inline-flex
							items-center
							justify-center
							p-2
							rounded-md
							text-gray-400
							hover:text-white hover:bg-gray-700
							focus:outline-none focus:ring-2 focus:ring-inset focus:ring-white
						"
						@click.stop="mobileMenuToggled = !mobileMenuToggled"
					>
						<font-awesome-icon icon="bars" />
						<span class="sr-only">Open main menu</span>
					</button>
				</div>
				<div
					class="
						flex-1 flex
						items-center
						justify-center
						sm:items-stretch sm:justify-start
					"
				>
					<router-link
						to="/"
						class="
							flex-shrink-0 flex
							items-center
							bg-gray-800
							pr-1
							rounded-lg
							shadow-md
						"
					>
						<img
							class="block h-6 w-auto"
							src="../assets/curousbear_logo3.png"
							alt="Workflow"
						/>
					</router-link>
					<div class="hidden sm:block sm:ml-6">
						<div class="flex space-x-4">
							<router-link
								v-for="item in navigations"
								:key="item.name"
								:to="item.href"
								:class="[
									item.current
										? 'bg-gray-900 text-white'
										: 'text-gray-300 hover:bg-gray-700 hover:text-white',
									'px-3 py-2 rounded-md text-sm font-medium',
								]"
								>{{ item.name }}
							</router-link>
						</div>
					</div>
				</div>
				<div
					class="
						absolute
						inset-y-0
						right-0
						flex
						items-center
						pr-2
						sm:static sm:inset-auto sm:ml-6 sm:pr-0
					"
				>
					<button
						type="button"
						class="
							bg-gray-800
							p-1
							rounded-full
							text-gray-400
							hover:text-white
							focus:outline-none
							focus:ring-2
							focus:ring-offset-2
							focus:ring-offset-gray-800
							focus:ring-white
						"
					>
						<span class="sr-only">View notifications</span>
						<bell-icon class="h-6 w-6" aria-hidden="true" />
					</button>

					<!-- Profile dropdown -->
					<div as="div" class="ml-3 relative">
						<div>
							<button
								class="
									bg-gray-800
									flex
									text-sm
									rounded-full
									focus:outline-none
									focus:ring-2
									focus:ring-offset-2
									focus:ring-offset-gray-800
									focus:ring-white
								"
								@click.stop="profileMenuToggled = !profileMenuToggled"
							>
								<span class="sr-only">Open user menu</span>
								<font-awesome-icon
									class="h-6 w-6 text-white rounded-full"
									icon="user"
								/>
								<!-- <img
                  class="h-8 w-8 rounded-full"
                  src="https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80"
                  alt=""
                /> -->
							</button>
						</div>
						<div
							v-show="profileMenuToggled"
							v-click-outside="() => (profileMenuToggled = false)"
							class="
								origin-top-right
								absolute
								right-0
								mt-2
								w-48
								rounded-md
								shadow-lg
								py-1
								bg-white
								ring-1 ring-black ring-opacity-5
								focus:outline-none
							"
						>
							<div>
								<router-link
									to="#"
									class="
										block
										px-4
										py-2
										text-sm text-gray-700
										hover:bg-gray-200
									"
									>Your Profile
								</router-link>
							</div>
							<div>
								<router-link
									to="#"
									class="
										block
										px-4
										py-2
										text-sm text-gray-700
										hover:bg-gray-200
									"
									>Settings
								</router-link>
							</div>
							<div>
								<router-link
									to="#"
									class="
										block
										px-4
										py-2
										text-sm text-gray-700
										hover:bg-gray-200
									"
									>Sign out
								</router-link>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="sm:hidden">
			<div
				v-click-outside="() => (mobileMenuToggled = false)"
				v-show="mobileMenuToggled"
				class="px-2 pt-2 pb-3 space-y-1"
			>
				<router-link
					v-for="item in navigations"
					:key="item.name"
					:to="item.href"
					:class="[
						item.current
							? 'bg-gray-900 text-white'
							: 'text-gray-300 hover:bg-gray-700 hover:text-white',
						'block px-3 py-2 rounded-md text-base font-medium',
					]"
					>{{ item.name }}
				</router-link>
			</div>
		</div>
	</div>
</template>

<script lang="ts">
import { computed, watch, ComputedRef, reactive, toRefs } from 'vue'
import { BellIcon, MenuIcon, XIcon } from '@heroicons/vue/outline'
import { useRoute, useRouter, RouterLink } from 'vue-router'
import { useStore } from 'vuex'
import { routes, protectedRoutes } from '../routes'
// @ts-ignore
import vClickOutside from 'click-outside-vue3'

type State = {
	currentRouteName: string
	profileMenuToggled: boolean
	mobileMenuToggled: boolean
}

type Navigation = {
	name: string
	href: string
	current: boolean
}

export default {
	components: {
		BellIcon,
		MenuIcon,
		XIcon,
		RouterLink,
	},
	directives: {
		vClickOutside: vClickOutside.directive,
	},
	setup() {
		const state: State = reactive({
			currentRouteName: '',
			profileMenuToggled: false,
			mobileMenuToggled: false,
		})

		const store = useStore()
		const route = useRoute()
		const router = useRouter()

		const isCurrent = (routeName: string) => {
			return state.currentRouteName === routeName
		}

		watch(route, () => {
			state.currentRouteName = route.name?.toString() ?? ''
		})

		const isLoggedIn = computed(() => {
			const user = store.getters.getUser
			const currentRoute = router.currentRoute.value
			console.log(user), console.log(protectedRoutes)
			console.log(currentRoute)
			console.log(protectedRoutes.find((e) => e.path == currentRoute.path))
			if (
				user !== undefined ||
				protectedRoutes.find((e) => e.path == currentRoute.path) === undefined
			) {
				return true
			} else {
				router.push('/login')
				return false
			}
		})

		// TODO - set up more route categories in routes.ts for display in main links, under profileDropDown etc..
		const navigations: ComputedRef<Navigation[]> = computed(() => {
			const things: Navigation[] = []

			routes.forEach((e) => {
				things.push({
					name: e.name,
					href: e.path,
					current: isCurrent(e.name),
				} as Navigation)
			})

			return things
		})

		return {
			...toRefs(state),
			isLoggedIn,
			navigations,
			routes,
		}
	},
}
</script>
