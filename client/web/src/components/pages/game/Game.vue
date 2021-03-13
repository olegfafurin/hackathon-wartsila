<template>
    <div class="hello">
        <h1>Wartsila game</h1>
        <Radar :size="300" :map="radarMap.map"></Radar>
    </div>
</template>

<script>
    import Radar from "@/components/pages/game/controls/radar/Radar";

    export default {
        name: 'Game',
        components: {Radar},
        props: {
            msg: String
        },
        data() {
            return {
                radarMap: {
                    angle_speed: 1,
                    range: 1,
                    map: {
                        pathPoints: [
                            {
                                x: 0,
                                y: 0,
                            }, {
                                x: 0,
                                y: 1,
                            }, {
                                x: 0,
                                y: 2,
                            }, {
                                x: 0,
                                y: 3,
                            }, {
                                x: 0,
                                y: 4,
                            }, {
                                x: 0,
                                y: 5,
                            }, {
                                x: 0,
                                y: 6,
                            },
                        ],
                        vertexes: [
                            {
                                id: 1,
                                x: 0,
                                y: 0,
                            },
                            {
                                id: 2,
                                x: 0,
                                y: 6,
                            },

                        ],
                        logic: {
                            current: {
                                id: 1,
                                x: 0,
                                y: 0,
                            },
                            dir: "N"
                        },
                        items: {},
                    },
                    root: Number,
                    size: Number, //radar size in px
                }
            }
        }

    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    h3 {
        margin: 40px 0 0;
    }

    ul {
        list-style-type: none;
        padding: 0;
    }

    li {
        display: inline-block;
        margin: 0 10px;
    }

    a {
        color: #42b983;
    }
</style>
