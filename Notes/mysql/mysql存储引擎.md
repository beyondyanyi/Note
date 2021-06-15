Mysql5.7支持的存储引擎：

可使用show engines查看支持的存储引擎

```
InnoDB、MyISAM、Memory、Archive、Federated、CSV、BLACKHOLE
```



Archive

```
归档引擎
Archive只支持insert和select操作，在mysql5.5以后支持索引。
Archive拥有很好的压缩机制，它使用zlib压缩库，在记录被请求时会实时压缩，所以它经常被用来当做仓库使用。
```

MRG_MYISAM

```
MRG_MyISAM存储引擎是一组MyISAM表的组合，老版本叫 MERGE 其实是一回事儿。

这些MyISAM表结构必须完全相同，尽管其使用不如其它引擎突出，但是在某些情况下非常有用。

说白了，Merge表就是几个相同MyISAM表的聚合器；Merge表中并没有数据，对Merge类型的表可以进行查询、更新、删除操作，这些操作实际上是对内部的MyISAM表进行操作。
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





#### 如果需要使用不同的存储引擎，请先考虑一下几个因素：

```
1.事务
2.备份
3.崩溃恢复
4.特有的特性
```

