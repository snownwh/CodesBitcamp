<template>
    
  <div id="sri_layout_popup" style="height: 100%">
    <section id="sri_section">
      <div id="sri_wrap">
        <div id="content">
          <div class="jview_progress" style="display: none;">
            <div class="row"></div>
          </div>
          <div class="wrap_jview" v-loading="loading">
            <section class="jview">
              <div class="wrap_jv_cont">
                <div class="wrap_jv_header">
                   <div class="jv_header">
                    <a :href="getOneRecruit1.webUrl" class="company" target="_blank">
                      <!-- 회사명 -->
                      {{ this.$store.state.s_employment.getOneRecruit.memberDto.memberName}}
                    </a>
                    <button type="button" title="관심기업 등록" class="btn_jview spr_jview btn_interest" onclick="#" onmousedown="#" first_nudge="off">
                      <span>관심기업 등록</span>
                    </button>
                    <h1 class="tit_job">
                        <!-- 글제목 -->
                      {{getOneRecruit1.title}}
                    </h1>
                    <div class="btn_apply">
                      <span class="dday">D-{{getOneRecruit1.dDay}}</span>
                      <a class="sri_btn_lg for_btn_event" title="홈페이지 지원" :href="getOneRecruit1.webUrl" target="_blank" rel="nofollow" onclick="try{Saramin.updateHomepageLink(37953815, &quot;homepage&quot;);} catch(e) {}" onmousedown="try{n_trackEvent('homepage_apply', 'view', '37953815')}catch(e){}">
                        <span class="sri_btn_homepage_apply">홈페이지 지원</span>
                      </a>
                    </div>
                  </div>
                </div>
                <div class="jv_cont jv_summary">
                  <div class="jv_title blind">핵심 정보</div>
                  <div class="cont">
                    <div class="col">
                      <dl>
                        <dt>경력</dt>
                        <dd><strong>{{getOneRecruit1.career}}</strong></dd>
                        
                      </dl>            
                      <dl>
                        <dt>학력</dt>
                        <dd><strong>{{getOneRecruit1.education}}</strong></dd>
                      </dl>                
                      <dl>
                        <dt>근무형태</dt>
                        <dd><strong>{{getOneRecruit1.workingType}}</strong></dd>
                      </dl>
                    </div>
                    <div class="col">
                      <dl>
                        <dt>급여</dt>
                        <dd>{{getOneRecruit1.salary}}</dd>
                      </dl>
                      <dl>
                        <dt>직급/직책</dt>
                        <dd>{{getOneRecruit1.position}}</dd>
                      </dl>
                      <dl>
                        <dt>근무지역</dt>
                        <dd>{{getOneRecruit1.workingLocation}}</dd>
                      </dl>
                    </div>
                    <ul class="meta">
                      <li>조회수 <strong>{{getOneRecruit1.readCount}}</strong></li>
                      <!-- <li>홈페이지접속 <strong>105</strong></li> -->
                      <!-- <li class="txt_noti">특이사항</li> -->
                    </ul>
                  </div>
                </div>
                <div class="jv_cont jv_detail">
                  <div class="jv_title blind">상세요강</div>
                    <div class="cont">
                      <div class="output ql-snow">
                        <div class="ql-editor" v-html="getOneRecruit1.content"></div>
                    </div>
                      
                    </div>
                    
                  </div>
                  <div class="jv_cont jv_howto">
                    <div class="jv_title">접수기간 및 방법</div>
                    <div class="cont box">
                      <div class="status">
                        <div class="info_timer" data-remain-time="1429051">
                          <span class="txt">남은 기간</span>
                          <span class="day">{{getOneRecruit1.dDay}}</span>
                          <span class="txt_day">일</span>
                          <span v-show=false class="time">{{dTime}}</span>
                          <span class="time">{{final}}</span>
                        </div>
                        <dl class="info_period">
                          <dt>시작일</dt>
                          <!-- <dd>{{getOneRecruit.cvStartDate}</dd> -->
                          <dd>{{startDate}}</dd>
                          <dt class="end">마감일</dt>
                          <dd>{{endDate}}</dd>
                        </dl>
                      </div>
                        <dl class="guide">
                          <dt>지원방법</dt>
                          <dd class="method">
                            <a :href="getOneRecruit1.webUrl" onmousedown="#" class="link" target="_blank">홈페이지 지원</a>                
                          </dd>
                        </dl>
                      <p class="noti">마감일은 기업의 사정, 조기마감 등으로 변경될 수 있습니다.</p>
                  </div>
                </div>
                <div v-if="login1.memberSeq === getOneRecruit1.memberSeq">
                    <el-button type="primary" round @click="recruitUpdate(getOneRecruit1.boardSeq)">수정</el-button>
                    <el-button type="primary" round @click="recruitDelete(getOneRecruit1.boardSeq)">삭제</el-button>
                </div>
            </div>
            </section>
          </div> 
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import Vue from "vue"
import moment from "moment"
import VueMomentJS from "vue-momentjs"
import 'element-ui/lib/theme-chalk/index.css';
import { loading } from 'element-ui';
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'

Vue.use(VueMomentJS, moment)

export default {
  data(){
    return{
      empBoardSeq : "",
      getOneRecruit1 : "",
      nowDate : this.$moment(new Date()).format('YYYY.MM.DD HH:mm:ss'),
      startDate : "",
      endDate : "",
      final:"",
      login1:[],
      loading: true,
    }
  },
  mounted(){
     
    // 구인공고 리스트에서 공고를 클릭시 게시판seq를 파라미터로 가지고 와서 DTO를 불러와서 getOneRecruit에 담는다.
    this.loading = true
    this.empBoardSeq = this.$route.params.boardId
      var params = new URLSearchParams()
      params.append("empBoardSeq", this.empBoardSeq)
      axios.post("http://localhost:9000/getOneRecruit", params)
          .then(res =>{
            
            this.$store.state.s_employment.getOneRecruit = res.data
            this.getOneRecruit1 = this.$store.state.s_employment.getOneRecruit
            this.startDate = this.$moment(res.data.cvStartDate).format('YYYY.MM.DD HH:mm')
            this.endDate = this.$moment(res.data.cvEndDate).format('YYYY.MM.DD HH:mm')
            this.loading = false
      })

  },
  computed:{
    //디데이 함수 setInterVal로 호출
    dTime(){
        return setInterval(() => this.dTimer(), 1000)
    }
  },
  methods:{
    //디데이 시간 함수
    dTimer(){
        var now  = this.$moment(new Date()).format('YYYY.MM.DD HH:mm:ss');
        var then = this.endDate;
        
        // console.log(moment.utc(moment(then,"YYYY.MM.DD HH:mm:ss").diff(moment(now,"YYYY.MM.DD HH:mm:ss"))).format("HH:mm:ss"))
        this.final = moment.utc(moment(then,"YYYY.MM.DD HH:mm:ss").diff(moment(now,"YYYY.MM.DD HH:mm:ss"))).format("HH:mm:ss")
    },
    recruitUpdate(seq){
        this.$router.push({
        path: "/recruitUpdate/" + seq
        })
    },
    recruitDelete(seq){
        const deleteCheck = confirm("정말로 이 글을 삭제하시겠습니까?")
        if(deleteCheck){
            var params = new URLSearchParams()
            params.append("empBoardSeq", seq)
            axios.post("http://localhost:9000/recruitDelete", params)
                .then(res =>{
                    if(res.data === true){
                        alert("성공적으로 삭제되었습니다.")
                        this.$router.push({
						name: 'recruiting'
					    })
                    } else {
                        alert("삭제를 실패하였습니다.")
                        this.$router.push({
						name: 'recruiting'
					    })
                    }
              })    
        } else {
            return
        }

    }

  },
    
  created(){
		let sMemberSeq = sessionStorage.getItem("loginUser")
		this.login1 = JSON.parse(sMemberSeq)
		// this.memberSeq = this.$store.state.loginUser.memberSeq
	}
}



</script>
<style lang="scss" scoped>

  .example {
    display: flex;
    flex-direction: column;

    .editor {
      height: 40rem;
      overflow: hidden;
    }

    .output {
      width: 100%;
      height: 20rem;
      margin: 0;
      border: 1px solid #ccc;
      overflow-y: auto;
      resize: vertical;

      &.code {
        padding: 1rem;
        height: 16rem;
      }

      &.ql-snow {
        border-top: none;
        height: 24rem;
      }

      
    }
  }
</style>
<style scoped>






/* reset & common class*/
html {
    color: #000;
    background: #fff
}

html, body {
    width: 100%;
    height: 100%;
    -webkit-text-size-adjust: none
}

body, div, dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, h6, pre, code, form, fieldset, legend, input, textarea, p, blockquote, button {
    margin: 0;
    padding: 0;
    font-family: "Malgun Gothic", dotum, gulim, sans-serif
}

table {
    border-spacing: 0;
    border-collapse: collapse;
    font-family: "Malgun Gothic", dotum, gulim, sans-serif;
    font-size: inherit;
    line-height: 100%
}

fieldset, img {
    border: 0
}

address, caption, cite, code, dfn, em, strong, th, var {
    font-weight: normal;
    font-style: normal
}

strong, b {
    font-weight: bold
}

ol, ul, li {
    list-style: none
}

caption, th {
    text-align: left
}

h1, h2, h3, h4, h5, h6 {
    font-size: 100%;
    font-weight: normal
}

q:before, q:after {
    content: ""
}

del, ins {
    text-decoration: none
}

abbr, acronym {
    border: 0;
    font-variant: normal
}

sup {
    vertical-align: text-top
}

sub {
    vertical-align: text-bottom
}

legend {
    color: #000
}

a {
    color: #444;
    text-decoration: none
}

header, footer, section, nav, article {
    display: block
}

input, button, textarea, select, optgroup, option {
    font-family: inherit;
    font-size: inherit;
    font-weight: inherit;
    font-style: inherit
}

input, button, textarea, select {
    line-height: normal
}

button {
    border: 0;
    background: transparent;
    cursor: pointer
}

body {
    position: relative;
    padding-top: 60px;
    min-width: 1004px;
    box-sizing: border-box
}

body#sri_layout_popup {
    padding-top: 0
}

#sri_header {
    position: fixed;
    top: 0;
    left: 0;
    z-index: 100;
    width: 100%;
    min-width: 1260px;
    height: 60px;
    border-bottom: 1px solid rgba(0, 0, 0, 0.07);
    box-sizing: border-box;
    background: #4876EF
}

body#sri_layout_popup #sri_header {
    display: none
}

#sri_header:after {
    display: table;
    clear: both;
    content: "";
    table-layout: fixed
}

#sri_header.narrow {
    min-width: 900px
}

#sri_header.narrow .wrap_nav.primary {
    display: none
}

#sri_section {
    display: table;
    overflow: hidden;
    position: relative;
    z-index: 1;
    width: 100%;
    height: 100%;
    box-sizing: border-box
}

body.has_lnb #sri_section {
    padding-left: 220px
}

#sri_lnb_wrap {
    position: fixed;
    top: 60px;
    bottom: 0;
    left: 0;
    z-index: 20;
    width: 220px;
    border-right: 1px solid #e7e7e8;
    box-sizing: border-box;
    background: #fcfcfc
}

#sri_lnb_wrap.tablet_support {
    position: absolute;
    top: 0
}

#sri_lnb_wrap .viewport {
    overflow: hidden;
    position: relative;
    z-index: 1;
    width: 100%;
    height: 100%
}

#sri_lnb_wrap .overview {
    position: absolute;
    top: 0;
    left: 0;
    margin: 0;
    padding: 0;
    width: 100%;
    list-style: none
}

#sri_lnb_wrap .scrollbar {
    position: absolute;
    top: 0;
    right: 0;
    z-index: 2;
    width: 6px;
    transition: opacity 0.5s linear;
    opacity: 0
}

#sri_lnb_wrap .track {
    position: relative;
    width: 100%;
    height: 100%;
    background: #fcfcfc
}

#sri_lnb_wrap .thumb {
    overflow: hidden;
    position: absolute;
    top: 0;
    left: 0;
    width: 6px;
    height: 20px;
    background: #f2f2f3;
    cursor: pointer
}

#sri_lnb_wrap .thumb .end {
    overflow: hidden;
    width: 100%;
    height: 5px;
    background: #f2f2f3
}

#sri_lnb_wrap .disable {
    display: none
}

#sri_lnb_wrap:hover .scrollbar {
    opacity: 1
}

#sri_lnb_wrap .btn_fold {
    display: block;
    position: absolute;
    top: 0;
    right: -30px;
    width: 30px;
    height: 51px;
    border: 1px solid #cbcbcb;
    box-sizing: border-box;
    background: rgba(000, 000, 000, .17)
}

#sri_lnb_wrap .btn_fold.folded .ic_open {
    background-position: -50px 0
}



#sri_footer {
    display: table-row;
    width: 100%;
    height: 1px
}

body#sri_layout_popup #sri_footer {
    display: none
}

/* 팝업뷰 */
#sri_layout_popup {
    min-width: 960px
}

#sri_layout_popup .wrap_jview {
    padding: 0
}

#sri_layout_popup .jv_remote {
    top: 39px;
    margin-left: -549px
}

#sri_layout_popup .jv_remote.shrink {
    margin-left: -472px
}

#sri_layout_popup .jv_remote.fix_pop {
    top: 99px
}

#sri_layout_popup .jview_floating .jv_header_float {
    margin-left: -435px
}

/* 인쇄 */
@page {
    margin: 60px 40px;
    size: A4
}

body#sri_layout_popup.printing #content {
    background: #fff
}

body#sri_layout_popup.printing .main_nudge, body#sri_layout_popup.printing .jview .btn_tooltip, body#sri_layout_popup.printing .jview .btn_link, body#sri_layout_popup.printing .jview .btn_move_map, body#sri_layout_popup.printing .jview .btn_interest, body#sri_layout_popup.printing .jview .btn_share, body#sri_layout_popup.printing .jview .wrap_share, body#sri_layout_popup.printing .jv_summary .btn_modify, body#sri_layout_popup.printing .jv_footer .btn_tags, body#sri_layout_popup.printing .jv_cont .btn_more_cont {
    display: none !important
}

body#sri_layout_popup.printing .toolTipWrap {
    display: block;
    margin: 0;
    padding: 2px 0
}

body#sri_layout_popup.printing .toolTip {
    display: block;
    position: static;
    margin: 0;
    padding: 0;
    width: auto;
    border: 0;
    color: inherit;
    background: none;
    box-shadow: none
}

body#sri_layout_popup.printing .toolTip .tail, body#sri_layout_popup.printing .toolTip .btnClose {
    display: none
}

body#sri_layout_popup.printing .jview .toolTip ul, body#sri_layout_popup.printing .jview .toolTip .toolTipCont {
    font-size: 12px
}

body#sri_layout_popup.printing .jview .toolTip ul > li, body#sri_layout_popup.printing .jview .toolTip ul > li span {
    line-height: 18px
}

body#sri_layout_popup.printing .jv_cont .layer_group {
    overflow: visible;
    position: static
}

body#sri_layout_popup.printing .jv_cont .layer_group .layer {
    max-height: none
}

body#sri_layout_popup.printing .jv_cont .layer_group .layer .cont_more {
    display: block
}

body#sri_layout_popup.printing .jv_footer .cont {
    padding-top: 0
}

body#sri_layout_popup.printing .jv_footer .cont .tags {
    overflow: visible;
    position: static;
    padding: 9px 0;
    height: auto
}

#content {
    position: relative;
    z-index: 1;
    margin: 44px auto 80px auto;
    width: 960px
}

#sri_section.has_banner #content {
    padding-right: 160px;
    min-height: 960px
}

#sri_wrap {
    display: table-row;
    width: 100%;
    height: 100%
}

.wrap_nav ul.nav .wrap_depth2 .content {
    width: 750px
}

/* default layout override */
body {
    min-width: 1260px
}

#sri_header {
    position: absolute
}

#content {
    margin-top: 20px;
    padding: 39px 0 40px;
    width: 100%;
    min-height: 2000px;
    background: #f1f3f9
}

button {
    border: 0;
    background: none
}

.clipboard_dummy {
    overflow: hidden;
    position: fixed;
    top: -1px;
    left: -1px;
    width: 1px;
    height: 1px
}

.jview .enc_mail {
    display: none
}

#header > .inner.fixed_script {
    position: absolute !important
}

/* 프로그레스 바 */
.jview_progress {
    display: none;
    position: fixed;
    top: 0;
    left: 0;
    z-index: 11;
    width: 100%;
    height: 4px;
    background: #e5e6eb
}

.jview_progress .row {
    display: block;
    width: 100%;
    height: 4px;
    border: 0;
    background: #5b76f5;
    -webkit-transform: scaleX(0);
    -ms-transform: scaleX(0);
    transform: scaleX(0);
    -webkit-transform-origin: 0 0;
    -moz-transform-origin: 0 0;
    -ms-transform-origin: 0 0;
    -o-transform-origin: 0 0;
    transform-origin: 0 0;
    will-change: transform
}

/* 공고뷰 컨텐츠 */
.wrap_jview {
    position: relative;
    z-index: 1;
    margin: 0 auto;
    padding-right: 300px;
    width: 960px
}

/* 공통 요소들 */
.spr_jview:before, .spr_jview:after {
    display: inline-block;
    vertical-align: top;
    background: url(//www.saraminimage.co.kr/sri/person/jobs_view/spr_jview_190208.png) no-repeat
}

.jview .btn_jview {
    display: inline-block;
    padding: 0 9px 3px;
    height: 28px;
    border: 1px solid #e4e4e4;
    box-sizing: border-box;
    font-size: 0;
    vertical-align: top;
    background-color: #fff
}

.jview a.btn_jview {
    line-height: 23px
}

.jview .btn_jview span {
    color: #666;
    font-size: 13px;
    font-weight: normal;
    letter-spacing: -1px
}

.jview .jv_header .btn_jview span {
    color: #888
}

.jview .btn_jview strong {
    color: #666;
    font-size: 13px;
    font-weight: bold;
    letter-spacing: -1px
}

.jview .btn_interest:before {
    margin: 3px 5px 0 0;
    width: 15px;
    height: 13px;
    background-position: 0 0;
    content: ""
}

.jview .btn_interest.on:before {
    background-position: -30px 0
}

.jview .btn_print:before {
    margin: 3px 5px 0 0;
    width: 15px;
    height: 15px;
    background-position: -100px -70px;
    content: ""
}

.jview .btn_share:before {
    margin: 3px 5px 0 0;
    width: 12px;
    height: 13px;
    background-position: -80px -70px;
    content: ""
}

.jview .btn_scrap {
    display: block;
    padding: 11px 0 7px;
    width: 60px;
    height: 60px;
    border: 1px solid #e9e9e9
}

.jview .btn_scrap .txt_scrap {
    display: block;
    padding-top: 24px;
    width: 100%;
    height: 100%;
    color: #949494;
    box-sizing: border-box;
    font-size: 12px;
    letter-spacing: 0;
    line-height: 14px
}

.jview .btn_scrap .txt_scrap:before {
    position: absolute;
    left: 50%;
    top: 11px;
    margin-left: -10px;
    width: 19px;
    height: 19px;
    background-position: -60px 0
}

.jview .btn_scrap.on .txt_scrap:before {
    background-position: -60px -30px
}

.jview .btn_link:after {
    margin: 8px 0 0 8px;
    width: 6px;
    height: 9px;
    background-position: 0 -150px;
    content: ""
}

.jview .sri_btn_lg {
    display: block;
    width: 185px
}

.jview .sri_btn_lg span {
    width: 183px
}

.jview .share {
    display: inline-block;
    position: relative;
    margin-left: 4px;
    vertical-align: middle
}

.jview .share .list_share {
    display: none;
    position: absolute;
    top: 38px;
    left: 50%;
    margin-left: -62px;
    padding: 2px;
    width: 123px;
    height: 30px;
    border: 1px solid #eee;
    background-color: #fff
}

.jview .share .list_share:before {
    position: absolute;
    top: -7px;
    left: 58px;
    width: 11px;
    height: 7px;
    background-position: -60px -20px;
    content: ""
}

.jview .share .list_share:after {
    position: absolute;
    top: -20px;
    left: -1px;
    width: 129px;
    height: 20px;
    background: none;
    content: ""
}

.jview .share .list_share li {
    float: left;
    border-left: 1px solid #eee
}

.jview .share .list_share .spr_jview {
    display: block;
    padding: 4px;
    width: 30px;
    height: 30px;
    box-sizing: border-box
}

.jview .share .list_share li:first-child {
    border-left: 0
}

.jview .share .list_share .spr_jview:after {
    display: block;
    width: 22px;
    height: 22px;
    content: ""
}

.jview .share .list_share .facebook:after {
    background-position: 0 -40px
}

.jview .share .list_share .twitter:after {
    background-position: -30px -40px
}

.jview .share .list_share .copy_url:after {
    background-position: -60px -40px
}

.jview .share .list_share .send_sms:after {
    background-position: -90px -40px
}

.jview .share:hover .list_share {
    display: block
}

.jview {
    position: relative
}

.jview + .jview {
    margin-top: 29px
}

.jview > .wrap_jv_cont {
    position: relative;
    padding: 40px 44px 100px;
    border: 1px solid #e5e6eb;
    background: #fff
}

.jview > .jv_ads {
    overflow: hidden;
    margin: 29px auto 0;
    width: 728px;
    height: 90px
}

.jview a + .toolTipWrap, .jview span + .toolTipWrap, .jview strong + .toolTipWrap {
    margin-left: 6px
}

.jview .toolTipWrap + .toolTipWrap {
    margin-left: 4px
}

.jview .toolTipWrap.opened .toolTip {
    display: block
}

.jview .toolTip .toolTipCont {
    font-size: 12px
}

.jview .toolTip .toolTipTit, .jview .toolTip p {
    color: #666;
    word-break: break-all
}

.jview .toolTip ul {
    display: block;
    font-size: 12px
}

.jview .toolTip ul > li {
    position: relative;
    margin-top: 6px;
    padding-left: 66px;
    min-height: 16px;
    color: #666;
    line-height: 16px
}

.jview .toolTip ul > li:first-child {
    margin-top: 0
}

.jview .toolTip ul > li > span {
    display: block;
    position: absolute;
    top: 0;
    left: 0;
    width: 66px;
    color: #888;
    line-height: 16px
}

.jview .toolTip .txt_noti {
    margin-top: 10px;
    padding: 8px 15px 10px;
    color: #949494;
    font-size: 11px;
    letter-spacing: -1px;
    line-height: 17px;
    background-color: #f9f9f9
}

.jview .scroll {
    scrollbar-3dLight-Color: #fff;
    scrollbar-arrow-color: #e5e6eb;
    scrollbar-base-color: #fff;
    scrollbar-Face-Color: #e5e6eb;
    scrollbar-Track-Color: #fff;
    scrollbar-DarkShadow-Color: #fff;
    scrollbar-Highlight-Color: #fff;
    scrollbar-Shadow-Color: #fff
}

.jview .scroll::-webkit-scrollbar {
    width: 6px;
    height: 6px
}

.jview .scroll::-webkit-scrollbar-track {
    background: #fcfcfc
}

.jview .scroll::-webkit-scrollbar-thumb {
    background: #f2f2f3
}

.wrap_jv_header {
    padding-bottom: 30px;
    min-height: 75px
}

.wrap_jv_header + .jv_cont {
    margin-top: 0
}

.jv_header .company {
    display: inline-block;
    overflow: hidden;
    margin-right: 8px;
    padding-right: 2px;
    max-width: 380px;
    height: 28px;
    font-size: 18px;
    font-weight: normal;
    line-height: 23px;
    text-overflow: ellipsis;
    vertical-align: middle;
    white-space: nowrap
}

.jv_header .tag + .company {
    max-width: 420px
}


















































































.hidden, .blind {
    overflow: hidden;
    clip: rect(1px, 1px, 1px, 1px);
    position: absolute !important;
    width: 1px;
    height: 1px
}

.ellipsis {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap
}

.noSelect {
    -webkit-user-select: none;
    -moz-user-select: none;
    user-select: none;
    -o-user-select: none;
    -khtml-user-select: none
}

#skipnavigation a {
    position: absolute;
    left: -3000%
}

#skipnavigation a:focus {
    display: block;
    top: 0;
    left: 0;
    z-index: 1000;
    width: 100%;
    height: 30px;
    color: #fff;
    line-height: 30px;
    text-align: center;
    background: #6174d1
}

/* layout */
.sri_lnb_icon {
    display: inline-block;
    vertical-align: top;
    background: url(//www.saraminimage.co.kr/sri/common/layout/sri_lnb_icon_181004.png) no-repeat;
    background-size: 300px 150px
}

.sri_gnb_option {
    display: inline-block;
    vertical-align: middle;
    background: url(//www.saraminimage.co.kr/sri/common/layout/sri_gnb_option_menu.png) no-repeat;
    background-size: 210px 44px
}







#sri_banner {
    position: absolute;
    top: 130px;
    left: 50%;
    margin-left: 440px;
    width: 120px
}

body.has_lnb #sri_banner {
    z-index: 10;
    margin-left: 550px
}

#sri_banner_fixed {
    position: relative;
    z-index: 20;
    width: 120px;
    box-sizing: border-box
}



.sri_ready {
    display: block;
    visibility: hidden;
    overflow: hidden;
    position: fixed;
    top: -1px;
    left: -1px;
    z-index: -1;
    width: 1px;
    height: 1px
}

.sri_dimmed {
    overflow: auto;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.7)
}

.has_lnb .sri_dimmed {
    z-index: 1
}

/* icon */
.sri_lnb_icon.arrow {
    width: 11px;
    height: 28px;
    background-position: -150px 0
}

.sri_lnb_icon.ic_golink {
    width: 6px;
    height: 45px;
    background-position: -250px 0
}

.sri_lnb_icon.ic_open {
    width: 30px;
    height: 49px;
    background-position: 0 0
}

.sri_lnb_icon.btn_recently_del {
    width: 19px;
    height: 26px;
    background-position: 0 -50px
}

.sri_lnb_icon.btn_go_top {
    width: 40px;
    height: 40px;
    background-position: -250px -50px
}

.sri_lnb_icon.ic_up {
    width: 19px;
    height: 12px;
    background-position: -150px -50px
}

.sri_lnb_icon.ic_hot {
    width: 25px;
    height: 12px;
    background-position: -100px -50px
}

.sri_lnb_icon.ic_new {
    width: 13px;
    height: 12px;
    background-position: -200px -50px
}

.sri_lnb_icon.ic_renew {
    width: 13px;
    height: 12px;
    background-position: 0 -100px
}

.sri_lnb_icon.ic_window {
    width: 11px;
    height: 11px;
    background-position: -50px -50px
}

.sri_lnb_icon.ic_company {
    width: 46px;
    height: 14px;
    background-position: -200px 0
}

.sri_gnb_option.ic_log_out {
    margin-right: 6px;
    width: 15px;
    height: 44px;
    background-position: -60px 0
}

.sri_gnb_option.ic_log_in {
    margin-right: 8px;
    width: 17px;
    height: 44px;
    background-position: -120px 0
}

.sri_gnb_option.ic_join {
    margin-right: 8px;
    width: 17px;
    height: 44px;
    background-position: -90px 0
}

.sri_gnb_option.ic_go_arrow {
    margin-left: 10px;
    width: 5px;
    height: 44px;
    background-position: -150px 0
}

.sri_gnb_option.ic_reg {
    margin-right: 6px;
    width: 19px;
    height: 19px;
    background-position: 0 -12px
}

.sri_gnb_option.ic_manage {
    margin-right: 8px;
    width: 17px;
    height: 19px;
    background-position: -30px -12px
}

.sri_gnb_option.ic_talent {
    margin-right: 5px;
    width: 20px;
    height: 20px;
    background-position: -180px -12px
}

.lnb_menu.no_depth .sri_lnb_icon.ic_new, .lnb_menu.no_depth .sri_lnb_icon.ic_renew {
    margin-top: 0;
    vertical-align: middle
}

/* sri_header */
.wrap_logo {
    float: left;
    padding: 9px 0 0 21px
}

.wrap_logo a:hover {
    text-decoration: none
}

.wrap_logo a img {
    vertical-align: top
}

.wrap_logo .title {
    display: inline-block;
    margin: 10px 0 0 4px;
    color: #fff;
    font-size: 23px;
    font-weight: bold;
    letter-spacing: -3px;
    vertical-align: top
}

#sri_header .wrap_header {
    float: right;
    position: relative;
    height: 60px;
    border-bottom: 1px solid rgba(0, 0, 0, 0.07);
    box-sizing: border-box
}

#sri_header .wrap_header:after {
    display: table;
    clear: both;
    content: "";
    table-layout: fixed
}

.wrap_search {
    float: left;
    position: relative;
    margin-right: 15px;
    padding-top: 12px
}

.wrap_search legend {
    clip: rect(1px, 1px, 1px, 1px);
    position: absolute;
    width: 1px;
    height: 1px
}

.wrap_search .box_search {
    position: relative
}

.wrap_search .box_search_input {
    display: inline-block;
    height: 36px;
    border-radius: 36px;
    box-sizing: border-box;
    vertical-align: top;
    background-color: #fff
}

.wrap_search .box_search_input input {
    display: block;
    padding: 8px 37px 8px 14px;
    width: 232px;
    height: 36px;
    border: 0;
    border-radius: 36px;
    box-sizing: border-box;
    color: #444;
    font-size: 13px;
    font-weight: bold;
    letter-spacing: -1px;
    background: none
}

.wrap_search .box_search_input input:focus {
    border: 1px solid #425bd5;
    outline: none
}

.wrap_search .box_search_input input::-webkit-input-placeholder {
    color: #777;
    font-weight: normal
}

.wrap_search .box_search_input input:-ms-input-placeholder {
    color: #777;
    font-weight: normal
}

.wrap_search .box_search_input input::-ms-clear {
    display: none
}

.wrap_search .box_search_input .pseudo_placeholder {
    display: inline-block;
    padding-left: 5px;
    letter-spacing: -1px;
    text-align: center
}

.wrap_search .btn_search {
    display: inline-block;
    position: absolute;
    top: 0;
    right: 0;
    margin-right: 1px;
    padding: 18px;
    width: 17px;
    height: 17px;
    border: 0;
    text-indent: -9999px;
    background: url(//www.saraminimage.co.kr/sri/common/btn/btn_search_new.png) 50% 50% no-repeat
}

.wrap_nav {
    float: left
}

.wrap_nav.primary {
    margin: 0 15px 0 -9px
}

.wrap_nav ul.nav li.depth1 {
    float: left;
    height: 60px;
    vertical-align: top
}

.wrap_nav ul.nav li.depth1 a.depth1_link {
    display: inline-block;
    padding: 0 7px 0 8px;
    height: 60px;
    color: #fff;
    font-size: 15px;
    letter-spacing: -2px;
    line-height: 58px;
    cursor: pointer
}

.wrap_nav ul.nav li.depth1 a.depth1_link:hover {
    display: inline-block;
    padding: 0 7px 0 8px;
    height: 60px;
    color: #fff;
    line-height: 58px;
    _height: 62px;
    text-decoration: underline
}

.wrap_nav ul.nav li.depth1 a.depth1_link.new_window {
    display: inline-block;
    padding-right: 18px;
    background: url(//www.saraminimage.co.kr/sri/common/ico/ico_new_window.png) no-repeat 66px 25px
}

.wrap_nav ul.nav li.depth1.selected a.depth1_link {
    display: inline-block;
    padding: 0 14px;
    height: 42px;
    border-top: 2px solid #364fc7;
    border-right: none;
    border-left: none;
    color: #364fc7;
    font-weight: bold;
    line-height: 40px;
    text-decoration: none;
    background: #f6f6f6
}

.wrap_nav ul.nav li.depth1.selected a.depth1_link:hover {
    padding: 0 14px;
    border-right: none;
    border-left: none;
    background: #fff
}

.wrap_nav ul.nav li.depth1:first-child.selected a.depth1_link {
    padding: 0 14px 0 17px;
    border-right: none;
    border-left: 2px solid #364fc7
}

.wrap_nav ul.nav li.depth1 a.depth1_link .icon {
    position: absolute;
    top: -9px;
    left: 0;
    width: 60px;
    height: 20px;
    color: #425bd5;
    font-size: 0;
    line-height: 20px;
    text-indent: -9999px;
    background: url(//www.saraminimage.co.kr/ui/common/nav/sp_gnb_0406.png) 0 0 no-repeat
}

.wrap_nav ul.nav li.depth1 a.depth1_link .icon_talent_market {
    left: 50%;
    margin-left: -27px;
    width: 54px;
    background: url(//www.saraminimage.co.kr/ui/common/nav/sp_gnb_0328.png) no-repeat 0 -60px
}

.wrap_nav ul.nav li.depth1 a.depth1_link .icon_beta {
    top: -8px;
    left: 50%;
    margin-left: -18px;
    width: 36px;
    height: 16px;
    background-position: 0 -80px
}

.wrap_nav ul.nav .wrap_depth2 {
    display: none;
    position: absolute;
    top: 59px;
    letter-spacing: -1px
}

.wrap_nav ul.nav li.depth1 a.depth1_link.total_menu {
    padding: 0;
    width: 60px;
    background: url(//www.saraminimage.co.kr/sri/common/layout/ic_total_menu.png) no-repeat
}

#recruit-nav .wrap_depth2, #curation-level .wrap_depth2, #public-level .wrap_depth2 {
    right: 0
}

.wrap_nav ul.nav .hover {
    display: block
}

.wrap_nav ul.nav .wrap_depth2 {
    overflow: hidden;
    z-index: 900;
    padding: 14px 0 2px 0;
    border: 1px solid #444;
    font-weight: normal;
    text-align: left;
    background-color: #fff
}

.wrap_nav ul.nav .wrap_depth2 li {
    padding: 0;
    min-height: 20px;
    color: #444;
    font-size: 12px;
    font-weight: normal;
    line-height: 22px
}

.wrap_nav ul.nav .wrap_depth2 li > a {
    display: inline-block;
    font-size: 12px;
    letter-spacing: -1px;
    line-height: 20px;
    vertical-align: top
}

.wrap_nav ul.nav .wrap_depth2 li > a:hover {
    text-decoration: underline
}

.wrap_nav ul.nav .wrap_depth2 li.typ_spc > a {
    color: #425bd5
}

.wrap_nav ul.nav .wrap_depth2 {
    padding: 8px 10px 10px 6px;
    width: 450px
}



.wrap_nav ul.nav .wrap_depth2 .gnb_sub_home {
    position: relative;
    padding: 0 0 8px 20px;
    height: 19px;
    border-bottom: 1px solid #e5e5e5;
    font-size: 14px;
    letter-spacing: -1px;
    line-height: 19px
}

.wrap_nav ul.nav .wrap_depth2 .gnb_sub_home .title_local_home {
    display: inline-block;
    font-size: 14px;
    font-weight: bold
}

.wrap_nav ul.nav .wrap_depth2 .gnb_sub_home .btn_info_search {
    overflow: hidden;
    position: absolute;
    top: -3px;
    right: 18px;
    width: 123px;
    height: 25px;
    text-indent: -3000px;
    background: url(//www.saraminimage.co.kr/ui/common/nav/btn_gnb_info_search.png) no-repeat
}

.wrap_nav ul.nav .wrap_depth2 .gnb_sub_home > div > a {
    font-weight: bold
}

.wrap_nav ul.nav .wrap_depth2 .gnb_sub_home > div > a:hover {
    text-decoration: underline
}

.wrap_nav ul.nav .wrap_depth2 .column.last {
    padding-right: 0;
    background: #fff none
}

.wrap_nav ul.nav .wrap_depth2 .column .tit_depth2_link {
    clear: both;
    margin: 0 0 5px -8px;
    padding-left: 8px;
    height: 16px;
    color: #2d45bf;
    font-size: 12px;
    font-weight: bold;
    letter-spacing: -1px;
    line-height: 16px;
    background: url(//www.saraminimage.co.kr/main/new/bul_point.gif) no-repeat 0 50%
}

.wrap_nav ul.nav .wrap_depth2 .column .tit_depth2_link a {
    color: #2d45bf;
    text-decoration: none
}

.wrap_nav ul.nav .wrap_depth2 .column .tit_depth2_link a:hover {
    text-decoration: underline
}

.wrap_nav ul.nav .wrap_depth2 .separate {
    display: inline-block;
    overflow: hidden;
    margin: 7px 4px 3px 6px;
    width: 1px;
    height: 10px;
    vertical-align: top;
    background-color: #e8e8e8
}

.wrap_nav ul.nav .wrap_depth2 img.icon {
    margin: 5px 0 0 2px;
    vertical-align: top
}

.wrap_nav ul.nav .wrap_depth2 img.icon_new {
    margin: 10px 0 0 4px;
    vertical-align: top
}

.wrap_nav ul.nav .wrap_depth2 .list_column_link {
    margin: 5px 0 14px;
    padding: 2px;
    list-style: none
}

.wrap_nav ul.nav .wrap_depth2 .list_column_link li .area_side {
    float: none;
    position: absolute;
    right: 20px;
    bottom: 10px;
    padding-left: 0;
    width: auto
}

.wrap_nav ul.nav .wrap_depth2 .list_column_link li .area_side img {
    vertical-align: top
}

.wrap_nav ul.nav .wrap_depth2 .list_column_link li .area_side a {
    display: inline-block;
    margin-left: 10px;
    padding: 0 0 0 6px;
    color: #2d45bf !important;
    font-weight: normal !important;
    text-decoration: none !important;
    vertical-align: top;
    background: url(//www.saraminimage.co.kr/main/new/bul_point_small.gif) 0 50% no-repeat
}

.wrap_nav ul.nav .wrap_depth2 .list_column_link li .area_side a:hover {
    text-decoration: underline !important
}

.wrap_nav ul.nav .wrap_depth2 .list_column_link li.separate_img_link {
    margin-top: 5px
}

.wrap_nav ul.nav .wrap_depth2 .list_column_link .reduce_ltp {
    letter-spacing: -1px
}

.wrap_nav ul.nav .wrap_depth2 .visit_mark {
    position: absolute;
    top: 390px;
    left: 615px
}

.wrap_nav ul.nav .wrap_depth2 .rct-lnk {
    display: block;
    margin: -2px 4px 14px -8px
}

.wrap_nav ul.nav .wrap_depth2 .rct-lnk img {
    vertical-align: top
}

.wrap_nav ul.nav li .list_depth3 {
    display: none;
    position: absolute;
    top: 36px;
    left: 193px;
    padding-top: 12px;
    box-sizing: border-box
}

.wrap_nav ul.nav li .list_depth3 li {
    float: left;
    padding-left: 17px;
    width: 190px;
    box-sizing: border-box
}

.wrap_nav ul.nav li .list_depth3 li a {
    display: block;
    font-weight: normal !important;
    text-decoration: none !important
}

.wrap_nav ul.nav li .list_depth3 li a:hover {
    text-decoration: underline !important
}

.wrap_nav ul.nav.typ_etc li.depth1 a.depth1_link {
    color: #bbc7ff
}

.wrap_nav ul.nav.typ_etc li.depth1 a.depth1_link:hover {
    color: #364fc7
}

.wrap_nav ul.nav .depth1.selected .wrap_depth2_type1 {
    display: block
}

.wrap_nav ul.nav .wrap_depth2_type1 {
    display: none;
    position: absolute;
    right: 0;
    left: 0;
    z-index: 10;
    margin-left: -200px;
    width: 900px;
    height: 40px
}

.wrap_nav ul.nav .wrap_depth2_type1 ul {
    background-color: #f6f6f6
}

.wrap_nav ul.nav .wrap_depth2_type1 ul:after {
    display: block;
    clear: both;
    content: ""
}

.wrap_nav ul.nav .wrap_depth2_type1 ul li {
    float: left;
    padding: 0 15px;
    height: 40px;
    box-sizing: border-box;
    font-size: 13px;
    letter-spacing: -1px;
    line-height: 40px;
    text-align: center
}

.wrap_nav ul.nav .wrap_depth2_type1 ul li:hover, .wrap_nav ul.nav .wrap_depth2_type1 ul li.selected {
    border-bottom: 2px solid #4057c9
}

.wrap_nav ul.nav .wrap_depth2_type1 ul li a {
    display: block;
    text-decoration: none
}

.wrap_nav ul.nav .wrap_depth2_type1 ul li.selected a {
    color: #364fc7;
    font-weight: bold
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 {
    padding: 8px 191px 0 0;
    width: 767px
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .gnb_sub_home {
    width: 938px
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .column {
    display: inline-block;
    height: 430px;
    vertical-align: top
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav .title_depth2 {
    padding-left: 20px;
    height: 33px;
    font-size: 14px;
    font-weight: bold;
    line-height: 30px;
    background: #fbfbfb
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav .title_depth2 > a {
    display: block;
    height: 33px;
    background: url(//www.saraminimage.co.kr/main/new/bul_gnb_point_sub.png) no-repeat 149px 12px
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav .title_depth2 > a.typ_hiddenstar {
    background: url(//www.saraminimage.co.kr/main/new/bul_gnb_hiddenstar01.png) 56px 50% no-repeat
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav .title_depth2 > a:hover {
    text-decoration: underline
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav .title_depth2.add_title {
    border-top: 1px solid #e5e5e5
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav li.depth2 {
    float: left;
    width: 191px;
    height: 186px;
    border-right: 1px solid #e5e5e5;
    border-bottom: 1px solid #e5e5e5;
    box-sizing: border-box
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav li.depth2.direct_link_menu {
    position: absolute;
    top: 35px;
    right: 0;
    width: 194px;
    height: 385px;
    border-top: 1px solid #e5e5e5
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav li.depth2.type_long_height {
    height: 198px
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav .depth3 {
    overflow: hidden;
    padding: 6px 5px 6px 20px;
    min-height: 98px;
    box-sizing: border-box
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav .depth3 .ico_tit_category {
    display: inline-block;
    margin: -2px 2px 0 0;
    width: 55px;
    height: 16px;
    color: #fff;
    font-size: 0;
    line-height: 1px;
    vertical-align: middle;
    background: url(//www.saraminimage.co.kr/sri/common/ico/ico_tit_category.png) no-repeat
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav .link_menu .btn_hightschool_saramin {
    display: inline-block;
    margin-top: 7px;
    padding-right: 16px;
    width: 150px;
    height: 31px;
    border: 1px solid #e5e5e5;
    box-sizing: border-box;
    font-size: 13px;
    line-height: 25px;
    text-align: center;
    background: url(//www.saraminimage.co.kr/main/new/bul_gnb_point_sub.png) no-repeat 118px 10px
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav .link_menu .btn_hightschool_saramin span.point {
    color: #778bf5
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav .link_menu .btn_hightschool_saramin:hover {
    color: #555;
    text-decoration: none
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav .depth3 .btn_alba_cokr {
    display: inline-block;
    margin-top: 10px;
    padding-right: 16px;
    width: 150px;
    height: 31px;
    border: 1px solid #e5e5e5;
    box-sizing: border-box;
    font-size: 13px;
    line-height: 25px;
    text-align: center;
    background: url(//www.saraminimage.co.kr/main/new/bul_gnb_point_sub.png) no-repeat 102px 10px
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav .depth3 .btn_alba_cokr:hover {
    color: #555;
    text-decoration: none
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav li.depth2 .banner {
    padding-top: 20px;
    text-align: center
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav .link_menu {
    margin: 0 23px;
    padding: 10px 0 12px;
    border-top: 1px solid #e5e5e5
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav .link_menu:first-of-type {
    padding-top: 6px;
    border-top: 0
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav .link_menu a {
    display: block;
    margin-top: 5px;
    font-size: 13px
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav .link_menu a:first-child {
    margin-top: 0
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav .link_menu a.btn_hightschool_saramin {
    margin-top: 9px
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav .link_menu .new_window:after {
    display: inline-block;
    margin: 4px 0 0 6px;
    width: 11px;
    height: 11px;
    vertical-align: top;
    background: url(//www.saraminimage.co.kr/sri/common/ico/ico_new_window.png) no-repeat 0 -50px;
    content: ""
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav .link_menu a[class * ="ico_"] {
    position: relative;
    padding-left: 24px
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav .link_menu a[class * ="ico_"]:before {
    position: absolute;
    content: ""
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav .link_menu .ico_recommend:before {
    top: -2px;
    left: 0;
    width: 14px;
    height: 22px;
    background: url(//www.saraminimage.co.kr/sri/common/ico/ico_sri_recommend_s.png) no-repeat
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav .link_menu .ico_salary:before {
    top: 4px;
    left: 0;
    width: 19px;
    height: 16px;
    background: url(//www.saraminimage.co.kr/sri/common/ico/ico_company_salary.png) no-repeat
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav .link_menu .ico_talent:before {
    top: 3px;
    left: 2px;
    width: 14px;
    height: 15px;
    background: url(//www.saraminimage.co.kr/sri/common/ico/ico_direct_talent.png) no-repeat
}

.wrap_nav ul.nav #job-category-nav .wrap_depth2 {
    padding: 8px 0 0;
    width: 572px
}

.wrap_nav ul.nav #job-category-nav .wrap_depth2 li a {
    display: block;
    font-size: 12px
}

.wrap_nav ul.nav #job-category-nav .wrap_depth2 .column {
    display: inline-block;
    margin-left: 0;
    padding: 0;
    width: 191px;
    vertical-align: top;
    background: #fbfbfb
}

.wrap_nav ul.nav #job-category-nav .wrap_depth2 .column .list_column_link {
    margin: 0;
    padding: 12px 0;
    border-right: 1px solid #e5e5e5
}

.wrap_nav ul.nav #job-category-nav .wrap_depth2 .column .list_column_link li, .wrap_nav ul.nav #job-category-nav .wrap_depth2 .column .list_column_link li a {
    height: 22px;
    line-height: 20px;
    vertical-align: middle
}

.wrap_nav ul.nav #job-category-nav .wrap_depth2 .column .list_column_link li {
    padding: 0 20px;
    border-top: 1px solid #fbfbfb;
    border-bottom: 1px solid #fbfbfb;
    background: url(//www.saraminimage.co.kr/main/new/bul_gnb_point_sub01.png) no-repeat 172px -16px
}

.wrap_nav ul.nav #job-category-nav .wrap_depth2 .column .list_column_link li.mhover {
    margin-right: -1px;
    border-top: 1px solid #e5e5e5;
    border-bottom: 1px solid #e5e5e5;
    background-color: #fff;
    background-position: 172px 8px
}

.wrap_nav ul.nav #job-category-nav .wrap_depth2 .column .list_column_link li.mhover .list_depth3 li {
    padding: 0 0 0 20px;
    width: 190px;
    height: 24px;
    border: none;
    box-sizing: border-box;
    background: none
}

.wrap_nav ul.nav #job-category-nav .wrap_depth2 .column .list_column_link li.mhover .list_depth3 li a {
    line-height: 22px
}

.wrap_nav ul.nav #job-category-nav .wrap_depth2 .last_column {
    width: 130px
}

.wrap_nav ul.nav #job-category-nav li.mhover .list_depth3 {
    display: block;
    width: 380px;
    height: 351px;
    background: url(//www.saraminimage.co.kr/main/new/bg_gnb_sub_div01.png) repeat
}

.wrap_nav ul.nav #job-category-nav li.mhover a:hover {
    font-weight: bold;
    text-decoration: none
}

.wrap_nav ul.nav #job-category-nav .list_column_link .depth2_link:hover {
    color: #444
}

.wrap_nav ul.nav #job-category-nav li .depth03 li a {
    display: block;
    float: left;
    padding-left: 7px !important;
    width: auto !important;
    font-size: 12px !important;
    font-weight: normal;
    text-decoration: none;
    background: url(//www.saraminimage.co.kr/main/new/bul_point_sub.gif) no-repeat 0 4px
}

.wrap_nav ul.nav #job-category-nav li .depth03 li a:hover {
    color: #ff4800;
    font-weight: normal;
    letter-spacing: -1px;
    text-decoration: underline;
    background: url(//www.saraminimage.co.kr/main/new/bul_point_sub.gif) no-repeat 0 4px
}

.wrap_nav ul.nav #area-nav .wrap_depth2 {
    padding: 8px 0 0;
    width: 763px
}

.wrap_nav ul.nav #area-nav .wrap_depth2 li a {
    display: block;
    font-size: 12px
}

.wrap_nav ul.nav #area-nav .wrap_depth2 .column {
    display: inline-block;
    margin-left: 0;
    padding: 0;
    width: 191px;
    vertical-align: top;
    background: #fbfbfb
}

.wrap_nav ul.nav #area-nav .wrap_depth2 .column .list_column_link {
    margin: 0;
    padding: 12px 0;
    border-right: 1px solid #e5e5e5
}

.wrap_nav ul.nav #area-nav .wrap_depth2 .column .list_column_link li, .wrap_nav ul.nav #area-nav .wrap_depth2 .column .list_column_link li a {
    height: 23px;
    line-height: 20px;
    vertical-align: top
}

.wrap_nav ul.nav #area-nav .wrap_depth2 .column .list_column_link li {
    padding: 0 20px;
    border-top: 1px solid #fbfbfb;
    border-bottom: 1px solid #fbfbfb;
    background: url(//www.saraminimage.co.kr/main/new/bul_gnb_point_sub01.png) no-repeat 172px -16px
}

.wrap_nav ul.nav #area-nav .wrap_depth2 .column .list_column_link li.mhover {
    margin-right: -1px;
    border-top: 1px solid #e5e5e5;
    border-bottom: 1px solid #e5e5e5;
    background-color: #fff;
    background-position: 172px 8px
}

.wrap_nav ul.nav #area-nav .wrap_depth2 .column .list_column_link li.mhover .list_depth3 li {
    padding: 0 0 0 17px;
    width: 190px;
    height: 25px;
    border: none;
    box-sizing: border-box;
    background: none
}

.wrap_nav ul.nav #area-nav .wrap_depth2 .column .list_column_link li.mhover .list_depth3 li a {
    line-height: 22px
}

.wrap_nav ul.nav #area-nav .wrap_depth2 .last_column {
    width: 130px
}

.wrap_nav ul.nav #area-nav li.mhover .list_depth3 {
    display: block;
    width: 572px;
    height: 476px;
    background: url(//www.saraminimage.co.kr/main/new/bg_gnb_sub_div01.png) repeat
}

.wrap_nav ul.nav #area-nav li.mhover a:hover {
    font-weight: bold;
    text-decoration: none
}

.wrap_nav ul.nav #area-nav .list_column_link .depth2_link:hover {
    color: #444
}

.wrap_nav ul.nav #area-nav li .depth03 li a {
    display: block;
    float: left;
    padding-left: 7px !important;
    width: auto !important;
    font-size: 12px !important;
    font-weight: normal;
    text-decoration: none;
    background: url(//www.saraminimage.co.kr/main/new/bul_point_sub.gif) no-repeat 0 4px
}

.wrap_nav ul.nav #area-nav li .depth03 li a:hover {
    color: #ff4800;
    font-weight: normal;
    letter-spacing: -1px;
    text-decoration: underline;
    background: url(//www.saraminimage.co.kr/main/new/bul_point_sub.gif) no-repeat 0 4px
}

.wrap_nav ul.nav #public-level .wrap_depth2 {
    padding: 8px 0 0;
    width: 956px
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav:after {
    display: table;
    clear: both;
    content: "";
    table-layout: fixed
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav li.top_line {
    margin-top: 7px;
    padding-top: 7px;
    border-top: 1px solid #e5e5e5
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .column_tit {
    padding-left: 20px;
    height: 33px;
    font-size: 14px;
    font-weight: bold;
    line-height: 30px;
    background: #fbfbfb
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .column_tit > a {
    display: block;
    position: relative;
    height: 33px
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .column_tit > a:hover {
    text-decoration: underline
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav a.public_link {
    margin-right: 16px;
    padding-right: 0;
    font-size: 14px;
    background: url(//www.saraminimage.co.kr/main/new/bul_gnb_point_sub.png) no-repeat right center
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav a.public_link:hover {
    text-decoration: underline
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .column_tit .more_calendar {
    position: absolute;
    top: 0;
    right: 16px;
    padding-right: 12px;
    font-size: 12px;
    font-weight: normal;
    background: #fbfbfb url(//www.saraminimage.co.kr/main/new/bul_gnb_point_sub.png) no-repeat 100% 12px
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav li.column {
    float: left;
    position: relative;
    width: 191px;
    height: 332px;
    border-left: 1px solid #e5e5e5;
    box-sizing: border-box;
    
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav > li:first-child {
    width: 190px;
    border-left: 0
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .title_depth3 {
    height: 20px;
    font-size: 13px;
    font-weight: bold;
    line-height: 20px
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .title_depth3 a {
    font-size: 13px
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav li.class05 {
    width: 192px;
    border-right: none;
    
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav li.class06, .wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav li.class07, .wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav li.class08, .wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav li.class09 {
    padding-bottom: 41px;
    height: 188px;
    border-bottom: none
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav li.class10 {
    overflow: hidden;
    padding-bottom: 0;
    width: 192px;
    border: none;
    
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .list_category {
    padding: 6px 20px 0 20px;
    border: 0 none;
    box-sizing: border-box
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .list_category .category_tit {
    position: relative;
    height: 22px;
    font-size: 13px;
    font-weight: bold;
    line-height: 22px
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .list_category .category_tit strong {
    font-size: 13px;
    line-height: 20px;
    vertical-align: top
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .list_category .category_tit span {
    display: inline-block;
    overflow: hidden;
    font-weight: normal;
    letter-spacing: 0;
    line-height: 20px;
    vertical-align: middle
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .list_category .category_tit .btn_more {
    display: block;
    position: absolute;
    top: 5px;
    right: 0;
    width: 15px;
    height: 15px;
    background: url(//www.saraminimage.co.kr/sri/common/btn/btn_more_calendar.png) no-repeat 0 0;
    cursor: pointer
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .calendar_info {
    padding-bottom: 3px
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .calendar_info a {
    overflow: hidden;
    width: 90%;
    text-overflow: ellipsis;
    white-space: nowrap
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .list_category .public_link {
    display: block;
    position: relative;
    margin-right: 0;
    font-size: 13px;
    font-weight: bold;
    line-height: 20px;
    background: url(//www.saraminimage.co.kr/main/new/bul_gnb_point_sub.png) no-repeat right center
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .list_category .last_line {
    margin-top: 6px;
    padding: 6px 0 6px 0;
    border-bottom: 1px solid #e5e5e5
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .recruit_category .category_tit {
    margin-top: 5px;
    padding-top: 5px;
    height: 21px;
    border-top: 1px solid #e5e5e5;
    color: #6d82f3;
    line-height: 20px
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .recruit_category .tit_top {
    margin-top: 0;
    padding-top: 0;
    border: none
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .recruit_category .keyword {
    width: auto
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .recruit_category .keyword li {
    display: inline-block;
    _display: inline;
    zoom:1;
    _vertical-align: bottom
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .recruit_category .keyword a {
    vertical-align: baseline
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .recruit_category .keyword .block {
    display: block
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .column .box_btn {
    position: absolute;
    top: auto;
    bottom: 20px;
    left: 20px
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .column .btn_type {
    display: inline-block;
    margin-top: 3px;
    padding: 0 16px 0 12px;
    width: 150px;
    height: 31px;
    border: 1px solid #e5e5e5;
    box-sizing: border-box;
    font-size: 13px;
    line-height: 25px;
    text-align: left;
    text-decoration: none;
    background: url(//www.saraminimage.co.kr/main/new/bul_gnb_point_sub.png) no-repeat 128px 10px
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .column .btn_type:hover {
    color: #555;
    text-decoration: none
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .column .btn_type .point {
    color: #778bf5
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .column .banner {
    position: absolute;
    bottom: 20px;
    left: 20px
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .column .banner a {
    color: #fff;
    font-size: 0;
    line-height: 0
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .column .banner img {
    vertical-align: top
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .recruit_live_info {
    margin-top: 6px;
    padding: 12px 0 0 0;
    border-top: 1px solid #e5e5e5
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .recruit_live_info li {
    padding-bottom: 12px
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .recruit_live_info a {
    color: #555;
    font-size: 12px;
    line-height: 18px;
    word-break: break-all
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .recruit_live_info b {
    color: #333
}

.wrap_nav ul.nav #public-level .wrap_depth2 .public_level_nav .recruit_live_info + .category_tit {
    margin-top: 8px;
}

.wrap_nav ul.nav #public-level .wrap_depth2 .area_pass_refer {
    border-top: 1px solid #e5e5e5;
    background: #f4f4f4
}

.wrap_nav ul.nav #public-level .wrap_depth2 .area_pass_refer .tit {
    float: left;
    margin-top: 14px;
    width: 190px;
    height: 45px;
    border-right: 1px solid #e5e5e5;
    color: #444;
    font-size: 16px;
    font-weight: bold;
    line-height: 45px;
    text-align: center
}

.wrap_nav ul.nav #public-level .wrap_depth2 .area_pass_refer:after {
    display: table;
    clear: both;
    content: "";
    table-layout: fixed
}

.wrap_nav ul.nav #public-level .wrap_depth2 .area_pass_refer ul {
    float: left;
    margin: 15px 21px;
    width: 723px;
    _width: 720px
}

.wrap_nav ul.nav #public-level .wrap_depth2 .area_pass_refer ul:after {
    display: table;
    clear: both;
    content: "";
    table-layout: fixed
}

.wrap_nav ul.nav #public-level .wrap_depth2 .area_pass_refer li {
    float: left;
    width: 12.5%;
    text-align: center
}

.wrap_nav ul.nav #public-level .wrap_depth2 .area_pass_refer li a {
    display: inline-block;
    padding-top: 28px;
    color: #444;
    font-size: 12px;
    letter-spacing: -2px;
    background: url(//www.saraminimage.co.kr/sri/common/ico/ico_gnb_pass_refer.png) 10px 0 no-repeat
}

.wrap_nav ul.nav #public-level .wrap_depth2 .area_pass_refer li .spec_info {
    background-position: -86px 0
}

.wrap_nav ul.nav #public-level .wrap_depth2 .area_pass_refer li .job_fair {
    background-position: -171px 0
}

.wrap_nav ul.nav #public-level .wrap_depth2 .area_pass_refer li .aftermote_aptitude {
    background-position: -262px 0
}

.wrap_nav ul.nav #public-level .wrap_depth2 .area_pass_refer li .interview {
    background-position: -349px 0
}

.wrap_nav ul.nav #public-level .wrap_depth2 .area_pass_refer li .successful_interview {
    background-position: -448px 0
}

.wrap_nav ul.nav #public-level .wrap_depth2 .area_pass_refer li .senior_list {
    background-position: -533px 0
}

.wrap_nav ul.nav #public-level .wrap_depth2 .area_pass_refer li .self_introduction {
    background-position: -624px 0
}

.wrap_nav ul.nav #public-level .wrap_depth2 .area_pass_refer li a:hover {
    text-decoration: none
}

.wrap_nav ul.nav #curation-level .wrap_depth2 {
    padding: 8px 0 0;
    width: 932px
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_level_nav:after {
    display: table;
    clear: both;
    content: "";
    table-layout: fixed
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_level_nav li.column {
    float: left;
    position: relative;
    width: 311px;
    height: 153px;
    border-left: 1px solid #e5e5e5;
    box-sizing: border-box
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_level_nav li:nth-child(3n + 1) {
    width: 310px;
    border-left: 0
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_level_nav .column_tit {
    padding: 0 20px 0 18px;
    height: 39px;
    font-size: 14px;
    font-weight: bold;
    line-height: 36px;
    background: #fbfbfb
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_level_nav .column_tit:before {
    position: absolute;
    top: 8px;
    left: 18px;
    width: 21px;
    height: 22px;
    background: url(//www.saraminimage.co.kr/sri/person/jobs_recruit/ico_curation_small_spr.png) 0 0 no-repeat;
    content: ""
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_level_nav li:nth-child(4n + 1) .column_tit:before {
    background-position: 0 0
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_level_nav li:nth-child(4n + 2) .column_tit:before {
    background-position: -50px 0
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_level_nav li:nth-child(4n + 3) .column_tit:before {
    background-position: -100px 0
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_level_nav li:nth-child(4n + 4) .column_tit:before {
    background-position: -150px 0
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_level_nav .column_tit > a {
    display: block;
    position: relative;
    height: 39px
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_level_nav .column_tit > a:hover {
    text-decoration: underline
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_level_nav a.curation_link {
    overflow: hidden;
    box-sizing: border-box;
    width: 100%;
    padding: 0 20px 0 26px;
    font-size: 14px;
    background: url(//www.saraminimage.co.kr/main/new/bul_gnb_point_sub.png) no-repeat right center;
    text-overflow: ellipsis;
    white-space: nowrap
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_level_nav a.curation_link:hover {
    text-decoration: underline
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_level_nav .column_list {
    padding: 10px 0 13px
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_level_nav .column_list li {
    position: relative;
    padding: 5px 0 7px 32px;
    width: auto;
    min-height: 0;
    line-height: 18px
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_level_nav .column_list li:before {
    display: block;
    position: absolute;
    top: 14px;
    left: 20px;
    width: 2px;
    height: 2px;
    background: #888;
    content: ""
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_level_nav .column_list li a {
    display: inline-block;
    overflow: hidden;
    width: 100%;
    color: #444;
    font-size: 12px;
    letter-spacing: -1px;
    line-height: 18px;
    text-overflow: ellipsis;
    vertical-align: top;
    white-space: nowrap
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_refer {
    border-top: 1px solid #e5e5e5;
    background: #f4f4f4
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_refer .tit {
    float: left;
    margin-top: 12px;
    padding: 0 20px;
    width: 167px;
    box-sizing: border-box
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_refer .tit a {
    display: block;
    height: 36px;
    color: #444;
    font-size: 15px;
    font-weight: bold;
    letter-spacing: -2px;
    line-height: 34px;
    background: url(//www.saraminimage.co.kr/main/new/bul_gnb_point_sub2.png) no-repeat right center
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_refer .tit a:hover {
    text-decoration: underline
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_refer:after {
    display: table;
    clear: both;
    content: "";
    table-layout: fixed
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_refer ul {
    float: left;
    padding: 12px 0;
    width: 765px;
    box-sizing: border-box;
    font-size: 0
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_refer ul:after {
    display: table;
    clear: both;
    content: "";
    table-layout: fixed
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_refer li {
    display: inline-block
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_refer li + li {
    margin-left: 8px
}

.wrap_nav ul.nav #curation-level .wrap_depth2 .curation_refer li a {
    display: block;
    padding: 9px 13px 11px;
    border-radius: 18px;
    color: #444;
    font-size: 12px;
    letter-spacing: -1px;
    line-height: 16px;
    background: #fff;
    box-shadow: 0 1px 0 0 rgba(231, 231, 231, 0.5)
}
/* WMG-23769 */

.wrap_nav ul.nav #comsalary-level .wrap_depth2 {
    padding: 8px 0 0;
    width: 759px
}

.wrap_nav ul.nav #comsalary-level .wrap_depth2 .comsalary_level_nav:after {
    display: table;
    clear: both;
    content: "";
    table-layout: fixed
}

.wrap_nav ul.nav #comsalary-level .wrap_depth2 .comsalary_level_nav li.column {
    float: left;
    position: relative;
    width: 253px;
    height: 416px;
    border-left: 1px solid #e5e5e5;
    box-sizing: border-box
}

.wrap_nav ul.nav #comsalary-level .wrap_depth2 .comsalary_level_nav li:first-child {
    border-left: 0
}

.wrap_nav ul.nav #comsalary-level .wrap_depth2 .comsalary_level_nav .column_tit {
    padding-left: 20px;
    height: 39px;
    font-size: 14px;
    font-weight: bold;
    line-height: 36px;
    background: #fbfbfb
}

.wrap_nav ul.nav #comsalary-level .wrap_depth2 .comsalary_level_nav .column_tit > a {
    display: block;
    position: relative;
    height: 33px
}

.wrap_nav ul.nav #comsalary-level .wrap_depth2 .comsalary_level_nav .column_tit > a:hover {
    text-decoration: underline
}

.wrap_nav ul.nav #comsalary-level .wrap_depth2 .comsalary_level_nav .column_tit_link li {
    padding: 0 15px 0 20px;
    height: 39px;
    border-bottom: 1px solid #e5e5e5;
    font-size: 14px;
    font-weight: bold;
    line-height: 30px;
    background: #fbfbfb
}

.wrap_nav ul.nav #comsalary-level .wrap_depth2 .comsalary_level_nav .column_tit_link li > a {
    display: block;
    position: relative;
    height: 39px;
    font-size: 14px;
    font-weight: bold;
    line-height: 36px;
    background: url(//www.saraminimage.co.kr/main/new/bul_gnb_point_sub.png) no-repeat right center
}

.wrap_nav ul.nav #comsalary-level .wrap_depth2 .comsalary_level_nav .column_tit_link li .sri_lnb_icon.ic_window {
    margin: 9px 0 0 3px;
    width: 16px;
    height: 16px;
    background-position: -48px -45px
}

.wrap_nav ul.nav #comsalary-level .wrap_depth2 .comsalary_level_nav .column_list {
    padding: 10px 0 13px
}

.wrap_nav ul.nav #comsalary-level .wrap_depth2 .comsalary_level_nav .column_list li {
    position: relative;
    padding: 5px 0 7px 32px;
    width: auto;
    min-height: 0;
    line-height: 18px
}

.wrap_nav ul.nav #comsalary-level .wrap_depth2 .comsalary_level_nav .column_list li:before {
    display: block;
    position: absolute;
    top: 14px;
    left: 20px;
    width: 2px;
    height: 2px;
    background: #888;
    content: ""
}

.wrap_nav ul.nav #comsalary-level .wrap_depth2 .comsalary_level_nav .column_list li a {
    display: inline-block;
    overflow: hidden;
    width: 100%;
    color: #444;
    font-size: 12px;
    letter-spacing: -1px;
    line-height: 18px;
    text-overflow: ellipsis;
    vertical-align: top;
    white-space: nowrap
}

.wrap_nav ul.nav #comsalary-level .wrap_depth2 .comsalary_level_nav .column_list li a .sri_lnb_icon.ic_window {
    width: 16px;
    height: 16px;
    background-position: -48px -45px
}

.wrap_nav ul.nav #comsalary-level .wrap_depth2 .comsalary_level_nav .box_banner {
    position: absolute;
    bottom: 24px;
    left: 20px;
    width: 210px;
    height: 189px
}

.wrap_nav ul.nav #comsalary-level .wrap_depth2 .comsalary_level_nav .box_banner a {
    display: block
}

.wrap_member {
    float: left;
    z-index: 910;
    height: 60px;
    border-right: 1px solid #426bd9;
    border-left: 1px solid #426bd9;
    font-size: 13px
}

.wrap_member .member_option:after {
    display: table;
    clear: both;
    content: "";
    table-layout: fixed
}

.wrap_member .member_option .box_member, .wrap_member .member_option .box_alram {
    float: left;
    line-height: 60px
}

.wrap_member .member_option .box_member {
    padding: 0 15px
}

.wrap_member .member_option .box_member a:hover {
    text-decoration: underline
}

.wrap_member .member_option .box_member > a:hover {
    text-decoration: none
}

.wrap_member .member_option .box_member a:hover span {
    text-decoration: underline
}

.wrap_member .member_option .box_member a:hover span.icon_title {
    text-decoration: none
}

.wrap_member .member_option .box_member a:hover span em {
    text-decoration: underline
}

.wrap_member .member_option .box_member .photo {
    display: inline-block;
    overflow: hidden;
    margin-top: 12px;
    width: 36px;
    height: 36px;
    border-radius: 50%;
    vertical-align: top;
    background: url(//www.saraminimage.co.kr/sri/common/bg/bg_person_photo_s_new.png) no-repeat 50%
}

.wrap_member .member_option .box_member .photo img {
    margin-top: -4px;
    width: 100%
}

.wrap_member .member_option .box_member .name {
    display: inline-block;
    padding-right: 15px;
    color: #fff;
    font-size: 14px;
    letter-spacing: -1px;
    line-height: 60px;
    background: url(//www.saraminimage.co.kr/sri/common/ico/ico_arrow_down.png) no-repeat 100% 53%
}

.wrap_member .member_option .box_member.on .name {
    background: url(//www.saraminimage.co.kr/sri/common/ico/ico_arrow_up.png) no-repeat 100% 53%
}

.wrap_member .member_option .box_member .link_layer.on .name {
    background: url(//www.saraminimage.co.kr/sri/common/ico/ico_arrow_up.png) no-repeat 100% 53%
}

.wrap_member .member_option .box_member .text {
    margin: 0;
    line-height: 60px;
    text-align: left
}
/* 이력서 개편후 삭제요망 */

.wrap_member .member_option .box_member .sid {
    color: #888;
    font-size: 11px;
    letter-spacing: 0
}

.wrap_member .member_option .box_member .sid .text {
    max-width: 70px
}

.wrap_member .member_option .box_member em {
    display: inline-block;
    overflow: hidden;
    font-style: normal;
    text-overflow: ellipsis;
    vertical-align: top;
    white-space: nowrap
}

.wrap_member .member_option .box_member .text {
    padding: 0
}

.wrap_member .member_option .box_member.join_member {
    border-left: 1px solid #426bd9;
    color: #fff;
    font-size: 14px
}

.wrap_member .member_option .box_member.join_member a {
    color: #fff;
    letter-spacing: -1px
}

.wrap_member .member_option .box_member.join_member a:hover {
    text-decoration: underline
}

.wrap_member .member_person_name {
    position: relative;
    padding: 0 15px;
    width: 135px;
    text-align: center
}

.wrap_member .member_person_name .name .text {
    max-width: 55px;
    font-weight: bold;
    letter-spacing: -1px
}

.wrap_member .notification_myinfo {
    display: none;
    position: absolute;
    top: 12px;
    right: 242px;
    padding: 0 21px 0 16px
}

.wrap_member .notification_myinfo .inner {
    height: 30px;
    color: #fff;
    line-height: 28px;
    text-align: center;
    white-space: nowrap;
    background: url(//www.saraminimage.co.kr/ui/layout/person_mypage_2017/bg_toast_message_spr.png) repeat-x 0 -50px
}

.wrap_member .notification_myinfo:before, .wrap_member .notification_myinfo:after {
    display: block;
    position: absolute;
    top: 0;
    height: 30px;
    background: url(//www.saraminimage.co.kr/ui/layout/person_mypage_2017/bg_toast_message_spr.png) no-repeat 0 0;
    content: ""
}

.wrap_member .notification_myinfo:before {
    left: 0;
    width: 16px
}

.wrap_member .notification_myinfo:after {
    right: 0;
    width: 21px;
    background-position: -16px 0
}

.wrap_member .box_alram {
    width: 60px;
    height: 60px;
    border-left: 1px solid #426bd9
}

.wrap_member .box_alram a {
    display: inline-block
}

.wrap_member .box_alram .btn_alram {
    display: inline-block;
    overflow: hidden;
    position: relative;
    width: 60px;
    height: 40px;
    font-size: 1px;
    text-indent: -200%;
    vertical-align: middle;
    background: url(//www.saraminimage.co.kr/sri/common/ico/ico_alram_center.png) no-repeat 50%
}

.wrap_member .box_alram .btn_alram .count {
    display: inline-block;
    position: absolute;
    top: 0;
    right: 12px;
    width: 18px;
    height: 18px;
    border: 0;
    color: #fff;
    font-size: 10px;
    letter-spacing: 0;
    line-height: 18px;
    text-align: center;
    text-indent: 0;
    background: url(//www.saraminimage.co.kr/sri/common/ico/ico_new.png) no-repeat 0 0
}

.wrap_member .login_wrap {
    width: 145px
}

.wrap_member .login_wrap .photo {
    display: inline-block;
    overflow: hidden;
    margin-top: 10px;
    width: 33px;
    height: 33px;
    border: 1px solid #e5e5e5;
    border-radius: 50%;
    vertical-align: top;
    background: url(//www.saraminimage.co.kr/ui/layout/person_mypage_2017/bg_no_photo_33x33.png) no-repeat 50%
}

.wrap_member .join {
    width: 63px
}

.wrap_member .member_company_name {
    position: relative;
    width: 170px;
    text-align: center
}

.wrap_member .member_company_name .link_company {
    cursor: pointer
}

.wrap_member .member_company_name .link_company .icon_title, .wrap_member .member_company_name .link_manager .icon_title {
    display: inline-block;
    padding: 0 9px 0 7px;
    min-width: 30px;
    height: 20px;
    border: 1px solid #fff;
    border-radius: 10px;
    box-sizing: border-box;
    color: #fff;
    font-size: 12px;
    font-weight: bold;
    letter-spacing: -2px;
    line-height: 16px;
    text-align: left
}

.wrap_member .member_company_name .name .text {
    max-width: 95px;
    font-weight: bold;
    letter-spacing: -1px
}

.wrap_member .member_option .box_member {
    position: relative
}

.wrap_member .member_option .layer_box_member {
    display: none;
    position: absolute;
    top: 59px;
    right: -1px;
    left: -1px;
    border: 1px solid #444;
    background: #fff
}

.wrap_member #nologin_area:hover .layer_box_member {
    display: block
}

.wrap_member #nologin_area:hover .name {
    background: url(//www.saraminimage.co.kr/sri/common/ico/ico_arrow_up.png) no-repeat 100% 53%
}

.member_option .link_manager {
    cursor: pointer
}

.member_option .layer_box_member .list {
    padding: 0 18px;
    letter-spacing: -1px
}

.member_option .layer_box_member .list.nologin {
    padding: 0
}

.member_option .layer_box_member .list.nologin + .list.nologin {
    margin: 15px 0
}

.member_option .layer_box_member .list.list_icon {
    margin-bottom: 12px;
    font-weight: bold
}

.member_option .layer_box_member .list.list_icon li {
    height: 42px;
    border-bottom: 1px solid #e7e7e8;
    line-height: 42px
}

.member_option .layer_box_member .list li {
    height: 25px;
    font-size: 13px;
    line-height: 25px;
    text-align: left
}

.member_option .layer_box_member .list.nologin li {
    height: 25px;
    padding: 0 18px;
    font-size: 13px;
    line-height: 25px;
    text-align: left
}

.member_option .layer_box_member .list.nologin .com_member_link {
    margin: 10px -18px -15px -18px;
    height: 52px
}
/* WMG-20352 */

.member_option .layer_box_member .list li > a {
    display: inline-block;
    color: #444
}

.member_option .layer_box_member .list li > .ico_recommend {
    position: relative;
    padding-left: 20px
}

.member_option .layer_box_member .list li > .ico_recommend:before {
    position: absolute;
    top: 0;
    left: 2px;
    width: 14px;
    height: 22px;
    background: url(//www.saraminimage.co.kr/sri/common/ico/ico_sri_recommend_s.png) no-repeat;
    content: ""
}

.member_option .layer_box_member .list.type_center li {
    height: 42px;
    line-height: 42px;
    text-align: center
}

.member_option .layer_box_member .list.type_center + .btn_link {
    margin-top: 0
}

.member_option .layer_box_member .list.nologin .btn_link {
    margin: 0 0 4px 0;
    height: 34px;
    line-height: 34px
}

.member_option .layer_box_member .list.nologin:first-child li.btn_link {
    border-top: 0
}

.member_option .layer_box_member .list.nologin .btn_link .sri_gnb_option.ic_go_arrow {
    height: 34px;
    background-position-y: -3px
}

.member_option .layer_box_member .btn_link {
    display: block;
    margin-top: 18px;
    height: 42px;
    border-top: 1px solid #e7e7e8;
    color: #444;
    line-height: 42px;
    text-align: center;
    background: #fbfbfb
}

.member_option .layer_myarea_wrap {
    display: none;
    position: absolute;
    top: 59px;
    right: 0;
    width: 454px;
    border: 1px solid #444;
    box-sizing: border-box;
    background-color: #fff
}

.member_option.on .layer_myarea_wrap {
    display: block
}

.member_option .layer_myarea_wrap .profile_wrap {
    text-align: left
}

.member_option .layer_myarea_wrap .profile_wrap .name_wrap {
    display: inline-block;
    margin-right: 20px;
    max-width: 105px;
    color: #dfdfdf;
    font-size: 22px;
    letter-spacing: -2px
}

.member_option .layer_myarea_wrap .profile_wrap .name_wrap .name {
    display: inline-block;
    overflow: hidden;
    max-width: 82px;
    height: 40px;
    text-overflow: ellipsis;
    vertical-align: top;
    white-space: nowrap
}

.member_option .layer_myarea_wrap .profile_wrap .img_spr {
    background: url(//www.saraminimage.co.kr/ui/layout/person_mypage_2017/ico_profile_spr.png)
}

.member_option .layer_myarea_wrap .profile_wrap .photo {
    position: relative
}

.member_option .layer_myarea_wrap .profile_wrap .photo .thum {
    display: block;
    overflow: hidden;
    width: 96px;
    height: 96px;
    border: 1px solid #e0e0e0;
    border-radius: 50%;
    background: url(//www.saraminimage.co.kr/sri/common/bg/bg_person_photo.png) no-repeat 50%
}

.member_option .layer_myarea_wrap .profile_wrap .photo .thum img {
    margin-top: -10px;
    width: 100%
}

.member_option .layer_myarea_wrap .profile_wrap .photo button {
    position: absolute;
    border: none;
    color: #fff;
    font-size: 1px;
    line-height: 1px;
    text-indent: -9999px
}

.member_option .layer_myarea_wrap .profile_wrap .photo button.btn_del {
    top: 6px;
    left: 4px;
    width: 20px;
    height: 20px;
    background-position: 0 -100px
}

.member_option .layer_myarea_wrap .profile_wrap .photo button.btn_regist {
    right: 0;
    bottom: 0;
    width: 28px;
    height: 28px;
    background-position: 0 -50px
}

.member_option .layer_myarea_wrap .profile_wrap .list_person_settings {
    margin: 11px 0 0 2px
}

.member_option .layer_myarea_wrap .profile_wrap .list_person_settings:after {
    display: table;
    clear: both;
    content: "";
    table-layout: fixed
}

.member_option .layer_myarea_wrap .profile_wrap .list_person_settings > li {
    float: left;
    margin-left: -1px;
    width: 102px;
    box-sizing: border-box
}

.member_option .layer_myarea_wrap .profile_wrap .list_person_settings > li .link {
    display: inline-block;
    margin: -1px 0 0 -1px;
    width: 100%;
    height: 40px;
    border: 1px solid #e7e7e8;
    box-sizing: border-box;
    color: #6e6d6d;
    font-size: 13px;
    letter-spacing: -2px;
    line-height: 34px;
    text-align: center;
    background-color: #fbfbfb
}

.member_option .layer_myarea_wrap .profile_wrap .list_person_settings > li .link:hover {
    position: relative;
    z-index: 10;
    margin: -1px 0 0 -1px;
    border: 1px solid #666;
    text-decoration: none;
    background-color: #fff
}

.member_option .layer_myarea_wrap .profile_wrap .list_person_settings > li .ico_recommend {
    position: relative;
    padding: 0 2px 0 19px
}

.member_option .layer_myarea_wrap .profile_wrap .list_person_settings > li .ico_recommend:before {
    position: absolute;
    top: -3px;
    left: 0;
    width: 14px;
    height: 22px;
    background: url(//www.saraminimage.co.kr/sri/common/ico/ico_sri_recommend_s.png) no-repeat;
    content: ""
}

.member_option .layer_myarea_wrap .profile_wrap .area_person_photo {
    position: absolute;
    top: 17px;
    left: 17px;
    letter-spacing: -1px;
    text-align: center
}

.member_option .layer_myarea_wrap .profile_wrap .area_person_photo .box_name {
    margin-top: 7px;
    color: #656565;
    font-size: 16px;
    letter-spacing: -2px
}

.member_option .layer_myarea_wrap .profile_wrap .area_person_photo .box_name .name {
    display: inline-block;
    padding-right: 2px;
    max-width: 57px;
    color: #3550d4;
    font-weight: bold;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    vertical-align: top
}

.member_option .layer_myarea_wrap .profile_wrap .area_person_photo .sid {
    max-width: 98px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    vertical-align: top;
    color: #555;
    font-size: 14px
}

.member_option .layer_myarea_wrap .profile_wrap .area_person_photo .sid.sns_login {
    font-size: 11px
}

.member_option .layer_myarea_wrap .profile_wrap .area_person_photo .sid .ic {
    display: inline-block;
    width: 10px;
    height: 10px;
    margin-right: 4px;
    background: url(//www.saraminimage.co.kr/sri/common/ico/icon_sid_myname.png) no-repeat;
    vertical-align: middle
}

.member_option .layer_myarea_wrap .profile_wrap .area_person_photo .sid .social_type01 {
    margin-right: 4px;
    background-position: 0 0;
}

.member_option .layer_myarea_wrap .profile_wrap .area_person_photo .sid .social_type02 {
    margin-right: 4px;
    background-position: -10px 0;
}

.member_option .layer_myarea_wrap .profile_wrap .area_person_photo .sid .social_type03 {
    margin-right: 4px;
    background-position: -20px 0;
}

.member_option .layer_myarea_wrap .profile_wrap .area_person_photo .sid .social_type04 {
    margin-right: 4px;
    background-position: -30px 0;
}

.member_option .layer_myarea_wrap .profile_wrap .area_person_info {
    position: relative;
    margin-left: 131px;
    padding-top: 23px;
    width: 305px;
    height: 155px;
    letter-spacing: -1px
}

.member_option .layer_myarea_wrap .profile_wrap .area_person_info .person_click_data a {
    display: inline-block;
    padding-left: 19px;
    width: 78px;
    height: 20px;
    color: #565656;
    font-size: 13px;
    letter-spacing: -2px;
    background: url(//www.saraminimage.co.kr/sri/common/ico/ico_person_spr.png) no-repeat 0 0
}

.member_option .layer_myarea_wrap .profile_wrap .area_person_info .person_click_data a.link_scrap {
    background-position: 0 1px
}

.member_option .layer_myarea_wrap .profile_wrap .area_person_info .person_click_data a.link_apply {
    background-position: 0 -49px
}

.member_option .layer_myarea_wrap .profile_wrap .area_person_info .person_click_data a.link_lately {
    padding-left: 22px;
    background-position: 0 -97px
}

.member_option .layer_myarea_wrap .profile_wrap .area_person_info .person_click_data a .point {
    margin-left: 3px;
    color: #ff662f;
    font-size: 14px;
    font-weight: normal;
    letter-spacing: -1px
}

.member_option .layer_myarea_wrap .profile_wrap .btn_logout {
    display: inline-block;
    position: absolute;
    top: 145px;
    right: 2px;
    padding: 1px 0 0 20px;
    height: 20px;
    color: #888;
    font-size: 13px;
    vertical-align: top;
    background: url(//www.saraminimage.co.kr/sri/common/ico/ico_person_spr.png) no-repeat 0 0
}

.member_option .layer_myarea_wrap .profile_wrap .btn_logout {
    background-position: 0 -146px
}

.member_option .layer_myarea_wrap .activity_wrap {
    border-top: 1px solid #e4e4e4;
    letter-spacing: -1px
}

.member_option .layer_myarea_wrap .tabmenu {
    height: 50px;
    font-size: 15px
}

.member_option .layer_myarea_wrap .tabmenu li {
    float: left;
    width: 50%;
    height: 49px;
    border-bottom: 1px solid #dedede;
    box-sizing: border-box;
    color: #999;
    line-height: 49px;
    text-align: center;
    background-color: #f8f8f8;
    cursor: pointer
}

.member_option .layer_myarea_wrap .tabmenu li em.new {
    display: inline-block;
    overflow: hidden;
    position: relative;
    margin-top: -12px;
    width: 5px;
    height: 5px;
    font-size: 1px;
    text-indent: -9999px;
    vertical-align: middle;
    background: url(//www.saraminimage.co.kr/ui/layout/person_mypage_2017/ico_new01.png) no-repeat
}

.member_option .layer_myarea_wrap .tabmenu li:first-child {
    width: 50%;
    border-right: 1px solid #dedede;
    box-sizing: border-box
}

.member_option .layer_myarea_wrap .tabmenu li.selected {
    border-bottom-color: #fff;
    color: #444;
    background-color: #fff
}

.member_option .layer_myarea_wrap .tabmenu li.disable {
    color: #aaa
}

.member_option .layer_myarea_wrap .tabcont_wrap {
    height: 579px
}

.member_option .layer_myarea_wrap .tabcont_wrap .tabcont {
    overflow-x: hidden;
    overflow-y: auto;
    padding: 25px 20px;
    width: 452px;
    height: 100%;
    box-sizing: border-box
}

.member_option .layer_myarea_wrap .recruit_summary {
    position: relative;
    margin-bottom: 10px;
    padding: 0;
    width: 100%;
    border: 1px solid #e5e5e5;
    box-sizing: border-box;
    text-align: left;
    background-color: #fff
}

.member_option .layer_myarea_wrap .recruit_summary:last-child, .member_option .layer_myarea_wrap .activity_summary:last-child {
    margin-top: 0
}

.member_option .layer_myarea_wrap .recruit_summary .desc {
    height: 32px;
    border-bottom: 1px solid #ebebeb;
    font-size: 13px;
    line-height: 32px
}

.member_option .layer_myarea_wrap .recruit_summary .desc .point {
    color: #ff6129
}

.member_option .layer_myarea_wrap .recruit_summary .wrap_layer_tooltip {
    margin: 4px 0 0 2px;
    height: 16px
}

.member_option .layer_myarea_wrap .recruit_summary .wrap_layer_tooltip button.interested_corp {
    background-position: -42px 0
}

.member_option .layer_myarea_wrap .recruit_summary .wrap_layer_tooltip button.interested_on {
    background-position: -62px 0
}

.member_option .layer_myarea_wrap .recruit_summary ul.info_list {
    padding: 11px 0 37px;
    line-height: 22px
}

.member_option .layer_myarea_wrap .recruit_summary ul.info_list li.company_name {
    color: #444;
    font-size: 15px;
    font-weight: bold
}

.member_option .layer_myarea_wrap .recruit_summary ul.info_list li.recruit_name {
    padding-top: 1px;
    color: #444;
    font-size: 14px
}

.member_option .layer_myarea_wrap .recruit_summary ul.info_list li.recruit_sector {
    color: #888;
    font-size: 12px;
    line-height: 18px
}

.member_option .layer_myarea_wrap .recruit_summary ul.info_list li.recruit_sector span:before {
    display: inline-block;
    padding: 0 5px 0 2px;
    color: #e5e5e5;
    font-size: 10px;
    content: "|"
}

.member_option .layer_myarea_wrap .recruit_summary ul.info_list li.recruit_sector span:first-child:before {
    padding: 0;
    content: ""
}

.member_option .layer_myarea_wrap .recruit_summary .util {
    position: absolute;
    right: 20px;
    bottom: 47px;
    left: 20px;
    height: 19px;
    line-height: 19px;
    text-align: right
}

.member_option .layer_myarea_wrap .recruit_summary.box_my_activity .util {
    bottom: 15px
}

.member_option .layer_myarea_wrap .recruit_summary .util .btn_scrap {
    float: left;
    height: 19px;
    line-height: 19px;
    vertical-align: top
}

.member_option .layer_myarea_wrap .recruit_summary .util .date {
    display: inline-block;
    height: 14px;
    color: #666;
    font-size: 12px;
    line-height: 14px;
    vertical-align: top
}

.member_option .layer_myarea_wrap .recruit_summary .util .apply {
    margin-left: 5px;
    width: 48px;
    height: 16px
}

.member_option .layer_myarea_wrap .recruit_summary .util .apply span {
    display: inline-block;
    padding: 0 0 2px 0;
    width: 46px;
    height: 12px;
    border: 1px solid #bebebe;
    color: #666;
    font-size: 11px;
    letter-spacing: -1px;
    line-height: 12px;
    text-align: center;
    vertical-align: top;
    background: #fff
}

.member_option .layer_myarea_wrap .recruit_summary .util .apply span.now {
    border: 1px solid #ff8149;
    color: #fff;
    background: #ff8d5a
}

.member_option .layer_myarea_wrap .recruit_summary .util .apply span.closed {
    cursor: default
}

.member_option .layer_myarea_wrap .recruit_summary .from_section {
    margin: 0;
    padding: 0 20px 0 30px;
    height: 32px;
    box-sizing: border-box;
    line-height: 32px;
    text-align: right;
    background-color: #f9f9f9
}

.member_option .layer_myarea_wrap .recruit_summary .from_section a {
    display: inline-block;
    color: #555;
    font-size: 12px
}

.member_option .layer_myarea_wrap .recruit_summary .from_section a span {
    font-size: 13px
}

.member_option .layer_myarea_wrap .no_activity {
    display: table;
    width: 100%;
    height: 150px
}

.member_option .layer_myarea_wrap .no_activity p {
    display: table-cell;
    color: #444;
    font-size: 30px;
    letter-spacing: -2px;
    line-height: 38px;
    text-align: center;
    vertical-align: middle
}

.member_option .layer_myarea_wrap .activity_summary {
    position: relative;
    margin-bottom: 10px;
    padding: 0 20px;
    border: 1px solid #e5e5e5;
    text-align: left;
    background-color: #fff
}

.member_option .layer_myarea_wrap .activity_summary .desc {
    height: 32px;
    border-bottom: 1px solid #ebebeb;
    font-size: 14px;
    line-height: 32px
}

.member_option .layer_myarea_wrap .activity_summary .desc a {
    display: inline-block;
    color: #444;
    font-size: 12px
}

.member_option .layer_myarea_wrap .activity_summary .desc a span {
    font-size: 14px
}

.member_option .layer_myarea_wrap .activity_summary .desc .point {
    color: #ff6129
}

.member_option .layer_myarea_wrap .activity_summary .desc .date {
    float: right;
    color: #999;
    font-size: 13px
}

.member_option .layer_myarea_wrap .activity_summary .wrap_layer_tooltip {
    margin: 4px 0 0 2px;
    height: 16px
}

.member_option .layer_myarea_wrap .activity_summary .btn_scrap {
    display: inline-block;
    margin-top: 3px;
    line-height: 20px;
    vertical-align: top
}

.member_option .layer_myarea_wrap .activity_summary .text_point01 {
    padding-top: 15px;
    color: #ff6129;
    font-size: 12px;
    line-height: 14px
}

.member_option .layer_myarea_wrap .activity_summary ul.info_list {
    padding: 11px 0 13px;
    line-height: 22px
}

.member_option .layer_myarea_wrap .activity_summary ul.info_list li.company_name, .member_option .layer_myarea_wrap .activity_summary ul.info_list li.title {
    font-size: 15px
}

.member_option .layer_myarea_wrap .activity_summary ul.info_list li.company_name a, .member_option .layer_myarea_wrap .activity_summary ul.info_list li.title a {
    color: #444;
    font-weight: bold
}

.member_option .layer_myarea_wrap .activity_summary ul.info_list li.recruit_name, .member_option .layer_myarea_wrap .activity_summary ul.info_list li.data {
    overflow: hidden;
    width: 308px;
    font-size: 14px;
    line-height: 16px;
    text-overflow: ellipsis;
    white-space: nowrap
}

.member_option .layer_myarea_wrap .activity_summary ul.info_list li.recruit_name a, .member_option .layer_myarea_wrap .activity_summary ul.info_list li.data a {
    color: #999
}

.member_option .layer_myarea_wrap .activity_summary ul.info_list li.company_name .sri_layer_tooltip {
    display: inline-block;
    margin-top: 3px;
    height: 16px;
    line-height: 16px
}

.member_option .layer_myarea_wrap .activity_summary ul.info_list li.recruit_name {
    padding-top: 4px
}

.member_option .layer_myarea_wrap .activity_summary ul.info_list li.recruit_document {
    color: #666;
    font-size: 12px
}

.member_option .layer_myarea_wrap .activity_summary ul.info_list li.recruit_document a {
    text-decoration: underline
}

.member_option .layer_myarea_wrap .activity_summary ul.info_list li.recruit_sector {
    color: #666;
    font-size: 12px
}

.member_option .layer_myarea_wrap .activity_summary ul.info_list li.recruit_sector span:before {
    display: inline-block;
    padding: 0 5px 0 2px;
    color: #e5e5e5;
    font-size: 10px;
    content: "|"
}

.member_option .layer_myarea_wrap .activity_summary ul.info_list li.recruit_sector span:first-child:before {
    padding: 0;
    content: ""
}

.member_option .layer_myarea_wrap .activity_summary ul.info_list li.recruit_category {
    color: #999;
    font-size: 12px
}

.member_option .layer_myarea_wrap .activity_summary ul.info_list li.recruit_category span {
    padding-right: 8px
}

.member_option .layer_myarea_wrap .activity_summary ul.info_list li.count {
    padding-top: 3px;
    color: #444;
    font-size: 13px
}

.member_option .layer_myarea_wrap .activity_summary ul.info_list li.count .point {
    color: #ff6129;
    font-weight: bold;
    text-decoration: underline
}

.member_option .layer_myarea_wrap .activity_summary a.link_wrap, .member_option .layer_myarea_wrap .recruit_summary a.link_wrap {
    display: block;
    padding: 0 20px
}

.member_option .layer_myarea_wrap .recruit_summary a:hover {
    text-decoration: none
}

.member_option .layer_myarea_wrap .btn_close {
    overflow: hidden;
    position: absolute;
    top: 15px;
    right: 15px;
    width: 17px;
    height: 17px;
    border: none;
    font-size: 1px;
    text-indent: -200%;
    background: url(//www.saraminimage.co.kr/ui/layout/person_mypage_2017/btn_top_banner01.png) no-repeat
}

.member_option .alarm_center_layer {
    display: none;
    top: 59px;
    right: 0;
    min-height: 246px;
    border: 1px solid #444
}

.member_option .alarm_center_layer.on {
    display: block
}

.member_option .layer_company_wrap {
    display: none;
    position: absolute;
    top: 59px;
    right: 0;
    z-index: 20;
    width: 130px;
    border: 1px solid #444;
    background: #fff
}

.member_option .layer_company_wrap.on {
    display: block
}

.member_option .layer_company_wrap ul {
    margin: 9px 0 12px 17px
}

.member_option .layer_company_wrap li {
    margin-left: auto;
    line-height: 23px;
    text-align: left
}

.member_option .layer_company_wrap li a {
    color: #444;
    font-size: 12px;
    letter-spacing: -1px;
    text-decoration: none
}

.member_option .layer_company_wrap li a:hover {
    text-decoration: underline
}

.loading_action_wrap {
    padding: 50px 0;
    text-align: center
}

.loading_action_wrap .text {
    display: inline-block;
    padding-left: 43px;
    height: 32px;
    color: #999;
    font-size: 20px;
    line-height: 32px;
    background: url(//www.saraminimage.co.kr/js/libs/images/loading.gif) no-repeat 0 0
}

/* sri_lnb */
#sri_lnb .lnb_menu {
    border-bottom: 1px solid #efefef;
    background: #fff
}

#sri_lnb .lnb_menu > a {
    position: relative
}

#sri_lnb .lnb_menu .category {
    display: inline-block;
    height: 23px;
    box-sizing: border-box;
    margin: -1px 6px -3px 0;
    padding: 0 8px 2px 7px;
    color: #666;
    font-size: 11px;
    letter-spacing: -1px;
    line-height: 19px;
    border: 1px solid #d9d9d9;
    border-radius: 13px;
    vertical-align: top;
}

#sri_lnb .lnb_menu .depth_menu {
    display: block;
    padding: 14px 0 16px 20px;
    width: 100%;
    box-sizing: border-box;
    color: #444;
    font-size: 15px;
    letter-spacing: -1px
}

#sri_lnb .lnb_menu a.depth_menu {
    cursor: pointer
}

#sri_lnb .lnb_menu a.depth_menu:hover {
    color: #999
}

#sri_lnb .lnb_menu.on .depth_menu {
    padding-bottom: 10px
}

#sri_lnb .lnb_menu.depth_two .arrow {
    position: absolute;
    top: 0;
    right: 21px
}

#sri_lnb .lnb_menu.depth_two.on .arrow {
    background-position: -100px 0
}

#sri_lnb .lnb_menu .depth_menu_inner {
    display: none
}

#sri_lnb .lnb_menu .depth_menu_inner a {
    display: block;
    padding: 1px 0 5px 30px;
    width: 100%;
    box-sizing: border-box;
    color: #666;
    font-size: 13px;
    letter-spacing: -1px;
    cursor: pointer
}

#sri_lnb .lnb_menu .depth_menu_inner a:hover {
    color: #999
}

#sri_lnb .lnb_menu.on .depth_menu_inner {
    display: block;
    padding-bottom: 13px
}

#sri_lnb .lnb_menu .depth_menu_inner.sep_menu_3 {
    padding: 0 0 12px 15px
}

#sri_lnb .lnb_menu .depth_menu_inner.sep_menu_3:after {
    display: table;
    clear: both;
    content: "";
    table-layout: fixed
}

#sri_lnb .lnb_menu .depth_menu_inner.sep_menu_3 li {
    float: left;
    position: relative;
    margin-right: 10px;
    margin-bottom: 5px;
    width: 52px;
    text-align: center
}

#sri_lnb .lnb_menu .depth_menu_inner.sep_menu_3 li:after {
    position: absolute;
    top: 7px;
    left: -6px;
    width: 1px;
    height: 10px;
    background: #ececed;
    content: ""
}

#sri_lnb .lnb_menu .depth_menu_inner.sep_menu_3 li:nth-of-type(3n-2):after {
    display: none
}

#sri_lnb .lnb_menu .depth_menu_inner.sep_menu_3 a {
    padding: 2px 0 0 0
}

#sri_lnb .lnb_menu .depth_menu_inner.sep_menu_2 {
    padding: 0 0 12px 20px
}

#sri_lnb .lnb_menu .depth_menu_inner.sep_menu_2:after {
    display: table;
    clear: both;
    content: "";
    table-layout: fixed
}

#sri_lnb .lnb_menu .depth_menu_inner.sep_menu_2 li {
    float: left;
    position: relative;
    margin: 0 19px 6px 0;
    width: 67px;
    text-align: left
}

#sri_lnb .lnb_menu .depth_menu_inner.sep_menu_2 li:after {
    position: absolute;
    top: 5px;
    right: -13px;
    width: 1px;
    height: 10px;
    background: #ececed;
    content: ""
}

#sri_lnb .lnb_menu .depth_menu_inner.sep_menu_2 li:nth-of-type(2n) {
    margin-right: 0;
    width: 75px
}

#sri_lnb .lnb_menu .depth_menu_inner.sep_menu_2 li:nth-of-type(2n):after {
    display: none
}

#sri_lnb .lnb_menu .depth_menu_inner.sep_menu_2 a {
    padding: 1px 0 0 10px
}

#sri_lnb .lnb_menu.no_depth {
    padding: 15px 0
}

#sri_lnb .lnb_menu.no_depth .ic_golink {
    position: absolute;
    right: 13px
}

#sri_lnb .lnb_menu .link_type {
    display: inline-block;
    margin: 0 0 7px 15px;
    padding-left: 13px;
    width: 189px;
    height: 45px;
    border: 1px solid #e7e7e8;
    box-sizing: border-box;
    line-height: 40px
}

#sri_lnb .lnb_menu .link_type strong {
    font-size: 15px
}

#sri_lnb .lnb_menu .link_type.smaller {
}

#sri_lnb .lnb_menu .link_type.smaller strong {
    font-size: 14px;
    letter-spacing: -1px
}

#sri_lnb .lnb_menu .link_type:last-child {
    margin-bottom: 0
}

#sri_lnb .lnb_menu .nav_title {
    padding: 15px 0 0 15px;
    height: 51px;
    box-sizing: border-box;
    font-size: 14px;
    letter-spacing: -1px;
    background: #fff
}

#sri_lnb .lnb_menu .nav_title .icon_title {
    display: inline-block;
    margin-bottom: 1px;
    padding: 2px 2px 0 2px;
    width: 55px;
    height: 20px;
    border: 1px solid #a1a1a1;
    border-radius: 10px;
    box-sizing: border-box;
    font-size: 11px;
    letter-spacing: -1px;
    line-height: 12px;
    text-align: center;
    vertical-align: top
}

#sri_lnb .lnb_menu .nav_title strong {
    margin-left: 1px
}

.nav_title .icon_title {
    display: inline-block;
    margin-bottom: 1px;
    padding: 2px 2px 0 2px;
    width: 55px;
    height: 20px;
    border: 1px solid #a1a1a1;
    border-radius: 10px;
    box-sizing: border-box;
    font-size: 11px;
    letter-spacing: -1px;
    line-height: 12px;
    text-align: center;
    vertical-align: top
}

.lnb_menu .sri_lnb_icon.ic_up, .lnb_menu .sri_lnb_icon.ic_hot, .lnb_menu .sri_lnb_icon.ic_new, .lnb_menu .sri_lnb_icon.ic_renew, .lnb_menu .sri_lnb_icon.ic_window, .lnb_menu .sri_lnb_icon.ic_company {
    margin-top: 6px
}

.lnb_menu .depth_menu_inner .sri_lnb_icon.ic_up, .lnb_menu .depth_menu_inner .sri_lnb_icon.ic_hot, .lnb_menu .depth_menu_inner .sri_lnb_icon.ic_new, .lnb_menu .depth_menu_inner .sri_lnb_icon.ic_window, .lnb_menu .depth_menu_inner .sri_lnb_icon.ic_company {
    margin-top: 4px
}

/* recently_menu_wrap */
.recently_menu_wrap {
    padding: 15px 15px 0 15px;
    box-sizing: border-box
}

.recently_menu_wrap .title {
    display: block;
    margin-bottom: 15px;
    padding-left: 5px;
    color: #444;
    font-size: 13px;
    letter-spacing: -1px
}

.recent_list li {
    display: inline-block;
    position: relative;
    margin: 0 5px 5px 0;
    padding: 0 19px 0 11px;
    height: 26px;
    border: 1px solid #dedede;
    border-radius: 13px;
    box-sizing: border-box;
    color: #777;
    font-size: 12px;
    letter-spacing: -1px;
    line-height: 23px;
    background: #fff
}

.recent_list li .btn_recently_del {
    position: absolute;
    top: 0;
    right: 0
}

/* sri_lnb_banner */
#sri_lnb_banner {
    margin: 15px auto 0 auto;
    padding-bottom: 50px;
    width: 189px
}

#sri_lnb_banner .items li {
    display: none;
    width: 189px;
    height: 210px
}

#sri_lnb_banner .items li:first-child {
    display: block
}

#sri_lnb_banner .items img {
    display: block;
    width: 189px;
    height: 210px
}

#sri_lnb_banner .btns {
    text-align: center
}

#sri_lnb_banner .btns li {
    display: inline-block;
    margin: 0 5px;
    width: 6px;
    height: 6px;
    border: 1px solid #b2b2b2;
    border-radius: 4px;
    background-color: #fff
}

#sri_lnb_banner .btns li.on {
    background-color: #b2b2b2
}

#sri_lnb_banner .btns li a {
    display: block;
    width: 8px;
    height: 8px
}

/* sri_banner_scrolled */
#sri_banner_scrolled ul li a {
    display: inline-block;
    width: 120px
}

#sri_banner_scrolled ul li a img {
    vertical-align: top
}

#sri_banner_scrolled .google-adsense {
    overflow: hidden;
    margin-top: 12px;
    height: 600px
}

#sri_banner_scrolled + #sri_banner_fixed {
    margin-top: 13px
}

#sri_banner_fixed .data_list {
    display: table;
    width: 118px;
    height: 37px;
    border: 1px solid #e6e6e6;
    border-top: 0;
    background: #fff
}

#sri_banner_fixed .data_list:after {
    display: table;
    clear: both;
    content: "";
    table-layout: fixed
}

#sri_banner_fixed .data_list:first-child {
    border-top: 1px solid #e6e6e6
}

#sri_banner_fixed .data_list dt {
    display: table-cell;
    padding-left: 8px;
    width: 42px;
    font-size: 11px;
    font-weight: bold;
    letter-spacing: -1px;
    vertical-align: middle
}

#sri_banner_fixed .data_list dd {
    display: table-cell;
    padding-right: 10px;
    width: 58px;
    text-align: right;
    vertical-align: middle
}

#sri_banner_fixed .data_list dd a {
    color: #ff4d4d;
    font-size: 11px
}

#sri_banner_fixed .data_list dd a em {
    font-size: 15px;
    font-weight: bold
}

#sri_banner_fixed .btn_go_top {
    display: block;
    margin: 15px auto 0 auto
}

/* layout - footer */
.sri_footer {
    clear: both;
    display: block;
    width: 100%;
    position: relative;
    z-index: 10;
    min-width: 960px
}

.sri_footer .wrap_award {
    border-top: #ededed 1px solid;
    background: #fff
}

.sri_footer .wrap_footer {
    min-width: 960px;
    border: solid #eaeaea;
    border-width: 1px 0;
    background: #f8f8f8
}

.sri_footer .award ul {
    font-size: 0;
    text-align: center;
    width: 960px;
    margin: 0 auto
}

.sri_footer .award ul li {
    box-sizing: border-box;
    display: -moz-inline-stack;
    display: inline-block;
    zoom: 1;
    /* * display:inline; */
    width: 110px;
    padding: 17px 0 18px 0;
    margin-left: 11px
}

.sri_footer .award ul li.first {
    margin-left: 0
}

.sri_footer .award ul li a {
    letter-spacing: -1px;
    text-decoration: none
}

.sri_footer .award ul li a img {
    display: block;
    margin-bottom: 12px
}

.sri_footer .award ul li a .prize {
    display: block;
    height: 28px;
    margin-bottom: 2px;
    color: #666;
    font-family: "Malgun Gothic", dotum, gulim, sans-serif;
    font-size: 11px;
    line-height: 14px
}

.sri_footer .award ul li a .org {
    display: block;
    color: #b0b0b0;
    font-family: "Malgun Gothic", dotum, gulim, sans-serif;
    font-size: 11px
}

.sri_footer .links {
    font-family: "Malgun Gothic", dotum, gulim, sans-serif;
    text-align: center
}

.sri_footer .links ul {
    width: 960px;
    height: 60px;
    margin: 0 auto;
    font-size: 0;
    line-height: 60px
}

.sri_footer .links ul li {
    box-sizing: border-box;
    display: -moz-inline-stack;
    display: inline-block;
    zoom: 1;
    /* * display:inline; */
    margin-left: 45px
}

.sri_footer .links ul li.first {
    margin-left: 0
}

.sri_footer .links ul li a {
    color: #666;
    font-family: "Malgun Gothic", dotum, gulim, sans-serif;
    font-size: 13px;
    letter-spacing: -1px;
    text-decoration: none
}

.sri_footer .links ul li.bold a {
    font-weight: bold
}

.sri_footer .affiliates {
    height: 44px;
    border-top: 1px solid #e7e7e8
}

.sri_footer .affiliates ul {
    width: 960px;
    height: 44px;
    margin: 0 auto;
    font-size: 0;
    line-height: 44px;
    text-align: center
}

.sri_footer .affiliates ul li {
    box-sizing: border-box;
    display: -moz-inline-stack;
    display: inline-block;
    zoom: 1;
    /* * display:inline; */
    height: 44px;
    margin-left: 11px;
    line-height: 0;
    vertical-align: top
}

.sri_footer .affiliates ul li.first {
    margin-left: 0px
}

.sri_footer .affiliates ul li a {
    display: -moz-inline-stack;
    display: inline-block;
    zoom: 1;
    /* * display:inline; */
    box-sizing: border-box;
    height: 100%;
    padding-top: 12px;
    vertical-align: top
}

.sri_footer .affiliates ul li a img {
    vertical-align: top
}

.sri_footer .inner {
    position: relative;
    box-sizing: border-box;
    width: 960px;
    padding: 30px 0 21px 214px;
    margin: 0 auto
}

.sri_footer .logo {
    display: block;
    position: absolute;
    top: 44px;
    left: 40px;
    width: 144px;
    height: 54px
}

.sri_footer .customer {
    margin-bottom: 12px
}

.sri_footer .customer p, .sri_footer .customer div {
    height: 16px;
    padding: 0;
    margin: 0;
    color: #444;
    font-family: "Malgun Gothic", dotum, gulim, sans-serif;
    font-size: 12px;
    letter-spacing: -1px;
    line-height: 16px
}

.sri_footer .customer p span, .sri_footer .customer div span {
    letter-spacing: 0
}

.sri_footer .customer .btns {
    height: 20px;
    color: #999;
    line-height: 20px
}

.sri_footer .customer .btns a {
    display: -moz-inline-stack;
    display: inline-block;
    zoom: 1;
    /* * display:inline; */
    box-sizing: border-box;
    width: 71px;
    height: 20px;
    border: #c8c8c8 1px solid;
    background: #f8f8f8;
    color: #888;
    font-size: 11px;
    line-height: 18px;
    text-align: center;
    text-decoration: none
}

.sri_footer .customer .btns a.first {
    margin-left: 8px
}

.sri_footer .copyright {
    width: 960px;
    margin: 0 auto 30px auto
}

.sri_footer .copyright p {
    padding: 0;
    margin: 0;
    color: #999;
    font-family: "Malgun Gothic", dotum, gulim, sans-serif;
    font-size: 12px;
    letter-spacing: -1px;
    line-height: 18px;
    text-align: center
}

.sri_footer .btn_mobile_view {
    display: block;
    box-sizing: border-box;
    width: 960px;
    height: 40px;
    margin: 30px auto 0 auto;
    border: #e2e2e2 1px solid;
    background: #f1f1f1;
    color: #444;
    font-family: "Malgun Gothic", dotum, gulim, sans-serif;
    font-size: 16px;
    line-height: 38px;
    text-align: center;
    text-decoration: none
}

/* 알림센터 */
.alarm_center_layer {
    position: absolute;
    top: 40px;
    right: 0;
    z-index: 10;
    padding: 9px 0 11px 8px;
    width: 332px;
    border: 1px solid #a1a1a1;
    text-align: left;
    background: #fff
}

.alarm_center_layer p {
    margin: 0
}

.alarm_center_layer .layer_scroll_box {
    overflow-y: auto;
    position: relative;
    width: 332px;
    background: #fff url(//www.saraminimage.co.kr/ui/top/alarm_list2.gif) repeat-y 19px 0
}

.alarm_center_layer .no_data {
    overflow: hidden;
    background: none
}

.alarm_center_layer .alarm_list {
    overflow: hidden;
    position: relative;
    width: 100%
}

.alarm_center_layer .alarm_list li {
    display: block;
    position: relative;
    margin: 5px 8px 0 0;
    border: 1px solid #cbcbcc;
    box-sizing: border-box;
    background: #fff
}

.alarm_center_layer .alarm_list li .deco_img {
    display: block;
    position: absolute;
    top: -8px;
    left: 15px;
    width: 10px;
    height: 8px;
    font: 0/0 Arial;
    background: url(//www.saraminimage.co.kr/ui/notification/bul_deco_alarm.png) no-repeat 0 -10px;
    /* * top:-7px */
}

.alarm_center_layer .alarm_list .checked {
    margin-bottom: 15px;
    text-align: center
}

.alarm_center_layer .alarm_list .checked p {
    color: #444 !important;
    font-size: 14px;
    font-weight: bold;
    line-height: 20px;
    /* * display:inline; */
    /* * zoom:1 */
}

.alarm_center_layer .alarm_list .checked .deco_img, .alarm_center_layer .alarm_list .etcetera .deco_img {
    background-position: 0 0
}

.alarm_center_layer .alarm_list .nudge_banner img {
    width: 100%
}

.alarm_center_layer .alarm_list .checked .alarm_tit, .alarm_center_layer .alarm_list .etcetera .alarm_tit {
    background-color: #fff
}

.alarm_center_layer .alarm_view {
    display: block;
    position: relative;
    padding: 0 0 12px;
    width: 100%
}

.alarm_center_layer .alarm_view:hover {
    text-decoration: none
}

.alarm_center_layer .alarm_view:hover span {
    text-decoration: none
}

.alarm_center_layer .alarm_view .alarm_tit {
    display: block;
    margin-bottom: 10px;
    padding: 14px 0 8px 14px;
    color: #6d82f3;
    font-size: 14px;
    font-weight: bold;
    line-height: 20px;
    background-color: #f3f8ff
}

.alarm_center_layer .alarm_view .alarm_txt {
    display: block;
    margin: 0 14px;
    color: #444;
    font-size: 14px;
    font-weight: bold;
    line-height: 20px
}

.alarm_center_layer .alarm_view .alarm_stxt {
    display: block;
    margin: 5px 14px 0;
    color: #444;
    font-size: 11px;
    letter-spacing: 0;
    line-height: 18px
}

.alarm_center_layer .alarm_view .alarm_time {
    position: absolute;
    top: 18px;
    right: 45px;
    color: #a4a4a4;
    font-size: 11px;
    letter-spacing: 0;
    line-height: 18px
}

.alarm_center_layer .alarm_view .txt_allchecked {
    display: block;
    margin: 22px 0 7px !important;
    color: #444 !important;
    font-size: 14px;
    font-weight: bold;
    text-align: center
}

.alarm_center_layer .alarm_view .web {
    right: 15px
}

.alarm_center_layer .alarm_login {
    display: block;
    position: relative;
    padding: 5px
}

.alarm_center_layer .alarm_login span {
    text-align: left
}

.alarm_center_layer .notice_tit {
    margin: 12px 0 3px;
    padding-left: 52px;
    height: 20px;
    font-size: 14px;
    font-weight: bold;
    background: url(//www.saraminimage.co.kr/ui/notification/bul_tit_alarm.png) no-repeat 12px 0
}

.alarm_center_layer .notice_tit strong {
    display: inline-block;
    overflow: hidden;
    padding-right: 12px;
    height: 19px;
    color: #fff;
    font-size: 13px;
    font-weight: bold;
    letter-spacing: -1px;
    line-height: 18px;
    vertical-align: top;
    background: #6d82f3 url(//www.saraminimage.co.kr/ui/notification/bg_tit_alarm.gif) no-repeat 100% 0
}

.alarm_center_layer .notice_tit strong\ {
    display: inline;
    zoom: 1;
    line-height: 22px
}

.alarm_center_layer .tit_no1 {
    position: relative;
    margin: 0 0 3px 0
}

.alarm_center_layer .btn_set_arlam {
    display: block;
    position: absolute;
    top: 18px;
    right: 14px;
    width: 25px;
    height: 19px;
    background: url(//www.saraminimage.co.kr/ui/top/icon_push2.png) no-repeat 0 0
}

.alarm_center_layer .height_fixed {
    height: 527px
}

.alarm_center_layer .button_box {
    padding: 10px 0 0;
    width: 324px;
    line-height: 1;
    background: #fff
}

.alarm_center_layer .button_box .btn_bottom {
    display: block;
    height: 31px;
    color: #fff;
    font-weight: bold;
    line-height: 31px;
    text-align: center;
    text-decoration: none;
    background: url(//www.saraminimage.co.kr/ui/notification/btn_alarm_bottom.png) no-repeat
}

.alarm_center_layer .button_box .btn_bottom:hover {
    color: #fff
}

.alarm_center_layer .btn_closed_alarm {
    margin: 6px 8px 0 0;
    width: 324px;
    text-align: right
}

.alarm_center_layer .btn_closed_alarm .btn_close_layer {
    display: inline-block;
    padding-right: 15px;
    height: 19px;
    color: #888;
    font-size: 11px;
    line-height: 19px;
    text-decoration: none;
    background: url(//www.saraminimage.co.kr/ui/notification/bg_close_layer.png) no-repeat 100% 4px;
    /* * display:inline;
    * zoom:1;
    * line-height:22px */
}

.alarm_center_layer .btn_closed_alarm .btn_close_layer:hover {
    color: #888
}

.alarm_center_layer .loading_txt {
    padding: 10px 0;
    text-align: center
}

.alarm_center_layer .loading_txt img {
    margin: 0 2px;
    vertical-align: middle
}

.alarm_center_layer .not-notif {
    margin-top: 7px;
    color: #a3a3a3;
    font-size: 12px;
    text-align: center
}

.alarm_center_layer .wrap_no_alarm {
    position: relative;
    margin: 20px 0;
    height: 96px;
    background: url(//www.saraminimage.co.kr/ui/notification/bg_noalarm_util.png) no-repeat 50% 0
}

.alarm_center_layer .wrap_no_alarm .link {
    position: absolute;
    top: 0;
    left: 0;
    padding-top: 78px;
    width: 100%;
    color: #b4b4b4;
    font-size: 11px;
    line-height: 18px;
    text-align: center;
    text-decoration: underline
}

.alarm_center_layer .wrap_no_alarm .link:hover {
    color: #b4b4b4
}

.alarm_center_layer .alarm_view .alarm_cnt {
    margin-top: 6px
}

.alarm_center_layer .alarm_view .alarm_cnt .row {
    padding-left: 15px
}

.alarm_center_layer .alarm_view .alarm_cnt .row:after {
    display: block;
    clear: both;
    content: ""
}

.alarm_center_layer .alarm_view .alarm_cnt .row .col_lft {
    float: left;
    width: 57px;
    font-size: 11px;
    color: #999;
    line-height: 17px;
    letter-spacing: 0
}

.alarm_center_layer .alarm_view .alarm_cnt .row .col_rgt {
    margin-left: 57px;
    font-size: 11px;
    color: #666;
    line-height: 17px;
    letter-spacing: 0
}

.alarm_center_layer .alarm_view .alarm_btn_area {
    margin: 10px 15px 0 0;
    padding: 0;
    color: #666;
    font-size: 11px;
    letter-spacing: -1px;
    line-height: 13px;
    text-align: right
}

/* WMG-6853 [유채꽃] 기간체계 변경 */
.alarm_center_layer.company .height_fixed {
    height: 426px
}

.alarm_center_layer.company .alarm_view .alarm_stxt {
    text-align: right
}

.alarm_center_layer.company .alarm_view .detail {
    margin-top: 6px;
    margin-left: 5px
}

.alarm_center_layer.company .alarm_view .detail li {
    margin: 0;
    padding: 2px 0 2px 10px;
    border: 0;
    font-size: 11px;
    line-height: 12px
}

.alarm_center_layer.company .alarm_view .detail li .emp {
    color: #f5543d
}

.alarm_center_layer.company .layer_scroll_box .hurry {
    display: inline-block;
    margin-top: 10px;
    width: 100%;
    height: 34px;
    font-size: 12px;
    letter-spacing: -1px;
    text-align: center
}

.alarm_center_layer.company .button_box .btn_bottom {
    letter-spacing: 0
}

/* content bottom googlead */
#content .google-adsense.doctype {
    margin: 100px auto 80px;
    width: 728px
}

/* 포장이사용 wrap */
.package_wrap {
    margin: 0 auto
}

.package_wrap.w925 {
    width: 925px
}

.package_wrap.w890 {
    width: 890px
}

.package_wrap.w774 {
    width: 774px
}

.package_wrap.w740 {
    width: 740px
}

.package_wrap.w695 {
    width: 695px
}

.package_wrap.w680 {
    width: 680px
}

/* WMG-18108 */
#sri_lnb .lnb_menu .lnb_pop_nudge, .folded #sri_lnb .lnb_menu .lnb_pop_nudge, .folded #sri_lnb .lnb_menu.on .lnb_pop_nudge {
    display: none
}

#sri_lnb .lnb_menu.on .lnb_pop_nudge {
    display: block;
}

.lnb_nudge_magazine {
    position: fixed;
    z-index: 10;
    width: 219px;
}

.lnb_nudge_magazine .inner {
    position: absolute;
    top: 20px;
    right: -228px;
    width: 220px;
    height: 102px
}

.lnb_nudge_magazine .inner img {
    vertical-align: top
}

.lnb_nudge_magazine .inner .btn_close {
    position: absolute;
    top: 0;
    right: 1px;
    width: 33px;
    height: 33px
}

.lnb_nudge_magazine .inner .btn_close img {
    width: 100%;
    height: 100%
}

/* wmg-17777 */
.wrap_nav ul.nav .wrap_depth2 li > a .icon_new {
    margin: 6px 0 0 0
}

.wrap_nav ul.nav #area-nav .wrap_depth2 .link_subway {
    position: absolute;
    right: 0;
    bottom: 0;
    padding-left: 19px;
    width: 573px;
    height: 37px;
    border: 1px solid #e5e5e5;
    border-width: 1px 0 0 1px;
    box-sizing: border-box;
    background: #fff
}

.wrap_nav ul.nav #area-nav .wrap_depth2 .link_subway .btn_subway_recruit {
    display: inline-block;
    padding-right: 16px;
    line-height: 35px;
    font-size: 13px;
    color: #444;
    background: url(//www.saraminimage.co.kr/sri/common/btn/btn_arr_gnb_link.png) no-repeat 100% 55%
}

.wrap_nav ul.nav #area-nav .wrap_depth2 .link_subway .btn_subway_recruit:hover {
    text-decoration: underline
}

/* WMG-23007 */
.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav li.depth2.direct_link_menu {
    height: 429px
}

.wrap_nav ul.nav #recruit-nav .wrap_depth2 .recruit_info_nav li.depth2.type_long_height {
    height: 242px
}

.wrap_nav ul.nav .wrap_depth2 li > a .icon_beta {
    margin: 6px 0 0 0;
    vertical-align: top
}






.jv_title {
    margin-bottom: 14px;
    color: #222;
    font-size: 20px;
    font-weight: bold;
    letter-spacing: -1px;
    line-height: 22px
}

.jv_title > strong {
    color: #4876ef
}

.jv_title .link_txt {
    float: right;
    margin: 2px 11px 0 0;
    border-bottom: 1px solid #4876ef;
    font-weight: normal;
    font-size: 13px;
    ;
    line-height: 17px;
    color: #4876ef
}

.jv_title .link_txt:after {
    position: absolute;
    right: 0;
    top: 7px;
    width: 6px;
    height: 9px;
    background-position: 0 -170px;
    content: ""
}



/* 즉시지원 버튼 컬러
.jview .sri_btn_lg>span.sri_btn_immediately {border:1px solid #ff6d6a;color:#fff;background:#ff6d6a}
.offer.list .sri_btn_xs {display:inline-block;overflow:hidden;margin:0;padding:0;width:auto;height:auto;box-sizing:border-box;text-align:center;vertical-align:top;background:#fff;cursor:pointer}
.offer.list .sri_btn_xs>span.sri_btn_immediately {width:52px;border:1px solid #ff6d6a;color:#ff6d6a;background:0}
 */


/* 추천영역 */
.jview_wing {
    overflow: hidden;
    position: fixed;
    top: 99px;
    left: 50%;
    z-index: 2;
    margin-left: 350px;
    width: 280px;
    height: calc(100vh - 99px);
    box-sizing: border-box;
    -webkit-transform: translateZ(0);
    -ms-transform: translateZ(0);
    transform: translateZ(0);
    will-change: transform
}

.jview_wing:after {
    display: block;
    position: absolute;
    top: 0;
    left: 0;
    z-index: 2;
    width: 100%;
    height: 5px;
    background: #f1f3f9 url(//www.saraminimage.co.kr/sri/person/jobs_view/bg_jview_wing.gif) 0 0 repeat-x;
    content: ""
}

.jview_floating.on + .jview_wing:after {
    display: none
}

.jview_wing .scroller {
    overflow-y: scroll;
    width: 300px;
    height: 100%
}

.jview_wing .inner {
    position: relative;
    z-index: 1;
    padding: 25px 0;
    width: 280px
}

.jview_wing .inner > .title {
    margin-bottom: 10px;
    width: 280px;
    color: #222;
    font-size: 18px;
    font-weight: bold;
    letter-spacing: -1px;
    line-height: 24px
}

.jview_wing .inner > .title strong {
    color: #4876ef
}

.jview_wing .jv_ads {
    overflow: hidden;
    margin: 15px 0 0 15px;
    width: 250px;
    height: 250px
}

.jview_wing .offer.list {
    width: 280px
}

.jview_recommendation {
    margin: 50px auto 0;
    width: 960px
}

.offer .info {
    font-size: 12px;
    line-height: 16px
}

.offer .info .txt_bar {
    display: inline-block;
    margin: 0 2px;
    width: 1px;
    height: 10px;
    color: #e9e9e9;
    font-size: 0;
    vertical-align: middle;
    background: #e9e9e9
}

.offer .btn_scrap {
    display: inline-block;
    overflow: hidden;
    position: relative;
    z-index: 3;
    padding: 4px;
    height: 24px
}

.offer .btn_scrap:before {
    width: 17px;
    height: 16px;
    background-position: 0 -20px;
    content: ""
}

.offer .btn_scrap.on {
    background-position: -30px -20px
}

.offer .btn_interest {
    padding: 3px;
    width: 21px;
    height: 19px;
    vertical-align: middle
}

.offer .btn_interest:before {
    display: block;
    width: 15px;
    height: 13px;
    background-position: 0 0;
    content: ""
}

.offer .btn_interest.on:before {
    background-position: -30px 0
}

.offer .ico_offer {
    display: inline-block;
    margin-right: 4px;
    width: 41px;
    height: 16px;
    font-size: 0;
    line-height: 0;
    vertical-align: top;
    background: url(//www.saraminimage.co.kr/sri/person/jobs_view/ico_offer.png) no-repeat 0 0
}

.offer.list li {
    display: none;
    position: relative;
    margin-bottom: 12px;
    padding: 76px 20px 0 19px;
    width: 100%;
    height: 112px;
    box-sizing: border-box
}

.offer.list li:before {
    display: block;
    position: absolute;
    top: 23px;
    left: 0;
    z-index: 1;
    width: 100%;
    height: 89px;
    border: 1px solid #cfd1d6;
    border-radius: 4px;
    box-sizing: border-box;
    background: #fff;
    content: ""
}

.offer.list li.offer:before {
    border-left: 2px solid #7d90f0
}
/* AB test */

.offer.list li.related:before {
    background: #fcfcfd
}

.offer.list li .link {
    display: block;
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    box-sizing: border-box
}

.offer.list li .link > span {
    display: block;
    position: relative;
    z-index: 2;
    letter-spacing: -1px
}

.offer.list li .link .related {
    display: inline-block;
    float: right;
    margin-right: 4px;
    color: #222;
    font-size: 12px;
    font-weight: bold;
    line-height: 18px;
    text-align: right;
    vertical-align: middle
}

.offer.list li .link .related strong {
    color: #4876ef
}

.offer.list li .link .company {
    overflow: hidden;
    z-index: 1;
    margin: 0 0 16px 4px;
    color: #222;
    font-size: 14px;
    font-weight: bold;
    line-height: 18px;
    text-overflow: ellipsis;
    white-space: nowrap
}

.offer.list li .link .related + .company {
    width: 176px
}

.offer.list li .link .title {
    overflow: hidden;
    margin: 0 20px 1px;
    color: #444;
    font-size: 14px;
    text-overflow: ellipsis;
    white-space: nowrap
}

.offer.list li .link .title .ico_offer {
    display: inline-block;
    margin-right: 4px;
    width: 41px;
    height: 16px;
    font-size: 0;
    line-height: 0;
    vertical-align: middle;
    background: url(//www.saraminimage.co.kr/sri/person/jobs_view/ico_offer.png) no-repeat 0 0
}

.offer.list li .info {
    margin: 0 20px
}

.offer.list li .sri_btn_xs {
    display: inline-block;
    float: right;
    position: relative;
    z-index: 3;
    margin-top: 3px
}

.offer.list li .date {
    display: inline-block;
    float: right;
    position: relative;
    z-index: 3;
    margin-top: 3px;
    color: #666;
    font-size: 11px;
    line-height: 16px;
    text-align: right;
    vertical-align: top
}

.offer.list li .sri_btn_xs + .date {
    margin-right: 8px
}

.offer.tbl {
    width: 100%;
    table-layout: fixed
}

.offer.tbl td {
    padding: 14px 0 19px;
    border-top: 1px solid #444;
    border-bottom: 1px solid #ebebeb;
    color: #666;
    font-size: 14px;
    letter-spacing: -1px;
    vertical-align: top;
    background: #fff
}

.offer.tbl .company {
    padding: 14px 0 19px 30px;
    line-height: 24px
}

.offer.tbl .company .link {
    max-width: 128px
}

.offer.tbl .link {
    display: inline-block;
    overflow: hidden;
    color: #333;
    text-overflow: ellipsis;
    vertical-align: top;
    white-space: nowrap
}

.offer.tbl .link:hover {
    text-decoration: underline
}



.offer.tbl .recruit .link {
    max-width: 440px;
    font-size: 16px
}

.offer.tbl .ico_offer {
    display: block;
    margin-top: 3px
}

.offer.tbl .recruit .txt_recruit {
    display: block;
    margin-bottom: 8px;
    line-height: 24px
}

.offer.tbl .info {
    display: block;
    margin: 0
}

.offer.tbl .info > em {
    margin-left: 8px;
    font-size: 13px;
    line-height: 16px;
    vertical-align: middle
}

.offer.tbl .info > em:before {
    margin: -1px 8px 0 0;
    height: 12px;
    background: #c5c5c5
}

.offer.tbl .info > em:first-child {
    margin-left: 0
}

.offer.tbl .d_day {
    float: left;
    width: 128px;
    box-sizing: border-box;
    color: #888;
    font-size: 13px;
    line-height: 22px
}

.offer.tbl .d_day .date {
    display: block;
    color: #666;
    font-size: 14px
}

.offer.tbl .sri_btn_sm {
    float: left;
    margin-top: 6px;
    width: 90px
}

.offer.tbl .sri_btn_sm span {
    width: 88px
}

.jview_wing .btn_help, .jview_recommendation .btn_help {
    display: inline-block;
    margin-top: 6px;
    width: 16px;
    height: 16px;
    vertical-align: top
}

.jview_wing .btn_help span, .jview_recommendation .btn_help span {
    clip: rect(1px, 1px, 1px, 1px);
    position: absolute;
    width: 1px;
    height: 1px
}

.jview_wing .btn_help:after, .jview_recommendation .btn_help:after {
    width: 16px;
    height: 16px;
    background-position: -100px -150px;
    content: ""
}

.jview_wing .toolTip em, .jview_recommendation .toolTip em {
    color: #4876ef
}

.jview_wing .btn_help:hover + .toolTip, .jview_recommendation .btn_help:hover + .toolTip {
    display: block
}

.jview_wing .toolTip {
    margin: 10px 0 0 -178px;
    width: 220px;
    font-weight: normal
}

.jview_recommendation .toolTip {
    margin: 10px 0 0 -125px;
    width: 220px;
    font-weight: normal
}

.jview_wing .toolTip .tail.tail_top_right {
    right: 73px
}

/* 리모콘 */
.jv_remote {
    position: fixed;
    top: 99px;
    left: 50%;
    z-index: 2;
    margin-left: -699px;
    width: 70px;
    box-sizing: border-box;
    -webkit-transform: translateZ(0);
    -ms-transform: translateZ(0);
    transform: translateZ(0);
    will-change: transform
}

.jv_remote > .links > button {
    display: none;
    position: relative;
    padding-top: 45px;
    width: 70px;
    height: 76px;
    border: 1px solid #e5e6eb;
    border-top: 0;
    box-sizing: border-box;
    background: #fbfcfc
}

.jv_remote > .links > button:first-child {
    border-top: 1px solid #e5e6eb
}

.jv_remote > .links > button.ready {
    display: block
}

.jv_remote > .links > button.ready.on {
    border-right-color: #fff;
    background: #fff
}

.jv_remote > .links > button > span {
    display: block;
    color: #888;
    font-size: 12px;
    font-weight: normal;
    letter-spacing: -1px;
    line-height: 16px;
    text-align: center
}

.jv_remote > .links > button.ready.on > span {
    color: #444;
    font-weight: bold
}

.jv_remote > .links > button:before {
    position: absolute;
    top: 15px;
    left: 23px;
    width: 23px;
    height: 25px;
    content: ""
}

.jv_remote > .links > button.jv_detail:before {
    background-position: 0 -90px
}

.jv_remote > .links > button.jv_howto:before {
    background-position: -30px -90px
}

.jv_remote > .links > button.jv_statics:before {
    background-position: -60px -90px
}

.jv_remote > .links > button.jv_company:before {
    background-position: -90px -90px
}

.jv_remote > .links > button.jv_reference:before {
    background-position: -120px -90px
}

.jv_remote > .links > button.jv_detail.ready.on:before {
    background-position: 0 -120px
}

.jv_remote > .links > button.jv_howto.ready.on:before {
    background-position: -30px -120px
}

.jv_remote > .links > button.jv_statics.ready.on:before {
    background-position: -60px -120px
}

.jv_remote > .links > button.jv_company.ready.on:before {
    background-position: -90px -120px
}

.jv_remote > .links > button.jv_reference.ready.on:before {
    background-position: -120px -120px
}

.jv_remote > .nav {
    display: none;
    margin: 24px 0 0 10px;
    width: 47px;
    border: 1px solid #e5e6eb;
    box-sizing: border-box
}

.jv_remote > .nav > button {
    display: block;
    position: relative;
    padding: 8px 0;
    width: 100%;
    height: 43px;
    box-sizing: border-box;
    background: #fff
}

.jv_remote > .nav > button:first-child {
    border-bottom: 1px solid #e5e6eb
}

.jv_remote > .nav > button.btn_prev:before, .jv_remote > .nav > button.btn_next:after {
    display: block;
    margin-left: 15px;
    width: 17px;
    height: 10px;
    opacity: 0.2
}

.jv_remote > .nav > button.btn_prev:before {
    margin-bottom: 4px;
    background-position: -120px 0;
    content: ""
}

.jv_remote > .nav > button.btn_next:after {
    margin-top: 5px;
    background-position: -120px -20px;
    content: ""
}

.jv_remote > .nav > button span {
    display: block;
    color: #949494;
    font-size: 11px;
    letter-spacing: -1px;
    line-height: 13px;
    opacity: 0.3
}

.jv_remote > .nav > button.ready:before, .jv_remote > .nav > button.ready.btn_next:after {
    opacity: 1
}

.jv_remote > .nav > button.ready span {
    opacity: 1
}

.jv_remote > .nav > button.btn_next.ready.blink {
    background-color: #4876ef;
    -webkit-animation: btnNextBlink 0.4s ease-in-out infinite;
    animation: btnNextBlink 0.4s ease-in-out infinite
}

.jv_remote > .nav > button.btn_next.ready.blink span {
    color: #fff
}

.jv_remote > .nav > button.btn_next.ready.blink:after {
    background-position: -150px -20px
}

@-webkit-keyframes btnNextBlink {
    0% {
        -webkit-transform: translateY(0px);
        transform: translateY(0px)
    }

    50% {
        -webkit-transform: translateY(10px);
        transform: translateY(10px)
    }

    100% {
        -webkit-transform: translateY(0px);
        transform: translateY(0px)
    }
}

@keyframes btnNextBlink {
    0% {
        -webkit-transform: translateY(0px);
        transform: translateY(0px)
    }

    50% {
        -webkit-transform: translateY(10px);
        transform: translateY(10px)
    }

    100% {
        -webkit-transform: translateY(0px);
        transform: translateY(0px)
    }
}

.jv_remote.shrink {
    margin-left: -622px;
    padding-top: 13px;
    width: 31px
}

.jv_remote.shrink > .links {
    position: relative
}

.jv_remote.shrink > .links:before {
    display: block;
    position: absolute;
    top: 8px;
    bottom: 8px;
    left: 15px;
    width: 1px;
    background: #e0e0e0;
    content: ""
}

.jv_remote.shrink > .links > button {
    display: none;
    margin-top: 5px;
    padding: 0;
    width: 31px;
    height: 27px;
    border: 0;
    background: none
}

.jv_remote.shrink > .links > button:before {
    display: none
}

.jv_remote.shrink > .links > button:first-child {
    margin-top: 0;
    border: 0
}

.jv_remote.shrink > .links > button.ready {
    display: block
}

.jv_remote.shrink > .links > button.ready.on {
    background: none
}

.jv_remote.shrink > .links > button.ready > span {
    margin: 0 auto;
    width: 13px;
    height: 13px;
    border-radius: 13px;
    box-sizing: border-box;
    font-size: 0;
    line-height: 0;
    background: #e0e0e0
}

.jv_remote.shrink > .links > button.ready.on > span {
    width: 15px;
    height: 15px;
    border: 2px solid #444;
    border-radius: 15px;
    background: #fff
}

.jv_remote.shrink > .links > button.ready.on:before {
    display: block;
    position: absolute;
    top: 11px;
    left: 13px;
    width: 5px;
    height: 5px;
    border-radius: 5px;
    background: #444;
    content: ""
}

.jv_remote.shrink > .nav {
    display: none;
    margin: 9px 0 0 0;
    width: 31px;
    border: 0
}

.jv_remote.shrink > .nav > button {
    padding: 0;
    width: 31px;
    height: 27px;
    background: none
}

.jv_remote.shrink > .nav > button:first-child {
    margin-bottom: 1px;
    border: 0
}

.jv_remote.shrink > .nav > button:before, .jv_remote.shrink > .nav > button.btn_next:after {
    display: block;
    position: absolute;
    left: 12px;
    margin-left: 0;
    width: 7px;
    height: 4px;
    opacity: 0.3
}

.jv_remote.shrink > .nav > button.btn_prev:before {
    top: 11px;
    margin-bottom: 0;
    background-position: -180px 0;
    content: ""
}

.jv_remote.shrink > .nav > button.btn_next:after {
    top: 12px;
    margin-top: 0;
    background-position: -180px -20px;
    content: ""
}

.jv_remote.shrink > .nav > button span {
    margin: 0 auto;
    width: 13px;
    height: 13px;
    border: 1px solid #e5e6eb;
    border-radius: 13px;
    box-sizing: border-box;
    font-size: 0;
    line-height: 0;
    background: #fcfcfd;
    opacity: 1
}

.jv_remote.shrink > .nav > button.ready span {
    background: #fff
}

.jv_remote.shrink > .nav > button.ready.blink {
    background: none
}

.jv_remote.shrink > .nav > button.ready.blink span {
    border-color: #4876ef;
    background-color: #4876ef
}

.jv_remote.shrink > .nav > button.ready:before, .jv_remote.shrink > .nav > button.ready.btn_next:after {
    opacity: 1
}

.jv_remote.shrink > .nav > button.ready.btn_next.ready.blink:after {
    background-position: -180px -150px
}

/* TOP 버튼 */
.jview_top {
    display: none;
    position: fixed;
    left: 50%;
    bottom: 20px;
    z-index: 100;
    margin-left: 645px;
    padding: 7px 0;
    width: 40px;
    height: 40px;
    border: 1px solid #cbcbcb;
    box-sizing: border-box;
    background: #fff
}

.jview_top:before {
    display: block;
    margin: 0 0 2px 11px;
    width: 17px;
    height: 10px;
    background-position: -150px 0;
    content: ""
}

.jview_top span {
    display: block;
    color: #444;
    font-family: dotum, gulim, sans-serif;
    font-size: 11px;
    line-height: 13px
}

/* 공고 타이틀 & 플로팅 공통 */
.jv_header {
    position: relative;
    padding-right: 270px;
    color: #222;
    font-size: 0;
    letter-spacing: -1px;
    line-height: 28px;
    min-height: 74px
}

.jv_header .inner {
    -webkit-transform: translateZ(0);
    -ms-transform: translateZ(0);
    transform: translateZ(0);
    will-change: transform
}

.jv_header .tag {
    display: inline-block;
    position: relative;
    margin-right: 8px;
    padding-right: 9px;
    height: 28px;
    box-sizing: border-box;
    color: #888;
    font-size: 16px;
    line-height: 23px;
    text-align: center;
    vertical-align: middle
}

.jv_header .tag:after {
    display: block;
    position: absolute;
    top: 5px;
    right: 0;
    width: 1px;
    height: 16px;
    background: #e4e4e4;
    content: ""
}



.jv_header .tit_job {
    margin-top: 4px;
    font-size: 26px;
    font-weight: bold;
    letter-spacing: -1.5px;
    line-height: 34px
}

.jv_header .tit_job > .ico_offer {
    display: inline-block;
    margin: -5px 4px 0 0;
    width: 52px;
    height: 24px;
    font-size: 0;
    line-height: 0;
    vertical-align: middle;
    background: url(//www.saraminimage.co.kr/sri/person/jobs_view/ico_offer2_200402.png) no-repeat 0 0
}
/* WMG-25511 추가 */

.jv_header .btn_jview.btn_careers {
    margin-left: 4px
}

.jv_header .btn_jview.btn_careers .num {
    font-weight: bold;
    color: #444
}

.jv_header .btn_scrap {
    position: absolute;
    top: 14px;
    right: 195px
}

.jv_header .btn_apply {
    position: absolute;
    top: 4px;
    right: 0;
    width: 185px;
    text-align: center
}

.jv_header .btn_apply .sri_btn_lg {
    position: absolute;
    top: 10px;
    z-index: 1
}

.jv_header .btn_apply .dday {
    display: inline-block;
    position: relative;
    z-index: 2;
    padding: 0 8px;
    height: 21px;
    border: 1px solid #ff7673;
    border-radius: 21px;
    color: #ff6d6a;
    font-size: 12px;
    letter-spacing: -1px;
    line-height: 18px;
    background: #fff
}

.jv_header_float .jv_header .tag {
    font-size: 13px
}

.jv_header_float .jv_header .tag:after {
    top: 7px;
    height: 13px
}

.jv_header_float .jv_header .company {
    color: #666;
    font-size: 15px;
    line-height: 25px
}

.jv_header_float .jv_header .tit_job {
    overflow: hidden;
    margin-top: 4px;
    width: 100%;
    font-size: 19px;
    line-height: 22px;
    text-overflow: ellipsis;
    white-space: nowrap
}

.jv_header_float .jv_header {
    padding-top: 14px
}

.jv_header_float .jv_header .btn_scrap {
    top: 16px
}

.jv_header_float .jv_header .btn_apply {
    top: 6px
}
/* WMG-23274 추가 */

.jv_header .ico_recuite_method {
    display: inline-block;
    margin: 6px 13px 0 0;
    padding: 4px 12px 6px;
    height: 26px;
    border-radius: 14px;
    box-sizing: border-box;
    font-size: 13px;
    line-height: 15px;
    color: #fff;
    background: #444;
    vertical-align: top
}

.jv_header .ico_recuite_method:before {
    display: inline-block;
    vertical-align: top;
    content: ""
}

.jv_header .ico_blind:before {
    margin: 2px 4px 0 0;
    width: 18px;
    height: 14px;
    background: url(//www.saraminimage.co.kr/sri/person/jobs_view/ico_blind.png) no-repeat 0 0
}

.jv_header .ico_form:before {
    margin: 1px 3px 0 0;
    width: 16px;
    height: 15px;
    background: url(//www.saraminimage.co.kr/sri/person/jobs_view/ico_form.png) no-repeat 0 0
}

.jview_floating .jv_header .ico_recuite_method {
    margin: 0 9px 0 0
}

/* 플로팅 */
.jview_floating.jview {
    display: none;
    position: fixed;
    top: 4px;
    left: 0;
    z-index: 10;
    width: 100%;
    height: 96px;
    background: #fff;
    box-shadow: 0 0 12px 0 rgba(100, 107, 113, 0.3)
}

.jview_floating.on {
    display: block
}

.jview_floating .jv_header_float {
    position: absolute;
    top: 0;
    left: 50%;
    margin-left: -585px;
    width: 870px
}

.jv_util {
    position: absolute;
    top: 0;
    left: 50%;
    margin-left: 350px;
    padding-top: 28px;
    width: 280px
}

.jv_util .links {
    font-size: 0
}

.jv_util .links a {
    display: inline-block;
    position: relative;
    padding: 0;
    color: #949494;
    font-size: 13px;
    letter-spacing: -1px;
    line-height: 15px;
    text-align: center;
    vertical-align: top
}

.jv_util .links.person a {
    padding: 0 9px
}

.jv_util .links a + a {
    margin-left: 28px
}

.jv_util .links a + a:before {
    display: block;
    position: absolute;
    top: 0;
    left: -15px;
    width: 1px;
    height: 35px;
    background: #eeefef;
    content: ""
}

.jv_util .links.login {
    padding-right: 31px;
    text-align: center
}

.jv_util .links.login a + a:before {
    top: 10px;
    height: 14px
}

.jv_util .links a strong {
    display: block;
    margin-top: 5px;
    color: #444;
    font-size: 15px;
    letter-spacing: 0
}

.jv_util .links a span {
    display: inline-block;
    line-height: 32px;
    vertical-align: top
}

.jv_util .links a.text {
    line-height: 35px
}

.jv_util .links a.text span {
    line-height: 17px
}

.jv_util .links a:hover strong {
    text-decoration: underline
}

.jv_util .menu {
    position: absolute;
    top: 30px;
    right: 0;
    width: 31px;
    height: 31px
}

.jv_util .menu .btn_menu, .jv_util .menu .btn_close {
    display: block;
    position: absolute;
    top: 0;
    right: 0;
    width: 31px;
    height: 31px;
    border-radius: 31px
}

.jv_util .menu .btn_menu {
    padding-left: 9px;
    background: #8694b7
}

.jv_util .menu .btn_menu:before, .jv_util .menu .btn_menu:after, .jv_util .menu .btn_menu span {
    display: block;
    overflow: hidden;
    width: 13px;
    height: 2px;
    font-size: 0;
    line-height: 0;
    background: #fff;
    content: ""
}

.jv_util .menu .btn_menu span {
    margin: 3px 0
}

.jv_util .menu .btn_close {
    display: none;
    background: #555
}

.jv_util .menu .btn_close span {
    display: block;
    overflow: hidden;
    position: relative;
    margin: 0 auto;
    width: 10px;
    height: 10px;
    font-size: 0;
    line-height: 0
}

.jv_util .menu .btn_close span:before, .jv_util .menu .btn_close span:after {
    position: absolute;
    top: 50%;
    left: 50%;
    width: 12px;
    height: 2px;
    background-color: #fff;
    content: ""
}

.jv_util .menu .btn_close span:before {
    -webkit-transform: translate(-50%, -50%) rotate(45deg);
    -ms-transform: translate(-50%, -50%) rotate(45deg);
    transform: translate(-50%, -50%) rotate(45deg)
}

.jv_util .menu .btn_close span:after {
    -webkit-transform: translate(-50%, -50%) rotate(135deg);
    -ms-transform: translate(-50%, -50%) rotate(135deg);
    transform: translate(-50%, -50%) rotate(135deg)
}

.jv_util .menu .layer_menu {
    display: none;
    position: absolute;
    top: 47px;
    right: 0;
    width: 208px;
    border: 1px solid #cfd1d6;
    box-sizing: border-box;
    background: #fff;
    box-shadow: 2px 3px 5px 0 rgba(0, 0, 0, 0.13)
}

.jv_util .menu .layer_menu:before {
    position: absolute;
    top: -13px;
    right: 12px;
    width: 12px;
    height: 13px;
    background-position: -90px -20px;
    content: ""
}

.jv_util .menu .layer_menu .nav {
    padding: 13px 0 16px
}

.jv_util .menu .layer_menu .nav li {
    line-height: 32px;
    text-align: center
}

.jv_util .menu .layer_menu .nav li a {
    color: #222;
    font-size: 15px;
    letter-spacing: -1px
}

.jv_util .menu .layer_menu .nav li a:hover {
    color: #5b76f5;
    font-weight: bold
}

.jv_util .menu .layer_menu .nav li a:hover:before {
    display: inline-block;
    margin-right: 6px;
    content: "-"
}

.jv_util .menu .layer_menu .nav li a:hover:after {
    display: inline-block;
    margin-left: 6px;
    content: "-"
}

.jv_util .menu .layer_menu .lately {
    padding: 16px 12px 15px;
    border-top: 1px solid #f1f1f3;
    font-size: 0;
    text-align: center
}

.jv_util .menu .layer_menu .lately span {
    display: block;
    margin: 0 auto 4px;
    width: 70px;
    height: 21px;
    border: 1px solid #e7e8ea;
    border-radius: 21px;
    box-sizing: border-box;
    color: #949494;
    font-size: 12px;
    letter-spacing: -1px;
    line-height: 16px;
    text-align: center
}

.jv_util .menu .layer_menu .lately li {
    display: inline-block;
    margin: 0 3px;
    line-height: 18px;
    vertical-align: middle
}

.jv_util .menu .layer_menu .lately li a {
    color: #666;
    font-size: 12px;
    font-weight: normal;
    letter-spacing: -1px;
    text-decoration: underline
}

.jv_util .menu.on .btn_menu {
    display: none
}

.jv_util .menu.on .btn_close {
    display: block
}

.jv_util .menu.on .layer_menu {
    display: block
}



/* 개인 마이페이지 */
#pm_contents {
    padding: 0;
    width: 100%
}

#pm_contents .jv_remote {
    top: 134px
}

#pm_contents .jview_wing:after {
    display: none
}

#pm_contents .jview_wing .inner {
    padding: 35px 0 25px
}

#btn_goto_top {
    display: none !important
}





.jv_cont {
    position: relative;
    z-index: 1;
    margin-top: 50px
}

.jv_cont > .cont {
    position: relative;
    letter-spacing: -1px
}

.jv_cont > .box {
    padding: 29px 29px 0;
    border: 1px solid #ebebeb;
    border-top-color: #999
}

.iframe_content {
    display: block;
    margin: 0 auto;
    width: 870px
}



/* 핵심정보 */
.jv_summary .col {
    float: left;
    padding: 25px 0 30px;
    width: 420px;
    border-top: 1px solid #e5e6eb;
    font-size: 14px;
    letter-spacing: -0.5px
}

.jv_summary .col + .col {
    margin-left: 30px
}

.jv_summary .col > dl {
    display: block;
    position: relative;
    margin-top: 5px;
    padding-left: 86px;
    min-height: 22px
}

.jv_summary .col > dl:first-child {
    margin-top: 0
}

.jv_summary .col > dl > dt {
    position: absolute;
    top: 0;
    left: 0;
    color: #888;
    line-height: 23px
}

.jv_summary .col > dl > dd {
    color: #444;
    line-height: 23px
}

.jv_summary .col > dl > dd strong {
    color: #4c78ea;
    font-weight: normal
}

.jv_summary .col > dl > dd .etc {
    color: #888;
    font-size: 13px
}

.jv_summary .col > dl > dd > ul {
    display: block;
    padding: 2px 0 3px;
    font-size: 13px
}

.jv_summary .col > dl > dd > ul > li {
    position: relative;
    margin-top: 4px;
    padding-left: 48px;
    line-height: 18px
}

.jv_summary .col > dl > dd > ul > li:first-child {
    margin-top: 0
}

.jv_summary .col > dl > dd > ul > li > span {
    display: block;
    position: absolute;
    top: 0;
    left: 0;
    width: 40px;
    color: #888;
    line-height: 18px
}

.jv_summary .col .btn_jview {
    padding: 0 8px 3px;
    height: 23px;
    border: 1px solid #dbdcde;
    vertical-align: top
}

.jv_summary .col .btn_tooltip:after {
    margin: 8px 0 0 6px;
    width: 7px;
    height: 4px;
    vertical-align: top;
    background-position: -120px -70px;
    content: ""
}

.jv_summary .col .opened .btn_tooltip:after {
    margin-top: 7px;
    -webkit-transform: rotate(180deg);
    -ms-transform: rotate(180deg);
    transform: rotate(180deg)
}

.jv_summary .col .btn_move_map:after {
    margin: 6px 0 0 6px;
    width: 4px;
    height: 7px;
    vertical-align: top;
    background-position: -150px -70px;
    content: ""
}

.jv_summary .col .btn_jview span {
    color: #666;
    font-size: 12px
}

.jv_summary .meta {
    clear: both;
    position: relative;
    padding-top: 12px;
    border-top: 1px solid #e5e6eb;
    font-size: 0;
    line-height: 18px;
    text-align: right
}

.jv_summary .meta:before {
    display: block;
    position: absolute;
    top: -1px;
    left: 420px;
    width: 30px;
    height: 1px;
    background: #fff;
    content: ""
}

.jv_summary .meta > li {
    display: inline-block;
    position: relative;
    color: #888;
    font-size: 13px
}

.jv_summary .meta > li > strong {
    color: #666;
    font-weight: bold;
    letter-spacing: 0
}

.jv_summary .meta > li + li {
    margin-left: 8px;
    padding-left: 10px
}

.jv_summary .meta > li + li:before {
    display: block;
    position: absolute;
    top: 50%;
    left: 0;
    margin-top: -1px;
    width: 2px;
    height: 2px;
    background: #bebfc3;
    content: ""
}

.jv_summary .meta .share {
    margin-left: 0;
    vertical-align: top
}

.jv_summary .meta .share .btn_share {
    padding: 0 0 3px;
    height: 18px;
    border: 0;
    color: #888
}

.jv_summary .meta .share .list_share {
    top: 28px
}

.jv_summary .meta .txt_noti {
    position: absolute;
    left: 0;
    margin: 0;
    padding: 0;
    color: #888;
    font-size: 13px;
    letter-spacing: -1px;
    line-height: 17px
}

.jv_summary .meta .txt_noti:before {
    display: none
}

.jv_summary .btns {
    margin-top: 15px;
    text-align: center
}

.jv_summary .btns .btn_modify {
    margin: 0 3px;
    padding: 0 13px 3px;
    height: 32px;
    border: 1px solid #e4e4e4;
    background: #f8f8f8
}
/* WMG-23274 수정 */

.jv_summary .btns .btn_modify span {
    color: #444;
    font-size: 14px
}

.jv_summary .btns .btn_modify:before {
    margin: 5px 10px 0 0;
    width: 14px;
    height: 14px;
    vertical-align: top;
    background-position: -180px -70px;
    content: ""
}

.jv_summary .btns .btn_modify_type2:before {
    display: none
}
/* WMG-23274 추가 */

.jv_summary .toolTip {
    margin: 10px 0 0 -118px;
    width: 262px
}

.jv_summary .preferred .toolTip {
    margin: 10px 0 0 0
}

/* 상세요강 */
.jv_detail {
    z-index: auto
}

/* 더보기 버튼 관련 */
.jv_cont.expand {
    z-index: 2
}

.jv_cont .cont_more {
    display: none
}

.jv_cont .btn_more_cont {
    display: block;
    position: relative;
    width: 100%;
    height: 40px;
    border: 1px solid #ebebeb;
    border-top: 0;
    box-sizing: border-box;
    color: #666;
    font-size: 14px;
    letter-spacing: -1px;
    text-decoration: none;
    background: #fff
}

.jv_cont .btn_more_cont .spr_jview:after {
    margin: 6px 0 0 9px;
    width: 13px;
    height: 8px;
    background-position: -180px -120px;
    content: "";
    opacity: 0.5
}

.jv_cont.expand .btn_more_cont {
    position: absolute
}

.jv_cont.expand .btn_more_cont .spr_jview:after {
    background-position: -180px -90px
}

.jv_cont .layer_group {
    overflow: hidden;
    position: relative;
    width: 868px;
    border: 1px solid #ebebeb;
    border-top-color: #999;
    background: #fff
}

.jv_cont .layer_group .layer {
    width: 868px;
    box-sizing: border-box
}

.jv_cont.expand .cont_more {
    display: block
}

.jv_cont.expand .layer_group {
    overflow-y: auto;
    position: absolute;
    top: 0;
    left: 0
}

.jv_cont.expand .layer_group .layer {
    height: auto;
    max-height: none
}

.jv_cont.expand .layer_group .layer .ellipsis {
    overflow: visible;
    max-height: none
}

/* 복리후생 */
.jv_benefit .layer_group .layer {
    padding: 19px 9px;
    max-height: 114px
}

.jv_benefit.expand .layer_group {
    max-height: 342px
}

.jv_benefit .row {
    display: table;
    width: 100%;
    table-layout: fixed
}

.jv_benefit .row + .row {
    margin-top: 11px
}

.jv_benefit .row .col {
    display: table-cell;
    padding: 0 20px;
    font-size: 14px;
    line-height: 22px
}

.jv_benefit .row .col dt {
    margin-bottom: 6px;
    color: #444;
    font-weight: bold
}

.jv_benefit .row .col dd {
    color: #888
}

.jv_benefit .ellipsis {
    overflow: hidden;
    max-height: 44px
}

/* 근무지 위치 */
.jv_location .wrap_mapapi {
    margin: 0 0 59px;
    height: 300px
}

.jv_location .address {
    margin-bottom: 30px
}

.jv_location .address > span {
    display: block;
    position: relative;
    padding-left: 24px;
    color: #444;
    font-size: 15px;
    line-height: 20px
}

.jv_location .address > span:before {
    position: absolute;
    top: 2px;
    left: 0;
    width: 14px;
    height: 18px;
    content: ""
}

.jv_location .txt_adr:before {
    background-position: -120px -40px
}

.jv_location .txt_subway {
    margin-top: 6px
}

.jv_location .txt_subway:before {
    background-position: -150px -40px
}

.jv_location .btn_mapview {
    display: block;
    width: 100%;
    height: 37px;
    border: 0;
    border-top: 1px solid #ebebeb;
    color: #666;
    font-size: 15px;
    letter-spacing: -1px;
    background-color: #fff
}

.jv_location .btn_mapview > span:before {
    margin-right: 8px;
    width: 18px;
    height: 18px;
    background-position: -180px -40px;
    content: ""
}

.jv_location .layer_map {
    display: none;
    top: -1px;
    z-index: 20;
    margin-left: -435px;
    padding: 35px 29px 30px
}

.jv_location.expand {
    z-index: 5
}

.jv_location.expand .layer_map {
    display: block
}

.jv_location .layer_map .container_map {
    width: 810px;
    height: 300px
}

/* 접수기간 및 방법 */
.jv_howto {
    padding-bottom: 28px
}

.jv_howto .cont.box {
    display: table;
    padding: 0;
    border-right: 0
}

.jv_howto .status {
    display: table-cell;
    position: relative;
    z-index: 2;
    padding: 22px 0 30px;
    width: 289px;
    box-sizing: border-box;
    text-align: center;
    vertical-align: middle
}

.jv_howto .status .inner {
    display: inline-block
}

.jv_howto .status .copy {
    color: #666;
    font-size: 21px;
    font-weight: bold;
    line-height: 35px
}

.jv_howto .status .copy.once {
    margin-bottom: 4px;
    font-weight: normal;
    line-height: 26px
}

.jv_howto .status .copy strong {
    color: #4876ef;
    font-weight: bold
}

.jv_howto .status .copy.end {
    color: #949494
}

.jv_howto .status .copy_sub {
    margin-bottom: 4px;
    color: #888;
    font-size: 14px;
    line-height: 21px
}

.jv_howto .info_period {
    padding: 13px 45px 0;
    text-align: left
}

.jv_howto .info_period:after {
    display: table;
    clear: both;
    content: "";
    table-layout: fixed
}

.jv_howto .info_period dt {
    float: left;
    width: 73px;
    height: 26px;
    border: 1px solid #cdcdcd;
    border-radius: 26px;
    box-sizing: border-box;
    color: #888;
    font-size: 14px;
    line-height: 22px;
    text-align: center
}

.jv_howto .info_period dd {
    float: right;
    padding: 1px 0;
    width: 124px;
    height: 26px;
    box-sizing: border-box;
    font-size: 15px;
    letter-spacing: 0;
    line-height: 22px;
    text-align: center
}

.jv_howto .info_period dt.end, .jv_howto .info_period dt.end + dd {
    margin-top: 6px;
    border-color: #4876ef;
    color: #4876ef
}

.jv_howto .status .copy.end + .info_period dt, .jv_howto .status .copy.end + .info_period dd {
    border-color: #cdcdcd;
    color: #949494
}

.jv_howto .status .sri_btn_lg {
    margin: 0 auto
}

.jv_howto .status .info_period + button, .jv_howto .status .info_period + a {
    margin-top: 25px
}

.jv_howto .info_timer {
    padding-top: 5px;
    height: 56px;
    color: #4876ef;
    font-size: 0;
    font-weight: bold;
    line-height: 36px;
    text-align: center
}

.jv_howto .info_timer .txt {
    display: block;
    height: 20px;
    font-size: 15px;
    font-weight: normal;
    line-height: 20px
}

.jv_howto .info_timer .day, .jv_howto .info_timer .time {
    display: inline-block;
    font-size: 28px;
    vertical-align: bottom
}

.jv_howto .info_timer .txt_day {
    display: none;
    margin-right: 8px;
    font-size: 20px;
    vertical-align: bottom
}

.jv_howto .info_timer .day + .txt_day {
    display: inline-block
}

.jv_howto .guide {
    display: table-cell;
    position: relative;
    z-index: 2;
    padding: 22px 30px;
    width: 580px;
    box-sizing: border-box;
    font-size: 14px;
    background: #f8f9fa
}

.jv_howto .guide:after {
    display: table;
    clear: both;
    content: "";
    table-layout: fixed
}

.jv_howto .guide > dt {
    display: block;
    float: left;
    margin-top: 4px;
    width: 88px;
    color: #888;
    line-height: 28px
}

.jv_howto .guide > dt:first-of-type {
    margin-top: 0
}

.jv_howto .guide > dd {
    display: block;
    float: left;
    margin-top: 4px;
    width: 432px;
    min-height: 28px;
    color: #666;
    line-height: 28px
}

.jv_howto .guide > dd:first-of-type {
    margin-top: 0
}

.jv_howto .guide > dd.manager {
    color: #444;
    font-weight: bold
}

.jv_howto .guide > dd.info {
    letter-spacing: 0
}

.jv_howto .guide > dd.method > button, .jv_howto .guide > dd.method > a, .jv_howto .guide > dd.method > span {
    color: #444;
    letter-spacing: -1px
}

.jv_howto .guide > dd.method > button, .jv_howto .guide > dd.method > a {
    display: inline-block;
    overflow: visible;
    line-height: normal;
    text-decoration: underline
}

.jv_howto .guide > dd.template {
    color: #444
}

.jv_howto .interview .toolTipWrap {
    margin-left: 8px;
}

.jv_howto .btn_tooltip:after {
    margin: 7px 0 0 6px;
    width: 9px;
    height: 6px;
    vertical-align: top;
    background-position: -30px -150px;
    content: ""
}

.jv_howto .opened .btn_tooltip:after {
    margin-top: 7px;
    -webkit-transform: rotate(180deg);
    -ms-transform: rotate(180deg);
    transform: rotate(180deg)
}

.jv_howto .btn_link:after {
    margin: 5px 0 0 6px;
    width: 6px;
    height: 9px;
    vertical-align: top;
    background-position: 0 -150px;
    content: ""
}

.jv_howto .toolTip {
    margin: 10px 0 0 -84px;
    width: 222px
}

.jv_howto .toolTip .attachment {
    margin: -4px 0 0 -4px;
    font-size: 0
}

.jv_howto .toolTip .attachment a {
    margin: 4px 0 0 4px
}

.jv_howto .btn_link {
    margin-left: 8px
}

.jv_howto .btn_template {
    margin-right: 8px;
    text-decoration: underline
}

.jv_howto .btn_template:after, .jv_howto .btn_download:after {
    margin: 7px 0 0 6px;
    width: 13px;
    height: 11px;
    vertical-align: top;
    background-position: -80px -150px;
    content: ""
}

.jv_howto .btn_template:after {
    margin-top: 9px
}

.jv_howto .btn_download[target="_blank"]:after {
    margin: 7px 1px 0 7px;
    width: 11px;
    height: 11px;
    background-position: -60px -150px
}

.jv_howto .btn_download span {
    display: inline-block;
    overflow: hidden;
    width: 70px;
    text-overflow: ellipsis;
    white-space: nowrap
}

.jv_howto .noti {
    position: absolute;
    bottom: -28px;
    left: 0;
    padding-left: 20px;
    color: #888;
    font-size: 13px;
    line-height: 28px
}

.jv_howto .noti:before {
    display: block;
    position: absolute;
    top: 8px;
    left: 0;
    width: 14px;
    height: 14px;
    border-radius: 14px;
    color: #fff;
    font-size: 9px;
    font-weight: bold;
    line-height: 14px;
    text-align: center;
    background: #e1e1e1;
    content: "!"
}

.jv_cont.expired .btn_modify, .jv_cont.expired .btn_interest, .jv_howto .status .btn_scrap, .jv_howto .status .btn_interest {
    display: block;
    margin: 0 auto;
    padding: 0 0 7px;
    width: 185px;
    height: 60px;
    border: 1px solid #e9e9e9;
    border-radius: 2px;
    letter-spacing: -2px;
    background: #fff
}

.jv_cont.expired .btn_modify span, .jv_cont.expired .btn_interest span, .jv_howto .status .btn_scrap span, .jv_howto .status .btn_interest span {
    display: inline-block;
    color: #888;
    font-size: 21px;
    line-height: normal
}

.jv_cont.expired .btn_interest:before, .jv_howto .status .btn_interest:before {
    margin: 8px 5px 0 0;
    width: 20px;
    height: 17px;
    background-position: -120px -150px;
    content: ""
}

.jv_cont.expired .btn_interest.on:before, .jv_howto .status .btn_interest.on:before {
    background-position: -150px -150px
}

.jv_howto .status .btn_scrap:before {
    margin: 6px 5px 0 0
}

.jv_cont.expired .btn_modify span {
    line-height: 51px
}

/* 지원자 통계 */
.jv_statics .layer_group .layer {
    overflow: hidden;
    max-height: 256px
}

.jv_statics .layer_group .layer:after {
    display: table;
    clear: both;
    content: "";
    table-layout: fixed
}

.jv_statics.expand .layer_group {
    max-height: 480px
}

.jv_statics .box_chart {
    float: left;
    position: relative;
    padding: 40px 29px;
    width: 434px;
    height: 256px;
    box-sizing: border-box
}
/* WMG-23274 수정 */

.jv_statics .box_chart:before {
    display: block;
    position: absolute;
    top: 0;
    right: -1px;
    width: 1px;
    height: 100%;
    background: #ebebeb;
    content: ""
}

.jv_statics .box_chart:after {
    display: block;
    position: absolute;
    bottom: -1px;
    left: 0;
    width: 100%;
    height: 1px;
    background: #ebebeb;
    content: ""
}

.jv_statics .box_chart .tit_stats {
    position: absolute;
    top: 39px;
    left: 29px;
    color: #444;
    font-size: 15px;
    font-weight: bold;
    line-height: 16px
}

.jv_statics .narrow {
    padding: 54px 29px 0;
    width: 289px;
    height: 256px;
    border-top: 0
}

.jv_statics .narrow:first-child {
    width: 290px
}

.jv_statics .narrow .tit_stats {
    top: 29px;
    left: 34px
}

.jv_statics .count {
    padding: 103px 0 0 0
}

.jv_statics .chart_donut {
    position: relative;
    margin: 0 27px 0 0;
    height: 172px;
    text-align: right
}

.jv_statics .narrow .chart_donut {
    margin: 0 auto;
    width: 172px;
    height: 172px
}

.jv_statics .chart_donut .donut {
    display: block;
    position: absolute;
    top: 0;
    right: 0;
    -webkit-transform: rotate(-90deg);
    -ms-transform: rotate(-90deg);
    transform: rotate(-90deg);
    -webkit-transform-origin: 50% 50%;
    -ms-transform-origin: 50% 50%
}

.jv_statics .chart_donut .legend {
    position: absolute;
    top: 0;
    right: 0;
    padding: 72px 0 0 30px;
    width: 142px;
    text-align: center
}

.jv_statics .chart_donut .legend dl {
    float: left;
    width: 60px
}

.jv_statics .chart_donut .legend dt {
    display: block;
    position: absolute;
    top: 109px;
    padding-left: 22px;
    color: #888;
    font-size: 13px;
    line-height: 18px
}

.jv_statics .chart_donut .legend dt:before {
    position: absolute;
    top: 2px;
    left: 0;
    width: 12px;
    height: 16px;
    content: ""
}

.jv_statics .chart_donut .legend dl.male dt {
    left: -137px
}

.jv_statics .chart_donut .legend dl.male dt:before {
    background-position: 0 -70px
}

.jv_statics .chart_donut .legend dl.female dt {
    left: -78px
}

.jv_statics .chart_donut .legend dl.female dt:before {
    background-position: -30px -70px
}

.jv_statics .chart_donut .legend dd {
    display: block;
    color: #949494;
    font-size: 12px;
    letter-spacing: 0;
    line-height: 16px
}

.jv_statics .chart_donut .legend dd.perc {
    overflow: hidden;
    height: 28px;
    color: #51b0f1;
    font-size: 14px;
    line-height: 28px
}

.jv_statics .chart_donut .legend dd.perc span {
    display: inline-block;
    font-size: 28px;
    font-weight: bold;
    letter-spacing: -1px;
    vertical-align: baseline
}

.jv_statics .chart_donut .legend dl.female dd.perc {
    color: #fa6a71
}

.jv_statics .chart_donut .legend .total {
    position: absolute;
    top: 64px;
    left: -176px;
    width: 176px;
    color: #444;
    font-size: 28px;
    line-height: 28px
}

.jv_statics .chart_donut .legend .total span {
    display: inline-block;
    margin: 6px 6px 0 0;
    font-size: 15px;
    line-height: 18px;
    vertical-align: top
}

.jv_statics .narrow .chart_donut .legend {
    padding-top: 54px
}

.jv_statics .narrow .chart_donut .legend .total {
    display: none
}

.jv_statics .narrow .chart_donut .legend dt {
    position: static;
    padding-left: 0;
    font-size: 14px
}

.jv_statics .narrow .chart_donut .legend dt:before {
    display: none
}

.jv_statics .narrow .chart_donut .legend dl.male dt {
    color: #51b0f1
}

.jv_statics .narrow .chart_donut .legend dl.female dt {
    color: #fa6a71
}

.jv_statics .chart_line {
    display: table;
    position: relative;
    margin: 0 0 0 87px;
    width: 288px;
    box-sizing: border-box;
    background: url(//www.saraminimage.co.kr/sri/person/jobs_view/bg_graph_stats.png) 0 0 repeat-x
}

.jv_statics .narrow .chart_line {
    margin: 11px 0 0 0;
    width: 230px
}

.jv_statics .chart_line .col {
    display: table-cell;
    position: relative;
    padding-bottom: 34px;
    height: 130px;
    text-align: center
}

.jv_statics .chart_line .col .legend {
    position: absolute;
    bottom: 0;
    left: 50%;
    margin-left: -24px;
    width: 48px;
    height: 30px;
    color: #888;
    font-size: 12px;
    letter-spacing: -1px;
    line-height: 15px
}

.jv_statics .chart_line .bar {
    position: relative;
    margin: 0 auto;
    width: 24px;
    height: 100%
}

.jv_statics .chart_line .bar .fill {
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 0;
    background-color: #acd9f8
}

.jv_statics .chart_line .bar .value {
    position: absolute;
    top: -18px;
    left: 50%;
    margin-left: -24px;
    width: 48px;
    height: 18px;
    color: #92c9f5;
    font-size: 12px;
    letter-spacing: 0;
    line-height: 18px
}

.jv_statics .chart_line .col.emph .bar .fill {
    background: #4fa4f9
}

.jv_statics .chart_line .col.emph .bar .value {
    color: #4fa4f9;
    font-weight: bold
}

.jv_statics .chart_line.sub .bar .fill {
    background-color: #aeb9f0
}

.jv_statics .chart_line.sub .bar .value {
    color: #7c90f2
}

.jv_statics .chart_line.sub .col.emph .bar .fill {
    background-color: #7c90f2
}

.jv_statics .count .total {
    width: 100%;
    height: 57px;
    text-align: center
}

.jv_statics .count.sub .total {
    position: absolute;
    top: 23px;
    left: 0
}

.jv_statics .count .total dt {
    display: block;
    margin-bottom: 2px;
    color: #444;
    font-size: 15px;
    line-height: 22px
}

.jv_statics .count .total dd {
    display: block;
    color: #666;
    font-size: 20px;
    line-height: 30px
}

.jv_statics .count .total dd span {
    color: #5b76f5;
    font-size: 28px;
    font-weight: bold;
    vertical-align: top
}

.jv_statics .count .scroll {
    overflow-x: hidden;
    overflow-y: auto;
    width: 100%;
    height: 153px
}

.jv_statics .count .detail {
    padding: 6px 29px;
    width: 430px;
    box-sizing: border-box;
    color: #888;
    font-size: 13px;
    letter-spacing: -1px;
    line-height: 34px
}
/* WMG-23274 수정 */

.jv_statics .count .detail:after {
    display: table;
    clear: both;
    content: "";
    table-layout: fixed
}

.jv_statics .count .detail dt {
    overflow: hidden;
    float: left;
    width: 310px;
    text-overflow: ellipsis;
    white-space: nowrap
}
/* WMG-23274 수정 */

.jv_statics .count .detail dd {
    overflow: hidden;
    float: left;
    width: 52px;
    text-align: right
}

.jv_statics .count .detail dd span {
    color: #5b76f5;
    letter-spacing: 0;
    white-space: nowrap
}

.jv_statics .count.narrow .detail {
    width: 290px
}
/* WMG-23274 추가 */

.jv_statics .count.narrow .detail dt {
    width: 180px
}
/* WMG-23274 추가 */

/* 인사통 */
.jv_insatong.expand .layer_group {
    max-height: 480px
}

.jv_insatong .layer_group .layer {
    padding-bottom: 15px
}

.jv_insatong .txt_qna {
    overflow: hidden;
    height: 58px;
    border-bottom: 1px solid #ebebeb;
    color: #666;
    font-size: 14px;
    line-height: 58px;
    text-align: center
}

.jv_insatong .txt_qna .company {
    display: inline-block;
    overflow: hidden;
    padding-right: 2px;
    max-width: 360px;
    font-weight: bold;
    text-overflow: ellipsis;
    vertical-align: top;
    white-space: nowrap
}

.jv_insatong .txt_qna .date {
    padding-left: 18px;
    color: #999;
    letter-spacing: 0
}

.jv_insatong .txt_qna .num {
    color: #5b76f5;
    font-weight: bold
}

.jv_insatong .item_qna {
    position: relative;
    padding: 22px 0 3px 0;
    border-top: 0;
    font-size: 0;
    letter-spacing: -1px
}

.jv_insatong .more .item:first-of-type {
    border-top: 0
}

.jv_insatong .item_qna dl {
    position: relative;
    padding: 0 0 0 20px;
    width: 640px
}

.jv_insatong .item_qna dl dt {
    margin-left: 10px;
    color: #222;
    font-size: 15px;
    line-height: 20px;
    word-break: break-all
}

.jv_insatong .item_qna dl dt > span {
    display: inline-block;
    margin-right: 6px
}

.jv_insatong .item_qna dl dd {
    display: inline-block;
    margin: 10px 0 0 10px;
    padding: 7px 20px 10px 20px;
    border: 1px solid #cfcfcf;
    border-radius: 4px;
    box-sizing: border-box;
    color: #222;
    font-size: 14px;
    line-height: 20px;
    vertical-align: top;
    word-break: break-all;
    background: #fcfcfc
}

.jv_insatong .item_qna .btn_like {
    position: absolute;
    top: 16px;
    left: 706px;
    padding: 0 7px 0 30px;
    width: 108px;
    height: 32px;
    border: 1px solid #dbdbdb;
    border-radius: 0;
    box-sizing: border-box;
    color: #666;
    font-size: 12px;
    letter-spacing: -1px;
    line-height: 28px;
    text-align: left
}

.jv_insatong .item_qna .btn_like span {
    display: inline-block;
    margin-left: 3px;
    width: 21px;
    text-align: center
}

.jv_insatong .item_qna .btn_like:after {
    display: block;
    position: absolute;
    top: 6px;
    left: 7px;
    width: 19px;
    height: 19px;
    background-position: -150px -90px;
    content: ""
}

.jv_insatong .item_qna .btn_like.liked {
    border-color: #9aa9f3;
    color: #5b76f5
}

.jv_insatong .item_qna .btn_like.liked:after {
    background-position: -150px -120px
}

/* 기업정보 */
.jv_company > .box {
    padding: 24px 14px
}

.jv_company .logo {
    overflow: hidden;
    float: left;
    width: 260px;
    height: 155px;
    line-height: 155px;
    text-align: center
}

.jv_company .logo > img {
    vertical-align: middle
}

.jv_company .wrap_info {
    padding: 0 15px;
    width: 810px
}

.jv_company .wrap_info .title {
    margin-bottom: 15px;
    height: 28px;
    font-size: 0
}

.jv_company .wrap_info .company_name {
    display: inline-block;
    overflow: hidden;
    margin-right: 5px;
    padding-right: 2px;
    max-width: 540px;
    color: #222;
    font-size: 18px;
    line-height: 25px;
    text-overflow: ellipsis;
    vertical-align: top;
    white-space: nowrap
}

.jv_company .wrap_info .title .btn_link {
    margin-left: 4px
}

.jv_company .logo + .wrap_info {
    padding: 0 15px 0 290px;
    width: 535px
}

.jv_company .logo + .wrap_info .company_name {
    max-width: 238px
}

.jv_company .info {
    margin-left: -15px
}

.jv_company .info:after {
    display: table;
    clear: both;
    content: "";
    table-layout: fixed
}

.jv_company .info dl {
    display: block;
    float: left;
    position: relative;
    margin-left: 15px;
    padding-left: 70px;
    width: 190px;
    min-height: 28px;
    line-height: 28px
}

.jv_company .info dl dt {
    position: absolute;
    top: 0;
    left: 0;
    width: 70px;
    color: #888;
    font-size: 14px;
    line-height: 28px
}

.jv_company .info dl dd {
    overflow: hidden;
    width: 100%;
    color: #444;
    font-size: 14px;
    line-height: 28px;
    text-overflow: ellipsis;
    white-space: nowrap
}

.jv_company .info dl dd > span {
    color: #949494
}

.jv_company .info dl dd > a {
    display: inline-block;
    overflow: hidden;
    padding-right: 1px;
    max-width: 99%;
    color: #4876ef;
    text-decoration: underline;
    text-overflow: ellipsis;
    vertical-align: top;
    white-space: nowrap
}

.jv_company .info dl.wide {
    width: 465px
}

.jv_company .info dl.wide dd {
    padding-top: 5px;
    line-height: 18px
}

.jv_company .btn_modify {
    position: absolute;
    top: -34px;
    right: 0
}

.jv_company .btn_modify > span {
    color: #888;
    font-size: 13px;
    letter-spacing: -1px
}

.jv_company .btn_modify:after {
    margin: 8px 0 0 8px;
    width: 6px;
    height: 9px;
    background-position: 0 -150px;
    content: ""
}
/* 기업인증 WMG-23893 */

.jv_company .noti {
    margin: 16px 10px 0 0;
    color: #adadad;
    font-size: 13px;
    line-height: 28px;
    text-align: right
}

.jv_company sup {
    display: inline-block;
    margin: -1px 0 0 2px;
    color: #adadad;
    font-size: 13px;
    vertical-align: top
}

.jv_company .sub {
    margin-top: 9px;
    padding: 0;
    border-top: 1px solid #ebebeb;
    font-size: 14px;
    text-align: center
}
/* WMG-23893 추가*/

.jv_company .sub .sub_header {
    padding: 17px 0 23px;
    border-bottom: 1px solid #ebebeb;
    color: #666;
    line-height: normal;
    background-color: #fcfcfc
}

.jv_company .sub .sub_header strong {
    margin-right: 6px
}

.jv_company .sub .mark_list {
    padding: 31px 0
}

.jv_company .sub .mark_list li {
    display: inline-block;
    position: relative;
    margin-right: 90px;
    width: 78px;
    color: #333;
    line-height: 20px;
    vertical-align: top;
    cursor: pointer
}

.jv_company .sub .mark_list li span {
    display: table-cell;
    width: 78px;
    height: 40px;
    vertical-align: middle
}

.jv_company .sub .mark_list li:last-child {
    margin-right: 0
}

.jv_company .sub .mark_list li:before {
    position: absolute;
    top: -4px;
    left: -29px;
    width: 25px;
    height: 50px;
    background: url(//www.saraminimage.co.kr/sri/company/layout/line-left.png) center center no-repeat;
    content: ''
}

.jv_company .sub .mark_list li:after {
    position: absolute;
    top: -4px;
    right: -29px;
    width: 25px;
    height: 50px;
    background: url(//www.saraminimage.co.kr/sri/company/layout/line-right.png) center center no-repeat;
    content: ''
}

.jv_company .sub .mark_list li:hover {
    text-decoration: underline
}

/* 합격자료 */
.jv_reference.expand .layer_group {
    max-height: 480px
}

.jv_reference .layer_group .layer:after {
    display: table;
    clear: both;
    content: "";
    table-layout: fixed
}

.jv_reference .reference {
    display: block;
    float: left;
    position: relative;
    padding: 32px 29px 27px;
    width: 289px;
    height: 239px;
    box-sizing: border-box
}

.jv_reference .reference:first-child {
    width: 290px
}

.jv_reference .reference:before {
    display: block;
    position: absolute;
    top: 0;
    right: -1px;
    width: 1px;
    height: 100%;
    background: #ebebeb;
    content: ""
}

.jv_reference .reference:after {
    display: block;
    position: absolute;
    bottom: -1px;
    left: 0;
    width: 100%;
    height: 1px;
    background: #ebebeb;
    content: ""
}

.jv_reference .cont_more .reference:after {
    display: none
}

.jv_reference .reference dl dt {
    margin-bottom: 12px;
    color: #444;
    font-size: 18px
}

.jv_reference .reference dl dd {
    overflow: hidden;
    height: 69px;
    color: #888;
    font-size: 14px;
    line-height: 23px
}

.jv_reference .reference.cvletter dd {
    height: 115px
}

.jv_reference .reference.news dd {
    height: 115px
}

.jv_reference .reference dl dd.view {
    margin-top: 44px;
    padding-top: 7px;
    height: 23px;
    border-top: 1px solid #ebebeb;
    text-align: right
}

.jv_reference .reference dl dd.view:before {
    margin: 8px 5px 0 0;
    width: 16px;
    height: 10px;
    background-position: -60px -70px;
    content: ""
}

.jv_reference .reference dl dd.view span {
    color: #666;
    font-weight: bold;
    letter-spacing: 0
}

.jv_reference .reference.bg {
    background-image: url(//www.saraminimage.co.kr/sri/person/jobs_view/bg_reference.jpg)
}

.jv_reference .reference.bg.cvletter {
    background-position: 0 0
}

.jv_reference .reference.bg.news {
    background-position: 0 -250px
}

.jv_reference .reference.bg dl dt, .jv_reference .reference.bg dl dd, .jv_reference .reference.bg dl dd.view, .jv_reference .reference.bg dl dd.view span {
    color: #fff
}

.jv_reference .reference .link {
    padding-top: 28px;
    text-align: center
}

.jv_reference .reference .link span {
    display: block;
    color: #444;
    font-size: 18px;
    line-height: 26px
}

.jv_reference .reference .link .guide {
    display: inline-block;
    margin-top: 44px;
    border-bottom: 1px solid #bcbcbc;
    color: #666;
    font-size: 14px;
    line-height: 18px
}

.jv_reference .reference .more {
    display: none;
    position: absolute;
    top: 0;
    left: 0;
    z-index: 2;
    padding-top: 80px;
    width: 100%;
    height: 100%;
    box-sizing: border-box;
    text-align: center;
    background: rgba(68, 91, 153, 0.92)
}

.jv_reference .reference .more span {
    display: block;
    color: #fff;
    font-size: 15px;
    line-height: 31px
}

.jv_reference .reference .more .count {
    display: inline-block;
    border-bottom: 1px solid #b6bdd3;
    font-size: 18px;
    line-height: 24px
}

.jv_reference .reference .more .count strong {
    font-weight: bold;
    letter-spacing: 0
}

.jv_reference .reference:focus .more, .jv_reference .reference:hover .more {
    display: block
}

/* 태그 */
.jv_footer {
    margin-top: 41px
}

.jv_footer .cont {
    padding-top: 90px
}

.jv_footer .tags {
    position: absolute;
    top: 0;
    left: 0;
    z-index: 1;
    width: 100%;
    border: 1px solid #fff;
    box-sizing: border-box;
    font-size: 0
}

.jv_footer .tags.on {
    border: 1px solid #ebebeb;
    background: #fff
}

.jv_footer .tags ul {
    overflow: hidden;
    padding: 8px 47px 0 0;
    height: 38px;
    line-height: 38px
}

.jv_footer .tags.on ul {
    overflow-y: auto;
    padding: 8px 8px 0 0;
    height: auto;
    max-height: 152px
}

.jv_footer .tags ul li {
    display: inline-block;
    margin-left: 8px;
    vertical-align: top
}

.jv_footer .tags ul li a {
    display: block;
    padding: 6px 10px 8px;
    color: #444;
    font-size: 12px;
    line-height: 16px;
    background: #f1f3f9
}

.jv_footer .tags .btn_tags {
    display: none;
    position: absolute;
    top: 8px;
    right: -1px;
    z-index: 2;
    width: 32px;
    height: 30px;
    border: 1px solid #e5e6eb;
    background: #fff
}

.jv_footer .tags .btn_tags:before, .jv_footer .tags .btn_tags:after {
    display: block;
    position: absolute;
    background: #949494;
    content: ""
}

.jv_footer .tags .btn_tags:before {
    top: 9px;
    left: 14px;
    width: 2px;
    height: 10px
}

.jv_footer .tags .btn_tags:after {
    top: 13px;
    left: 10px;
    width: 10px;
    height: 2px
}

.jv_footer .tags.on .btn_tags {
    top: auto;
    bottom: -30px
}

.jv_footer .tags.on .btn_tags:before {
    display: none
}

.jv_footer .utils {
    height: 28px;
    line-height: 28px;
    text-align: right
}

.jv_footer .utils .share {
    margin: 0 4px 0 0;
    vertical-align: top
}

.jv_footer .utils .copy {
    float: left;
    position: relative;
    padding-left: 20px;
    color: #444;
    font-size: 13px
}

.jv_footer .utils .copy:before {
    display: block;
    position: absolute;
    top: 8px;
    left: 0;
    width: 14px;
    height: 14px;
    border-radius: 14px;
    color: #fff;
    font-size: 9px;
    font-weight: bold;
    line-height: 14px;
    text-align: center;
    background: #e1e1e1;
    content: "!"
}

.jv_footer .utils .copy a {
    display: inline-block;
    line-height: 18px;
    text-decoration: underline
}
/* 마감표시 */

.expired {
    z-index: auto;
    padding: 54px 0 64px;
    text-align: center;
    background: #f7f7fa
}

.expired .copy {
    margin-bottom: 6px;
    color: #222;
    font-size: 26px;
    letter-spacing: -1px;
    line-height: 38px
}

.expired .copy strong {
    color: #4876ef;
    font-weight: bold
}

.expired .copy + .copy {
    margin-bottom: 28px;
    font-size: 16px
}

.expired .copy + .copy strong {
    display: inline-block;
    overflow: hidden;
    max-width: 440px;
    text-align: left;
    text-overflow: ellipsis;
    vertical-align: top;
    white-space: nowrap
}

/* SMS공유 WMG-12367 from /css/nudge/content.css */
.layer_pop_manage {
    position: absolute;
    z-index: 30;
    border: 1px solid #444;
    box-sizing: border-box;
    font-family: "Malgun Gothic", gulim, dotum, sans-serif;
    background: #fff;
    box-shadow: 0 1px 1px 1px rgba(0, 0, 0, 0.12)
}

.layer_pop_manage legend {
    visibility: hidden;
    overflow: hidden;
    margin-left: -1px;
    width: 1px;
    height: 1px
}

.layer_pop_manage.layer_sms {
    padding: 0;
    width: 440px
}

.layer_pop_manage.layer_sms_error {
    padding: 0;
    width: 357px
}

.layer_pop_manage.layer_sms_error .txt_notice {
    margin: 10px 0 25px
}

.layer_pop_manage .layer_manage_wrap {
    position: relative;
    z-index: 100;
    padding: 35px 30px 30px
}

.layer_pop_manage .layer_manage_wrap h4 {
    margin-bottom: 8px;
    color: #444;
    font-size: 19px;
    font-weight: bold;
    letter-spacing: -1px;
    text-align: left
}

.layer_pop_manage .layer_manage_wrap .txt_sms_desc {
    color: #666;
    font-size: 13px;
    letter-spacing: -1px;
    line-height: 21px;
    text-align: left
}

.layer_pop_manage .layer_manage_wrap .txt_notice {
    margin: 0;
    padding: 0;
    color: #666;
    font-size: 13px;
    letter-spacing: -1px;
    line-height: 21px;
    text-align: left
}

.layer_pop_manage .layer_manage_wrap .txt_link {
    color: #6b80f1;
    text-decoration: underline
}

.layer_manage_wrap .bottom_btn_wrap {
    margin-top: 20px;
    text-align: center
}

.layer_manage_wrap .bottom_btn_wrap .btn_basic_type05.btn_send {
    padding: 5px 33px 9px
}

.layer_manage_wrap .btn_layer_close {
    position: absolute;
    top: 14px;
    right: 14px;
    width: 17px;
    height: 17px;
    border: 0 none;
    background: url(//www.saraminimage.co.kr/ui/layout/person_mypage_2017/btn_layer_close.png) no-repeat 50% 50%;
    cursor: pointer
}

.layer_manage_wrap .btn_layer_close span {
    overflow: hidden;
    font-size: 0;
    line-height: 0;
    text-indent: -100%;
    white-space: nowrap
}

.default_table {
    width: 100%;
    border-spacing: 0;
    border-collapse: collapse;
    font-family: "Malgun Gothic", gulim, dotum, sans-serif;
    table-layout: fixed
}

.default_table.nudge_table {
    margin-top: 15px;
    border-top: 1px solid #686868
}

.default_table.nudge_table th {
    padding-left: 20px;
    height: 43px;
    border-bottom: 1px solid #eaeaea;
    box-sizing: border-box;
    color: #666;
    font-size: 13px;
    font-weight: normal;
    letter-spacing: -1px;
    text-align: left;
    background: #f8f8f8;
    _height: 50px
}

.default_table.nudge_table td {
    padding: 9px 15px 9px;
    border-bottom: 1px solid #eaeaea;
    box-sizing: border-box;
    color: #444;
    font-family: "Malgun Gothic", gulim, dotum, sans-serif;
    font-size: 13px;
    text-align: left;
    word-break: break-all;
    _padding: 4px 10px
}

.default_table.nudge_table td input {
    padding: 0 5px;
    height: 32px;
    border: 1px solid #dbdbdb;
    box-sizing: border-box;
    font-family: "Malgun Gothic", gulim, dotum, sans-serif;
    font-size: 13px;
    line-height: 32px;
    vertical-align: middle
}

.default_table.nudge_table td.tbl_send {
    overflow: hidden
}

.default_table.nudge_table .txt_phone {
    float: left;
    margin-right: 10px;
    padding-top: 6px;
    color: #444;
    font-size: 13px
}

.default_table.nudge_table .btn_change {
    float: left
}

.btn_basic_type04 {
    display: inline-block;
    padding: 5px 13px 9px;
    height: 32px;
    border: 1px solid #8f8f8f;
    box-sizing: border-box;
    color: #fff;
    font-size: 13px;
    letter-spacing: -1px;
    vertical-align: top;
    background-color: #9c9c9c
}

.btn_basic_type05 {
    display: inline-block;
    padding: 5px 13px 9px;
    height: 32px;
    border: 1px solid #5f75eb;
    box-sizing: border-box;
    color: #fff;
    font-size: 13px;
    letter-spacing: -1px;
    vertical-align: top;
    background-color: #6b80f1
}

.btn_basic_type06 {
    display: inline-block;
    padding: 5px 13px 9px;
    height: 32px;
    border: 1px solid #6b80f1;
    box-sizing: border-box;
    color: #6b80f1;
    font-size: 13px;
    letter-spacing: -1px;
    vertical-align: top;
    background-color: #fff
}

.default_table caption {
    display: none
}

/* 플로팅 배너 WMG-19101 from /css/nudge/content.css */
.main_nudge:before, .main_nudge .btn_close:after {
    background: url(//www.saraminimage.co.kr/sri/person/bg/bg_resume_nudge.png) no-repeat 0 0
}

.main_nudge {
    position: fixed;
    right: 23px;
    bottom: 23px;
    z-index: 1000;
    padding: 17px 35px 21px 116px;
    min-width: 354px;
    height: 100px;
    box-sizing: border-box;
    background: #313131
}

.main_nudge:before {
    position: absolute;
    top: 0;
    left: 0;
    width: 100px;
    content: ""
}

.main_nudge .desc_nudge {
    color: #fff;
    font-size: 15px;
    letter-spacing: -1px;
    line-height: 21px;
    vertical-align: middle
}

.main_nudge.main_nudge .link_nudge {
    text-decoration: underline
}

.main_nudge .btn_close {
    overflow: hidden;
    position: absolute;
    top: 0;
    right: 0;
    width: 39px;
    height: 40px;
    border: 0;
    background: none
}

.main_nudge .btn_close:after {
    display: block;
    margin: 0 auto;
    width: 12px;
    height: 12px;
    background-position: 0 -280px;
    content: ""
}

.resume_nudge1 {
    height: 100px
}

.resume_nudge1:before {
    height: 100px;
    background-position: 0 0
}

.main_nudge.resume_nudge1 .link_nudge {
    color: #acf1f3
}

.resume_nudge2 {
    height: 78px
}

.resume_nudge2:before {
    height: 78px;
    background-position: 0 -110px
}

.main_nudge.resume_nudge2 .link_nudge {
    color: #ff98a4
}

.resume_nudge3 {
    height: 78px
}

.resume_nudge3:before {
    height: 78px;
    background-position: 0 -200px
}

.main_nudge.resume_nudge3 .link_nudge {
    color: #86c2ff
}



@media print {
    * {
        -webkit-print-color-adjust: exact !important;
        print-color-adjust: exact !important;
        color-adjust: exact !important
    }
}

/* 관리자내 공고뷰 미리보기 */
.desc_txt_preview {
    _position: absolute;
    top: 0;
    _top: expression(eval(document.body.scrollTop));
    left: 0;
    z-index: 100;
    width: 100%;
    margin: 0 auto;
    padding: 5px;
    text-align: center
}

.desc_txt_preview .txt {
    padding: 0;
    margin: 0;
    font-size: 13px;
    font-weight: bold;
    color: #FFF;
    letter-spacing: -1px;
    line-height: 24px
}

.desc_txt_preview .txt .point {
    font-weight: bold;
    font-size: 16px;
    color: #FFF
}

.job_view_preview {
    padding-top: 130px
}

/* 지원 버튼 색상 변경 예외 처리 */
.jv_header .btn_apply .sri_btn_lg span, .jv_howto .cont.box .sri_btn_lg span {
    border: 1px solid #dbdbdb;
    color: #666;
    background: #fff
}

.jv_header .btn_apply .sri_btn_lg span.sri_btn_immediately, .jv_howto .cont.box .sri_btn_lg span.sri_btn_immediately {
    border: 1px solid #ff8d5a;
    color: #fff;
    background: #ff8d5a
}

.jv_header .btn_apply .sri_btn_lg span.sri_btn_tobe_apply, .jv_howto .cont.box .sri_btn_lg span.sri_btn_tobe_apply {
    border: 1px solid #888;
    color: #fff;
    background: #888
}

.jv_header .btn_apply .sri_btn_lg span.sri_btn_expired_apply, .jv_howto .cont.box .sri_btn_lg span.sri_btn_expired_apply {
    border: 1px solid #888;
    color: #fff;
    background: #888
}

/* AI헌팅 flag, nudge */
.flag_reward {
    display: inline-block;
    padding: 0 8px;
    margin-right: 6px;
    background: #ffe581;
    color: #363225;
    font-size: 13px;
    letter-spacing: -0.5px;
    line-height: 28px;
    vertical-align: top
}

.flag_reward:after {
    display: inline-block;
    width: 16px;
    height: 16px;
    margin: 7px 0 0 2px;
    background: url(//www.saraminimage.co.kr/sri/common/img/img_reward_flag.png) 0 0 no-repeat;
    content: "";
    vertical-align: top;
}

.wrap_nudge {
    margin-top: 30px;
    border: 2px solid #f4f4f4;
    background: #fff;
    text-align: center;
    padding: 11px 0;
}

.wrap_nudge .nudge_reward {
    display: inline-block;
    position: relative;
    padding: 0 0 0 66px;
    line-height: 24px;
    color: #444;
    letter-spacing: -0.5px;
    font-size: 16px;
}

.wrap_nudge .nudge_reward:before {
    position: absolute;
    display: block;
    width: 50px;
    height: 66px;
    left: 0;
    top: -35px;
    background: url(//www.saraminimage.co.kr/sri/person/img/img_reward_small.png) 0 0 no-repeat;
    content: ""
}

/* 즉시지원 버튼 */
.sri_btn_lg {overflow:hidden;margin:0;padding:0;width:212px;height:60px;border:0;box-sizing:border-box;text-align:center;vertical-align:top;cursor:pointer}
.sri_btn_lg span {display:inline-block;width:210px;height:58px;border:1px solid #dce2ef;color:#8a92a6;font-family:"Malgun Gothic",sans-serif !important;font-size:20px !important;font-weight:bold;letter-spacing:-2px;line-height:54px;background:#fff}
.sri_btn_lg span.sri_btn_immediately {position:relative;border:1px solid #ff6d6a;color:#ff6d6a}
.sri_btn_md {overflow:hidden;margin:0;padding:0;width:160px;height:32px;border:0;box-sizing:border-box;text-align:center;vertical-align:top;cursor:pointer}
.sri_btn_md span {display:inline-block;width:158px;height:30px;border:1px solid #dce2ef;color:#8a92a6;font-family:"Malgun Gothic",sans-serif !important;font-size:14px !important;letter-spacing:-1px;line-height:28px;background:#fff}
.sri_btn_md span.sri_btn_immediately {position:relative;border:1px solid #ff6d6a;color:#ff6d6a}
.sri_btn_sm {overflow:hidden;margin:0;padding:0;width:103px;height:28px;border:0;box-sizing:border-box;text-align:center;vertical-align:top;cursor:pointer}
.sri_btn_sm span {display:inline-block;width:101px;height:26px;border:1px solid #dce2ef;color:#8a92a6;font-family:"Malgun Gothic",sans-serif !important;font-size:13px !important;letter-spacing:-1px;line-height:24px;background:#fff}
.sri_btn_sm span.sri_btn_immediately {position:relative;border:1px solid #ff6d6a;color:#ff6d6a}
.sri_btn_xs {overflow:hidden;margin:0;padding:0;height:18px;border:0;box-sizing:border-box;text-align:center;vertical-align:top;cursor:pointer}
.sri_btn_xs span {display:inline-block;padding-top:1px;width:78px;height:18px;box-sizing:border-box;border:1px solid #dce2ef;color:#8a92a6;font-family:"Malgun Gothic",sans-serif !important;font-size:12px !important;letter-spacing:-1px;line-height:12px;vertical-align:top;background:#fff}
.sri_btn_xs span.sri_btn_immediately {position:relative;width:52px;border:1px solid #ff6d6a;color:#ff6d6a}
.sri_btn_xss {overflow:hidden;margin:0;padding:0;width:38px;height:18px;border:0;box-sizing:border-box;text-align:center;vertical-align:top;cursor:pointer}
.sri_btn_xss span {display:inline-block;padding-right:1px;width:35px;height:16px;font-family:"Malgun Gothic",sans-serif;font-size:11px;letter-spacing:-1px;line-height:13px;vertical-align:top}
span.sri_btn_passdata {border:1px solid #ff9494;color:#f76a6a;background:#fff}
span.sri_btn_coverletter {border:1px solid #dbdbdb;color:#666;background:#fff}
span.sri_btn_insatong {border:1px solid #aeb7dc;color:#8692c8;background:none}

/* checkbox, radio 기본 */
.sri_check {display:inline-block;position:relative;height:18px;vertical-align:middle;cursor:pointer}
.sri_check .inp_check {position:absolute;top:0;left:0;width:18px;height:18px;opacity:0.01;filter:alpha(opacity=0.1)}
.sri_check .txt_check {display:block;padding:0 7px 0 29px;height:18px;color:#444;font-size:13px;letter-spacing:-1px;line-height:15px;background:url(//www.saraminimage.co.kr/ui/form/ico_checkbox.png) 0 0 no-repeat}
.sri_check .txt_check.blank {padding:0;width:18px}
.sri_check.check_on .txt_check {background-position:0 -50px}
.sri_check.small {height:15px}
.sri_check.small .inp_check {top:1px;width:13px;height:13px;cursor:pointer}
.sri_check.small .txt_check {padding:0 7px 0 20px;height:15px;font-size:12px;line-height:12px;background-position:0 -99px}
.sri_check.small.check_on .txt_check {background-position:0 -149px}
.sri_radio_wrap {display:inline-block;height:18px;line-height:18px;vertical-align:middle}
.sri_radio_wrap .sri_check {vertical-align:top}
.sri_radio .txt_check {background-position:0 -200px}
.sri_radio.check_on .txt_check {background-position:0 -250px}
.sri_radio_wrap.small {height:15px;line-height:15px}
.sri_radio.small .txt_check {background-position:0 -299px}
.sri_radio.small.check_on .txt_check {background-position:0 -349px}
.sri_check.disabled .txt_check {background:url(//www.saraminimage.co.kr/sri/common/ico/sri_ico_checkbox.png) 0 -400px no-repeat}/* WMG-14952 */
.sri_check.sri_radio.disabled .txt_check {background:url(//www.saraminimage.co.kr/sri/common/ico/sri_ico_checkbox02.png) 0 -450px no-repeat}
.sri_check.sri_radio.disabled.check_on .txt_check {background:url(//www.saraminimage.co.kr/ui/form/ico_checkbox.png) 0 -250px no-repeat}/* WMG-14952 */

/* select 기본 */
.sri_select {display:inline-block;position:relative;height:32px;color:#444;font-size:13px;vertical-align:middle}
.sri_select .selected {overflow:hidden;padding:0 29px 1px 11px;width:100%;height:32px;border:1px solid #dbdbdb;box-sizing:border-box;font-family:Malgun Gothic, "맑은 고딕",dotum,"돋움", sans-serif;letter-spacing:-1px;text-align:left;text-overflow:ellipsis;white-space:nowrap;background-color:#fff;background-position:100% 0;cursor:pointer}
.sri_select .first {color:#aeaeae}
.sri_selected .selected {border-color:#ababab}
.sri_selected .first {border-color:#dbdbdb}
.sri_select .ico_arr {background-image:url(//www.saraminimage.co.kr/ui/form/ico_arr.png);background-repeat:no-repeat}
.sri_select .list_opt {display:none;overflow:hidden;overflow-y:auto;position:absolute;top:31px;right:0;left:0;z-index:10;max-height:204px;border:1px solid #ababab;box-sizing:content-box;background-color:#fff}
.sri_select .list_opt .link_opt {display:block;overflow:hidden;padding:0 11px;height:32px;box-sizing:border-box;color:#444;font-size:13px;line-height:30px;text-align:left;text-overflow:ellipsis;white-space:nowrap;word-break:normal}
.sri_select .list_opt .link_opt:hover {background-color:#ebebeb}
.sri_select .list_opt .on .link_opt {background-color:#ebebeb}
.sri_select.reverse .list_opt {top:auto;bottom:31px}
.sri_select.open .selected {z-index:10;border-color:#ababab;background-position:100% -50px}
.sri_select.open .list_opt {display:block}
.sri_select_lg {width:100%;height:50px;font-size:15px}
.sri_select_lg .selected {padding-right:39px;padding-left:19px;height:50px;font-weight:bold;background-position:98% 10px}
.sri_select_lg .first {font-weight:normal}
.sri_select_lg .list_opt .link_opt {padding:0 19px;height:34px;font-size:15px;line-height:34px}
.sri_select_lg.open .selected {background-position:98% -41px}
.sri_select_lg.open .list_opt {top:49px}
.sri_select_lg.reverse .list_opt {top:auto;bottom:49px}
.sri_select.disabled {opacity:0.3;filter:alpha(opacity=30)}
.sri_select.disabled .selected {color:#000;background-color:#e8e8e8;cursor:default}
.sri_select .selected:disabled {background-color:#f8f8f8;cursor:default}

/* button */
.btn_basic_type01 {display:inline-block;padding:5px 13px 9px;height:32px;border:1px solid #ababab;box-sizing:border-box;color:#444;font-size:13px;letter-spacing:-1px;line-height:normal;vertical-align:top;background-color:#fff}
.btn_basic_type01.btn_type_arrow:after {display:inline-block;margin-left:7px;width:4px;height:7px;background:url(//www.saraminimage.co.kr/ui/layout/person_mypage_2017/ico_resume_manage01.png) no-repeat -1px -241px;content:""}
.btn_basic_type02 {display:inline-block;padding:5px 13px 9px;height:32px;border:1px solid #dbdbdb;box-sizing:border-box;color:#444;font-size:13px;letter-spacing:-1px;line-height:normal;vertical-align:top;background-color:#fff}
.btn_basic_type03 {display:inline-block;padding:5px 13px 9px;height:32px;border:1px solid #e4e4e4;box-sizing:border-box;color:#444;font-size:13px;letter-spacing:-1px;line-height:normal;vertical-align:top;background-color:#f8f8f8}
.btn_basic_type04 {display:inline-block;padding:5px 13px 9px;height:32px;border:1px solid #8f8f8f;box-sizing:border-box;color:#fff;font-size:13px;letter-spacing:-1px;line-height:normal;vertical-align:top;background-color:#9c9c9c}
.btn_basic_type05 {display:inline-block;padding:5px 13px 9px;height:32px;border:1px solid #5f75eb;box-sizing:border-box;color:#fff;font-size:13px;letter-spacing:-1px;line-height:normal;vertical-align:top;background-color:#6b80f1}
.btn_basic_type01:hover,
.btn_basic_type02:hover,
.btn_basic_type03:hover {color:#444}
.btn_basic_type04:hover,
.btn_basic_type05:hover {color:#fff}
.btn_basic_type05.btn_type_arrow:after {display:inline-block;margin-left:7px;width:4px;height:7px;background:url(//www.saraminimage.co.kr/ui/layout/person_mypage_2017/ico_resume_manage01.png) no-repeat -45px -241px;content:""}
.btn_basic_type04.btn_type_arrow:after{display:inline-block;width:4px;height:7px;margin-left:7px;background:url(//www.saraminimage.co.kr/ui/layout/person_mypage_2017/ico_resume_manage01.png) no-repeat -45px -241px;content:'';}
.btn_basic_type01.btn_view,
.btn_basic_type04.btn_print,
.btn_basic_type05.btn_set {background-image:url(//www.saraminimage.co.kr/ui/layout/person_mypage_2017/ico_resume_manage01.png);background-repeat:no-repeat}
.btn_basic_type01.btn_view {padding-right:26px}
.btn_basic_type04.btn_print,
.btn_basic_type05.btn_set {padding-left:41px}
.btn_basic_type01.btn_view {background-position:84px -510px}
.btn_basic_type04.btn_print {background-position:15px -114px}
.btn_basic_type05.btn_set {background-position:13px -154px}
.btn_smaller_type01 {display:inline-block;padding:1px 9px 3px 9px;height:23px;border:1px solid #e4e4e4;box-sizing:border-box;color:#444;font-size:12px;letter-spacing:-1px;line-height:17px;background-color:#fff}
.btn_smaller_type01:hover {color:#444}
.btn_smaller_type01.btn_more:after {display:inline-block;margin-left:8px;width:4px;height:7px;background:url(//www.saraminimage.co.kr/ui/layout/person_mypage_2017/ico_resume_manage01.png) no-repeat -1px -241px;content:""}
.btn_biggest_type01 {display:inline-block;padding:13px 8px 14px;width:200px;height:50px;border:1px solid #5f75eb;box-sizing:border-box;color:#fff;font-size:16px;line-height:normal;text-align:center;vertical-align:top;background-color:#6b80f1}/* WMG-17554 */
.btn_biggest_type02 {display:inline-block;padding:13px 10px 14px;height:50px;border:1px solid #555;box-sizing:border-box;color:#fff;font-size:16px;line-height:normal;text-align:center;vertical-align:top;background-color:#555}/* WMG-17554 */
.btn_biggest_type03 {display:inline-block;padding:13px 10px 14px;height:50px;border:1px solid #ababab;box-sizing:border-box;color:#fff;color:#666;font-size:16px;line-height:normal;text-align:center;vertical-align:top;background-color:#fff}/* WMG-17554 */
.btn_biggest_type04 {display:inline-block;padding:13px 10px 14px;height:50px;border:1px solid #707070;box-sizing:border-box;color:#fff;color:#fff;font-size:16px;line-height:normal;text-align:center;vertical-align:top;background-color:#707070}/* WMG-17554 */
.btn_basic2 {display:inline-block;padding:6px 14px 8px;height:40px;border:1px solid #dbdbdb;box-sizing:border-box;color:#666;font-size:15px;letter-spacing:-1px;line-height:normal;vertical-align:top;background-color:#fff}
.btn_basic2.type02 {border:1px solid #444;color:#fff;background-color:#555}
.btn_basic2.type03 {border:1px solid #8f8f8f;color:#fff;background-color:#9c9c9c}
.btn_basic2.type05 {border:1px solid #5f75eb;color:#fff;background-color:#7185e8}
.btn_basic2.type05:disabled {border:1px solid #8f8f8f;color:#fff;background-color:#9c9c9c}
.btn_basic2.btn_copy:before {display:inline-block;margin-right:8px;width:15px;height:14px;vertical-align:middle;background:url(//www.saraminimage.co.kr/sri/common/ico/ico_sri_input2.png) 0 -150px no-repeat;content:""}
.btn_basic2.btn_reset:before {display:inline-block;margin-right:8px;width:15px;height:14px;vertical-align:middle;background:url(//www.saraminimage.co.kr/sri/common/ico/ico_sri_input2.png) 0 -200px no-repeat;content:""}
.btn_basic2.btn_pdown:before {display:inline-block;margin-right:8px;width:15px;height:14px;vertical-align:middle;background:url(//www.saraminimage.co.kr/sri/common/ico/ico_sri_input2.png) 0 -250px no-repeat;content:""}

/* form */
.sri_input[type="number"],
.sri_input[type="text"] {padding:0 11px 1px 11px;height:32px;border:1px solid #dbdbdb;box-sizing:border-box;color:#444;font-size:13px;line-height:28px;vertical-align:middle;background:#fff}
.sri_input[type="number"]:disabled,
.sri_input[type="text"]:disabled,
.sri_input.readonly {background-color:#ccc;opacity:0.3;filter:alpha(opacity=30)}
.sri_marks {display:inline-block;padding:0 10px 0 3px;font-size:13px;vertical-align:middle}

.sri_input2 {display:inline-block;position:relative;font-size:0;vertical-align:top}
.sri_input2>input[type="checkbox"] {clip:rect(1px, 1px, 1px, 1px);position:absolute;width:1px;height:1px}
.sri_input2>input[type="checkbox"]+label {display:block;padding-left:24px;height:20px;box-sizing:border-box;color:#444;font-size:13px;letter-spacing:-1px;line-height:normal;background:url(//www.saraminimage.co.kr/sri/common/ico/ico_sri_input2.png) 0 0 no-repeat;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none}
.sri_input2>input[type="checkbox"]:checked+label {background-position:0 -50px}
.sri_input2>input[type="checkbox"]:disabled+label {background-position:0 -100px}
.sri_input2>input[type="radio"] {display:block;position:absolute;top:0;left:0;z-index:2;margin:0;padding:0;width:100%;height:100%;opacity:0;-webkit-appearance:none;-moz-appearance:none;appearance:none}
.sri_input2>input[type="radio"]+label {display:block;position:relative;z-index:1;width:100%;height:100%;border:1px solid #dbdbdb;box-sizing:border-box;color:#666;font-size:15px;letter-spacing:-1px;line-height:38px;text-align:center;background:#fff;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none}
.sri_input2>input[type="radio"]:checked+label {border:1px solid #444;color:#fff;background:#555}
.sri_input2>input[type="radio"]:disabled+label {border:1px solid #dbdbdb;color:#969696;background:#f8f8f8}
.inp_rdo {display:inline-block;position:relative}
.inp_rdo>input {position:absolute;top:0;width:1px;height:1px;outline:none;opacity:0;pointer-events:none;filter:alpha(opacity=0)}
.inp_rdo>.lbl {display:inline-block;color:#222;font-family:"Malgun Gothic",gulim,dotum,sans-serif;font-size:14px;letter-spacing:-0.5px;line-height:22px;vertical-align:top}
.inp_rdo>.lbl:after {display:table;clear:both;content:"";table-layout:fixed}
.inp_rdo>.lbl:before {float:left;margin:0 8px 0 0;width:24px;height:24px;vertical-align:top;background:url(//www.saraminimage.co.kr/sri/common/ico/sri_ico_checkbox03.png) no-repeat 0 0;content:""}
.inp_rdo>input:focus+.lbl:before {outline:5px auto -webkit-focus-ring-color;outline:1px dotted invert}
.inp_rdo input+.lbl:before {background-position:0 -500px}
.inp_rdo input[disabled="disabled"]+.lbl:before {background-position:0 -600px}
.inp_rdo.checked .lbl:before,
.inp_rdo input:checked+.lbl:before {background-position:0 -550px}
.inp_rdo.small>.lbl {font-size:13px;line-height:16px}
.inp_rdo.small>.lbl:before {margin:0 8px 0 0;width:18px;height:18px;background-position:0 -200px}
.inp_rdo.small input[disabled="disabled"]+.lbl:before {background-position:0 -450px}
.inp_rdo.small.checked .lbl:before,
.inp_rdo input:checked+.lbl:before {background-position:0 -250px}

.sri_input2>input[type="text"] {padding:6px 14px 8px 14px;width:100%;height:40px;border:1px solid #555;box-sizing:border-box;color:#444;font-size:15px;font-weight:bold;letter-spacing:0;line-height:normal;vertical-align:top;background:#fff}
.sri_input2>select {padding:6px 30px 8px 14px;height:40px;border:1px solid #555;box-sizing:border-box;color:#444;font-size:15px;font-weight:bold;letter-spacing:0;line-height:normal;background:#fff url(//www.saraminimage.co.kr/sri/common/ico/ico_sri_input2.png) 100% -600px no-repeat;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none;-webkit-appearance:none;-moz-appearance:none;appearance:none}
.sri_input2>select::-ms-expand {display:none}
.sri_input2>input::-ms-clear {display:none}
.sri_input>input::-ms-clear {display:none}

/* WMG-17554 */
.sri_input3>input[type="checkbox"]:focus+label,
.sri_input3>input[type="radio"]:focus+label {outline:1px solid #94b6ed;-webkit-box-shadow:0 0 2px #94b6ed;box-shadow:0 0 2px #94b6ed}

.sri_input3 {display:inline-block;position:relative;font-size:0;vertical-align:top}
.sri_input3>input[type="checkbox"] {clip:rect(1px, 1px, 1px, 1px);position:absolute;width:1px;height:1px}
.sri_input3>input[type="checkbox"]+label {display:block;padding-left:29px;height:22px;box-sizing:border-box;color:#444;color:#444;font-size:13px;letter-spacing:-1px;line-height:22px;background:url(//www.saraminimage.co.kr/ui/form/ico_checkbox.png) 0 2px no-repeat;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none}
.sri_input3>input[type="checkbox"]:checked+label {background-position:0 -48px}
.sri_input3>input[type="checkbox"]:disabled+label {background:url(//www.saraminimage.co.kr/sri/common/ico/sri_ico_checkbox.png) 0 -398px no-repeat}

.sri_input3.small>input[type="checkbox"]+label {padding-left:20px;height:17px;font-size:12px;line-height:16px;background-position:0 -98px}
.sri_input3.small>input[type="checkbox"]:checked+label {background-position:0 -148px}

@media screen\0 {/* ie9 */
    .sri_input2>select {padding:6px;background-image:none}
}
_:-ms-lang(x),
.sri_input2>select {padding:6px 14px 8px 14px;background:#fff url(//www.saraminimage.co.kr/sri/common/ico/ico_sri_input2.png) 100% -600px no-repeat}  /* ie10 */
_:-ms-fullscreen,
:root .sri_input2>select {padding:6px 14px 8px 14px;background:#fff url(//www.saraminimage.co.kr/sri/common/ico/ico_sri_input2.png) 100% -600px no-repeat}  /* ie11 */

.sri_input2.spin_button>button {width:32px;height:32px;border:1px solid #dbdbdb;box-sizing:border-box;background:#fff url(//www.saraminimage.co.kr/sri/common/ico/ico_sri_input2.png) 0 -550px no-repeat}
.sri_input2.spin_button>button.plus {background-position:0 -500px}
.sri_input2.spin_button>button.minus+button.plus {margin-left:5px}
.sri_input2.spin_button.number {position:relative;padding:0 39px}
.sri_input2.spin_button.number>input[type="text"] {padding:6px 0 8px 0;width:44px;text-align:center}
.sri_input2.spin_button.number>button {width:39px;height:40px;background-color:#f8f8f8}
.sri_input2.spin_button.number>button.minus {position:absolute;top:0;left:0;margin:0;border-right:0;background-position:0 -450px}
.sri_input2.spin_button.number>button.plus {position:absolute;top:0;right:0;border-left:0;background-position:0 -400px}
.sri_input2_text {display:inline-block;margin:0 14px 0 1px;color:#444;font-size:15px;letter-spacing:-1px;line-height:40px;vertical-align:top}

.sri_input2>input::-webkit-input-placeholder {color:#949494}
.sri_input2>input:-ms-input-placeholder {color:#949494}
.sri_input2>input::-ms-input-placeholder {color:#949494}
.sri_input2>input::placeholder {color:#949494}

.sri_input2>input[type="checkbox"]:focus+label,
.sri_input2>input[type="radio"]:focus+label {outline:1px solid #94b6ed;-webkit-box-shadow:0 0 2px #94b6ed;box-shadow:0 0 2px #94b6ed}

.sri_input2.right>input {text-align:right}
.sri_input2.large>input[type="text"] {height:50px;font-size:19px}
.sri_input2.large+.sri_input2_text {line-height:50px}




.sri_input2.date {padding-right:39px;box-sizing:border-box}
.sri_input2.date>input[type="text"] {padding:6px 0 8px 0;border:1px solid #dbdbdb;font-weight:normal;text-align:center;background:#fff}
.sri_input2.date>button {display:block;position:absolute;top:0;right:0;width:40px;height:40px;border:#dbdbdb 1px solid;color:transparent;font-size:0;background:#f8f8f8 url(//www.saraminimage.co.kr/sri/common/ico/ico_sri_input2.png) 0 -350px no-repeat}
.ui-datepicker.sri_input2_datepicker {padding:15px}
.ui-widget.ui-widget-content.sri_input2_datepicker {width:220px}
.ui-datepicker.sri_input2_datepicker .ui-datepicker-title {width:auto;height:34px;font-size:14px;line-height:34px}
.ui-datepicker.sri_input2_datepicker table {font-size:14px}
.ui-datepicker.sri_input2_datepicker th {padding:0 5px 7px 5px}
.ui-datepicker.sri_input2_datepicker td span,
.ui-datepicker.apply_datepicker td a {font-family:"Malgun Gothic", "맑은 고딕", dotum, "돋움", sans-serif;font-size:12px}
.ui-datepicker.sri_input2_datepicker .ui-datepicker-week-end .ui-state-active {color:#fff}
.ui-datepicker.sri_input2_datepicker .ui-datepicker-title .ui-datepicker-year,
.ui-datepicker.sri_input2_datepicker .ui-datepicker-title .ui-datepicker-month {margin:0;width:auto;vertical-align:middle}
.ui-datepicker.sri_input2_datepicker .ui-datepicker-title .ui-datepicker-year {margin-right:2px}
.ui-datepicker.sri_input2_datepicker .ui-datepicker-title .ui-datepicker-month {margin-left:8px}
.ui-datepicker.sri_input2_datepicker .ui-datepicker-prev,
.ui-datepicker.sri_input2_datepicker .ui-datepicker-next {width:34px;height:34px;line-height:34px}

/* wmg-19307 */
.sri_input3>select {padding:3px 22px 4px 11px;height:32px;border:1px solid #b8b8b8;box-sizing:border-box;color:#222;font-size:13px;letter-spacing:0;line-height:normal;background:#fff url(//www.saraminimage.co.kr/sri/common/ico/ico_sri_input2.png) 100% -604px no-repeat;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none;-webkit-appearance:none;-moz-appearance:none;appearance:none}
.sri_input3>select::-ms-expand {display:none}
@media screen\0 {/* ie9 */
    .sri_input3>select {padding:3px;background-image:none}
}
_:-ms-lang(x),
.sri_input3>select {padding:0 11px 1px 11px;background:#fff url(//www.saraminimage.co.kr/sri/common/ico/ico_sri_input2.png) 100% -604px no-repeat}  /* ie10 */
_:-ms-fullscreen,
:root .sri_input3>select {padding:0 11px 1px 11px;background:#fff url(//www.saraminimage.co.kr/sri/common/ico/ico_sri_input2.png) 100% -604px no-repeat}  /* ie11 */

/* 테이블 (my디자인) */
.tbl_fieldset {width:100%;border-bottom:#eaeaea 1px solid;border-collapse:collapse;table-layout:fixed}
.tbl_fieldset caption {visibility:hidden;overflow:hidden;position:absolute;top:-1px;left:-1px;height:0;font-size:0}
.tbl_fieldset button,
.tbl_fieldset input {vertical-align:middle}
.tbl_fieldset th {padding:9px 0 9px 20px;border-top:#eaeaea 1px solid;color:#666;font-size:13px;font-weight:normal;text-align:left;background:#f7f7f7}
.tbl_fieldset td {padding:9px 0 9px 15px;border-top:#eaeaea 1px solid;color:#444;font-size:0;letter-spacing:0;text-align:left;word-spacing:0;background:#fff}
.tbl_fieldset td .sub {margin-top:7px}
.tbl_fieldset .expiredin {display:inline-block;margin:7px 0 0 3px;color:#f00;font-size:12px;letter-spacing:-1px;vertical-align:top}
.tbl_fieldset .expiredin strong {font-weight:normal;letter-spacing:0}
.tbl_fieldset td .sri_input,
.tbl_fieldset td .sri_select,
.tbl_fieldset td .btn_basic_type04 {margin-right:6px}
.tbl_fieldset td .sri_check {margin-right:12px}
.tbl_fieldset td .sri_radio {margin-right:14px}

/* layerpopup */
.layer_pop_manage {position:absolute;left:50%;z-index:102;padding:35px 30px 30px;border:1px solid #444;box-sizing:border-box;background:#fff;box-shadow:0 1px 1px 1px rgba(0,0,0,0.12)}
.layer_pop_manage h4 {margin-bottom:9px;color:#444;font-size:20px;font-weight:bold;letter-spacing:-1px;text-align:left}
.layer_pop_manage h4 span {font-weight:normal}
.layer_pop_manage .tit_layer {display:block;margin-bottom:9px;color:#444;font-size:20px;font-weight:bold;letter-spacing:-1px;text-align:left} /* WMG-13739 IT직종 개편 넛지 */
.layer_pop_manage .bottom_btn_wrap {margin-top:10px;text-align:center}
.layer_pop_manage .bottom_btn_wrap button,
.layer_pop_manage .bottom_btn_wrap a {margin-left:1px}
.layer_pop_manage .bottom_btn_wrap button:first-child,
.layer_pop_manage .bottom_btn_wrap a:first-child {margin-left:0}
.layer_pop_manage .btn_layer_close {position:absolute;top:14px;right:14px;width:17px;height:17px;border:0 none;background:url(//www.saraminimage.co.kr/ui/layout/person_mypage_2017/btn_layer_close.png) no-repeat 50% 50%}
.layer_pop_manage .btn_layer_close span {overflow:hidden;font-size:0;line-height:0;text-indent:-100%;white-space:nowrap;*text-indent:0}

/* page_nation */
.page_nation {margin-top:20px;text-align:center;vertical-align:middle}
.page_nation a,
.page_nation strong {display:inline-block;margin-right:1px;padding:5px 2px 0 2px;min-width:26px;height:25px;border:1px solid #dfdfdf;color:#888;font-family:"Malgun Gothic","맑은 고딕", dotum,"돋움",sans-serif;font-size:14px;text-decoration:none;vertical-align:middle;background:#fff}
.page_nation a:hover {text-decoration:none}
.page_nation strong {height:25px;border:1px solid #444;color:#444}
.page_nation .prev,
.page_nation .next {position:relative;padding:5px 0 0;width:30px;border:0 none;color:#444;line-height:16px;vertical-align:top}
.page_nation .prev {margin-right:19px}
.page_nation .next {margin-left:19px}
.page_nation .prev:after,
.page_nation .next:after {display:block;position:absolute;top:9px;width:7px;height:13px;background:url(//www.saraminimage.co.kr//ui/public_recruit/ico_recruit_paging.png) no-repeat 0 0;content:""}
.page_nation .prev:after {left:-11px}
.page_nation .next:after {right:-11px;background-position:-21px 0}
.list_info_bottom+.wrap_paging .page_nation {margin-top:0}

.default_paging {margin-top:20px;text-align:center;vertical-align:middle}
.default_paging a {cursor:pointer}
.default_paging a:hover {text-decoration:none}
.default_paging .num,
.default_paging strong {display:inline-block;margin-right:1px;padding-top:5px;width:24px;height:20px;color:#666;font-size:12px;font-weight:bold;text-decoration:none;vertical-align:middle;background-color:#fff}
.default_paging strong {width:22px;border:1px solid #667df9;color:#667df9}
.default_paging .next,
.floating_area,
.default_paging .pre_n {display:inline-block;padding-top:3px;height:22px;border:0 none;vertical-align:top}
.default_paging .spr {display:inline-block;overflow:hidden;padding-right:18px;padding-left:18px;height:20px;color:#666;line-height:20px;vertical-align:middle;cursor:pointer}

/* 로그인 레이어 */
.pop_login_layer {position:fixed;top:50%;left:50%;z-index:9999;margin-left:-247px;width:504px;height:405px;box-sizing:border-box;font-family:"맑은 고딕",dotum,sans-serif;font-size:12px;text-align:left;background:#fff;background:url(//www.saraminimage.co.kr/ui/common/bg_shadow.png) 0 0 no-repeat}
.pop_login_layer.no_shadow {position:static;top:0;left:0;margin:0;width:494px;height:395px;background:none}
.pop_login_layer .outer {position:relative;padding:42px 50px 0 50px;width:494px;height:395px;border:1px solid #666;box-sizing:border-box;background:#fff}
.pop_login_layer.no_shadow .outer {border:1px solid #fff}
.pop_login_layer .inner {width:392px}
.pop_login_layer legend {display:block;visibility:visible;position:relative;left:auto !important;width:392px;height:auto;color:#444;font-family:"맑은 고딕",dotum,sans-serif;font-size:20px;font-weight:bold;letter-spacing:-2px;line-height:inherit;text-align:center;text-indent:0 !important}
.pop_login_layer legend strong {color:#425ad4}
.pop_login_layer .setting:after {display:block;visibility:hidden;clear:both;height:0;content:"."}
.pop_login_layer .setting {display:inline-block;position:relative;margin-top:28px;margin-bottom:7px;height:20px;line-height:20px}
.pop_login_layer .setting {display:block}
* html .pop_login_layer .setting {height:1%}
.pop_login_layer .setting {zoom:1}
.pop_login_layer .setting label {display:block;float:left;margin-right:15px;height:16px;color:#666;font-family:"맑은 고딕",dotum,sans-serif;letter-spacing:-1px;line-height:16px;vertical-align:top}
.pop_login_layer .setting input {display:block;float:left;margin-top:3px;margin-right:5px;font-family:"맑은 고딕",dotum,sans-serif;vertical-align:top;*margin-top:-1px}
.pop_login_layer .setting .dsc_ly {display:block;position:absolute;bottom:30px;left:-12px;z-index:10;width:272px;height:55px;*left:-8px}
.pop_login_layer .setting .dsc_ly .btn_ly_close {display:block;overflow:hidden;position:absolute;top:0;right:0;width:17px;height:17px;border:0 none;color:transparent;font-size:0;line-height:0;text-indent:-100%;text-shadow:none;white-space:nowrap;background:url(//www.saraminimage.co.kr/ui/common/btn_login_ly_close.png) 50% 50% no-repeat;cursor:pointer;*text-indent:0}
.pop_login_layer .setting .dsc_ly .dsc_arr {display:block;position:absolute;top:53px;left:12px;width:13px;height:8px;line-height:normal;background:url(//www.saraminimage.co.kr/ui/common/btn_login_ly_arr.png) 0 0 no-repeat}
.pop_login_layer .setting .dsc_ly .dsc_txt {margin:0;padding:6px 10px 7px;border:1px solid #bababa;color:#888;font-size:11px;letter-spacing:-1px;line-height:20px;background-color:#fff}
.pop_login_layer .setting .dsc_ly .dsc_txt strong {color:#555;font-weight:normal}
.pop_login_layer .setting .ssl_login_box {display:block;overflow:hidden;float:left;position:relative;padding-right:33px;width:60px;height:20px;box-sizing:border-box;color:#666;font-family:"맑은 고딕",dotum,sans-serif;font-size:12px;line-height:16px;vertical-align:top}
.pop_login_layer .setting .ssl_login_hide {position:absolute;top:-25px;left:0;z-index:-1;width:0;height:0;line-height:0}
.pop_login_layer .setting .ssl_login_box .label_type {display:block;position:absolute;top:2px;right:0;margin:0;padding:0;width:29px;height:16px;text-indent:-9999px;vertical-align:top;background:url(//www.saraminimage.co.kr/ui/common/btn_autologin2_off.png) 0 0 no-repeat;cursor:pointer}
.pop_login_layer .setting .ssl_login_box .label_type.on {background:url(//www.saraminimage.co.kr/ui/common/btn_autologin2_on.png) 0 0 no-repeat}
.pop_login_layer .login-form {position:relative;width:392px;height:85px}
.pop_login_layer .login-form label {position:absolute;top:0;left:0;z-index:2;margin:0;padding:8px 20px;width:294px;height:40px;box-sizing:border-box;color:#959595;font-family:"맑은 고딕",dotum,sans-serif;font-size:14px;font-weight:bold;line-height:23px}
.pop_login_layer .login-form input {position:absolute;z-index:1;margin:0;padding:8px 20px;width:293px;height:40px;border:none;box-sizing:border-box;color:#444;font-family:"맑은 고딕",dotum,sans-serif;font-size:14px;font-weight:bold;letter-spacing:0;line-height:23px;background-color:transparent}
.pop_login_layer .login-form .id-input-box {position:absolute;top:0;left:0;width:294px;height:43px;border-top:1px solid #cdcdcd;border-bottom:1px solid #e4e4e4;border-left:1px solid #cdcdcd;box-sizing:border-box}
.pop_login_layer .login-form .pw-input-box {position:absolute;top:43px;left:0;width:294px;height:42px;border-bottom:1px solid #cdcdcd;border-left:1px solid #cdcdcd;box-sizing:border-box}
.pop_login_layer .login-form .btn-login {position:absolute;top:0;left:294px;width:98px;height:85px;border:none;color:#fff;font-family:"맑은 고딕",dotum,sans-serif;font-size:16px;font-weight:bold;background:#6b80f1;cursor:pointer}
.pop_login_layer .signup-forgotten {overflow:hidden;position:relative;margin:18px 0 29px;width:100%;height:16px;font-family:"맑은 고딕",dotum,sans-serif;font-size:13px;letter-spacing:-1px}
.pop_login_layer .signup-forgotten .sign-up {float:left;margin-right:10px;color:#666;font-weight:bold;text-decoration:none}
.pop_login_layer .signup-forgotten .forgotten {float:left;color:#666;text-decoration:none}
.pop_login_layer .signup-forgotten span {display:block;float:left;zoom:1;position:relative;margin-right:8px;padding:0;width:1px;height:16px;font-size:0;line-height:16px;background:url(//www.saraminimage.co.kr/ui/common/bg_vline.png) no-repeat 0 50%}
.pop_login_layer .signup-forgotten .service_info_txt {position:absolute;right:0}
.pop_login_layer .signup-forgotten .service_info_txt img {margin-top:2px;margin-left:6px;vertical-align:top}
.pop_login_layer .signup-forgotten .service_info_txt:hover {color:#555}
.pop_login_layer .social_login_layer {display:inline-block;zoom:1;width:100%;*display:inline}
.pop_login_layer .social_login_layer:after {display:block;clear:both;content:""}
.pop_login_layer .social_login_layer li {display:block;float:left;zoom:1;position:relative;margin-bottom:6px;width:50%;height:38px;box-sizing:border-box;background-color:#fff}
.pop_login_layer .social_login_layer a {display:block;zoom:1;padding-left:50px;height:38px;border:1px solid #ddd;box-sizing:border-box;color:#666;font-family:"맑은 고딕",sans-serif;font-size:12px;letter-spacing:-1px;line-height:34px;text-decoration:none;*line-height:36px}
.pop_login_layer .social_login_layer .sl_naver {margin-right:3px;background:url(//www.saraminimage.co.kr/ui/social_login/icon_sns_naver_m.png) 20px 12px no-repeat}
.pop_login_layer .social_login_layer .sl_facebook {margin-left:3px;background:url(//www.saraminimage.co.kr/ui/social_login/icon_sns_facebook_m.png) 22px 9px no-repeat}
.pop_login_layer .social_login_layer .sl_kakao {margin-right:3px;background:url(//www.saraminimage.co.kr/ui/social_login/icon_sns_kakao_m.png) 17px 9px no-repeat}
.pop_login_layer .social_login_layer .sl_google {margin-left:3px;background:url(//www.saraminimage.co.kr/ui/social_login/icon_sns_google_m.png) 18px 9px no-repeat}
.pop_login_layer .pop_login_layer_close {overflow:hidden;position:absolute;top:0;right:0;width:43px;height:43px;border:0;color:transparent;font-size:0;line-height:0;text-indent:-100%;text-shadow:none;white-space:nowrap;background:url(//www.saraminimage.co.kr/ui/common/btn_login_close.png) no-repeat 50% 50%;cursor:pointer;*text-indent:0}
.pop_login_layer .login-form input:-webkit-autofill {-webkit-box-shadow:0 0 0 1000px white inset !important}
.pop_login_layer .txt_mail {margin:24px 0 0 1px;padding-left:12px;color:#999;letter-spacing:-1px;line-height:20px;text-indent:-12px}
.pop_login_layer .txt_mail .btn_mail {display:inline-block;margin-left:6px;padding:0 20px 0 7px;height:15px;border:1px solid #aaa;color:#999;font-size:11px;line-height:13px;text-indent:0;background:#f8f8f8 url(//www.saraminimage.co.kr/ui/common/bul_arr_mail.gif) no-repeat 66px 3px;cursor:pointer}
.pop_login_layer_local {width:494px;height:auto;min-height:278px;background:none;-webkit-box-shadow:5px 5px 5px 0 rgba(0,0,0,0.15);-moz-box-shadow:5px 5px 5px 0 rgba(0,0,0,0.15);box-shadow:5px 5px 5px 0 rgba(0,0,0,0.15);_height:278px}
.pop_login_layer_local .outer {padding-bottom:36px;height:auto;min-height:270px;_height:270px}
.pop_login_layer_local .signup-forgotten {margin-bottom:0}
.pop_login_layer.pop_login_layer_local .signup-forgotten {margin-bottom:29px}

/* 외부 인증 오류 */
.pop_social_certify_error {padding:0 20px;height:640px;background:#f4f5f7}
.pop_social_certify_error * {margin:0;padding:0;font-family:Malgun Gothic,"맑은 고딕",AppleGothic,Dotum,"돋움",sans-serif}
.pop_social_certify_error h1 {padding:32px 0 2px;font-size:21px;font-weight:bold;letter-spacing:-2px}
.pop_social_certify_error>p {padding:12px 0 0;color:#777;font-size:13px;letter-spacing:-1px;line-height:20px}
.pop_social_certify_error>div {margin:17px 0 0;padding:30px 0 31px 30px;border:1px solid #e8e8e8;letter-spacing:-1px;line-height:20px;text-align:left;background:#fff}
.pop_social_certify_error>div strong {color:#444;font-size:15px;font-weight:bold}
.pop_social_certify_error>div strong span {position:relative;top:3px}
.pop_social_certify_error>div ol li {padding:12px 0 0 13px;color:#777;font-size:13px;text-indent:-13px}
.pop_social_certify_error>div ol li span {color:#444}
.pop_social_certify_error>ul {padding:25px 0 0 30px;line-height:18px;text-align:left}
.pop_social_certify_error>ul li a {color:#999;font-size:12px}

/* 관심기업 추가/해제 버튼 */
button.interested_corp {display:block;width:16px;height:16px;border:0;vertical-align:middle;background:url(//www.saraminimage.co.kr/ui/btn/sp_interested_corp.png) 0 1px no-repeat;cursor:pointer;*display:inline;*zoom:1;_overflow:hidden}
button.interested_corp span {display:block;overflow:hidden;font-size:0;text-indent:100%;white-space:nowrap;_display:inline}
button.interested_on {background-position:-20px 1px}
button.interested_corp_large {width:20px;height:20px;background-position:-40px 1px}
button.interested_large_on {background-position:-60px 1px}

/* 툴팁 */
.sri_layer_tooltip {display:inline-block;vertical-align:top;*display:inline;*zoom:1}
.sri_tooltip {display:block;position:absolute;z-index:200;padding:8px 10px 10px;min-width:33px;border:1px solid #444;color:#888;font-family:"Malgun Gothic",dotum,gulim,sans-serif;font-size:12px;background-color:#fff;box-shadow:0 1px 3px #d8d8d8}
.sri_tooltip.on {display:block}
.sri_tooltip.size_wide {padding:16px 20px}
.sri_tooltip [class^="tail_"] {position:absolute;width:7px;height:11px;background:url(//www.saraminimage.co.kr/sri/common/tooltip_tail.png) no-repeat}
.sri_tooltip .tail_left_top {top:25px;left:-7px;background-position:-11px 0}
.sri_tooltip .tail_left_center {top:50%;left:-7px;margin-top:-6px;background-position:-11px 0}
.sri_tooltip .tail_left_bottom {bottom:25px;left:-7px;background-position:-11px 0}
.sri_tooltip .tail_right_top {top:25px;right:-7px;background-position:-18px 0}
.sri_tooltip .tail_right_center {top:50%;right:-7px;margin-top:-6px;background-position:-18px 0}
.sri_tooltip .tail_right_bottom {right:-7px;bottom:25px;background-position:-18px 0}
.sri_tooltip .tail_top_left {top:-7px;left:25px;width:11px;height:7px;background-position:0 -7px}
.sri_tooltip .tail_top_center {top:-7px;left:50%;margin-left:-6px;width:11px;height:7px;background-position:0 -7px}
.sri_tooltip .tail_top_right {top:-7px;right:25px;width:11px;height:7px;background-position:0 -7px}
.sri_tooltip .tail_bottom_left {bottom:-7px;left:25px;width:11px;height:7px;background-position:0 0}
.sri_tooltip .tail_bottom_center {bottom:-7px;left:50%;margin-left:-6px;width:11px;height:7px;background-position:0 0}
.sri_tooltip .tail_bottom_right {right:25px;bottom:-7px;width:11px;height:7px;background-position:0 0}
.sri_tooltip .tooltip_inner {line-height:15px}
.sri_tooltip .tooltip_inner.txt_left {text-align:left}
.sri_tooltip .tooltip_inner.txt_center {text-align:center}
.sri_tooltip .tooltip_inner .title {overflow:hidden;color:#666;font-size:12px;font-weight:normal;line-height:18px}
.sri_tooltip .btn_close_tooltip {position:absolute;top:0;right:0;padding:10px;border:0;background:none}
.sri_tooltip .btn_close_tooltip:after {display:block;width:13px;height:13px;background:url(//www.saraminimage.co.kr/ui/layer/button/spr_lefttop_arrow.png) 0 0 no-repeat;content:""}
.sri_tooltip .apply_text_notice {margin-top:15px;color:#666;font-size:13px}
.sri_tooltip .apply_text_notice:first-child {margin-top:0}
.sri_tooltip .apply_text_notice dt {position:relative;padding-left:25px;font-weight:bold}
.sri_tooltip .apply_text_notice dt span {position:absolute;top:0;left:0;color:#6d82f3;font-size:13px}
.sri_tooltip .apply_text_notice dd {position:relative;margin-top:5px;padding-left:25px;font-size:12px}
.sri_tooltip .apply_text_notice dd span {position:absolute;top:0;left:0;font-size:13px;font-weight:bold}

/* h1 title */
/*
셋중 하나로 통일
연봉정보 등 :.tit_area > .title_contents
지역직종 타이틀 :.tit_area > .detail_tit
공채 타이틀 :.wrap_title_recruit > .title_common :선택
*/
/* 공용타이틀 */
.wrap_title_recruit {position:relative;padding:0 0 50px}
.wrap_title_recruit.title_type2 {border-bottom:1px solid #444}
.wrap_title_recruit .desc {display:block;margin:0 0 10px 0;color:#666;font-size:15px;letter-spacing:-3px;line-height:16px}
.wrap_title_recruit .title_common {display:inline-block;color:#444;font-size:32px;font-weight:normal;letter-spacing:-4px;line-height:36px;vertical-align:top}
.wrap_title_recruit .title_common .num {letter-spacing:-2px}
.wrap_title_recruit .value {display:inline-block;padding:8px 0 0 5px;color:#444;font-size:20px;font-weight:normal;letter-spacing:0;line-height:22px;vertical-align:top}
.wrap_title_recruit .value:before {margin-right:8px;color:#dbdbdb;font-size:15px;line-height:22px;vertical-align:top;content:"|"}
.wrap_title_recruit .value .sort+.sort:before {margin-right:7px;color:#dbdbdb;font-size:15px;line-height:22px;vertical-align:top;content:"|"}
.wrap_title_recruit .value b {color:#ff6410;font-size:22px;font-weight:normal;line-height:24px;vertical-align:top}
.wrap_title_recruit.title_view .title_common {font-size:28px;letter-spacing:-3px}
.wrap_title_recruit.title_keyword {margin:40px 0 45px}
.wrap_title_recruit.title_keyword .title_common {font-size:22px;letter-spacing:-2px}
.wrap_title_recruit.title_keyword .value {padding-top:11px;font-size:16px;letter-spacing:-2px;line-height:18px}
.wrap_title_recruit.title_keyword .value b {font-size:16px;letter-spacing:0;line-height:19px}
.wrap_title_recruit.title_keyword .value:before {line-height:18px}
.wrap_title_recruit button {border:0;font-family:"Malgun Gothic", dotum, gulim, sans-serif;letter-spacing:-1px;background:none;cursor:pointer}
.wrap_title_recruit .tit_btn_area {position:absolute;top:45px;right:0}
.wrap_title_recruit .tit_btn_area .btn_get {margin-top:10px;padding-left:28px;color:#555;font-size:13px;font-weight:bold;background:url(//www.saraminimage.co.kr/ui/recruit_detail_search/search_sprite.png) no-repeat 0 -216px}

/* wmg-19307 */
/* company ui pattern _추후 중앙 ui pattern 작업 시 이동 예정 */
/* tabmenu */
.tabList {width:100%}
.tabList:after {display:table;clear:both;content:"";table-layout:fixed}
.tabList>li {float:left;width:20%;height:100%;border:1px solid #dedede;border-right:0;border-bottom-color:#666;box-sizing:border-box;color:#222;font-size:16px;vertical-align:top;background:#fbfbfb}
.tabList>li .inTab {display:block;padding:16px 0 15px;width:100%;text-align:center}
.tabList>li.select {position:relative;z-index:1;border-color:#666;border-right:1px solid #666;border-bottom-color:#fff;border-left:1px solid #666;font-weight:bold;background:#fff}
.tabList>li .inTab:hover,
.tabList>li .inTab:focus {text-decoration:underline}
.tabList>li.select:first-child {border-left:1px solid #666}
.tabList>li:last-child {border-right:1px solid #dedede}
.tabList>li.select:last-child {border-right:1px solid #666}
.tabList>li.select+li {border-left:0}

/* Table */
.tblType {border-top:1px solid #666}
.tblType table {width:100%;border-spacing:0;border-collapse:collapse;table-layout:fixed}
.tblType thead th,
.tblType tbody th,
.tblType tbody td {margin:0;border-bottom:1px solid #e1e1e1;box-sizing:border-box;color:#222;font-size:14px;letter-spacing:-1px;line-height:18px;text-align:center}
.tblType thead th {padding:14px 0;height:47px;border-bottom:0}
.tblType thead th,
.tblType tbody th {font-weight:bold;background-color:#f7f7f7}
.tblType tbody td {padding:14px 10px;height:47px;white-space:normal;word-break:break-all}
.tblType tbody td.ellipsis {overflow:hidden;text-overflow:ellipsis;white-space:nowrap}
.tblType.tblScroll {overflow-y:scroll;border-bottom:1px solid #e1e1e1}
.tblType.tblScroll tbody tr:last-child th,
.tblType.tblScroll tbody tr:last-child td {border-bottom:0}
.tblType .txtLeft {text-align:left}
.tblType .txtRight {text-align:right}
.tblType .tblVisited>td {color:#888;background-color:#fafafa}
.tblType .tblVisited>td .imgBlur {top:0;left:0;background-color:#fff;opacity:0.4;filter:alpha(opacity=40)}
.tblType.sizeL td {height:70px}
.tblType.sizeEtc td {padding:15px 10px;height:auto}

/* Pagination */
.pagiNation {font-size:13px;letter-spacing:-1px;text-align:center}
.pagiNation .btnPrev {display:inline-block;margin-right:20px;padding-left:10px;width:40px;height:35px;box-sizing:border-box;line-height:33px;vertical-align:middle;background:url(//www.saraminimage.co.kr/sri/company/applicant/btn_page_prev.png) 0 50% no-repeat}
.pagiNation .btnNext {display:inline-block;margin-left:20px;padding-right:10px;width:40px;height:35px;box-sizing:border-box;line-height:33px;vertical-align:middle;background:url(//www.saraminimage.co.kr/sri/company/applicant/btn_page_next.png) 100% 50% no-repeat}
.pagiNation span.page,
.pagiNation button.page,
.pagiNation a.page {display:inline-block;margin:0 2px;width:33px;height:35px;box-sizing:border-box;line-height:33px;vertical-align:middle}
.pagiNation span.page {border:1px solid #313131;color:#313131;font-size:15px;font-weight:bold}


/* COMPONENTS 정의 v2019 */
.inpSel {display:inline-block;height:32px;border:1px solid #dbdbdb;box-sizing:border-box;vertical-align:middle;background:#fff}
.inpSel select {padding:0 29px 1px 11px;width:100%;height:30px;border:none;box-sizing:border-box;color:#444;font-family:"Malgun Gothic",gulim,dotum,sans-serif;font-size:13px;letter-spacing:-1px;line-height:29px;text-align:left;vertical-align:top;white-space:nowrap;text-overflow:ellipsis;background:url(//www.saraminimage.co.kr/sri/common/ico/ico_select_arr.png) no-repeat 100% 0;-webkit-appearance:none;-moz-appearance:none}
.inpSel select option {height:32px;font-size:13px;color:#444;line-height:16px}
.inpSel select option:hover {background:#d0d0d0;cursor:pointer}
.inpSel select::-ms-expand {opacity:0}
.inpSel.disabled {border-color:#dbdbdb;background-color:#e8e8e8;opacity:0.3}
.inpSel.disabled select {color:#000}

.inpSel select {padding:5px 6px 6px\0/} /* ie9 */
_:-ms-lang(x), .inpSel select {padding:5px 11px 6px} /* ie10 */
_:-ms-fullscreen, :root .inpSel select {padding:5px 11px 6px} /* ie11 */
.inpSel select[disabled]::-ms-value {color:#d0d0d0}

.inpTypo {padding:0 11px 1px 11px;width:100%;height:32px;border:1px solid #dbdbdb;box-sizing:border-box;color:#444;font-size:13px;line-height:28px;vertical-align:middle;background:#fff}
.inpTypo:disabled,
.inpTypo:read-only {background-color:#ccc;opacity:0.3}
.searchTypoBox {width:auto;height:auto;font-size:0}
.searchTypoBox .inpTypo {display:inline-block;padding-left:10px;width:187px;border:1px solid #ddd;border-right:0 none;-webkit-box-sizing:border-box;box-sizing:border-box;color:#888;letter-spacing:-1px}
.searchTypoBox .btnTypoSearch ,
.searchTypoBox .btnTypoReset {padding-bottom:4px;height:32px;border:0;cursor:pointer}
.searchTypoBox .btnTypoSearch {width:52px;-webkit-box-sizing:border-box;box-sizing:border-box;background-color:#6d82f3;color:#fff;font-size:13px;font-weight:bold;vertical-align:top}
.searchTypoBox .btnTypoReset {margin-left:5px;width:32px;font-size:0;vertical-align:middle;background:url(//www.saraminimage.co.kr/sri/common/ico/ico_search_btn_reset.png) no-repeat -2px -2px}
.dataTypoBox {padding-right:39px;position:relative;display:inline-block;box-sizing:border-box}
.dataTypoBox .inpTypo {padding:6px 0 8px 0;height:40px;border:1px solid #dbdbdb;font-weight:normal;text-align:center;background:#fff;font-size:15px}
.dataTypoBox > button {display:block;position:absolute;top:0;right:0;width:40px;height:40px;border:#dbdbdb 1px solid;color:transparent;font-size:0;background:#f8f8f8 url(//www.saraminimage.co.kr/sri/common/ico/ico_sri_input2.png) 0 -350px no-repeat}
.ui-datepicker.dataTypoDatepicker {padding:15px}
.ui-widget.ui-widget-content.dataTypoDatepicker {width:220px}
.ui-datepicker.dataTypoDatepicker .ui-datepicker-title {width:auto;height:34px;font-size:14px;line-height:34px}
.ui-datepicker.dataTypoDatepicker table {font-size:14px}
.ui-datepicker.dataTypoDatepicker th {padding:0 5px 7px 5px}
.ui-datepicker.dataTypoDatepicker td span,
.ui-datepicker.apply_datepicker td a {font-family:"Malgun Gothic", "맑은 고딕", dotum, "돋움", sans-serif;font-size:12px}
.ui-datepicker.dataTypoDatepicker .ui-datepicker-week-end .ui-state-active {color:#fff}
.ui-datepicker.dataTypoDatepicker .ui-datepicker-title .ui-datepicker-year,
.ui-datepicker.dataTypoDatepicker .ui-datepicker-title .ui-datepicker-month {margin:0;width:auto;vertical-align:middle}
.ui-datepicker.dataTypoDatepicker .ui-datepicker-title .ui-datepicker-year {margin-right:2px}
.ui-datepicker.dataTypoDatepicker .ui-datepicker-title .ui-datepicker-month {margin-left:8px}
.ui-datepicker.dataTypoDatepicker .ui-datepicker-prev,
.ui-datepicker.dataTypoDatepicker .ui-datepicker-next {width:34px;height:34px;line-height:34px}

.inpChk>input[type="checkbox"]:focus+label,
.inpRdo>input[type="radio"]:focus+label {outline:1px solid #94b6ed;-webkit-box-shadow:0 0 2px #94b6ed;box-shadow:0 0 2px #94b6ed}
.inpChk {display:inline-block;position:relative;font-size:0;vertical-align:top}
.inpChk .lbl {display:block;padding-left:29px;height:22px;box-sizing:border-box;color:#444;font-size:13px;letter-spacing:-1px;line-height:22px;background:url(//www.saraminimage.co.kr/sri/common/ico/sri_ico_checkbox03.png) 0 2px no-repeat;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none}
.inpChk>input[type="checkbox"] {clip:rect(1px, 1px, 1px, 1px);position:absolute;width:1px;height:1px}
.inpChk>input[type="checkbox"]:checked+ .lbl {background-position:0 -48px}
.inpChk>input[type="checkbox"]:disabled+ .lbl {background:url(//www.saraminimage.co.kr/sri/common/ico/sri_ico_checkbox.png) 0 -398px no-repeat}
.inpChk.small>input[type="checkbox"] + .lbl {padding-left:20px;height:17px;font-size:12px;line-height:16px;background-position:0 -98px}
.inpChk.small>input[type="checkbox"]:checked+ .lbl {background-position:0 -148px}
.inpChk.black>input[type="checkbox"] + .lbl {padding-left:20px;height:17px;background:url(//www.saraminimage.co.kr/sri/common/ico/ico_sri_input2.png) 0 0 no-repeat;font-size:12px;line-height:16px}
.inpChk.black>input[type="checkbox"]:checked + .lbl {background-position:0 -50px}
.inpChk .lbl.hide {padding-left:20px;font-size:0}
.inpChk.small .lbl.hide {padding-left:17px}

.inpRdo {display:inline-block;position:relative;font-size:0;vertical-align:top}
.inpRdo .lbl {display:block;padding-left:29px;height:24px;box-sizing:border-box;color:#444;font-size:13px;letter-spacing:-1px;line-height:17px;background:url(//www.saraminimage.co.kr/sri/common/ico/sri_ico_checkbox03.png) 0 -200px no-repeat;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none}
.inpRdo>input[type="radio"] {clip:rect(1px, 1px, 1px, 1px);position:absolute;width:1px;height:1px}
.inpRdo>input[type="radio"]:checked + .lbl {background-position:0 -250px}
.inpRdo>input[type="radio"]:disabled + .lbl {background-position:0 -450px}
.inpRdo.small .lbl {padding-left:20px;height:16px;font-size:12px;line-height:16px;background-position:0 -298px}
.inpRdo.small>input[type="radio"]:checked + .lbl {background-position:0 -348px}
.inpRdo .lbl.hide {padding-left:18px;font-size:0}
.inpRdo.small .lbl.hide {padding-left:14px}

/* textarea */
textarea.inpTarea {overflow-x:hidden;overflow-y:scroll;padding:11px;border:1px solid #dbdbdb;box-sizing:border-box;color:#444;font-size:14px;letter-spacing:-0.5px;line-height:18px;resize:none}
textarea.inpTarea[disabled="disabled"],
textarea.inpTarea:read-only {background-color:#ccc;opacity:0.3}

/* 스크랩 버튼 */
.spr_scrap {display:block;overflow:hidden;background:url(//www.saraminimage.co.kr/sri/common/spr/spr_scrap.png) no-repeat 0 0}
.spr_scrap.btn_scrap {width:17px;height:16px;background-position:-30px 0}
.spr_scrap.btn_scrap.on {background-position:-30px -30px}
/* 텍스트 type */
.btn_scrap .txt_scrap:before {display:inline-block;vertical-align:top;background:url(//www.saraminimage.co.kr/sri/common/spr/spr_scrap.png) no-repeat 0 0;content:""}

/* 상단 카테고리구분 (3depth) */
.wrap_category_type {padding-bottom:12px}
.wrap_category_type .list_category {overflow:hidden;padding:25px 0 12px 26px;border:1px solid #eaeaea;border-top:0}
.wrap_category_type .list_category li {float:left;padding-bottom:14px;width:179px;color:#777;font-size:15px}
.wrap_category_type .list_category li a {color:#444;letter-spacing:-1px;line-height:17px}
.wrap_category_type .list_category li a:hover,
.wrap_category_type .list_category .on a {font-weight:bold;text-decoration:underline}
.wrap_category_type.type_salary li {width:200px}

/* 채용정보 190814 추가 */
.careers .list_employ .btn_tag:after {display:inline-block;overflow:hidden;background:url(//www.saraminimage.co.kr/sri/company/information/img_info_190430.png) no-repeat 0 0}
.careers .tab_careers {overflow:hidden;position:relative;margin-top:46px}
.careers .tab_careers:after {position:absolute;left:0;bottom:0;width:100%;border-bottom:1px solid #e5e6eb;content:""}
.careers .tab_careers li {float:left;position:relative;z-index:1;height:52px;margin-right:34px}
.careers .tab_careers li .btn_careers {display:block;position:relative;z-index:1;padding:3px 4px 26px;height:100%;box-sizing:border-box;font-weight:bold;font-size:17px;letter-spacing:-1px;color:#7b7e8e}
.careers .tab_careers li .btn_careers .num {display:inline-block;margin:4px 0 0 4px;padding:0 9px;height:20px;border-radius:10px;font-weight:normal;font-size:14px;color:#fff;letter-spacing:0;vertical-align:top;background:#cbcdd6}
.careers .tab_careers li.on:after, .careers .tab_careers li:hover:after {position:absolute;left:0;bottom:0;z-index:1;width:100%;height:4px;content:""}
.careers .tag_careers {overflow:hidden;position:relative;margin-top:23px;padding-left:83px}
.careers .tag_careers .tit_tag {position:absolute;left:0;top:2px;font-weight:bold;font-size:13px;color:#8a8c94}
.careers .list_tag {overflow:hidden}
.careers .list_tag > li {float:left;position:relative;margin:0 4px 8px}
.careers .list_tag input[type="checkbox"] {position:absolute;top:0;left:0;z-index:1;margin:0;padding:0;width:100%;height:100%;cursor:pointer;opacity:0;filter:alpha(opacity=0)}
.careers .list_tag .tag_txt {display:block;padding:5px 12px 7px;height:28px;box-sizing:border-box;font-size:13px;line-height:15px;color:#777;background:#fff}
.careers .list_tag input[type="checkbox"]:checked + label {color:#fff;background:#444}
.careers .list_tag .tag_txt.tag_recuite_method {color:#fff;background:#cbcdd6} /* WMG-23274 추가 */
.careers .list_employ {margin-top:47px}
.careers .list_employ > li {position:relative;margin-top:9px;padding-bottom:24px;width:100%;border:1px solid #e5e6eb;box-sizing:border-box;background:#fff}
.careers .list_employ > li:hover {border-color:#444}
.careers .list_employ > li > .area_detail {position:relative;padding:27px 176px 0 46px}
.careers .list_employ .tit {display:inline-block;overflow:hidden;max-width:708px;font-weight:bold;font-size:20px;line-height:28px;color:#333;text-overflow:ellipsis;white-space:nowrap}
.careers .list_employ .tit:hover {text-decoration:underline}
.careers .list_employ .btn_scrap {display:inline-block;margin:3px 0 0 2px;width:26px;height:26px;vertical-align:top}
.careers .list_employ .btn_scrap.on {background-position:-120px -100px}
.careers .list_employ .ico {display:inline-block;margin:5px 0 0 2px;padding:2px 0;width:55px;height:20px;font-weight:bold;font-size:11px;color:#fff;vertical-align:top;box-sizing:border-box;background:#fa6a71;text-align:center}
.careers .list_employ .state {overflow:hidden;margin-top:2px}
.careers .list_employ .state span {font-size:13px;color:#444}
.careers .list_employ .state span:before {display:inline-block;margin:6px 12px 0;width:1px;height:12px;background:#d8d8d8;vertical-align:top;content:""}
.careers .list_employ .state span:first-child:before {display:none}
.careers .list_employ .state span.emph {font-weight:bold}
.careers .list_employ .list_tag {margin:14px 0 0 -4px;padding-left:0;min-height:17px}
.careers .list_employ .list_tag .tag_txt {background:#f8f9fc}
.careers .list_employ .list_tag > li.checked .tag_txt {color:#fff;background-color:#444}
.careers .list_employ .list_tag .tag_txt.tag_recuite_method {color:#fff;background-color:#cbcdd6}/* WMG-23274 추가 */
.careers .list_employ .btn_tag {position:absolute;right:46px;bottom:8px;width:30px;height:28px;border:1px solid #e6e6e8;}
.careers .list_employ .btn_tag:after {display:block;margin:0 auto;width:10px;height:10px;background-position:-140px -60px;content:""}
.careers .list_employ .btn_tag.folder:after {background-position:-160px -60px}
.careers .list_employ .list_date {position:absolute;right:46px;top:36px;width:103px;font-size:14px;color:#444;text-align:center}
.careers .list_employ .sri_btn_sm, .careers .list_employ .link_homepage {position:absolute;right:46px;top:74px}
.careers .list_employ .sri_btn_sm {height:40px}
.careers .list_employ .sri_btn_sm span.sri_btn_immediately {padding:9px 0 10px;font-size:14px !important;height:38px;color:#ff6d6a;line-height:16px;vertical-align:top;box-sizing:border-box;background:transparent}
.careers .list_employ .sri_btn_sm span {height:38px;font-size:14px !important;color:#8a92a6;line-height:35px}
.careers .desc_nodata {padding:250px 0 150px;font-size:17px;color:#929292;text-align:center}
.careers .page_nation {margin-top:58px}
.careers .page_nation a, .careers .page_nation strong {color:#444}
.careers .page_nation .prev, .careers .page_nation .next {background:none}
/* WMG-23274 추가 */
.careers .list_employ .area_recruit {position:relative;margin:16px 0 0;padding:0 46px;border-top:1px solid #e5e6eb}
.careers .list_employ .tit_area {padding-top:21px;box-sizing:border-box;font-size:13px;line-height:15px;color:#222}
.careers .list_employ .tit_area strong {color:#ed483c}
.careers .list_employ .btn_folder {position:absolute;left:0;top:0;width:100%;height:60px}
.careers .list_employ .btn_folder .txt {position:absolute;right:45px;top:20px;padding:0 5px;font-size:13px;color:#222;letter-spacing:-1px}
.careers .list_employ .btn_folder .txt:after {display:inline-block;margin:6px 0 0 8px;width:11px;height:7px;vertical-align:top;background:url(//www.saraminimage.co.kr/sri/company/information/ico/ico_arrow.png);content:""}
.careers .area_recruit.open .btn_folder .txt:after {background-position:0 -10px}
.careers .list_employ .list_recruit {display:none;margin:20px 0 6px 0}
.careers .list_employ .list_recruit li {padding:5px 0 6px;font-size:14px;line-height:16px}
.careers .list_employ .list_recruit li a {font-weight:bold;color:#222}
.careers .list_employ .list_recruit li a:hover {text-decoration:underline}
.careers .list_employ .list_recruit li span {margin-left:10px;font-weight:normal;color:#555}
.careers .area_recruit.open .list_recruit {display:block}

/* 그래프 */
.radarChart .line {stroke:#ddd;stroke-width:1px}
.radarChart .line_axis {stroke:#ddd;stroke-width:1px}
.radarChart .label {font-size:14px;fill:#666}
.radarChart .label_score {font-size:14px;fill:#666}
.radarChart .radar_pointer {fill:#fff;stroke:#2365f2}
.radarChart .radar_pointer.radar_pointer00 {stroke-width:2px}
.radarChart .radar_pointer.radar_pointer01 {display:none}
.radarChart .radar_polygon00 {stroke-width:1px;stroke:#2365f2;fill:rgba(8,87,255,0.6)}
.radarChart .radar_polygon01 {stroke:#283248;fill:transparent;stroke-dasharray:3}
.radarChart .axis.max .label {font-weight:bold;color:#222}
.radarChart .axis.max .label_score {font-weight:bold;color:#222}
.combieChart .label_group .labels {font-size:14px}
.combieChart .bar.field1 {fill:#00d5ab}
.combieChart .bar.field2 {fill:#ebeff4;stroke:#aaafb7;stroke-dasharray:5,5}
.combieChart .group .label.field1 {fill:#666;font-size:13px;font-weight:bold;letter-spacing:0}
.combieChart .group .label.field2 {font-size:13px;letter-spacing:0}
.combieChart .group.max .field1 {fill:#2365f2}
.combieChart .xAxis .tick line {display:none}
.combieChart .xAxis .tick text {color:#666;font-size:14px}
.combieChart .xAxis .tick.max text {font-weight:bold;color:#222}
.combieChart .xAxis .domain {stroke:#666}
.combieChart .yAxis line {stroke:#ddd}
.combieChart .yAxis text {color:#888;font-size:13px;letter-spacing:0}
.combieChart .yAxis .domain {display:none}

/* pagination */
.pagination {margin-top:30px;font-size:0;letter-spacing:-1px;text-align:center}
.pagination a:hover {color:#444;text-decoration:none}
.pagination button.btnPrev,
.pagination button.btnNext {border:0}
.pagination .btnPrev,
.pagination .btnNext {display:inline-block;width:40px;height:35px;box-sizing:border-box;font-size:14px;line-height:33px;vertical-align:middle;color:#444}
.pagination .btnPrev {margin-right:19px;padding-left:10px;background:url(//www.saraminimage.co.kr/sri/common/ico/bul_page_prev.png) 0 50% no-repeat}
.pagination .btnNext {margin-left:14px;padding-right:10px;background:url(//www.saraminimage.co.kr/sri/common/ico/bul_page_next.png) 100% 50% no-repeat}
.pagination span.page,
.pagination button.page,
.pagination a.page {display:inline-block;margin:0 5px 0 0;padding:0 2px;min-width:32px;height:32px;border:1px solid #dfdfdf;box-sizing:border-box;color:#444;font-size:14px;line-height:30px;vertical-align:middle;background:#fff}
.pagination span.page {border:1px solid #444;color:#444;font-size:14px;font-weight:bold}
.pagination.sizeS .btnPrev {margin-right:10px;width:40px;height:27px;font-size:12px;line-height:27px}
.pagination.sizeS .btnNext {margin-left:5px;width:40px;height:27px;font-size:12px;line-height:27px}
.pagination.sizeS span.page,
.pagination.sizeS button.page,
.pagination.sizeS a.page {min-width:24px;height:27px;border:none;color:#666;font-size:12px;line-height:27px}
.pagination.sizeS span.page {border:1px solid #667df9;color:#667df9;font-size:12px}

/* toggle - on/off */
.inpChkSw {display:inline-block;position:relative;}
.inpChkSw input {clip:rect(1px, 1px, 1px, 1px);position:absolute;width:1px;height:1px}
.inpChkSw .lbl {position:relative;padding-left:51px;color:#666;font-weight:bold;font-size:13px;line-height:17px;}
.inpChkSw .lbl:before {position:absolute;left:0;top:50%;margin-top:-9px;width:42px;height:17px;border-radius:9px;background:#9c9c9c;transition:background .15s linear;content:""}
.inpChkSw .lbl:after {position:absolute;left:-1px;top:50%;margin-top:-12px;width:23px;height:23px;border:1px solid #9c9c9c;box-sizing:border-box;background:#fff;border-radius:50%;transition:left .15s linear;content:""}
.inpChkSw input:checked+.lbl:before{background:#4876ef}
.inpChkSw input:checked+.lbl:after {left:19px;border-color:#4876ef}

/* toggle - 선택 */
.inpRdoSw {display:inline-block;vertical-align:middle}
.inpRdoSw:after {display:table;clear:both;content:"";table-layout:fixed}
.inpRdoSw .inOption {float:left;position:relative}
.inpRdoSw .inOption+.inOption {margin-left:-1px}
.inpRdoSw .inOption>input {position:absolute;top:0;width:1px;height:1px;outline:none;opacity:0;pointer-events:none}
.inpRdoSw .inOption>.lbl {display:inline-block;padding:5px 14px 6px;height:32px;border:1px solid #dbdbdb;box-sizing:border-box;color:#666;font-size:13px;letter-spacing:-1px;line-height:19px;text-align:center;vertical-align:top;background-color:#fff;cursor:pointer;user-select:none;}
.inpRdoSw .inOption:nth-child(n + 2):after {position:absolute;top:0;left:-1px;z-index:2;width:2px;height:100%;content:""}
.inpRdoSw .inOption>input:focus+.lbl {outline:5px auto -webkit-focus-ring-color;outline:1px dotted invert}
.inpRdoSw .inOption>input:checked+.lbl {position:relative;z-index:2;border-color:#4876ef;background-color:#4876ef;color:#fff;}
.inpRdoSw.colorWhite .inOption>input:checked+.lbl {background-color:#fff;color:#4876ef;}
.inpRdoSw.colorGray .inOption>input:checked+.lbl {background-color:#555;color:#fff;border-color:#444;}
.inpRdoSw.colorWhite .inOption>input:checked:disabled+.lbl,
.inpRdoSw.colorGray .inOption>input:checked:disabled+.lbl,
.inpRdoSw .inOption>input:disabled+.lbl {border-color:#ddd;color:#888;background-color:#efefef;cursor:default}
.inpRdoSw.sizeS .inOption>.lbl {padding-top:2px;padding-bottom:3px;height:23px;font-size:12px;line-height:16px;}
.inpRdoSw.sizeL .inOption>.lbl {padding-top:9px;padding-bottom:10px;height:40px;font-size:15px;line-height:19px}
.inpRdoSw.sizeXL .inOption>.lbl {padding-top:11px;padding-bottom:11px;height:50px;font-size:16px;line-height:26px}

/* popover/tooltip */
.toolTipWrap {display:inline-block;position:relative}
.toolTip {display:none;position:absolute;z-index:200;padding:18px 22px 20px 22px;min-width:33px;border:1px solid #444;border-radius:2px;color:#222;font-family:"Malgun Gothic",dotum,gulim,sans-serif;font-size:13px;background-color:#fff;box-shadow:0 1px 3px #d8d8d8}
.toolTip.on {display:block}
.toolTip .tail {position:absolute;background:url(//www.saraminimage.co.kr/sri/common/tooltip_tail.png) no-repeat}
.toolTip .tail.tail_bottom_left {bottom:-7px;left:25px;width:11px;height:7px;background-position:0 0}
.toolTip .tail.tail_bottom_center {bottom:-7px;left:50%;margin-left:-6px;width:11px;height:7px;background-position:0 0}
.toolTip .tail.tail_bottom_right {right:25px;bottom:-7px;width:11px;height:7px;background-position:0 0}
.toolTip .tail.tail_top_left {top:-7px;left:25px;width:11px;height:7px;background-position:0 -7px}
.toolTip .tail.tail_top_center {top:-7px;left:50%;margin-left:-6px;width:11px;height:7px;background-position:0 -7px}
.toolTip .tail.tail_top_right {top:-7px;right:25px;width:11px;height:7px;background-position:0 -7px}
.toolTip .tail.tail_right_top {top:25px;right:-7px;width:7px;height:11px;background-position:-18px 0}
.toolTip .tail.tail_right_center {top:50%;right:-7px;margin-top:-6px;width:7px;height:11px;background-position:-18px 0}
.toolTip .tail.tail_right_bottom {right:-7px;bottom:25px;width:7px;height:11px;background-position:-18px 0}
.toolTip .tail.tail_left_top {top:25px;left:-7px;width:7px;height:11px;background-position:-11px 0}
.toolTip .tail.tail_left_center {top:50%;left:-7px;margin-top:-6px;width:7px;height:11px;background-position:-11px 0}
.toolTip .tail.tail_left_bottom {bottom:25px;left:-7px;width:7px;height:11px;background-position:-11px 0}
.toolTip .toolTipCont {line-height:20px}
.toolTip .toolTipCont.txtLeft {text-align:left}
.toolTip .toolTipCont.txtCenter {text-align:center}
.toolTip .toolTipCont .toolTipTxt {margin-right:12px}
.toolTip .toolTipCont .toolTipTit {display:block;margin-bottom:10px;color:#222;font-weight:bold;letter-spacing:-1px}
.toolTip .toolTipCont .toolTipTit+.toolTipTxt {margin-right:0}
.toolTip .btnClose {position:absolute;top:0;right:0;padding:15px 15px 10px 10px;border:0;background:none}
.toolTip .btnClose:after {display:block;width:13px;height:13px;background:url(//www.saraminimage.co.kr/ui/layer/button/spr_lefttop_arrow.png) 0 0 no-repeat;content:""}
.toolTip:hover+.sri_tooltip {display:block}
.toolTip.type2 {padding:18px 22px 20px 22px;border:1px solid #5b8ae8;box-sizing:border-box;color:#fff;letter-spacing:-1px;background-color:#5b8ae8}
.toolTip.type2 .btn_close:after {background:url(//www.saraminimage.co.kr/sri/company/talent/btn_close_tooltip.png) 0 0 no-repeat}
.toolTip.type2 .tail.tailBottomCenter {background:url(//www.saraminimage.co.kr/sri/company/talent/ico_arrow3.png) 50% 100% no-repeat}

/* 공고 view 템플릿 css */
.user_content {margin:0 auto;width:860px;color:#444;font-size:13px;line-height:1.231;letter-spacing:-1px;word-break:break-all}
.user_content:after{display:table;table-layout:fixed;clear:both;content:"";}
.user_content img {max-width:860px !important;border:0}
.user_content map {border:0}
.user_content table {max-width:860px !important;line-height:normal}
.user_content p {margin:0}

.user_content .enc_mail {display:none}
.user_content .detail_enc_mail_btn {cursor: pointer; letter-spacing:0}

/* recruit_template.css 에서 생성 */
.temp_wrap {margin:0;padding:0;font-family:굴림, gulim !important;font-size:12px;vertical-align:middle;}
.temp_wrap table {width:100%;min-width:550px;word-break:break-all;table-layout:fixed;}
.temp_wrap table th,
.temp_wrap table td {padding:0; font-size:12px;}
.temp_wrap p {margin:0;}
.blind {display:none;visibility:hidden;position:absolute;top:-2000px;left:-2000px;width:1px;height:1px;color:#fff;}

.temp-wrap01 {margin:0 auto;width:100%;border:2px solid #e8e8e8;}
.temp-wrap01 .temp-bg {width:100%;background:url(//www.saraminimage.co.kr/recruit/templete_type/img_temp01_bottom.gif) no-repeat 100% 100%;}
.temp-wrap01 .temp-bg td {background:#fff;}
.temp-wrap01 .temp-bg {width:100%;background:url(//www.saraminimage.co.kr/recruit/templete_type/img_temp01_bottom.gif) no-repeat 100% 100%;}
.temp-wrap01 .temp-bg .tb-head {background:url(//www.saraminimage.co.kr/recruit/templete_type/bg_temp01_bgpattern.gif) repeat-x 0 0;}
.temp-wrap01 .temp-bg .im-vis {border-bottom:2px solid #e8e8e8;background:url(//www.saraminimage.co.kr/recruit/templete_type/img_temp01_topleft2.gif) no-repeat 0 0;}
.temp-wrap01 .temp-bg .im-vis .bg-right {min-width:550px;height:147px;background:url(//www.saraminimage.co.kr/recruit/templete_type/img_temp01_topright2.gif) no-repeat 100% 0;}
.temp-wrap01 .temp-bg .tit {margin-bottom:10px !important;padding-left:12px !important;color:#467f22;font-family:dotum !important;font-size:14px !important;font-weight:bold;text-align:left;background:url(//www.saraminimage.co.kr/recruit/templete_type/bul_view_barrow.gif) no-repeat 0 2px;}
.temp-wrap01 .temptb {width:100%;min-width:500px;border-top:1px solid #d9e3ce;border-bottom:1px solid #d9e3ce;}
.temp-wrap01 .temptb th {padding:10px 0;border-right:1px solid #ecf1e6;border-bottom:1px solid #d9e3ce;color:#686868;font-weight:bold;text-align:center;background:#f0fae6;}
.temp-wrap01 .temptb th.end {border-right:none;}
.temp-wrap01 .temptb td {padding:15px 0;border-right:1px solid #ecf1e6;color:#686868;font-family:굴림, gulim ;font-size:12px;text-align:center;}
.temp-wrap01 .temptb td.end {border-right:none;}
.temp-wrap01 .temptb td p {margin:0 0 5px 0;padding:0 10px;color:#686868;text-align:left;}
.temp-wrap01 .temptb td p.cen {text-align:center;}
.temp-wrap01 .temptb td p.end {margin-bottom:0;}
.temp-wrap01 td .con {margin-bottom:3px;padding-left:20px;color:#686868;}
.temp-wrap01 td .last {margin-bottom:0;}
.temp-wrap01 .head_txt {display:inline-block;margin:53px 0 0 49px;width:357px;height:46px;background:url(//www.saraminimage.co.kr/recruit/templete_type/txt_temp01_tit2.png) no-repeat 0 0;}

.temp-wrap02 {margin:0 auto;width:100%;border:2px solid #e8e8e8;}
.temp-wrap02 .temp-bg {width:100%;}
.temp-wrap02 .temp-bg .tb-head {background:#fff;}
.temp-wrap02 .temp-bg .im-vis {background:#fafafa url(//www.saraminimage.co.kr/recruit/templete_type/img_temp02_topleft.png) no-repeat 0 0;}
.temp-wrap02 .temp-bg .im-vis .bg-right {width:100%;min-width:550px;height:147px;border-bottom:2px solid #e8e8e8;text-align:center;background:url(//www.saraminimage.co.kr/recruit/templete_type/img_temp02_topright.png) no-repeat 100% 0;}
.temp-wrap02 .temp-bg .tit {margin-bottom:10px !important;padding-left:12px !important;color:#183877;font-family:dotum !important;font-size:14px !important;font-weight:bold;text-align:left;background:url(//www.saraminimage.co.kr/recruit/templete_type/bul_view_barrow02.gif) no-repeat 0 2px;}
.temp-wrap02 .temptb {width:100%;min-width:500px;}
.temp-wrap02 .temptb th {padding:10px 0;border-right:2px solid #fff;border-bottom:2px solid #fff;color:#686868;font-weight:bold;text-align:center;background:#eaeff2;}
.temp-wrap02 .temptb th.end {border-right:none;}
.temp-wrap02 .temptb td {padding:15px 0;border-right:2px solid #fff;color:#686868;font-family:굴림, gulim;font-size:12px;text-align:center;background:#f6f9fa;}
.temp-wrap02 .temptb td.end {border-right:none;}
.temp-wrap02 .temptb td p {margin:0 0 5px 0;padding:0 10px;color:#686868;text-align:left;}
.temp-wrap02 .temptb td p.cen {text-align:center;}
.temp-wrap02 .temptb td p.end {margin-bottom:0;}
.temp-wrap02 td .con {margin-bottom:3px;padding-left:20px;color:#686868;}
.temp-wrap02 td .last {margin-bottom:0;}
.temp-wrap02 .head_txt {display:inline-block;margin:45px 0 0 0;width:300px;height:45px;background:url(//www.saraminimage.co.kr/recruit/templete_type/txt_temp02_tit2.png) no-repeat 0 0;}

.temp-wrap03 {margin:0 auto;width:100%;border:2px solid #e8e8e8;background:url(//www.saraminimage.co.kr/recruit/templete_type/img_temp03_bottom.png) no-repeat 100% 100%;}
.temp-wrap03 .temp-bg {width:100%;background:url(//www.saraminimage.co.kr/recruit/templete_type/img_temp03_topleft3.png) no-repeat 0 0;}
.temp-wrap03 .temp-bg {width:100%;background:url(//www.saraminimage.co.kr/recruit/templete_type/img_temp03_topleft3.png) no-repeat 0 0;}
.temp-wrap03 .temp-bg .im-vis {background:url(//www.saraminimage.co.kr/recruit/templete_type/img_temp03_topright2.png) no-repeat 100% 0;}
.temp-wrap03 .temp-bg .im-vis .bg-right {min-width:550px;height:170px;background:url(//www.saraminimage.co.kr/recruit/templete_type/img_temp03_topbar3.png) repeat-x 50% 115px;}
.temp-wrap03 .temp-bg .tit {margin-bottom:10px !important;padding-left:16px !important;color:#183877;font-family:dotum !important;font-size:14px !important;font-weight:bold;text-align:left;background:url(//www.saraminimage.co.kr/recruit/templete_type/bul_view_barrow03.gif) no-repeat 0 2px;}
.temp-wrap03 .temptb {width:100%;min-width:500px;}
.temp-wrap03 .temptb th {padding:10px 0;border-right:2px solid #fff;border-bottom:2px solid #fff;color:#686868;font-weight:bold;text-align:center;background:#fdf4d7;}
.temp-wrap03 .temptb th.end {border-right:none;}
.temp-wrap03 .temptb td {padding:15px 0;border-right:2px solid #fff;color:#686868;font-family:굴림, gulim;font-size:12px;text-align:center;background:#fffbed;}
.temp-wrap03 .temptb td p.cen {text-align:center;}
.temp-wrap03 .temptb td.end {border-right:none;}
.temp-wrap03 .temptb td p {margin:0 0 5px 0;padding:0 10px;color:#686868;text-align:left;}
.temp-wrap03 .temptb td p.end {margin-bottom:0;}
.temp-wrap03 td .con {margin-bottom:3px;padding-left:20px;color:#686868;}
.temp-wrap03 td .last {margin-bottom:0;}
.temp-wrap03 .head_txt {display:inline-block;margin:52px 0 0 82px;width:267px;height:45px;background:url(//www.saraminimage.co.kr/recruit/templete_type/txt_temp03_tit2.png) no-repeat 0 0;}

.temp-wrap04 {margin:0 auto;width:100%;border:2px solid #e8e8e8;}
.temp-wrap04 .temp-bg {width:100%;background:url(//www.saraminimage.co.kr/recruit/templete_type/img_temp04_pattern2.png) repeat-x 0 0;}

.temp-wrap04 .temp-bg .im-vis {background:url(//www.saraminimage.co.kr/recruit/templete_type/img_temp04_topleft.png) no-repeat 0 0;}
.temp-wrap04 .temp-bg .im-vis .bg-right {min-width:550px;height:147px;border-bottom:2px solid #e8e8e8;text-align:right;}
.temp-wrap04 .temp-bg .tit {margin-bottom:10px !important;padding-left:12px !important;color:#000;font-family:dotum !important;font-size:14px !important;font-weight:bold;text-align:left;background:url(//www.saraminimage.co.kr/recruit/templete_type/bul_view_barrow05.gif) no-repeat 0 2px;}
.temp-wrap04 .temptb {width:100%;min-width:500px;}
.temp-wrap04 .temptb th {padding:10px 0;border-right:2px solid #fff;border-bottom:2px solid #fff;color:#686868;font-weight:bold;text-align:center;background:#f0f0f0;}
.temp-wrap04 .temptb th.end {border-right:none;}
.temp-wrap04 .temptb td {padding:15px 0;border-right:2px solid #fff;color:#686868;font-family:굴림, gulim;font-size:12px;text-align:center;background:#f6f6f6;}
.temp-wrap04 .temptb td p.cen {text-align:center;}
.temp-wrap04 .temptb td.end {border-right:none;}
.temp-wrap04 .temptb td p {margin:0 0 5px 0;padding:0 10px;color:#686868;text-align:left;}
.temp-wrap04 .temptb td p.end {margin-bottom:0;}
.temp-wrap04 td .con {margin-bottom:3px;padding-left:20px;color:#686868;}
.temp-wrap04 td .last {margin-bottom:0;}
.temp-wrap04 .head_txt {display:inline-block;margin:45px 56px 0 0;width:215px;height:65px;background:url(//www.saraminimage.co.kr/recruit/templete_type/txt_temp04_tit2.png) no-repeat 0 0;}

.temp-wrap05 {margin:0 auto;width:100%;border:2px solid #e8e8e8;}
.temp-wrap05 .temp-bg {width:100%;background:url(//www.saraminimage.co.kr/recruit/templete_type/bg_temp05_bgpattern.gif) repeat-x 0 100%;}

.temp-wrap05 .temp-bg .bg-addit {background:url(//www.saraminimage.co.kr/recruit/templete_type/img_temp05_topright2.png) no-repeat 100% 0;}
.temp-wrap05 .temp-bg .bg-addit2 {background:url(//www.saraminimage.co.kr/recruit/templete_type/img_temp05_topleft2.png) no-repeat 0 0;}
.temp-wrap05 .temp-bg .bg-addit3 {background:url(//www.saraminimage.co.kr/recruit/templete_type/img_temp05_bottom.png) no-repeat 100% 100%;}

.temp-wrap05 .temp-bg .im-vis .bg-right {min-width:550px;height:290px;background:url(//www.saraminimage.co.kr/recruit/templete_type/img_temp05_topleft2.png) no-repeat 0 0;}
.temp-wrap05 .temp-bg .im-vis .bg-right img {margin:72px 0 0 48px;}
.temp-wrap05 .temp-bg .tit {margin-bottom:10px !important;padding-left:17px !important;color:#a10f00;font-family:dotum !important;font-size:14px !important;font-weight:bold;text-align:left;background:url(//www.saraminimage.co.kr/recruit/templete_type/bul_view_barrow04.gif) no-repeat 0 2px;}
.temp-wrap05 .temptb {width:100%;min-width:500px;}
.temp-wrap05 .temptb th {padding:10px 0;border-right:2px solid #fff;border-bottom:2px solid #fff;color:#686868;font-weight:bold;text-align:center;background:#ededed;}
.temp-wrap05 .temptb th.end {border-right:none;}
.temp-wrap05 .temptb td {padding:15px 0;border-right:2px solid #fff;color:#686868;font-family:굴림, gulim;font-size:12px;text-align:center;background:#f6f6f6;}
.temp-wrap05 .temptb td.end {border-right:none;}
.temp-wrap05 .temptb td p {margin:0 0 5px 0;padding:0 10px;color:#686868;text-align:left;}
.temp-wrap05 .temptb td p.cen {text-align:center;}
.temp-wrap05 .temptb td p.end {margin-bottom:0;}
.temp-wrap05 td .con {margin-bottom:3px;padding-left:20px;color:#686868;}
.temp-wrap05 td .last {margin-bottom:0;}

/* 채용공고템플릿 20111024 */
.srd {padding-bottom:50px; padding-left:20px; width:650px; text-align:left;}
.srd h3 {padding-top:20px; border-bottom:5px #3363c5 solid; color:#365ba2; font-family:"맑은 고딕","다음","sans-serif"; font-size:30px; font-weight:bold; letter-spacing:-2px; line-height:55px; text-align:center;}
.srd h4 {padding-top:38px; border-bottom:1px #7e92b8 solid; color:#3e61a2; font-family:"맑은 고딕","다음","sans-serif" ; font-size:14px; font-weight:bold; letter-spacing:-1px; line-height:27px;}
.srd-con {margin:0;padding:17px 0 0; color:#444; font-family:"굴림","돋움","sans-serif"; font-size:12px; line-height:20px;}
.srd-lp {padding-left:15px;}

/* 2016년 3월 개선버전 추가 */
.new_template {margin:0 auto;width:695px;}
.new_template,
.new_template th,
.new_template td {font-family:malgun Gothic, "맑은 고딕", "나눔고딕",sans-serif !important;letter-spacing:-1px;}
.new_template th {text-align:center;}
.new_template h1,
.new_template h2,
.new_template h3,
.new_template p {margin:0;}
.new_template strong {font-weight:bold;}
.new_template .table_header .margin {width:80px;}
.new_template .tit_recruit_header {padding:0;color:#1d1e25;font-family:malgun Gothic, "맑은 고딕", "나눔고딕",sans-serif !important;font-size:40px;font-weight:bold;letter-spacing:-2px;line-height:46px;text-align:center;background-color:transparent;}
.new_template .tit_recruit_header p {font-family:malgun Gothic, "맑은 고딕", "나눔고딕",sans-serif !important;font-size:40px !important;line-height:46px !important;}
.new_template .desc_recruit_header {margin:20px 0 16px !important;color:#555;font-family:malgun Gothic, "맑은 고딕", "나눔고딕",sans-serif !important;font-size:16px !important;line-height:24px !important;text-align:center;}
.new_template .tit_section {margin:30px 0 16px;padding:0 0 0 32px;height:25px;color:#1d1e25;font-family:malgun Gothic, "맑은 고딕", "나눔고딕",sans-serif;font-size:20px;font-weight:bold;letter-spacing:-2px;line-height:25px;background:url(//www.saraminimage.co.kr/ui/recruit/view_template/sp_icon_title.png) no-repeat;}
.new_template .above_table_summary {margin-bottom:8px;}
.new_template .wrap_table_content {padding-left:30px;}
.new_template .wrap_table_content table tbody th,
.new_template .wrap_table_content table tbody td {padding-bottom:6px;color:#555;font-family:malgun Gothic, "맑은 고딕", "나눔고딕",sans-serif;font-size:14px;text-align:left;vertical-align:top;}
.new_template .wrap_table_content table tbody th {color:#333;font-weight:bold;}
.new_template .txt_caution {margin-bottom:0 !important;padding-left:30px !important;color:#555;font-family:malgun Gothic, "맑은 고딕", "나눔고딕",sans-serif !important;font-size:14px !important;line-height:22px;}
.new_template .txt_caution:last-child {margin-bottom:54px !important;}
.new_template .table_summary {border:1px solid #ebebeb;border-width:2px 0 2px 1px;}
.new_template .table_summary th,
.new_template .table_summary td {border:1px solid #ebebeb;border-width:1px 1px 0 0;}
.new_template .table_summary thead th {height:47px;border-top-width:0;color:#1d1e25;font-family:malgun Gothic, "맑은 고딕", "나눔고딕",sans-serif;font-size:14px;font-weight:bold;}
.new_template .table_summary tbody td {padding:17px 4px 19px;color:#333;font-family:malgun Gothic, "맑은 고딕", "나눔고딕",sans-serif;font-size:14px;line-height:normal;text-align:center;}
.new_template .table_summary tbody td.left_align {padding-left:19px;text-align:left;}
.new_template .table_summary tbody td p {font-family:malgun Gothic, "맑은 고딕", "나눔고딕",sans-serif !important;font-size:14px !important;line-height:normal !important;}
.new_template .new_template_style1 .tit_section {padding-left:24px;background-position:0 12px;}
.new_template .new_template_style1 .table_summary {border-top-color:#a5bcea;border-bottom-color:#ddd;}
.new_template .new_template_style1 .wrap_table_content {padding-left:24px;}
.new_template .new_template_style2 .tit_section {background-position:0 -42px;}
.new_template .new_template_style2 .table_summary {border-top-color:#c7c8ce;border-bottom-color:#ddd;}
.new_template .new_template_style2 .wrap_table_content {padding-left:32px;}
.new_template .new_template_style3 .wrap_header {padding-top:176px !important;background:url(//www.saraminimage.co.kr/ui/recruit/view_template/header_template3.png) no-repeat;}
.new_template .new_template_style3 .tit_recruit_header {font-size:28px;line-height:36px;}
.new_template .new_template_style3 .tit_section {background-position:0 -95px;}
.new_template .new_template_style3 .table_summary {border-top-color:#6483d0;border-bottom-color:#ddd;}
.new_template .new_template_style3 .wrap_table_content,
.new_template .new_template_style3 .txt_caution {padding-left:34px;}
.new_template .new_template_style3 .table_header .margin {width:110px;}
.new_template .new_template_style4 .wrap_header {padding-top:54px !important;text-align:left;background:url(//www.saraminimage.co.kr/ui/recruit/view_template/header_template4.png) 100% 0 no-repeat;}
.new_template .new_template_style4 .tit_recruit_header {overflow:hidden;height:139px;letter-spacing:-3px;text-align:left;}
.new_template .new_template_style4 .desc_recruit_header {margin-top:32px !important;text-align:left;}
.new_template .new_template_style4 .tit_section {background-position:0 -145px;}
.new_template .new_template_style4 .table_summary {border-top-color:#ec4438;border-bottom-color:#ddd;}
.new_template .new_template_style4 div.wrap_table_content,
.new_template .new_template_style4 div.txt_caution {padding-left:34px !important;}
.new_template .new_template_style5 .tit_section {background-position:0 -195px;}
.new_template .new_template_style5 .table_summary {border-top-color:#ebda7b;border-bottom-color:#ddd;}
.new_template .new_template_style5 div.wrap_table_content,
.new_template .new_template_style5 div.txt_caution {padding-left:34px !important;}
#contentsLayer .new_template_style5 p {color:#555;font-family:malgun Gothic, "맑은 고딕", "나눔고딕",sans-serif;font-size:14px;line-height:18px;}
#contentsLayer div,
#contentsLayer div p {font-family:malgun Gothic, "맑은 고딕", "나눔고딕",sans-serif !important;}
/* WMG-14742 */
.new_template .new_template_text .template_text_inner {padding:38px 44px 60px;border:10px solid #f2f2f2;color:#666;font-family:"malgun Gothic",nanumGothic,sans-serif !important;font-size:15px;line-height:22px;text-align:left;}
.new_template .new_template_text .tit_section {margin:2px 0 16px;padding-left:0;height:auto;color:#313131;font-size:22px;line-height:25px;background:none;}
.new_template .new_template_text p {color:#666;font-family:"malgun Gothic",nanumGothic,sans-serif;font-size:15px;line-height:22px;}
.new_template .new_template_text ul {margin:0;padding:0;}
.new_template .new_template_text li {margin:0;padding:0;list-style-type:none;color:#666;font-family:"malgun Gothic",nanumGothic,sans-serif;font-size:15px;line-height:22px;}
.new_template .new_template_text dl {margin:0;padding:0;}
.new_template .new_template_text dt {margin:16px 0 0;color:#444;font-family:"malgun Gothic",nanumGothic,sans-serif;font-size:13px;font-weight:bold;line-height:22px;}
.new_template .new_template_text dd {margin:0;padding:0;color:#666;font-family:"malgun Gothic",nanumGothic,sans-serif;font-size:15px;line-height:22px;}
.new_template .new_template_text strong,
.new_template .new_template_text b {font-weight:bold;}
.new_template .new_template_text em,
.new_template .new_template_text i {font-weight:inherit;font-style:italic;}
.new_template .new_template_text .txt_caution {margin:7px 0 0 !important;padding-left:0 !important;color:#666;font-size:15px !important;}

/* 사람인 채용공고템플릿 */
#sriRecruitWrap {width:695px;margin:0 auto;color:#555;font-family:"Malgun Gothic", "맑은 고딕", Dotum, "돋움", AppleGothic,sans-serif;letter-spacing:-3px;}
#sriRecruitWrap img {vertical-align:top;}
#sriRecruitWrap p {margin:0;}

#sriRecruitWrap .sri_recruit_data {margin-top:7px;width:100%;}
#sriRecruitWrap .sri_recruit_table {margin-left:37px;padding-bottom:43px; width:620px; border-top:2px solid #555;}
#sriRecruitWrap .sri_recruit_title {padding:35px 0 35px 10px;height:98px;box-sizing:border-box;font-size:30px;font-weight:bold;line-height:21px;}
#sriRecruitWrap .sri_recruit_table table {border:0;border-spacing:0;border-collapse:collapse;text-align:left;table-layout:fixed;}
#sriRecruitWrap .sri_recruit_table th,
#sriRecruitWrap .sri_recruit_table td {font-family:"Malgun Gothic", "맑은 고딕", Dotum, "돋움", AppleGothic,sans-serif;}
#sriRecruitWrap .sri_recruit_table th {color:#597cef;font-size:28px;line-height:43px;vertical-align:top;}
#sriRecruitWrap .sri_recruit_table .recruit_category {margin-top:-2px;padding:20px 0 20px 11px;width:120px;border-top:2px solid #d3d3d3; box-sizing:border-box; font-weight:bold; letter-spacing:-3px;}
#sriRecruitWrap .sri_recruit_table td {padding:25px 0 32px 0;border-top:2px solid #d3d3d3;color:#444;font-size:24px;letter-spacing:-2px;line-height:36px;}
#sriRecruitWrap .sri_recruit_table td strong {font-size:24px;font-weight:bold;}
#sriRecruitWrap .recruit_detail_contents {padding-bottom:1px; font-size:22px;}
#sriRecruitWrap .recruit_detail_contents strong {display:block; padding-top:40px;}
#sriRecruitWrap .recruit_detail_contents strong.top_none,
#sriRecruitWrap .recruit_detail_contents strong:first-child {padding-top:0;}
#sriRecruitWrap .sri_recruit_process {margin:0;padding:25px 0;}
#sriRecruitWrap .sri_recruit_process:after {display:block;clear:both;content:"";}
#sriRecruitWrap .sri_recruit_process dt {float:left;padding-left:10px;width:150px; color:#597cef;font-size:28px;font-weight:bold;line-height:43px;vertical-align:top;}
#sriRecruitWrap .sri_recruit_process dd {float:left;margin:11px 0 11px 0;width:450px;}






</style>