export default function (axios) {
    return {
        register(login, password) {
            return axios.post('/user/register', {login, password})
        },
        login(login, password) {
            return axios.post('/user/login', {login, password})
        },
        logout() {
            return axios.post('/user/logout')
        },

        createRoom() {
            return axios.post('/game/createRoom')
        },
        joinRoom(roomId) {
            return axios.post('/game/joinRoom', {roomId})
        },
        getField() {
            return axios.get('/game/getField')
        }
    }
}