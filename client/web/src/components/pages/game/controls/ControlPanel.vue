<template>
    <div class='all'>
        <div class="control-container" :style="containerStyle">
            <div class="top-controls" v-if="size.w/size.h <1.5 && size.w/size.h >0.67">
                <img src="http://ferrisgame.ru:8080/assets/images/cabin.png" alt="Фон" class="background-img"/>
            </div>
            <div class="bottom-controls" :style="bottomStyle">
                <img src="http://ferrisgame.ru:8080/assets/images/panel-background.png" alt="Фон"
                     class="background-img"/>
                <ButtonsBlock :matrix="move"/>
                <div class="fire-buttons">
                    <SpritedButton btn-name="missleF" :onclick="fire"/>
                    <div class="amount-indicator">{{playerData.missiles}}</div>
                    <SpritedButton btn-name="mine" :onclick="mine"/>
                    <div class="amount-indicator">{{playerData.mines}}</div>
                </div>
                <div class='money'>
                    <img src="http://ferrisgame.ru:8080/assets/images/coin.png">
                    <div class="amount-indicator">{{playerData.money}}</div>
                </div>
                <div class="hp-indicator">
                    <img :src="'http://ferrisgame.ru:8080/assets/images/hp'+playerData.hp +'.png'" :alt="playerData.hp"/>
                </div>
            </div>
            <!--        <img src="http://ferrisgame.ru:8080/assets/images/background.png" alt="Фон" class="background-img"  :style="containerStyle"/>-->
        </div>
    </div>
</template>

<script>
    import ButtonsBlock from "@/components/pages/game/controls/ButtonsBlock";
    import api from "@/axios/api";
    import $axios from "@/axios/instance";
    import SpritedButton from "@/components/pages/game/controls/SpritedButton";

    export default {
        name: "ControlPanel",
        components: {SpritedButton, ButtonsBlock},
        props: {
            size: {
                w: Number,
                h: Number,
            },
            playerData: Object
        },
        data() {
            return {
                move:
                    [
                        [false, "forward", false],
                        ["left", "back", "right"],
                    ],
            }
        },
        computed: {
            containerStyle() {
                return "height:" + this.size.h + "px; width:" + this.size.w + "px;"
            },
            bottomStyle() {
                return "height:" + this.size.w / 3 + "px; width:" + this.size.w + "px; padding: " + this.size.w / 30 + "px " + this.size.w / 20 + "px;"
            }
        },
        methods: {
            fire() {
                api($axios).fire().then(r => {
                    console.log(r.data);
                    return r.data;
                }).catch(e => {
                    console.log(e)
                })
            },
            mine() {
                api($axios).mine().then(r => {
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
    .control-container {
        position: relative;
        display: flex;
        flex-direction: column-reverse;
    }
    .fire-buttons{
        display: grid;
        height: 100%;
        grid-template-rows: 45% 45%; /* 2 строки */
        grid-template-columns: 45% 45%; /* 2 строки */
        gap: 10px;
    }

    .bottom-linked{
        display: flex;
        flex-direction: column-reverse;
    }

    .subbackground-img {
        height: 100%;
        width: 100%;
        z-index: -500;
    }

    .background-img {
        height: 100%;
        width: 100%;
        position: absolute;
        top: 0;
        left: 0;
        z-index: -1000;
    }

    .bottom-controls {
        box-sizing: border-box;
        position: relative;
        display: grid;
        grid-template-columns: 30% 20% 10% 20%;
        flex-direction: row-reverse;
        align-content: flex-end;
        width: 100%;
        justify-content: space-between;
        min-width: 300px;
    }

    .button-blocks {
        display: flex;
        justify-content: space-around;

    }
    .amount-indicator {
        font-family: Maler, 'open-sans',serif;
        color: white;
        font-size: 2em;
    }
    .hp-indicator{
        height: 100%;
        display: flex;
        flex-direction: column-reverse;
        padding-right: 5px;
    }
</style>