import $axios from '@/axios/instance'
import api from '@/axios/api'

export const gameStore = {
    namespaced: true,
    state: () => ({
        failed: false,
        status: null
    }),
    mutations: {
        setFailed(state, failed) {
            state.failed = failed
        },
        setStatus(state, status) {
            state.status = status
        }
    },
    actions: {
        move(context, direction) {
            return api($axios).move(direction).then(r => {
                context.commit('setStatus', r.data.status)
                context.commit('setFailed', false)
            }).catch(e => {
                context.commit('setStatus', e.response)
                context.commit('setFailed', true)
            })
        },
        fire(context, direction) {
            return api($axios).fire(direction).then(r => {
                context.commit('setStatus', r.data.status)
                context.commit('setFailed', false)
            }).catch(e => {
                context.commit('setStatus', e.response)
                context.commit('setFailed', true)
            })
        }
    }
}