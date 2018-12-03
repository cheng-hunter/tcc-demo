CREATE DATABASE `TCC_RED` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE TCC_RED;
CREATE TABLE `RED_RED_PACKET_ACCOUNT` (
  `RED_PACKET_ACCOUNT_ID` int(11) NOT NULL COMMENT '红包账户',
  `BALANCE_AMOUNT` decimal(10,0) DEFAULT NULL COMMENT '红包金额',
  `USER_ID` int(11) DEFAULT NULL COMMENT '红包账户对应的用户ID',
  PRIMARY KEY (`RED_PACKET_ACCOUNT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='红包账户表';

CREATE TABLE `RED_TRADE_ORDER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SELF_USER_ID` bigint(11) DEFAULT NULL COMMENT '出款人',
  `OPPOSITE_USER_ID` bigint(11) DEFAULT NULL COMMENT '对方账户编号',
  `MERCHANT_ORDER_NO` varchar(45) NOT NULL COMMENT '商户订单号',
  `AMOUNT` decimal(10,0) DEFAULT NULL COMMENT '交易金额',
  `STATUS` varchar(45) DEFAULT NULL COMMENT '交易状态',,
  `VERSION` int(11) DEFAULT NULL COMMENT '数据版本',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `MERCHANT_ORDER_NO_UNIQUE` (`MERCHANT_ORDER_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='红包订单表';

INSERT INTO `RED_RED_PACKET_ACCOUNT` (`RED_PACKET_ACCOUNT_ID`,`BALANCE_AMOUNT`,`USER_ID`) VALUES (1,950,1000);
INSERT INTO `RED_RED_PACKET_ACCOUNT` (`RED_PACKET_ACCOUNT_ID`,`BALANCE_AMOUNT`,`USER_ID`) VALUES (2,500,2000);
