<template>
    <div class="radar-container" :style="compStyle">
        <img src="http://ferrisgame.ru:8080/assets/images/radar.png" alt="Фон радара" :style="compStyle"
             class="radar-sprite"/>
        <img v-if="!inGame" src="http://ferrisgame.ru:8080/assets/images/radar-border.png" alt="Рамка радара"
             :style="compStyle"
             class="radar-sprite"/>
        <div class="connect-container">
            <Connect v-if="!inGame" :on-connect="onConnect"/>
        </div>
        <div v-for="p in (inGame ? map.pathPoints : [])" :key="p.x *100000 + p.y" class="path-point"
             :style="addShiftToStyle(p, ppSize, gridSize/smallifier) + (p.active ? 'background: radial-gradient(lightgreen 0%, #lightgreen 30%, #FFFFFF00 60%);' : '')">
            <img src="http://ferrisgame.ru:8080/assets/images/point.png" alt="точка" style="width:100%; height:100%"/>
        </div>
        <Point v-for='p in (inGame ? points: [])'
               :key='p.id'
               :current="p.current"
               :active="p.active"
               :position="p.position"
               :size="gridSize"
               :enemy="p.enemy">
        </Point>
        <img v-if="inGame" src='http://ferrisgame.ru:8080/assets/images/arrow.png'
             alt="игрок"
             :style="addShiftToStyle({x:map.logic.current.x, y:map.logic.current.y},getSizeStyle(2*gridSize) + getRotation,2*gridSize)"
             class="player-icon"/>
        <div v-if="inGame" class="radar-shadow"/>
        <img v-if="inGame" src="http://ferrisgame.ru:8080/assets/images/radar-border.png" alt="Рамка радара"
             :style="compStyle"
             class="radar-sprite high"/>

    </div>
</template>

<script>
    import Point from "@/components/pages/game/radar/Point";
    import Connect from "@/components/pages/auth/Connect";

    export default {
        name: "Radar",
        components: {Connect, Point},
        props: {
            onConnect: Function,
            inGame: Boolean,
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
            playerData: Object
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
            getRotation() {
                console.log(this.map.logic.dir);
                if (this.map.logic.dir === "N")
                    return "transform: rotate(0deg);";
                else if (this.map.logic.dir === "E")
                    return "transform: rotate(90deg);";
                else if (this.map.logic.dir === "S")
                    return "transform: rotate(180deg);";
                return "transform: rotate(-90deg);";

            },
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
                    let hasEnemy = false;
                    if (this.playerData) {
                        for (let v in this.playerData.enemies) {
                            if (p.id === this.playerData.enemies[v].id) {
                                hasEnemy = true;
                                break;
                            }
                        }
                    }

                    return {
                        current: p.id === this.map.logic.current.id,
                        active: false,
                        position: this.calcPosition(p),
                        enemy: hasEnemy,
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
        -webkit-transition: all 0.3s ease;;
        -moz-transition: all 0.3s ease;;
        -o-transition: all 0.3s ease;;
        transition: all 0.3s ease;
        overflow: hidden;
    }

    .path-point {
        position: absolute;
        -webkit-transition: all 0.3s ease;;
        -moz-transition: all 0.3s ease;;
        -o-transition: all 0.3s ease;;
        transition: all 0.3s ease;
    }

    .player-icon {
        position: absolute;
        -webkit-transition: all 0.3s ease;
        -moz-transition: all 0.3s ease;
        -o-transition: all 0.3s ease;
        transition: all 0.3s ease;
    }

    .radar-sprite {
        position: absolute;
        top: 0;
        left: 0;
        z-index: -300;
    }

    .radar-shadow {
        position: absolute;
        top: 0;
        left: 0;
        z-index: 10;
        width: 100%;
        height: 100%;
        background: radial-gradient(#00000000 0%, #00000000 40%, #2c3e50 80%, #2c3e50 100%);
    }

    .high {
        z-index: 300;
    }

    .connect-container {
        margin: 20%;
    }
</style>
