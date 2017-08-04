DROP TABLE IF EXISTS lc_product;
CREATE TABLE lc_product (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  product_code char(50) NOT NULL COMMENT '产品编码',
  product_name char(255) NOT NULL  COMMENT '产品名称',
  product_type int(2) NOT NULL  COMMENT '产品类型 1-普通定期',
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品表';



