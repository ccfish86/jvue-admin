import ApiUtils from '@/api/'

export default {
  modules: {
    user: {
      modules: {
        list: {
          data() {
            return {
              searchForm: {
                name: null,
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
              name: this.searchForm.name,
              page: this.searchForm.page,
              pageSize: this.searchForm.pageSize
            }
            let response = await ApiUtils.get('/hifi-admin-user/api/account/users', param)
            this.loading = false
            let {status, data} = response
            if (status === 200) {
              this.searchForm.page = data.pageNum
              this.searchForm.pageSize = data.pageSize
              this.searchForm.pageCount = data.pages
              this.searchForm.totalCount = data.total
              this.list = data.data || []
            }
          }
        }
      }
    },
    role: {
      modules: {
        list: {
          data() {
            return {
              loading: false,
              list: []
            }
          },
          async fetch() {
            this.loading = true

            let response = await ApiUtils.get('/hifi-admin-user/api/account/roles')
            this.loading = false
            let {status, data} = response
            if (status === 200) {
              this.list = data.data || []
            }
          }
        }
      }
    }
  }
}
