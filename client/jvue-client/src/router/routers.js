/**
 * Created by Yuangui on 2017-08-09.
 */

export default [
  {
    path: '/login',
    name: 'login',
    meta: {auth: false, title: '登录'},
    component: resolve => require(['@/pages/login'], resolve)
  },
  {
    path: '/',
    name: 'main',
    meta: {title: '首页', active: 1},
    component: resolve => require(['@/pages/common/main'], resolve),
    children: [
    ]
  },
  {
    path: '/error',
    component: resolve => require(['@/pages/error/'], resolve),
    hidden: true,
    children: [
      {
        path: '404',
        meta: {auth: false, title: '404'},
        name: 'E404',
        component: resolve => require(['@/pages/error/404'], resolve)
      },
      {
        path: '403',
        meta: {auth: false, title: '403'},
        name: 'E403',
        component: resolve => require(['@/pages/error/403'], resolve)
      }
    ]
  }
  // ,
  // {
  //   path: '*',
  //   hidden: true,
  //   redirect: {path: '/error/404'}
  // }
]
