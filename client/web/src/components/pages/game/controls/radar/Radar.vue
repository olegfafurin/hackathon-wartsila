<template>
    <div class="radar-container" :style="compStyle">
        <img src="@/assets/radar.png" alt="Фон радара" :style="compStyle"/>
        <Point v-for='p in points'
               :key='p.id'
               :current="p.current"
               :active="p.active"
               :position="p.position">
        </Point>
        <div v-for="p in map.pathPoints" :key="p.x *100000 + p.y" class="path-point"
        :style="ppSize"/>
    </div>
</template>

<script>
    import Point from "@/components/pages/game/controls/radar/Point";

    export default {
        name: "Radar",
        components: {Point},
        props: {
            angle_speed: Number,
            range: Number,
            map: {
                pathPoints : [],
                vertexes: [],
                logic:{
                    current: Number,
                    N: Number,
                    W: Number,
                    S: Number,
                    E: Number,
                },
                items: Object,
            },
            root: Number,
            size: Number, //radar size in px
        },
        computed: {
            points() {
                return [
                    {
                        id: 1,
                        current: true,
                        active: true,
                        position: {
                            x: 10,
                            y: 10,
                        }
                    },
                    {
                        id: 2,
                        current: false,
                        active: true,
                        position: {
                            x: 20,
                            y: 20,
                        }
                    },
                    {
                        id: 3,
                        current: false,
                        active: false,
                        position: {
                            x: 40,
                            y: 40,
                        }
                    }
                ]
            },
            compStyle() {
                return "height:" + this.size + "px; width:" +this.size + "px;"
            },
            ppSize(){
                const range = 60;
                return  "height:" + this.size/range + "px; width:" +this.size/range + "px;"
            }
        }
    }
</script>

<style scoped>
.radar-container{
    position: relative;
}
</style>