<template>
  <div>
    <div class="containers">
      <el-backtop  :bottom="10" :right="0">
  <div class="tyt">返回顶部
    </div>
 </el-backtop>
    <el-input placeholder="请输入内容" v-model="search" class="input-with-select">
    <el-button slot="append" icon="el-icon-search" ></el-button>
  </el-input>
    </div>
    <GoodsListNavs></GoodsListNavs>
    <div class="container">
      <div class="bread-crumb">
        <Breadcrumb>
          <BreadcrumbItem to="/Index">
            <Icon type="ios-home-outline"></Icon> 首页
          </BreadcrumbItem>
          <BreadcrumbItem to="/merchant">
            <Icon type="bag"></Icon>
          </BreadcrumbItem>
        </Breadcrumb>
      </div>
      <!-- 商品标签导航 -->
      <GoodsClassNav></GoodsClassNav>
      <!-- 商品展示容器 -->
      <div class="goods-box">
        <div class="as-box">
          <div class="item-as-title">
            <span>商品精选</span>
            <span>广告</span>
          </div>
          <div v-show="admind.length<0"></div>

          <div class="item-as" v-for="(item,index) in admind.slice(10,20)" :key="index" @click="onSubmits(item)">
            <router-link v-bind:to="'/goodsDetail/'+item.productId">
            <div class="item-as-img">
              <img :src="item.productImage" width="159px" height="120px" alt="">
            </div>
            <div class="item-as-price">
              <span>
                <Icon type="social-yen text-danger"></Icon>
                <span class="seckill-price text-danger">{{item.productPrice}}</span>
              </span>
            </div>
            <div class="item-as-intro">
              <span>{{item.productName.substring(0,45)}}...</span>
            </div>
            <div class="item-as-selled">

            </div>
            </router-link>

          </div>
        </div>
        <div class="goods-list-box">
          <div class="ff">
            <div class="ghh"><div class="jjs"><el-input class="jjs" v-model="price1" placeholder="￥">
              </el-input></div>
             <div class="jjss1"><span class="sw">-</span></div>
             <div class="jjss"> <el-input class="jjss" v-model="price2"  placeholder="￥"></el-input></div>
      <el-button
        class="li"
        @click="Onclick"
          >筛 选</el-button></div>
             <div class="box_top">
      <!-- 价格排序 -->

       <el-button class="el-icon-top" @click="index1">价格升序</el-button>
      <el-button class="el-icon-bottom"  @click="index">价格降序</el-button>
</div>

            <div v-for="admin in filteredBlogs" :key="admin"  border class="ss"  style="width: 100%" @click="onSubmits(admin)">
    <!-- <router-link v-bind:to="'/blog/'+admin.productId"> -->
     <router-link v-bind:to="'/goodsDetail/'+admin.productId">
   <div class="sss">
    <v-card
        class="mx-auto"
        max-width="344"

      >

         <div class="ee">
         <img :src="admin.productImage" width="300" height="250" />
      </div>

        <v-card-text>
          <p class="swq" style="font-size:23px;font-weight:bold;margin-left:7px;margin-top:4px;">
            ￥{{admin.productPrice}}.00
          </p>
         <p style="color: rgb(46, 46, 46);font-size:13px;margin-left:13px;margin-top:4px;word-wrap:break-word;word-break:break-all;width:273px;height:40px;">{{admin.productName}}</p>
        </v-card-text>

        <v-card-title>
          <v-rating
            :value="4"
            dense
            color="orange"
            background-color="orange"
            hover
            class="mr-2"
          ></v-rating>

        </v-card-title>

      </v-card>
      <div class="gt"><img src="@/assets/ym.png" ><img src="@/assets/cf.png" height="19"><div class="qf"><span>{{admin.productHome}}</span></div> </div>
</div>

</router-link>
  </div>
<div class="fy">

<el-row :gutter="20" class="userindex-list">
      <el-col :span="24" class="userindex-page-box">
         <el-pagination :currentPage="currentPage" :page-sizes="[24, 48, 72, 120]" :page-size="pageSize"
                     layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange"
                     @current-change="handleCurrentChange">
      </el-pagination>
      </el-col>
    </el-row>
    </div>
        </div>
        </div>
      </div>

    </div>
    <Spin size="large" fix v-if="isLoading"></Spin>
    </div>
</template>

<script>
import Search from '@/components/Search';
import GoodsListNavs from '@/components/nav/GoodsListNavs';
import GoodsClassNav from '@/components/nav/GoodsClassNav';
import store from '@/store/index';
// eslint-disable-next-line no-unused-vars
import { mapState, mapActions } from 'vuex';
export default {
  name: 'GoodsList',
  beforeRouteEnter (to, from, next) {
    window.scrollTo(0, 0);
    next();
  },
  data () {
    return {
      ids: this.$store.state.userInfo.id,
      product: [],
      admind: [],
      price1: '',
      price2: '',
      list1: [],
      sort: true, // 排序
      id: this.$route.params.id,
      search: '',
      currentPage: 1,
      pageSize: 24,
      total: 0,
      admin: [],

      searchItem: '',
      isAction: [ true, false, false ],
      icon: [ 'arrow-up-a', 'arrow-down-a', 'arrow-down-a' ],
      goodsTool: [
        {title: '综合', en: 'sale'},
        {title: '评论数', en: 'remarks'},
        {title: '价格', en: 'price'}
      ]
    };
  },
  computed: {
    ...mapState(['userInfo', 'shoppingCart']),
    filteredBlogs: function () {
      return this.admin.filter((admin) => {
        return admin.productName.match(this.search);
      });
    }

  },
  methods: {

    Onclick () {
      this.admin = [];
      if (!this.price1 && !this.price2) {
        location.reload();
      } else {
        this.list1.forEach((ele) => {
          if (this.price1 <= ele.productPrice && ele.productPrice <= this.price2) {
            this.admin.push(ele);
          }
        });
      }
    },
    index () {
      // eslint-disable-next-line no-unused-expressions
      this.cc;
      // 排序
      this.sort = !this.sort;
      if (this.sort) {
        this.admin.sort((a, b) => {
          return a.productPrice - b.productPrice;
        });
      } else {
        this.admin.sort((a, b) => {
          return a.productPrice - b.productPrice;
        });
      }
    },
    index1 () {
      this.sort = !this.sort;
      if (this.sort) {
        this.admin.sort((a, b) => {
          return b.productPrice - a.productPrice;
        });
      } else {
        this.admin.sort((a, b) => {
          return b.productPrice - a.productPrice;
        });
      }
    },

    handleSizeChange (pageSize) {
      // 改变每页的个数触发
      this.pageSize = pageSize;
      this.lode();
    },
    handleCurrentChange (pageNum) {
      // 改变当前页码触发
      this.currentPage = pageNum;
      this.lode();
    },

    loded () {
      // eslint-disable-next-line no-undef
      axios
        .get('http://localhost:8888/info/page', {
          params: {
            pageNum: this.currentPage,
            pageSize: this.pageSize,
            search: this.search
          }
        })
        .then((res) => {
          console.log(res);
          this.admind = res.data.data.records;
          this.total = res.data.data.total;
        });
    },

    onSubmits (admin) {
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

    lode () {
      // eslint-disable-next-line no-undef
      axios
        .get('http://localhost:8888/info/' + this.id, {
          params: {
            pageNum: this.currentPage,
            pageSize: this.pageSize,
            search: this.search
          }
        })
        .then((res) => {
          console.log(res);
          this.admin = res.data.data.records;

          this.list1 = res.data.data.records;
          this.total = res.data.data.total;
        });
    },

    orderBy (data, index) {
      console.log(data);
      this.icon = [ 'arrow-down-a', 'arrow-down-a', 'arrow-down-a' ];
      this.isAction = [ false, false, false ];
      this.isAction[index] = true;
      this.icon[index] = 'arrow-up-a';
      this.SET_GOODS_ORDER_BY(data);
    }
  },
  created () {
    this.loded();
    this.lode();
    this.loadGoodsList();
  },
  mounted () {
    this.searchItem = this.$route.query.sreachData;
  },
  components: {
    Search,
    GoodsListNavs,
    GoodsClassNav
  },
  store
};
</script>

<style scoped>
.container {
  margin: 15px auto;
  width: 93%;
  min-width: 1000px;
}
.text-danger {
  color: #A94442;
}
.seckill-price{
  margin-right: 5px;
  font-size: 25px;
  font-weight: bold;
}
.goods-box {
  display: flex;
}
.as-box {
  width: 200px;
  height: 2900px;
  border: 1px solid #ccc;
}
.item-as-title{
  width: 100%;
  height: 36px;
  color: #B1191A;
  line-height: 36px;
  font-size: 18px;
}
.item-as-title span:first-child{
  margin-left: 20px;
}
.item-as-title span:last-child{
  float: right;
  margin-right: 15px;
  font-size: 10px;
  color: #ccc;
}
.item-as{
  width: 160px;
  margin: 18px auto;
}
.item-as-img{
  width: 160px;
  height: 160px;
  margin: 0px auto;
}
.item-as-price span{
  font-size: 18px;
}
.item-as-intro{
  margin-top: 5px;
  font-size: 12px;
}
.item-as-selled{
  margin-top: 5px;
  font-size: 12px;
}
.item-as-selled span{
  color: #005AA0;
}
.goods-list-box {
  margin-left: 15px;
  width: calc(100% - 215px);
}

.goods-list-tool ul{
  padding-left: 15px;
  list-style: none;
}
.goods-list-tool li{
  cursor: pointer;
  float: left;
}
.goods-list-tool span{
  padding: 5px 8px;
  border: 1px solid #ccc;
  border-left: none;
  line-height: 36px;
  background-color: #fff;
}
.goods-list-tool span:hover{
  border: 1px solid #E4393C;
}
.goods-list-tool i:hover{
  color: #E4393C;
}

.goods-show-num span{
  color: #005AA0;
  font-weight: bold;
}

.ff {
  width: 100%;
  height: 100%;
  margin: 0 auto;
}

.sss {
  width: 300px;
  height: 430px;
  margin-top: 40px;
  margin-left: 80px;
  float: left;
  box-shadow: 0 4px 8px rgba(0, 0, 0, .22), 0 0 12px rgba(0, 0, 0, .14)
}

.ss {
  width: 1000%;
}
.containers {
  padding-top: 15px;
  margin: 0px auto;
  margin-bottom: 15px;
  width: 700px;
}

.input-with-select {
  width: 700px;
  background-color: #fff;
}
.swq {
  color: rgb(238, 82, 25);
}
.gt {
  margin-left: 13px;
  margin-top: 22px;
}
.rt {
  float: right;
}
.bv {
  float: left;
  margin-top: 11px;
  margin-right: 84px;
}
.qf {
  margin-right: 13px;
  float: right;
}
.fy {
  margin-left: 500px;
  padding-top: 3000px;
  margin-bottom: 100px;
}

.sss .ee{
  position: relative;
  overflow: hidden;
}

.sss:hover{
  border: 2px solid #ee7546;
 box-shadow: 0 4px 8px #ee7546, 0 0 12px #ee7546
}

.item-as-intro:hover{
  color: #ee7546;

}
.box_top{

 margin-left: 1280px;

}
.ghh{
  float: left;
  margin-left: 900px;
}

.ghh .li{
  float: right;
  margin-left: 30px;

}
.ghh .jjs{
  float: left;
  width: 80px;

}
.ghh .jjss{
  float: left;
  width: 80px;
  margin-left: 8px;
}
.jjss1{
  margin-top: 10px;
  padding-left: 15px;
  float: left;
}
.el-icon-top{
  font-weight: bold;
}
.el-icon-bottom{
  font-weight: bold;
}
.li{
  font-weight: bold;
}
.tyt{

      height: 100%;
      width: 100%;
      border-radius: 50%;
      font-size: 13px;
      background-color: #f2f5f6;
      box-shadow: 0 0 6px rgba(0,0,0, .12);
      text-align: center;

      color: #1989fa;
      padding: 4px;}
</style>
