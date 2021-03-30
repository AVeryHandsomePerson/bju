create  table dwd.dwd_sku_name
(
    sku_id         bigint            comment '主键',
    item_name        String          comment '商品名称'
)COMMENT '商品 sku 中间表'
location '/user/hive/warehouse/dwd.db/dwd_sku_name';


DROP TABLE IF EXISTS dwd.dim_goods_cat;
create table if not exists dwd.dim_goods_cat(
cat_3d_id string,                   -- 三级商品分类id
cat_3d_name string,                 -- 三级商品分类名称
cat_2d_id string,                    -- 二级商品分类Id
cat_2d_name string,                  -- 二级商品分类名称
cat_1t_id string,                   -- 一级商品分类id
cat_1t_name string                  -- 一级商品分类名称
)
partitioned by (dt string)
STORED AS PARQUET TBLPROPERTIES('parquet.compression'='SNAPPY');

create table if not exists dwd.dw_shop_order(
shop_id string,
order_id string,
order_source string,
paid string,
industry string,
industry_name string
)
partitioned by (dt string)
STORED AS PARQUET TBLPROPERTIES('parquet.compression'='SNAPPY');






-- auto-generated definition
create table if not exists ods.ods_base_dictionary
(
    id           bigint             comment 'id',
    code         string             comment '编码',
    `name`       string             comment '名称',
    `type`       string             comment '所属类型',
    remark       string             comment '备注',
    create_time  string             comment '创建时间',
    create_user  bigint             comment '创建人',
    update_time  string             comment '修改时间',
    update_user  bigint             comment '修改人',
    yn           int                comment '是否有效 1:有效 0:无效',
    delete_flag  int                comment '删除标志0：可删除 1：不可删除',
    content_type int                comment '字段类型：0文字 1图片',
    url          string             comment '图片地址',
    shop_id      bigint             comment '店铺ID'
)
comment '基础字典表'
PARTITIONED BY (
dt string
)
stored as orc
location '/user/hive/warehouse/ods.db/ods_base_dictionary'
tblproperties ("orc.compression"="snappy");
ALTER TABLE ods_base_dictionary ADD IF NOT EXISTS PARTITION (dt='20210325') location '/user/hive/warehouse/ods.db/ods_base_dictionary/dt=20210325/';
