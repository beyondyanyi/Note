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





1.文件系统中，

​	创建数据库（schema）时，Mysql将每个保存为数据目录下的一个子目录。

​    创建表（table）时,Mysql将表（table）数据库子目录下创建一个和表同名的.frm文件保存表的定义

​    可以使用 show table status like 'tableName' 查询表的相关信息







##### 转换表的引擎

###### 1.ALTER TABLE

```
例如：ALTER TABLE mytable ENGINE=InnoDB;
此语法可以适用于任何存储引擎，但是需要执行很长时间，Mysql会将数据从原表复制到一张新表中，在复制期间可能会消耗系统所有的I/O能力，同事原表上会加上读锁。所以繁忙的表上执行此操作要特别小心
```

###### 2.导入与导出

```
为了更好地控制转换的过程，可以使用mysqldump工具将数据导出到文件，然后修改文件中CREATE TABLE语句中的存储引擎选项，注意同事修改表名，因为同一个数据库中不能存在相同的表名，即使他们是不通的存储引擎。同时要注意mysqldump默认会自动在CRATE TABLE语句前加上DROP TABLE语句，可能会导致数据丢失
```

###### 3.创建与查询

```
第三种转换的技术综合了第一种方法的高效和第二种方法的安全。不需要导出整个表的数据，而是先创建一个新的存储引擎的表，然后利用INSERT...SELECT语法来导入数据

CREATE TABLE innodb_table like myisam_table;
ALTER TABLE innodo_table ENGINE=InnoDB;
INSERT INTO innodb_table SELECT * FROM myisam_table;
这样操作完成以后，新表是原表的一个全量复制，原表还在，如果需要可以删除原表。
```

备注：pt-online-schema-change工具可以比较简单、方便地执行上述过程，避免手工操作可能导致的失误和繁琐

