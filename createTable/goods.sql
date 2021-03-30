--商品表
CREATE external  TABLE `ods_start_log` (
url string COMMENT '当前页面url',
refer string COMMENT '跳转页面url',
time_in string COMMENT '进入页面时间',
time_out string COMMENT '退出页面时间',
time_keep string COMMENT '页面停留时间（秒）',
user_cookies string COMMENT '获取COOKIE里面的值',
domain string COMMENT '',
title string COMMENT '页面title',
language string COMMENT '获取用户的默认语言',
serial_num string COMMENT '浏览器关闭标识（3分钟无操作）',
user_ip string COMMENT 'IP地址',
key_words string COMMENT '引擎关键字',
finger_print string COMMENT '浏览器指纹（浏览器配置不变，唯一标识）',
description string COMMENT '页面描述'
)
PARTITIONED BY (
  `day` string
)
STORED AS parquet
LOCATION  'hdfs://bogon:8020/user/hive/warehouse/bjubigdata_hdfs_db.db/hdfs_page';

--------在售商品数据表
CREATE external  TABLE `dws_goods_number` (
item_id string  COMMENT '商品id',
item_name string  COMMENT '商品名称',
seller_id string   COMMENT '商家ID',
sale_number string   COMMENT '商品数量'
)
PARTITIONED BY (
  dt string
)
STORED AS parquet
LOCATION  'hdfs://bogon:8020/user/hive/warehouse/goods.db/dws_goods_number';


CREATE TABLE `trade_orders` (
`id` BIGINT COMMENT '主键',
`order_id` BIGINT COMMENT '订单id',
`parent_order_id` BIGINT COMMENT '是否为子订单(0未拆单，-1为父订单，其它为父订单ID)',
`seller_id` BIGINT COMMENT '卖家ID',
`buyer_id` BIGINT COMMENT '买家ID',
`shop_id` BIGINT COMMENT '店铺ID',
`name` string COMMENT '用户名',
`province_id` BIGINT COMMENT '省',
`city_id` BIGINT COMMENT '市',
`county_id` BIGINT COMMENT '县',
`detail_address` string COMMENT '详细地址',
`full_address` string COMMENT '全地址',
`mobile` string COMMENT '移动电话',
`phone` string COMMENT '固定电话',
`email` string COMMENT '邮件',
`order_time` datetime DEFAULT NULL COMMENT '下订单时间',
`order_finish_time` datetime DEFAULT NULL COMMENT '订单完成时间',
`total_price` double COMMENT '优惠前金额',
`total_discount` double COMMENT '总优惠金额',
`freight` double COMMENT '运费',
`payment_price` double COMMENT '实际支付金额',
`state` int COMMENT '订单状态 1:待付款，2：待配送，3：待确认送货，4：待评价，5：已完成',
`yn` int COMMENT '取消订单(1:否，2:是)',
`order_jfs_key` string COMMENT 'jfs读取key',
`invoice` int COMMENT '是否有发票(1:无，2:有)',
`invoice_title` string COMMENT '发票抬头',
`logistics_no` string COMMENT '物流编号',
`logistics_company` string COMMENT '物流公司',
`memo` string COMMENT '备注',
`payment_type` int COMMENT '支付类型(1:网银,2:中信,3:线下)',
`payment_time` TIMESTAMP COMMENT '付款时间',
`shipment_type` int COMMENT '配送类型',
`deleted` int COMMENT '订单删除(1:无,2:是)',
`delete_time` datetime DEFAULT NULL COMMENT '订单删除时间',
`locked` int COMMENT '订单锁定(1:无,2:是)',
`lock_time` datetime DEFAULT NULL COMMENT '锁定时间',
`refund` int COMMENT '退款(1:无,2:是)',
`refund_time` datetime DEFAULT NULL COMMENT '退款时间',
`delay` int COMMENT '延期收货(1:无,2:是)',
`delay_over_time` datetime DEFAULT NULL COMMENT '延期收货结束时间',
`evaluate` int COMMENT '买家订单评价(1:无,2:是)',
`seller_evaluate` int COMMENT '卖家订单评价(1:无,2:是)',
`after_service` int COMMENT '申请售后服务(1:无,2:是,3:完成)',
`create_time` TIMESTAMP COMMENT '创建时间',
`update_time` TIMESTAMP COMMENT '订单更新时间',
`cancel_time` TIMESTAMP COMMENT '订单取消时间',
`change_payment_price_time` TIMESTAMP COMMENT '修改实际支付金额时间',
`paid` int DEFAULT '1' COMMENT '是否付款 1未付款 2 已付款',
`pay_period` int  COMMENT '支付账期（天）',
`promo_code` string COMMENT '优惠码',
`deliver_time` TIMESTAMP DEFAULT NULL COMMENT '订单发货时间',
`is_change_price` int DEFAULT '0' COMMENT '是否改价(1:是，0:否)'
)
--订单详情
CREATE TABLE `trade_order_items` (
`id` string COMMENT '主键',
`order_id` string COMMENT '订单id',
`item_id` string COMMENT 'SKU所属品类ID',
`cid` string COMMENT '类目ID',
`sku_id` string COMMENT 'SKU',
`sku_name` string COMMENT 'SKU名称',
`primitive_price` string COMMENT '原始价',
`pay_price_total` string COMMENT '实际支付总金额',
`pay_price` string COMMENT '实际支付金额',
`area_id` string COMMENT '区域ID',
`num` BIGINT COMMENT '数量',
`promotion_discount` string COMMENT '促销优惠金额',
`promotion_id` string COMMENT '促销ID',
`promotion_type` int COMMENT '促销类型(1直降，2满减)',
`create_time` string COMMENT '创建时间',
`update_time` string COMMENT '更新时间'
);



create external table ods_item
(
    item_id                  bigint        comment '商品id',
    brand_id                 bigint        comment '品牌',
    cid                      bigint        comment '类目ID',
    seller_id                bigint        comment '商家ID',
    shop_id                  bigint        comment '店铺ID',
    shop_cid                 string        comment '商品所属店铺类目id：可以绑定多个店铺类目',
    shop_freight_template_id bigint        comment '运费模版ID',
    attributes               string        comment '商品类目属性keyId:valueId',
    attr_sale                string        comment '商品销售属性keyId:valueId',
    status                   int           comment '商品状态,1:未发布、2:待审核、3:待上架、4:在售、5:已下架、6:锁定、7:申请解锁、8:平台下架、9:系统下架、10:定时下架、11:下线、20:审核驳回、30:删除 ',
    type                     int           comment '1:普通商品 2:虚拟商品',
    item_name                string        comment '商品名称',
    shelve_time              date          comment '上架时间',
    off_shelve_time          date          comment '下架时间',
    task_shelve_time         date          comment '定时上架时间',
    task_off_shelve_time     date          comment '定时下架时间',
    origin                   string        comment '商品产地',
    weight                   string        comment '商品毛重',
    volume                   string        comment '商品体积',
    length                   string        comment '长',
    width                    string        comment '宽',
    height                   string        comment '高',
    ad                       string        comment '广告词',
    keyword                  string        comment '关键字：每个商品可设置5个关键字，英文逗号分隔',
    remark                   string,
    unit_code                string        comment '单位编码',
    unit_name                string        comment '单位名称',
    quotation_way            string        comment '报价方式  1: 按产品数量报价  2：按产品规格报价',
    create_time              date          comment '创建日期',
    create_user              bigint,
    update_time              date          comment '修改日期',
    update_user              bigint,
    yn                       int           comment '是否有效',
    sign                     string        comment '标记',
    status_change_reason     string        comment '平台方下架或锁定或审核驳回时给出的理由',
    platform                 string        comment '所属平台',
    give_away                int           comment '是否是赠品  1:是  2:否',
    pay_type                 string        comment '支付方式  1.货到付款  2:在线支付',
    sale_channel             string        comment '销售渠道  1:web  2:h5  3:app  4:微信',
    points                   float         comment '积分比例',
    upc                      string        comment '商品通用条码：管理商品_单个商品层面',
    goods_code               string        comment '货号：管理商品_批发层面',
    attr_template_id         bigint        comment '属性模板ID',
    `describe`               string        comment '商品描述',
    ad_url                   string        comment '广告词链接',
    source_item_id           bigint        comment '原商品ID',
    master_item_id           bigint        comment '主商品ID',
    rebate_flag              int           comment '是否设置商品交易扣点0:未设置 1:已设置',
    shop_sales_terr_temp_id  bigint        comment '店铺销售区域模板ID'
)
PARTITIONED BY (
  dt string
)
stored as orc
location '/user/hive/warehouse/ods.db/ods_item'
tblproperties ("orc.compression"="snappy");
ALTER TABLE ods_item ADD IF NOT EXISTS PARTITION (dt='2021-03-22') location '/user/hive/warehouse/ods.db/ods_item/dt=2021-03-22/';

create external table dwd.fact_item
(
    item_id                  bigint        comment '商品id',
    brand_id                 bigint        comment '品牌',
    cid                      bigint        comment '类目ID',
    seller_id                bigint        comment '商家ID',
    shop_id                  bigint        comment '店铺ID',
    shop_cid                 string        comment '商品所属店铺类目id：可以绑定多个店铺类目',
    shop_freight_template_id bigint        comment '运费模版ID',
    attributes               string        comment '商品类目属性keyId:valueId',
    attr_sale                string        comment '商品销售属性keyId:valueId',
    status                   int           comment '商品状态,1:未发布、2:待审核、3:待上架、4:在售、5:已下架、6:锁定、7:申请解锁、8:平台下架、9:系统下架、10:定时下架、11:下线、20:审核驳回、30:删除 ',
    type                     int           comment '1:普通商品 2:虚拟商品',
    item_name                string        comment '商品名称',
    shelve_time              date          comment '上架时间',
    off_shelve_time          date          comment '下架时间',
    task_shelve_time         date          comment '定时上架时间',
    task_off_shelve_time     date          comment '定时下架时间',
    origin                   string        comment '商品产地',
    weight                   string        comment '商品毛重',
    volume                   string        comment '商品体积',
    length                   string        comment '长',
    width                    string        comment '宽',
    height                   string        comment '高',
    ad                       string        comment '广告词',
    keyword                  string        comment '关键字：每个商品可设置5个关键字，英文逗号分隔',
    remark                   string,
    unit_code                string        comment '单位编码',
    unit_name                string        comment '单位名称',
    quotation_way            string        comment '报价方式  1: 按产品数量报价  2：按产品规格报价',
    create_time              date          comment '创建日期',
    create_user              bigint,
    update_time              date          comment '修改日期',
    update_user              bigint,
    yn                       int           comment '是否有效',
    sign                     string        comment '标记',
    status_change_reason     string        comment '平台方下架或锁定或审核驳回时给出的理由',
    platform                 string        comment '所属平台',
    give_away                int           comment '是否是赠品  1:是  2:否',
    pay_type                 string        comment '支付方式  1.货到付款  2:在线支付',
    sale_channel             string        comment '销售渠道  1:web  2:h5  3:app  4:微信',
    points                   float         comment '积分比例',
    upc                      string        comment '商品通用条码：管理商品_单个商品层面',
    goods_code               string        comment '货号：管理商品_批发层面',
    attr_template_id         bigint        comment '属性模板ID',
    `describe`               string        comment '商品描述',
    ad_url                   string        comment '广告词链接',
    source_item_id           bigint        comment '原商品ID',
    master_item_id           bigint        comment '主商品ID',
    rebate_flag              int           comment '是否设置商品交易扣点0:未设置 1:已设置',
    shop_sales_terr_temp_id  bigint        comment '店铺销售区域模板ID',
    create_zipper_time       string        comment '有效开始时间',
    end_zipper_time          string        comment '有效结束时间'
)
PARTITIONED BY (
  dt string
)
stored as orc
location '/user/hive/warehouse/dwd.db/fact_item'
tblproperties ("orc.compression"="snappy");

--初始化商品拉链表
insert overwrite table dwd.fact_item
select
    item_id                 ,
    brand_id                ,
    cid                     ,
    seller_id               ,
    shop_id                 ,
    shop_cid                ,
    shop_freight_template_id,
    attributes              ,
    attr_sale               ,
    status                  ,
    type                    ,
    item_name               ,
    shelve_time             ,
    off_shelve_time         ,
    task_shelve_time        ,
    task_off_shelve_time    ,
    origin                  ,
    weight                  ,
    volume                  ,
    length                  ,
    width                   ,
    height                  ,
    ad                      ,
    keyword                 ,
    remark                  ,
    unit_code               ,
    unit_name               ,
    quotation_way           ,
    create_time             ,
    create_user             ,
    update_time             ,
    update_user             ,
    yn                      ,
    sign                    ,
    status_change_reason    ,
    platform                ,
    give_away               ,
    pay_type                ,
    sale_channel            ,
    points                  ,
    upc                     ,
    goods_code              ,
    attr_template_id        ,
    `describe`              ,
    ad_url                  ,
    source_item_id          ,
    master_item_id          ,
    rebate_flag             ,
    shop_sales_terr_temp_id ,
    case when update_time is not null
    then to_date(update_time) else to_date(create_time)
    end as create_zipper_time,
    '9999-12-31' as end_zipper_time,
    date_format(create_time,'yyyyMMdd')
from
    ods.ods_item


create external table ods.ods_item_sku
(
    sku_id         bigint             comment '主键',
    item_id        bigint          comment '商品id',
    seller_id      bigint          comment '卖家id',
    shop_id        bigint          comment '店铺id',
    attributes     string          comment '销售属性集合：keyId:valueId',
    sku_code       string          comment '商家sku编码',
    status         int             comment 'sku 状态,1:有效;2:无效',
    create_time    string          comment '创建日期',
    update_time    string          comment '修改日期',
    create_user    bigint         ,
    update_user    bigint         ,
    yn             int             comment '0：无效 1：有效;',
    source_item_id bigint          comment '原商品ID',
    source_sku_id  bigint          comment '原SKUID',
    master_item_id bigint          comment '主商品ID',
    master_sku_id  bigint          comment '主SKUID'
)
    comment 'sku信息'
PARTITIONED BY (
  dt string
)
stored as orc
location '/user/hive/warehouse/ods.db/ods_item_sku'
tblproperties ("orc.compression"="snappy");

ALTER TABLE ods_item_sku ADD IF NOT EXISTS PARTITION (dt='20210325') location '/user/hive/warehouse/ods.db/ods_item_sku/dt=20210325/';

create external table ods.ods_item_category
(
    cid         string                   comment '类目id',
    parent_cid  int                      comment '父级ID',
    name        string                   comment '类目名称',
    level       int                      comment '级别',
    leaf        string                   comment '是否叶子节点',
    sort_num    string                   comment '排序号',
    status      int                      comment '类目级别',
    channel     string                   comment '支持渠道  1:WAP  2:Web 3:APP',
    sign        string                   comment '标记',
    create_time string                   comment '创建日期',
    update_time string                   comment '修改日期',
    create_user bigint                  ,
    update_user bigint                  ,
    yn          int                      comment '1:有效;0:删除',
    picture_url string                   comment '图片url',
    platform    string                   comment '所属平台',
    seller_code string                   comment '商家类目编码',
    activity    string                   comment '参加活动：1、支持7天无理由退货，2、支持增值税专用票 逗号分隔',
    rebate_flag int                      comment '是否设置商品交易扣点0:未设置 1:已设置  （只3级类目  一二级都为0）'
)
    comment '后台类目'
PARTITIONED BY (
  dt string
)
stored as orc
location '/user/hive/warehouse/ods.db/ods_item_category'
tblproperties ("orc.compression"="snappy");
ALTER TABLE ods_item_category ADD IF NOT EXISTS PARTITION (dt='20210325') location '/user/hive/warehouse/ods.db/ods_item_category/dt=20210325/';
