<template>
  <div style="padding: 10px;">

    <div :data="admin">
      <div class="ju">
        <img :src="admin.avatar" width="170" height="170" style="border-radius: 100px; box-shadow: 0 4px 8px rgba(0, 0, 0, .22), 0 0 12px rgba(0, 0, 0, .14);">
        <div class="ju8">
          <el-button @click="handleEdit(this.admin)">修 改</el-button>
        </div>
      </div>
      <div class="jj">
        <div class="ju1">
          <i class="el-icon-user"></i>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称
          <span style="margin-left: 90px;">
       {{admin.customerName}}
       </span>
        </div>
        <div class="ju3">
          <i class="el-icon-phone-outline"></i>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话
          <span style="margin-left: 90px;">
       {{admin.phone}}
        </span>
        </div>

        <div class="ju4">
          <i class="el-icon-message"></i>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱
          <span style="margin-left: 90px;">
      {{admin.email}}
      </span>
        </div>

        <div class="ju5">
          <i class="el-icon-present"></i>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄
          <span style="margin-left: 90px;">
       {{admin.age}}
       </span>
        </div>
        <div class="ju6">
          <i class="el-icon-male"></i>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别
          <span style="margin-left: 90px;">
       {{admin.sex}}
       </span>
        </div>
      </div>
    </div>

    <el-dialog v-model="dialogVisible" title="修改" width="30%">
      <el-form :model="form" label-width="120px">
        <el-form-item label="昵称">
          <el-input v-model="form.customerName"></el-input>
        </el-form-item>
        <el-form-item label="头像">
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
        <el-form-item label="签名">
          <el-input v-model="form.signatures"></el-input>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input v-model="form.age"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio v-model="form.sex" label="男">男</el-radio>
          <el-radio v-model="form.sex" label="女">女</el-radio>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="form.phone"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email"></el-input>
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
      admin:[],
      id:'',
    }
  },
  created() {
    this.id=JSON.parse(sessionStorage.getItem('userInfo')).id
    const url='/customerInfo/find/'+this.id;
    request.get(url).then(res=>{
      this.admin=res;
    })
    this.lode()
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
      request.post("/customerInfo/deleteBatch",this.ids).then(res => {
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
    filesUplodeSeccess(res){
      console.log(res)
      this.form.avatar = res.data

    },
    lode() {
      request.get("/customerInfo/page", {
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
      if (this.$refs['upload']) {
        this.$refs['upload'].clearFiles()  // 清除历史文件列表
      }

    },
    save() {
      if (this.form.customerId) {
        request.put("/customerInfo/update", this.form).then(res => {
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
        request.post("/customerInfo", this.form).then(res => {
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
      this.$nextTick(() =>{
        this.$refs['uplode'].clearFiles()
      })


    },
    handleDelete(customerId) {

      request.delete("/customerInfo/" + customerId).then(res => {
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



<style scoped>
.jj{
  padding-top: 100px;
}
.ju{
  margin-left: 280px;
  padding-top: 190px;
  float: left;

}
.ju1{
  margin-top: 40px;
  font-size: 17px;
  color: rgb(141, 148, 148);
  font-weight: bold;
  margin-left: 590px;

}

.ju3{
  margin-top: 40px;
  font-size: 17px;
  font-weight: bold;
  color: rgb(141, 148, 148);
  margin-left: 590px;
}
.ju4{
  margin-top: 40px;
  font-size: 17px;
  font-weight: bold;
  color: rgb(141, 148, 148);
  margin-left: 590px;
}
.ju5{
  margin-top: 40px;
  font-size: 17px;
  font-weight: bold;
  color: rgb(141, 148, 148);
  margin-left: 590px;
}
.ju6{
  margin-top: 40px;
  font-size: 17px;
  font-weight: bold;
  color: rgb(141, 148, 148);
  margin-left: 590px;
}

.ju8{

  margin-top: 60px;
  padding-left: 40px;
}

</style>
