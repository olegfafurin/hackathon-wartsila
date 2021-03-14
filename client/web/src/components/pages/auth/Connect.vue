<template>
    <div>
        <label>
            <input type='text' name='roomId' :value='roomId'>
        </label>
        <div class="button-group">
            <my-button :size="150" :onclick='createRoom' :text="'Создать игру'"></my-button>
            <my-button :size="150" :onclick='joinRoom' :text="'Присоединиться'"></my-button>
        </div>
    </div>
</template>

<script>
    import api from '@/axios/api'
    import $axios from '@/axios/instance'
    import MyButton from "@/components/pages/auth/MyButton";

    export default {
        name: 'Connect',
        components: {MyButton},
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
                this.roomID = Math.random().toString();
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
.button-group{
    width: 400px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;

}
</style>