<template>
    <div class="control-container">
        <div class="bottom-controls">
            <Timer/>

            <ButtonsBlock/>
            <ButtonsBlock/>
        </div>
        <img src="@/assets/background.png" alt="Фон" class="background-img"/>
    </div>
</template>

<script>
    import Timer from "@/components/pages/game/controls/Timer";
    import ButtonsBlock from "@/components/pages/game/controls/ButtonsBlock";

    export default {
        name: "ControlPanel",
        components: {ButtonsBlock, Timer}
    }
</script>

<style scoped>
    .control-container {
        position: relative;
        height: 100vh;
        width: calc(100% - 100vh);
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
    }
</style>