<template>
  <div>
 <span style="margin-left: 18px;"> <el-button type="text" @click="dialogFormVisible = true">使用新地址</el-button>

<el-dialog title="收货地址" :visible.sync="dialogFormVisible">
  <el-form :model="addresses" ref="fruitRule"
    :rules="rules">
   <el-form-item label="收货地址" prop="addressName">
    <el-input v-model="addresses.addressName"></el-input>
  </el-form-item>
  <el-form-item label="收货人" prop="addressDescribe">
    <el-input v-model="addresses.addressDescribe"></el-input>
  </el-form-item>
  <el-form-item label="收货人电话" prop="addressPhone">
    <el-input v-model.number="addresses.addressPhone"
    oninput="value=value.replace(/^\.+|[^\d.]/g,'')" minlength="11" show-word-limit></el-input>
  </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="onSubmits('fruitRule');">立即创建</el-button>
  </div>
</el-dialog></span>
<div class="shopping-cart-null" v-show="address.length <= 0">
                <Icon type="ios-cart-outline" class="cart-null-icon"></Icon>
                <span>你的购物车没有空空哦</span>
                <span>赶快去添加商品吧~</span>
              </div>
              <div class="shopping-cart-list" v-show="address.length > 0">
    <div class="address-box" v-for="item in address" :key="item.addressId">
      <div class="address-header">
        <span>{{item.addressDescribe}}</span>
        <div class="address-action">
         <router-link v-bind:to="'/editAddress/'+item.addressId"> <span @click="edit(index)"><Icon type="edit"></Icon> 修改</span></router-link>
          <span @click="goodsDelete(item.addressId)"><Icon type="trash-a"></Icon> 删除</span>

        </div>
      </div>
      <div class="address-content">
        <p><span class="address-content-title"> 收 货 人 :</span> {{item.addressDescribe}}</p>
         <p><span class="address-content-title">收货地址:</span> {{item.addressName}}</p>
        <p><span class="address-content-title">收货人电话:</span> {{item.addressPhone}}</p>
      </div>
    </div>
              </div>
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
      currentPage: 1,
      pageSize: 5,
      total: 0,
      modal: false,
      dialogTableVisible: false,
      dialogFormVisible: false,
      id: this.$store.state.userInfo.id,
      address: [],
      addresses: {},
      rules: {
        addressName: [
          { required: true, message: '请输入地址', trigger: 'blur' }
        ],
        addressDescribe: [
          { required: true, message: '请输入收货人名称', trigger: 'blur' }
        ],
        addressPhone: [
          { required: true, message: '收货人电话不能为空,并且只能为数字哦,并且长度只能为11位', trigger: 'blur'
          }

        ]
      }
    };
  },
  created () {
    this.lode();
  },
  computed: {

    ...mapState(['userInfo', 'shoppingCart'])

  },
  methods: {

    lode () {
      // eslint-disable-next-line no-undef
      axios.get('http://localhost:8888/address/finds/' + this.id, {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {
        console.log(res);
        this.address = res.data.data.records;
        this.total = res.data.data.total;
      });
    },

    onSubmits (formName) {
      this.addresses.customerId = this.id;

      this.$refs[formName].validate((valid) => {
        if (valid) {
          let _this = this;
          // eslint-disable-next-line no-undef
          axios
            .post('http://localhost:8888/address/add', this.addresses)
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
    goodsDelete (goodId) {
      let _this = this;
      this.$confirm('是否确定要删除', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          // eslint-disable-next-line no-undef
          axios
            .delete('http://localhost:8888/address/delete/' + goodId)
            .then(function (response) {
              if (response.data) {
                _this.$alert('删除成功!', '删除收货地址', {
                  confirmButtonText: '确定',
                  callback: (action) => {
                    // 跳转到 /table
                    location.reload();
                  }
                });
              }
            });
        })
        .catch(() => {});
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
