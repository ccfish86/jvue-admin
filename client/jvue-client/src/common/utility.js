/**
 * Created by Yuangui on 2017-01-10.
 */
import dateUtil from 'element-ui/src/utils/date'
const isDate = function (date) {
  if (date === null || date === undefined) return false
  if (isNaN(new Date(date).getTime())) return false
  return true
}

const utils = {
  getFistImage (images) {
    if (!images) {
      return null
    }
    if (images instanceof Array) {
      if (images.length > 0) {
        return images[0]
      } else {
        return null
      }
    } else {
      return images
    }
  },
  setWindowTitle (title) {
    document.title = title
    var mobile = navigator.userAgent.toLowerCase()
    if (/iphone|ipad|ipod/.test(mobile)) {
      var iframe = document.createElement('iframe')
      iframe.style.display = 'none'
      // 替换成站标favicon路径或者任意存在的较小的图片即可
      // iframe.setAttribute('src', '/favicon.ico')
      var iframeCallback = function () {
        setTimeout(function () {
          iframe.removeEventListener('load', iframeCallback)
          document.body.removeChild(iframe)
        }, 0)
      }
      iframe.addEventListener('load', iframeCallback)
      document.body.appendChild(iframe)
    }
  },
  toDate (date) {
    return isDate(date) ? new Date(date) : null
  },
  formatDate (date, format) {
    date = utils.toDate(date)
    if (!date) return ''
    return dateUtil.format(date, format || 'yyyy-MM-dd')
  },
  formatDateTime (date, format) {
    date = utils.toDate(date)
    if (!date) return ''
    return dateUtil.format(date, format || 'yyyy-MM-dd HH:mm:ss')
  },
  formatSeconds (value) {
    let theTime = parseInt(value)
    let theTime1 = 0
    let theTime2 = 0
    if (theTime > 60) {
      theTime1 = parseInt(theTime / 60)
      theTime = parseInt(theTime % 60)
      if (theTime1 > 60) {
        theTime2 = parseInt(theTime1 / 60)
        theTime1 = parseInt(theTime1 % 60)
      }
    }
    let result = '' + parseInt(theTime) + '秒'
    if (theTime1 > 0) {
      result = '' + parseInt(theTime1) + '分' + result
    }
    if (theTime2 > 0) {
      result = '' + parseInt(theTime2) + '小时' + result
    }
    return result
  },
  /**
   * 清除CSS style
   * @param dom HLElement
   * @param styleName 样式名
   * @param recursive 是否递归（向上）
   */
  cleanStyle (dom, styleName, recursive) {
    dom.style[styleName] = null
    if (recursive) {
      if (dom.parentNode) {
        utils.cleanStyle(dom.parentNode, styleName, recursive)
      }
    }
  },
  setCookie (name, value, iDay) {
    let oDate = new Date()
    oDate.setDate(oDate.getDate() + iDay)
    document.cookie = name + '=' + encodeURIComponent(value) + ';expires=' + oDate
  },
  getCookie (name) {
    let arr = document.cookie.split(';')
    for (let i = 0; i < arr.length; i++) {
      let arr2 = arr[i].split('=')
      if (arr2[0].trim() === name.trim()) {
        let getC = decodeURIComponent(arr2[1])
        return getC
      }
    }
    return ''
  },
  removeCookie (name) {
    utils.setCookie(name, '1', -1)
  },
  toRoutes (menus) {
    let userRouters = []
    menus.forEach(menu => {
      let {
        id,
        moduleId,
        path,
        component,
        name,
        meta = {},
        iconCls,
        children,
        showNav
      } = menu
      if (children && children instanceof Array) {
        children = utils.toRoutes(children)
      }
      meta.moduleId = moduleId
      meta.id = id
      meta.name = name
      meta.showNav = showNav

      let userRouter = {
        path: path,
        component (resolve) {
          let vfile = component.replace(/(-)/g, (v) => '/')
          require(['@/pages/' + vfile + '.vue'], resolve)
        },
        name: name,
        iconCls: iconCls,
        meta: meta,
        children: children
      }
      userRouters.push(userRouter)
    })
    return userRouters
  },
  sortInfo (props, sorting) {
    if (props) {
      let sort = utils.underLine(props)
      let direction = sorting === 'descending' ? 1 : 0
      return {sort, direction}
    }
    return {}
  },
  underLine (str) {
    var re = /([A-Z])/g
    return str.replace(re, function ($0, $1) {
      return '_' + $1.toLowerCase()
    })
  },
  treeToList (lst, name = 'name', children = 'childs', append) {
    let result = []
    if (lst && lst instanceof Array) {
      for (let item of lst) {
        item.fullName = append ? append + ' ' + item[name] : item[name]
        let cResult = utils.treeToList(item[children], name, children, item[name])
        delete item[children]
        result.push(item)
        result = result.concat(cResult)
      }
    }
    return result
  },
  getCascader (cascaderCode) {
    if (cascaderCode && cascaderCode instanceof Array) {
      let len = cascaderCode.length
      if (len > 0) {
        return cascaderCode[len - 1]
      } else {
        return null
      }
    }
    return cascaderCode
  },
  getDeCascader (code, len) {
    if (code === null) {
      return []
    }

    let tlen = code.length
    if (tlen >= len) {
      let result = []
      for (let i = 0; i++; i < 10) {
        let si = i * len
        if (si + len > tlen) {
          result.push(code.substr(si, len))
        } else {
          result.push(code.substr(si, tlen - si))
          break
        }
      }
      return result
    }
    return [code]
  },
  treeToObject (lst, key = 'code', name = 'name', children = 'childs', append) {
    let result = {}
    if (lst && lst instanceof Array) {
      for (let item of lst) {
        let fullName = append ? append + ' ' + item[name] : item[name]
        let cResult = utils.treeToObject(item[children], key, name, children, fullName)
        let code = item[key]
        result[code] = fullName
        result = Object.assign(result, cResult)
      }
    }
    return result
  }
}
// 使用时直接在Script中使用[utils.setWindowTitle]取值即可
// global.utils = utils
export {utils}
