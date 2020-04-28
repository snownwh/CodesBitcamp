<template>
  <div>
    <el-card shadow="hover" v-for="(task, index) in list" :key="index" style="margin:10px 5px 10px 5px;">
     <div class="default">
      <div v-bind:class="{'done': task.del == 1}" > 
       <p>{{task.todoDate}}</p>
       <h4>{{task.title}}</h4>
       <p v-if="task.del ==1" style="margin-top: 12px; flaot:left">완료 됨</p>
      </div>
      <el-button type="text" @click="del(task.todoSeq)" v-if="task.del == 0" >삭제</el-button>
      <el-button type="text" @click="done(task.todoSeq)" v-if="task.del ==0">완료</el-button>
      <el-button type="text" @click="edit(task.todoSeq, index, task.title)" v-if="task.del ==0">수정</el-button>
        <div v-if="index == clicked">
          <div v-if="isShow">
            <el-input v-model="editList" placeholder="수정사항을 작성해주세요." @input="editVal" style="width:80%; margin-right: 5px"></el-input>
            <el-button type="success" plain @click="editReal(task.todoSeq)" v-model="editBtn" v-if="editBtn">수정</el-button>
            <el-button type="success" plain @click="editReal(task.todoSeq)" v-model="editBtn" disabled v-else>수정</el-button>
          </div>
        </div><!-- clicked -->
     </div> <!-- default -->
    </el-card> 
 
  </div>
</template>

<script>
export default {
    props:["list"], 
    data(){
        return{

        }
    }
}
</script>

<style scoped>

.done h4{
 text-decoration: line-through;
  color: #cecece;
}
.done p{
  color: #cecece;
}
.default{
  margin: 10px;
}
.default p{
 margin: 0px 0px 10px;
}
</style>