<template>
    <div class="board_write">
        <br>
        <br>
        <el-button @click="showList" round>목록으로</el-button>
        <br>
        <br>
        <br>
        <div class="form" style="width: 90%; margin: auto;">
            <el-form :model="ruleForm" label-position="top" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
                <el-form-item label="제목"  prop="title">
                    <el-input v-model="ruleForm.title"></el-input>
                </el-form-item>
                <el-form-item label="내용" style="margin-bottom: 10px;"/>
                <quillexamplesnow @contentS="textContent" />
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

        <br>
        <br>
        <br>
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
            content: '',
            ruleForm:{

            },
            rules: {
                title: [
                    { required: true, message: '제목을 입력하세요', trigger: 'blur' },
                ],
            }
        }
    },
    methods:{
        showList(){
            this.$emit("showBoard")
        },
        textContent(value){
            this.content = value
        },
        submitForm(formName) {
			this.$refs[formName].validate((valid) => {
			if (valid) {
                // alert(this.$store.state.loginUser.memberSeq)
                // alert(this.$store.state.s_group.groupSeq)
				axios.get("http://localhost:9000/insertGroupBoard",{
					params:{
                		memberSeq: this.$store.state.loginUser.memberSeq,
                        groupInfoSeq: this.$store.state.s_group.groupSeq,
						title: this.ruleForm.title,
						content: this.content
					}
				}).then(res =>{
                    alert("게시글 작성이 완료되었습니다.")
                    this.allList()
                    this.showList()
				})
				
			} else {
				console.log('error submit!!');
				return false;
			}
			});
        },
        allList(){
          this.$store.state.s_group.showBoardList = true
          this.$store.state.s_group.s_keyWord=''
          this.$store.state.s_group.searchWord=''
          var params = new URLSearchParams();
          params.append('page', 1);
          params.append('limit', this.$store.state.s_group.listQuery.limit);
          params.append('groupSeq', this.$store.state.s_group.groupSeq);
          params.append('keyWord', this.$store.state.s_group.s_keyWord);
          params.append('searchWord', this.$store.state.s_group.searchWord);
          axios.post("http://localhost:9000/groupPagingList", params)
                    .then(res => {
                this.$store.state.s_group.groupBoardList = res.data
                this.getTotal()
                this.$store.state.s_group.showBoardList = false
              })
        },
        getTotal(){
                var params = new URLSearchParams()
                params.append('groupSeq', this.$store.state.s_group.groupSeq);
                params.append('keyWord', this.$store.state.s_group.s_keyWord);
                params.append('searchWord', this.$store.state.s_group.searchWord);
                axios.post("http://localhost:9000/groupBoardTotal", params)
                      .then(res => {
                        this.$store.state.s_group.total = res.data
                      })
        }
    }
}
</script>

<style>

</style>