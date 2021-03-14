<template>
    <div class="button-container" :style="compStyle">
        <SpritedButton :btn-name="matrix[0][0]" :empty="!matrix[0][0]"/>
        <SpritedButton :btn-name="matrix[0][1]" :empty="!matrix[0][1]"/>
        <SpritedButton :btn-name="matrix[0][2]" :empty="!matrix[0][2]"/>

        <SpritedButton :btn-name="matrix[1][0]" :empty="!matrix[1][0]"/>
        <SpritedButton :btn-name="matrix[1][1]" :empty="!matrix[1][1]"/>
        <SpritedButton :btn-name="matrix[1][2]" :empty="!matrix[1][2]"/>

        <SpritedButton :btn-name="matrix[2][0]" :empty="!matrix[2][0]"/>
        <SpritedButton :btn-name="matrix[2][1]" :empty="!matrix[2][1]"/>
        <SpritedButton :btn-name="matrix[2][2]" :empty="!matrix[2][2]"/>

    </div>
</template>

<script>
    import SpritedButton from "@/components/pages/game/controls/SpritedButton";

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
        }
    }
</script>

<style scoped>
    .button-container {
        display: grid;
        height: 100%;
        grid-template-rows: 25% 25% 25%; /* 3 строки */
        grid-template-columns: 25% 25% 25%; /* 3 столбца */
        gap: 1em;
    }
</style>