<template>
    <div :style="'height: 50px; width:'+size+'px'" class="my-btn" @click="onclick">
        <img src="http://ferrisgame.ru:8080/assets/images/button.png" alt="кнопка"
             :style="'position: absolute; left:0; top:0; width:'+size+'px; height:50px;'"/>
        <div class="btn-text">{{text}}</div>
    </div>
</template>

<script>
    export default {
        name: "MyButton",
        props: {
            text: String,
            size: Number,
            onclick: Object
        }
    }
</script>

<style scoped>
    .my-btn {
        -webkit-transition: all 0.3s ease;;
        -moz-transition: all 0.3s ease;;
        -o-transition: all 0.3s ease;;
        transition: all 0.3s ease;
        cursor: pointer;
        position: relative;
    }

    .my-btn:hover {
        -webkit-transform: scale(1.2);
        -ms-transform: scale(1.2);
        transform: scale(1.2);
    }

    .btn-text {
        color: white;
        z-index: 100;
        position: absolute;
        top: 16px;
        width: 100%;

    }
</style>