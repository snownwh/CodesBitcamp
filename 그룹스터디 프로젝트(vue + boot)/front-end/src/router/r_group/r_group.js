import store from "@/store/s_index.js"

const group = (to, from , next) => {
    if(store.state.isLogin === true){ //아직 로그인이 안된 유저니까 막음
        //alert("로그인이 안되어있음")
        next('/group/main')
    }else{
        next()
    }
  }

export default [

    {
    path: '/group',
    name: 'Group',
    beforeEnter: group,
    component: () => import('@/views/Group/login_menu/l_main'),
    children:[

        ]
    },
    {
    name: 'l_main',
    path: '/group/menu/:groupSeq',
    component: () => import('@/views/Group/login_menu/menu'),
    children:[

    ]
    },
    {
    path: '/group/main',
    name: 'Non_login_group',
    component: () => import('@/views/Group/Non_login_menu/n_main'),
    children: [
        ]
    },
    {
    name: 'groupdetail',
    path: '/group/main/detail/:contentId',
    component: () => import('@/views/Group/detail/group_detail')
    },
    {
    path: '/group/create',
    name: 'Create',
    component: () => import('@/views/Group/detail/group_create'),
    }
]
