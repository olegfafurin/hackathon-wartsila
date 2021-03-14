<template>
    <div class="button-container" :style="compStyle">
        <SpritedButton :btn-name="matrix[0][0]" :empty="!matrix[0][0]"/>
        <SpritedButton :btn-name="matrix[0][1]" :empty="!matrix[0][1]" :onclick="() => move()"/>
        <SpritedButton :btn-name="matrix[0][2]" :empty="!matrix[0][2]"/>

        <SpritedButton :btn-name="matrix[1][0]" :empty="!matrix[1][0]" :onclick="() => rotate(1)"/>
        <SpritedButton :btn-name="matrix[1][1]" :empty="!matrix[1][1]" :onclick="() => rotate(2)"/>
        <SpritedButton :btn-name="matrix[1][2]" :empty="!matrix[1][2]" :onclick="() => rotate(3)"/>


    </div>
</template>

<script>
    import SpritedButton from "@/components/pages/game/controls/SpritedButton";
    import api from "@/axios/api";
    import $axios from "@/axios/instance";

    export default {
        name: "ButtonsBlock",
        components: {SpritedButton},
        props: {
            size: Number,
            matrix: []
        },
        computed: {
            compStyle() {
                return "height:" + this.size + "px; width:" + this.size + "px;"
            }
        },
        methods: {
            move() {
                api($axios).move().then(r => {
                    console.log(r.data);
                    return r.data;
                }).catch(e => {
                    console.log(e)
                })
            },
            rotate(i) {
                api($axios).rotate(i).then(r => {
                    console.log(r.data);
                    return r.data;
                }).catch(e => {
                    console.log(e)
                })
            }
        }
    }
</script>

<style scoped>
    .button-container {
        display: grid;
        grid-template-rows: 40% 40%; /* 3 строки */
        grid-template-columns: 25% 25% 25%; /* 3 столбца */
        gap: 10px;
    }
</style>