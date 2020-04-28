<template>
    <div class="content02_view">
        <h1> Company member Management tool </h1>


      <el-table 
        :data="CompanyMemberAll"
        stripe
        style="width:100%"
        
        >

            <el-table-column
              prop="memberSeq"
              label="memberSeq"
              width="100">
            </el-table-column>

            <el-table-column
              prop="memberId"
              label="memberId"
              width="100">
            </el-table-column>

            <el-table-column
              prop="pwd"
              label="pwd"
              width="100">
            </el-table-column>

            <el-table-column
              prop="memberName"
              label="memberName"
              width="100">
            </el-table-column>
            
            <el-table-column
              prop="birth"
              label="birth"
              width="100">
            </el-table-column>

            <el-table-column
              prop="address"
              label="address"
              width="100">
            </el-table-column>

            <el-table-column
              prop="location"
              label="location"
              width="100">
            </el-table-column>

            <el-table-column
              prop="email"
              label="email"
              width="100">
            </el-table-column>

            <el-table-column
              prop="myMoney"
              label="myMoney"
              width="100">
            </el-table-column>

            <el-table-column
              prop="cv"
              label="cv"
              width="100">
            </el-table-column>

            <el-table-column
              prop="companyInfo"
              label="companyInfo"
              width="100">
            </el-table-column>
            
            <el-table-column
              prop="auth"
              label="auth"
              width="100">
            </el-table-column>

            <el-table-column
              prop="del"
              label="del"
              width="100">
            </el-table-column>

            <el-table-column
              prop="dislike"
              label="dislike"
              width="100">
            </el-table-column>

            <el-table-column
              prop=""
              label="button"
              width="100"
              
              >
              <button type="button"> 상세정보 </button>

            </el-table-column>



      </el-table>

      <br> <hr>

            <pre>
-> callList
/adminCompanyMemberShowAll
/showCompanyInfo  (Memberdto)
/adminGetCompanyInfoOne
/finishApprovalCompanyList
/adminGetCompanyLocationALl
/adminGetCompanyNmaeAll
      </pre>
      <!-- <button v-on:click="showAdminCompanyMamberShowAll"> Call Company List All </button> -->
      <!-- <ul>
        <li v-for="item in CompanyMemberAll" :key="item.memberSeq">{{item}}</li>

      </ul> -->

      <button v-on:click="changePermission"> 1/1 권한 설정 </button>
    

      


    </div>
</template>

<script scoped>

import 'element-ui/lib/theme-chalk/display.css';
const path = "http://localhost:9000";

export default {
    data(){
      return{
        CompanyMemberAll:[]
        




      }
        
    },
    created(){
      
        axios.post(path+"/adminCompanyMemberShowAll")
        .then(res=>{
          console.log(res)
          this.CompanyMemberAll=res.data
        })
      
    },

    methods:{

      changePermission(){
        const confirmflag = confirm("그룹개설신청 허가하시겠습니까");
        if(confirmflag){

          var params = new URLSearchParams();	// post 방식으로 받아야함.
          // params.append('groupInfoSeq', this.listQuery.page);
          // params.append('memberSeq', this.listQuery.limit);

          //TestCode <<< START >>>
          params.append('groupInfoSeq', 1);
          params.append('memberSeq', 1);
          //TestCode <<< END >>>

          axios.post(path+"/AdminAcceptCreateGroup",params)
          .then(res=>{
            console.log(res.data)
            //$router.path.push('/managemember2')
          })
        }else{
          alert("그룹개설신청 거부");
        }
      },


      
        
          
     
    }

}
</script>

<style>

</style>


    <!-- <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        prop="date"
        label="Date"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="Name"
        width="180">
      </el-table-column>
      <el-table-column
        prop="address"
        label="Address">
      </el-table-column>
    </el-table> -->












    <!--

    ELEMENT UI 의 이미지 영역 참조. <<image preview >>

    <div class="demo-image__preview">
  <el-image 
    style="width: 100px; height: 100px"
    :src="url" 
    :preview-src-list="srcList">
  </el-image>
</div>

<script>
  export default {
    data() {
      return {
        url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
        srcList: [
          'https://fuss10.elemecdn.com/8/27/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg',
          'https://fuss10.elemecdn.com/1/8e/aeffeb4de74e2fde4bd74fc7b4486jpeg.jpeg'
        ]
      }
    }
  }
</script>




-->