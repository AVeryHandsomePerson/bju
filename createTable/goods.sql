create external table ods.ods_item
(
    item_id                  bigint        comment '商品id',
    brand_id                 bigint        comment '品牌',
    cid                      bigint        comment '类目ID',
    seller_id                bigint        comment '商家ID',
    shop_id                  bigint        comment '店铺ID',
    shop_cid                 string        comment '商品所属店铺类目id：可以绑定多个店铺类目',
    shop_freight_template_id bigint        comment '运费模版ID',
    attributes               string        comment '商品类目属性keyId:valueId',
    attr_sale                string        comment '商品销售属性keyId:valueId',
    status                   int           comment '商品状态,1:未发布、2:待审核、3:待上架、4:在售、5:已下架、6:锁定、7:申请解锁、8:平台下架、9:系统下架、10:定时下架、11:下线、20:审核驳回、30:删除 ',
    type                     int           comment '1:普通商品 2:虚拟商品',
    item_name                string        comment '商品名称',
    shelve_time              date          comment '上架时间',
    off_shelve_time          date          comment '下架时间',
    task_shelve_time         date          comment '定时上架时间',
    task_off_shelve_time     date          comment '定时下架时间',
    origin                   string        comment '商品产地',
    weight                   string        comment '商品毛重',
    volume                   string        comment '商品体积',
    length                   string        comment '长',
    width                    string        comment '宽',
    height                   string        comment '高',
    ad                       string        comment '广告词',
    keyword                  string        comment '关键字：每个商品可设置5个关键字，英文逗号分隔',
    remark                   string,
    unit_code                string        comment '单位编码',
    unit_name                string        comment '单位名称',
    quotation_way            string        comment '报价方式  1: 按产品数量报价  2：按产品规格报价',
    create_time              date          comment '创建日期',
    create_user              bigint,
    update_time              date          comment '修改日期',
    update_user              bigint,
    yn                       int           comment '是否有效',
    sign                     string        comment '标记',
    status_change_reason     string        comment '平台方下架或锁定或审核驳回时给出的理由',
    platform                 string        comment '所属平台',
    give_away                int           comment '是否是赠品  1:是  2:否',
    pay_type                 string        comment '支付方式  1.货到付款  2:在线支付',
    sale_channel             string        comment '销售渠道  1:web  2:h5  3:app  4:微信',
    points                   float         comment '积分比例',
    upc                      string        comment '商品通用条码：管理商品_单个商品层面',
    goods_code               string        comment '货号：管理商品_批发层面',
    attr_template_id         bigint        comment '属性模板ID',
    `describe`               string        comment '商品描述',
    ad_url                   string        comment '广告词链接',
    source_item_id           bigint        comment '原商品ID',
    master_item_id           bigint        comment '主商品ID',
    rebate_flag              int           comment '是否设置商品交易扣点0:未设置 1:已设置',
    shop_sales_terr_temp_id  bigint        comment '店铺销售区域模板ID'
)
PARTITIONED BY (
  dt string
)
stored as orc
location '/user/hive/warehouse/ods.db/ods_item'
tblproperties ("orc.compression"="snappy");
ALTER TABLE ods_item ADD IF NOT EXISTS PARTITION (dt='2021-03-22') location '/user/hive/warehouse/ods.db/ods_item/dt=2021-03-22/';

create external table ods.ods_item_sku
(
    sku_id         bigint          comment '主键',
    item_id        bigint          comment '商品id',
    seller_id      bigint          comment '卖家id',
    shop_id        bigint          comment '店铺id',
    attributes     string          comment '销售属性集合：keyId:valueId',
    sku_code       string          comment '商家sku编码',
    status         int             comment 'sku 状态,1:有效;2:无效',
    create_time    string          comment '创建日期',
    update_time    string          comment '修改日期',
    create_user    bigint         ,
    update_user    bigint         ,
    yn             int             comment '0：无效 1：有效;',
    source_item_id bigint          comment '原商品ID',
    source_sku_id  bigint          comment '原SKUID',
    master_item_id bigint          comment '主商品ID',
    master_sku_id  bigint          comment '主SKUID'
)
    comment 'sku信息'
PARTITIONED BY (
  dt string
)
stored as orc
location '/user/hive/warehouse/ods.db/ods_item_sku'
tblproperties ("orc.compression"="snappy");

ALTER TABLE ods_item_sku ADD IF NOT EXISTS PARTITION (dt='20210325') location '/user/hive/warehouse/ods.db/ods_item_sku/dt=20210325/';

create external table ods.ods_item_category
(
    cid         string                   comment '类目id',
    parent_cid  int                      comment '父级ID',
    name        string                   comment '类目名称',
    level       int                      comment '级别',
    leaf        string                   comment '是否叶子节点',
    sort_num    string                   comment '排序号',
    status      int                      comment '类目级别',
    channel     string                   comment '支持渠道  1:WAP  2:Web 3:APP',
    sign        string                   comment '标记',
    create_time string                   comment '创建日期',
    update_time string                   comment '修改日期',
    create_user bigint                  ,
    update_user bigint                  ,
    yn          int                      comment '1:有效;0:删除',
    picture_url string                   comment '图片url',
    platform    string                   comment '所属平台',
    seller_code string                   comment '商家类目编码',
    activity    string                   comment '参加活动：1、支持7天无理由退货，2、支持增值税专用票 逗号分隔',
    rebate_flag int                      comment '是否设置商品交易扣点0:未设置 1:已设置  （只3级类目  一二级都为0）'
)
    comment '后台类目'
PARTITIONED BY (
  dt string
)
stored as orc
location '/user/hive/warehouse/ods.db/ods_item_category'
tblproperties ("orc.compression"="snappy");
ALTER TABLE ods_item_category ADD IF NOT EXISTS PARTITION (dt='20210325') location '/user/hive/warehouse/ods.db/ods_item_category/dt=20210325/';
