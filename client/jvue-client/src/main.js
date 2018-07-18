// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import vuet from './vuet/'
import ElementUI from 'element-ui'
import router from './router'
import components from './components/'
import 'element-ui/lib/theme-chalk/index.css'
import 'nprogress/nprogress.css'
import './common/'
import './filters/'
import './assets/css/'
import './api/'

Vue.config.productionTip = false
Vue.use(ElementUI, {size: 'small', zIndex: 20000}) // 防止被ueditor挡住

Object.keys(components).forEach((key) => {
  // 首字母大写
  let name = key.replace(/(\w)/, (v) => v.toUpperCase())
  Vue.component(`v${name}`, components[key])
})

/* eslint-disable no-new */
global.app = new Vue({
  el: '#app',
  vuet,
  router,
  template: '<App/>',
  components: { App }
})
