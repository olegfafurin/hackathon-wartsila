<template>
    <div class="radar-container" :style="compStyle">
        <img src="@/assets/radar.png" alt="Фон радара" :style="compStyle"/>
        <Point v-for='p in points'
               :key='p.id'
               :current="p.current"
               :active="p.active"
               :position="p.position"
               :grid-size="1">
        </Point>
        <div v-for="p in map.pathPoints" :key="p.x *100000 + p.y" class="path-point"
             :style="addShiftToStyle(p, ppSize)"/>
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
                pathPoints: [],
                vertexes: [],
                logic: {
                    current: {
                        id: Number,
                        x: Number,
                        y: Number,
                    },
                    dir: String
                },
                items: Object,
            },
            size: Number, //radar size in px
        },
        methods: {
            calcPosition(pos) {
                return {
                    x: pos.x * this.gridSize + this.shift.x,
                    y: pos.y * this.gridSize + this.shift.y,
                }
            },
            addShiftToStyle(pos, base_style) {
                const p = this.calcPosition(pos);
                console.log(p);
                return base_style + " border-radius:2rem; top:" + p.x + "px; left:" + p.y + "px;"
            }
        },
        computed: {
            gridSize() {
                return 10;
            },
            shift() {
                return {
                    x: this.size / 2 - this.map.logic.current.x * this.gridSize,
                    y: this.size / 2 - this.map.logic.current.y * this.gridSize,
                }
            },
            points() {
                return this.map.vertexes.map((p) => {
                    return {
                        current: p.id === this.map.logic.current.id,
                        active: false,
                        position: this.calcPosition(p)
                    }
                })
            },
            compStyle() {
                return "height:" + this.size + "px; width:" + this.size + "px;"
            },
            ppSize() {
                const range = 60;
                return "height:" + this.size / range + "px; width:" + this.size / range + "px; "
            }
        }
    }
</script>

<style scoped>
    .radar-container {
        position: relative;
    }

    .path-point {
        position: absolute;
        background-color: aquamarine;

    }
</style>