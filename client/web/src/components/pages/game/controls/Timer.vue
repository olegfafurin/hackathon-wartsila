<template>
    <div class="timer-container">
        <img src="@/assets/timer.png" alt="Часики-то тикают" class="timer-img">
    </div>
</template>

<script>
    export default {
        name: "Timer"
    }
</script>

<style scoped>
    .timer-container{
        width: 25%;
        display: flex;
        flex-direction: column-reverse;
    }
    .timer-img{
        width: 100%;

    }

</style>