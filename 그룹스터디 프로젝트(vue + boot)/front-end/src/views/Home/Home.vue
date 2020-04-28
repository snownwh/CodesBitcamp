<template>
  <div class="home">
    <div id="banner">
      <div class="slide">
        <ul>
          <li><img src="../../assets/css/images/welcome1.jpg"></li>
          <li><img src="../../assets/css/images/welcome2.jpg"></li>
          <li><img src="../../assets/css/images/welcome3.jpg"></li>
          <li><img src="../../assets/css/images/welcome4.jpg"></li>
        </ul>
      </div>
    </div>
    <!-- 그룹 스터디 -->
    <h3>Group Study</h3>
    <div>
      <router-link :to="{ name : 'University'}">대학생/취업</router-link> |
      <router-link :to="{ name : 'Official'}">공무원/임용</router-link> |
      <router-link :to="{ name : 'Language'}">어학/회화</router-link> |
      <router-link :to="{ name : 'Life'}">라이프/취미</router-link>
    </div>
    <router-view class="viewOne" name="one"></router-view>
    <div class="hr_div"/>
    <!-- 취업 정보 -->
    <h3>취업</h3>
    <div>
      <router-link :to="{ name : 'University'}">구인 공고</router-link>
    </div>
      <router-view class="viewTwo" name="two"></router-view>
    <div class="hr_div"/>
    <!-- 게시판 -->
    <h3>Notice</h3>
    <div>
      <router-link :to="{ name : 'University'}">대학생/취업</router-link> |
      <router-link :to="{ name : 'Official'}">공무원/임용</router-link> |
      <router-link :to="{ name : 'Language'}">어학/회화</router-link> |
      <router-link :to="{ name : 'Life'}">라이프/취미</router-link>
    </div>
    <router-view class="viewThree" name="three"></router-view>
    <div class="hr_div"/>

  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/views/Home/HelloWorld.vue'

export default {
  name: 'Home',
  components: {
    HelloWorld
  },

}
</script>

<style scoped>

h3{
  margin: 10px;
}

.hr_div{
  margin: auto;
  margin-top: 30px;
  margin-bottom: 70px;
  background: #9e9e9e;
  height: 1px;
  width: 90%;
}

.slide{height:400px;overflow:hidden;position:relative;}
.slide li{position:absolute;left:0;right:0;top:0;bottom:0;opacity:0;transition:1s;animation:fade 16s infinite;}
.slide li:nth-child(1){background:#ffa;animation-delay:0s}
.slide li:nth-child(2){background:#faa;animation-delay:4s}
.slide li:nth-child(3){background:#afa;animation-delay:8s}
.slide li:nth-child(4){background:#aaf;animation-delay:12s}
.slide input{display:none;}
.slide .bullet{position:absolute; top:370px;left:0;right:0;text-align:center; z-index:10;}
.slide .bullet label{width:10px;height:10px;border-radius:10px;border:2px solid #666;display:inline-block;background:#fff;font-size:0;transition:0.5s;cursor:pointer;}
/* 슬라이드 조작 */
#pos1:checked ~ ul li:nth-child(1),
#pos2:checked ~ ul li:nth-child(2),
#pos3:checked ~ ul li:nth-child(3),
#pos4:checked ~ ul li:nth-child(4){opacity:1;}
/* bullet 조작 */
#pos1:checked ~ .bullet label:nth-child(1),
#pos2:checked ~ .bullet label:nth-child(2),
#pos3:checked ~ .bullet label:nth-child(3),
#pos4:checked ~ .bullet label:nth-child(4){background:#666;}

@keyframes fade {
  0% {opacity:0;}
  5% {opacity:1;}
  25% {opacity:1;}
  30% {opacity:0;}
  100% {opacity:0;}
}

img{width:1030px; height:400px}

</style>
