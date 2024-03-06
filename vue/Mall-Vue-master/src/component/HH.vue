<template>
  <div>

    <div v-for="admin in admins" :key="admin">
    {{admin.productId}}
    </div>
  <el-button @click="onSubmitss()"></el-button>
  </div>
</template>

<script>
import axios from '../plugins/axios';

export default {
  name: 'hh',
  admins: '',
  props: {
    sreachs: {
      type: String,
      default: ''

    }
  },

  data () {
    return {
      admins: '',

      sps: this.$route.query.sps,
      list: [],
      lists: [],
      show: true,
      show1: true

    };
  },

  mounted () {

  },
  created () {
    console.log(this.$route.query.sps);
    this.admins = this.sps;

    this.lodess();
    this.lodes();
  },

  methods: {

    onSubmitss () {
      axios
        .post('http://localhost:8888/master/add', this.lo)
        .then(function (response) {

        });
    },

    showDetail (index) {
      index === 1 ? (this.panel1 = true) : (this.panel2 = true);
    },
    hideDetail (index) {
      index === 1 ? (this.panel1 = false) : (this.panel2 = false);
    },
    lodes () {
      axios
        .get('http://localhost:8888/productCategory/list', {

        })
        .then((res) => {
          console.log(res);
          this.list = res.data.data;
        });
    },

    lodess () {
      axios
        .get('http://localhost:8888/productCategory/list', {

        })
        .then((res) => {
          console.log(res);
          this.lists = res.data.data.children[0];
        });
    }

  }
};
</script>

<style  scoped>
 .transition-box {
    margin-bottom: 10px;
    width: 200px;
    height: 100px;
    border-radius: 4px;
    background-image: url('../assets/sx.png');
    text-align: center;
    color: #fff;
    padding: 40px 20px;
    box-sizing: border-box;
    margin-right: 20px;
    padding-top: 0px;
    padding-bottom: 0px;
    padding-left: 0px;
    padding-right: 0px;
  }
.detail-item-panel ul {
  list-style: none;
}
.detail-item-panel li {
  line-height: 38px;
  margin-left: 40px;
}
.detail-item-title {
  padding-right: 6px;
  font-weight: bold;
  font-size: 12px;
  cursor: pointer;
  color: #555555;
}
.detail-item-title:hover {
  color: #d9534f;
}
.ss{
  background: red;
  width: 200px;
  height: 100px;
  background-image: url('../assets/q6.png');

}
</style>
