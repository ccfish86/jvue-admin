<template>
  <div>
    <el-form ref="pageAdd" :model="pageAdd.form" label-width="150px" class="container-form__mid">
      <el-form-item label="所属模块" :error="errors.first('module')">
        <el-select clearable v-model="pageAdd.form.moduleId" placeholder="请选择" data-vv-name="module"
                   v-validate="'required'" @change="moduleChanged">
          <el-option v-for="item in moduleNames.list" :key="item.code"
                     :label="item.name" :value="item.code">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="父画面">
        <el-select clearable v-model="pageAdd.form.parentId" placeholder="请选择" >
          <el-option v-for="item in pageNames.list" :key="item.code"
                     :label="item.name" :value="item.code">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="画面名称" :error="errors.first('pageName')">
        <el-input v-model="pageAdd.form.name" data-vv-name="pageName" 
                  v-validate="'required|max:20'"></el-input>
      </el-form-item>
      <el-form-item label="画面类型" :error="errors.first('pageType')">
        <el-radio-group size="small" v-model="pageAdd.form.type" data-vv-name="pageType" 
                        v-validate="'required'">
          <el-radio-button :label="1">菜单</el-radio-button>
          <el-radio-button :label="2">画面</el-radio-button>
          <el-radio-button :label="3">菜单+画面</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="画面路径" :error="errors.first('pagePath')">
        <el-input v-model="pageAdd.form.path" data-vv-name="pagePath" 
                  v-validate="'required|max:120'"></el-input>
      </el-form-item>
      <el-form-item label="画面组件" :error="errors.first('pageComponent')">
        <el-input v-model="pageAdd.form.component" data-vv-name="pageComponent" 
                  v-validate="'required|max:120'"></el-input>
      </el-form-item>

      <el-form-item label="画面是否启用">
        <el-switch on-text="Yes" off-text="No" :inactive-value="0" :active-value="1" v-model="pageAdd.form.enabled"></el-switch>
      </el-form-item>

      <el-form-item label="导航是否启用">
        <el-switch on-text="Yes" off-text="No" :inactive-value="0" :active-value="1" v-model="pageAdd.form.showNav"></el-switch>
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
  name: 'add',
  mixins: [
    mapModules({pageAdd: 'sys-page-add', moduleNames: 'sys-module-names', pageNames: 'sys-page-names'}),
    mapRules({need: 'sys-module-names', temp: 'sys-page-add'})
  ],
  methods: {
    onSubmit () {
      this.$validator.validateAll().then(result => {
        if (result) {
          this.pageAdd.save().then((res) => {
            this.$message(messages.messageSaveSuccess())
            // 跳转至编辑画面
            let id = res.id
            this.$router.push(`/sys/page/edit/${id}`)
            // this.pageAdd.reset()
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
    },
    moduleChanged (nid) {
      // 模块切换，重新查询父画面（一级）
      if (nid != null) {
        this.pageNames.getPages(nid)
      }
    }
  }
}
</script>

<style scoped>

</style>
