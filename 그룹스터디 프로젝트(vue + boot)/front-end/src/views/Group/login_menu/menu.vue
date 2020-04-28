<template>
<div class="menu">
    <br>
    <el-tabs tab-position="top" style="height: auto;">
        <el-tab-pane label="그룹 정보"><GroupInfo></GroupInfo></el-tab-pane>
        <el-tab-pane label="그룹 일정"><GroupCalendar></GroupCalendar></el-tab-pane>
        <el-tab-pane label="그룹 게시판">
            <GroupBoard v-show="groupBoard" v-on:showDetail="showDetail" v-on:showWrite="showWrite"/>
            <boardDetail v-show="boardDetail" v-on:showBoard="showBoard" v-on:showUpdate="showUpdate"/>
            <boardWrite v-show="boardWrite" v-on:showBoard="showBoard"/>
            <boardUpdate v-show="boardUpdate" v-on:showBoard="showBoard" v-on:showDetail="showDetail"/>
        </el-tab-pane>
        <el-tab-pane label="그룹 자료실">
            <GroupReference v-show="groupReference" v-on:showReferenceWrite="showReferenceWrite" v-on:showReferenceDetail="showReferenceDetail"/>
            <ReferenceDetail v-show="referenceDetail" v-on:showGroupReference="showGroupReference"/>
            <ReferenceWrite v-show="referenceWrite" v-on:showGroupReference="showGroupReference"/>
        </el-tab-pane>
    </el-tabs>
</div>
</template>

<script>
import GroupInfo from '@/views/Group/login_menu/groupInfo.vue'
import GroupCalendar from '@/views/Group/login_menu/groupCalendar.vue'

import GroupBoard from '@/views/Group/login_menu/groupBoard.vue'
import boardDetail from '@/views/Group/detail/board_detail.vue'
import boardWrite from '@/views/Group/detail/board_write.vue'
import boardUpdate from '@/views/Group/detail/board_update.vue'

import GroupReference from '@/views/Group/login_menu/groupReference.vue'
import ReferenceWrite from '@/views/Group/detail/reference_write.vue'
import ReferenceDetail from '@/views/Group/detail/reference_detail.vue'

export default {
    components:{ 
        GroupInfo, GroupCalendar,
        GroupBoard,  boardDetail, boardWrite, boardUpdate,
        GroupReference, ReferenceDetail, ReferenceWrite },
    data() {
        return {
            groupBoard: true,
            boardDetail: false,
            boardWrite: false,
            boardUpdate: false,

            groupReference: true,
            referenceDetail: false,
            referenceWrite: false,
        };
    },
    methods:{
        showDetail() {
            this.boardDetail = true
            this.boardWrite = false
            this.groupBoard = false
            this.boardUpdate = false
        },
        showBoard(){
            this.groupBoard = true
            this.boardDetail = false
            this.boardWrite = false
            this.boardUpdate = false
        },
        showWrite(){
            this.boardWrite = true
            this.groupBoard = false
            this.boardDetail = false
            this.boardUpdate = false
        },
        showUpdate(){
            this.boardUpdate= true
            this.groupBoard = false
            this.boardDetail = false
            this.boardWrite = false
        },
        showReferenceWrite(){
            this.referenceWrite = true
            this.groupReference = false
            this.referenceDetail = false
        },
        showGroupReference(){
            this.groupReference = true
            this.referenceWrite = false
            this.referenceDetail = false
        },
        showReferenceDetail(){
            this.referenceDetail = true
            this.groupReference = false
            this.referenceWrite = false
        }
    },
    mounted(){

    }
}
</script>

<style>

</style>