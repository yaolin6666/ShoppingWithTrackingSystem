<template>
<div>
    <el-row :gutter="170" class="userindex-list"  >
      <el-col :span="24"  >
        <el-table  :data="notice"   style="width: 100%;" @selection-change="handleSelectionChange">
          <el-table-column label="" width="150" >
          </el-table-column>
          <el-table-column prop="productImage" label="商品图片" width="300">
            <template slot-scope="scope">
              <el-image style="width: 200px; height: 150px" :src="scope.row.productImage" :preview-src-list="[scope.row.productImage]">
                <div slot="error" class="image-slot">
                  <i class="el-icon-picture-outline"></i>
                </div>
              </el-image>
            </template>
          </el-table-column>
          <el-table-column prop="productName" label="商品名称" width="200" >
          </el-table-column>
          <el-table-column prop="productColor" label="货源号" width="100" >
          </el-table-column>
          <el-table-column prop="productPrice" label="价格" width="150" >
          </el-table-column>
          <el-table-column prop="productNum" label="数量" width="100">
          </el-table-column>
          <el-table-column label="" width="150" >
          </el-table-column>
          <el-table-column prop="createTime" label="创建时间" width="200">
          </el-table-column>
          <el-table-column prop="money" fixed="right" label="支付金额" width="200" align="center">
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
  <Tabs style="margin-left: 10%">
    <TabPane label="订单详细">
      <el-row :gutter="0" class="userindex-list">
        <el-col :span="21">
          <el-table :data="orderInfo" border style="width: 100%">
            <el-table-column prop="orderInfo" label="订单信息" width="1000"/>
            <el-table-column prop="createTime" label="创建时间" >
            </el-table-column>
            <el-table-column prop="updateTime" label="修改时间" >
            </el-table-column>
          </el-table>
        </el-col>
      </el-row>
    </TabPane>
    <TabPane label="物流信息">
      <el-row :gutter="0" class="userindex-list">
        <el-col :span="21">
          <el-table :data="shippingInfo" border style="width: 100%">
            <el-table-column prop="deliverInfo" label="订单信息" width="1000"/>
            <el-table-column prop="createTime" label="创建时间" >
            </el-table-column>
            <el-table-column prop="updateTime" label="修改时间" >
            </el-table-column>
          </el-table>
        </el-col>
      </el-row>
    </TabPane>
  </Tabs>
</div>
</template>

<script>
import store from '@/store/index';
import { mapState } from 'vuex';
import Search from '@/components/Search';
import GoodsListNav from '@/components/nav/GoodsListNav';
import request from '../utils/request';
export default {
  name: 'Notice',

  created () {
    this.lode();
  },

  methods: {
    lode () {
      // eslint-disable-next-line no-undef
      axios.get('http://localhost:8888/master/find/' + this.id, {
      }).then(res => {
        // eslint-disable-next-line eqeqeq
        if (res.data != '') {
          res.data.money = res.data.productNum * res.data.productPrice * (100 - res.data.discount) / 100;
          this.shippingSn = res.data.shippingSn;
          this.orderId = res.data.orderId;
          this.notice.push(res.data);
        }
        // eslint-disable-next-line no-undef
        axios.get('http://localhost:8888/refund/find/' + this.id, {
        }).then(res => {
          // eslint-disable-next-line eqeqeq
          if (res.data != '') {
            res.data.money = res.data.productNum * res.data.productPrice * (100 - res.data.discount) / 100;
            this.shippingSn = res.data.shippingSn;
            this.orderId = res.data.orderId;
            this.notice.push(res.data);
          }
          // eslint-disable-next-line no-undef
          request.get('http://localhost:8888/OrderOrigin/queryOrderOrigin', {
            params: {
              orderId: this.orderId
            }
          }).then(res => {
            this.orderInfo = res;
          });
          if (this.notice[0].shippingSn) {
            // eslint-disable-next-line no-undef
            request.get('http://localhost:8888/deliverInfo/queryDeliverInfo', {
              params: {shippingSn: this.shippingSn}
            }).then(res => {
              this.shippingInfo = res;
            });
          }
        });
      });
    },
    hh () {
      this.$router.push({path: '/hh', query: { sps: this.po }});
    }
  },
  components: {
    Search,
    GoodsListNav,
    store
  },
  computed: {
    ...mapState(['userInfo', 'shoppingCart'])

  },
  data () {
    return {
      id: this.$route.params.id,
      idss: this.$store.state.userInfo.id,
      po: [],
      product: [],
      queryInfo: {
        name: '',
        type: ''

      },
      orderId: 0,
      shippingsn: '',
      currentPage: 1,
      pageSize: 12,
      total: 0,
      tableData: [],
      notice: [],
      ids: [],
      shippingInfo: [],
      orderInfo: [],
      dialogFormVisible: false,
      form: {

      },
      formLabelWidth: '120px',
      search: ''
    };
  }
};
</script>

<style scoped>
.userindex {
 margin-left: 150px;

  min-height: 100%;
  padding: 30px;
}
/* 搜索 */
.userindex-queryInfo {

margin-top: 20px;
  margin-bottom: 40px;
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

.esd{
  margin-top: 100px;
  margin-right: 230px;
}
.eg{
  margin-top: 80px;
}
.ss{
  float: right;
  padding-top: 22px;
  padding-right: 208px;
}
.rt{

      height: 100%;
      width: 100%;
      border-radius: 50%;
      font-size: 13px;
      background-color: #f2f5f6;
      box-shadow: 0 0 6px rgba(0,0,0, .12);
      text-align: center;

      color: #1989fa;
      padding: 4px;

}
.item-intro-show {
  width: 80%;
  height: 3500px;
  margin: 15px auto;
  display: flex;
  flex-direction: row;
  background-color: #fff;
}
.item-intro-recommend {
  width: 200px;
  display: flex;

  flex-direction: column;
}
.item-intro-recommend-column {
  display: flex;
  flex-direction: column;
  box-shadow: 0px 0px 5px #999;
}
.item-recommend-title {
  width: 100%;
  height: 38px;
  font-size: 16px;
  line-height: 38px;
  color: #fff;
  background-color: #e4393c;
  box-shadow: 0px 0px 5px #e4393c;
  text-align: center;
}
.item-recommend-column {
  margin-top: 15px;
}
.item-recommend-intro {
  padding: 5px 15px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  font-size: 12px;
  color: #999;
  cursor: pointer;
}
.item-recommend-img {
  width: 80%;
  margin: 0px auto;
  cursor: pointer;
}
.item-recommend-img img {
  width: 100%;
}
.item-recommend-top-num {
  color: rgb(19, 18, 18);
  margin: 0px 2px;
  padding: 1px 5px;
  border-radius: 12px;
}
.item-recommend-price {
  color: #e4393c;
  font-weight: bolder;
}
.item-intro-detail {
  margin-left: 30px;
  width: calc(80vw - 300px);
}
.item-intro-nav {
  width: 100%;
  height: 38px;
  background-color: #f7f7f7;
  border-bottom: 1px solid #e4393c;
}
.item-intro-nav ul {
  margin: 0px;
  padding: 0px;
  list-style: none;
}
.item-intro-nav li {
  float: left;
  height: 100%;
  width: 120px;
  line-height: 38px;
  text-align: center;
  color: #e4393c;
}
.item-intro-nav li:first-child {
  background-color: #e4393c;
  color: #fff;
}
.item-intro-img {
  width: 100%;
}
/* .item-intro-img img {

} */
/************* 商品参数 *************/
/* .item-param-container {
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  justify-content: space-between;
} */
.item-param-box {
  padding: 5px;
  padding-left: 30px;
  width: 240px;
  height: 36px;
  font-size: 14px;
  /* text-align: center; */
  /* background-color: #ccc; */
}
.item-param-title {
  color: #232323;
}
.item-param-content {
  color: #999;
}
.remarks-title {
  padding-left: 15px;
  height: 36px;
  font-size: 16px;
  font-weight: bolder;
  line-height: 36px;
  color: #666666;
  background-color: #f7f7f7;
}
.remarks-analyse-box {
  padding: 15px;
  display: flex;
  align-items: center;
}
.remarks-analyse-addresses {
  margin-left: 15px;
  margin-right: 15px;
}
.remarks-analyse-num {
  font-size: 26px;
}
.remarks-analyse-title {
  font-size: 12px;
  line-height: 20px;
}
.remarks-bar {
  padding-left: 15px;
  height: 36px;
  line-height: 36px;
  color: #666666;
  background-color: #f7f7f7;
}
.remarks-bar span {
  margin-right: 15px;
}
.remarks-box {
  padding: 15px;
  /* display: flex; */
  flex-direction: row;
  border-bottom: 1px #ccc dotted;
}
.remarks-user {
  float: left;
  width: 180px;
}
.remarks-user-name {
  padding-left: 15px;
}
/* .remarks-content-box {

} */
.ee {
  margin-top: 10px;
  margin-left: 1000px;
}
.remarks-star {
  background-color: #fff;
}
.remarks-content {
  font-size: 14px;
  color: #232323;
  line-height: 28px;
}
.remarks-sub {
  margin-top: 15px;
  color: #ccc;
}
.remarks-time {
  margin-left: 15px;
}
.remarks-page {
  margin-top: 100px;
  margin-right: 400px;
  display: flex;
  justify-content: flex-end;
}
/***************商品详情介绍和推荐侧边栏结束***************/
</style>

<style>
   /* 改变便签页样式 */
 .ivu-tabs-ink-bar {
   background-color: #e4393c !important;
 }
.item-tabs > .ivu-tabs > .ivu-tabs-bar .ivu-tabs-tab {
  border-radius: 0px;
  color: #999;
  height: 38px;
  background: #f7f7f7;
}
.item-tabs > .ivu-tabs > .ivu-tabs-bar .ivu-tabs-tab-active {
  color: #fff;
  background-color: #e4393c;
}
.item-tabs > .ivu-tabs > .ivu-tabs-bar .ivu-tabs-tab-active:before {
  content: "";
  display: block;
  width: 100%;
  height: 1px;
  color: #fff;
  background: #f7f7f7;
  position: absolute;
  top: 0;
  left: 0;
}
.ivu-rate-star-full:before,
.ivu-rate-star-half .ivu-rate-star-content:before {
  color: #e4393c;
}
.jj {
  margin-left: 50px;
}
.demo-image__preview {
  margin-top: 8px;
  font-size: 15px;
}
.kk {
  margin-top: 8px;
  color: #999;
}
.mm {
  float: right;
}
.shopping-cart-null {
  height: 500px;
  padding: 15px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.cart-null-icon {
  font-size: 58px;
  margin-bottom: 15px;
}
.shopping-cart-null span {
  color: #999999;
  font-size: 12px;
  line-height: 16px;
}
.item-recommend-top-num:hover {
  color: #ee7546;
}

</style>
<style scoped>
/***************商品详情介绍和推荐侧边栏开始***************/
.item-intro-show {
  width: 80%;
  height: 3500px;
  margin: 15px auto;
  display: flex;
  flex-direction: row;
  background-color: #fff;
}
.item-intro-recommend {
  width: 200px;
  display: flex;

  flex-direction: column;
}
.item-intro-recommend-column {
  display: flex;
  flex-direction: column;
  box-shadow: 0px 0px 5px #999;
}
.item-recommend-title {
  width: 100%;
  height: 38px;
  font-size: 16px;
  line-height: 38px;
  color: #fff;
  background-color: #e4393c;
  box-shadow: 0px 0px 5px #e4393c;
  text-align: center;
}
.item-recommend-column {
  margin-top: 15px;
}
.item-recommend-intro {
  padding: 5px 15px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  font-size: 12px;
  color: #999;
  cursor: pointer;
}
.item-recommend-img {
  width: 80%;
  margin: 0px auto;
  cursor: pointer;
}
.item-recommend-img img {
  width: 100%;
}
.item-recommend-top-num {
  color: rgb(19, 18, 18);
  margin: 0px 2px;
  padding: 1px 5px;
  border-radius: 12px;
}
.item-recommend-price {
  color: #e4393c;
  font-weight: bolder;
}
.item-intro-detail {
  margin-left: 30px;
  width: calc(80vw - 300px);
}
.item-intro-nav {
  width: 100%;
  height: 38px;
  background-color: #f7f7f7;
  border-bottom: 1px solid #e4393c;
}
.item-intro-nav ul {
  margin: 0px;
  padding: 0px;
  list-style: none;
}
.item-intro-nav li {
  float: left;
  height: 100%;
  width: 120px;
  line-height: 38px;
  text-align: center;
  color: #e4393c;
}
.item-intro-nav li:first-child {
  background-color: #e4393c;
  color: #fff;
}
.item-intro-img {
  width: 100%;
}
/* .item-intro-img img {

} */
/************* 商品参数 *************/
/* .item-param-container {
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  justify-content: space-between;
} */
.item-param-box {
  padding: 5px;
  padding-left: 30px;
  width: 240px;
  height: 36px;
  font-size: 14px;
  /* text-align: center; */
  /* background-color: #ccc; */
}
.item-param-title {
  color: #232323;
}
.item-param-content {
  color: #999;
}
.remarks-title {
  padding-left: 15px;
  height: 36px;
  font-size: 16px;
  font-weight: bolder;
  line-height: 36px;
  color: #666666;
  background-color: #f7f7f7;
}
.remarks-analyse-box {
  padding: 15px;
  display: flex;
  align-items: center;
}
.remarks-analyse-addresses {
  margin-left: 15px;
  margin-right: 15px;
}
.remarks-analyse-num {
  font-size: 26px;
}
.remarks-analyse-title {
  font-size: 12px;
  line-height: 20px;
}
.remarks-bar {
  padding-left: 15px;
  height: 36px;
  line-height: 36px;
  color: #666666;
  background-color: #f7f7f7;
}
.remarks-bar span {
  margin-right: 15px;
}
.remarks-box {
  padding: 15px;
  /* display: flex; */
  flex-direction: row;
  border-bottom: 1px #ccc dotted;
}
.remarks-user {
  float: left;
  width: 180px;
}
.remarks-user-name {
  padding-left: 15px;
}
/* .remarks-content-box {

} */
.ee {
  margin-top: 10px;
  margin-left: 1000px;
}
.remarks-star {
  background-color: #fff;
}
.remarks-content {
  font-size: 14px;
  color: #232323;
  line-height: 28px;
}
.remarks-sub {
  margin-top: 15px;
  color: #ccc;
}
.remarks-time {
  margin-left: 15px;
}
.remarks-page {
  margin-top: 100px;
  margin-right: 400px;
  display: flex;
  justify-content: flex-end;
}
/***************商品详情介绍和推荐侧边栏结束***************/
</style>

<style>
/* 改变便签页样式 */
.ivu-tabs-ink-bar {
  background-color: #e4393c !important;
}
.item-tabs > .ivu-tabs > .ivu-tabs-bar .ivu-tabs-tab {
  border-radius: 0px;
  color: #999;
  height: 38px;
  background: #f7f7f7;
}
.item-tabs > .ivu-tabs > .ivu-tabs-bar .ivu-tabs-tab-active {
  color: #fff;
  background-color: #e4393c;
}
.item-tabs > .ivu-tabs > .ivu-tabs-bar .ivu-tabs-tab-active:before {
  content: "";
  display: block;
  width: 100%;
  height: 1px;
  color: #fff;
  background: #f7f7f7;
  position: absolute;
  top: 0;
  left: 0;
}
.ivu-rate-star-full:before,
.ivu-rate-star-half .ivu-rate-star-content:before {
  color: #e4393c;
}
.jj {
  margin-left: 50px;
}
.demo-image__preview {
  margin-top: 8px;
  font-size: 15px;
}
.kk {
  margin-top: 8px;
  color: #999;
}
.mm {
  float: right;
}
.shopping-cart-null {
  height: 500px;
  padding: 15px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.cart-null-icon {
  font-size: 58px;
  margin-bottom: 15px;
}
.shopping-cart-null span {
  color: #999999;
  font-size: 12px;
  line-height: 16px;
}
.item-recommend-top-num:hover {
  color: #ee7546;
}
</style>
