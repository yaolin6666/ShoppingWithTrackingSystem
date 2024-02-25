<template>
  <div class="yy">
    <div class="shopping-cart-null" v-show="admin.length <= 0">
                <Icon type="ios-cart-outline" class="cart-null-icon"></Icon>
                <span>你的收藏没有空空哦</span>
                <span>赶快去收藏商品吧~</span>
              </div>
              <div class="shopping-cart-list" v-show="admin.length > 0">
    <div class="ii" >
    <div :data="admin" v-for="item in admin" :key="item.collectionId" @click="onSubmits(item)">

      <div class="ww" >
        <router-link v-bind:to="'/goodsDetail/'+item.productId">
        <p><img :src="item.productImage" width="200" height="200"></p>
      <div class="qq"><p>{{item.productName.substring(0, 22)}}...</p></div>
       </router-link>
      <div class="rr"><span style="font-weight:bold;color:red;font-size:15px;">￥{{item.productPrice}}</span >

      <span style="margin-left: 10px;font-weight:bold;">{{item.productColor}}</span>

      <span style="float:right" @click="goodsDelete(item.collectionId)"><Icon type="trash-a"></Icon> 删除</span>
      </div>

 </div>

    </div>
    <div class="uu"> </div>
 <div class="tt">
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
              </div>
</div>

</template>

<script>
import store from '@/store/index';
import { mapState, mapActions } from 'vuex';
export default {
  name: 'MyShoppingCart',
  data () {
    return {
      product:[],
      search: '',
     currentPage: 1,
      pageSize: 18,
      total: 0,
      admin:[],
       id:this.$store.state.userInfo.id,

    };
  },
  created () {
    this.lode()
    this.loadShoppingCart();
  },
  computed: {
 ...mapState(['userInfo', 'shoppingCart'])
  },
  methods: {

onSubmits(admin) {
   admin.customerId=this.id
      this.product=admin;


          let _this = this;
          axios
            .post("http://localhost:8888/img/add", this.product)
            .then(function (response) {
              console.log(this.product)
            });

      },


       goodsDelete(collectionId) {
      let _this = this;
      this.$confirm("是否确定要删除", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          axios
            .delete("http://localhost:8888/collection/delete/" + collectionId)
            .then(function (response) {
              if (response.data) {
                _this.$alert("删除成功!", "删除收藏", {
                  confirmButtonText: "确定",
                  callback: (action) => {
                    //跳转到 /table
                    location.reload();
                  },
                });
              }
            });
        })
        .catch(() => {});
    },

    lode() {
      axios.get("http://localhost:8888/collection/finds/" +this.id, {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,

        }
      }).then(res => {
        console.log(res);
        this.admin = res.data.data.records
        this.total = res.data.data.total
      })

    },





    handleSizeChange(pageSize) {//改变每页的个数触发
      this.pageSize = pageSize
      this.lode()

    },
    handleCurrentChange(pageNum) {//改变当前页码触发
      this.currentPage = pageNum
      this.lode()

    },

    goTo () {
      this.$router.push('/order');
    }
  },
store
};
</script>

<style scoped>
.yy{
  width: 100%;
  height: 1000px;
}
.go-to {
  margin: 15px;
  display: flex;
  justify-content: flex-end;
}
.qq{
   width: 200px;


}
.ww{

  float: left;
  margin-left: 50px;
  margin-bottom: 20px;
}
.rr{
  margin-top: 7px;
}
.tt{
  padding-top: 1000px;
  margin-left: 470px;
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
.qq:hover{
  color:red;
}
</style>
