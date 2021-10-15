##### 1.查询慢查询日志的开启状态和慢查询日志储存的位置

```
show variables like '%quer%';
```

2.永久开启慢查询日志

```
修改my.cnf
[mysqld]
slow_query_log=1
slow_query_log_file=/var/lib/mysql/slow-query.log
long_query_time=1
log_queries_not_using_indexes=1



##slow_query_log是否开启慢查询 0关闭 1开启
##slow_query_log_file 慢查询日志路径（需要有写权限）
##long_query_time 慢查询阈值（默认10s）
##log_queries_not_using_indexes 表明记录没有使用索引的sql语句
```

##### 2.慢查询分析工具

```
mysql自带的分析慢查询的工具
mysqldumpslow


常用参数
-s:排序方式： c查询次数 t查询时间 l锁定时间 r返回记录 ac平均查询次数 al平均锁定时间 ar平均返回记录数 at平均查询时间
-t：topN查询
-g:正则表达式
```

