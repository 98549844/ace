#查看表的存储引擎
show create table ace.test_table;

#批量修改存储引擎
#InnoDB for transactional rollback function
SELECT CONCAT('ALTER TABLE ', table_name, ' ENGINE=InnoDB;')
FROM information_schema.tables
WHERE table_schema = 'ace'
  AND ENGINE = 'myisam';

#直接更改存储引擎innodb
alter table ace.test_table
    engine =innodb;

