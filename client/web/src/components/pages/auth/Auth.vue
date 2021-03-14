<template>
  <div>
    <div v-if='loggedIn'>
      <div>Your login: {{ loggedLogin }}</div>
      <button @click='doLogout'>Logout</button>
    </div>
    <div v-else>
      <label>
        <input type='text' name='login' v-model='login'/>
      </label>
      <label>
        <input type='text' name='password' v-model='password'>
      </label>

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
      ).then(() => {
        this.$router.replace("/")
      })
    },
    doLogout() {
      this.$store.dispatch(
          'user/logout'
      ).then(() => {
        this.$router.replace("/login")
      })
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