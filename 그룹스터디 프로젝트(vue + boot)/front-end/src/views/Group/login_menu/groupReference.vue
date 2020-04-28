<template>
  <div class="groupboard">
    <br>
    <br>
    <div class="boardTableFrom">
      <el-button @click="showReferenceWrite" round>자료올리기</el-button>
      <div class="boardSearchBar">
        <el-input
          v-model="search"
          size="large"
          placeholder="Search">
          <el-button slot="append" icon="el-icon-search" style="cursor: default;" round></el-button>
        </el-input>
      </div>
      <el-table 
        v-loading="this.$store.state.s_group.showGroupReferenceList"
        :row-class-name="clickableRows"
        :data="this.$store.state.s_group.groupReferenceList.filter( data => !search || data.title.toLowerCase().includes(search.toLowerCase())
                                                || data.memberDto.memberId.toLowerCase().includes(search.toLowerCase()) )"
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
          width="470px"
          >
        </el-table-column>
        <el-table-column
          prop="memberDto.memberId"
          label="작성자"
          width="100px"
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
      </el-table>
    
    </div>
  </div>
</template>

<script scoped>
import 'element-ui/lib/theme-chalk/index.css';
import { loading } from 'element-ui';

export default {
  name: 'GroupReference',
  data(){
    return{
      search:"",
    }
  },
  methods:{
    getList(){
      this.$store.state.s_group.showGroupReferenceList = true
      var params = new URLSearchParams()
      params.append('groupSeq', this.$store.state.s_group.groupSeq);
      axios.post("http://localhost:9000/groupPdsList", params)
                  .then(res => {
              console.log(res.data)
              this.$store.state.s_group.groupReferenceList = res.data
              this.$store.state.s_group.showGroupReferenceList = false
            })
    },
    gotoClick(row, column, event){
      this.$emit("showReferenceDetail")
      this.$store.state.s_group.showReferenceDetail = true
      var params = new URLSearchParams();	// post 방식으로 받아야함.
      params.append('boardSeq', row.boardSeq);
      axios.post("http://localhost:9000/groupBoardDetail", params).then(res => {
        this.$store.state.s_group.groupReferenceDetail = res.data
        if (String(res.data.memberSeq) === String(this.$store.state.loginUser.memberSeq)){
          this.$store.state.s_group.showDeleteBtn = true
          this.$store.state.s_group.showUpdateBtn = true
        } else{
          this.$store.state.s_group.showDeleteBtn = false
          this.$store.state.s_group.showUpdateBtn = false
        }
        this.$store.state.s_group.showReferenceDetail = false
        })
    },
    clickableRows :function (row, rowIndex) {
      //alert(row.rowIndex)
      return "clickableRows";
    },
    showReferenceWrite(){
      this.$emit("showReferenceWrite")
    }
  },
  computed:{
   
  },
  created(){
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
</style>
