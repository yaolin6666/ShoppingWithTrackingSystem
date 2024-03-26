/*
 * @Author: Gavin
 * @Date: 2018-06-11 11:27:10
 * @Last Modified by: Gavin
 * @Last Modified time: 2018-06-11 12:23:40
 * @Describe: 搜索商品导航标签
 */
<template>
  <div class="item-class-show" >

    <div v-show="list.length<0">

      </div>
      <div v-show="list.length>0">
    <Row class="item-class-group"   >
      <i-col class="item-class-name" span="3">分类 :  </i-col>
      <i-col class="item-class-select" span="21">

      <span v-for="(item,index) in list" :key="index" @click="jus">

          <router-link v-bind:to='`/goodsList/${item.categoryId}`' class="ghg" >
             <span>{{item.categoryName}}</span>
          </router-link>

      </span>

      </i-col>
    </Row>
</div>

  </div>
</template>

<script>
export default {
  name: 'GoodsClassNav',
  data () {
    return {
      id: this.$route.params.id,
      admin: [],
      list: [],

      tagsInfo: [
        {
          tagName: '品牌'

        },
        {
          tagName: '分类'
        }

      ]
    };
  },

  created () {
    // eslint-disable-next-line no-unused-expressions,no-sequences
    this.lodes(),
    this.gt();
    const _this = this;

    this.$axios
      .get('http://localhost:8888/brand/' + this.id)
      .then(function (resp) {
        _this.admin = resp.data.data;
      });
  },
  methods: {
    ju () {
      // eslint-disable-next-line eqeqeq
      if (location.href.indexOf('#') == -1) {
        location.href = location.href + '#';
        location.reload();
      }
    },
    jus () {
      // eslint-disable-next-line eqeqeq
      if (location.href.indexOf('#') == -1) {
        location.href = location.href + '#';
        location.reload();
      }
    },

    lodes () {
      // eslint-disable-next-line no-undef
      axios
        .get('http://localhost:8888/productCategory/parent/' + this.id, {

        })
        .then((res) => {
          this.list = res.data.data;
        });
    },

    gt () {

    }
  },
  mounted () {
  }
};
</script>

<style scoped>
.item-class-show {
  margin: 15px auto;
  width: 100%;
}
.item-class-group {
  margin-top: 1px;
  height: 45px;
  border-bottom: 1px solid #ccc;
}
.item-class-group:first-child {
  border-top: 1px solid #ccc;
}
.item-class-name {
  padding-left: 15px;
  line-height: 44px;
  color: #666;
  font-weight: bold;
  background-color: #f3f3f3;
}
.item-class-name:first-child {
  line-height: 43px;
}
.item-class-select span {
  margin-left: 15px;
  width: 160px;
  color: #005aa0;
  line-height: 45px;
  cursor: pointer;
}
</style>
