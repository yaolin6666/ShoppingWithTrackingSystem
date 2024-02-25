<template>
  <div>
    <div class="add-container">
      <div class="add-title">
        <h1>修改收货地址</h1>
      </div>
      <div class="add-box">
        <Form :model="good" label-position="left" ref="form" :label-width="100" :rules="rules">
          <FormItem label="收件人" prop="goodDescribe">
            <i-input v-model="good.goodDescribe" size="large"></i-input>
          </FormItem>
          <FormItem label="收件地址" prop="goodName">
            <i-input v-model="good.goodName" size="large"></i-input>
          </FormItem>
          <FormItem label="手机号码" prop="goodPhone">
            <el-input v-model="good.goodPhone" size="large" minlength="11" oninput="value=value.replace(/^\.+|[^\d.]/g,'')"></el-input>
          </FormItem>
        </Form>
      </div>
      <div class="add-submit">
        <Button type="primary" @click="onSubmit('form')">修改地址</Button>
      </div>
    </div>
  </div>
</template>

<script>
import Distpicker from 'v-distpicker';
export default {
  name: 'AddAddress',
  data () {
    return {

      good:[],
      id:this.$route.params.id,
      rules: {
        goodName: [
          { required: true, message: '请输入地址', trigger: 'blur' }
        ],
        goodDescribe: [
          { required: true, message: '请输入收货人名称', trigger: 'blur' }
        ],


      }

    };
  },
   created() {

            let _this = this
            axios.get('http://localhost:8888/good/find/'+this.id).then(function (response) {
                _this.good = response.data
            })
        },
  methods: {
    onSubmit(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let _this = this
                        axios.put('http://localhost:8888/good/update',this.good).then(function (response) {
                            if(response.data){
                                _this.$alert('修改成功！', '修改数据', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        //跳转到/table
                                        _this.$router.push('/home/myAddress')
                                    }
                                });
                            }
                        })
                    }
                });
            }
  },

};
</script>

<style scoped>
.add-container {
  margin: 15px auto;
  width: 60%;
  min-width: 600px;
}
.add-title {
  margin-bottom: 15px;
  text-align: center;
}
.add-submit {
  display: flex;
  justify-content: center;
}
</style>
