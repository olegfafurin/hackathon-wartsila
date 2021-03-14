<template>
  <div>
    <div v-if='loggedIn'>
      <div>Your login: {{ loggedLogin }}</div>
      <button @click='doLogout'>Выйти</button>
    </div>
    <div v-else>
      <label>
        <input type='text' name='login' v-model='login'/>
      </label>
      <label>
        <input type='text' name='password' v-model='password'>
      </label>
      <div class="btn-group">
        <MyButton @click='doRegister' :size="160" text="Регистрация"></MyButton>
        <MyButton @click='doLogin' :size="160" text="Вход"></MyButton>
      </div>
    </div>
  </div>
</template>

<script>
import api from '@/axios/api'
import $axios from '@/axios/instance'
import MyButton from "@/components/pages/auth/MyButton";

export default {
  name: 'Auth',
  components: {MyButton},
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
.btn-group{
  display: flex;
  flex-direction: row;

}
</style>