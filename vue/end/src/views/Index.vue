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
    <div ref="chart1" style="width: 800px;margin-left: 10%;height: 500px;"></div>
  </div>
</div>
</template>

<script>
import request from "@/utils/request.js"

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
        customerId: '',
        refundSize:'',
      }
    },
    created() {
      this.customerId=JSON.parse(sessionStorage.getItem('userInfo')).id;
      this.lode()
    },
    methods: {
      lode(){
        request.get("/account/count").then(res =>{
          console.log(res)
          this.user = res.data
        })
        request.get("/product/count",{
          params: {
            customerId: this.customerId
          }
        }
        ).then(res =>{
          console.log(res)
          this.product = res.data
        })
        request.get("/master/count",{
          params: {
            shopId: this.customerId
          }
        }).then(res =>{
          console.log(res)
          this.order = res.data
        })
        request.get("/comment/findAll",{
          params: {
            shopId: this.customerId
          }
        }).then(res =>{
          console.log(res)
          this.tableData = res.data
        })
        request.get("/confirm/count").then(res =>{
          console.log(res)
          this.confirm = res.data
        })
        request.get("/refund/count").then(res =>{
          console.log(res)
          this.refund = res.data
          this.refundSize=JSON.parse(this.refund)
        })
        request.get("/comment/count").then(res =>{
          console.log(res)
          this.comment = res.data
        })
      },
    },
    mounted() {
      request.get("/master/shopCount",{
        params: {
          shopId: this.customerId
        }
      }).then(res =>{
        let orderList=res;
        let order=[],confirm=[],receipt=[];
        for(let i=0;i<orderList.length;i++){
          if(orderList[i].status<200){
            order.push(orderList[i])
          }else if(orderList[i].status<300){
            confirm.push(orderList[i])
          }else if(orderList[i].status<400){
            receipt.push(orderList[i])
          }
        }
        let orderSize=order.length;
        let confirmSize=confirm.length;
        let receiptSize=receipt.length;
        let echarts = require("echarts"); //引入echarts
        let chart1 = echarts.init(this.$refs.chart1);
        const option1 = {
          title: {
            text: '订单详情'
          },
          tooltip: {},
          xAxis: {
            type:'category',
            data: ['待发货','发货中','已收货','已退款']
          },
          yAxis: {
            type:'value'
          },
          series: [{
            name: '数量',
            type: 'bar',
            data: [orderSize,confirmSize,receiptSize,this.refundSize]
          }]
        };
        chart1.setOption(option1)
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
