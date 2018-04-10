import ApiUtils from '@/api/'
// import {utils} from '@/common/'
export default {
  modules: {
    module: {
      modules: {
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
              list: []
            }
          },
          async fetch () {
            this.loading = true
            const param = {
              page: this.searchForm.page - 1,
              pageSize: this.searchForm.pageSize
            }

            const response = await ApiUtils.get('/api/module', param)
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
          async remove (id) {
            const response = await ApiUtils.delete(`/api/module/${id}`)
            let {error, message, data} = response.data
            if (error === null) {
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
            const response = await ApiUtils.post('/api/module', param)
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
            const response = await ApiUtils.get(`/api/module/${id}`)
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
            const response = await ApiUtils.get(`/api/module/${id}`)
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
            const response = await ApiUtils.put(`/api/module/${id}`, param)
            let {error, message, data} = response.data
            if (error === null) {
              return data
            } else {
              Promise.reject(message)
            }
          }
        },
        names: {
          data () {
            return {
              list: []
            }
          },
          async fetch () {
            const response = await ApiUtils.get('/api/module/ext/names')
            let {status, data = {}} = response
            if (status === 200 && data.error === null) {
              this.list = data.data || []
            }
          }
        }
      }
    },
    menu: {
      modules: {
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
              list: []
            }
          },
          async fetch () {
            this.loading = true
            const param = {
              page: this.searchForm.page - 1,
              pageSize: this.searchForm.pageSize
            }

            const response = await ApiUtils.get('/api/menu', param)
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
          async remove (id) {
            const response = await ApiUtils.delete(`/api/menu/${id}`)
            let {error, message, data} = response.data
            if (error === null) {
              return data
            } else {
              Promise.reject(message)
            }
          }
        },
        add: {
          data () {
            return {
              form: {
                type: 1,
                showNav: 0
              }
            }
          },
          async fetch () {
            // 初始化信息
          },
          async save () {
            let param = this.form
            const response = await ApiUtils.post('/api/menu', param)
            let {error, message, data} = response.data
            if (error === null) {
              return data
            } else {
              Promise.reject(message)
            }
          }
        },
        edit: {
          data () {
            return {
              form: {},
              apis: [],
              segments: []
            }
          },
          async fetch () {
            let id = this.app.$route.params['id']
            const response = await ApiUtils.get(`/api/menu/${id}`)
            let {error, message, data} = response.data
            if (error === null) {
              this.form = data
              if (data.moduleId) {
                let menuNames = this.vuet.getModule('sys-menu-names')
                menuNames.getMenus(data.moduleId)
              }
              return data
            } else {
              Promise.reject(message)
            }
          },
          async loadApis() {
            let id = this.app.$route.params['id']
            const response = await ApiUtils.get(`/api/menu/ext/${id}/api`)
            let {error, message, data} = response.data
            if (error === null) {
              this.apis = data.map(d => d.apiId)
              return data
            } else {
              Promise.reject(message)
            }
          },
          async loadSegments() {
            let id = this.app.$route.params['id']
            const response = await ApiUtils.get(`/api/menu/ext/${id}/segment`)
            let {error, message, data} = response.data
            if (error === null) {
              this.segments = data
              return data
            } else {
              Promise.reject(message)
            }
          },
          async saveApis() {
            let id = this.app.$route.params['id']
            let params = {apis: this.apis}
            const response = await ApiUtils.put(`/api/menu/ext/${id}/api`, params)
            let {error, message, data} = response.data
            if (error === null) {
              this.apis = data.map(d => d.apiId)
              return data
            } else {
              Promise.reject(message)
            }
          },
          async save () {
            let id = this.app.$route.params['id']
            let param = this.form
            const response = await ApiUtils.put(`/api/menu/${id}`, param)
            let {error, message, data} = response.data
            if (error === null) {
              return data
            } else {
              Promise.reject(message)
            }
          },
          async addSegment(segmentId, name) {
            let id = this.app.$route.params['id']
            let param = {
              menuId: id,
              segmentId,
              name
            }
            const response = await ApiUtils.post(`/api/segment`, param)
            let {error, message, data} = response.data
            if (error === null) {
              this.segments.push(data)
              return data
            } else {
              Promise.reject(message)
            }
          },
          async removeSegment(id, index) {
            const response = await ApiUtils.delete(`/api/segment/${id}`)
            let {error, message, data} = response.data
            if (error === null) {
              this.segments.splice(index, 1)
              return data
            } else {
              Promise.reject(message)
            }
          }
        },
        names: {
          data () {
            return {
              list: []
            }
          },
          async fetch() {
            const response = await ApiUtils.get('/api/menu/ext/names')
            let {status, data = {}} = response
            if (status === 200 && data.error === null) {
              this.list = data.data || []
            }
          },
          async getMenus (moduleId) {
            const response = await ApiUtils.get('/api/menu/ext/names', {moduleId})
            let {status, data = {}} = response
            if (status === 200 && data.error === null) {
              this.list = data.data || []
            }
          }
        }
      }
    },
    api: {
      modules: {
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
              list: []
            }
          },
          async fetch () {
            this.loading = true
            const param = {
              page: this.searchForm.page - 1,
              pageSize: this.searchForm.pageSize
            }

            const response = await ApiUtils.get('/api/api', param)
            this.loading = false
            let {status, data = {}} = response
            if (status === 200 && data.error === null) {
              this.searchForm.page = data.pageNum + 1
              this.searchForm.pageSize = data.pageSize
              this.searchForm.pageCount = data.pages
              this.searchForm.totalCount = data.total
              this.list = data.data || []
            }
          }
        },
        add: {},
        edit: {}
      }
    },
    segment: {
      modules: {
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
              list: []
            }
          },
          async fetch () {
            this.loading = true
            const param = {
              page: this.searchForm.page - 1,
              pageSize: this.searchForm.pageSize
            }

            const response = await ApiUtils.get('/api/segment', param)
            this.loading = false
            let {status, data = {}} = response
            if (status === 200 && data.error === null) {
              this.searchForm.page = data.pageNum + 1
              this.searchForm.pageSize = data.pageSize
              this.searchForm.pageCount = data.pages
              this.searchForm.totalCount = data.total
              this.list = data.data || []
            }
          }
        },
        add: {},
        edit: {}
      }
    },
    acl: {
      modules: {
        list: {
          data () {
            return {
              list: []
            }
          },
          async fetch() {
            const response = await ApiUtils.get('/api/acl')
            let {error, message, data = []} = response.data
            if (error === null) {
              this.list = data
            }
          }
        }
      }
    },
    role: {
      modules: {
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
              list: []
            }
          },
          async fetch () {
            this.loading = true
            const param = {
              page: this.searchForm.page - 1,
              pageSize: this.searchForm.pageSize
            }

            const response = await ApiUtils.get('/api/role', param)
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
          async remove (id) {
            const response = await ApiUtils.delete(`/api/role/${id}`)
            let {error, message, data} = response.data
            if (error === null) {
              return data
            } else {
              Promise.reject(message)
            }
          },
          async toggleEnable(id, enabled) {
            const response = await ApiUtils.patch(`/api/role/${id}/${enabled}`)
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
            const response = await ApiUtils.post('/api/role', param)
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
            const response = await ApiUtils.get(`/api/role/${id}`)
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
            const response = await ApiUtils.get(`/api/role/${id}`)
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
            const response = await ApiUtils.put(`/api/role/${id}`, param)
            let {error, message, data} = response.data
            if (error === null) {
              return data
            } else {
              Promise.reject(message)
            }
          }
        },
        grant: {
          data() {
            return {
              roleInfo: {},
              allRoleInfo: {},
              currentModule: `1`,
              currentPages: [],
              form: {
                menus: []
              }
            }
          },
          async fetch() {
            let id = this.app.$route.params['id']
            Promise.all([ApiUtils.get(`/api/role/ext/${id}/grant`), ApiUtils.get(`/api/menu/ext/menus`)]).then(res => {
              let [granted, allMenu] = res
              let {error, message, data} =  granted.data
              let {error: error2, message: message2, data: data2} =  allMenu.data
              if (error === null && error2 === null) {
                this.roleInfo = data
                this.allRoleInfo = data2
                this.changeModule(1)
                Promise.resolve(data)
              } else {
                Promise.reject(`${message} ${message2}`)
              }
            }).catch(err => {
              Promise.reject(err)
            })
            // const response = await ApiUtils.get(`/api/role/ext/${id}/grant`)
            // let {error, message, data} = response.data
            // if (error === null) {
            //   this.roleInfo = data
            //   return data
            // } else {
            //   Promise.reject(message)
            // }
          },
          async save() {
            // 保存
          },
          async changeModule(moduleId) {
            // allRoleInfo里面取
            this.currentModule = moduleId
            if (this.allRoleInfo) {
              this.currentPages = this.allRoleInfo.menus.filter(m => m.moduleId === moduleId)
            }
            return
          }
        }
      }
    }
  }
}
