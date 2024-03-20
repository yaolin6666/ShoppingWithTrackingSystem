<template>

  <div class="ll">
    <div class="pi1">
      网上商城系统
    </div>
    <div class="pi">

   <span>
  <el-steps :active="active" finish-status="success">
  <el-step title="拍下商品"></el-step>
  <el-step title="付款到支付宝"></el-step>
  <el-step title="确认收货"></el-step>
  <el-step title="评价"></el-step>
</el-steps></span></div>
    <div class="ko">
      <h3 class="ff">
        选择收货地址
      </h3>
      <div style="margin-left: 190px;">
        <div style="word-wrap:break-word;word-break:break-all;width:1172px;">
          <el-radio-group v-model="addressId" v-for="item in addressList" :key="item.addressId" @click="toggleTab(item.addressId)">
            <el-radio-button :label="item.addressId" class="jiaf" style="margin-left: 8px;">
              {{item.addressName}} 收货人:{{item.addressDescribe}} 电话:{{item.addressPhone}}
            </el-radio-button>
          </el-radio-group>
        </div>
      </div>
      <router-link to="/home/myAddress">
        <div class="bb">管理收货地址</div>
      </router-link>
      <div class="bb">
        <el-button type="text" @click="dialogFormVisible = true">使用新地址</el-button>
        <el-dialog title="收货地址" :visible.sync="dialogFormVisible">
          <el-form :model="address" ref="fruitRule"
                   :rules="rules">
            <el-form-item label="收货地址" prop="goodName">
              <el-input v-model="address.addressName"></el-input>
            </el-form-item>
            <el-form-item label="收货人" prop="goodDescribe">
              <el-input v-model="address.addressDescribe"></el-input>
            </el-form-item>
            <el-form-item label="收货人电话" prop="goodPhone">
              <el-input v-model="address.addressPhone"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="onSubmits('fruitRule');"
            >立即创建
            </el-button
            >
          </div>
        </el-dialog>
      </div>
    </div>

    <div class="tt1">
      <div class="tt">
        <div class="v1"></div>
      </div>
    </div>

    <div class="tableTitle">

    </div>
    <div class="gu"><h3>确认订单信息</h3></div>
    <div class="vv1">
      <span class="qq">商品图片</span>
      <span class="n1">商品名称</span>
      <span>颜色</span>
      <span>价格</span>
      <span>数量</span>
      <span>优惠方式</span>
      <span>订单金额</span>
    </div>
    <div class="lj"></div>

    <div class="vv2">
      <router-link v-bind:to="'/goodsDetail/'+notice.productId" style="color: #495060;">
        <div class="m1" @click="onSubmitss(notice)">
  <span class="qq">
    <img :src="notice.productImage" width="70px" height="70px">
    </span>
        </div>
      </router-link>
      <div class="m2"><span style="word-wrap:break-word;word-break:break-all;width:120px;margin-left: 50px;">{{notice.productName.substring(0, 32)}}...
    <img src="@/assets/bv2.png">
  </span>

        <span
          style="word-wrap:break-word;word-break:break-all;width:50px;margin-left: 99px;">{{notice.productColor}}</span>
        <span
          style="word-wrap:break-word;word-break:break-all;width:50px;margin-left: 162px;">{{notice.productPrice}}</span>

        <span style="word-wrap:break-word;word-break:break-all;width:50px;margin-left: 108px;"><el-input-number
          v-model="notice.productNum" @change="handleChange" :min="1" :max="notice.productMnum" size="mini"
          style="width:105px"></el-input-number></span>
        <span style="word-wrap:break-word;word-break:break-all;width:120px;margin-left: 172px;">省205:超值优惠</span>
        <span style="font-size:16px;font-weight:bold;width:20px;margin-left: 132px;" class="font-weight-black"
              v-rainbow>{{notice.productNum * notice.productPrice}}.00</span>
        </div>
    </div>

    <div class="lj1"></div>
    <div class="q0">
      <div class="g4" v-rainbow><span style="margin-left: 127px;font-size:16px;font-weight:bold;">- 0.00</span>
        <div class="g8" style="margin-left: 97px;font-size:14px;font-weight:bold;">{{gender}}</div>
        <div class="g8" style="margin-left: 138px;font-size:14px;font-weight:bold;">0.00</div>
        <div class="fu1">
          <span style="color: black;">店铺合计(含运费)</span>
          <span style="color: rgba(19, 18, 18, 0.575);font-size:14px;"></span>
          <span style="margin-right: 150px;font-size:17px;font-weight:bold;">￥{{notice.productNum * notice.productPrice + notice.shippingMoney}}.00</span>
        </div>
      </div>
      <div class="g5">
        <span style="margin-left: 400px;">优惠:</span><span style="margin-left: 25px;"></span>
      </div>
      <div class="ee">
        <div class="pan0">
        </div>
        <div class="jia">
          <div class="v4" style="margin-left: 720px; width:600px">运费险：<img src="@/assets/bg6.png" height="17px">卖家赠送，退换货可赔
          </div>
          <span>订单备注：</span>
          <el-input
            class="p8"
            type="textarea"
            :rows="2"
            width="200px"
            placeholder="请输入内容"
            v-model="notice.productMessage">
          </el-input>
          <div class="gl"></div>
        </div>

      </div>
      <div class="gl"></div>
    </div>

    <div class="fj"></div>
    <div class="wan">
      <el-radio-group v-model="notice.paymentMethod" size="medium">
        <el-radio label="匿名付款"></el-radio>
        <el-radio label="朋友代付"></el-radio>
        <el-radio label="花呗分期"></el-radio>
      </el-radio-group>
    </div>
    <el-divider class="jg"></el-divider>
    <div class="fuu">
      <div class="fuu1">
        <span class="fuu2" style="font-size:14px;font-weight:bold;">实付款：</span>
        <span style="font-size:27px;font-weight:bold;color: darkgray;">￥</span>
        <span style="word-wrap:break-word;word-break:break-all;width:400px;font-size:28px;font-weight:bold;color: red;">{{notice.productNum * notice.productPrice + notice.shippingMoney}}.00</span>
      </div>
      <div class="fm1" style="word-wrap:break-word;word-break:break-all;width:300px;">

        <span style="font-size:14px;font-weight:bold;margin-left: 55px;">寄送至:</span>
        <span style="word-wrap:break-word;word-break:break-all;width:400px;">{{notice.address}}</span>

      </div>
      <div class="fm2" style="word-wrap:break-word;word-break:break-all;width:300px;">
        <span style="font-size:14px;font-weight:bold;margin-left: 55px;">收货人:</span>

        <span style="word-wrap:break-word;word-break:break-all;width:400px;">{{notice.shippingUser}} {{notice.productPhones}}</span>

      </div>
    </div>
    <el-dialog title="选择支付方式" :visible.sync="dialogFormVisibles" width="600px">
      <div class="pay-container">
        <div style="margin-bottom:10px;">
          <p>
            <img @click="shows = !shows;show = !show;buy(notice.productId)" src="@/assets/zfb.png" width="150px"
                 height="70px"/>
          </p>
          <p>
            <img @click="shows = !shows;show = !show;buy(notice.productId)" src="@/assets/wechatpay.jpg" width="150px"
                 height="70px"/>
          </p>
          <p>
            <img @click="shows = !shows;show = !show;buy(notice.productId)" src="@/assets/unionpay.jpg" width="150px"
                 height="70px"/>
          </p>
          <p>
            <img @click="shows = !shows;show = !show;buy(notice.productId)" src="@/assets/visamaster.jpg" width="150px"
                 height="70px"/>
          </p>
        </div>

      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisibles = false">取 消</el-button>
        <el-button style="background: red;color: seashell;font-size:17px;width:190px;" v-show="shows">请完成支付
        </el-button>
        <el-button style="background: red;color: seashell;font-size:17px;width:190px;
" v-show="show" @click="del();onSubmit()"
        >已完成购买
        </el-button>
      </div>
    </el-dialog>
    <div class="cc">
      <router-link to="/notice"><span
        style="margin-right:43px;color: #0e30f0;font-size:13px;font-weight:bold;">返回购物车</span></router-link>
      <el-button style="background: red;color: seashell;font-size:17px;width:190px;"
                 @click="dialogFormVisibles = true;next"
      >立即支付
      </el-button>
    </div>
    <el-row>
      <el-popover
        placement="right"
        width="400"
        trigger="click">
        <el-form
          ref="fruitRules"
          :model="notice"

          :rules="rules"
          label-width="300px"
          class="demo-ruleForm"
          style="width: 600px">
          <el-form-item label="商品图片" prop="adminImage">
            <el-img v-model="notice.productImage" :src="notice.productImage" width="300" height="100"></el-img>
          </el-form-item>
          <el-form-item label="订单ID" prop="orderDetailId">
            <el-input v-model="notice.orderDetailId">{{notice.orderDetailId}}</el-input>
          </el-form-item>

          <el-form-item label="商品ID" prop="productId">
            <el-input v-model="notice.productId">{{notice.productId}}</el-input>
          </el-form-item>
          <el-form-item label="商品名称" prop="productName">
            <el-input v-model="notice.productName">{{notice.productName}}</el-input>
          </el-form-item>
          <el-form-item label="收货人" prop="shippingUser">
            <el-radio-group v-model="notice.shippingUser" v-for="item in admin" :key="item.goodId"
                            @click="toggleTab(item.goodId)">
              <el-radio-button :label="item.goodDescribe"></el-radio-button>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="地址" prop="address">
            <el-radio-group v-model="notice.address" v-for="item in admin" :key="item.goodId"
                            @click="toggleTab(item.goodId)">
              <el-radio-button :label="item.goodName"></el-radio-button>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="电话号码" prop="productPhones">
            <el-radio-group v-model="notice.productPhones" v-for="item in admin" :key="item.goodId"
                            @click="toggleTab(item.goodId)">
              <el-radio-button :label="item.goodPhone"></el-radio-button>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="颜色" prop="productColor">
            <el-input v-model="notice.productColor"></el-input>
          </el-form-item>
          <el-form-item label="数量" prop="productNum">
            <el-input v-model="notice.productNum"></el-input>
          </el-form-item>
          <el-form-item label="价格" prop="productPrice">
            <el-input v-model="notice.productPrice"></el-input>
          </el-form-item>
          <el-form-item label="付款方式" prop="paymentMethod">
            <el-radio-group v-model="notice.paymentMethod" size="medium">
              <el-radio label="匿名付款"></el-radio>
              <el-radio label="朋友代付"></el-radio>
              <el-radio label="花呗分期"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="商品总数量" prop="productMnum">
            <el-input v-model="notice.productMnum">{{notice.productMnum}}</el-input>
          </el-form-item>
          <el-form-item label="快递费用" prop="shippingMoney">
            <el-input v-model="notice.shippingMoney"></el-input>
          </el-form-item>

          <el-form-item label="订单备注" prop="productMessage">
            <el-input v-model="notice.productMessage"></el-input>
          </el-form-item>

          <el-form-item label="订单金额" prop="orderMoney">
            <el-input v-model="notice.orderMoney">{{notice.productNum * notice.productPrice}}</el-input>
          </el-form-item>

          <el-form-item label="支付金额" prop="shippingMoney">
            <el-input v-model="notice.shippingMoney">{{notice.productNum * notice.productPrice + notice.shippingMoney}}
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="danger" @click="del();onSubmit('fruitRules')">立即创建
            </el-button>
            <el-button>取消</el-button>
          </el-form-item>
        </el-form>
      </el-popover>
    </el-row>
  </div>

</template>

<script>
import store from '@/store/index';
// eslint-disable-next-line no-unused-vars
import {mapState, mapActions} from 'vuex';

export default {
  name: 'order',
  created () {
    const _this = this;
    this.$axios.get('http://localhost:8888/address/finds/' + this.ids).then(function (resp) {
      _this.addressList = resp.data.data.records;

      console.log(resp);
    });
    let orderDetailId = this.$route.query.orderDetailId;

    // eslint-disable-next-line no-undef
    axios.get('http://localhost:8888/shopping/find/' + orderDetailId).then(function (response) {
      _this.notice = response.data;
    });
  },
  updated () {
    const _this = this;
    // eslint-disable-next-line no-undef
    axios.get('http://localhost:8888/product/find/' + this.notice.productId).then(function (response) {
      _this.goodInfo = response.data;
    });
    for (let i = 0; i < this.addressList.length; i++) {
      // eslint-disable-next-line eqeqeq
      if (this.addressList[i].addressId == this.addressId) {
        this.notice.address = this.addressList[i].addressName;
        this.notice.shippingUser = this.addressList[i].addressDescribe;
        this.notice.productPhones = this.addressList[i].addressPhone;
      }
    }
  },
  data () {
    return {
      ids: this.$store.state.userInfo.id,
      show: false,
      shows: true,
      product: [],
      active: 0,

      dialogTableVisibles: false,
      dialogFormVisibles: false,
      gender: '',
      addressId: '',
      shopCustomerId: '',
      goodInfo: {},
      notice: {
        name: '',
        sale: '',
        icon: '',
        status: '',
        shopCustomerId: ''
      },
      address: {},
      addressList: [],
      admin: [],
      rules: {
        addressName: [
          {required: true, message: '收货人地址为空', trigger: 'blur'}
        ],
        addressDescribe: [
          {required: true, message: '收货人名称为空', trigger: 'blur'}
        ],
        addressPhone: [
          {required: true, message: '收货人电话不能为空', trigger: 'blur'}
        ]

      },
      dialogTableVisible: false,
      dialogFormVisible: false
    };
  },
  methods: {

    onSubmitss (admin) {
      admin.customerId = this.ids;
      this.product = admin;

      // eslint-disable-next-line no-unused-vars
      let _this = this;
      // eslint-disable-next-line no-undef
      axios
        .post('http://localhost:8888/img/add', this.product)
        .then(function (response) {
          console.log(this.product);
        });
    },
    next () {
      if (this.active++ > 2) this.active = 0;
    },

    onSubmits (formName) {
      this.address.customerId = this.ids;
      this.address.status = 100;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let _this = this;
          // eslint-disable-next-line no-undef
          axios
            .post('http://localhost:8888/address/add', this.address)
            .then(function (response) {
              if (response.data) {
                _this.$alert(_this.address.addressName + '添加成功！', '添加数据', {
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

    del () {
      let _this = this;
      this.axios
        .delete('http://localhost:8888/shopping/delete/' + this.notice.orderDetailId)
        .then(function (response) {
          if (response.data) {
            // eslint-disable-next-line no-undef
            _this.$alert(notice.productName + '删除成功!', '删除数据', {
              confirmButtonText: '确定',
              callback: (action) => {
                // 跳转到 /table
                location.reload();
              }
            });
          }
        })

        .catch(() => {
        });
    },

    buy (productId) {
      // eslint-disable-next-line no-undef
      axios.get('http://localhost:8888/master/buy/' + productId).then(res => {
        // 请求成功跳转沙箱支付的页面
        window.open(res.data.data);
      });
    },

    onSubmit () {
      this.notice.shopCustomerId = this.goodInfo.customerId;
      this.notice.customerId = this.ids;
      this.notice.status = 100;
      let _this = this;
      for (let i = 0; i < this.addressList.length; i++) {
        // eslint-disable-next-line eqeqeq
        if (this.addressList[i].goodId == this.addressId) {
          this.notice.address = this.addressList[i].goodName;
          this.notice.shippingUser = this.addressList[i].goodDescribe;
          this.notice.productPhones = this.addressList[i].goodPhone;
        }
      }
      // eslint-disable-next-line no-undef
      axios
        .post('http://localhost:8888/master/add', this.notice)
        .then(function (response) {
          if (response.data) {
            _this.$alert('请等待商家发货！', '购买商品', {
              confirmButtonText: '确定',
              callback: (action) => {
                // 跳转到/table
                _this.$router.push('/payDone');
              }
            });
          }
        });
    },

    decreases (notice) {
      notice.shippingMoney = '60';
    }
  },
  computed: {
    ...mapState(['userInfo', 'shoppingCart'])
  },
  store
};
</script>

<style scoped>
  .pi {
    height: 200px;
  }

  .ll {

    height: 100%;
    border: 2px solid #ffffff;

  }

  .ko {

    margin: 30px;
    position: absolute;
    left: 10%;
    width: 500px;
    margin-left: -75px;
  }

  .bb {

    margin-top: 50px;
    width: 100px;
    margin-left: 1400px;
  }

  .tableTitle {
    position: relative;
    margin-left: 450px;
    width: 1160px;
    height: 1px;
    background-color: #d4d4d4;
    text-align: center;
    font-size: 16px;
    color: rgba(101, 101, 101, 1);
  }

  .tt {

    margin-top: 350px;
  }

  .v1 {
    width: 150px;
    border: 1px solid #fa0202;
    margin-left: 310px;

  }

  .ff {
    margin-left: 200px;
    margin-bottom: 5px;

  }

  .gu {

    height: 20px;
    margin-top: 30px;
    margin-left: 310px;

  }

  .vv1 {

    margin-left: 250px;
    margin-top: 50px;

  }

  .vv1 .qq {

    margin-left: 59px;

  }

  .vv1 .n1 {
    margin-left: 63px;
  }

  .vv1 span {

    margin-left: 172px;
    font-size: 13px;
    color: #969494;
  }

  .lj {
    width: 1300px;
    height: 3px;
    margin: 2px auto;
    margin-left: 310px;
    background: #73ddf8;

  }

  .vv2 {

    margin-left: 250px;
    margin-top: 25px;

  }

  .vv2 .qq {

    margin-left: 59px;

  }

  .vv2 span {

    float: left;
    margin-left: 120px;
    font-size: 13px;
    color: #969494;
  }

  .ee {
    margin-top: 58px;
    width: 430px;
    margin-left: 310px;

  }

  .cc {
    margin-top: 185px;
    margin-left: 1285px;
  }

  .lj1 {
    width: 1300px;
    height: 2px;
    margin-top: 120px;
    margin-left: 310px;
    background: #a2e0f0;

  }

  .p1 {
    width: 600px;
    margin-top: 20px;
    margin-left: 310px;
    float: left;
  }

  .p8 {
    margin-top: 8px;
  }

  .m1 {
    float: left;
  }

  .m2 {
    margin-top: 20px;

  }

  .m2 span {
    color: rgba(19, 18, 18, 0.575);
  }

  .g5 {
    margin-top: 3px;
    float: right;
    margin-right: 205px;
  }

  .q0 {
    width: 100%;
    height: 100%;
  }

  .g4 {

    float: right;
    margin-right: 104px;
  }

  .pan1 {
    margin-top: 18px;
  }

  .g8 {
    margin-top: 70px;

    margin-right: 50px;
  }

  .jia {
    margin-top: 20px;
  }

  .fu1 {
    margin-top: 58px;
    margin-right: 100px;
  }

  .gl {
    height: 14px;
  }

  .fj {
    width: 1300px;
    height: 1px;
    background: #a2e0f0;
    margin-left: 310px;
  }

  .wan {
    margin-top: 20px;
    float: right;
    margin-right: 350px;
  }

  .jg {
    margin-top: 50px;
    background: #73ddf8;
    width: 1300px;
    margin-left: 310px;
  }

  .jiaf {
    border: 1px solid #ffff;
  }

  .fuu {
    width: 300px;
    height: 135px;
    border: 1px solid #fa0202;
    margin-top: 10px;
    float: right;
    margin-right: 320px;
  }

  .fuu1 {
    margin-top: 10px;
    padding-right: 55px;
    float: right;
  }

  .fm1 {
    width: 400px;
    margin-top: 10px;
    float: right;

  }

  .fm2 {
    margin-top: 10px;
    margin-left: 175px;
    float: right;
    padding-right: 55px;
  }

  .pay-container {
    margin: 15px auto;
    width: 80%;
    min-width: 1000px;
  }

  .pay-demo img {
    height: 80%;
  }

  .pay-tips a {
    color: #999999;
  }

  .pi {
    width: 670px;
    padding-top: 60px;
    margin-left: 950px;

  }

  .pi1 {
    margin-top: 30px;
    float: left;
    margin-left: 297px;

  }
</style>
