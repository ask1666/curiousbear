import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import './index.css'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faBars, faUser } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
// @ts-ignore
import vClickOutside from 'click-outside-vue3'
// @ts-ignore
import { store } from './Store'
library.add(...[faBars, faUser])

createApp(App)
	.component('font-awesome-icon', FontAwesomeIcon)
	.use(router)
	.use(store)
	.use(vClickOutside)
	.mount('#app')
