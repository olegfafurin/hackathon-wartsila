<template>
  <div id="app">
    <Game msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
import Game from "@/components/pages/game/Game";
import Auth from '@/components/pages/auth/Auth';

export default {
  name: 'App',
  components: {
    Game, Auth
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
  body {
    margin: 0;
  }
</style>
