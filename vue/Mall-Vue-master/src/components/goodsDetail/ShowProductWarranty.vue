<template>
  <div>
    <el-row :gutter="0" class="userindex-list">
      <el-col :span="21">
        <el-table :data="admin" border style="width: 100%" @selection-change="handleSelectionChange">
          <el-table-column  prop="arginfoId" label="货源序号" width="120"/>
          <el-table-column prop="count" label="库存(kg)" width="200"/>
          <el-table-column prop="createTime" label="创建时间" width="200">
          </el-table-column>
          <el-table-column prop="updateTime" label="最后修改时间" width="200">
          </el-table-column>
          <el-table-column label="详细" width="300">
            <template #default="scope">
                <el-button type="primary"
                    size="big"
                    @click="allData(scope.row.arginfoId)"
                >查看详细
                </el-button>
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
import request from '../../utils/request';

export default {
  name: 'ShowProductWarranty',
  created () {
    this.id = this.$route.params.id;
    this.lode();
  },
  methods: {
    lode () {
      request.get('/argInfo/pageUser', {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          productId: this.id
        }
      }).then(res => {
        console.log(res);
        this.admin = res.data.records;
        this.total = res.data.total;
      });
    },
    allData (Id) {
      window.open('/originInfo/' + Id);
    }
  },
  data () {
    return {
      originProductId: '',
      admins: {},
      productList: [],
      search: '',
      dialogVisible: false,
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

</style>
