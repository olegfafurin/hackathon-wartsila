<template>
  <div class="hello">
    <h1>Wartsila game</h1>
    <Radar :size="300"></Radar>
  </div>
</template>

<script>
  import Radar from "@/components/pages/game/controls/radar/Radar";
  export default {
    name: 'Game',
    components: {Radar},
    props: {
      msg: String
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h3 {
    margin: 40px 0 0;
  }
  ul {
    list-style-type: none;
    padding: 0;
  }
  li {
    display: inline-block;
    margin: 0 10px;
  }
  a {
    color: #42b983;
  }
</style>
