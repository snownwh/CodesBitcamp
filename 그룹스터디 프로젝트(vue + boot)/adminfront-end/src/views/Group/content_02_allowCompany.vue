<template>
    <div class="allowcompany">
        <h1> allow company </h1>


        <h3> 1111 {{request}} 1111 </h3>

        <div v-if="showallowcompany">
            allow group show 
        </div>
        <div v-else-if="showAllowCreateGroup">
            allow group show 
        </div>
        <div v-else>
            another area 
        </div>
    </div>


</template>

<script>
export default {
    data(){
        return{
            request:'undefined'
        }
    },
    watch:{
        '$route':{
            handler:'fetchData',
            immediate:true      //create()의 중복역할. $route()의 즉시실행역할을 한다.
        }
    },
    methods:{
        fetchData(){
            setTimeout(() =>{
                if(this.$route.params.request=="allowcompany" || this.$route.params.request=="allowCreateGroup"){
                    this.request = this.$route.params.request
                }else{
                    console.log(this.$router.push('../unknownPage'))
                }
            })
        }

    }


}
</script>

<style>

</style>