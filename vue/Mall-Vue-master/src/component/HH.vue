<template>
  <div>

    <div v-for="admin in admins" :key="admin">
    {{admin.productId}}
    </div>
  <el-button @click="onSubmitss()"></el-button>
  </div>
</template>

<script>

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
      // eslint-disable-next-line no-undef
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
      // eslint-disable-next-line no-undef
      axios
        .get('http://localhost:8888/productCategory/list', {

        })
        .then((res) => {
          console.log(res);
          this.list = res.data.data;
        });
    },

    lodess () {
      // eslint-disable-next-line no-undef
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
 .detail-item-panel ul {
  list-style: none;
}
.detail-item-panel li {
  line-height: 38px;
  margin-left: 40px;
}

</style>
