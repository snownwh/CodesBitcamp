<template>
  <div>
   <div class="slib">
            <div class="slib_info">
                <div class="tit">
                  <img src='../../assets/css/images/area.png'>
                </div>
                <div class="titup">
                   <table class="table1" style="margin-left: 55px">
                     <colgroup>
                    <col style="width: 270px">
                    <col style="width: 30px">
                    <col style="width: 50px">
                    </colgroup>
                      <thead>
                        <tr>
                          <th style="text-align: center;">관심지역</th>
                          <th></th>
                          <th></th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td colspan="3">{{myinter.interArea}}</td>
                          <td><i class="el-icon-paperclip" style="color: #ff5151"></i> </td>
                          <td>
                            <el-button type="text" @click="editAddr" style="color: #ff5151; font-size: 16px">수정</el-button>
                          </td>
                        </tr>                   
                      </tbody>
                    </table>
                    <table class="table1" v-if="addr">
                      <tbody>
                        <tr>
                             <el-button type="info" plain style="width: 100px; padding: 11px;" @click="execDaumPostcode">우편번호 찾기</el-button>
                        </tr>
                        <tr>
                          <td>
                            <el-input  placeholder="주소" readonly="readonly" v-model="address"></el-input>
                          </td>
                          <td>
                            <el-input  placeholder="상세주소" style="width: 300px" v-model="extraAddress"></el-input>
                          </td>
                          <td style="width: 14px"></td>
                          <td><i class="el-icon-s-tools" style="color: #d77f4a"></i> </td>
                            <td>
                            <el-button type="text" @click="editArea" style="color: #d77f4a; font-size: 16px">수정하기</el-button>
                          </td>
                        </tr>                   
                      </tbody>
                    </table>
                </div>
            </div>
        </div>
   <div class="slib">
            <div class="slib_info">
                <div class="tit">
                  <img src='../../assets/css/images/puzzle.png'>
                </div>
                <div class="titup">
       
                <p>관심분야</p>
                <h3 v-for="big in myBig" :key="big.seq">
                  {{big.bigName}}
                </h3>
                <br>
              <p>상세분야</p>
                <h3 v-for="small in mySmall" :key="small.seq">
                  {{small.smallName}}
                </h3>
                <el-button @click="showshow"></el-button>
                <Inter 
                v-if="showInter"
                :memSeq="memSeq"
                @close="showInter = false"
                @update="updateInter">
                </Inter>
                </div>
            </div>
        </div>
  

        


  </div>
</template>

<script>
import logincss from '@/assets/css/member/myinfo.css'
import Inter from "@/views/MyPage/interestUpdate.vue"
export default {
  props:['myinter','myBig','mySmall','memSeq'],
  components:{
      Inter,
    },
  data(){
      return{
          addr: false,
          showInter: false,
          address: '',
          extraAddress: '',
          postcode: '',
      }
    },
     methods: {
       editAddr(){
         this.addr = true;
       },
      editArea(){
        if(this.address =='' || this.extraAddress ==''){
          alert('주소를 입력해주세요')
         }else{
          var params = new URLSearchParams();
          var realAddr = this.address + this.extraAddress
          params.append('memberSeq', this.memSeq)
          params.append('interArea', realAddr)
         axios.post('http://localhost:9000/updateInterArea', params)
            .then(res => {
              alert('정보수정이 완료되었습니다')
              this.addr = false
              this.$emit('updateInterArea', this.memSeq)
            })
         }
      },
       execDaumPostcode() {
            new daum.Postcode({
            onComplete: (data) => {
            if (data.userSelectedType === 'R') {
              this.address = data.roadAddress;
            } else {
              this.address = data.jibunAddress;
            }
            if (data.userSelectedType === 'R') {
              if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
              this.extraAddress += data.bname;
              }
              if (data.buildingName !== '' && data.apartment === 'Y') {
              this.extraAddress +=
                this.extraAddress !== ''
                ? `, ${data.buildingName}`
                : data.buildingName;
              }
              if (this.extraAddress !== '') {
              this.extraAddress = ` (${this.extraAddress})`;
              }
            } else {
              this.extraAddress = '';
            }
            },
          }).open(); 
        },
       showshow(){
         this.showInter = true
       },
       updateInter(){
         this.showInter = false
           var params = new URLSearchParams();
		      params.append('memberSeq', this.$route.params.seq)
          axios.post('http://localhost:9000/myPageInter', params)
          .then(res => {
            var smyinfo = res.data
            this.myinfo = smyinfo
            
            var bg = smyinfo.interBigDto
            var sm = smyinfo.interSmallDto
        
            var big;
            big = JSON.stringify(bg)
            big = JSON.parse(big)
            this.myBig = big

            var small;
            small = JSON.stringify(sm)
            small = JSON.parse(small)
            this.mySmall = small
          })
       }
  }
}
</script>

<style>

</style>