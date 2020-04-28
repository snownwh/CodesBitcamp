<template>
  <div class="boardDetail">
    <br>
    <br>
    <div class="groupDetailcCntainer" v-loading="this.$store.state.s_group.showBoardDetail">
      <div class="groupName">
        <h1>{{ this.$store.state.s_group.groupBoardDetail.title }}</h1>
      </div>
      <div class="hr"></div>
      <div class="groupInfoTitle">
        <h5>내용</h5>
      </div>
      <div class="groupInfoContent">
        {{ this.$store.state.s_group.groupBoardDetail.content }}
      </div>
      <div class="hr"></div>
      <div class="groupInfoTitle">
        <h5><span>상세 정보</span></h5>
      </div>
      <div class="groupInfoContent">
        <div><span>작성자</span> {{ this.$store.state.s_group.groupBoardDetail.memberSeq }}</div>
        <div><span>작성일</span> {{ this.$store.state.s_group.groupBoardDetail.writeDate }}</div>
        <div><span>조회수</span> {{ this.$store.state.s_group.groupBoardDetail.readCount }}</div>
      </div>
      <div class="hr"></div>
      <div class="groupName">
        <el-button type="primary" @click="showList" round>돌아가기</el-button>
        <el-button type="primary" v-show="this.$store.state.s_group.showUpdateBtn" @click="boardOneUpdate" round>수정하기</el-button>
        <el-button type="primary" v-show="this.$store.state.s_group.showDeleteBtn" @click="boardOneDelete" round>삭제하기</el-button>
      </div>
      <div class="hr"></div>
      <div class="hr"></div>


    </div>
    <br>
    <br>
  </div>
</template>

<script>
import { loading } from 'element-ui';
export default {
    data(){
        return{
            boardOne: "",
            boardSeq: 0,
        }
    },
    methods:{
        showList(){
          this.allList()
          this.$emit("showBoard")
        },
        boardOneUpdate(){
          this.$emit("showUpdate")
        },
        boardOneDelete(){
          var params = new URLSearchParams();	// post 방식으로 받아야함.
          params.append('boardSeq', this.$store.state.s_group.groupBoardDetail.boardSeq);
          axios.post("http://localhost:9000/groupBoardDelete", params)
                  .then(res => {
                    alert("게시글이 정상적으로 삭제 되었습니다.")
                    this.showList()
          })
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
    },
    mounted(){

    },
}
</script>

<style scoped>

.group_detail{
    width: 950px;
    margin: auto;
}

.groupDetailcCntainer {
    position: relative;
    margin: auto;
    overflow: hidden;
    height: auto;
    background: #f7f7f7;
}

.groupImage img{
    display: table;
    margin: auto;
    height: 250px;
}

.groupName{
    text-align: center;
    padding: 35px;
}
.groupInfoTitle{
    float: left;
    padding: 60px 0px 0px 100px;
}
.groupInfoTitle > h5 > span{
    color: #727272;
}
.groupInfoContent{
    float: right;
    padding: 60px 100px 60px 0px;
    width: 600px;
    text-align: initial;
}
.groupInfoContent > div{
    margin: 14px 0px;
    line-height: 24px;
}

.groupInfoContent > div > span{
    display: list-item;
    color: #727272;
}

.hr{
    display: flex;
    margin: auto;
    margin-top: 15px;
    margin-bottom: 5px;
    background: #c1c1c1;
    height: 1px;
    width: 80%;
}

.schedules {
    display: grid;
    color: #4e4e4e;
    grid-template-columns: repeat(auto-fill, 30px);
    grid-gap: 4px;
}
.schedule {
    padding: 8px;
    border: 1px solid #4e4e4e;
    font-size: 0.7em;
    text-align: center;
}
.schedule_check{
    padding: 8px;
    border: 1px solid #4e4e4e;
    font-size: 0.7em;
    text-align: center;
    background: #FF6C00;
    color: #f5f5f5;
    transition: all 0.4s ease-in-out;
}

</style>