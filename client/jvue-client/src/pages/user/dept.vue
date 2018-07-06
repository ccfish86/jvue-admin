<template>
  <div>
    <el-row class="actions-top--edit" type="flex" justify="end">
      <el-col :span="2" :pull="2">
        <el-button type="success" icon="el-icon-document" size="small"
                   @click="add">添加
        </el-button>
      </el-col>
    </el-row>

    <el-table :data="userDept.list" :default-sort="{prop: 'code', order: ''}" sortable="custom" @sort-change="sortChanged"
              v-loading="userDept.loading" empty-text="没有您要查询的数据"
              size="small" border stripe>
      <el-table-column prop="id" label="ID" align="center" width="95" sortable>
      </el-table-column>
      <el-table-column prop="code" label="编码" align="left" min-width="60" sortable>
      </el-table-column>
      <el-table-column prop="name" label="名称" align="left" min-width="100" sortable>
      </el-table-column>
      <el-table-column prop="level" label="层级" align="left" min-width="60" sortable>
      </el-table-column>

      <el-table-column label="操作" align="center" width="250" fixed="right">
        <template slot-scope="scope">
          <el-button-group>
            <el-button type="primary" size="mini" @click="addChild(scope.row)" v-show="scope.row.level< 6">加子部门</el-button>
            <el-button type="success" size="mini" @click="edit(scope.row)">编辑</el-button>
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
                       :current-page="userDept.searchForm.page"
                       :page-sizes="[10, 20, 50, 100, 200, 400]"
                       :page-size="userDept.searchForm.pageSize"
                       :total="userDept.searchForm.totalCount">
        </el-pagination>
      </el-col>
    </el-row>

    <!-- 添加-编辑 -->
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible">
      <el-form :model="form">
        <el-form-item label="编码" :label-width="formLabelWidth" :error="errors.first('dialog.code')" >
          <el-input v-model="form.code" :maxlength="6" auto-complete="off" size="small" data-vv-name="code" data-vv-scope="dialog"
                    v-validate="'required|max:3'">
            <template slot="prepend" v-if="form.parentCode">{{form.parentCode}}</template>
          </el-input>
        </el-form-item>
        <el-form-item label="名称" :label-width="formLabelWidth" :error="errors.first('dialog.name')" >
          <el-input v-model="form.name" :maxlength="16" auto-complete="off" size="small" data-vv-name="name" data-vv-scope="dialog"
                    v-validate="'required'"></el-input>
        </el-form-item>
        <el-form-item label="父标签" :label-width="formLabelWidth" >
          <el-select clearable v-model="form.parentCode" placeholder="请选择"  >
            <el-option v-for="item in userDept.parentCodes" :key="item.code"
                       :label="item.name" :value="item.code">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="save()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {messages, utils} from '@/common'
  import {mapModules} from 'vuet'
  export default {
    name: 'index',
    mixins: [
      mapModules({userDept: 'user-dept-list'})
    ],
    data () {
      return {
        formLabelWidth: '120px',
        dialogVisible: false,
        dialogTitle: '',
        form: {
          position: 0
        }
      }
    },
    mounted () {
      this.userDept.search()
    },
    methods: {
      handleSizeChange (newSize) {
        this.userDept.searchForm.pageSize = newSize
        this.userDept.search()
      },
      handleCurrentChange (newPage) {
        this.userDept.searchForm.page = newPage
        this.userDept.search()
      },
      changeFormType (type) {
        if (type) {
          this.form.parentCode = null
          this.userDept.getParentCodes()
        }
      },
      add () {
        this.form = {
          position: 0,
          type: Number(this.userDept.searchForm.type)
        }
        this.errors.clear('dialog')
        this.dialogTitle = '追加'
        this.dialogVisible = true
        this.userDept.getParentCodes()
      },
      addChild (item) {
        this.form = {
          position: 0,
          type: item.type,
          parentCode: item.code
        }
        this.errors.clear('dialog')
        this.dialogTitle = '追加'
        this.dialogVisible = true
        this.userDept.getParentCodes(item.type)
      },
      save () {
        this.$validator.validate('dialog.*').then(result => {
          if (result) {
            if (this.form.id) {
              // 编辑
              this.userDept.save(this.form.id, this.form).then(res => {
                this.$message(messages.messageSaveSuccess())
                this.dialogVisible = false
                this.userDept.search()
              })
            } else {
              // 追加
              this.userDept.add(this.form).then(res => {
                this.$message(messages.messageSaveSuccess())
                this.dialogVisible = false
                this.userDept.search()
              })
            }
          }
        }).catch(err => {
          console.debug(err)
          this.$notify(messages.notifyCheckError())
        })
      },
      edit (item) {
        this.errors.clear('dialog')
        this.form = Object.assign({}, item)
        if (item.level > 1) {
          this.form.code = this.form.code.substr(3* (item.level - 1) , 3)
        }
        this.dialogTitle = '编辑'
        this.dialogVisible = true
        this.userDept.getParentCodes(item.type)
      },
      remove (id) {
        this.$confirm('删除数据可能无法恢复, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.userDept.remove(id).then(() => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            this.userDept.search()
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
      },
      sortChanged ({ prop, order }) {
        let { sort, direction } = utils.sortInfo(prop, order)
        Object.assign(this.userDept.searchForm, {
          sort: sort,
          direction: direction
        })
        this.userDept.fetch()
      }
    }
  }
</script>

<style scoped>

</style>
