<template>
  <div>
    <div class="single-image">
      <div class="single-image__small" >
        <img :src="(image||value)||addPic|imageThum(200,150)" @click="change"/>
        <!-- 后续追加预览/删除和编辑 -->
      </div>
      <div class="single-image__tbar" v-if="image||value">
        <el-button icon="el-icon-view" @click="preview" type="text" size="small"/>
        <el-button icon="el-icon-delete" @click="remove" type="text" size="small" v-if="!disabled"/>
      </div>
      <div class="single-image__small" v-if="sample" >
        <img :src="sample"/>
        <span>示例图</span>
      </div>
    </div>
  </div>
</template>
<style scoped>
  @import url("./single_image.css");
</style>
<script>
import AddPic from '@/assets/images/add.png'
import { LDFimg } from '@/jslib/ldf-img.js'
import ImagePreview from '@/jslib/preview.js'
import {mapModules} from 'vuet'

export default {
  mixins: [
    mapModules({storePhoto: 'common-storage-photo'})
  ],
  props: {
    value: {
      type: String
    },
    disabled: {
      type: Boolean,
      default: false
    },
    waterMark: {
      type: Boolean,
      default: false
    },
    sample: {
      type: String,
      default: ''
    },
    upload: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      image: null,
      ldfImage: new LDFimg()
    }
  },
  computed: {
    addPic () {
      return this.disabled ? '' : AddPic
    }
  },
  methods: {
    change () {
      if (this.disabled) {
        this.preview()
        return
      }

      this.ldfImage.init({
        maxWH: 1280,
        quality: 600
      }, (imgData) => {
        this.image = imgData.dataURL
        let base64 = imgData.dataURL.replace(/^data:.+;base64,(.+)$/, '$1')
        if (this.upload) {
          this.storePhoto.upload(imgData.fileName, base64, this.waterMark ? 1 : 0).then((url) => {
            this.$emit('changed', url, imgData.dataURL)
          }).catch((err) => {
            console.log('上传失败' + err)
          })
        } else {
          this.$emit('changed', base64, imgData.dataURL)
        }
      })
    },
    /**
       * $emit(是否为原图)
       */
    remove () {
      if (this.image) {
        this.image = null
        this.$emit('removed')
      } else {
        this.value = null
        this.$emit('removed', true)
      }
    },
    preview () {
      let preImage = this.image || this.value
      if (preImage !== null) {
        new ImagePreview(preImage).preview()
      }
    }
  }
}
</script>
