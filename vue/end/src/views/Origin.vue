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
    <el-dialog title="修改" v-model="dialogVisible">
      <el-form
          ref="fruitRules"
          :model="admins"
          label-width="120px">
        <el-form-item label="商品描述" prop="descript">
          <el-input v-model="admins.descript"></el-input>
        </el-form-item>
        <el-form-item label="商品描述图1" prop="productMd">
          <el-upload
              ref="uplode"
              action="http://localhost:8888/files/uplode"
              :on-success="filesUplodeSeccessd">
            <el-button type="primary">点击上传</el-button>
            <div solt="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>
        <el-form-item label="商品描述图2" prop="productMf">
          <el-upload
              ref="uplode"
              action="http://localhost:8888/files/uplode"
              :on-success="filesUplodeSeccessf">
            <el-button type="primary">点击上传</el-button>
            <div solt="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>
        <el-form-item label="商品描述图3" prop="productMg">
          <el-upload
              ref="uplode"
              action="http://localhost:8888/files/uplode"
              :on-success="filesUplodeSeccessg">
            <el-button type="primary">点击上传</el-button>
            <div solt="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>
        <el-form-item label="商家二维码" prop="productErm">
          <el-upload
              ref="uplode"
              action="http://localhost:8888/files/uplode"
              :on-success="filesUplodeSeccessh">
            <el-button type="primary">点击上传</el-button>
            <div solt="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">确定</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <!-- 检索结果 -->
    <el-row :gutter="0" class="userindex-list">
      <el-col :span="21">
        <el-table :data="admin" border style="width: 100%" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55"/>
          <el-table-column  prop="productId" label="商品ID" width="80"/>
          <el-table-column prop="productImage" label="商品图片" width="120">
            <template #default="scope">
              <el-image style="width: 100px; height: 100px" :src="scope.row.productImage" :preview-src-list="[scope.row.productImage]">
              </el-image>
            </template>
          </el-table-column>
          <el-table-column prop="productName" label="商品名称" width="250"/>
           <el-table-column prop="descript" label="商品描述" width="200"/>
          <el-table-column prop="productMd" label="商品描述图片1" width="120">
             <template #default="scope">
          <el-image style="width: 100px; height: 100px" :src="scope.row.productMd" :preview-src-list="[scope.row.productMd]">
            <div solt="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
            </div>
          </el-image>
        </template>
          </el-table-column>
           <el-table-column prop="productMf" label="商品描述图片2" width="120">
              <template #default="scope">
          <el-image style="width: 100px; height: 100px" :src="scope.row.productMf" :preview-src-list="[scope.row.productMf]">
            <div solt="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
            </div>
          </el-image>
        </template>
          </el-table-column>
          <el-table-column prop="productMg" label="商品描述图片3" width="120">
             <template #default="scope">
          <el-image style="width: 100px; height: 100px" :src="scope.row.productMg" :preview-src-list="[scope.row.productMg]">
            <div solt="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
            </div>
          </el-image>
        </template>
          </el-table-column>
          <el-table-column prop="productErm" label="商家二维码" width="120">
             <template #default="scope">
              <el-image style="width: 100px; height: 100px" :src="scope.row.productErm" :preview-src-list="[scope.row.productErm]">
                <div solt="error" class="image-slot">
                  <i class="el-icon-picture-outline"></i>
                </div>
              </el-image>
            </template>
          </el-table-column>
          <el-table-column prop="createTime" label="创建时间" width="120">
          </el-table-column>
          <el-table-column prop="updateTime" label="最后修改时间" width="120">
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="150" align="center">
            <template #default="scope">
              <el-button
                type="primary"
                icon="el-icon-edit"
                circle
                size="small"
                @click="edit(scope.row)"
              >
              </el-button>

              <el-button
                type="danger"
                icon="el-icon-delete"
                circle
                size="small"
                @click="del(scope.row)"
              >
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>

    <!-- 分页 -->
    <el-row :gutter="20" class="userindex-list">
      <el-col :span="24" class="userindex-page-box">
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
    lode() {
      request.get("/product/page", {
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
    edit(row) {
      this.admins = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
      this.$nextTick(() =>{
        this.$refs['uplode'].clearFiles()
      })
    },
      //修改
    onSubmit() {
      request.put("/product/update", this.admins).then(res => {
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
    },
    //删除
    del(row) {
      this.$confirm("是否确定要删除" , {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          request
              .delete("/product/delete/" + row.productId)
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
        })
        .catch(() => {});
    },
    deleteBatch(){
      if (!this.ids.length){
        ElMessage({
          type:"warning",
          message:"请先进行选择"
        })
        return
      }
      request.post("/product/deleteBatch",this.ids).then(res => {
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
    filesUplodeSeccessd(res){
      console.log(res)
      this.admins.productMd = res.data

    },
    filesUplodeSeccessf(res){
      console.log(res)
      this.admins.productMf = res.data

    },
    filesUplodeSeccessg(res){
      console.log(res)
      this.admins.productMg = res.data

    },
    filesUplodeSeccessh(res){
      console.log(res)
      this.admins.productErm = res.data

    },
    handleSelectionChange(val){
      this.ids = val.map(v => v.productId)
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
      admins:{},
      search: '',
      dialogVisible:false,
      currentPage: 1,
      pageSize: 5,
      total: 0,
      tableData: [],
      admin: [],
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
/* 搜索 */
.userindex-queryInfo {
  margin-bottom: 10px;
}
.userindex-queryInfo-li {
  width: 100%;
  height: auto;
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
