<template>
    <div class="acceptManage">
        <div class="leftBar">
            <ul>
                <li> <h2> {{title}} </h2> </li>
                <li><router-link class="active" to="/recyclebin1"> 휴지통내역1 </router-link></li>
                <li><router-link  to="/recyclebin2">  휴지통내역2 </router-link></li>
                <li><router-link  to="/recyclebin3">  휴지통내역3 </router-link></li>
                <li><router-link  to="/recyclebin4">  휴지통내역4 </router-link></li>
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
            title:"삭제내역"
        }
    },
    methods:{
        
    }

}
</script>

<style>

</style>