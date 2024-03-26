# 介绍

---

一款带有溯源系统的农产品销售/团购网站

---
# 开发计划


---
### 溯源系统修改 WIP

~~fabric网络搭建 智能合约的建立~~

~~货源数据库添加 状态管理 数量管理~~

区块链浏览器

~~完成 修改商品数据库 绑定商家~~ 

fabric应用层编写

confrimController

DeliverInfoController

ArgInfoController

order相关controller

### 商家审核机制 Done

~~页面添加~~

~~权限判定~~

~~判定~~

~~申请页面~~

~~数据库~~

### 团购功能 WIP

~~购物流程优化~~

~~schedule进行自动清理订单~~

~~数据库~~

~~后端管理~~

前端处理

## 代码Dao Done

~~arginfo arginfoorigin~~

~~deliverinfoorigin~~~

~~orderorigin~~

~~team template~~

### 订单 WIP

订单详情

创建订单 选择货源号

---

### ~~支付宝沙箱支付~~ Done

~~~
支付宝沙箱账号
商家
账　　号: akcapm8186@sandbox.com
密　　码: 111111

买家
账　　号: pbhaxq7393@sandbox.com
登录密码: 111111
支付密码: 111111
~~~

### 服务器状态
~~~
CONTAINER ID   IMAGE                                                                                                                                                                                   COMMAND                   CREATED          STATUS          PORTS                                                                                                                             NAMES
41c7f47ef920   dev-peer0.org2.example.com-fabrictrackingcontractv1-7ab1b9e70b4a1396fcbebd72ecc218df36cabac350217f8d5107045f8b2ca8c2-d98b45ed671f16eed7dae773b4935b73844d570dc00365be4f30923598303e56   "/__cacert_entrypoin…"   56 minutes ago   Up 56 minutes                                                                                                                                     dev-peer0.org2.example.com-fabricTrackingContractv1-7ab1b9e70b4a1396fcbebd72ecc218df36cabac350217f8d5107045f8b2ca8c2
41ad31c8cf99   dev-peer0.org1.example.com-fabrictrackingcontractv1-7ab1b9e70b4a1396fcbebd72ecc218df36cabac350217f8d5107045f8b2ca8c2-824acad3a665b51e6eb64cac367bc8ad74f62b3928fb6a35478231d8e7e521c6   "/__cacert_entrypoin…"   56 minutes ago   Up 56 minutes                                                                                                                                     dev-peer0.org1.example.com-fabricTrackingContractv1-7ab1b9e70b4a1396fcbebd72ecc218df36cabac350217f8d5107045f8b2ca8c2
78363e18fbae   hyperledger/fabric-tools:latest                                                                                                                                                         "/bin/bash"               59 minutes ago   Up 59 minutes                                                                                                                                     cli
84efa45f5fb8   hyperledger/fabric-orderer:latest                                                                                                                                                       "orderer"                 59 minutes ago   Up 59 minutes   0.0.0.0:7050->7050/tcp, :::7050->7050/tcp, 0.0.0.0:7053->7053/tcp, :::7053->7053/tcp, 0.0.0.0:9443->9443/tcp, :::9443->9443/tcp   orderer.example.com
9db3e2fdb289   hyperledger/fabric-peer:latest                                                                                                                                                          "peer node start"         59 minutes ago   Up 59 minutes   0.0.0.0:7051->7051/tcp, :::7051->7051/tcp, 0.0.0.0:9444->9444/tcp, :::9444->9444/tcp                                              peer0.org1.example.com
bc125f479cef   hyperledger/fabric-peer:latest                                                                                                                                                          "peer node start"         59 minutes ago   Up 59 minutes   0.0.0.0:9051->9051/tcp, :::9051->9051/tcp, 7051/tcp, 0.0.0.0:9445->9445/tcp, :::9445->9445/tcp                                    peer0.org2.example.com
c932cdda5e74   mysql                                                                                                                                                                                   "docker-entrypoint.s…"   2 days ago       Up 2 days       0.0.0.0:3306->3306/tcp, :::3306->3306/tcp, 33060/tcp                                                                              objective_ritchie
a81bc76c5f28   postgres                                                                                                                                                                                "docker-entrypoint.s…"   4 weeks ago      Up 2 days       0.0.0.0:5432->5432/tcp, :::5432->5432/tcp                                                                                         condescending_noether
f380bf5b2247   redis                                                                                                                                                                                   "docker-entrypoint.s…"   4 weeks ago      Up 2 days       0.0.0.0:6379->6379/tcp, :::6379->6379/tcp                                                                                         zen_chatterjee
~~~

# 不足之处
信息录入使用手动方式录入 应结合物联网设备进行上传或者其他形式进行

用户操作便捷度不够 购物车与整个系统功能并不是很融洽