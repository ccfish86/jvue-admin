/**
 * Created by Yuangui on 2017-01-16.
 */

export default {
  bankIcon (value) {
    try {
      let img = require('@/assets/images/banks/' + value + '.png')
      return img
    } catch (err) {
      return ''
    }
  }
}
