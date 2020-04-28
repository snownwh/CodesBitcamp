<template>
    <div class="create">
        <div class="createContainer">
            <div class="title">그룹 스터디 생성</div>
            <el-form :model="ruleForm" label-position="top" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
                <!-- 아이디 설정 -->
                <el-form-item label="아이디" prop="memberId">
                    <el-input placeholder="아이디를 입력해 주세요" v-model="ruleForm.memberId" style="width: 57%; margin-right: 10px"></el-input>
                     <el-button type="success" @click="idcheck" plain v-model="checkIs">중복확인</el-button>
                </el-form-item>
				<!-- 비밀번호 -->
				 <el-form-item label="비밀번호" prop="pass">
					<el-input type="password" v-model="ruleForm.pass" placeholder="비밀번호를 입력해 주세요" autocomplete="off" style="width: 75%;"></el-input>
				</el-form-item>
				<el-form-item label="비밀번호 확인" prop="checkPass">
					<el-input type="password" v-model="ruleForm.checkPass" placeholder="비밀번호를 확인해 주세요"  autocomplete="off" style="width: 75%;"></el-input>
				</el-form-item> 
                <!-- 이름 -->
                <el-form-item label="이름" prop="memberName">
                    <el-input placeholder="성함을 입력해 주세요" v-model="ruleForm.memberName" style="width: 75%;"></el-input>
                </el-form-item>
				<!--  이메일 -->
				 <el-form-item prop="email" label="Email">
					<el-input v-model="ruleForm.email" placeholder="올바른 이메일 형식을 입력해 주세요"  style="width: 75%;"></el-input>
				 </el-form-item>	 
                <!--  나이대선택 -->
				<el-form-item label="성별/ 연령" prop="age">
					<el-select v-model="ruleForm.age" placeholder="나이대를 선택해주세요" style="margin-right: 20px; width: 50%;" >
						<el-option label="10대" value="10대"></el-option>
						<el-option label="20-24" value="20-24"></el-option>
						<el-option label="25-29" value="25-29"></el-option>
						<el-option label="30-34" value="30-34"></el-option>
						<el-option label="40대" value="40대"></el-option>
					</el-select>
				
					<el-radio v-model="ruleForm.gender" label="1" prop="gender" >남자</el-radio>
  					<el-radio v-model="ruleForm.gender" label="2" prop="gender" >여자</el-radio>
				</el-form-item>

                 <!--  주소 -->
                <el-form-item label="주소" prop="extraAddress">
                    <el-input v-model="ruleForm.address" placeholder="주소" readonly="readonly" style="width: 50%; margin-right: 10px"></el-input>
                    <el-button type="info"  @click="execDaumPostcode" plain>우편번호 찾기</el-button>
                    <el-input v-model="ruleForm.extraAddress" placeholder="상세주소"  style="width: 75%;"></el-input>
                </el-form-item>
            

                <!-- 서브밋버튼 -->
                <div class="submitBtnDiv">
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">회원가입</el-button>
                        <el-button @click="resetForm('ruleForm')">취소</el-button>
                    </el-form-item>
                </div>
            </el-form>
        </div>

    </div>
</template>

<script>
export default {
    data(){
     /*  var validateId = (rule, value, callback) => {
          if(this.ruleForm.memberId === '' || this.ruleForm.memberId === null){
            callback(new Error('제발'))
          }
      }; */

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
            checkIs: false,
            ruleForm: {
                memberId: '',
                pass: '',
                checkPass: '',
                memberName: '',
				email: '', 
				gender: '1',
				age: '',
				postcode: '',
      			address: '',
                extraAddress: '',
                auth: '0'
            },
            rules: {
                memberId: [
                    { required: true, message: '아이디를 입력해주세요', trigger: 'blur' },
                    { min: 3, max: 20, message: '아이디는 3글자에서 20자로 사이로 지어주세요', trigger: 'blur' },
                ],
                pass: [
                    { required: true, validator: validatePass, trigger: 'blur' }
                ],
                checkPass: [
                    { required: true, validator: validatePass2, trigger: 'blur' }
                ],
                memberName:[
                     { required: true, message: '이름을 입력해주세요', trigger: 'blur' },
                ],
                email: [
                    { required: true, message: '이메일을 입력해주세요', trigger: 'blur' },
                    { type: 'email', message: '이메일 형식으로 입력해주세요', trigger: ['blur', 'change'] }
                ],
                age: [
                    { required: true, message: '나이대를 선택해 주세요', trigger: 'blur' },
                ],
                gender:[
                    {  message: '성별을 주세요', trigger: 'blur'}
                ],
                address:[
                    { required: true, message: '상세 주소를 입력해주세요', trigger: 'blur' },
                ],
                extraAddress:[
                    {message: '상세주소를 입력해주세요', trigger: 'blur' }
                ]
            },
           
        }
    },
    methods:{
         idcheck(){
            if( this.ruleForm.memberId == ''){
                alert('아이디를 입력해주세요')
            }else {
                var params = new URLSearchParams();
                params.append('memberId', this.ruleForm.memberId)
                axios.post('http://localhost:9000/checkid', params)
                .then(res => {
                    if(res.data == true){
                        alert(' 아이디 사용이 가능합니다.')
                        this.checkIs = true
                    }else{
                        alert('이미 아이디가 존재합니다')
                    }
                }) 
            }
        }, 
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
            if (valid) {
                if( this.checkIs  == false){
                    alert('아이디 중복확인을 해주세요')
                }else{
                 
                    var memberAddress = this.ruleForm.address + this.ruleForm.extraAddress
               
                    var params = new URLSearchParams();
                    params.append('memberId', this.ruleForm.memberId)
                    params.append('pwd', this.ruleForm.pass)
                    params.append('memberName', this.ruleForm.memberName)
                    params.append('email', this.ruleForm.email)
                    params.append('gender',  this.ruleForm.gender)
                    params.append('age', this.ruleForm.age)
                    params.append('address', memberAddress)
                    params.append('auth', this.ruleForm.auth)
                    console.log(params)
                    axios.post('http://localhost:9000/createMember', params).then(
                        res => {
                            if(res.data == true){
                                alert('회원가입이 완료 되었습니다')
                                this.$router.push ({name:'memberInter'})
                            }
                        }) 
                }
              
            } else {
                console.log('error submit!!');
                return false;
            }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
		},
		execDaumPostcode() {
			new daum.Postcode({
			onComplete: (data) => {
			if (data.userSelectedType === 'R') {
				this.ruleForm.address = data.roadAddress;
			} else {
				this.ruleForm.address = data.jibunAddress;
			}
			if (data.userSelectedType === 'R') {
				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
				this.ruleForm.extraAddress += data.bname;
				}
				if (data.buildingName !== '' && data.apartment === 'Y') {
				this.ruleForm.extraAddress +=
					this.ruleForm.extraAddress !== ''
					? `, ${data.buildingName}`
					: data.buildingName;
				}
				if (this.ruleForm.extraAddress !== '') {
				this.ruleForm.extraAddress = ` (${this.ruleForm.extraAddress})`;
				}
			} else {
				this.ruleForm.extraAddress = '';
			}
			},
		}).open();
		}
    }

}
</script>

<style scope>

.createContainer {
    position: relative;
    margin: auto;
    overflow: hidden;
    width: 600px;
    height: auto;
    border-radius: 10px;
}
.title{
    font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
    font-size: 18px;
    margin: auto;
    margin-top: 30px;
    margin-bottom: 20px;
    text-align: center;
}
.submitBtnDiv{
    margin-left: -120px;
    text-align: center;
}
</style>