<template>
    <div>
        <div class="header_top">
            <div class="inner"> 
            <div v-if="isLogin" style="margin-right: 30px; padding-right: 10px; margin-top: 10px">
                    <span>
                        <button  @click="showModal = true">로그인</button>
                        <Modal v-if="showModal" @close="showModal = false">
                        </Modal>
                    </span> |
                    <span><button @click="$router.push({name:'join'})">회원가입</button></span>
            </div>
            <div v-else style="margin-right: 30px;">
                <span>
                    <!-- <p>{{loginUser.memberId}}님 환영합니다</p>  this.$store.state.show == true -->
                    <button @click="$router.push ({name:'mypage'}) " >마이페이지</button>
                </span> 
                |
                <span><button @click="logout">로그아웃</button></span>
            </div>
             
            <div class="logo">
                <router-link :to="{path: '/'}" exact>
                    <img style="height: 55px; margin-top: -25px;" src='../../assets/css/images/logo.png' alt="팔레트" />
                </router-link>
            </div>
            <div class="searchWrap">
                <form name="search" method="get" action="#">
                    <input type="text" maxlength="20" name="mainSearchWord" placeholder="검색어를 입력해주세요">
                    <el-button icon="el-icon-search" size="small" circle></el-button>
                </form>
            </div>
            </div>
        </div>
    </div>
</template>

<script>
import {mapState, mapMutations} from 'vuex'
import Modal from "@/views/Member/Login.vue";
import 'element-ui/lib/theme-chalk/index.css';
export default {
    name : "headerTop",
        data(){
            return{
                showModal: false,
                auth: this.$store.state.auth,
                //loginUser: this.$store.state.loginUser,
                //isLogin: this.$store.state.isLogin
            }
    },
    components:{
        Modal
    },
    computed:{
    ...mapState(['isLogin', 'loginUser'])
    },
    methods:{
        ...mapMutations(['logout'])
    },    

}
</script>

<style>

</style>