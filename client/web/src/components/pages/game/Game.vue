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
                    map: {
                        pathPoints: [
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
                        ],
                        logic: {
                            current: {
                                id: 0,
                                x: 2,
                                y: 6,
                            },
                            dir: "N"
                        },
                    },
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
            api($axios).createRoom(Math.random().toString()).then(r => {
                console.log(r.data);
                console.log('комната создалась');
                setInterval(this.getField, 200);
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
                    console.log(r.data.field);
                    let v = [];
                    for(let e in r.data.field.edges){
                        console.log(e);
                        for(let p in r.data.field.edges[e].path){
                            v.push({
                                x: r.data.field.edges[e].path[p].first,
                                y: -r.data.field.edges[e].path[p].second
                            })
                        }
                    }
                    console.log("vershiny");
                    console.log(v);
                    this.radarData.map = {
                        pathPoints: v,
                        vertexes: r.data.field.vertices.map((p) => {
                            return {
                                ...p,
                                y: -p.y,
                            }
                        }),
                        logic: {
                            current: {
                                ...r.data.currentPoint,
                                y: -r.data.currentPoint.y,
                            },
                            dir: r.data.currentDirection[0]
                        },
                    };
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
