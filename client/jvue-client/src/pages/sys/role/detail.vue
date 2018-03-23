<template>
  <div>
    <div>角色名：{{roleDetail.detail.name}}</div>
    <div>是否启用：{{roleDetail.detail.enabled|enumName('yn')}}</div>
    <div>***后续续追加一些其他方面的信息展示***</div>
  </div>
</template>

<script>
import {mapModules, mapRules} from 'vuet'
export default {
  name: 'detail',
  mixins: [
    mapModules({roleDetail: 'sys-role-detail'}),
    mapRules({route: 'sys-role-detail'})
  ]
}
</script>

<style scoped>

</style>
