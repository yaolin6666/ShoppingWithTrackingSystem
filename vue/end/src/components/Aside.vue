<template>
  <div>
      <el-menu
      style="min-height: calc(100vh - 50px);width: 200px;height:950px;font-weight: bold"
      background-color="#334960"
      text-color="white"
      :default-active="path"
      router
    >
      <el-menu-item index="/index">
        <i class="el-icon-house"></i>
        <span>系统主页</span>
      </el-menu-item>
       <el-menu-item index="/record" v-if="this.role==1">
        <i class="el-icon-notebook-1"></i>
        <span>访问记录</span>
      </el-menu-item>
      <el-sub-menu index="1">
        <template #title>
          <i class="el-icon-user"></i>
          <span >用户管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/userinfo">用户信息管理</el-menu-item>
          <el-menu-item index="/adminAssign" v-if="this.role==1">商家审核</el-menu-item>
          <el-menu-item index="/shopAdmin" v-if="this.role==1">商家管理</el-menu-item>
          <el-menu-item index="/userAssign" v-if="this.role==2">商家审核资料提交</el-menu-item>
        </el-menu-item-group>
      </el-sub-menu>
      <el-sub-menu index="2" v-if="this.role==3">
        <template #title>
          <i class="el-icon-box"></i>
          <span>商品管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/product">商品信息管理</el-menu-item>
          <el-menu-item index="/team">团购优惠管理</el-menu-item>
          <el-menu-item index="/origin">商品货源管理</el-menu-item>
        </el-menu-item-group>
      </el-sub-menu>
      <el-sub-menu index="3" v-if="this.role==3">
        <template #title>
          <i class="el-icon-shopping-cart-full"></i>
          <span>订单管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/useraddr">等待发货管理</el-menu-item>
          <el-menu-item index="/collection">已发货列表</el-menu-item>
          <el-menu-item index="/refund">退款管理</el-menu-item>
          <el-menu-item index="/comment">评价管理</el-menu-item>
        </el-menu-item-group>
      </el-sub-menu>
      <el-menu-item index="/feedback" v-if="this.role==1">
        <i class="el-icon-chat-line-round"></i>
        <span>用户反馈</span>
      </el-menu-item>
        <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=1808055811&site=qq&menu=yes" style="text-decoration: none;color: white">
      <el-menu-item>
          <i class="el-icon-phone"></i>
          <span>在线客服</span>
      </el-menu-item>
        </a>
    </el-menu>
  </div>
</template>
<script>
export default {
  name: "Aside",
  data() {
    return {
      path: this.$route.path,
      isCollapse: false,
      role: ''
    }
  },
  created() {
    this.role=JSON.parse(sessionStorage.getItem('userInfo')).role;
  },
  methods: {
    handleOpen() {
      this.$parent.handleOpen();
    },
    handleClose() {
      this.$parent.handleClose();
    },
    toggleCollapse() {
      this.isCollapse = !this.isCollapse;
    },
  }
}
</script>
<style>

</style>
