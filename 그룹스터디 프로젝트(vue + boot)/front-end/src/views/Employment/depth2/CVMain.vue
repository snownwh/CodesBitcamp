<template>
  <div class="CV">
    <!-- 일반 회원 / CV 없을 때 -->
    <div v-if="(login1.auth === 0 | login1.auth === 1) & this.$store.state.s_employment.oneMember.cv === 0" class="writeNewCV">
      <el-button type="primary" round @click="writeCV">새로운 이력서 작성</el-button>
    </div>
    <!-- 일반 회원 / CV 있을 때 -->
    <div v-else-if="(login1.auth === 0 | login1.auth === 1) & this.$store.state.s_employment.oneMember.cv === 1" class="updateCV">
      <el-button type="primary" round @click="updateCV">이력서 수정하기</el-button>
    </div>
    <!-- 기업 회원 -->
    <div v-else class="CVList">
      <div class="boardTableFrom">
      <p>이력서 열람</p>
        <div class="boardSearchBar">
          <el-input
            v-model="searchWord"
            size="large"
            placeholder="전체목록보기버튼">
          <el-button slot="prepend" icon="el-icon-tickets" circle style="margin-right:10px" @click="allList"></el-button>
          <el-select v-model="s_keyWord" slot="prepend" placeholder="Select">
            <el-option label="작성자" value="writer"></el-option>
            <el-option label="제목" value="title"></el-option>
          </el-select>
          <el-button slot="append" icon="el-icon-search" @click="searchBoard"></el-button>
          </el-input>
        </div>
        <el-table 
          v-loading="this.$store.state.s_employment.loadingCVDetail"
          :row-class-name="clickableRows"
          :data="tableData"
          stripe
          style="width: 100% cursor:pointer"
          @row-click="gotoClick"
        >
          <el-table-column
            prop="finalnum"
            label="글번호"
            width="150px">
          </el-table-column>
          <el-table-column
            prop="title"
            label="글제목"
            width="400px"
            >
          </el-table-column>
          <el-table-column
            prop="memberDto.memberName"
            label="작성자"
            width="150px"
            >
          </el-table-column>
          <el-table-column
            prop="readCount"
            label="조회수"
            width="100px"
            >
          </el-table-column>
          <el-table-column
            prop="writeDate"
            label="작성일"
            width="170px"
            >
          </el-table-column>
        </el-table> <br>
        <div class="pageination">
          <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { loading } from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import Pagination from '@/components/Pagination'

export default {
  components: { Pagination },
  data(){
    return{
      tableData: [],
      total: 0,
      listQuery:{
        page: 1,
        limit: 10,
        title: "",
        searchWord: "",
        s_keyWord: ""
      },
      searchWord:'',
      
      s_keyWord:'',
      loading: true,
      login1 : "",
    }
  },
  methods:{
    updateCV(){
      this.$emit("showCVUpdate")
    },
    writeCV(){
      this.$emit("showCVWriting")
    },
    allList(){
     this.s_keyWord=''
     this.searchWord=''

     this.loading = true
     var params = new URLSearchParams();
     params.append('page', 1);
     params.append('limit', this.listQuery.limit);
     params.append('keyWord', this.s_keyWord);
     params.append('searchWord', this.searchWord);
     axios.post("http://localhost:9000/CVPagingList", params)
              .then(res => {
          this.tableData = res.data
          this.getTotal()
          this.loading = false
        })
    },
    
    getList(){
      this.loading = true
      // listQuery
      var params = new URLSearchParams();	// post 방식으로 받아야함.
      params.append('page', this.listQuery.page);
      params.append('limit', this.listQuery.limit);
      
      params.append('keyWord', this.s_keyWord);
      params.append('searchWord', this.searchWord);
      axios.post("http://localhost:9000/CVPagingList", params)
              .then(res => {
          this.tableData = res.data
          this.loading = false
        })
    },
    searchBoard(){
      if(this.s_keyWord==''){
        alert('검색타입을 설정해주세요')
      }
      if(this.searchWord==""){
        alert('검색어를 입력해주세요')
      }
      
      if(this.s_keyWord != '' && this.searchWord!=''){
        // alert(this.s_keyWord +"/" + this.searchWord)
        this.loading = true
        var params = new URLSearchParams();	// post 방식으로 받아야함.
        params.append('page', 1);
        params.append('limit', this.listQuery.limit);
        
        params.append('keyWord', this.s_keyWord);
        params.append('searchWord', this.searchWord);
        axios.post("http://localhost:9000/CVPagingList", params)
                .then(res => {
            this.tableData = res.data
            this.getTotal()
            this.loading = false
          })
      }
    },
    getTotal(){
      var params = new URLSearchParams()
      params.append('keyWord', this.s_keyWord);
      params.append('searchWord', this.searchWord)
      axios.post("http://localhost:9000/CVList", params)
            .then(res => {
              this.total = res.data
            })
    },
     handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    gotoClick(row, column, event){
      this.$emit("showCVDetail")
      this.$store.state.s_employment.loadingCVDetail = true
      var params = new URLSearchParams();	// post 방식으로 받아야함. 
      params.append('cvSeq', row.cvSeq);
      axios.post("http://localhost:9000/getOneCV", params).then(res => { 
        this.$store.state.s_employment.cvDetail = res.data
        this.$store.state.s_employment.loadingCVDetail = false
        
        })

    },
    clickableRows :function (row, rowIndex) {
      //alert(row.rowIndex)
      return "clickableRows";
    }
  },
  mounted(){
    var params = new URLSearchParams();	// post 방식으로 받아야함. 
      params.append('memberSeq', this.login1.memberSeq);
      axios.post("http://localhost:9000/oneMember", params).then(res => { 
        this.$store.state.s_employment.oneMember = res.data
      })
    
  
  },
  created(){
		let sMemberSeq = sessionStorage.getItem("loginUser")
    this.login1 = JSON.parse(sMemberSeq)
    
    //페이징
      this.getTotal()
      this.getList()

      
    
		// this.memberSeq = this.$store.state.loginUser.memberSeq
  }
}
</script>

<style scoped>

@import url("https://fonts.googleapis.com/css?family=Lato:400,700");
/* center container in the middle */

/* CV 버튼 */
.CV{
  width: 950px;
  margin: auto;
}

.writeNewCV {
  text-align: center;
  padding: 35px;
}

.updateCV{
  text-align: center;
  padding: 35px;
}

/* 테이블 */
.boardTableFrom{
  margin: auto;
}

/* 검색바 */
.boardSearchBar{
  width: 45%;
  float: right;
}

/* 페이징 */
.pageination{
  margin: auto;
  display: table;
}
.el-select {
  width: 100px;
}

</style>
