<template>
    <div class="decor-container" :style="compStyle">
        <img class="img-dec" :src="'http://ferrisgame.ru:8080/assets/images/' + name+ '.png'" :alt="name"/>
    </div>
</template>

<script>
    export default {
        name: "Decor",
        props: {
            name: String,
            x: Number,
            y: Number,
            size: Number,
        },
        computed:{
            compStyle(){
                return "height:" + this.size + "px; width:" + this.size + "px;" +
                        "top:" + this.y + "px; left" +this.x + "px;"
            }
        }
    }
</script>

<style scoped>
    .decor-container{
        position: absolute;
        -webkit-transition: all 0.3s ease;
        -moz-transition: all 0.3s ease;
        -o-transition: all 0.3s ease;
        transition: all 0.3s ease;
    }

    .decor-container:hover{
        transform: rotate(0.5turn);
    }
    .img-dec{
        width: inherit;
        height: inherit;
    }
</style>