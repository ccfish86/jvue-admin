import ApiUtils from '@/api/'

export default {
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
          page: this.searchForm.page,
          pageSize: this.searchForm.pageSize
        }

        const response = await ApiUtils.get('/api/page', param)
        this.loading = false
        let {status, data = {}} = response
        if (status === 200 && data.error === null) {
          this.searchForm.page = data.pageNum
          this.searchForm.pageSize = data.pageSize
          this.searchForm.pageCount = data.pages
          this.searchForm.totalCount = data.total
          this.list = data.data || []
        }
      },
      async remove (id) {
        const response = await ApiUtils.delete(`/api/page/${id}`)
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
        const response = await ApiUtils.post('/api/page', param)
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
        const response = await ApiUtils.get(`/api/page/${id}`)
        let {error, message, data} = response.data
        if (error === null) {
          this.form = data
          if (data.moduleId) {
            let pageNames = this.vuet.getModule('sys-page-names')
            pageNames.getPages(data.moduleId)
          }
          return data
        } else {
          Promise.reject(message)
        }
      },
      async loadApis () {
        let id = this.app.$route.params['id']
        const response = await ApiUtils.get(`/api/page/ext/${id}/api`)
        let {error, message, data} = response.data
        if (error === null) {
          this.apis = data.map(d => d.apiCode)
          return data
        } else {
          Promise.reject(message)
        }
      },
      async loadSegments () {
        let id = this.app.$route.params['id']
        const response = await ApiUtils.get(`/api/page/ext/${id}/segment`)
        let {error, message, data} = response.data
        if (error === null) {
          this.segments = data
          return data
        } else {
          Promise.reject(message)
        }
      },
      async saveApis () {
        let id = this.app.$route.params['id']
        let params = {apis: this.apis}
        const response = await ApiUtils.put(`/api/page/ext/${id}/api`, params)
        let {error, message, data} = response.data
        if (error === null) {
          this.apis = data.map(d => d.apiCode)
          return data
        } else {
          Promise.reject(message)
        }
      },
      async save () {
        let id = this.app.$route.params['id']
        let param = this.form
        const response = await ApiUtils.put(`/api/page/${id}`, param)
        let {error, message, data} = response.data
        if (error === null) {
          return data
        } else {
          Promise.reject(message)
        }
      },
      async addSegment (segmentCode, name) {
        let id = this.app.$route.params['id']
        let param = {
          pageId: id,
          segmentCode,
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
      async removeSegment (id, index) {
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
      async fetch () {
        const response = await ApiUtils.get('/api/page/ext/names')
        let {status, data = {}} = response
        if (status === 200 && data.error === null) {
          this.list = data.data || []
        }
      },
      async getPages (moduleId) {
        const response = await ApiUtils.get('/api/page/ext/names', {moduleId})
        let {status, data = {}} = response
        if (status === 200 && data.error === null) {
          this.list = data.data || []
        }
      }
    }
  }
}
