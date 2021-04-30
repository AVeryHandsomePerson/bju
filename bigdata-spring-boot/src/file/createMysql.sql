create table t_datasource
(
    id          bigint auto_increment
        primary key,
    db_type     varchar(20) not null comment '数据库类型',
    host        varchar(20) not null comment '数据库IP',
    port        int         not null comment '数据库端口',
    db_name     varchar(50) not null comment '数据库实例名',
    db_user     varchar(20) not null comment '用户名',
    db_password varchar(50) null comment '密码',
    comment     varchar(50) null comment '备注',
    create_time datetime null,
    update_time datetime null
) comment '数据源(数据库实例)表';

create table t_graph_info
(
    id               int auto_increment comment 'id'
        primary key,
    des_sql          text null,
    graph_type       varchar(255) null comment '图形类型',
    condition_fields varchar(255) null comment '条件字段',
    description      varchar(255) null comment '业务描述',
    db_id            int null comment '结果库连接地址'
) comment '图形业务';

create table t_graph_template_info
(
    id         int auto_increment comment 'id'
        primary key,
    graph_info text null comment '模板图形信息',
    temp_name  text null comment '模板名称',
) comment '图形模板';


create table t_group_info
(
    id             int auto_increment comment 'id'
        primary key,
    group_name     text null comment '分组名称',
    group_describe text null comment '分组描述',
    order_id       int null comment '排序ID',
    create_time    DATETIME null comment '创建时间',
    operation_user varchar(50) null comment '创建用户',
) comment '分组信息';


create table t_menu_info
(
    id            int auto_increment comment 'id'
        primary key,
    platform      varchar(10) comment '平台',
    industry      varchar(10) comment '行业',
    shop_type     varchar(10) comment '店铺类型',
    shop_classify varchar(10) comment '店铺分类',
    `describe`    text comment '描述',
    one_menu      varchar(50) comment '一级菜单',
    two_menu      varchar(50) comment '二级菜单',
    three_menu    varchar(50) comment '三级菜单',
    order_id      int null comment '排序ID',
    picture_path  varchar(255) comment '图片路径',
    menu_id       varchar(255) comment '绑定菜单',
    two_order_id  int comment '二级排序ID'
) comment '行业菜单';


create table s_one_menu_info
(
    id           int auto_increment comment 'id'
        primary key,
    menu_name    varchar(50) comment '一级菜单名称',
    picture_path text comment '图片路径',
    url          text comment '链接',
    order_id     int null comment '排序ID',
    status       int null comment '1.显示 2.隐藏',
    logo         text comment '权限标识',
    `describe`   text comment '备注'
) comment '店铺菜单';


create table s_two_menu_info
(
    id           int auto_increment comment 'id'
        primary key,
    menu_name    varchar(50) comment '一级菜单名称',
    picture_path text comment '图片路径',
    url          text comment '链接',
    order_id     int null comment '排序ID',
    status       int null comment '1.显示 2.隐藏',
    logo         text comment '权限标识',
    `describe`   text comment '备注',
    one_menu_id  int comment '一级菜单ID'
) comment '店铺菜单';