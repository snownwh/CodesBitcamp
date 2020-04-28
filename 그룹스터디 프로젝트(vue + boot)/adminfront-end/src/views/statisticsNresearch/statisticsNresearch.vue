<template>
      <div class="statisticsNresearch">
        <div class="leftBar">
            <ul>
                <li> <h2> {{title}} </h2> </li>
                <li><router-link class="active" to="/statisticsNresearch1"> 통계및조사1 </router-link></li>
                <li><router-link  to="/statisticsNresearch2">  통계및조사3 </router-link></li>
                <li><router-link  to="/statisticsNresearch3">  통계및조사2 </router-link></li>
                <li><router-link  to="/statisticsNresearch4">  통계및조사4 </router-link></li>
            </ul>
        </div> 
        <div  class="margin-L_25 contentPadding" >
            <router-view ></router-view>
        </div>
    </div>
</template>

<script>
export default {
    data(){
        return{
            title:"통계 및 조사"
        }
    },
    methods:{
        
    }

}
</script>

<style>

</style>