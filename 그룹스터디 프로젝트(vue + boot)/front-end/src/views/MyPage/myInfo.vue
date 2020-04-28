<template>
  <div>
       <div class="slib">
            <div class="slib_info">
                <div class="tit">
                  <img src='../../assets/css/images/adult.png'>
                </div>
                <div class="titup">
                  <table class="table1" style="margin-left: 55px">
                    <colgroup>
                    <col style="width: 100px">
                    <col style="width: 70px">
                    <col style="width: 240px">
                    </colgroup>
                    <thead>
                      <tr>
                        <th style="text-align: left;">아이디</th>
                        <th>나이대</th>
                        <th>이메일</th>
                        <th></th>
                        <th></th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td style="text-align: left;">{{myinfo.memberId}}</td>
                        <td>{{myinfo.age}}</td>
                        <td>{{myinfo.email}}</td>
                        <td><i class="el-icon-paperclip" style="color: #ff5151"></i> </td>
                        <td>
                          <el-button type="text" @click="edit" style="color: #ff5151; font-size: 16px">수정</el-button>
                        </td>
                      </tr>                   
                    </tbody>
                  </table>

                  <table class="table1" style="float: right;margin-right: 78px" v-if="table">
                    <tbody>
                      <tr>
                        <td style="width: 70px"> 이메일</td>
                        <td>
                          <el-input placeholder="올바른 이메일 형식을 입력해 주세요"  
                          style="height: 31px; width: 242px;"
                          v-model="eamil"></el-input>
                        </td>
                        <td style="width: 50px">나이</td>
                        <td style="width: 120px">
                            <el-select placeholder=" 선택" 
                            style="height: 31px; width: 100px;"
                            v-model="age" >
                            <el-option label="10대" value="10대"></el-option>
                            <el-option label="20-24" value="20-24"></el-option>
                            <el-option label="25-29" value="25-29"></el-option>
                            <el-option label="30-34" value="30-34"></el-option>
                            <el-option label="40대" value="40대"></el-option>
                            </el-select>
                        </td>
                        <td><i class="el-icon-s-tools" style="color: #d77f4a"></i> </td>
                        <td>
                          <el-button type="text" plain @click="editInfo" style="color: #d77f4a; font-size: 16px">수정하기</el-button>
                        </td>
                      </tr>                 
                    </tbody>
                  </table>
                  
                </div>
            </div>
        </div>
        <div class="slib">
            <div class="slib_info">
               <div class="tit">
                  <img src='../../assets/css/images/address.png'>
               </div>
               <div class="titup">
                  <table class="table1" style="margin-left: 55px">
                     <colgroup>
                    <col style="width: 270px">
                    <col style="width: 30px">
                    <col style="width: 50px">
                    </colgroup>
                      <thead>
                        <tr>
                          <th style="text-align: center;">주소</th>
                          <th></th>
                          <th></th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td colspan="3">{{myinfo.address}}</td>
                          <td><i class="el-icon-paperclip" style="color: #ff5151"></i> </td>
                          <td>
                            <el-button type="text" @click="editAddr" style="color: #ff5151; font-size: 16px">수정</el-button>
                          </td>
                        </tr>                   
                      </tbody>
                    </table>
                  <table class="table1" v-if="addr">
                      <tbody>
                        <tr>
                             <el-button type="info" plain style="width: 100px; padding: 11px;" @click="execDaumPostcode">우편번호 찾기</el-button>
                        </tr>
                        <tr>
                          <td>
                            <el-input  placeholder="주소" readonly="readonly" v-model="address"></el-input>
                          </td>
                          <td>
                            <el-input  placeholder="상세주소" style="width: 300px" v-model="extraAddress"></el-input>
                          </td>
                          <td style="width: 14px"></td>
                          <td><i class="el-icon-s-tools" style="color: #d77f4a"></i> </td>
                            <td>
                            <el-button type="text" @click="updateAdd" style="color: #d77f4a; font-size: 16px">수정하기</el-button>
                          </td>
                        </tr>                   
                      </tbody>
                    </table>
               </div>
            </div>
       </div>
       <div class="slib">
            <div class="slib_info">
               <div class="tit">
                 <img src='../../assets/css/images/lock.png'>
               </div>
               <div class="titup">
               <div v-if="passup" style="padding:77px 64px; width: 600px">
                    <p style="color:#908d8d"> 비밀번호 변경</p><br>
                    <ul>
                      <li style="float: left; margin-right: 12px;">
                    <el-input v-model="passCheck" placeholder="현재 비밀번호를 입력해주세요" style="width: 264px"></el-input>
                      </li>
                      <li>
                    <el-button type="text" @click="passedit" style="color: #ff5151; font-size: 16px">비밀번호 확인</el-button>
                      </li>
                    </ul>
               </div>
                 <div v-else style="padding-left: 33px;">
                  <el-form :model="ruleForm" :rules="rules" label-position="left"  ref="ruleForm" class="demo-ruleForm" style="padding-top: 50px">
                    <el-form-item label="변경할 비밀번호 입력" prop="pass">
                      <el-input type="password" v-model="ruleForm.pass" placeholder="비밀번호를 입력해 주세요" autocomplete="off" style="width: 100%;"></el-input>
                    </el-form-item>
                    <el-form-item label="변경할 비밀번호 확인" prop="checkPass">
                    <el-input type="password" v-model="ruleForm.checkPass" placeholder="비밀번호를 확인해 주세요"  autocomplete="off" style="width: 100%;"></el-input>
                    </el-form-item> 
                    <el-form-item style="margin-left: 20px">
                        <el-button type="text" @click="submitForm('ruleForm')">변경하기</el-button>
                    </el-form-item>
                  </el-form>
                 </div>
               </div>
            </div>
        </div>
        <div class="slib">
          <div class="slib_info1">
                 <el-button round style="float: right; margin-left: 24px;" @click="userOut">회원탈퇴</el-button>
          </div>
        </div>
  </div>
</template>

<script>
import logincss from '@/assets/css/member/myinfo.css'

export default {
    props:["myinfo", "memSeq"], 
    data(){
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('비밀번호를 입력해주세요'));
        } else {
          if (this.ruleForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('비밀번호 확인을 입력해주세요'));
        } else if (value !== this.ruleForm.pass) {
          callback(new Error('비밀번호가 일치하지 않습니다'));
        } else {
          callback();
        }
      };
      return{
        table: false,
        addr: false,
        passup: true,
        eamil:'',
        age:'',
        address: '',
        extraAddress: '',
        postcode: '',
        passCheck: '',
        ruleForm: {
            pass: '',
            checkPass: '',
        },
        rules: {
            pass: [
                { required: true, validator: validatePass, trigger: 'blur' }
            ],
            checkPass: [
                { required: true, validator: validatePass2, trigger: 'blur' }
            ],
        }
      }
    },
    methods:{
        execDaumPostcode() {
          new daum.Postcode({
              onComplete: (data) => {
              if (data.userSelectedType === 'R') {
                this.address = data.roadAddress;
              } else {
                this.address = data.jibunAddress;
              }
              if (data.userSelectedType === 'R') {
                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                this.extraAddress += data.bname;
                }
                if (data.buildingName !== '' && data.apartment === 'Y') {
                this.extraAddress +=
                  this.extraAddress !== ''
                  ? `, ${data.buildingName}`
                  : data.buildingName;
                }
                if (this.extraAddress !== '') {
                this.extraAddress = ` (${this.extraAddress})`;
                }
              } else {
                this.extraAddress = '';
              }
            },
          }).open();
      },
      edit(){
        this.table= true;
      },
      editInfo(){
        if(this.eamil == "" ||this.age ==""){
            alert('이메일이나나 나이를 입력해주세요')
        }else{
          var params = new URLSearchParams();
          params.append('memberSeq', this.memSeq)
          params.append('email', this.eamil)
          params.append('age', this.age)
          axios.post('http://localhost:9000/updateInfo', params)
          .then(res => {
              if(res.data == true){
                this.table = false;
                alert('수정이 완료되었습니다.')
                this.$emit('emailUpdate',this.memSeq)
              }
          }) 
        }
      },
      editAddr(){
        this.addr = true
      },
      updateAdd(){
        if(this.address =='' || this.extraAddress ==''){
           alert('주소를 입력해주세요')
        }else{
        var params = new URLSearchParams();
        params.append('memberSeq', this.memSeq)
        var realAddr = this.address + this.extraAddress
        params.append('address', realAddr)     
         axios.post('http://localhost:9000/updateAddr', params)
          .then(res => {
              if(res.data == true){
                alert('수정이 완료되었습니다.')
                this.addr = false;
                this.$emit('addrUpdate',this.memSeq)
              }
          }) 
        }
      },
      passedit(){
        if(this.passCheck ==""){
          alert('비밀번호를 입력해주세요')
        }else{
           var params = new URLSearchParams();
           params.append('memberSeq', this.memSeq)
           params.append('pwd', this.passCheck)
      
           axios.post('http://localhost:9000/checkPass', params)
            .then(res => {
                if(res.data == true){
                  this.passCheck = "";
                  this.passup = false;
                }else{
                  alert('비밀번호가 맞지 않습니다.')
                }
            }) 
         }
      },
      userOut(){
        this.$prompt('아이디를 입력해주세요', '회원 탈퇴', {
          confirmButtonText: '탈퇴하기',
          cancelButtonText: '취소',
       
        }).then(({ value }) => {
          var params = new URLSearchParams();
          params.append('memberSeq', this.memSeq)
          params.append('memberId', value)
          console.log(params)
          axios.post('http://localhost:9000/delUser', params).then(
              res => {
                if(res.data == true){
                  this.$message({
                      type: 'success',
                      message: '회원탈퇴가 완료되었습니다.'
                  });
                  sessionStorage.removeItem("loginUser")
                  this.$store.commit( 'logout' ) 
                  this.$router.push ({path:'/'})
                }else{
                  this.$message({
                      type: 'error',
                      message: '해당하는 아이디가 없습니다.'
                  });
                }
              }) 
         
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '취소되었습니다.'
          });       
        });
    
      },
      submitForm(formName) {
            this.$refs[formName].validate((valid) => {
            if (valid) {
                    var params = new URLSearchParams();
                    params.append('memberSeq', this.memSeq)
                    params.append('pwd', this.ruleForm.checkPass)
                    console.log(params)
                    axios.post('http://localhost:9000/updatePass', params).then(
                        res => {
                            if(res.data == true){
                                alert('비밀번호가 변경되었습니다.')
                                this.passup = true;
                                this.$emit('passUpdate', this.memSeq)
                            }
                        }) 
            } else {
                console.log('error submit!!');
                return false;
            }
            });
        },
    }
}
</script>
