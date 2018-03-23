<template>
  <div>
    <div class="txt-left txt-b-info">你正在编辑：{{menuEdit.form.name}}</div>
    <el-tabs v-model="activeName" type="border-card" tab-position="left" @tab-click="handleClick">
      <el-tab-pane label="基本属性" name="basic">
        <el-form ref="menuEdit" :model="menuEdit.form" label-width="150px" class="container-form__mid">
          <el-form-item label="所属模块" :error="errors.first('module')">
            <el-select clearable v-model="menuEdit.form.moduleId" placeholder="请选择" data-vv-name="module"
                       v-validate data-vv-rules="required" @change="moduleChanged">
              <el-option v-for="item in moduleNames.list"
                         :label="item.name" :value="item.code" :key="item.code">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="父画面">
            <el-select clearable v-model="menuEdit.form.parentId" placeholder="请选择" >
              <el-option v-for="item in menuNames.list"
                         :label="item.name" :value="item.code" :key="item.code">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="画面名称" :error="errors.first('menuName')">
            <el-input v-model="menuEdit.form.name" data-vv-name="menuName" v-validate
                      data-vv-rules="required|max:20"></el-input>
          </el-form-item>
          <el-form-item label="画面类型" :error="errors.first('menuType')">
            <el-radio-group size="small" v-model="menuEdit.form.type" data-vv-name="menuType" v-validate
                            data-vv-rules="required">
              <el-radio-button :label="1">菜单</el-radio-button>
              <el-radio-button :label="2">画面</el-radio-button>
              <el-radio-button :label="3">菜单+画面</el-radio-button>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="画面路径" :error="errors.first('menuPath')">
            <el-input v-model="menuEdit.form.path" data-vv-name="menuPath" v-validate
                      data-vv-rules="required|max:120"></el-input>
          </el-form-item>
          <el-form-item label="画面组件" :error="errors.first('menuComponent')">
            <el-input v-model="menuEdit.form.component" data-vv-name="menuComponent" v-validate
                      data-vv-rules="required|max:120"></el-input>
          </el-form-item>

          <el-form-item label="画面是否启用">
            <el-switch on-text="Yes" off-text="No" :inactive-value="0" :active-value="1" v-model="menuEdit.form.enabled"></el-switch>
          </el-form-item>

          <el-form-item label="导航是否启用">
            <el-switch on-text="Yes" off-text="No" :inactive-value="0" :active-value="1" v-model="menuEdit.form.showNav"></el-switch>
          </el-form-item>
          <el-form-item>
            <el-button native-type="submit" type="primary" size="small" @click.native.prevent="onSubmit">保存</el-button>
            <el-button size="small" @click.native.prevent="$router.back()">取消</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="后台接口" name="api">
        <div class="txt-left txt-small">
          维护画面所对应的接口，这样在管理画面权限时，直接能方便的找到当前画面所使用的接口，更方便给角色授权。
        </div>
        <!--<div>-->
          <!--<ul>-->
            <!--<ol v-for="item in aclList.list">{{item.id}}  {{item.name}}</ol>-->
          <!--</ul>-->
        <!--</div>-->
        <el-transfer v-model="menuEdit.apis" filterable :props="{key: 'id',label: 'name'}" :data="aclList.list" >
        </el-transfer>
        <div>&nbsp;</div>
        <div class="txt-center">
            <el-button native-type="submit" size="small" type="primary" @click="saveApis()">保存</el-button>
        </div>
      </el-tab-pane>
      <el-tab-pane label="画面片段" name="segment">
        <div class="txt-left txt-small">
          维护画面所对应的组件或片段，这样在管理画面权限时，方便给不同角色授权与画面不同内容的权限。
        </div>
        <el-table :data="menuEdit.segments" empty-text="没有您要查询的数据"
               size="small" border stripe>
          <el-table-column prop="id" label="ID" align="center" width="95">
          </el-table-column>
          <el-table-column prop="segmentId" label="画面片段ID" align="left" min-width="100">
          </el-table-column>
          <el-table-column prop="name" label="画面片段名" align="left" min-width="180">
          </el-table-column>
          <el-table-column label="操作" align="center" width="200" fixed="right">
            <template slot-scope="scope">
              <el-button-group>
                <el-button type="danger" size="mini" @click="removeSegment(scope.row.id, scope.$index)">删除</el-button>
              </el-button-group>
            </template>
          </el-table-column>
        </el-table>
        <el-form :inline="true" size="small" :model="segment" class="segment-form-inline">
          <el-form-item label="画面片段ID">
            <el-input v-model="segment.id" placeholder="画面片段ID"></el-input>
          </el-form-item>
          <el-form-item label="画面片段名">
            <el-input v-model="segment.name" placeholder="画面片段名"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" size="small" @click="saveSegment">追加</el-button>
          </el-form-item>
        </el-form>

      </el-tab-pane>
    </el-tabs>

  </div>
</template>

<script>
import {mapModules, mapRules} from 'vuet'
import {messages} from '@/common'
export default {
  name: 'edit',
  mixins: [
    mapModules({
      menuEdit: 'sys-menu-edit',
      moduleNames: 'sys-module-names',
      menuNames: 'sys-menu-names',
      aclList: 'sys-acl-list'
    }),
    mapRules({need: 'sys-module-names', temp: 'sys-menu-edit', route: 'sys-acl-list'})
  ],
  data() {
    return {
      activeName: 'basic',
      segment: {}
    }
  },
  mounted () {
  },
  methods: {
    onSubmit () {
      this.$validator.validateAll().then(result => {
        if (result) {
          this.menuEdit.save().then((res) => {
            this.$message(messages.messageSaveSuccess())
            this.menuEdit.reset()
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
        this.menuNames.getMenus(nid).then(apis => {
          // 选中行
        })
      }
    },
    handleClick(tab, event) {
      if (this.activeName === 'api') {
        this.menuEdit.loadApis()
      } else if (this.activeName === 'segment') {
        this.menuEdit.loadSegments()
      }
    },
    saveApis() {
      if (this.menuEdit.apis !== null && this.menuEdit.apis.length > 20) {
        // TODO 单画面禁止保存超过20个接口，以避免在授权时的体验问题
        this.$notify({
          title: '警告',
          message: '不要选择超过20个接口，以免后续授权时产生混淆',
          type: 'warning',
          duration: 2500
        })
      } else {
        this.menuEdit.saveApis()
      }
    },
    saveSegment() {
      this.menuEdit.addSegment(this.segment.id, this.segment.name).then(() => this.segment.reset())
    },
    removeSegment(id, index) {
      this.menuEdit.removeSegment(id, index)
    }
  }
}
</script>

<style scoped>
  .segment-form-inline{
    margin-top: 15px
  }
</style>
