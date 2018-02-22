<template>
  <el-row>
    <el-col :span="24">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item class="breadcrumb-inner" v-for="item in $route.matched" v-if="item.meta.title"
                            :to="item.path? item: {path: '/'}" :key="item">
          {{ item.meta.title }}
        </el-breadcrumb-item>
      </el-breadcrumb>
    </el-col>
    <el-col :span="24" class="content-wrapper">
      <router-view></router-view>
    </el-col>
  </el-row>
</template>
<style scoped>
  .el-col + .el-col {
    margin-top: 20px;
  }
</style>
<script>
export default {
  data () {
    return {}
  },
  mounted () {
  },
  methods: {}
}
</script>
