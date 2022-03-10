## 一：索引类型：

​	1.全文索引（FULLTEXT）  （可以解决where name like "%word%"效率低问题）

​		只有MyISAM引擎支持,只能在char、varchar、text列上使用

​	2.哈希索引（HASH）（只在=和in条件下高效）

​	3.B树索引（BTREE）

​	4.R树索引（RTREE）(相比于btree,优势在于范围查找)

​		仅支持geometry数据类型

​    	支持引擎：MyISAM、BDb、InnoDb、NDb、Archive

### 二：索引种类：

​	普通索引：仅加速查询

​	唯一索引：加速查询+列值唯一（可以有null）

​	主键索引：加速查询+列值唯一（不可以有null）+表中只有一个

​	组合索引：多列值组成一个索引，专门用于组合搜索，其效率大于索引合并

​	全文索引：对文本的内容进行分词，进行搜索

​	





## 三.创建索引：



##### 		普通索引：

```
CREATE INDEX index_name ON table_name(col_name);
```

​		唯一索引：

```
CREATE UNIQUE INDEX index_name ON table_name(col_name);
```

​		创建普通组合索引：

```
CREATE INDEX index_name ON table_name(col_name_1,col_name_2);
```

​		创建唯一组合索引：

```
CREATE UNIQUE INDEX index_name ON table_name(col_name_1,col_name_2);
```

#### 	通过修改表结构创建索引：

```
ALTER TABLE table_name ADD INDEX index_name(col_name);
```

#### 	创建表时直接指定索引：

```
CREATE TABLE table_name (
    ID INT NOT NULL,col_name VARCHAR (16) NOT NULL,INDEX index_name (col_name)
);
```

#### 	删除索引：

```
--直接删除索引
DROP INDEX index_name ON table_name;
--修改表结构删除索引
ALTER TABLE table_name DROP INDEX index_name;
```

## 四：三种索引

####   普通索引：

```
   create index on Tablename(列的列表) 

　　alter table TableName add index (列的列表) 

　　create table TableName([...], index [IndexName] (列的列表) 
```

####  唯一索引：

```
	create unique index on Tablename(列的列表) 

　　ALTER table mytable ADD UNIQUE [indexName](列的列表) 
　　
　　create table TableName([...], unique [indexName] (列的列表) 
```

#### 全文索引：

```
	create unique index on Tablename(列的列表) 
	ALTER TABLE tbl_name ADD FULLTEXT index_name (列的列表)
	　create table TableName([...], FULLTEXT(indexName) (列的列表) 
	　
	　
	　
	　
    CREATE TABLE article ( 
                  id INT AUTO_INCREMENT NOT NULL PRIMARY KEY, 
                  title VARCHAR(200), 
                  content TEXT, 
                  FULLTEXT(title, content) 
              ) TYPE=MYISAM;
              
    ALTER TABLE table_name ADD FULLTEXT INDEX index_name (column1,column2,...);
    
    CREATE FULLTEXT INDEX index_name ON table_name (column1,column2,...)
```

## 五：删除索引：

```
 DROP INDEX index_name ON table_name ;
 ALTER TABLE table_name DROP INDEX index_name;
```

## 六：查看索引：

```
show index from tblname;
```



# 慢查询记录：

```
slow_query_log : 是否启用慢查询日志，[1 | 0] 或者 [ON | OFF]

slow_query_log_file : MySQL数据库（5.6及以上版本）慢查询日志存储路径。
                    可以不设置该参数，系统则会默认给一个缺省的文件 HOST_NAME-slow.log

long_query_time : 慢查询的阈值，当查询时间超过设定的阈值时，记录该SQL语句到慢查询日志。

log_queries_not_using_indexes ：设置为 ON ，可以捕获到所有未使用索引的SQL语句(不建议启用)

log_output : 日志存储方式。
            log_output='FILE'，表示将日志存入文件，默认值是'FILE'。      
            log_output='TABLE'，表示将日志存入数据库，这样日志信息就会被写入到 mysql.slow_log 表中。
            MySQL数据库支持同时两种日志存储方式，配置的时候以逗号隔开即可，如：log_output='FILE,TABLE'。
            日志记录到系统的专用日志表中，要比记录到文件耗费更多的系统资源，因此对于需要启用慢查询日志，又需要能够获得更高的系统性能，那么建议优先记录到文件。
            
          
###暂时开启
mysql> set global slow_query_log=ON;
mysql> set global slow_query_log_file='/xxx/mysql-slow.log';

###永久开启
[mysqld]
slow_query_log           = 1
slow_query_log_file      = /xxx/mysql-slow.log
long_query_time          = 1

--也可以写成这种形式
slow-query-log           = 1
slow-query-log-file      = /xxx/mysql-slow.log
long-query-time          = 1
然后重启mysql服务

###暂时关闭
mysql> set global slow_query_log=OFF;

###永久关闭
[mysqld]
slow_query_log           = 0

###查看慢记录文件

# Time: 2017-11-22T12:22:32.554299Z
# User@Host: www[www] @  [192.168.10.2]  Id: 580785559
# Query_time: 24.354270  Lock_time: 0.000238 Rows_sent: 1  Rows_examined: 511156
SET timestamp=1511353352;
SELECT * FROM mo_user WHERE email = 'chxxx@hotmail.com' LIMIT 1;

其中参数说明如下：
log 记录的时间：# Time: 2017-11-22T12:22:32.554299Z
SQL 的执行主机：# User@Host: www[www] @ [192.168.10.2] Id: 580785559
SQL 的执行信息（执行时间(单位：s)，锁时间，返回结果行数，查询总行数）：# Query_time: 24.354270 Lock_time: 0.000238 Rows_sent: 1 Rows_examined: 511156;
SQL 执行发生的时间：SET timestamp=1511353352;
SQL 的执行内容：SELECT * FROM mo_user WHERE email = 'chxxx@hotmail.com' LIMIT 1;


###查看记录最多的十条
mysqldumpslow -s r -t 10 mysql-slow.log
```



**三、优点：**

1. 通过创建唯一性索引，可以保证数据库表中的每一行数据的唯一性。
2. 可以加快数据的检索速度
3. 可以加速表与表之间的连接
4. 在使用分组和排序进行检索的时候，可以减少查询中分组和排序的时间

 

**四、缺点**

1. 创建索引和维护索引要耗费时间，这种时间随着数据量的增加而增加。
2. 索引需要占用物理空间，数据量越大，占用空间越大
3. 会降低表的增删改的效率，因为每次增删改索引，都需要进行动态维护

 

**五、什么时候需要创建索引**

1.  主键自动建立唯一索引
2.  频繁作为查询条件的字段应该创建索引
3.  查询中排序的字段创建索引将大大提高排序的速度（索引就是排序加快速查找
4.  查询中统计或者分组的字段；

 

 **六、什么时候不需要创建索引**

1. 频繁更新的字段不适合创建索引，因为每次更新不单单是更新记录，还会更新索引，保存索引文件
2. where条件里用不到的字段，不创建索引；
3. 表记录太少，不需要创建索引；
4. 经常增删改的表；
5. 数据重复且分布平均的字段，因此为经常查询的和经常排序的字段建立索引。注意某些数据包含大量重复数据，因此他建立索引就没有太大的效果，例如性别字段，只有男女，不适合建立索引。