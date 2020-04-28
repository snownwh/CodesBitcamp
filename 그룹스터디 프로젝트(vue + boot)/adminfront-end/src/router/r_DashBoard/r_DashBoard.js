
//  import requireAuth from '@/router/r_index.js'

//  const changeloc = requireAuth.requireAuth()


export default[{
    // path:'/',
    // name:'dashboard',
    // component:()=>('@/view/home/dashboard.vue')
    path: '/',
    name: 'dashboard',
    component:()=> import('@/views/home/dashboard.vue'),
    // beforeEnter: changeloc,
    
    children:[
        {
            path:'/',
            name:'dashboardContents',
            component:() => import('@/views/home/dashboardContents.vue'),
            // beforeEnter: changeloc
            
        }
    ]

}]