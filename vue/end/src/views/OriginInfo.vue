<template>
  <div class="userindex">
    <div style="margin: 10px 0;display: flex;">
      <el-input v-model="search" placeholder="请输入" style="width: 20%;" clearable/>
      <el-button type="primary" style="margin-left: 5px" @click="lode">查询</el-button>
      <el-button type="primary" @click="adds">新增</el-button>
    </div>
    <el-dialog title="添加货源详细" v-model="dialogVisible">
      <el-form
          ref="fruitRules"
          :model="admins"
          label-width="120px">
        <el-form-item label="溯源信息">
          <el-input v-model="admins.argInfo"></el-input>
        </el-form-item>
        <el-form-item label="溯源信息视频" prop="extraArgInfo">
          <el-upload class="upload-demo" action="http://localhost:8888/files/uplode" :on-preview="handlePreview" :on-remove="handleRemove" :file-list="fileList" :on-success="filesUplodeSuccess" list-type="video">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">不超过1gb</div>
          </el-upload>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="create">确定</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-row :gutter="0" class="userindex-list">
      <el-col :span="21">
        <el-table :data="admin" border style="width: 100%" @selection-change="handleSelectionChange">
          <el-table-column  prop="arginfoId" label="溯源详细ID" width="150"/>
          <el-table-column  prop="arginfoId" label="溯源ID" width="150"/>
          <el-table-column prop="productImage" label="溯源信息" width="430"/>
          <el-table-column prop="productImage" label="溯源额外信息" width="450">
          </el-table-column>
          <el-table-column prop="createTime" label="创建时间" width="140">
          </el-table-column>
          <el-table-column prop="updateTime" label="最后修改时间" width="140">
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
    this.id=JSON.parse(sessionStorage.getItem('userInfo')).id;
    this.lode()
  },
  methods: {
    lode() {
      request.get("/argInfo/page", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          shopId: this.id,
        }
      }).then(res => {
        console.log(res);
        this.admin = res.data.records
        this.total = res.data.total
      })
      request.get("/product/findAllAdmin", {
        params: {
          accountId: this.id
        }
      }).then(res => {
        console.log(res);
        this.productList = res
        this.productList.forEach(item=>{
          this.productMap[item.productId]={
            productImg:item.productImg,
            productName:item.productName
          }
        })
      })
    },
    edit(row) {
      this.admins = JSON.parse(JSON.stringify(row))
      this.dialogVisible1 = true
      this.$nextTick(() =>{
        this.$refs['uplode'].clearFiles()
      })
    },
    adds() {
      this.dialogVisible = true
      this.admins = {}
      if (this.$refs['upload']) {
        this.$refs['upload'].clearFiles()  // 清除历史文件列表
      }
    },
    create(){
      request.post("/argInfo/add",{
          productId: this.originProductId,
          shopId: this.id,
        }).then(res => {
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
        location.reload();
      });
    },
      //修改
    onSubmit() {
      request.put("/argInfo/update", this.admins).then(res => {
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
        this.dialogVisible1 = false
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
              .delete("/argInfo/delete/" + row.arginfoId)
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
      request.post("/argInfo/deleteBatch",this.ids).then(res => {
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
    filesUplodeSuccess(res){
      console.log(res)
      this.admins.extraArgInfo = res.data

    },
    handleSelectionChange(val){
      this.ids = val.map(v => v.arginfoId)
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
      originProductId:'',
      id: this.$route.params.id,
      admins:{},
      productList:[],
      search: '',
      dialogVisible:false,
      dialogVisible1:false,
      currentPage: 1,
      pageSize: 5,
      total: 0,
      tableData: [],
      admin: [],
      ids: [],
      sort: true,
      productMap:{},
      options: [{
        value: 0,
        label: '已售罄'
      }, {
        value: '1',
        label: '未上架'
      }, {
        value: '2',
        label: '已上架'
      }],
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
.userindex-list {
  width: 100%;
  height: auto;
  margin-bottom: 20px;
}
.userindex-page-box {
  width: 100%;
  height: auto;
  display: flex;
  justify-content: center;
}
</style>
