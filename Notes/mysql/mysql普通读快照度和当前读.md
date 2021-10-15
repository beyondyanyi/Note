##### 1.快照读

普通读（也称快照读，英文名：Consistent Read），就是单纯的 SELECT 语句，不包括下面这两类语句：

```
 
```

SELECT ... FOR UPDATE 
SELECT ... LOCK IN SHARE MODE

普通读的执行方式是生成 ReadView，直接利用 MVCC 机制来进行读取，并不会对记录进行加锁。





##### 2.当前读

聊完快照读，再聊聊当前读（也称锁定读，Locking Read）。

2.1 定义

当前读，读取的是最新版本，并且需要先获取对应记录的锁，如以下这些 SQL 类型：

```
 
```

select ... lock in share mode 、

select ... for update、

update 、delete 、insert

 

当然，获取什么类型的锁取决于当前事务的隔离级别、语句的执行计划、查询条件等因素。例如，要 update 一条记录，在事务执行过程中，如果不加锁，那么另一个事务可以 delete 这条数据并且能成功 commit ，就会产生冲突了。所以 update 的时候肯定要是当前读，得到最新的信息并且锁定相应的记录。

当前读是通过 next-key 锁(行记录锁+间隙锁)来是实现的。

> **这里补充下行锁的 3 种算法：**
>
> 行锁（Record Lock）：锁直接加在索引记录上面。
>
> 间隙锁（Gap Lock）：是 Innodb 为了解决幻读问题时引入的锁机制，所以只有在 Read Repeatable 、Serializable 隔离级别才有。
>
> Next-Key Lock ：Record Lock + Gap Lock，锁定一个范围并且锁定记录本身 



3.