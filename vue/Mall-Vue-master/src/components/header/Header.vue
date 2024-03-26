<template>
  <div class="box">
    <div class="nav">
      <ul class="location">
        <li>
          <Dropdown placement="bottom-start">
            <a href="javascript:void(0)">
              <Icon type="ios-location" class="icon"></Icon>
            </a>
            <DropdownMenu slot="list">
              <div class="city">

                <baidu-map class="bm-view" :center="center" :zoom="zoom" @ready="handler"></baidu-map>

              </div>
            </DropdownMenu>
          </Dropdown>
        </li>
      </ul>
      <ul class="detail">
        <li class="first" v-show="!username">
          <span>请登录</span>
        </li>
        <li v-show="!!username">
          <Dropdown>
            <div class="username-p">
        <li class="first" v-show="!admin.customerName">
          <span>请设置昵称</span>
        </li>
        <li class="first" v-show="!admin.avatar">
          <span>请设置头像</span>
        </li>
        <p class="username">
          <span style="font-size:18px;">{{ admin.customerName }}</span>
        </p>
        <span>
                <img :src="admin.avatar" width="40px" height="40px"
                     style="border-radius: 50px; box-shadow: 0 4px 8px rgba(0, 0, 0, .22), 0 0 12px rgba(0, 0, 0, .14);">
              </span>
    </div>

    <DropdownMenu slot="list">
      <div class="my-page">
        <div class="my-product" @click="myInfo">
          <Icon type="home"></Icon>
          <p>我的主页</p>
        </div>

        <div class="sign-out" @click="signOutFun">
          <div @click="onSubmitss">
            <Icon type="log-out"></Icon>
            <p>退出登录</p>
          </div>
        </div>
      </div>
    </DropdownMenu>
    </Dropdown>
    </li>
    <li>
      <router-link to="/Index">网站导航</router-link>
    </li>
    <li>
      <Dropdown placement="bottom-start">
        <a href="javascript:void(0)">
          <Icon type="ios-cart-outline"></Icon>
          购物车
          <el-badge :value="total" class="item"></el-badge>
        </a>
        <DropdownMenu slot="list">
          <div class="shopping-cart-null" v-show="notice.length <= 0">
            <Icon type="ios-cart-outline" class="cart-null-icon"></Icon>
            <span>你的购物车没有空空哦</span>
            <span>赶快去添加商品吧~</span>
          </div>
          <div class="shopping-cart-list" v-show="notice.length > 0">
            <div class="shopping-cart-box" v-for="item in notice" :key="item.orderDetailId"
                 @click="onSubmits(item);ju()">
              <router-link v-bind:to="'/goodsDetail/'+item.productId">
                <div class="shopping-cart-img">

                  <img :src="item.productImage" width="90" height="50">
                </div>
              </router-link>
              <router-link v-bind:to="'/goodsDetail/'+item.productId">
                <div class="shopping-cart-product">
                  <div class="shopping-cart-title">
                    <p>{{ item.productName.substring(0, 22) }}...</p>
                  </div>
                  <div class="shopping-cart-detail">
                    <p>
                      货源号:
                      <span class="shopping-cart-text">
                          {{ item.productColor }}
                        </span>
                      数量:
                      <span class="shopping-cart-text">
                          {{ item.productNum }}
                        </span>
                      价钱:
                      <span class="shopping-cart-text">
                          {{ item.productPrice }}
                        </span>
                    </p>
                  </div>
                </div>
              </router-link>
              <span style="margin-left: 78px;" @click="goodsDelete(item.orderDetailId)"><Icon type="trash-a"></Icon> 删除</span>
            </div>
            <el-row :gutter="20" class="userindex-list">
              <el-col :span="24" class="userindex-page-box">
                <el-pagination :currentPage="currentPage" :page-sizes="[5, 10, 20, 30]" :page-size="pageSize"
                               layout="total, sizes, prev, pager, next, jumper" :total="total"
                               @size-change="handleSizeChange"
                               @current-change="handleCurrentChange">
                </el-pagination>
              </el-col>
            </el-row>
            <div class="go-to-buy">
              <Button type="error" size="small" @click="goToPay">
                去结账
              </Button>
            </div>
          </div>
        </DropdownMenu>
      </Dropdown>
    </li>
    <li>
      <router-link to="/orders">我的订单</router-link>
    </li>
    <li>
      <router-link to="/share">社群交流</router-link>
    </li>
    <li>
      <router-link to="/origin">查询货源信息</router-link>
    </li>
    <li>
      <router-link to="/freeback">意见反馈</router-link>
    </li>
    <li>
      <router-link to="/myfootprint">我的足迹</router-link>
    </li>
    <li>
      <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=3364469827&site=qq&menu=yes">联系客服</a>

    </li>

    </ul>
  </div>
  </div>
</template>

<script>
import store from '@/store/index';
import {mapState, mapActions} from 'vuex';

export default {
  name: 'M-Header',
  created () {
    const _this = this;

    this.$axios
      .get('http://localhost:8888/account/find/' + this.id)
      .then(function (resp) {
        _this.admin = resp.data;

        console.log(resp);
      });

    this.lode();
    this.isLogin();
  },
  data () {
    return {
      recordNr: '退出前台',
      center: {lng: 0, lat: 0},
      zoom: 3,
      product: [],
      admin: [],
      ruleForm: {},
      username: this.$store.state.userInfo.username,
      customerName: this.$store.state.userInfo.customerName,
      id: this.$store.state.userInfo.id,
      avatar: this.$store.state.userInfo.avatar,
      search: '',
      currentPage: 1,
      pageSize: 5,
      total: 0,
      city: '珠海',
      cityArr: [
        ['北京', '上海', '天津', '重庆', '广州'],
        ['深圳', '河南', '辽宁', '吉林', '江苏'],
        ['江西', '四川', '海南', '贵州', '云南'],
        ['西藏', '陕西', '甘肃', '青海', '珠海']
      ],
      notice: []
    };
  },
  computed: {
    ...mapState(['userInfo', 'shoppingCart'])
  },
  methods: {
    onSubmitss () {
      this.ruleForm.username = this.username;

      this.ruleForm.recordNr = this.recordNr;

      let _this = this;
      axios
        .post('http://localhost:8888/record/add', this.ruleForm);
    },

    handler ({BMap, map}) {
      console.log(BMap, map);
      this.center.lng = 108.196015;
      this.center.lat = 22.837699;
      this.zoom = 35;
    },
    onSubmits (admin) {
      admin.customerId = this.id;
      this.product = admin;

      let _this = this;
      axios
        .post('http://localhost:8888/img/add', this.product)
        .then(function (response) {
          console.log(this.product);
        });
    },

    ju () {
      if (location.href.indexOf('#') == -1) {
        location.href = location.href + '#';
        location.reload();
      }
    },

    goodsDelete (orderDetailId) {
      let _this = this;
      this.$confirm('是否确定要删除', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          axios
            .delete('http://localhost:8888/shopping/delete/' + orderDetailId)
            .then(function (response) {
              if (response.data) {
                _this.$alert('删除成功!', '删除数据', {
                  confirmButtonText: '确定',
                  callback: (action) => {
                    // 跳转到 /table
                    location.reload();
                  }
                });
              }
            });
        })
        .catch(() => {
        });
    },

    lode () {
      axios.get('http://localhost:8888/shopping/finds/' + this.id, {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {
        console.log(res);
        this.notice = res.data.data.records;
        this.total = res.data.data.total;
      });
    },

    handleSizeChange (pageSize) { // 改变每页的个数触发
      this.pageSize = pageSize;
      this.lode();
    },
    handleCurrentChange (pageNum) { // 改变当前页码触发
      this.currentPage = pageNum;
      this.lode();
    },

    ...mapActions(['signOut', 'isLogin']),
    changeCity (city) {
      this.city = city;
    },
    goToPay () {
      this.$router.push('/notice');
    },
    myInfo () {
      this.$router.push('/home/myOrder/' + this.id);
    },
    signOutFun () {
      this.signOut();
      this.$router.push('/');
    }
  },
  store
};

</script>

<style scoped>
.box {
  width: 100%;
  height: 45px;
  background-color: #e3e4e5;
}

.nav {
  margin: 0% auto;
  width: 90%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.nav ul {
  list-style: none;
}

.nav li {
  float: left;
  font-size: 14px;
  line-height: 45px;
  margin-right: 15px;
  font-weight: bold;
}

.nav a {
  text-decoration: none;
  color: #999999;
  padding-left: 15px;
  border-left: 1px solid #ccc;
  cursor: pointer;
}

.location a {
  border-left: none;
}

.nav a:hover {
  color: #d9534f;
}

.location {
  color: #999999;
}

.icon {
  color: #d9534f;
}

.first {
  color: #999999;
}

.first a:first-child {
  padding-left: 3px;
  border-left: none;

}

.city {
  padding: 10px 15px;
  width: 500px;
  height: 500px;
}

.city-item {
  font-weight: bold;
  cursor: pointer;
  padding: 5px;
}

.city-item:hover {
  color: #d9534f;
}

.person-icon {
  color: #d9534f;
  background-color: #f0cdb2;
}

.username {
  float: left;
  margin-right: 20px;
  color: #999999;

}

.shopping-cart-list {
  padding: 3px 25px;
}

.shopping-cart-box {
  margin: 8px 0px;
  margin-top: 15px;
  padding-bottom: 15px;
  height: 60px;
  display: flex;
  align-items: center;
  border-bottom: 1px #ccc dotted;
}

.shopping-cart-box:first-child {
  margin-top: 8px;
}

.shopping-cart-img {
  margin-top: 28px;
  margin-right: 15px;
  width: 90px;
  height: 80px;
}

.shopping-cart-img img {
  width: 100%;

}

.shopping-cart-product {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-content: space-between;
  width: 200px;
  overflow: hidden;
  font-size: 12px;
  line-height: 20px;
  color: #999999;
}

.shopping-cart-detail {
  color: #999999;
}

.shopping-cart-text {
  color: #ccc;
}

.go-to-buy {
  display: flex;
  justify-content: flex-end;
}

.shopping-cart-null {
  padding: 15px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100px;
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

.username-p {

  cursor: pointer;
}

.my-page {
  padding: 3px 5px;
  width: 180px;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.my-page a {
  margin: 0px;
  padding: 0px;
  border: none;
}

.my-product {
  padding: 5px;
  width: 50%;
  height: 100%;
  text-align: center;
  cursor: pointer;
}

.my-product:hover {
  box-shadow: 0px 0px 5px #ccc;
}

.my-product i {
  font-size: 28px;
}

.my-product p {
  font-size: 12px;
}

.sign-out {
  padding: 5px;
  width: 50%;
  height: 100%;
  text-align: center;
  cursor: pointer;
}

.sign-out:hover {
  box-shadow: 0px 0px 5px #ccc;
}

.sign-out i {
  font-size: 28px;
}

.sign-out p {
  font-size: 12px;
}

.cc1 {

  float: right;
}

.bm-view {
  width: 500px;
  height: 500px;

}

</style>
