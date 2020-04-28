<template>
  <div class="login">
    <h2>Log in to Trello</h2>
    <form @submit.prevent="onSubmit">
      <div>
        <label for="email">Email</label>
        <input class="form-control" type="text" name="email" 
          v-model="email" autofocus placeholder="e.g., test@test.com" />
      </div>
      <div>
        <label for="password">Passwrod</label>
        <input class="form-control" type="password" 
          v-model="password" placeholder="123123" />
      </div>
      <button  class="btn" :class="{'btn-success': !invalidForm}" type="submit" :disabled="invalidForm">Log In</button>
    </form>
    <p class="error" v-if="error">{{error}}</p>
  </div>
</template>

<script>
import {auth} from '../api/api_index.js'

export default {
  data() {
    return {
      email: '',
      password: '',
      error: '',
      rPath:''
    }
  },
  computed: {
    invalidForm() {
      return !this.email || !this.password
    }
  },
  //rpath로 리다이렉트
  created(){
    this.rPath = this.$route.query.rPath || '/'
  },
  methods: {
    onSubmit() {
      console.log(this.email, this.password)
      var params = new URLSearchParams();
      params.append('memberId', this.email)
      params.append('pwd', this.password)

      axios.post('http://localhost:9000/adminLoginCheck',params).then(
        res => {
        console.log(res.data)
        //this.$router.push(this.rPath)//rPath로 redirect
          if(res.data.memberId == undefined){
            alert("id나 password가 틀렸습니다.");
            this.$store.state.isLogin = true;
            return;
          }

          sessionStorage.setItem("loginUser", JSON.stringify(res.data.memberId)); //String
          //alert(res.data.memberId)
          var loginData = sessionStorage.getItem("loginUser"); //세션가져오기
          console.log('세션가져오기' + loginData)
          var login = JSON.parse(loginData); //JSON
          this.$store.commit('loginSuccess', login)
          console.log('로그인성공');

          console.log("출력 : " + this.$store.state.loginUser);
          console.log("출력 : " + this.$store.state.isLogin);

          this.$router.push ({path:'/'})
          this.$emit('close')


      })
      .catch(err =>{
        console.log(err)
      })
    }
  }
}
</script>

<style>
.login {
  width: 400px;
  margin: 0 auto; 
}
.error {
  color: #f00;
}

</style>
