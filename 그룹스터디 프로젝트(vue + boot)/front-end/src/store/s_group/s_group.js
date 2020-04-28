export default {
    namespaced: true,
    state: {
        groupSeq: 0,
        groupList: [],
        detailSeq: 0,
        groupCalendar:[],
        groupCalendarDetail:[],
        groupCalendarStartDate:'',
        groupBoardList: [],
        groupBoardDetail: [],
        groupBoardDetailComments: [],
        groupReferenceList: [],
        groupReferenceDetail: [],
        grouDetail: [],
        showGroupInfo: false,
        showGroupCalendar: false,
        showBoardList: false,
        showBoardDetail: false,
        showDeleteBtn: false,
        showUpdateBtn: false,
        showReferenceDetail: false,
        showGroupReferenceList: false,
        total: 0,
        listQuery:{
          page: 1,
          limit: 5,
        },
        searchWord:'',
        s_keyWord:'',
        check1:false,
        check2:false,

    },
    getters: {
        getDetailSeq(){
            this.detailSeq
        }
    },
    mutations: {
        
    },
    actions: {
    }
}