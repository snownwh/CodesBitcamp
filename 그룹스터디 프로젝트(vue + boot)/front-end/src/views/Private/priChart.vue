<template>
    <div>
  <highcharts :options="chartOptions"></highcharts>
    </div>
</template>

<script>
import Vue from 'vue'
import HighchartsVue from 'highcharts-vue'
import {Chart} from 'highcharts-vue'
Vue.use(HighchartsVue)


export default {
    components: {
        highcharts: Chart 
    },
    data(){
        return{
            chartOptions: {
            chart: {
                type: 'column'
            },
            title: {
                text: '주간 목표 달성'
            },
            series: [{
                data: [3, 2, 1, 5, 0, 0, 0],
                color: '#6fcd98',
                name: '달성 수',
               
            }],
            xAxis: {
                categories: [],
            },
            yAxis: {
                min: 0,
                title: {
                    text: ''
                }
            },
            } 
        }
    }, 
    mounted(){
        var loginData = sessionStorage.getItem("loginUser");
         var login = JSON.parse(loginData); 
         var memSeq = login.memberSeq
         var params = new URLSearchParams();
         params.append('memberSeq', memSeq)
         axios.post('http://localhost:9000/selectDate', params)
          .then(res => {
              
             let count = 0
             var list = res.data
             console.log(list)
            for(const key in list){
                const element = list[key]
                this.chartOptions.xAxis.categories.push(element.todoDate)
                // if(element.del == '1'){
                //     count ++
                //     this.chartOptions.series[0].data[0] = count
                // }
            }
              //this.this.chartOptions.series[0].data = res.data.

         }) 
        //axios
        // alert(this.chartOptions.series[0].data)
        // alert(this.chartOptions.xAxis.categories[0])
    }


}
</script>

<style>

</style>