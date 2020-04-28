<template>
  <div class="reference_write">
        <br>
        <br>
        <el-button @click="showGroupReference" round>목록으로</el-button>
        <br>
        <br>
        <el-form :model="ruleForm" label-position="top" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
            <el-form-item label="자료 올리기" prop="fileList">
                <el-upload
                    action=""
                    list-type="picture-card"
                    accept=".jpg, .jpeg, .png, .bmp, .txt, .ppt, .pptx, .hwp"
                    multiple
                    v-model="ruleForm.fileList"
                    :limit="3"
                    :auto-upload="false"
                    :on-change="handleChange"
                    :on-exceed="handleExceed"
                    :on-preview="handlePictureCardPreview"
                    :on-remove="handleRemove">
                    <i class="el-icon-plus"></i>
                </el-upload>
                <el-dialog :visible.sync="dialogVisible">
                    <img width="100%" :src="ruleForm.dialogImageUrl" alt="">
                </el-dialog>
            </el-form-item>

            <el-form-item label="자료 제목"  prop="title">
                <el-input v-model="ruleForm.title"></el-input>
            </el-form-item>

            <el-form-item label="자료 간략내용" style="margin-bottom: 10px;">
                <el-input v-model="ruleForm.content"></el-input>
            </el-form-item>
                <br>
                <br>
                <br>
            <div class="writeButton" style="display: table; margin: auto;">
                <el-form-item>
                    <el-button @click="submitForm('ruleForm')" round>작성완료</el-button>
                </el-form-item>
            </div>
        </el-form>
  </div>
</template>

<script>
import quillexamplesnow from '@/components/Qilledit'

export default {
    components:{
        quillexamplesnow
    },
    data(){
        return{
            dialogVisible: false,
            ruleForm:{
                title: '',
                content: '',
                dialogImageUrl: '',
                fileList: [],
            },
            rules: {
                title: [
                    { required: true, message: '자료 제목을 입력하세요', trigger: 'blur' },
                ],
                content: [
                    { required: true, message: '간략하게 자료에 대한 설명을해주세요', trigger: 'blur' },
                ],
                fileList: [
                    { required: true, message: '자료를 올려주세요', trigger: 'change' },
                ]
            }
        }
    },
    methods:{
        showGroupReference(){
            this.$emit("showGroupReference")
        },
        textContent(value){
            this.content = value
        },
        submitForm(formName) {
			this.$refs[formName].validate((valid) => {
			if (valid) {
                let formData = new FormData();
                formData.append('groupInfoSeq', this.$route.params.groupSeq)
                formData.append('memberSeq', this.$store.state.loginUser.memberSeq)
                formData.append('memberId', this.$store.state.loginUser.memberId)
                formData.append('title',this.ruleForm.title)
                formData.append('content',this.ruleForm.content)
                this.ruleForm.fileList.forEach(function(element){
                    formData.append('files', element)
                })
                for (let key of formData.entries()){
                    console.log(`${key}`)
                }
				axios.post("http://localhost:9000/insertGroupReference", formData ,{
					headers:{
                        'Content-Type' : 'multipart/form-data'
					}
				}).then(res =>{
                    console.log(res.data)
                    alert("자료가 업로드 되었습니다.")
                    this.getList()
                    this.showGroupReference()
				})
				
			} else {
				console.log('error submit!!');
				return false;
			}
            });
        },
        handleChange(file, fileList){
            this.ruleForm.fileList.push(file.raw) 
            console.log("addList")
            console.log(this.ruleForm.fileList)
        },
        handleRemove(file, fileList) {
            this.ruleForm.fileList = this.ruleForm.fileList.filter(data => data.name !== file.raw.name)
            console.log("removeList")
            console.log(this.ruleForm.fileList)
        },
        handlePictureCardPreview(file) {
            this.ruleForm.dialogImageUrl = file.url;
            this.dialogVisible = true;
        },
        handleExceed(files, fileList){
            this.$message({
                showClose: true,
                center: true,
                message: '자료는 한번에 2개만 업로드가능합니다.',
                type: 'error'
            })
        },
        getList(){
            this.$store.state.s_group.showGroupReferenceList = true
            var params = new URLSearchParams()
            params.append('groupSeq', this.$store.state.s_group.groupSeq);
            axios.post("http://localhost:9000/groupPdsList", params)
                        .then(res => {
                    this.$store.state.s_group.groupReferenceList = res.data
                    this.$store.state.s_group.showGroupReferenceList = false
        })
        },
    }
}
</script>

<style>

</style>