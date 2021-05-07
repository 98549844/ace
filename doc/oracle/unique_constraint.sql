--唯一性约束
--唯一性约束指: 一个col或多个col组成一个拜佛和一标识约束
--联合字段中.可以包含空值.
--在Oracle中.唯一性约束最多可以有32列
--唯一性约束可以在创建表时或使用ALTER TABLE语句创建

--unique constraint和pk的分别
--pk不可以null, unique constraint可以为null
--pk和unique constrain不能在同一个col里

--创建table
--基于单列的唯一性约束示例

--preparing table
------------------------------------------------------------------------------------
DROP TABLE tb_supplier;
DROP TABLE tb_products;

CREATE TABLE tb_supplier
(
    supplier_id   NUMBER NOT NULL,
    supplier_name VARCHAR2(50),
    contact_name  VARCHAR2(50)
);

CREATE TABLE tb_products
(
    product_id   NUMBER NOT NULL,
    product_name NUMBER NOT NULL,
    product_type VARCHAR2(50),
    supplier_id  NUMBER
);
------------------------------------------------------------------------------------


CREATE TABLE tb_supplier
(
    supplier_id   NUMBER NOT NULL,
    supplier_uid  NUMBER NOT NULL,
    supplier_name VARCHAR2(50),
    contact_name  VARCHAR2(50),
    CONSTRAINT pk PRIMARY KEY (supplier_id),
    CONSTRAINT tb_supplier_u1 UNIQUE (supplier_uid)--创建表时创建唯一性约束
);


--基于多列的唯一性约束示例
CREATE TABLE tb_products
(
    product_id   NUMBER NOT NULL,
    supplier_uid NUMBER NOT NULL,
    product_name NUMBER NOT NULL,
    product_type VARCHAR2(50),
    supplier_id  NUMBER,
    CONSTRAINT pk PRIMARY KEY (supplier_id),
    CONSTRAINT tb_products_u1 UNIQUE (supplier_uid, product_name) --定义复合唯一性约束
);

--使用ALTER TABLE语法
--基于单列的唯一性约束
ALTER TABLE tb_products
    ADD CONSTRAINT constraint_name1 UNIQUE (column1);
    ADD CONSTRAINT constraint_name2 UNIQUE (column2);
    ADD CONSTRAINT constraint_name3 UNIQUE (column3);

--基于多列的唯一性约束
ALTER TABLE tb_products
    ADD CONSTRAINT tb_products_u1 UNIQUE (product_id, product_name);


--禁用,启用,删除 唯一性约束
ALTER TABLE tb_supplier
    DISABLE CONSTRAINT constraint_name;


ALTER TABLE tb_supplier
    ENABLE CONSTRAINT constraint_name;

ALTER TABLE tb_supplier
    DROP CONSTRAINT constraint_name;


