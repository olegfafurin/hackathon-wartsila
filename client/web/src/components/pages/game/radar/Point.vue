<template>
    <div :class="className" :style="stylePosition">
        <img src="http://ferrisgame.ru:8080/assets/images/point.png" alt="точка" style="width:100%; height:100%"/>
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
            size: Number,
            description: Object,
        },
        computed: {
            className() {
                return "point" + (this.active ? " point-active" : "") + (this.current ? " point-current" : "")
            },
            stylePosition() {
                const res = "left:" + (this.position.x - this.size / 2) + "px; top:" + (this.position.y - this.size / 2) + "px; " +
                    "width: " + this.size + "px; " +
                    "height: " + this.size + "px; " +
                    "border-radius: " + this.size + "px; ";
                return res;
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
        z-index: -200;
        -webkit-transition: all 0.3s ease;
        -moz-transition: all 0.3s ease;
        -o-transition: all 0.3s ease;
        transition: all 0.3s ease;
    }

    .point-active {
        background-color: aqua;
    }


</style>