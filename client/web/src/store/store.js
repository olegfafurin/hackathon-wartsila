import Vue from 'vue'
import Vuex from 'vuex'

import {userStore} from '@/store/modules/user'
import {gameStore} from '@/store/modules/game'

Vue.use(Vuex)

export const store = new Vuex.Store({
    modules: {
        user: userStore,
        game: gameStore
    }
})