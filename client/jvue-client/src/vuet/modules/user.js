import ApiUtils from '@/api/'
import {utils} from '@/common/'

export default {
  modules: {
    self: {
      data() {
        return {
          user: {
            id: null,
            name: null,
            permissions: [],
            menus: [],
            modules: [],
            leftRoutes: [],
            moduleId: null,
            active: ''
          },
          token: null,
          menus: [],
          modules: [],
          routers: [],
          leftRoutes: [],
          moduleId: null,
          active: ''
        }
      },
      async fetch() {
        // 登录时处理
      },
      async login(param) {
        let response = await ApiUtils.post('/api/login', param)
        let {data} = response
        if (data.error === null) {
          this.token = data.data
          await this.loadRouters()
          return data.data
        } else {
          return Promise.reject(data.message)
        }
      },
      async updateToken() {
        // TODO
      },
      async loadRouters() {
        let response = await ApiUtils.get('/api/auth/menu')
        let {data} = response
        if (data.error === null) {
          let {menus, modules} = data.data
          this.menus = menus
          this.modules = modules
          // this.refreshRoutes()
          let userRouters = utils.toRoutes(this.menus)
          await this.app.$router.addRoutes(userRouters)
          this.routers = userRouters
          this.changeModule()
          return data.data
        } else {
          return Promise.reject(data.message)
        }
      },
      async loadUser() {
        let response = await ApiUtils.get('/api/account')
        let {data} = response
        if (data.error === '') {
          return data.data
        } else {
          return Promise.reject(new Error(data.message))
        }
      },
      async signout() {
        // 删除本地存储
        sessionStorage.removeItem('jvue-admin-self')
        await ApiUtils.get('/api/logout')
        this.reset()
      },
      async changeModule(moduleId = 0) {
        if (this.moduleId !== moduleId) {
          this.moduleId = moduleId
          this.leftRoutes = []
          // 只需要处理第一层
          this.routers.forEach(router => {
            if (router.meta.moduleId === moduleId) {
              this.leftRoutes.push(router)
            }
          })
          //  FIXME 处理模块后，需要切换到对应的页面
        }
      },
      reloadRouters() {
        if (this.menus && this.menus.length > 0) {
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
