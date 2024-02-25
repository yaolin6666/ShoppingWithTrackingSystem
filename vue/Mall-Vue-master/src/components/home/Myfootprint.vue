<template>
<div class="er">
  <el-backtop  :bottom="10" :right="0">
  <div
      class="uii"
    >
      返回顶部
    </div>
 </el-backtop>
  <!-- <div class="content-time" v-for="todo in Data" :key="todo.imgId">
            <div class="time">
                <p >{{todo.createTime}}</p>
            </div>
            <div class="mui-card" :key="item.id" v-for="item in todo.subList">
                <div class="mui-card-content ">
                    <div class="left">
                        <p><img :src="item.productImage" width="200" height="200"></p>

                    </div>
                </div>
            </div>
        </div> -->

  <div class="ghg">
    <div class="ghg1">
      <span>
        我的足迹
      </span>
    </div>
    <div class="ghg2">
      <el-input size="small" placeholder="请输入商品名称" v-model="search">
        <el-button slot="append" icon="el-icon-search" @click="lode"></el-button>
        </el-input>
    </div>
  </div>
  <div class="yy" >

    <div class="shopping-cart-null" v-show="admin.length <= 0">
                <Icon type="ios-cart-outline" class="cart-null-icon"></Icon>
                <span>你的足迹没有空空哦</span>
                <span>赶快去看一看商品吧~</span>
              </div>
              <div class="shopping-cart-list" v-show="admin.length > 0">
    <div class="ii"  v-for="todo in Data" :key="todo.imgId">


<div class="time">
                <div class="times"><span style=" color: black;font-weight: bold;font-size: 27px;">{{todo.createTime.substring(0,10)}}</span>
                   <span style="font-size: 14px;margin-left:14px">{{todo.subList.length}}件宝贝</span>

                  <el-divider ></el-divider>

                </div>
            </div>
      <div class="ww" v-for="item in todo.subList" :key="item.imgId">

        <div class="ee" >
          <!-- <div>{{item.createTime}}</div> -->
<p><img :src="item.productImage" width="200" height="200"></p>
        <div class="more" @click="goodsDelete(item.imgId)"><i  class="el-icon-delete" ></i></div>


<router-link v-bind:to="'/goodsDetail/'+item.productId" style="color: #999;">




      <div class="rr"><span style="font-weight:bold;color:red;font-size:15px;">￥{{item.productPrice}}</span >

       <div class="qq" @click="onSubmits(item)"><p>{{item.productName.substring(0, 22)}}...</p></div>

      </div>

      </router-link>
</div>

 </div>

    </div>

    <div class="uu"> </div>

</div>
<div class="tt">
 <el-row :gutter="20" class="userindex-list">
      <el-col :span="24" class="userindex-page-box">
         <el-pagination :currentPage="currentPage" :page-sizes="[80, 160, 240, 320, 400, 480, 560, 640, 720, 800, 880, 960, 1140]" :page-size="pageSize"
                     layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange"
                     @current-change="handleCurrentChange">
      </el-pagination>
      </el-col>
    </el-row>
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
      Data:[],
      product:[],
      search: '',
     currentPage: 1,
      pageSize: 80,
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
              console.log(this.this.product)
            });

      },
  // some(){
  //   let newArr = [];
  //               //通过forEach循环数组
  //               this.dataAll.forEach((item, i) => {
  //                   let index = -1;
  //                   //然后在跑到这里筛选 根据不同的时间放置不同的数组    some（）用来查找数组中是否存在某个值  如果存在 就return true
  //                   let isExists = newArr.some((newItem, j) => {
  //                       if(item.createTime == newItem.createTime) {
  //                           index = j;
  //                           return true;
  //                       }
  //                   })
  //                   //代码是先跑这里的if条件判读
  //                   if(!isExists) {
  //                       newArr.push({
  //                           createTime: item.createTime,
  //                           subList: [item]
  //                       })
  //                   } else {
  //                       newArr[index].subList.push(item);
  //                   }
  //                   this.Data = newArr
  //               })

  // },



       goodsDelete(imgId) {
      let _this = this;
      this.$confirm("是否确定要删除", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          axios
            .delete("http://localhost:8888/img/delete/" + imgId)
            .then(function (response) {
              if (response.data) {
                _this.$alert("删除成功!", "删除足迹", {
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
      axios.get("http://localhost:8888/img/finds/" +this.id, {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
         search: this.search,
        }
      }).then(res => {
        console.log(res);
        this.admin = res.data.data.records
        this.total = res.data.data.total
        //  this.admin.sort((a,b)=>{
        //         return a.imgId<b.imgId;
        //     })

              let newArr = [];
                //通过forEach循环数组
                this.admin.forEach((item, i) => {
                    let index = -1;
                    //然后在跑到这里筛选 根据不同的时间放置不同的数组    some（）用来查找数组中是否存在某个值  如果存在 就return true
                    let isExists = newArr.some((newItem, j) => {
                        if(item.createTime == newItem.createTime) {
                            index = j;
                            return true;
                        }
                    })
                    //代码是先跑这里的if条件判读
                    if(!isExists) {
                        newArr.push({
                            createTime: item.createTime,
                            subList: [item]
                        })
                    } else {
                        newArr[index].subList.push(item);
                    }
                    this.Data = newArr

                    console.log(this.Data)
                })



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
.er{
  background: #f7f6f6;
  width: 100%;
  height: 100%;
}
.yy{

  margin-left: 200px;
  width: 1500px;
  height: 100%;
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
  background: #fff;
  height: 280px;
  float: left;
  margin-left: 80px;
  margin-bottom: 20px;
  margin-top: 20px;
}
.rr{
  margin-top: 7px;
}
.tt{
  padding-top: 6500px;
  padding-bottom: 200px;
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
.qq:hover{
  color:red;
}
.ww .ee {
  position: relative;
  /* overflow: hidden; */
}
.ww .ee:hover .more{
  display: inline;


}
.ww .ee .more{
  display: none;
  position: absolute;
  top: 0px;
  width: 45px;
  right: 0px;
  height: 50px;
  background:rgb(48, 47, 47);
  opacity: 0.8;
  transition: 0.3s;


}
.el-icon-delete{
  font-size: 33px;
  line-height: 50px !important;
  padding-left: 5px;
  color: #fff;
}
.ww .ee .more:hover{
  background: #ee7546;
  opacity: 1;
  transition: 0.3s;

}
.ghg{
  width: 100%;
  height: 70px;
  background: #FF4400;

}
.ghg1{
  padding-left: 280px;
  padding-top: 15px;
  font-size: 25px;
  font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
  color: #fff;
  font-weight: bold;
  float: left;

}
.ghg2{
  width: 350px;
  float: right;
  margin-right: 300px;
  padding-top: 20px;
}
.left{
  float: left;
}
.content-time{
  width: 1000px;
}
.time{

  width: 1420px;
  float: right;

}
.times{

  padding-top: 100px;
  padding-right: 100px;

}
.ww:hover{
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)
}
.uii{

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
