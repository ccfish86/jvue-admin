<template>
    <div>
      <el-form ref="userAdd" :model="userAdd.form" label-width="150px" class="container-form__mid">
        <el-form-item label="用户名称" :error="errors.first('username')">
          <el-input v-model="userAdd.form.username" data-vv-name="username"
                    v-validate="'required|alpha_num|min:2'"></el-input>
        </el-form-item>
        <el-form-item label="用户密码" :error="errors.first('password')">
          <el-input type="password" v-model="userAdd.form.password" data-vv-name="password"
                    v-validate="'required'"></el-input>
        </el-form-item>
        <el-form-item label="用户昵称" :error="errors.first('nickname')">
          <el-input v-model="userAdd.form.nickname" data-vv-name="nickname"
                    v-validate="'required|max:20'"></el-input>
        </el-form-item>
        <el-form-item label="用户Email" :error="errors.first('email')">
          <el-input v-model="userAdd.form.email" data-vv-name="email"
                    v-validate="'required|email|max:64'"></el-input>
        </el-form-item>

        <!-- 部门 -->
        <el-form-item label="部门">
          <el-cascader expand-trigger="hover" change-on-select :options="deptNames.depts"
                       :props="deptProps" v-model="userAdd.form.deptCode"></el-cascader>
        </el-form-item>

        <el-form-item label="用户状态">
          <el-switch on-text="Yes" off-text="No" :inactive-value="0" :active-value="1" v-model="userAdd.form.status"></el-switch>
        </el-form-item>

        <v-sec :code="1">
          <el-form-item label="是否管理员">
            <el-switch on-text="Yes" off-text="No" :inactive-value="0" :active-value="1" v-model="userAdd.form.superUser"></el-switch>
          </el-form-item>
        </v-sec>
        <el-form-item>
          <el-button native-type="submit" type="primary" @click.native.prevent="onSubmit">保存</el-button>
          <el-button @click.native.prevent="$router.back()">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
</template>

<script>
import {mapModules, mapRules} from 'vuet'
import {messages} from '@/common'
export default {
  name: 'add',
  mixins: [
    mapModules({userAdd: 'user-add', deptNames: 'user-dept-names'}),
    mapRules({temp: 'user-add', need: 'user-dept-names'})
  ],
  data () {
    return {
      deptProps: {
        value: 'code',
        label: 'name',
        children: 'childs'
      }
    }
  },
  methods: {
    onSubmit () {
      this.$validator.validateAll().then(result => {
        if (result) {
          this.userAdd.save().then((res) => {
            this.$message(messages.messageSaveSuccess())
            this.userAdd.reset()
          }).catch((err) => {
            this.$notify({
              title: '警告',
              message: err,
              type: 'warning',
              duration: 2500
            })
          })
        } else {
          this.$notify(messages.notifyCheckError())
        }
      }).catch(result => {
        this.$notify(messages.notifyCheckError())
      })
    }
  }
}
</script>

<style scoped>

</style>
