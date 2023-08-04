import VueRouter from 'vue-router';

const routes = [
    {
        path:'/',
        name:'login',
        component:()=>import('../components/Login')
    },
    {
        path:'/index',
        name:'login',
        component:()=>import('../components/index')
    }
]

const router = new VueRouter({
    mode:'history',
    routes
})

export default router;