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
            return api($axios).login(formData.login, formData.password).then(r => {
                const data = r.data
                localStorage.setItem('accessToken', data.accessToken)
                context.commit('login', data.login)
                return data
            }).catch(e => {
                console.log(e)
            })
        },
        logout(context) {
            return api($axios).logout().then(() => {
                localStorage.removeItem('accessToken')
                context.commit('logout')
            }).catch(e => {
                console.log(e)
            })
        }
    }
}