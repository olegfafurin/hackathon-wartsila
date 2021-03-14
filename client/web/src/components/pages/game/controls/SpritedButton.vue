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
                return "@/assets/" + this.btnName + ".png";
            }
        }
    }
</script>

<style scoped>
    .custom-button {
        width: 100%;
        height: 100%;
        border: solid black 2px;
    }

    .empty-button {
        opacity: 0;
    }
</style>