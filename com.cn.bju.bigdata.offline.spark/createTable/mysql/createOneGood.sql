create table shop_goods_pay_info
(
    shop_id             bigint comment '商铺ID',
    sku_id              bigint comment 'SKU商品',
    source_type         varchar(10) comment '渠道类型',
    sku_name            varchar(255) comment '上架商品名称',
    paid_number         double comment '支付件数',
    sale_user_number    bigint comment '支付人数',
    sale_succeed_money  double comment '支付金额',
    all_sale_user_count bigint comment '总下单用户数',
    sku_rate            double comment '支付转化率',
    dt                  date not null
) comment '支付指标' ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table shop_newputaway_goods
(
    shop_id     bigint comment '商铺ID',
    shelve_time date comment '上架时间',
    item_id     bigint comment '上架商品ID',
    item_name   varchar(255) comment '上架商品名称',
    dt          date not null
) comment '商铺-单品分析-最新上架商品数' ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table shop_goods_sale_top
(
    shop_id     bigint comment '商铺ID',
    sku_id      bigint comment 'SKU_ID',
    sku_name    varchar(255) comment 'SKU名称',
    sale_number bigint comment '销量',
    dt          date not null
) comment '商铺-单品分析-商品销量排行' ENGINE=InnoDB DEFAULT CHARSET=utf8;

