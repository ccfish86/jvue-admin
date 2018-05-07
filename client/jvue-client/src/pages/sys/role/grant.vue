<template>
  <div>
    <div>
      <div>角色名：{{roleDetail.detail.name}}</div>
      <div>是否启用：{{roleDetail.detail.enabled|enumName('yn')}}</div>
    </div>
    <el-radio-group v-model="roleGrant.currentModule" @change="roleGrant.changeModule">
      <el-radio-button v-for="m of moduleNames.list" :key="m.id" :label="m.code" >{{m.name}}</el-radio-button>
    </el-radio-group>
    处理角色授权
    <el-form ref="roleGrant" :model="roleGrant.form" label-width="150px" >
        <template v-for="(page, index) of roleGrant.currentPages">
          <el-row :key="index">
            <el-col :span="3">
              <div>{{page.name}}</div>
            </el-col>
            <el-col :span="21" >
              <el-row>
              <el-col :span="24" v-for="spage of page.children" :key="spage.id">
                <!--<el-checkbox-group v-model="roleGrant.form.pages" @change="handleCheckedCitiesChange">-->
                  <!--<el-checkbox v-for="city in cities" :label="city" :key="city">{{city}}</el-checkbox>-->
                <!--</el-checkbox-group>-->
                <el-checkbox :label="spage.name" v-model="spage.checked"></el-checkbox>
                <el-row v-if="roleGrant.allRoleInfo.apis[spage.id]">
                  <el-col :span="1">
                    <div class="txt-small">API</div>
                  </el-col>
                  <el-col :span="23">
                    <el-checkbox v-for="api of roleGrant.allRoleInfo.apis[spage.id]" :key="api.id" :label="api.name" v-model="api.checked"></el-checkbox>
                  </el-col>
                </el-row>

                <el-row v-if="roleGrant.allRoleInfo.segments[spage.id]">
                  <el-col :span="1">
                    <div class="txt-small">片段 </div>
                  </el-col>
                  <el-col :span="23">
                    <el-checkbox v-for="segment of roleGrant.allRoleInfo.segments[spage.id]" :key="segment.id" :label="segment.name" v-model="segment.checked"></el-checkbox>
                  </el-col>
                </el-row>
              </el-col>
              </el-row>
            </el-col>
          </el-row>

        </template>
      <!--<el-tree :data="roleGrant.currentPages" :props="{children: 'children',label: 'name'}">-->

      <!--</el-tree>-->

      <el-form-item>
        <el-button native-type="submit" type="primary" @click.native.prevent="onSubmit">保存</el-button>
        <el-button @click.native.prevent="$router.back()">取消</el-button>
      </el-form-item>
    </el-form>
    <!--{{roleGrant.currentPages}}<br>-->
    <!--{{roleGrant.roleInfo}}<br>-->
    <br>
    菜单树
    <br>
    菜单对应的API/画面片段
    <br>
    初始显示已经授予过的权限，未授予的权限皆活性化
  </div>
</template>

<script>
import {mapModules, mapRules} from 'vuet'
import {messages} from '@/common'
export default {
  name: 'grant',
  mixins: [
    mapModules({
      moduleNames: 'sys-module-names',
      pageNames: 'sys-page-names',
      aclList: 'sys-acl-list',
      roleGrant: 'sys-role-grant',
      roleDetail: 'sys-role-detail'
    }),
    mapRules({need: 'sys-module-names', route: ['sys-acl-list', 'sys-role-grant', 'sys-role-detail']})
  ],
  data () {
    return {
      currentModule: `1`,
      currentPages: []
    }
  },
  mounted () {
  },
  methods: {
    onSubmit () {
      this.roleGrant.save().then(result => {
        this.$message(messages.messageSaveSuccess())
      }).catch(err => {
        this.$notify({
          title: '警告',
          message: err,
          type: 'warning',
          duration: 2500
        })
      })
    }
  }
}
</script>

<style scoped>

</style>
