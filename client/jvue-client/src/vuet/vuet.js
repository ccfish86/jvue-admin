import Vue from 'vue'
import Vuet from 'vuet'
import VuetRoute from 'vuet-route'
import VuetStore from 'vuet-store'
import common from './modules/common'
import user from './modules/user'
import sys from './modules/sys'

Vue.use(Vuet)
Vuet.rule('route', VuetRoute)
Vuet.rule('store', VuetStore)

export default new Vuet({
  pathJoin: '-',
  modules: {
    // 引入各业务的modules
    common, // common: common
    user,
    sys
  }
})
