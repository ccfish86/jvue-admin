<template>
  <div>
    <el-alert title="ERROR 载入失败" type="error" description="请稍后再试，或联系管理员。"
              :closable="false" show-icon></el-alert>
    <el-row>
      <el-col :span="20" :offset="4">
        <router-view></router-view>
      </el-col>

    </el-row>
    <el-row>
      <el-col :span="2" :offset="4" class="blt-error-button">
          <el-button @click="$router.replace('/home')" type="primary">返回首页</el-button>
      </el-col>
      <el-col :span="2" :offset="1" class="blt-error-button">
        <el-button @click="$router.replace('/user/')">个人中心</el-button>
      </el-col>

    </el-row>
  </div>
</template>
<style scoped>
  .blt-error-button {
    line-height: 500%;
    vertical-align: center;
  }
</style>
<script>
export default{}
</script>
