/*
 * ldf-img
 * @author: ldf
 * githup: https://github.com/Gump8/ldf-img.git
 * */

// require('core-js(/library)/fn/object/assign');
let LDFimg = function () {
  let self = this

  /*
   * 实例化时须确保删除掉前一个实例化产生的dom结构
   * 此插件只获取压缩的图片数据,与上传步骤完全分离
   * */
  self.removeDom()
  self.createDom()
}
LDFimg.prototype = {
  /*
   * 默认参数
   * */
  options: {
    maxWH: 1024,
    quality: 600,
    FDkey: 'picture'
  },
  /*
   * 最终返回的数据
   * */
  backData: {
    dataURL: '',
    fileName: '',
    Blob: '',
    fileFormData: '',
    W: 0,
    H: 0,
    size: 0
  },
  /*
   * 初始化函数
   * 1. FileReader读取数据
   * 2. canvas等比压缩宽高,再压缩质量
   * 3. base64(URL)、Binary、FormData数据类型转换转换
   * */
  init: function (params, callback) {
    let self = this

    /*
     * UC浏览器(支付宝)中不支持Object.assign
     * Babel默认只转换新的JavaScript句法（syntax），而不转换新的API，
     * 比如Iterator、Generator、Set、Maps、Proxy、Reflect、Symbol、Promise等全局对象，
     * 以及一些定义在全局对象上的方法
     * self.options = Object.assign({}, self.options, params);
     * */
    if (params.maxWH) {
      self.options.maxWH = params.maxWH
    }
    if (params.quality) {
      self.options.quality = params.quality
    }
    if (params.FDkey) {
      if (typeof params.FDkey !== 'string') {
        alert('FDkey应为字符!')
        return
      }
      self.options.FDkey = params.FDkey
    }

    // console.log(self.options);
    // console.log(params);

    let inputFile = document.getElementById('ldf-input-file')

    inputFile.click()
    inputFile.addEventListener('change', function (e) {
      let files = e.target.files
      let file = files[0]
      let fileType = file.type
      let srcSizeKB = Math.round(file.size / 1024)
      let fileName = file.name

      // 保存文件大小数据
      self.backData.size = srcSizeKB
      // console.log('srcSizeKB', srcSizeKB);

      let readURL = new FileReader()

      /*
       * 将源文件读取为data: URL格式的字符串
       * base64
       * */
      readURL.readAsDataURL(file)
      readURL.onload = function () {
        let dataURL = readURL.result
        // console.log('yuan',dataURL)
        // console.log('源大小', self.fileSizeKB(dataURL));

        // gif图片，文件大小不超，不压缩
        let size = self.fileSizeKB(dataURL)
        if (/data:image\/gif;base64,/.test(dataURL) && size < self.options.quality) {
          let img = new Image()
          img.src = dataURL
          img.onload = function () {
            // 图像宽高
            let srcW = img.width
            let srcH = img.height
            let blob = self.dataURLtoBlob(dataURL, fileType)
            // console.log(blob);
            let formData = self.toFileFormData('', blob, fileName)

            self.backData.dataURL = dataURL
            self.backData.H = srcH
            self.backData.W = srcW
            self.backData.size = size
            self.backData.Blob = blob
            self.backData.fileName = fileName
            self.backData.fileFormData = formData

            typeof callback === 'function' && callback(self.backData)
          }
        } else {
          // gif图会自动转换为png
          fileType = file.type.replace('gif', 'png')
          self.WHcompress(fileType, fileName, dataURL, self.options.quality, function (data) {
            // console.log('clllllll')
            if (data.compressSizeRate < 1) {
              self.sizeCompress(fileType, fileName, data.newImgData, data.compressSizeRate, function () {
                /*
                 * 质量压缩只能压一次
                 * 即使没有达到目标值也不能再压
                 * */
                typeof callback === 'function' && callback(self.backData)
              })
            } else {
              // console.log('clllllll')
              typeof callback === 'function' && callback(self.backData)
            }
          })
        }
      }

      /*
       * 不管成功或失败都会执行
       * 解决若上传失败,再次选择同一张图时无法触发change事件问题
       * */
      readURL.onloadend = function () {
        self.removeDom()
        self.createDom()
      }
    })
  },
  /*
   * 把元素标签放入dom结构中, 解决IE兼容性问题
   * 完成后需删除
   * */
  createDom: function () {
    let self = this
    let dom = document.body || document.documentElement
    let inDom = `<div class="ldf-dom-container" style="display: none">
                            <canvas id="ldf-canvas"></canvas>
                            <input id="ldf-input-file" type="file" accept="image/*" >
                         </div>`
    if (self.isChromePC()) {
      inDom = `<div class="ldf-dom-container" style="display: none">
                            <canvas id="ldf-canvas"></canvas>
                            <input id="ldf-input-file" type="file" accept="image/gif,image/png,image/jpeg,image/jpg,image/bmp">
                         </div>`
    }
    dom.insertAdjacentHTML('beforeEnd', inDom)
  },
  /*
   * accept="image/gif,image/png,image/jpeg,image/jpg"
   * 实例化时须确保删除掉前一个实例化产生的dom结构
   * 此插件只获取压缩的图片数据,与上传的步骤完全分离
   * */
  removeDom: function () {
    let dom = document.getElementsByClassName('ldf-dom-container')
    for (let i = 0; i < dom.length; i++) {
      dom[i].parentNode.removeChild(dom[i])
    }
  },
  /*
  * chrome 64 位版本中会出现选择文件慢问题
  * */
  isChromePC: function () {
    let self = this

    let agent = window.navigator.userAgent
    if ((/Windows NT/.test(agent) || /Macintosh/.test(agent)) && /Chrome/.test(agent)) {
      return true
    }
    return false
  },
  /*
   * 压缩图片的宽高
   * */
  WHcompress: function (fileType, fileName, dataURL, quality, callback) {
    let self = this

    let img = new Image()
    img.src = dataURL
    img.onload = function () {
      // 图像宽高
      let srcW = img.width
      let srcH = img.height

      let _dWH = self.dWH(srcW, srcH, self.options.maxWH)
      // console.log('dWH', _dWH)

      let canvas = document.getElementById('ldf-canvas')
      let context = canvas.getContext('2d')
      canvas.width = _dWH.width
      canvas.height = _dWH.height

      // 清空后, 重写画布
      context.clearRect(0, 0, canvas.width, canvas.height)
      context.drawImage(img, 0, 0, canvas.width, canvas.height)

      let newImgData = canvas.toDataURL(fileType)

      // 压缩宽高后的图像大小
      let newImgSize = self.fileSizeKB(newImgData)
      // console.log('压缩宽高后', newImgSize)

      // 返回图像的压缩率
      let compressSizeRate = self.qualityRate(newImgData, self.options.quality)

      // 保存最终返回的图像的宽高
      self.backData.W = _dWH.width
      self.backData.H = _dWH.height
      self.backData.size = newImgSize

      // 大于 0.92 时, 不再进行质量压缩, 保存最终返回的数据
      if (compressSizeRate >= 0.9) {
        let blob = self.dataURLtoBlob(newImgData, fileType)
        // console.log(blob);
        let formData = self.toFileFormData('', blob, fileName)

        self.backData.dataURL = newImgData
        self.backData.size = newImgSize
        self.backData.Blob = blob
        self.backData.fileName = fileName
        self.backData.fileFormData = formData
      }

      typeof callback === 'function' && callback({
        compressSizeRate: compressSizeRate,
        newImgData: newImgData
      })
    }
  },
  /*
   * 压缩图片的质量
   * */
  sizeCompress: function (fileType, fileName, dataURL, qualityRate, callback) {
    let self = this

    let img = new Image()
    img.src = dataURL
    img.onload = function () {
      // 图像宽高
      let srcW = img.width
      let srcH = img.height

      let _dWH = self.dWH(srcW, srcH, self.options.maxWH)

      let canvas = document.getElementById('ldf-canvas')
      let context = canvas.getContext('2d')
      canvas.width = _dWH.width
      canvas.height = _dWH.height

      // 清空后, 重写画布
      context.clearRect(0, 0, canvas.width, canvas.height)
      context.drawImage(img, 0, 0, canvas.width, canvas.height)

      let newImgData = canvas.toDataURL(fileType, qualityRate)
      // console.log(fileType, qualityRate);
      // console.log(self.fileSizeKB(newImgData));

      let newImgSize = self.fileSizeKB(newImgData)

      // 保存最终返回的数据
      let blob = self.dataURLtoBlob(newImgData, fileType)
      // console.log(blob)
      let formData = self.toFileFormData('', blob, fileName)

      self.backData.dataURL = newImgData
      self.backData.size = newImgSize
      self.backData.Blob = blob
      self.backData.fileName = fileName
      self.backData.fileFormData = formData

      typeof callback === 'function' && callback({
        newImgData: newImgData
      })
    }
  },
  /*
   * 长宽等比缩小
   * 图像的一边(长或宽)为最大目标值
   * */
  dWH: function (srcW, srcH, dMax) {
    let defaults = {
      width: srcW,
      height: srcH
    }
    if (Math.max(srcW, srcH) > dMax) {
      if (srcW > srcH) {
        defaults.width = dMax
        defaults.height = Math.round(srcH * (dMax / srcW))
        return defaults
      } else {
        defaults.height = dMax
        defaults.width = Math.round(srcW * (dMax / srcH))
        return defaults
      }
    } else {
      return defaults
    }
  },
  /*
   * 计算dataURL(base64)文件大小(KB)
   * */
  fileSizeKB: function (dataURL) {
    let self = this

    let sizeKB = 0
    sizeKB = Math.round((dataURL.split(',')[1].length * 3 / 4) / 1024)

    return sizeKB
  },
  /*
   * 计算toDataURL(type, encoderOptions)的质量压缩率
   * 0 ~ 1
   * */
  qualityRate: function (imgData, dsize) {
    let self = this

    let fileSize = self.fileSizeKB(imgData)
    // console.log('fileSize', fileSize);

    let dQualityRate = Math.round((dsize - 1) / fileSize * 100) / 100

    // console.log('dsize', dsize);
    // console.log('dQualityRate', dQualityRate);

    return dQualityRate
  },
  /*
   * 转为Blob
   * */
  dataURLtoBlob: function (dataURL, fileType) {
    let self = this

    let byteString = atob(dataURL.split(',')[1])
    let mimeString = dataURL.split(',')[0].split(':')[1].split(';')[0]
    let ab = new ArrayBuffer(byteString.length)
    let ia = new Uint8Array(ab)
    for (let i = 0; i < byteString.length; i++) {
      ia[i] = byteString.charCodeAt(i)
    }
    if (fileType) {
      mimeString = fileType
    }
    return new Blob([ab], {type: mimeString, lastModifiedDate: new Date()})
  },
  /*
   * 转换数据类型为:FormData
   * 关键在于写入文件名及其后缀(文件类型)
   * file.type不可写
   *
   * let file = new File([blob], fileName);
   * fd.append("picture", file);
   * 此方法在 iOS 中不兼容
   *
   * 选用此法: fd.append("picture", blob, fileName);
   * */
  toFileFormData: function (dataURL, blob, fileName) {
    let self = this

    let fd = new FormData()
    let FDkey = self.options.FDkey
    if (blob) {
      fd.append(FDkey, blob, fileName)
      return fd
    } else {
      let blobData = self.dataURLtoBlob(dataURL, fileType)
      fd.append(FDkey, blobData, fileName)
      return fd
    }
  }
}

export {LDFimg}
