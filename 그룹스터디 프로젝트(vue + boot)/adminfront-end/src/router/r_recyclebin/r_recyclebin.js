export default[{
    path:'/recyclebin',
    name:'recyclebin',
    component:() => import('@/views/Recyclebin/recyclebin.vue'),
    children:[
        
        {
            path:'/',
            name:'recyclebin',
            component:() => import('@/views/Recyclebin/content_00.vue'),
        },
        {
            path:'/recyclebin1',
            name:'recyclebin1',
            component:() => import('@/views/Recyclebin/content_01.vue'),
        },
        {
            path:'/recyclebin2',
            name:'recyclebin2',
            component:() => import('@/views/Recyclebin/content_02.vue'),
        },
        {
            path:'/recyclebin3',
            name:'recyclebin3',
            component:() => import('@/views/Recyclebin/content_03.vue'),
        },
        {
            path:'/recyclebin4',
            name:'recyclebin4',
            component:() => import('@/views/Recyclebin/content_04.vue'),
        }
    ]
        

}]