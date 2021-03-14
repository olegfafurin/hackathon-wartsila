<template>
    <div class="button-container">
        <SpritedButton btn-name="b11" :empty="true"/>
        <SpritedButton btn-name="b12"/>
        <SpritedButton btn-name="b13" :empty="true"/>

        <SpritedButton btn-name="b21"/>
        <SpritedButton btn-name="b22" :empty="true"/>
        <SpritedButton btn-name="b23"/>

        <SpritedButton btn-name="b31" :empty="true"/>
        <SpritedButton btn-name="b32"/>
        <SpritedButton btn-name="b33" :empty="true" />
    </div>
</template>

<script>
    import SpritedButton from "@/components/pages/game/controls/SpritedButton";

    export default {
        name: "ButtonsBlock",
        components: {SpritedButton}
    }
</script>

<style scoped>
    .button-container {
        display: grid;
        width: 300px;
        height: 300px;
        grid-template-rows: 25% 25% 25%; /* 3 строки */
        grid-template-columns: 25% 25% 25%; /* 3 столбца */
        gap: 1em;
    }
</style>