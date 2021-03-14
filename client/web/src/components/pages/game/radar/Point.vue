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
            size: Number,
            description: Object,
        },
        computed: {
            className() {
                return "point" + (this.active ? " point-active" : "") + (this.current ? " point-current" : "")
            },
            stylePosition() {
                console.log(this.size);
                console.log(this.position.x);
                const res = "left:" + (this.position.x - this.size/2) + "vh; top:" + (this.position.y - this.size / 2) + "vh; " +
                    "width: " + this.size + "vh; " +
                    "height: " + this.size + "vh; " +
                    (!this.current ? ("border-radius: " + this.size + "vh; ") : "");
                console.log(res);
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

    }

    .point-active {
        background-color: aqua;
    }

    .point-current {
        background: none;
    }



</style>