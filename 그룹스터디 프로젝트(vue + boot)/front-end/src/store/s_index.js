import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate';

import s_about from '@/store/s_about/s_aboutus.js'
import s_private from '@/store/s_private/s_private.js'
import s_group from '@/store/s_group/s_group.js'
import s_employment from '@/store/s_employment/s_employment.js'
import s_notice from '@/store/s_notice/s_notice.js'
import s_search from '@/store/s_subStore.js'
import s_member from '@/store/s_member/s_member.js'
import s_subStore from '@/store/s_subStore.js'
import router from '@/router/r_index.js'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    loginUser : null,
    isLogin : true,
    auth: null,
    show: true,
    currpage: "/",
    depth1:"",
    depth2:"",
    depth3:"",
    totalDepth: ""
  },
  getters:{
    pageName(state){
      state.depth1 = state.currpage.split("/")[1]
      state.depth2 = state.currpage.split("/")[2]
      state.depth3 = state.currpage.split("/")[3]
     
      if (state.depth1 != undefined){
        state.totalDepth = state.depth1
      }
      if (state.depth2 != undefined){
        state.totalDepth = state.depth1 + " > " + state.depth2
      }
      if (state.depth3 != undefined){
        state.totalDepth = state.depth1 + " > " + state.depth2 + " > " + state.depth3
      }
      return state.totalDepth
    },
    // showControll: state =>{
    //   state.show = false
    //   return state.show
    // }
    
  },
  mutations: {
    loginSuccess(state, payload){
      state.isLogin = false  //로그인 성공시
      state.loginUser = payload
      state.auth = payload.auth
    },
    logout(state){
      state.isLogin = true
      state.loginUser = null
      
      sessionStorage.removeItem("loginUser")
      router.push({path:'/'})
    }
  },
  actions: {
    getMember({commit} ,loginObj){
      commit('loginSuccess', loginObj)
    }
  },
  modules: {
    s_about,
    s_private,
    s_group,
    s_employment,
    s_notice,
    s_search,
    s_subStore,
    s_member,
  },
  plugins: [createPersistedState()]
})
