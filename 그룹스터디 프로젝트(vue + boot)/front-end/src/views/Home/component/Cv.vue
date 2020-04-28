<template>
<div class="sri_product">
  <div class="cont_product cont_special">
    <div class="inner_cont">
      <div class="top_product">
        <strong class="tit_cont"></strong>
        <!-- <el-button v-if="login1.auth == 3" type="primary" @click="recruitingWrite" style="float: right; margin-top:3px; margin-right:30px">글쓰기</el-button> -->
        <ul class="list_product basic" id="_special" style="margin-top:20px">
          <div v-for="recruitingInfo in RecruitingList" :key="recruitingInfo.boardSeq">
            <li v-if="$moment(recruitingInfo.cvStartDate).valueOf() - now < 0" style="margin-right: 30px; margin-bottom: 15px;" >
              <div  class="box_product">
                <a @click="showEmpDetail(recruitingInfo.boardSeq)" class="link_box"></a> 
                <span class="product_logo">
                  <img :src="recruitingInfo.memberDto.companyLogo" class="img" alt="이미지없음" rel="nofollow">
                </span>
                <strong class="product_tit">{{recruitingInfo.memberDto.memberName}}</strong> 
                <em class="product_desc">{{recruitingInfo.title}}</em> 
                
                <span class="recruit_func">
                  <span class="blind">공고 마감일</span>
                  <span class="num_dday" v-if="recruitingInfo.dDay > 0">D - {{recruitingInfo.dDay}}</span>
                  <span class="num_dday" v-else>오늘마감</span>  
                  <button class="sri_btn_xs" title="클릭하면 입사지원할 수 있는 창이 뜹니다." onclick="try{quickApplyForm(&#39;37993617&#39;,&#39;&#39;,&#39;t_category=main&t_content=platinum_fix_expand&#39;, &#39;&#39;); return false;} catch (e) {}; return false;" onmousedown="try{n_trackEvent(&#39;apply&#39;,&#39;main&#39;,&#39;quick_apply&#39;,&#39;&#39;);}catch(e){}"><span class="sri_btn_immediately track_event" data-track_event="main|Ads_quick_apply|platinum_fix_expand|1">즉시지원</span></button> 
                </span> 
                <span class="bg"></span>
              </div> 
            </li>
          </div>
        </ul>
      </div>
    </div>
  </div>
  
  
</div>
</template>

<script>
import Vue from "vue"
import moment from "moment"
import VueMomentJS from "vue-momentjs"
import { loading } from 'element-ui';

Vue.use(VueMomentJS, moment)

export default {
  data(){
    return{
      RecruitingList: this.$store.state.s_employment.RecruitingList,
      
      login1 : "",
      now : "",
      
    }
  },
  mounted(){
    
    this.now = moment().valueOf()

    // this.$store.state.currpage = this.$route.path
    axios.get("http://localhost:9000/getAllRecuritingInfo")
                      .then(res => {
                  // alert(JSON.stringify(res.data))
                  this.RecruitingList = res.data
                  this.$store.state.s_employment.RecruitingList = res.data
                  
                  
                })

  },
    
  methods:{
    showEmpDetail(seq){
      this.$router.push({
        path: "/RecruitingDetail/"+seq
        })
    },
    recruitingWrite(seq){
      this.$router.push({
        path: "/RecruitingWriting/"
        })
    }
  },
  created(){
		let sMemberSeq = sessionStorage.getItem("loginUser")
    this.login1 = JSON.parse(sMemberSeq)
    
    
		// this.memberSeq = this.$store.state.loginUser.memberSeq
	}
  
}
</script>



<style>
.hidden,
.blind {overflow:hidden;clip:rect(1px,1px,1px,1px);position:absolute !important;width:1px;height:1px}

/* component 분리 */
.sri_btn_xs {display:inline-block;overflow:hidden;margin:0;padding:0;width:auto;height:auto;box-sizing:border-box;text-align:center;vertical-align:top;background:#fff;cursor:pointer}
.sri_btn_xs>span {display:block;padding-top:1px;width:78px;height:18px;border:1px solid #dce2ef;box-sizing:border-box;color:#8a92a6;font-size:12px  !important;letter-spacing:-1px;line-height:12px;text-align:center;}
.sri_btn_xs>span.sri_btn_immediately {position:relative;width:52px;border:1px solid #ff6d6a;color:#ff6d6a;background:0}
.sri_btn_xs>span.sri_btn_tobe_apply,
.sri_btn_xs>span.sri_btn_visit_apply,
.sri_btn_xs>span.sri_btn_expired_apply {background:none}
.sri_btn_xs>span.sri_btn_expired_apply {width:52px}

/* 오픈형 (플래티넘/프라임/스페셜)*/
.list_product {width:1000px; height: auto}
.list_product:after {display:table;clear:both;content:"";table-layout:fixed;}
.list_product li {float:left;position:relative;margin-right:16px;width:303px}
.list_product .box_product {overflow:hidden;position:absolute;top:0;left:0;padding:0 20px;width:100%;height:100%;box-sizing:border-box;background:#fff}
.list_product .box_product:after {position:absolute;top:0;right:0;bottom:0;left:0;content:""}
.list_product .box_product .link_box {position:absolute;top:0;left:0;z-index:10;width:100%;height:100%;background-color:#fff;opacity:0;filter:alpha(Opacity=0);}
.list_product .open .box_product:before {position:absolute;top:0;left:0;z-index:1;width:100%;height:4px;content:""}
.list_product .open .box_product:after {border-top:0}
.list_product .open .box_product:hover {z-index:15}
.list_product .product_logo {display:block;overflow:hidden;width:100%;color:#444;font-weight:bold;letter-spacing:-2px;text-align:center;text-overflow:ellipsis;white-space:nowrap;}
.list_product .product_logo .img {display:inline-block}
.list_product .product_tit {display:block;overflow:hidden;color:#444;font-weight:bold;text-overflow:ellipsis;white-space:nowrap}
.list_product .product_desc {display:block;overflow:hidden;color:#444;letter-spacing:-1.5px;text-overflow:ellipsis;white-space:nowrap}
.list_product .product_desc.open {display:none}
.list_product .product_desc.open .txt {display:block;overflow:hidden;text-overflow:ellipsis;white-space:nowrap}
.list_product .open .box_product:hover .wrap_desc {display:block}
.list_product .open .box_product:hover .product_desc {text-overflow:initial;white-space:initial}
.list_product .open .box_product:hover .product_desc {display:none}
.list_product .open .box_product:hover .product_desc.open {display:block}
.list_product .wrap_desc {display:none;overflow:hidden;position:absolute;left:0;z-index:1;width:100%;box-sizing:border-box}
.list_product .wrap_desc .txt_desc {display:block;overflow:hidden;margin-top:3px;color:#888;font-size:13px;letter-spacing:-0.5px;line-height:15px;text-overflow:ellipsis;white-space:nowrap}
.list_product .thumb_product {display:none;overflow:hidden;position:absolute;z-index:1;border-radius:50%;background:#fff}
.list_product .bg {position:absolute;bottom:-280px;left:0;z-index:-1;width:303px;height:227px;-webkit-transition:initial;transition:initial}
.list_product .bg:before {position:absolute;top:0;left:0;width:100%;height:100%;background:#f4f4f4;content:""}
.list_product .open .box_product:hover .bg {-webkit-transition:bottom 0.5s cubic-bezier(0.19, 1, 0.22, 1);transition:bottom 0.5s cubic-bezier(0.19, 1, 0.22, 1)} */

/* 상품 */
.sri_product {position:relative;z-index:1;}
.cont_product {width:100%;font-size:15px;letter-spacing:-1px;line-height:18px;}
.cont_product .inner_cont {position:relative;margin:0 auto;width:1000px;}
.top_product {overflow:hidden}
.top_product:after {display:table;clear:both;content:"";table-layout:fixed}
.tit_cont {display:inline-block;color:#222;font-size:18px;font-weight:normal;letter-spacing:-2px;line-height:24px;vertical-align:top}

/* cont */
.cont_product .btn_close:after,
.cont_product .btn_product:after,
.recruit_func {position:absolute;right:17px;bottom:20px;z-index:10}
.recruit_func .num_dday {display:inline-block;margin-top:2px;color:#949494;font-size:12px;letter-spacing:-0.5px;line-height:12px;vertical-align:top}
.recruit_func .num_dday.last {margin-top:0;color:#ff0400}
.recruit_func .sri_btn_xs {margin-left:6px}

/* 상품안내 레이어 */
.cont_product .btn_product {float:right;margin-top:7px;color:#444;font-size:13px;letter-spacing:-1px;line-height:15px}
.cont_product .btn_product span {position:relative}
.cont_product .btn_product:after {display:inline-block;margin:4px 0 0 7px;width:6px;height:9px;vertical-align:top;background-position:-400px -500px}
.cont_product .lpop_wrap {position:absolute;top:22px;right:0;z-index:20;padding:25px 30px 37px;width:420px;border:1px solid #888;box-sizing:border-box;font-size:15px;letter-spacing:-1px;line-height:18px;background:#fff}
.cont_product .lpop_wrap .tit_lpop {display:block;color:#222;font-size:18px;font-weight:normal;line-height:20px}
.cont_product .lpop_wrap .line_top {border-top:1px solid #dadada}
.cont_product .lpop_cont {margin-top:10px}
.cont_product .price_product {padding:0 6px}
.cont_product .price_product .tit_price {margin-top:26px;color:#222;font-weight:bold}
.cont_product .price_product .tit_price:first-child {margin-top:19px}
.cont_product .cont_price {position:relative;margin-top:1px}
.cont_product .cont_price .num_price {display:block;color:#f00;font-weight:bold;letter-spacing:-0.5px}
.cont_product .cont_price .unit_price {display:inline-block;margin:2px 0 0 3px;color:#949494;font-size:13px;font-weight:normal;letter-spacing:-1px;line-height:13px;vertical-align:top}
.cont_product .list_price li {margin-top:9px;color:#444;font-size:13px;line-height:13px}
.cont_product .list_price li:before {display:inline-block;margin:2px 8px 0 0;color:#d8d8d8;font-size:13px;line-height:7px;vertical-align:top;content:""}
.cont_product .list_price .txt_price {float:right;margin-right:20px;color:#f00;font-size:14px;letter-spacing:-0.5px}
.cont_product .list_price .txt_price.num {margin-right:0}
.cont_product .list_price .ico_plus {display:inline-block;margin:-1px 2px 0 0;color:#444;font-size:13px;vertical-align:top}
.cont_product .cont_price .list_price .unit_price {margin:0 0 0 3px;font-size:12px}
.cont_product .price_link {position:absolute;top:-7px;right:0}
.cont_product .price_link .link_product {float:left;margin-left:5px;padding:4px 0;width:66px;height:24px;border:1px solid #00a5a7;box-sizing:border-box;color:#fff;font-size:12px;line-height:12px;text-align:center;background:#00a5a7}
.cont_product .price_link .link_product:first-child {color:#00a5a7;background:#fff}
.cont_product .btn_close {position:absolute;top:0;right:0;width:52px;height:53px}
.cont_product .btn_close:after {margin:0 auto;width:13px;height:13px;background-position:-700px -500px}
.cont_platinum.cont_product .lpop_wrap {top:35px}
.cont_prime.cont_product .lpop_wrap {top:33px}
.cont_special.cont_product .lpop_wrap {top:35px}
.cont_hotbanner.cont_product .lpop_wrap {top:34px}
.cont_best.cont_product .lpop_wrap {top:34px}
.cont_extract.cont_product .lpop_wrap {top:33px}
.cont_narae.cont_product .lpop_wrap {top:35px}

/* 스페셜 */
.cont_special {padding-top:0px;background:#fff}
.cont_special .list_product li {margin-top:13px;height:135px}
.cont_special .box_product:after {border:1px solid #b4d2ff}
.cont_special .box_product:hover:after,
.cont_special .basic .box_product:hover:after {border:1px solid #4876ef}
.cont_special .open .box_product:before {height:3px;background:#91beff}
.cont_special .open .box_product:hover {height:283px}
.cont_special .open .box_product:hover:before,
.cont_special .basic .open .box_product:hover:before {background:#4876ef}
.cont_special .open .box_product:hover:after {border:1px solid #4876ef}
.cont_special .product_logo {margin:19px auto 0;height:55px;font-size:18px;line-height:31px}
.cont_special .product_tit {margin-top:8px;font-size:13px}
.cont_special .product_desc {margin-top:1px;font-size:16px;line-height:22px}
.cont_special .product_desc.open {width:186px}
.cont_special .open .box_product:hover .thumb_product {display:block;right:22px;bottom:98px;width:90px;height:90px}
.cont_special .open .box_product:hover .wrap_desc {top:185px;padding:0 22px;font-size:12px}
.cont_special .btn_scrap {bottom:15px}
.cont_special .recruit_func {bottom:14px}
.cont_special .basic .open .box_product:before {background:#ccd4e3}
.cont_special .basic .box_product:after {border:1px solid #ccd4e3}
.cont_special .list_product .bg:before {-webkit-transform:skew(0deg, -12deg);-ms-transform:skew(0deg, -12deg);transform:skew(0deg, -12deg);content:""}
.cont_special .list_product .open .box_product:hover .bg {bottom:-92px}

</style>