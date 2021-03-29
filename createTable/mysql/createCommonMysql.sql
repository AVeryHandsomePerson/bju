create table goods_province_top
(
    shop_id            bigint null,
    province_name      text null,
    sale_user_count    bigint not null,
    sale_succeed_money double null,
    sale_ratio         double null,
    dt                 date   not null
) comment '商品省份TOP 10' ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table goods_province_type_top
(
    shop_id            bigint null,
    order_type         varchar(10) null,
    province_name      text null,
    sale_user_count    bigint not null,
    sale_succeed_money double null,
    sale_ratio         double null,
    dt                 date   not null
) comment '商品省份TOP 10' ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table successful_transaction
(
    shop_id            bigint null,
    order_type varchar(5) null comment '订单类型',
    sale_succeed_money double null  comment '成交金额',
    succeed_orders_number double null  comment '成交单量',
    sale_succeed_number double null  comment '成交件数',
    sale_user_number double null  comment '支付人数',
    dt                 date not null
) comment '成交商品' ENGINE=InnoDB DEFAULT CHARSET=utf8;



