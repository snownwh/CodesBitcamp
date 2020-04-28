<template>
  <div>
    <div class="cvCntainer" v-loading="this.$store.state.s_employment.loadingCVDetail">
      <div class="cvtitle">
        <h1>{{ this.$store.state.s_employment.cvDetail.title }}</h1>
      </div>
      <div class="hr"></div>
      <div class="cvInfoTitle">
        <h5>이름</h5>
      </div>
      <div class="cvInfoContent">
        <div> {{ this.$store.state.s_employment.cvDetail.memberDto.memberName }} </div>
      </div>
      <div class="hr"></div>
      <div class="cvInfoTitle">
        <h5>이력서</h5>
      </div>
      <div class="cvInfoContent">
        <div></div>
      </div>
      <div class="hr"></div>
      <div class="cvtitle">
        <el-button type="primary" @click="cvMain" round>돌아가기</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { loading } from 'element-ui';
export default {
  data(){
    return{
      
      
    }
  },
  mounted(){
    
    
  },
  methods:{
    cvMain(){
      this.$emit("showCVMain")
      
    }
  }
}
</script>

<style scoped>
.cvCntainer {
    position: relative;
    margin: auto;
    overflow: hidden;
    height: auto;
    /* background: #f7f7f7; */
}

.cvtitle{
    text-align: center;
    padding: 35px;
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

.cvInfoTitle{
    float: left;
    padding: 60px 0px 0px 100px;
}

.cvInfoContent{
    float: right;
    padding: 60px 100px 60px 0px;
    width: 600px;
    text-align: initial;
}

</style>