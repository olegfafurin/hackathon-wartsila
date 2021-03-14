<template>
  <div>
    <label>
      <input type='text' name='login' :value='login'/>
    </label>
    <label>
      <input type='text' name='password' :value='password'>
    </label>

    <div v-if='loggedIn'>
      <div>Your login: {{loggedLogin}}</div>
      <button @click='doLogout'>Logout</button>
    </div>
    <div v-else>
      <button @click='doRegister'>Register</button>
      <button @click='doLogin'>Login</button>
    </div>
  </div>
</template>

<script>
import api from '@/axios/api'
import $axios from '@/axios/instance'

export default {
  name: 'Auth',
  data() {
    return {
      login: '',
      password: ''
    }
  },
  computed: {
    loggedLogin() {
      return this.$store.state.user.login
    },
    loggedIn() {
      return this.$store.state.user.login != null
    }
  },
  methods: {
    doLogin() {
      this.$store.dispatch(
          'user/login',
          {login: this.login, password: this.password}
      )
    },
    doLogout() {
      this.$store.commit('user/logout')
    },
    doRegister() {
      api($axios).register(this.login, this.password).then(() => {
        alert('Ok')
      }).catch(e => {
        alert(e)
      })
    }
  }
}
</script>

<style scoped>

</style>