<template>
  <div class="private">
   <el-tabs v-model="activeName" @tab-click="handleClick">
    <el-tab-pane label="TO DO LIST" name="first">
      <Todo></Todo>
    </el-tab-pane>
    <el-tab-pane label="활동로그" name="second">
      <TodoChart></TodoChart>
    </el-tab-pane>
    <el-tab-pane label="나의 일정" name="third"> 
      <Schedule></Schedule>
    </el-tab-pane>
    <el-tab-pane label="자료실" name="forth">
      <Board></Board>
    </el-tab-pane>
  </el-tabs>
  </div>
</template>


<style scoped>
  .private{
    padding-top: 10px;
    min-height: 100vh;
  }
</style>

<script>
import Schedule from '@/views/Private/priSchedule.vue'
import Board from '@/views/Private/priBoard.vue'
import Todo from '@/views/Private/priTodo.vue'
import TodoChart from '@/views/Private/priChart.vue'
 export default {
    components:{
        Schedule,
        Board,
        Todo,
        TodoChart
    },
    data() {
      return {
        activeName: 'first'
      };
    },
    methods: {
      handleClick(tab, event) {
        console.log(tab, event);
      }
    }
  };
</script>