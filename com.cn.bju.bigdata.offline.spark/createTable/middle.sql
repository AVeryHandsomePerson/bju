create table dwd.dwd_sku_name
(
    sku_id    bigint comment '主键',
    item_name String comment '商品名称',
    status    String comment '商品状态',
    cid       String comment '三级类目id'
)COMMENT '商品 sku 中间表'
location '/user/hive/warehouse/dwd.db/dwd_sku_name';


DROP TABLE IF EXISTS dwd.dim_goods_cat;
create table if not exists dwd.dim_goods_cat
(
    cat_3d_id
    string, -- 三级商品分类id
    cat_3d_name
    string, -- 三级商品分类名称
    cat_2d_id
    string, -- 二级商品分类Id
    cat_2d_name
    string, -- 二级商品分类名称
    cat_1t_id
    string, -- 一级商品分类id
    cat_1t_name
    string  -- 一级商品分类名称
)
    partitioned by
(
    dt string
)
    STORED AS parquet TBLPROPERTIES
(
    'parquet.compression'='SNAPPY'
);

create table if not exists dwd.dw_shop_order
(
    shop_id
    string,
    order_id
    string,
    order_source
    string,
    paid
    string,
    industry
    string,
    industry_name
    string
)
    partitioned by
(
    dt string
)
    STORED AS parquet TBLPROPERTIES
(
    'parquet.compression'='SNAPPY'
);


-- auto-generated definition
create
external table if not exists ods.ods_base_dictionary
(
    `id` bigint             comment 'id',
      `code` varchar(20)             comment '编码',
      `name` varchar(50)             comment '名称',
      `type` varchar(20)             comment '所属类型',
      `remark` varchar(200)             comment '备注',
      `create_time` string             comment '创建时间',
      `create_user` bigint             comment '创建人',
      `update_time` string             comment '修改时间',
      `update_user` bigint             comment '修改人',
      `yn` int             comment '是否有效 1:有效 0:无效',
      `delete_flag` int             comment '删除标志0：可删除 1：不可删除',
      `content_type` int             comment '字段类型：0文字 1图片',
      `url` varchar(200)             comment '图片地址',
      `shop_id` bigint             comment '店铺ID'
)
comment '基础字典表'
PARTITIONED BY (
dt string
)
stored as parquet
location '/user/hive/warehouse/ods.db/ods_base_dictionary'
tblproperties ("orc.compression"="snappy");
ALTER TABLE ods_base_dictionary
    ADD IF NOT EXISTS PARTITION (dt='20210325') location '/user/hive/warehouse/ods.db/ods_base_dictionary/dt=20210325/';


--订单中间表
create table dwd.dw_orders_merge_detail
(
    shop_id             bigint,
    order_type          string,
    po_type             string,
    buyer_id            bigint,
    paid                bigint,
    refund              int,
    seller_id           bigint,
    create_time         string,
    payment_time        string,
    status              string,
    order_source        string,
    num                 double,
    sku_id              bigint,
    item_name           string,
    cost_price          bigint,
    payment_total_money double,
    order_id            string,
    cid                 string,
    province_name       string
) COMMENT '订单表 订单详情中间表'
location '/user/hive/warehouse/dwd.db/dw_orders_merge_detail';


create table dwd.dw_refund_orders
(
    id                     bigint comment '主键',
    create_time            string comment '创建时间',
    modify_time            string comment '修改时间',
    refund_reason          string comment '退款原因',
    refund_money           double comment '退款金额',
    sku_id                 bigint comment 'skuID',
    shop_id                bigint comment '店铺ID',
    order_type             string comment '订单类型',
    buyer_id               bigint comment '买家id',
    refund                 bigint comment '退款状态',
    refund_status          int comment '退款退货(换货)状态：
(1)退款(换货)申请等待卖家确认中，
(2)卖家不同意协议,等待买家修改,
(3)退款(换货)申请达成,等待买家发货,
(4)买家已退货,等待卖家确认收货,
(5)退款(换货)关闭,
(6)退款(换货)成功,
(7)退款中',
    order_id               string comment '订单的主键ID',
    paid                   bigint comment '支付状态 1：未支付 2：已支付',
    actually_payment_money double comment '支付状态 1：未支付 2：已支付'
) COMMENT '订单表 订单退货中间表'
location '/user/hive/warehouse/dwd.db/dw_refund_orders';