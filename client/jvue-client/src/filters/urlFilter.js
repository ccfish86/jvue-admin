/**
 * Created by Yuangui on 2017-01-07.
 */
import defaultImage from '@/assets/images/timg.jpg'

const DEFAULT_ROOT = '//www.100902.com/'
const DEFAULT_WIDTH = 100
const DEFAULT_HEIGHT = 100
const DEFAULT_IMAGE = defaultImage

export default {
  thum (value, width, height, defaultImage) {
    if (value && value !== '') {
      if (value.match(/^https?:\/\/.+/g)) {
        if (value.indexOf(DEFAULT_ROOT) !== -1) {
          return value.replace(/^(.+)(_\d+x\d+)?(\.\w+)$/, '$1_' + (width || DEFAULT_WIDTH) + 'x' + (height || DEFAULT_HEIGHT) + '$3')
        } else {
          return value
        }
      } else if (value.match(/^data:.+;base64,(.+)$/)) {
        return value
      } else {
        return DEFAULT_ROOT + value.replace(/^(.+)(_\d+x\d+)?(\.\w+)$/, '$1_' + (width || DEFAULT_WIDTH) + 'x' + (height || DEFAULT_HEIGHT) + '$3')
      }
    } else {
      return DEFAULT_IMAGE
    }
  },
  trimUrls (urls) {
    if (urls === null) {
      return []
    }
    if (urls instanceof Array) {
      return urls.filter(url => url && url !== '')
    }
    if (urls instanceof String) {
      return [urls]
    }
    return urls
  },
  imageOri (value) {
    if (value && value !== '') {
      if (value.match(/^https?:\/\/.+/g)) {
        if (value.indexOf(DEFAULT_ROOT) !== -1) {
          return value.replace(/^(.+?)(_\d+x\d+)?(\.\w+)$/, '$1$3')
        } else {
          return value
        }
      } else if (value.match(/^data:.+;base64,(.+)$/)) {
        return value
      } else {
        return DEFAULT_ROOT + value
      }
    } else {
      return DEFAULT_IMAGE
    }
  }
}
