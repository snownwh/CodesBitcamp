export default[{
    path:'/board',
    name:'Board',
    component:() => import('@/views/Board/board.vue'),
    children:[
        {
            path:'/board1',
            name:'board1',
            component:() => import('@/views/Board/content_01.vue'),
        },
        {
            path:'/board2',
            name:'board2',
            component:() => import('@/views/Board/content_02.vue'),
        },
        {
            path:'/board3',
            name:'board3',
            component:() => import('@/views/Board/content_03.vue'),
        },
        {
            path:'/board4',
            name:'board4',
            component:() => import('@/views/Board/content_04.vue'),
        },
        
    ]

}]