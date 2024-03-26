<template>
  <div>
 <span style="margin-left: 18px;">
   <div style="margin: 10px 0;display: flex;">
      <el-input v-model="search" placeholder="请输入" style="width: 20%;" clearable/>
      <el-button type="primary" style="margin-left: 5px" @click="lode">查询</el-button>
     <el-button type="primary" @click="dialogFormVisible = true">分享产品</el-button>
   </div>
   <el-dialog title="分享产品" :visible.sync="dialogFormVisible">
     <el-form :model="share" ref="fruitRule">
       <el-row>
         <el-col :span="8">
           <el-form-item label="标题">
             <el-input v-model="share.title"></el-input>
           </el-form-item>
         </el-col>
         <el-col style="margin-left: 20px" :span="10">
           <el-form-item label="产品">
             <el-select v-model="share.productId" filterable placeholder="请选择">
               <el-option v-for="item in productList" :key="item.productId" :label="item.productName" :value="item.productId"></el-option>
             </el-select>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="10">
        <el-form-item label="内容" prop="addressName">
          <el-input type="textarea" v-model="share.content"></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="10">
        <el-form-item label="描述图片1" prop="productMd">
          <el-upload class="upload-demo" action="http://localhost:8888/files/uplode" :on-preview="handlePreview" :on-remove="handleRemove" :file-list="fileList" :on-success="filesUplodeSeccess1" list-type="picture">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">不超过1Gb</div>
          </el-upload>
        </el-form-item>
      </el-col>
      <el-col :span="10">
        <el-form-item label="描述图片2" prop="productMf">
          <el-upload class="upload-demo" action="http://localhost:8888/files/uplode" :on-preview="handlePreview" :on-remove="handleRemove" :file-list="fileList" :on-success="filesUplodeSeccess2" list-type="picture">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">不超过1Gb</div>
          </el-upload>
        </el-form-item>
      </el-col>
      <el-col :span="10">
        <el-form-item label="描述图片3" prop="productMg">
          <el-upload class="upload-demo" action="http://localhost:8888/files/uplode" :on-preview="handlePreview" :on-remove="handleRemove" :file-list="fileList" :on-success="filesUplodeSeccess3" list-type="picture">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">不超过1Gb</div>
          </el-upload>
        </el-form-item>
      </el-col>
            <el-col :span="10">
        <el-form-item label="描述视频" prop="productMg">
          <el-upload class="upload-demo" action="http://localhost:8888/files/uplode" :on-preview="handlePreview" :on-remove="handleRemove" :file-list="fileList" :on-success="filesUplodeSeccess4" list-type="picture">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">不超过1Gb</div>
          </el-upload>
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="onSubmits('fruitRule');">立即分享</el-button>
  </div>
</el-dialog>
 </span>
    <el-dialog title="查看详细" :visible.sync="dialogFormVisible1">
      <el-form ref="fruitRule">
        <el-row>
          <el-col :span="8">
            <el-form-item label="标题" prop="shareTitle">
              <div>{{this.shareDetail.shareTitle}}</div>
            </el-form-item>
          </el-col>
          <el-col style="margin-left: 20px" :span="10">
            <el-form-item label="产品名称">
              <div>{{this.productDetail.productName}}</div>
            </el-form-item>
            <el-form-item label="产品图片">
              <template #default="scope">
                <el-image style="width: 200px; height: 200px" :src="productDetail.productImage" :preview-src-list="[productDetail.productImage]">
                </el-image>
              </template>
            </el-form-item>
            <el-form-item>
              <el-button @click="jumpTo(productDetail.productId)">跳转到商品</el-button>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
            <el-form-item label="内容" prop="content">
              <el-input type="textarea" v-model="shareDetail.shareContent"></el-input>
            </el-form-item>
        </el-row>
        <el-row>
          <el-col :span="10">
            <el-form-item label="描述图片1" prop="shareImg1">
              <template #default="scope">
                <el-image style="width: 100px; height: 100px" :src="shareDetail.shareImg1" :preview-src-list="[shareDetail.shareImg1]">
                </el-image>
              </template>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="描述图片2" prop="shareImg2">
              <template #default="scope">
                <el-image style="width: 100px; height: 100px" :src="shareDetail.shareImg2" :preview-src-list="[shareDetail.shareImg2]">
                </el-image>
              </template>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="描述图片3" prop="shareImg3">
              <template #default="scope">
                <el-image style="width: 100px; height: 100px" :src="shareDetail.shareImg3" :preview-src-list="[shareDetail.shareImg3]">
                </el-image>
              </template>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="描述视频" prop="productMg">
              <video :src=shareDetail.shareVideo></video>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
      </div>
    </el-dialog>

    <el-row :gutter="0" class="userindex-list">
      <el-col :span="21">
        <el-table :data="shareList" border style="width: 100%">
          <el-table-column align="center" prop="shareTitle" label="标题" />
          <el-table-column prop="createTime" label="创建时间" width="200">
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="150" align="center">
            <template #default="scope">
              <el-button
                  type="primary"
                  size="small"
                  @click="see(scope.row)">
                查看详细
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <div class="su">
      <el-row :gutter="20" class="userindex-list">
        <el-col :span="24" class="userindex-page-box">
          <el-pagination :currentPage="currentPage" :page-sizes="[5, 10, 20, 30]" :page-size="pageSize"
                         layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange"
                         @current-change="handleCurrentChange">
          </el-pagination>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import store from '@/store/index';
// eslint-disable-next-line no-unused-vars
import { mapState, mapActions } from 'vuex';
import Distpicker from 'v-distpicker';
export default {
  name: 'MyAddress',
  data () {
    return {
      search: '',
      share: {},
      shareDetail: {},
      productDetail: {},
      currentPage: 1,
      pageSize: 5,
      total: 0,
      modal: false,
      productList: [],
      productListAll: [],
      accountList: [],
      dialogTableVisible: false,
      dialogFormVisible: false,
      dialogFormVisible1: false,
      id: this.$store.state.userInfo.id,
      shareList: [],
      addresses: {}
    };
  },
  created () {
    this.lode();
  },
  computed: {

    ...mapState(['userInfo', 'shoppingCart'])

  },
  methods: {
    filesUplodeSeccess1 (res) {
      this.share.shareImg1 = res.data;
    },
    filesUplodeSeccess2 (res) {
      this.admins.shareImg2 = res.data;
    },
    filesUplodeSeccess3 (res) {
      this.admins.shareImg3 = res.data;
    },
    filesUplodeSeccess4 (res) {
      this.admins.shareVideo = res.data;
    },
    see (row) {
      this.shareDetail = JSON.parse(JSON.stringify(row));
      for (let i = 0; i < this.productListAll.length; i++) {
        // eslint-disable-next-line eqeqeq
        if (row.productId == this.productListAll[i].productId) {
          this.productDetail = this.productListAll[i];
        }
      }
      this.dialogFormVisible1 = true;
    },
    jumpTo () {
      window.open('http://localhost:8080/goodsDetail/' + this.productDetail.productId);
    },
    lode () {
      // eslint-disable-next-line no-undef
      axios.get('http://localhost:8888/share/page', {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {
        this.shareList = res.data.data.records;
        this.total = res.data.data.total;
      });
      // eslint-disable-next-line no-undef
      axios.get('http://localhost:8888/master/buyList/' + this.id, {
      }).then(res => {
        this.productList = res.data;
      });
      // eslint-disable-next-line no-undef
      axios.get('http://localhost:8888/account/findAll', {
      }).then(res => {
        this.accountList = res.data;
      });
      // eslint-disable-next-line no-undef
      axios.get('http://localhost:8888/product/findAll', {
      }).then(res => {
        this.productListAll = res.data;
      });
    },

    onSubmits (formName) {
      this.share.accountId = this.id;

      this.$refs[formName].validate((valid) => {
        if (valid) {
          let _this = this;
          // eslint-disable-next-line no-undef
          axios
            .post('http://localhost:8888/addres', this.share)
            .then(function (response) {
              if (response.data) {
                _this.$alert('添加成功！', '添加收货地址', {
                  confirmButtonText: '确定',
                  callback: (action) => {
                    // 跳转到/table
                    location.reload();
                  }
                });
              }
            });
        }
      });
    },
    handleSizeChange (pageSize) { // 改变每页的个数触发
      this.pageSize = pageSize;
      this.lode();
    },
    handleCurrentChange (pageNum) { // 改变当前页码触发
      this.currentPage = pageNum;
      this.lode();
    }

  },
  components: {
    Distpicker
  },
  store
};
</script>

<style scoped>
.address-box {
  padding: 15px;
  margin: 15px;
  border-radius: 5px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, .22), 0 0 12px rgba(0, 0, 0, .14)
}
.address-header {
  height: 35px;
  display: flex;
  justify-content: space-between;
  color: #232323;
  font-size: 18px;
}
.address-content {
  font-size: 14px;
}
.address-content-title {
  color: #999;
}
.address-action span{
  margin-left: 15px;
  font-size: 14px;
  color: #2d8cf0;
  cursor: pointer;
}
.su{
  margin-top: 600px;
  margin-left: 500px;
}
.shopping-cart-null {
  padding: 15px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.cart-null-icon {
  font-size: 38px;
  margin-bottom: 15px;
}
.shopping-cart-null span {
  color: #999999;
  font-size: 12px;
  line-height: 16px;
}
</style>
