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
    },
    list: {
      data () {
        return {
          searchForm: {
            page: 1,
            pageSize: 10,
            pageCount: 0,
            totalCount: 0
          },
          loading: false,
          list: [],
          userRoles: []
        }
      },
      async fetch () {
        this.loading = true
        const param = {
          page: this.searchForm.page - 1,
          pageSize: this.searchForm.pageSize
        }

        const response = await ApiUtils.get('/api/user', param)
        this.loading = false
        let {status, data = {}} = response
        if (status === 200 && data.error === null) {
          this.searchForm.page = data.pageNum + 1
          this.searchForm.pageSize = data.pageSize
          this.searchForm.pageCount = data.pages
          this.searchForm.totalCount = data.total
          this.list = data.data || []
        }
      },
      async saveRoles(id, roles) {
        const response = await ApiUtils.put(`/api/user/ext/${id}/role`, roles)
        let {error, message, data} = response.data
        if (error === null) {
          this.fetch()
          return data
        } else {
          Promise.reject(message)
        }
      },
      async remove (id) {
        const response = await ApiUtils.delete(`/api/user/${id}`)
        let {error, message, data} = response.data
        if (error === null) {
          return data
        } else {
          Promise.reject(message)
        }
      },
      async toggleEnable(id, enabled) {
        const response = await ApiUtils.patch(`/api/user/${id}/${enabled}`)
        let {error, message, data} = response.data
        if (error === null) {
          this.fetch()
          return data
        } else {
          Promise.reject(message)
        }
      }
    },
    add: {
      data () {
        return {
          form: {}
        }
      },
      async fetch () {
        // 初始化信息
      },
      async save () {
        let param = this.form
        const response = await ApiUtils.post('/api/user', param)
        let {error, message, data} = response.data
        if (error === null) {
          return data
        } else {
          Promise.reject(message)
        }
      }
    },
    detail: {
      data () {
        return {
          detail: {}
        }
      },
      async fetch () {
        let id = this.app.$route.params['id']
        const response = await ApiUtils.get(`/api/user/${id}`)
        let {error, message, data} = response.data
        if (error === null) {
          this.detail = data
          return data
        } else {
          Promise.reject(message)
        }
      }
    },
    edit: {
      data () {
        return {
          form: {}
        }
      },
      async fetch () {
        let id = this.app.$route.params['id']
        const response = await ApiUtils.get(`/api/user/${id}`)
        let {error, message, data} = response.data
        if (error === null) {
          this.form = data
          return data
        } else {
          Promise.reject(message)
        }
      },
      async save () {
        let id = this.app.$route.params['id']
        let param = this.form
        const response = await ApiUtils.put(`/api/user/${id}`, param)
        let {error, message, data} = response.data
        if (error === null) {
          return data
        } else {
          Promise.reject(message)
        }
      }
    }
  }
}
