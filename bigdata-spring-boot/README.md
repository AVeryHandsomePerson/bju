# 接口
## 1. /getShelves
?shopId=2000000085&startTime=2021-03-29&endTime=2021-03-30

表名:putaway_goods

包含指标：

```
item_number 在架商品数
```

## 2. /getSale
?shopId=2000000082&startTime=2021-03-29&endTime=2021-03-30

表名:shop_goods_sale

包含指标：

```
shop_id
shop_name
sale_number 动销商品数 = 有成交商品的品种数
sale_user_number 下单客户数
sale_number 下单笔数
sale_goods_number 下单商品件数
sale_succeed_number 成交商品件数
sale_succeed_money 成交金额
goods_transform_ratio 商品转换率 --需要埋点目前为00
```



## 3. /getClientPrice 修改为 /getSaleInfo

* 接口传参：

   1. 全平台：

      ?shopId=2000000082&startTime=2021-03-29&endTime=2021-03-30

   2. 区分平台：

      ?shopId=2000000082&startTime=2021-03-29&endTime=2021-03-30&type=TB 

      or

      ?shopId=2000000082&startTime=2021-03-29&endTime=2021-03-30&type=TC

包含指标：

```
    shop_id              
    order_type            
    sale_user_number       '成交客户数' = 支付人数
    orders_succeed_number  '成交单量' =支付订单数
    sale_order_number      '下单笔数'
    sale_succeed_money     '成交金额' = 本店收款的支付金额
    money                  '客单价'
```

## 4. /getMoneyTop

?shopId=2000000082&startTime=2021-03-29&endTime=2021-03-30

商品金额TOP

表名:shop_goods_money_top

包含指标：

```
sale_user_count 统计支付人数
sale_succeed_money 统计支付金额
sale_ratio 统计支付金额占总支付金额比例
```

## 5. /getProfitTop
?shopId=2000000082&startTime=2021-03-29&endTime=2021-03-30

商品利润TOP

表名:shop_goods_profit_top

包含指标：

```
sale_user_count 支付人数
sale_succeed_profit 成交利润
sale_profit_ratio 利润占比
```





## 6. /getProvince

* 接口传参：

   1. 全平台：

      ?shopId=2000000082&startTime=2021-03-29&endTime=2021-03-30

   2. 区分平台：

      ?shopId=2000000082&startTime=2021-03-29&endTime=2021-03-30&type=TB

包含指标：

```
province_name --省份
```



## 7. /getProvinceTop

* 接口传参：

  1. 全平台：

     ?shopId=2000000082&startTime=2021-03-29&endTime=2021-03-30&dt=2021-03-25 

  2. 区分平台：

     ?shopId=2000000082&startTime=2021-03-29&endTime=2021-03-30&type=TB

包含指标：

```
shop_id   --商铺id
shop_name --商铺名称
order_type --渠道
province_name --省份
sale_user_count -- 支付人数
sale_succeed_money --支付金额
sale_ratio -- 支付比例
```





## 8. /getSalSuc --删除

* 接口传参：

   1. 全平台：

       ?shopId=2000000082&startTime=2021-03-29&endTime=2021-03-30

   2. 区分平台：

      ?shopId=2000000082&startTime=2021-03-29&endTime=2021-03-30&type=TB 

包含指标：

1. ```
   交易分析：
   支付金额
   支付人数
   支付订单数
   
   
   成交金额 =本店收款的支付金额
   成交商品件数
   成交单量
   支付人数
   支付订单数，即成交单量
   ```

/getSalSuc?shopId=2000000081&dt=2021-03-22  

| shop_id | sale_succeed_number | succeed_orders_number | sale_user_number | sale_succeed_money | dt   |
| ------- | ------------------- | --------------------- | ---------------- | ------------------ | ---- |
| 商铺ID  | 成交商品件数        | 成交单量              | 支付人数         | 成交金额           | 时间 |

## 9. /getRefundIndex 修改为getRefundInfo

* 接口传参：

   1. 全平台：

       ?shopId=2000000082&startTime=2021-03-29&endTime=2021-03-30

   2. 区分平台：

      ?shopId=2000000082&startTime=2021-03-29&endTime=2021-03-30&type=TB

表名:shop_refund_info

包含指标

```
 shop_id               '商铺ID'
 order_type            '平台类型'
 orders_succeed_number '支付成功数'
 avg_time              '退款平均处理时间'
 refund_money          '成功退款金额'
 refund_number         '成功退款笔数'
 refund_ratio          '退款率'
```

## 10. /getRefundReason

* 接口传参：

  1. 全平台：

     ?shopId=2000000082&startTime=2021-03-29&endTime=2021-03-30

  2. 区分平台：

     ?shopId=2000000082&startTime=2021-03-29&endTime=2021-03-30&type=TB

表名:shop_refund_info

包含指标

```
shop_id              '商铺ID'
shop_name            '商铺名称'
order_type           '平台类型'
refund_reason_number '总退款笔数'
refund_money         '成功退款金额'
refund_number        '成功退款笔数'
refund_number_ratio  '退款金额比'
refund_money_ratio   '退款笔数比'
```





## 11. /getRefundSku

* 接口传参：

  1. 全平台：

     ?shopId=2000000082&startTime=2021-03-29&endTime=2021-03-30

  2. 区分平台：

     ?shopId=2000000082&startTime=2021-03-29&endTime=2021-03-30&type=TB

表名:shop_refund_info

包含指标

```
shop_id              '商铺ID'
shop_name            '商铺名称'
sku_id               '商品ID'
order_type           '平台类型'
sku_name             '商品名称'
refund_reason_number '总退款笔数'
refund_money         '成功退款金额'
orders_succeed_money '订单金额'
refund_number        '成功退款数量'
refund_ratio         '退款率'
refund_reason_ratio  '退款原因比'
```









## 12. /getSaleUser？shopId=2000000085&skuId=2000000082&dt=2021-03-22 --删除

包含指标

```
下单客户数( 商品分析-商品明细)   跟getPayIndex合并
```

| shop_id | sku_id  | sale_user_count | dt   |
| ------- | ------- | --------------- | ---- |
| 商铺ID  | 商品SKU | 下单客户数      | 时间 |



## 13. /getNewPutAway

?shopId=2000000085&startTime=2021-03-29&endTime=2021-03-30

最新在架商品

表名:shop_goods_profit_top

包含指标：

```
shop_id
shelve_time 上架时间
item_id 商品id
item_name 商品名称
```

## 14. /getSaleTop

?shopId=2000000085&startTime=2021-03-29&endTime=2021-03-30

商品销量

表名:shop_goods_sale_top

包含指标：

```
shop_id
sku_id 商品sku_id
sale_number 销售数量
sku_name 商品名称
```

## 15. /getPayIndex

全渠道支付

?shopId=2000000082&startTime=2021-03-29&endTime=2021-03-30&skuId=2000000045&type=all

各渠道支付

?shopId=2000000082&startTime=2021-03-29&endTime=2021-03-30&skuId=2000000045&type=no

包含指标

```
shop_id              '商铺ID'
sku_id               'SKU商品'
source_type          '渠道类型'
sku_name             '上架商品名称'
paid_number          '支付件数'
sale_user_number     '支付人数'
sale_succeed_money   '支付金额'
all_sale_user_count  '总下单用户数'
sku_rate             '支付转化率'
dt                  
```

## 16. /getClientSale

* 接口传参：

   1. 全平台：

      ?shopId=2000000085&startTime=2021-03-29&endTime=2021-03-30

   2. 区分平台：

      ?shopId=2000000085&startTime=2021-03-29&endTime=2021-03-30&type=TB

包含指标

```
shop_id                 
order_type              '平台类型'
user_dis_number         '全部成交客户占比'
present_user_dis_number '当天划分平台成交的用户数'
aged_user_dis_number    '当天划分平台成交的老用户数'
new_user_dis_number     '成交的新用户数'
type_user_ratio         '成交客户占比'
new_user_ratio          '新成交客户占比'
aged_user_ratio         '老成交客户占比'
dt                      
```
## 17. /getClientSaleTop

* 接口传参：

   1. 全平台：

      ?shopId=2000000085&startTime=2021-03-29&endTime=2021-03-30
      
   2. 区分平台：
   
      ?shopId=2000000085&startTime=2021-03-29&endTime=2021-03-30&type=TB

包含指标

```
客户分析:
客户采购排行
shop_id             
order_type           '订单类型'
user_name            '用户名'
sale_succeed_money   '采购金额'
sale_succeed_profit  '采购利润'
dt                  
```

## 18. /pu_info 改为/puInfo

* 接口传参：


​            ?shopId=2000000087&startTime=2021-04-02&endTime=2021-04-02&limit=10&page=0

包含指标

```
采购分析:
详细数据
shop_id             
name       '商品名称'
pu_num     '采购数量'
pu_money   '采购金额'
dt                  
```

## 19. /pu_type改为/puType

* 接口传参：

  1.商品：

   ?shopId=2000000087&startTime=2021-04-02&endTime=2021-04-02&type=1

  2.供应商：

    ?shopId=2000000087&startTime=2021-04-02&endTime=2021-04-02&type=2

  3.类目 

​         ?shopId=2000000087&startTime=2021-04-02&endTime=2021-04-02&type=3

包含指标

```
采购分析:
详细数据
shop_id             
name       
pu_type  '类型'
pu_num   '采购数量'
dt                  
```

## 20. /getWareHouseInOut

* 接口传参：

  1.商品：

  ?shopId=2000000102&startTime=2021-04-02&endTime=2021-04-02&type=1

  分页:

  ?shopId=2000000102&startTime=2021-04-02&endTime=2021-04-02&type=1&limit=10&page=1

  2.仓库：

  ?shopId=2000000102&startTime=2021-04-02&endTime=2021-04-02&type=2

  分页:

  ?shopId=2000000102&startTime=2021-04-02&endTime=2021-04-02&type=2&limit=10&page=1

包含指标

```
id               
shop_id          
business_id      业务ID
business_name    业务名称
real_inbound_num 入库数量
real_outbound_num 出库数量
business_type    1 商品 2仓库
dt                            
```

## 21. /getWareHouseInfo

* 接口传参：

  1.商品：

  ?shopId=2000000102&startTime=2021-04-02&endTime=2021-04-02&type=1

  分页:

  ?shopId=2000000102&startTime=2021-04-02&endTime=2021-04-02&type=1&limit=10&page=1

  2.仓库：

  ?shopId=2000000102&startTime=2021-04-02&endTime=2021-04-02&type=2

  分页:

  ?shopId=2000000102&startTime=2021-04-02&endTime=2021-04-02&type=2&limit=10&page=1

  3.品牌

  ?shopId=2000000102&startTime=2021-04-02&endTime=2021-04-02&type=3

  分页:

  ?shopId=2000000102&startTime=2021-04-02&endTime=2021-04-02&type=2&limit=10&page=1

包含指标

```
id            
shop_id       
business_id   业务ID
business_name 业务名称
inventory     库存
number_money  金额
business_type 1 商品 2仓库  3品牌
dt                             
```

