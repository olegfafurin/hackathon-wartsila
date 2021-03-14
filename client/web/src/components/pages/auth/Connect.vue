<template>
    <div>
        <MyTextField :value='roomId'/>
        <div class="button-group">
            <my-button :size="180" :onclick='createRoom' :text="'Создать игру'"></my-button>
            <my-button :size="180" :onclick='joinRoom' :text="'Присоединиться'"></my-button>
        </div>
    </div>
</template>

<script>
    import api from '@/axios/api'
    import $axios from '@/axios/instance'
    import MyButton from "@/components/pages/auth/MyButton";
    import MyTextField from "@/components/pages/auth/MyTextField";

    export default {
        name: 'Connect',
        components: {MyTextField, MyButton},
        props: {
            onConnect: Function
        },
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
                    console.log('комната создалась');
                    this.onConnect();

                }).catch(e => {
                    console.log(e);
                    console.log('комната не создалась')
                })
            },
            joinRoom() {
                api($axios).joinRoom(this.roomId).then(r => {
                    console.log(r.data);
                    console.log('присоединился');
                    this.onConnect();
                }).catch(e => {
                    console.log(e);
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