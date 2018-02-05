<template>
  <div>
    <div class="multi-image--list">
      <div class="single-image" v-for="img in values" :key="img">
        <div class="single-image__small">
          <img :src="img|imageThum(200,150)" @click="preview(img)"/>
        </div>
        <div class="single-image__tbar">
          <el-button icon="el-icon-view" @click="preview(img)" type="text" size="small"/>
          <el-button icon="el-icon-delete" @click="remove(index, true)" type="text" size="small" v-if="!disabled"/>
        </div>
      </div>
      <div class="single-image" v-for="img in images" :key="img">
        <div class="single-image__small">
          <img :src="img|imageThum(200,150)" @click="preview(img)"/>
        </div>
        <div class="single-image__tbar">
          <el-button icon="el-icon-view" @click="preview(img)" type="text" size="small"/>
          <el-button icon="el-icon-delete" @click="remove(index)" type="text" size="small" v-if="!disabled"/>
        </div>
      </div>
      <div class="single-image" v-if="addable">
        <div class="single-image__small">
          <img width="196" :src="addPic" @click="change()"/>
        </div>
      </div>
    </div>

  </div>
</template>
 <style scoped>
   @import url("./single_image.css");
  .multi-image--list{
    width: 100%;
    height: auto;
    display: flex;
    flex-wrap: wrap;
    vertical-align: bottom;
  }
   .single-image {
     width: 33.3%;
   }
   .multi-image--list> .single-image {
     margin-bottom: 10px;
   }
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
    disabled: {
      type: Boolean,
      default: false
    },
    waterMark: {
      type: Boolean,
      default: false
    },
    // 编辑画面用，请配合$emit("deleted")使用，如果数据为后绑定，可使用:values.sync
    values: {
      type: Array,
      default: () => {
        return []
      }
    },
    title: {
      type: String,
      default: ''
    },
    sample: {
      type: String,
      default: ''
    },
    maxSize: {
      type: Number,
      default: 0
    },
    upload: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      images: [],
      imageDatas: [],
      addPic: AddPic,
      ldfImage: new LDFimg(),
      selectedImage: null
    }
  },
  computed: {
    addable () {
      let isFull = this.maxSize > 0 && this.maxSize <= this.values.length + this.images.length
      return !this.disabled && !isFull
    }
  },
  methods: {
    change () {
      if (this.disabled) {
        return
      }

      this.ldfImage.init({
        maxWH: 1280,
        quality: 600
      }, (imgData) => {
        this.images.push(imgData.dataURL)
        this.notify(imgData.fileName, imgData.dataURL.replace(/^data:.+;base64,(.+)$/, '$1'))
      })
    },
    notify (fileName, localData) {
      if (this.upload) {
        this.storePhoto.upload(fileName, localData, this.waterMark ? 1 : 0).then((url) => {
          this.$emit('selected', url)
        }).catch((err) => {
          console.log('上传失败' + err)
        })
      } else {
        this.$emit('selected', localData)
      }
    },
    remove (index, existed) {
      this.$emit('removed', index, existed)
      if (!existed) {
        this.images.splice(index, 1)
      }
    },
    preview (image) {
      this.selectedImage = image
      let preImage = this.selectedImage
      if (preImage !== null) {
        new ImagePreview(preImage).preview()
      }
    }
  }
}
</script>
