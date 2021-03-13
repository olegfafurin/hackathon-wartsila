<template>
    <div>
        <Point v-for='p in points'
               :key='p.id'
               :current="p.current"
               :active="p.active"
               :position="p.position">
        </Point>
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
            map: Object,
            root: Number,
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
            }
        }
    }
</script>

<style scoped>

</style>