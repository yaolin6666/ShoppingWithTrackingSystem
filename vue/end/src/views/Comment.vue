<template>
  <div style="padding: 10px;">
    <div style="margin: 10px 0;display: flex;">
      <el-input v-model="search" placeholder="请输入" style="width: 20%;" clearable/>
      <el-button type="primary" style="margin-left: 5px" @click="lode">查询</el-button>
      <el-popconfirm title="确认删除吗?" @confirm="deleteBatch">
        <template #reference>
          <el-button type="danger">批量删除</el-button>
        </template>
      </el-popconfirm>
    </div>

    <el-table :data="tableData" border stripe style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"/>
      <el-table-column prop="commentId" label="ID" sortable width="60"/>
      <el-table-column prop="productId" label="商品ID" sortable width="85"/>
      <el-table-column label="评论人头像">
        <template #default="scope">
          <el-image
              style="width: 100px; height: 100px"
              :src="scope.row.avatar"
              :preview-src-list="[scope.row.avatar]"
          >
          </el-image>
        </template>
      </el-table-column>
      <el-table-column prop="customerName" label="用户昵称"/>
      <el-table-column prop="commentNr" label="评论内容" width="200"/>
      <el-table-column label="评论晒图">
        <template #default="scope">
          <el-image style="width: 100px; height: 100px" :src="scope.row.commentPic" :preview-src-list="[scope.row.commentPic]">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column prop="commentPf" label="商品评分">
        <template #default="scope">
          <el-rate v-model="scope.row.commentPf" disabled :label-width="formLabelWidth"></el-rate>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间"/>
      <el-table-column prop="updateTime" label="修改时间"/>
      <el-table-column label="操作" width="150" align="center">
        <template #default="scope">
          <el-button icon="el-icon-edit" type="primary" circle @click="handleEdit(scope.row)"></el-button>
          <el-popconfirm title="确认删除吗?" @confirm="handleDelete(scope.row.commentId)">
            <template #reference>
              <el-button type="danger" icon="el-icon-delete" circle></el-button>
            </template>
          </el-popconfirm>

        </template>
      </el-table-column>
    </el-table>

    <div style="text-align: center;margin:20px 60px 0 0">
      <el-pagination v-model:currentPage="currentPage" :page-sizes="[5, 10, 20, 30]" :page-size="pageSize"
                     layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange"
                     @current-change="handleCurrentChange">
      </el-pagination>
    </div>

    <el-dialog v-model="dialogVisible" title="修改" width="30%">
      <el-form :model="form" label-width="120px">
        <el-form-item label="评论内容">
          <el-input v-model="form.commentNr"></el-input>
        </el-form-item>
        <el-form-item label="评论图片">
          <el-upload ref="uplode"
                     action="http://localhost:8888/files/uplode"
                     :on-success="filesUplodeSeccess"
          >
            <el-button size="small" type="primary">上传</el-button>
            <template #tip>
              <div class="el-upload__tip">
                只能上传jpg/png文件且文件大小不超过1MB
              </div>
            </template>
          </el-upload>
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
</template>

<script>

import request from "@/utils/request.js"
import {ElMessage} from 'element-plus'

export default {
  name: 'Commnet',

  components: {},
  data() {
    return {
      id: '',
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
    this.id = JSON.parse(sessionStorage.getItem('userInfo')).id;
    this.lode()
  },
  methods: {
    deleteBatch() {
      if (!this.ids.length) {
        ElMessage({
          type: "warning",
          message: "请先进行选择"
        })
        return
      }
      request.post("/comment/deleteBatch", this.ids).then(res => {
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
    handleSelectionChange(val) {
      this.ids = val.map(v => v.commentId)
    },
    filesUplodeSeccess(res) {
      console.log(res)
      this.form.avatar = res.data

    },
    lode() {
      request.get("/comment/page", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search,
          shopId: this.id
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
      if (this.$refs['upload']) {
        this.$refs['upload'].clearFiles()  // 清除历史文件列表
      }

    },
    save() {
      if (this.form.commentId) {
        request.put("/comment", this.form).then(res => {
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
        request.post("/comment", this.form).then(res => {
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
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
      this.$nextTick(() => {
        this.$refs['uplode'].clearFiles()
      })


    },
    handleDelete(commentId) {

      request.delete("/comment/" + commentId).then(res => {
        console.log(commentId);
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
