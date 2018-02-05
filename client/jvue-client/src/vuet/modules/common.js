/*
 * 共通相关modules
 *
 */

import ApiUtils from '@/api/'

export default {
  modules: {
    region: {
      modules: {
        list: {
          data() {
            return {
              data: []
            }
          },
          /* 取省市县地址 */
          async fetch() {
            let response = await ApiUtils.get('00021')
            let {code, message, data} = response.data
            if (code === '0') {
              this.data = data
              return data
            } else {
              return Promise.reject(message)
            }
          }
        },
        tree: {
          data() {
            return {
              data: []
            }
          },
          /* 取省市县地址 */
          async fetch() {
            let response = await ApiUtils.get('00022')
            let {code, message, data} = response.data
            if (code === '0') {
              this.data = data
              return data
            } else {
              return Promise.reject(message)
            }
          }
        }
      }
    },
    dict: {
      modules: {
        enums: {
          data() {
            return {
              data: []
            }
          },
          /* 获取枚举类型数据列表 */
          async fetch() {
            let response = await ApiUtils.get('00013')
            let {code, message, data} = response.data
            if (code === '0') {
              this.data = data
              return data
            } else {
              return Promise.reject(message)
            }
          },
          async getEnums(key) {
            if (this.data[key]) {
              return this.data[key]
            }
            let response = await ApiUtils.get('00013', {codeName: key})
            let {code, message, data} = response.data
            if (code === '0') {
              return data[key]
            } else {
              return Promise.reject(message)
            }
          }
        },
        codes: {
          data() {
            return {
              data: []
            }
          },
          /* 获取字典类型数据列表 */
          async fetch() {
            let response = await ApiUtils.get('00015')
            let {code, message, data} = response.data
            if (code === '0') {
              this.data = data
              return data
            } else {
              return Promise.reject(message)
            }
          }
        }
      }
    },
    storage: {
      modules: {
        photo: {
          data() {
            return {
              loading: false
            }
          },
          async upload(fileName, base64, waterMark = 0) {
            this.loading = true
            let req = {
              name: fileName,
              imageData: base64,
              watermarkSign: waterMark
            }
            const response = await ApiUtils.post(`/hifi-storage-service/api/storage/photo`, req)
            this.loading = false
            let {status, data} = response
            if (status === 200) {
              return data.data
            } else {
            }
            return Promise.reject(new Error())
          }
        },
        html: {
          data() {
            return {
              loading: false,
              content: ''
            }
          },
          async load(url) {
            this.loading = true
            this.content = ''
            let param = {
              fileId: url
            }
            const response = await ApiUtils.get(`/hifi-storage-service/api/storage/richText`, param)
            this.loading = false
            let {status, data} = response
            if (status === 200) {
              this.content = data.data
              return data.data
            } else {
              this.content = ''
            }
            return Promise.reject(new Error())
          }
        }
      }
    }
  }
}
