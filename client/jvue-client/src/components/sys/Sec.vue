<template>
  <div>
    <slot v-if="permitted"></slot>
  </div>
</template>

<script>
import vuet from '@/vuet/'

export default {
  name: 'sec',
  props: {
    // 画面内部编码
    code: {
      type: Number,
      required: true,
      default: 1
    }
  },
  data () {
    return {
      permitted: false
    }
  },
  beforeCreate () {
    let userSelf = vuet.getModule('user-self')
    if (userSelf /* && userSelf.secCodes && userSelf.secCodes.contains(this.code) */) {
      this.$nextTick(function () {
        // 取当前的pageId
        let pageId = this.$route.meta.id
        // 取当前page的segmentId[]
        let segments = userSelf.segments[pageId]
        if (segments && segments.includes(this.code)) {
          this.permitted = true
        } else {
          this.permitted = false
        }
      })
    }
  }
}
</script>
