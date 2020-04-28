export default[{
    path:'/login',
    name:'login',
    component:()=> import('@/components/Login.vue'),

    // path:'/logout',
    // name:'logout',
    // component:()=> import('@/components/Logout.vue'),


    children:[
  /*
        {
            path:'/',
            name:'acceptlist',
            component:() => import('@/views/Group/content_00.vue'),
        },
        {
            path:'/group01',
            name:'acceptlist1',
            component:() => import('@/views/Group/content_01.vue'),
        },
        {
            path:'/group02',
            name:'acceptlist2',
            component:() => import('@/views/Group/content_02.vue'),
        },
        {
            path:'/group03',
            name:'acceptlist3',
            component:() => import('@/views/Group/content_03.vue'),
        },
        {
            path:'/group04',
            name:'acceptlist4',
            component:() => import('@/views/Group/content_04.vue'),
        },

        */
    ]

}]

