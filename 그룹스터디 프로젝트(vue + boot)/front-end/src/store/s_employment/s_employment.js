import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)


export default new Vuex.Store({
    namespaced: true,
    state: {
        RecruitingList: [],
        getOneRecruit : "",
        cvDetail : "",
        showCVMain : false,
        showCVDetail : false,
        showCVWriting : false,
        showCVUpdate : false,
        loadingCVList : false,
        loadingCVDetail : false,
        oneMember : ""

    },
    mutations: {
        
    }, 
    actions: {
    }  
})