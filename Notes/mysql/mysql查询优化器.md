##### 查询优化器提示（hint）:

一般指改变 mysql 优化器的执行计划， 除非业务需要， 不建议这样做。



```
1.HIGH_PRIORITY :
	提示mysql该语句优先执行 
  LOW_PRIORITY：
	提示mysql该语句处于等待执行， 有可能出现一直等待状态
	
	
  这两个提示针对表级锁有用， 不要使用在Innodb中， 由于优先级排序操作， 禁用并发， 严重影响性能。
  这两个提示只是控制mysql访问数据的队列顺序， 不会影响具体的请求对资源的控制

2.DELAYED
   对于 INSERT 和 REPLACE 有效。
   mysql 会将提示语句立即返回给客户端， 并将插入的行数据放入到缓冲区， 然后在表空闲的时候批量将数据写入。
   并不是所有的存储引擎都支持， 并且可能影响 LAST_INSERT_ID() 的功能
   
   
3.STRAIGHT_JOIN
	在数据量大的联表查询中灵活运用的话，直接影响关联顺序， 减少statistics的时间， 能大大缩短查询时间
    STRAIGHT_JOIN功能同join类似，但能让左边的表来驱动右边的表，能改表优化器对于联表查询的执行顺序
    
4.SQL_SMALL_RESULT:
	提示优化器结果集比较小，可以将结果放在内存中的索引临时表，以避免排序和IO操作
  SQL_BIG_RESULT：
  	提示优化器结果集比较大，建议使用磁盘临时表做排序操作
  	
5.SQL_BUFFER_RESULT
    提示优化器将结果集放入到临时表中，服务器端缓存，尽快释放表锁
    
6.SQL_CACHE：
	查询结果放入查询缓存
  SQL_NO_CACHE:
  	查询结果不放入查询缓存
  	
7.SQL_CALC_FOUND_ROWS
  不是优化器提示， 不影响优化器的执行计划， 但会让mysql返回的结果集中包含更多信息
   
  对应FOUND_ROW();
8.FOR UPDATE 、LOCK IN SHARE MODE:
  
   不是优化提示器， 控制SELECT语句的锁机制， 只对行级锁有效， InnoDB支持这两个提示
   
9.USE INDEX、IGNORE INDEX、FORCE INDEX、FOR GROUP BY、FOR ORDER BY
    提示优化器使用不使用索引
  USE INDEX 、 FORCE INDEX 使用基本一致，FORCE INDEX 更加强调全表扫描代价更大
  
10.影响优化器的参数设置
	 optimizer_search_depth: 控制优化器获取执行计划的限度， 如果查询时间长时间处于statistics状态， 可以调低此参数。
     optimizer_prune_level: mysql默认打开， 让优化器根据扫描行数决定是否跳过某些执行计划。
     optimizer_switch： 包含一些开启/关闭优化器特性的标志位。

```

