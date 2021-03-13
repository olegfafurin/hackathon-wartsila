<template>
    <div :class="className" :style="stylePosition">
    </div>
</template>

<script>
    export default {
        name: "Point",
        props: {
            current: Boolean,
            active: Boolean,
            position: {
                x: Number,
                y: Number,
            },
            description: Object,
        },
        computed: {
            className() {
                return "point" + (this.active ? " point-active" : "") + (this.current ? " point-current" : "")
            },
            stylePosition() {
                return "top:" + this.position.x + "px; left:" + this.position.y + ";"
            }
        },
        mounted() {
            console.log(this);
        }
    }
</script>

<style scoped>
    .point {
        position: absolute;
        background-color: #42b983;
        border-radius: 1em;
        width: 1em;
        height: 1em;
    }
    .point-active {
        background-color: aqua;
    }
    .point-current {
        background-color: azure;
    }

</style>