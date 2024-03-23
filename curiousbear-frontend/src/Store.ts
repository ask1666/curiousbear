import { createStore } from 'vuex'
import { UserCredential } from '../generated'

type Store = {
	user: string | undefined,
	token: AccessToken,
}

export type AccessToken = {
	access_token: String,
	expires_in: Number,
}

export const store = createStore({
	state() {
		return {
			user: '',
			token: {},
		} as Store
	},
	mutations: {
		setUser(state: Store, newUser: string) {
			state.user = newUser
		},
		setToken(state: Store, newToken: AccessToken) {
			state.token.access_token = newToken.access_token
			state.token.expires_in = newToken.expires_in
		},
	},
	getters: {
		getUser(state: Store): string | undefined {
			return state.user
		},
		getToken(state: Store) : AccessToken {
			return state.token
		},
	},
	actions: {},
})
