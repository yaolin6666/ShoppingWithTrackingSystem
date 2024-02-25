<template>
  <div>
    <div class="item-intro-show">
      <div class="item-intro-recommend">
        <div class="item-recommend-title">
          <p>商品推荐</p>
        </div>
        <div class="item-intro-recommend-column">
          <div
            class="item-recommend-column"
            v-for="(item, index) in adminss"
            :key="index"
            @click="jh"
          >
            <router-link
              v-bind:to="'/goodsDetail/' + item.productId"
              v-if="index"
            >
              <div class="item-recommend-img">
                <img :src="item.productImage" alt="" />
              </div>
              <div class="item-recommend-intro">
                <span>
                  <span class="item-recommend-top-num"
                    >{{ item.productName.substr(0, 45) }}...</span
                  >

                  <p class="item-recommend-price">￥{{ item.productPrice }}</p>
                </span>
              </div>
            </router-link>
          </div>
        </div>
      </div>
      <div class="item-intro-detail" ref="itemIntroDetail" :data="admin">
        <div class="item-intro-nav item-tabs">
          <Tabs>
            <TabPane label="商品介绍">
              <div class="remarks-title">
                <span>商品介绍</span>
              </div>
              <div class="item-intro-img" ref="itemIntroGoods">
                <div style="margin-top: 35px">
                  <img :src="admin.productIm" width="900px" height="300" />
                </div>
                <div style="margin-top: 35px">
                  <img :src="admin.productImd" width="900px" height="700" />
                </div>
                <div style="margin-top: 35px">
                  <img :src="admin.productImf" width="900px" height="700" />
                </div>
                <div style="margin-top: 35px">
                  <img :src="admin.productImg" width="900px" height="700" />
                </div>
              </div>
            </TabPane>
            <TabPane label="规格参数">
              <div class="remarks-title">
                <span>规格参数</span>
              </div>
              <div class="item-param-container">
                <div
                  style="
                    padding-left: 15px;
                    font-size: 20px;
                    font-weight: bolder;
                    color: #e4393c;
                  "
                >
                  商品描述
                </div>
                <p
                  style="
                    padding-left: 15px;
                    padding-top: 5px;

                    font-size: 14px;
                    color: #999;
                  "
                >
                  {{ admin.descript }}
                </p>
                <div
                  style="
                    padding-left: 15px;
                    font-size: 20px;
                    font-weight: bolder;
                    color: #e4393c;
                  "
                >
                  商品重量
                </div>
                <p
                  style="
                    padding-left: 15px;
                    padding-top: 5px;

                    font-size: 14px;
                    color: #999;
                  "
                >
                  {{ admin.weight }}g
                </p>
              </div>
              <div style="margin-top: 35px">
                <img :src="admin.productMd" width="900px" height="700" />
              </div>
              <div style="margin-top: 35px">
                <img :src="admin.productMf" width="900px" height="700" />
              </div>
              <div style="margin-top: 35px">
                <img :src="admin.productMg" width="900px" height="700" />
              </div>
            </TabPane>
            <TabPane label="售后保障">
              <ShowProductWarranty></ShowProductWarranty>
            </TabPane>
            <TabPane label="商品评价">
              <div class="remarks-container">
                <div class="remarks-title">
                  <span>商品评价</span>
                </div>
                <!-- <el-input placeholder="请输入内容" v-model="search"  class="input-with-select">

    <el-button slot="append" icon="el-icon-search" @click="lode()"></el-button>
      </el-input> -->
                <div class="remarks-analyse-box">
                  <div class="remarks-analyse-goods" v-show="!totalSumAll">
                    <i-circle :percent="totalSumAll" stroke-color="#e4393c">
                      <span class="remarks-analyse-num">0%</span>
                      <p class="remarks-analyse-title">好评率</p>
                    </i-circle>
                  </div>
                  <div class="remarks-analyse-goods" v-show="!!totalSumAll">
                    <i-circle :percent="totalSumAll" stroke-color="#e4393c">
                      <span class="remarks-analyse-num"
                        >{{ totalSumAll }}%</span
                      >
                      <p class="remarks-analyse-title">好评率</p>
                    </i-circle>
                  </div>
                  <div class="remarks-analyse-tags">
                    <!-- <Tag
                      checkable
                      :color="tagsColor[index % 4]"
                      v-for="(item, index) in tog"
                      :key="item.name"

                      ><span @click="ki(item.name);">{{ item.name }}</span></Tag
                    > -->
                    <Tag
                      checkable
                      :color="tagsColor[index % 4]"
                      v-for="(item, index) in goodsInfo.remarks.remarksTags"
                      :key="index"
                      >{{ item }}</Tag
                    >
                  </div>
                </div>
                <div
                  class="shopping-cart-null"
                  v-show="filteredBlogs.length <= 0"
                >
                  <Icon type="ios-cart-outline" class="cart-null-icon"></Icon>
                  <span>商品的评价没有空空哦</span>
                  <span>赶快去购买商品评价吧~</span>
                </div>
                <div
                  class="shopping-cart-list"
                  v-show="filteredBlogs.length > 0"
                >
                  <div class="remarks-bar">
                    <span>好评({{ totalSumAllsss }})</span>
                    <span>中评({{ totalSumAllss }})</span>
                    <span>差评({{ totalSumAlls }})</span>
                  </div>
                  <div
                    class="remarks-box"
                    v-for="(item, index) in filteredBlogs"
                    :key="index"
                  >
                    <div class="remarks-user">
                      <Avatar :src="item.avatar" />
                      <span v-show="!item.customerName"> 用户匿名 </span>
                      <span
                        class="remarks-user-name"
                        v-show="!!item.customerName"
                        >{{ item.customerName }}</span
                      >
                    </div>
                    <div class="ee">{{ item.createTime }}</div>
                    <!-- <div class="remarks-content-box">
                    <p>
                      <Rate
                        disabled
                        :value="item.values"
                        allow-half
                        class="remarks-star"
                      ></Rate>
                    </p>
                    <p class="remarks-content">{{ item.commentNr }}</p>
                    <p class="remarks-sub">
                      <span class="remarks-item">{{ item.productColor }}</span>
                      <span class="remarks-time">{{ item.productNum }}</span>
                    </p>
                  </div> -->

                    <div class="jj">
                      <p class="remarks-sub"></p>
                      <p v-show="!item.commentNr">该用户默认好评！</p>
                      <p class="remarks-content" v-show="!!item.commentNr">
                        {{ item.commentNr }}
                      </p>
                      <div v-show="!item.commentPic"></div>
                      <div
                        class="demo-image__preview"
                        v-show="!!item.commentPic"
                      >
                        <el-image
                          style="width: 80px; height: 80px"
                          :src="item.commentPic"
                          :preview-src-list="srcList"
                          @click="vbs()"
                        >
                        </el-image>
                      </div>
                      <div class="kk">
                        <span class="remarks-item"
                          >购买颜色：{{ item.productColor }}</span
                        >

                        <span class="remarks-time"
                          >购买数量：{{ item.productNum }}</span
                        >
                        <div class="mm">
                          <el-rate
                            v-model="item.commentPf"
                            disabled
                            :label-width="formLabelWidth"
                          ></el-rate>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="remarks-page">
                  <el-row :gutter="20" class="userindex-list">
                    <el-col :span="24" class="userindex-page-box">
                      <el-pagination
                        :currentPage="currentPage"
                        :page-sizes="[12, 24, 36, 48]"
                        :page-size="pageSize"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total="total"
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                      >
                      </el-pagination>
                    </el-col>
                  </el-row>
                </div>
              </div>
            </TabPane>
          </Tabs>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ShowProductWarranty from "@/components/goodsDetail/ShowProductWarranty";
import store from "@/vuex/store";
import { mapState } from "vuex";
export default {
  name: "ShowGoodsDetail",
  data() {
    return {
      adminss: [],
      search: "",
      currentPage: 1,

      pageSize: 12,
      total: 0,
      id: this.$route.params.id,
      admin: [],
      admins: [],
      srcList: [],
      tagsColor: ["blue", "green", "red", "yellow"],

      tog: [{ name: "ni" }, { name: "hh" }],
    };
  },
  computed: {
    //  totalxt(){
    //    let totalxt =0;
    //     for (var i=0; i<this.admins.length; i++) {
    //       for (var j=i+1; j<this.admins.length; j++) {
    //           if (this.admins[i].customerName == this.admins.customerName) {
    //               totalxt++

    //           }

    //       }
    //       return totalxt;
    //   }
    //  },

    filteredBlogs: function () {
      return this.admins.filter((admins) => {
        return admins.customerName.match(this.search);
      });
    },
    totalSumAll() {
      let totalSumAll = 0;

      this.admins.map((item) => {
        totalSumAll += item.commentPf;
      });

      totalSumAll = Math.floor((totalSumAll / (this.admins.length * 5)) * 100);
      return totalSumAll;
    },
    totalSumAlls() {
      let totalSumAlls = 0;

      this.admins.map((item) => {
        if (2 > item.commentPf) {
          totalSumAlls++;
        } else {
          totalSumAlls == 0;
        }
      });
      return totalSumAlls;
    },

    totalSumAllss() {
      let totalSumAllss = 0;

      this.admins.map((item) => {
        if (4 > item.commentPf && item.commentPf >= 2) {
          totalSumAllss++;
        } else {
          totalSumAllss == 0;
        }
      });
      return totalSumAllss;
    },

    totalSumAllsss() {
      let totalSumAllsss = 0;

      this.admins.map((item) => {
        if (6 > item.commentPf && item.commentPf >= 4) {
          totalSumAllsss++;
        } else {
          totalSumAllsss == 0;
        }
      });
      return totalSumAllsss;
    },

    ...mapState(["goodsInfo"]),
  },
  methods: {
    ki(name) {
      console.log(name);
      this.search = name;
    },

    jh() {
      location.reload();
    },

    lodes() {
      axios.get("http://localhost:8888/info/page").then((res) => {
        console.log(res);
        this.adminss = res.data.data.records;
      });
    },

    handleSizeChange(pageSize) {
      //改变每页的个数触发
      this.pageSize = pageSize;
      this.lode();
    },
    handleCurrentChange(pageNum) {
      //改变当前页码触发
      this.currentPage = pageNum;
      this.lode();
    },
    lode() {
      axios
        .get("http://localhost:8888/comment/find/" + this.id, {
          params: {
            pageNum: this.currentPage,
            pageSize: this.pageSize,
            search: this.search,
          },
        })
        .then((res) => {
          console.log(res);
          this.admins = res.data.data.records;
          this.total = res.data.data.total;
        });
    },

    //   vbs() {
    //          this.srcList = this.admins;
    //      console.log('sli',this.srcList);
    //      },
    // changeHeight() {
    //   let heightCss = window.getComputedStyle(this.$refs.itemIntroGoods).height;
    //   console.log(heightCss);
    //   heightCss = parseInt(heightCss.substr(0, heightCss.length - 2)) + 89;
    //   this.$refs.itemIntroDetail.style.height = heightCss + "px";
    // },
  },
  created() {
    // let sum = 0;
    // this.admins.forEach((item) => {
    //    //遍历prodAllPrice这个字段，并累加
    //    sum += item.commentPf;
    //  });
    // //返回
    // this.sum = sum;
    this.lodes();
    this.lode();
    const _this = this;

    this.$axios
      .get("http://localhost:8888/info/find/" + this.id)
      .then(function (resp) {
        _this.admin = resp.data;

        console.log(resp);
      });
    // this.$axios
    // .get("http://localhost:8888/comment/find/" + this.id)
    // .then(function (resp) {
    //   _this.admins = resp.data.data;

    //   console.log(resp);
    // });
  },
  updated() {
    this.$nextTick(() => {
      setTimeout(this.changeHeight, 100);
      setTimeout(this.changeHeight, 1000);
      setTimeout(this.changeHeight, 3000);
      setTimeout(this.changeHeight, 5000);
      setTimeout(this.changeHeight, 10000);
      setTimeout(this.changeHeight, 15000);
      setTimeout(this.changeHeight, 20000);
      setTimeout(this.changeHeight, 25000);
      setTimeout(this.changeHeight, 30000);
      setTimeout(this.changeHeight, 50000);
    });
  },
  components: {
    ShowProductWarranty,
  },
  store,
};
</script>

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
.remarks-analyse-goods {
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
