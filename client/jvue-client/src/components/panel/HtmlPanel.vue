<template>
  <div class="panel-container" v-loading="storeHtml.loading">
    <pre v-text="storeHtml.content" v-if="format === 'text'"></pre>
    <div v-html="html" v-else-if="format === 'markdown'"></div>
    <div v-html="storeHtml.content" v-else></div>
  </div>
</template>
<script>
import {mapModules} from 'vuet'
import MarkdownIt from 'markdown-it'

export default {
  mixins: [
    mapModules({storeHtml: 'common-storage-html'})
  ],
  // 使用时请使用 :url.sync=""传值
  props: {
    format: {
      type: String,
      default: 'text'
    },
    url: {
      required: true
    }
  },
  data () {
    return {
      html: ''
    }
  },
  watch: {
    url (value) {
      this.load(value)
    }
  },
  mounted () {
    this.load(this.url)
  },
  methods: {
    load (url) {
      if (url && url.length > 0) {
        // 加载中
        this.storeHtml.load(url).then((data) => {
          // loaded
          if (this.format === 'markdown') {
            let md = new MarkdownIt()
            if (md) {
              this.html = md.render(data)
            } else {
              console.info(data)
              this.html = data
            }
          }
        }).catch(() => {
          // do nth
        })
      }
    }
  }
}
</script>
<style scoped>
  .panel-container {
    background-color: white;
  }
  pre {
    white-space: pre-wrap;
    word-wrap: break-word;
    line-height: 180%;
  }
</style>
