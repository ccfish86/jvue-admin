import Axios from 'axios'
import NProgress from 'nprogress'
import {Message, MessageBox, Notification} from 'element-ui'
import vuet from '@/vuet/'

// 为不同的接口服务器配置不同的axios实例
// 指定header等参数时，在跨域等场合时，需要服务器端支持，这儿配置的为同域不跨域的主要接口
const axios = Axios.create({
  withCredentials: true,
  timeout: 10000,
  headers: {
    'Accept': 'application/json;charset=utf-8'
  }
})

axios.interceptors.request.use(async config => {
  let userSelf = vuet.getModule('user-self')
  if (userSelf && userSelf.token) {
    await userSelf.updateToken(40)
    return config
  } else {
    return config
  }
})

axios.interceptors.response.use((response) => {
  let {status, data} = response
  if (status === 200 || status === 201) {
    // if (data.code === '0') {
    //   return response
    // } else if (data.code === '401') {
    //   // global.app.$router.push('/login')
    // } else {
    //   Notification({
    //     title: '错误',
    //     message: data.message,
    //     type: 'warning'
    //   })
    // }
    return response
  } else if (status === 302) {
    return response
  } else {
    Notification({
      title: '错误',
      message: data.message,
      type: 'warning'
    })
    return Promise.reject(response)
  }
}, (error) => {
  if (error.response) {
    let {status} = error.response
    if (status === 401) {
      global.app.$router.push('/login')
    } else {
      // TODO: jhipster's format
      if (error.response.data) {
        let {title, message, detail} = error.response.data
        MessageBox({
          title: title,
          message: detail || message,
          type: 'error'
        })
      } else {
        Message.error(error.message)
      }
    }
  } else {
    Message.error(error.message)
  }
  return Promise.reject(error)
})

const post = (path, data, config) => {
  try {
    NProgress.start()
    let result = axios.post(path, data, config)
    return result
  } finally {
    NProgress.done()
  }
}
const get = (path, params) => {
  try {
    NProgress.start()
    let result = axios.get(path, {params: params})
    return result
  } finally {
    NProgress.done()
  }
}
const put = (path, data, config) => {
  try {
    NProgress.start()
    let result = axios.put(path, data, config)
    return result
  } finally {
    NProgress.done()
  }
}
const remove = (path, config) => {
  try {
    NProgress.start()
    let result = axios.delete(path, config)
    return result
  } finally {
    NProgress.done()
  }
}

// 暴露的对象或方法
export {axios, get, post, put, remove as delete}
