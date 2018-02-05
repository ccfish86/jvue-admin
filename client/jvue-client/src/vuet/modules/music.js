// import ApiUtils from '@/api/'

import ApiUtils from '@/api/'

export default {
  modules: {
    artist: {
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

            const response = await ApiUtils.get('/hifi-admin-music/api/music/artist', param)
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
        },
        detail: {
          data() {
            return {
              loading: true,
              record: {}
            }
          },
          async fetch() {
            this.loading = true
            let id = this.app.$route.params.id
            let params = {
              id: id
            }
            const response = await ApiUtils.get(`/hifi-admin-music/api/music/artist`, params)
            this.loading = false
            let {status, data} = response
            if (status === 200) {
              this.record = data.data
            }
          }
        },
        edit: {
          data() {
            return {
              loading: true,
              record: {},
              form: {
                name: '',
                albumSize: 0
              }
            }
          },
          async fetch() {
            this.loading = true
            let id = this.app.$route.params.id
            const response = await ApiUtils.get(`/hifi-admin-music/api/music/artist/${id}`)
            this.loading = false
            let {status, data} = response
            if (status === 200) {
              this.record = data.data
              this.reset()
            }
          },
          reset() {
            let storeHtml = this.vuet.getModule('common-storage-html')
            this.form = Object.assign({}, this.record)
            storeHtml.load(this.record.introduction).then((content) => {
              this.form.introduction = content
            })
          },
          async save(record) {
            // this.form
            this.loading = true
            let id = this.app.$route.params.id
            const response = await ApiUtils.put(`/hifi-admin-music/api/music/artist/${id}`, this.form)
            this.loading = false
            let {status, data} = response
            if (status === 200) {
              this.record = data.data
            }
          }
        },
        add: {
          data() {
            return {
              loading: true,
              form: {
                name: '',
                albumSize: 0
              }
            }
          },
          fetch() {
            // do nth
          },
          async save(record) {
            // this.form
            this.loading = true
            const response = await ApiUtils.post(`/hifi-admin-music/api/music/artist`, this.form)
            this.loading = false
            let {status, data, message} = response
            if (status === 200) {
              this.record = data.data
              return data
            }
            return Promise.reject(new Error(message))
          }
        }
      }
    },
    album: {
      modules: {
        // music-album-list
        list: {
          data() {
            return {
              searchForm: {
                name: '',
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
              genre: this.searchForm.genre,
              artistName: this.searchForm.artistName,
              catalog: this.searchForm.catalog,
              page: this.searchForm.page,
              pageSize: this.searchForm.pageSize
            }

            const response = await ApiUtils.get('/hifi-admin-music/api/music/album', param)
            this.loading = false
            let {status, data} = response
            if (status === 200) {
              this.searchForm.page = data.pageNum
              this.searchForm.pageSize = data.pageSize
              this.searchForm.pageCount = data.pages
              this.searchForm.totalCount = data.total
              this.list = data.data || []
            }
          },
          async shield(id, temp) {
            const param = {
              id: id,
              tempStatus: temp
            }
            const response = await ApiUtils.post(`/hifi-admin-music/api/music/album/shield`, param)
            this.loading = false
            let {status, data} = response
            if (status === 200) {
              // this.record = data.data
              return data
            } else {
              return Promise.reject(new Error(''))
            }
          }
        },
        detail: {
          data() {
            return {
              loading: true,
              record: {},
              albumArray: []
            }
          },
          async fetch() {
            this.albumArray = []
            this.loading = true
            let id = this.app.$route.params.id
            let params = {
              id: id
            }
            const response = await ApiUtils.get(`/hifi-admin-music/api/music/album/detail`, params)
            this.loading = false
            let {status, data} = response
            if (status === 200) {
              this.record = data.data
              this.albumArray.push(this.record)
              console.log(this.albumArray)
            }
          }
        },
        edit: {
          data() {
            return {
              loading: true,
              form: {}
            }
          },
          async save(record) {
            // this.form
            if (record.id) {
              return Promise.reject(new Error('接口没做完'))
            } else {
              return Promise.reject(new Error('ID没值'))
            }
          },
          async fetch() {
          }
        },
        upload: {
          data() {
            return {
              loading: false,
              pathes: [],
              selected: 0,
              albumMap: {},
              progresses: {},
              progressesMap: {}
            }
          },
          async fetch() {
            const response = await ApiUtils.get('/hifi-admin-music/api/music/upload/vfs')
            let {status, data} = response
            if (status === 200) {
              this.pathes = data.data
              if (this.pathes.length > this.selected) {
                this.loadAlbums(this.selected)
              }
            }
          },
          async loadAlbums(index, force = false) {
            this.loading = true
            let path = this.pathes[index]
            if (!force) {
              if (!path || this.albumMap[index.toString()]) {
                this.loading = false
                return Promise.resolve()
              }
            }
            const param = {
              path: path
            }
            const response = await ApiUtils.get('/hifi-admin-music/api/music/upload/vfs/files', param)
            this.loading = false
            let {status, data} = response
            if (status === 200) {
              this.albumMap[index.toString()] = data.data
              return Promise.resolve()
            } else {
              return Promise.reject(new Error('专辑列表读取失败！'))
            }
          },
          async show(path, name) {
            const param = {
              path: path,
              name: name
            }
            const response = await ApiUtils.get('/hifi-admin-music/api/music/upload/album', param)
            let {status, data} = response
            if (status === 200) {
              return data
            } else {
              return Promise.reject(new Error('专辑列表读取失败！'))
            }
          },
          async upload(path, name) {
            const param = {
              path: path,
              name: name
            }
            const response = await ApiUtils.post('/hifi-admin-music/api/music/upload/album', param)
            let {status, data} = response
            if (status === 200) {
              this.app.$set(this.progresses, path + name, data.data)
              return data
            } else {
              return Promise.reject(new Error('专辑上传失败！'))
            }
          },
          async refreshProgresses() {
            let jobIds = []
            for (let jobId of Object.values(this.progresses)) {
              if (this.progressesMap[jobId] === 'faild' || this.progressesMap[jobId] === '100') {
                continue
              }
              jobIds.push(jobId)
            }
            if (jobIds.length < 1) {
              return
            }
            let param = {
              jobIds
            }
            const response = await ApiUtils.get('/hifi-admin-music/api/music/upload/progresses', param)
            let {status, data, title} = response
            if (status === 200) {
              if (data.data) {
                for (let [key, value] of Object.entries(data.data)) {
                  // this.progressesMap.set(key, value)
                  // this.progressesMap[key] = value
                  let formated = (value === 'faild' ? '失败' : value.substr(0, 5))
                  this.app.$set(this.progressesMap, key, formated)
                }
              }
              return data
            }
            return Promise.reject(new Error(title))
          }
        }
      }
    },
    comment: {
      modules: {
        list: {
          data() {
            return {
              searchForm: {
                albumId: null,
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
            if (this.searchForm.albumId == null) {
              let albumId = this.app.$route.params.albumId
              if (albumId) {
                this.searchForm.albumId = this.app.$route.params.albumId
              }
            }
            const param = {
              albumId: this.searchForm.albumId,
              userName: this.searchForm.userName,
              albumName: this.searchForm.albumName,
              page: this.searchForm.page,
              pageSize: this.searchForm.pageSize
            }
            const response = await ApiUtils.get('/hifi-admin-music/api/music/album/comments', param)
            this.loading = false
            let {status, data} = response
            if (status === 200) {
              this.searchForm.page = data.pageNum
              this.searchForm.pageSize = data.pageSize
              this.searchForm.pageCount = data.pages
              this.searchForm.totalCount = data.total
              this.list = data.data || []
            }
          },
          async shield(id, isShield) {
            const param = {
              id: id,
              isShield: isShield
            }
            const response = await ApiUtils.post(`/hifi-admin-music/api/music/album/comment/shield`, param)
            let {status, data} = response
            if (status === 200) {
              // this.record = data.data
              console.info(data)
              return data
            } else {
              return Promise.reject(new Error(''))
            }
          }
        },
        detail: {
          data() {
            return {
              loading: true,
              record: {}
            }
          },
          async fetch() {
            this.loading = true
            let id = this.app.$route.params.id
            const response = await ApiUtils.get(`/hifi-admin-music/api/music/album/comments/detail/${id}`)
            this.loading = false
            let {status, data} = response
            if (status === 200) {
              this.record = data.data
            }
          }
        }
      }
    },
    catalog: {
      data() {
        return {
          selectAll: true,
          form: {},
          selected: [],
          catalogs: []
        }
      },
      async fetch() {
        const response = await ApiUtils.get(`/hifi-admin-music/api/music/catalog`)
        let {status, data} = response
        if (status === 200) {
          this.catalogs = data.data
          this.selected = data.data
        }
      },
      async upordown(id, flag) {
        const param = {
          id: id,
          flag: flag
        }
        const response = await ApiUtils.post('/hifi-admin-music/api/music/catalog/upordown', param)
        let {status, data} = response
        if (status === 200) {
          return data
        } else {
          return Promise.reject(new Error(''))
        }
      },
      async edit(catalog) {
        const param = {
          id: catalog.id,
          name: catalog.name,
          code: catalog.code,
          parentCode: catalog.parentcode,
          rank: catalog.rank,
          isHot: catalog.ishot
        }
        const response = await ApiUtils.post('/hifi-admin-music/api/music/catalog/edit', param)
        let {status, data} = response
        if (status === 200) {
          return data
        } else {
          return Promise.reject(new Error(''))
        }
      },
      async shield(id, isShields) {
        const param = {
          id: id,
          deleted: isShields
        }
        const response = await ApiUtils.post(`/hifi-admin-music/api/music/catalog/shields`, param)
        let {status, data} = response
        if (status === 200) {
          // this.record = data.data
          console.info(data)
          return data
        } else {
          return Promise.reject(new Error(''))
        }
      },
      async add(code, names) {
        let param = {
          parentCode: code,
          names: names
        }
        const response = await ApiUtils.post(`/hifi-admin-music/api/music/catalog`, param)
        let {status, data} = response
        if (status === 200) {
          console.info(data.data)
          this.fetch()
        }
      },
      changeSelected() {
        if (this.selectAll) {
          this.selected = this.catalogs
        } else {
          this.selected = []
        }
      }
    }
  }
}
