<template>
  <div class="container">
    <Layout class="layout">
      <Sider class="side-bar" :style="{height: 'calc(100vh - 35px)', background: '#fff', overflow: 'auto'}" ref="side" :collapsed-width="78">
        <Menu active-name="1-2" theme="light" width="auto" @on-select="onSelect">
          <div class="user-icon">

            <div class="user-img">
              <img :src="admin.avatar" width="80px" height="100px" >
            </div>
            <p>{{admin.customerName}}</p>
          </div>

            <router-link v-bind:to="'/home/myOrder/'+this.id"><MenuItem ><Icon type="clipboard"></Icon>我的主页</MenuItem></router-link>
          <router-link v-bind:to="'/home/myaddress'"><MenuItem><Icon type="location"></Icon>我的收货地址</MenuItem></router-link>
          <router-link v-bind:to="'/orders'"><MenuItem><Icon type="clipboard"></Icon>我的订单</MenuItem></router-link>
          <router-link v-bind:to="'/home/myShoppingCart'"><MenuItem><Icon type="ios-cart"></Icon>我的收藏</MenuItem></router-link>
        </Menu>
      </Sider>
      <Layout class="layout">
        <Header :style="{background: '#fff'}">
          <h2>{{activeTitle}}</h2>
        </Header>
        <Content class="content">
          <transition mode="out-in">
            <router-view></router-view>
          </transition>
        </Content>
        <Footer class="layout-footer-center"></Footer>
      </Layout>
    </Layout>
  </div>
</template>

<script>
export default {
  name: 'Home',
  data () {
    return {
      admin: [],
      username: this.$store.state.userInfo.username,
      customerName: this.$store.state.userInfo.customerName,
      id: this.$store.state.userInfo.id,
      avatar: this.$store.state.userInfo.avatar,
      activeTitle: '我的主页',
      bar: {
        'myAddress': '我的收货地址',
        'myOrder': '我的订单',
        'myShoppingCart': '我的收藏'

      }
    };
  },
  created () {
    const _this = this;

    this.$axios
      .get('http://localhost:8888/account/find/' + this.id)
      .then(function (resp) {
        _this.admin = resp.data;

        console.log(resp);
      });
  },
  methods: {

    onSelect (name) {
      this.activeTitle = this.bar[name];
      this.$router.push(`/home/${name}`);
    }
  }
};
</script>

<style scoped>
.side-bar a{
  color: #232323;
}
.user-icon {
  height: 200px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.user-icon span {
  font-size: 96px;
}
.user-img {
  margin-bottom: 15px;
  width: 96px;
  height: 96px;
  border-radius: 48px;
  overflow: hidden;
  box-shadow: 0 4px 8px rgba(0, 0, 0, .22), 0 0 12px rgba(0, 0, 0, .14);
}
.user-img img{
  width: 100%;
}
.content {
  margin: 15px;
  background-color: #fff;
  padding: 15px;
}
.layout-footer-center {
  padding: 0px 15px;
  padding-bottom: 15px;
  text-align: center;
}
</style>
