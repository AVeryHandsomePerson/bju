create table refund_index
(
    shop_id           bigint comment '商铺ID',
    order_type    varchar(5) null comment '平台类型',
    save_user_count    bigint null comment '支付成功数',
    avg_time          Double   comment '退款平均处理时间',
    refund_money      bigint not null comment '成功退款金额',
    refund_number     double comment '成功退款笔数',
    quit_ratio        double comment '退款率',
    dt                date    not null
) comment '退款指标' ENGINE=InnoDB DEFAULT CHARSET=utf8;

