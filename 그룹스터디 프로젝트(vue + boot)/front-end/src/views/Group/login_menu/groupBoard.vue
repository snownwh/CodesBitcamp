<template>
  <div class="groupboard">
    <br>
    <br>
    <div class="boardTableFrom">
      <el-button @click="showWrite" round>글쓰기</el-button>
      <div class="boardSearchBar">
        <el-input v-model="searchWord"
                  placeholder="전체목록보기버튼"
                  class="input-with-select">
        <el-button slot="prepend" icon="el-icon-tickets" circle style="margin-right: 10px" @click="allList"></el-button>
          <el-select v-model="s_keyWord" slot="prepend" placeholder="Select">
            <el-option label="작성자" value="writer"></el-option>
            <el-option label="제목" value="title"></el-option>
          </el-select>
          <el-button slot="append" icon="el-icon-search" @click="searchBoard"></el-button>
        </el-input>
      </div>
      <el-table 
        v-loading="this.$store.state.s_group.showBoardList"
        :row-class-name="clickableRows"
        :data="this.$store.state.s_group.groupBoardList"
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
          width="470px">
        </el-table-column>
        <el-table-column
          prop="memberDto.memberId"
          label="작성자"
          width="100px">
        </el-table-column>
        <el-table-column
          prop="readCount"
          label="조회수"
          width="100px">
        </el-table-column>
        <el-table-column
          prop="writeDate"
          label="작성일"
          width="170px">
        </el-table-column>
      </el-table> <br>
      <div class="pageination">
        <pagination v-show="total>0" :total="this.$store.state.s_group.total" :page.sync="listQuery.page" :limit.sync="listQuery.limit"
          @pagination="getList" />
      </div>
    </div>
  </div>
</template>

<script scoped>
import 'element-ui/lib/theme-chalk/index.css';
import Pagination from '@/components/Pagination';
import { loading } from 'element-ui';

export default {
  components: { Pagination },
  data(){
    return{
      total: 0,
        listQuery:{
          page: 1,
          limit: 5,
        },
      searchWord:'',
      s_keyWord:'',
      groupSeq:0,
    }
  },
  methods:{
   allList(){
     this.s_keyWord=''
     this.searchWord=''

     this.$store.state.s_group.showBoardList = true
     var params = new URLSearchParams();
     params.append('page', 1);
     params.append('limit', this.listQuery.limit);
     params.append('groupSeq', this.groupSeq);
     params.append('keyWord', this.s_keyWord);
     params.append('searchWord', this.searchWord);
     axios.post("http://localhost:9000/groupPagingList", params)
              .then(res => {
          this.$store.state.s_group.groupBoardList = res.data
          this.getTotal()
          this.$store.state.s_group.showBoardList = false
        })
   },
   getList(){
      this.$store.state.s_group.showBoardList = true
      var params = new URLSearchParams();	// post 방식으로 받아야함.
      params.append('page', this.listQuery.page);
      params.append('limit', this.listQuery.limit);
      params.append('groupSeq', this.groupSeq);
      params.append('keyWord', this.s_keyWord);
      params.append('searchWord', this.searchWord);
      axios.post("http://localhost:9000/groupPagingList", params)
              .then(res => {
          this.$store.state.s_group.groupBoardList = res.data
          this.$store.state.s_group.showBoardList = false
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
        this.$store.state.s_group.showBoardList = true
        var params = new URLSearchParams();	// post 방식으로 받아야함.
        params.append('page', 1);
        params.append('limit', this.listQuery.limit);
        params.append('groupSeq', this.groupSeq);
        params.append('keyWord', this.s_keyWord);
        params.append('searchWord', this.searchWord);
        axios.post("http://localhost:9000/groupPagingList", params)
                .then(res => {
            this.$store.state.s_group.groupBoardList = res.data
            this.getTotal()
            this.$store.state.s_group.showBoardList = false
          })
      }
    },
    getTotal(){
        this.groupSeq = this.$route.params.groupSeq
        this.$store.state.s_group.groupSeq = this.$route.params.groupSeq
            var params = new URLSearchParams()
            params.append('groupSeq', this.groupSeq);
            params.append('keyWord', this.s_keyWord);
            params.append('searchWord', this.searchWord)
            axios.post("http://localhost:9000/groupBoardTotal", params)
                  .then(res => {
                    this.$store.state.s_group.total = res.data
                    this.total = this.$store.state.s_group.total
                  })
    },
     handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    gotoClick(row, column, event){
      this.$emit("showDetail")
      this.$store.state.s_group.showBoardDetail = true
      var params = new URLSearchParams();	// post 방식으로 받아야함. 
      params.append('boardSeq', row.boardSeq); 
      axios.post("http://localhost:9000/groupBoardDetail", params).then(res => { 
        this.$store.state.s_group.groupBoardDetail = res.data
        if (String(res.data.memberSeq) === String(this.$store.state.loginUser.memberSeq)){
          this.$store.state.s_group.showDeleteBtn = true
          this.$store.state.s_group.showUpdateBtn = true
        } else{
          this.$store.state.s_group.showDeleteBtn = false
          this.$store.state.s_group.showUpdateBtn = false
        }
          this.$store.state.s_group.showBoardDetail = false
        })
      axios.post("http://localhost:9000/groupBoardDetailComments", params).then(res => { 
        this.$store.state.s_group.groupBoardDetailComments = res.data
      })
    },
    clickableRows :function (row, rowIndex) {
      //alert(row.rowIndex)
      return "clickableRows";
    },
    showWrite(){
      this.$emit("showWrite")
    }
  },
  computed:{
   
  },
  created(){
   
    this.getTotal()
    this.getList()
  }
}
</script>

<style scoped>
.boardTableFrom{
  margin: auto;
}
.boardSearchBar{
  width: 45%;
  float: right;
}
.pageination{
  margin: auto;
  display: table;
}
.el-select {
  width: 100px;
}
</style>
