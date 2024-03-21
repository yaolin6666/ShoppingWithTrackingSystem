<template>
  <div class="userindex">

    <div style="margin: 10px 0;display: flex;">
      <el-input v-model="search" placeholder="请输入" style="width: 20%;" clearable/>
      <el-button type="primary" style="margin-left: 5px" @click="lode">查询</el-button>
      <el-popconfirm title="确认删除吗?" @confirm="deleteBatch">
        <template #reference>
          <el-button type="danger">批量删除</el-button>
        </template>
      </el-popconfirm>
    </div>
    <el-dialog title="" v-model="dialogVisible">
      <el-form :model="form" label-width="120px">
        <el-form-item label="反馈标题">
          <el-input v-model="form.feedbackBt"></el-input>
        </el-form-item>
        <el-form-item label="反馈内容">
          <el-input v-model="form.feedbackNy"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
            <span>
              <el-button type="primary" @click="onSubmit">确定</el-button>
              <el-button @click="dialogVisible = false">取 消</el-button>
            </span>
      </template>

    </el-dialog>
    <!-- 检索结果 -->
    <el-row :gutter="20" class="userindex-list">
      <el-col :span="24">
        <el-table :data="admin.filter(data => !search || data.feedbackBt.toLowerCase().includes(search.toLowerCase()))"
                  border stripe style="width: 100%" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="50" />
          <el-table-column prop="feedbackId" label="ID" width="50"/>

          <el-table-column prop="feedbackBt" label="反馈标题" width="200"/>

          <el-table-column prop="feedbackNy" label="反馈内容" />

          <el-table-column prop="createTime" label="创建时间" width="150"/>


          <el-table-column fixed="right" label="操作" width="150" align="center">
            <template #default="scope">


              <el-button
                  type="danger"
                  icon="el-icon-delete"
                  circle
                  @click="del(scope.row.feedbackId)"
              >
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>

    <!-- 分页 -->
    <el-row :gutter="0" class="userindex-list">
      <el-col :span="22" class="userindex-page-box">
        <el-pagination v-model:currentPage="currentPage" :page-sizes="[5, 10, 20, 30]" :page-size="pageSize"
                       layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange"
                       @current-change="handleCurrentChange">
        </el-pagination>
      </el-col>
    </el-row>


  </div>
</template>

<script>
import request from "@/utils/request.js"
import {ElMessage} from 'element-plus'

export default {
  created() {
    this.lode()

  },
  methods: {
    add() {
      this.dialogVisible = true
      this.form = {}
    },
    edit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
    },

    lode() {
      request.get("/feedback/page", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search,
        }
      }).then(res => {
        console.log(res);
        this.admin = res.data.records
        this.total = res.data.total
      })

    },

    //删除
    del(feedbackId) {
      this.$confirm("是否确定要删除", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
          .then(() => {
            request
                .delete("/feedback/delete/" + feedbackId)
                .then(res => {
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
                });
          });
    },
    deleteBatch(){
      if (!this.ids.length){
        ElMessage({
          type:"warning",
          message:"请先进行选择"
        })
        return
      }
      request.post("/feedback/deleteBatch",this.ids).then(res => {
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
      this.ids = val.map(v => v.feedbackId)
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

  data() {
    return {
      admin:[],
      dialogVisible: false,
      search: '',
      currentPage: 1,
      pageSize: 5,
      total: 0,
      tableData: [],
      form: {},
      ids: [],
    };
  },

};
</script>

<style scoped>
.userindex {
  width: 100%;
  min-height: 100%;
  padding: 15px;
  box-sizing: border-box;
}

.userindex-queryInfo {
  margin-bottom: 10px;
}


/* 列表 */
.userindex-list {
  width: 100%;
  height: auto;
  margin-bottom: 20px;
}

/* 分页 */
.userindex-page-box {
  width: 100%;
  height: auto;
  display: flex;
  justify-content: center;
}
</style>
