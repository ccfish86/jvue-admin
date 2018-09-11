<template>
  <div>
    <el-form ref="moduleEdit" :model="moduleEdit.form" label-width="150px" class="container-form__mid">
      <el-form-item label="模块名称" :error="errors.first('moduleName')">
        <el-input v-model="moduleEdit.form.name" data-vv-name="moduleName"
                  v-validate="'required|max:20'"></el-input>
      </el-form-item>
      <el-form-item label="模块是否启用">
        <el-switch on-text="Yes" off-text="No" :inactive-value="0" :active-value="1" v-model="moduleEdit.form.enabled"></el-switch>
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
    mapModules({moduleEdit: 'sys-module-edit'}),
    mapRules({route: 'sys-module-edit'})
  ],
  methods: {
    onSubmit () {
      this.$validator.validateAll().then(result => {
        if (result) {
          this.moduleEdit.save().then((res) => {
            this.$message(messages.messageSaveSuccess())
            this.moduleEdit.fetch()
          }).catch((err) => {
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
    }
  }
}
</script>

<style scoped>

</style>
