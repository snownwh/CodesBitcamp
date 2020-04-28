<template>
    <div class="l_main">
        <button class="btn btn-primary" @click="createGroup" style="float: right;">Group Create</button>
        
        <h1>내 그룹</h1>
        <MyGroup></MyGroup>


        <div class="hr"></div>
        <p>전체 그룹</p>
        <MyOtherGroup></MyOtherGroup>
    </div>

    
</template>

<script>
import MyOtherGroup from '@/components/GroupList/MyOtherGroup.vue'
import MyGroup from '@/components/GroupList/MyGroup.vue'
export default {
    components: { MyOtherGroup, MyGroup },
    methods:{
     createGroup(){
      this.$router.push({name :"Create"})
     }
    }
}
</script>

<style scoped>
.hr{
  display: flex;
  margin: auto;
  margin-top: 15px;
  margin-bottom: 5px;
  background:#c1c1c1;
  height: 1px;
  width: 80%;
}
</style>