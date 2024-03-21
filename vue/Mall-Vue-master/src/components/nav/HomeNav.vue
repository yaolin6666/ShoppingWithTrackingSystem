<template>
  <div>
      <div class="nav-body">
        <div class="nav-content">
                <Carousel autoplay loop>
                    <CarouselItem  v-for="(item, index) in image" :key="index" >
                      <router-link to="/merchant">
                        <img :src="item.url"  >
                      </router-link>
                    </CarouselItem>
                </Carousel>
        </div>
      </div>
  </div>
</template>
<script>
import store from '@/vuex/store';
import { mapState } from 'vuex';
export default {
  name: 'HomeNav',
  data () {
    return {
      list: [],
      images: [
        {url: require('@/assets/ccc4.png')},
        {url: require('@/assets/jt.png')},
        {url: require('@/assets/ccc5.png')}

      ],
      image: [
        {url: require('@/assets/1.jpg')},
        {url: require('@/assets/2.jpg')},
        {url: require('@/assets/3.jpg')},
        {url: require('@/assets/4.jpg')},
        {url: require('@/assets/5.jpg')}

      ],
      imagess: [
        {url: require('@/assets/ccc10.png')},
        {url: require('@/assets/ccc7.png')},
        {url: require('@/assets/ccc11.png')},
        {url: require('@/assets/ccc14.png')}

      ],
      panel1: false,
      panel2: false,
      nav: [
        '秒杀',
        '优惠券',
        '闪购',
        '拍卖',
        '服装城',
        '超市',
        '生鲜',
        '全球购',
        '金融'
      ],
      panelData1: {
        navTags: ['优秀商品', '容易购买', '选择']
        // '妆比社', '全球购美妆', '宠物馆' ],
      },
      panelData2: {
        navTags: [ '赛事', '运动城', '户外馆', '健身房', '骑行馆', '钟表城' ]

      }
    };
  },
  computed: {
    ...mapState(['marketing'])
  },
  created () {
    this.lodes();
  },
  methods: {
    lodes () {
      // eslint-disable-next-line no-undef
      axios
        .get('http://localhost:8888/productCategory/list', {
        })
        .then((res) => {
          console.log(res);
          this.list = res.data.data;
        });
    }
  },
  store
};
</script>

<style scoped>
.nav-item ul {
  list-style: none;
  margin-left: 13px;
}
.nav-item li {
  float: left;
  font-size: 16px;
  font-weight: bold;

  margin-left: 35px;
}

.nav-item a {
  text-decoration: none;
  color: #555555;
}
.nav-item li:hover {
  color: #d9534f;
}
/*大的导航信息，包含导航，幻灯片等*/
.nav-body {
  width: 1500px;
  height: 350px;
  margin: 0px auto;
  margin-left: 188px;
}
.nav-side ul {
  width: 100%;
  padding: 0px;
  padding-top: 15px;
  list-style: none;
}
.nav-side li {
  padding: 7.5px;
  padding-left: 50px;
  font-size: 14px;
  line-height: 10px;
  font-size: 16px;
 letter-spacing:20px;

}
.nav-side li:hover {
  background: #999395;
}

/*导航内容*/
.nav-content {
  width: 100%;
  margin-left: 25%;
  overflow: hidden;
  float: left;
}
.nav-detail-item span {
  padding: 6px;
  padding-left: 12px;
  margin-left: 15px;
  font-size: 12px;
  background-color: #6e6568;
}
.nav-detail-item span:hover {
  margin-left: 15px;
  background-color: #f44336;
}
.detail-item-panel ul {
  list-style: none;
}
.detail-item-panel li {
  line-height: 38px;
  margin-left: 40px;
}

.detail-item-row a {
  color: #555555;
}

.el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 100px;
    margin: 0;
  }

.detail-item-panel span{
  font-size: 20px;
  margin-top: 40px;
}

.detail-item span:hover{
  color: red;
}
.ii img{
  border-radius: 20px;
}
</style>
