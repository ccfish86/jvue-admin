<template>
  <div>
    <el-row>
      <el-col>你对当前画面无操作权限，请确认</el-col>
    </el-row>
    <el-row v-if="$route.redirectedFrom">
      <el-col>URL: {{$route.redirectedFrom}} </el-col>
    </el-row>
  </div>
</template>
<script>
</script>
