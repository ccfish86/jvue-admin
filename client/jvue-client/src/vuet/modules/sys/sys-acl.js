import ApiUtils from '@/api/'

export default {
  modules: {
    list: {
      data () {
        return {
          list: []
        }
      },
      async fetch () {
        const response = await ApiUtils.get('/api/acl')
        let {error, message, data = []} = response.data
        if (error === null) {
          this.list = data
        } else {
          Promise.reject(new Error(message))
        }
      }
    }
  }
}
