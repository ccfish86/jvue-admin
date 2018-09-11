<template>
  <el-container>

    <el-container>
      <el-aside width="201px" class="left-nav">
        <el-menu :default-active="String(userSelf.active)" router theme="dark" @open="handleOpen" @close="handleClose" :default-openeds="userSelf.openeds">
          <template v-for="router in userSelf.leftRoutes">
            <el-submenu :index="router.path" :key="router.path"
                        v-if="router.children && router.children instanceof Array && router.children.length > 0">
              <template slot="title">{{router.name}}</template>
              <el-menu-item v-for="child in router.children" :key="child.name" :index="child.path">{{child.meta.name}}
              </el-menu-item>
            </el-submenu>
            <el-menu-item :index="router.path" :key="router.meta.id" v-else>{{router.meta.name}}</el-menu-item>
          </template>
        </el-menu>
        <!--<router-view name="left">{{userSelf.leftRoutes}}</router-view>-->
        <div style="background: #e1f3d8">
          <div>&nbsp;</div>
          <el-tag size="small">#<a href="http://192.168.10.83/zentao" target="_blank">BUG提交</a>#</el-tag>
        </div>
      </el-aside>
      <el-container>
        <el-header>
          <el-row>
            <el-col :span="18" class="logo">
              <el-row :gutter="10">
                <el-col :span="24">
                    <div>
                    <el-menu :default-active="String(userSelf.moduleId)" mode="horizontal">
                      <el-menu-item v-for="module in userSelf.modules" :index="String(module.id)" :key="module.name"
                                    @click="userSelf.changeModule(module.id)">{{module.name}}
                  </el-menu-item>
                    </el-menu>
                  </div>
                </el-col>
              </el-row>
            </el-col>
            <el-col :span="5" :push="1">
              <el-dropdown size="small" @command="handleCommand">
            <span class="el-dropdown-link">
              <i class="fa fa-user"></i>&nbsp;&nbsp;{{userSelf.user.nickname}}<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
                <el-dropdown-menu slot="dropdown" >
                  <el-dropdown-item command="github" >代码地址</el-dropdown-item>
                  <el-dropdown-item command="api" >API地址</el-dropdown-item>
                  <el-dropdown-item command="signout" divided>退出登录</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </el-col>
          </el-row>
        </el-header>
        <el-header height="25px">
          <el-breadcrumb separator-class="el-icon-arrow-right" v-if="$route.matched.length > 0">
            <el-breadcrumb-item class="breadcrumb-inner" to="/">
              首页
            </el-breadcrumb-item>
            <el-breadcrumb-item class="breadcrumb-inner" v-for="item in $route.matched" v-if="item.meta.name"
                                :to="item.path? item: {path: '/'}" :key="item.path">
              {{item.meta.name}}
            </el-breadcrumb-item>
          </el-breadcrumb>
        </el-header>
      <el-main>
        <transition name="fade">
          <router-view></router-view>
        </transition>
      </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>

<style scoped>
  .left-nav {
    min-height:720px;
  }
  .el-breadcrumb{
    margin-top: 15px
  }
  /* 路由切换动效 */
  .fade-leave-active {
    transition-duration: 0s;
  }

  .fade-enter-active {
    /*transition: all .5s;*/
    opacity: 1;
    transition: opacity 0.5s linear;
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
      need: 'common-dict-enums',
      store: [{path: 'user-self'}, {path: 'common-dict-enums'}]
    })
  ],
  data () {
    return {
      loading: true,
      authed: false
    }
  },
  mounted () {
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
    handleOpen () {
      // console.log('handleOpen')
    },
    handleClose () {
      // console.log('handleClose')
    },
    signout () {
      this.userSelf.signout().finally(() => {
        this.$router.push('/login?logout')
      })
    },
    handleCommand (command) {
      if (command === 'github') {
        window.open('https://github.com/ccfish86/jvue-admin')
      } else if (command === 'api') {
        window.open('/api/swagger-ui.html')
      } else if (command === 'signout') {
        this.signout()
      } else {
        console.warn(`未处理：${command}`)
      }
    }
  }
}
</script>
