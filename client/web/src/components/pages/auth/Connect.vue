<template>
  <div>
    <label>
      <input type='text' name='roomId' :value='roomId'>
    </label>
    <button v-if='emptyId' @click='createRoom'>Create game</button>
    <button v-else @click='joinRoom'>Join game</button>
  </div>
</template>

<script>
import api from '@/axios/api'
import $axios from '@/axios/instance'

export default {
  name: 'Connect',
  data() {
    return {
      roomId: ''
    }
  },
  computed: {
    emptyId() {
      return this.roomId === ''
    }
  },
  methods: {
    createRoom() {
      api($axios).createRoom(this.roomId).then(r => {
        console.log(r.data)
        console.log('комната создалась')
        setInterval(this.getField, 1000)
      }).catch(e => {
        console.log(e)
        console.log('комната не создалась')
      })
    },
    joinRoom() {
      api($axios).joinRoom(this.roomId).then(r => {
        console.log(r.data)
        console.log('присоединился')
        setInterval(this.getField, 1000)
      }).catch(e => {
        console.log(e)
        console.log('не присоединился')
      })
    }
  }
}
</script>

<style scoped>

</style>