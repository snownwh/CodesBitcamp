import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    loginUser : "",
    isLogin : true,
    currpage: "/",
    depth1:"",
    depth2:"",
    depth3:"",
    totalDepth: "",
    adminAllStudyGroupList:[],//group/content2.vue
    adminAllStudyGroupListSelector:""//group/content2.vue
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
    }
  },
  mutations: {
    loginSuccess(state, payload){
      state.isLogin = false  //로그인 성공시
      state.loginUser = payload
    },
    logout(state){
      state.isLogin = true
      state.loginUser = null
    }
  },
  actions: {
    getMember({commit} ,loginObj){
      commit('loginSuccess', loginObj)
    }
  },
  modules: {

  }
})