create table client_sale_analysis
(
    shop_id                 bigint     null,
    user_dis_number         bigint     null comment '全部成交客户占比',
    present_user_dis_number int        null comment '当天划分平台成交的用户数',
    aged_user_dis_number    int        null comment '当天划分平台成交的老用户数',
    new_user_dis_number     int        null comment '成交的新用户数',
    type_user_ratio          double    null comment '成交客户占比',
    new_user_ratio          double     null comment '新成交客户占比',
    aged_user_ratio         double     null comment '老成交客户占比',
    dt                      date       not null
)
    comment '客户销售分析' charset = utf8;
create table client_sale_analysis_type
(
    shop_id                 bigint     null,
    order_type              varchar(5) null comment '平台类型',
    user_dis_number         bigint     null comment '划分平台全部成交客户占比',
    present_user_dis_number int        null comment '划分平台当天划分平台成交的用户数',
    aged_user_dis_number    int        null comment '划分平台当天划分平台成交的老用户数',
    new_user_dis_number     int        null comment '划分平台成交的新用户数',
    type_user_ratio          double     null comment '划分平台成交客户占比',
    new_user_ratio          double     null comment '划分平台新成交客户占比',
    aged_user_ratio         double     null comment '划分平台老成交客户占比',
    dt                      date       not null
)
    comment '分平台客户销售分析' charset = utf8;

create table client_sale_top
(
    shop_id            bigint null,
    order_type varchar(5) null  comment '订单类型',
    user_name varchar(100) null  comment '用户名',
    sale_succeed_money integer null  comment '采购金额',
    sale_succeed_profit integer null  comment '采购利润',
    dt                 date not null
) comment '客户采购排行榜' ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- auto-generated definition
create table client_one_price
(
    shop_id            bigint      null,
    order_type         varchar(5)  null,
    money              double      null,
    sale_user_count    bigint(100) null comment '不区分平台用户数',
    sale_succeed_money double      null comment '成交金额',
    dt                 date        not null
)
    comment '客单价' charset = utf8;