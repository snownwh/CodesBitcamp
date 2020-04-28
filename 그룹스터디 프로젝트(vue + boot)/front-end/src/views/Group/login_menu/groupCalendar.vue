<template>
  <div class="detail3">
      <br>
      <br>
      <div class="calendar">
      <full-calendar :events="this.$store.state.s_group.groupCalendar" :config="config" 
                      @day-click="dayClick" @event-selected="eventSelected" @event-drop="eventDrop"
                      v-loading="this.$store.state.s_group.showGroupCalendar"
                      ></full-calendar>
        <Cwrite v-if="show_calendar_write" @close="show_calendar_write = false" :startDate="clickDay"></Cwrite>
        <Cdetail v-if="show_calendar_detail" @close="show_calendar_detail = false"></Cdetail>
      </div>
      <el-button 
      @click="compareWith">수정하기</el-button>
  </div>
</template>

<script>

import { loading } from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import 'fullcalendar/dist/fullcalendar.css'
import 'fullcalendar/dist/locale/ko'
import { FullCalendar } from 'vue-full-calendar'
import Cwrite from '@/views/Group/detail/calendar_write.vue'
import Cdetail from '@/views/Group/detail/calendar_detail.vue'

export default {
  name: 'GroupSchedule',
  data(){
    return{
      
      show_calendar_write: false,
      show_calendar_detail: false,
      //memberlist: this.$store.state.s_subStore.data,
      config: {
              locale: 'ko',
              defaultView:'month',
              
      },
      clickDay:'',
    }
  },
  components:{
        Cwrite,
        Cdetail
  },
  methods:{
    getCalendar(){
      this.$store.state.s_group.showGroupCalendar = true

      let params = new URLSearchParams()	
      let groupSeq = this.$store.state.s_group.groupSeq
      params.append('groupInfoSeq', groupSeq)
      axios.post("http://localhost:9000/getGroupCalendar", params)
      .then(res => {
        console.log("DB: "+JSON.stringify(res.data))
        let e = JSON.stringify(res.data)
        this.$store.state.s_group.groupCalendar = JSON.parse(e)
        this.$store.state.s_group.showGroupCalendar = false
      })  
    },
    eventSelected(event, jsEvent, view){
         if(event.end === null){
        event.end = event.start
      }
        alert(event.calendarSeq+"/"+event.title + "/"+ event.start +"/"+ event.end +"/" + event.content+"/"+event.color)
        this.$store.state.s_group.groupCalendarDetail = event
        //alert(this.$store.state.s_group.groupCalendarDetail.start)
        
        this.show_calendar_detail = true
    },
    dayClick(date, jsEvent, view){
        this.$confirm('추가하시겠습니까?', '일정추가', {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel',
          type: 'info'
        }).then(() => {
          console.log(date)

          //alert(date._i)
          this.clickDay = date
          this.$store.state.s_group.groupCalendarStartDate = date._i
          this.show_calendar_write = true

        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'canceled'
          });          
        });
    },
    eventDrop(event){
      
      if(event.end === null){
        event.end = event.start
      }
      //alert(event.calendarSeq+"/"+event.start +"/"+event.end)

      this.$confirm('변경하시겠습니까?', 'Warning', {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {

          this.$store.state.s_group.showGroupCalendar = true
          let params = new URLSearchParams()	
          let groupSeq = this.$store.state.s_group.groupSeq
          params.append('calendarSeq', event.calendarSeq)
          params.append('start', this.$moment(event.start).format('YYYY.MM.DD HH:mm:ss'))
          params.append('end', this.$moment(event.end).format('YYYY.MM.DD HH:mm:ss'))
          axios.post("http://localhost:9000/resizeCalendar", params)
            .then(res => {
              this.$message({ type: 'success', message:'update completed' });
              this.$store.state.s_group.showGroupCalendar = false
            })
        
        }).catch(() => {
          this.getCalendar()
          this.$message({
            type: 'info',
            message: 'update canceled'
          });          
        });


    }
  },
    mounted(){
      
      this.getCalendar()
        
   }
  
}

</script>

<style scoped>
.calendar{
  width: 80%;
  margin: auto;
  margin-bottom: 50px;
  height: auto;
  
}
.fc-view-container{
height: auto;
}

</style>