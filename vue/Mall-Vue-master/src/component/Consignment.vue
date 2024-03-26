<template>
  <div class="userindex">
    <div align="center" style="font-size: 20px" v-if="this.user.length<1">
      暂无此分类下订单
    </div>
    <div v-if="this.user.length>0">
      <el-backtop :bottom="10" :right="0">
        <div class="qwr">返回顶部</div>
      </el-backtop>
      <el-row :gutter="20" class="userindex-queryInfo">
        <el-col :xs="8" :sm="6" :md="6" :lg="4" :xl="4">
          <el-input
              class="userindex-queryInfo-li"
              v-model="search"
              clearable
              size="small"
              placeholder="请输入用户名称"
          ></el-input>
        </el-col>
        <el-col :xs="6" :sm="4" :md="3" :lg="2" :xl="2">
          <el-button type="primary" class="userindex-queryInfo-li" size="small" @click="lode()"
          >搜索
          </el-button
          >
        </el-col>
      </el-row>
      <el-row :gutter="20" class="userindex-list" :search="search">
        <el-col :span="24">
          <table style="margin-top:20px;" align="center">
            <div class="bb">
              <div class="ff">
                <span style="margin-left: 162px;">宝贝</span>
                <span style="margin-left: 163px;">货源号</span>
                <span style="margin-left: 106px;">单价</span>
                <span style="margin-left: 131px;">数量</span>
                <span style="margin-left: 116px;">实付款</span>
                <span style="margin-left: 58px;">交易状态</span>
                <span style="margin-left: 44px;">交易操作</span>
              </div>
            </div>
            <div class="shopping-cart-null" v-show="user.length <= 0">
              <Icon type="ios-cart-outline" class="cart-null-icon"></Icon>
              <span>你的订单没有空空哦</span>
              <span>赶快去添加商品吧~</span>
            </div>
            <div class="shopping-cart-list" v-show="user.length > 0">
              <tbody v-for="user in user" :key="user">
              <div class="vs">
                <tr>
                  <div class="sk">
                    <div class="rr"><span
                        style="font-weight:bold;margin-left: 44px;margin-top: 3px; ">{{ user.createTime }}</span></div>
                  </div>
                </tr>
                <div v-bind:to="'/goodsDetail/'+user.productId">
                  <tr>
                    <td align="center" class="kk" style="border: 1px solid #ffff;"><img :src="user.productImage"
                                                                                        width="100px" height="70px">
                    </td>
                    <td class="kk1" style="border: 1px solid #ffff;">
                      <div style="float:left;margin-left: 12px;word-wrap:break-word;word-break:break-all; width:150px">
                        <span>{{ user.productName }}</span></div>
                      <div style="float:left;margin-left: 35px;word-wrap:break-word;word-break: break-all; width:60px">
                        <span>{{ user.productColor }}</span></div>
                      <div
                          style="float:left;margin-left: 70px;word-wrap:break-word;word-break: break-all; width:85px;color:red;">
                        <span>￥{{ user.productPrice }}</span></div>
                      <div
                          style="float:right;margin-right: 18px;word-wrap:break-word;word-break: break-all; width:85px">
                        <span>{{ user.productNum }}</span></div>
                    </td>
                    <td class="kk2"><p style="margin-left: 26px;font-weight:bold;color: red;">
                      ￥{{ user.productPrice * user.productNum * (100 - user.discount) / 100 }}</p>
                      <p style="margin-left: 20px;margin-top: 5px;">({{ user.paymentMethod }})</p>
                    </td>
                    <td class="kk2"><p style="margin-left: 26px;">支付成功</p>
                      <el-button  @click="getDetail(user.orderId)">订单详情</el-button>
                    </td>
                    <td class="kk2">
                      <el-button
                          @click="updateMaster(user)">
                        确认收货
                      </el-button>
                      <router-link v-bind:to="'/ordersss/'+user.orderId"><p
                          style="margin-left: 21px;margin-top: 18px;color:red">退款/退货</p></router-link>
                    </td>
                  </tr>
                </div>
              </div>
              </tbody>
            </div>
          </table>

        </el-col>
      </el-row>

      <!-- 分页 -->
      <div class="cfs">
        <el-row :gutter="20" class="userindex-list">
          <el-col :span="24" class="userindex-page-box">
            <el-pagination :currentPage="currentPage" :page-sizes="[7, 14, 21, 28]" :page-size="pageSize"
                           layout="total, sizes, prev, pager, next, jumper" :total="total"
                           @size-change="handleSizeChange"
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
// eslint-disable-next-line no-unused-vars
import {mapState, mapActions} from 'vuex';

export default {
  name: 'Consignment',
  methods: {
    getDetail (orderId) {
      window.open('/orderDetail/' + orderId);
    },

    onSubmits (admin) {
      admin.customerId = this.id;
      this.product = admin;

      // eslint-disable-next-line no-unused-vars
      let _this = this;
      // eslint-disable-next-line no-undef
      axios
        .post('http://localhost:8888/img/add', this.product)
        .then(function (response) {
        });
    },

    lode () {
      // eslint-disable-next-line no-undef
      axios.get('http://localhost:8888/confirm/finds/' + this.id, {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {
        this.user = res.data.data.records;
        this.total = res.data.data.total;
      });
    },
    tableRowStyle ({row, rowIndex}) {
      return 'background-color:pink;font-size:15px;';
    },
    tableHeaderColor ({row, column, rowIndex, columnIndex}) {
      return 'background-color:lightblue;color:#fff;font-wight:500;font-size:20px;text-align:center';
    },
    add () {
      this.$router.push('/adduser');
    },
    edit (row) {
      this.$router.push('/edituser?userId=' + row.userId);
    },
    updateMaster (user) {
      let _this = this;
      // eslint-disable-next-line no-undef
      axios
        .post('http://localhost:8888/assess/add', user)
        .then(function (response) {
          if (response.data) {
            _this.$alert(user.productName + '成功收货', '添加数据', {
              confirmButtonText: '确定',
              callback: (action) => {
                // 跳转到/table
                location.reload();
              }
            });
          }
        });
      // eslint-disable-next-line no-undef
      axios.post('http://localhost:8888/OrderOrigin/add', {
        orderId: user.orderId,
        content: '确认收货'
      });
    },
    // 删除
    userDelete (row) {
      let _this = this;
      this.$confirm('是否确定要删除' + ' ' + row.userName + '?', '删除数据', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          // eslint-disable-next-line no-undef
          axios
            .delete('http://localhost:8888/userinfo/delete/' + row.userId)
            .then(function (response) {
              if (response.data) {
                _this.$alert(row.userName + '删除成功!', '删除数据', {
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
    handleSizeChange (pageSize) { // 改变每页的个数触发
      this.pageSize = pageSize;
      this.lode();
    },
    handleCurrentChange (pageNum) { // 改变当前页码触发
      this.currentPage = pageNum;
      this.lode();
    }
  },
  computed: {
    ...mapState(['userInfo', 'shoppingCart']),
    filteredBlogs: function () {
      return this.user.filter((user) => {
        return user.productName.match(this.search);
      });
    }
  },
  created () {
    this.lode();
  },
  data () {
    return {
      id: this.$store.state.userInfo.id,
      product: [],
      search: '',
      currentPage: 1,
      pageSize: 7,
      total: 0,
      options: [
        {
          label: 1,
          value: '安心蔬菜'
        },
        {
          label: 2,
          value: '新鲜牛奶'
        }
      ],
      tableData: [],
      user: [],
      dialogFormVisible: false,
      form: {},
      formLabelWidth: '120px'
    };
  },
  store
};
</script>

<style scoped>
.userindex {
  width: 100%;
  min-height: 100%;
  padding: 15px;
  box-sizing: border-box;

}

.userindex-queryInfo {
  margin-bottom: 10px;

}

.userindex-queryInfo-li {
  margin-left: 975px;
  width: 100%;
  height: auto;
  border: 3px solid rgb(192, 223, 238);
  background: rgb(192, 223, 238);
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
  justify-content: flex-end;

}

td {
  padding: 10px 0;
  border: 1px solid #e5e5e5;
  vertical-align: top;
  width: 50px;
}

.kk {
  width: 150px;
}

.kk1 {
  width: 600px;
}

.kk2 {
  width: 100px;
}

.sk {

  width: 1057px;
  height: 40px;
  background: rgb(206, 232, 245);
}

.rr {
  padding-top: 11px;
}

.bb {
  width: 1057px;
  height: 40px;
  background: rgb(206, 232, 245);
  margin-bottom: 40px;
}

.ff {
  padding-top: 11px;
}

.sa {
  width: 50px;
}

.vs {
  margin-bottom: 20px;
  border-radius: 5px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, .22), 0 0 12px rgba(0, 0, 0, .14)

}

.cfs {
  margin-right: 700px;
}

.shopping-cart-null {
  padding: 15px;
  display: flex;
  height: 500px;
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

.vs:hover {
  border: 2px solid #ee7546;
  box-shadow: 0 4px 8px #ee7546, 0 0 12px #ee7546
}
</style>
