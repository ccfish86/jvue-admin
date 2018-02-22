<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
import 'babel-polyfill'
export default {
  name: 'App'
}
</script>
<style scoped>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 2px;
  }
</style>
