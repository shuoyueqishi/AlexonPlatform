-- 系统用户表
DROP TABLE IF EXISTS `user_t`;
CREATE TABLE `user_t` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `number` varchar(10) NOT NULL COMMENT '账号',
  `password` varchar(60) NOT NULL COMMENT '密码',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(100) DEFAULT NULL COMMENT '性别',
  `email` varchar(100) DEFAULT NULL COMMENT '邮件',
  `telephone` varchar(15) DEFAULT NULL COMMENT '电话',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `creation_date` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `last_update_date` varchar(50) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_NUM_PASSWORD` (`number`,`password`),
  KEY `IDX_NUMBER` (`number`),
  KEY `IDX_PASSWORD` (`password`),
  KEY `IDX_NAME` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT '系统用户表';

-- 会员表
DROP TABLE IF EXISTS `member_t`;
CREATE TABLE `member_t` (
    `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
    `card_number` varchar(10) NOT NULL COMMENT '会员卡号码',
    `name` varchar(60) NOT NULL COMMENT '会员姓名',
    `birthday` varchar(50) DEFAULT NULL COMMENT '会员生日',
    `telephone` bigint(11) NOT NULL COMMENT '电话号码',
    `integral` int(11) DEFAULT 0 COMMENT '积分',
    `money` int(11) DEFAULT 0 COMMENT '开卡金额',
    `payment_type` int(4) DEFAULT NULL COMMENT '支付方式',
    `address` varchar(200) DEFAULT NULL COMMENT '会员地址',
    `delete_flag` SMALLINT DEFAULT 0 COMMENT '删除标志，0:未删除，1：删除',
    `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
    `creation_date` varchar(50) DEFAULT NULL COMMENT '创建时间',
    `last_update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
    `last_update_date` varchar(50) DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_cardNumber_name` (`card_number`,`name`),
    KEY `idx_cardNumber` (`card_number`),
    KEY `idx_name` (`name`),
    KEY `idx_telephone` (`telephone`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT '会员表';

-- 供应商表
DROP TABLE IF EXISTS `supplier_t`;
CREATE TABLE `supplier_t` (
    `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
    `code` varchar(10) NOT NULL COMMENT '供应商编码',
    `name` varchar(80) NOT NULL COMMENT '供应商名称',
    `contact` varchar(40) DEFAULT NULL COMMENT '联系人',
    `telephone` varchar(11) NOT NULL COMMENT '电话号码',
    `email` varchar(50) NOT NULL COMMENT '邮箱地址',
    `address` varchar(150) NOT NULL COMMENT '地址',
    `remark` varchar(200) DEFAULT 0 COMMENT '备注',
    `valid_flag` int(2) DEFAULT 0 COMMENT '有效标识，0：有效，1：无效',
    `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
    `creation_date` varchar(50) DEFAULT NULL COMMENT '创建时间',
    `last_update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
    `last_update_date` varchar(50) DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_code_name` (`code`,`name`),
    KEY `idx_code` (`code`),
    KEY `idx_name` (`name`),
    KEY `idx_contact` (`contact`),
    KEY `idx_telephone` (`telephone`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT '供应商表';