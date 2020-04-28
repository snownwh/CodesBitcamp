<template>
    <div class="group">

        
        <div class="leftBar">
            <ul>
                <li> <h2> {{title}} </h2> </li>
                <li><router-link class="active" to="/group01"> 기업회원 승인 </router-link></li>
                <li><router-link  to="/group02"> 그룹개설 승인 </router-link></li>
                <li><router-link  to="/group03"> 검색2 </router-link></li>
                <li><router-link  to="/group04"> 검색3 </router-link></li>
                
            </ul>
        </div> 
        <div class="margin-L_25" >
            <router-view ></router-view>
        </div>


    </div>
</template>

<script>
export default {
    data(){
        return{
            title:'소모임 관리'

        }
    },
    methods:{
        
    }

}
</script>

<style>

</style>