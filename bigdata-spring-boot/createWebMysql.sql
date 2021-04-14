create table t_graph_info
(
    id          integer PRIMARY KEY AUTO_INCREMENT comment 'id',
    sql         text,
    graph_type  varchar(255) comment '图形类型',
    condition   varchar(255) comment '条件字段',
    description varchar(255) comment '业务描述',
    dt          date not null
) comment '图形业务' charset = utf8;