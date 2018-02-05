<template>
  <div>
    <img src="/static/img/404.png">
    <el-row v-if="$route.redirectedFrom">
      <el-col>URL-link: <el-button @click="back()">{{$route.redirectedFrom}} </el-button> </el-col>
      <el-col >URL-back: <el-button @click="$router.back()">{{$route.redirectedFrom}} </el-button> </el-col>
    </el-row>
  </div>
</template>
<script>
export default{
  methods: {
    back() {
      let path = this.$route.redirectedFrom
      if (path) {
        this.$router.push(path)
      } else {
        console.error('path is null')
      }
    }
  }
}
</script>
