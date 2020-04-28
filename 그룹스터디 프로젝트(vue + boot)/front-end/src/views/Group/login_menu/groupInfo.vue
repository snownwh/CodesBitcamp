<template>
    <div class="groupInfo">
        <br>
    <br>
    <div class="groupDetailcCntainer" v-loading="this.$store.state.s_group.showGroupInfo">
      <!-- <div class="groupImage">
        <img :src="this.$store.state.s_group.grouDetail.image" />
      </div>
      <div class="groupName">
        <h1>{{ this.$store.state.s_group.grouDetail.groupName }}</h1>
      </div> -->
      <!-- <div class="hr"></div>
      <div class="groupInfoTitle">
        <h5>스터디 소개</h5>
      </div>
      <div class="groupInfoContent">
        {{ this.$store.state.s_group.grouDetail.info }}
      </div> -->
      
      <div class="groupInfoTitle">
        <h3><span>{{ this.$store.state.s_group.grouDetail.groupName }}</span></h3>
        <img :src="this.$store.state.s_group.grouDetail.image" style="margin: 40px; height: 250px;"/>
      </div>
      <div class="groupInfoInnerImage">
      </div>
      <div class="groupInfoContent">
        <div><span>분류</span> {{ this.$store.state.s_group.grouDetail.interBigDto.bigName }} > {{this.$store.state.s_group.grouDetail.interSmallDto.smallName}}</div>
        <div><span>지역</span> {{ this.$store.state.s_group.grouDetail.groupLocation }}</div>
        <div><span>인원</span> {{ this.$store.state.s_group.grouDetail.currMember}} / {{ this.$store.state.s_group.grouDetail.maxMember}}</div>
        <div><span>일정</span></div>

        <div class="schedules" style="line-height: 12px;">
            <div v-if="this.$store.state.s_group.grouDetail.groupSchedule.monday == 0" class="schedule">월</div>
            <div v-else class="schedule_check">월</div>
            
            <div v-if="this.$store.state.s_group.grouDetail.groupSchedule.tuesday == 0" class="schedule">화</div>
            <div v-else class="schedule_check">화</div>
            
            <div v-if="this.$store.state.s_group.grouDetail.groupSchedule.wednesday == 0" class="schedule">수</div>
            <div v-else class="schedule_check">수</div>
            
            <div v-if="this.$store.state.s_group.grouDetail.groupSchedule.thursday == 0" class="schedule">목</div>
            <div v-else class="schedule_check">목</div>
            
            <div v-if="this.$store.state.s_group.grouDetail.groupSchedule.friday == 0" class="schedule">금</div>
            <div v-else class="schedule_check">금</div>
            
            <div v-if="this.$store.state.s_group.grouDetail.groupSchedule.saturday == 0" class="schedule">토</div>
            <div v-else class="schedule_check">토</div>
            
            <div v-if="this.$store.state.s_group.grouDetail.groupSchedule.sunday == 0" class="schedule">일</div>
            <div v-else class="schedule_check">일</div>
        </div>
        <div><span>시작일</span> {{ this.$store.state.s_group.grouDetail.startDate }}</div>
        <div><span>완료일</span> {{ this.$store.state.s_group.grouDetail.endDate }}</div>

        <div>
          <el-button v-show="this.$store.state.s_group.check1" type="primary" @click="attendClass">출석하기</el-button>
          <el-button v-show="this.$store.state.s_group.check2" type="primary" disabled @click="attendClass">출석완료</el-button>
        </div>
      </div>
      <div class="hr"></div>
    </div>
    <br>
    <br>
    </div>
</template>

<script>
import { loading } from 'element-ui';
export default {
    name : 'GroupInfo',
    date(){
        return{
            groupInfoSeq: "",
            loginSeq:0,
        }
    },methods:{
        getGroupOne(){
            this.$store.state.s_group.showGroupInfo = true
            this.$store.state.s_group.groupSeq = this.$route.params.groupSeq
            this.groupInfoSeq = this.$store.state.s_group.groupSeq

            var params = new URLSearchParams();	// post 방식으로 받아야함.
            params.append('groupInfoSeq', this.groupInfoSeq);
            
            axios.post("http://localhost:9000/getOneGroup", params)
                        .then(res => {
                    this.$store.state.s_group.grouDetail = res.data;
                })
            this.attendStatus()
            

        },
        attendStatus(){
          this.loginSeq = this.$store.state.loginUser.memberSeq
          //alert("loginSeq : "+this.loginSeq)
          var params = new URLSearchParams();	
          params.append('memberSeq', this.loginSeq)
          params.append('groupInfoSeq', this.groupInfoSeq)
          axios.post("http://localhost:9000/getAttendStatus", params)
                      .then(res => {
                  if(res.data === 0){  // 미출석
                    //alert(res.data)
                       this.$store.state.s_group.check1 = true;
                       this.$store.state.s_group.check2 = false;
                  }else{ 
                    // alert("처음 상태: "+res.data)              // 출석
                      this.$store.state.s_group.check1 = false;
                      this.$store.state.s_group.check2 = true;
                  }
                  //alert("data:" +  res.data) 
                  this.$store.state.s_group.showGroupInfo = false
              })
        },
        attendClass(){
          let weekday = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"]
          let today = new Date()
          let checkday = weekday[today.getDay()]
          //alert("출첵 "+weekday[today.getDay()])
          let groupSchedule = this.$store.state.s_group.grouDetail.groupSchedule
         
          //alert(JSON.stringify(groupSchedule) +"/ "+loginSeq)
          var params = new URLSearchParams()
          params.append('checkday', checkday)
          params.append('groupScheduleSeq', this.$store.state.s_group.grouDetail.groupSchedule.groupScheduleSeq)
          params.append('groupInfoSeq', this.$store.state.s_group.grouDetail.groupSchedule.groupInfoSeq)
          params.append('sunday', this.$store.state.s_group.grouDetail.groupSchedule.sunday)
          params.append('monday', this.$store.state.s_group.grouDetail.groupSchedule.monday)
          params.append('tuesday', this.$store.state.s_group.grouDetail.groupSchedule.tuesday)
          params.append('wednesday', this.$store.state.s_group.grouDetail.groupSchedule.wednesday)
          params.append('thursday', this.$store.state.s_group.grouDetail.groupSchedule.thursday)
          params.append('friday', this.$store.state.s_group.grouDetail.groupSchedule.friday)
          params.append('saturday', this.$store.state.s_group.grouDetail.groupSchedule.saturday)
          params.append('memberSeq', this.loginSeq)

          axios.post("http://localhost:9000/attendGroup", params)
                        .then(res => {
                    if(res.data === 0){
                      alert('출석일이 아닙니다')
                    }else if(res.data === 2){
                      alert('이미 출석처리 됐습니다')
                    }else{
                      alert('출첵!!!!')
                      this.$store.state.s_group.check1 = false;
                      this.$store.state.s_group.check2 = true;
                      //alert(this.check1)
                    }
                    
                })
          
        }

    },
    created(){
     
      this.loginSeq = this.$store.state.loginUser.memberSeq
      this.getGroupOne();
        
    }

}
</script>

<style scoped>
.groupInfoInnerImage{
    float: left;
}

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