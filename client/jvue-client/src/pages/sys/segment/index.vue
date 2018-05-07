<template>
  <div>

    <el-table :data="segmentList.list" v-loading="segmentList.loading" empty-text="没有您要查询的数据"
              size="small" border stripe>
      <el-table-column prop="id" label="ID" align="center" width="95">
      </el-table-column>
      <el-table-column prop="pageId" label="画面ID" align="left" min-width="70">
      </el-table-column>
      <el-table-column prop="pageId" label="画面名" align="left" min-width="120">
        <template slot-scope="scope">{{scope.row.pageId|pageName}}</template>
      </el-table-column>
      <el-table-column prop="segmentCode" label="画面片段ID" align="left" min-width="70">
      </el-table-column>
      <el-table-column prop="name" label="画面片段名" align="left" min-width="120">
      </el-table-column>
    </el-table>

    <el-row type="flex" class="container-table__footer" justify="end">
      <el-col :span="12" class="container-table__footer__pagination">
        <el-pagination layout="total, sizes, prev, pager, next, jumper" background
                       @size-change="handleSizeChange"
                       @current-change="handleCurrentChange"
                       :current-page="segmentList.searchForm.page"
                       :page-sizes="[10, 20, 50, 100, 200, 400]"
                       :page-size="segmentList.searchForm.pageSize"
                       :total="segmentList.searchForm.totalCount">
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
    mapModules({segmentList: 'sys-segment-list'}),
    mapRules({route: 'sys-segment-list', need: 'sys-page-names'})
  ],
  filters: {
    pageName: (pageId) => {
      return filters.pageName(pageId)
    }
  },
  methods: {
    handleSizeChange (newSize) {
      this.segmentList.searchForm.pageSize = newSize
      this.segmentList.fetch()
    },
    handleCurrentChange (newPage) {
      this.segmentList.searchForm.page = newPage
      this.segmentList.fetch()
    },
    showDetail (id) {
      this.$router.push(`/sys/segment/detail/${id}`)
    },
    edit (id) {
      this.$router.push(`/sys/segment/edit/${id}`)
    }
  }
}
</script>

<style scoped>

</style>
