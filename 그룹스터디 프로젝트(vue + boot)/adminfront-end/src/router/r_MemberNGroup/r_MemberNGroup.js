// import {requireAuth} from '../r_index.js'

export default[{
    path:'/memberNGroup',
    name:'MemberNGroup',
    component:() => import('@/views/MemberNGroup/member.vue'),
    // beforeEnter: requireAuth,
    children:[
        {   //리스트 출력
            path:'/managemember1/',
            name:'managemember1',
            component:() => import('@/views/MemberNGroup/content_01.vue'),
        },
        {   //디테일 페이지로 이동
            path:'/managemember1/:memid',
            name:'managemember1',
            component:() => import('@/views/MemberNGroup/content_01_memdetail.vue'),
        },


        {
            path:'/managemember2',
            name:'managemember2',
            component:() => import('@/views/MemberNGroup/content_02.vue'),
            // beforeEnter: requireAuth,
        },
        {
            path:'/managemember3',
            name:'managemember3',
            component:() => import('@/views/MemberNGroup/content_03.vue'),
            // beforeEnter: requireAuth,
        },
        {
            path:'/managemember4',
            name:'managemember4',
            component:() => import('@/views/MemberNGroup/content_04.vue'),
            // beforeEnter: requireAuth,
        },
        
    ]

}]