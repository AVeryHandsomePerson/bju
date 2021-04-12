create external table ods.ods_refund_detail

create external table ods.ods_user
(
    `id` bigint                              ,
      `platform` varchar(20)          comment '所属平台',
      `tenant_id` bigint          comment '租户id',
      `seller_id` bigint          comment '卖家ID',
      `parent_id` bigint          comment '父账号id(如果父账号id为1，则该账号为父账号，默认为1)',
      `name` varchar(128)        comment '姓名',
      `mobile` varchar(200)          comment '联系手机号',
      `email` varchar(200)          comment '联系邮箱',
      `nickname` varchar(255)          comment '昵称',
      `sex` int          comment '性别 1男 2女',
      `birthday` string          comment '生日',
      `hobbies` varchar(100)          comment '兴趣爱好',
      `icon` varchar(200)          comment '头像',
      `type` int        comment '用户类型（1-普通用户，2-买家，3-卖家, 4-平台）',
      `flag` int          comment '1:个人 2：企业 3超级管理员 4:店员',
      `pay_password` varchar(200)          comment '买家支付密码',
      `status` int          comment '1-普通用户待验证，2-普通用户验证通过，3-买家待审核，4-买家审核通过，5-卖家待审核，6-卖家审核通过9:删除',
      `create_time` string          comment '创建时间',
      `modify_time` string          comment '更新时间',
      `create_user` bigint          comment '创建人id',
      `modify_user` bigint          comment '修改人id',
      `failed_login_count` int          comment '失败登录次数',
      `yn` int          comment '0:无效,1:有效',
      `login_time` string          comment '上次登录时间',
      `login_num` int          comment '登录次数',
      `pay_password_safe` int          comment '支付密码强度：1低、2中、3高',
      `seller_pay_password` varchar(200)          comment '卖家支付密码',
      `logout_time` string          comment '注销时间',
      `job_number` varchar(255)          comment '员工编号',
      `remark` varchar(255)          comment '备注',
      `is_sales_man` int          comment '是否为业务员 1:是   0:不是',
      `is_buyer_man` int          comment '是否是采购员 1：是 0：否',
      `is_cut_man` int          comment '是否是分切员 1：是 0：否'
)
comment '用户表'
PARTITIONED BY (
  dt string
)
stored as parquet
location '/user/hive/warehouse/ods.db/ods_user'
tblproperties ("orc.compression"="snappy");



stored as PARQUET
location '/user/hive/warehouse/ods.db/ods_user'
tblproperties ("parquet.compression"="snappy");

ALTER TABLE ods.ods_user ADD IF NOT EXISTS PARTITION (dt='20210325') location '/user/hive/warehouse/ods.db/ods_user/dt=20210325/';


