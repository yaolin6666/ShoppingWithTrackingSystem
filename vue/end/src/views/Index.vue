<template>
  <div>
    <div style="width: 100%;height: 150px;margin: 10px;">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-card shadow="always" style="background-color:darkturquoise;color: white;margin: 0 10px;">
            <i class="el-icon-box" style="float: left;font-size: 80px"/>
            <p style="text-align: center;font-size: 26px;font-weight: bold">商品总数</p>
            <p style="text-align: center;font-size: 36px;margin-top: 20px">{{product}}</p>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card shadow="always" style="background-color:coral;color: white;margin: 0 10px;">
            <i class="el-icon-user" style="float: left;font-size: 80px"/>
            <p style="text-align: center;font-size: 26px;font-weight: bold">用户总数</p>
            <p style="text-align: center;font-size: 36px;margin-top: 20px">{{user}}</p>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card shadow="always" style="background-color: limegreen;color: white;margin: 0 10px;">
            <i class="el-icon-shopping-cart-full" style="float: left;font-size: 80px"/>
            <p style="text-align: center;font-size: 26px;font-weight: bold">今日订单</p>
            <p style="text-align: center;font-size: 36px;margin-top: 20px">{{order}}</p>
          </el-card>
        </el-col>
        <el-col :span="6">
        <el-card shadow="always" style="background-color: gold;color: white;margin-right: 30px">
          <i class="el-icon-star-off" style="float: left;font-size: 70px"/>
          <p style="text-align: center;font-size: 26px;font-weight: bold">好评率</p>
          <p style="text-align: center;font-size: 36px;margin-top: 20px">{{totalSumAll}}%</p>
        </el-card>
      </el-col>
      </el-row>
    </div>
  <div style="width: 100%;height: 500px;display: flex">
    <div ref="chart2" style="width: 600px;height: 500px;margin-left: 50px"></div>
    <div ref="chart1" style="width: 650px;height: 500px;"></div>
  </div>
</div>
</template>

<script>
import request from "@/utils/request.js"
import echarts from "echarts";
  export default {
    name: "Index",
    data(){
      return{
        user:'',
        product:'',
        order:'',
        tableData:[],
        category:[],
       
        pageSize:300,
        jio:'',

      }
    },
    created() {
      
      this.lode()
    },
    methods: {
     

      lode(){
        request.get("/customerInfo/count").then(res =>{
          console.log(res)
          this.user = res.data
        })
        request.get("/info/count").then(res =>{
          console.log(res)
          this.product = res.data
        })
        request.get("/master/count").then(res =>{
          console.log(res)
          this.order = res.data
        })
        request.get("/comment/findAll").then(res =>{
          console.log(res)
          this.tableData = res.data
        })
        request.get("/confirm/count").then(res =>{
          console.log(res)
          this.confirm = res.data
        })
        request.get("/receipt/count").then(res =>{
          console.log(res)
          this.receipt = res.data
        })
        request.get("/refund/count").then(res =>{
          console.log(res)
          this.refund = res.data
        })
        request.get("/assess/count").then(res =>{
          console.log(res)
          this.assess = res.data
        })
        request.get("/comment/count").then(res =>{
          console.log(res)
          this.comment = res.data
        })
        request.get("/info/group").then(res =>{
          console.log(res.data.map(item => {return item.num}))
          this.infonum = res.data.map(item => {return item.num})
        })

      },
    },
    mounted() {
      request.get("/productCategory/list").then(res =>{
        console.log(res)
        this.category = res.data.map(item => {return item.categoryName})
        let echarts = require("echarts"); //引入echarts
        // 基于准备好的dom，初始化echarts实例
        let chart1 = echarts.init(this.$refs.chart1);
        let chart2 = echarts.init(this.$refs.chart2);
        //图表内容
        const option1 = {
          title: {
            text: '订单详情'
          },
          tooltip: {},
          xAxis: {
            type:'category',
            data: ['待发货','发货中','已收货','已退款','待评价','已评价']
          },
          yAxis: {
            type:'value'
          },
          series: [{
            name: '数量',
            type: 'bar',
            data: [this.order,this.confirm,this.receipt,this.refund,this.assess,this.comment,]
          }]
        };
        const option2 = {
          title: {
            text: "各分类商品数",
          },
          tooltip: {},
          //图例组件：展现了不同系列的标记(symbol)，颜色和名字。可以通过点击图例控制哪些系列不显示。
          legend: {
            orient: "vertical",
            x: "left", //可设定图例在左、右、居中
            y: "center", //可设定图例在上、下、居中
            padding: [0, 0, 0, 0], //可设定图例[距上方距离，距右方距离，距下方距离，距左方距离]
            data: this.category,
          },
          series: [
            {
              name: "数量",
              type: "pie", // 设置图表类型为饼图
              radius: "55%", // 饼图的半径，外半径为可视区尺寸（容器高宽中较小一项）的 55% 长度。
              roseType: "angle", //也可以通过设置参数把饼图显示成南丁格尔图。
              data: [
                // 数据数组，name 为数据项名称，value 为数据项值
                { value: this.infonum[0], name: this.category[0] },
                { value: this.infonum[1], name: this.category[1] },
                { value: this.infonum[2], name: this.category[2] },
                { value: this.infonum[3], name: this.category[7] },
                { value: this.infonum[4], name: this.category[9] },
                { value: this.infonum[5], name: this.category[5] },
                { value: this.infonum[6], name: this.category[6] },
                { value: this.infonum[7], name: this.category[3] },
                { value: this.infonum[8], name: this.category[8] },
                { value: this.infonum[9], name: this.category[4] },
                { value: this.infonum[10], name: this.category[11] },
                { value: this.infonum[11], name: this.category[10] },
              ],
            },
          ],
        };
        chart1.setOption(option1),
            chart2.setOption(option2)
      })


    },
    computed: {
      
      totalSumAll(){
        let totalSumAll = 0;
        this.tableData.map((item) => {totalSumAll += item.commentPf})
        totalSumAll = Math.floor((totalSumAll/ (this.tableData.length *5))*100)
        return totalSumAll
         
      },
      
    }
  }

</script>

<style scoped>

</style>
