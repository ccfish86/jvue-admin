/**
 * Created by Yuangui on 2017-01-08.
 */
// currency
import { utils } from "@/common/"

export default {
  currencyCny (value) {
    if (value) {
      return '￥' + value.toFixed(2)
    } else {
      return '￥0'
    }
  },
  cutToDate (val) {
    if (val && val.length > 10) {
      return val.substr(0, 10)
    }
    return val
  },
  dateTimeFormat (value, format) {
    return utils.formatDate(value, format)
  },
  yesNo (value) {
    return value === 1 ? '是' : '否'
  },
  age (birthday) {
    if (birthday) {
      const now = new Date()
      const birthdaty = utils.toDate(birthday)
      return birthdaty ? Math.floor((now - birthdaty) / (24 * 3600 * 1000 * 30 * 12)) + '岁' : '-'
    }
  }
}
