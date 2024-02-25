<template>
  <div>

    <div v-for="admin in admins" :key="admin">
    {{admin.productId}}
    </div>
  <el-button @click="onSubmitss()"></el-button>
   <!-- <div v-for="(item,index) in list" :key="index"><h1>
     <router-link v-bind:to="'/goodsList/'+item.categoryId">
     {{item.categoryName}}
     </router-link>

     </h1>
     <div>
        <router-link v-bind:to="'/goodsList/'+item.children[0].categoryId">
       {{item.children[0].categoryName}}
        </router-link>
       </div>
     </div>
   <div>
     <li @mouseenter="showDetail(1)" @mouseleave="hideDetail(1)">
       点击1
     </li>
      <li @mouseenter="showDetail(2)" @mouseleave="hideDetail(2)">
       点击2
     </li>
   </div>
 <div>
    <div class="detail-item-panel panel-1" :duration="{ enter: 100, leave: 100 }" v-show="panel1" @mouseenter="showDetail(1)"  @mouseleave="hideDetail(1)">
      1111111111111
    </div>
     <div class="detail-item-panel panel-1" :duration="{ enter: 100, leave: 100 }" v-show="panel2" @mouseenter="showDetail(2)"  @mouseleave="hideDetail(2)">
      222222222222222
    </div>
 </div>

  <div>
    <el-button @click="show1= !show1">Click Me</el-button>
    <el-button @click="show1 = !show1">Click Me1</el-button>

    <div style="display: flex; margin-top: 20px; height: 100px;">
      <transition name="el-fade-in-linear">
        <div v-show="show" class="transition-box">

           <div v-show="show1" class="ss"></div>
           </div>
      </transition>

    </div>
  </div>
  <div>{{sreachs}}</div> -->
  </div>
</template>

<script>
export default {
  name: 'hh',
  admins:'',
  props:{
   sreachs:{
     type: String,
     default:'',


   },
  },

  data() {
    return {
      admins:"",

       sps:this.$route.query.sps,
      list:[],
      lists:[],
show: true,
show1: true

    };
  },

  mounted() {

  },
  created(){
    console.log(this.$route.query.sps)
    this.admins=this.sps

   this.lodess()
    this.lodes()
  },

  methods: {

 onSubmitss() {


          let _this = this;
          axios
            .post("http://localhost:8888/master/add", this.lo)
            .then(function (response) {

            });

      },

    showDetail (index) {
      index === 1 ? (this.panel1 = true) : (this.panel2 = true)  ;
    },
     hideDetail (index) {
      index === 1 ? (this.panel1 = false) : (this.panel2 = false) ;
     },
     lodes() {
      axios
        .get("http://localhost:8888/productCategory/list" , {

        })
        .then((res) => {
          console.log(res);
          this.list = res.data.data;


        });
    },

    lodess() {
      axios
        .get("http://localhost:8888/productCategory/list" , {

        })
        .then((res) => {
          console.log(res);
          this.lists = res.data.data.children[0];


        });
    },

  },
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
