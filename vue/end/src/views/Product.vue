<template>
    <div style="padding: 10px;">
        <div style="margin: 10px 0;display: flex;">
            <el-input v-model="search" placeholder="请输入" style="width: 20%;" clearable/>
            <el-button type="primary" style="margin-left: 5px" @click="lode">查询</el-button>
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
                      <el-form-item label="商品名称" prop="productName">
                        <el-input v-model="admins.productName"></el-input>
                      </el-form-item>
                    </el-col>
                    </el-row>

                  <el-row>
                    <el-col :span="10">
                      <el-form-item label="价格" prop="productPrice">
                        <el-input v-model="admins.productPrice"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="10">
                      <el-form-item label="重量" prop="weight">
                        <el-input v-model="admins.weight"></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="20">
                      <el-form-item label="商品描述" prop="descript">
                        <el-input type="textarea" :rows="2" v-model="admins.descript"></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="12">
                      <el-form-item label="商品图片" prop="productImage">
                        <el-upload class="upload-demo" action="http://localhost:8888/files/uplode" :on-preview="handlePreview" :on-remove="handleRemove" :file-list="fileList" :on-success="filesUplodeSeccess" list-type="picture">
                          <el-button size="small" type="primary">点击上传</el-button>
                          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过1gb</div>
                        </el-upload>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="10">
                      <el-form-item label="商品规格图片1" prop="productMd">
                        <el-upload class="upload-demo" action="http://localhost:8888/files/uplode" :on-preview="handlePreview" :on-remove="handleRemove" :file-list="fileList" :on-success="filesUplodeSeccesse" list-type="picture">
                          <el-button size="small" type="primary">点击上传</el-button>
                          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过1Gb</div>
                        </el-upload>
                      </el-form-item>
                    </el-col>
                    <el-col :span="10">
                      <el-form-item label="商品规格图片2" prop="productMf">
                        <el-upload class="upload-demo" action="http://localhost:8888/files/uplode" :on-preview="handlePreview" :on-remove="handleRemove" :file-list="fileList" :on-success="filesUplodeSeccessr" list-type="picture">
                          <el-button size="small" type="primary">点击上传</el-button>
                          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过1Gb</div>
                        </el-upload>
                      </el-form-item>
                    </el-col>
                    <el-col :span="10">
                      <el-form-item label="商品规格图片3" prop="productMg">
                        <el-upload class="upload-demo" action="http://localhost:8888/files/uplode" :on-preview="handlePreview" :on-remove="handleRemove" :file-list="fileList" :on-success="filesUplodeSeccesst" list-type="picture">
                          <el-button size="small" type="primary">点击上传</el-button>
                          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过1Gb</div>
                        </el-upload>
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
        name: 'Product',

        created() {
          this.id=JSON.parse(sessionStorage.getItem('userInfo')).id;
            this.lode()

        },
        methods: {

            filesUplodeSeccess(res) {
                console.log(res)
                this.admins.productImage = res.data

            },
            filesUplodeSeccessd(res) {
                console.log(res)
                this.admins.productIm = res.data

            },
            filesUplodeSeccessf(res) {
                console.log(res)
                this.admins.productImd = res.data

            },
            filesUplodeSeccessg(res) {
                console.log(res)
                this.admins.productImf = res.data

            },
            filesUplodeSeccessh(res) {
                console.log(res)
                this.admins.productImg = res.data

            },
            filesUplodeSeccesse(res) {
                console.log(res)
                this.admins.productMd = res.data

            },
            filesUplodeSeccessr(res) {
                console.log(res)
                this.admins.productMf = res.data

            },
            filesUplodeSeccesst(res) {
                console.log(res)
                this.admins.productMg = res.data

            },
            filesUplodeSeccessy(res) {
                console.log(res)
                this.admins.productErm = res.data

            },


            handleRemove(file) {
                console.log(file);
            },
            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },
            handleDownload(file) {
                console.log(file);
            },

            onSubmit() {
              this.admins.customerId=this.id;
                if (this.admins.productId) {
                    request.put("/info/update", this.admins).then(res => {
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
                    request.post("/info/add", this.admins).then(res => {
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
                request.get("/info/page", {
                    params: {
                        pageNum: this.currentPage,
                        pageSize: this.pageSize,
                        search: this.search,
                        customerId: this.id
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
                        .delete("/info/delete/" + row.productId)
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
                request.post("/info/deleteBatch", this.ids).then(res => {
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
                    descript: [
                        {required: true, message: "商品描述", trigger: "blur"},
                    ],
                    weight: [
                        {required: true, message: "商品重量", trigger: "blur"},
                    ],
                    productPrice: [
                        {required: true, message: "商品价格", trigger: "blur"},
                    ],
                    productImage: [
                        {required: true, message: "商品图片", trigger: "blur"},
                    ],
                    productMnum: [
                        {required: true, message: "商品最大数量", trigger: "blur"},
                    ],
                    productName: [
                        {required: true, message: "商品名称", trigger: "blur"},
                    ],
                    productMd: [
                        {required: true, message: "商品规格图片1", trigger: "blur"},
                    ],
                    productMf: [
                        {required: true, message: "商品规格图片2", trigger: "blur"},
                    ],
                    productMg: [
                        {required: true, message: "商品规格图片3", trigger: "blur"},
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
