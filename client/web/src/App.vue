<template>
  <div id="app">
    <Game msg="Welcome to Your Vue.js App"/>
    <router-view></router-view>

  </div>
</template>

<script>
import Game from "@/components/pages/game/Game";

export default {
  name: 'App',
  components: {
    Game
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
