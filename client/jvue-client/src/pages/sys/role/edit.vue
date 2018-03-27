<template>
  <div>
    <el-form ref="roleEdit" :model="roleEdit.form" label-width="150px" class="container-form__mid">
      <el-form-item label="角色名称" :error="errors.first('roleName')">
        <el-input v-model="roleEdit.form.name" data-vv-name="roleName" v-validate
                  data-vv-rules="required|max:20"></el-input>
      </el-form-item>
      <el-form-item label="角色是否启用">
        <el-switch on-text="Yes" off-text="No" :inactive-value="0" :active-value="1" v-model="roleEdit.form.enabled"></el-switch>
      </el-form-item>
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
  name: 'edit',
  mixins: [
    mapModules({roleEdit: 'sys-role-edit'}),
    mapRules({route: 'sys-role-edit'})
  ],
  methods: {
    onSubmit () {
      this.$validator.validateAll().then(result => {
        if (result) {
          this.roleEdit.save().then((res) => {
            this.$message(messages.messageSaveSuccess())
            this.roleEdit.fetch()
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
