<template>
  <div>

    <div class="item-detail-show">
      <el-backtop :bottom="10" :right="0">
        <div
          class="hyy"
        >
          返回顶部
        </div>
      </el-backtop>
      <div class="item-detail-left">
        <div class="item-detail-big-img">
          <img :src="admin.productImage" height="350px">
        </div>
        <div class="item-detail-img-row">
          <div
            class="item-detail-img-smalls">
          </div>
        </div>
      </div>
      <div class="item-detail-right" :data="admin">
        <div class="item-detail-title">
          <p>
            <span class="item-detail-express" style="word-wrap:break-word;word-break:break-all;width:600px;">校园配送</span>
            {{ admin.productName }}
          </p>
        </div>
        <div class="item-detail-price-row">
          <div class="item-price-left">
            <div class="item-price-row">
              <p>
                <span class="item-price-title">价格</span>
                <span class="item-price">￥{{ admin.productPrice}}</span>
              </p>
            </div>
          </div>
          <div class="item-price-right">
            <div class="item-remarks-sum">
              <p>累计评价</p>
              <p>
                <span class="item-remarks-num"
                >{{ total }} 条</span
                >
              </p>
            </div>
          </div>
        </div>
        <!-- 选择颜色 -->
        <div class="item-select">
          <div class="item-select-title">
            <p>选择颜色</p>
          </div>
          <div class="item-select-column">
            <el-radio-group v-model="admin.productColor" size="medium">
              <el-radio-button v-show="admin.productCs" :label="admin.productCs"
                               style="border: 1px solid silver;"></el-radio-button>
              <el-radio-button v-show="admin.productCd" style="margin-left:30px;border: 1px solid silver;"
                               :label="admin.productCd"></el-radio-button>
              <el-radio-button v-show="admin.productCf" style="margin-left:31px;border: 1px solid silver;"
                               :label="admin.productCf"></el-radio-button>
              <el-radio-button v-show="admin.productCg" style="margin-left:30px;border: 1px solid silver;"
                               :label="admin.productCg"></el-radio-button>
            </el-radio-group>
          </div>
        </div>
        <p style="margin-top: 21px;"><span style="color: rgb(155, 154, 154);">数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量</span>
          <sapn style="margin-left:27px">
            <el-input-number v-model="admin.productNum" @change="handleChange" :min="1"
                             :max="admin.productMnum"></el-input-number>
          </sapn>
        </p>
        <br/>
        <div class="add-buy-car-box">
          <div class="add-buy-car">
            <Button type="error" size="large" @click="onSubmit('fruitRules')"
            >加入购物车
            </Button
            >
            <span style="margin-left: 30px;">
            <Button type="error" size="large" @click="onSubmits('fruitRules')"
            >收藏</Button
            >
            </span>
          </div>
        </div>
        <el-popover
          placement="top"
          width="1000"
          trigger="click"> -->

          <el-form
            ref="fruitRules"
            :model="admin"
            :rules="rules"
            label-width="300px"
            class="demo-ruleForm"
            style="width: 600px"
          >
            <el-form-item
              label="商品Id"
              prop="productId"
            >
              <el-input v-model="admin.productId">{{admin.productId}}</el-input>
            </el-form-item>
            <el-form-item
              label="商品名称"
              prop="productName"
            >
              <el-input v-model="admin.productName">{{admin.productName}}</el-input>
            </el-form-item>
            <el-form-item
              label="商品总数量"
              prop="productMnum"
            >
              <el-input v-model="admin.productMnum">{{admin.productMnum}}</el-input>
            </el-form-item>
            <el-form-item label="颜色">
              <el-radio-group v-model="admin.productColor" size="medium">
                <el-radio border :label="admin.productCs"></el-radio>
                <el-radio border :label="admin.productCd"></el-radio>
                <el-radio border :label="admin.productCf"></el-radio>
                <el-radio border :label="admin.productCg"></el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item
              label="价格"
              prop="productPrice"
            >
              <el-input v-model="admin.productPrice">{{admin.productPrice * admin.PriceNum}}</el-input>
            </el-form-item>
            <el-form-item
              label="商品图片"
              prop="productErm"
            >
              <el-img v-model="admin.productErm" :src="admin.productErm" width="300" height="100"></el-img>
            </el-form-item>

            <el-form-item
              label="数量"
              prop="admin.productNum"
            >
              <el-input v-model="admin.productNum">{{admin.productNum}}</el-input>
            </el-form-item>
            <el-input-number v-model="admin.adminState" @change="handleChange" :min="1" :max="10"
                             label="描述文字"></el-input-number>
            <el-form-item>
              <el-button type="primary" @click="onSubmit('fruitRules')"
              >立即创建
              </el-button
              >

              <el-button>取消</el-button>
            </el-form-item>
          </el-form>
          <!-- <el-button slot="reference">click 激活</el-button> -->

        </el-popover>

      </div>
    </div>
  </div>
</template>

<script>

import store from '@/store/index';
import {mapState} from 'vuex';

export default {
  name: 'ShowGoods',
  data () {
    return {
      inject: ['reload'],
      total: 0,
      blogs: [],
      admin: [],
      price: 0,
      admins: [],
      count: 1,
      selectBoxIndex: 0,
      imgIndex: 0,
      search: '',
      id: this.$route.params.id,
      ids: this.$store.state.userInfo.id

    };
  },
  computed: {
    ...mapState(['userInfo', 'shoppingCart']),
    filteredBlogs: function () {
      return this.blogs.filter((blog) => {
        return blog.title.match(this.search);
      });
    }

  },
  methods: {
    lode () {
      // eslint-disable-next-line no-undef
      axios
        .get('http://localhost:8888/comment/find/' + this.id, {})
        .then((res) => {
          console.log(res);
          this.admins = res.data.data.records;
          this.total = res.data.data.total;
        });
    },

    handleChange (value) {
      console.log(value);
    },

    onSubmit (formName) {
      this.admin.customerId = this.ids;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let _this = this;
          // eslint-disable-next-line no-undef
          axios
            .post('http://localhost:8888/shopping/add', this.admin)
            .then(function (response) {
              if (response.data) {
                _this.$alert('添加成功！', '加入购物车', {
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

    onSubmits (formName) {
      this.admin.customerId = this.ids;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let _this = this;
          // eslint-disable-next-line no-undef
          axios
            .post('http://localhost:8888/collection/add', this.admin)
            .then(function (response) {
              if (response.data) {
                _this.$alert('收藏成功！', '收藏商品', {
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

    select (index1, index2) {
      this.selectBoxIndex = index1 * 3 + index2;
      this.price = this.goodsInfo.setMeal[index1][index2].price;
    },
    showBigImg (index) {
      this.imgIndex = index;
    },
    addShoppingCartBtn () {
      const index1 = parseInt(this.selectBoxIndex / 3);
      const index2 = this.selectBoxIndex % 3;
      const date = new Date();
      const goodsId = date.getTime();
      const data = {
        goods_id: goodsId,
        title: this.goodsInfo.title,
        count: this.count,
        package: this.goodsInfo.setMeal[index1][index2]
      };
      this.addShoppingCart(data);
      this.$router.push('/shoppingCart');
    }
  },
  mounted () {
    const father = this;
    setTimeout(() => {
      father.price = father.goodsInfo.setMeal[0][0].price || 0;
    }, 300);
  },
  created () {
    console.log(this.ids);
    this.lode();
    const _this = this;
    this.$axios
      .get('http://jsonplaceholder.typicode.com/posts')
      .then(function (resp) {
        _this.blogs = resp.data.slice(0, 10);

        console.log(resp);
      });

    this.$axios.get('http://localhost:8888/info/find/' + this.id).then(function (resp) {
      _this.admin = resp.data;

      console.log(resp);
    });
  },

  store

};
</script>

<style scoped>
  .item-detail-show {
    width: 80%;
    margin: 15px auto;
    display: flex;
    flex-direction: row;
    background-color: #fff;
  }

  .item-detail-left {
    width: 350px;
    margin-right: 30px;
  }

  .item-detail-big-img {
    width: 350px;
    height: 350px;
    box-shadow: 0px 0px 8px #ccc;
    cursor: pointer;
  }

  .item-detail-big-img img {
    width: 100%;
  }

  .item-detail-img-row {
    margin-top: 15px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }

  .item-detail-img-small {
    width: 68px;
    height: 68px;
    box-shadow: 0px 0px 8px #ccc;
    cursor: pointer;
  }

  .item-detail-img-smalls {
    width: 68px;
    height: 68px;

    cursor: pointer;
  }

  .item-detail-img-small img {
    width: 100%;
  }

  /*商品选购详情*/
  .item-detail-right {
    display: flex;
    flex-direction: column;
  }

  .item-detail-title p {
    color: #666;
    font-size: 20px;
  }

  .item-detail-express {
    font-size: 14px;
    padding: 2px 3px;
    border-radius: 3px;
    background-color: #e4393c;
    color: #fff;
  }

  /*商品标签*/
  .item-detail-tag {
    font-size: 12px;
    color: #e4393c;
  }

  /*价格详情等*/
  .item-detail-price-row {
    padding: 5px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    background-color: #f3f3f3;
    width: 1010px;
  }

  .item-price-left {
    display: flex;
    flex-direction: column;
  }

  .item-price-title {
    color: #999999;
    font-size: 14px;
    margin-right: 15px;
  }

  .item-price-row {
    margin: 5px 0px;
  }

  .item-price {
    color: #e4393c;
    font-size: 23px;
    cursor: pointer;
  }

  .item-price-full-cut {
    margin-right: 5px;
    padding: 3px;
    color: #e4393c;
    font-size: 12px;
    background-color: #ffdedf;
    border: 1px dotted #e4393c;
    cursor: pointer;

  }

  .item-remarks-sum {
    padding-left: 8px;
    border-left: 1px solid #ccc;
  }

  .item-remarks-sum p {
    color: #999999;
    font-size: 12px;
    line-height: 10px;
    text-align: center;
  }

  .item-remarks-num {
    line-height: 18px;
    color: #005eb7;
  }

  .item-select {
    display: flex;
    flex-direction: row;
    margin-top: 15px;
  }

  .item-select-title {
    color: #999999;
    font-size: 14px;
    margin-right: 15px;
  }

  .item-select-column {
    display: flex;

    flex-direction: column;
  }

  .item-select-row {
    display: flex;
    flex-direction: row;
    margin-bottom: 8px;
  }

  .item-select-box {
    display: flex;
    flex-direction: row;
    align-items: center;
  }

  .item-select-img {
    width: 36px;
  }

  .item-select-box {
    padding: 5px;
    margin-right: 8px;
    background-color: #f7f7f7;
    border: 0.5px solid #ccc;
    cursor: pointer;
  }

  .item-select-box:hover {
    border: 0.5px solid #e3393c;
  }

  .item-select-box-active {
    border: 0.5px solid #e3393c;
  }

  .item-select-img img {
    width: 100%;
  }

  .item-select-intro p {
    margin: 0px;
    padding: 5px;
  }

  .item-select-class {
    padding: 5px;
    margin-right: 8px;
    background-color: #f7f7f7;
    border: 0.5px solid #ccc;
    cursor: pointer;
  }

  .item-select-class:hover {
    border: 0.5px solid #e3393c;
  }

  .add-buy-car-box {
    width: 100%;
    margin-top: 15px;
    border-top: 1px dotted #ccc;
  }

  .add-buy-car {
    margin-top: 30px;
    margin-left: 70px;

  }

  .hyy {

    height: 100%;
    width: 100%;
    border-radius: 50%;
    font-size: 13px;
    background-color: #f2f5f6;
    box-shadow: 0 0 6px rgba(0, 0, 0, .12);
    text-align: center;

    color: #1989fa;
    padding: 4px;

  }

  /******************商品图片及购买详情结束******************/
</style>
