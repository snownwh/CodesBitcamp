import Vue from 'vue'
import VueRouter from 'vue-router'

import r_Board from '@/router/r_Board/r_Board.js'
import r_Accept from '@/router/r_Accept/r_Accept.js'
import r_Group from '@/router/r_Group/r_Group.js'
import r_MemberNGroup from '@/router/r_MemberNGroup/r_MemberNGroup.js'
import r_recyclebin from '@/router/r_recyclebin/r_recyclebin.js'
import r_statisticsNresearch from '@/router/r_statisticsNresearch/r_statisticsNresearch.js'
import r_DashBoard from '@/router/r_DashBoard/r_DashBoard.js'
import r_Login from '@/router/r_Login/r_Login.js'
import r_Logout from '@/router/r_Login/r_Logout.js'
import r_Home from './r_Home/r_Home'


Vue.use(VueRouter)



const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes:[
    ...r_Accept,
    ...r_Board,
    ...r_Group,
    ...r_MemberNGroup,
    ...r_recyclebin,
    ...r_statisticsNresearch,
    ...r_DashBoard,
    ...r_Login,
    ...r_Logout,
    ...r_Home,
    { path: '*', component:() => import('@/components/NotFoundComponent.vue') }


  ]
})

export default router
