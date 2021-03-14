<template>
    <div>
        <MyTextField :onchange="onChange"/>
        <MyTextField :onchange="onChangeLevel"/>
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
                roomId: '',
                levelId: 9,
            }
        },
        computed: {
            emptyId() {
                return this.roomId === ''
            }
        },
        methods: {
            createRoom() {
                console.log(this.roomId);
                api($axios).createRoom({roomName: this.roomId, levelId: this.levelId}).then(r => {
                    console.log(r.data);
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
            },
            onChange(ev) {
                this.roomId = ev.target.value;
            },
            onChangeLevel(ev) {
                this.levelId = ev.target.value;
            }
        }
    }
</script>

<style scoped>
.button-group{
    width: 100%;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-content: center;
}

    @media screen and (max-width: 600px) {
        flex-direction: column;

    }
</style>