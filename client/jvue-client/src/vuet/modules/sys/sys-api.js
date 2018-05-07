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

        const response = await ApiUtils.get('/api/api', param)
        this.loading = false
        let {status, data = {}} = response
        if (status === 200 && data.error === null) {
          this.searchForm.page = data.pageNum
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
