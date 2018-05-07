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

        const response = await ApiUtils.get('/api/role', param)
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
        const response = await ApiUtils.delete(`/api/role/${id}`)
        let {error, message, data} = response.data
        if (error === null) {
          return data
        } else {
          Promise.reject(message)
        }
      },
      async toggleEnable (id, enabled) {
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
      data () {
        return {
          roleInfo: {},
          allRoleInfo: {},
          currentModule: `1`,
          currentPages: [],
          form: {
            pages: []
          }
        }
      },
      async fetch () {
        let id = this.app.$route.params['id']
        Promise.all([ApiUtils.get(`/api/role/ext/${id}/grant`), ApiUtils.get(`/api/page/ext/pages`)]).then(res => {
          let [granted, allPage] = res
          let {error, message, data} = granted.data
          let {error: error2, message: message2, data: data2} = allPage.data
          if (error === null && error2 === null) {
            // fill data's roles to data2
            for (let page of data.pages) {
              if (page.children) {
                for (let spage of page.children) {
                  for (let page2 of data2.pages) {
                    if (page2.id !== page.id) {
                      continue
                    }
                    if (page2.children) {
                      for (let spage2 of page2.children) {
                        if (spage.id === spage2.id) {
                          spage2.checked = true
                          break
                        }
                      }
                    }
                  }
                }
              }
            }
            for (let [key, value] of Object.entries(data.apis)) {
              if (value) {
                let apis = data2.apis[key]
                console.info(apis)
                console.info(value)
                for (let api of apis) {
                  if (value.includes(api.code)) {
                    api.checked = true
                  }
                }
              }
            }
            for (let [key, value] of Object.entries(data.segments)) {
              if (value) {
                let segments = data2.segments[key]
                for (let segment of segments) {
                  if (value.includes(segment.code)) {
                    console.info('checked')
                    segment.checked = true
                  }
                }
              }
            }
            this.roleInfo = data
            this.allRoleInfo = data2
            this.changeModule(1)
            Promise.resolve(data)
          } else {
            Promise.reject(new Error(`${message} ${message2}`))
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
      async save () {
        // 保存
        let pageRoles = []
        for (let page of this.currentPages) {
          console.info(`save then page `)
          if (page.children) {
            for (let spage of page.children) {
              let hasRole = false
              let apiIds = []
              let segmentIds = []
              if (spage.checked) {
                hasRole = true
              }
              let apis = this.allRoleInfo.apis[spage.id]
              if (apis) {
                // console.info(apis)
                for (let api of apis) {
                  if (api.checked) {
                    apiIds.push(api.id)
                    hasRole = true
                  }
                }
              }

              let segments = this.allRoleInfo.segments[spage.id]
              if (segments) {
                for (let segment of segments) {
                  if (segment.checked) {
                    segmentIds.push(segment.id)
                    hasRole = true
                  }
                }
              }

              if (hasRole) {
                pageRoles.push({pageId: spage.id, apiIds, segmentIds})
              }
            }
          }
        }

        // 读取选中的数据
        let param = {
          moduleId: this.currentModule,
          pageRoles
        }
        let id = this.app.$route.params['id']
        const response = await ApiUtils.put(`/api/role/ext/${id}/grant`, param)
        let {error, message, data} = response.data
        if (error === null) {
          return data
        } else {
          Promise.reject(message)
        }
      },
      async changeModule (moduleId) {
        // allRoleInfo里面取
        this.currentModule = moduleId
        if (this.allRoleInfo) {
          this.currentPages = this.allRoleInfo.pages.filter(m => m.moduleId === moduleId)
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
        const response = await ApiUtils.get('/api/role/ext/names')
        this.loading = false
        let {status, data = {}} = response
        if (status === 200 && data.error === null) {
          this.list = data.data || []
        }
      }
    }
  }
}
