<template>
  <div style="width: 100%;height: 100vh;overflow: hidden" class="loginbg">
    <div class="loginPart">
  <div style="width: 300px;margin: 150px auto">
    <div style="text-align:center;font-size: 30px;color:white;margin: 20px 0;font-weight: bold;">欢迎登录</div>
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm"
                 class="demo-ruleForm">
          <el-form-item prop="username">
            <el-input prefix-icon="el-icon-user-solid" v-model="ruleForm.username" placeholder="请输入账号"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input prefix-icon="el-icon-lock" v-model="ruleForm.password" show-password
                      placeholder="请输入密码"></el-input>
          </el-form-item>
          <el-form-item>
            <div style="display: flex">
              <el-input prefix-icon="el-icon-key" v-model="ruleForm.validCode" style="width: 50%"
                        placeholder="请输入验证码"></el-input>
              <ValidCode @input="createValidCode" style="background-color: cornsilk;height: 32px;margin-left: 20px;"/>
            </div>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')" style="width: 135px">登录</el-button>
            <el-button type="primary" @click="$router.push('/register')" style="width: 135px">注册</el-button>
          </el-form-item>
        </el-form>
  </div>
</div>
  </div>
</template>
<script>
import ValidCode from "@/components/ValidCode";
import {ElMessage} from "element-plus";

export default {
  name: 'Login',
  components: {ValidCode},
  data() {

    return {
      recordNr:"登录后台",
      ruleForm: {},
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入用密码', trigger: 'blur'}
        ],
        validCode: '',
      }
    };
  },
  created() {
    sessionStorage.removeItem("user")
  },
  methods: {
    createValidCode(data) {
      this.validCode = data
    },
    submitForm() {
      this.ruleForm.recordNr=this.recordNr
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          if (!this.ruleForm.validCode) {
            this.$message.error("请填写验证码")
            return
          }
          if (this.ruleForm.validCode.toLowerCase() !== this.validCode.toLowerCase()) {
            this.$message.error("验证码错误")
            return
          }
          // 提交逻辑
          this.$axios.post('/login', this.ruleForm).then((res) => {
            this.$axios
            .post("http://localhost:8888/record/add", this.ruleForm)
            const token = res.headers['authorization']
            this.$store.commit('SET_TOKEN', token)
            this.$store.commit('SET_USERINFO', res.data.data)
            console.log(res.data.data);
            sessionStorage.setItem("user", JSON.stringify(res.data))
            this.$router.push("/index")
            ElMessage({
              type: 'success',
              message: '登录成功',
            })

          })
        }
      });
    },
  },
  mounted() {

  }
}
</script>
<style>
.loginbg {
  background-image: url("./../assets/login.jpg");
  /* background-repeat: no-repeat; */
  width: 100%;
  height: 100%;
  position: fixed;
  background-size: 100% 100%;
}
.loginPart{
    position:absolute;
    /*定位方式绝对定位absolute*/
    top:50%;
    left:50%;
    /*顶和高同时设置50%实现的是同时水平垂直居中效果*/
    transform:translate(-50%,-50%);
    /*实现块元素百分比下居中*/
    width:500px;
    padding:0px;
    background: rgba(245, 245, 245, 0.1);
    /*背景颜色为黑色，透明度为0.8*/
    box-sizing:border-box;
    /*box-sizing设置盒子模型的解析模式为怪异盒模型，
    将border和padding划归到width范围内*/
    box-shadow: 0px 15px 25px rgba(0,0,0,.5);
    /*边框阴影  水平阴影0 垂直阴影15px 模糊25px 颜色黑色透明度0.5*/
    border-radius:15px;
    /*边框圆角，四个角均为15px*/
  }
</style>
