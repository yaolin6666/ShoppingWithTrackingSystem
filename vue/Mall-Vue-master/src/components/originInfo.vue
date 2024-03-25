<template>
  <div class="userindex">
    <el-row :gutter="0" class="userindex-list">
      <el-col :span="21">
        <el-table :data="admin" border style="width: 100%" @selection-change="handleSelectionChange">
          <el-table-column  prop="id" label="溯源详细ID" width="180"/>
          <el-table-column  prop="arginfoId" label="溯源ID" width="150"/>
          <el-table-column prop="argInfo" label="溯源信息" width="300"/>
          <el-table-column prop="mediaInfo" label="溯源额外信息" width="300">
            <template #default="scope">
              <el-image style="width: 200px; height: 200px" :src="scope.row.mediaInfo" :preview-src-list="[scope.row.mediaInfo]">
                <div solt="error" class="image-slot">
                  <i class="el-icon-picture-outline"></i>
                </div>
              </el-image>
            </template>
          </el-table-column>
          <el-table-column prop="createTime" label="创建时间" width="200">
          </el-table-column>
          <el-table-column prop="updateTime" label="最后修改时间" width="200">
          </el-table-column>
        </el-table>
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
      request.get('/argInfo/queryArgOrigin', {
        params: {
          argInfoId: this.idOrigin
        }
      }).then(res => {
        console.log(res);
        this.admin = res;
      });
    }
  },

  data () {
    return {
      originProductId: '',
      id: '',
      idOrigin: this.$route.params.id,
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
.userindex-list {
  width: 100%;
  height: auto;
  margin-bottom: 20px;
}
</style>
