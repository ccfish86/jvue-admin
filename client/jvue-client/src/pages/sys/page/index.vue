<template>
    <div>
      <el-row class="actions-top--edit" type="flex" justify="end">
        <el-col :span="2" :pull="2">
          <el-button type="success" icon="el-icon-document" size="small"
                     @click="$router.push('/sys/page/add')">添加
          </el-button>
        </el-col>
      </el-row>
      <el-table :data="pageList.list" v-loading="pageList.loading" empty-text="没有您要查询的数据"
                size="small" border stripe>
        <el-table-column prop="id" label="ID" align="center" width="95">
        </el-table-column>
        <el-table-column prop="name" label="画面名" align="left" min-width="120">
        </el-table-column>
        <el-table-column prop="component" label="组件名" align="left" min-width="140">
        </el-table-column>
        <el-table-column prop="path" label="路径" align="left" min-width="180">
        </el-table-column>
        <el-table-column prop="moduleId" label="模块名" align="left" min-width="140">
          <template slot-scope="scope">{{scope.row.moduleId|moduleName}}</template>
        </el-table-column>
        <el-table-column prop="enabled" label="是否启用" align="left" min-width="100">
          <template slot-scope="scope">{{scope.row.enabled|enumName('yn')}}</template>
        </el-table-column>
        <el-table-column prop="showNav" label="是否导航" align="left" min-width="100">
          <template slot-scope="scope">{{scope.row.showNav|enumName('yn')}}</template>
        </el-table-column>

        <el-table-column label="操作" align="center" width="200" fixed="right">
          <template slot-scope="scope">
            <el-button-group>
              <el-button type="success" size="mini" @click="edit(scope.row.id)">编辑</el-button>
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
                         :current-page="pageList.searchForm.page"
                         :page-sizes="[10, 20, 50, 100, 200, 400]"
                         :page-size="pageList.searchForm.pageSize"
                         :total="pageList.searchForm.totalCount">
          </el-pagination>
        </el-col>
      </el-row>
    </div>
</template>

<script>
import {mapModules, mapRules} from 'vuet'
import filters from '../common/filters'
export default {
  name: 'index',
  mixins: [
    mapModules({pageList: 'sys-page-list'}),
    mapRules({route: 'sys-page-list', need: 'sys-module-names'})
  ],
  filters: {
    moduleName: (moduleId) => {
      return filters.moduleName(moduleId)
    }
  },
  methods: {
    handleSizeChange (newSize) {
      this.pageList.searchForm.pageSize = newSize
      this.pageList.fetch()
    },
    handleCurrentChange (newPage) {
      this.pageList.searchForm.page = newPage
      this.pageList.fetch()
    },
    showDetail (id) {
      this.$router.push(`/sys/page/detail/${id}`)
    },
    edit (id) {
      this.$router.push(`/sys/page/edit/${id}`)
    },
    remove (id) {
      this.$confirm('删除画面可能无法恢复, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.pageList.remove(id).then(() => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          this.pageList.fetch()
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
