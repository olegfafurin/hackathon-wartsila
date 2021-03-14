import axios from 'axios'

const $axios = axios.create({
    baseURL: 'http://ferrisgame.ru:8080/',
    headers: {
        'Access-Control-Allow-Origin': '*',
        'Content-Type': 'application/json'
    }
})

$axios.interceptors.request.use(
    r => {
        let accessToken = localStorage.getItem('accessToken')
        if (accessToken) {
            r.headers['Authorization'] = `Bearer ${accessToken}`
        }
        return r
    },
    e => Promise.reject(e)
)

export default $axios