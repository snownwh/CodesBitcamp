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
        :data="adminAllStudyGroupList.filter(data=> String(data.permission).includes(value.value))"
        stripe
        style="width:100%"
        >
        <!-- @row-click="eventReceiver" -->
        <!-- @cell-click="inputcellclick()" -->

            <el-table-column
            prop="currMember"
            label="currMember"
            width="100">
            </el-table-column>

            <el-table-column
            prop="del"
            label="del"
            width="50">
            </el-table-column>

            <el-table-column
            prop="startDate"
            label="startDate"
            width="100">
            </el-table-column>

            <el-table-column
            prop="endDate"
            label="endDate"
            width="100">
            </el-table-column>

            <el-table-column
            prop="groupInfoSeq"
            label="groupInfoSeq"
            width="100">
            </el-table-column>

            <el-table-column
            prop="groupLocation"
            label="groupLocation"
            width="100">
            </el-table-column>

            <el-table-column
            prop="groupName"
            label="groupName"
            width="100">
            </el-table-column>
            
            <el-table-column
            prop="image"
            label="image"
            width="100">
                <div slot-scope="{row}" class="img-container">
                    <img :src="row.image" alt="Agenda"
                    style="width:100px; height:100px;"
                    >
                </div>
            </el-table-column>

            <el-table-column
            prop="info"
            label="info"
            width="100">
            </el-table-column>

            <el-table-column
            prop="interBigSeq"
            label="interBigSeq"
            width="100">
            </el-table-column>

            <el-table-column
            prop="interSmallSeq"
            label="interSmallSeq"
            width="90">
            </el-table-column>

            <el-table-column
            prop="maxMember"
            label="maxMember"
            width="100">
            </el-table-column>

            <el-table-column
            prop="memberSeq"
            label="memberSeq"
            width="100">
            </el-table-column>

            <el-table-column
            prop="permission"
            label="permission"
            width="100">
            </el-table-column>

            <el-table-column
            prop="smallInfo"
            label="smallInfo"
            width="100">
            </el-table-column>

            <el-table-column
              prop=""
              label="button"
              width="120" >
                <template slot-scope="scope">
                    <div v-loading="loading">
                        <el-button
                            v-if="scope.row.permission===0"
                            size="mini"
                            @click="changePermission(scope,1)"> 개설허가 </el-button>

                        <el-button
                            v-if="scope.row.permission===1"
                            size="mini"
                            type="danger"
                            @click="changePermission(scope,0)"> 개설취소 </el-button>
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
                value: '1',
                label: '승인완료'
                }, {
                value: '0',
                label: '비승인'
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
            
            axios.post("http://localhost:9000/AdmingetAllStudyGroup")
            .then(res => {
                
                console.log("admin all group print ")
                this.$store.state.adminAllStudyGroupListSelector = this.value
                this.$store.state.adminAllStudyGroupList = res.data

                this.value=this.$store.state.adminAllStudyGroupListSelector
                this.adminAllStudyGroupList = this.$store.state.adminAllStudyGroupList

                console.log(this.adminAllStudyGroupList)
                
                console.log("base selected value : ") 
                console.log(this.selectedvalue[0].value)

            })
        },
        changePermission(scope, permissionStatus){

            const groupInofSeq = scope.row.groupInfoSeq
            const memberSeq =  scope.row.memberSeq
            const perStatus = permissionStatus
            var inputStr=""

            
            this.loading=true


            console.log("투루펄스 여부 " + this.loading)
        
            if(permissionStatus=1){
                inputStr="그룹개설신청 허가하시겠습니까"
            }else{
                inputStr="그룹개설을 취소하겠습니까"
            }

            const confirmflag = confirm(inputStr);
            if(confirmflag){

                var params = new URLSearchParams();	// post 방식으로 받아야함.
                params.append('groupInfoSeq', groupInofSeq);
                params.append('memberSeq', memberSeq);
                params.append('permission', perStatus );

                axios.post(path+"/AdminAcceptCreateGroup",params)
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
        // inputcellclick(row, column, cell, event){
        // console.log(row);
        // console.log(column);
        // console.log(event);
        // console.log(event);
        // },
        //  handleEdit(index, row) {
        // console.log(index) 
        // console.log(row.memberDto.address);
        // },
        // handleDelete(index, row) {
        //     console.log(index, row);
        // },
        // resdataFunc(adminAllStudyGroupList){
        //     console.log("resdata method 진입 ")
        //     console.log(this.adminAllStudyGroupList);
            
        // },

        // acceptPermissionStatus(scope){
        //     // scope.row.groupInfoSeq, scope.row.memberSeq, 1
        //     console.log(scope.row.groupInfoSeq);
        //     console.log(scope.row.memberSeq);
        //     console.log(1);
        //     changePermission(scope.row.groupInfoSeq, scope.row.memberSeq, 1 )

        // },
        // canclePermissionStatus(scope){
        //     // scope.row.groupInfoSeq, scope.row.memberSeq, 0
        //     console.log(scope.row.groupInfoSeq);
        //     console.log(scope.row.memberSeq);
        //     console.log(0);
        //     changePermission(scope.row.groupInfoSeq, scope.row.memberSeq, 0 )

        // },




    }//end of methods()

}// end of export default Area
</script>

<style>

</style>