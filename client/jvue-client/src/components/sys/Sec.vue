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
    code: {
      type: String,
      required: true,
      default: 'text'
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
        this.permitted = true
      })
    }
  }
}
</script>
