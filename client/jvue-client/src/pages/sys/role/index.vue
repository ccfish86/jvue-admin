<template>
  <div>
    <el-row class="actions-top--edit" type="flex" justify="end">
      <el-col :span="2" :pull="2">
        <v-sec :code="1">
          <el-button type="success" icon="el-icon-document" size="small"
                     @click="$router.push('/sys/role/add')">添加
          </el-button>
        </v-sec>
      </el-col>
    </el-row>
    <el-table :data="roleList.list" v-loading="roleList.loading" empty-text="没有您要查询的数据"
              size="small" border stripe>
      <el-table-column prop="id" label="ID" align="center" width="95">
      </el-table-column>
      <el-table-column prop="name" label="角色名" align="left" min-width="120">
      </el-table-column>
      <el-table-column prop="enabled" label="是否启用" align="left" width="100">
        <template slot-scope="scope">{{scope.row.enabled|enumName('yn')}}</template>
      </el-table-column>

      <el-table-column label="操作" align="center" width="250" fixed="right">
        <template slot-scope="scope">
          <el-button-group>
            <el-button type="primary" size="mini" @click="grant(scope.row.id)">授权</el-button>
            <el-button type="success" size="mini" @click="edit(scope.row.id)">编辑</el-button>
            <el-button type="warning" size="mini" @click="unable(scope.row.id)" v-if="scope.row.enabled === 1">禁用</el-button>
            <el-button size="mini" @click="enable(scope.row.id)" v-else>启用</el-button>
            <el-button type="danger" size="mini" @click="remove(scope.row.id)">删除</el-button>
          </el-button-group>
        </template>
      </el-table-column>
    </el-table>
    <el-row type="flex" class="container-table__footer" justify="end">
      <el-col :span="12" class="container-table__footer__pagination">
        <el-pagination layout="total, sizes, prev, pager, next, jumper" background
                       @size-change="handleSizeChange"
                       @current-change="handleCurrentChange"
                       :current-page="roleList.searchForm.page"
                       :page-sizes="[10, 20, 50, 100, 200, 400]"
                       :page-size="roleList.searchForm.pageSize"
                       :total="roleList.searchForm.totalCount">
        </el-pagination>
      </el-col>
    </el-row>
  </div>
</template>

<script>
// import {messages} from '@/common'
import {mapModules, mapRules} from 'vuet'
export default {
  name: 'index',
  mixins: [
    mapModules({roleList: 'sys-role-list'}),
    mapRules({route: 'sys-role-list'})
  ],
  methods: {
    handleSizeChange (newSize) {
      this.roleList.searchForm.pageSize = newSize
      this.roleList.fetch()
    },
    handleCurrentChange (newPage) {
      this.roleList.searchForm.page = newPage
      this.roleList.fetch()
    },
    grant (id) {
      this.$router.push(`/sys/role/grant/${id}`)
    },
    edit (id) {
      this.$router.push(`/sys/role/edit/${id}`)
    },
    enable (id) {
      this.roleList.toggleEnable(id, 1)
    },
    unable (id) {
      this.roleList.toggleEnable(id, 0)
    },
    remove (id) {
      this.$confirm('删除角色可能无法恢复, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.roleList.remove(id).then(() => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          this.roleList.fetch()
        }).catch((err) => {
          this.$notify({
            title: '警告',
            message: err,
            type: 'warning',
            duration: 2500
          })
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    }
  }
}
</script>

<style scoped>

</style>
