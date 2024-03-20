<template>
  <div style="padding: 10px;">
    <div v-if="existData">
      <div class="add-box" style="width:60%;padding-left: 35%">
        <el-Form :model="form" :rules="rules" label-position="left" ref="form" :label-width="100">
          <el-form-item label="营业执照" prop="certificationImg">
            <img :src="existData.certificationImg" width="300" height="250"/>
          </el-form-item>
          <el-form-item label="认证图片1" prop="img_1">
            <img :src="existData.img1" width="300" height="250"/>
          </el-form-item>
          <el-form-item v-if="existData.img2!=null" label="认证图片2">
            <img :src="existData.img2" width="300" height="250"/>
          </el-form-item>
          <el-form-item v-if="existData.img3!=null" label="认证图片3">
            <img :src="existData.img3" width="300" height="250"/>
          </el-form-item>
        </el-Form>
      </div>
      <div class="add-submit">
        <el-Button type="primary" disabled="disabled">已提交 请等待审核</el-Button>
      </div>
    </div>
    <div v-if="!existData">
      <div class="add-box" style="width:60%;padding-left: 35%">
        <el-Form :model="form" :rules="rules" label-position="left" ref="form" :label-width="100">
          <el-form-item label="营业执照" prop="certificationImg">
            <el-upload ref="uplode"
                       action="http://localhost:8888/files/uplode"
                       :on-success="filesUplodeSeccess1">
              <el-button size="small" type="primary">上传</el-button>
              <template #tip>
                <div class="el-upload__tip">
                  只能上传jpg/png文件且文件大小不超过1MB
                </div>
              </template>
            </el-upload>
          </el-form-item>
          <el-form-item label="认证图片1" prop="img_1">
            <el-upload ref="uplode"
                       action="http://localhost:8888/files/uplode"
                       :on-success="filesUplodeSeccess2">
              <el-button size="small" type="primary">上传</el-button>
              <template #tip>
                <div class="el-upload__tip">
                  只能上传jpg/png文件且文件大小不超过1MB
                </div>
              </template>
            </el-upload>
          </el-form-item>
          <el-form-item label="认证图片2">
            <el-upload ref="uplode"
                       action="http://localhost:8888/files/uplode"
                       :on-success="filesUplodeSeccess3">
              <el-button size="small" type="primary">上传</el-button>
              <template #tip>
                <div class="el-upload__tip">
                  只能上传jpg/png文件且文件大小不超过1MB
                </div>
              </template>
            </el-upload>
          </el-form-item>
          <el-form-item label="认证图片3">
            <el-upload ref="uplode"
                       action="http://localhost:8888/files/uplode"
                       :on-success="filesUplodeSeccess4">
              <el-button size="small" type="primary">上传</el-button>
              <template #tip>
                <div class="el-upload__tip">
                  只能上传jpg/png文件且文件大小不超过1MB
                </div>
              </template>
            </el-upload>
          </el-form-item>
        </el-Form>
      </div>
      <div class="add-submit">
        <el-Button type="primary" @click="submitData">提交资料</el-Button>
      </div>
    </div>
  </div>
</template>

<script>

import request from "@/utils/request";

import {ElMessage} from "element-plus";

export default {
  name: 'User',

  components: {},
  data() {
    return {
      accountId: '',
      form: {},
      existData: {},
      rules: {
        certificationImg: [
          {required: true, message: '请上传营业执照', trigger: 'blur'}
        ],
        img_1: [
          {required: true, message: '请至少上传一张认证图片', trigger: 'blur'}
        ]
      }
    }
  },
  created() {
    this.accountId = JSON.parse(sessionStorage.getItem('userInfo')).id;
    request.get("/register/find/" + this.accountId).then(res => {
      console.log(res);
      this.existData = res
      this.lode()
      this.dialogVisible = false
    })
  },
  methods: {
    filesUplodeSeccess1(res) {
      this.form.certificationImg = res.data
    },
    filesUplodeSeccess2(res) {
      this.form.img_1 = res.data
    },
    filesUplodeSeccess3(res) {
      this.form.img_2 = res.data
    },
    filesUplodeSeccess4(res) {
      this.form.img_3 = res.data
    },
    submitData() {
      this.form.accountId = this.accountId;
      request.post("/register/add", this.form).then(res => {
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
}
</script>

<style scoped>
.add-submit {
  display: flex;
  justify-content: center;
}
</style>
