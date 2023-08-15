import vue from 'vue'
import Vuex from 'vuex'
import router,{resetRouter} from "@/router";
import createPersistedState from 'vuex-persistedstate';
vue.use(Vuex)

function addNewRoute(menuList) {
    let routes = router.options.routes
    console.log('routes有')
    console.log(routes)
    console.log('menuList有')
    console.log(menuList)
    routes.forEach(routerItem=>{
        if(routerItem.path == '/index'){
            menuList.forEach(menu=>{
                let childRoute = {
                    path:'/' + menu.menuclick,
                    name:menu.menuname,
                    meta:{
                        title:menu.menuname
                    },
                    component:()=>import('../components/' + menu.menucomponent)
                }

                routerItem.children.push(childRoute)
            })
        }
    })
    resetRouter()
    router.addRoutes(routes)
}

export default new Vuex.Store({
    state: {
        menu:[

        ]
    },
    mutations: {
        setMenu(state, menuList) {
            state.menu = menuList

            addNewRoute(menuList)
        },
        setRouter(state, menuList) {
            addNewRoute(menuList)
        },
    },
    getters: {
        getMenu(state) {
            return state.menu
        }
    },
    plugins: [createPersistedState()]
})