import ApiUtils from '@/api/'
// import {utils} from '@/common/'
export default {
  modules: {
    module: {
      modules: {
        list: {
          data() {
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
          async fetch() {
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
          async del(id) {
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
          data() {
            return {
              form: {}
            }
          },
          async fetch() {
            // 初始化信息
          },
          async save() {
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
          data() {
            return {
              detail: {}
            }
          },
          async fetch() {
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
          data() {
            return {
              form: {}
            }
          },
          async fetch() {
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
          async save() {
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
          data() {
            return {
              list: []
            }
          },
          async fetch() {
            const response = await ApiUtils.get('/api/module/names')
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
          data() {
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
          async fetch() {
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
          }
        },
        add: {
          data() {
            return {
              form: {
                type: 1,
                showNav: 0
              }
            }
          },
          async fetch() {
            // 初始化信息
          },
          async save() {
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
          data() {
            return {
              form: {}
            }
          },
          async fetch() {
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
          async save() {
            let id = this.app.$route.params['id']
            let param = this.form
            const response = await ApiUtils.put(`/api/menu/${id}`, param)
            let {error, message, data} = response.data
            if (error === null) {
              return data
            } else {
              Promise.reject(message)
            }
          }
        },
        names: {
          data() {
            return {
              list: {}
            }
          },
          async getMenus(moduleId) {
            const response = await ApiUtils.get('/api/menu/names', {moduleId})
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
          data() {
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
          async fetch() {
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
    }
  }
}
