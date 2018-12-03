CREATE DATABASE `TCC_ORD` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE TCC_ORD;
CREATE TABLE `ORD_ORDER` (
  `ORDER_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `PAYER_USER_ID` int(11) DEFAULT NULL COMMENT '付款人',
  `PAYEE_USER_ID` int(11) DEFAULT NULL COMMENT '收款人',
  `RED_PACKET_PAY_AMOUNT` decimal(10,0) DEFAULT NULL COMMENT '红包付款金额',
  `CAPITAL_PAY_AMOUNT` decimal(10,0) DEFAULT NULL COMMENT '资金账户付款金额',
  `STATUS` varchar(45) DEFAULT NULL COMMENT '状态',
  `MERCHANT_ORDER_NO` varchar(45) NOT NULL COMMENT '商户订单号',
  `VERSION` int(11) DEFAULT NULL COMMENT '数据版本号',
  PRIMARY KEY (`ORDER_ID`),
  UNIQUE KEY `MERCHANT_ORDER_NO_UNIQUE` (`MERCHANT_ORDER_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=1188 DEFAULT CHARSET=utf8 COMMENT='订单表';


CREATE TABLE `ORD_SHOP` (
  `SHOP_ID` int(11) NOT NULL COMMENT '商铺编号',
  `OWNER_USER_ID` int(11) DEFAULT NULL COMMENT '商铺持有人',
  PRIMARY KEY (`SHOP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='商铺表';

CREATE TABLE `ORD_PRODUCT`(
  `PRODUCT_ID` int(11) NOT NULL '商品编号',
  `SHOP_ID` int(11) NOT NULL COMMENT '商铺编号',
  `PRODUCT_NAME` VARCHAR(64) DEFAULT NULL   COMMENT '商品名称',
  `PRICE` DECIMAL(10,0) DEFAULT NULL   COMMENT '商品价格,单位(元)',
  PRIMARY KEY (`PRODUCT_ID`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='商品表';


INSERT INTO `ORD_SHOP` (`SHOP_ID`,`OWNER_USER_ID`) VALUES (1,1000);

INSERT INTO `ORD_PRODUCT` (`PRODUCT_ID`,`SHOP_ID`,`PRODUCT_NAME`,`PRICE`) VALUES (1,1,'IPhone6S',5288);
INSERT INTO `ORD_PRODUCT` (`PRODUCT_ID`,`SHOP_ID`,`PRODUCT_NAME`,`PRICE`) VALUES (2,1,'MAC Pro',10288);
INSERT INTO `ORD_PRODUCT` (`PRODUCT_ID`,`SHOP_ID`,`PRODUCT_NAME`,`PRICE`) VALUES (3,1,'IWatch',2288);