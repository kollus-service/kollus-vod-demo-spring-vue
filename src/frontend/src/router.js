import Vue from 'vue';
import VueRouter from 'vue-router'

import Home from './view/Home'

import Login from './view/Login'
import Admin from './view/Admin';
import Viewer from './view/Viewer';
import Upload from './view/admin/upload';
import Categories from './view/admin/categories';
import Channels from './view/admin/channels';
import Player from './view/admin/player';
import Users from './view/admin/users';
import Logs from './view/admin/logs';
Vue.use(VueRouter);

const NotFound = {template: "<div>NotFound</div>"}
export const router = new VueRouter({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'login',
            component: Login
        },
        {
            path:'/login',
            name:'login',
            component: Login
        },
        {
            path: '/admin',
            name: 'admin',
            component: Admin,
            children:[
                {
                    path:'',
                    component: Upload
                },
                {
                    path:'upload',
                    component: Upload
                },
                {
                    path:'categories',
                    component: Categories
                },
                {
                    path:'channels',
                    component: Channels
                },
                {
                    path:'player',
                    component: Player
                },
                {
                    path:'users',
                    component: Users
                },
                {
                    path:'logs',
                    component: Logs
                },
            ]
        },
        {
            path: '/viewer',
            name: 'viewer',
            component: Player
        },
        {
            path:'*',
            component: NotFound
        }

    ]
});

router.beforeEach((to, from, next) => {
    const publicPages = ['/','/login',
        ];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');
    if (authRequired && !loggedIn){
        next('/login');
    }
    else {
        next();
    }
});
