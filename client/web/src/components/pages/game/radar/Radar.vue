<template>
    <div class="radar-container" :style="compStyle">
        <img src="http://localhost:8080/radar.png" alt="Фон радара" :style="compStyle" class="radar-sprite"/>
        <div v-for="p in map.pathPoints" :key="p.x *100000 + p.y" class="path-point"
             :style="addShiftToStyle(p, ppSize, gridSize/smallifier)"/>
        <Point v-for='p in points'
               :key='p.id'
               :current="p.current"
               :active="p.active"
               :position="p.position"
               :size="gridSize">
        </Point>
        <img src='http://localhost:8080/player.png'
             alt="игрок"
             :style="addShiftToStyle({x:map.logic.current.x, y:map.logic.current.y},getSizeStyle(2*gridSize),2*gridSize)"
             class="player-icon"/>
        <img src="http://localhost:8080/radar-border.png" alt="Рамка радара" :style="compStyle" class="radar-sprite"/>

    </div>
</template>

<script>
    import Point from "@/components/pages/game/radar/Point";

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
            addShiftToStyle(pos, base_style, size) {
                const p = this.calcPosition(pos);
                return base_style + " border-radius:2rem; left:" + (p.x - size / 2) + "px; top:" + (p.y - size / 2) + "px;"
            },
            getSizeStyle(s) {
                return "height:" + s + "px; width:" + s + "px; "
            }
        },
        computed: {
            gridSize() {
                return this.size / 20;
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
                return "height:" + this.gridSize / this.smallifier + "px; width:" + this.gridSize / this.smallifier + "px; "
            },
            smallifier() {
                console.log(this.map.pathPoints.length);
                return Math.sqrt(Math.sqrt(this.map.pathPoints.length))
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

    .player-icon {
        position: absolute;
    }

    .radar-sprite {
        position: absolute;
        top: 0;
        left: 0;
    }
</style>
