#### 读锁（共享锁 shared lock）

```
SELECT * FROM table_name WHERE ... LOCK IN SHARE MODE
```

##### 详解

即事务A 使用共享锁 获取了某条（或者某些）记录时，事务B 可以读取这些记录，可以继续添加共享锁，但是不能修改或删除这些记录（当事务B 对这些数据修改或删除时会进入阻塞状态，直至锁等待超时或者事务A提交）

##### 使用场景

读取结果集的最新版本，同时防止其他事务**产生更新该结果集**
主要用在需要数据依存关系时确认某行记录是否存在，并确保没有人对这个记录进行UPDATE或者DELETE操作

##### 注意事项

使用读锁时，避免产生如下操作

```csharp
事务1
BEGIN;
select * from sys_user where id = 1 LOCK IN SHARE MODE; (步骤1)

update sys_user set username = "taven" where id = 1; (步骤3，发生阻塞)
COMMIT;
```

```
事务2
BEGIN;
select * from sys_user where id = 1 LOCK IN SHARE MODE; (步骤2)

update sys_user set username = "taven" where id = 1; (步骤4，死锁)
COMMIT;
```

##### 分析

根据我们之前对读锁定义可知，当有事务拿到一个结果集的读锁时，其他事务想要更新该结果集，需要拿到读锁的事务提交（释放锁）。而上述情况两个事务分别拿到了读锁，而且都有update 操作，两个事务互相等待造成死锁（都在等待对方释放读锁）



#### 写锁（排他锁 exclusive lock）

```
SELECT * FROM table_name WHERE ... FOR UPDATE
```

##### 详解

一个写锁会阻塞其他的**读锁和写锁**

即事务A 对某些记录添加**写锁**时，事务B 无法向这些记录添加写锁或者读锁（不添加锁的读取是可以的），事务B 也无法执行对 锁住的数据 update delete

##### 使用场景

读取结果集的最新版本，同时防止其他事务产生**读取或者更新该结果集**。
例如：并发下对商品库存的操作

##### 注意事项

在使用读锁、写锁时都需要注意，读锁、写锁属于行级锁。即事务1 对商品A 获取写锁，和事务2 对商品B 获取写锁互相不会阻塞的。需要我们注意的是我们的SQL要**合理使用索引**，当我们的SQL 全表扫描的时候，**行级锁会变成表锁**。
 使用`EXPLAIN`查看 SQL是否使用了索引，扫描了多少行



#### 乐观锁

- 上述介绍的是**行级锁**，可以最大程度地支持并发处理（同时也带来了最大的锁开销）乐观锁是一种逻辑锁，通过数据的版本号（vesion）的机制来实现，极大降低了数据库的性能开销。

- 我们为表添加一个字段 version，读取数据时将此版本号一同读出，之后更新时，对此版本号**+1**，同时将提交数据的version 与数据库中对应记录的当前version 进行比对，如果提交的数据版本号大于数据库表当前版本号，则予以更新，否则认为是过期数据



```bash
update t_goods 
set status=2,version=version+1
where id=#{id} and version < #{version}; // 更新前将version自增
```

或者

```bash
update t_goods 
set status=2,version=version+1
where id=#{id} and version = #{version}; // 更新前version 不自增
```

