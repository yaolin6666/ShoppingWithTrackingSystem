<template>
  <div style="padding: 10px;">

    <div style="margin: 10px 0;display: flex;">
      <el-input v-model="search" placeholder="请输入" style="width: 20%;" clearable/>
      <el-button type="primary" style="margin-left: 5px" @click="lode">查询</el-button>
    </div>

    <el-table :data="tableData" border stripe style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" />
      <el-table-column prop="accountId" label="ID" sortable/>
      <el-table-column prop="username" label="用户名"/>
      <el-table-column label="角色">
        <template #default="scope">
          <span v-if="scope.row.role === 0">用户</span>
          <span v-if="scope.row.role === 1">管理员</span>
          <span v-if="scope.row.role === 2">商家(待审核)</span>
          <span v-if="scope.row.role === 3">商家</span>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间"/>
      <el-table-column prop="updateTime" label="修改时间"/>
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-popconfirm v-if="scope.row.role==3" title="确认封禁吗?" @confirm="handleEdit(scope.row.accountId)">
            <template #reference>
              <el-button type="danger"  circle>封禁账号</el-button>
            </template>
          </el-popconfirm>
          <el-popconfirm v-if="scope.row.role==2" title="确认删除吗?" @confirm="handleDelete(scope.row.accountId)">
            <template #reference>
              <el-button type="danger"  circle>删除账号</el-button>
            </template>
          </el-popconfirm>

        </template>
      </el-table-column>
    </el-table>
    <div style="margin: 20px 0;text-align: center">
      <el-pagination v-model:currentPage="currentPage" :page-sizes="[5, 10, 20, 30]" :page-size="pageSize"
                     layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange"
                     @current-change="handleCurrentChange">
      </el-pagination>

      <el-dialog v-model="dialogVisible" title="" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="用户名">
            <el-input v-model="form.username"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="form.password"></el-input>
          </el-form-item>
          <el-form-item label="角色">
            <el-radio v-model="form.role" label="0" style="padding-left: 50px">用户</el-radio>
            <el-radio v-model="form.role" label="1">管理员</el-radio>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="save">确定</el-button>
          </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>

import request from "@/utils/request.js"
import {ElMessage} from 'element-plus'

export default {
  name: 'User',

  components: {},
  data() {
    return {
      form: {},
      dialogVisible: false,
      search: '',
      currentPage: 1,
      pageSize: 5,
      total: 0,
      tableData: [],
      ids: [],
    }
  },
  created() {
    this.lode()
  },
  mounted() {

  },

  methods: {
    deleteBatch(){
      if (!this.ids.length){
        ElMessage({
          type:"warning",
          message:"请先进行选择"
        })
        return
      }
      request.post("/account/deleteBatch",this.ids).then(res => {
        if (res.code === 200) {
          ElMessage({
            type: 'success',
            message: '批量删除成功',
          })
          this.lode()
        } else {
          ElMessage({
            type: 'error',
            message: res.msg
          })
        }
      })
    },
    handleSelectionChange(val){
      this.ids = val.map(v => v.customerId)
    },
    lode() {
      request.get("/account/pageShop", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search,
        }
      }).then(res => {
        console.log(res);
        this.tableData = res.data.records
        this.total = res.data.total
      })

    },
    add() {
      this.dialogVisible = true
      this.form = {}

    },
    save() {
      if (this.form.customerId) {
        request.put("/account/update", this.form).then(res => {
          console.log(res);
          if (res.code === 200) {
            ElMessage({
              type: 'success',
              message: '修改成功',
            })
          } else {
            ElMessage({
              type: 'error',
              message: res.msg
            })
          }
          this.lode()
          this.dialogVisible = false
        })
      } else {
        request.post("/account", this.form).then(res => {
          console.log(res);
          if (res.code === 200) {
            ElMessage({
              type: 'success',
              message: '添加成功',
            })
          } else {
            ElMessage({
              type: 'error',
              message: res.msg
            })
          }
          this.lode()
          this.dialogVisible = false
        })
      }

    },
    handleEdit(customerId) {
      request.put("/account/" + customerId).then(res => {
        console.log(customerId);
        if (res.code === 200) {
          ElMessage({
            type: 'success',
            message: '删除成功',
          })
        } else {
          ElMessage({
            type: 'error',
            message: res.msg
          })
        }
        this.lode()
      })

    },
    handleDelete(customerId) {
      request.delete("/account/" + customerId).then(res => {
        console.log(customerId);
        if (res.code === 200) {
          ElMessage({
            type: 'success',
            message: '删除成功',
          })
        } else {
          ElMessage({
            type: 'error',
            message: res.msg
          })
        }
        this.lode()
      })

    },
    handleSizeChange(pageSize) {//改变每页的个数触发
      this.pageSize = pageSize
      this.lode()

    },
    handleCurrentChange(pageNum) {//改变当前页码触发
      this.currentPage = pageNum
      this.lode()

    },
  },
}
</script>
