<template>
  <div>
      <el-input placeholder="TODO LIST를 작성해주세요. 예)매일 독서, #공부" v-model="list" @input="show" style="width: 70%; margin-right: 10px; margin-left: 6px;"></el-input>
      <el-button type="success" plain @click="listAdd" v-model="add" v-if="add">할일 추가</el-button>
      <el-button type="success" plain @click="listAdd" v-model="add" disabled v-else>할일 추가</el-button>
      <el-button plain @click="cancle">취소</el-button>
  </div>
</template>

<script>
export default {
    data(){
        return{
            list: '',
            add: false
        }
    },
    methods:{
        show(){
            this.list == ''? this.add = false : this.add = true
        },
        cancle(){
            this.$emit("cancle")
        },
        listAdd(){
            if(this.list == ''){
                this.add =false
            }else{
                var loginData = sessionStorage.getItem("loginUser");
                var login = JSON.parse(loginData); 
                var memSeq = login.memberSeq

                var params = new URLSearchParams();
                params.append('memberSeq', memSeq)
                params.append('title', this.list)
                axios.post('http://localhost:9000/addTodoList', params)
                .then(res => {
                    this.$emit("listAdd", this.list)
                    this.list = ''
                }) 
            }
        },

    }
}
</script>

<style>

</style>