<template>
  <div class="footer">
    <hr>
    <div style="text-align:left; margin-bottom:8px">
        <router-link :to="{path: '/'}" exact>
            <img style="height:30px" src='../../assets/css/images/logo.png' alt="팔레트" />
        </router-link>
        <span style="float:right">
        <span><a href="#"><img style="height:32px" src="../../assets/css/images/ico_insta.png" alt="인스타그램"></a></span>&nbsp;
				<span><a href="#"><img style="height:32px" src="../../assets/css/images/ico_fb.png" alt="페이스북"></a></span>&nbsp;
				<span><a href="#"><img style="height:32px" src="../../assets/css/images/ico_blog.png" alt="네이버블로그"></a></span>&nbsp;
				<span><a href="#"><img style="height:32px" src="../../assets/css/images/ico_naverpost.png" alt="네이버포스트"></a></span>&nbsp;
				<span><a href="#"><img style="height:32px" src="../../assets/css/images/ico_youtube.png" alt="유튜브"></a></span>				
        </span>
    </div><br>
    <div style="text-align:left; font-size:13px; color:#888888">
      <p>대표자:신종하</p>
      <p>사업장 소재지:(주)PALETTE &nbsp;서울특별시 서초구 서초4동 강남대로 459</p>
      <p style="margin-bottom:15px">문의:1588-1588</p>
      <p>Copyright © PALETTE㈜ All Rights Reserved</p>
    </div>
  </div>
</template>

<script>
import global from '@/assets/css/global.css'

export default {
    name: "FooterInfo"
}
</script>

<style scoped>
  hr{border-top: 1px solid #bbb}
  p, span {margin-bottom:9px;}
</style>