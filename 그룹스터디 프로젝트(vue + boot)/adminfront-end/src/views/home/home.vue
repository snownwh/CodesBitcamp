<template>
<div>

    <div class="leftBar">
      <ul>
          <li><router-link class="active" to="child">Home</router-link></li>
          <li><a href="#news">News</a></li>
          <li><a href="#contact">Contact</a></li>
          <li><a href="#about">About</a></li>
      </ul>
    </div> 
    <div style="margin-left:25%;">
      <router-view ></router-view>
    </div>
      
</div>
</template>

<script>
export default {

}
</script>

<style>

</style>