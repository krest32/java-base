--创建数据库
CREATE DATABASE Demodb

--删除数据库
DROP DATABASE Demodb

--创建表
CREATE TABLE Winter_User(id CHAR(10), name VARCHAR(50),gender VARCHAR(50),age INT)

--删除表
DELETE dbo.Winter_User				--删除表数据，不删除表结构，不释放空间
TRUNCATE TABLE dbo.Winter_User		--删除表数据，不删除表结构，释放空间
DROP TABLE dbo.Winter_User			--删除表所有，删除表结构，释放空间

--增加字段（列）
ALTER TABLE dbo.Winter_User ADD addr VARCHAR(50)

--删除字段（列）
ALTER TABLE dbo.Winter_User DROP COLUMN addr


--插入单条数据
INSERT dbo.Winter_User
        ( id, NAME, gender, age )
VALUES  ( '1',
          'zhaocl', -- NAME - varchar(50)
          'women', -- gender - varchar(50)
          18  -- addr - varchar(50)
          )
--批量插入数据
INSERT dbo.Winter_User
        ( id, NAME, gender, age )
VALUES  ( '5',
          'Fly', -- NAME - varchar(50)
          'women', -- gender - varchar(50)
          18  -- addr - varchar(50)
          ),
          ('6',
          'Nico', -- NAME - varchar(50)
          'men', -- gender - varchar(50)
          23  -- addr - varchar(50)
          )


--删除数据
DELETE FROM dbo.Winter_User WHERE NAME = 'zhaocl'  --（注意where条件，否则就删除所有数据了）

--更新数据
UPDATE dbo.Winter_User SET age = 1990 WHERE NAME = 'zhaocl' --（注意where条件，否则更新所有字段值）

--查询表所有数据
SELECT * FROM dbo.Winter_User

--查询某列数据
SELECT name FROM dbo.Winter_User

--返回唯一不同的值
SELECT DISTINCT name FROM dbo.Winter_User

--升序排列（默认ASC）
SELECT * FROM dbo.Winter_User ORDER BY id ASC

--降序排列
SELECT * FROM dbo.Winter_User ORDER BY id DESC

--获取第一条数据
SELECT TOP 1 name,age FROM dbo.Winter_User
--加入排序
SELECT TOP 1 id,name,age FROM dbo.Winter_User ORDER BY id DESC
--加入选择以及排序
SELECT TOP 1 id,name,age FROM dbo.Winter_User WHERE age<20 ORDER BY id DESC

--获取最后一条数据（按照名字排序倒序排序，获取第一个）
SELECT TOP 1 name FROM dbo.Winter_User ORDER BY name DESC

--获取某列最大值
SELECT MAX(age) AS maxAge FROM dbo.Winter_User
--加入限制体条件
SELECT MAX(age) AS maxAge FROM dbo.Winter_User WHERE gender='women'

--获取某列最小值
SELECT MIN(age) AS minAge FROM dbo.Winter_User
--加入限制条件
SELECT MIN(age) AS minAge FROM dbo.Winter_User WHERE age >20 and gender='women'

--某列求和(varchar类型的不可)
SELECT SUM(age) AS sumAge FROM dbo.Winter_User

--字母转大写
SELECT UPPER(age) FROM dbo.Winter_User

--字母转小写
UPDATE dbo.Winter_User SET Age = LOWER(age)

--字符串截取
SELECT SUBSTRING(name,1,3) FROM dbo.Winter_User
--可以配合使用
SELECT SUBSTRING(name,1,3) as name1, SUBSTRING(name,1,3) as name2  FROM dbo.Winter_User


--获取字符串长度
SELECT LEN(name) AS lenName FROM dbo.Winter_User
--查询所有数据，同时返回名字的长度
SELECT * , LEN(name) AS lenName FROM dbo.Winter_User


--四舍五入
SELECT ROUND(1.56,1)

--第三个参数非0时，表示舍弃位数，不再进行四舍五入
SELECT ROUND(2.56,1,1)

--获取当前时间
SELECT GETDATE()

--获取当前年月日
SELECT DATENAME(YEAR,GETDATE())
SELECT DATENAME(MONTH,GETDATE())
SELECT DATENAME(DAY,GETDATE())
SELECT YEAR(GETDATE())

--FORMAT格式化 日期格式化
SELECT FORMAT(GETDATE(),'yyyy-MM-dd')
--日期加时间格式化
SELECT FORMAT(GETDATE(),'yyyy-MM-dd hh:mm:ss')


--对结果集分组，并对组进行操作（SUM）
SELECT name,SUM(age) AS sumAge FROM dbo.Winter_User GROUP BY name

--where无法和聚合函数一起使用，所以使用HAVING跟条件
SELECT name, AVG(age) AS avgAge FROM dbo.Winter_User GROUP BY name HAVING name = 'zhaocl'


--LIKE
SELECT name FROM dbo.Winter_User WHERE name LIKE '%a%'	--包含a（SQL不区分大小写）
SELECT name FROM dbo.Winter_User WHERE name LIKE '%a'	--a结尾
SELECT name FROM dbo.Winter_User WHERE name LIKE 'a%'	--a开头
SELECT name FROM dbo.Winter_User WHERE name LIKE '_a_'	--三位并且中间是a
SELECT name FROM dbo.Winter_User WHERE name LIKE '_a'	--两位并且末尾是a
SELECT name FROM dbo.Winter_User WHERE name LIKE 'a_'	--两位并且a开头

--返回多个条件中符合的条目
SELECT * FROM dbo.Winter_User WHERE age IN ('17','18','100')

--返回范围内的数据
SELECT * FROM dbo.Winter_User WHERE age BETWEEN 1 AND 26 --[]两边都包括

--分页条件查询
select top pageSize *
from (select row_number()
over(order by sno asc) as rownumber,*
from student) temp_row
where rownumber>((pageIndex-1)*pageSize);

set statistics time on;
-- 分页查询第2页，每页有10条记录
select top 10 *
from (select row_number()
over(order by id asc) as rownumber,*
from dbo.Winter_User) temp_row
where rownumber>10;

-- 条件分页查询
set statistics time on;
-- 分页查询第2页，每页有10条记录
select top 3 *
from (select row_number()
over(order by id asc) as rownumber,*
from dbo.Winter_User) temp_row
where
name LIKE '%a%'
and age=23
and rownumber>3;

