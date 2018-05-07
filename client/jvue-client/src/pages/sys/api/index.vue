<template>
  <div>

    <el-table :data="apiList.list" v-loading="apiList.loading" empty-text="没有您要查询的数据"
              size="small" border stripe>
      <el-table-column prop="id" label="ID" align="center" width="95">
      </el-table-column>
      <el-table-column prop="pageId" label="画面ID" align="left" min-width="70">
      </el-table-column>
      <el-table-column prop="pageId" label="画面名" align="left" min-width="120">
        <template slot-scope="scope">{{scope.row.pageId|pageName}}</template>
      </el-table-column>
      <el-table-column prop="apiCode" label="接口ID" align="left" min-width="70">
      </el-table-column>
      <el-table-column prop="name" label="接口名" align="left" min-width="120">
      </el-table-column>
    </el-table>

    <el-row type="flex" class="container-table__footer" justify="end">
      <el-col :span="12" class="container-table__footer__pagination">
        <el-pagination layout="total, sizes, prev, pager, next, jumper" background
                       @size-change="handleSizeChange"
                       @current-change="handleCurrentChange"
                       :current-page="apiList.searchForm.page"
                       :page-sizes="[10, 20, 50, 100, 200, 400]"
                       :page-size="apiList.searchForm.pageSize"
                       :total="apiList.searchForm.totalCount">
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
    mapModules({apiList: 'sys-api-list'}),
    mapRules({route: 'sys-api-list', need: 'sys-page-names'})
  ],
  filters: {
    pageName: (pageId) => {
      return filters.pageName(pageId)
    }
  },
  methods: {
    handleSizeChange (newSize) {
      this.apiList.searchForm.pageSize = newSize
      this.apiList.fetch()
    },
    handleCurrentChange (newPage) {
      this.apiList.searchForm.page = newPage
      this.apiList.fetch()
    },
    showDetail (id) {
      this.$router.push(`/sys/api/detail/${id}`)
    },
    edit (id) {
      this.$router.push(`/sys/api/edit/${id}`)
    }
  }
}
</script>

<style scoped>

</style>
