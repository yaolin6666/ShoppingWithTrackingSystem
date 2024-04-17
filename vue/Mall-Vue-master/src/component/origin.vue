<template>
  <div class="userindex">
    <div style="margin: 10px 0;display: flex;">
      <el-input v-model="search" placeholder="请输入" style="width: 20%;" clearable/>
      <el-button type="primary" style="margin-left: 5px" @click="lode">查询</el-button>
      <el-button class="el-icon-bottom" @click="index1">降序</el-button>
      <el-button class="el-icon-top"  @click="index">升序</el-button>
    </div>
    <el-row :gutter="0" class="userindex-list">
      <el-col :span="21">
        <el-table :data="admin" border style="width: 100%" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55"/>
          <el-table-column  prop="arginfoId" label="溯源ID" width="140"/>
          <el-table-column prop="productName" label="名称" width="200">
            <template #default="scope">
              <div>{{productMap[scope.row.productId].productName}}</div>
            </template>
          </el-table-column>
          <el-table-column prop="productImage" label="商品图片" width="500">
            <template #default="scope">
              <el-image style="width: 300px; height: 300px" :src="productMap[scope.row.productId].productImg" :preview-src-list="[productMap[scope.row.productId].productImg]">
              </el-image>
            </template>
          </el-table-column>
          <el-table-column prop="status" label="状态" width="200">
            <template #default="scope">
              <div v-if="scope.row.status==0">已售罄</div>
              <div v-if="scope.row.status==1">未上架</div>
              <div v-if="scope.row.status==2">已上架</div>
            </template>
          </el-table-column>
          <el-table-column prop="createTime" label="创建时间" width="200">
          </el-table-column>
          <el-table-column prop="updateTime" label="最后修改时间" width="200">
          </el-table-column>
          <el-table-column label="详细">
            <template #default="scope">
              <router-link v-bind:to="'/OriginInfo/'+scope.row.arginfoId">
                <p style="margin-left: 21px;margin-top: 18px;color:deepskyblue">查看详细</p>
              </router-link>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>

    <!-- 分页 -->
    <el-row :gutter="20" class="userindex-list">
      <el-col :span="24" class="userindex-page-box">
        <el-pagination v-model:currentPage="currentPage" :page-sizes="[5, 10, 20, 30]" :page-size="pageSize"
                       layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange"
                       @current-change="handleCurrentChange">
        </el-pagination>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import request from '@/utils/request.js';
export default {
  created () {
    this.id = JSON.parse(sessionStorage.getItem('userInfo')).id;
    this.lode();
  },
  methods: {
    lode () {
      request.get('/argInfo/pageUserAll', {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          shopId: this.id,
          search: this.search
        }
      }).then(res => {
        this.admin = res.data.records;
        this.total = res.data.total;
      });
      request.get('/product/findAll', {
        params: {
          accountId: this.id
        }
      }).then(res => {
        console.log(res);
        this.productList = res;
        this.productList.forEach(item => {
          this.productMap[item.productId] = {
            productImg: item.productImage,
            productName: item.productName
          };
        });
      });
    },
    index () {
      // 排序
      this.sort = !this.sort;
      if (this.sort) {
        this.admin.sort((a, b) => {
          return a.status - b.status;
        });
      } else {
        this.admin.sort((a, b) => {
          return a.status - b.status;
        });
      }
    },
    index1 () {
      this.sort = !this.sort;
      if (this.sort) {
        this.admin.sort((a, b) => {
          return b.status - a.status;
        });
      } else {
        this.admin.sort((a, b) => {
          return b.status - a.status;
        });
      }
    },
    handleCurrentChange (pageNum) { // 改变当前页码触发
      this.currentPage = pageNum;
      this.lode();
    }
  },

  data () {
    return {
      originProductId: '',
      admins: {},
      productList: [],
      search: '',
      dialogVisible: false,
      dialogVisible1: false,
      currentPage: 1,
      pageSize: 5,
      total: 0,
      tableData: [],
      admin: [],
      ids: [],
      id: '',
      sort: true,
      productMap: {},
      options: [{
        value: 0,
        label: '已售罄'
      }, {
        value: '1',
        label: '未上架'
      }, {
        value: '2',
        label: '已上架'
      }]
    };
  }

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
  width: 100%;
  height: auto;
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
</style>
