import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import './index.css'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faBars } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
// @ts-ignore
import vClickOutside from 'click-outside-vue3'
library.add(...[faBars])

createApp(App)
	.component('font-awesome-icon', FontAwesomeIcon)
	.use(router)
	.use(vClickOutside)
	.mount('#app')
