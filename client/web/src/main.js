import Vue from 'vue'
import Vuex from 'vuex'
import App from './App.vue'

Vue.config.productionTip = false
Vue.use(Vuex)

import {store} from '@/store/store'

new Vue({
  render: h => h(App),
  store: store
}).$mount('#app')
