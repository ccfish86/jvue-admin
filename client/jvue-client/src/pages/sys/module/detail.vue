<template>
  <div>
    <div>模块名：{{moduleDetail.detail.name}}</div>
    <div>是否启用：{{moduleDetail.detail.enabled|enumName('yn')}}</div>
    <div>***后续续追加一些其他方面的信息展示***</div>
  </div>
</template>

<script>
  import {mapModules, mapRules} from 'vuet'
  export default {
    name: 'detail',
    mixins: [
      mapModules({moduleDetail: 'sys-module-detail'}),
      mapRules({route: 'sys-module-detail'})
    ]
  }
</script>

<style scoped>

</style>
