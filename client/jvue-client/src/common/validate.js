/**
 * Created by Yuangui on 2017-01-07.
 */
import Vue from 'vue'
import Validator from 'vee-validate'
import attributesEn from './attributes/en.js'
import attributesZh from './attributes/zh_CN.js'
import messagesEn from './messages/en.js'
import messagesZh from './messages/zh_CN.js'

const valConfig = {
  errorBagName: 'errors', // change if property conflicts.
  fieldsBagName: 'fieldBags',
  delay: 0,
  locale: 'zh_CN',
  dictionary: {
    en: {
      attributes: attributesEn,
      messages: messagesEn
    },
    zh_CN: {
      attributes: attributesZh,
      messages: messagesZh
    }
  },
  strict: true
}
Vue.use(Validator, valConfig)
