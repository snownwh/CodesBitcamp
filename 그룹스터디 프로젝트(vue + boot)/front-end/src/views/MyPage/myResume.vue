<template>
  <div>
    <div class="slib">
      <div class="slib_info">
        <div class="tit">
          <img src='../../assets/css/images/resume.png'>
        </div>
        <div class="titup">
          <table class="table1" style="margin-left: 55px">
            <colgroup>
            <col style="width: 45%">
            <col style="width: 45%">
            </colgroup>
            <thead>
              <tr>
                <th>제목</th>
                <th>관심분야</th>
                <th></th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>{{title}}</td>
                <td>{{category}}</td>
                <td><i class="el-icon-paperclip" style="color: #ff5151"></i> </td>
                <td>
                  <el-button type="text" @click="edit" style="color: #ff5151; font-size: 16px">수정</el-button>
                </td>
              </tr>                   
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import logincss from '@/assets/css/member/myinfo.css'

export default {
  data(){
    return{
      login1 : "",
      title : "",
      category : ""
    }
  },
  mounted(){
    var params = new URLSearchParams();	// post 방식으로 받아야함. 
      params.append('memberSeq', this.login1.memberSeq);
      axios.post("http://localhost:9000/getOneCVByMemberSeq", params).then(res => { 
        this.$store.state.s_employment.cvDetail = res.data
        this.title = this.$store.state.s_employment.cvDetail.title
        this.category = this.$store.state.s_employment.cvDetail.category
        })
  },
  created(){
		let sMemberSeq = sessionStorage.getItem("loginUser")
    this.login1 = JSON.parse(sMemberSeq)
  },
  methods:{
    edit(){
      this.$router.push({
        path: '/employment/' + this.login1.memberSeq
      })
    }
  }

}
</script>

<style>

</style>
