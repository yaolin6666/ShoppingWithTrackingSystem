<template>
  <div>
    <div class="nav-item">
      <ul>
        <li >限时秒杀</li>
        <router-link to="/merchant"><li >全部商品</li></router-link>
        <li >有好货</li>
         <li >品牌精选</li>
         <li >商品分类</li>
         <li >品牌分类</li>
      </ul>

    </div>
    <div class="nav-body">
      <!-- 侧边导航 -->
      <div class="nav-side" ref="navSide">
          <div v-for="(item,index) in list" :key="index">
     <!-- <router-link v-bind:to="'/goodsList/'+item.categoryId">
     {{item.categoryName}}
     </router-link> -->


        <ul>
          <li @mouseenter="showDetail(item.categoryNum)" @mouseleave="hideDetail(item.categoryNum)">
           <router-link v-bind:to='`/goodsList/${item.categoryId}`' class="ghg" > <span class="nav-side-item"  >{{item.categoryName }}

      </span>
           </router-link>
          </li>
          <!-- <li @mouseenter="showDetail(2)" @mouseleave="hideDetail(2)">
            <span class="nav-side-item">手机</span> /
            <span class="nav-side-item">运营商</span> /
            <span class="nav-side-item">数码</span>
          </li>
          <li @mouseenter="showDetail(1)" @mouseleave="hideDetail(1)">
            <span class="nav-side-item">电脑</span> /
            <span class="nav-side-item">办公</span>
          </li>
          <li @mouseenter="showDetail(2)" @mouseleave="hideDetail(2)">
            <span class="nav-side-item">家居</span> /
            <span class="nav-side-item">家具</span> /
            <span class="nav-side-item">家装</span> /
            <span class="nav-side-item">厨具</span>
          </li>
          <li @mouseenter="showDetail(1)" @mouseleave="hideDetail(1)">
            <span class="nav-side-item">男装</span> /
            <span class="nav-side-item">女装</span> /
            <span class="nav-side-item">童装</span> /
            <span class="nav-side-item">内衣</span>
          </li>
          <li @mouseenter="showDetail(2)" @mouseleave="hideDetail(2)">
            <span class="nav-side-item">美妆个护</span> /
            <span class="nav-side-item">宠物</span>
          </li>
          <li @mouseenter="showDetail(1)" @mouseleave="hideDetail(1)">
            <span class="nav-side-item">女鞋</span> /
            <span class="nav-side-item">箱包</span> /
            <span class="nav-side-item">钟表</span> /
            <span class="nav-side-item">珠宝</span>
          </li>
          <li @mouseenter="showDetail(2)" @mouseleave="hideDetail(2)">
            <span class="nav-side-item">男鞋</span> /
            <span class="nav-side-item">运动</span> /
            <span class="nav-side-item">户外</span>
          </li>
          <li @mouseenter="showDetail(1)" @mouseleave="hideDetail(1)">
            <span class="nav-side-item">汽车</span> /
            <span class="nav-side-item">汽车用品</span>
          </li>
          <li @mouseenter="showDetail(2)" @mouseleave="hideDetail(2)">
            <span class="nav-side-item">母婴</span> /
            <span class="nav-side-item">玩具乐器</span>
          </li>
          <li @mouseenter="showDetail(1)" @mouseleave="hideDetail(1)">
            <span class="nav-side-item">食品</span> /
            <span class="nav-side-item">酒类</span> /
            <span class="nav-side-item">生鲜</span> /
            <span class="nav-side-item">特产</span>
          </li>
          <li @mouseenter="showDetail(2)" @mouseleave="hideDetail(2)">
            <span class="nav-side-item">礼品鲜花</span> /
            <span class="nav-side-item">农资绿植</span>
          </li>
          <li @mouseenter="showDetail(1)" @mouseleave="hideDetail(1)">
            <span class="nav-side-item">医药保健</span> /
            <span class="nav-side-item">计生情趣</span>
          </li>
          <li @mouseenter="showDetail(2)" @mouseleave="hideDetail(2)">
            <span class="nav-side-item">图书</span> /
            <span class="nav-side-item">音像</span> /
            <span class="nav-side-item">电子书</span>
          </li> -->
        </ul>

      </div>

      </div>
 <div class="nav-content">
        <!-- 幻灯片 -->
        <!-- <div>
          <Carousel autoplay loop >
              <CarouselItem  v-for="(item, index) in marketing.CarouselItems" :key="index" >

                <router-link to="/merchant">
                  <img :src="item"  >
                </router-link>
              </CarouselItem>
          </Carousel>
        </div> -->
         <div>
          <Carousel autoplay loop >
              <CarouselItem  v-for="(item, index) in image" :key="index" >
                <!-- <router-link to="/goodsList"> -->
                <router-link to="/merchant">
                  <img :src="item.url"  >
                </router-link>
              </CarouselItem>
          </Carousel>
        </div>
        <div class="nav-show">
          <div class="nav-show-img" v-for="(item, index) in image2" :key="index">

            <router-link to="/merchant">
              <img :src="item.url"  >
            </router-link>
          </div>
        </div>
      </div>
      <div class="index">
        <!-- 跑马灯  -->

    </div>
     <div class="qwe">
         <span ></span>
          <router-link to="/merchant">
         <span >
            <el-carousel indicator-position="none"  height="160px" >
    <el-carousel-item  v-for="item in images" :key="item" >
      <img :src="item.url" width="417px" height="144px"   alt="无图片"/>
    </el-carousel-item>
  </el-carousel>
  </span>
          </router-link>
  <div class="rrt"><img src="@/assets/gt.png" height="179px" width="417px"></div>
<div style="width:417px">
   <router-link to="/merchant">
 <el-carousel indicator-position="none"  height="130px" >
    <el-carousel-item  v-for="item in imagess" :key="item" >
      <img :src="item.url" width="417px"  height="130px" alt="无图片"/>
    </el-carousel-item>
  </el-carousel>
   </router-link>
  </div>
        </div>
    </div>

    <transition name="fade" >
      <div class="detail-item-panel panel-1" :duration="{ enter: 100, leave: 100 }" v-show="panel1" @mouseenter="showDetail(1)" ref="itemPanel1" @mouseleave="hideDetail(1)">
        <div class="nav-detail-item">
          <span v-for="(item, index) in panelData1.navTags" :key="index">{{item}}  </span>
        </div>
        <ul>
          <!-- <li v-for="(items, index) in panelData1.classNav" :key="index" class="detail-item-row">
            <span class="detail-item-title">{{items.title}}
              <span class="glyphicon glyphicon-menu-right"></span>
            </span>
            <router-link to="/goodsList" v-for="(item, subIndex) in items.tags" :key="subIndex">



          </li> -->

        <span v-for="(item,index) in list" :key="index" >
        <span class="detail-item" v-if="index==0" >
          <router-link v-bind:to="'/goodsList/'+item.children[0].categoryId">
          <span style="margin-left: 25px;"><el-divider direction="vertical"></el-divider> &nbsp; &nbsp;{{item.children[0].categoryName}}</span></router-link> &nbsp; <el-divider direction="vertical"></el-divider>

          <router-link v-bind:to="'/goodsList/'+item.children[1].categoryId">
          <span>&nbsp;{{item.children[1].categoryName}}</span>  </router-link>   &nbsp;<el-divider direction="vertical"></el-divider>

           <router-link v-bind:to="'/goodsList/'+item.children[2].categoryId">
          <span>{{item.children[2].categoryName}}</span>  </router-link>&nbsp;&nbsp;<el-divider direction="vertical"></el-divider>

          <p style="margin-left: 39px;margin-top: 20px;"> <router-link v-bind:to="'/goodsList/'+item.children[3].categoryId">
          <span><el-divider direction="vertical"></el-divider> &nbsp; {{item.children[3].categoryName}}</span>  </router-link> &nbsp; <el-divider direction="vertical"></el-divider>

           <router-link v-bind:to="'/goodsList/'+item.children[4].categoryId">
          <span>{{item.children[4].categoryName}}</span>  </router-link>&nbsp;<el-divider direction="vertical"></el-divider>

           <router-link v-bind:to="'/goodsList/'+item.children[5].categoryId">
          <span>{{item.children[5].categoryName}}</span>  </router-link> &nbsp;<el-divider direction="vertical"></el-divider></p>

           <p style="margin-top: 20px;"><router-link v-bind:to="'/goodsList/'+item.children[6].categoryId">
          <span style="margin-left: 39px;"><el-divider direction="vertical"></el-divider>&nbsp; &nbsp;{{item.children[6].categoryName}}</span> &nbsp; &nbsp;<el-divider direction="vertical"></el-divider> </router-link></p>
          </span>
        </span>

         </ul>
      </div>
    </transition>


    <div class="qw">

    <transition name="fade">

      <div class="detail-item-panel panel-2" :duration="{ enter: 100, leave: 100 }" v-show="panel2" @mouseenter="showDetail(2)" ref="itemPanel2" @mouseleave="hideDetail(2)">

        <div class="nav-detail-item">
          <span v-for="(item, index) in panelData1.navTags" :key="index">{{item}}  </span>
        </div>
        <!-- <ul>

          <li v-for="(items, index) in panelData2.classNav" :key="index" class="detail-item-row">
            <span class="detail-item-title">{{items.title}}
              <span class="glyphicon glyphicon-menu-right"></span>
            </span>

            <router-link to="/goodsList" v-for="(item, subIndex) in items.tags" :key="subIndex">
            <router-link to="/Index" v-for="(item, subIndex) in items.tags" :key="subIndex">
              <span class="detail-item">{{item}}</span>

            </router-link>

          </li>

        </ul> -->
  <span v-for="(item,index) in list" :key="index" >
        <span class="detail-item" v-if="index==1">
          <router-link v-bind:to="'/goodsList/'+item.children[0].categoryId">
          <span style="margin-left: 26px;"><el-divider direction="vertical"></el-divider> &nbsp; &nbsp;{{item.children[0].categoryName}}</span>  </router-link>&nbsp;&nbsp; <el-divider direction="vertical"></el-divider>

          <router-link v-bind:to="'/goodsList/'+item.children[1].categoryId">
          <span>&nbsp; {{item.children[1].categoryName}}</span>  </router-link> &nbsp; &nbsp;<el-divider direction="vertical"></el-divider>

            <router-link v-bind:to="'/goodsList/'+item.children[2].categoryId">
          <span >&nbsp; &nbsp;{{item.children[2].categoryName}}</span>  </router-link>&nbsp; &nbsp; <el-divider direction="vertical"></el-divider>


           <p style="margin-left: 27px;margin-top: 20px;"> &nbsp; <el-divider direction="vertical"></el-divider><router-link v-bind:to="'/goodsList/'+item.children[3].categoryId">
          <span>&nbsp; {{item.children[3].categoryName}}</span>  </router-link> &nbsp;<el-divider direction="vertical"></el-divider>

            <router-link v-bind:to="'/goodsList/'+item.children[4].categoryId">
          <span> &nbsp;&nbsp;{{item.children[4].categoryName}}</span> &nbsp; &nbsp;<el-divider direction="vertical"></el-divider>  </router-link></p>
          </span>
        </span>
      </div>

    </transition>
      </div>
 <transition name="fade" >
      <div class="detail-item-panel panel-3" :duration="{ enter: 100, leave: 100 }" v-show="panel3" @mouseenter="showDetail(3)" ref="itemPanel3" @mouseleave="hideDetail(3)">
        <div class="nav-detail-item">
          <span v-for="(item, index) in panelData1.navTags" :key="index">{{item}}  </span>
        </div>
        <ul>
          <!-- <li v-for="(items, index) in panelData1.classNav" :key="index" class="detail-item-row">
            <span class="detail-item-title">{{items.title}}
              <span class="glyphicon glyphicon-menu-right"></span>
            </span>
            <router-link to="/goodsList" v-for="(item, subIndex) in items.tags" :key="subIndex">



          </li> -->

        <span v-for="(item,index) in list" :key="index" >
        <span class="detail-item" v-if="index==2">
          <router-link v-bind:to="'/goodsList/'+item.children[0].categoryId">
          <span style="margin-left: 25px;"><el-divider direction="vertical"></el-divider> &nbsp; &nbsp;{{item.children[0].categoryName}}</span>  </router-link> &nbsp;<el-divider direction="vertical"></el-divider>

          <router-link v-bind:to="'/goodsList/'+item.children[1].categoryId">
          <span>&nbsp; &nbsp;{{item.children[1].categoryName}}</span>  </router-link> &nbsp; &nbsp;<el-divider direction="vertical"></el-divider>

           <router-link v-bind:to="'/goodsList/'+item.children[2].categoryId">
          <span>&nbsp; {{item.children[2].categoryName}}</span>  </router-link> &nbsp; &nbsp;&nbsp;<el-divider direction="vertical"></el-divider>

           <p style="margin-left: 37px;margin-top: 20px;"><el-divider direction="vertical"></el-divider> &nbsp;&nbsp;&nbsp;<router-link v-bind:to="'/goodsList/'+item.children[3].categoryId">
          <span>{{item.children[3].categoryName}}</span>  </router-link> &nbsp;<el-divider direction="vertical"></el-divider>

           <router-link v-bind:to="'/goodsList/'+item.children[4].categoryId">
          <span>&nbsp; {{item.children[4].categoryName}}</span>  </router-link>&nbsp; <el-divider direction="vertical"></el-divider>

          <router-link v-bind:to="'/goodsList/'+item.children[5].categoryId">
          <span> {{item.children[5].categoryName}}</span>  </router-link> &nbsp; <el-divider direction="vertical"></el-divider></p>

          </span>
        </span>

         </ul>
      </div>
    </transition>

<transition name="fade" >
      <div class="detail-item-panel panel-4" :duration="{ enter: 100, leave: 100 }" v-show="panel4" @mouseenter="showDetail(4)" ref="itemPanel4" @mouseleave="hideDetail(4)">
        <div class="nav-detail-item">
          <span v-for="(item, index) in panelData1.navTags" :key="index">{{item}}  </span>
        </div>
        <ul>
          <!-- <li v-for="(items, index) in panelData1.classNav" :key="index" class="detail-item-row">
            <span class="detail-item-title">{{items.title}}
              <span class="glyphicon glyphicon-menu-right"></span>
            </span>
            <router-link to="/goodsList" v-for="(item, subIndex) in items.tags" :key="subIndex">



          </li> -->

        <span v-for="(item,index) in list" :key="index" >
        <span class="detail-item" v-if="index==3">
          <router-link v-bind:to="'/goodsList/'+item.children[0].categoryId">
          <span style="margin-left: 25px;"> <el-divider direction="vertical"></el-divider> &nbsp; &nbsp;{{item.children[0].categoryName}}</span>  </router-link> &nbsp; &nbsp;<el-divider direction="vertical"></el-divider>

          <router-link v-bind:to="'/goodsList/'+item.children[1].categoryId">
          <span>&nbsp;&nbsp;&nbsp;  {{item.children[1].categoryName}}</span>  </router-link>&nbsp; &nbsp;&nbsp;&nbsp;<el-divider direction="vertical"></el-divider>

           <router-link v-bind:to="'/goodsList/'+item.children[2].categoryId">
          <span> &nbsp; &nbsp;&nbsp;{{item.children[2].categoryName}}</span>  </router-link>&nbsp; &nbsp;&nbsp; &nbsp;<el-divider direction="vertical"></el-divider>

           <p style="margin-left: 33px;margin-top: 20px;"> &nbsp;<el-divider direction="vertical"></el-divider> &nbsp; &nbsp; &nbsp;<router-link v-bind:to="'/goodsList/'+item.children[3].categoryId">
          <span>{{item.children[3].categoryName}}</span>  </router-link> &nbsp;&nbsp; &nbsp;<el-divider direction="vertical"></el-divider>

           <router-link v-bind:to="'/goodsList/'+item.children[4].categoryId">
          <span>&nbsp; &nbsp; {{item.children[4].categoryName}}</span>  </router-link> &nbsp; &nbsp; &nbsp;<el-divider direction="vertical"></el-divider>

          <router-link v-bind:to="'/goodsList/'+item.children[5].categoryId">
          <span>&nbsp; &nbsp; {{item.children[5].categoryName}}</span>  </router-link>&nbsp; &nbsp; &nbsp; <el-divider direction="vertical"></el-divider>
</p>
           <p style="margin-left: 38px;margin-top: 20px;"><el-divider direction="vertical"></el-divider>&nbsp;&nbsp; &nbsp;<router-link v-bind:to="'/goodsList/'+item.children[6].categoryId">
          <span>{{item.children[6].categoryName}}</span>  </router-link> &nbsp; &nbsp;<el-divider direction="vertical"></el-divider>

          <router-link v-bind:to="'/goodsList/'+item.children[7].categoryId">
          <span>&nbsp; &nbsp; {{item.children[7].categoryName}}</span>  </router-link> &nbsp; &nbsp; &nbsp;<el-divider direction="vertical"></el-divider></p>

          </span>
        </span>

         </ul>
      </div>
    </transition>


<transition name="fade" >
      <div class="detail-item-panel panel-5" :duration="{ enter: 100, leave: 100 }" v-show="panel5" @mouseenter="showDetail(5)" ref="itemPanel5" @mouseleave="hideDetail(5)">
        <div class="nav-detail-item">
          <span v-for="(item, index) in panelData1.navTags" :key="index">{{item}}  </span>
        </div>
        <ul>
          <!-- <li v-for="(items, index) in panelData1.classNav" :key="index" class="detail-item-row">
            <span class="detail-item-title">{{items.title}}
              <span class="glyphicon glyphicon-menu-right"></span>
            </span>
            <router-link to="/goodsList" v-for="(item, subIndex) in items.tags" :key="subIndex">



          </li> -->

        <span v-for="(item,index) in list" :key="index" >
        <span class="detail-item" v-if="index==4">
          <router-link v-bind:to="'/goodsList/'+item.children[0].categoryId">
          <span style="margin-left: 25px;"><el-divider direction="vertical"></el-divider> &nbsp;{{item.children[0].categoryName}}</span>  </router-link> <el-divider direction="vertical"></el-divider>

          <router-link v-bind:to="'/goodsList/'+item.children[1].categoryId">
          <span>&nbsp;{{item.children[1].categoryName}}</span>  </router-link> &nbsp;<el-divider direction="vertical"></el-divider>

           <router-link v-bind:to="'/goodsList/'+item.children[2].categoryId">
          <span>&nbsp;{{item.children[2].categoryName}}</span>  </router-link> &nbsp;<el-divider direction="vertical"></el-divider>

          <p style="margin-left: 37px;margin-top: 20px;"> <el-divider direction="vertical"></el-divider>&nbsp;&nbsp;&nbsp; <router-link v-bind:to="'/goodsList/'+item.children[3].categoryId">
          <span>{{item.children[3].categoryName}}</span>  </router-link>&nbsp;&nbsp; <el-divider direction="vertical"></el-divider>

           <router-link v-bind:to="'/goodsList/'+item.children[4].categoryId">
          <span>&nbsp;&nbsp;&nbsp;{{item.children[4].categoryName}}</span>  </router-link> &nbsp;&nbsp;<el-divider direction="vertical"></el-divider>

          <router-link v-bind:to="'/goodsList/'+item.children[5].categoryId">
          <span>&nbsp;  {{item.children[5].categoryName}}</span>  </router-link>&nbsp;<el-divider direction="vertical"></el-divider>
</p>

          </span>
        </span>

         </ul>
      </div>
    </transition>


<transition name="fade" >
      <div class="detail-item-panel panel-6" :duration="{ enter: 100, leave: 100 }" v-show="panel6" @mouseenter="showDetail(6)" ref="itemPanel6" @mouseleave="hideDetail(6)">
        <div class="nav-detail-item">
          <span v-for="(item, index) in panelData1.navTags" :key="index">{{item}}  </span>
        </div>
        <ul>
          <!-- <li v-for="(items, index) in panelData1.classNav" :key="index" class="detail-item-row">
            <span class="detail-item-title">{{items.title}}
              <span class="glyphicon glyphicon-menu-right"></span>
            </span>
            <router-link to="/goodsList" v-for="(item, subIndex) in items.tags" :key="subIndex">



          </li> -->

        <span v-for="(item,index) in list" :key="index" >
        <span class="detail-item" v-if="index==5">
          <router-link v-bind:to="'/goodsList/'+item.children[0].categoryId">
          <span style="margin-left: 25px;margin-top: 20px;">&nbsp;<el-divider direction="vertical"></el-divider> &nbsp; &nbsp;{{item.children[0].categoryName}}</span>  </router-link> &nbsp;<el-divider direction="vertical"></el-divider>

          <router-link v-bind:to="'/goodsList/'+item.children[1].categoryId">
          <span>&nbsp; {{item.children[1].categoryName}}</span>  </router-link> &nbsp; &nbsp;<el-divider direction="vertical"></el-divider>

           <router-link v-bind:to="'/goodsList/'+item.children[2].categoryId">
          <span>&nbsp; {{item.children[2].categoryName}}</span>  </router-link> &nbsp; &nbsp;<el-divider direction="vertical"></el-divider>

           <p style="margin-left: 44px;margin-top: 20px;"><el-divider direction="vertical"></el-divider> &nbsp;&nbsp;<router-link v-bind:to="'/goodsList/'+item.children[3].categoryId">
          <span>&nbsp; {{item.children[3].categoryName}}</span>  </router-link> &nbsp; &nbsp;<el-divider direction="vertical"></el-divider>

           <router-link v-bind:to="'/goodsList/'+item.children[4].categoryId">
          <span> &nbsp;&nbsp;&nbsp;&nbsp;{{item.children[4].categoryName}}</span>  </router-link> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<el-divider direction="vertical"></el-divider>

          <router-link v-bind:to="'/goodsList/'+item.children[5].categoryId">
          <span> &nbsp;&nbsp;{{item.children[5].categoryName}}</span>  </router-link>&nbsp;&nbsp;&nbsp;&nbsp;<el-divider direction="vertical"></el-divider></p>

           <p style="margin-left: 44px;margin-top: 20px;"><el-divider direction="vertical"></el-divider> &nbsp;&nbsp;&nbsp; <router-link v-bind:to="'/goodsList/'+item.children[6].categoryId">
          <span>{{item.children[6].categoryName}}</span>  </router-link>&nbsp;&nbsp;&nbsp;&nbsp;<el-divider direction="vertical"></el-divider></p>

          </span>
        </span>

         </ul>
      </div>
    </transition>


    <transition name="fade" >
      <div class="detail-item-panel panel-7" :duration="{ enter: 100, leave: 100 }" v-show="panel7" @mouseenter="showDetail(7)" ref="itemPanel7" @mouseleave="hideDetail(7)">
        <div class="nav-detail-item">
          <span v-for="(item, index) in panelData1.navTags" :key="index">{{item}} </span>
        </div>
        <ul>
          <!-- <li v-for="(items, index) in panelData1.classNav" :key="index" class="detail-item-row">
            <span class="detail-item-title">{{items.title}}
              <span class="glyphicon glyphicon-menu-right"></span>
            </span>
            <router-link to="/goodsList" v-for="(item, subIndex) in items.tags" :key="subIndex">



          </li> -->

        <span v-for="(item,index) in list" :key="index" >
        <span class="detail-item" v-if="index==6">
          <router-link v-bind:to="'/goodsList/'+item.children[0].categoryId">
          <span style="margin-left: 25px;margin-top: 20px;">&nbsp;<el-divider direction="vertical"></el-divider> &nbsp;&nbsp; &nbsp;{{item.children[0].categoryName}}</span>  </router-link> &nbsp;&nbsp;<el-divider direction="vertical"></el-divider>

          <router-link v-bind:to="'/goodsList/'+item.children[1].categoryId">
          <span> &nbsp;&nbsp;&nbsp;&nbsp;{{item.children[1].categoryName}}</span>  </router-link> &nbsp;&nbsp;&nbsp;&nbsp;<el-divider direction="vertical"></el-divider>

           <router-link v-bind:to="'/goodsList/'+item.children[2].categoryId">
          <span>&nbsp; &nbsp;&nbsp;&nbsp;{{item.children[2].categoryName}}</span>  </router-link> &nbsp;&nbsp;&nbsp;<el-divider direction="vertical"></el-divider>

           <p style="margin-left: 42px;margin-top: 20px;"><el-divider direction="vertical"></el-divider> &nbsp; <router-link v-bind:to="'/goodsList/'+item.children[3].categoryId">
          <span>{{item.children[3].categoryName}}</span>  </router-link> &nbsp;<el-divider direction="vertical"></el-divider>

           <router-link v-bind:to="'/goodsList/'+item.children[4].categoryId">
          <span>&nbsp;&nbsp;&nbsp; {{item.children[4].categoryName}}</span>  </router-link>&nbsp; &nbsp;&nbsp;<el-divider direction="vertical"></el-divider>

          <router-link v-bind:to="'/goodsList/'+item.children[5].categoryId">
          <span>&nbsp; &nbsp;&nbsp; &nbsp; {{item.children[5].categoryName}}</span>  </router-link>&nbsp; &nbsp; &nbsp; <el-divider direction="vertical"></el-divider></p>

           <p style="margin-left: 42px;margin-top: 20px;"><el-divider direction="vertical"></el-divider> &nbsp; <router-link v-bind:to="'/goodsList/'+item.children[6].categoryId">
          <span>{{item.children[6].categoryName}}</span>  </router-link>&nbsp;&nbsp;<el-divider direction="vertical"></el-divider></p>

          </span>
        </span>

         </ul>
      </div>
    </transition>


    <transition name="fade" >
      <div class="detail-item-panel panel-8" :duration="{ enter: 100, leave: 100 }" v-show="panel8" @mouseenter="showDetail(8)" ref="itemPanel8" @mouseleave="hideDetail(8)">
        <div class="nav-detail-item">
          <span v-for="(item, index) in panelData1.navTags" :key="index">{{item}}  </span>
        </div>
        <ul>


        <span v-for="(item,index) in list" :key="index" >
        <span class="detail-item" v-if="index==7">
          <router-link v-bind:to="'/goodsList/'+item.children[0].categoryId">
          <span style="margin-left: 22px;margin-top: 20px;">&nbsp;<el-divider direction="vertical"></el-divider> {{item.children[0].categoryName}}</span>  </router-link> &nbsp;<el-divider direction="vertical"></el-divider>

          <router-link v-bind:to="'/goodsList/'+item.children[1].categoryId">
          <span>&nbsp; {{item.children[1].categoryName}}</span>  </router-link> <el-divider direction="vertical"></el-divider>

           <router-link v-bind:to="'/goodsList/'+item.children[2].categoryId">
          <span> &nbsp;{{item.children[2].categoryName}}</span>  </router-link> <el-divider direction="vertical"></el-divider>

           <p style="margin-left: 42px;margin-top: 20px;"><el-divider direction="vertical"></el-divider><router-link v-bind:to="'/goodsList/'+item.children[3].categoryId">
          <span>&nbsp;{{item.children[3].categoryName}}</span>  </router-link> &nbsp;<el-divider direction="vertical"></el-divider>

           <router-link v-bind:to="'/goodsList/'+item.children[4].categoryId">
          <span>&nbsp; {{item.children[4].categoryName}}</span>  </router-link> <el-divider direction="vertical"></el-divider>

          <router-link v-bind:to="'/goodsList/'+item.children[5].categoryId">
          <span> &nbsp; &nbsp;{{item.children[5].categoryName}}</span>  </router-link> &nbsp;<el-divider direction="vertical"></el-divider></p>

          <p style="margin-left: 42px;margin-top: 20px;"><el-divider direction="vertical"></el-divider><router-link v-bind:to="'/goodsList/'+item.children[6].categoryId">
          <span>{{item.children[6].categoryName}}</span></router-link><el-divider direction="vertical"></el-divider></p>

          </span>
        </span>

         </ul>
      </div>
    </transition>


     <transition name="fade" >
      <div class="detail-item-panel panel-9" :duration="{ enter: 100, leave: 100 }" v-show="panel9" @mouseenter="showDetail(9)" ref="itemPanel9" @mouseleave="hideDetail(9)">
        <div class="nav-detail-item">
          <span v-for="(item, index) in panelData1.navTags" :key="index">{{item}}  </span>
        </div>
        <ul>


        <span v-for="(item,index) in list" :key="index" >
        <span class="detail-item" v-if="index==8">
          <router-link v-bind:to="'/goodsList/'+item.children[0].categoryId">
          <span style="margin-left: 22px;margin-top: 20px;">&nbsp;<el-divider direction="vertical"></el-divider> &nbsp;&nbsp;{{item.children[0].categoryName}}</span>  </router-link> &nbsp;&nbsp;<el-divider direction="vertical"></el-divider>

          <router-link v-bind:to="'/goodsList/'+item.children[1].categoryId">
          <span>&nbsp; {{item.children[1].categoryName}}</span>  </router-link> &nbsp;&nbsp;<el-divider direction="vertical"></el-divider>

           <router-link v-bind:to="'/goodsList/'+item.children[2].categoryId">
          <span>&nbsp; {{item.children[2].categoryName}}</span>  </router-link> &nbsp;&nbsp;<el-divider direction="vertical"></el-divider>

           <p style="margin-left: 42px;margin-top: 20px;"><el-divider direction="vertical"></el-divider>&nbsp;<router-link v-bind:to="'/goodsList/'+item.children[3].categoryId">
          <span>&nbsp; {{item.children[3].categoryName}}</span>  </router-link> &nbsp;&nbsp;<el-divider direction="vertical"></el-divider>

           <router-link v-bind:to="'/goodsList/'+item.children[4].categoryId">
          <span>&nbsp; {{item.children[4].categoryName}}</span>  </router-link> &nbsp; <el-divider direction="vertical"></el-divider>

          <router-link v-bind:to="'/goodsList/'+item.children[5].categoryId">
          <span>&nbsp; {{item.children[5].categoryName}}</span>  </router-link>&nbsp; &nbsp;<el-divider direction="vertical"></el-divider></p>

           <p style="margin-left: 42px;margin-top: 20px;"><el-divider direction="vertical"></el-divider>&nbsp;&nbsp; <router-link v-bind:to="'/goodsList/'+item.children[6].categoryId">
          <span>{{item.children[6].categoryName}}</span>  </router-link>  &nbsp; <el-divider direction="vertical"></el-divider></p>

          </span>
        </span>

         </ul>
      </div>
    </transition>

    <transition name="fade" >
      <div class="detail-item-panel panel-10" :duration="{ enter: 100, leave: 100 }" v-show="panel10" @mouseenter="showDetail(10)" ref="itemPanel10" @mouseleave="hideDetail(10)">
        <div class="nav-detail-item">
          <span v-for="(item, index) in panelData1.navTags" :key="index">{{item}}  </span>
        </div>
        <ul>


        <span v-for="(item,index) in list" :key="index" >
        <span class="detail-item" v-if="index==9">
          <router-link v-bind:to="'/goodsList/'+item.children[0].categoryId">
          <span style="margin-left: 22px;margin-top: 20px;">&nbsp;<el-divider direction="vertical"></el-divider> &nbsp;{{item.children[0].categoryName}}</span>  </router-link> &nbsp;&nbsp;<el-divider direction="vertical"></el-divider>

          <router-link v-bind:to="'/goodsList/'+item.children[1].categoryId">
          <span>&nbsp;{{item.children[1].categoryName}}</span>  </router-link> &nbsp;&nbsp;<el-divider direction="vertical"></el-divider>

           <router-link v-bind:to="'/goodsList/'+item.children[2].categoryId">
          <span>&nbsp;{{item.children[2].categoryName}}</span>  </router-link> &nbsp;<el-divider direction="vertical"></el-divider>

           <p style="margin-left: 42px;margin-top: 20px;"><el-divider direction="vertical"></el-divider>&nbsp;<router-link v-bind:to="'/goodsList/'+item.children[3].categoryId">
          <span>{{item.children[3].categoryName}}</span>  </router-link> &nbsp;&nbsp;<el-divider direction="vertical"></el-divider>

           <router-link v-bind:to="'/goodsList/'+item.children[4].categoryId">
          <span>{{item.children[4].categoryName}}</span>  </router-link> &nbsp;&nbsp;<el-divider direction="vertical"></el-divider></p>



          </span>
        </span>

         </ul>
      </div>
    </transition>


    <transition name="fade" >
      <div class="detail-item-panel panel-11" :duration="{ enter: 100, leave: 100 }" v-show="panel11" @mouseenter="showDetail(11)" ref="itemPanel11" @mouseleave="hideDetail(11)">
        <div class="nav-detail-item">
          <span v-for="(item, index) in panelData1.navTags" :key="index">{{item}}  </span>
        </div>
        <ul>


        <span v-for="(item,index) in list" :key="index" >
        <span class="detail-item" v-if="index==10">
          <router-link v-bind:to="'/goodsList/'+item.children[0].categoryId">
          <span style="margin-left: 22px;margin-top: 20px;">&nbsp;<el-divider direction="vertical"></el-divider> &nbsp;&nbsp;&nbsp;{{item.children[0].categoryName}}</span>  </router-link> &nbsp;&nbsp;&nbsp;<el-divider direction="vertical"></el-divider>

          <router-link v-bind:to="'/goodsList/'+item.children[1].categoryId">
          <span> &nbsp;&nbsp;{{item.children[1].categoryName}}</span>  </router-link> &nbsp;&nbsp;&nbsp;<el-divider direction="vertical"></el-divider>

           <router-link v-bind:to="'/goodsList/'+item.children[2].categoryId">
          <span> &nbsp;&nbsp;{{item.children[2].categoryName}}</span>  </router-link> &nbsp;&nbsp;<el-divider direction="vertical"></el-divider>

           <p style="margin-left: 42px;margin-top: 20px;"><el-divider direction="vertical"></el-divider> &nbsp; &nbsp; &nbsp;<router-link v-bind:to="'/goodsList/'+item.children[3].categoryId">
          <span>{{item.children[3].categoryName}}</span>  </router-link> &nbsp; &nbsp; <el-divider direction="vertical"></el-divider>

           <router-link v-bind:to="'/goodsList/'+item.children[4].categoryId">
          <span>&nbsp; &nbsp; {{item.children[4].categoryName}}</span>  </router-link> &nbsp; &nbsp; &nbsp;<el-divider direction="vertical"></el-divider></p>





          </span>
        </span>

         </ul>
      </div>
    </transition>



<transition name="fade" >
      <div class="detail-item-panel panel-12" :duration="{ enter: 100, leave: 100 }" v-show="panel12" @mouseenter="showDetail(12)" ref="itemPanel12" @mouseleave="hideDetail(12)">
        <div class="nav-detail-item">
          <span v-for="(item, index) in panelData1.navTags" :key="index">{{item}}  </span>
        </div>
        <ul>


        <span v-for="(item,index) in list" :key="index" >
        <span class="detail-item" v-if="index==11">
          <router-link v-bind:to="'/goodsList/'+item.children[0].categoryId">
          <span style="margin-left: 22px;margin-top: 20px;">&nbsp;<el-divider direction="vertical"></el-divider> &nbsp; &nbsp;&nbsp;{{item.children[0].categoryName}}</span>  </router-link> &nbsp; &nbsp; <el-divider direction="vertical"></el-divider>

          <router-link v-bind:to="'/goodsList/'+item.children[1].categoryId">
          <span> &nbsp; &nbsp;{{item.children[1].categoryName}}</span>  </router-link> &nbsp; &nbsp; &nbsp;<el-divider direction="vertical"></el-divider>

           <router-link v-bind:to="'/goodsList/'+item.children[2].categoryId">
          <span> &nbsp;&nbsp; {{item.children[2].categoryName}}</span>  </router-link> &nbsp; &nbsp; &nbsp;<el-divider direction="vertical"></el-divider>

           <p style="margin-left: 42px;margin-top: 20px;"><el-divider direction="vertical"></el-divider>&nbsp;<router-link v-bind:to="'/goodsList/'+item.children[3].categoryId">
          <span>{{item.children[3].categoryName}}</span>  </router-link>&nbsp;&nbsp;<el-divider direction="vertical"></el-divider>

           <router-link v-bind:to="'/goodsList/'+item.children[4].categoryId">
          <span>&nbsp;{{item.children[4].categoryName}}</span>  </router-link> &nbsp;&nbsp; <el-divider direction="vertical"></el-divider>

           <router-link v-bind:to="'/goodsList/'+item.children[5].categoryId">
          <span> &nbsp;&nbsp;{{item.children[5].categoryName}}</span>  </router-link>&nbsp; <el-divider direction="vertical"></el-divider></p>

           <p style="margin-left: 42px;margin-top: 20px;"><el-divider direction="vertical"></el-divider> &nbsp;&nbsp;<router-link v-bind:to="'/goodsList/'+item.children[6].categoryId">
          <span>{{item.children[6].categoryName}}</span>  </router-link> &nbsp; &nbsp;<el-divider direction="vertical"></el-divider>

           <router-link v-bind:to="'/goodsList/'+item.children[7].categoryId">
          <span>&nbsp; &nbsp;{{item.children[7].categoryName}}</span>  </router-link> &nbsp;&nbsp; &nbsp;<el-divider direction="vertical"></el-divider></p>



          </span>
        </span>

         </ul>
      </div>
    </transition>



    <div class="ii">
  <router-link to="/goodsList/113"><span style="margin-left: 68px;"><img src="@/assets/ccc1.png" height="109px" width="236px"></span></router-link>
  <router-link to="/goodsList/111"><span style="margin-left: 13px;"><img src="@/assets/ccc12.png" height="109px" width="236px"></span></router-link>
  <router-link to="/goodsList/161"><span style="margin-left: 13px;"><img src="@/assets/ccc9.png" height="109px" width="236px"></span></router-link>
  <router-link to="/goodsList/1"><span style="margin-left: 13px;"><img src="@/assets/zzz4.png" height="109px" width="236px"></span></router-link>
  <router-link to="/goodsList/181"><span style="margin-left: 13px;"><img src="@/assets/zzz5.png" height="109px" width="236px"></span></router-link>
   <router-link to="/goodsList/140"><span style="margin-left: 13px;"><img src="@/assets/ccc13.png" height="109px" width="236px"></span></router-link>
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
      list:[],
       images: [
          { url: require("@/assets/ccc4.png")},
          { url: require("@/assets/jt.png")},
          { url: require("@/assets/ccc5.png")},

        ],
         image: [
          { url: require("@/assets/1.jpg")},
          { url: require("@/assets/2.jpg")},
          { url: require("@/assets/3.jpg")},
          { url: require("@/assets/4.jpg")},
          { url: require("@/assets/5.jpg")},

        ],
         image2: [
          { url: require("@/assets/nav_showimg1.jpg")},
          { url: require("@/assets/nav_showimg2.jpg")},


        ],
        imagess: [
          { url: require("@/assets/ccc10.png")},
          { url: require("@/assets/ccc7.png")},
          { url: require("@/assets/ccc11.png")},
           { url: require("@/assets/ccc14.png")},

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
        navTags: [ '优秀商品', '容易购买', '选择'] ,
        // '妆比社', '全球购美妆', '宠物馆' ],
        },
      panelData2: {
        navTags: [ '赛事', '运动城', '户外馆', '健身房', '骑行馆', '钟表城' ],

      }
    };
  },
  computed: {
    ...mapState(['marketing'])
  },
  created(){
     this.lodes()
  },
  methods: {
     lodes() {
      axios
        .get("http://localhost:8888/productCategory/list" , {

        })
        .then((res) => {
          console.log(res);
          this.list = res.data.data;


        });
    },

    showDetail (index) {
      index === 1 ? (this.panel1 = true) : (this.panel2 = true)  ;
      index === 2 ? (this.panel2 = true) : (this.panel1 = true)  ;
      index === 3 ? (this.panel3 = true) : (this.panel1 = true)  ;
      index === 4 ? (this.panel4 = true) : (this.panel1 = true)  ;
      index === 5 ? (this.panel5 = true) : (this.panel1 = true)  ;
       index === 6 ? (this.panel6 = true) : (this.panel1 = true)  ;
        index === 7 ? (this.panel7 = true) : (this.panel1 = true)  ;
         index === 8 ? (this.panel8 = true) : (this.panel1 = true)  ;
         index === 9 ? (this.panel9 = true) : (this.panel1 = true)  ;
          index === 10 ? (this.panel10 = true) : (this.panel1 = true)  ;
          index === 11 ? (this.panel11 = true) : (this.panel1 = true)  ;
          index === 12 ? (this.panel12 = true) : (this.panel1 = true)  ;



    },
    hideDetail (index) {
      index === 1 ? (this.panel1 = false) : (this.panel2 = false) ;
       index === 2 ? (this.panel2 = false) : (this.panel1 = false) ;
       index === 3 ? (this.panel3 = false) : (this.panel1 = false) ;
        index === 4 ? (this.panel4 = false) : (this.panel1 = false) ;
         index === 5 ? (this.panel5 = false) : (this.panel1 = false) ;
          index === 6 ? (this.panel6 = false) : (this.panel1 = false) ;
           index === 7 ? (this.panel7 = false) : (this.panel1 = false) ;
           index === 8 ? (this.panel8 = false) : (this.panel1 = false) ;
            index === 9 ? (this.panel9 = false) : (this.panel1 = false) ;
             index === 10 ? (this.panel10 = false) : (this.panel1 = false) ;
             index === 11 ? (this.panel11 = false) : (this.panel1 = false) ;
              index === 12 ? (this.panel12 = false) : (this.panel1 = false) ;




    }
  },
  mounted () {
    this.$refs.itemPanel1.style.left =
      this.$refs.navSide.offsetLeft + this.$refs.navSide.offsetWidth + 'px';
    this.$refs.itemPanel2.style.left =
      this.$refs.navSide.offsetLeft + this.$refs.navSide.offsetWidth + 'px';
     this.$refs.itemPanel3.style.left =
      this.$refs.navSide.offsetLeft + this.$refs.navSide.offsetWidth + 'px';
      this.$refs.itemPanel4.style.left =
      this.$refs.navSide.offsetLeft + this.$refs.navSide.offsetWidth + 'px';
       this.$refs.itemPanel5.style.left =
      this.$refs.navSide.offsetLeft + this.$refs.navSide.offsetWidth + 'px';
       this.$refs.itemPanel6.style.left =
      this.$refs.navSide.offsetLeft + this.$refs.navSide.offsetWidth + 'px';
      this.$refs.itemPanel7.style.left =
      this.$refs.navSide.offsetLeft + this.$refs.navSide.offsetWidth + 'px';
      this.$refs.itemPanel8.style.left =
      this.$refs.navSide.offsetLeft + this.$refs.navSide.offsetWidth + 'px';
       this.$refs.itemPanel9.style.left =
      this.$refs.navSide.offsetLeft + this.$refs.navSide.offsetWidth + 'px';
       this.$refs.itemPanel10.style.left =
      this.$refs.navSide.offsetLeft + this.$refs.navSide.offsetWidth + 'px';
      this.$refs.itemPanel11.style.left =
      this.$refs.navSide.offsetLeft + this.$refs.navSide.offsetWidth + 'px';
       this.$refs.itemPanel12.style.left =
      this.$refs.navSide.offsetLeft + this.$refs.navSide.offsetWidth + 'px';
    this.$refs.itemPanel1.style.top = this.$refs.navSide.offsetTop + 'px';
    this.$refs.itemPanel2.style.top = this.$refs.navSide.offsetTop + 'px';
     this.$refs.itemPanel3.style.top = this.$refs.navSide.offsetTop + 'px';
     this.$refs.itemPanel4.style.top = this.$refs.navSide.offsetTop + 'px';
     this.$refs.itemPanel5.style.top = this.$refs.navSide.offsetTop + 'px';
     this.$refs.itemPanel6.style.top = this.$refs.navSide.offsetTop + 'px';
     this.$refs.itemPanel7.style.top = this.$refs.navSide.offsetTop + 'px';
     this.$refs.itemPanel8.style.top = this.$refs.navSide.offsetTop + 'px';
     this.$refs.itemPanel9.style.top = this.$refs.navSide.offsetTop + 'px';
     this.$refs.itemPanel10.style.top = this.$refs.navSide.offsetTop + 'px';
     this.$refs.itemPanel11.style.top = this.$refs.navSide.offsetTop + 'px';
      this.$refs.itemPanel12.style.top = this.$refs.navSide.offsetTop + 'px';
  },
  updated () {
    this.$refs.itemPanel1.style.left =
      this.$refs.navSide.offsetLeft + this.$refs.navSide.offsetWidth + 'px';
    this.$refs.itemPanel2.style.left =
      this.$refs.navSide.offsetLeft + this.$refs.navSide.offsetWidth + 'px';
      this.$refs.itemPanel3.style.left =
      this.$refs.navSide.offsetLeft + this.$refs.navSide.offsetWidth + 'px';
      this.$refs.itemPanel4.style.left =
      this.$refs.navSide.offsetLeft + this.$refs.navSide.offsetWidth + 'px';
       this.$refs.itemPanel5.style.left =
      this.$refs.navSide.offsetLeft + this.$refs.navSide.offsetWidth + 'px';
        this.$refs.itemPanel6.style.left =
      this.$refs.navSide.offsetLeft + this.$refs.navSide.offsetWidth + 'px';
       this.$refs.itemPanel7.style.left =
      this.$refs.navSide.offsetLeft + this.$refs.navSide.offsetWidth + 'px';
      this.$refs.itemPanel8.style.left =
      this.$refs.navSide.offsetLeft + this.$refs.navSide.offsetWidth + 'px';
       this.$refs.itemPanel9.style.left =
      this.$refs.navSide.offsetLeft + this.$refs.navSide.offsetWidth + 'px';
      this.$refs.itemPanel10.style.left =
      this.$refs.navSide.offsetLeft + this.$refs.navSide.offsetWidth + 'px';
      this.$refs.itemPanel11.style.left =
      this.$refs.navSide.offsetLeft + this.$refs.navSide.offsetWidth + 'px';

      this.$refs.itemPanel12.style.left =
      this.$refs.navSide.offsetLeft + this.$refs.navSide.offsetWidth + 'px';
    this.$refs.itemPanel1.style.top = this.$refs.navSide.offsetTop + 'px';



    this.$refs.itemPanel2.style.top = this.$refs.navSide.offsetTop + 'px';
      this.$refs.itemPanel3.style.top = this.$refs.navSide.offsetTop + 'px';
     this.$refs.itemPanel4.style.top = this.$refs.navSide.offsetTop + 'px';
this.$refs.itemPanel5.style.top = this.$refs.navSide.offsetTop + 'px';
this.$refs.itemPanel6.style.top = this.$refs.navSide.offsetTop + 'px';
this.$refs.itemPanel7.style.top = this.$refs.navSide.offsetTop + 'px';
this.$refs.itemPanel8.style.top = this.$refs.navSide.offsetTop + 'px';
this.$refs.itemPanel9.style.top = this.$refs.navSide.offsetTop + 'px';
this.$refs.itemPanel10.style.top = this.$refs.navSide.offsetTop + 'px';
this.$refs.itemPanel11.style.top = this.$refs.navSide.offsetTop + 'px';
this.$refs.itemPanel12.style.top = this.$refs.navSide.offsetTop + 'px';
  },
  store
};
</script>

<style scoped>
.nav-item {
  width: 680px;
  height: 36px;
  margin: 0px auto;
}
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
  height: 485px;
  margin: 0px auto;
  margin-left: 188px;
}
.nav-side {
  width: 250px;
  height: 100%;
  padding: 0px;
  color: #fff;
  float: left;
  background-color: #000;
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
.nav-side-item:hover {
  cursor: pointer;
  color: #c81623;
}

/*导航内容*/
.nav-content {

  width: 792px;
  margin-left: 18px;
  overflow: hidden;
  float: left;
}
/*导航图片*/
.nav-show-img {

  margin-top: 10px;
  float: left;
}
.nav-show-img:nth-child(2) {
  margin-left: 12px;

}
/*显示商品*/
.content {
  width: 100%;
}
/*显示商品详细信息*/
.detail-item-panel {
  width: 440px;
  height: 485px;
  background-color: #fff;
  position: absolute;
  top: 168px;
  left: 389px;
  z-index: 999;
}
.nav-detail-item {
  margin-left: 40px;
  margin-top: 15px;
  margin-bottom: 15px;
  cursor: pointer;
  color: #eee;
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
.detail-item-row a {
  color: #555555;
}
.detail-item{
  font-size: 14px;
  padding-left: 12px;
  padding-right: 8px;
  cursor: pointer;
  border-left: 1px solid #ccc;
}
.detail-item:hover {
  color: #d9534f;
}
.fg{

  float: right;
}
.qw{
  float: left;
}
 .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 100px;
    margin: 0;
  }
.index{
  width: 20px;
}
.qwe{
  margin-left: 1078px;
}
.fg{
  margin-top: 10px;
  margin-right: 6px;
}
.ii{
  margin-top: 18px;
  margin-left: 120px;
}
.rrt{
  margin-bottom: 11px;
}
.detail-item-panel span{
  font-size: 20px;
  margin-top: 40px;
}
.ghg{
  color: #fff;
}

.detail-item span:hover{
  color: red;
}
.ii img{
  border-radius: 20px;
}
</style>
