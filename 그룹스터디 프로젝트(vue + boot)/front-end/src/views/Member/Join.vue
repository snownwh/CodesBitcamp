<template>
  <div>
        <div id="cont_wrap">
            <div class="memberbox">
                    <div class="box">
                        <p class="tit icon01"><strong>일반</strong>회원가입</p>
                        <div class="con">
                            <img src="../../assets/css/images/member.png" alt="일반">
                        </div>
                        <p class="btn"><router-link :to="{name:'memberJoin'}" class="con_btn blue">일반회원가입 바로가기</router-link></p>
                    </div>
                    <div class="box">
                        <p class="tit icon02"><strong>기업</strong>회원가입</p>
                        <div class="con">
                            <img src="../../assets/css/images/company.png" alt="기업">
                        </div>
                        <p class="btn"><router-link :to="{name:'companyJoin'}" class="con_btn blue">기업회원가입 바로가기</router-link></p>
                    </div>
            </div>
        </div>

  </div>
</template>

<script>
export default {

}
</script>

<style>
/***로그인&아이디비밀번호찾기 공통박스***/
.member_box{position:relative;border:1px solid #e5e4e5; clear:both;display:inline-block; width:100%; height:450px;}
.member_box .bg{position:absolute;display:inline-block;}
.member_box .tit{font-size:30px;color:#222; font-family:'NanumSquareR'; font-weight:800; line-height:1.2; letter-spacing:-1px;}
.member_box .txt{font-size:15px;color:#555;padding-bottom:20px;}
.member_box .join {background:url(/nanum/site/builder/dir/main/img/menu730/login_bg_img.png) no-repeat bottom #f5f7f9; height:100%;}
.member_box .area{width:50%; float:left; padding:100px 50px; box-sizing:border-box;}
.member_box .area p{position:relative;margin-bottom:10px;}
.member_box .area p input{width:100%;height:52px;line-height:52px;font-size:14px;padding:0 15px; border:0; background:#f5f5f5; box-sizing:border-box;}
.member_box .area p input:focus{border:1px solid #222;background:#f8f8f8;}
.member_box .area p label{position:absolute;top:0;left:16px;height:52px;line-height:52px;font-size:14px;color:#999;}
.member_box .area input.inp_btn,
.member_box .area a.inp_btn{display:inline-block;width:100%;padding:14px 0;line-height:normal;border:none;margin:0;color:#fff;font-family:'NanumSquareR';font-size:15px;background:#4f40b4;}
.member_box .area input.inp_btn:hover{background:#000;}
.member_box .join_btn {display:inline-block; padding: 10px 30px; border: 1px solid #4f40b4; font-size:15px; color:#4f40b4; text-transform: uppercase;  transition: all 0.3s;}
.member_box .join_btn:hover {background:#4f40b4; color:#fff;  }


/***회원가입&본인인증 공통박스***/
.memberbox{display:inline-block;width:100%;  padding-top: 125px}
.memberbox .box{position:relative;float:left;width:420px;margin-left:110px;padding:60px 40px 40px;min-height:600px;border:1px solid #dfdfdf;transition:all 0.2s ease;-webkit-transition:all 0.2s ease;}
.memberbox .box:first-child{margin-left:0;}
.memberbox .box .tit{border-bottom:1px dashed #ccc;font-size:30px; padding-bottom:30px; color:#222;text-align:center;font-family:'NanumSquareR';background-repeat:no-repeat;background-position:center 56px;}
.memberbox .box .con > ul{padding-top:22px;}
.memberbox .box .btn{position:absolute;bottom:40px;left:0;width:100%;text-align:center;}
.memberbox .box:hover{border:1px solid #ef6c00;box-shadow:8px 10px 20px rgba(0,0,0,0.2);-webkit-box-shadow:8px 10px 20px rgba(0,0,0,0.2);}

/*회원가입*/
.memberbox .box .tit.icon01{background:url(/nanum/site/builder/dir/main/img/menu730/join_icon01.gif)no-repeat center 0;}
.memberbox .box .tit.icon02{background:url(/nanum/site/builder/dir/main/img/menu730/join_icon02.gif)no-repeat center 0;}

.con_btn.blue, .con_sbtn.blue {
    background: #ef6c00;
    display: inline-block;
    margin: 0 3px;
    color: #fff;
    padding: 16px 40px;
    font-weight: normal;
    font-family: 'NanumSquareR';
    font-size: 15px;
    line-height: normal;
    vertical-align: middle;
    text-align: center;
    cursor: pointer;
    border: 0;
    letter-spacing: -0.05em;
}
.cont_wrap{
    padding-top: 100px;
}
.con img{
    width: 170px;
    margin-left: 90px;
    margin-top: 100px;
}

</style>