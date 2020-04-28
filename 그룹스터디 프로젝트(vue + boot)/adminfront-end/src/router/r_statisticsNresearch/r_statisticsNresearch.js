export default[{
    path:'/statisticsNresearch',
    name:'StatisticsNresearch',
    component:() => import('@/views/statisticsNresearch/statisticsNresearch.vue'),
    children:[
        {
            path:'/',
            name:'statisticsNresearchHome',
            component:() => import('@/views/statisticsNresearch/content_01.vue'),
        },
        {
            path:'/statisticsNresearch1',
            name:'statisticsNresearch1',
            component:() => import('@/views/statisticsNresearch/content_01.vue'),
        },
        {
            path:'/statisticsNresearch2',
            name:'statisticsNresearch2',
            component:() => import('@/views/statisticsNresearch/content_02.vue'),
        },
        {
            path:'/statisticsNresearch3',
            name:'statisticsNresearch3',
            component:() => import('@/views/statisticsNresearch/content_03.vue'),
        },
        {
            path:'/statisticsNresearch4',
            name:'statisticsNresearch4',
            component:() => import('@/views/statisticsNresearch/content_04.vue'),
        },
        
    ]

}]