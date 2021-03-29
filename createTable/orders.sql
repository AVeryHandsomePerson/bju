create
external table ods_orders
(
    order_id               bigint  comment '订单编号',
    parent_order_id        bigint  comment '父订单编号',
    order_type             int     comment '订单类型
     * 1 普通订单
     * 2 预售订单
     * 3 线下订单
     * 4 积分订单
     * 5 无价格订单
     * 6 B端采购订单 (手工单-向虚拟店铺购买)
     * 7 B端销售订单 (手工单-向虚拟门店销售)
     * 8 B端采购订单 (正常单-向真实店铺购买)
     * 9 门店手工创建销售订单（暂时作废）
     * 10 客户预下单(定制)',
    status                 int  comment '订单状态 0:系统处理中、1:待付款、2:待配送、3:待收货、4:待评价(已无效由其他字段控制)、5:已完成、6:已取消、7:已关闭、8:已删除、9:订单过期、10:待付尾款、11:未付尾款、12:锁单（冻结）、13:待付定金、14:待审核、15:已审核 、16:已驳回、17:待仓库发货',
    buyer_status           int  comment '买家状态',
    seller_type            int  comment '销售类型 1普通 2定制',
    order_platform         string  comment '所属平台',
    order_source           string  comment '订单的来源
（1）PC商城
（2）H5商城',
    total_money            DOUBLE  comment '订单总金额',
    freight_money          DOUBLE  comment '订单总运费',
    discount_money         DOUBLE  comment '订单优惠总金额',
    cut_money              DOUBLE  comment '分切总金额',
    other_fee              DOUBLE  comment '其他费用',
    round_down             DOUBLE  comment '抹零金额',
    actually_payment_money DOUBLE  comment '应支付的的总金额',
    buyer_id               bigint          comment '买家id',
    buyer_name             string           comment '买家账号',
    buyer_shop_id          bigint           comment '买家店铺ID',
    buyer_shop_name        string           comment '买家店铺名称',
    seller_id              bigint           comment '卖家id',
    seller_name            string           comment '卖家账号',
    shop_id                bigint           comment '店铺ID',
    shop_name              string           comment '店铺名称',
    `option`               string           comment '订单标记',
    paid                   int              comment '支付状态 1：未支付 2：已支付',
    payment_source         string           comment '支付渠道',
    payment_time           string             comment '付款时间',
    refund                 int              comment '是否售后  0未退款退货  1已退款退货',
    `exchange`               int              comment '是否换货  0未换货  1换货',
    invoice                int              comment '是否需要发票 —— 0不需要  1普通发票  2 增值税发票',
    buyer_memo             string           comment '买家备注',
    seller_memo            string           comment '卖家备注',
    is_change_price        int              comment '是否改价  —— 0未改价 1已改价',
    settle_flag            int              comment '结算标识',
    evaluation             string           comment '评价状态(1:无,2:是)',
    create_time            string             comment '创建时间',
    modify_time            string             comment '修改时间',
    create_user            bigint           comment '创建者',
    modify_user            bigint           comment '修改者',
    deposit                DOUBLE   comment '预售定金',
    retainage              DOUBLE   comment '预售尾款',
    retainage_order_id     bigint         comment '尾款支付订单编号',
    presell_id             bigint         comment '预售Id',
    presell_pay_type       int            comment '预售付款类型：0：仅全款，1：仅定金',
    order_credit           int            comment '订单使用积分',
    yn                     int            comment '是否有效： 0无效  1有效
（注：该字段做彻底删除用）',
    manage_user_id         bigint         comment '经销商业务员用户ID(卖方业务员ID)',
    manage_username        string         comment '经销商业务员用户名(卖方业务员名称)',
    buyer_manage_user_id   bigint         comment '买方业务员ID',
    buyer_manage_username  string         comment '买方业务员名称',
    purchase_date          string           comment '采购日期',
    warehouse_code         string         comment '仓库编码(买方入库仓库)',
    warehouse_name         string         comment '仓库名称(买方入库仓库)',
    reason                 string         comment '驳回原因',
    audit_time             string           comment '审核时间',
    audit_user_id          int            comment '审核人ID',
    audit_username         string         comment '审核人用户名',
    remark                 string         comment '备注',
    seller_org_code        string         comment '归属卖家组织机构编码',
    seller_org_parent_code string         comment '归属卖家上级组织机构编码',
    buyer_org_code         string         comment '归属买家组织机构编码',
    buyer_org_parent_code  string         comment '归属买家上级组织机构编码',
    delivery_type          int            comment '配送方式 1：自提 2：汽运',
    print_price            int            comment '是否打印价格： 1，否；2，是',
    consignment            int            comment '发货完成标识：1，否；2，是',
    store_complete         int            comment '入库完成标识：1，否；2，是',
    balance_amount         DOUBLE comment '余额支付金额',
    balance_flag           int            comment '余额支付标识（0，否；1，是）',
    issue_flag             int            comment '订单下发标识 0：未下发 1：已下发 2：下发失败 3:无仓发货（目前只针对c端订单）',
    self_pick_flag         int            comment '1:自提订单 0:非自提订单',
    expect_receive_time    string           comment '期望收货时间',
    delivery_remark        string         comment '发货备注'
) COMMENT '订单表'
PARTITIONED BY (
  `dt` string
)
stored as orc
location '/user/hive/warehouse/ods.db/ods_orders'
tblproperties ("orc.compression"="snappy");
ALTER TABLE ods_orders ADD IF NOT EXISTS PARTITION (dt='20210325') location '/user/hive/warehouse/ods.db/ods_orders/dt=20210325/';

create external table dwd.fact_orders
(
    order_id               bigint  comment '订单编号',
    parent_order_id        bigint  comment '父订单编号',
    order_type             int     comment '订单类型
     * 1 普通订单
     * 2 预售订单
     * 3 线下订单
     * 4 积分订单
     * 5 无价格订单
     * 6 B端采购订单 (手工单-向虚拟店铺购买)
     * 7 B端销售订单 (手工单-向虚拟门店销售)
     * 8 B端采购订单 (正常单-向真实店铺购买)
     * 9 门店手工创建销售订单（暂时作废）
     * 10 客户预下单(定制)',
    status                 int  comment '订单状态 0:系统处理中、1:待付款、2:待配送、3:待收货、4:待评价(已无效由其他字段控制)、5:已完成、6:已取消、7:已关闭、8:已删除、9:订单过期、10:待付尾款、11:未付尾款、12:锁单（冻结）、13:待付定金、14:待审核、15:已审核 、16:已驳回、17:待仓库发货',
    buyer_status           int  comment '买家状态',
    seller_type            int  comment '销售类型 1普通 2定制',
    order_platform         string  comment '所属平台',
    order_source           string  comment '订单的来源
（1）PC商城
（2）H5商城',
    total_money            DOUBLE  comment '订单总金额',
    freight_money          DOUBLE  comment '订单总运费',
    discount_money         DOUBLE  comment '订单优惠总金额',
    cut_money              DOUBLE  comment '分切总金额',
    other_fee              DOUBLE  comment '其他费用',
    round_down             DOUBLE  comment '抹零金额',
    actually_payment_money DOUBLE  comment '应支付的的总金额',
    buyer_id               bigint          comment '买家id',
    buyer_name             string           comment '买家账号',
    buyer_shop_id          bigint           comment '买家店铺ID',
    buyer_shop_name        string           comment '买家店铺名称',
    seller_id              bigint           comment '卖家id',
    seller_name            string           comment '卖家账号',
    shop_id                bigint           comment '店铺ID',
    shop_name              string           comment '店铺名称',
    `option`               string           comment '订单标记',
    paid                   int              comment '支付状态 1：未支付 2：已支付',
    payment_source         string           comment '支付渠道',
    payment_time           string             comment '付款时间',
    refund                 int              comment '是否售后  0未退款退货  1已退款退货',
    `exchange`               int              comment '是否换货  0未换货  1换货',
    invoice                int              comment '是否需要发票 —— 0不需要  1普通发票  2 增值税发票',
    buyer_memo             string           comment '买家备注',
    seller_memo            string           comment '卖家备注',
    is_change_price        int              comment '是否改价  —— 0未改价 1已改价',
    settle_flag            int              comment '结算标识',
    evaluation             string           comment '评价状态(1:无,2:是)',
    create_time            string             comment '创建时间',
    modify_time            string             comment '修改时间',
    create_user            bigint           comment '创建者',
    modify_user            bigint           comment '修改者',
    deposit                DOUBLE   comment '预售定金',
    retainage              DOUBLE   comment '预售尾款',
    retainage_order_id     bigint         comment '尾款支付订单编号',
    presell_id             bigint         comment '预售Id',
    presell_pay_type       int            comment '预售付款类型：0：仅全款，1：仅定金',
    order_credit           int            comment '订单使用积分',
    yn                     int            comment '是否有效： 0无效  1有效
（注：该字段做彻底删除用）',
    manage_user_id         bigint         comment '经销商业务员用户ID(卖方业务员ID)',
    manage_username        string         comment '经销商业务员用户名(卖方业务员名称)',
    buyer_manage_user_id   bigint         comment '买方业务员ID',
    buyer_manage_username  string         comment '买方业务员名称',
    purchase_date          string           comment '采购日期',
    warehouse_code         string         comment '仓库编码(买方入库仓库)',
    warehouse_name         string         comment '仓库名称(买方入库仓库)',
    reason                 string         comment '驳回原因',
    audit_time             string           comment '审核时间',
    audit_user_id          int            comment '审核人ID',
    audit_username         string         comment '审核人用户名',
    remark                 string         comment '备注',
    seller_org_code        string         comment '归属卖家组织机构编码',
    seller_org_parent_code string         comment '归属卖家上级组织机构编码',
    buyer_org_code         string         comment '归属买家组织机构编码',
    buyer_org_parent_code  string         comment '归属买家上级组织机构编码',
    delivery_type          int            comment '配送方式 1：自提 2：汽运',
    print_price            int            comment '是否打印价格： 1，否；2，是',
    consignment            int            comment '发货完成标识：1，否；2，是',
    store_complete         int            comment '入库完成标识：1，否；2，是',
    balance_amount         DOUBLE comment '余额支付金额',
    balance_flag           int            comment '余额支付标识（0，否；1，是）',
    issue_flag             int            comment '订单下发标识 0：未下发 1：已下发 2：下发失败 3:无仓发货（目前只针对c端订单）',
    self_pick_flag         int            comment '1:自提订单 0:非自提订单',
    expect_receive_time    string           comment '期望收货时间',
    delivery_remark        string         comment '发货备注',
    create_zipper_time           string     comment '有效开始时间',
    end_zipper_time           string     comment '有效结束时间'
) COMMENT '订单拉链表'
PARTITIONED BY (
  dt string
)
stored as orc
location '/user/hive/warehouse/dwd.db/fact_orders'
tblproperties ("orc.compression"="snappy");
--动态分区 在spark-sql中执行
set hive.exec.dynamici.partition=true;
set hive.exec.dynamic.partition.mode=nonstrict;
--初始化拉链表
insert overwrite table dwd.fact_orders
select order_id,
       parent_order_id,
       order_type,
       status,
       buyer_status,
       seller_type,
       order_platform,
       order_source,
       total_money,
       freight_money,
       discount_money,
       cut_money,
       other_fee,
       round_down,
       actually_payment_money,
       buyer_id,
       buyer_name,
       buyer_shop_id,
       buyer_shop_name,
       seller_id,
       seller_name,
       shop_id,
       shop_name,
       `option`,
       paid,
       payment_source,
       payment_time,
       refund,
       `exchange`,
       invoice,
       buyer_memo,
       seller_memo,
       is_change_price,
       settle_flag,
       evaluation,
       create_time,
       modify_time,
       create_user,
       modify_user,
       deposit,
       retainage,
       retainage_order_id,
       presell_id,
       presell_pay_type,
       order_credit,
       yn,
       manage_user_id,
       manage_username,
       buyer_manage_user_id,
       buyer_manage_username,
       purchase_date,
       warehouse_code,
       warehouse_name,
       reason,
       audit_time,
       audit_user_id,
       audit_username,
       remark,
       seller_org_code,
       seller_org_parent_code,
       buyer_org_code,
       buyer_org_parent_code,
       delivery_type,
       print_price,
       consignment,
       store_complete,
       balance_amount,
       balance_flag,
       issue_flag,
       self_pick_flag,
       expect_receive_time,
       delivery_remark,
       case when modify_time is not null
       then to_date(modify_time) else to_date(create_time)
       end as create_zipper_time,
       '9999-12-31' as end_zipper_time,
       date_format(create_time,'yyyyMMdd')
from ods.ods_orders


create external table ods.ods_orders_detail
(
    `id`                bigint,
    order_id            bigint comment '订单的主键ID',
    item_id             bigint comment '商品ID',
    cid                 bigint comment '商品的类目ID',
    sku_id              bigint comment 'skuid',
    sku_code            string comment '商家SKU编码',
    item_name           string comment '商品名称',
    sku_pic_url         string comment '商品图片',
    sku_sale_attr_str   string comment '商品销售属性中文名称',
    item_original_price double comment '商品的原始价格',
    cost_price          double comment '成本价',
    payment_total_money double comment '支付的总价格（不含运费）',
    payment_price       double comment '支付的单价（不含运费）',
    cut_price           double comment '分切单价',
    cut_price_total     double comment '分切总价',
    refund              int comment '退款标记',
    `exchange`          int comment '是否换货  0未换货  1换货',
    num                 double comment '商品数量',
    sheet_num           int comment '张数',
    reel                int comment '卷数',
    discount_money      double comment '总的优惠金额',
    freight_template_id bigint comment '运费模板ID',
    delivery_type       int comment '运送方式',
    seller_id           bigint,
    shop_id             bigint,
    buyer_item_id       bigint comment '买方商品编码',
    buyer_sku_id        bigint comment '买方SKUID',
    buyer_sku_code      string comment '买方商家SKU编码',
    evaluation          string comment '订单商品评价状态1为评价，2以评价',
    create_time         string comment '创建时间',
    modify_time         string comment '修改时间',
    create_user         bigint comment '创建者',
    modify_user         bigint comment '修改者',
    is_gift             int comment '是否是赠品  1：是  0，不是',
    inbound_num         double comment '入库数量',
    outbound_num        double comment '出库数量',
    weight_unit         int comment '克重',
    width_unit          int comment '幅宽',
    length_unit         int comment '长度',
    purchase_num        double comment '采购量',
    divided_balance     double comment '分摊的余额金额',
    delivery_date       string comment '交货时间',
    urgent_type         int comment '是否加急 0不加急 1加急',
    already_cut         int comment '是否已分切 0未分切 1已分切',
    already_outbound    int comment '是否已出库 0未出库 1已出库',
    work_order_no       string comment '工单号'
) comment '订单明细表'
PARTITIONED BY (
  dt string
)
stored as orc
location '/user/hive/warehouse/ods.db/ods_orders_detail'
tblproperties ("orc.compression"="snappy");
ALTER TABLE ods.ods_orders_detail
ADD IF NOT EXISTS PARTITION (dt='20210325') location '/user/hive/warehouse/ods.db/ods_orders_detail/dt=20210325/';

--每日全量
create external table ods.ods_orders_receive
(
    id               bigint  comment '主键ID',
    order_id         bigint        comment '订单主键ID',
    consignee_name   string        comment '收货人姓名',
    consignee_mobile string        comment '收货人手机',
    consignee_phone  string        comment '收货人固定电话',
    consignee_mail   string        comment '收货人邮箱',
    province_code    bigint        comment '省ID',
    city_code        bigint        comment '市ID',
    country_code     bigint        comment '县区ID',
    town_code        bigint        ,
    province_name    string        comment '省名称',
    city_name        string        comment '市名称',
    country_name     string        comment '区名称',
    town_name        string        ,
    detail_address   string        comment '详细地址',
    create_time      string      comment '创建时间',
    modify_time      string      comment '修改时间',
    create_user      bigint        comment '创建者',
    modify_user      bigint        comment '修改者',
    yn               int           comment '是否有效'
)
comment '订单收货信息表'
PARTITIONED BY (
  dt string
)
stored as orc
location '/user/hive/warehouse/ods.db/ods_orders_receive'
tblproperties ("orc.compression"="snappy");
ALTER TABLE ods.ods_orders_receive ADD IF NOT EXISTS PARTITION (dt='20210325') location '/user/hive/warehouse/ods.db/ods_orders_receive/dt=20210325/';

create table dwd.fact_orders_receive
(
    id               bigint  comment '主键ID',
    order_id         bigint        comment '订单主键ID',
    consignee_name   string        comment '收货人姓名',
    consignee_mobile string        comment '收货人手机',
    consignee_phone  string        comment '收货人固定电话',
    consignee_mail   string        comment '收货人邮箱',
    province_code    bigint        comment '省ID',
    city_code        bigint        comment '市ID',
    country_code     bigint        comment '县区ID',
    town_code        bigint        ,
    province_name    string        comment '省名称',
    city_name        string        comment '市名称',
    country_name     string        comment '区名称',
    town_name        string        ,
    detail_address   string        comment '详细地址',
    create_time      string      comment '创建时间',
    modify_time      string      comment '修改时间',
    create_user      bigint        comment '创建者',
    modify_user      bigint        comment '修改者',
    yn               int           comment '是否有效',
    create_zipper_time           string     comment '有效开始时间',
    end_zipper_time           string     comment '有效结束时间'
)
comment '订单收货信息拉链表'
PARTITIONED BY (
  dt string
)
stored as orc
location '/user/hive/warehouse/dwd.db/fact_orders_receive'
tblproperties ("orc.compression"="snappy");

--初始化拉链表
insert overwrite table dwd.fact_orders_receive
select id,
       order_id,
       consignee_name,
       consignee_mobile,
       consignee_phone,
       consignee_mail,
       province_code,
       city_code,
       country_code,
       town_code,
       province_name,
       city_name,
       country_name,
       town_name,
       detail_address,
       create_time,
       modify_time,
       create_user,
       modify_user,
       yn,
       case when modify_time is not null
                then to_date(modify_time) else to_date(create_time)
           end as create_zipper_time,
       '9999-12-31' as end_zipper_time,
       date_format(create_time,'yyyyMMdd')
from ods.ods_orders_receive

create external table ods.ods_refund_details
(
    id                bigint              comment '主键ID',
    refund_id         bigint              comment '退款退货表的主键ID',
    order_id          bigint              comment '订单ID',
    order_detail_id   bigint              comment '订单详情的主键ID',
    item_id           bigint              comment '商品的ID',
    item_name         string              comment '商品名称',
    sku_id            bigint              comment 'skuID',
    sku_code          string              comment '商家SKU编码',
    sku_pic_url       string              comment '商品图片',
    sku_sale_attr_str string              comment '商品销售属性中文名称',
    num               double              comment '数量',
    transaction_money double              comment '交易金额',
    refund_money      double              comment '退款金额',
    refund_num        double              comment '退款退货数量',
    refund_price      double              comment '退款单价',
    notes             string              comment '备注',
    create_time       string                comment '创建时间',
    modify_time       string                comment '修改时间',
    create_user       bigint              comment '创建者',
    modify_user       bigint              comment '修改者',
    yn                int                 comment '是否有效',
    buyer_item_id     bigint              comment '买方商品编码',
    buyer_sku_id      bigint              comment '买方SKUID',
    buyer_sku_code    string              comment '买方商家SKU编码',
    inbound_num       double              comment '入库数量',
    outbound_num      double              comment '出库数量',
    divided_balance   double              comment '余额金额'
)
comment '退货退款明细表'
PARTITIONED BY (
  dt string
)
stored as orc
location '/user/hive/warehouse/ods.db/ods_refund_details'
tblproperties ("orc.compression"="snappy");
ALTER TABLE ods.ods_refund_details ADD IF NOT EXISTS PARTITION (dt='20210325') location '/user/hive/warehouse/ods.db/ods_refund_details/dt=20210325/';
------退款拉链表
create external table dwd.fact_refund_detail
(
    id                bigint              comment '主键ID',
    refund_id         bigint              comment '退款退货表的主键ID',
    order_id          bigint              comment '订单ID',
    order_detail_id   bigint              comment '订单详情的主键ID',
    item_id           bigint              comment '商品的ID',
    item_name         string              comment '商品名称',
    sku_id            bigint              comment 'skuID',
    sku_code          string              comment '商家SKU编码',
    sku_pic_url       string              comment '商品图片',
    sku_sale_attr_str string              comment '商品销售属性中文名称',
    num               double              comment '数量',
    transaction_money double              comment '交易金额',
    refund_money      double              comment '退款金额',
    refund_num        double              comment '退款退货数量',
    refund_price      double              comment '退款单价',
    notes             string              comment '备注',
    create_time       string                comment '创建时间',
    modify_time       string                comment '修改时间',
    create_user       bigint              comment '创建者',
    modify_user       bigint              comment '修改者',
    yn                int                 comment '是否有效',
    buyer_item_id     bigint              comment '买方商品编码',
    buyer_sku_id      bigint              comment '买方SKUID',
    buyer_sku_code    string              comment '买方商家SKU编码',
    inbound_num       double              comment '入库数量',
    outbound_num      double              comment '出库数量',
    divided_balance   double              comment '余额金额',
    create_zipper_time           string     comment '有效开始时间',
    end_zipper_time           string     comment '有效结束时间'
)
comment '退货退款明细拉链表'
PARTITIONED BY (
  dt string
)
stored as orc
location '/user/hive/warehouse/dwd.db/fact_refund_detail'
tblproperties ("orc.compression"="snappy");
---------初始化退货拉链表
--动态分区 在spark-sql中执行
set hive.exec.dynamici.partition=true;
set hive.exec.dynamic.partition.mode=nonstrict;
insert overwrite table dwd.fact_refund_detail
select
    id                ,
    refund_id         ,
    order_id          ,
    order_detail_id   ,
    item_id           ,
    item_name         ,
    sku_id            ,
    sku_code          ,
    sku_pic_url       ,
    sku_sale_attr_str ,
    num               ,
    transaction_money ,
    refund_money      ,
    refund_num        ,
    refund_price      ,
    notes             ,
    create_time       ,
    modify_time       ,
    create_user       ,
    modify_user       ,
    yn                ,
    buyer_item_id     ,
    buyer_sku_id      ,
    buyer_sku_code    ,
    inbound_num       ,
    outbound_num      ,
    divided_balance   ,
    case when modify_time is not null
             then to_date(modify_time) else to_date(create_time)
        end as create_zipper_time,
    '9999-12-31' as end_zipper_time,
    date_format(create_time,'yyyyMMdd')
from ods.ods_refund_details
--订单退货表
create external table ods.ods_refund_apply
(
    id                     bigint              comment '主键',
    refund_no              string              comment '退款退货编号',
    type                   int                 comment '退单类型：1，零售退单； 2，采购退单（手工单退单，订单类型6）； 3，销售退单（手工单退单，订单类型7）； 4，采购退单（正常单，订单类型8）； 5，销售退单（正常单，订单类型8）',
    refund_type            int                 comment '退款退货类型：（1）退款，（2）退货 ，（3）换货',
    order_id               bigint              comment '订单的主键ID',
    order_status           int                 comment '订单的状态',
    refund_status          int                 comment '退款退货(换货)状态：
(1) 退款(换货)申请等待卖家确认中，
(2)卖家不同意协议,等待买家修改,
(3)退款(换货)申请达成,等待买家发货,
(4)买家已退货,等待卖家确认收货,
(5)退款(换货)关闭,
(6)退款(换货)成功,
(7)退款中',
    refund_reason          string               comment '退款退货原因',
    question_description   string               comment '问题描述',
    receiving              int                  comment '是否收到货：1收到；2：未收到',
    audit_id               bigint               comment '审核者ID',
    audit_name             string               comment '审核者姓名',
    audit_time             string               comment '审核时间',
    audit_notes            string               comment '审核意见备注',
    refund_total_money     string               comment '可退的总金额',
    apply_refund_money     string               comment '申请的退款金额（含余额支付时，仅表示第三方支付的金额。总退款金额=apply_refund_money+balance_amout）',
    province_code          bigint               comment '省编码',
    province_name          string               comment '省名称',
    city_code              bigint               comment '市编码',
    city_name              string               comment '市名称',
    country_code           bigint               comment '区/县编码',
    country_name           string               comment '区/县名称',
    town_code              bigint               comment '乡/镇编码',
    town_name              string               comment '乡/镇名称',
    detail_address         string               comment '详细地址',
    refund_address         string               comment '退货地址',
    refund_receiver        string               comment '退货收货人',
    refund_mobile          string               comment '退货收货人电话',
    refund_directions      string               comment '退款说明',
    create_time            string               comment '创建时间',
    modify_time            string               comment '修改时间',
    create_user            bigint               comment '创建者',
    modify_user            bigint               comment '修改者',
    yn                     int                  comment '是否有效',
    manage_user_id         bigint               comment '经销商业务员用户ID(卖方业务员ID)',
    manage_username        string               comment '经销商业务员用户名(卖方业务员名称)',
    seller_org_code        string               comment '归属卖家组织机构编码',
    seller_org_parent_code string               comment '归属卖家上级组织机构编码',
    buyer_manage_user_id   bigint               comment '买方业务员ID',
    buyer_manage_username  string               comment '买方业务员名称',
    buyer_org_code         string               comment '归属买家组织机构编码',
    buyer_org_parent_code  string               comment '归属买家上级组织机构编码',
    create_flag            int                  comment '创建标识 1：买方创建 2：卖方创建',
    buyer_id               bigint               comment '买家id',
    buyer_name             string               comment '买家账号',
    buyer_shop_id          bigint               comment '买家店铺ID',
    buyer_shop_name        string               comment '买家店铺名称',
    seller_id              bigint               comment '卖家id',
    seller_name            string               comment '卖家账号',
    shop_id                bigint               comment '店铺ID',
    shop_name              string               comment '店铺名称',
    inbound_num            int                  comment '入库总数量',
    outbound_num           int                  comment '出库总数量',
    all_item_num           int                  comment '订单总商品数量',
    consignment            int                  comment '发货完成标识：1，否；2，是',
    store_complete         int                  comment '入库完成标识：1，否；2，是',
    balance_amount         string               comment '余额金额',
    is_create_bound_bill   int                  comment '是否创建入库单：0，否；1，是'
)
comment '退款退货主表'
PARTITIONED BY (
  dt string
)
stored as orc
location '/user/hive/warehouse/ods.db/ods_refund_apply'
tblproperties ("orc.compression"="snappy");
ALTER TABLE ods.ods_refund_apply ADD IF NOT EXISTS PARTITION (dt='20210325') location '/user/hive/warehouse/ods.db/ods_refund_apply/dt=20210325/';
------退款拉链表
create external table dwd.fact_refund_apply
(
    id                     bigint              comment '主键',
    refund_no              string              comment '退款退货编号',
    type                   int                 comment '退单类型：1，零售退单； 2，采购退单（手工单退单，订单类型6）； 3，销售退单（手工单退单，订单类型7）； 4，采购退单（正常单，订单类型8）； 5，销售退单（正常单，订单类型8）',
    refund_type            int                 comment '退款退货类型：（1）退款，（2）退货 ，（3）换货',
    order_id               bigint              comment '订单的主键ID',
    order_status           int                 comment '订单的状态',
    refund_status          int                 comment '退款退货(换货)状态：
(1) 退款(换货)申请等待卖家确认中，
(2)卖家不同意协议,等待买家修改,
(3)退款(换货)申请达成,等待买家发货,
(4)买家已退货,等待卖家确认收货,
(5)退款(换货)关闭,
(6)退款(换货)成功,
(7)退款中',
    refund_reason          string               comment '退款退货原因',
    question_description   string               comment '问题描述',
    receiving              int                  comment '是否收到货：1收到；2：未收到',
    audit_id               bigint               comment '审核者ID',
    audit_name             string               comment '审核者姓名',
    audit_time             string               comment '审核时间',
    audit_notes            string               comment '审核意见备注',
    refund_total_money     string               comment '可退的总金额',
    apply_refund_money     string               comment '申请的退款金额（含余额支付时，仅表示第三方支付的金额。总退款金额=apply_refund_money+balance_amout）',
    province_code          bigint               comment '省编码',
    province_name          string               comment '省名称',
    city_code              bigint               comment '市编码',
    city_name              string               comment '市名称',
    country_code           bigint               comment '区/县编码',
    country_name           string               comment '区/县名称',
    town_code              bigint               comment '乡/镇编码',
    town_name              string               comment '乡/镇名称',
    detail_address         string               comment '详细地址',
    refund_address         string               comment '退货地址',
    refund_receiver        string               comment '退货收货人',
    refund_mobile          string               comment '退货收货人电话',
    refund_directions      string               comment '退款说明',
    create_time            string               comment '创建时间',
    modify_time            string               comment '修改时间',
    create_user            bigint               comment '创建者',
    modify_user            bigint               comment '修改者',
    yn                     int                  comment '是否有效',
    manage_user_id         bigint               comment '经销商业务员用户ID(卖方业务员ID)',
    manage_username        string               comment '经销商业务员用户名(卖方业务员名称)',
    seller_org_code        string               comment '归属卖家组织机构编码',
    seller_org_parent_code string               comment '归属卖家上级组织机构编码',
    buyer_manage_user_id   bigint               comment '买方业务员ID',
    buyer_manage_username  string               comment '买方业务员名称',
    buyer_org_code         string               comment '归属买家组织机构编码',
    buyer_org_parent_code  string               comment '归属买家上级组织机构编码',
    create_flag            int                  comment '创建标识 1：买方创建 2：卖方创建',
    buyer_id               bigint               comment '买家id',
    buyer_name             string               comment '买家账号',
    buyer_shop_id          bigint               comment '买家店铺ID',
    buyer_shop_name        string               comment '买家店铺名称',
    seller_id              bigint               comment '卖家id',
    seller_name            string               comment '卖家账号',
    shop_id                bigint               comment '店铺ID',
    shop_name              string               comment '店铺名称',
    inbound_num            int                  comment '入库总数量',
    outbound_num           int                  comment '出库总数量',
    all_item_num           int                  comment '订单总商品数量',
    consignment            int                  comment '发货完成标识：1，否；2，是',
    store_complete         int                  comment '入库完成标识：1，否；2，是',
    balance_amount         string               comment '余额金额',
    is_create_bound_bill   int                  comment '是否创建入库单：0，否；1，是',
    create_zipper_time           string     comment '有效开始时间',
    end_zipper_time           string     comment '有效结束时间'
)
comment '退款退货主表拉链表'
PARTITIONED BY (
  dt string
)
stored as orc
location '/user/hive/warehouse/dwd.db/fact_refund_apply'
tblproperties ("orc.compression"="snappy");


insert overwrite table dwd.fact_refund_apply
select
    id,
    refund_no,
    type,
    refund_type,
    order_id,
    order_status,
    refund_status,
    refund_reason,
    question_description,
    receiving,
    audit_id,
    audit_name,
    audit_time,
    audit_notes,
    refund_total_money,
    apply_refund_money,
    province_code,
    province_name,
    city_code,
    city_name,
    country_code,
    country_name,
    town_code,
    town_name,
    detail_address,
    refund_address,
    refund_receiver,
    refund_mobile,
    refund_directions,
    create_time,
    modify_time,
    create_user,
    modify_user,
    yn,
    manage_user_id,
    manage_username,
    seller_org_code,
    seller_org_parent_code,
    buyer_manage_user_id,
    buyer_manage_username,
    buyer_org_code,
    buyer_org_parent_code,
    create_flag,
    buyer_id,
    buyer_name,
    buyer_shop_id,
    buyer_shop_name,
    seller_id,
    seller_name,
    shop_id,
    shop_name,
    inbound_num,
    outbound_num,
    all_item_num,
    consignment,
    store_complete,
    balance_amount,
    is_create_bound_bill,
    case when modify_time is not null
             then to_date(modify_time) else to_date(create_time)
        end as create_zipper_time,
    '9999-12-31' as end_zipper_time,
    date_format(create_time,'yyyyMMdd')
from ods.ods_refund_apply




--订单中间表
create table dwd.dw_orders_merge_detail
(
    shop_id             bigint,
    order_type          string,
    buyer_id            bigint,
    paid                bigint,
    refund              int,
    seller_id           bigint,
    create_time         string,
    payment_time         string,
    status         string,
    num                 double ,
    sku_id              bigint ,
    item_name           string ,
    cost_price          bigint ,
    payment_total_money double ,
    order_id            string,
    province_name       string
) COMMENT '订单表 订单详情中间表'
location '/user/hive/warehouse/dwd.db/dw_orders_merge_detail';


create table dwd.dw_refund_orders
(
    shop_id      bigint comment '店铺ID',
    order_type      string comment '订单类型',
    buyer_id     bigint comment '买家id',
    refund_status  int  comment '退款退货(换货)状态：
(1)退款(换货)申请等待卖家确认中，
(2)卖家不同意协议,等待买家修改,
(3)退款(换货)申请达成,等待买家发货,
(4)买家已退货,等待卖家确认收货,
(5)退款(换货)关闭,
(6)退款(换货)成功,
(7)退款中',
    order_id     string comment '订单的主键ID',
    paid         bigint comment '支付状态 1：未支付 2：已支付',
    id           bigint comment '主键',
    create_time  string comment '创建时间',
    modify_time  string comment '修改时间',
    refund_money  double comment '退款金额'
) COMMENT '订单表 订单退货中间表'
location '/user/hive/warehouse/dwd.db/dw_refund_orders';



