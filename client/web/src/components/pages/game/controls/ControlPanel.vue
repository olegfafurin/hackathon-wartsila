<template>
    <div class="control-container" :style="containerStyle">
        <div class="bottom-controls">
            <Timer/>
            <div class="button-blocks">
                <ButtonsBlock/>
                <ButtonsBlock/>
            </div>
        </div>
        <img src="@/assets/background.png" alt="Фон" class="background-img"  :style="containerStyle"/>
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
        computed: {
            containerStyle() {
                return "height:" + this.size.h + "px; width:" + this.size.w + "px;"
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


    .background-img {
        height: 100%;
        width: 100%;
        position: absolute;
        top: 0;
        left: 0;
        z-index: -1000;
    }

    .bottom-controls {
        display: flex;
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