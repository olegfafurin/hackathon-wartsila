<template>
    <div class="container">
        <Radar :size="radarSize" :map="radarData.map" :in-game="false"></Radar>
        <ControlPanel :size="controlSize"/>
    </div>
</template>

<script>
    import Radar from "@/components/pages/game/radar/Radar";
    import ControlPanel from "@/components/pages/game/controls/ControlPanel";
    import api from "@/axios/api";
    import $axios from "@/axios/instance";

    export default {
        name: 'Game',
        components: {ControlPanel, Radar},
        props: {
            msg: String
        },
        data() {
            return {
                radarData: {
                    angle_speed: 1,
                    range: 1,
                    map: {
                        pathPoints: [
                            {
                                x: 1,
                                y: 6,
                                active:true,
                            },
                            {
                                x: 0,
                                y: 7,
                                active:true,
                            },
                            {
                                x: 0,
                                y: 8,
                                active:true,
                            },
                            {
                                x: 1,
                                y: 9,
                                active:true,
                            },
                            {
                                x: 2,
                                y: 9,
                                active:true,
                            },
                            {
                                x: 3,
                                y: 9,
                                active:true,
                            },
                            {
                                x: 4,
                                y: 9,
                                active:true,
                            },
                            {
                                x: 5,
                                y: 9,
                                active:true,
                            },
                            {
                                x: 6,
                                y: 9,
                                active:true,
                            },
                            {
                                x: 7,
                                y: 9,
                                active:true,
                            },
                            {
                                x: 8,
                                y: 9,
                                active:true,
                            },
                            {
                                x: 9,
                                y: 8,
                                active:true,
                            },
                            {
                                x: 10,
                                y: 7,
                                active:true,
                            },
                            {
                                x: 10,
                                y: 6,
                                active:true,
                            },
                            {
                                x: 10,
                                y: 5,
                                active:true,
                            },
                            {
                                x: 10,
                                y: 4,
                                active:true,
                            },
                            {
                                x: 9,
                                y: 3,
                                active:true,
                            },
                            {
                                x: 2,
                                y: 7,
                                active:true,
                            },
                            {
                                x: 3,
                                y: 8,
                                active:true,
                            },
                            {
                                x: 4,
                                y: 8,
                                active:true,
                            },
                            {
                                x: 5,
                                y: 7,
                                active:true,
                            },
                            {
                                x: 3,
                                y: 6,
                                active:true,
                            },
                            {
                                x: 4,
                                y: 6,
                                active:true,
                            },
                            {
                                x: 2,
                                y: 5,
                                active:true,
                            },
                            {
                                x: 2,
                                y: 4,
                                active:true,
                            },
                            {
                                x: 5,
                                y: 5
                            },
                            {
                                x: 5,
                                y: 4
                            },
                            {
                                x: 3,
                                y: 3
                            },
                            {
                                x: 4,
                                y: 3
                            },
                            {
                                x: 2,
                                y: 2
                            },
                            {
                                x: 3,
                                y: 1
                            },
                            {
                                x: 4,
                                y: 1
                            },
                            {
                                x: 5,
                                y: 2
                            },
                            {
                                x: 1,
                                y: 3
                            },
                            {
                                x: 0,
                                y: 2
                            },
                            {
                                x: 0,
                                y: 1
                            },
                            {
                                x: 1,
                                y: 0
                            },
                            {
                                x: 2,
                                y: 0
                            },
                            {
                                x: 3,
                                y: 0
                            },
                            {
                                x: 4,
                                y: 0
                            },
                            {
                                x: 5,
                                y: 0
                            },
                            {
                                x: 6,
                                y: 0
                            },
                            {
                                x: 7,
                                y: 0
                            },
                            {
                                x: 8,
                                y: 1
                            },
                            {
                                x: 8,
                                y: 2
                            },
                            {
                                x: 6,
                                y: 3
                            },
                            {
                                x: 7,
                                y: 3
                            },
                            {
                                x: 6,
                                y: 6
                            },
                            {
                                x: 7,
                                y: 5
                            },
                            {
                                x: 8,
                                y: 4
                            }
                        ],
                        vertexes: [

                            {
                                id: 0,
                                x: 2,
                                y: 6,
                                items: [],
                                edges: {
                                    WEST: 0,
                                    NORTH: 1,
                                    EAST: 2,
                                    SOUTH: 3
                                }
                            },
                            {
                                id: 1,
                                x: 5,
                                y: 6,
                                items: [],
                                edges: {
                                    SOUTH: 4,
                                    EAST: 9
                                }
                            },
                            {
                                id: 2,
                                x: 8,
                                y: 3,
                                items: [],
                                edges: {}
                            },
                            {
                                id: 3,
                                x: 5,
                                y: 3,
                                items: [],
                                edges: {
                                    SOUTH: 6,
                                    EAST: 8
                                }
                            },
                            {
                                id: 4,
                                x: 2,
                                y: 3,
                                items: [],
                                edges: {
                                    EAST: 5,
                                    WEST: 7
                                }
                            }

                        ],
                        logic: {
                            current: {
                                id: 0,
                                x: 2,
                                y: 6,
                            },
                            dir: "N"
                        },
                        items: {},
                    },
                    root: Number,
                    size: Number, //radar size in px
                },
                radarSize: Math.min(document.documentElement.clientWidth, document.documentElement.clientHeight),
                controlSize: {
                    w: document.documentElement.clientWidth === Math.min(document.documentElement.clientWidth, document.documentElement.clientHeight) ? document.documentElement.clientWidth : (document.documentElement.clientWidth - Math.min(document.documentElement.clientWidth, document.documentElement.clientHeight)),
                    h: document.documentElement.clientHeight === Math.min(document.documentElement.clientWidth, document.documentElement.clientHeight) ? document.documentElement.clientHeight : (document.documentElement.clientHeight - Math.min(document.documentElement.clientWidth, document.documentElement.clientHeight)),
                },
            }
        },
        created() {
            window.addEventListener("resize", this.myEventHandler);
            api($axios).createRoom("random").then(r => {
                console.log(r.data);
                console.log('комната создалась');
                setInterval(this.getField, 10000);
            }).catch(e => {
                console.log(e);
                console.log('комната не создалась')
            })

        },
        destroyed() {
            window.removeEventListener("resize", this.myEventHandler);
        },
        methods: {
            getField() {
                api($axios).getField().then(r => {
                    console.log(r.data);
                    return r.data;
                }).catch(e => {
                    console.log(e)
                })
            },
            myEventHandler(e) {
                console.log(e);
                this.radarSize = this.getRadarSize();
                this.controlSize = this.getControlSize();
            },
            getRadarSize() {
                return Math.min(document.documentElement.clientWidth, document.documentElement.clientHeight);
            },
            getControlSize() {
                const dw = document.documentElement.clientWidth;
                const dh = document.documentElement.clientHeight;
                return {
                    w: dw === this.radarSize ? dw : (dw - this.getRadarSize()),
                    h: dh === this.radarSize ? dh : (dh - this.getRadarSize()),
                }
            }
        }

    }
</script>

<!-- Add scoped attribute to limit CSS to this component only -->
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

    .container {
        display: flex;
        flex-direction: row;
    }

    @media (max-width: 480px) {
        .container {
            flex-direction: column;
        }
    }
</style>
