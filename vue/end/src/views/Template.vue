<template>
    <div style="padding: 10px;">
        <div style="margin: 10px 0;display: flex;">
            <el-button type="primary" @click="adds">新增</el-button>
            <el-popconfirm title="确认删除吗?" @confirm="deleteBatch">
                <template #reference>
                    <el-button type="danger">批量删除</el-button>
                </template>
            </el-popconfirm>
        </div>
        <el-dialog title="" v-model="dialogVisible" width="800px">
            <el-card>
                <el-form ref="fruitRules" :model="admins" :rules="rules" label-width="120px" class="demo-ruleForm">
                  <el-row>
                    <el-col :span="10">
                      <el-form-item label="需求人数" prop="teamNeed">
                        <el-input v-model="admins.teamNeed"></el-input>
                      </el-form-item>
                    </el-col>
                    </el-row>
                  <el-row>
                    <el-col :span="10">
                      <el-form-item label="优惠率" prop="discount">
                        <el-input v-model="admins.discount"></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>
                    <el-form-item>
                      <el-button type="primary" @click="onSubmit('fruitRules')">立即创建</el-button>
                      <el-button>取消</el-button>
                    </el-form-item>
                </el-form>
            </el-card>
        </el-dialog>
      <el-row :gutter="0" class="userindex-list">
        <el-col :span="21">
          <el-table :data="admin" border style="width: 100%" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"/>
            <el-table-column  prop="templateId" label="优惠ID" width="120"/>
            <el-table-column prop="teamNeed" label="需求人数" width="250"/>
            <el-table-column prop="discount" label="优惠率" width="200"/>
            <el-table-column prop="createTime" label="创建时间" width="200">
            </el-table-column>
            <el-table-column prop="updateTime" label="最后修改时间" width="200">
            </el-table-column>
            <el-table-column label="操作" width="150" align="center">
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
        name: 'Product',

        created() {
          this.id=JSON.parse(sessionStorage.getItem('userInfo')).id;
          this.lode()

        },
        methods: {
            handleRemove(file) {
                console.log(file);
            },
            handlePictureCardPreview(file) {
                this.dialogVisible = true;
            },
            handleDownload(file) {
                console.log(file);
            },
            onSubmit() {
              this.admins.shopId=this.id;
                if (this.admins.templateId) {
                    request.put("/template/update", this.admins).then(res => {
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
                    request.post("/template/add", this.admins).then(res => {
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
                    })
                }
            },

            lode() {
                request.get("/template/page", {
                    params: {
                        pageNum: this.currentPage,
                        pageSize: this.pageSize,
                        search: this.search,
                        shopId: this.id
                    }
                }).then(res => {
                    console.log(res);
                    this.admin = res.data.records
                    this.total = res.data.total
                })

            },
            adds() {
                this.dialogVisible = true
                this.admins = {}
                if (this.$refs['upload']) {
                    this.$refs['upload'].clearFiles()  // 清除历史文件列表
                }
            },
            //修改
            edit(row) {
                this.admins = JSON.parse(JSON.stringify(row))
                this.dialogVisible = true
            },
            //删除
            del(row) {
                this.$confirm("是否确定要删除", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning",
                }).then(() => {
                    request
                        .delete("/template/delete/" + row.templateId)
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
                    .catch(() => {
                    });
            },
            deleteBatch() {
                if (!this.ids.length) {
                    ElMessage({
                        type: "warning",
                        message: "请先进行选择"
                    })
                    return
                }
                request.post("/template/deleteBatch", this.ids).then(res => {
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
                this.ids = val.map(v => v.templateId)
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
                dialogVisible: false,
                admins: {},
                search: '',
                currentPage: 1,
                pageSize: 5,
                total: 0,
                tableData: [],
                admin: [],
                ids: [],
                id: '',
                rules: {
                    teamNeed: [
                        {required: true, message: "需求人数", trigger: "blur"},
                    ],
                    discount: [
                        {required: true, message: "优惠率", trigger: "blur"},
                    ],
                },
            };
        },

    };
</script>

<style scoped>
    .userindex-list {
        width: 100%;
        height: auto;
        margin-bottom: 30px;
    }

    .userindex-page-box {
        width: 100%;
        height: auto;
        display: flex;
        justify-content: center;
    }
</style>
