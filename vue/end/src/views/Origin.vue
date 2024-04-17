<template>
  <div class="userindex">
    <div style="margin: 10px 0;display: flex;">
      <el-input v-model="search" placeholder="请输入" style="width: 20%;" clearable/>
      <el-button type="primary" style="margin-left: 5px" @click="lode">查询</el-button>
      <el-button type="primary" @click="adds">新增</el-button>
      <el-popconfirm title="确认删除吗?" @confirm="deleteBatch">
        <template #reference>
          <el-button type="danger">批量删除</el-button>
        </template>
      </el-popconfirm>
      <el-button class="el-icon-bottom" @click="index1">降序</el-button>
      <el-button class="el-icon-top"  @click="index">升序</el-button>
    </div>
    <el-dialog title="添加货源号" v-model="dialogVisible">
      <el-form
          ref="fruitRules"
          :model="originProductId"
          label-width="120px">
        <el-form-item label="选择商品">
          <el-select v-model="originProductId" filterable placeholder="请选择">
            <el-option
                v-for="item in productList"
                :key="item.productId"
                :label="item.productName"
                :value="item.productId">
              <div>
                <div>{{item.productName}}</div>
                <img style="width: 16px;height: 16px;vertical-align: text-bottom;" :src="item.productImg" :preview-src-list="[item.productImg]">
            </div>
            </el-option>
        </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="create">确定</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog title="修改库存状态" v-model="dialogVisible1">
      <el-form
          ref="fruitRules"
          :model="admins"
          label-width="120px">
        <el-form-item label="货源号状态">
          <el-select v-model="admins.status" filterable placeholder="请选择">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="货源号库存">
          <el-input v-model="admins.count"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">确定</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-row :gutter="0" class="userindex-list">
      <el-col :span="21">
        <el-table :data="admin" border style="width: 100%" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55"/>
          <el-table-column  prop="arginfoId" label="溯源ID" width="120"/>
          <el-table-column prop="productName" label="名称" width="150">
            <template #default="scope">
            <div>{{productMap[scope.row.productId].productName}}</div>
            </template>
          </el-table-column>
          <el-table-column prop="productImage" label="商品图片" width="150">
            <template #default="scope">
              <el-image style="width: 100px; height: 100px" :src="productMap[scope.row.productId].productImg" :preview-src-list="[productMap[scope.row.productId].productImg]">
              </el-image>
            </template>
          </el-table-column>
          <el-table-column prop="status" label="状态" width="150">
          <template #default="scope">
            <div v-if="scope.row.status==0">已售罄</div>
            <div v-if="scope.row.status==1">未上架</div>
            <div v-if="scope.row.status==2">已上架</div>
          </template>
          </el-table-column>
          <el-table-column prop="count" label="库存(kg)" width="150"/>
          <el-table-column prop="createTime" label="创建时间" width="150">
          </el-table-column>
          <el-table-column prop="updateTime" label="最后修改时间" width="150">
          </el-table-column>
          <el-table-column label="详细" width="150">
            <template #default="scope">
            <router-link v-bind:to="'/OriginInfo/'+scope.row.arginfoId">
              <p style="margin-left: 21px;margin-top: 18px;color:deepskyblue">查看详细</p>
            </router-link>
            </template>
          </el-table-column>
          <el-table-column  label="操作" width="120" align="center">
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
            productImg:item.productImage,
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
      this.ids = val.map(v => v.arginfoId)
    },
    handleSizeChange(pageSize) {//改变每页的个数触发
      this.pageSize = pageSize
      this.lode()
    },
    index () {
      // 排序
      this.sort = !this.sort;
      if (this.sort) {
        this.admin.sort((a, b) => {
          return a.status - b.status;
        });
      } else {
        this.admin.sort((a, b) => {
          return a.status - b.status;
        });
      }
    },
    index1 () {
      this.sort = !this.sort;
      if (this.sort) {
        this.admin.sort((a, b) => {
          return b.status - a.status;
        });
      } else {
        this.admin.sort((a, b) => {
          return b.status - a.status;
        });
      }
    },
    handleCurrentChange(pageNum) {//改变当前页码触发
      this.currentPage = pageNum
      this.lode()
    },
  },

  data() {
    return {
      originProductId:'',
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
      id: '',
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
