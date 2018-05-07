<template>
  <div>
    <el-row class="actions-top--edit" type="flex" justify="end">
      <el-col :span="2" :pull="2">
        <v-sec code="9999">

          <el-button type="success" icon="el-icon-document" size="small" @click="$router.push('/user/add')">添加
          </el-button>
        </v-sec>
      </el-col>
    </el-row>
    <el-table :data="userList.list" v-loading="userList.loading" empty-text="没有您要查询的数据" size="small" border stripe>
      <el-table-column type="expand" align="left">
        <template slot-scope="props">
          <el-row type="flex" class="table-expand__content">
            <el-col flex="left" :span="18" class="form-col__role">
              <el-checkbox-group v-model="props.row.roleIds">
                <el-checkbox size="small" v-for="item in roleNames.list" :label="item.code" :key="item.code">{{item.name}}</el-checkbox>
              </el-checkbox-group>
            </el-col>
            <el-col :span="4" :offset="1">
              <el-button-group>
                <el-button type="success" icon="el-icon-save" size="mini" @click.prevent="saveRole(props.row)" :disabled="props.row.status !== 1">保存
                </el-button>
                <!--<el-button type="danger" icon="el-icon-save" size="mini" @click.prevent="resetRole(props.row)"  :disabled="props.row.status !== 1">重置-->
                <!--</el-button>-->
              </el-button-group>
            </el-col>
          </el-row>
        </template>
      </el-table-column>
      <el-table-column prop="id" label="ID" align="center" width="95">
      </el-table-column>
      <el-table-column prop="username" label="登录名" align="left" min-width="100">
      </el-table-column>
      <el-table-column prop="nickname" label="用户名" align="left" min-width="100">
      </el-table-column>
      <el-table-column prop="email" label="邮件" align="left" min-width="100">
      </el-table-column>
      <el-table-column prop="roles" label="权限" align="left" min-width="100">
        <template slot-scope="scope">
          <el-tag v-for="role in scope.row.roles" :key="role">{{role.id|roleName}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="status" label="状态" align="left" width="100">
      </el-table-column>
      <el-table-column prop="superUser" label="是否管理员" align="left" width="100">
        <template slot-scope="scope">
          {{scope.row.superUser|enumName('yn')}}
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" width="150" fixed="right">
        <template slot-scope="scope">
          <el-button-group>
            <el-button type="primary" size="small" @click="showDetail(scope.row.id)">详情</el-button>
            <!--<el-button type="success" size="small" disabled @click="edit(scope.row.id)">编辑(开发中)</el-button>-->
            <el-button type="danger" size="small" @click="remove(scope.row.id)">删除</el-button>
          </el-button-group>
        </template>
      </el-table-column>
    </el-table>
    <el-row type="flex" class="container-table__footer" justify="end">
      <el-col :span="12" class="container-table__footer__pagination">
        <el-pagination layout="total, sizes, prev, pager, next, jumper" background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="userList.searchForm.page" :page-sizes="[10, 20, 50, 100, 200, 400]" :page-size="userList.searchForm.pageSize" :total="userList.searchForm.totalCount">
        </el-pagination>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { messages } from '@/common'
import { mapModules, mapRules } from 'vuet'
import vuet from '@/vuet'
export default {
  name: 'index',
  mixins: [
    mapModules({ userList: 'user-list', roleNames: 'sys-role-names' }),
    mapRules({ route: 'user-list', need: 'sys-role-names' })
  ],
  data () {
    return {}
  },
  filters: {
    roleName (role) {
      let roleNames = vuet.getModule('sys-role-names')
      if (roleNames) {
        for (let r of roleNames.list) {
          if (r.code === role) {
            return r.name
          }
        }
      }
      return '_' + role
    }
  },
  methods: {
    handleSizeChange (newSize) {
      this.userList.searchForm.pageSize = newSize
      this.userList.fetch()
    },
    handleCurrentChange (newPage) {
      this.userList.searchForm.page = newPage
      this.userList.fetch()
    },
    showDetail (id) {
      this.$router.push(`/sys/user/detail/${id}`)
    },
    edit (id) {
      this.$router.push(`/sys/user/edit/${id}`)
    },
    enable (id) {
      this.userList.toggleEnable(id, 1)
    },
    unable (id) {
      this.userList.toggleEnable(id, 0)
    },
    remove (id) {
      this.$confirm('删除用户可能无法恢复, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.userList
            .remove(id)
            .then(() => {
              this.$message({
                type: 'success',
                message: '删除成功!'
              })
              this.userList.fetch()
            })
            .catch(err => {
              this.$notify({
                title: '警告',
                message: err,
                type: 'warning',
                duration: 2500
              })
            })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    },
    resetRole (user) {
      console.info(user)
    },
    saveRole (user) {
      this.userList
        .saveRoles(user.id, user.roleIds)
        .then(res => {
          this.$message(messages.messageSaveSuccess())
        })
        .catch(err => {
          this.$notify({
            title: '警告',
            message: err,
            type: 'warning',
            duration: 2500
          })
        })
    },
    permit (user, role) {
      if (user.status !== 1) {
        return false
      }
      if (this.allowRoleMap) {
        return Object.keys(this.allowRoleMap).indexOf(role) < 0
      }
      return true
    }
  }
}
</script>

<style scoped>
.table-expand__content {
  text-align: start;
}
</style>
