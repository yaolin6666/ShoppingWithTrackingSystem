
<template>
<div>
 <Search></Search>
    <GoodsListNav></GoodsListNav>
<!-- 管理员管理 -->
  <div class="userindex">
    <el-backtop  :bottom="10" :right="0">
  <div
      class="rt"
    >
      返回顶部
    </div>
 </el-backtop>

    <!-- 搜索条件 -->
    <div class="qss">

    <el-row :gutter="20" class="userindex-queryInfo">
      <!-- 商品名称搜索 --><span style="float:left;margin-left: 58px;">
      </span>
      <el-col :xs="8" :sm="6" :md="6" :lg="4" :xl="4">

        <el-input
          class="userindex-queryInfo-li"
          v-model="search"
          clearable



          size="small"
          placeholder="请输入商品名称"
        ></el-input>
      </el-col>

        <el-col :xs="6" :sm="4" :md="3" :lg="2" :xl="2">
        <el-button  class="userindex-queryInfo-li" size="small" @click="lode()"
          >搜 索</el-button
        >

      </el-col>
      <div class="ss" >
      <el-popconfirm title="确认删除吗?" @confirm="deleteBatch" >
        <template #reference>
          <el-button style="background:  rgb(255, 102, 0);color:#fff">批量删除</el-button>
        </template>
      </el-popconfirm>
    </div>
      <!-- 添加按钮 -->
<!-- <el-button @click="hh"></el-button> -->
    </el-row>
    </div>

    <!-- 检索结果 -->
    <el-row :gutter="170" class="userindex-list"  >
      <el-col :span="24"  >
        <el-table  :data="notice"   style="width: 100%;" @selection-change="handleSelectionChange">

           <el-table-column
      type="selection"

      width="55">
    </el-table-column>
          <!-- <el-table-column type="index" prop="adminId" label="管理员ID" width="80" >
          </el-table-column> -->
          <!-- <el-table-column
                    prop="good_id"
                    label="商品id"
                    width="180"> -->

          <el-table-column prop="productImage" label="商品图片" width="300">
         <template slot-scope="scope">
          <el-image style="width: 200px; height: 150px" :src="scope.row.productImage" :preview-src-list="[scope.row.productImage]">
            <div slot="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
            </div>
          </el-image>
        </template>
      </el-table-column>
       <el-table-column prop="productName" label="商品名称" width="200" >
          </el-table-column>
          <el-table-column prop="productColor" label="颜色" width="150" >
          </el-table-column>
          <el-table-column prop="productPrice" label="价格" width="150" >
          </el-table-column>
          <el-table-column prop="productNum" label="数量" width="100">
          </el-table-column>
          <el-table-column prop="createTime" label="创建时间" width="200">
          </el-table-column>
          <!-- <el-table-column prop="updateTime" label="最后修改时间" width="200">
          </el-table-column> -->
          <el-table-column fixed="right" label="操作" width="200" align="center">
            <template slot-scope="scope">
              <el-button
                type="primary"
                icon="el-icon-view"
                circle
                size="small"
                @click="edit(scope.row);onSubmitss(scope.row)"
              >
              </el-button>

              <el-button
                type="danger"
                icon="el-icon-delete"
                circle
                size="small"
                @click="del(scope.row)"
              >
              </el-button>


              <el-button
                type="danger"

                circle
                size="small"
                @click="add(scope.row)"
              >下 单
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <div class="eg"><span style="margin-left: 65px;"><img src="@/assets/d.png" style="border: 1px solid rgb(206, 207, 209);"></span>
    <span style="margin-left: 225px;"><img src="@/assets/f.png" style="border: 1px solid rgb(206, 207, 209);"></span>
    <span style="margin-left: 245px;"><img src="@/assets/g.png" style="border: 1px solid rgb(206, 207, 209);"></span>
    </div>
    <!-- 分页 -->
    <div class="esd">
    <el-row :gutter="20" class="userindex-list">
      <el-col :span="24" class="userindex-page-box">
         <el-pagination :currentPage="currentPage" :page-sizes="[12, 24, 36, 48]" :page-size="pageSize"
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
import Search from '@/components/Search';
import GoodsListNav from '@/components/nav/GoodsListNav';
export default {
  name:"Notice",

  created() {
    this.lode()
    console.log(this.idss)
    // const _this = this;
    // axios.get("http://localhost:8888/shopping/findAll").then(function (resp) {
    //   _this.notice = resp.data;
    //   console.log(resp);
    //   //   alert(resp);
    //   _this.tableData = resp.data.content;
    //   _this.pageSize = resp.data.size;
    //   _this.total = resp.data.totalElements;
    // });
  },

  methods: {
    onSubmitss(admin) {
      admin.customerId=this.idss
      this.product=admin;


          let _this = this;
          axios
            .post("http://localhost:8888/img/add", this.product)
            .then(function (response) {
              console.log(this.product)
            });

      },

lode() {
      axios.get("http://localhost:8888/shopping/finds/"+ this.idss, {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search,
        }
      }).then(res => {
        console.log(res);
        this.notice = res.data.data.records
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
 handleSelectionChange(val) {
      this.ids = val.map(v => v.orderDetailId)
      this.po = val.map(v => v)
      console.log(this.po)
    },
    //添加
    add(row) {
      this.$router.push('/order?orderDetailId=' + row.orderDetailId);
    },
      //修改
    edit(row) {
      this.$router.push('/goodsDetail/'+row.productId)
    },
    //删除
    del(row) {
      let _this = this;
      this.$confirm("是否确定要删除" , {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          axios
            .delete("http://localhost:8888/shopping/delete/" + row.orderDetailId)
            .then(function (response) {
              if (response.data) {
                _this.$alert("删除成功!", "删除数据", {
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


     deleteBatch(){
       let _this = this;
      if (!this.ids.length){
         _this.$alert("请先进行选择商品删除!", {
                  confirmButtonText: "确定",
                  callback: (action) => {
                    //跳转到 /table
                    location.reload();
                  },
                });
        return
      }

      axios.post("/shopping/deleteBatch",this.ids).then(function (response) {
              if (response.data) {
                _this.$alert("删除成功!", "删除数据", {
                  confirmButtonText: "确定",
                  callback: (action) => {
                    //跳转到 /table
                    location.reload();
                  },
                });
              }
            });
    },
    hh(){
       this.$router.push({path: '/hh', query: { sps: this.po }});
    }
    // page(currentPage){
    //         const _this=this;
    //         axios.get('http://localhost:8181/stock/findAll/'+(currentPage-1)+'/3').then(function (resp) {
    //             _this.tableData=resp.data.content;
    //             _this.pageSize=resp.data.size;
    //             _this.total=resp.data.totalElements;
    //         })
    //     }
    // page(currentPage) {
    //   switch (currentPage) {
    //     case 1:
    //       this.tableData = [
    //         {
    //           id: goodId,
    //           name: good_name,
    //         },
    //       ];
    //   }
    // },
  },
components: {
    Search,
    GoodsListNav,
    store,

  },
  computed:{
    ...mapState(['userInfo', 'shoppingCart']),

  },
  data() {
    return {
      idss:this.$store.state.userInfo.id,
      po:[],
      product:[],
      queryInfo: {
        name: "",
        type: "",

      },
       search: '',
     currentPage: 1,
      pageSize: 12,
      total: 0,
      tableData: [],
      notice: [],
      ids: [],
      dialogFormVisible: false,
      form: {

      },
      formLabelWidth: "120px",
      search:""
    };
  },
};
</script>

<style scoped>
.userindex {
 margin-left: 150px;

  min-height: 100%;
  padding: 30px;
  /* box-sizing: border-box; */


}
/* 搜索 */
.userindex-queryInfo {

margin-top: 20px;
  margin-bottom: 40px;
}
.userindex-queryInfo-li {
  border: 3px solid rgb(255, 102, 0);
  margin-top: 22px;
  margin-left: 684px;
  width: 100%;
  height: auto;
  color: aliceblue;
  font-size: 15px;
  background:  rgb(255, 102, 0);


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
  justify-content: center;
}
.el-table-column{
  margin-top: 50px;
}

.esd{
  margin-top: 100px;
  margin-right: 230px;
}
.eg{
  margin-top: 80px;
}
.ss{
  float: right;
  padding-top: 22px;
  padding-right: 208px;
}
.rt{

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
