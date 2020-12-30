
DROP TABLE IF EXISTS customer_info_t;
CREATE TABLE `customer_info_t` (
                                   `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
                                   `customer` varchar(32) NOT NULL COMMENT '客户名称',
                                   `telephone` varchar(32) NOT NULL COMMENT '电话',
                                   `address` varchar(256) NOT NULL COMMENT '地址',
                                   `_mycat_op_time` bigint DEFAULT NULL COMMENT '全局表保存修改时间戳的字段名',
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `uk_customer` (`customer`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='客户表';

DROP TABLE IF EXISTS order_head_t;
CREATE TABLE `order_head_t` (
                                `order_head_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
                                `order_date` varchar(8) NOT NULL COMMENT '下单时间',
                                `order_no` varchar(32) NOT NULL COMMENT '订单号',
                                PRIMARY KEY (`order_head_id`),
                                UNIQUE KEY `uk_order_no` (`order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='订单头表';

DROP TABLE IF EXISTS order_line_t;
CREATE TABLE `order_line_t` (
                                `order_line_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
                                `order_head_id` bigint NOT NULL COMMENT '订单头关联ID',
                                `order_date` varchar(8) NOT NULL COMMENT '下单时间',
                                `commodity` varchar(128) NOT NULL COMMENT '商品',
                                `customer` varchar(64) NOT NULL COMMENT '客户',
                                PRIMARY KEY (`order_line_id`),
                                KEY `idx_order_head` (`order_head_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单商品表';