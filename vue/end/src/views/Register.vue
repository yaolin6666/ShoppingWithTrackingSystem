<template>
    <div>
        <el-container>
            <el-header>
            </el-header>
            <el-main>
                <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px"
                    class="demo-ruleForm">
                    <el-form-item prop="username">
                        <el-input prefix-icon="el-icon-user" v-model="ruleForm.username" placeholder="用户名"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input prefix-icon="el-icon-lock" v-model="ruleForm.password" placeholder="密码" show-password></el-input>
                    </el-form-item>
                    <el-form-item prop="confirm">
                        <el-input prefix-icon="el-icon-lock" v-model="ruleForm.confirm" placeholder="确认密码" show-password></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="register">注册</el-button>
                        <el-button @click="resetForm('ruleForm')">重置</el-button>
                    </el-form-item>
                </el-form>
            </el-main>
        </el-container>
    </div>
</template>
<script>
import request from "@/utils/request.js"
import { ElMessage } from 'element-plus'
    export default {
        name: 'Register',
        data() {

            return {
                ruleForm: {
                  role:2
                },
                rules: {
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' },
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' },
                    ],
                    confirm: [
                        { required: true, message: '请输入确认密码', trigger: 'blur' },
                    ],

                }
            };
        },
        methods: {
            register() {
                if (this.ruleForm.password !== this.ruleForm.confirm) {
                    ElMessage({
                        type: 'error',
                        message: '两次密码输入不一致',
                    })
                    return
                }
                this.$refs['ruleForm'].validate((valid) => {
                    if (valid) {
                        request.post('/register',this.ruleForm).then(res => {
                          console.log(res);
                            if (res.code === 200) {
                                ElMessage({
                                    type: 'success',
                                    message: '注册成功',
                                })
                                this.$router.push("/login")
                            } else {
                                ElMessage({
                                    type: 'error',
                                    message: res.msg,
                                })
                              this.$router.push("/register")
                            }
                        })

                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });

            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }

        },
        mounted() {

        }
    }
</script>
<style>

.demo-ruleForm {
        max-width: 500px;
        margin: 0 auto;
    }
</style>