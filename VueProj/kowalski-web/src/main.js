import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/global.css';
import axios from "axios";
import VueRouter from 'vue-router';
import router from './router';
import store from './store';

Vue.prototype.$HttpUrl='http://localhost:8081'
Vue.prototype.$axios=axios;
Vue.config.productionTip = false
Vue.use(VueRouter);
Vue.use(ElementUI);

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
