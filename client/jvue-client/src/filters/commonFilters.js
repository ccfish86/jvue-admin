/**
 * Created by Yuangui on 2017-04-21.
 */
// import Store from '../store/'

const removeEmptyList = (value) => {
  if (value.children) {
    if (value.children.length === 0) {
      delete value.children
    } else {
      value.children.forEach(child => {
        removeEmptyList(child)
      })
    }
  }
}
const ALL_ROLES = {
  // 库管
  'LM': 'role$19'
}
export default {
  filterEmpty: (value) => {
    if (value && value instanceof Array) {
      value.forEach(item => {
        removeEmptyList(item)
      })
    }
    return value
  },
  hasRole: (value, role, mark) => {
    if (ALL_ROLES[role]) {
      let roles = [] // Store.state.user.roles
      if (roles && roles.includes(ALL_ROLES[role])) {
        return value
      }
      return mark
    }
  }
}
