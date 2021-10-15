#### 4.2陷阱：

##### 1.太多的列

##### 2.太多的关联

mysql限制了每个关联操作最多只能有61张表

单个查询最好控制在12个表以内做关联

##### 3.防止过多使用枚举

枚举不适用于需要修改的列，因为需要alter table操作

##### 4.并此发明的NULL

避免使用null,可尽量使用0、某个特殊值、空字符串作为代替。

但是遵循这个原则也不要走极端。





#### 4.3范式和反范式

对于给定的数据通常有很多种表示方式，从完全的范式化到完全的的反范式化，以及两者的折中。



##### 范式的优点和缺点：

```
优点：

范式化的更新操作比反范式化快
范式化的表更小，可以更好的放在内存里，所以执行操作会更快
很少冗余字段意味着检索列表时更少需要distinct或group by


缺点：
 范式化的表通常需要关联，稍微复杂一些的查询语句都会至少需要一次关联，这不但代价昂贵，也可能使一些索引策略无效
```



##### 反范式的优点和缺点：

```
优点：

反范式化的表因为所有数据都在一个表中，可以很好地避免关联


缺点：
范式化的优点反过来就是缺点
```



##### 混用范式化和反范式化：

事实上，完全的范式化和反范式化表都是实验室里才有的东西，在实际中很少会这么极端地使用，在实际中经常需要混用，可能使用部分范式化的表、缓存表等

最常见的反范式化数据的方法是复制或缓存，在不同的表中存储相同的特定列。在Mysql5.0和更新版本中，可以使用触发器更新缓存值，这使得这样的方案变得更简单



##### 4.4缓存表和汇总表

有时提升性能最好的方法是在同一张表中保存衍生的冗余数据。



汇总表：

同级最近24小时的消息发送总数等（每小时生成一张汇总表）



缓存表：

存储那些可以比较简单地从schema其他表获取（但是每次获取的速度比较慢）数据的表





当重建汇总表和缓存表时，通常需要保证数据在操作时依然可用。这就需要通过使用“影子表”来实现。

例如：

​	drop table if exists my_summary_new,my_summary_old;

​	##新建影子表并复制数据

​    create table my_summary_new like my_summary;

​    ##通过原子操作重命名两个表名完成数据重建

​    rename table my_summary to my_summary_old,my_summary_new to my_summary;



##### 4.4.1物化视图

mysql不支持原生的物化视图，需要借助第三方工具实现（Flexviews）

##### 4.4.2计数器表

计数器表可单独建一个表用来计数，常规的计数器表记录上有互斥锁，只能串行执行，不支持并发，要获取更高的并发更新性能，可将计数器保存在多行记录中；

```
1)普通的计数
create table hit_count(
    cnt int unsigned not null
)   engine=InnoDB;

##计数
update hit_counter set  cnt=cnt+1;
##获取统计结果
select cnt from hit_counter;

2)高并发计数
create table hit_count(
	slot tinyint unsigned not null primary key,
    cnt int unsigned not null
)   engine=InnoDB;

##预先插入一百行记录,随机槽更新
update hit_counter set cnt=cnt+1 where slot=rand()*100;
##获取统计结果
select sum(cnt) from hit_counter;


3)其他计数（每天重置开始一个新的计数器）,on duplicate key update实现
create table daily_hit_count(
	day date not null,
	slot tinyint unsigned not null,
    cnt int unsigned not null,
    primary key(day,slot)
)   engine=InnoDB;

##插入新纪录（如重复则更新，同样是100个随机槽）
insert into daily_hit_count(day,slot,cnt)
			values(current_date,rand()*100,1)
			on duplicate key update cnt=cnt+1;
			
如果想避免表行数过多，还可以定期合并每天的结果

```



##### 4.5加快alter table操作速度

mysql执行大部分修改表的操作是用新的结构创建一个新的空表，从旧表中查出所有数据插入新表，然后删除旧表。（这样操作可能花费很多时间，如果内存不足而表有很大，而且还有很多索引的情况下尤其如此。大部分alter table操作会导致服务中断）



alter table常用技巧：

```
1.在另外一台不提供服务的机器上执行alter table操作，然后和提供服务的主库进行切换
2."影子拷贝"，创建一张和源表无关的新表，然后通过重命名和删表操作交换两张表。（第三放工具：online schema change、openark toolkit、Percona Toolkit）

```

不是所有操作都会引起表的重建，比如修改列的默认值，有两种方式：

1.引起重建表

alter table sakila.film

​	modify column rental_duration tinyint(3) not null default 5;



2.不引起重建表(这个语句会直接修改.frm文件而不涉及表数据，所以这个操作是非常快的)

alter table sakila.film

​	alter column rental_duration set default 5;



##### 4.5.1只修改.frm文件

alter column 设置或删除列的默认值，该操作会直接修改.frm文件而不涉及表数据

modify column 会引起表的重建，非常慢

change column 列的重命名、列类型的变更以及列位置的移动（会引起表的重建）



下面这些操作是**有可能**不需要重建表的（官方文档没有记录的奇技淫巧）：

```
移除一个列的auto_increment属性。
增加、移除，或更改enum和set常量。如果移除的是已经有行数据用到其值的常量，查询将会返回一个空字符串
```



创建一个和原表相同结构的表，然后替换.frm,达成修改而不引发重建的目的

```
1.创建一张有相同结构的空表，并进行所需要的修改
2.执行flush tables with read lock。这将会关闭所有正在使用的表，并且禁止任何表被打开
3.交换.frm文件
4.执行unlock tables来释放第2步的读锁
```





4.5.2快速创建MyISAM索引

（官方文档没有记录的操作）

```
1.用需要的表结构创建一张表，但是不包括索引
2.载入数据到表中以构建.MYD文件
3.按照需要的结构创建另外一张空表，这次要包含索引。这会创建需要的.frm和.MYI文件
4.获取读锁并刷新表
5.重命名第二张表的.frm和.MYI文件，让MySQL认为是第一张表的文件
6.释放读锁
7.使用repair table来重建表的索引。该操作会通过排序来创建所有索引，包括唯一索引
```

