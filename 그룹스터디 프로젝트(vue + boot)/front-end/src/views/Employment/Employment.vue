<template>
  <div class="employment">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="구인 공고" name="first">
       <recruiting></recruiting>
      </el-tab-pane>
      <el-tab-pane label="이력서" name="second">
       <CVMain v-show="cvMain" v-on:showCVDetail="showCVDetail" v-on:showCVWriting="showCVWriting" v-on:showCVUpdate="showCVUpdate"></CVMain>
       <CVDetail v-show="cvDetail" v-on:showCVMain="showCVMain"></CVDetail>
       <CVWriting v-show="cvWriting" v-on:showCVMain="showCVMain"></CVWriting>
       <CVUpdate v-show="cvUpdate" v-on:showCVMain="showCVMain"></CVUpdate>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import recruiting from '@/views/Employment/depth2/Recruiting_list'
import CVMain from '@/views/Employment/depth2/CVMain'
import CVDetail from '@/views/Employment/depth2/CVDetail'
import CVWriting from '@/views/Employment/depth2/CVWriting'
import CVUpdate from '@/views/Employment/depth2/CVUpdate'

export default {
  components:{
    recruiting, CVMain, CVDetail, CVWriting, CVUpdate
  },
  data(){
    return{
      activeName: 'first',
      cvMain : true,
      cvDetail : false,
      cvWriting : false,
      cvUpdate : false,
      paramNum : 0

    }
  },

  mounted(){
    
  },
  methods: {
    showCVMain(){
      this.activeName = 'second'
      this.cvMain = true
      this.cvDetail = false
      this.cvWriting = false
      this.cvUpdate = false
    },
    showCVDetail(){
      this.activeName = 'second'
      this.cvMain = false
      this.cvDetail = true
      this.cvWriting = false
      this.cvUpdate = false
    },
    showCVWriting(){
      this.activeName = 'second'
      this.cvMain = false
      this.cvDetail = false
      this.cvWriting = true
      this.cvUpdate = false
    },
    showCVUpdate(){
      this.activeName = 'second'
      this.cvMain = false
      this.cvDetail = false
      this.cvWriting = false
      this.cvUpdate = true
    }
    
  },
  mounted(){
    this.paramNum = this.$route.params.memberSeq
    if(this.paramNum > 0){
      this.showCVUpdate()
    }
    // alert("this.showCVUpdate()")
      
      
  }
}
</script>

<style scope>
@-webkit-keyframes text-focus-in{0%{-webkit-filter:blur(12px);filter:blur(12px);opacity:0}100%{-webkit-filter:blur(0);filter:blur(0);opacity:1}}@keyframes text-focus-in{0%{-webkit-filter:blur(12px);filter:blur(12px);opacity:0}100%{-webkit-filter:blur(0);filter:blur(0);opacity:1}}
.text-focus-in{-webkit-animation:text-focus-in .3s cubic-bezier(.55,.085,.68,.53) .8s both;animation:text-focus-in .3s cubic-bezier(.55,.085,.68,.53) .8s both}

@-webkit-keyframes bounce-top{0%{-webkit-transform:translateY(-45px);transform:translateY(-45px);-webkit-animation-timing-function:ease-in;animation-timing-function:ease-in;opacity:1}24%{opacity:1}40%{-webkit-transform:translateY(-24px);transform:translateY(-24px);-webkit-animation-timing-function:ease-in;animation-timing-function:ease-in}65%{-webkit-transform:translateY(-12px);transform:translateY(-12px);-webkit-animation-timing-function:ease-in;animation-timing-function:ease-in}82%{-webkit-transform:translateY(-6px);transform:translateY(-6px);-webkit-animation-timing-function:ease-in;animation-timing-function:ease-in}93%{-webkit-transform:translateY(-4px);transform:translateY(-4px);-webkit-animation-timing-function:ease-in;animation-timing-function:ease-in}25%,55%,75%,87%{-webkit-transform:translateY(0);transform:translateY(0);-webkit-animation-timing-function:ease-out;animation-timing-function:ease-out}100%{-webkit-transform:translateY(0);transform:translateY(0);-webkit-animation-timing-function:ease-out;animation-timing-function:ease-out;opacity:1}}@keyframes bounce-top{0%{-webkit-transform:translateY(-45px);transform:translateY(-45px);-webkit-animation-timing-function:ease-in;animation-timing-function:ease-in;opacity:1}24%{opacity:1}40%{-webkit-transform:translateY(-24px);transform:translateY(-24px);-webkit-animation-timing-function:ease-in;animation-timing-function:ease-in}65%{-webkit-transform:translateY(-12px);transform:translateY(-12px);-webkit-animation-timing-function:ease-in;animation-timing-function:ease-in}82%{-webkit-transform:translateY(-6px);transform:translateY(-6px);-webkit-animation-timing-function:ease-in;animation-timing-function:ease-in}93%{-webkit-transform:translateY(-4px);transform:translateY(-4px);-webkit-animation-timing-function:ease-in;animation-timing-function:ease-in}25%,55%,75%,87%{-webkit-transform:translateY(0);transform:translateY(0);-webkit-animation-timing-function:ease-out;animation-timing-function:ease-out}100%{-webkit-transform:translateY(0);transform:translateY(0);-webkit-animation-timing-function:ease-out;animation-timing-function:ease-out;opacity:1}}
.bounce-top{-webkit-animation:bounce-top .9s both;animation:bounce-top .9s both}

.gradientBorder:hover::before {
    transform: translateX(0);
}

div.employment_detail
{
  margin: 0 auto;
	padding: 1em 3em;
	text-align: center;
}

div.employment_detail a
{
    text-decoration: none;
    margin: 0px 10px;
    padding: 10px 10px;
    position: relative;
    z-index: 0;
    cursor: pointer;
}

/* Circle behind */
div.circleBehind a:before, div.circleBehind a:after
{
    position: absolute;
    top: 22px;
    left: 50%;
    width: 40px;
    height: 40px;
    border: 2px solid #0277bd;
    transform: translateX(-50%) translateY(-50%) scale(0.8);
    border-radius: 50%;
    background: transparent;
    content: "";
    opacity: 0;
    transition: all 0.3s;
    z-index: -1;
}

div.circleBehind a:after
{
    border-width: 1px;
    transition: all 0.4s;
}

div.circleBehind a:hover:before
{
    opacity: 1;
    transform: translateX(-50%) translateY(-50%) scale(0.8);
}

div.circleBehind a:hover:after
{
    opacity: 1;
    transform: translateX(-50%) translateY(-50%) scale(1.1);
}
</style>


