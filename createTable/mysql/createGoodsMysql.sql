create table goods_profit_top
(
    shop_id            bigint null,
    item_name          text   null,
    sale_user_count    bigint not null,
    sale_succeed_profit double null,
    sale_profit_ratio   double null,
    dt                 date    not null
) comment '商品利润TOP 10' ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table goods_money_top
(
    shop_id            bigint null,
    item_name          text   null,
    sale_user_count    bigint not null,
    sale_succeed_money double null,
    sale_ratio         double null,
    dt                 date    not null
) comment '商品金额TOP 10' ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table goods_sale
(
    shop_id    bigint null,
    var_number bigint not null comment '动销商品品种数',
    sale_user_number bigint not null comment '下单客户数',
    sale_number bigint not null comment '下单笔数',
    sale_goods_number bigint not null comment '下单商品件数',
    dt         date    not null
)comment '商品销售' ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- create table place_orders_goods
-- (
--     shop_id           bigint null,
--     sale_goods_number double null,
--     dt                date    not null
-- )comment '下单商品件数' ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- create table place_orders_user
-- (
--     shop_id         bigint null,
--     sale_user_count bigint not null,
--     dt              date    not null
-- )comment '下单客户数' ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table putaway_item
(
    shop_id     bigint null,
    item_number bigint not null,
    dt          date    not null
)comment '统计在架商品数据' ENGINE=InnoDB DEFAULT CHARSET=utf8;
create table trading_goods
(
    shop_id             bigint null,
    sale_succeed_number double null,
    dt                  date    not null
)comment '成交商品件数' ENGINE=InnoDB DEFAULT CHARSET=utf8;

