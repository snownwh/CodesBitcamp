import Vue from 'vue'
import App from './App.vue'
import router from '@/router/r_index.js'
import store from '@/store/s_index.js'
import 'expose-loader?$!expose-loader?jQuery!jquery' 
import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
//element ui
import ElementUI from 'element-ui';
import locale from 'element-ui/lib/locale/lang/en'

Vue.use(ElementUI, {locale})
Vue.use(BootstrapVue)
Vue.use(BootstrapVueIcons)





Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
