<template>
  <div style="padding: 10px;">

    <div style="margin: 10px 0;display: flex;">
      <el-input v-model="search" placeholder="请输入" style="width: 20%;" clearable/>
      <el-button type="primary" style="margin-left: 5px" @click="lode">查询</el-button>
    </div>

    <el-table :data="tableData" border stripe style="width: 100%" @selection-change="handleSelectionChange">

      <el-table-column prop="accountId" label="ID" sortable/>
      <el-table-column prop="certificationImg" label="营业执照" width="150" height="300">
        <template v-slot:default="scope">
          <el-image :src="scope.row.certificationImg"/>
        </template>
      </el-table-column>
      <el-table-column prop="certificationImg" label="认证图片1" width="150" height="300">
        <template v-slot:default="scope">
          <el-image :src="scope.row.img1"/>
        </template>
      </el-table-column>
      <el-table-column prop="certificationImg" label="认证图片2" width="150" height="300">
        <template v-slot:default="scope">
          <el-image  v-if="scope.row.img2" :src="scope.row.img2"/>
          <div v-if="!scope.row.img2">未上传</div>
        </template>
      </el-table-column>
      <el-table-column prop="certificationImg" label="认证图片3" width="150" height="300">
        <template v-slot:default="scope">
          <el-image v-if="scope.row.img3" :src="scope.row.img3"/>
          <div v-if="!scope.row.img3">未上传</div>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间"/>
      <el-table-column prop="updateTime" label="修改时间"/>
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-popconfirm title="确认同意吗?" @confirm="handleEdit(scope.row)">
            <template #reference>
              <el-button type="primary" icon="el-icon-edit" circle></el-button>
            </template>
          </el-popconfirm>

          <el-popconfirm title="确认拒绝吗?" @confirm="handleDelete(scope.row.registerId)">
            <template #reference>
              <el-button type="danger" icon="el-icon-delete" circle></el-button>
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
    handleSelectionChange(val){
      this.ids = val.map(v => v.customerId)
    },
    lode() {
      request.get("/register/page", {
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
    handleEdit(row) {
      request.put("/register/agreeAccount",row).then(res => {
        if (res.code === 200) {
          ElMessage({
            type: 'success',
            message: '通过成功',
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
    handleDelete(registerId) {
      request.delete("/register/delete/" + registerId).then(res => {
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
