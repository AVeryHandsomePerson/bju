create
external table ods.ods_inbound_bill
(
   `id` bigint,
  `sign_bill_id` bigint comment '签收单ID',
  `type` int comment '入库单类型 1:B采购入库 2：B销售退货入库 3:手工创建 4:分切成品入库 5:分切单卷筒芯入库 6:调拨入库 7:虚拟销售入库 8：虚拟采购入库 9:虚拟销售退货出库 10：虚拟采购退货出库',
  `order_id` bigint comment '采购单号',
  `seller_id` bigint comment '店铺卖家ID',
  `shop_id` bigint comment '店铺ID',
  `shop_name` varchar(255) comment '店铺名称',
  `status` int comment '状态 1:待签收 2：待审核  3：审核拒绝 4：已签收 5：已关闭',
  `order_amount` decimal(10,2) comment '订单金额',
  `inbound_amount` decimal(10,2) comment '入库金额',
  `purchase_date` string comment '采购日期',
  `create_time` string comment '创建时间',
  `audit_time` string comment '审核时间',
  `audit_user_id` bigint comment '审核人用户ID',
  `audit_username` varchar(255) comment '审核人用户名',
  `audit_remark` varchar(255) comment '审核备注',
  `modify_time` string comment '修改时间',
  `modify_user` bigint comment '修改人ID',
  `warehouse_code` varchar(255) comment '入库仓库',
  `warehouse_name` varchar(255) comment '入库仓库名称',
  `supplier_shop_id` bigint comment '供应商店铺ID',
  `supplier_shop_name` varchar(255) comment '供应商店铺名称',
  `manage_user_id` bigint comment '业务员ID',
  `manage_username` varchar(255) comment '业务员名称',
  `org_code` varchar(255) comment '归属组织机构编码',
  `org_parent_code` varchar(255) comment '归属上级组织机构编码',
  `inbound_remark` varchar(512) comment '入库备注',
  `operate_user_id` bigint comment '操作人用户id',
  `operate_user_name` varchar(125) comment '操作人名称',
  `operate_time` string comment '操作时间',
  `cut_order_id` char(32) comment '分切单编码',
  `customer_shop_id` bigint comment '入库客户id',
  `customer_shop_name` varchar(64) comment '入库客户名称',
  `is_refuse_receive` int comment '是否是拒收入库 1：否 2：是'
)
comment '入库单'
PARTITIONED BY (
  dt string
)
stored as parquet
location '/user/hive/warehouse/ods.db/ods_inbound_bill'
tblproperties ("orc.compression"="snappy");



create
external table ods.ods_inbound_bill_detail
(
  `id` bigint,
  `inbound_bill_id` bigint comment '入库单号',
  `order_id` bigint comment '采购单号',
  `order_detail_id` bigint comment '采购单商品行ID',
  `item_id` bigint comment '商品ID',
  `sku_id` bigint,
  `item_name` varchar(255) comment '商品名称',
  `item_type` int comment '商品类型 1卷筒 2纸张 3卷筒芯 4不可选卷筒芯',
  `sku_pic_url` varchar(255) comment '商品图片',
  `sku_sale_attr` varchar(255) comment '销售属性文字',
  `shop_sku_code` varchar(255) comment '商家SKU编码',
  `order_num` decimal(16,4) comment '订单商品数量',
  `price` decimal(10,2) comment '商品入库价格',
  `inbound_num` decimal(16,4) comment '入库数量',
  `real_inbound_num` decimal(16,4) comment '实际入库数量',
  `inbound_reel` int comment '入库卷数',
  `real_inbound_reel` int comment '实际入库卷量',
  `create_time` string comment '创建时间',
  `brand_id` bigint comment '品牌id',
  `cid` bigint comment '商品的类目ID'
)
comment '入库单明细'
PARTITIONED BY (
  dt string
)
stored as parquet
location '/user/hive/warehouse/ods.db/ods_inbound_bill_detail'
tblproperties ("orc.compression"="snappy");

create
external table ods.ods_outbound_bill
(
  `id` bigint,
  `type` int  comment '出库单类型 1:销售出库 2：采购退货出库 3:手工创建 4:分切单原料出库 5:分切成品出库 6:调拨出库 7:虚拟销售出库 8：虚拟采购出库  9:虚拟销售退货出库 10：虚拟采购退货出库 11: 卷筒出库',
  `order_id` bigint  comment '订单号',
  `shop_id` bigint  comment '店铺ID',
  `shop_name` varchar(255)  comment '店铺名称',
  `buyer_shop_id` bigint  comment '买方店铺ID',
  `buyer_shop_name` varchar(255)  comment '买方店铺名称',
  `status` int  comment '状态 0:待拣货 1：待出库 2：待审核 3：审核拒绝 4：待发运 5：已完成 6：已关闭 7:拒收待审核 8：拒收审核通过 9 拒收已入库 10 差异签收待审核 11差异签收审核通过 12差异签收审核驳回',
  `sign_status` int  comment '签收状态 0：未签收 1：已签收 2：已拒收',
  `sign_time` string  comment '签收日期',
  `sign_shop_id` bigint  comment '签收店铺ID',
  `sign_user_id` bigint  comment '签收用户ID',
  `real_sign_user` varchar(255)  comment '实际签收人',
  `sign_remark` varchar(255)  comment '签收意见',
  `order_amount` decimal(10,2)  comment '订单金额',
  `create_time` string  comment '创建时间',
  `audit_time` string  comment '审核时间',
  `audit_user_id` bigint  comment '审核人ID',
  `audit_user_name` varchar(255)  comment '审核人名称',
  `audit_remark` varchar(255)  comment '审核备注',
  `outbound_time` string  comment '出库日期',
  `modify_time` string  comment '修改时间',
  `modify_user` bigint  comment '修改人',
  `warehouse_code` varchar(255)  comment '仓库编码',
  `warehouse_name` varchar(255)  comment '仓库名称',
  `manage_user_id` bigint  comment '业务员ID',
  `manage_username` varchar(255)  comment '业务员名称',
  `org_code` varchar(255)  comment '归属组织机构编码',
  `org_parent_code` varchar(255)  comment '归属上级组织机构编码',
  `outbound_remark` varchar(512)  comment '出库备注',
  `operate_user_id` bigint  comment '操作人用户id',
  `operate_user_name` varchar(125)  comment '操作人名称',
  `operate_time` string  comment '操作时间',
  `license_plate_num` varchar(100)  comment '车牌号',
  `driver_id` bigint  comment '司机ID',
  `driver_name` varchar(255)  comment '司机名称',
  `logistics_code` varchar(50)  comment '物流商ID,对应字典表code',
  `logistics_no` varchar(255)  comment '物流单号，多个单号就用逗号隔开',
  `freight_label_num` int  comment '货签数',
  `cut_order_id` char(32)  comment '分切单编码',
  `consignee_name` varchar(20)  comment '收货人姓名',
  `consignee_mobile` varchar(20)  comment '收货人手机',
  `consignee_mail` varchar(32)  comment '收货人邮箱',
  `province_code` bigint  comment '省ID',
  `city_code` bigint  comment '市ID',
  `country_code` bigint  comment '县区ID',
  `town_code` bigint ,
  `province_name` varchar(200)  comment '省名称',
  `city_name` varchar(200)  comment '市名称',
  `country_name` varchar(200)  comment '区名称',
  `town_name` varchar(255) ,
  `detail_address` varchar(200)  comment '详细地址',
  `expect_receive_time` string  comment '期望收货时间',
  `reclaim_status` int  comment '回收状态：1，未回收；2，已回收'
)
comment '出库表'
PARTITIONED BY (
  dt string
)
stored as parquet
location '/user/hive/warehouse/ods.db/ods_outbound_bill'
tblproperties ("orc.compression"="snappy");


create
external table ods.ods_outbound_bill_detail
(
  `id` bigint,
  `outbound_bill_id` bigint comment '出库单号',
  `order_id` bigint comment '订单号',
  `order_detail_id` bigint comment '订单明细ID',
  `cid` bigint comment '商品的类目ID',
  `brand_id` bigint comment '品牌ID',
  `item_id` bigint comment '商品ID',
  `sku_id` bigint comment 'skuID',
  `item_name` varchar(255) comment '商品名称',
  `sku_pic_url` varchar(255) comment '图片地址',
  `sku_sale_attr` varchar(255) comment '销售属性文字',
  `shop_sku_code` varchar(255) comment '商家SKU编码',
  `order_num` decimal(16,4) comment '订单商品数量',
  `price` decimal(10,2) comment '价格',
  `outbound_num` decimal(16,4) comment '计划出库数量',
  `real_outbound_num` decimal(16,4) comment '实际出库数量',
  `sign_num` decimal(16,4) comment '签收数量',
  `outbound_reel` int comment '计划出库卷',
  `real_outbound_reel` int comment '实际出库卷',
  `sign_reel` int comment '签收卷数',
  `piece_weight` decimal(16,4) comment '单件重量',
  `total_weight` decimal(16,4) comment '总重量',
  `create_time` string   comment '创建时间'
)
comment '出库单明细'
PARTITIONED BY (
  dt string
)
stored as parquet
location '/user/hive/warehouse/ods.db/ods_outbound_bill_detail'
tblproperties ("orc.compression"="snappy");

create
external table ods.ods_item_master
(
  `id` bigint,
  `shop_id` bigint comment '店铺ID',
  `sku_code` varchar(255) comment '商家SKU编码',
  `origin_id` bigint comment '起始商家主数据ID',
  `origin_sku_code` varchar(255) comment '起始商家SKU编码',
  `item_name` varchar(255) comment '商品名称',
  `brand_id` bigint comment '品牌ID',
  `cid` bigint comment '类目ID',
  `sale_attr` varchar(255) comment '销售属性',
  `pic_url` varchar(255) comment '图片',
  `status` int comment '状态 1:启用 2：停用 3:删除',
  `item_type` int comment '商品类型 0普通商品 1卷筒 2平张 3卷筒芯',
  `qualification_effect_date` string comment '资质有效期',
  `need_update_qualification` boolean comment '是否需要更新商品资质 1不需要 2需要',
  `create_time` string comment '创建时间',
  `create_user` bigint comment '创建人',
  `modify_time` string comment '修改时间',
  `modify_user` bigint comment '修改人'
)
comment '出库单明细'
PARTITIONED BY (
  dt string
)
stored as parquet
location '/user/hive/warehouse/ods.db/ods_item_master'
tblproperties ("orc.compression"="snappy");


create
external table ods.ods_warehouse_item
(
  `id` bigint,
  `shop_id` bigint  comment '所属店铺ID',
  `shop_name` varchar(255)  comment '所属店铺名称',
  `warehouse_code` varchar(255)  comment '仓库编码',
  `warehouse_name` varchar(255)  comment '仓库名称',
  `shop_sku_code` varchar(255)  comment '商家SKU编码',
  `origin_sku_code` varchar(255)  comment '原始SKU编码',
  `item_name` varchar(255)  comment '商品名称',
  `item_pic_url` varchar(255)  comment '商品图片',
  `inventory` decimal(16,4)  comment '真实库存',
  `occupied_inventory` decimal(16,4)  comment '占用库存量',
  `inventory_min` decimal(16,4)  comment '库存下限',
  `inventory_max` decimal(16,4)  comment '库存上限',
  `check_inventory` decimal(16,4)  comment '盘点数量',
  `reel` int  comment '卷',
  `status` int  comment '状态 1：正常 2：盘点中 3：盘点审核驳回',
  `reason` varchar(255)  comment '审核备注',
  `audit_username` varchar(255)  comment '审核人名称',
  `audit_user_id` bigint  comment '审核人用户ID',
  `audit_time` string  comment '审核时间',
  `item_id` bigint ,
  `sku_id` bigint ,
  `supplier_shop_id` bigint  comment '供应商店铺ID',
  `supplier_shop_name` varchar(255)  comment '供应商店铺名称',
  `customer_shop_id` bigint  comment '库存归属客户ID',
  `customer_shop_name` varchar(255)  comment '库存归属客户名称',
  `item_type` int  comment '商品类型 0普通类型 1卷筒 2平张 3卷筒芯',
  `memo` varchar(255)  comment '库存备注',
  `create_time` string ,
  `create_user` bigint ,
  `modify_time` string ,
  `modify_user` bigint
)
comment '出库单明细'
PARTITIONED BY (
  dt string
)
stored as parquet
location '/user/hive/warehouse/ods.db/ods_warehouse_item'
tblproperties ("orc.compression"="snappy");

create
external table ods.ods_item_brand
(
  `brand_id` bigint,
  `brand_name` varchar(40),
  `brand_name_cn` varchar(255),
  `brand_name_en` varchar(100),
  `brand_key` varchar(10),
  `brand_logo_url` varchar(256),
  `registrant` varchar(200),
  `registered_number` varchar(255),
  `tm_type` varchar(20),
  `status` int,
  `create_time` string,
  `update_time` string,
  `create_user` bigint,
  `update_user` bigint,
  `yn` int
)
comment '品牌信息'
PARTITIONED BY (
  dt string
)
stored as parquet
location '/user/hive/warehouse/ods.db/ods_item_brand'
tblproperties ("orc.compression"="snappy");