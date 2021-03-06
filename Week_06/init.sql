CREATE TABLE IF NOT EXISTS `t_user`(
   `id` INT not null AUTO_INCREMENT comment 'id',
   `name` VARCHAR(20) NOT NULL comment '用户名',
   `nickname` VARCHAR(20) NOT NULL comment '昵称',
   `password` VARCHAR(20) NOT NULL comment '密码',
   `identify_card` VARCHAR(20) NOT NULL comment '身份证',
   `create_at` timestamp NOT NULL default current_timestamp comment '创建时间',
   `update_at` timestamp NOT NULL default current_timestamp on update current_timestamp comment '更新时间',
   `is_deleted` boolean NOT NULL comment '是否删除',
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `t_order`(
   `id` INT not null AUTO_INCREMENT comment 'id',
   `user_id` int NOT NULL comment '用户id',
   `price` VARCHAR(20) NOT NULL comment '价格',
   `status` VARCHAR(20) NOT NULL comment '状态',
   `create_at` timestamp NOT NULL default current_timestamp comment '创建时间',
   `update_at` timestamp NOT NULL default current_timestamp on update current_timestamp comment '更新时间',
   `is_deleted` boolean NOT NULL comment '是否删除',
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `t_commodity`(
   `id` INT not null AUTO_INCREMENT comment 'id',
   `code` VARCHAR(50) NOT NULL comment '编码',
   `name` VARCHAR(20) NOT NULL comment '名称',
   `type` VARCHAR(20) NOT NULL comment '类型',
   `weight` int NOT NULL comment '重量（kg）',
   `create_at` timestamp NOT NULL default current_timestamp comment '创建时间',
   `update_at` timestamp NOT NULL default current_timestamp on update current_timestamp comment '更新时间',
   `is_deleted` boolean NOT NULL comment '是否删除',
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE IF NOT EXISTS `m_order_commodity`(
   `id` INT not null AUTO_INCREMENT comment 'id',
   `order_id` int NOT NULL comment '订单id',
   `commodity_id` int NOT NULL comment '商品id',
   `create_at` timestamp NOT NULL default current_timestamp comment '创建时间',
   `update_at` timestamp NOT NULL default current_timestamp on update current_timestamp comment '更新时间',
   `is_deleted` boolean NOT NULL comment '是否删除',
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;