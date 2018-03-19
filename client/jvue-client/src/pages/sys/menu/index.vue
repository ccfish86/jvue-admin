<template>
    <div>
      <el-row class="actions-top--edit" type="flex" justify="end">
        <el-col :span="2" :pull="2">
          <el-button type="success" icon="el-icon-document" size="small"
                     @click="$router.push({name: 'sys-menu-add'})">添加
          </el-button>
        </el-col>
      </el-row>
      <el-table :data="menuList.list" v-loading="menuList.loading" empty-text="没有您要查询的数据"
                size="small" border stripe>
        <el-table-column prop="id" label="ID" align="center" width="95">
        </el-table-column>
        <el-table-column prop="name" label="画面名" align="left" min-width="180">
        </el-table-column>
        <el-table-column prop="component" label="组件名" align="left" min-width="180">
        </el-table-column>
        <el-table-column prop="path" label="路径" align="left" min-width="180">
        </el-table-column>
        <el-table-column prop="moduleId" label="模块名" align="left" min-width="180">
          <template slot-scope="scope">{{scope.row.moduleId|moduleName}}</template>
        </el-table-column>
        <el-table-column prop="enabled" label="是否启用" align="left" min-width="100">
          <template slot-scope="scope">{{scope.row.enabled|enumName('yn')}}</template>
        </el-table-column>

        <el-table-column label="操作" align="center" width="200" fixed="right">
          <template slot-scope="scope">
            <el-button-group>
              <el-button type="primary" size="mini" @click="showDetail(scope.row.id)">详情</el-button>
              <el-button type="success" size="mini" @click="edit(scope.row.id)">编辑</el-button>
            </el-button-group>
          </template>
        </el-table-column>
      </el-table>

      <el-row type="flex" class="container-table__footer" justify="end">
        <el-col :span="12" class="container-table__footer__pagination">
          <el-pagination layout="total, sizes, prev, pager, next, jumper" background
                         @size-change="handleSizeChange"
                         @current-change="handleCurrentChange"
                         :current-page="menuList.searchForm.page"
                         :page-sizes="[1, 10, 50, 100, 200, 400]"
                         :page-size="menuList.searchForm.pageSize"
                         :total="menuList.searchForm.totalCount">
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
    mapModules({menuList: 'sys-menu-list'}),
    mapRules({route: 'sys-menu-list', need: 'sys-module-names'})
  ],
  filters: {
    moduleName: (moduleId) => {
      return filters.moduleName(moduleId)
    }
  },
  methods: {
    handleSizeChange (newSize) {
      this.menuList.searchForm.pageSize = newSize
      this.menuList.fetch()
    },
    handleCurrentChange (newPage) {
      this.menuList.searchForm.page = newPage
      this.menuList.fetch()
    },
    showDetail (id) {
      this.$router.push(`/sys/menu/detail/${id}`)
    },
    edit (id) {
      this.$router.push(`/sys/menu/edit/${id}`)
    }
  }
}
</script>

<style scoped>

</style>
