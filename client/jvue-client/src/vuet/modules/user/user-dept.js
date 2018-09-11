import ApiUtils from '@/api/'
import {utils} from '@/common'

export default {
  modules: {
    list: {
      data () {
        return {
          searchForm: {
            page: 1,
            pageSize: 20,
            pageCount: 0,
            totalCount: 0,
            sort: 'code',
            direction: 1
          },
          loading: false,
          list: [],
          types: [],
          parentCodes: []
        }
      },
      async fetch () {

      },
      async search () {
        this.loading = true
        let {page, pageSize, sort, direction} = this.searchForm
        let param = {page, pageSize, sort, direction}
        const response = await ApiUtils.get('/api/dept', param)
        this.loading = false
        let {status, data = {}} = response
        if (status === 200 && data.error === null) {
          this.searchForm.page = data.pageNum
          this.searchForm.pageSize = data.pageSize
          this.searchForm.pageCount = data.pages
          this.searchForm.totalCount = data.total
          this.list = utils.treeToList(data.data, 'name') || []
        }
      },
      async getParentCodes (type) {
        let param = {type: type || this.searchForm.type}
        const response = await ApiUtils.get('/api/dept/ext/parentCodes', param)
        let {status, data = {}} = response
        if (status === 200 && data.error === null) {
          this.parentCodes = data.data || []
        }
      },
      async changeType () {
        this.search()
      },
      async remove (id) {
        const response = await ApiUtils.delete(`/api/dept/${id}`)
        let {error, message, data} = response.data
        if (error === null) {
          return data
        } else {
          Promise.reject(message)
        }
      },
      async add (item) {
        let {type, code, name, parentCode, position} = item
        // 补位
        code = code.padStart(3, '0')
        if (parentCode && parentCode !== null && parentCode.length > 0) {
          code = parentCode + code
        }
        let param = {type, code, name, parentCode, position}
        const response = await ApiUtils.post(`/api/dept`, param)
        let {error, message, data} = response.data
        if (error === null) {
          return data
        } else {
          Promise.reject(message)
        }
      },
      async save (id, item) {
        let {type, code, name, parentCode, position} = item
        // 补位
        code = code.padStart(3, '0')
        if (parentCode && parentCode !== null && parentCode.length > 0) {
          code = parentCode + code
        }
        let param = {type, code, name, parentCode, position}
        const response = await ApiUtils.put(`/api/dept/${id}`, param)
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
          depts: [],
          dict: {}
        }
      },
      async fetch () {
        // 初始化信息
        const response = await ApiUtils.get(`/api/dept/ext/tree`)
        let {error, message, data} = response.data
        if (error === null) {
          this.depts = data
          this.dict = utils.treeToObject(data)
          return data
        } else {
          Promise.reject(message)
        }
      }
    }
  }
}
