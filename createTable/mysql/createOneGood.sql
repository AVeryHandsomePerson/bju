create table order_sale_user
(
    shop_id           bigint comment '商铺ID',
    sku_id            bigint   comment 'SKU商品',
    sale_user_count   bigint not null comment '下单客户数',
    dt                date    not null
) comment '下单客户' ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table pay_index_info
(
    shop_id              bigint comment '商铺ID',
    sku_id               bigint   comment 'SKU商品',
    paid_number          double  null comment '支付件数',
    sale_user_count      bigint  null comment '支付人数',
    sale_succeed_money   double  null comment '每个商品得支付金额',
    sku_rate             double  null comment '支付转化率',
    dt                   date   not null
) comment '支付指标' ENGINE=InnoDB DEFAULT CHARSET=utf8;
