import ApiUtils from '@/api/'
import {utils} from '@/common/'

export default {
  modules: {
    self: {
      data () {
        return {
          user: {
            id: null,
            username: null,
            nickname: null,
            token: null,
            permissions: [],
            menus: [],
            modules: [],
            leftRoutes: [],
            moduleId: null,
            active: ''
          },
          menus: [],
          modules: [],
          routers: [],
          leftRoutes: [],
          moduleId: null,
          active: '',
          openeds: []
        }
      },
      async fetch () {
        // 登录时处理
      },
      async login (param) {
        let response = await ApiUtils.post('/api/login', param)
        let {data} = response
        if (data.error === null) {
          this.user = data.data
          await this.loadRouters()
          return data.data
        } else {
          return Promise.reject(data.message)
        }
      },
      async updateToken () {
        // TODO
      },
      async loadRouters () {
        let response = await ApiUtils.get('/api/auth/menu')
        let {data} = response
        if (data.error === null) {
          let {menus, modules} = data.data
          this.menus = menus
          this.modules = modules
          // this.refreshRoutes()
          let userRoutes = utils.toRoutes(this.menus)
          // 追加404
          userRoutes.push({
            path: '*',
            meta: {auth: false},
            hidden: true,
            redirect: {path: '/error/404'}
          })
          await this.app.$router.addRoutes(userRoutes)
          this.routers = userRoutes
          this.changeModule()
          return data.data
        } else {
          return Promise.reject(data.message)
        }
      },
      async signout () {
        // 删除本地存储
        this.reset()
        await ApiUtils.post('/api/logout')
      },
      async changeModule (moduleId = 0) {
        if (this.moduleId !== moduleId) {
          this.moduleId = moduleId
          this.leftRoutes = []
          this.openeds = []
          // 只需要处理第一层，第二层
          this.routers.forEach(router => {
            if (router.meta && router.meta.moduleId === moduleId && router.meta.showNav === 1) {
              // console.info(`router.meta.showNav = ${router.meta.showNav}`)
              let nrouter = Object.assign(router)
              if (nrouter.children) {
                this.openeds.push(router.path)
                nrouter.children = nrouter.children.filter((r) => r.meta && r.meta.showNav === 1)
              }
              this.leftRoutes.push(nrouter)
            } else {

            }
          })
          //  FIXME 处理模块后，需要切换到对应的页面
        }
      },
      reloadRouters () {
        if (this.menus && this.menus.length > 0) {
          //  判断用户登录状态，如果未登录/登录失败，仅localStorage里有缓存的话，直接跳登录，不再加载动态路由
          // TODO 判断用户登录状态
          let userRoutes = utils.toRoutes(this.menus)
          // 追加404
          userRoutes.push({
            path: '*',
            meta: {auth: false},
            hidden: true,
            redirect: {path: '/error/404'}
          })
          this.app.$router.addRoutes(userRoutes)
        }
      }
    }
  }
}
