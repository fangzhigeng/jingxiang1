DROP TABLE IF EXISTS product;

CREATE TABLE IF NOT EXISTS product(id bigint(20) unsigned not null primary key,name varchar(32),price int,tenant_id varchar(32),created_by varchar(32),updated_by varchar(32),created_time date,updated_time date, is_deleted int(4),version int(11));

INSERT INTO product(id, name, price, tenant_id, created_by, updated_by, created_time, updated_time, is_deleted, version) VALUES (1, '苹果', 20, null, null, null, null, null, 0, 1),
(2, '梨', 10, null, null ,null, null, null, 0, 1),
(3, '桃子', 123, null, null ,null, null, null, 0, 1),
(4, '香蕉', 11, null, null ,null, null, null, 0, 1),
(5, '猕猴桃', 50, null, null ,null, null, null, 0, 1),
(6, '桔子', 33, null, null ,null, null, null, 0, 1),
(7, '菠萝', 90, null, null ,null, null, null, 0, 1),
(8, '芒果', 766, null, null ,null, null, null, 0, 1),
(9, '红枣', 29, null, null ,null, null, null, 0, 1),
(10, '榴莲', 990, null, null ,null, null, null, 0, 1),
(11, '柚子', 291, null, null ,null, null, null, 0, 1),
(12, '小番茄', 200, null, null ,null, null, null, 0, 1);