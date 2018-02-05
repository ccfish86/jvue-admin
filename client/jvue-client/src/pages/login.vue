<template >
  <div>
    <div class="copyright">
      <div>京ICP备XXXXXXXXXXXXXXX号 | 京公网安备 XXXXXXXXXXXXXXXX</div>
      <div>Copyright &copy; 2015-2019 All Rights Reserved.<a href="http://www.baolaitong.com">ZZZZZZZZZZZZZZZZZZZ</a>  版权所有</div>
    </div>
    <el-form :model="loginForm" ref="loginForm" label-position="left" label-width="0px" class="login-container">
      <h3 class="title">JVUE-管理系统登录</h3>
      <el-form-item prop="account" :error="errors.first('account')">
        <el-input type="text" v-model.trim="loginForm.account" placeholder="账号" data-vv-name="account" v-validate
                  data-vv-rules="required||alpha_num||min:2" autofocus></el-input>
      </el-form-item>
      <el-form-item prop="password" :error="errors.first('password')">
        <el-input type="password" v-model="loginForm.password" auto-complete="off" data-vv-name="password" v-validate
                  data-vv-rules="required" placeholder="密码"></el-input>
      </el-form-item>
      <el-checkbox v-model="checked" class="remember">自动登录</el-checkbox>
      <el-form-item style="width:100%;">
        <el-button type="primary" style="width:40%;" native-type="submit" @click.native.prevent="handleSubmit"
                   :loading="logining">登录
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { mapModules } from 'vuet'
import {utils, messages} from '@/common/'

export default {
  mixins: [
    mapModules({ self: 'user-self' })
  ],
  data () {
    return {
      logining: false,
      loginForm: {
        account: '',
        password: ''
      },
      checked: false
    }
  },
  methods: {
    handleSubmit (ev) {
      this.$validator.validateAll().then(result => {
        if (result) {
          this.logining = true
          let loginParams = {
            username: this.loginForm.account,
            password: this.loginForm.password,
            remember: this.checked ? 1 : 0
          }
          this.self.login(loginParams).then((res) => {
            this.logining = false
            this.$message({
              showClose: true,
              duration: 1500,
              message: '登录成功'
            })
            // 初始化路由
            var path = this.$route.query.redirect
            this.$router.replace({path: path == '/' || path == undefined ? '/' : path})
          }).catch((err) => {
            this.logining = false
            this.$notify({
              title: '警告',
              message: err,
              type: 'warning',
              duration: 2500
            })
          })
        }
      }).catch(result => {
        this.$notify(messages.notifyCheckError())
      })
    },
    autoLogin () {
      //        this.$get('10002').then((response) => {
      //          let {code, data} = response.data
      //          if (code === global.consts.ERROR_CODE.SUCCESS) {
      //            if (data && data.id) {
      //              // this.USER_SIGNIN(data)
      //              this.$router.push('/erp/')
      //            }
      //          }
      //        })
    }
  },
  mounted () {
    let remembered = Boolean(utils.getCookie('remembered'))
    if (remembered) {
      this.checked = remembered
      this.autoLogin()
    }
  }
}

</script>

<style scoped>
  .copyright {
    width: 100%;
    position: fixed;
    bottom: 10px;
    text-align: center;
    font-size: 12px;
    color: #99a9bf;
    font-family: '黑体', Arial;
  }
  .copyright a:link{
    color: #99a9bf;
  }

  .login-container {
    margin: 180px 288px auto auto;
  }

  @media screen and (max-height: 768px) {
    .copyright {
      display: none;
    }

    .login-container {
      margin: 80px 100px auto auto;
    }
  }

  .login-container {
    /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin-bottom: 20px;
    background-color: #F9FAFC;
    border: 2px solid #8492A6;
    width: 350px;
    padding: 35px 35px 15px 35px;
  }

  .login-container .title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }

  .login-container .remember {
    margin: 0px 0px 35px 0px;
  }
</style>
