import Vue from 'vue'
import Router from 'vue-router'
import NProgress from 'nprogress'
import Routers from './routers'
import {utils} from '@/common/'
import vuet from '@/vuet/'

Vue.use(Router)

NProgress.configure({showSpinner: false})

let initedMenu = false
const router = new Router({
  routes: Routers,
  mode: 'history',
  base: '/admin/'
})
router.onReady(() => {
  // 处理刷新后，router重加载
  let userSelf = vuet.getModule('user-self')
  if (userSelf && userSelf.user) {
    userSelf.reloadRouters()
  }
})
router.afterEach(router => {
  let {meta} = router
  if (meta.title) {
    utils.setWindowTitle(meta.title + ':::JVUE管理系统')
  } else {
    utils.setWindowTitle('JVUE::管理系统')
  }
  NProgress.done()
})
router.beforeEach((to, from, next) => {
  NProgress.start()
  let {meta, path, name} = to
  let userSelf = vuet.getModule('user-self')
  let { auth = true } = meta
  if (userSelf && userSelf.user) {
    // 已登录
    return next()
  } else {
    if (auth) {
      return next({ path: '/login' })
    } else {
      return next()
    }
  }
})
export default router
