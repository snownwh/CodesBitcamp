<template>
  <div class="acceptManage">
        <div class="leftBar">
            <ul>
                <li> <h2> {{title}} </h2> </li>
                <li><router-link class="active" to="/acceptlist1"> 승인관리1 </router-link></li>
                <li><router-link  to="/acceptlist2">  승인관리2 </router-link></li>
                <li><router-link  to="/acceptlist3">  승인관리3 </router-link></li>
                <li><router-link  to="/acceptlist4">  승인관리4 </router-link></li>
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
            title:"승인관리"

        }
    },
    methods:{
        
    }

}
</script>

<style>

</style>