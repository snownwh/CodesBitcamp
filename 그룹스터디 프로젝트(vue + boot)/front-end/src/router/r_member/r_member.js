export default [
    {
        path: '/login',
        name: 'login',
        component: () => import('@/views/Member/Login.vue')
    },
    {
        path:'/join',
        name: 'join',
        component: () => import('@/views/Member/Join.vue')
    },
    {
        path:'/memberJoin',
        name: 'memberJoin',
        component: () => import('@/views/Member/Member_Join.vue')
    },
    {
        path:'/memberInter',
        name: 'memberInter',
        component: () => import('@/views/Member/memberInterest.vue')
    },
    {
        path:'/companyJoin',
        name:'companyJoin',
        component: () => import('@/views/Member/Company_Join.vue')
    },
    {
        path: '/mypage', 
        name: 'mypage',
        component: () => import('@/views/Member/MyPage.vue'),
        children : [	//array[]를 만들어서 children안에 주소객체들을 만들음
            { 
                path : 'myInfo',  
                name : 'MyInfo',  
                component: () => import('@/views/MyPage/myInfo.vue')
            },
            {
                path: 'myInter',
                name: 'MyInter',
                component: () =>  import('@/views/MyPage/myInter.vue')
            }, 
            {
                path: 'mySchedule',
                name: 'MySchedule',
                component: () => import('@/views/MyPage/mySchedule.vue')
            },
            {
                path: 'myResume',
                name: 'MyResume',
                component: () => import('@/views/MyPage/myResume.vue')
            },
            {
                path: 'myStudy',
                name: 'MyStudy',
                component: () => import('@/views/MyPage/myStudy.vue')
            },

      ]
    }

]

