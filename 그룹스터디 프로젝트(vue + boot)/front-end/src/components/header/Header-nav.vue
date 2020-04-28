<template>
<!-- <router-link to="/home">HOME</router-link> |
<router-link to="/aboutus">About Us</router-link> |
<router-link to="/private">개인스터디</router-link> |
<router-link to="/group">그룹스터디</router-link> |
<router-link to="/employment">취업</router-link> |
<router-link to="/notice">게시판</router-link> |
<router-link to="/search">검색</router-link> -->
    <div class="header-btm">
			<div class="inner">
				<ul class="gnb clfix">
                    <li><router-link to="/aboutus">ABOUT US</router-link></li>
                    <li><router-link to="/private">개인스터디</router-link></li>
                    <li><router-link to="/group">그룹스터디</router-link></li>
                    <li><router-link to="/employment">취업</router-link></li>
                    <li><router-link to="/notice">게시판</router-link></li>
                    <!-- <li><router-link to="/search">검색</router-link></li> -->
                </ul>	
                <div class="headerBar">
                </div>
                <div class="all_menu">
                    <div class="inner">
                        <ul>
                            <li>
                                <strong>ABOUT US</strong>
                                <a href="#">팔레트</a>
                            </li>
                            <li>
                                <strong>개인스터디</strong>
                                <a href="#">1</a>
                                <a href="#">2</a>
                                <a href="#">3</a>
                            </li>
                            <li>
                                <strong>그룹스터디</strong>
                                <a href="#">1</a>
                            </li>
                            <li>
                                <strong>취업</strong>
                                <a href="#">1</a>
                                <a href="#">2</a>
                                <a href="#">3</a>
                            </li>
                            <li>
                                <strong>게시판</strong>
                                <a href="#">1</a>
                                <a href="#">2</a>
                                <a href="#">3</a>
                            </li>
                        </ul>
                    </div>
                </div>

			</div><!-- //inner -->
		</div><!-- //header-btm -->
</template>

<script>
export default {
    name: "HeaderNav"
}
</script>

<style scoped>


.headerBar{
    line-height: 30px;
    height: 1px;
    background: #d6d6d6;
    margin: auto calc(0% - 50vw); /* container 무시하고 전체 적용 */
    text-align: center;
} 

</style>