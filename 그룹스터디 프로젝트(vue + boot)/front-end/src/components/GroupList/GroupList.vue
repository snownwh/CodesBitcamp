<template>
     <div class="n_main_background"><!-- 배경 -->
      <div class="n_main_component">
      <!-- 상단 버튼 -->
      <div style="display: flow-root;">
        <div class="btn-group btn-group-toggle" data-toggle="buttons">
          <label class="btn">
            <input type="radio" name="options" id="option3" autocomplete="off" @click="searchBigSeq(0)"> 전체보기
          </label>
          <label class="btn">
            <input type="radio" name="options" id="option1" autocomplete="off" @click="searchBigSeq(1)"> 대학생/취업
          </label>
          <label class="btn">
            <input type="radio" name="options" id="option2" autocomplete="off" @click="searchBigSeq(2)"> 공무원/임용
          </label>
          <label class="btn">
            <input type="radio" name="options" id="option3" autocomplete="off" @click="searchBigSeq(3)"> 어학/회화
          </label>
          <label class="btn">
            <input type="radio" name="options" id="option3" autocomplete="off" @click="searchBigSeq(4)"> 라이프/취미
          </label>

        </div>
      </div>
      <!-- 그룹 리스트 css적용 html -->
      <div style="display: flex; flex-wrap: wrap;" v-loading="loading">
         
            <aside class="profile-card shadow-drop-br" v-for="groupInfo in list" :key="groupInfo.groupInfoSeq" style="margin: 20px auto;">
            <div class="blue">
                <br>
                <div class="Classification">
                <p>{{groupInfo.interSmallDto.smallName}} 스터디</p>
                </div>
                <div class="hr"></div>
                <div class="profile-bio detail_desc">
                <div class="info">
                    <h4>{{groupInfo.groupName}}</h4>
                    <p>{{groupInfo.groupLocation}} | {{groupInfo.memberDto.memberName}}</p>
                    <p>{{groupInfo.smallInfo}}</p>
                    <p>{{groupInfo.currMember}} / {{groupInfo.maxMember}}</p>
                </div>
                <!-- <div class="date">
                    <p>스터디 시작일 : {{groupInfo.startDate}}</p>
                    <p>스터디 종료일 : {{groupInfo.endDate}}</p>
                </div> -->
                </div>
                <header>
                <div class="imageCircle all">
                    <div style="height: 50px;"></div>
                    <router-link :to="{ name: 'groupdetail', params: { contentId: groupInfo.groupInfoSeq }}">
                        <img :src="groupInfo.image">
                    </router-link>
                </div>
                </header>
                <div class="backimage">
                <img :src="groupInfo.image" style="width: 290px; height: auto;">
                </div>
                <div class="buttons detailcontainer">
                <button class="add" @click="gotoDetail(groupInfo.groupInfoSeq)">{{groupInfo.startDate}} 시작</button>
                <button class="like"><span>♥</span></button>
                </div>
            </div>
            </aside>
        
        <div class="back_footer">
        </div>
      </div>
      </div>
    </div><!-- 배경 -->
</template>

<script>
import { loading } from 'element-ui';
export default {
    name: 'GroupList',

  data(){
    return{
      loginSeq:'',
      loading: true,
      list: this.$store.state.s_group.groupList,
      image: "",      
    }
  },  
  mounted(){
    this.loading = true
    this.$store.state.currpage = this.$route.path
    axios.get("http://localhost:9000/getAllGroup")
            .then(res => {
        //alert(JSON.stringify(res.data))
        this.list = res.data
        this.$store.state.s_group.groupList = res.data
        this.loading = false;
      })
  },
  methods:{
    gotoDetail(seq){
      //alert("seq=" + seq)
      this.$router.push('/group/main/detail/'+seq)
    },
    searchBigSeq(seq){
      //alert(seq)
      this.loading = true
      var params = new URLSearchParams();	// post 방식으로 받아야함.
      params.append('interBigSeq', seq);
      axios.post("http://localhost:9000/groupSearchList", params)
              .then(res => {
          this.list = res.data
          this.loading = false
        })
    }
  },

}
</script>

<style scoped>

.Classification{
  text-align: center;
}

@-webkit-keyframes shadow-drop-br{0%{-webkit-box-shadow:0 0 0 0 transparent;box-shadow:0 0 0 0 transparent}100%{-webkit-box-shadow:12px 12px 20px -12px rgba(0,0,0,.35);box-shadow:12px 12px 20px -12px rgba(0,0,0,.35)}}@keyframes shadow-drop-br{0%{-webkit-box-shadow:0 0 0 0 transparent;box-shadow:0 0 0 0 transparent}100%{-webkit-box-shadow:12px 12px 20px -12px rgba(0,0,0,.35);box-shadow:12px 12px 20px -12px rgba(0,0,0,.35)}}
.shadow-drop-br{-webkit-animation:shadow-drop-br .4s cubic-bezier(.25,.46,.45,.94) both;animation:shadow-drop-br .4s cubic-bezier(.25,.46,.45,.94) both}

.imageCircle{
  position: absolute;
  left: 25%;
  z-index: 1;
}
.backimage{
  position: absolute;
  top: 200px;
}

div.group_detail
{
  margin: 0 auto;
	text-align: center;
}

.detail1{
  margin: auto;
  width: 90%;
}

.back_footer{
  height: 450px;
}

.info{
  height: 50px;
}

.info p{
  margin: 15px;
}

.date{
  margin-top: 5px;
}

.hr{
  margin: auto;
  margin-top: 15px;
  margin-bottom: 5px;
  background: #9e9e9e;
  height: 1px;
  width: 70%;
}

.detail_desc {
	 text-transform: none;
	 letter-spacing: 0;
	 color: #4e4e4e;
	 font-size: 1em;
}

.detailcontainer {
   text-transform: uppercase;
	 font-family: "Lato", sans-serif, sans-serif;
	 position: relative;
	 margin: auto;
	 overflow: hidden;
	 background: #fff;
}

.detailcontainer button {
	 padding: 10px;
	 display: inline-block;
	 outline: 0;
	 border: 0;
	 text-transform: uppercase;
	 letter-spacing: 1px;
	 color: #fff;
	 cursor: pointer;
}
.detailcontainer button:hover {
  transition: all 0.4s ease-in-out;
}

.buttons{
  position: absolute;
  z-index: 1;
  bottom: 0px;
  margin: auto;
  width: 100%;
  opacity: 0.9;
}

.add {
  width: 80%;
}
.like {
  width: 20%;
}

.blue header img {
  border-radius: 50%;
  width: 150px;
  height: 150px;
  border: 4px solid #f5f5f5;
}

.blue button{
  background: rgba(106, 169, 233, 0.8);
}
.blue button:hover{
  background: #6aa9e9;
}

aside {
  position: relative;
  background: #f5f5f5;
  border-top: 0px solid rgba(0, 0, 0, 0.22);
  border-bottom: 0px solid rgba(0, 0, 0, 0.33);
  margin: 0 auto;
  display: block;
  height: 420px;
  width: 290px;
  background-size: cover;
  overflow: hidden;
  transition: all ease 0.3s;
}

aside:hover header img {
  animation: profile_image 2000ms linear both;
}

header {
  text-align: center;
}

.profile-bio {
  text-align: center;
  padding: 1px 20px 10px 20px !important;
  transition: all linear 0.7s;
  height: 85px;
}

/*PROFILE IMAGE ANIMATE */
@keyframes profile_image{0%{transform:matrix3d(1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1)}3.4%{transform:matrix3d(1.032,0,0,0,0,1.041,0,0,0,0,1,0,0,0,0,1)}4.7%{transform:matrix3d(1.045,0,0,0,0,1.06,0,0,0,0,1,0,0,0,0,1)}6.81%{transform:matrix3d(1.066,0,0,0,0,1.089,0,0,0,0,1,0,0,0,0,1)}9.41%{transform:matrix3d(1.088,0,0,0,0,1.117,0,0,0,0,1,0,0,0,0,1)}10.21%{transform:matrix3d(1.094,0,0,0,0,1.123,0,0,0,0,1,0,0,0,0,1)}13.61%{transform:matrix3d(1.112,0,0,0,0,1.133,0,0,0,0,1,0,0,0,0,1)}14.11%{transform:matrix3d(1.114,0,0,0,0,1.133,0,0,0,0,1,0,0,0,0,1)}17.52%{transform:matrix3d(1.121,0,0,0,0,1.124,0,0,0,0,1,0,0,0,0,1)}18.72%{transform:matrix3d(1.121,0,0,0,0,1.119,0,0,0,0,1,0,0,0,0,1)}21.32%{transform:matrix3d(1.12,0,0,0,0,1.107,0,0,0,0,1,0,0,0,0,1)}24.32%{transform:matrix3d(1.115,0,0,0,0,1.096,0,0,0,0,1,0,0,0,0,1)}25.23%{transform:matrix3d(1.113,0,0,0,0,1.094,0,0,0,0,1,0,0,0,0,1)}29.03%{transform:matrix3d(1.106,0,0,0,0,1.09,0,0,0,0,1,0,0,0,0,1)}29.93%{transform:matrix3d(1.105,0,0,0,0,1.09,0,0,0,0,1,0,0,0,0,1)}35.54%{transform:matrix3d(1.098,0,0,0,0,1.096,0,0,0,0,1,0,0,0,0,1)}36.74%{transform:matrix3d(1.097,0,0,0,0,1.098,0,0,0,0,1,0,0,0,0,1)}41.04%{transform:matrix3d(1.096,0,0,0,0,1.102,0,0,0,0,1,0,0,0,0,1)}44.44%{transform:matrix3d(1.097,0,0,0,0,1.103,0,0,0,0,1,0,0,0,0,1)}52.15%{transform:matrix3d(1.099,0,0,0,0,1.101,0,0,0,0,1,0,0,0,0,1)}59.86%{transform:matrix3d(1.101,0,0,0,0,1.099,0,0,0,0,1,0,0,0,0,1)}63.26%{transform:matrix3d(1.101,0,0,0,0,1.099,0,0,0,0,1,0,0,0,0,1)}75.28%{transform:matrix3d(1.1,0,0,0,0,1.1,0,0,0,0,1,0,0,0,0,1)}85.49%{transform:matrix3d(1.1,0,0,0,0,1.1,0,0,0,0,1,0,0,0,0,1)}90.69%{transform:matrix3d(1.1,0,0,0,0,1.1,0,0,0,0,1,0,0,0,0,1)}100%{transform:matrix3d(1.1,0,0,0,0,1.1,0,0,0,0,1,0,0,0,0,1)}}aside:hover header h1{animation:name_and_job 1.5s linear both;animation-delay:.4s}@keyframes name_and_job{0%{transform:matrix3d(1,0,0,0,0,1,0,0,0,0,1,0,-300,0,0,1)}1.3%{transform:matrix3d(3.905,0,0,0,0,1,0,0,0,0,1,0,-237.02,0,0,1)}2.55%{transform:matrix3d(4.554,0,0,0,0,1,0,0,0,0,1,0,-182.798,0,0,1)}4.1%{transform:matrix3d(4.025,0,0,0,0,1,0,0,0,0,1,0,-125.912,0,0,1)}5.71%{transform:matrix3d(3.039,0,0,0,0,1,0,0,0,0,1,0,-79.596,0,0,1)}8.11%{transform:matrix3d(1.82,0,0,0,0,1,0,0,0,0,1,0,-31.647,0,0,1)}8.81%{transform:matrix3d(1.581,0,0,0,0,1,0,0,0,0,1,0,-21.84,0,0,1)}11.96%{transform:matrix3d(1.034,0,0,0,0,1,0,0,0,0,1,0,4.825,0,0,1)}12.11%{transform:matrix3d(1.023,0,0,0,0,1,0,0,0,0,1,0,5.53,0,0,1)}15.07%{transform:matrix3d(.947,0,0,0,0,1,0,0,0,0,1,0,12.662,0,0,1)}16.12%{transform:matrix3d(.951,0,0,0,0,1,0,0,0,0,1,0,13.007,0,0,1)}27.23%{transform:matrix3d(1.001,0,0,0,0,1,0,0,0,0,1,0,2.352,0,0,1)}27.58%{transform:matrix3d(1.001,0,0,0,0,1,0,0,0,0,1,0,2.121,0,0,1)}38.34%{transform:matrix3d(1,0,0,0,0,1,0,0,0,0,1,0,-.311,0,0,1)}40.09%{transform:matrix3d(1,0,0,0,0,1,0,0,0,0,1,0,-.291,0,0,1)}50%{transform:matrix3d(1,0,0,0,0,1,0,0,0,0,1,0,-.048,0,0,1)}60.56%{transform:matrix3d(1,0,0,0,0,1,0,0,0,0,1,0,.007,0,0,1)}82.78%{transform:matrix3d(1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1)}100%{transform:matrix3d(1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1)}}aside:hover ul li:first-child{animation:social_animation 2s ease-in-out both;animation-delay:.75s}aside:hover ul li:nth-child(2){animation:social_animation 2s ease-in-out both;animation-delay:1s}aside:hover ul li:nth-child(3){animation:social_animation 2s ease-in-out both;animation-delay:1.25s}@keyframes social_animation{0%{transform:matrix3d(1,0,0,0,0,1,0,0,0,0,1,0,-300,0,0,1)}1.3%{transform:matrix3d(3.905,0,0,0,0,1,0,0,0,0,1,0,-237.02,0,0,1)}2.55%{transform:matrix3d(4.554,0,0,0,0,1,0,0,0,0,1,0,-182.798,0,0,1)}4.1%{transform:matrix3d(4.025,0,0,0,0,1,0,0,0,0,1,0,-125.912,0,0,1)}5.71%{transform:matrix3d(3.039,0,0,0,0,1,0,0,0,0,1,0,-79.596,0,0,1)}8.11%{transform:matrix3d(1.82,0,0,0,0,1,0,0,0,0,1,0,-31.647,0,0,1)}8.81%{transform:matrix3d(1.581,0,0,0,0,1,0,0,0,0,1,0,-21.84,0,0,1)}11.96%{transform:matrix3d(1.034,0,0,0,0,1,0,0,0,0,1,0,4.825,0,0,1)}12.11%{transform:matrix3d(1.023,0,0,0,0,1,0,0,0,0,1,0,5.53,0,0,1)}15.07%{transform:matrix3d(.947,0,0,0,0,1,0,0,0,0,1,0,12.662,0,0,1)}16.12%{transform:matrix3d(.951,0,0,0,0,1,0,0,0,0,1,0,13.007,0,0,1)}27.23%{transform:matrix3d(1.001,0,0,0,0,1,0,0,0,0,1,0,2.352,0,0,1)}27.58%{transform:matrix3d(1.001,0,0,0,0,1,0,0,0,0,1,0,2.121,0,0,1)}38.34%{transform:matrix3d(1,0,0,0,0,1,0,0,0,0,1,0,-.311,0,0,1)}40.09%{transform:matrix3d(1,0,0,0,0,1,0,0,0,0,1,0,-.291,0,0,1)}50%{transform:matrix3d(1,0,0,0,0,1,0,0,0,0,1,0,-.048,0,0,1)}60.56%{transform:matrix3d(1,0,0,0,0,1,0,0,0,0,1,0,.007,0,0,1)}82.78%{transform:matrix3d(1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1)}100%{transform:matrix3d(1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1)}}


</style>