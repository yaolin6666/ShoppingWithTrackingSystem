<template>
  <div class="container">
    <el-backtop  :bottom="10" :right="0">
      <div class="t1t">返回顶部</div>
    </el-backtop>
    <Search><div></div></Search>    <!-- slot的作用 -->
    <HomeNav></HomeNav>
    <div class="fixed_div1"><img src="@/assets/hb.png" width="105px" height="120px"></div>
      <div  class ="fixed_div"  style ="box-shadow: 0 4px 8px rgba(0, 0, 0, .22), 0 0 12px rgba(0, 0, 0, .14);border-radius: 15px;background: rgb(243, 197, 197);" >
        <router-link to="/orders" style="color: rgb(46, 46, 45);"  >
          <div class="oo" >
            <el-tooltip class="item" effect="dark" content="我的订单" placement="left" >
              <i class="el-icon-tickets" style="font-size:36px;"></i>
            </el-tooltip>
         </div>
        </router-link>
        <div class="oo1" >
          <el-tooltip class="item" effect="dark" content="App联系13301781673" placement="left" >
            <i class="el-icon-mobile-phone" style="font-size:36px;color: rgb(46, 46, 45);"></i>
          </el-tooltip>
        </div>
        <router-link to="/home/myShoppingCart" style="color: rgb(46, 46, 45);"  >
          <div class="oo2" >
            <el-tooltip class="item" effect="dark" content="我的收藏" placement="left" >
              <i class="el-icon-star-off" style="font-size:36px;"></i>
            </el-tooltip>
          </div>
        </router-link>
        <div class="oo3" >
          <Dropdown  placement="left">
            <i class="el-icon-copy-document" style="font-size:36px;color: rgb(46, 46, 45);"  ></i>
            <DropdownMenu slot="list">
              <p style="font-size:16px;margin-left: 18px;font-weight: bold;">下载App</p>
              <img src="@/assets/jf.png" width="100px" height="90px">
           </DropdownMenu>
         </Dropdown>
        </div>
      </div >
    <div class="content">
      <div class="seckill">
        <div class="seckill-head">
          <div class="seckill-icon">
            <img src="static/img/index/clock.png">
          </div>
          <div class="seckill-text">
            <span class="seckill-title">有好货</span>
            <span class="seckill-remarks">总有你想不到的实惠</span>
          </div>
        </div>
<div class="ff">
  <div v-for="admin in filteredBlogs.slice(0,28)" :key="admin.productId"  border class="ss"  style="width: 100%" >
     <router-link v-bind:to="'/goodsDetail/'+admin.productId" >
   <div class="sss" @click="onSubmits(admin)">
    <v-card class="mx-auto" max-width="344">
      <div class="ee" >
         <img :src="admin.productImage" width="300" height="250"/>
         <router-link v-bind:to="'/goodsList/'+admin.twoCategoryId"><div class="more"><span class="kkw1">找 相 似</span></div></router-link>
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
      <div class="gt">
        <img src="@/assets/cf.png" height="19">
        <div class="qf">
        <span>{{admin.productHome}}</span>
        </div>
      </div>
    <div class="rt">
      <router-link v-bind:to="'/promptly/'+admin.productId">
        <div class="bv">
          <img src="@/assets/mm.png">
        </div>
        <el-button  size="mini" style="width:70px; background: coral;color:#fff;margin-top:6px;margin-right:13px;"> <span style=" float:right; margin-left: 20px;" >立即购买</span></el-button>
      </router-link>
      <router-link v-bind:to="'/goodsDetail/'+admin.productId">
        <el-button  size="mini" style="width:85px; background: coral;color:#fff;margin-top:6px;margin-right:12px;"> <span style=" float:right; margin-left: 20px;" >加入购物车</span></el-button>
      </router-link>
    </div>
</div>

</router-link>
  </div>

</div>

      </div>

    </div>

  </div>

</template>

<script>
import Search from '@/components/Search';
import HomeNav from '@/components/nav/HomeNav';
import store from '@/store/index';
import { mapState } from 'vuex';
export default {
  name: 'Index',
  created () {
    this.lode();
  },
  data () {
    return {
      product: [],
      currentPage: 1,
      pageSize: 58,
      total: 0,
      admin: [],
      ids: this.$store.state.userInfo.id,
      erm: [
        {
          url: require('@/assets/f4.png')
        }
      ],
      search: '',
      src: 'https://cdn.vuetifyjs.com/images/cards/forest-art.jpg',

      setIntervalObj: null
    };
  },
  methods: {

    onSubmits (admin) {
      admin.customerId = this.ids;
      this.product = admin;
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
        .get('http://localhost:8888/info/page', {
          params: {
            pageNum: this.currentPage,
            pageSize: this.pageSize,
            search: this.search
          }
        })
        .then((res) => {
          console.log(res);
          this.admin = res.data.data.records;
          this.total = res.data.data.total;
        });
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
    }
  },
  computed: {
    filteredBlogs: function () {
      return this.admin.filter((admin) => {
        return admin.productName.match(this.search);
      });
    },
    ...mapState(['userInfo', 'shoppingCart'])
  },
  components: {
    Search,
    HomeNav
  },
  store
};
</script>

<style scoped>
.container {
  background-color: #f6f6f6;
}
.content {
  width: 1600px;
  margin: 0px auto;
}
.seckill {
  width: 100%;
  height: 2000px;
  margin: 15px auto;
  background-color: #fff;
}
.seckill-head {

  width: 100%;
  height: 50px;
  background-color: #2e659d;
}
.seckill-icon {
  width: 68px;
  height: 100%;
  float: left;
}
.seckill-icon img {
  width: 35px;
  height: 35px;
  margin-top: 6px;
  margin-left: 15px;
  animation-name: shake-clock;
  animation-duration: 0.3s;
  animation-iteration-count: infinite; /*设置无线循环*/
}
/*定义闹钟震动动画关键帧*/
@keyframes shake-clock {
  0% {
    transform: rotate(-8deg);
  }
  50% {
    transform: rotate(8deg);
  }
  100% {
    transform: rotate(-8deg);
  }
}
.seckill-text {
  width: 300px;
  height: 100%;
  float: left;
}
.seckill-text .seckill-title {
  font-size: 22px;
  line-height: 50px;
  color: #fff;
}
.seckill-text .seckill-remarks {
  margin-left: 5px;
  font-size: 10px;
  color: #fff;
}

.seckill-item-img img {
  width: 130px;
  height: 130px;
  margin-left: 15px;
  margin-top: 15px;
  transition: margin-top 0.3s;
}
.seckill-item-img:hover img {
  margin-top: 6px;
  transition: margin-top 0.3s;
}

.seckill-item-info i:first-child {
  font-size: 14px;
}

.item-class-head ul {
  list-style: none;
  float: right;
  margin-right: 30px;
  line-height: 50px;
}
.item-class-head a {
  padding: 6px;
  margin-left: 15px;
  font-size: 12px;
  background-color: #6da6be;
  border: 1px solid #6da6be;
  text-decoration: none;
  color: #fff;
}
.item-class-eat-head a {
  background-color: #eeb955;
  border: 1px solid #eeb955;
}
.item-class-head a:hover {
  border: 1px solid #fff;
}
.item-class-head li {
  float: left;
}

.item-big-img img {
  margin-left: 0px;
  transition: margin-left 0.3s;
}
.item-big-img:hover img {
  margin-left: -15px;
  transition: margin-left 0.3s;
}

.item-four-detail img {
  margin-left: 0px;
  transition: margin-left 0.3s;
}
.item-four-detail:hover img {
  margin-left: -6px;
  transition: margin-left 0.3s;
}

.item-four-detail-img img {
  margin-left: 5px;
  width: 90px;
}

.item-content-bottom-img img {
  margin-left: 0px;
  transition: margin-left 0.3s;
}
.item-content-bottom-img:hover img {
  margin-left: -15px;
  transition: margin-left 0.3s;
}
.ff {
  width: 100%;
  height: 3500px;
  margin: 0 auto;
}

.sss {
  width: 300px;
  height: 430px;
  margin-top: 40px;
  margin-left: 80px;
  float: left;
  /* border: 1px solid #d1cdcd; */
  box-shadow: 0 4px 8px rgba(0, 0, 0, .22), 0 0 12px rgba(0, 0, 0, .14)
}

.ss {
  width: 1000%;
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

.fixed_div {
            position : fixed ;
            left : 1710px ;
             top: 500px;
            width : 40px ;
            height: 230px;
         }
          .fixed_div1 {
            position : fixed ;
            left : 1680px ;
             top: 400px;
            width : 50px ;
            height: 200px;
         }
         .oo{
           width: 50px;
           padding-top: 15px;
          margin-left: 2px;
           height: 5px;

         }
          .oo1{
           width: 50px;
padding-top: 55px;

          margin-left: 2px;
           height: 5px;

         }
          .oo2{
           width: 50px;
padding-top: 55px;
          margin-left: 2px;
           height: 5px;

         }
         .oo3{
           width: 50px;
padding-top: 55px;
          margin-left: 2px;
           height: 5px;

         }

.lls img {
  display: block;
    border-radius: 12px;
}

.llss img {
  display: block;
    border-radius: 12px;
}

.qww img{
  margin-left: 15px;
}

.rff .ee{
  position: relative;
  overflow: hidden;
}
.rff .ee:hover .more{
  top:215px;

}
.rff .ee .more{
  position: absolute;
  top: 250px;
  width: 300px;
  height: 35px;
  background: #ee7546;
  opacity: 0.8;
  transition: 0.3s;

}

.rf .ee{
  position: relative;
  overflow: hidden;
}
.rf .ee:hover .more{
  top:215px;

}
.rf .ee .more{
  position: absolute;
  top: 250px;
  width: 300px;
  height: 35px;
  background: #ee7546;
  opacity: 0.8;
  transition: 0.3s;

}

.sss .ee{
  position: relative;
  overflow: hidden;
}
.sss .ee:hover .more{
  top:215px;

}
.sss .ee .more{
  position: absolute;
  top: 256px;
  width: 300px;
  height: 35px;
  background: #ee7546;
  opacity: 0.8;
  transition: 0.3s;

}

.kkw1{
  font-size: 15px;
  margin-left: 120px;
  color: #fff;
   font-family: tahoma, arial, "Hiragino Sans GB", 宋体, sans-serif;
   font-weight: bold;
}

.sss:hover{
  border: 2px solid #ee7546;
 box-shadow: 0 4px 8px #ee7546, 0 0 12px #ee7546
}

.t1t{

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
</style>
