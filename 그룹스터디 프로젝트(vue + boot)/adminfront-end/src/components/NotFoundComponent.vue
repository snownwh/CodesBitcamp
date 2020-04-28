<template>
<div class="NotFoundComponent">
    <h1>요청하신 페이지를 찾을 수 없습니다</h1>
    <br>
    <br>
    <br>
    <h1>404 Not Found Page</h1>
    <br>
    <br>
    <br>
    <router-link to="/">
        <img src="@/assets/css/images/logo.png">
    </router-link>
    <br>
    <br>
    <br>
    <br>
    <br>
    <router-link to="/"><h1><P>메인 페이지</p> <U><p>이동하기 클릭</p></U></h1></router-link>

</div>
</template>

<script>
export default {

}
</script>

<style scope>
.NotFoundComponent {
    text-align: center;
    margin-top: 50px;
}
</style>