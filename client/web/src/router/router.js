import Vue from 'vue'
import VueRouter from 'vue-router'
import Auth from '@/components/pages/auth/Auth'
import Rules from '@/components/pages/auth/Rules'

Vue.use(VueRouter)

export const router = new VueRouter({
    routes: [
        {path: '/login', component: Auth},
        {path: '/', component: Rules}
    ]
})