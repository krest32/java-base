CREATE TABLE IF NOT EXISTS `runoob_tbl`(
   `runoob_id` INT UNSIGNED AUTO_INCREMENT,
   `runoob_title` VARCHAR(100) NOT NULL,
   `runoob_author` VARCHAR(40) NOT NULL,
   `submission_date` DATE,
   PRIMARY KEY ( `runoob_id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

select * from runoob_tbl;

SELECT * from runoob_tbl WHERE runoob_author='菜鸟教程';

UPDATE runoob_tbl SET runoob_title='学习 C++' WHERE runoob_id=3;

DELETE FROM runoob_tbl WHERE runoob_id=3;

SELECT * from runoob_tbl  WHERE runoob_author LIKE '%COM';

SELECT * FROM Websites;
SELECT * FROM apps;

SELECT country FROM Websites
UNION
SELECT country FROM apps
ORDER BY country;


SELECT country FROM Websites
UNION ALL
SELECT country FROM apps
ORDER BY country;


SELECT country, name FROM Websites
WHERE country='CN'
UNION ALL
SELECT country, app_name FROM apps
WHERE country='CN'
ORDER BY country;

SELECT * from runoob_tbl ORDER BY submission_date ASC;


SELECT * FROM employee_tbl;

SELECT name, COUNT(*) FROM   employee_tbl GROUP BY name;

/**
WITH ROLLUP 可以实现在分组统计数据基础上再进行相同的统计（SUM,AVG,COUNT…）。
例如我们将以上的数据表按名字进行分组，再统计每个人登录的次数：
 */
SELECT name, SUM(signin) as signin_count FROM employee_tbl GROUP BY name WITH ROLLUP;


SELECT a.runoob_id, a.runoob_author, b.runoob_count
FROM runoob_tbl a INNER JOIN tcount_tbl b
ON a.runoob_author = b.runoob_author;

/*
  等同于上一句
 */
SELECT a.runoob_id, a.runoob_author, b.runoob_count
FROM runoob_tbl a, tcount_tbl b
WHERE a.runoob_author = b.runoob_author;

SELECT a.runoob_id, a.runoob_author, b.runoob_count
FROM runoob_tbl a LEFT JOIN tcount_tbl b
ON a.runoob_author = b.runoob_author;


SELECT a.runoob_id, a.runoob_author, b.runoob_count
FROM runoob_tbl a RIGHT JOIN tcount_tbl b
ON a.runoob_author = b.runoob_author;


create table runoob_test_tbl(
    runoob_author varchar(40) NOT NULL,
     runoob_count  INT
);
INSERT INTO runoob_test_tbl (runoob_author, runoob_count) values ('RUNOOB', 20);
INSERT INTO runoob_test_tbl (runoob_author, runoob_count) values ('菜鸟教程', NULL);
INSERT INTO runoob_test_tbl (runoob_author, runoob_count) values ('Google', NULL);
INSERT INTO runoob_test_tbl (runoob_author, runoob_count) values ('FK', 20);


SELECT * from runoob_test_tbl;

/**
  两句话不起作用
 */
SELECT * FROM runoob_test_tbl WHERE runoob_count = NULL;
SELECT * FROM runoob_test_tbl WHERE runoob_count != NULL;

/**
  上面修改为
 */
SELECT * FROM runoob_test_tbl WHERE runoob_count IS NULL;
SELECT * from runoob_test_tbl WHERE runoob_count IS NOT NULL;

CREATE TABLE runoob_transaction_test( id int(5)) engine=innodb;

select * from runoob_transaction_test;

/**
  事务提交
 */
begin;
insert into runoob_transaction_test value(5);
insert into runoob_transaction_test value(6);
commit;


select * from runoob_transaction_test;

/**
  事务回滚
 */
begin;
insert into runoob_transaction_test values(7);
rollback;

select * from runoob_transaction_test;


/**
  使用了 ALTER 命令及 DROP 子句来删除以上创建表的 i 字段：
 */
ALTER TABLE testalter_tbl  DROP i;

/**
  添加字段，並設定類型
 */
ALTER TABLE testalter_tbl ADD i INT;

/**
  如果你需要指定新增字段的位置，可以使用MySQL提供的关键字 FIRST (设定位第一列)，
  AFTER 字段名（设定位于某个字段之后）。
    尝试以下 ALTER TABLE 语句, 在执行成功后，使用 SHOW COLUMNS 查看表结构的变化：
 */
ALTER TABLE testalter_tbl DROP i;
ALTER TABLE testalter_tbl ADD i INT FIRST;
ALTER TABLE testalter_tbl DROP i;
ALTER TABLE testalter_tbl ADD i INT AFTER c;


/**
  把字段 c 的类型从 CHAR(1) 改为 CHAR(10)，可以执行以下命令:
 */
ALTER TABLE testalter_tbl MODIFY c CHAR(10);

/**
  使用 CHANGE 子句, 语法有很大的不同。 在 CHANGE 关键字之后，紧跟着的是你要修改的字段名，然后指定新字段名及类型
 */
ALTER TABLE testalter_tbl CHANGE i j BIGINT;
ALTER TABLE testalter_tbl CHANGE j j INT;


/**
  查看数据表类型可以使用
 */
SHOW TABLE STATUS LIKE 'testalter_tbl';

/**
  将数据表 testalter_tbl 重命名为 alter_tbl：
 */
ALTER TABLE testalter_tbl RENAME TO alter_tbl;



CREATE TABLE mytable(
ID INT NOT NULL,
username VARCHAR(16) NOT NULL
)
/**
  修改表结构(添加索引)
 */
CREATE UNIQUE INDEX indexName ON mytable(username(10));

ALTER table mytable ADD UNIQUE myname (username(10));


ALTER TABLE testalter_tbl ADD INDEX (c);

ALTER TABLE testalter_tbl DROP INDEX c;

ALTER TABLE testalter_tbl MODIFY i INT NOT NULL;

CREATE TABLE person_tbl
(
    first_name CHAR(20) NOT NULL,
    last_name CHAR(20) NOT NULL,
    sex CHAR(10),
    PRIMARY KEY (last_name, first_name)
);
/**
  你想删除数据表中的重复数据，你可以使用以下的SQL语句：
 */
CREATE TABLE tmp SELECT last_name, first_name, sex FROM person_tbl  GROUP BY (last_name, first_name, sex);
DROP TABLE person_tbl;
ALTER TABLE tmp RENAME TO person_tbl;


/**
  統計重複的數據
 */
SELECT COUNT(*) as repetitions, last_name, first_name
    FROM person_tbl
    GROUP BY last_name, first_name
    HAVING repetitions > 1;