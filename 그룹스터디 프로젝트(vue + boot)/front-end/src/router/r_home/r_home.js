import home from '@/views/Home/Home.vue'

import university from '@/views/Home/component/University'
import official from '@/views/Home/component/Official'
import language from '@/views/Home/component/Language'
import life from '@/views/Home/component/Life'

import cv from '@/views/Home/component/Cv'

export default [
    {
        path: '/',
        name: 'Home',
        component: home,
        children:[
            {
                path: '',
                name: 'University',
                components: {
                    one: university,
                    two: cv,
                    three: life
                }
            },
            {
                path: '/official',
                name: 'Official',
                components: {
                    one: official,
                    two: cv,
                    three: life
                }
            },
            {
                path: '/Language',
                name: 'Language',
                components: {
                    one: language,
                    two: cv,
                    three: life
                }
            },
            {
                path: '/Life',
                name: 'Life',
                components: {
                    one: life,
                    two: cv,
                    three: life
                }
            }
        ]
    }
]