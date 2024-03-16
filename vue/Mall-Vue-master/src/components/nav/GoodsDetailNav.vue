<template>
  <div class="shop-nav-box">
    <div class="shop-nav-container">
    </div>
  </div>
</template>

<script>
export default {
  name: 'GoodsDetailNav',
  data () {
    return {

    };
  }
};
</script>

<style scoped>
.shop-nav-box {
  height: 38px;
  background-color: #333333 !important;
}

.shop-nav-container {
  width: 80%;
  height: 100%;
  margin: 0px auto;
}

.shop-nav-container ul {
  padding: 0px;
  margin: 0px;
  list-style: none;
}

.shop-nav-container li {
  float: left;
  margin-right: 30px;
  line-height: 35px;
}

.shop-nav-container a {
  text-decoration: none;
  color: rgb(233, 224, 224);
  font-weight: bold;
}

.shop-nav-container li span:hover{
  color: #ee7546;
}
</style>
