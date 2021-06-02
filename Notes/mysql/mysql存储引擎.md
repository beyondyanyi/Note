Mysql5.7支持的存储引擎：

可使用show engines查看支持的存储引擎

```
InnoDB、MyISAM、Memory、Archive、Federated、CSV、BLACKHOLE
```



MyISAM

```
MyISAM存储引擎不支持事务，不支持外键，索引采用的是非聚集性索引。所以MyISAM的表在磁盘上存储有三个文件，文件扩展名
	.frm：存储表的结构
	.MYI：存储表的索引结构
	.MYD：存储表的数据
```



MEMORY

```
memory使用内存来创建表，每个memory表的实际内存存储在内存中，表的定义结构在磁盘中，memory存储引擎访问数据非常快，memory的数据结构是通过哈希索引，一旦服务关闭，内存上的数据就会丢失
```



InnoDB

```
InnoDB存储引擎提供了具有提交、回滚等事务功能，支持外键，支持自动增长列等功能。InnoDB采用的是聚集性索引，索引和数据是存储在同一个文件下的，所以InnoDB的表在磁盘上有两个文件：
	.frm：存储表的结构
	.ibd：存储索引和表的数据
```

