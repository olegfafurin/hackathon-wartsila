import $axios from '@/axios/instance'
import api from '@/axios/api'

export const userStore = {
    namespaced: true,
    state: () => ({
        login: null
    }),
    mutations: {
        login(state, login) {
            state.login = login
        },
        logout(state) {
            state.login = null
        }
    },
    actions: {
        login(context, formData) {
            api($axios).login(formData.login, formData.password).then(r => {
                localStorage.setItem('accessToken', r.accessToken)
                context.commit('login', r.login)
            }).catch(e => {
                console.log(e)
                context.commit('logout')
            })
        }
    }
}