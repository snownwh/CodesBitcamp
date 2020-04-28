<template>
	<el-form>
		<el-form :model="ruleForm" label-position="top" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
			<el-form-item label="기업명">
				<el-input readonly="readonly" :value="login1.memberName"></el-input>
			</el-form-item>
			<el-form-item label="제목"  prop="title">
				<el-input v-model="ruleForm.title" :value="ruleForm.title"></el-input>
			</el-form-item>
			<el-form-item label="접수기간">
				<el-date-picker
					v-model="ruleForm.cvStartDate"
					type="datetime"
					placeholder="시작일">
				</el-date-picker>
				-
				<el-date-picker
					v-model="ruleForm.cvEndDate"
					type="datetime"
					placeholder="마감일">
				</el-date-picker>
			</el-form-item>
			<el-form-item label="경력"  prop="career">
				<el-input v-model="ruleForm.career"></el-input>
			</el-form-item>
			<el-form-item label="학력" prop="education">
				<el-input v-model="ruleForm.education"></el-input>
			</el-form-item>
			<el-form-item label="근무형태" prop="workingType">
				<el-input v-model="ruleForm.workingType"></el-input>
			</el-form-item>
			<el-form-item label="급여" prop="salary">
				<el-input v-model="ruleForm.salary"></el-input>
			</el-form-item>
			<el-form-item label="직급" prop="position">
				<el-input v-model="ruleForm.position"></el-input>
			</el-form-item>
			<el-form-item label="근무지" prop="workingLocation">
				<el-input v-model="ruleForm.workingLocation"></el-input>
			</el-form-item>
			<el-form-item label="기업로고 이미지" prop="image">
				<el-upload
					v-model="ruleForm.dialogImageUrl"
					action="https://jsonplaceholder.typicode.com/posts/"
					list-type="picture-card"
					accept=".jpg, .jpeg, .png, .bmp"
					multiple
					:limit="1"
					:on-exceed="handleExceed"
					:on-preview="handlePictureCardPreview"
					:on-remove="handleRemove">
					<i class="el-icon-plus"></i>
				</el-upload>
				<el-dialog :visible.sync="ruleForm.dialogVisible" append-to-body=true>
					<img width="100%"  :src="ruleForm.dialogImageUrl" alt="">
				</el-dialog>
			</el-form-item>
			<div class="board_update">
        		<quillexamplesnow
					@contentS="qContent"
				/>
    		</div>
			<el-form-item>
				<el-button type="primary" @click="submitForm('ruleForm')">수정</el-button>
				<el-button @click="resetForm('ruleForm')">취소</el-button>
			</el-form-item>
		</el-form>
	</el-form>
</template>

<script>
import quillexamplesnow from '@/components/Qilledit'
import 'element-ui/lib/theme-chalk/index.css';
import Vue from "vue"
import moment from "moment"
import VueMomentJS from "vue-momentjs"

Vue.use(VueMomentJS, moment)

export default {
	components:{
        quillexamplesnow
    },
	 data() {
      return {
        ruleForm: {
		  title: '',
          career: '',
          education: '',
          workingType: '',
		  salary: '',
		  cvStartDate: '',
		  cvEndDate : '',
          position: '',
          workingLocation: '',
		  dialogImageUrl: ""
		},
		content: '',
		login1: [],
		getOneRecruit1 : "",
		login1 : "",
        rules: {
		  title: [
			{ required: true, message: '제목을 입력하세요', trigger: 'blur' },
		  ],
		  career: [
			{ required: true, message: '경력을 입력하세요', trigger: 'blur' },
		  ],
		  period: [
			{ required: true, message: '경력을 입력하세요', trigger: 'blur' },
		  ],
		  education: [
			{ required: true, message: '학력을 입력하세요', trigger: 'blur' },
		  ],
		  workingType: [
            { required: true, message: '근무형태를 입력하세요', trigger: 'blur' },
		  ],
		  salary: [
            { required: true, message: '급여를 입력하세요', trigger: 'blur' },
		  ],
		  position: [
            { required: true, message: '직책을 입력하세요', trigger: 'blur' },
		  ],
		  workingLocation: [
            { required: true, message: '근무지를 입력하세요', trigger: 'blur' },
          ]
        }
      };
    },
    methods: {
		qContent(value){
			this.content = value
		},
		submitForm(formName) {
			this.$refs[formName].validate((valid) => {
				if (valid) {
					axios.get("http://localhost:9000/recruitUpdating",{
						params:{
							boardSeq: this.getOneRecruit1.boardSeq,
							memberSeq: this.login1.memberSeq,
							title: this.ruleForm.title,
							career: this.ruleForm.career,
							education: this.ruleForm.education,
							workingType: this.ruleForm.workingType,
							cvStartDate : this.$moment(this.ruleForm.cvStartDate).format('YYYY.MM.DD HH:mm:ss'),
							cvEndDate : this.$moment(this.ruleForm.cvEndDate).format('YYYY.MM.DD HH:mm:ss'),
							salary: this.ruleForm.salary,
							position: this.ruleForm.position,
							workingLocation: this.ruleForm.workingLocation,
							image: this.ruleForm.dialogImageUrl,
							content:this.content
						}
					}).then(res =>{
						if(res.data === true){
							alert("성공적으로 수정되었습니다.")
							this.$router.push({
								name: 'recruiting'
							})
						}
						else {
							alert("수정을 실패했습니다. 다시 확인해주시기 바랍니다.")
						}
					})
				} else {
					alert('공고가 등록되지 않았습니다.');
					this.$router.push({
						name: 'recruiting'
					})
				}
			});
		},
		resetForm(formName) {
			this.$refs[formName].resetFields();
		},
		handleRemove(file, fileList) {
			console.log(file, fileList);
		},
		handlePictureCardPreview(file) {
			this.ruleForm.dialogImageUrl = file.url;
			this.ruleForm.dialogVisible = true;
		},
		handleExceed(files, fileList){
			this.$message({
				showClose: true,
				center: true,
				message: '대표이미지는 1개만 업로드가능합니다.',
				type: 'error'
			})
		}
	},
	created(){
		let sMemberSeq = sessionStorage.getItem("loginUser")
		this.login1 = JSON.parse(sMemberSeq)
		// this.memberSeq = this.$store.state.loginUser.memberSeq
	},
	mounted(){
		this.empBoardSeq = this.$route.params.boardSeq
		var params = new URLSearchParams()
		params.append("empBoardSeq", this.empBoardSeq)
		axios.post("http://localhost:9000/getOneRecruit", params)
			.then(res =>{
				this.$store.state.s_employment.getOneRecruit = res.data
				this.getOneRecruit1 = this.$store.state.s_employment.getOneRecruit
				this.startDate = this.$moment(res.data.cvStartDate).format('YYYY.MM.DD HH:mm:ss')
            	this.endDate = this.$moment(res.data.cvEndDate).format('YYYY.MM.DD HH:mm:ss')
				this.ruleForm.title = this.getOneRecruit1.title
				this.ruleForm.cvStartDate = this.startDate
				this.ruleForm.cvEndDate = this.endDate
				this.ruleForm.career = this.getOneRecruit1.career
				this.ruleForm.education = this.getOneRecruit1.education
				this.ruleForm.workingType = this.getOneRecruit1.workingType
				this.ruleForm.salary = this.getOneRecruit1.salary
				this.ruleForm.position = this.getOneRecruit1.position
				this.ruleForm.workingLocation = this.getOneRecruit1.workingLocation
				this.content = this.getOneRecruit1.content
			})
  	}
  }
  
</script>