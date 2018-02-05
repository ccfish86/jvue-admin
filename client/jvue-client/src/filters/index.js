/**
 * Created by Yuangui on 2017-01-05.
 */

import Vue from 'vue'
import DictData from './dictData'
import UrlFilter from './urlFilter'
import formaters from './formatter'
import filters from './commonFilters'
// import BankFilter from './bankFilter'

Vue.filter('regionName', DictData.regionData)
Vue.filter('dictName', DictData.dictData)
Vue.filter('enumName', DictData.enumData)
Vue.filter('enumsType', DictData.enumsType)
Vue.filter('regionsType', DictData.regionsType)
Vue.filter('codesType', DictData.codesType)
Vue.filter('imageThum', UrlFilter.thum)
Vue.filter('trimUrls', UrlFilter.trimUrls)
Vue.filter('imageOri', UrlFilter.imageOri)

Object.keys(formaters).forEach(key => {
  Vue.filter(key, formaters[key])
})

Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

export default {

}
