<template>
  <div class="er">
    <div class="ert">
    <div :data="admin">
      <div class="ju"><img :src="admin.avatar" width="170px" height="170px" style="border-radius: 100px; box-shadow: 0 4px 8px rgba(0, 0, 0, .22), 0 0 12px rgba(0, 0, 0, .14);">
      <div class="ju8">
         <el-button @click="dialogVisible = true">修 改</el-button>
       </div>
      </div>

       <div class="jj">
     <div class="ju1">
       <i class="el-icon-user"></i>
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称
       <span style="margin-left: 90px;">
       {{admin.customerName}}
       </span>
       </div>
     <div class="ju2">
       <i class="el-icon-edit-outline"></i>
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名
        <span style="margin-left: 90px;">
       {{admin.signatures}}
        </span>
       </div>
     <div class="ju3">
       <i class="el-icon-phone-outline"></i>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话
        <span style="margin-left: 90px;">
       {{admin.phone}}
        </span>
       </div>

    <div class="ju4">
      <i class="el-icon-message"></i>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱
      <span style="margin-left: 90px;">
      {{admin.email}}
      </span>
      </div>


     <div class="ju5">
       <i class="el-icon-present"></i>
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄
       <span style="margin-left: 90px;">
       {{admin.age}}
       </span>
       </div>
     <div class="ju6">
       <i class="el-icon-male"></i>
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别
       <span style="margin-left: 90px;">
       {{admin.sex}}
       </span>
       </div>
     <!-- 用户头像:{{admin.avatar}} -->
     <!-- <div class="ju7">
       <i class="el-icon-money"></i>
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;余&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;额
       <span style="margin-left: 90px;">
       {{admin.wallet}}
       </span>
       </div> -->

     </div>
    </div>
    </div>
    <el-dialog title="收货地址"  :visible.sync="dialogVisible">
  <el-form :model="admin" ref="fruitRule"
    :rules="rules">
    <el-form-item>
       <el-upload
  class="upload-demo"
  ref="uplode"
  action="http://localhost:8888/files/uplode"

  :on-preview="handlePreview"
  :on-remove="handleRemove"
  :file-list="fileList"
  :on-success="filesUplodeSeccess"
  list-type="picture">
  <el-button size="small" type="primary" @click="handleEdit()">点击上传</el-button>
  <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>

</el-upload>
    </el-form-item>
   <el-form-item label="用户昵称" prop="customerName">
    <el-input v-model="admin.customerName"></el-input>
  </el-form-item>
  <!-- <el-form-item label="收货地址" prop="goodName">
     <Distpicker v-model="goods.province"  @province="getProvince" @city="getCity" @area="getArea"></Distpicker>
  </el-form-item> -->
  <el-form-item label="用户签名" prop="signatures">
    <el-input v-model="admin.signatures"></el-input>
  </el-form-item>
  <el-form-item label="用户电话" prop="goodPhone">
    <el-input v-model="admin.phone"
    oninput="value=value.replace(/^\.+|[^\d.]/g,'')"

 minlength="11"
 show-word-limit
    ></el-input>
  </el-form-item>
   <el-form-item label="用户邮箱" prop="email">
    <el-input v-model="admin.email"></el-input>
  </el-form-item>
   <el-form-item label="用户年龄" prop="age">
    <el-input v-model="admin.age"></el-input>
  </el-form-item>
   <el-form-item label="用户性别" prop="sex">
    <el-radio v-model="admin.sex" label="男">男</el-radio>
            <el-radio v-model="admin.sex" label="女">女</el-radio>
  </el-form-item>
   <!-- <el-form-item label="用户余额" prop="wallet">
    <el-input v-model="admin.wallet"></el-input>
  </el-form-item> -->
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="onSubmit('fruitRule');"
        >立即修改</el-button
      >
  </div>
</el-dialog>
  </div>
</template>

<script>
export default {
  name: 'MyOrder',
  data () {

    // 邮箱验证
    var checkEmail = (rule, value, callback) => {
      const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
      // if (!value) {
      //   return callback(new Error('邮箱不能为空'))
      // }
      setTimeout(() => {
        if (mailReg.test(value)) {
          callback();
        } else {
          callback(new Error("请输入正确的邮箱格式"));
        }
      }, 100);
    };
    return {
       dialogVisible: false,
      admin:[],
      id:this.$route.params.id,
       rules: {
        email: [{ validator: checkEmail, trigger: "blur" }],
      },

    };
  },
  created(){
    const _this = this;

    this.$axios
      .get("http://localhost:8888/customerInfo/find/" + this.id)
      .then(function (resp) {
        _this.admin = resp.data;

        console.log(resp);
      });

  },
   methods: {

filesUplodeSeccess(res){
      console.log(res)
      this.admin.avatar = res.data

    },
  handleEdit() {

      this.dialogVisible = true
      this.$nextTick(() =>{
        this.$refs['uplode'].clearFiles()
      })


    },
    onSubmit(admin) {
                this.$refs[admin].validate((valid) => {
                    if (valid) {
                        let _this = this
                        axios.put('http://localhost:8888/customerInfo/update',this.admin).then(function (response) {
                            if(response.data){
                                _this.$alert('修改成功！', '修改个人主页', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        //跳转到/table
                                        location.reload();
                                    }
                                });
                            }
                        })
                    }
                });
            },


    },
};
</script>

<style scoped>
.page-size {
  margin: 15px 0px;
  display: flex;
  justify-content: flex-end;
  align-items: center;
}
.jj{
  padding-top: 100px;
}
.ju{
  margin-left: 280px;
  padding-top: 190px;
  float: left;


}
.ju1{
  margin-top: 40px;
  font-size: 17px;
  color: rgb(141, 148, 148);
  font-weight: bold;
  margin-left: 590px;

}
.ju2{
 margin-top: 40px;
  font-size: 17px;
  font-weight: bold;
  color: rgb(141, 148, 148);
  margin-left: 590px;
}
.ju3{
  margin-top: 40px;
  font-size: 17px;
  font-weight: bold;
  color: rgb(141, 148, 148);
  margin-left: 590px;
}
.ju4{
  margin-top: 40px;
  font-size: 17px;
  font-weight: bold;
  color: rgb(141, 148, 148);
  margin-left: 590px;
}
.ju5{
  margin-top: 40px;
  font-size: 17px;
  font-weight: bold;
  color: rgb(141, 148, 148);
  margin-left: 590px;
}
.ju6{
  margin-top: 40px;
  font-size: 17px;
  font-weight: bold;
  color: rgb(141, 148, 148);
  margin-left: 590px;
}
.ju7{
  margin-top: 40px;
  font-size: 17px;
  font-weight: bold;
  color: rgb(141, 148, 148);
  margin-left: 590px;
}
.ju8{

 margin-top: 60px;
 padding-left: 40px;
}
.er{
  border-radius: 30px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, .22), 0 0 12px rgba(0, 0, 0, .14);
  height: 800px;
  background: rgb(250, 221, 205);
padding-top: 50px;
 padding-left: 107px;
 padding-bottom: 50px;
 padding-right: 40px;

}
.ert{
  border-radius: 30px;
  height: 700px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, .22), 0 0 12px rgba(0, 0, 0, .14);
  background: white;
  width: 1330px;


}
</style>
