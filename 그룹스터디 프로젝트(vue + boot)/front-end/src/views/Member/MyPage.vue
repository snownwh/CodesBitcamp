<template>
  <div class="myPageWrap">
			<h4 style="margin-left: 29px; padding-top: 5px;" > <strong>{{name}}</strong>님</h4>
		<br>
	  <div class="mymenu">
			<el-menu
			class="el-menu-vertical-demo"
			active-text-color="#ff674b"
			default-active="5">
			<el-menu-item index="1" v-model="auth" v-if="auth == '1' | auth == '0' " @click="studyClick(memSeq)" >
				<i class="el-icon-edit"></i>
				<span>내 스터디</span>
			</el-menu-item>
			<el-menu-item index="2" v-model="auth" v-if="auth == '1' | auth == '0' "  @click="scheduleClick(memSeq)">
				<i class="el-icon-postcard"></i>
				<span>내 일정</span>
			</el-menu-item>
			<el-menu-item index="3" v-model="auth"  @click="resumeClick(memSeq)">
				<i class="el-icon-document"></i>
				<span>내 이력서</span>
			</el-menu-item>
			<el-menu-item index="4" v-model="auth" v-if="auth == '1' | auth == '0' "  @click="interclick(memSeq)">
				<i class="el-icon-star-off"></i>
				<span>내 관심분야</span>
			</el-menu-item><!-- $router.push({name:'MyInfo', params: { seq: memSeq }}), show = false" -->
			<el-menu-item index="5" v-model="auth"  @click="infoClick(memSeq)" >
				<i class="el-icon-setting"></i>
				<span>내 정보수정</span>
			</el-menu-item>
			</el-menu>
	 </div>
	 <div class="myContent" >
		<MyStudy v-if="study">
		</MyStudy>
		<MySchedule v-if="schedule">
		</MySchedule>
		<MyResume v-if="resume">
		</MyResume>
		<MyInter v-if="inter"
		:memSeq="memSeq"
		:myinter="myinter"
		:myBig="myBig"
		:mySmall="mySmall"
		@updateInterArea="updateInterArea">
		</MyInter>
		<MyInfo v-if="info"
		:memSeq="memSeq"
		:myinfo="myinfo"
		@emailUpdate="emailUpdate"
		@addrUpdate="addrUpdate"
		@passUpdate="passUpdate">
		</MyInfo>
	 </div> 
	 <!-- <div class="myContent" v-else>
		 	<router-view :key="$route.fullPath">
			</router-view> 
	 </div> -->
  </div>
</template>

<script>
import MyStudy from '@/views/MyPage/myStudy.vue'
import MySchedule from '@/views/MyPage/mySchedule.vue'
import MyResume from '@/views/MyPage/myResume.vue'
import MyInter from '@/views/MyPage/myInter.vue'
import MyInfo from '@/views/MyPage/myInfo.vue'
export default {
	components:{
		MyStudy, MySchedule, MyResume, MyInter, MyInfo
	},
	data(){
		return{
			study: false,
			schedule:false,
			resume:false,
			inter:false,
			info:true,
			myinfo: [],
			myinter:[],
         	myBig: [],
          	mySmall: [],
			name: '',
			memSeq: '',
			auth: '',
		}
	}, 
	 mounted(){
		 var loginData = sessionStorage.getItem("loginUser");
		 var login = JSON.parse(loginData); 
		 this.name = login.memberName
		 this.memSeq = login.memberSeq
		 this.auth = login.auth
		 var params = new URLSearchParams();
		 params.append('memberSeq', this.memSeq)
		 axios.post('http://localhost:9000/myPageInfomation', params)
		 .then(res => {
		    this.myinfo = res.data
		  }) 
	}, 
	methods:{
		studyClick(){
			this.study = true
			this.schedule = false
			this.resume = false
			this.inter = false
			this.info = false
		},
		scheduleClick(){
			this.study = false
			this.schedule = true
			this.resume = false
			this.inter = false
			this.info = false
		},
		resumeClick(){
			this.study = false
			this.schedule = false
			this.resume = true
			this.inter = false
			this.info = false
		},
		interclick(memSeq){
			this.study = false
			this.schedule = false
			this.resume = false
			this.inter = true
			this.info = false
			var params = new URLSearchParams();
			params.append('memberSeq', memSeq)
			axios.post('http://localhost:9000/myPageInter', params)
			.then(res => {
				var smyinfo = res.data
				this.myinter = smyinfo
				
				var bg = smyinfo.interBigDto
				var sm = smyinfo.interSmallDto
			
				var big;
				big = JSON.stringify(bg)
				big = JSON.parse(big)
				this.myBig = big

				var small;
				small = JSON.stringify(sm)
				small = JSON.parse(small)
				this.mySmall = small
			}) 
		},
		infoClick(memSeq){
			this.study = false
			this.schedule = false
			this.resume = false
			this.inter = false
			this.info = true		
			var params = new URLSearchParams();
			params.append('memberSeq', memSeq)
			axios.post('http://localhost:9000/myPageInfomation', params)
			.then(res => {
			this.myinfo = res.data
			 }) 
		},
		emailUpdate(memSeq){
			var params = new URLSearchParams();
			params.append('memberSeq', memSeq)
			axios.post('http://localhost:9000/myPageInfomation', params)
			.then(res => {
				this.myinfo = res.data
			 }) 
		},
		addrUpdate(memSeq){
			var params = new URLSearchParams();
			params.append('memberSeq', memSeq)
			axios.post('http://localhost:9000/myPageInfomation', params)
			.then(res => {
				this.myinfo = res.data
			 }) 
		},
		passUpdate(memSeq){
			var params = new URLSearchParams();
			params.append('memberSeq', memSeq)
			axios.post('http://localhost:9000/myPageInfomation', params)
			.then(res => {
				this.myinfo = res.data
			 }) 
		},
		updateInterArea(memSeq){
			var params = new URLSearchParams();
			params.append('memberSeq', memSeq)
			axios.post('http://localhost:9000/myPageInter', params)
			.then(res => {
				this.myinter = res.data
			})  
		},
	}
}
</script>

<style scoped>
.myPageWrap{
	min-height: 120vh;
}

.mymenu{
	float: left;
	width: 15%;
}
.myContent{
	float: left;
	width: 85%;
	padding-left: 25px;
}
.el-menu-item:hover{
	background-color: #fbfbfb;
}
.el-menu-item:focus{
	background-color: #fbfbfb;
}

</style>