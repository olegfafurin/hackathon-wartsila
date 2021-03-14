<template>
    <div :class="empty? 'empty-button':'custom-button'">
        <img class="button-sprite" :src="fullPath" :alt="btnName"/>
    </div>
</template>

<script>
    export default {
        name: "SpritedButton",
        props: {
            btnName: String,
            empty: Boolean
        },
        computed: {
            fullPath() {
                return "http://ferrisgame.ru:8080/assets/images/" + this.btnName + ".png";
            }
        }
    }
</script>

<style scoped>
    .button-sprite {
        width: 100%;
    }

    .empty-button {
        opacity: 0;
    }
</style>