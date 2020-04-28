<template>
    <div class="logoutpage">
        <h1> log out now </h1>
        3초후 메인페이지로 이동합니다
    </div>
</template>

<script>
    import {mapState, mapMutations} from 'vuex'
export default {
    

    created(){
        var getSession = sessionStorage.getItem("loginUser")
        var login=JSON.parse(getSession);
        this.$store.commit('logout',login);
        console.log("출력 : " + this.$store.state.loginUser);
        console.log("출력 : " + this.$store.state.isLogin);
        this.goToNextPage()
    },
    mounted() {
        
    },
    methods:{
    // ...mapMutations(['logout'])
    goToNextPage(){
        console.log("logoutconsole");
        setTimeout(()=> this.isHidden=false,3000);
        this.$router.push({path:'/'})
    }
  }
}
</script>

<style>

</style>