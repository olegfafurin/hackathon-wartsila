<template>
    <div class="inp-container">
        <input type="text" @change="onchange" class="my-input">
        <img src="http://ferrisgame.ru:8080/assets/images/text-input.png" alt="Ввод"/>
    </div>
</template>

<script>
    export default {
        name: "MyTextField",
        data() {
            return {
                value: ''
            }
        },
        props: {
            size: {

            },
            onchange: Function
        }
    }
</script>

<style scoped>
    .inp-container{
        position: relative;
    }
    .my-input {
        position: absolute;
        top:10%;
        left:10%;
        width: 80%;
        height: 80%;
        font-family: Maler, sans-serif;
        color: #211813;
        background: none;
        border: none;
        font-size: 2em;
    }
</style>