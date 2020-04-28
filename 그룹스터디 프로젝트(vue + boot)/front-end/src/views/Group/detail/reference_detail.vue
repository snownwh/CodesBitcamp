<template>
  <div class="reference_detail">
    <br>
    <br>
    <div class="groupDetailcCntainer" v-loading="this.$store.state.s_group.showReferenceDetail">
      <div class="groupName">
        <h1>{{ this.$store.state.s_group.groupReferenceDetail.title }}</h1>
      </div>
      <div class="hr"></div>
      <div class="groupInfoTitle">
        <h5>내용</h5>
      </div>
      <div class="groupInfoContent">
        {{ this.$store.state.s_group.groupReferenceDetail.content }}
        {{ this.$store.state.s_group.groupReferenceDetail.image }}
        <el-button @click="downloadBtn" round>DownLoad</el-button>
      </div>
      <div class="hr"></div>
      <div class="groupInfoTitle">
        <h5><span>상세 정보</span></h5>
      </div>
      <div class="groupInfoContent">
        <div><span>작성자</span> {{ this.$store.state.s_group.groupReferenceDetail.memberSeq }}</div>
        <div><span>작성일</span> {{ this.$store.state.s_group.groupReferenceDetail.writeDate }}</div>
        <div><span>조회수</span> {{ this.$store.state.s_group.groupReferenceDetail.readCount }}</div>
      </div>
      <div class="hr"></div>
      <div class="groupName">
        <el-button type="primary" @click="showGroupReference" round>돌아가기</el-button>
        <el-button type="primary" v-show="this.$store.state.s_group.showUpdateBtn" round>수정하기</el-button>
        <el-button type="primary" v-show="this.$store.state.s_group.showDeleteBtn" round>삭제하기</el-button>
      </div>
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
        boardSeq: ""
      }
  },
  methods:{
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
    showGroupReference(){
      this.getList()
      this.$emit("showGroupReference")
    },
    downloadBtn(){
      console.log('https://bit-palette.s3.ap-northeast-2.amazonaws.com/111/Awesome.PNG')
      axios({
      url: 'https://bit-palette.s3.ap-northeast-2.amazonaws.com/111/Awesome.PNG' ,
      method: 'GET',
      responseType: 'blob',
      }).then((response) => {
            var fileURL = window.URL.createObjectURL(new Blob([response.data]));
            var fileLink = document.createElement('a');

            fileLink.href = fileURL;
            fileLink.setAttribute('download', 'file.png');
            document.body.appendChild(fileLink);

            fileLink.click();
      });
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