import { createStore } from 'vuex'
import { UserCredential } from '../generated'

type Store = {
	user: UserCredential | undefined,
	token: String,
}

export const store = createStore({
	state() {
		return {
			user: undefined,
			token: '',
		} as Store
	},
	mutations: {
		setUser(state: Store, newUser) {
			state.user = newUser
		},
		setToken(state: Store, newToken) {
			state.token = newToken
		},
	},
	getters: {
		getUser(state: Store) {
			return state.user
		},
		getToken(state: Store) {
			return state.token
		},
	},
	actions: {},
})
