<template>
  <div class="about">
    <div class="introduce">
      <span class="orange bold">PALETTE는</span>
      <br>
      <span class="bold">개인 스터디 일정 관리</span>와
      <br>
      각양각색의
      <span class="bold">리더</span>와 함께하는
      <br>
      <span class="bold">오프라인 스터디 매칭 플랫폼</span>입니다.
    </div>
    <hr>
    <section class="advantage-section">
      <div class="section-content">
        <div class="question">
          
          <span class="highlight">PALETTE,</span>
          <br>
          어떤 점이 좋나요?
        </div>
        <ul class="advantage-list">
          <li class="advantage type-1">
            <div class="advantage-icon"><img style="height:110px" src="../../assets/css/images/about3.png"></div>
            <div class="title">내게 꼭 맞는 수준별 스터디</div>
            <div class="description">
              입문부터 고급까지<br>
              세부 레벨로 진행되는 스터디에요 !
            </div>
          </li>
          <li class="advantage type-2">
            <div class="advantage-icon2"><img style="height:110px" src="../../assets/css/images/about1.png"></div>
            <div class="title">최대 6~8명의 소수 정예</div>
            <div class="description">
              도란도란 정예 멤버로<br>
              꼭 맞는 사람들끼리 시작해요 :)
            </div>
          </li>
          <li class="advantage type-3">
            <div class="advantage-icon"><img style="height:110px" src="../../assets/css/images/about2.png"></div>
            <div class="title">나만의 자료실</div>
            <div class="description">
              나만의 자료실에<br>
              저장해 보아요~
            </div>
          </li>
          
        </ul>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  data(){
    return{

    }
  },

  mounted(){
    this.$store.state.currpage = this.$route.path
  }
}
</script>

<style>
div{display: block;}
ul {
  /* display: block;
  list-style: none;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px; */
  /* padding-inline-start: 40px; */
  }


.about {height: 100vh;}
.introduce {margin: 72px auto 4pc; width:60pc; color:#484b53; font-size:38px; font-weight:200; line-height:1.48em}
.orange {color:#ff6c00}
.bold {font-weight:700}
.advantage-section {padding:40pt 0 168px; background:#fff}
.section-indent {margin:0 auto; overflow: hidden; width:60pc;}
.question {font-size:38px; font-weight: 200; line-height: 1.38em;  margin-left:20px}
.highlight {color:#ef6c00; font-weight: 700;}
.advantage-list {margin-top: 81pt}
.advantage {display: inline-block; vertical-align: top;}
.type-1 {width:34%;}
.type-2 {width:34%;}
.type-3 {width:32%;}
.advantage-icon {
    width: 72px;
    height: 72px;
    
    background-position-y: -86px;
  }
.advantage-icon2 {
    width: 72px;
    height: 72px;
    background-position-y: -86px;
  }
.advantage-icon3 {
    width: 72px;
    height: 72px;
    background-position-y: -86px;
  }
.title{margin: 36px 0 24px; color: #3c3c3c; font-size:18pt; font-weight: 600;}
.description{color:#777b7e; font-size:18px; line-height: 1.6em;}
</style>