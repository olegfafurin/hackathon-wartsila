<template>
  <div class='all'>
    <router-view></router-view>
    <div class="control-container" :style="containerStyle">
        <div class="top-controls">
            <img src="http://localhost:8080/images/cabin.png" alt="Фон" class="background-img"/>
        </div>
        <div class="bottom-controls" :style="bottomStyle">
            <img src="http://ferrisgame.ru:8080/assets/images/panel-background.png" alt="Фон" class="background-img"/>
            <ButtonsBlock :matrix="move"/>
            <ButtonsBlock :matrix="fire"/>
            <Timer current="12" total="72"/>
        </div>
<!--        <img src="http://ferrisgame.ru:8080/assets/images/background.png" alt="Фон" class="background-img"  :style="containerStyle"/>-->
    </div>
  </div>
</template>

<script>
    import Timer from "@/components/pages/game/controls/Timer";
    import ButtonsBlock from "@/components/pages/game/controls/ButtonsBlock";

    export default {
        name: "ControlPanel",
        components: {ButtonsBlock, Timer},
        props: {
            size: {
                w: Number,
                h: Number,
            }
        },
        data() {
            return{
                move:
                [
                    [false,"forward",false],
                    ["left",false,"right"],
                    [false,"back",false]
                ],
                fire:
                [
                    [false,"missleF",false],
                    ["missleL","mine","missleR"],
                    [false,"missleB",false]
                ],
            }
        },
        computed: {
            containerStyle() {
                return "height:" + this.size.h + "px; width:" + this.size.w + "px;"
            },
            bottomStyle() {
                return "height:" + this.size.w /3 + "px; width:" + this.size.w + "px; padding: " + this.size.w/30 + "px " + this.size.w/20 + "px;"
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
        grid-template-columns: 30% 30% 25%;
        flex-direction: row-reverse;
        align-content: flex-end;
        width: 100%;
        justify-content: space-between;

    }

    .button-blocks {
        display: flex;
        justify-content: space-around;

    }
</style>