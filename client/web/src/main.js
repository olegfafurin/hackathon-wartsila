import Vue from 'vue'
import Vuex from 'vuex'
import App from './App.vue'

Vue.config.productionTip = false
Vue.use(Vuex)

import {store} from '@/store/store'
import {router} from '@/router/router'

new Vue({
    render: h => h(App),
    store: store,
    router: router
}).$mount('#app')
