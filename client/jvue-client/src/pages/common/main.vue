<template>
  <el-row class="container">
    <el-col :span="24" class="header">
      <!--<router-view name="top"></router-view>-->
      <el-row>
        <el-col :span="16" class="logo">
          <el-row :gutter="10">
            <el-col :span="2">
              <span class="logo-info-span">JVUE</span>
            </el-col>
            <el-col :span="20">
              <el-menu :default-active="String(userSelf.moduleId)" mode="horizontal">
                <el-menu-item v-for="module in userSelf.modules" :index="String(module.id)" :key="module.name"
                              @click="userSelf.changeModule(module.id)">{{module.name}}
                </el-menu-item>
              </el-menu>
            </el-col>
          </el-row>
        </el-col>
        <el-col :span="3">
          <div class="main-top--menu-item logo-div__nav"><i
            class="fa fa-user"></i>&nbsp;&nbsp;{{userSelf.user.nickname}}
          </div>
        </el-col>
        <el-col :span="4">
          <el-row style="text-align:center">
            <el-col>
              <div class="main-top--menu-item" @click="signout"><i class="fa fa-power-off"></i>&nbsp;&nbsp;退出登录
              </div>
            </el-col>
          </el-row>
        </el-col>
      </el-row>
    </el-col>
    <el-col :span="24" class="main">
      <aside>
        <el-menu :default-active="String(userSelf.active)" router theme="dark" @open="handleOpen" @close="handleClose">
          <template v-for="router in userSelf.leftRoutes">
            <el-submenu :index="router.path"
                        v-if="router.children && router.children instanceof Array && router.children.length > 0">
              <template slot="title">{{router.name}}</template>
              <el-menu-item v-for="child in router.children" :key="child.name" :index="child.path">{{child.meta.name}}
              </el-menu-item>
            </el-submenu>
            <el-menu-item :index="router.path" v-else>{{router.meta.name}}</el-menu-item>
          </template>
        </el-menu>
        <!--<router-view name="left">{{userSelf.leftRoutes}}</router-view>-->
        <div style="background: #e1f3d8">
          <div>&nbsp;</div>
          <el-tag size="small">#<a href="http://192.168.10.83/zentao" target="_blank">BUG提交</a>#</el-tag>
        </div>
      </aside>
      <section class="content-container">
        <transition name="fade">
          <router-view></router-view>
        </transition>
      </section>
    </el-col>
  </el-row>
</template>

<style scoped>
  .container {
    height: 100%;
  }

  .header {
    margin-top: 0;
    line-height: 40px;
  }

  .main {
    background: #324057;
    position: absolute;
    top: 65px;
    bottom: 0px;
    /*height: 100%;*/
    /*min-height: 700px;*/
    /*overflow: hidden;*/
  }

  aside {
    position: absolute;
    top: 0px;
    left: 0px;
    width: 180px;
    height: 100%;
  }

  .content-container {
    background: white;
    position: absolute;
    right: 0px;
    top: 0px;
    bottom: 0px;
    left: 190px;
    overflow-y: scroll;
    padding: 20px;
  }

  @media screen and (max-width: 1366px) {
    aside {
      width: 180px;
    }

    .content-container {
      left: 180px;
    }
  }

  /* 路由切换动效 */
  .fade-leave-active {
    transition-duration: 0s;
  }

  .fade-enter-active {
    /*transition: all .5s;*/
    opacity: 1;
    transition: opacity 1s linear;
  }

  .fade-enter, .fade-leave-active {
    opacity: 0;
  }
</style>
<script>
  import {mapModules, mapRules} from 'vuet'

  export default {
    mixins: [
      mapModules({userSelf: 'user-self'}),
      mapRules({
        store: [{path: 'user-self'}]
      }),
    ],
    data() {
      return {
        loading: true,
        authed: false
      }
    },
    mounted() {
      if (this.$route.matched) {
        this.$route.matched.forEach(router => {
          if (router.meta && router.meta.moduleId) {
            this.userSelf.changeModule(router.meta.moduleId)
          }
          if (router.path) {
            this.userSelf.active = router.path
            return true
          }
        })
      }
    },
    methods: {
      handleOpen() {
        // console.log('handleOpen')
      },
      handleClose() {
        // console.log('handleClose')
      },
      signout() {
        this.userSelf.signout()
        this.userSelf.reset()
      }
    }
  }
</script>
