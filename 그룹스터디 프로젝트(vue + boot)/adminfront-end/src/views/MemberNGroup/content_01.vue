<template>
    <div class="content01_view">
        <h1> manager member 11111111111111111111111</h1>
        <el-table
        :data="showAllMember"
        stripe
        style="width: 100%">
                    
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
            prop="address"
            label="address"
            width="100">
            </el-table-column>

            <el-table-column
            prop="interArea"
            label="interArea"
            width="100">
            </el-table-column>

            <el-table-column
            prop="email"
            label="email"
            width="100">
            </el-table-column>

            <el-table-column
            prop="age"
            label="age"
            width="100">
            </el-table-column>

            <el-table-column
            prop="gender"
            label="gender"
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
              width="100">
              <button type="button"> 상세정보 </button>
                <template slot-scope="scope">
                     <router-link :to="'/managemember1/'+scope.row.memberId">  <!--memberid가 파라미터로 전달됨-->
                        <el-button type="primary" size="small" icon="el-icon-search">
                            상세정보
                        </el-button>
                    </router-link>

                    <!-- content_01_memdetail -->


                </template>

            </el-table-column>



        </el-table>    

    </div>
</template>

<script scoped>
var output_data;
import 'element-ui/lib/theme-chalk/index.css';
// import memberdetail from '@/view/Member/content_01_memdetail';

export default {
    data(){
        return {
            showAllMember:null,
            // memberdetail:false,

        }
    },
    created(){

        // axios.post("http://localhost:9000/AdminMemberSelectAll")
        // .then(res => {
        // console.log(res.data)
        // this.showAllMember=res.data;
        // console.log(this.showAllMember)
        // })
        this.showMemberList()
    },


    methods:{

        // axios data processing

        showMemberList(){
            axios.post("http://localhost:9000/AdminMemberSelectAll")
                .then(res => {
                // console.log(JSON.stringify(res.data))
                this.showAllMember=res.data;
            })
        },

    },
//   mounted(){
//       this.$store.state.currpage = this.$route.path
//       var params = new URLSearchParams();
//       params.append('itemsPerPage', this.itemsPerPage);
//       params.append('currentPage', this.currentPage);
//       axios.post("http://localhost:9000/groupBoardList", params)
//                 .then(res => {
//             this.tableData = res.data
//             this.totalItem = res.data.length
//           })
//   }
}
</script>


<style scoped>
.boardTableFrom{
  width: 80%;
  margin: auto;
}
.boardSearchBar{
  width: 45%;
  float: right;
}
</style>
