<template>
    <el-table 
        v-loading="this.$store.state.s_group.showBoardList"
        :row-class-name="clickableRows"
        :data="this.$store.state.s_group.groupBoardDetailComments"
        stripe
        style="width: 100% cursor:pointer"
        @row-click="gotoClick">

        <el-table-column
          prop="finalnum"
          label="글번호"
          width="150px">
        </el-table-column>
        <el-table-column
          prop="title"
          label="글제목"
          width="470px">
        </el-table-column>
        <el-table-column
          prop="memberDto.memberId"
          label="작성자"
          width="100px">
        </el-table-column>
        <el-table-column
          prop="readCount"
          label="조회수"
          width="100px">
        </el-table-column>
        <el-table-column
          prop="writeDate"
          label="작성일"
          width="170px">
        </el-table-column>


    </el-table>
</template>

<script>
export default {

}
</script>

<style>

</style>