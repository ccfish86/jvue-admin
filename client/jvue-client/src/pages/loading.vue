<template>
  <div>loading page.
  正在跳转
  </div>
</template>

<script>
  import {mapModules, mapRules} from 'vuet'
  export default {
    mixins: [
      mapModules({self: 'user-self'}),
      mapRules({once: 'user-self', store: 'user-self'})
    ],
    name: 'index',
    mounted() {
        this.$router.replace('/user/list')
    }
  }
</script>

<style scoped>

</style>
