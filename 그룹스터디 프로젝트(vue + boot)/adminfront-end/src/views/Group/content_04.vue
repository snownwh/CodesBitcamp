<template>
    <div class="content02_view">
        <h1> {{title}} show Group list</h1>
    <pre>
 	GROUP_INFO_SEQ  =  groupInfoSeq
	GROUP_INFO_SEQ  =  groupInfoSeq
	MEMBER_SEQ  =  memberSeq
	MEMBER_NAME  =  memberName
	INTER_BIG_SEQ  =  interBigSeq
	BIG_NAME  =  bigName
	INTER_SMALL_SEQ  =  interSmallSeq
	SMALL_NAME  =  smallName
	
    </pre>
        
    <el-table 
    :data="GroupList" 
    stripe 
    style="width:100%">

        <el-table-column
            prop="groupInfoSeq"
            label="groupInfoSeq"
            width="100">
        </el-table-column>

        <el-table-column
            prop="groupInfoSeq"
            label="groupInfoSeq"
            width="100">
        </el-table-column>

        <el-table-column
            prop="memberSeq"
            label="memberSeq"
            width="100">
        </el-table-column>

        <el-table-column
            prop="memberDto.memberName"
            label="memberName"
            width="100">
        </el-table-column>

        <el-table-column
            prop="interBigSeq"
            label="interBigSeq"
            width="100">
        </el-table-column>

        <el-table-column
            prop="interBigDto.bigName"
            label="bigName"
            width="100">
        </el-table-column>

        <el-table-column
            prop="interSmallSeq"
            label="interSmallSeq"
            width="100">
        </el-table-column>

        <el-table-column
            prop="interSmallDto.smallName"
            label="smallName"
            width="100">
        </el-table-column>

    </el-table>





    </div>
</template>

<script>
export default {
    data(){
        return {
            title:'group1',
            GroupList:[]
        }
    },
created(){

    axios.get("http://localhost:9000/AdmingetAllGroup")
    .then(res => {
        console.log(res.data)
    this.GroupList = res.data
    })

},
methods:{
    // shwoAll(){
    //   axios.post("http://localhost:9000/AdmingetAllGroup")
    //           .then(res => {
    //       this.list = res.data
    //     })
    // }
  }


}
</script>

<style>

</style>