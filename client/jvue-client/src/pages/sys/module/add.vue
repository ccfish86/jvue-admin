<template>
    <div>
      <el-form ref="moduleAdd" :model="moduleAdd.form" label-width="150px" class="container-form__mid">
        <el-form-item label="模块名称" :error="errors.first('moduleName')">
          <el-input v-model="moduleAdd.form.name" data-vv-name="moduleName" v-validate
                    data-vv-rules="required|max:20"></el-input>
        </el-form-item>
        <el-form-item label="模块是否启用">
          <el-switch on-text="Yes" off-text="No" inactive-value="0" active-value="1" v-model="moduleAdd.form.enabled"></el-switch>
        </el-form-item>
        <el-form-item>
          <el-button native-type="submit" type="primary" @click.native.prevent="onSubmit">保存</el-button>
          <el-button @click.native.prevent="$router.back()">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
</template>

<script>
  import {mapModules} from 'vuet'
  import {messages} from '@/common'
export default {
  name: 'add',
  mixins: [
    mapModules({moduleAdd: 'sys-module-add'})
  ],
  methods: {
    onSubmit() {
      this.$validator.validateAll().then(result => {
        if (result) {
          this.moduleAdd.save().then((res) => {
            this.$message(messages.messageSaveSuccess())
            this.moduleAdd.reset()
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
