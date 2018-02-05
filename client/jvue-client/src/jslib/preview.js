const DEFAULT_ROOT = '//www.100902.com/'

export class ImagePreview {
  constructor (url) {
    this.url = url
  }
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
      return ''
    }
  }
  preview () {
    let image = this.imageOri(this.url)
    let fullDiv = `<div class="blt-fullscreen" id="image-preview">
      <img src="${image}" />
      <div class="blt-rt">点击关闭</div>
      </div>`
    let dom = document.body || document.documentElement
    dom.insertAdjacentHTML('beforeEnd', fullDiv)
    let imagePreviewDiv = document.getElementById('image-preview')
    imagePreviewDiv.addEventListener('click', function () {
      imagePreviewDiv.parentNode.removeChild(imagePreviewDiv)
    })
  }
}

export default ImagePreview
