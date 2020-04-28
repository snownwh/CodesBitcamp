<template>
    <div class="content02_view">
        <h1> {{title}} group 22222222222222222221122222222222222222222222221</h1>
        
            <el-select v-model="value" placeholder="Select" value-key="value">
                <el-option
                v-for="item in selectedvalue"
                :key="item.value"
                :label="item.label"
                :value="item"
                >
                <!-- :value="item.value" -->
                </el-option>
            </el-select>
        
        <el-table 
        :data="adminAllStudyGroupList.filter(data=> String(data.auth).includes(value.value))"
        stripe
        style="width:100%"
        >
        <!-- @row-click="eventReceiver" -->
        <!-- @cell-click="inputcellclick()" -->

            <el-table-column
            prop="memberId"
            label="memberId"
            width="100">
            </el-table-column>


            <el-table-column
            prop="memberName"
            label="memberName"
            width="100">
            </el-table-column>

             <el-table-column
            prop="companyLogo"
            label="companyLogo"
            width="100">
                <div slot-scope="{row}" class="img-container">
                    <img :src="row.companyLogo" alt="NocompanyLogo"
                    style="width:100px; height:100px;"
                    >
                </div>
            </el-table-column>

            <el-table-column
            prop="address"
            label="address"
            width="100">
            </el-table-column>

            <el-table-column
            prop="cv"
            label="cv"
            width="100">
            </el-table-column>

            <el-table-column
            prop="email"
            label="email"
            width="100">
            </el-table-column>

            <el-table-column
            prop="companyInfo"
            label="companyInfo"
            width="100">
            </el-table-column>
            
            <el-table-column
            prop="myMoney"
            label="myMoney"
            width="100">
            </el-table-column>

            <el-table-column
            prop="del"
            label="del"
            width="50">
            </el-table-column>

            <el-table-column
            prop="dislike"
            label="dislike"
            width="80">
            </el-table-column>

            <el-table-column
            prop="info"
            label="info"
            width="100">
            </el-table-column>

            <el-table-column
            prop="memberSeq"
            label="memberSeq"
            width="100">
            </el-table-column>

            <el-table-column
            prop="auth"
            label="auth"
            width="60">
            </el-table-column>


            <el-table-column
              prop=""
              label="button"
              width="170" >
                <template slot-scope="scope">
                    <div v-loading="loading">
                        <el-button
                            v-if="scope.row.auth===2"
                            size="mini"
                            @click="changeATUH(scope,3)"> 기업회원으로 전환 </el-button>

                        <el-button
                            v-if="scope.row.auth===3"
                            size="mini"
                            type="danger"
                            @click="changeATUH(scope,2)"> 기업회원 허가 철회 </el-button>
                     </div>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import 'element-ui/lib/theme-chalk/display.css';
import 'element-ui/lib/theme-chalk/index.css';
import { loading } from 'element-ui';


const path = "http://localhost:9000";

export default {
    data(){
        return {
            title:'group2',
            adminAllStudyGroupList:[],

            selectedvalue: [{
                value: '',
                label: '전체보기'
                }, {
                value: '2',
                label: '비전환 기업리스트'
                }, {
                value: '3',
                label: '기업회원 전환완료'
            }],
            value: '',
            loading:false,

            search:'permission'
        }
    },
    created(){
        this.getGroupAllowList();
        this.value = this.selectedvalue[0]
    },
    methods:{

        getGroupAllowList(){
            
            axios.post("http://localhost:9000/adminCompanyMemberShowAll")
            .then(res => {
                
                console.log("admin all group print ")
                this.$store.state.adminAllStudyGroupListSelector = this.value
                this.$store.state.adminAllStudyGroupList = res.data

                this.value=this.$store.state.adminAllStudyGroupListSelector
                this.adminAllStudyGroupList = this.$store.state.adminAllStudyGroupList
                console.log("adminAllStudyGroupList 출력 내역 ")
                console.log(this.adminAllStudyGroupList)
                
                console.log("base selected value : ") 
                console.log(this.selectedvalue[0].value)

            })
        },
        changeATUH(scope, AuthStatus){

            const memberSeq = scope.row.memberSeq
            const memberId =  scope.row.memberId
            const auth = AuthStatus
            var inputStr=""

            
            this.loading=true


            console.log("투루펄스 여부 " + this.loading)
        
            if(AuthStatus=1){
                inputStr="기업회원 전환하시겠습니까?"
            }else{
                inputStr="기업회원전환 대기로 변경 하시겠습니까?"
            }

            const confirmflag = confirm(inputStr);
            if(confirmflag){

                var params = new URLSearchParams();	// post 방식으로 받아야함.
                params.append('memberSeq', memberSeq);
                params.append('memberId', memberId);
                params.append('auth', auth );

                axios.post(path+"/changeAuthCompanyMember",params)
                .then(res=>{
                    if(res.data = true)console.log(res.data);
                    this.getGroupAllowList()
                    this.loading=false;
                }).catch(error=>{
                    console.log("오류발생! 오류내용 : "+error)
                    this.loading=false;
                })
                
     
                
            }else{
                alert("취소되었습니다.");
                this.loading=false;
            }
            
        },
        eventReceiver(row, column, event){
            console.log(row);
            console.log(column);
            console.log(event);

            const row1 = JSON.stringify(row);
            console.log(row1)


        },




    }//end of methods()

}// end of export default Area
</script>

<style>

</style>