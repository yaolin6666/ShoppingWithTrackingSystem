// 用户管理
<template>

  <div class="userindex">
    <div align="center" style="font-size: 20px" v-if="this.user.length<1">
      暂无此分类下订单
    </div>
    <div v-if="this.user.length>0">
    <el-backtop  :bottom="10" :right="0">
  <div
     class="uyu"
    >
      返回顶部
    </div>
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
        <el-button type="primary" class="userindex-queryInfo-li" size="small" style="font-weight: bold;" @click="lode()"
          >搜 索</el-button
        >
      </el-col>
      <!-- 添加按钮 -->
    </el-row>

    <!-- 检索结果 -->
    <el-row :gutter="20" class="userindex-list" :search="search">
      <el-col :span="24">
<table style="margin-top:20px;" align="center"  >
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
                <span>你的评价没有空空哦</span>
                <span>赶快去添加商品吧~</span>
              </div>
              <div class="shopping-cart-list" v-show="user.length > 0">
<tbody :data="user"  v-for="user in user" :key="user.assessId" @click="onSubmits(user)">
  <div class="vs">
 <tr >
<div class="sk" ><div class="rr"><span style="font-weight:bold;margin-left: 44px;margin-top: 3px; ">{{user.createTime}}</span></div></div>
</tr>
<div v-bind:to="'/goodsDetail/'+user.productId">
<tr  >
<td align="center" class="kk" style="border: 1px solid #ffff;"><img :src="user.productImage" width="100px" height="70px"></td>
<td   class="kk1" style="border: 1px solid #ffff;"><div style="float:left;margin-left: 12px;word-wrap:break-word;word-break:break-all; width:150px"><span >{{user.productName}}</span></div>
<div style="float:left;margin-left: 35px;word-wrap:break-word;word-break: break-all; width:60px"><span>{{user.productColor}}</span></div>
<div style="float:left;margin-left: 70px;word-wrap:break-word;word-break: break-all; width:85px;color:red;"><span >￥{{user.productPrice}}</span></div>
<div style="float:right;margin-right: 18px;word-wrap:break-word;word-break: break-all; width:85px"><span >{{user.productNum}}</span></div></td>
<td class="kk2"><p style="margin-left: 26px;font-weight:bold;color: red;">￥{{user.productPrice*user.productNum*(100-user.discount)/100}}</p>
<p style="margin-left: 20px;margin-top: 5px;">({{user.paymentMethod}})</p>
</td>
<td class="kk2"><p style="margin-left: 26px;">付款成功</p>
  <el-button  @click="getDetail(user.orderId)">订单详情</el-button>
</td>
<td class="kk2"><p style="margin-left: 25px;margin-top: 27px;">等待评价</p>
</td>
</tr>
</div>
</div>

<div style="float:right;margin-top:65px"><el-button style="color:red" @click="add(user.assessId);dialogFormVisible = true">评价</el-button></div>
</tbody>
              </div>
</table>

<el-dialog title="商品评价" :visible.sync="dialogFormVisible">
  <el-form :model="admins" ref="fruitRules" :rules="rules">
      <el-form-item
      label="商品图片"
      prop="commentPic"
      :label-width="formLabelWidth"
    >
     <el-upload
  class="upload-demo"
  action="http://localhost:8888/files/uplode"
  :on-preview="handlePreview"
  :on-remove="handleRemove"
  :file-list="fileList"
  :on-success="filesUplodeSeccess"
  list-type="picture">
  <el-button size="small" type="primary">点击上传</el-button>

</el-upload>
    </el-form-item>
     <el-form-item label="评价内容" :label-width="formLabelWidth" prop="commentNr">
     <el-input v-model="admins.commentNr"  type="textarea"
  :rows="2"
  placeholder="请输入评价内容"></el-input>
    </el-form-item>
    <el-form-item label="商品评分" :label-width="formLabelWidth" prop="commentPf">
      <el-rate v-model="admins.commentPf" :label-width="formLabelWidth"></el-rate>
    </el-form-item>
  </el-form>

  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="dialogFormVisible = false;onSubmit('fruitRules');goodsDelete()">确 定</el-button>
  </div>
</el-dialog>

      </el-col>
    </el-row>

    <!-- 分页 -->
    <div class="cfs">
    <el-row :gutter="20" class="userindex-list">
      <el-col :span="24" class="userindex-page-box">
         <el-pagination :currentPage="currentPage" :page-sizes="[7, 14, 21, 28]" :page-size="pageSize"
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
import { mapState } from 'vuex';
export default {
  name: 'Assess',

  methods: {
    getDetail (orderId) {
      window.open('/orderDetail/' + orderId);
    },
    ju () {
      const _this = this;

      this.$axios
        .get('http://localhost:8888/account/find/' + this.id)
        .then(function (resp) {
          _this.admind = resp.data;
        });
    },

    onSubmits (admin) {
      admin.customerId = this.id;
      this.product = admin;
      // eslint-disable-next-line no-undef
      axios
        .post('http://localhost:8888/img/add', this.product)
        .then(function (response) {
        });
    },

    filesUplodeSeccess (res) {
      this.admins.commentPic = res.data;
    },

    lode () {
      // eslint-disable-next-line no-undef
      axios.get('http://localhost:8888/assess/finds/' + this.id, {
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

    // 设置表格行的样式
    tableRowStyle ({row, rowIndex}) {
      return 'background-color:pink;font-size:15px;';
    },
    // 设置表头行的样式
    tableHeaderColor ({row, column, rowIndex, columnIndex}) {
      return 'background-color:lightblue;color:#fff;font-wight:500;font-size:20px;text-align:center';
    },

    // 添加
    add (assessId) {
      const _this = this;

      this.$axios
        .get('http://localhost:8888/assess/find/' + assessId)
        .then(function (resp) {
          _this.admins = resp.data;
        });
    },
    // 修改
    edit (row) {
      this.$router.push('/edituser?userId=' + row.userId);
    },
    // 删除
    onSubmit (formName) {
      this.admins.avatar = this.admind.avatar;
      if (!this.admind.customerName) {
        this.admins.customerName = this.cc;
      } else {
        this.admins.customerName = this.admind.customerName;
      }
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let _this = this;
          // eslint-disable-next-line no-undef
          axios
            .post('http://localhost:8888/comment/add', this.admins)
            .then(function (response) {
              if (response.data) {
                _this.$alert('评价成功！', '评价商品', {
                  confirmButtonText: '确定',
                  callback: (action) => {
                    // 跳转到/table
                    _this.$router.push('/Assess');
                    location.reload();
                  }
                });
              }
            });
        }
      });
    },

    goodsDelete () {
      let _this = this;

      this
        .axios
        .delete('http://localhost:8888/assess/delete/' + this.admins.assessId)
        .then(function (response) {
          if (response.data) {
            _this.$router.push('/Assess');
            // 跳转到 /table
          }
        })

        .catch(() => {});
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
    const _this = this;

    this.$axios
      .get('http://localhost:8888/account/find/' + this.id)
      .then(function (resp) {
        _this.adminss = resp.data;
      });
    this.ju();
    this.lode();
  },
  data () {
    return {
      cc: '匿名用户',
      customerName: this.$store.state.userInfo.customerName,
      product: [],
      id: this.$store.state.userInfo.id,
      avatar: this.$store.state.userInfo.avatar,
      admind: [],
      search: '',
      admins: [
      ],

      dialogTableVisible: false,
      dialogTableVisibles: false,
      dialogFormVisibles: false,
      adminss: {},
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
      form: {
      },
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
/* 搜索 */
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
/* 列表 */
.userindex-list {
  width: 100%;
  height: auto;
  margin-bottom: 20px;
}
/* 分页 */
.userindex-page-box {
  width: 100%;
  height: auto;
  display: flex;
  justify-content: flex-end;

}
.vs{
  float: left;
}
td {
  padding: 10px 0;
  border: 1px solid #e5e5e5;
  vertical-align: top;
  width: 50px;
}
.kk{
  width: 150px;
}
.kk1{
  width: 600px;
}
.kk2{

  width: 100px;
}
.sk{

  width: 1057px;
  height: 40px;
  background:rgb(206, 232, 245);
}
.rr{
  padding-top: 11px;
}
.bb{
  width: 1057px;
  height: 40px;
  background:rgb(206, 232, 245);
  margin-bottom: 40px;
}
.ff{
  padding-top: 11px;
}
.sa{
  width: 50px;
}
.vs{
  margin-bottom: 20px;
  border-radius: 5px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, .22), 0 0 12px rgba(0, 0, 0, .14)

}
.cfs{
  margin-right: 700px;
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
  font-size: 38px;
  margin-bottom: 15px;
}
.shopping-cart-null span {
  color: #999999;
  font-size: 12px;
  line-height: 16px;

}

.vs:hover{
  border: 2px solid #ee7546;
 box-shadow: 0 4px 8px #ee7546, 0 0 12px #ee7546
}
.uyu{
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
