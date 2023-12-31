import VueRouter from 'vue-router';

const routes = [
    {
        path:'/',
        name:'login',
        component:()=>import('../components/Login')
    },
    {
        path:'/index',
        name:'index',
        component:()=>import('../components/index'),
        children:[
            {
                path:'/Home',
                name:'home',
                meta:{
                    title:'首页'
                },
                component:()=>import('../components/Home')
            },
            {
                path:'/Admin',
                name:'Admin',
                meta:{
                    title:'管理员管理'
                },
                component:()=>import('../components/admin/AdminManage.vue')
            },
            {
                path:'/User',
                name:'User',
                meta:{
                    title:'管理员管理'
                },
                component:()=>import('../components/user/UserManage.vue')
            },

        ]
    }
]

export function resetRouter(){
    router.matcher = new VueRouter({
        mode:'history',
        routes:[]
    }).matcher
}

const router = new VueRouter({
    mode:'history',
    routes
})

const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(to) {
    return VueRouterPush.call(this, to).catch(err => err)
}
//把路由暴露出来给后续的功能使用
//后续的功能指的就是vuex实现动态路由
export default router;