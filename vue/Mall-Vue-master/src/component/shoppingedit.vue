<template>
  <el-form ref="form" :rules="rules" :model="notice" label-width="300px" style="width: 600px" center>
    <el-form-item label="管理员ID">
      <el-input v-model="notice.adminId" readonly class="ss">{{ notice.adminId }}</el-input>
    </el-form-item>
    <el-form-item label="管理员名称" prop="adminName">
      <el-input v-model="notice.adminName">{{ notice.adminName }}</el-input>
    </el-form-item>
    <el-form-item label="活动性质" prop="adminPwd">
      <el-radio-group v-model="notice.adminPwd" size="medium">
        <el-radio border label="红色"></el-radio>
        <el-radio border label="蓝色"></el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="联系方式" prop="adminPhone">
      <el-input v-model="notice.adminPhone">{{ notice.adminPhone * notice.adminState }}</el-input>
    </el-form-item>
    <el-form-item label="管理员状态" prop="adminState">
      <el-input-number v-model="notice.adminState" @change="handleChange" :min="1" :max="10"
                       label="描述文字"></el-input-number>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit('form')">立即修改</el-button>
      <el-button>取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: 'shoppingedit',
  created () {
    let adminId = this.$route.query.adminId;
    let _this = this;
    // eslint-disable-next-line no-undef
    axios.get('http://localhost:8888/notice/find/' + adminId).then(function (response) {
      _this.notice = response.data;
    });
  },
  data () {
    return {
      notice: {
      }
    };
  },

  methods: {
    handleChange (value) {
      console.log(value);
    },
    onSubmit (notice) {
      this.$refs[notice].validate((valid) => {
        if (valid) {
          let _this = this;
          // eslint-disable-next-line no-undef
          axios.put('http://localhost:8888/notice/update', this.notice).then(function (response) {
            if (response.data) {
              _this.$alert(_this.notice.adminName + '修改成功！', '修改数据', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/notice');
                }
              });
            }
          });
        }
      });
    }
  }
};
</script>

<style scoped>

</style>
