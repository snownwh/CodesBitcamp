<template>
      <div class="boardManage">
        <div class="leftBar">
            <ul>
                <li> <h2> {{title}} </h2> </li>
                <li><router-link class="active" to="/board1"> 회원관리1 </router-link></li>
                <li><router-link  to="/board2">  회원관리2 </router-link></li>
                <li><router-link  to="/board3">  회원관리3 </router-link></li>
                <li><router-link  to="/board4">  회원관리4 </router-link></li>
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
            title:"게시판 관리"
        }
    },
    methods:{
        
    }

}
</script>

<style>

</style>