<template>
  <div>
    <el-form ref="menuAdd" :model="menuAdd.form" label-width="150px" class="container-form__mid">
      <el-form-item label="所属模块" :error="errors.first('module')">
        <el-select clearable v-model="menuAdd.form.moduleId" placeholder="请选择" data-vv-name="module"
                   v-validate data-vv-rules="required" @change="moduleChanged">
          <el-option v-for="item in moduleNames.list"
                     :label="item.name" :value="item.code">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="父画面">
        <el-select clearable v-model="menuAdd.form.parentId" placeholder="请选择" >
          <el-option v-for="item in menuNames.list"
                     :label="item.name" :value="item.code">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="画面名称" :error="errors.first('menuName')">
        <el-input v-model="menuAdd.form.name" data-vv-name="menuName" v-validate
                  data-vv-rules="required|max:20"></el-input>
      </el-form-item>
      <el-form-item label="画面类型" :error="errors.first('menuType')">
        <el-radio-group size="small" v-model="menuAdd.form.type" data-vv-name="menuType" v-validate
                        data-vv-rules="required">
          <el-radio-button :label="1">菜单</el-radio-button>
          <el-radio-button :label="2">画面</el-radio-button>
          <el-radio-button :label="3">菜单+画面</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="画面路径" :error="errors.first('menuPath')">
        <el-input v-model="menuAdd.form.path" data-vv-name="menuPath" v-validate
                  data-vv-rules="required|max:120"></el-input>
      </el-form-item>
      <el-form-item label="画面组件" :error="errors.first('menuComponent')">
        <el-input v-model="menuAdd.form.component" data-vv-name="menuComponent" v-validate
                  data-vv-rules="required|max:120"></el-input>
      </el-form-item>

      <el-form-item label="画面是否启用">
        <el-switch on-text="Yes" off-text="No" :inactive-value="0" :active-value="1" v-model="menuAdd.form.enabled"></el-switch>
      </el-form-item>

      <el-form-item label="导航是否启用">
        <el-switch on-text="Yes" off-text="No" :inactive-value="0" :active-value="1" v-model="menuAdd.form.showNav"></el-switch>
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
      mapModules({menuAdd: 'sys-menu-add', moduleNames: 'sys-module-names', menuNames: 'sys-menu-names'}),
      mapRules({need: 'sys-module-names', temp: 'sys-menu-add'})
    ],
    methods: {
      onSubmit() {
        this.$validator.validateAll().then(result => {
          if (result) {
            this.menuAdd.save().then((res) => {
              this.$message(messages.messageSaveSuccess())
              // 跳转至编辑画面
              let id = res.id
              this.$router.push(`/sys/menu/edit/${id}`)
              // this.menuAdd.reset()
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
      moduleChanged(nid) {
        // 模块切换，重新查询父画面（一级）
        if (nid != null) {
          this.menuNames.getMenus(nid)
        }
      }
    }
  }
</script>

<style scoped>

</style>
