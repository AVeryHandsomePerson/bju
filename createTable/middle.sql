create  table dwd.dwd_sku_name
(
    sku_id         bigint            comment '主键',
    item_name        String          comment '商品名称'
)COMMENT '商品 sku 中间表'
location '/user/hive/warehouse/dwd.db/dwd_sku_name';