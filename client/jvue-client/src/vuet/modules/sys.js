// import ApiUtils from '@/api/'

import module from './sys/sys-module'

import role from './sys/sys-role'
import page from './sys/sys-page'
import api from './sys/sys-api'
import segment from './sys/sys-segment'
import acl from './sys/sys-acl'

// import {utils} from '@/common/'
export default {
  modules: {
    module,
    page,
    api,
    segment,
    acl,
    role
  }
}
