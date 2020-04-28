<template>
  <div id="app">
    <div id="wrap">
      <div id="header" class="sub">
        <div id="header-top">
          <div class="container">
            <!-- header-top -->
            <HeaderTop></HeaderTop>
          </div>
        </div>
        <div id="header-nav">
          <div class="container" style="height: 40px;">
            <!-- header-nav -->
            <HeaderNav></HeaderNav>
          </div>
        </div>
      </div>
      <div id="contents">
        <div class="container">
          <!-- contents -->
          <Contents></Contents>
        </div>
      </div>
      <div id="footer">
        
        <div id="footer-nav">
          <div class="container">
            <!-- footer-nav -->
            <FooterNav></FooterNav>
          </div>
        </div>

           <div id="footer-info">
          <div class="container">
            <!-- footer-info -->
            <FooterInfo></FooterInfo>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>

<script>
// @ is an alias to /src
import HeaderTop from '@/components/header/Header-top.vue'
import HeaderNav from '@/components/header/Header-nav.vue'
import FooterNav from '@/components/footer/Footer-nav.vue'
import FooterInfo from '@/components/footer/Footer-info.vue'
import Contents from '@/components/Contents.vue'
import global from '@/assets/css/global.css'
//import elementUiCss from '@/assets/css/element-ui.css'

export default {
  name: 'Home',
  components: {
    HeaderTop,
    HeaderNav,
    FooterNav,
    FooterInfo,
    Contents
  }
}
</script>