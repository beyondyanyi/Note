#查看正在被锁定的表（in_use多少个线程在使用 name_locked 是否被锁）
show OPEN TABLES where In_use > 0;

#查询那些线程正在运行
show processlist;

#查看mysql服务器上的版本
select version();

#查看表的引擎
show create table dept_emp;

#查询当前默认的存储引擎
show variables like '%storage_engine%';

#查看表的信息

show table status like 'departments';

```
name 表明
engine 存储引擎
version
row_format 行的格式
rows 行数
avg_row_length 平均每行包含的字节数
data_length 表的数据长度
max_data_length 表数据的最大的容量，该值和存储引擎有关
index_length 索引的大小
data_free 表示已分配但目前没有使用的空间
auto_increment 下一个auto_increment值
create_time 表创建时间
update_time 表最后更新时间
check_time 使用check table命令或myisamchk工具最后一次检查表的时间
collation 表的默认字符集和字符排序规则
checksum  如果启用，保存的是整个表的实时校验和 
create_options 创建表时指定的其他选项
comment 该列包含了一些其他的额外信息；InnoDB表，则保存的是InnoDB表空间的剩余空间。如果是一个视	图，则该列包含“VIEW”的文本字样
```



##事务

#查询有多少事务正在执行
select * from information_schema.innodb_trx;
#查看自动提交模式
show variables like 'AUTOCOMMIT';
#设置自动提交模式(1启 0禁)
set  AUTOCOMMIT=1;

#设置事务隔离级别
set session transaction isolation level xxx;