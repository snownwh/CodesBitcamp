<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">

          <div class="modal-header">
            <slot name="header">
   
            </slot>
          </div>

          <div class="modal-body">
            <slot name="body">
                    <div class="login_page_wrap">
            <div class="login_message">
              <p> 회원이 아니면, 지금 <span><strong>
                <button style="    vertical-align: baseline; font-size: 20px; padding-left: 5px; color: blue;" @click="regi">회원가입
               </button></strong></span>을 해주세요.
               </p>        
            </div>
            <div class="login_input_wrap">         
                <!-- input box -->
                <div class="setting">
                    <input type="checkbox" id="id_save"  v-model="idSave" @click="saveId">
                    <label for="id_save">아이디 저장</label>
                </div>
                <div class="login-form">
                    <div class="id-input-box focus">
                        <input type="text" v-model="id" class="txt_tool" placeholder="아이디">
                    </div>
                    <div class="pw-input-box">
                        <input type="password" v-model="pwd" class="txt_tool" placeholder="비밀번호" maxlength="32">
                    </div>
                    <button @click="login" class="btn-login">로그인</button>
                </div>
                <p class="signup-forgotten">
                 
                   <span></span>
                   <button class="forgotten" @click="idSearch" style="font-weight: 5px">아이디/비밀번호 찾기</button>
                </p>
                 </div>
             </div>
            </slot>
          </div>

          <div class="modal-footer">
            <slot name="footer">
              <button class="modal-default-button" @click="exit">
                      CLOSE
              </button>
            </slot>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>
<script>
import logincss from '@/assets/css/member/login.css'
export default {
    data(){
      return{
          id: null,
          pwd: null,
          idSave: ''
      }
    },
    mounted(){
        var userId = this.$cookie.get('userId')
        if(userId != null){
          this.id = userId
          this.idSave = true
        }
    },
    methods:{
        exit(){
          this.$router.push({path:'/'});
          this.$emit('close')
        },
        regi(){
          this.$router.push({name:'join'});
          this.$emit('close')
        },
        login(){
           var params = new URLSearchParams();
           params.append('memberId', this.id)
           params.append('pwd', this.pwd)

          this.id == null ? alert('아이디를 입력해주세요'):
          this.pwd == null ? alert('비밀번호를 입력해 주세요'):
          axios.post('http://localhost:9000/getOneMember', params).then(
    				res => {
              if(res.data.memberId == undefined){
                alert("id나 password가 틀렸습니다.");
                this.$store.state.isLogin = true;
                return;
              }
                sessionStorage.setItem("loginUser", JSON.stringify(res.data)); //String
                //alert(res.data.memberId)
                var loginData = sessionStorage.getItem("loginUser"); //세션가져오기
                //alert('세션가져오기' + loginData)
                var login = JSON.parse(loginData); //JSON
                this.$store.commit('loginSuccess', login )
                alert('로그인성공')
                // this.$router.push ({path: '/'}) 
                this.$emit('close')   
            })
        },
        saveId(){
          if(this.idSave == false){
                if(this.id == null){
                  alert('아이디를 입력해주세요')
                  this.idSave = false
                }else{
                  this.$cookie.set('userId', this.id, 365);
                }
          }else if(this.idSave == ''){
             if(this.id == null){
                  alert('아이디를 입력해주세요')
                  this.idSave = ''
                }else{
                  this.$cookie.set('userId', this.id, 365);
                }
          
          }else {
            this.$cookie.delete('userId');
          }
        },
        idSearch(){

        }
    }
}

</script>
<style>

</style>