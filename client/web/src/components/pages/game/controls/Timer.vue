<template>
    <div class="timer-container">
        <div class="timer-anim-box">
            <div class="innerCircle" :style="timercolor"/>
            <img src="http://ferrisgame.ru:8080/assets/images/timer.png" alt="Часики-то тикают" class="timer-img">
            <div class="timer-text">{{total-current}} с</div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Timer",
        props: {
            current: Number,
            total: Number,
        },
        computed: {
            timercolor() {
                return "background: rgb(" + 255 * (this.current / this.total) + ", "+ 255 * (1-this.current / this.total) +", 28); ";
            }
        }
    }
</script>

<style scoped>
    .timer-container {
        display: flex;
        flex-direction: column-reverse;
    }

    .timer-img {
        width: 100%;
        cursor: pointer;
    }

    .timer-anim-box {
        position: relative;
    }

    .innerCircle {
        box-sizing: border-box;
        margin: 5% 10%;
        position: absolute;
        height: 80%;
        width: 80%;
        z-index: -200;
        border-radius: 1222222px;

    }

    .timer-text {
        position: absolute;
        top: 84%;
        left: 3%;
        height: 80%;
        width: 100%;
        font-family: Maler, serif;
        color: white;
    }


</style>